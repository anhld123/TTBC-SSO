package vbsp.ims.action.ktktnb;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Calendar;
import java.util.List;
import vbsp.ims.model.ktnb.ListKTNB;
import vbsp.ims.dao.ktnb.ListKTNBDA;

public class ListKTNBAuthActionSupport extends ActionSupport {
    private  List<ListKTNB> DSKTNB;
    private List<String> lstYearReport;
    private String defaultYearReport;
    
    public ListKTNBAuthActionSupport() {
    }
    
    public String execute() throws Exception {
        DSKTNB = new ListKTNBDA().ListDMKTNBAuth();
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
