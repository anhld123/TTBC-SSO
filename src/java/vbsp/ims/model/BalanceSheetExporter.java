/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.io.BalanceExcelExport;
import vbsp.ims.jasper.ExportJasperReport;
import vbsp.ims.loadparams.LoadReportParams;

/**
 *
 * @author Trung
 */
public class BalanceSheetExporter {

    final String OLD_FORMAT = "dd-MMM-yyyy";
    final String NEW_FORMAT = "ddMMyyyy";

    private String reportDate;
    private List<String> posCodeList;
    private String consolidateFlag;
    private String termFlag;
    private String accountType;
    private String rootPath;
    private String printType;
    private String printSize;
    private String posOfUser;
    private List<Account> accountList;
    private ResultSet resultset;
    ExportJasperReport exportReport = new ExportJasperReport();
    LoadReportParams objLRP = new LoadReportParams();

    public BalanceSheetExporter() {
    }

    public BalanceSheetExporter(String rootPath, String reportDate,
            String posCodeList, String consolidateFlag,
            String accountType, String termFlag, String printType,String printSize,
            String posOfUser, ResultSet rs) {
        this.reportDate = reportDate;
        this.posCodeList = DefineFun.string2Array(posCodeList, ",", 1);
        this.consolidateFlag = consolidateFlag;
        this.accountType = accountType;
        this.termFlag = termFlag;
        this.rootPath = rootPath;
        this.printType = printType;
        this.printSize = printSize;
        this.posOfUser = posOfUser;
        this.resultset = rs;
    }

    public BalanceSheetExporter(String rootPath, String reportDate,
            String posCodeList, String consolidateFlag,
            String accountType, String termFlag, String printType,String printSize,
            String posOfUser, List<Account> accountList) {
        this.reportDate = reportDate;
        this.posCodeList = DefineFun.string2Array(posCodeList, ",", 1);
        this.consolidateFlag = consolidateFlag;
        this.accountType = accountType;
        this.termFlag = termFlag;
        this.rootPath = rootPath;
        this.printType = printType;
        this.printSize = printSize;
        this.posOfUser = posOfUser;
        this.accountList = accountList;
    }

