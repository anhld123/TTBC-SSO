package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.bcnt.POS;
import vbsp.ims.bcnt.Report;
import vbsp.ims.dao.DaoBCNT;

public class LoadBcntGridActionSupport extends ActionSupport implements ServletRequestAware{
    private String reportId;
    private String reportDate;
    private String quarteryear; //CuongBM: Quy bao cao
    private String posId;    
    private List<Report> reportList;
    private List<POS> posList;    
    private HttpServletRequest request = null;    
    private DaoBCNT daoBcnt = new DaoBCNT();
    
    public LoadBcntGridActionSupport() {
    }
    
    @Override
    public String execute() throws Exception {
        return "success";
    }
    
    public String populateReportId(){
        reportList = daoBcnt.getReportList();
        
        return "success";
    }
    
    public String populatePosId(){
        //Lay username
        HttpSession session = request.getSession();
        String strUserName=session.getAttribute("username").toString();
        
        posList = daoBcnt.getPosList(strUserName);
        
        return "success";
    }
        
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public String getReportId() {
        return reportId;
    }
    
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
    
    public String getReportDate() {
        return reportDate;
    }
    
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }
    
     public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }
    
    public List<POS> getPosList() {
        return posList;
    }

    public void setPosList(List<POS> posList) {
        this.posList = posList;
    }
    
    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }
    
    public String getQuarteryear() {
        return quarteryear;
    }

    public void setQuarteryear(String quarteryear) {
        this.quarteryear = quarteryear;
    }
    
    
    
//</editor-fold>

    
    
}
