/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.khnv2021.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
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
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.khnv2021.model.DULIEU_NT_100;
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
                                if (lstData.get(k).code.equals(code)
                                        && code != "XD00024" && code != "XD00052") {
                                    numberStyle = dataPrinCell.getCellStyle();
                                    numberStyle.setDataFormat(format.getFormat("#,##0"));
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

                    sheet.protectSheet("khnv2024");
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
                    fillTitle(titleCell, "NHU CẦU VỐN TÍN DỤNG CHÍNH SÁCH NĂM " + (Integer.parseInt(namBc) - 2) + "-" + namBc);

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
                                    numberStyle.setDataFormat(format.getFormat("#,##0;-#,##0;;@"));
                                    numberStyle.setAlignment(HorizontalAlignment.RIGHT);
                                    //numberStyle.setFont(font);
                                    numberStyle.setLocked(false);
                                    dataPrinCell6.setCellStyle(numberStyle);
                                    dataPrinCell6.setCellValue(lstData.get(k).d4);

                                    numberStyle = dataPrinCell7.getCellStyle();
                                    numberStyle.setDataFormat(format.getFormat("#,##0;-#,##0;;@"));
                                    numberStyle.setAlignment(HorizontalAlignment.RIGHT);
                                    //numberStyle.setFont(font);
                                    numberStyle.setLocked(false);
                                    dataPrinCell7.setCellStyle(numberStyle);
                                    dataPrinCell7.setCellValue(lstData.get(k).d5);

                                    numberStyle = dataPrinCell8.getCellStyle();
                                    numberStyle.setDataFormat(format.getFormat("#,##0;-#,##0;;@"));
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
                        numberStyle.setDataFormat(format.getFormat("#,##0"));
                        numberStyle.setAlignment(HorizontalAlignment.RIGHT);
                        numberStyle.setFont(font);
                        numberStyle.setLocked(false);
                        xssfCell03.setCellStyle(numberStyle);
                        xssfCell03.setCellValue(lstData.get(i).d2);

                        XSSFCell xssfCell05 = xssfRow.getCell(5, Row.CREATE_NULL_AS_BLANK);
                        numberStyle = xssfCell05.getCellStyle();
                        numberStyle.setDataFormat(format.getFormat("#,##0"));
                        numberStyle.setAlignment(HorizontalAlignment.RIGHT);
                        numberStyle.setFont(font);
                        numberStyle.setLocked(false);
                        xssfCell05.setCellStyle(numberStyle);
                        xssfCell05.setCellValue(lstData.get(i).d4);

                        XSSFCell xssfCell04 = xssfRow.getCell(4, Row.CREATE_NULL_AS_BLANK);
                        lockStyle = xssfCell04.getCellStyle();
                        lockStyle.setDataFormat(format.getFormat("#,##0"));
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

    public FileExportInfo xuatExcel_Mau01_2024(String posCode, String commune, String subcommune, String name_subcommune, String reportDate, String namBc, String dotBc, String savedDirPath) throws SQLException {
        String filePath = "", fileName = "";
        List<String> lstOfTextFile = new ArrayList<>();
        List<DownloadFileInfor> filesList = new ArrayList<>();
        List<String> zipFileList = new ArrayList<>();
        lstOfTextFile.clear();
        filesList.clear();
        zipFileList.clear();
        XDKHDao2021 daoXdkh = new XDKHDao2021();
        List<POSModel> subCommuneList = daoXdkh.getSubCommuneList(posCode, commune);
        List<String> lstTitleData = daoXdkh.getTitleData(posCode);
        ArrayList<String> fullPathList = new ArrayList<>();
        String strTimeFile = Long.toString(System.currentTimeMillis());
        String zipFile = "FileNen_KHNV01_" + strTimeFile + ".zip", zipPath = "";
        String cSeach = ServletActionContext.getRequest().getParameter("cSeach");
        try {

            String strPreYear = getPreYearString(Integer.parseInt(namBc));
            String strTwoYearAgo = getPreYearString(Integer.parseInt(namBc) - 1);

            //xu ly cho export file ra PDF hoac la Excel
            Date dReportDate = new SimpleDateFormat("dd-MMM-yyyy").parse(reportDate);
            String strCurrDate = new SimpleDateFormat("ddMMyyyy").format(dReportDate);
            //duong dan chua file tren o dia + Define.M_REPORT_XLS
            //String strPathSave = savedDirPath;
            //Ham nay lay ra ten file bao cao can tao, ten file jasper report

            DaoMau02 daoMau02 = new DaoMau02();

            String templateFile = savedDirPath + Define.M_EXCEL_TEMP + "/KHNV/KHNV_01C_2024.xlsx";

//            for (DistrictInfo district : lstDistrict) {
            String strFileSave = "KHNV_01C_2024_" + commune //+ "_" + district.districtCode
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
            List<DULIEU_NT_100> lstData = daoMau02.getExportData_2024(commune, "", "", reportDate);

            if (lstData.size() > 0) {

                // Fill data              
                XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new java.io.FileInputStream(fileName));
                XSSFSheet sheet = xssfWorkbook.getSheetAt(0);

                String strTitle = "NHU CẦU VAY VỐN TÍN DỤNG CHÍNH SÁCH GIAI ĐOẠN " + namBc + " - " + (Integer.parseInt(namBc) + 4);
                XSSFCell xssfCellTitle = sheet.getRow(3).getCell(0, Row.CREATE_NULL_AS_BLANK);
                XSSFCell xssfCellTitle0 = sheet.getRow(3).getCell(28, Row.CREATE_NULL_AS_BLANK);
                XSSFCell xssfCellTitle1 = sheet.getRow(3).getCell(51, Row.CREATE_NULL_AS_BLANK);
                fillTitle(xssfCellTitle, strTitle);
                fillTitle(xssfCellTitle0, strTitle);
                fillTitle(xssfCellTitle1, strTitle);

//                String strTitle1 = "XÃ/PHƯỜNG/THỊ TRẤN: " + " " +"; HUYỆN: " + "; TỈNH: " ;
                String strTitle1 = "XÃ/PHƯỜNG/THỊ TRẤN: " + name_subcommune.toUpperCase() + "; HUYỆN:" + lstTitleData.get(0).toUpperCase() + "; TỈNH: " + lstTitleData.get(1).toUpperCase();

                XSSFCell xssfCellTitle2 = sheet.getRow(4).getCell(0, Row.CREATE_NULL_AS_BLANK);
                XSSFCell xssfCellTitle3 = sheet.getRow(4).getCell(28, Row.CREATE_NULL_AS_BLANK);
                XSSFCell xssfCellTitle4 = sheet.getRow(4).getCell(51, Row.CREATE_NULL_AS_BLANK);
                fillTitle(xssfCellTitle2, strTitle1);
                fillTitle(xssfCellTitle3, strTitle1);
                fillTitle(xssfCellTitle4, strTitle1);

                XSSFWorkbook workbook = sheet.getWorkbook();

                XSSFCellStyle boldStyle = createCellStyle(workbook, true, false, (short) 12, "Times New Roman", HorizontalAlignment.CENTER, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
                XSSFCellStyle orderStyle = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
                XSSFCellStyle codeStyle = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, false, false);
                XSSFCellStyle leftStyle = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.LEFT, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
                XSSFCellStyle numberStyle = createCellStyle(workbook, true, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, false, false);

                for (int i = 0; i < lstData.size(); i++) {
                    XSSFRow xssfRow = sheet.getRow(i + 11);
                    if (xssfRow == null) {
                        xssfRow = sheet.createRow(i + 11);
                    }

                    XSSFCell xssfCell00 = xssfRow.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    XSSFCell xssfCell01 = xssfRow.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    XSSFCell xssfCell02 = xssfRow.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    XSSFCell xssfCell03 = xssfRow.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    XSSFCell xssfCell04 = xssfRow.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    XSSFCell xssfCell05 = xssfRow.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    XSSFCell xssfCell18 = xssfRow.getCell(20, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    XSSFCell xssfCell28 = xssfRow.getCell(28, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    XSSFCell xssfCell33 = xssfRow.getCell(35, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    XSSFCell xssfCell46 = xssfRow.getCell(48, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    for (int ii = 4; ii < 67; ii++) {
                        if (ii == 4 || ii == 5 || ii == 20 || ii == 28 || ii == 35 || ii == 48) {
                            continue; // Bỏ qua giá trị này và tiếp tục vòng lặp
                        }
                        XSSFCell xssfCell = xssfRow.getCell(ii, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        if (lstData.get(i).getKIEUIN() == 0) {
                            xssfCell.setCellStyle(numberStyle);
                        } else {
                            xssfCell.setCellStyle(codeStyle);
                        }
                        // Lấy giá trị tương ứng từ getD1 đến getD63
                        try {
                            // Bỏ qua cell 2 và 3
                            if (ii != 4 && ii != 5 && ii != 20 && ii != 28 && ii != 35 && ii != 48) {
                                Method method = lstData.get(i).getClass().getMethod("getD" + (ii - 3));
                                Object value = method.invoke(lstData.get(i));
                                if (value != null) {
                                    if (value instanceof Number) {
                                        xssfCell.setCellValue(((Number) value).doubleValue());
                                    } else if (value instanceof String) {
                                        try {
                                            // chuyển chuỗi thành số
                                            double doubleValue = Double.parseDouble((String) value);
                                            xssfCell.setCellValue(doubleValue);
                                        } catch (NumberFormatException e) {
                                            // Nếu chuỗi không thể chuyển thành số, kiểm tra xem nó có thể là công thức không
                                            if (((String) value).startsWith("=")) {
                                                // Nếu là công thức, sử dụng setCellFormula thay vì setCellValue
                                                xssfCell.setCellFormula(((String) value).substring(1));
                                            } else {
                                                // Nếu không phải công thức, ghi giá trị chuỗi vào ô Excel
                                                xssfCell.setCellValue((String) value);
                                            }
                                        }
                                    } else {
                                        // Xử lý các loại khác nếu cần thiết
                                        xssfCell.setCellValue(value.toString());
                                    }
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    // Kiểm tra printType
                    if (lstData.get(i).getKIEUIN() == 0) {
                        xssfCell00.setCellStyle(boldStyle);
                        xssfCell01.setCellStyle(boldStyle);
                        xssfCell02.setCellStyle(numberStyle);
                        xssfCell03.setCellStyle(numberStyle);
                        xssfCell04.setCellStyle(boldStyle);
                        xssfCell05.setCellStyle(boldStyle);
                        xssfCell18.setCellStyle(numberStyle);
                        xssfCell28.setCellStyle(numberStyle);
                        xssfCell33.setCellStyle(numberStyle);
                        xssfCell46.setCellStyle(numberStyle);
                    }

                    xssfCell00.setCellValue(lstData.get(i).getTT_HIENTHI());
                    xssfCell01.setCellValue(lstData.get(i).getTEN());
                    xssfCell04.setCellValue(lstData.get(i).getMA());
                    xssfCell05.setCellValue(lstData.get(i).getD100());
                }

                for (int i = lstData.size(); i < sheet.getLastRowNum() + 1; i++) {
                    XSSFRow rowToDelete = sheet.getRow(i + 11);
                    if (rowToDelete != null) {
                        sheet.removeRow(rowToDelete);
                    }
                }
                // đóng khung dòng cuối
                int lastRowNum = sheet.getLastRowNum();
                XSSFRow lastRow = sheet.getRow(lastRowNum);
                if (lastRow == null) {
                    lastRow = sheet.createRow(lastRowNum);
                }
                XSSFRow borderRow = sheet.createRow(lastRowNum + 1);
                XSSFCellStyle borderStyle = sheet.getWorkbook().createCellStyle();
                borderStyle.setBorderTop(BorderStyle.THIN);
                for (int i = 0; i < lastRow.getLastCellNum(); i++) {
                    if (borderRow.getCell(i) == null) {
                        borderRow.createCell(i);
                    }
                    borderRow.getCell(i).setCellStyle(borderStyle);
                }
                // Thêm nội dung phần cuối
                int startRowNum = lstData.size() + 13;
                XSSFRow row1 = sheet.createRow(startRowNum);
                XSSFRow row2 = sheet.createRow(startRowNum + 1);

                XSSFCell cell1_1 = row1.createCell(5);
                cell1_1.setCellValue("CÁN BỘ TÍN DỤNG THEO DÕI ĐỊA BÀN");
                XSSFCell cell1_2 = row1.createCell(18);
                cell1_2.setCellValue("CHỦ TỊCH UBND XÃ/PHƯỜNG/THỊ TRẤN");

                XSSFCell cell2_1 = row2.createCell(5);
                cell2_1.setCellValue("(Ký, ghi rõ họ và tên)");
                XSSFCell cell2_2 = row2.createCell(18);
                cell2_2.setCellValue("(Ký tên, đóng dấu)");

                // Style for signature rows
                XSSFCellStyle signatureStyle = xssfWorkbook.createCellStyle();
                XSSFFont signatureBoldFont = xssfWorkbook.createFont();
                signatureBoldFont.setBold(true);
                signatureBoldFont.setFontHeightInPoints((short) 12); // Set cỡ chữ 12
                signatureBoldFont.setFontName("Times New Roman");
                signatureStyle.setFont(signatureBoldFont);
                signatureStyle.setAlignment(HorizontalAlignment.CENTER);

                cell1_1.setCellStyle(signatureStyle);
                cell1_2.setCellStyle(signatureStyle);

                XSSFCellStyle italicStyle = xssfWorkbook.createCellStyle();
                XSSFFont italicFont = xssfWorkbook.createFont();
                italicFont.setItalic(true);
                italicFont.setFontHeightInPoints((short) 12); // Set cỡ chữ 12
                italicFont.setFontName("Times New Roman");
                italicStyle.setFont(italicFont);
                italicStyle.setAlignment(HorizontalAlignment.CENTER);

                cell2_1.setCellStyle(italicStyle);
                cell2_2.setCellStyle(italicStyle);

                FormulaEvaluator formulaEvaluator = xssfWorkbook.getCreationHelper().createFormulaEvaluator();
                formulaEvaluator.evaluateAll();
                sheet.protectSheet("khnv2024");
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

    public FileExportInfo xuatExcel_Mau02_2024(POSModel pos, String posFlag, String reportDate, String namBc, String dotBc, String savedDirPath) {
        String filePath = "", fileName = "";
        List<String> lstOfTextFile = new ArrayList<>();
        List<DownloadFileInfor> filesList = new ArrayList<>();
        List<String> zipFileList = new ArrayList<>();
        lstOfTextFile.clear();
        filesList.clear();
        zipFileList.clear();
        ArrayList<String> fullPathList = new ArrayList<>();
        String strTimeFile = Long.toString(System.currentTimeMillis());
        String zipFile = "FileNen_KHNV02_2024" + strTimeFile + ".zip", zipPath = "";
        String posCode = pos.getId();
        try {
            Date dReportDate = (new SimpleDateFormat("dd-MMM-yyyy")).parse(reportDate);
            String strCurrDate = (new SimpleDateFormat("ddMMyyyy")).format(dReportDate);
            String strPosFlag = "";
            if (posFlag.equals("N")) {
                strPosFlag = "S";
            } else {
                strPosFlag = "M";
            }
            DaoMau02 daoMau02 = new DaoMau02();
            List<DistrictInfo> lstDistrict = daoMau02.getDistrictByPos(posCode);
            String templateFile = savedDirPath + "EXCEL_TEMPLATE/" + "/KHNV/KHNV_02C_2024.xlsx";
            String strFileSave = "KHNV_02C_2024_" + posCode + "_" + strPosFlag + "_" + strCurrDate + "_" + strTimeFile.substring(strTimeFile.length() - 4, strTimeFile.length());
            String strPathSave = savedDirPath + "EXPORT_REPORT/XLS/";
            strFileSave = strFileSave + ".XLSX";
            filePath = strFileSave;
            fileName = strPathSave + strFileSave;
            File source = new File(templateFile);
            File dest = new File(fileName);
            FileUtil.copyFile(source, dest);
            List<DULIEU_NT_100> lstData = daoMau02.getExportData_02_2024(posCode, posFlag, "", reportDate);
            if (lstData.size() > 0) {
                XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new java.io.FileInputStream(fileName));
                XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
                XSSFWorkbook workbook = sheet.getWorkbook();
                XSSFCellStyle boldStyle = createCellStyle(workbook, true, false, (short) 12, "Times New Roman", HorizontalAlignment.CENTER, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
                XSSFCellStyle leftStyle = createCellStyle(workbook, true, false, (short) 12, "Times New Roman", HorizontalAlignment.LEFT, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
                XSSFCellStyle codeStyleSTT = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.CENTER, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
                XSSFCellStyle codeStyle = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.LEFT, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
                XSSFCellStyle italicsStyle = createCellStyle(workbook, false, true, (short) 12, "Times New Roman", HorizontalAlignment.LEFT, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
                XSSFCellStyle italicsStyleSTT = createCellStyle(workbook, false, true, (short) 12, "Times New Roman", HorizontalAlignment.CENTER, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
                XSSFCellStyle numberStyle = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, false, false);
                XSSFCellStyle numberStylep = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, true, false);
                XSSFCellStyle numberStylea = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, false, true);
                XSSFCellStyle numberStyle1 = createCellStyle(workbook, true, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, false, false);
                XSSFCellStyle numberStyle1p = createCellStyle(workbook, true, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, true, false);
                XSSFCellStyle numberStyle1a = createCellStyle(workbook, true, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, false, true);
                XSSFCellStyle numberStyle2 = createCellStyle(workbook, false, true, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, false, false);
                XSSFCellStyle numberStyle2p = createCellStyle(workbook, false, true, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, true, false);
                XSSFCellStyle numberStyle2a = createCellStyle(workbook, false, true, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, false, true);
                XSSFCellStyle numberStyle00 = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
                XSSFCellStyle numberStyle00p = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, true, false);
                XSSFCellStyle numberStyle00a = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, false, true);
                XSSFCellStyle numberStyle11 = createCellStyle(workbook, true, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
                XSSFCellStyle numberStyle11p = createCellStyle(workbook, true, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, true, false);
                XSSFCellStyle numberStyle11a = createCellStyle(workbook, true, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, false, true);
                XSSFCellStyle numberStyle22 = createCellStyle(workbook, false, true, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
                XSSFCellStyle numberStyle22p = createCellStyle(workbook, false, true, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, true, false);
                XSSFCellStyle numberStyle22a = createCellStyle(workbook, false, true, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, false, true);
                String strTitle = "KẾ HOẠCH TÍN DỤNG GIAI ĐOẠN " + namBc + " - " + (Integer.parseInt(namBc) + 4);
                XSSFCell xssfCellTitle = sheet.getRow(4).getCell(0, Row.CREATE_NULL_AS_BLANK);
                fillTitle(xssfCellTitle, strTitle);

                String strPosTitle = pos.getDesc().toUpperCase();
                XSSFCell xssfPosTitle = sheet.getRow(2).getCell(1, Row.CREATE_NULL_AS_BLANK);
                fillTitle(xssfPosTitle, strPosTitle);

                String colTitle3 = "Ước thực hiện đến 31/12/" + (Integer.parseInt(namBc) - 2);
                XSSFCell colTitle = sheet.getRow(6).getCell(3, Row.CREATE_NULL_AS_BLANK);
                fillTitle(colTitle, colTitle3);

                String colTitle5 = "Ước thực hiện đến 31/12/" + (Integer.parseInt(namBc) - 1);
                colTitle = sheet.getRow(6).getCell(4, Row.CREATE_NULL_AS_BLANK);
                fillTitle(colTitle, colTitle5);

                String colTitle6 = "Kế hoạch tín dụng năm " + namBc;
                colTitle = sheet.getRow(6).getCell(5, Row.CREATE_NULL_AS_BLANK);
                fillTitle(colTitle, colTitle6);

                String colTitle55 = "Tăng, giảm so với 31/12/" + (Integer.parseInt(namBc) - 1);
                colTitle = sheet.getRow(7).getCell(6, Row.CREATE_NULL_AS_BLANK);
                fillTitle(colTitle, colTitle55);

                String colTitle7 = "Kế hoạch tín dụng năm " + (Integer.parseInt(namBc) + 1);
                colTitle = sheet.getRow(6).getCell(8, Row.CREATE_NULL_AS_BLANK);
                fillTitle(colTitle, colTitle7);

                String colTitle77 = "Tăng, giảm so với 31/12/" + namBc;
                colTitle = sheet.getRow(7).getCell(9, Row.CREATE_NULL_AS_BLANK);
                fillTitle(colTitle, colTitle77);

                String colTitle8 = "Kế hoạch tín dụng năm " + (Integer.parseInt(namBc) + 2);
                colTitle = sheet.getRow(6).getCell(11, Row.CREATE_NULL_AS_BLANK);
                fillTitle(colTitle, colTitle8);

                String colTitle88 = "Tăng, giảm so với 31/12/" + (Integer.parseInt(namBc) + 1);
                colTitle = sheet.getRow(7).getCell(12, Row.CREATE_NULL_AS_BLANK);
                fillTitle(colTitle, colTitle88);

                String colTitle9 = "Kế hoạch tín dụng năm " + (Integer.parseInt(namBc) + 3);
                colTitle = sheet.getRow(6).getCell(14, Row.CREATE_NULL_AS_BLANK);
                fillTitle(colTitle, colTitle9);

                String colTitle99 = "Tăng, giảm so với 31/12/" + (Integer.parseInt(namBc) + 2);
                colTitle = sheet.getRow(7).getCell(15, Row.CREATE_NULL_AS_BLANK);
                fillTitle(colTitle, colTitle99);

                String colTitle10 = "Kế hoạch tín dụng năm " + (Integer.parseInt(namBc) + 4);
                colTitle = sheet.getRow(6).getCell(17, Row.CREATE_NULL_AS_BLANK);
                fillTitle(colTitle, colTitle10);

                String colTitle100 = "Tăng, giảm so với 31/12/" + (Integer.parseInt(namBc) + 3);
                colTitle = sheet.getRow(7).getCell(19, Row.CREATE_NULL_AS_BLANK);
                fillTitle(colTitle, colTitle100);
                String colTitle101 = "Tăng, giảm so với 31/12/" + (Integer.parseInt(namBc) - 1);
                colTitle = sheet.getRow(7).getCell(20, Row.CREATE_NULL_AS_BLANK);
                fillTitle(colTitle, colTitle101);
                for (int i = 0; i < lstData.size(); i++) {
                    XSSFRow xssfRow = sheet.getRow(i + 11);
                    if (xssfRow == null) {
                        xssfRow = sheet.createRow(i + 11);
                    }
                    XSSFCell xssfCell00 = xssfRow.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    XSSFCell xssfCell02 = xssfRow.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    XSSFCell xssfCell01 = xssfRow.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    if (((DULIEU_NT_100) lstData.get(i)).getKIEUIN() == 1) {
                        xssfCell00.setCellStyle((CellStyle) boldStyle);
                        xssfCell02.setCellStyle((CellStyle) codeStyleSTT);
                        xssfCell01.setCellStyle((CellStyle) leftStyle);
                    } else if (((DULIEU_NT_100) lstData.get(i)).getKIEUIN() == 2) {
                        xssfCell00.setCellStyle((CellStyle) italicsStyleSTT);
                        xssfCell02.setCellStyle((CellStyle) codeStyleSTT);
                        xssfCell01.setCellStyle((CellStyle) italicsStyle);
                    } else {
                        xssfCell00.setCellStyle((CellStyle) codeStyleSTT);
                        xssfCell02.setCellStyle((CellStyle) codeStyleSTT);
                        xssfCell01.setCellStyle((CellStyle) codeStyle);
                    }
                    xssfCell00.setCellValue(((DULIEU_NT_100) lstData.get(i)).getTT_HIENTHI());
                    xssfCell02.setCellValue(((DULIEU_NT_100) lstData.get(i)).getMA());
                    xssfCell01.setCellValue(((DULIEU_NT_100) lstData.get(i)).getTEN());
                    int[] _arrIncludeCol = {3, 4};
                    int[] _arrExcludeCol = {
                        6, 7, 9, 10, 12, 13, 15, 16, 18, 19,
                        20, 21};
                    int[] _arrLockRow = {
                        0, 1, 2, 3, 7, 8, 23, 31, 38, 51,
                        55};
                    int[] _arrPercentCol = {7, 10, 13, 16, 19, 21};
                    int[] _arrAbsoluteCol = {6, 9, 12, 15, 18, 20};
                    for (int ii = 3; ii < 22; ii++) {
                        XSSFCell xssfCell = xssfRow.getCell(ii, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        if (((DULIEU_NT_100) lstData.get(i)).getKIEUIN() == 1) {
                            if (inArray(_arrExcludeCol, ii)
                                    || inArray(_arrLockRow, i) || (i > 7 && i < 72 && i != 54
                                    && !inArray(_arrIncludeCol, ii))) {
                                if (inArray(_arrPercentCol, ii)) {
                                    xssfCell.setCellStyle((CellStyle) numberStyle11p);
                                } else if (inArray(_arrAbsoluteCol, ii)) {
                                    xssfCell.setCellStyle((CellStyle) numberStyle11a);
                                } else {
                                    xssfCell.setCellStyle((CellStyle) numberStyle11);
                                }
                            } else if (inArray(_arrPercentCol, ii)) {
                                xssfCell.setCellStyle((CellStyle) numberStyle1p);
                            } else if (inArray(_arrAbsoluteCol, ii)) {
                                xssfCell.setCellStyle((CellStyle) numberStyle1a);
                            } else {
                                xssfCell.setCellStyle((CellStyle) numberStyle1);
                            }
                        } else if (((DULIEU_NT_100) lstData.get(i)).getKIEUIN() == 2) {
                            if (inArray(_arrExcludeCol, ii)
                                    || inArray(_arrLockRow, i) || (i > 7 && i < 72 && i != 54
                                    && !inArray(_arrIncludeCol, ii))) {
                                if (inArray(_arrPercentCol, ii)) {
                                    xssfCell.setCellStyle((CellStyle) numberStyle22p);
                                } else if (inArray(_arrAbsoluteCol, ii)) {
                                    xssfCell.setCellStyle((CellStyle) numberStyle22a);
                                } else {
                                    xssfCell.setCellStyle((CellStyle) numberStyle22);
                                }
                            } else if (inArray(_arrPercentCol, ii)) {
                                xssfCell.setCellStyle((CellStyle) numberStyle2p);
                            } else if (inArray(_arrAbsoluteCol, ii)) {
                                xssfCell.setCellStyle((CellStyle) numberStyle2a);
                            } else {
                                xssfCell.setCellStyle((CellStyle) numberStyle2);
                            }
                        } else if (inArray(_arrExcludeCol, ii)
                                || inArray(_arrLockRow, i) || (i > 7 && i < 72 && i != 54
                                && !inArray(_arrIncludeCol, ii))) {
                            if (inArray(_arrPercentCol, ii)) {
                                xssfCell.setCellStyle((CellStyle) numberStyle00p);
                            } else if (inArray(_arrAbsoluteCol, ii)) {
                                xssfCell.setCellStyle((CellStyle) numberStyle00a);
                            } else {
                                xssfCell.setCellStyle((CellStyle) numberStyle00);
                            }
                        } else if (inArray(_arrPercentCol, ii)) {
                            xssfCell.setCellStyle((CellStyle) numberStylep);
                        } else if (inArray(_arrAbsoluteCol, ii)) {
                            xssfCell.setCellStyle((CellStyle) numberStylea);
                        } else {
                            xssfCell.setCellStyle((CellStyle) numberStyle);
                        }
                        try {
                            Method method = ((DULIEU_NT_100) lstData.get(i)).getClass().getMethod("getD" + (ii - 2), new Class[0]);
                            Object value = method.invoke(lstData.get(i), new Object[0]);
                            if (value != null) {
                                if (value instanceof Number) {
                                    xssfCell.setCellValue(((Number) value).doubleValue());
                                } else {
                                    try {
                                        double doubleValue = Double.parseDouble(value.toString());
                                        xssfCell.setCellValue(doubleValue);
                                    } catch (NumberFormatException e) {
                                        xssfCell.setCellValue(value.toString());
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                CellStyle defaultStyle = workbook.createCellStyle();

                int startRow = lstData.size() + 11;
                for (int i = sheet.getNumMergedRegions() - 1; i >= 0; i--) {
                    CellRangeAddress mergedRegion = sheet.getMergedRegion(i);
                    if (mergedRegion.getFirstRow() >= startRow) {
                        sheet.removeMergedRegion(i);
                    }
                }

                for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
                    XSSFRow row = sheet.getRow(i);
                    if (row != null) {
                        for (Cell cell : row) {
                            // Loại bỏ wrap text
                            cell.getCellStyle().setWrapText(false);
                            cell.setCellStyle(defaultStyle);
                        }
                        sheet.removeRow(row);
                    }
                }
                if (startRow <= sheet.getLastRowNum()) {
                    sheet.shiftRows(startRow + 1, sheet.getLastRowNum(), startRow - sheet.getLastRowNum() - 1);
                }
                // đóng khung dòng cuối
                int lastRowNum = sheet.getLastRowNum();
                XSSFRow lastRow = sheet.getRow(lastRowNum);
                if (lastRow == null) {
                    lastRow = sheet.createRow(lastRowNum);
                }
                XSSFRow borderRow = sheet.createRow(lastRowNum + 1);
                XSSFCellStyle borderStyle = sheet.getWorkbook().createCellStyle();
                borderStyle.setBorderTop(BorderStyle.THIN);
                for (int i = 0; i < lastRow.getLastCellNum(); i++) {
                    if (borderRow.getCell(i) == null) {
                        borderRow.createCell(i);
                    }
                    borderRow.getCell(i).setCellStyle(borderStyle);
                }
                int startRowNum = lstData.size() + 12;
                XSSFRow row1 = sheet.createRow(startRowNum);
                XSSFRow row2 = sheet.createRow(startRowNum + 1);
                XSSFRow row3 = sheet.createRow(startRowNum + 2);

                XSSFCell cell1_1 = row1.createCell(14);
                cell1_1.setCellValue("…, ngày … tháng … Năm ……");

                XSSFCell cell1_2 = row2.createCell(2);
                cell1_2.setCellValue("GIÁM ĐỐC");

                XSSFCell cell1_3 = row2.createCell(14);
                cell1_3.setCellValue("TRƯỞNG BĐD HĐQT - NHCSXH");

                XSSFCell cell2_2 = row3.createCell(2);
                cell2_2.setCellValue("(Ký tên)");
                XSSFCell cell2_3 = row3.createCell(14);
                cell2_3.setCellValue("(Ký tên, đóng dấu)");

                XSSFCellStyle signatureStyle = xssfWorkbook.createCellStyle();
                XSSFFont signatureBoldFont = xssfWorkbook.createFont();
                signatureBoldFont.setBold(true);
                signatureBoldFont.setFontHeightInPoints((short) 12); // Set cỡ chữ 12
                signatureBoldFont.setFontName("Times New Roman");
                signatureStyle.setFont(signatureBoldFont);
                signatureStyle.setAlignment(HorizontalAlignment.CENTER);

                cell1_2.setCellStyle(signatureStyle);
                cell1_3.setCellStyle(signatureStyle);

                XSSFCellStyle italicStyle = xssfWorkbook.createCellStyle();
                XSSFFont italicFont = xssfWorkbook.createFont();
                italicFont.setItalic(true);
                italicFont.setFontHeightInPoints((short) 12); // Set cỡ chữ 12
                italicFont.setFontName("Times New Roman");
                italicStyle.setFont(italicFont);
                italicStyle.setAlignment(HorizontalAlignment.CENTER);
                cell1_1.setCellStyle(italicStyle);
                cell2_2.setCellStyle(italicStyle);
                cell2_3.setCellStyle(italicStyle);
                FormulaEvaluator formulaEvaluator = xssfWorkbook.getCreationHelper().createFormulaEvaluator();
                formulaEvaluator.evaluateAll();
                sheet.protectSheet("khnv2024");
                java.io.FileOutputStream out = new java.io.FileOutputStream(fileName);
                xssfWorkbook.write(out);
                out.close();
                lstOfTextFile.add(fileName);
                FileInfo file = new FileInfo(new File(fileName));
                filesList.add(new DownloadFileInfor(file.getName(), fileName,
                        DefineFun.round_up(Double.valueOf(file.getSize() / 1000.0D)) + " KB"));
                fullPathList.add(file.getAbsolutePath());
                zipPath = Define.M_ROOT + "EXPORT_REPORT/XLS/" + zipFile;
            }
            if (fullPathList.size() > 1) {
                try {
                    FileZip.ZipFileFromArray(fullPathList, zipPath);
                    zipFileList.add(zipFile);
                    zipFileList.add(zipPath);
                } catch (Exception ex) {
                    Logger.getLogger(ExportText2SbvManager.class.getName()).log(Level.SEVERE, (String) null, ex);
                }
                filePath = zipFile;
                fileName = zipPath;
            }
            System.gc();
            return new FileExportInfo(fileName, filePath);
        } catch (Exception ex) {
            CoreLogger.error(getClass().getName() + " xuatExcelMau02_2024 " + ex.getMessage());
            System.err.println(getClass().getName() + " loi xuatExcelMau02_2024 " + ex.getMessage());
            return null;
        }
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
                    orderStyle.setAlignment(HorizontalAlignment.CENTER);
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
                    lockStyle.setDataFormat(format.getFormat("#,##0;-#,##0;;@"));
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

                        xssfCell03.setCellValue(lstData.get(i).d1);

                    }

                    XSSFCell xssfCell04 = xssfRow.getCell(4, Row.CREATE_NULL_AS_BLANK);
                    numberStyle = xssfCell04.getCellStyle();
                    numberStyle.setDataFormat(format.getFormat("#,##0;-#,##0;;@"));
                    numberStyle.setAlignment(HorizontalAlignment.RIGHT);
                    xssfCell04.setCellValue(lstData.get(i).d2);

                    XSSFCell xssfCell05 = xssfRow.getCell(5, Row.CREATE_NULL_AS_BLANK);
                    lockStyle = xssfCell05.getCellStyle();
                    lockStyle.setDataFormat(format.getFormat("#,##0;-#,##0;;@"));
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
                        xssfCell05.setCellValue(lstData.get(i).d3);

                    }

                    XSSFCell xssfCell06 = xssfRow.getCell(6, Row.CREATE_NULL_AS_BLANK);
                    numberStyle = xssfCell06.getCellStyle();
                    //numberStyle.setDataFormat(format.getFormat("#,##0;-#,##0;;@"));
                    numberStyle.setAlignment(HorizontalAlignment.RIGHT);
                    numberStyle.setLocked(true);
                    xssfCell06.setCellStyle(numberStyle);
                    xssfCell06.setCellValue(lstData.get(i).d4);
                    XSSFCell xssfCell07 = xssfRow.getCell(7, Row.CREATE_NULL_AS_BLANK);
                    lockStyle = xssfCell07.getCellStyle();
                    //lockStyle.setDataFormat(format.getFormat("#,##0.0;-#,##0.0;-;@"));
                    lockStyle.setAlignment(HorizontalAlignment.RIGHT);
                    lockStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                    lockStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                    lockStyle.setLocked(true);
                    xssfCell07.setCellStyle(lockStyle);

                }

                FormulaEvaluator formulaEvaluator = xssfWorkbook.getCreationHelper().createFormulaEvaluator();
                formulaEvaluator.evaluateAll();
                sheet.protectSheet("khnv2024");
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
                    Logger.getLogger(ExportText2SbvManager.class
                            .getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(ExportText2SbvManager.class
                            .getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(ExportText2SbvManager.class
                            .getName()).log(Level.SEVERE, null, ex);
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

                XSSFWorkbook workbook1 = sheet.getWorkbook();
                XSSFCellStyle boldCenterStyle = workbook1.createCellStyle();

                XSSFFont boldFont = workbook1.createFont();
                boldFont.setBold(true);
                boldFont.setFontName("Times New Roman"); // font Times New Roman
                boldFont.setFontHeightInPoints((short) 12);
                boldCenterStyle.setFont(boldFont);
//                boldCenterStyle.setAlignment(HorizontalAlignment.CENTER);
//                boldCenterStyle.setVerticalAlignment(VerticalAlignment.CENTER);

                String posDesc = "";
                String addCity = "";
                Connection conn = new DaoConnect().getConnect();
                String sql = "SELECT UPPER(POS_DESC) POS_DESC, UPPER(ADD_CITY) ADD_CITY FROM PO850MB WHERE POS_CD = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, posCode);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    posDesc = rs.getString("POS_DESC");
                    addCity = rs.getString("ADD_CITY");
                }

                rs.close();
                ps.close();

                String title1 = "1".equals(capbc) ? posDesc : "2".equals(capbc) ? addCity : "";

                XSSFRow rowTitle1 = sheet.getRow(3);
                if (rowTitle1 == null) {
                    rowTitle1 = sheet.createRow(3);
                }

                XSSFCell cellTitle1 = rowTitle1.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cellTitle1.setCellValue(title1);
                cellTitle1.setCellStyle(boldCenterStyle);

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

                    XSSFWorkbook workbook = sheet.getWorkbook();
                    XSSFCellStyle numberStyletmp = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
                    XSSFCellStyle numberStyletmp1 = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, false, false);

                    // style KHÓA
                    XSSFCellStyle numberLockStyle = workbook.createCellStyle();
                    numberLockStyle.cloneStyleFrom(numberStyletmp);
                    numberLockStyle.setDataFormat(format.getFormat("#,##0.00"));
                    numberLockStyle.setBorderTop(BorderStyle.THIN);
                    numberLockStyle.setBorderBottom(BorderStyle.THIN);
                    numberLockStyle.setBorderLeft(BorderStyle.THIN);
                    numberLockStyle.setBorderRight(BorderStyle.THIN);

                    // style KHÔNG KHÓA
                    XSSFCellStyle numberUnlockStyle = workbook.createCellStyle();
                    numberUnlockStyle.cloneStyleFrom(numberStyletmp1);
                    numberUnlockStyle.setDataFormat(format.getFormat("#,##0.00"));
                    numberUnlockStyle.setBorderTop(BorderStyle.THIN);
                    numberUnlockStyle.setBorderBottom(BorderStyle.THIN);
                    numberUnlockStyle.setBorderLeft(BorderStyle.THIN);
                    numberUnlockStyle.setBorderRight(BorderStyle.THIN);
                    boolean isOldRule = i == 0 || i == 1 || i == 2 || i == 3 || i == 7 || i == 8 || i == 33 || i == 40;

//                    boolean forceCol3Only = i != 13 && i != 16 && i != 27 && i != 28 && i != 6;
                    boolean forceCol3Only = i == 0 && i == 1 && i == 3 && i == 4 && i == 5 && i == 7 && i == 8 && i == 33 && i == 40;
                    // ===== CỘT 3 =====
                    XSSFCell xssfCell03 = xssfRow.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    XSSFCellStyle styleCol3 = (isOldRule || forceCol3Only) ? numberLockStyle : numberUnlockStyle;
                    xssfCell03.setCellStyle(styleCol3);
                    xssfCell03.setCellValue(lstData.get(i).d1);

                    // ===== CỘT 4 =====
                    XSSFCell xssfCell04 = xssfRow.getCell(4, Row.CREATE_NULL_AS_BLANK);
                    XSSFCellStyle cellStyle = isOldRule ? numberLockStyle : numberUnlockStyle;
                    xssfCell04.setCellStyle(cellStyle);
                    xssfCell04.setCellValue(lstData.get(i).d2);

                    // ===== CỘT 5 =====
                    XSSFCell xssfCell05 = xssfRow.getCell(5, Row.CREATE_NULL_AS_BLANK);
                    xssfCell05.setCellStyle(cellStyle);
                    xssfCell05.setCellValue(lstData.get(i).d3);

                    // ===== CỘT 7 (% 2 số thập phân) =====
                    XSSFCell xssfCell07 = xssfRow.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    XSSFDataFormat dataFormat1 = workbook.createDataFormat();
                    XSSFCellStyle percentStyle = workbook.createCellStyle();
                    percentStyle.cloneStyleFrom((isOldRule || forceCol3Only) ? numberLockStyle : numberUnlockStyle);
                    percentStyle.setDataFormat(dataFormat1.getFormat("0.00%"));
                    xssfCell07.setCellStyle(percentStyle);

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

// Phương thức tạo CellStyle    
    private XSSFCellStyle createCellStyle(XSSFWorkbook workbook, boolean isBold, boolean isItalic, short fontSize, String fontName, HorizontalAlignment alignment, short bgColor, boolean isLocked,
            boolean percentageValue, boolean absoluteValue) {
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints(fontSize);
        font.setFontName(fontName);
        font.setBold(isBold);
        font.setItalic(isItalic);
        style.setFont(font);
        style.setAlignment(alignment);

        // Thiết lập màu nền nếu không phải là màu tự động
        if (bgColor != IndexedColors.AUTOMATIC.getIndex()) {
            style.setFillForegroundColor(bgColor);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }

        // Đóng khung các ô
        style.setBorderBottom(BorderStyle.DOTTED);
        style.setBorderTop(BorderStyle.DOTTED);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        // Thiết lập định dạng số
        DataFormat format = workbook.createDataFormat();

        if (percentageValue) {
            style.setDataFormat(format.getFormat("#,##0.0"));
        } else {
            if (absoluteValue) {
                style.setDataFormat(format.getFormat("#,##0;-#,##0;;@"));
            } else {
                style.setDataFormat(format.getFormat("#,##0.00;-#,##0.00;;@"));
            }
        }

        style.setLocked(isLocked); // Khóa hoặc không khóa ô

        if (isLocked) {
            XSSFColor color = new XSSFColor(new java.awt.Color(217, 217, 217));
            ((XSSFCellStyle) style).setFillForegroundColor(color);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }
        style.setWrapText(true);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    boolean inArray(int[] source, int value) {
        boolean found = false;

        for (int number : source) {
            if (number == value) {
                found = true;
                break;
            }
        }

        return found;
    }

    public FileExportInfo xuatExcel_Mau01_2026(String posCode, String commune, String subcommune, String name_subcommune, String reportDate, String namBc, String dotBc, String savedDirPath) throws SQLException {
        String filePath = "", fileName = "";
        List<String> lstOfTextFile = new ArrayList<>();
        List<DownloadFileInfor> filesList = new ArrayList<>();
        List<String> zipFileList = new ArrayList<>();
        ArrayList<String> fullPathList = new ArrayList<>();

        XDKHDao2021 daoXdkh = new XDKHDao2021();
        List<POSModel> subCommuneList = daoXdkh.getSubCommuneList(posCode, commune);
        List<String> lstTitleData = daoXdkh.getTitleData(posCode);

        String strTimeFile = Long.toString(System.currentTimeMillis());
        String zipFile = "FileNen_KHNV01_" + strTimeFile + ".zip";
        String zipPath = "";

        try {
            // 1. Khởi tạo đường dẫn và sao chép file template
            String templateFile = savedDirPath + Define.M_EXCEL_TEMP + "/KHNV/KHNV_01C_2026.xlsx";
            fileName = buildFileName(savedDirPath, commune, reportDate, strTimeFile);
            filePath = fileName.substring(fileName.lastIndexOf("/") + 1); // Lấy tên file gốc

            FileUtil.copyFile(new File(templateFile), new File(fileName));

            // 2. Lấy dữ liệu và đổ vào file Excel
            DaoMau02 daoMau02 = new DaoMau02();
            List<DULIEU_NT_100> lstData = daoMau02.getExportData_2026(commune, "", "", reportDate);

            if (lstData.size() > 0) {
                // Gọi hàm xử lý ghi dữ liệu vào Excel
                processExcelData(fileName, lstData, name_subcommune, namBc, lstTitleData);

                // Cập nhật danh sách quản lý file
                lstOfTextFile.add(fileName);
                FileInfo file = new FileInfo(new File(fileName));
                filesList.add(new DownloadFileInfor(file.getName(), fileName, DefineFun.round_up((double) file.getSize() / 1000) + " KB"));
                fullPathList.add(file.getAbsolutePath());
                zipPath = Define.M_ROOT + Define.M_REPORT_XLS + zipFile;
            }

            // 3. Xử lý nén file nếu có nhiều hơn 1 file
            if (fullPathList.size() > 1) {
                handleZipFile(fullPathList, zipPath, zipFile, zipFileList);
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

    public FileExportInfo xuatExcel_Mau02_2026(POSModel pos, String posFlag, String reportDate, String namBc, String dotBc, String savedDirPath, String commune, String subcommune) throws SQLException {
        String filePath = "";
        String fileName = "";

        List<String> lstOfTextFile = new ArrayList<>();
        List<DownloadFileInfor> filesList = new ArrayList<>();
        ArrayList<String> fullPathList = new ArrayList<>();

        XDKHDao2021 daoXdkh = new XDKHDao2021();
        List<String> lstTitleData = daoXdkh.getTitleData(subcommune);

        String strTimeFile = Long.toString(System.currentTimeMillis());
        String posCode = pos.getId();

        try {
            Date dReportDate = (new SimpleDateFormat("dd-MMM-yyyy")).parse(reportDate);
            String strCurrDate = (new SimpleDateFormat("ddMMyyyy")).format(dReportDate);

            DaoMau02 daoMau02 = new DaoMau02();
            List<DULIEU_NT_100> lstData = daoMau02.getExportData2_2026(posCode, posFlag, "", reportDate, commune, subcommune);

            if (lstData == null || lstData.isEmpty()) {
                return null;
            }

            String templateFile = savedDirPath + "EXCEL_TEMPLATE/KHNV/KHNV_02C_2026.xlsx";
            String strFileSave = "KHNV2_XA_" + commune + "_S_" + strCurrDate + "_" + strTimeFile.substring(strTimeFile.length() - 4) + ".XLSX";
            String strPathSave = savedDirPath + "EXPORT_REPORT/XLS/";

            filePath = strFileSave;
            fileName = strPathSave + strFileSave;

            File source = new File(templateFile);
            File dest = new File(fileName);
            FileUtil.copyFile(source, dest);

            // Sử dụng try-with-resources tối ưu tài nguyên
            try (java.io.FileInputStream fis = new java.io.FileInputStream(fileName);
                    XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fis)) {

                XSSFSheet sheet = xssfWorkbook.getSheetAt(0);

                // 1. Điền thông tin tiêu đề báo cáo
                fillReportTitles(sheet, lstTitleData, pos, namBc, "2");

                // 2. Điền và cấu hình Style cho toàn bộ Cell dữ liệu
                fillReportData(sheet, lstData, "2");

                // 3. Dọn dẹp hàng thừa và xử lý format dòng cuối
                cleanAndFormatFooter(sheet, lstData.size());

                // 4. Tạo khu vực chữ ký phía dưới
                createSignatures(sheet, lstTitleData, lstData.size(), "2", posFlag);

                // Tính toán lại công thức và bảo mật sheet
                FormulaEvaluator formulaEvaluator = xssfWorkbook.getCreationHelper().createFormulaEvaluator();
                formulaEvaluator.evaluateAll();
                sheet.protectSheet("khnv20246");

                // Ghi dữ liệu ra file thực tế
                try (java.io.FileOutputStream out = new java.io.FileOutputStream(fileName)) {
                    xssfWorkbook.write(out);
                }

                lstOfTextFile.add(fileName);
                FileInfo file = new FileInfo(new File(fileName));
                filesList.add(new DownloadFileInfor(file.getName(), fileName, DefineFun.round_up(Double.valueOf(file.getSize() / 1000.0D)) + " KB"));
                fullPathList.add(file.getAbsolutePath());
            }

            return new FileExportInfo(fileName, filePath);

        } catch (Exception ex) {
            CoreLogger.error(getClass().getName() + " xuatExcelMau02_2026 " + ex.getMessage());
            System.err.println(getClass().getName() + " loi xuatExcelMau02_2026 " + ex.getMessage());
            return null;
        }
    }

    public FileExportInfo xuatExcel_Mau03_2026(POSModel pos, String posFlag, String reportDate, String namBc, String dotBc, String savedDirPath) throws SQLException {
        List<String> lstOfTextFile = new ArrayList<>();
        List<DownloadFileInfor> filesList = new ArrayList<>();
        List<String> zipFileList = new ArrayList<>();
        ArrayList<String> fullPathList = new ArrayList<>();

        String strTimeFile = Long.toString(System.currentTimeMillis());
        String zipFile = "FileNen_KHNV03_" + strTimeFile + ".zip";
        String zipPath = "";
        String posCode = pos.getId();

        XDKHDao2021 daoXdkh = new XDKHDao2021();
        List<String> lstTitleData = daoXdkh.getTitleData(posCode);

        try {
            Date dReportDate = (new SimpleDateFormat("dd-MMM-yyyy")).parse(reportDate);
            String strCurrDate = (new SimpleDateFormat("ddMMyyyy")).format(dReportDate);
            DaoMau02 daoMau02 = new DaoMau02();

            // 1. Xác định đường dẫn file template và file save
            String templateFile = savedDirPath + "EXCEL_TEMPLATE/KHNV/KHNV_03C_2026.xlsx";
            String strFileSave = "KHNV3_PGD_" + posCode + "_S_" + strCurrDate + "_" + strTimeFile.substring(strTimeFile.length() - 4) + ".XLSX";
            String strPathSave = savedDirPath + "EXPORT_REPORT/XLS/";
            String fileName = strPathSave + strFileSave;
            String filePath = strFileSave;

            // 2. Sao chép file từ template
            FileUtil.copyFile(new File(templateFile), new File(fileName));

            // 3. Lấy dữ liệu từ DB
            List<DULIEU_NT_100> lstData = daoMau02.getExportData3_2026(posCode, posFlag, "", reportDate, "", "");

            if (lstData != null && !lstData.isEmpty()) {
                try (FileInputStream fis = new FileInputStream(fileName);
                        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fis)) {

                    XSSFSheet sheet = xssfWorkbook.getSheetAt(0);

                    // 4. Điền tiêu đề báo cáo
                    fillReportTitles(sheet, lstTitleData, pos, namBc, "1");

                    // 5. Điền dữ liệu vào bảng
                    fillReportData(sheet, lstData, "1");

                    // 6. Xóa các dòng thừa phía dưới và định dạng lại dòng cuối
                    cleanAndFormatFooter(sheet, lstData.size());

                    // 7. Tạo phần ký tên (Chữ ký cuối bài)
                    createSignatures(sheet, lstTitleData, lstData.size(), "1", posFlag);

                    // 8. Tính toán lại công thức và bảo mật sheet
                    FormulaEvaluator formulaEvaluator = xssfWorkbook.getCreationHelper().createFormulaEvaluator();
                    formulaEvaluator.evaluateAll();
                    sheet.protectSheet("khnv20246");

                    // 9. Ghi file ra ổ đĩa
                    try (FileOutputStream out = new FileOutputStream(fileName)) {
                        xssfWorkbook.write(out);
                    }

                    // Tích hợp thông tin file phục vụ download
                    lstOfTextFile.add(fileName);
                    FileInfo file = new FileInfo(new File(fileName));
                    filesList.add(new DownloadFileInfor(file.getName(), fileName,
                            DefineFun.round_up(Double.valueOf(file.getSize() / 1000.0D)) + " KB"));
                    fullPathList.add(file.getAbsolutePath());
                    zipPath = Define.M_ROOT + "EXPORT_REPORT/XLS/" + zipFile;
                }
            }

            // 10. Xử lý nén ZIP nếu có nhiều file
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
            CoreLogger.error(getClass().getName() + " xuatExcelMau02_2024 " + ex.getMessage());
            System.err.println(getClass().getName() + " loi xuatExcelMau02_2024 " + ex.getMessage());
            return null;
        }
    }

    /**
     * Hàm điền toàn bộ thông tin tiêu đề động dựa vào năm báo cáo
     */
    private void fillReportTitles(XSSFSheet sheet, List<String> lstTitleData, POSModel pos, String namBc, String type) {
        if (type.equals("1")) {
            String strTitle1 = "NHCSXH " + lstTitleData.get(1).toUpperCase();
            String strTitle2 = lstTitleData.get(0).toUpperCase();
            fillTitle(sheet.getRow(2).getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK), strTitle1);
            fillTitle(sheet.getRow(3).getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK), strTitle2);

            String strTitle = "KẾ HOẠCH TÍN DỤNG GIAI ĐOẠN " + namBc + " - " + (Integer.parseInt(namBc) + 3);
            fillTitle(sheet.getRow(5).getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK), strTitle);

            String strPosTitle = pos.getDesc().toUpperCase();
            fillTitle(sheet.getRow(2).getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK), strPosTitle);
        } else if (type.equals("2")) {
            fillTitle(sheet.getRow(2).getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK), "NHCSXH " + lstTitleData.get(1).toUpperCase());
            fillTitle(sheet.getRow(3).getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK), lstTitleData.get(0).toUpperCase());
            fillTitle(sheet.getRow(5).getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK), "KẾ HOẠCH TÍN DỤNG GIAI ĐOẠN " + namBc + " - " + (Integer.parseInt(namBc) + 3));
            fillTitle(sheet.getRow(2).getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK), pos.getDesc().toUpperCase());
        }
        fillTitle(sheet.getRow(7).getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK), "Thực hiện đến 31/12/" + (Integer.parseInt(namBc) - 2));
        fillTitle(sheet.getRow(7).getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK), "Ước thực hiện đến 31/12/" + (Integer.parseInt(namBc) - 1));
        fillTitle(sheet.getRow(7).getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK), "Kế hoạch tín dụng năm " + namBc);
        fillTitle(sheet.getRow(8).getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK), "Tăng, giảm so với 31/12/" + (Integer.parseInt(namBc) - 1));
        fillTitle(sheet.getRow(8).getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK), "Tăng, giảm so với 31/12/" + namBc);
        fillTitle(sheet.getRow(7).getCell(11, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK), "Kế hoạch tín dụng năm " + (Integer.parseInt(namBc) + 2));
        fillTitle(sheet.getRow(8).getCell(12, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK), "Tăng, giảm so với 31/12/" + (Integer.parseInt(namBc) + 1));
        fillTitle(sheet.getRow(7).getCell(14, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK), "Kế hoạch tín dụng năm " + (Integer.parseInt(namBc) + 3));
        fillTitle(sheet.getRow(8).getCell(15, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK), "Tăng, giảm so với 31/12/" + (Integer.parseInt(namBc) + 2));
        fillTitle(sheet.getRow(7).getCell(17, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK), "Kế hoạch tín dụng năm " + (Integer.parseInt(namBc) + 4));
    }

    /**
     * Hàm duyệt danh sách đổ dữ liệu và gán CellStyle tương ứng cho từng ô
     */
    private void fillReportData(XSSFSheet sheet, List<DULIEU_NT_100> lstData, String type) {
        XSSFWorkbook workbook = (XSSFWorkbook) sheet.getWorkbook();

        // Tạo các Style mẫu
        XSSFCellStyle boldStyle = createCellStyle(workbook, true, false, (short) 12, "Times New Roman", HorizontalAlignment.CENTER, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
        XSSFCellStyle leftStyle = createCellStyle(workbook, true, false, (short) 12, "Times New Roman", HorizontalAlignment.LEFT, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
        XSSFCellStyle codeStyleSTT = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.CENTER, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
        XSSFCellStyle codeStyle = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.LEFT, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
        XSSFCellStyle italicsStyle = createCellStyle(workbook, false, true, (short) 12, "Times New Roman", HorizontalAlignment.LEFT, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
        XSSFCellStyle italicsStyleSTT = createCellStyle(workbook, false, true, (short) 12, "Times New Roman", HorizontalAlignment.CENTER, IndexedColors.AUTOMATIC.getIndex(), true, false, false);

        XSSFCellStyle numberStyle = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, false, true);
        XSSFCellStyle numberStylep = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, true, true);
        XSSFCellStyle numberStylea = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, false, true);

        XSSFCellStyle numberStyle1 = createCellStyle(workbook, true, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, false, true);
        XSSFCellStyle numberStyle1p = createCellStyle(workbook, true, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, false, false);
        XSSFCellStyle numberStyle1a = createCellStyle(workbook, true, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, false, true);

        XSSFCellStyle numberStyle2 = createCellStyle(workbook, false, true, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, false, true);
        XSSFCellStyle numberStyle2p = createCellStyle(workbook, false, true, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, false, true);
        XSSFCellStyle numberStyle2a = createCellStyle(workbook, false, true, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, false, true);

        XSSFCellStyle numberStyle00 = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, false, true);
        XSSFCellStyle numberStyle00p = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
        XSSFCellStyle numberStyle00a = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, false, true);

        XSSFCellStyle numberStyle11 = createCellStyle(workbook, true, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, false, true);
        XSSFCellStyle numberStyle11p = createCellStyle(workbook, true, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
        XSSFCellStyle numberStyle11a = createCellStyle(workbook, true, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, false, true);

        XSSFCellStyle numberStyle22 = createCellStyle(workbook, false, true, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, false, true);
        XSSFCellStyle numberStyle22p = createCellStyle(workbook, false, true, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
        XSSFCellStyle numberStyle22a = createCellStyle(workbook, false, true, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, false, true);
        int[] _arrIncludeCol;
        int[] _arrExcludeCol;
        int[] _arrLockRow;
        int[] _arrPercentCol;
        int[] _arrAbsoluteCol;
        if (null == type) {
            _arrIncludeCol = new int[]{0};
            _arrExcludeCol = new int[]{0};
            _arrLockRow = new int[]{0};
            _arrPercentCol = new int[]{0};
            _arrAbsoluteCol = new int[]{0};
        } else {
            switch (type) {
                case "1":
                    _arrIncludeCol = new int[]{3, 4};
                    _arrExcludeCol = new int[]{6, 7, 9, 10, 12, 13, 15, 16, 17, 18};
                    _arrLockRow = new int[]{0, 1, 2, 3, 7, 8, 32, 48};
                    _arrPercentCol = new int[]{7, 10, 13, 16, 18};
                    _arrAbsoluteCol = new int[]{0};
                    break;
                case "2":
                    _arrIncludeCol = new int[]{3, 4};
                    _arrExcludeCol = new int[]{6, 7, 9, 10, 12, 13, 15, 16, 17, 18};
                    _arrLockRow = new int[]{0, 1, 3, 4, 28, 43};
                    _arrPercentCol = new int[]{7, 10, 13, 16, 18};
                    _arrAbsoluteCol = new int[]{0};
                    break;
                default:
                    _arrIncludeCol = new int[]{0};
                    _arrExcludeCol = new int[]{0};
                    _arrLockRow = new int[]{0};
                    _arrPercentCol = new int[]{0};
                    _arrAbsoluteCol = new int[]{0};
                    break;
            }
        }
        for (int i = 0; i < lstData.size(); i++) {
            XSSFRow xssfRow = sheet.getRow(i + 12);
            if (xssfRow == null) {
                xssfRow = sheet.createRow(i + 12);
            }
            DULIEU_NT_100 item = lstData.get(i);

            XSSFCell xssfCell00 = xssfRow.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            XSSFCell xssfCell02 = xssfRow.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            XSSFCell xssfCell01 = xssfRow.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

            // Định dạng cột Text (STT, Mã, Tên) theo KieuIn
            switch (item.getKIEUIN()) {
                case 1:
                    xssfCell00.setCellStyle(boldStyle);
                    xssfCell02.setCellStyle(codeStyleSTT);
                    xssfCell01.setCellStyle(leftStyle);
                    break;
                case 3:
                    xssfCell00.setCellStyle(italicsStyleSTT);
                    xssfCell02.setCellStyle(codeStyleSTT);
                    xssfCell01.setCellStyle(italicsStyle);
                    break;
                default:
                    xssfCell00.setCellStyle(codeStyleSTT);
                    xssfCell02.setCellStyle(codeStyleSTT);
                    xssfCell01.setCellStyle(codeStyle);
                    break;
            }

            xssfCell00.setCellValue(item.getD17());
            xssfCell02.setCellValue(item.getMA());
            xssfCell01.setCellValue(item.getTEN());

            // Đổ các cột số từ cột 3 đến cột 18
            for (int ii = 3; ii < 19; ii++) {
                XSSFCell xssfCell = xssfRow.getCell(ii, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                boolean isExcludedOrLocked = inArray(_arrExcludeCol, ii) || inArray(_arrLockRow, i)
                        || (i == 28 && i == 43 && !inArray(_arrIncludeCol, ii));

                switch (item.getKIEUIN()) {
                    case 1:
                        if (isExcludedOrLocked) {
                            if (inArray(_arrPercentCol, ii)) {
                                xssfCell.setCellStyle(numberStyle11p);
                            } else if (inArray(_arrAbsoluteCol, ii)) {
                                xssfCell.setCellStyle(numberStyle11a);
                            } else {
                                xssfCell.setCellStyle(numberStyle11);
                            }
                        } else {
                            if (inArray(_arrPercentCol, ii)) {
                                xssfCell.setCellStyle(numberStyle1p);
                            } else if (inArray(_arrAbsoluteCol, ii)) {
                                xssfCell.setCellStyle(numberStyle1a);
                            } else {
                                xssfCell.setCellStyle(numberStyle1);
                            }
                        }
                        break;
                    case 2:
                        if (isExcludedOrLocked) {
                            if (inArray(_arrPercentCol, ii)) {
                                xssfCell.setCellStyle(numberStyle22p);
                            } else if (inArray(_arrAbsoluteCol, ii)) {
                                xssfCell.setCellStyle(numberStyle22a);
                            } else {
                                xssfCell.setCellStyle(numberStyle22);
                            }
                        } else {
                            if (inArray(_arrPercentCol, ii)) {
                                xssfCell.setCellStyle(numberStyle2p);
                            } else if (inArray(_arrAbsoluteCol, ii)) {
                                xssfCell.setCellStyle(numberStyle2a);
                            } else {
                                xssfCell.setCellStyle(numberStyle2);
                            }
                        }
                        break;
                    default:
                        if (isExcludedOrLocked) {
                            if (inArray(_arrPercentCol, ii)) {
                                xssfCell.setCellStyle(numberStyle00p);
                            } else if (inArray(_arrAbsoluteCol, ii)) {
                                xssfCell.setCellStyle(numberStyle00a);
                            } else {
                                xssfCell.setCellStyle(numberStyle00);
                            }
                        } else {
                            if (inArray(_arrPercentCol, ii)) {
                                xssfCell.setCellStyle(numberStylep);
                            } else if (inArray(_arrAbsoluteCol, ii)) {
                                xssfCell.setCellStyle(numberStylea);
                            } else {
                                xssfCell.setCellStyle(numberStyle);
                            }
                        }
                        break;
                }

                // Dùng Reflection lấy dữ liệu từ D1 -> D15 tương ứng cột số
                try {
                    int dIndex = ii - 2;
                    if (dIndex > 15) {
                        break;
                    }
                    Method method = item.getClass().getMethod("getD" + dIndex);
                    Object value = method.invoke(item);
                    if (value != null) {
                        try {
                            xssfCell.setCellValue(new BigDecimal(value.toString()).doubleValue());
                        } catch (Exception e) {
                            xssfCell.setCellValue(value.toString());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Hàm xóa các dòng thừa từ template dưới vùng dữ liệu động và đóng khung
     * dòng cuối
     */
    private void cleanAndFormatFooter(XSSFSheet sheet, int dataSize) {
        Workbook workbook = sheet.getWorkbook();
        CellStyle defaultStyle = workbook.createCellStyle();
        int startRow = dataSize + 12;

        // Xóa các merged region thừa phía dưới vùng dữ liệu
        for (int i = sheet.getNumMergedRegions() - 1; i >= 0; i--) {
            CellRangeAddress mergedRegion = sheet.getMergedRegion(i);
            if (mergedRegion.getFirstRow() >= startRow) {
                sheet.removeMergedRegion(i);
            }
        }

        // Xóa trắng dữ liệu thừa của các dòng phía dưới
        for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            if (row != null) {
                for (Cell cell : row) {
                    cell.getCellStyle().setWrapText(false);
                    cell.setCellStyle(defaultStyle);
                }
                sheet.removeRow(row);
            }
        }

        if (startRow <= sheet.getLastRowNum()) {
            sheet.shiftRows(startRow + 1, sheet.getLastRowNum(), startRow - sheet.getLastRowNum() - 1);
        }

        // Tạo đường viền (Border Top Thin) đóng khung dưới cùng bảng dữ liệu
        int lastRowNum = sheet.getLastRowNum();
        XSSFRow lastRow = sheet.getRow(lastRowNum);
        if (lastRow == null) {
            lastRow = sheet.createRow(lastRowNum);
        }
        XSSFRow borderRow = sheet.createRow(lastRowNum + 1);
        XSSFCellStyle borderStyle = sheet.getWorkbook().createCellStyle();
        borderStyle.setBorderTop(BorderStyle.THIN);

        for (int i = 0; i < lastRow.getLastCellNum(); i++) {
            if (borderRow.getCell(i) == null) {
                borderRow.createCell(i);
            }
            borderRow.getCell(i).setCellStyle(borderStyle);
        }
    }

    /**
     * Hàm tạo thông tin liên lưu, ngày tháng năm và khu vực chữ ký giám
     * đốc/trưởng ban
     */
    private void createSignatures(XSSFSheet sheet, List<String> lstTitleData, int dataSize, String type, String posFlag) {

        XSSFWorkbook xssfWorkbook = sheet.getWorkbook();
        int startRowNum = dataSize + 13;

        XSSFRow row5 = sheet.createRow(startRowNum);
        XSSFRow row1 = sheet.createRow(startRowNum + 1);
        XSSFRow row2 = sheet.createRow(startRowNum + 2);
        XSSFRow row3 = sheet.createRow(startRowNum + 3);
        XSSFRow row4 = sheet.createRow(startRowNum + 4);

        XSSFCell noteCell = row5.createCell(0);
        XSSFCell dateCell = row1.createCell(15);
        XSSFCell leftTitleCell = row2.createCell(2);
        XSSFCell leftSignCell = row3.createCell(2);
        XSSFCell rightTitleCell = row2.createCell(15);
        XSSFCell rightSignCell = row3.createCell(15);
        XSSFCell leftFooterCell = row4.createCell(2);
        XSSFCell rightFooterCell = row4.createCell(15);

        if ("1".equals(type)) {

            noteCell.setCellValue("Mẫu biểu được lập 02 liên, 01 liên lưu, 01 liên gửi NHCSXH cấp trên.");

            rightTitleCell.setCellValue("TM. BĐD HĐQT NHCSXH " + lstTitleData.get(1).toUpperCase());

        } else if ("2".equals(type)) {

            noteCell.setCellValue("Mẫu biểu được lập 02 liên, 01 liên lưu tại Phòng giao dịch NHCSXH, 01 liên gửi chi nhánh NHCSXH cấp tỉnh.");

            rightTitleCell.setCellValue("TM. BAN ĐẠI DIỆN HĐQT NHCSXH " + posFlag.toUpperCase());
        }

        dateCell.setCellValue("…, ngày … tháng … năm ……");

        leftTitleCell.setCellValue(
                lstTitleData.get(0).toUpperCase());

        leftSignCell.setCellValue("GIÁM ĐỐC");

        rightSignCell.setCellValue("TRƯỞNG BAN");

        leftFooterCell.setCellValue("(Ký tên)");

        rightFooterCell.setCellValue("(Ký tên, đóng dấu)");

        // Style chữ ký đậm
        XSSFCellStyle signatureStyle
                = xssfWorkbook.createCellStyle();

        XSSFFont signatureBoldFont
                = xssfWorkbook.createFont();

        signatureBoldFont.setBold(true);
        signatureBoldFont.setFontHeightInPoints((short) 12);
        signatureBoldFont.setFontName("Times New Roman");

        signatureStyle.setFont(signatureBoldFont);
        signatureStyle.setAlignment(HorizontalAlignment.CENTER);

        leftTitleCell.setCellStyle(signatureStyle);
        leftSignCell.setCellStyle(signatureStyle);
        rightTitleCell.setCellStyle(signatureStyle);
        rightSignCell.setCellStyle(signatureStyle);

        // Style nghiêng
        XSSFCellStyle italicStyle
                = xssfWorkbook.createCellStyle();

        XSSFFont italicFont
                = xssfWorkbook.createFont();

        italicFont.setItalic(true);
        italicFont.setFontHeightInPoints((short) 12);
        italicFont.setFontName("Times New Roman");

        italicStyle.setFont(italicFont);
        italicStyle.setAlignment(HorizontalAlignment.CENTER);

        dateCell.setCellStyle(italicStyle);
        leftFooterCell.setCellStyle(italicStyle);
        rightFooterCell.setCellStyle(italicStyle);

        // Style ghi chú
        XSSFCellStyle italicLeftStyle
                = xssfWorkbook.createCellStyle();

        italicLeftStyle.cloneStyleFrom(italicStyle);
        italicLeftStyle.setAlignment(HorizontalAlignment.LEFT);

        noteCell.setCellStyle(italicLeftStyle);
    }

    public String buildFileName(String savedDirPath, String commune, String reportDate, String strTimeFile) throws Exception {
        Date dReportDate = new SimpleDateFormat("dd-MMM-yyyy").parse(reportDate);
        String strCurrDate = new SimpleDateFormat("ddMMyyyy").format(dReportDate);

        String strFileSave = "KHNV_01_THON_" + commune
                + "_" + strCurrDate
                + "_" + strTimeFile.substring(strTimeFile.length() - 4);

        return savedDirPath + Define.M_REPORT_XLS + strFileSave + ".XLSX";
    }

    public void processExcelData(String fileName, List<DULIEU_NT_100> lstData, String name_subcommune, String namBc, List<String> lstTitleData) throws Exception {
        try (java.io.FileInputStream fis = new java.io.FileInputStream(fileName);
                XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);

            // Fill tiêu đề báo cáo
            fillReportTitles(sheet, name_subcommune, namBc, lstTitleData);

            // Khởi tạo các Styles
            XSSFWorkbook workbook = sheet.getWorkbook();
            XSSFCellStyle boldStyle = createCellStyle(workbook, true, false, (short) 12, "Times New Roman", HorizontalAlignment.CENTER, IndexedColors.AUTOMATIC.getIndex(), true, false, false);
            XSSFCellStyle codeStyle = createCellStyle(workbook, false, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), false, false, true);
            XSSFCellStyle numberStyle = createCellStyle(workbook, true, false, (short) 12, "Times New Roman", HorizontalAlignment.RIGHT, IndexedColors.AUTOMATIC.getIndex(), true, false, true);

            // Đổ dữ liệu các dòng
            for (int i = 0; i < lstData.size(); i++) {
                DULIEU_NT_100 dataItem = lstData.get(i);
                XSSFRow xssfRow = sheet.getRow(i + 12);
                if (xssfRow == null) {
                    xssfRow = sheet.createRow(i + 12);
                }

                // Điền dữ liệu động từ getD1 đến getD19 qua Reflection
                fillDynamicColumns(xssfRow, dataItem, numberStyle, codeStyle);

                // Điền dữ liệu các cột cố định
                fillFixedColumns(xssfRow, dataItem, boldStyle, numberStyle);
            }

            // Xóa các dòng thừa của template cũ
            for (int i = lstData.size(); i < sheet.getLastRowNum() + 1; i++) {
                XSSFRow rowToDelete = sheet.getRow(i + 12);
                if (rowToDelete != null) {
                    sheet.removeRow(rowToDelete);
                }
            }

            // Tạo khung viền cuối dòng và chữ ký
            generateFooterAndSignatures(sheet, xssfWorkbook, lstData.size());

            // Refresh công thức và bảo mật sheet
            FormulaEvaluator formulaEvaluator = xssfWorkbook.getCreationHelper().createFormulaEvaluator();
            formulaEvaluator.evaluateAll();
            sheet.protectSheet("khnv2024");

            // Ghi dữ liệu ngược lại file
            try (java.io.FileOutputStream out = new java.io.FileOutputStream(fileName)) {
                xssfWorkbook.write(out);
            }
        }
    }

    public void fillReportTitles(XSSFSheet sheet, String name_subcommune, String namBc, List<String> lstTitleData) {
        String strTitle = "NHU CẦU VAY VỐN TÍN DỤNG CHÍNH SÁCH TẠI " + name_subcommune.toUpperCase();
        String strTitle4 = "GIAI ĐOẠN " + namBc + " - " + (Integer.parseInt(namBc) + 3);

        fillTitle(sheet.getRow(4).getCell(0, Row.CREATE_NULL_AS_BLANK), strTitle);
        fillTitle(sheet.getRow(5).getCell(0, Row.CREATE_NULL_AS_BLANK), strTitle4);

        String strTitle1 = "NHCSXH " + lstTitleData.get(1).toUpperCase();
        String strTitle2 = lstTitleData.get(0).toUpperCase();

        fillTitle(sheet.getRow(1).getCell(0, Row.CREATE_NULL_AS_BLANK), strTitle1);
        fillTitle(sheet.getRow(2).getCell(0, Row.CREATE_NULL_AS_BLANK), strTitle2);
    }

    public void fillDynamicColumns(XSSFRow xssfRow, DULIEU_NT_100 dataItem, XSSFCellStyle numberStyle, XSSFCellStyle codeStyle) {
        for (int ii = 5; ii < 24; ii++) {
            XSSFCell xssfCell = xssfRow.getCell(ii, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

            if (dataItem.getKIEUIN() == 0) {
                xssfCell.setCellStyle(numberStyle);
            } else {
                xssfCell.setCellStyle(codeStyle);
            }

            try {
                Method method = dataItem.getClass().getMethod("getD" + (ii - 4));
                Object value = method.invoke(dataItem);

                if (value != null) {
                    if (value instanceof Number) {
                        xssfCell.setCellValue(((Number) value).doubleValue());
                    } else if (value instanceof String) {
                        String strVal = (String) value;
                        try {
                            double doubleValue = Double.parseDouble(strVal);
                            xssfCell.setCellValue(doubleValue);
                        } catch (NumberFormatException e) {
                            if (strVal.startsWith("=")) {
                                xssfCell.setCellFormula(strVal.substring(1));
                            } else {
                                xssfCell.setCellValue(strVal);
                            }
                        }
                    } else {
                        xssfCell.setCellValue(value.toString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void fillFixedColumns(XSSFRow xssfRow, DULIEU_NT_100 dataItem, XSSFCellStyle boldStyle, XSSFCellStyle numberStyle) {
        XSSFCell xssfCell00 = xssfRow.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        XSSFCell xssfCell01 = xssfRow.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        XSSFCell xssfCell02 = xssfRow.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        XSSFCell xssfCell03 = xssfRow.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        XSSFCell xssfCell04 = xssfRow.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        XSSFCell xssfCell05 = xssfRow.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

        if (dataItem.getKIEUIN() == 0) {
            xssfCell00.setCellStyle(boldStyle);
            xssfCell01.setCellStyle(boldStyle);
            xssfCell02.setCellStyle(numberStyle);
            xssfCell03.setCellStyle(numberStyle);
            xssfCell04.setCellStyle(boldStyle);
            xssfCell05.setCellStyle(boldStyle);
        }

        xssfCell00.setCellValue(dataItem.getTT_HIENTHI());
        xssfCell01.setCellValue(dataItem.getTEN());
        xssfCell04.setCellValue(dataItem.getMA());
        xssfCell05.setCellValue(dataItem.getD100());
    }

    public void generateFooterAndSignatures(XSSFSheet sheet, XSSFWorkbook xssfWorkbook, int dataSize) {
        int lastRowNum = sheet.getLastRowNum();
        XSSFRow lastRow = sheet.getRow(lastRowNum);
        if (lastRow == null) {
            lastRow = sheet.createRow(lastRowNum);
        }

        // Đóng khung dòng cuối
        XSSFRow borderRow = sheet.createRow(lastRowNum + 1);
        XSSFCellStyle borderStyle = sheet.getWorkbook().createCellStyle();
        borderStyle.setBorderTop(BorderStyle.THIN);
        for (int i = 0; i < lastRow.getLastCellNum(); i++) {
            if (borderRow.getCell(i) == null) {
                borderRow.createCell(i);
            }
            borderRow.getCell(i).setCellStyle(borderStyle);
        }

        // Thêm nội dung phần cuối (Chữ ký)
        int startRowNum = dataSize + 13;
        XSSFRow row1 = sheet.createRow(startRowNum);
        XSSFRow row2 = sheet.createRow(startRowNum + 1);

        XSSFCell cell1_1 = row1.createCell(20);
        cell1_1.setCellValue("Cán bộ tín dụng");
        XSSFCell cell2_1 = row2.createCell(20);
        cell2_1.setCellValue("(Ký, ghi rõ họ và tên)");

        // Style Chữ ký đậm
        XSSFCellStyle signatureStyle = xssfWorkbook.createCellStyle();
        XSSFFont signatureBoldFont = xssfWorkbook.createFont();
        signatureBoldFont.setBold(true);
        signatureBoldFont.setFontHeightInPoints((short) 12);
        signatureBoldFont.setFontName("Times New Roman");
        signatureStyle.setFont(signatureBoldFont);
        signatureStyle.setAlignment(HorizontalAlignment.CENTER);
        cell1_1.setCellStyle(signatureStyle);

        // Style Chữ ký nghiêng
        XSSFCellStyle italicStyle = xssfWorkbook.createCellStyle();
        XSSFFont italicFont = xssfWorkbook.createFont();
        italicFont.setItalic(true);
        italicFont.setFontHeightInPoints((short) 12);
        italicFont.setFontName("Times New Roman");
        italicStyle.setFont(italicFont);
        italicStyle.setAlignment(HorizontalAlignment.CENTER);
        cell2_1.setCellStyle(italicStyle);
    }

    public void handleZipFile(List<String> fullPathList, String zipPath, String zipFile, List<String> zipFileList) {
        try {
            FileZip.ZipFileFromArray(new ArrayList<>(fullPathList), zipPath);
            zipFileList.add(zipFile);
            zipFileList.add(zipPath);
        } catch (Exception ex) {
            Logger.getLogger(ExportText2SbvManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
