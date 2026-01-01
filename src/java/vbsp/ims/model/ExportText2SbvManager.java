/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vbsp.ims.dao.ExportText2SbvDao;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.export.excel.ExportFileHstdCt;
import vbsp.ims.export.excel.SbvExcelTemplateExport;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.zip.FileZip;
import vbsp.ims.restapi.*;
import java.util.Date;
import java.util.Locale;
import vbsp.ims.util.*;

/**
 *
 * @author Trung sua ngay 14/jan/2015
 */
public class ExportText2SbvManager {

    //--------------------------------------------------------------------------
    private static List<ListValue> lstRptGroupObj;
    private static List<ListValue> lstRptPeriod;
    private static List<String> lstOfTextFile = new ArrayList<>();
    private static List<DownloadFileInfor> filesList = new ArrayList<>();
    private static ExportText2SbvDao exportDao;
    private static SbvExcelTemplateExport templateExport;
    private static final List<String> zipFileList = new ArrayList<>();

    //--------------------------------------------------------------------------
    public ExportText2SbvManager() {
        exportDao = new ExportText2SbvDao();
        lstRptGroupObj = exportDao.getExportGroupReport("All", -1);
        lstRptPeriod = new ArrayList<>();
        lstRptPeriod.add(new ListValue("D", "Ngày"));
        lstRptPeriod.add(new ListValue("2D", "3Kỳ/Tháng"));
        lstRptPeriod.add(new ListValue("3D", "2Kỳ/Tháng"));
        lstRptPeriod.add(new ListValue("M", "Tháng"));
        lstRptPeriod.add(new ListValue("Q", "Quý"));
        lstRptPeriod.add(new ListValue("A", "Năm"));
    }

    //--------------------------------------------------------------------------
    public List<ListValue> getLstRptGroupObj(String username, int reportGrade) {
        lstRptGroupObj = exportDao.getExportGroupReport(username, reportGrade);
        return lstRptGroupObj;
    }

    public static List<String> getLstOfTextFile() {
        return lstOfTextFile;
    }

    public static void setLstOfTextFile(List<String> lstOfTextFile) {
        ExportText2SbvManager.lstOfTextFile = lstOfTextFile;
    }

    public List<ListValue> getLstRptPeriod(String report) {
        if (report.toUpperCase().equals("ALL")) {
            return lstRptPeriod;
        } else {
            return exportDao.getExportPeriod(report);
        }
    }

    public void setLstRptPeriod(List<ListValue> lstRptPeriod) {
        ExportText2SbvManager.lstRptPeriod = lstRptPeriod;
    }

    public static List<String> getZipFileList() {
        return zipFileList;
    }

    public static List<DownloadFileInfor> getFilesList() {
        return filesList;
    }

    public static void setFilesList(List<DownloadFileInfor> filesList) {
        ExportText2SbvManager.filesList = filesList;
    }