    public List<String> generate() {
        try {
            ArrayList<String> filePathList = new ArrayList<>();
            String lcReportId;
            lcReportId = DefineFun.getBalanceSheetReportId(accountType);
            HashMap<String, String> hmNameReport_Jasper = objLRP.getNameReport_Jasper(lcReportId);
            String lcReportName = hmNameReport_Jasper.get("NAME_FILE");
            String lcJasperFileName = hmNameReport_Jasper.get("NAME_JASPER");
            if (printSize.equals("02")){                
                lcJasperFileName = lcJasperFileName + "_A3";
            }
            String lcTongHop;
            if (consolidateFlag.equals("Y")) {
                lcTongHop = "_TONGHOP";
            } else {
                lcTongHop = "";
            }
            String ltermFlag;
            ltermFlag = termFlag.substring(2, 3);
            System.err.println("generate -->" + lcReportId + "-" + reportDate
                    + "-" + consolidateFlag + "-" + ltermFlag
                    + "-" + accountType);
            HashMap<String, Object> paramHashMap = new HashMap<>();
            //System.err.println(getMergeList2String().trim());
            String listOfPos = getMergeList2String().trim();
            paramHashMap.put("PARA_MAPGD", listOfPos);
            paramHashMap.put("PARA_NGAYBC", reportDate.trim());
            paramHashMap.put("PARA_KYBC", ltermFlag);
            paramHashMap.put("PARA_TONGHOP", consolidateFlag);
            paramHashMap.put("PARA_LOAIBC", accountType.trim());
            //paramHashMap.put(JRParameter.REPORT_LOCALE, Locale.GERMANY);
            //xu ly cho export file ra PDF hoac la Excel
            String lcCurrentDateStr
                    = DefineFun.convertStrDateFormat(reportDate, OLD_FORMAT, NEW_FORMAT);
            String lcSourceJasperFile = this.rootPath + Define.M_REPORT
                    + lcJasperFileName + ".jrxml";
            //kiem tra file xem da co chua neu chua co thi return
            File checkFile = new File(lcSourceJasperFile);
            if (!checkFile.exists()) {
                System.out.println("Lỗi file mẫu báo cáo jasper không có");
            }   //Ten file tao ra se luu lai de nguoi su dung download (chi ten file chua co duong dan)
            String lcTimeStr = Long.toString(System.currentTimeMillis());
            String lcSaveReportFile = "";
            if (this.posCodeList.size() > 1)
             lcSaveReportFile = this.posOfUser.trim() + "_"
                    + lcReportName + lcTongHop + "_" + lcCurrentDateStr
                    + "_" + lcTimeStr.substring(lcTimeStr.length() - 4, lcTimeStr.length());
            else lcSaveReportFile = posCodeList.get(0).trim() + "_"
                    + lcReportName + lcTongHop + "_" + lcCurrentDateStr
                    + "_" + lcTimeStr.substring(lcTimeStr.length() - 4, lcTimeStr.length());               
            String lcSaveDirPath, lcSaveFilePath;
            if (printType.equals("02") || printType.equals("01")) {
                lcSaveDirPath = this.rootPath + Define.M_REPORT_PDF;
                lcSaveReportFile += ".PDF";
                lcSaveFilePath = lcSaveDirPath + lcSaveReportFile;
                File Checkpath = new File(lcSaveDirPath);
                if (!Checkpath.exists()) {
                    System.out.println("Da tao thu muc: " + lcSaveReportFile);
                    Checkpath.mkdirs();
                }
                System.err.println(lcSourceJasperFile + ":" + lcSaveFilePath);
                exportReport.ExportJasperPdf(lcSourceJasperFile, paramHashMap, lcSaveFilePath);
            } else {
                lcSaveDirPath = this.rootPath + Define.M_REPORT_XLS;
                lcSaveReportFile += ".XLS";
                lcSaveFilePath = lcSaveDirPath + lcSaveReportFile;
                File Checkpath = new File(lcSaveDirPath);
                if (!Checkpath.exists()) {
                    System.out.println("Da tao thu muc: " + lcSaveReportFile);
                    Checkpath.mkdirs();
                }
                //System.err.println(lcSourceJasperFile + ":" + lcSaveFilePath);
                BalanceExcelExport excelExport = new BalanceExcelExport();
                try {
                    excelExport.ToExcel(lcSaveFilePath, reportDate, ltermFlag, accountType, accountList);
                    //exportReport.ExportJasperExcel(lcSourceJasperFile, paramHashMap, lcSaveFilePath);
                } catch (IOException | InvalidFormatException | SQLException ex) {
                    Logger.getLogger(BalanceSheetExporter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.gc();
            filePathList.add(lcSaveReportFile);
            filePathList.add(lcSaveFilePath);
            return filePathList;
        } catch (ParseException ex) {
            Logger.getLogger(BalanceSheetExporter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<String> generate_rs() {
        try {
            ArrayList<String> filePathList = new ArrayList<>();
            String lcReportId;
            lcReportId = DefineFun.getBalanceSheetReportId(accountType);
            HashMap<String, String> hmNameReport_Jasper = objLRP.getNameReport_Jasper(lcReportId);
            String lcReportName = hmNameReport_Jasper.get("NAME_FILE");
            String lcJasperFileName = hmNameReport_Jasper.get("NAME_JASPER");
            String lcTongHop;
            if (consolidateFlag.equals("Y")) {
                lcTongHop = "_TONGHOP";
            } else {
                lcTongHop = "";
            }
            String ltermFlag;
            ltermFlag = termFlag.substring(2, 3);           
            
            String lcCurrentDateStr
                    = DefineFun.convertStrDateFormat(reportDate, OLD_FORMAT, NEW_FORMAT);
            
            String lcTimeStr = Long.toString(System.currentTimeMillis());
            String lcSaveReportFile = this.posOfUser.trim() + "_"
                    + lcReportName + lcTongHop + "_" + lcCurrentDateStr
                    + "_" + lcTimeStr.substring(lcTimeStr.length() - 4, lcTimeStr.length());
            String lcSaveDirPath, lcSaveFilePath;

            lcSaveDirPath = this.rootPath + Define.M_REPORT_XLS;
            lcSaveReportFile += ".XLS";
            lcSaveFilePath = lcSaveDirPath + lcSaveReportFile;
            File Checkpath = new File(lcSaveDirPath);
            if (!Checkpath.exists()) {
                System.out.println("Da tao thu muc: " + lcSaveReportFile);
                Checkpath.mkdirs();
            }
            //System.err.println(lcSourceJasperFile + ":" + lcSaveFilePath);
            BalanceExcelExport excelExport = new BalanceExcelExport();
            try {
                excelExport.ToExcel(lcSaveFilePath, reportDate, ltermFlag, accountType, this.resultset);
                //exportReport.ExportJasperExcel(lcSourceJasperFile, paramHashMap, lcSaveFilePath);
            } catch (IOException | InvalidFormatException | SQLException ex) {
                Logger.getLogger(BalanceSheetExporter.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.gc();
            filePathList.add(lcSaveReportFile);
            filePathList.add(lcSaveFilePath);
            return filePathList;
        } catch (ParseException ex) {
            Logger.getLogger(BalanceSheetExporter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private String getMergeList2String() {
        String mergeString = "";
        for (String string : posCodeList) {
            mergeString += "P" + string.trim() + ",";
        }
        return mergeString.substring(0, mergeString.length() - 1);
    }
}
