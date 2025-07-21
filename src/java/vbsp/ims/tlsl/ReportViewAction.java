/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.tlsl;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.BuildPosTreeDao;
import vbsp.ims.dao.IMSRptDao;
import vbsp.ims.define.DefineFun;
import vbsp.ims.eom_help.EOMTaskHelpDao;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class ReportViewAction extends ActionSupport
        implements
        ServletRequestAware {

    private HttpServletRequest request;
    Map parameters = null;

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    private List<Report> reports;
    private String group;
    private List<ListValue> groups;
    private String[] checks;
    private String selectedNo;
    private List<ListValue> periods;

    private String reportDate;
    private String todate;
    private String export_format;
    private String report_type;
    private String message;
    private String selectedPos;
    private String userName;
    private int reportGrade;
    private TreeNode nodes;
    private String consolidateFlag;
    private TreeNode searchNodes;
    private String selectedPeriod;
    private String eco_area;

    private String fileNamelocal;
    private String filereport;

    @Override
    public String execute() {
        
        ReportDao reportDao = new ReportDao();
        
        if (group == null || group.isEmpty()) {
            reports = reportDao.list_report("ALL");
        } else {
            reports = reportDao.list_report(group);
        }
        return SUCCESS;
    }

    public String build_group_combo() {
        ReportDao reportDao = new ReportDao();
        groups = reportDao.list_group();
        return "populate";
    }

    public String build_period_combo() {
        EOMTaskHelpDao eomTaskDao = new EOMTaskHelpDao();
        periods = eomTaskDao.list_all_period("TLSL");
        return SUCCESS;
    }

    public String generate_data() {
        if (this.searchNodes == null) {
            System.err.print("searchNodes is null");
            try {
                reportGrade = Integer.parseInt(
                        request.getSession().getAttribute("reportGrade").toString());
                userName = request.getSession().getAttribute("username").toString();
                BuildPosTreeDao buildPosTreeDao = new BuildPosTreeDao(reportGrade, userName);
                
                if (null
                        == eco_area)                
                    buildPosTreeDao.build();
                else  switch (eco_area) {
                    case "1":
                        buildPosTreeDao.build();
                        break;
                    case "2":
                        buildPosTreeDao.build_vb96();
                        break;
                    default:
                        buildPosTreeDao.build_2025();
                        break;
                }
                
                this.searchNodes = buildPosTreeDao.getNodes();
            } catch (SQLException ex) {
                System.err.println("TLSL-generate_data[1]"+ex.getMessage());
            }
        }
        String listOfPos = getListOfPos();

        if (selectedNo == null || selectedNo.isEmpty()) {
            message = "<do> (*) Bạn chưa chọn báo cáo. <do>";
            return ERROR;
        } else {
            if (todate == null || todate.isEmpty()) {
                message = "<do> (*) Bạn chưa nhập ngày kết thúc số liệu. <do>";
                return ERROR;
            } else {
                if (selectedPeriod == null || selectedPeriod.equals("NULL")){
                    message = "<do> (*) Bạn chưa chọn kỳ báo cáo. <do>";
                    return ERROR;
                }
            }
        }

        ReportExporter exporter;
        
        if (export_format.equals("PDF")){
            exporter = new PDFExporter(
                reportDate,
                todate,
                selectedPeriod, 
                group, 
                selectedNo, 
                export_format, 
                listOfPos, 
                consolidateFlag);
            exporter.setReport_type(report_type);
            String reportEcoArea = "1";
            if ("1".equals(eco_area) || "3".equals(eco_area)){
                reportEcoArea = "1";
            } else {
                reportEcoArea = "2";
            }
            exporter.setEco_area(reportEcoArea);
        }
        else {
            
            exporter = new ExcelExporter(
                reportDate,
                todate,
                selectedPeriod, 
                group, 
                selectedNo, 
                export_format, 
                listOfPos, 
                consolidateFlag);
            exporter.setReport_type(report_type);
            //exporter.setEco_area(eco_area);
            String reportEcoArea = "1";
            if ("1".equals(eco_area) || "3".equals(eco_area)){
                reportEcoArea = "1";
            } else {
                reportEcoArea = "2";
            }
            exporter.setEco_area(reportEcoArea);
        }

        System.err.println("Export file...."+selectedNo+"~" + group+"~"
                +reportDate +"~"+ todate+"~"+listOfPos+"~"+consolidateFlag);
        
        boolean status = exporter.export();        
        
        //System.err.println("Export file done....");
        
        if (status || exporter.getPath() != null) {
            fileNamelocal = exporter.getPath();
            File file = new File(fileNamelocal);
            filereport = file.getName();
            return SUCCESS;
        } else {
            message = "<do> (*) Lỗi trong quá trình tạo file báo cáo hoặc có thể "
                    + "báo cáo chỉ in tổng hợp toàn quốc. <do> <xanh>[" 
                    + exporter.getName() + "] <xanh>" ;
            return ERROR;
        }
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public List<ListValue> getGroups() {
        return groups;
    }

    public void setGroups(List<ListValue> groups) {
        this.groups = groups;
    }

    public String[] getChecks() {
        return checks;
    }

    public void setChecks(String[] checks) {
        this.checks = checks;
    }

    public String getSelectedNo() {
        return selectedNo;
    }

    public void setSelectedNo(String selectedNo) {
        this.selectedNo = selectedNo;
    }

    public List<ListValue> getPeriods() {
        return periods;
    }

    public void setPeriods(List<ListValue> periods) {
        this.periods = periods;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getExport_format() {
        return export_format;
    }

    public void setExport_format(String export_format) {
        this.export_format = export_format;
    }

    public String getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(String selectedPos) {
        this.selectedPos = selectedPos;
    }

    private String getListOfPos() {
        String posString = "";
        
        userName = request.getSession().getAttribute("username").toString();
        
        reportGrade = Integer.parseInt(
                    request.getSession().getAttribute("reportGrade").toString());
        
        if (selectedPos == null 
                || selectedPos.trim().isEmpty()) {
            switch (reportGrade) {
                case 3:
                    posString = "000100";
                    this.consolidateFlag = "Y";
                    break;
                case 2:
                    posString = IMSRptDao.getPosOfUser(userName);
                    this.consolidateFlag = "Y";
                    break;
                default:
                    posString = IMSRptDao.getPosOfUser(userName);
                    this.consolidateFlag = "N";
                    break;
            }
        } else {
            
            String pos_cd;
            ArrayList<String> pos_stack = new ArrayList<>();
            ArrayList<String> listOfId
                    = (ArrayList<String>) DefineFun.string2Array(selectedPos, ",", 1);
            boolean isAdded;
            if (listOfId.size() > 0) {
                for (String id : listOfId) {
                    isAdded = false;
                    pos_cd = DefineFun.searchInTreeView(
                            id, this.searchNodes);
                    for (String added_pos : pos_stack) {
                        if (added_pos.equals(pos_cd)) {
                            isAdded = true;
                            break;
                        }
                    }
                    if (!isAdded && pos_cd != null) {
                        posString += pos_cd + "/";
                        pos_stack.add(pos_cd);
                    }
                }
            }
            posString = posString.trim().substring(0, 
                    posString.trim().length() - 1);
            if (reportGrade == 3) {
                this.consolidateFlag = "Y";
            } else {
                this.consolidateFlag = "N";
            }
        }
        //System.err.println(posString);
        return posString;
    }

    public String getSelectedPeriod() {
        return selectedPeriod;
    }

    public void setSelectedPeriod(String selectedPeriod) {
        this.selectedPeriod = selectedPeriod;
    }

    public String getFileNamelocal() {
        return fileNamelocal;
    }

    public void setFileNamelocal(String fileNamelocal) {
        this.fileNamelocal = fileNamelocal;
    }

    public String getFilereport() {
        return filereport;
    }

    public void setFilereport(String filereport) {
        this.filereport = filereport;
    }

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public String getEco_area() {
        return eco_area;
    }

    public void setEco_area(String eco_area) {
        this.eco_area = eco_area;
    }

    
    
}
