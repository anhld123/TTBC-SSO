/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.ReportUpdateDao;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.jasper.BuilderJasper;
import vbsp.ims.jasper.JasperParam;
import vbsp.ims.model.ReportInfor;
import vbsp.ims.model.ReportUpdateManager;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class ReportFileUploadAction
        extends ActionSupport
        implements ServletRequestAware {

    private List<File> fileUpload = new ArrayList<>();
    private List<String> fileUploadContentType = new ArrayList<>();
    private List<String> fileUploadFileName = new ArrayList<>();
    private List<ListValue> rptGroupList;
    private String selectedGroup;
    private List<ListValue> rptReportList;
    private String selectedReport;

    private String autoGenerateRptCode;
    private ReportInfor reportInfor;
    /* */
    private String shortcutName;
    private int reportUnit;
    private String reportDescript;
    private String reportTerm;
    private String reportType;
    private String reportGrade;
    private String generatedName;

    HttpServletRequest request;
    private String message;
    private List<JasperParam> reportParam = new ArrayList<>();
    private String paramDataArray;

    private ReportUpdateManager reportUpdateManager
            = new ReportUpdateManager();

    public ReportFileUploadAction() {
//        reportUpdateManager = new ReportUpdateManager();
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public List<File> getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(List<File> fileUpload) {
        this.fileUpload = fileUpload;
    }

    public List<String> getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(List<String> fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public List<String> getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(List<String> fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public String generateGroupCombo() {
//        if (this.rptGroupList.isEmpty())        
        this.rptGroupList = reportUpdateManager.getReportGroupList();
        this.rptReportList = reportUpdateManager.getReportList(
                this.selectedGroup);
        if (!this.selectedGroup.isEmpty()) {
            System.err.println("selectedGroup -->" + this.selectedGroup);
        }
        return "populate";
    }

    public String viewReport() {

        System.err.println(this.selectedGroup);

        if (!(this.selectedGroup.isEmpty()
                || this.selectedGroup.equals("NULL"))
                || !(this.selectedReport.isEmpty()
                || this.selectedReport.equals("NULL"))) {
            if (this.selectedReport.equals("ADDNEW")) {

                reportUpdateManager.setUpdateType("01");
                this.autoGenerateRptCode = ReportUpdateDao.getNextReportId(
                        this.selectedGroup);
                reportUpdateManager.getReportInfor().setReportCode(
                        this.autoGenerateRptCode);
                reportUpdateManager.getReportInfor().setReportGroupCode(
                        this.selectedGroup);
                return "success";

            } else {
                reportUpdateManager.setUpdateType("02");

                this.autoGenerateRptCode = this.selectedReport;
                reportUpdateManager.updateReportInfor(
                        this.autoGenerateRptCode);

                this.shortcutName = reportUpdateManager.getReportInfor().getShortcutName();
                this.reportUnit = reportUpdateManager.getReportInfor().getReportUnit();
                this.reportDescript = reportUpdateManager.getReportInfor().getDescript();
                this.reportTerm = reportUpdateManager.getReportInfor().getReportTerm();
                this.reportType = reportUpdateManager.getReportInfor().getReportType();
                this.reportGrade = reportUpdateManager.getReportInfor().getReportGrade();
                this.generatedName = reportUpdateManager.getReportInfor().getGeneratedName();

                return "success";
            }
        } else {
            this.message = "(*)Bạn chưa chọn nhóm báo cáo";
            return "error";
        }
    }

    public String disableReport() {
        boolean status = false;
        if (!this.selectedReport.isEmpty()) {
            status = reportUpdateManager.disableReport(
                    this.selectedReport);
        }
        if (status) {
            this.message = "(*)Báo cáo <" + this.selectedReport + "> đã được vô hiệu";
        } else {
            this.message = "(*)Vô hiệu Báo cáo <" + this.selectedReport + "> thất bại";
        }
        return "success";
    }

    public String enableReport() {
        boolean status = false;
        if (!this.selectedReport.isEmpty()) {
            status = reportUpdateManager.enableReport(this.selectedReport);
        }
        if (status) {
            this.message = "(*)Báo cáo <" + this.selectedReport + "> đã được kích hoạt";
        } else {
            this.message = "(*)Kích hoạt Báo cáo <" + this.selectedReport + "> thất bại";
        }
        return "success";
    }

    public String uploadReport() {
        if (fileUploadFileName.isEmpty() || this.shortcutName.isEmpty()
                || this.reportGrade.isEmpty() || this.reportDescript.isEmpty()
                || this.generatedName.isEmpty() || this.reportTerm.isEmpty()) {

            if (reportUpdateManager.getUpdateType().equals("02")) {

//                reportUpdateManager.getReportInfor().setReportCode(this.selectedReport);
                this.autoGenerateRptCode = this.selectedReport;

                reportUpdateManager.updateReportInfor(
                        this.autoGenerateRptCode);

                reportUpdateManager.getReportInfor().setReportGroupCode(this.selectedGroup);
                reportUpdateManager.getReportInfor().setShortcutName(this.shortcutName);
                reportUpdateManager.getReportInfor().setReportGrade(
                        getRevertMappingCode(this.reportGrade, 3));
                reportUpdateManager.getReportInfor().setDescript(this.reportDescript);
                reportUpdateManager.getReportInfor().setGeneratedName(this.generatedName);
                reportUpdateManager.getReportInfor().setReportUnit(this.reportUnit);
                reportUpdateManager.getReportInfor().setReportType(getRevertMappingCode(this.reportType, 2));

                reportParam = reportUpdateManager.getReportInfor().getParam();

                return "success";

            } else {
                this.message = "(*)Bạn phải nhập đầy đủ thông tin";
                return "input";
            }
        } else {
            /* Phan cap nhat file */
            String mainReportFile = uploadFile();
            /* Phan cap nhat thong tin report*/
            //this.autoGenerateRptCode = this.selectedReport;
            reportUpdateManager.updateReportInfor(
                    this.autoGenerateRptCode);

            reportUpdateManager.getReportInfor().setReportGroupCode(this.selectedGroup);
            reportUpdateManager.getReportInfor().setShortcutName(this.shortcutName);
            reportUpdateManager.getReportInfor().setReportGrade(getRevertMappingCode(this.reportGrade, 3));
            reportUpdateManager.getReportInfor().setDescript(this.reportDescript);
            reportUpdateManager.getReportInfor().setGeneratedName(this.generatedName);
            reportUpdateManager.getReportInfor().setReportUnit(this.reportUnit);
            reportUpdateManager.getReportInfor().setReportTerm(this.reportTerm);
            reportUpdateManager.getReportInfor().setReportType(getRevertMappingCode(this.reportType, 2));

            Path p = Paths.get(mainReportFile);
            String fileName = p.getFileName().toString();
            reportUpdateManager.getReportInfor().setJaserFileName(
                    fileName.toUpperCase().replace(".JRXML", "")
            );

            /* Phan lay thong tin tham so */
            System.err.println("getParameters~" + mainReportFile);

            reportParam = getParameters(mainReportFile);

            reportUpdateManager.getReportInfor().setParam(reportParam);
//            System.err.println("uploadReport()-->" + this.shortcutName + this.reportDescript);
            return "success";
        }
    }

    public String saveParam() {
        System.err.println("saveParam " + this.paramDataArray);

        if (this.paramDataArray.isEmpty()) {
            this.message = "(*)Cập nhật tham số thất bại";
        } else {
            ArrayList<String> paramList
                    = (ArrayList<String>) DefineFun.string2Array(
                            this.paramDataArray + " ", "#", 2);

//            this.autoGenerateRptCode = this.selectedReport;
//                reportUpdateManager.updateReportInfor(
//                        this.autoGenerateRptCode);
            reportInfor = reportUpdateManager.getReportInfor();

            ArrayList<JasperParam> newParam = new ArrayList<JasperParam>();
            JasperParam jasperParam;

            int i = 0, j = 0, arraySize;

            arraySize = (int) Math.floor(paramList.size() / 9);

            //System.err.println(reportInfor.getParam().size());
            for (i = 1; i <= arraySize; i++) {

                jasperParam = new JasperParam();

                jasperParam.setParamName(paramList.get((i - 1) * 9 + 1 - 1));
                jasperParam.setParamType(
                        getRevertMappingCode(paramList.get((i - 1) * 9 + 2 - 1).trim(), 4));
                jasperParam.setParamDescript(paramList.get((i - 1) * 9 + 3 - 1));
                jasperParam.setOrder(Integer.parseInt(paramList.get((i - 1) * 9 + 4 - 1)));
                jasperParam.setRefTable(paramList.get((i - 1) * 9 + 5 - 1));
                jasperParam.setDisplayColumn(paramList.get((i - 1) * 9 + 6 - 1));
                jasperParam.setParamColumn(paramList.get((i - 1) * 9 + 7 - 1));
                jasperParam.setFilterCondition(paramList.get((i - 1) * 9 + 8 - 1));
                jasperParam.setOrderCondition(paramList.get((i - 1) * 9 + 9 - 1));
                newParam.add(jasperParam);
            }

            reportInfor.setParam(newParam);

//            for (i = 1; i <= reportInfor.getParam().size(); i++) {
//                reportInfor.getParam().get(i - 1).setParamType(
//                        getRevertMappingCode(paramList.get((i - 1) * 8 + 1 - 1).trim(),4));
//                reportInfor.getParam().get(i - 1).setParamDescript(paramList.get((i - 1) * 8 + 2 - 1));
//                reportInfor.getParam().get(i - 1).setOrder(Integer.parseInt(paramList.get((i - 1) * 8 + 3 - 1)));
//                reportInfor.getParam().get(i - 1).setRefTable(paramList.get((i - 1) * 8 + 4 - 1));
//                reportInfor.getParam().get(i - 1).setDisplayColumn(paramList.get((i - 1) * 8 + 5 - 1));
//                reportInfor.getParam().get(i - 1).setParamColumn(paramList.get((i - 1) * 8 + 6 - 1));
//                reportInfor.getParam().get(i - 1).setFilterCondition(paramList.get((i - 1) * 8 + 7 - 1));
//                reportInfor.getParam().get(i - 1).setOrderCondition(paramList.get((i - 1) * 8 + 8 - 1));
//            }
            reportUpdateManager.setReportInfor(reportInfor);
//            ReportUpdateManager.getReportInfor().setParam(reportInfor.getParam());
            boolean lbUpdateStatus = reportUpdateManager.create();

            if (lbUpdateStatus) {
                reportUpdateManager.refresh();
                this.message = "(*)Cập nhật tham số thành công";
            } else {
                this.message = "(*)Cập nhật tham số thất bại";
            }
        }
        return "success";
    }

    /* Hàm lấy mã ánh xạ */
    public String getMappingCode(String orginalCode, int mappingType) {
        String mappedCode = "";
        switch (mappingType) {
            case 1:
            case 2:
                mappedCode = String.valueOf(Integer.valueOf(orginalCode));
                break;
            case 3:
                switch (orginalCode) {
                    case "/01/":
                        mappedCode = "1";
                        break;
                    case "/02/":
                        mappedCode = "2";
                        break;
                    case "/03/":
                        mappedCode = "3";
                        break;
                    case "/01/02/":
                        mappedCode = "4";
                        break;
                    case "/02/03/":
                        mappedCode = "5";
                        break;
                    case "/01/02/03/":
                        mappedCode = "6";
                        break;

                }
                break;
            case 4:
                switch (orginalCode) {
                    case "T":
                        mappedCode = "1";
                        break;
                    case "L":
                        mappedCode = "2";
                        break;
                    case "D":
                        mappedCode = "3";
                        break;
                }
                break;
            default:
                break;
        }
        return mappedCode;
    }
    /* Hàm lấy mã ánh xạ ngược */

    public String getRevertMappingCode(String mappeCode, int mappingType) {
        String orginalCode = "";
        switch (mappingType) {
            case 1:
            case 2:
                orginalCode = "0" + mappeCode.trim();
                break;
            case 3:
                switch (mappeCode) {
                    case "1":
                        orginalCode = "/01/";
                        break;
                    case "2":
                        orginalCode = "/02/";
                        break;
                    case "3":
                        orginalCode = "/03/";
                        break;
                    case "4":
                        orginalCode = "/01/02/";
                        break;
                    case "5":
                        orginalCode = "/02/03/";
                        break;
                    case "6":
                        orginalCode = "/01/02/03/";
                        break;

                }
                break;
            case 4:
                switch (mappeCode) {
                    case "1":
                        orginalCode = "T";
                        break;
                    case "2":
                        orginalCode = "L";
                        break;
                    case "3":
                        orginalCode = "D";
                        break;
                }
                break;
            default:
                break;
        }
        return orginalCode;
    }

    //--------------------------------------------------------------------------
    public String getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(String selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    public List<ListValue> getRptGroupList() {
        return rptGroupList;
    }

    public void setRptGroupList(List<ListValue> rptGroupList) {
        this.rptGroupList = rptGroupList;
    }

    public String getAutoGenerateRptCode() {
        return autoGenerateRptCode;
    }

    public void setAutoGenerateRptCode(String autoGenerateRptCode) {
        this.autoGenerateRptCode = autoGenerateRptCode;
    }

    public List<JasperParam> getReportParam() {
        return reportParam;
    }

    public void setReportParam(List<JasperParam> reportParam) {
        this.reportParam = reportParam;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getShortcutName() {
        return shortcutName;
    }

    public void setShortcutName(String shortcutName) {
        this.shortcutName = shortcutName;
    }

    public int getReportUnit() {
        return reportUnit;
    }

    public void setReportUnit(int reportUnit) {
        this.reportUnit = reportUnit;
    }

    public ReportInfor getReportInfor() {
        return reportInfor;
    }

    public void setReportInfor(ReportInfor reportInfor) {
        this.reportInfor = reportInfor;
    }

    public String getReportDescript() {
        return reportDescript;
    }

    public void setReportDescript(String reportDescript) {
        this.reportDescript = reportDescript;
    }

    public String getReportTerm() {
        return reportTerm;
    }

    public void setReportTerm(String reportTerm) {
        this.reportTerm = reportTerm;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportGrade() {
        return reportGrade;
    }

    public void setReportGrade(String reportGrade) {
        this.reportGrade = reportGrade;
    }

    public String getGeneratedName() {
        return generatedName;
    }

    public void setGeneratedName(String generatedName) {
        this.generatedName = generatedName;
    }

    public String getParamDataArray() {
        return paramDataArray;
    }

    public void setParamDataArray(String paramDataArray) {
        this.paramDataArray = paramDataArray;
    }

    public List<ListValue> getRptReportList() {
        return rptReportList;
    }

    public void setRptReportList(List<ListValue> rptReportList) {
        this.rptReportList = rptReportList;
    }

    public String getSelectedReport() {
        return selectedReport;
    }

    public void setSelectedReport(String selectedReport) {
        this.selectedReport = selectedReport;
    }

    //----------------------------------------------------------------------------------------
    private String uploadFile() {
        String destPath, mainReportPath = "";
        File destFile;
        int index = 0;
        for (String filename : fileUploadFileName) {
            try {
                destPath = !request.getRealPath("/").endsWith("/")
                        ? request.getRealPath("/") + "/" + Define.M_REPORT
                        : request.getRealPath("/") + Define.M_REPORT;
                System.err.println("uploadFile~" + destPath);
                destFile = new File(destPath, filename);
                FileUtils.copyFile(fileUpload.get(index), destFile);
                if (index == 0) {
                    mainReportPath = destPath + filename;
                }
                index++;
            } catch (IOException ex) {
            }
        }
        return mainReportPath;
    }

    private ArrayList<JasperParam> getParameters(String filePath) {
        try {
            BuilderJasper build
                    = new BuilderJasper(filePath);
            ArrayList<JasperParam> paramList
                    = build.getParameters();
            reportUpdateManager.suggest(paramList);
            return paramList;
        } catch (Exception ex) {
            System.err.println("getParameters error~" + ex.getMessage());
            return null;
        }
    }

    //----------------------------------------------------------------------------------------
    public ReportUpdateManager getReportUpdateManager() {
        return reportUpdateManager;
    }

    public void setReportUpdateManager(ReportUpdateManager reportUpdateManager) {
        this.reportUpdateManager = reportUpdateManager;
    }
}
