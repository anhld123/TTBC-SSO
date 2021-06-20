package vbsp.ims.loadparams;

import java.util.HashMap;
import java.util.List;
import vbsp.ims.dao.DaoLoadReportParams;
import vbsp.ims.log.CoreLogger;

//CuongBM: 15-Apr-14
//Description: Load danh sach tham so cho cac bao cao
public class LoadReportParams {

    private DaoLoadReportParams lrp = new DaoLoadReportParams();
    private String strNameReport;
    private String strNameJasper;

    //CuongBM: 15-Apr-14
    //Desc: Lay danh sach ma Report
    public List<Combo> getReportList(String reportGroup) {
        return lrp.getReportList(reportGroup);
    }

    //CuongBM: 16-Apr-2014
    //Desc:    Lay danh sach report paramenter cu bao cao
    public List<ReportParam> getReportPramsList(String reportId, String strUsername) {
        return lrp.getReportParmams(reportId, strUsername);
    }

    public void getNameReport(String strReport_id) {
        try {
            HashMap<String, String> hmNameReport = new HashMap<String, String>();
            hmNameReport = lrp.getNameFromReportID(strReport_id);

            setStrNameReport(hmNameReport.get("NAME_FILE"));
            setStrNameJasper(hmNameReport.get("NAME_JASPER"));

        } catch (Exception e) {
            CoreLogger.error(LoadReportParams.class.getClass() + " Loi khi goi ham getNameReport  -> " + e.getMessage());
        }

    }

    public HashMap<String, String> getNameReport_Jasper(String strReport_id) {
       HashMap<String, String> hmNameReport = new HashMap<String, String>();
        try {
            
            hmNameReport = lrp.getNameFromReportID(strReport_id);
        } catch (Exception e) {
            CoreLogger.error(LoadReportParams.class.getClass() + " Loi khi goi ham getNameReport  -> " + e.getMessage());
        }
        return hmNameReport;
    }

    public String getStrNameReport() {
        return strNameReport;
    }

    public void setStrNameReport(String strNameReport) {
        this.strNameReport = strNameReport;
    }

    public String getStrNameJasper() {
        return strNameJasper;
    }

    public void setStrNameJasper(String strNameJasper) {
        this.strNameJasper = strNameJasper;
    }

}
