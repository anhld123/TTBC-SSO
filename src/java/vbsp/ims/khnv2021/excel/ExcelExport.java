/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.khnv2021.excel;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vbsp.ims.canhbaosaisottt.P0001;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.fileutil.FileUtil;
import vbsp.ims.khnv2021.ReportTemplate;
import vbsp.ims.khnv2021.dao.DaoMau02;
import vbsp.ims.khnv2021.dao.DaoMau01A;
import vbsp.ims.khnv2021.dao.XDKHDao2021;
import vbsp.ims.khnv2021.model.FileExportInfo;
import vbsp.ims.khnv2021.model.Mau01AModel;
import vbsp.ims.khnv2021.model.Mau02Model;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.DownloadFileInfor;
import vbsp.ims.model.ExportText2SbvManager;
import vbsp.ims.model.FileInfo;
import vbsp.ims.query.ImsFillParaMeter;
import vbsp.ims.query.ImsPlSqlQuery;
import vbsp.ims.zip.FileZip;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import vbsp.ims.khnv2021.model.DistrictInfo;
import vbsp.ims.model.khnv.POSModel;
import vbsp.ims.nhaptaycn.action.QT_DULIEU_NT_50;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;

/**
 *
 * @author HP
 */
public class ExcelExport {

    public ExcelExport() {
    }

