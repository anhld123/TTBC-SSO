/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.CICReportDao;
import vbsp.ims.define.DefineFun;
import vbsp.ims.model.CICLogRecord;
import vbsp.ims.model.CICRecord;

/**
 *
 * @author Trung
 */
public class CICReportAction extends ActionSupport
        implements ServletRequestAware{
    private static final long serialVersionUID = 6518221459701336965L;
    HttpServletRequest request;
    
    private List<CICRecord> viewList = new ArrayList<>();
    private List<CICLogRecord> logList = new ArrayList<>();
    private String reportDate;
    private String searchKey;
    private String searchType;
    private String message;
    private String searchObj;
    
    public HttpServletRequest getRequest() {
        return request;
    }
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    //------------------------------------------------------------------------------------------    
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    public String view(){
        CICReportDao cicReportDao = new CICReportDao();  
        String l_report_date = DefineFun.convert2OracleDateFormat(reportDate);
        viewList = cicReportDao.getViewList(l_report_date);
        return SUCCESS;
    }
    
    public String viewLog(){
        CICReportDao cicReportDao = new CICReportDao();  
        String l_report_date = DefineFun.convert2OracleDateFormat(reportDate);
        logList = cicReportDao.getViewLogList(l_report_date);
        return SUCCESS;
    }
    
    public String search(){
        CICReportDao cicReportDao = new CICReportDao();  
        String l_report_date = DefineFun.convert2OracleDateFormat(reportDate);
        if (searchObj.equals("01")){
            logList = cicReportDao.getLogSearchList(l_report_date,searchKey,searchType);
            return INPUT;
        }else {
            viewList = cicReportDao.getSearchList(l_report_date,searchKey,searchType);
            return SUCCESS;
        }        
    }
    
    public String createJobs(){
        String l_userName = request.getSession().getAttribute("username").toString();
        CICReportDao cicReportDao = new CICReportDao();  
        String l_report_date = DefineFun.convert2OracleDateFormat(reportDate);
        message = "(*)" + cicReportDao.createJobs(l_report_date,l_userName);
        return SUCCESS;
    }

    //------------------------------------------------------------------------------------------    
    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public List<CICRecord> getViewList() {
        return viewList;
    }

    public void setViewList(List<CICRecord> viewList) {
        this.viewList = viewList;
    }

    public String getSearchObj() {
        return searchObj;
    }

    public void setSearchObj(String searchObj) {
        this.searchObj = searchObj;
    }
        
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }        

    public List<CICLogRecord> getLogList() {
        return logList;
    }

    public void setLogList(List<CICLogRecord> logList) {
        this.logList = logList;
    }       
    
    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
    //------------------------------------------------------------------------------------------        
}