    //--------------------------------------------------------------------------
    public boolean exportTextFile(String report, String lstOfPos,
            String reportDate, String considateFlag, String period, String sbvSendIndiGroup,
            boolean send2Sbv, boolean send9acc) throws ParseException, Exception {
        String mapReport = exportDao.getMappingReport(report);
        templateExport = new SbvExcelTemplateExport();
        Date dReportDate = DateUtil.stringToDate(reportDate, "dd-MMM-yyyy");
        String apiReportDate = DateUtil.dateToString(dReportDate, "yyyyMMdd");

        ArrayList<String> listOfPos
                = (ArrayList<String>) DefineFun.string2Array(lstOfPos, ",", 2);
        String textFilePath = "";
        lstOfTextFile.clear();
        filesList.clear();
        zipFileList.clear();

        createDir41stTime();

        if (listOfPos.size() > 0) {
            String strTimeFile = Long.toString(System.currentTimeMillis());
            String zipFile = "FileNen_" + strTimeFile + ".zip", zipPath = "";
            ArrayList<String> fullPathList = new ArrayList<>();
            for (String pos_cd : listOfPos) {
                switch (mapReport) {
                    case "B05A":
                        textFilePath = exportDao.getDataExportFile(mapReport, pos_cd, considateFlag,
                                reportDate, period, "", Define.M_ROOT + Define.M_REPORT_XLS);
                        zipPath = Define.M_ROOT + Define.M_REPORT_XLS + zipFile;
                        break;
                    case "TT31_B29":
                    case "TT31_B20":
                    case "TT31_B35":
                    case "TT31_B09":
                    case "TT31_B28":
                    case "TT31_B30":
                    case "01_NHCS":
                    case "BC_30A":
                    case "TT31_B20TM":
                    case "TT31_B29TM":
                    case "B65_NHNN":
                    case "PHI":
                    case "HOA_HONG":
                        SimpleDateFormat src = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                        SimpleDateFormat dest = new SimpleDateFormat("yyyyMMdd");
                        int reportDateNum = Integer.parseInt(dest.format(src.parse(reportDate)));
                        List<String> posList = Arrays.asList(pos_cd.split(","));

                        List<String> generatedFiles = new ArrayList<>();
                        for (String pos : posList) {
//                            System.out.println("pos_cd = " + pos);

                            ArrayList<CommisionFeeModel> commisionData = getCommisionFeeFromApi(pos, apiReportDate, mapReport.equals("PHI") ? "F" : "C");

                            textFilePath = null;

                            if (reportDateNum > 20250101) {
                                ensureFolder(Define.M_ROOT + Define.M_REPORT_XLS);

                                // Nếu không có dữ liệu, tạo Excel rỗng
                                if (commisionData == null) {
                                    commisionData = new ArrayList<>();
                                }

                                textFilePath = templateExport.generateExcelFromCommisionModel(
                                        commisionData,
                                        pos,
                                        considateFlag,
                                        reportDate,
                                        period,
                                        Define.M_ROOT + Define.M_REPORT_XLS
                                );

                            } else {
                                ensureFolder(Define.M_ROOT + Define.M_REPORT_TXT);

                                textFilePath = Define.M_ROOT + Define.M_REPORT_TXT
                                        + "/fee_" + pos + "_" + apiReportDate + ".txt";

                                if (commisionData == null) {
                                    commisionData = new ArrayList<>();
                                }
                                exportToFile(textFilePath, commisionData);
                            }

                            generatedFiles.add(textFilePath);
                        }

                        if (!generatedFiles.isEmpty()) {
                            zipPath = Define.M_ROOT + Define.M_REPORT_XLS + zipFile;
                            ensureFolder(Define.M_ROOT + Define.M_REPORT_XLS);
                            FileZip.ZipFileFromArray(new ArrayList<>(generatedFiles), zipPath);
                        }

                        // Zip tất cả file sau loop
                        if (!generatedFiles.isEmpty()) {
                            zipPath = Define.M_ROOT + Define.M_REPORT_XLS + zipFile;
                            ensureFolder(Define.M_ROOT + Define.M_REPORT_XLS);
                            FileZip.ZipFileFromArray(new ArrayList<>(generatedFiles), zipPath);
                        }

                        break;

                    case "SBV-BAL":
                        String send2sbvStr;
                        if (send2Sbv) {
                            send2sbvStr = "Y";
                        } else {
                            send2sbvStr = "N";
                        }
                        if (send9acc) {
                            send2sbvStr = send2sbvStr + "-Y";
                        } else {
                            send2sbvStr = send2sbvStr + "-N";
                        }
                        textFilePath = exportDao.getDataExportFile(mapReport, pos_cd, considateFlag,
                                reportDate, period, send2sbvStr, Define.M_ROOT + Define.M_REPORT_TXT);
                        zipPath = Define.M_ROOT + Define.M_REPORT_TXT + zipFile;
                        break;
//                    case "HOA_HONG":
//                        textFilePath = Define.M_ROOT + Define.M_REPORT_TXT + "/commision_" + pos_cd + "_" + apiReportDate + ".txt";
//                        exportCommisionFee(textFilePath, pos_cd, apiReportDate, "C");
//                        zipPath = Define.M_ROOT + Define.M_REPORT_TXT + zipFile;
//                        break;
                    case "EX050001":
                        textFilePath = exportDao.getDataExportFile(mapReport, pos_cd, considateFlag,
                                reportDate, period, "", Define.M_ROOT + Define.M_REPORT_XLS);
                        zipPath = Define.M_ROOT + Define.M_REPORT_TXT + zipFile;
                        break;
                    default:
                        textFilePath = exportDao.getDataExportFile(
                                mapReport,
                                pos_cd,
                                considateFlag,
                                reportDate,
                                period,
                                sbvSendIndiGroup,
                                Define.M_ROOT + Define.M_REPORT_TXT);
                        zipPath = Define.M_ROOT + Define.M_REPORT_TXT + zipFile;
                        break;
                }

                lstOfTextFile.add(textFilePath);
                FileInfo file = new FileInfo(new File(textFilePath));
                filesList.add(new DownloadFileInfor(file.getName(), textFilePath,
                        DefineFun.round_up((double) file.getSize() / 1000) + " KB"));
                fullPathList.add(file.getAbsolutePath());

                // Bo sung them phan sinh file thuyet minh
                if (mapReport.equals("SBV-INDI")
                        && !sbvSendIndiGroup.isEmpty()) {
                    textFilePath = exportDao.getSbvTextFile_TM("SBV-INDI-TM", pos_cd, considateFlag,
                            reportDate, period, sbvSendIndiGroup, Define.M_ROOT + Define.M_REPORT_TXT);
                    zipPath = Define.M_ROOT + Define.M_REPORT_TXT + zipFile;
                    lstOfTextFile.add(textFilePath);
                    file = new FileInfo(new File(textFilePath));
                    filesList.add(new DownloadFileInfor(file.getName(), textFilePath,
                            DefineFun.round_up((double) file.getSize() / 1000) + " KB"));
                    fullPathList.add(file.getAbsolutePath());
                }
            }
            if (fullPathList.size() > 0) {
                try {
                    FileZip.ZipFileFromArray(fullPathList, zipPath);
                    zipFileList.add(zipFile);
                    zipFileList.add(zipPath);
                } catch (Exception ex) {
                    Logger.getLogger(ExportText2SbvManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }

    protected void createDir41stTime() {
        /*Thu muc TXT*/
        String dirPath;
        dirPath = Define.M_ROOT + Define.M_REPORT_TXT;
        File saveDir = new File(dirPath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        /*Thu muc XLS */
        dirPath = Define.M_ROOT + Define.M_REPORT_XLS;
        saveDir = new File(dirPath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
    }

// type : C - Hoa hong, F - Phi    
    void exportCommisionFee(String commisionFile, String posCode, String reportDate, String type) {
        DuLieuNTService service = new DuLieuNTService();
        //String commisionFile = Define.M_ROOT + Define.M_REPORT_TXT + "/commision.txt";
        //String feeFile = Define.M_ROOT + Define.M_REPORT_TXT + "/fee.txt";
        //Date dReportDate = DateUtil.stringToDate(reportDate, "dd-MMM-yyyy");
        //String apiReportDate = DateUtil.dateToString(dReportDate, "yyyyMMdd");
        ArrayList<CommisionFeeModel> commisionData = service.getCommisionFeeData(posCode, reportDate, type);
        if (commisionData != null && commisionData.size() > 0) {
            exportToFile(commisionFile, commisionData);
        }
//        ArrayList<CommisionFeeModel> feeData = service.getCommisionFeeData(posCode, reportDate, "F"); 
//        if (feeData != null && feeData.size() > 0)
//        {
//            exportToFile(feeFile, feeData);
//        }

    }

    void exportToFile(String filePath, ArrayList<CommisionFeeModel> data) {
        try {
            Writer outfile = null;
            try {
                outfile = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(filePath), "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                CoreLogger.error(ExportFileHstdCt.class.getCanonicalName()
                        + " Loi khi khoi tao UTF-8 ExportFileHstdCt -> " + ex.getMessage());
            }
            try {
                for (int i = 0; i < data.size(); i++) {
                    String strRow = data.get(i).toString() + "\r\n";
                    Writer append = outfile.append(strRow);
                }
                outfile.flush();
                outfile.close();
            } catch (IOException efile) {
                CoreLogger.error(ExportFileHstdCt.class.getCanonicalName()
                        + " Loi khi ghi file ExportFile2Sbv -> " + efile.getMessage());
            }
        } catch (Exception e) {
            CoreLogger.error(ExportFileHstdCt.class.getCanonicalName()
                    + " Loi khi getdata ExportFile2Sbv -> " + e.getMessage());
        }
    }

    public ArrayList<CommisionFeeModel> getCommisionFeeFromApi(String posCode, String reportDate, String flagType) {
        DuLieuNTService service = new DuLieuNTService();
        ArrayList<CommisionFeeModel> result = new ArrayList<>();

        ArrayList<CommisionFeeModel> apiResponse
                = service.getCommisionFeeData(posCode, reportDate, flagType);
        if (apiResponse == null || apiResponse.isEmpty()) {
            return result;
        }
        for (CommisionFeeModel r : apiResponse) {
            CommisionFeeModel m = new CommisionFeeModel();
            m.setPosCode(r.getPosCode());
            m.setRefNo(r.getRefNo());
            m.setValDate(r.getValDate());
            m.setLegacyAc(r.getLegacyAc());
            m.setAccountPosCode(r.getAccountPosCode());
            m.setFlagDRCR(r.getFlagDRCR());

            m.setAmount(r.getAmount());

            m.setReason(r.getReason());
            m.setCurrency(r.getCurrency());
            m.setAccountType(r.getAccountType());
            result.add(m);
        }

        return result;
    }

    private void ensureFolder(String folderPath) {
        File dir = new File(folderPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