    public FileExportInfo xuatExcelMau01(List<String> lstCommune, String savedDirPath, String namBc, String dotBc, String pos_cd) {
        String filePath = "", fileName = "";
        List<String> lstOfTextFile = new ArrayList<>();
        List<DownloadFileInfor> filesList = new ArrayList<>();
        List<String> zipFileList = new ArrayList<>();
        lstOfTextFile.clear();
        filesList.clear();
        zipFileList.clear();
        ArrayList<String> fullPathList = new ArrayList<>();
        String strTimeFile = Long.toString(System.currentTimeMillis());
        String zipFile = "FileNen_KHNV01_" + strTimeFile + ".zip", zipPath = "";
        try {

            for (String value : lstCommune) {

                String save_id = "KHNV01";

                HashMap<String, String> paramHashMap = new HashMap<>();
//                String sPos_cd = "";
                String stringParaPos_cd = "";
                String sPosFlag = "";
                //xy lay lay cac tham so cho vao hashmap
                Map mapCollectPara = new HashMap();

                //xu ly cho export file ra PDF hoac la Excel
                String strCurrDate = new SimpleDateFormat("ddMMyyyy").format(new Date());
                //duong dan chua file tren o dia + Define.M_REPORT_XLS
                String strPathSave = savedDirPath;
                //Ham nay lay ra ten file bao cao can tao, ten file jasper report

                String strFileSave = save_id + "_" + value
                        + "_" + strCurrDate
                        + "_" + strTimeFile.substring(strTimeFile.length() - 4, strTimeFile.length());

                strPathSave += Define.M_REPORT_XLS;
                strFileSave += ".XLSX";
                filePath = strFileSave;
                File Checkpath = new File(strPathSave);
                if (!Checkpath.exists()) {
                    System.out.println("Da tao thu muc: " + strPathSave);
                    Checkpath.mkdirs();
                }
                //Xuat file du lieu o day

                XDKHDao2021 daoQuery = new XDKHDao2021();

//                        setQuery(daoQuery.getQuery(save_id, new DaoConnect().getConnect()));
                ImsPlSqlQuery plsql = new ImsPlSqlQuery();

                Date sdf = new Date();
                try {
                    sdf = new SimpleDateFormat("dd-MMM-yyyy").parse("31-may-2021");
                } catch (ParseException ex) {
                    Logger.getLogger(P0001.class.getName()).log(Level.SEVERE, null, ex);
                }
                paramHashMap.put("PD_REPORT_DATE", new SimpleDateFormat("dd-MMM-yyyy").format(sdf));

                mapCollectPara.put("PD_REPORT_DATE",
                        ImsFillParaMeter.newInstance("VARCHAR2", new SimpleDateFormat("dd-MMM-yyyy").format(sdf)));
//                sPos_cd = "000000";
                stringParaPos_cd = "PV_POS_CD";
                sPosFlag = "N";

                daoQuery.getDataExp(save_id, paramHashMap, pos_cd, stringParaPos_cd, sPosFlag, strPathSave + strFileSave, namBc, dotBc, value);

                //Kiem tra xem file da tao thanh cong chua
                File filerpt = new File(strPathSave + strFileSave);
                fileName = strPathSave + strFileSave;

                lstOfTextFile.add(fileName);
                FileInfo file = new FileInfo(new File(fileName));
                filesList.add(new DownloadFileInfor(file.getName(), fileName,
                        DefineFun.round_up((double) file.getSize() / 1000) + " KB"));
                fullPathList.add(file.getAbsolutePath());
                zipPath = Define.M_ROOT + Define.M_REPORT_XLS + zipFile;
            }

            if (fullPathList.size() > 1) {
                try {
                    FileZip.ZipFileFromArray(fullPathList, zipPath);
                    zipFileList.add(zipFile);
                    zipFileList.add(zipPath);
                } catch (Exception ex) {
                    Logger.getLogger(ExportText2SbvManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                filePath = zipFile;
                fileName = zipPath;
            }

            System.gc();
            return new FileExportInfo(fileName, filePath);
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " get_data_xaydungkh " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataXayDungKhDetail " + ex.getMessage());
            return null;
        }
    }

    public FileExportInfo xuatExcelMau01B(String posCode, List<POSModel> lstCommune, String reportDate, String namBc, String dotBc, String savedDirPath) {
        String filePath = "", fileName = "";
        List<String> lstOfTextFile = new ArrayList<>();
        List<DownloadFileInfor> filesList = new ArrayList<>();
        List<String> zipFileList = new ArrayList<>();
        lstOfTextFile.clear();
        filesList.clear();
        zipFileList.clear();
        ArrayList<String> fullPathList = new ArrayList<>();
        String strTimeFile = Long.toString(System.currentTimeMillis());
        String zipFile = "FileNen_KHNV01_" + strTimeFile + ".zip", zipPath = "";
        try {
            for (POSModel commune : lstCommune) {
                if (commune.getId().equals("000000")) {
                    continue;
                }
                //xu ly cho export file ra PDF hoac la Excel
                Date dReportDate = new SimpleDateFormat("dd-MMM-yyyy").parse(reportDate);
                String strCurrDate = new SimpleDateFormat("ddMMyyyy").format(dReportDate);
                String strPathSave = savedDirPath;
                //Ham nay lay ra ten file bao cao can tao, ten file jasper report
                String strFileSave = ReportTemplate.MAU_01B + "_" + posCode + "_" + commune.getId()
                        + "_" + strCurrDate;

                strPathSave += Define.M_REPORT_XLS;
                strFileSave += ".XLSX";
                filePath = strFileSave;

                String templateFile = savedDirPath + Define.M_EXCEL_TEMP + "/KHNV/KHNV_DK01B.xlsx";
                fileName = strPathSave + strFileSave;
                File source = new File(templateFile);
                File dest = new File(fileName);

                FileUtil.copyFile(source, dest);

                XDKHDao2021 daoXdkh = new XDKHDao2021();
                List<POSModel> subCommuneList = daoXdkh.getSubCommuneList(posCode, commune.getId());

                // Style
                XSSFCellStyle orderStyle;
                // Get data
                if (subCommuneList.size() > 0) {

                    // Fill data              
                    XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new java.io.FileInputStream(fileName));
                    XSSFSheet sheet = xssfWorkbook.getSheetAt(0);

                    XSSFRow titleRow = sheet.getRow(3);
                    if (titleRow == null) {
                        titleRow = sheet.createRow(3);
                    }

                    XSSFCell titleCell = titleRow.getCell(1, Row.CREATE_NULL_AS_BLANK);
                    fillTitle(titleCell, "NHU CẦU VỐN TÍN DỤNG CHÍNH SÁCH NĂM " + namBc);

                    XSSFRow subTitleRow = sheet.getRow(4);
                    if (subTitleRow == null) {
                        subTitleRow = sheet.createRow(4);
                    }

                    XSSFCell subTitleCell = subTitleRow.getCell(1, Row.CREATE_NULL_AS_BLANK);
                    fillTitle(subTitleCell, "XÃ/PHƯỜNG/THỊ TRẤN: " + commune.getDesc().toUpperCase());

                    XSSFRow codeRow = sheet.getRow(10);
                    if (codeRow == null) {
                        codeRow = sheet.createRow(10);
                    }

                    XSSFRow nameRow = sheet.getRow(7);
                    if (nameRow == null) {
                        nameRow = sheet.createRow(7);
                    }

                    int col = 0;

                    DaoMau01A daoMau01A = new DaoMau01A();

                    XSSFCellStyle numberStyle;
                    XSSFDataFormat format = xssfWorkbook.createDataFormat();

                    for (int i = 0; i < subCommuneList.size(); i++) {

                        if (subCommuneList.get(i).getId().equals("000000")) {
                            continue;
                        }

                        List<Mau01AModel> lstData = daoMau01A.getExportData(posCode, commune.getId(), subCommuneList.get(i).getId(), reportDate);

                        XSSFCell codeCell = codeRow.getCell(col + 4, Row.CREATE_NULL_AS_BLANK);
                        orderStyle = codeCell.getCellStyle();
                        orderStyle.setAlignment(HorizontalAlignment.LEFT);
                        orderStyle.setLocked(true);
                        codeCell.setCellValue(subCommuneList.get(i).getId());

                        XSSFCell nameCell = nameRow.getCell(col + 4, Row.CREATE_NULL_AS_BLANK);
                        orderStyle = nameCell.getCellStyle();
                        orderStyle.setAlignment(HorizontalAlignment.LEFT);
                        orderStyle.setLocked(true);
                        nameCell.setCellValue(subCommuneList.get(i).getDesc());

                        for (int j = 11; j < 106; j++) {
                            XSSFRow dataRow = sheet.getRow(j);
                            XSSFCell dataCodeCell = dataRow.getCell(0, Row.CREATE_NULL_AS_BLANK);
                            XSSFCell dataPrinCell = dataRow.getCell(col + 4, Row.CREATE_NULL_AS_BLANK);
                            String code = dataCodeCell.getStringCellValue();
                            for (int k = 0; k < lstData.size(); k++) {
                                if (lstData.get(k).code.equals(code)) {
                                    numberStyle = dataPrinCell.getCellStyle();
                                    numberStyle.setDataFormat(format.getFormat("#,##0.00"));
                                    numberStyle.setAlignment(HorizontalAlignment.RIGHT);
                                    //numberStyle.setFont(font);
                                    numberStyle.setLocked(false);
                                    dataPrinCell.setCellStyle(numberStyle);
                                    dataPrinCell.setCellValue(lstData.get(k).d2);
                                    break;
                                }
                            }
                        }

                        col++;

                    }

                    //final FormulaEvaluator evaluator = xssfWorkbook.getCreationHelper().createFormulaEvaluator();
                    for (int l = 3; l < 201; l++) {
                        sheet.autoSizeColumn(l);
//                        for (int m = 11; m <= sheet.getLastRowNum(); m++) 
//                        {
//                            final XSSFRow row = sheet.getRow(m);
//                            if (row != null) 
//                            {
//                                XSSFCell cell = row.getCell(l);
//                                if (cell != null && (cell.getCellType() != Cell.CELL_TYPE_BLANK)) 
//                                {
//                                    //formula type
//                                    if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
//                                        evaluator.evaluate(cell);                                    
//                                    }
//                                }
//                            }
//                        }
                    }

//                    for (int m = sheet.getLastRowNum(); m >= 11 ; m--) 
//                    {
//                        final XSSFRow row = sheet.getRow(m);
//                        if (row != null) 
//                        {
//                            XSSFCell cell = row.getCell(3);
//                            if (cell != null && (cell.getCellType() != Cell.CELL_TYPE_BLANK)) 
//                            {
//                                //formula type
//                                if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
//                                    evaluator.evaluate(cell);                                    
//                                }
//                            }
//                        }
//                    }
                    FormulaEvaluator formulaEvaluator = xssfWorkbook.getCreationHelper().createFormulaEvaluator();
                    formulaEvaluator.evaluateAll();

                    for (int l = 3; l < 201; l++) {
                        sheet.autoSizeColumn(l);
//                        for (int m = 11; m <= sheet.getLastRowNum(); m++) 
//                        {
//                            final XSSFRow row = sheet.getRow(m);
//                            if (row != null) 
//                            {
//                                XSSFCell cell = row.getCell(l);
//                                if (cell != null && (cell.getCellType() != Cell.CELL_TYPE_BLANK)) 
//                                {
//                                    //formula type
//                                    if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
//                                        evaluator.evaluate(cell);                                    
//                                    }
//                                }
//                            }
//                        }
                    }

                    sheet.protectSheet("1234567890");
                    java.io.FileOutputStream out = new java.io.FileOutputStream(fileName);
                    xssfWorkbook.write(out);
                    out.close();
                }

                lstOfTextFile.add(fileName);
                FileInfo file = new FileInfo(new File(fileName));
                filesList.add(new DownloadFileInfor(file.getName(), fileName,
                        DefineFun.round_up((double) file.getSize() / 1000) + " KB"));
                fullPathList.add(file.getAbsolutePath());
                zipPath = Define.M_ROOT + Define.M_REPORT_XLS + zipFile;
            }

            if (fullPathList.size() > 1) {
                try {
                    FileZip.ZipFileFromArray(fullPathList, zipPath);
                    zipFileList.add(zipFile);
                    zipFileList.add(zipPath);
                } catch (Exception ex) {
                    Logger.getLogger(ExportText2SbvManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                filePath = zipFile;
                fileName = zipPath;
            }

            System.gc();
            return new FileExportInfo(fileName, filePath);
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " get_data_xaydungkh " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataXayDungKhDetail " + ex.getMessage());
            return null;
        }
    }

    public FileExportInfo xuatExcelMau01B_3N(String posCode, List<POSModel> lstCommune, String reportDate, String namBc, String dotBc, String savedDirPath) {
        String filePath = "", fileName = "";
        List<String> lstOfTextFile = new ArrayList<>();
        List<DownloadFileInfor> filesList = new ArrayList<>();
        List<String> zipFileList = new ArrayList<>();
        lstOfTextFile.clear();
        filesList.clear();
        zipFileList.clear();
        ArrayList<String> fullPathList = new ArrayList<>();
        String strTimeFile = Long.toString(System.currentTimeMillis());
        String zipFile = "FileNen_KHNV01_3N_" + strTimeFile + ".zip", zipPath = "";
        System.out.println("xuatExcelMau01B_3N ---1");
        try {
            for (POSModel commune : lstCommune) {
                if (commune.getId().equals("000000")) {
                    continue;
                }
                //xu ly cho export file ra PDF hoac la Excel
                Date dReportDate = new SimpleDateFormat("dd-MMM-yyyy").parse(reportDate);
                String strCurrDate = new SimpleDateFormat("ddMMyyyy").format(dReportDate);
                String strPathSave = savedDirPath;
                //Ham nay lay ra ten file bao cao can tao, ten file jasper report
                String strFileSave = ReportTemplate.MAU_01B_3N + "_" + posCode + "_" + commune.getId()
                        + "_" + strCurrDate;
                System.out.println("xuatExcelMau01B_3N ---2");
                strPathSave += Define.M_REPORT_XLS;
                strFileSave += ".XLSX";
                filePath = strFileSave;

                String templateFile = savedDirPath + Define.M_EXCEL_TEMP + "/KHNV/KHNV_DK01B_3N.xlsx";
                fileName = strPathSave + strFileSave;
                File source = new File(templateFile);
                File dest = new File(fileName);
                System.out.println("xuatExcelMau01B_3N ---3");
                FileUtil.copyFile(source, dest);

                XDKHDao2021 daoXdkh = new XDKHDao2021();
                List<POSModel> subCommuneList = daoXdkh.getSubCommuneList(posCode, commune.getId());
                List<String> lstTitleData = daoXdkh.getTitleData(posCode);
                System.out.println("xuatExcelMau01B_3N ---4");
                // Style
                XSSFCellStyle orderStyle;
                // Get data
                if (subCommuneList.size() > 0) {

                    // Fill data              
                    XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new java.io.FileInputStream(fileName));
                    XSSFSheet sheet = xssfWorkbook.getSheetAt(0);

                    XSSFRow titleRow = sheet.getRow(3);
                    if (titleRow == null) {
                        titleRow = sheet.createRow(3);
                    }

                    XSSFCell titleCell = titleRow.getCell(0, Row.CREATE_NULL_AS_BLANK);
                    fillTitle(titleCell, "NHU CẦU VỐN TÍN DỤNG CHÍNH SÁCH NĂM " + namBc);

                    XSSFRow subTitleRow = sheet.getRow(4);
                    if (subTitleRow == null) {
                        subTitleRow = sheet.createRow(4);
                    }

                    XSSFCell subTitleCell = subTitleRow.getCell(0, Row.CREATE_NULL_AS_BLANK);
                    String subTitleData = "XÃ/PHƯỜNG/THỊ TRẤN: " + commune.getDesc().toUpperCase() + "; HUYỆN:" + lstTitleData.get(0).toUpperCase() + "; TỈNH: " + lstTitleData.get(1).toUpperCase();
                    fillTitle(subTitleCell, subTitleData);

                    XSSFRow codeRow = sheet.getRow(10);
                    if (codeRow == null) {
                        codeRow = sheet.createRow(10);
                    }

                    XSSFRow yearRow = sheet.getRow(11);
                    if (yearRow == null) {
                        yearRow = sheet.createRow(11);
                    }

                    XSSFRow nameRow = sheet.getRow(7);
                    if (nameRow == null) {
                        nameRow = sheet.createRow(7);
                    }

                    int col = 0;

                    DaoMau01A daoMau01A = new DaoMau01A();

                    XSSFCellStyle numberStyle;
                    XSSFDataFormat format = xssfWorkbook.createDataFormat();
                    System.out.println("So thon trong xa xuatExcelMau01B_3N = " + subCommuneList.size());
                    for (int i = 0; i < subCommuneList.size(); i++) {

                        if (subCommuneList.get(i).getId().equals("000000")) {
                            continue;
                        }

                        List<Mau01AModel> lstData = daoMau01A.getExportData3Year(posCode, commune.getId(), subCommuneList.get(i).getId(), reportDate);

                        XSSFCell codeCell = codeRow.getCell(col + 6, Row.CREATE_NULL_AS_BLANK);
                        orderStyle = codeCell.getCellStyle();
                        orderStyle.setAlignment(HorizontalAlignment.LEFT);
                        orderStyle.setLocked(true);
                        codeCell.setCellValue(subCommuneList.get(i).getId());

                        XSSFCell nameCell = nameRow.getCell(col + 6, Row.CREATE_NULL_AS_BLANK);
                        orderStyle = nameCell.getCellStyle();
                        orderStyle.setAlignment(HorizontalAlignment.LEFT);
                        orderStyle.setLocked(true);
                        nameCell.setCellValue(subCommuneList.get(i).getDesc());

                        XSSFCell yearCell6 = yearRow.getCell(col + 6, Row.CREATE_NULL_AS_BLANK);
                        orderStyle = yearCell6.getCellStyle();
                        orderStyle.setAlignment(HorizontalAlignment.LEFT);
                        orderStyle.setLocked(true);
                        yearCell6.setCellValue(Integer.parseInt(namBc) - 2);

                        XSSFCell yearCell7 = yearRow.getCell(col + 7, Row.CREATE_NULL_AS_BLANK);
                        orderStyle = yearCell7.getCellStyle();
                        orderStyle.setAlignment(HorizontalAlignment.LEFT);
                        orderStyle.setLocked(true);
                        yearCell7.setCellValue(Integer.parseInt(namBc) - 1);

                        XSSFCell yearCell8 = yearRow.getCell(col + 8, Row.CREATE_NULL_AS_BLANK);
                        orderStyle = yearCell8.getCellStyle();
                        orderStyle.setAlignment(HorizontalAlignment.LEFT);
                        orderStyle.setLocked(true);
                        yearCell8.setCellValue(Integer.parseInt(namBc));

                        for (int j = 12; j < 107; j++) {
                            XSSFRow dataRow = sheet.getRow(j);
                            XSSFCell dataCodeCell = dataRow.getCell(0, Row.CREATE_NULL_AS_BLANK);
                            XSSFCell dataPrinCell6 = dataRow.getCell(col + 6, Row.CREATE_NULL_AS_BLANK);
                            XSSFCell dataPrinCell7 = dataRow.getCell(col + 7, Row.CREATE_NULL_AS_BLANK);
                            XSSFCell dataPrinCell8 = dataRow.getCell(col + 8, Row.CREATE_NULL_AS_BLANK);
                            String code = dataCodeCell.getStringCellValue();
                            for (int k = 0; k < lstData.size(); k++) {
                                if (lstData.get(k).code.equals(code)) {
                                    numberStyle = dataPrinCell6.getCellStyle();
                                    numberStyle.setDataFormat(format.getFormat("#,##0.00"));
                                    numberStyle.setAlignment(HorizontalAlignment.RIGHT);
                                    //numberStyle.setFont(font);
                                    numberStyle.setLocked(false);
                                    dataPrinCell6.setCellStyle(numberStyle);
                                    dataPrinCell6.setCellValue(lstData.get(k).d4);

                                    numberStyle = dataPrinCell7.getCellStyle();
                                    numberStyle.setDataFormat(format.getFormat("#,##0.00"));
                                    numberStyle.setAlignment(HorizontalAlignment.RIGHT);
                                    //numberStyle.setFont(font);
                                    numberStyle.setLocked(false);
                                    dataPrinCell7.setCellStyle(numberStyle);
                                    dataPrinCell7.setCellValue(lstData.get(k).d5);

                                    numberStyle = dataPrinCell8.getCellStyle();
                                    numberStyle.setDataFormat(format.getFormat("#,##0.00"));
                                    numberStyle.setAlignment(HorizontalAlignment.RIGHT);
                                    //numberStyle.setFont(font);
                                    numberStyle.setLocked(false);
                                    dataPrinCell8.setCellStyle(numberStyle);
                                    dataPrinCell8.setCellValue(lstData.get(k).d6);
                                    break;
                                }
                            }
                        }

                        col = col + 3;

                    }

                    //final FormulaEvaluator evaluator = xssfWorkbook.getCreationHelper().createFormulaEvaluator();
                    for (int l = 3; l < 201; l++) {
                        sheet.autoSizeColumn(l);
                    }

                    FormulaEvaluator formulaEvaluator = xssfWorkbook.getCreationHelper().createFormulaEvaluator();
                    formulaEvaluator.evaluateAll();

                    for (int l = 3; l < 201; l++) {
                        sheet.autoSizeColumn(l);
                    }

//                    sheet.protectSheet("1234567890");
                    java.io.FileOutputStream out = new java.io.FileOutputStream(fileName);
                    xssfWorkbook.write(out);
                    out.close();
                }

                lstOfTextFile.add(fileName);
                FileInfo file = new FileInfo(new File(fileName));
                filesList.add(new DownloadFileInfor(file.getName(), fileName,
                        DefineFun.round_up((double) file.getSize() / 1000) + " KB"));
                fullPathList.add(file.getAbsolutePath());
                zipPath = Define.M_ROOT + Define.M_REPORT_XLS + zipFile;
            }

            if (fullPathList.size() > 1) {
                try {
                    FileZip.ZipFileFromArray(fullPathList, zipPath);
                    zipFileList.add(zipFile);
                    zipFileList.add(zipPath);
                } catch (Exception ex) {
                    Logger.getLogger(ExportText2SbvManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                filePath = zipFile;
                fileName = zipPath;
            }

            System.gc();
            return new FileExportInfo(fileName, filePath);
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " get_data_xaydungkh " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataXayDungKhDetail " + ex.getMessage());
            return null;
        }
    }

    private void fillTitle(XSSFCell cell, String value) {
        XSSFCellStyle titleStyle;
        titleStyle = cell.getCellStyle();
        titleStyle.setLocked(true);
        cell.setCellStyle(titleStyle);
        cell.setCellValue(value);
    }

    public FileExportInfo xuatExcelMau01a(String posCode, String communeCode, String communeName, List<String> lstSubCommune, String reportDate, String namBc, String dotBc, String savedDirPath) {
        String filePath = "", fileName = "";
        List<String> lstOfTextFile = new ArrayList<>();
        List<DownloadFileInfor> filesList = new ArrayList<>();
        List<String> zipFileList = new ArrayList<>();
        lstOfTextFile.clear();
        filesList.clear();
        zipFileList.clear();
        ArrayList<String> fullPathList = new ArrayList<>();
        String strTimeFile = Long.toString(System.currentTimeMillis());
        String zipFile = "FileNen_KHNV01A_" + strTimeFile + ".zip", zipPath = "";
        try {

            int preYear = Integer.parseInt(namBc) - 1;
            String strPreYear = Integer.toString(preYear);

            for (String strSubCommuneCode : lstSubCommune) {

                if (strSubCommuneCode.equals("000000")) {
                    continue;
                }
                //String save_id = "KHNV01A";

                //xu ly cho export file ra PDF hoac la Excel
                Date dReportDate = new SimpleDateFormat("dd-MMM-yyyy").parse(reportDate);
                String strCurrDate = new SimpleDateFormat("ddMMyyyy").format(dReportDate);
                //duong dan chua file tren o dia + Define.M_REPORT_XLS
                String strPathSave = savedDirPath;
                //Ham nay lay ra ten file bao cao can tao, ten file jasper report

                String strFileSave = ReportTemplate.MAU_01A + "_" + strSubCommuneCode
                        + "_" + strCurrDate
                        + "_" + strTimeFile.substring(strTimeFile.length() - 4, strTimeFile.length());

                strPathSave += Define.M_REPORT_XLS;
                strFileSave += ".XLSX";
                filePath = strFileSave;

                String templateFile = savedDirPath + Define.M_EXCEL_TEMP + "/KHNV/KHNV_DK01A.xlsx";
                fileName = strPathSave + strFileSave;
                File source = new File(templateFile);
                File dest = new File(fileName);

                FileUtil.copyFile(source, dest);

                // Get data
                DaoMau01A daoMau01A = new DaoMau01A();
                List<Mau01AModel> lstData = daoMau01A.getExportData(posCode, communeCode, strSubCommuneCode, reportDate);

                if (lstData.size() > 0) {

                    // Fill data              
                    XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new java.io.FileInputStream(fileName));
                    //SXSSFWorkbook workbook = new SXSSFWorkbook(xssfWorkbook, 1000);
                    XSSFSheet sheet = xssfWorkbook.getSheetAt(0);

                    // style
                    XSSFCellStyle numberStyle;
                    XSSFDataFormat format = xssfWorkbook.createDataFormat();

                    //XSSFCellStyle titleStyle;
                    //XSSFCellStyle subTitleStyle;
                    XSSFCellStyle orderStyle;
                    XSSFCellStyle codeStyle;
                    XSSFCellStyle nameStyle;
                    XSSFCellStyle lockStyle;

                    String strTitle = "NHU CẦU VAY VỐN TÍN DỤNG CHÍNH SÁCH NĂM " + namBc;
                    XSSFCell xssfCellTitle = sheet.getRow(4).getCell(0, Row.CREATE_NULL_AS_BLANK);
                    fillTitle(xssfCellTitle, strTitle);
//                    titleStyle = xssfCellTitle.getCellStyle();
//                    titleStyle.setLocked(true);
//                    xssfCellTitle.setCellStyle(titleStyle);
//                    xssfCellTitle.setCellValue(strTitle);

                    String strSubCommuneName = lstData.get(0).subCommuneName;
                    String strSubTitle = "THÔN: " + strSubCommuneName + " XÃ/PHƯỜNG/THỊ TRẤN: " + communeName;
                    XSSFCell xssfCellSubTitle = sheet.getRow(5).getCell(0, Row.CREATE_NULL_AS_BLANK);
                    //subTitleStyle = xssfCellTitle.getCellStyle();
                    //subTitleStyle.setLocked(true);
                    //xssfCellSubTitle.setCellStyle(subTitleStyle);
                    //xssfCellSubTitle.setCellValue(strSubTitle);
                    fillTitle(xssfCellSubTitle, strSubTitle);

                    String colTitle3 = "Ước dư nợ đến 31/12/" + strPreYear;
                    XSSFCell colTitle = sheet.getRow(7).getCell(3, Row.CREATE_NULL_AS_BLANK);
                    fillTitle(colTitle, colTitle3);

                    String colTitle5 = "Nhu cầu vốn năm " + namBc;
                    colTitle = sheet.getRow(7).getCell(4, Row.CREATE_NULL_AS_BLANK);
                    fillTitle(colTitle, colTitle5);

                    String colTitle6 = "Tăng, giảm so với 31/12/" + strPreYear;
                    colTitle = sheet.getRow(8).getCell(5, Row.CREATE_NULL_AS_BLANK);
                    fillTitle(colTitle, colTitle6);

                    for (int i = 0; i < lstData.size(); i++) {
                        XSSFRow xssfRow = sheet.getRow(i + ReportTemplate.MAU_01A_START_ROW);
                        if (xssfRow == null) {
                            xssfRow = sheet.createRow(i + ReportTemplate.MAU_01A_START_ROW);
                        }
                        XSSFFont font = xssfWorkbook.createFont();
                        if (lstData.get(i).printType == ReportTemplate.LEVEL_MAIN) {

                            font.setFontName("Times New Roman");
                            font.setFontHeightInPoints((short) 11);
                            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
                        } else {
                            font.setFontName("Times New Roman");
                            font.setFontHeightInPoints((short) 11);
                        }

                        XSSFCell xssfCell00 = xssfRow.getCell(0, Row.CREATE_NULL_AS_BLANK);

                        orderStyle = xssfCell00.getCellStyle();
                        orderStyle.setAlignment(HorizontalAlignment.LEFT);
                        orderStyle.setFont(font);
                        orderStyle.setLocked(true);

                        xssfCell00.setCellStyle(orderStyle);
                        xssfCell00.setCellValue(lstData.get(i).order);

                        XSSFCell xssfCell01 = xssfRow.getCell(1, Row.CREATE_NULL_AS_BLANK);

                        codeStyle = xssfCell01.getCellStyle();
                        codeStyle.setFont(font);
                        codeStyle.setLocked(true);
                        codeStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                        codeStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                        codeStyle.setAlignment(HorizontalAlignment.CENTER);

                        xssfCell01.setCellStyle(codeStyle);
                        xssfCell01.setCellValue(lstData.get(i).code);

                        XSSFCell xssfCell02 = xssfRow.getCell(2, Row.CREATE_NULL_AS_BLANK);

                        nameStyle = xssfCell02.getCellStyle();
                        nameStyle.setAlignment(HorizontalAlignment.LEFT);
                        nameStyle.setLocked(true);
                        nameStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                        nameStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

                        xssfCell02.setCellStyle(nameStyle);
                        xssfCell02.setCellValue(lstData.get(i).name);

                        XSSFCell xssfCell03 = xssfRow.getCell(3, Row.CREATE_NULL_AS_BLANK);
                        numberStyle = xssfCell03.getCellStyle();
                        numberStyle.setDataFormat(format.getFormat("#,##0.00"));
                        numberStyle.setAlignment(HorizontalAlignment.RIGHT);
                        numberStyle.setFont(font);
                        numberStyle.setLocked(false);
                        xssfCell03.setCellStyle(numberStyle);
                        xssfCell03.setCellValue(lstData.get(i).d2);

                        XSSFCell xssfCell05 = xssfRow.getCell(5, Row.CREATE_NULL_AS_BLANK);
                        numberStyle = xssfCell05.getCellStyle();
                        numberStyle.setDataFormat(format.getFormat("#,##0.00"));
                        numberStyle.setAlignment(HorizontalAlignment.RIGHT);
                        numberStyle.setFont(font);
                        numberStyle.setLocked(false);
                        xssfCell05.setCellStyle(numberStyle);
                        xssfCell05.setCellValue(lstData.get(i).d4);

                        XSSFCell xssfCell04 = xssfRow.getCell(4, Row.CREATE_NULL_AS_BLANK);
                        lockStyle = xssfCell04.getCellStyle();
                        lockStyle.setDataFormat(format.getFormat("#,##0.00"));
                        lockStyle.setAlignment(HorizontalAlignment.RIGHT);
                        lockStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                        lockStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                        lockStyle.setFont(font);
                        lockStyle.setLocked(true);
                        xssfCell04.setCellStyle(lockStyle);

                        XSSFCell xssfCell06 = xssfRow.getCell(6, Row.CREATE_NULL_AS_BLANK);
                        lockStyle = xssfCell06.getCellStyle();
                        lockStyle.setDataFormat(format.getFormat("#,##0.00"));
                        lockStyle.setAlignment(HorizontalAlignment.RIGHT);
                        lockStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                        lockStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                        lockStyle.setFont(font);
                        lockStyle.setLocked(true);
                        xssfCell06.setCellStyle(lockStyle);

                    }

                    FormulaEvaluator formulaEvaluator = xssfWorkbook.getCreationHelper().createFormulaEvaluator();
                    formulaEvaluator.evaluateAll();

                    sheet.protectSheet("123456");
                    java.io.FileOutputStream out = new java.io.FileOutputStream(fileName);
                    xssfWorkbook.write(out);
                    out.close();
                }

                lstOfTextFile.add(fileName);
                FileInfo file = new FileInfo(new File(fileName));
                filesList.add(new DownloadFileInfor(file.getName(), fileName,
                        DefineFun.round_up((double) file.getSize() / 1000) + " KB"));
                fullPathList.add(file.getAbsolutePath());
                zipPath = Define.M_ROOT + Define.M_REPORT_XLS + zipFile;
            }

            if (fullPathList.size() > 1) {
                try {
                    FileZip.ZipFileFromArray(fullPathList, zipPath);
                    zipFileList.add(zipFile);
                    zipFileList.add(zipPath);
                } catch (Exception ex) {
                    Logger.getLogger(ExportText2SbvManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                filePath = zipFile;
                fileName = zipPath;
            }

            System.gc();
            return new FileExportInfo(fileName, filePath);
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " get_data_xaydungkh " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataXayDungKhDetail " + ex.getMessage());
            return null;
        }
    }

    public FileExportInfo xuatExcelMau01(String posCode, List<String> lstCommuneCode, String reportDate, String namBc, String dotBc, String savedDirPath) {
        String filePath = "", fileName = "";
        List<String> lstOfTextFile = new ArrayList<>();
        List<DownloadFileInfor> filesList = new ArrayList<>();
        List<String> zipFileList = new ArrayList<>();
        lstOfTextFile.clear();
        filesList.clear();
        zipFileList.clear();
        ArrayList<String> fullPathList = new ArrayList<>();
        String strTimeFile = Long.toString(System.currentTimeMillis());
        String zipFile = "FileNen_KHNV01_" + strTimeFile + ".zip", zipPath = "";
        try {

            int preYear = Integer.parseInt(namBc) - 1;
            String strPreYear = Integer.toString(preYear);

            for (String communeCode : lstCommuneCode) {

                if (communeCode.equals("000000")) {
                    continue;
                }
                //String save_id = "KHNV01A";

                //xu ly cho export file ra PDF hoac la Excel
                Date dReportDate = new SimpleDateFormat("dd-MMM-yyyy").parse(reportDate);
                String strCurrDate = new SimpleDateFormat("ddMMyyyy").format(dReportDate);
                //duong dan chua file tren o dia + Define.M_REPORT_XLS
                String strPathSave = savedDirPath;
                //Ham nay lay ra ten file bao cao can tao, ten file jasper report

                String strFileSave = ReportTemplate.MAU_01 + "_" + communeCode
                        + "_" + strCurrDate
                        + "_" + strTimeFile.substring(strTimeFile.length() - 4, strTimeFile.length());

                strPathSave += Define.M_REPORT_XLS;
                strFileSave += ".XLSX";
                filePath = strFileSave;

                String templateFile = savedDirPath + Define.M_EXCEL_TEMP + "/KHNV/KHNV_DK01.xlsx";
                fileName = strPathSave + strFileSave;
                File source = new File(templateFile);
                File dest = new File(fileName);

                FileUtil.copyFile(source, dest);

                // Get data
                DaoMau01A daoMau01A = new DaoMau01A();
                List<Mau01AModel> lstData = daoMau01A.getExportData01(posCode, communeCode, reportDate);

                if (lstData.size() > 0) {

                    // Fill data              
                    XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new java.io.FileInputStream(fileName));
                    //SXSSFWorkbook workbook = new SXSSFWorkbook(xssfWorkbook, 1000);
                    XSSFSheet sheet = xssfWorkbook.getSheetAt(0);

                    // style
                    XSSFCellStyle numberStyle;
                    XSSFDataFormat format = xssfWorkbook.createDataFormat();

                    //XSSFCellStyle titleStyle;
                    //XSSFCellStyle subTitleStyle;
                    XSSFCellStyle orderStyle;
                    XSSFCellStyle codeStyle;
                    XSSFCellStyle nameStyle;
                    XSSFCellStyle lockStyle;

                    String strTitle = "Mẫu 01: NHU CẦU VỐN TÍN DỤNG CHÍNH SÁCH " + namBc;
                    XSSFCell xssfCellTitle = sheet.getRow(4).getCell(0, Row.CREATE_NULL_AS_BLANK);
                    fillTitle(xssfCellTitle, strTitle);
//                    titleStyle = xssfCellTitle.getCellStyle();
//                    titleStyle.setLocked(true);
//                    xssfCellTitle.setCellStyle(titleStyle);
//                    xssfCellTitle.setCellValue(strTitle);

                    String communeName = lstData.get(0).subCommuneName;
                    String strSubTitle = "Xã: " + communeName;
                    XSSFCell xssfCellSubTitle = sheet.getRow(5).getCell(0, Row.CREATE_NULL_AS_BLANK);
                    //subTitleStyle = xssfCellTitle.getCellStyle();
                    //subTitleStyle.setLocked(true);
                    //xssfCellSubTitle.setCellStyle(subTitleStyle);
                    //xssfCellSubTitle.setCellValue(strSubTitle);
                    fillTitle(xssfCellSubTitle, strSubTitle);

                    String colTitle3 = "Tổng số " + namBc;
                    XSSFCell colTitle = sheet.getRow(7).getCell(3, Row.CREATE_NULL_AS_BLANK);
                    fillTitle(colTitle, colTitle3);

                    String colTitle5 = "";
                    colTitle = sheet.getRow(7).getCell(4, Row.CREATE_NULL_AS_BLANK);
                    fillTitle(colTitle, colTitle5);

                    String colTitle6 = "";
                    colTitle = sheet.getRow(8).getCell(5, Row.CREATE_NULL_AS_BLANK);
                    fillTitle(colTitle, colTitle6);

                    for (int i = 0; i < lstData.size(); i++) {
                        XSSFRow xssfRow = sheet.getRow(i + ReportTemplate.MAU_01A_START_ROW);
                        if (xssfRow == null) {
                            xssfRow = sheet.createRow(i + ReportTemplate.MAU_01A_START_ROW);
                        }
                        XSSFCell xssfCell00 = xssfRow.getCell(0, Row.CREATE_NULL_AS_BLANK);

                        orderStyle = xssfCell00.getCellStyle();
                        orderStyle.setAlignment(HorizontalAlignment.LEFT);
                        orderStyle.setLocked(true);
                        xssfCell00.setCellStyle(orderStyle);
                        xssfCell00.setCellValue(lstData.get(i).order);

                        XSSFCell xssfCell01 = xssfRow.getCell(1, Row.CREATE_NULL_AS_BLANK);

                        codeStyle = xssfCell01.getCellStyle();
                        codeStyle.setLocked(true);
                        codeStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                        codeStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                        codeStyle.setAlignment(HorizontalAlignment.CENTER);

                        xssfCell01.setCellStyle(codeStyle);
                        xssfCell01.setCellValue(lstData.get(i).code);

                        XSSFCell xssfCell02 = xssfRow.getCell(2, Row.CREATE_NULL_AS_BLANK);

                        nameStyle = xssfCell02.getCellStyle();
                        nameStyle.setAlignment(HorizontalAlignment.LEFT);
                        nameStyle.setLocked(true);
                        nameStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                        nameStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                        xssfCell02.setCellStyle(nameStyle);
                        xssfCell02.setCellValue(lstData.get(i).name);

                        XSSFCell xssfCell03 = xssfRow.getCell(3, Row.CREATE_NULL_AS_BLANK);
                        numberStyle = xssfCell03.getCellStyle();
                        numberStyle.setDataFormat(format.getFormat("#,##0.00"));
                        numberStyle.setAlignment(HorizontalAlignment.RIGHT);
                        numberStyle.setLocked(false);
                        xssfCell03.setCellStyle(numberStyle);
                        xssfCell03.setCellValue(lstData.get(i).d2);

                        XSSFCell xssfCell04 = xssfRow.getCell(4, Row.CREATE_NULL_AS_BLANK);
                        lockStyle = xssfCell04.getCellStyle();
                        lockStyle.setDataFormat(format.getFormat("#,##0.00"));
                        lockStyle.setAlignment(HorizontalAlignment.RIGHT);
                        lockStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                        lockStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                        lockStyle.setLocked(true);
                        xssfCell04.setCellStyle(lockStyle);

                        XSSFCell xssfCell06 = xssfRow.getCell(6, Row.CREATE_NULL_AS_BLANK);
                        lockStyle = xssfCell06.getCellStyle();
                        lockStyle.setDataFormat(format.getFormat("#,##0.00"));
                        lockStyle.setAlignment(HorizontalAlignment.RIGHT);
                        lockStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                        lockStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                        lockStyle.setLocked(true);
                        xssfCell06.setCellStyle(lockStyle);

                    }

                    sheet.protectSheet("123456");
                    java.io.FileOutputStream out = new java.io.FileOutputStream(fileName);
                    xssfWorkbook.write(out);
                    out.close();
                }

                lstOfTextFile.add(fileName);
                FileInfo file = new FileInfo(new File(fileName));
                filesList.add(new DownloadFileInfor(file.getName(), fileName,
                        DefineFun.round_up((double) file.getSize() / 1000) + " KB"));
                fullPathList.add(file.getAbsolutePath());
                zipPath = Define.M_ROOT + Define.M_REPORT_XLS + zipFile;
            }

            if (fullPathList.size() > 1) {
                try {
                    FileZip.ZipFileFromArray(fullPathList, zipPath);
                    zipFileList.add(zipFile);
                    zipFileList.add(zipPath);
                } catch (Exception ex) {
                    Logger.getLogger(ExportText2SbvManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                filePath = zipFile;
                fileName = zipPath;
            }

            System.gc();
            return new FileExportInfo(fileName, filePath);
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " get_data_xaydungkh " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataXayDungKhDetail " + ex.getMessage());
            return null;
        }
    }

    String getPreYearString(int value) {
        int preYear = value - 1;
        String strPreYear = Integer.toString(preYear);
        return strPreYear;
    }

    public FileExportInfo xuatExcelMau02(POSModel pos, String posFlag, String reportDate, String namBc, String dotBc, String savedDirPath) {
        String filePath = "", fileName = "";
        List<String> lstOfTextFile = new ArrayList<>();
        List<DownloadFileInfor> filesList = new ArrayList<>();
        List<String> zipFileList = new ArrayList<>();
        lstOfTextFile.clear();
        filesList.clear();
        zipFileList.clear();
        ArrayList<String> fullPathList = new ArrayList<>();
        String strTimeFile = Long.toString(System.currentTimeMillis());
        String zipFile = "FileNen_KHNV02_" + strTimeFile + ".zip", zipPath = "";
        String posCode = pos.getId();

        try {

            String strPreYear = getPreYearString(Integer.parseInt(namBc));
            String strTwoYearAgo = getPreYearString(Integer.parseInt(namBc) - 1);

            //xu ly cho export file ra PDF hoac la Excel
            Date dReportDate = new SimpleDateFormat("dd-MMM-yyyy").parse(reportDate);
            String strCurrDate = new SimpleDateFormat("ddMMyyyy").format(dReportDate);
            //duong dan chua file tren o dia + Define.M_REPORT_XLS
            //String strPathSave = savedDirPath;
            //Ham nay lay ra ten file bao cao can tao, ten file jasper report
            String strPosFlag = "";
            if (posFlag.equals("N")) {
                strPosFlag = "S";
            } else {
                strPosFlag = "M";
            }

            DaoMau02 daoMau02 = new DaoMau02();

            List<DistrictInfo> lstDistrict = daoMau02.getDistrictByPos(posCode);
            String templateFile = savedDirPath + Define.M_EXCEL_TEMP + "/KHNV/KHNV_DK02.xlsx";

//            for (DistrictInfo district : lstDistrict) {
            String strFileSave = ReportTemplate.MAU_02 + "_" + posCode + "_" + strPosFlag //+ "_" + district.districtCode
                    + "_" + strCurrDate
                    + "_" + strTimeFile.substring(strTimeFile.length() - 4, strTimeFile.length());

            String strPathSave = savedDirPath + Define.M_REPORT_XLS;
            strFileSave += ".XLSX";
            filePath = strFileSave;

            fileName = strPathSave + strFileSave;
            File source = new File(templateFile);
            File dest = new File(fileName);

            FileUtil.copyFile(source, dest);

            // Get data
            List<Mau02Model> lstData = daoMau02.getExportData(posCode, posFlag, "", reportDate);

            if (lstData.size() > 0) {

                // Fill data              
                XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new java.io.FileInputStream(fileName));
                //SXSSFWorkbook workbook = new SXSSFWorkbook(xssfWorkbook, 1000);
                XSSFSheet sheet = xssfWorkbook.getSheetAt(0);

                // style
                XSSFCellStyle numberStyle;
                XSSFDataFormat format = xssfWorkbook.createDataFormat();

                XSSFCellStyle orderStyle;
                XSSFCellStyle codeStyle;
                XSSFCellStyle nameStyle;
                XSSFCellStyle lockStyle;

                String strTitle = "KẾ HOẠCH TÍN DỤNG NĂM " + namBc;
                XSSFCell xssfCellTitle = sheet.getRow(4).getCell(0, Row.CREATE_NULL_AS_BLANK);
                fillTitle(xssfCellTitle, strTitle);

                String strPosTitle = pos.getDesc() + " - " + "";
                XSSFCell xssfPosTitle = sheet.getRow(2).getCell(1, Row.CREATE_NULL_AS_BLANK);
                fillTitle(xssfPosTitle, strPosTitle);

                String colTitle3 = "Thực hiện đến 31/12/" + strTwoYearAgo;
                XSSFCell colTitle = sheet.getRow(6).getCell(3, Row.CREATE_NULL_AS_BLANK);
                fillTitle(colTitle, colTitle3);

                String colTitle5 = "Ước thực hiện đến 31/12/" + strPreYear;
                colTitle = sheet.getRow(6).getCell(4, Row.CREATE_NULL_AS_BLANK);
                fillTitle(colTitle, colTitle5);

                String colTitle6 = "Kế hoạch tín dụng năm " + namBc;
                colTitle = sheet.getRow(6).getCell(5, Row.CREATE_NULL_AS_BLANK);
                fillTitle(colTitle, colTitle6);

                String colTitle7 = "Tổng số";
                colTitle = sheet.getRow(7).getCell(5, Row.CREATE_NULL_AS_BLANK);
                fillTitle(colTitle, colTitle7);

                colTitle = sheet.getRow(7).getCell(6, Row.CREATE_NULL_AS_BLANK);
                fillTitle(colTitle, colTitle7);

                for (int i = 0; i < lstData.size(); i++) {

                    XSSFRow xssfRow = sheet.getRow(i + ReportTemplate.MAU_02_START_ROW);
                    if (xssfRow == null) {
                        xssfRow = sheet.createRow(i + ReportTemplate.MAU_02_START_ROW);
                    }
                    XSSFCell xssfCell00 = xssfRow.getCell(0, Row.CREATE_NULL_AS_BLANK);

                    orderStyle = xssfCell00.getCellStyle();
                    orderStyle.setAlignment(HorizontalAlignment.LEFT);
                    orderStyle.setLocked(true);
                    xssfCell00.setCellStyle(orderStyle);
                    xssfCell00.setCellValue(lstData.get(i).order);

                    XSSFCell xssfCell01 = xssfRow.getCell(1, Row.CREATE_NULL_AS_BLANK);

                    codeStyle = xssfCell01.getCellStyle();
                    codeStyle.setLocked(true);
                    codeStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                    codeStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                    codeStyle.setAlignment(HorizontalAlignment.CENTER);

                    xssfCell01.setCellStyle(codeStyle);
                    xssfCell01.setCellValue(lstData.get(i).code);

                    XSSFCell xssfCell02 = xssfRow.getCell(2, Row.CREATE_NULL_AS_BLANK);

                    nameStyle = xssfCell02.getCellStyle();
                    nameStyle.setAlignment(HorizontalAlignment.LEFT);
                    nameStyle.setLocked(true);
                    nameStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                    nameStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                    xssfCell02.setCellStyle(nameStyle);
                    xssfCell02.setCellValue(lstData.get(i).name);

                    XSSFCell xssfCell03 = xssfRow.getCell(3, Row.CREATE_NULL_AS_BLANK);
                    lockStyle = xssfCell03.getCellStyle();
                    lockStyle.setDataFormat(format.getFormat("#,##0"));
                    lockStyle.setAlignment(HorizontalAlignment.RIGHT);

                    if (lstData.get(i).code.equals("XD00100")
                            || lstData.get(i).code.equals("XD00101")
                            || lstData.get(i).code.equals("XD00102")
                            || lstData.get(i).code.equals("XD00103")
                            || lstData.get(i).code.equals("XD00109")
                            || lstData.get(i).code.equals("XD00110")
                            || lstData.get(i).code.equals("XD00002")
                            || lstData.get(i).code.equals("XD00030")
                            || lstData.get(i).code.equals("XD00039")
                            || lstData.get(i).code.equals("XD00111")) {
                        xssfCell03.setCellStyle(lockStyle);
                        xssfCell03.setCellValue(lstData.get(i).d1);
                    } else {
//                        lockStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//                        lockStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//                        lockStyle.setLocked(true);
//                        xssfCell03.setCellStyle(lockStyle);
                        xssfCell03.setCellValue(lstData.get(i).d1);
                    }

                    XSSFCell xssfCell04 = xssfRow.getCell(4, Row.CREATE_NULL_AS_BLANK);
                    numberStyle = xssfCell04.getCellStyle();
                    numberStyle.setDataFormat(format.getFormat("#,##0.00"));
                    numberStyle.setAlignment(HorizontalAlignment.RIGHT);
//                    numberStyle.setLocked(false);
//                    xssfCell04.setCellStyle(numberStyle);
                    xssfCell04.setCellValue(lstData.get(i).d2);

                    XSSFCell xssfCell05 = xssfRow.getCell(5, Row.CREATE_NULL_AS_BLANK);
                    lockStyle = xssfCell05.getCellStyle();
                    lockStyle.setDataFormat(format.getFormat("#,##0.00"));
                    lockStyle.setAlignment(HorizontalAlignment.RIGHT);

                    if (lstData.get(i).code.equals("XD00100")
                            || lstData.get(i).code.equals("XD00101")
                            || lstData.get(i).code.equals("XD00102")
                            || lstData.get(i).code.equals("XD00103")
                            || lstData.get(i).code.equals("XD00109")
                            || lstData.get(i).code.equals("XD00110")
                            || lstData.get(i).code.equals("XD00002")
                            || lstData.get(i).code.equals("XD00030")
                            || lstData.get(i).code.equals("XD00039")
                            || lstData.get(i).code.equals("XD00111")) {
                        xssfCell05.setCellStyle(lockStyle);
                        xssfCell05.setCellValue(lstData.get(i).d3);
                    } else {
//                        lockStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//                        lockStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//                        lockStyle.setLocked(true);
//                        xssfCell05.setCellStyle(lockStyle);
                        xssfCell05.setCellValue(lstData.get(i).d3);
                    }

                    XSSFCell xssfCell06 = xssfRow.getCell(6, Row.CREATE_NULL_AS_BLANK);
                    numberStyle = xssfCell06.getCellStyle();
                    numberStyle.setDataFormat(format.getFormat("#,##0.00"));
                    numberStyle.setAlignment(HorizontalAlignment.RIGHT);
                    numberStyle.setLocked(true);
                    xssfCell06.setCellStyle(numberStyle);
                    xssfCell06.setCellValue(lstData.get(i).d4);

                    XSSFCell xssfCell07 = xssfRow.getCell(7, Row.CREATE_NULL_AS_BLANK);
                    lockStyle = xssfCell07.getCellStyle();
                    lockStyle.setDataFormat(format.getFormat("#,##0.00"));
                    lockStyle.setAlignment(HorizontalAlignment.RIGHT);
                    lockStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                    lockStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                    lockStyle.setLocked(true);
                    xssfCell07.setCellStyle(lockStyle);

                }

                FormulaEvaluator formulaEvaluator = xssfWorkbook.getCreationHelper().createFormulaEvaluator();
                formulaEvaluator.evaluateAll();
                sheet.protectSheet("123456");
                java.io.FileOutputStream out = new java.io.FileOutputStream(fileName);
                xssfWorkbook.write(out);
                out.close();

                lstOfTextFile.add(fileName);
                FileInfo file = new FileInfo(new File(fileName));
                filesList.add(new DownloadFileInfor(file.getName(), fileName,
                        DefineFun.round_up((double) file.getSize() / 1000) + " KB"));
                fullPathList.add(file.getAbsolutePath());
                zipPath = Define.M_ROOT + Define.M_REPORT_XLS + zipFile;
            }
//            }

            if (fullPathList.size() > 1) {
                try {
                    FileZip.ZipFileFromArray(fullPathList, zipPath);
                    zipFileList.add(zipFile);
                    zipFileList.add(zipPath);
                } catch (Exception ex) {
                    Logger.getLogger(ExportText2SbvManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                filePath = zipFile;
                fileName = zipPath;
            }

            System.gc();
            return new FileExportInfo(fileName, filePath);
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " xuatExcelMau02 " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi xuatExcelMau02 " + ex.getMessage());
            return null;
        }
    }

    public FileExportInfo xuatExcelMau01BCTK_QD23(List<String> lstPosCd, String reportDate, String capBC, String savedDirPath, String pos_user) {
        String filePath = "", fileName = "";
        List<String> lstOfTextFile = new ArrayList<>();
        List<DownloadFileInfor> filesList = new ArrayList<>();
        List<String> zipFileList = new ArrayList<>();
        lstOfTextFile.clear();
        filesList.clear();
        zipFileList.clear();
        ArrayList<String> fullPathList = new ArrayList<>();
        String strTimeFile = Long.toString(System.currentTimeMillis());
        String zipFile = "FileNen_MAU_01BCTK_" + strTimeFile + ".zip", zipPath = "";
        try {

//            int preYear = Integer.parseInt(namBc) - 1;
//            String strPreYear = Integer.toString(preYear);
//            for (String strSubCommuneCode : lstPosCd) {
//                if (strSubCommuneCode.equals("000000")) {
//                    continue;
//                }
            //String save_id = "KHNV01A";
            //xu ly cho export file ra PDF hoac la Excel
            Date dReportDate = new SimpleDateFormat("dd-MMM-yyyy").parse(reportDate);
            String strCurrDate = new SimpleDateFormat("ddMMyyyy").format(dReportDate);
            //duong dan chua file tren o dia + Define.M_REPORT_XLS
            String strPathSave = savedDirPath;
            //Ham nay lay ra ten file bao cao can tao, ten file jasper report

            String strFileSave = "MAU_01BCTK_" + pos_user
                    + "_" + strCurrDate
                    + "_" + strTimeFile.substring(strTimeFile.length() - 4, strTimeFile.length());

            strPathSave += Define.M_REPORT_XLS;
            strFileSave += ".XLSX";
            filePath = strFileSave;

            String templateFile = savedDirPath + Define.M_EXCEL_TEMP + "/KHNV/MAU_01BCTK.xlsx";
            fileName = strPathSave + strFileSave;
            File source = new File(templateFile);
            File dest = new File(fileName);

            FileUtil.copyFile(source, dest);

            // Get data
//                DaoMau01A daoMau01A = new DaoMau01A();
            DaoNhaptaycnMain dao = new DaoNhaptaycnMain();
            List<QT_DULIEU_NT_50> lstData = dao.getDataEportElxQD23001("QD23_001", pos_user, capBC, reportDate, lstPosCd);

            if (lstData.size() > 0) {

                // Fill data              
                XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new java.io.FileInputStream(fileName));
                //SXSSFWorkbook workbook = new SXSSFWorkbook(xssfWorkbook, 1000);
                XSSFSheet sheet = xssfWorkbook.getSheetAt(0);

                // style
                XSSFCellStyle numberStyle;
                XSSFDataFormat format = xssfWorkbook.createDataFormat();

                //XSSFCellStyle titleStyle;
                //XSSFCellStyle subTitleStyle;
                XSSFCellStyle orderStyle;
                XSSFCellStyle codeStyle;
                XSSFCellStyle nameStyle;
                XSSFCellStyle lockStyle;

//                    String strTitle = "NHU CẦU VAY VỐN TÍN DỤNG CHÍNH SÁCH NĂM " + namBc;
//                    XSSFCell xssfCellTitle = sheet.getRow(4).getCell(0, Row.CREATE_NULL_AS_BLANK);
//                    fillTitle(xssfCellTitle, strTitle);
//                    titleStyle = xssfCellTitle.getCellStyle();
//                    titleStyle.setLocked(true);
//                    xssfCellTitle.setCellStyle(titleStyle);
//                    xssfCellTitle.setCellValue(strTitle);
//                    String strSubCommuneName = lstData.get(0).subCommuneName;
//                    String strSubTitle = "THÔN: " + strSubCommuneName + " XÃ/PHƯỜNG/THỊ TRẤN: " + communeName;
//                    XSSFCell xssfCellSubTitle = sheet.getRow(5).getCell(0, Row.CREATE_NULL_AS_BLANK);
                //subTitleStyle = xssfCellTitle.getCellStyle();
                //subTitleStyle.setLocked(true);
                //xssfCellSubTitle.setCellStyle(subTitleStyle);
                //xssfCellSubTitle.setCellValue(strSubTitle);
//                    fillTitle(xssfCellSubTitle, strSubTitle);
//                    String colTitle3 = "Ước dư nợ đến 31/12/" + strPreYear;
//                    XSSFCell colTitle = sheet.getRow(7).getCell(3, Row.CREATE_NULL_AS_BLANK);
//                    fillTitle(colTitle, colTitle3);
//
//                    String colTitle5 = "Nhu cầu vốn năm " + namBc;
//                    colTitle = sheet.getRow(7).getCell(4, Row.CREATE_NULL_AS_BLANK);
//                    fillTitle(colTitle, colTitle5);
//
//                    String colTitle6 = "Tăng, giảm so với 31/12/" + strPreYear;
//                    colTitle = sheet.getRow(8).getCell(5, Row.CREATE_NULL_AS_BLANK);
//                    fillTitle(colTitle, colTitle6);
                for (int i = 0; i < lstData.size(); i++) {
                    XSSFRow xssfRow = sheet.getRow(i + ReportTemplate.MAU_QD23001_START_ROW);
                    if (xssfRow == null) {
                        xssfRow = sheet.createRow(i + ReportTemplate.MAU_QD23001_START_ROW);
                    }
                    XSSFFont font = xssfWorkbook.createFont();
                    font.setFontName("Times New Roman");
                    font.setFontHeightInPoints((short) 11);

                    XSSFCell xssfCell00 = xssfRow.getCell(0, Row.CREATE_NULL_AS_BLANK);
                    xssfCell00.setCellValue(lstData.get(i).D1);

                    XSSFCell xssfCell01 = xssfRow.getCell(1, Row.CREATE_NULL_AS_BLANK);
                    xssfCell01.setCellValue(lstData.get(i).D2);

                    XSSFCell xssfCell02 = xssfRow.getCell(2, Row.CREATE_NULL_AS_BLANK);
                    xssfCell02.setCellValue(lstData.get(i).D3);

                    XSSFCell xssfCell03 = xssfRow.getCell(3, Row.CREATE_NULL_AS_BLANK);
                    xssfCell03.setCellValue(lstData.get(i).D4);

                    XSSFCell xssfCell05 = xssfRow.getCell(4, Row.CREATE_NULL_AS_BLANK);
                    xssfCell05.setCellValue(lstData.get(i).D5);

                    XSSFCell xssfCell06 = xssfRow.getCell(5, Row.CREATE_NULL_AS_BLANK);
                    xssfCell06.setCellValue(lstData.get(i).D6);

                    XSSFCell xssfCell07 = xssfRow.getCell(6, Row.CREATE_NULL_AS_BLANK);
                    xssfCell07.setCellValue(lstData.get(i).D7);

                    XSSFCell xssfCell08 = xssfRow.getCell(7, Row.CREATE_NULL_AS_BLANK);
                    xssfCell08.setCellValue(lstData.get(i).D8);

                    XSSFCell xssfCell09 = xssfRow.getCell(8, Row.CREATE_NULL_AS_BLANK);
                    xssfCell09.setCellValue(lstData.get(i).D9);

                    XSSFCell xssfCell10 = xssfRow.getCell(9, Row.CREATE_NULL_AS_BLANK);
                    xssfCell10.setCellValue(lstData.get(i).D10);

                    XSSFCell xssfCell11 = xssfRow.getCell(10, Row.CREATE_NULL_AS_BLANK);
                    xssfCell11.setCellValue(lstData.get(i).D11);

                    XSSFCell xssfCell12 = xssfRow.getCell(11, Row.CREATE_NULL_AS_BLANK);
                    xssfCell12.setCellValue(lstData.get(i).D12);

                    XSSFCell xssfCell13 = xssfRow.getCell(12, Row.CREATE_NULL_AS_BLANK);
                    xssfCell13.setCellValue(lstData.get(i).D13);

                    XSSFCell xssfCell14 = xssfRow.getCell(13, Row.CREATE_NULL_AS_BLANK);
                    xssfCell14.setCellValue(lstData.get(i).D14);

                    XSSFCell xssfCell15 = xssfRow.getCell(14, Row.CREATE_NULL_AS_BLANK);
                    xssfCell15.setCellValue(lstData.get(i).D15);

                    XSSFCell xssfCell16 = xssfRow.getCell(15, Row.CREATE_NULL_AS_BLANK);
                    xssfCell16.setCellValue(lstData.get(i).D16);

                    XSSFCell xssfCell17 = xssfRow.getCell(16, Row.CREATE_NULL_AS_BLANK);
                    xssfCell17.setCellValue(lstData.get(i).D17);

                    XSSFCell xssfCell18 = xssfRow.getCell(17, Row.CREATE_NULL_AS_BLANK);
                    xssfCell18.setCellValue(lstData.get(i).D18);

                    XSSFCell xssfCell19 = xssfRow.getCell(18, Row.CREATE_NULL_AS_BLANK);
                    xssfCell19.setCellValue(lstData.get(i).D19);

                    XSSFCell xssfCell20 = xssfRow.getCell(19, Row.CREATE_NULL_AS_BLANK);
                    xssfCell20.setCellValue(lstData.get(i).D20);

                    XSSFCell xssfCell21 = xssfRow.getCell(20, Row.CREATE_NULL_AS_BLANK);
                    xssfCell21.setCellValue(lstData.get(i).D21);

                    XSSFCell xssfCell22 = xssfRow.getCell(21, Row.CREATE_NULL_AS_BLANK);
                    xssfCell22.setCellValue(lstData.get(i).D22);

                }

                FormulaEvaluator formulaEvaluator = xssfWorkbook.getCreationHelper().createFormulaEvaluator();
                formulaEvaluator.evaluateAll();

                //sheet.protectSheet("123456");
                java.io.FileOutputStream out = new java.io.FileOutputStream(fileName);
                xssfWorkbook.write(out);
                out.close();
            }

            lstOfTextFile.add(fileName);
            FileInfo file = new FileInfo(new File(fileName));
            filesList.add(new DownloadFileInfor(file.getName(), fileName,
                    DefineFun.round_up((double) file.getSize() / 1000) + " KB"));
            fullPathList.add(file.getAbsolutePath());
            zipPath = Define.M_ROOT + Define.M_REPORT_XLS + zipFile;
//            }

            if (fullPathList.size() > 1) {
                try {
                    FileZip.ZipFileFromArray(fullPathList, zipPath);
                    zipFileList.add(zipFile);
                    zipFileList.add(zipPath);
                } catch (Exception ex) {
                    Logger.getLogger(ExportText2SbvManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                filePath = zipFile;
                fileName = zipPath;
            }

            System.gc();
            return new FileExportInfo(fileName, filePath);
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " get_data_xaydungkh " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataXayDungKhDetail " + ex.getMessage());
            return null;
        }
    }

    public FileExportInfo xuatExcelMau01BCTK_QD23_Temp(List<String> lstPosCd, String reportDate, String capBC, String savedDirPath, String pos_user) {
        String filePath = "", fileName = "";
        List<String> lstOfTextFile = new ArrayList<>();
        List<DownloadFileInfor> filesList = new ArrayList<>();
        List<String> zipFileList = new ArrayList<>();
        lstOfTextFile.clear();
        filesList.clear();
        zipFileList.clear();
        ArrayList<String> fullPathList = new ArrayList<>();
        String strTimeFile = Long.toString(System.currentTimeMillis());
        String zipFile = "FileNen_MAU_01BCTK_" + strTimeFile + ".zip", zipPath = "";
        try {

            Date dReportDate = new SimpleDateFormat("dd-MMM-yyyy").parse(reportDate);
            String strCurrDate = new SimpleDateFormat("ddMMyyyy").format(dReportDate);
            //duong dan chua file tren o dia + Define.M_REPORT_XLS
            String strPathSave = savedDirPath;
            //Ham nay lay ra ten file bao cao can tao, ten file jasper report

            String strFileSave = pos_user
                    + "_COVID_NLD_QD23_" + strTimeFile.substring(strTimeFile.length() - 4, strTimeFile.length());

            strPathSave += Define.M_REPORT_XLS;
            strFileSave += ".XLSX";
            filePath = strFileSave;

            String templateFile = savedDirPath + Define.M_EXCEL_TEMP + "/KHNV/COVID_NLD_QD23.xlsx";
            fileName = strPathSave + strFileSave;
            File source = new File(templateFile);
            File dest = new File(fileName);

            FileUtil.copyFile(source, dest);

            lstOfTextFile.add(fileName);
            FileInfo file = new FileInfo(new File(fileName));
            filesList.add(new DownloadFileInfor(file.getName(), fileName,
                    DefineFun.round_up((double) file.getSize() / 1000) + " KB"));
            fullPathList.add(file.getAbsolutePath());
            zipPath = Define.M_ROOT + Define.M_REPORT_XLS + zipFile;
//            }

            if (fullPathList.size() > 1) {
                try {
                    FileZip.ZipFileFromArray(fullPathList, zipPath);
                    zipFileList.add(zipFile);
                    zipFileList.add(zipPath);
                } catch (Exception ex) {
                    Logger.getLogger(ExportText2SbvManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                filePath = zipFile;
                fileName = zipPath;
            }

            System.gc();
            return new FileExportInfo(fileName, filePath);
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " get_data_xaydungkh " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataXayDungKhDetail " + ex.getMessage());
            return null;
        }
    }

    public FileExportInfo xuatExcelMauQT11(String posCode, String capbc, String reportDate, String namBc, String savedDirPath, String username) {
        String filePath = "", fileName = "";
        List<String> lstOfTextFile = new ArrayList<>();
        List<DownloadFileInfor> filesList = new ArrayList<>();
        List<String> zipFileList = new ArrayList<>();
        lstOfTextFile.clear();
        filesList.clear();
        zipFileList.clear();
        ArrayList<String> fullPathList = new ArrayList<>();
        String strTimeFile = Long.toString(System.currentTimeMillis());
//        String zipFile = "FileNen_KHNV_QT11_" + strTimeFile + ".zip", zipPath = "";
        try {

            int preYear = Integer.parseInt(namBc) - 1;
            String strPreYear = Integer.toString(preYear);

            //xu ly cho export file ra PDF hoac la Excel
            Date dReportDate = new SimpleDateFormat("dd-MMM-yyyy").parse(reportDate);
            String strCurrDate = new SimpleDateFormat("yyyyMMdd").format(dReportDate);
            //duong dan chua file tren o dia + Define.M_REPORT_XLS
            String strPathSave = savedDirPath;
            //Ham nay lay ra ten file bao cao can tao, ten file jasper report
            String coth = capbc.equals("1") ? "S" : "M";
            String strFileSave = "NV_QT_" + posCode + "_" + coth
                    + "_" + strCurrDate + "_" + username
                    + "_" + strTimeFile.substring(strTimeFile.length() - 4, strTimeFile.length());

            strPathSave += Define.M_REPORT_XLS;
            strFileSave += ".XLSX";
            filePath = strFileSave;

            String templateFile = savedDirPath + Define.M_EXCEL_TEMP + "/KHNV/NV_QT.xlsx";
            fileName = strPathSave + strFileSave;
            File source = new File(templateFile);
            File dest = new File(fileName);

            FileUtil.copyFile(source, dest);

            // Get data
            DaoMau01A daoMau01A = new DaoMau01A();
            List<Mau01AModel> lstData = daoMau01A.getExportDataQt11(posCode, coth, reportDate);

            if (lstData.size() > 0) {

                // Fill data              
                XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new java.io.FileInputStream(fileName));
                //SXSSFWorkbook workbook = new SXSSFWorkbook(xssfWorkbook, 1000);
                XSSFSheet sheet = xssfWorkbook.getSheetAt(0);

                // style
                XSSFCellStyle numberStyle;
                XSSFDataFormat format = xssfWorkbook.createDataFormat();

                //XSSFCellStyle titleStyle;
                //XSSFCellStyle subTitleStyle;
                XSSFCellStyle orderStyle;
                XSSFCellStyle codeStyle;
                XSSFCellStyle nameStyle;
                XSSFCellStyle lockStyle;

//                    String strTitle = "NHU CẦU VAY VỐN TÍN DỤNG CHÍNH SÁCH NĂM " + namBc;
//                    XSSFCell xssfCellTitle = sheet.getRow(4).getCell(0, Row.CREATE_NULL_AS_BLANK);
//                    fillTitle(xssfCellTitle, strTitle);
//                    titleStyle = xssfCellTitle.getCellStyle();
//                    titleStyle.setLocked(true);
//                    xssfCellTitle.setCellStyle(titleStyle);
//                    xssfCellTitle.setCellValue(strTitle);
//                    String strSubCommuneName = lstData.get(0).subCommuneName;
//                    String strSubTitle = "THÔN: " + strSubCommuneName + " XÃ/PHƯỜNG/THỊ TRẤN: " + "communeName";
//                    XSSFCell xssfCellSubTitle = sheet.getRow(5).getCell(0, Row.CREATE_NULL_AS_BLANK);
                //subTitleStyle = xssfCellTitle.getCellStyle();
                //subTitleStyle.setLocked(true);
                //xssfCellSubTitle.setCellStyle(subTitleStyle);
                //xssfCellSubTitle.setCellValue(strSubTitle);
//                    fillTitle(xssfCellSubTitle, strSubTitle);
//                    String colTitle3 = "Ước dư nợ đến 31/12/" + strPreYear;
//                    XSSFCell colTitle = sheet.getRow(7).getCell(3, Row.CREATE_NULL_AS_BLANK);
//                    fillTitle(colTitle, colTitle3);
//
//                    String colTitle5 = "Nhu cầu vốn năm " + namBc;
//                    colTitle = sheet.getRow(7).getCell(4, Row.CREATE_NULL_AS_BLANK);
//                    fillTitle(colTitle, colTitle5);
//                    String colTitle6 = "Tăng, giảm so với 31/12/" + strPreYear;
//                    colTitle = sheet.getRow(8).getCell(5, Row.CREATE_NULL_AS_BLANK);
//                    fillTitle(colTitle, colTitle6);
                for (int i = 0; i < lstData.size(); i++) {
                    XSSFRow xssfRow = sheet.getRow(i + ReportTemplate.MAU_01A_START_ROW);
                    if (xssfRow == null) {
                        xssfRow = sheet.createRow(i + ReportTemplate.MAU_01A_START_ROW);
                    }
                    XSSFFont font = xssfWorkbook.createFont();
                    if (lstData.get(i).printType == ReportTemplate.LEVEL_MAIN) {

                        font.setFontName("Times New Roman");
                        font.setFontHeightInPoints((short) 11);
                        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
                    } else {
                        font.setFontName("Times New Roman");
                        font.setFontHeightInPoints((short) 11);
                    }

                    XSSFCell xssfCell00 = xssfRow.getCell(0, Row.CREATE_NULL_AS_BLANK);

                    orderStyle = xssfCell00.getCellStyle();
                    orderStyle.setAlignment(HorizontalAlignment.LEFT);
                    orderStyle.setFont(font);
                    orderStyle.setLocked(true);

                    xssfCell00.setCellStyle(orderStyle);
                    xssfCell00.setCellValue(lstData.get(i).code);

                    XSSFCell xssfCell01 = xssfRow.getCell(1, Row.CREATE_NULL_AS_BLANK);

                    codeStyle = xssfCell01.getCellStyle();
                    codeStyle.setFont(font);
                    codeStyle.setLocked(true);
                    codeStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                    codeStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                    codeStyle.setAlignment(HorizontalAlignment.CENTER);

                    xssfCell01.setCellStyle(codeStyle);
                    xssfCell01.setCellValue(lstData.get(i).orderDisplay);

                    XSSFCell xssfCell02 = xssfRow.getCell(2, Row.CREATE_NULL_AS_BLANK);

                    nameStyle = xssfCell02.getCellStyle();
                    nameStyle.setAlignment(HorizontalAlignment.LEFT);
                    nameStyle.setLocked(true);
                    nameStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                    nameStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

                    xssfCell02.setCellStyle(nameStyle);
                    xssfCell02.setCellValue(lstData.get(i).name);

                    XSSFCell xssfCell03 = xssfRow.getCell(3, Row.CREATE_NULL_AS_BLANK);
                    numberStyle = xssfCell03.getCellStyle();
                    numberStyle.setDataFormat(format.getFormat("#,##0.00"));
                    numberStyle.setAlignment(HorizontalAlignment.RIGHT);
                    numberStyle.setFont(font);

                    if (lstData.get(i).getSubCommuneName().equals("1")) {
                        numberStyle.setLocked(true);
                    } else {
                        numberStyle.setLocked(false);
                    }
                    xssfCell03.setCellStyle(numberStyle);
                    xssfCell03.setCellValue(lstData.get(i).d1);

                    XSSFCell xssfCell04 = xssfRow.getCell(4, Row.CREATE_NULL_AS_BLANK);
                    numberStyle = xssfCell04.getCellStyle();
                    numberStyle.setDataFormat(format.getFormat("#,##0.00"));
                    numberStyle.setAlignment(HorizontalAlignment.RIGHT);
                    numberStyle.setFont(font);
                    if (lstData.get(i).getSubCommuneName().equals("1")) {
                        numberStyle.setLocked(true);
                    } else {
                        numberStyle.setLocked(false);
                    }
                    xssfCell04.setCellStyle(numberStyle);
                    xssfCell04.setCellValue(lstData.get(i).d2);

                    XSSFCell xssfCell05 = xssfRow.getCell(5, Row.CREATE_NULL_AS_BLANK);
                    numberStyle = xssfCell05.getCellStyle();
                    numberStyle.setDataFormat(format.getFormat("#,##0.00"));
                    numberStyle.setAlignment(HorizontalAlignment.RIGHT);
                    numberStyle.setFont(font);
                    if (lstData.get(i).getSubCommuneName().equals("1")) {
                        numberStyle.setLocked(true);
                    } else {
                        numberStyle.setLocked(false);
                    }
                    xssfCell05.setCellStyle(numberStyle);
                    xssfCell05.setCellValue(lstData.get(i).d3);

//                        XSSFCell xssfCell04 = xssfRow.getCell(4, Row.CREATE_NULL_AS_BLANK);
//                        lockStyle = xssfCell04.getCellStyle();
//                        lockStyle.setDataFormat(format.getFormat("#,##0.00"));
//                        lockStyle.setAlignment(HorizontalAlignment.RIGHT);
//                        lockStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//                        lockStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//                        lockStyle.setFont(font);
//                        lockStyle.setLocked(false);
//                        xssfCell04.setCellStyle(lockStyle);
//                        XSSFCell xssfCell06 = xssfRow.getCell(6, Row.CREATE_NULL_AS_BLANK);
//                        lockStyle = xssfCell06.getCellStyle();
//                        lockStyle.setDataFormat(format.getFormat("#,##0.00"));
//                        lockStyle.setAlignment(HorizontalAlignment.RIGHT);
//                        lockStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//                        lockStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//                        lockStyle.setFont(font);
//                        lockStyle.setLocked(true);
//                        xssfCell06.setCellStyle(lockStyle);
                }

                FormulaEvaluator formulaEvaluator = xssfWorkbook.getCreationHelper().createFormulaEvaluator();
                formulaEvaluator.evaluateAll();

                sheet.protectSheet("123456");
                java.io.FileOutputStream out = new java.io.FileOutputStream(fileName);
                xssfWorkbook.write(out);
                out.close();
            }

            lstOfTextFile.add(fileName);
            FileInfo file = new FileInfo(new File(fileName));
            filesList.add(new DownloadFileInfor(file.getName(), fileName,
                    DefineFun.round_up((double) file.getSize() / 1000) + " KB"));
            fullPathList.add(file.getAbsolutePath());

            System.gc();
            return new FileExportInfo(fileName, filePath);
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " get_data_xaydungkh " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataXayDungKhDetail " + ex.getMessage());
            return null;
        }
    }
}
