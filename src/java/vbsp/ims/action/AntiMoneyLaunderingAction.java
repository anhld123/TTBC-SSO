/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.action;


import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.define.Define;
import vbsp.ims.model.AntiMoney;
import vbsp.ims.model.BalanceSheetManager;
import javax.servlet.http.HttpServletRequest;
import vbsp.ims.dao.DaoExportHstdct;


/**
 *
 * @author Trung
 */
public class AntiMoneyLaunderingAction extends ActionSupport
        implements ServletRequestAware {
    private static final long serialVersionUID = 6518221459701336965L;

    HttpServletRequest request;

    private List<AntiMoney> antiMoneyList = new ArrayList<>();
    private String reportDate;
    private String userName;
    private int reportGrade;
    private String searchKey;
    private String searchType;
    private String launderingType;
    private String filereport;
    private String fileNamelocal;


    private String message;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public AntiMoneyLaunderingAction() {}
    

    /* Hàm view */
    public String view() {  
        String strReturn = "";
        message = "view() --> " + "reportDate: " + reportDate +
                ", searchKey: " + searchKey + 
                ", searchType: " + searchType + 
                ", launderingType: " + launderingType ;
        antiMoneyList = BalanceSheetManager.list_anti(reportDate,launderingType);
      //  antiMoneyList.add(new AntiMoney("1", "2", "002505"));
        if (antiMoneyList.size()>0)
        {
            strReturn = "success";
        } 
        else
        {
            message = "Ngày bạn chọn không có giao dịch.";
            strReturn = "error";
        }
        return strReturn;
    }

    /* Hàm xuất text */
    public String textExport() {    
        message = "textExport() --> " + "reportDate: " + reportDate +
                ", searchKey: " + searchKey + 
                ", searchType: " + searchType + 
                ", launderingType: " + launderingType ;
        String strPathSave = !request.getRealPath("/").endsWith("/")?request.getRealPath("/")+"/"+ Define.M_REPORT_TXT:
                             request.getRealPath("/")+ Define.M_REPORT_TXT;
//        System.err.println("Duong dan " + strPathSave);

        File Checkpath = new File(strPathSave);

        if (!Checkpath.exists()) {
            System.out.println("Da tao thu muc: " + strPathSave);
            Checkpath.mkdirs();
        }
        String strFullFile = "";
        DaoExportHstdct daoExp = new DaoExportHstdct();
        strFullFile = daoExp.getDataExportFile_CRT("1", reportDate, launderingType, strPathSave);
        
        filereport =  strFullFile.substring(strPathSave.length(), strFullFile.length());
        fileNamelocal = strFullFile;
        return "success";
    }

    /* Hàm gửi lại báo cáo */
    public String reexportText() {  
        message = "reexportText() --> " + "reportDate: " + reportDate +
                ", searchKey: " + searchKey + 
                ", searchType: " + searchType + 
                ", launderingType: " + launderingType ;
        String strPathSave = !request.getRealPath("/").endsWith("/")?request.getRealPath("/")+"/"+ Define.M_REPORT_TXT:
                             request.getRealPath("/")+ Define.M_REPORT_TXT;
//        System.err.println("Duong dan " + strPathSave);

        File Checkpath = new File(strPathSave);

        if (!Checkpath.exists()) {
            System.out.println("Da tao thu muc: " + strPathSave);
            Checkpath.mkdirs();
        }
        String strFullFile = "";
        DaoExportHstdct daoExp = new DaoExportHstdct();
        strFullFile = daoExp.getDataExportFile_CRT("2", reportDate, launderingType, strPathSave);
        
        filereport =  strFullFile.substring(strPathSave.length(), strFullFile.length());
        fileNamelocal = strFullFile;
        return "success";
    }

    /* Hàm tìm kiếm */
    public String search() {         
        message = "search() --> " + "reportDate: " + reportDate +
                ", searchKey: " + searchKey + 
                ", searchType: " + searchType + 
                ", launderingType: " + launderingType ;
        antiMoneyList = BalanceSheetManager.list_SearchAnti(reportDate,launderingType,searchType,searchKey);
        return "success";
    }
    
    //------------------------------------------------------------------------------------------    
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public List<AntiMoney> getAntiMoneyList() {
        return antiMoneyList;
    }

    public void setAntiMoneyList(List<AntiMoney> antiMoneyList) {
        this.antiMoneyList = antiMoneyList;
    }

    

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getFilereport() {
        return filereport;
    }

    public void setFilereport(String filereport) {
        this.filereport = filereport;
    }

    public String getFileNamelocal() {
        return fileNamelocal;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getLaunderingType() {
        return launderingType;
    }

    public void setLaunderingType(String launderingType) {
        this.launderingType = launderingType;
    }        
    
    public void setFileNamelocal(String fileNamelocal) {
        this.fileNamelocal = fileNamelocal;
    }    

    public String getText(Object object, String objectType) {
        if (objectType.equals("format.Number")) {
            return new DecimalFormat("#,###.##").format((BigDecimal) object);
        } else {
            return object.toString();
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }  
}
