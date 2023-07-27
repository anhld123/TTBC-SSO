package vbsp.ims.action.ktktnb;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import vbsp.ims.model.ktnb.ListKTNB;
import vbsp.ims.dao.ktnb.ListKTNBDA;

public class ListKTNBActionSupport extends ActionSupport {
    private  List<ListKTNB> DSKTNB;
    private List<String> lstYearReport;
    private String defaultYearReport;
    
     private HttpServletRequest request = null;
      private String reportGrade;

    public String getReportGrade() {
        return reportGrade;
    }

    public void setReportGrade(String reportGrade) {
        this.reportGrade = reportGrade;
    }
     
    public ListKTNBActionSupport() {
    }
    
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        setReportGrade(session.get("reportGrade").toString());
//        System.out.println("reportGrade = " + reportGrade);
        DSKTNB = new ListKTNBDA().ListDMKTNB(reportGrade);
        lstYearReport = new ListKTNBDA().getYearReport();
        return SUCCESS;
    }

    public String getDefaultYearReport() {
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    }

    public void setDefaultYearReport(String defaultYearReport) {
        this.defaultYearReport = defaultYearReport;
    }
    
    public List<String> getLstYearReport() {
        return lstYearReport;
    }

    public void setLstYearReport(List<String> lstYearReport) {
        this.lstYearReport = lstYearReport;
    }
    
    public List<ListKTNB> getDSKTNB() {
        return DSKTNB;
    }

    public void setDSKTNB(List<ListKTNB> DSKTNB) {
        this.DSKTNB = DSKTNB;
    }
 
}
