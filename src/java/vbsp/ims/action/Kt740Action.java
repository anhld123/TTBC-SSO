package vbsp.ims.action;

import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
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
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoKt740;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.loadparams.LoadReportParams;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.query.ImsFillParaMeter;
import vbsp.ims.query.ImsPlSqlQuery;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author LION
 */
public class Kt740Action extends ActionSupport implements ServletRequestAware {

    private String title;
    private String query;
    private String message;
    private String save_id;
    private String group_id;
    private String grade_id;
    private List<ListValue> lstObjQuery = new ArrayList<>();
    private List<ListValue> lstParaDesc = new ArrayList<>();
    private List<ListValue> lstObjGroup = new ArrayList<ListValue>();
    private LoadReportParams objLRP = new LoadReportParams();
    private HttpServletRequest request = null;
    private List<ReportParam> reportParamsList = new ArrayList<>();

    private String fileNamelocal;
    private String filereport;
    private List<ListValue> lstGrade = new ArrayList<ListValue>();
    private String[] defaultGrade;
    private List<String> rptGrade = new ArrayList<>();
    private List<String> lstGroup = new ArrayList<>();
    protected List<ListValue> lstParameters = new ArrayList<>();

    //<editor-fold defaultstate="collapsed" desc="Lop xu ly">
    public String LoadAddNewQuery() {
        try {
            System.err.println("Vao action Loadpara");
            lstParaDesc = new ArrayList<ListValue>();
            lstGrade = new ArrayList<ListValue>();
            lstParaDesc = new DaoKt740().loadDescPara();
            lstObjGroup = new DaoKt740().getGroupQuery(null);
            lstGrade.add(new ListValue("1", "Ngân hàng"));
            lstGrade.add(new ListValue("2", "Chi nhánh"));
            lstGrade.add(new ListValue("3", "Toàn quốc"));

            setDefaultGrade(new String[]{"1", "2", "3"});
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " LoadAddNewQuery -> " + e.getMessage());
            setMessage("Lỗi " + e.getMessage());
            return ERROR;
        }

        return SUCCESS;
    }

    public String execute() throws Exception {
//        System.err.println("Tieu de " + title);
//        System.err.println("Truy van " + query);
        if (group_id == null || group_id.isEmpty()) {
            setMessage("Bạn phải chọn nhóm báo cáo ");
            return ERROR;
        }
        if (grade_id == null || grade_id.isEmpty()) {
            setMessage("Bạn phải chọn cấp xuất báo cáo ");
            return ERROR;
        }
        lstGroup = convertStringtoList(group_id.replace(" ", "").split(","));
        rptGrade = convertStringtoList(grade_id.replace(" ", "").split(","));
        if (lstGroup == null || lstGroup.isEmpty()) {
            setMessage("Bạn phải chọn nhóm báo cáo ");
            return ERROR;
        }
        System.err.println(rptGrade.toString());
        if (title == null || title.isEmpty()) {
            setMessage("Bạn phải điền tiêu đề cho báo cáo ");
            return ERROR;
        }
        if (rptGrade == null || rptGrade.isEmpty()) {
            setMessage("Bạn phải chọn cấp xuất báo cáo ");
            return ERROR;
        }
        if (query == null || query.isEmpty()) {
            setMessage("Bạn phải truy vấn dữ liệu cho báo cáo ");
            return ERROR;
        }

        //Kiem tra ve procedure
        ImsPlSqlQuery PlSql = new ImsPlSqlQuery();

        if (PlSql.isOracleStoredProcedure(query)) {
            if (!PlSql.isStructureProcedure(query)) {
                setMessage("Bạn điền sai cấu trúc của procedure ");
                return ERROR;
            }

        }

        Map session = ActionContext.getContext().getSession();
        String sUserName = session.get("username").toString();
        DaoKt740 daoQuery = new DaoKt740();
        if (save_id != null) {
            String sUserCreaterpt = daoQuery.getUserCreateReport(save_id);
            if (!sUserCreaterpt.toLowerCase().equals(sUserName.toLowerCase())) {
                setMessage("Bạn chưa lưu được báo cáo, Do bạn không phải là người tạo báo cáo! Người tạo báo cáo là: " + sUserCreaterpt);
                return ERROR;
            }
        }
        String sGradeReport = "";
        for (String sRptGrade : rptGrade) {
            sGradeReport += "#" + sRptGrade;
        }

        sGradeReport += "#";
        group_id = "";
        for (String group : lstGroup) {
            group_id += "/" + group;
        }
        group_id += "/";
        boolean bSuccess = daoQuery.saveReportQuery(sUserName, save_id, group_id, title, query, sGradeReport);
        if (!bSuccess) {
            setMessage("Bạn phải chưa lưu được báo cáo xin kiểm tra lại kết nối mạng ");
            return ERROR;
        }
        //HashMap<String, String> hmQuery=daoQuery.getQuerySave("QUERY0000000108");

        //System.err.println("Query "+hmQuery.get("SRQ_QUERY"));
        setMessage("Tạo truy vấn thành công");
        return SUCCESS;
    }

    public String LoadMainParameterKt740(){
        return SUCCESS;
    }
    
    public String loadgroupEditQuerykt740() {
        try {
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới tải được báo cáo");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();

            DaoKt740 daoQuery = new DaoKt740();
            lstObjGroup = daoQuery.getGroupQuery(null);
        } catch (Exception e) {
            setMessage("Loi " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String editQuery() {
        try {
            lstParaDesc = new ArrayList<ListValue>();
            lstGrade = new ArrayList<ListValue>();
            //        System.err.println("vao phan sửa query " + save_id);
            lstGrade.add(new ListValue("1", "Ngân hàng"));
            lstGrade.add(new ListValue("2", "Chi nhánh"));
            lstGrade.add(new ListValue("3", "Toàn quốc"));

            lstParaDesc = new DaoKt740().loadDescPara();
            lstObjGroup = new DaoKt740().getGroupQuery(null);
//        setDefaultGrade(new String[]{"1", "2", "3"});

            HashMap<String, String> hmQuery = new DaoKt740().getEditQuery(save_id);

//        System.err.println("Query " + hmQuery.get("SRQ_QUERY"));
            title = hmQuery.get("STRF_TITLE_NAME");
            query = hmQuery.get("SRQ_QUERY");
            group_id = hmQuery.get("GROUP_ID");
            String strGradeRpt = hmQuery.get("GRADE_REPORT");
            ArrayList<String> lsttmp = DefineFun.SplitStringToArrayList(strGradeRpt, "#");
            defaultGrade = new String[lsttmp.size()];
            for (int i = 0; i < lsttmp.size(); i++) {
                defaultGrade[i] = lsttmp.get(i);
            }
//            defaultGrade=(String[]) DefineFun.SplitStringToArrayList(strGradeRpt, "#").toArray();
            setDefaultGrade(defaultGrade);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " editQuery -> " + e.getMessage());
        }

        return SUCCESS;
    }

    public String loadAllQuery() throws Exception {
//        System.err.println("vao phan sửa query");
        try {
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới tải được báo cáo");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (group_id == null || group_id.isEmpty()) {
                setMessage("Bạn phải chọn nhóm báo cáo ");
                return ERROR;
            }
            DaoKt740 daoQuery = new DaoKt740();

            lstObjQuery = daoQuery.getLoadAllQuery(sUserName, group_id, "");
        } catch (Exception e) {
            setMessage("Lỗi không load được báo cáo "+e.getMessage());
            return ERROR;
        }

        return SUCCESS;
    }

    public String loadAllQueryExp() throws Exception {
//        System.err.println("vao phan sửa query");

        Map session = ActionContext.getContext().getSession();

        if (session == null || session.size() == 0 || session.isEmpty()) {
            setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
            return ERROR;
        }

        String reportGrade = session.get("reportGrade").toString();

        if (reportGrade == null || reportGrade.isEmpty()) {
            setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
            return ERROR;
        }
        DaoKt740 daoQuery = new DaoKt740();
        String sUserName = session.get("username").toString();

        lstObjGroup = daoQuery.getGroupQuery(sUserName);
        lstObjQuery = daoQuery.getLoadAllQuery(sUserName, group_id, reportGrade);
        return SUCCESS;
    }

    public String DeleteQuery() throws Exception {

        Map session = ActionContext.getContext().getSession();
        String sUserName = session.get("username").toString();
        if (session == null) {
            setMessage("Bạn phải logout ra và login lai thì mới xóa được báo cáo!");
            return ERROR;
        }
        DaoKt740 daoQuery = new DaoKt740();
        String sUserCreaterpt = daoQuery.getUserCreateReport(save_id);
        if (!sUserCreaterpt.toLowerCase().equals(sUserName.toLowerCase())) {

            setMessage("Bạn chưa xóa được báo cáo, Do bạn không phải là người tạo báo cáo này! Người tạo báo cáo là: " + sUserCreaterpt);
            return ERROR;

        }
        boolean bSuccess = daoQuery.deleteReportQuery(save_id);
        if (!bSuccess) {
            setMessage("Bạn phải chưa xóa được truy vấn!");
            return ERROR;
        }
        setMessage("Xóa truy vấn thành công!");
        return SUCCESS;
    }

    public String getReportParams() throws Exception {
//        System.err.println("vao phan load tham so " + save_id);
        //Lay username
//        HttpSession session = request.getSession();
        //String strUserName="hagiangth";//session.getAttribute("username").toString();
        Map session = ActionContext.getContext().getSession();
        String strUserName = session.get("username").toString();
        DaoKt740 daoQuery = new DaoKt740();

        reportParamsList = daoQuery.getReportParmams(save_id, strUserName);

        return "success";
    }

    public String getReportParamsView() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        query = request.getParameter("query");

        if (query == null || query.isEmpty()) {
            setMessage("Không lấy ra được truy vấn nên không thể view báo cáo ");
            return ERROR;
        }
//        System.err.println("vao action nay roi " + query);
//        System.err.println("vao phan load tham so " + save_id);
        //Lay username
//        HttpSession session = request.getSession();
        //String strUserName="hagiangth";//session.getAttribute("username").toString();
        //03-01-2016 tungnv sua khong cho view bao cao bang procedure
        ImsPlSqlQuery plsql = new ImsPlSqlQuery();
        if (plsql.isOracleStoredProcedure(query)) {
            setMessage("Không thể view báo cáo bằng procedure ");
            return ERROR;
        }
        Map session = ActionContext.getContext().getSession();
        String strUserName = session.get("username").toString();
        DaoKt740 daoQuery = new DaoKt740();

        reportParamsList = daoQuery.getReportViewParmams(query, strUserName);

        return "success";
    }

    public String ExportExcelQuery() throws Exception {
//        System.err.println("vao ham export report ");

        if (save_id == null | save_id.isEmpty()) {
            setMessage("Bạn chưa chọn mẫu báo cáo nên không thể tạo báo cáo ");
            return ERROR;
        }

        HashMap<String, String> paramHashMap = new HashMap<>();

        Map<String, String[]> prameters = request.getParameterMap();
        String sPos_cd = "";
        String stringParaPos_cd = "";
        String sPosFlag = "";

        Map mapCollectPara = new HashMap();
        //xy lay lay cac tham so cho vao hashmap
        for (String parameter : prameters.keySet()) {
            String[] values = prameters.get(parameter);
            //Do neu parameter kieu date thi he thong se sinh them control dojo.date
            //   nen minh can phai loai bo tham so nay di
            if (parameter.indexOf("TEXT") > 0 || parameter.indexOf("DATE") > 0
                    || parameter.indexOf("LIST") > 0 || parameter.indexOf("NUMB") > 0) {
                if (parameter.indexOf("DATE") > 0) {
                    Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
                    paramHashMap.put(parameter.substring(0, parameter.length() - 5), new SimpleDateFormat("dd-MMM-yyyy").format(sdf));

                    mapCollectPara.put(parameter.substring(0, parameter.length() - 5),
                            ImsFillParaMeter.newInstance("VARCHAR2", new SimpleDateFormat("dd-MMM-yyyy").format(sdf)));
                    //System.err.println( parameter.substring(0, parameter.length() - 5)+" Tham so: "+new SimpleDateFormat("dd-MMM-yyyy").format(sdf));
                } else if (parameter.indexOf("NUMB") > 0) {
                    if (parameter.indexOf("_NUMB") > 0) {
                        paramHashMap.put(parameter.substring(0, parameter.length() - 5), values[0]);

                        mapCollectPara.put(parameter.substring(0, parameter.length() - 5),
                                ImsFillParaMeter.newInstance("NUMBER", values[0]));
                    }
                } else {
                    if (parameter.indexOf("_MAPGD") > 0) {
                        sPos_cd = values[0].trim();
                        stringParaPos_cd = parameter.substring(0, parameter.length() - 11);

                        mapCollectPara.put(parameter.substring(0, parameter.length() - 11),
                                ImsFillParaMeter.newInstance("VARCHAR2", values[0]));
                    } else if (parameter.indexOf("_TONGHOP") > 0) {
                        sPosFlag = values[0].trim();
                        mapCollectPara.put(parameter.substring(0, parameter.length() - 5),
                                ImsFillParaMeter.newInstance("VARCHAR2", values[0]));
                    } else {
                        paramHashMap.put(parameter.substring(0, parameter.length() - 5), values[0]);
                        mapCollectPara.put(parameter.substring(0, parameter.length() - 5),
                                ImsFillParaMeter.newInstance("VARCHAR2", values[0]));
                    }
                    //System.err.println( parameter.substring(0, parameter.length() - 5)+" Tham so: "+values[0]);
                }
            }
        }

        //xu ly cho export file ra PDF hoac la Excel
        String strCurrDate = new SimpleDateFormat("ddMMyyyy").format(new Date());
        //duong dan chua file tren o dia + Define.M_REPORT_XLS
        String strPathSave = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");
        //Ham nay lay ra ten file bao cao can tao, ten file jasper report

        String strTimeFile = Long.toString(System.currentTimeMillis());
        String strFileSave = save_id + "_"
                + "_" + strCurrDate
                + "_" + strTimeFile.substring(strTimeFile.length() - 4, strTimeFile.length());

        strPathSave += Define.M_REPORT_XLS;
        strFileSave += ".XLSX";
        filereport = strFileSave;
        File Checkpath = new File(strPathSave);
        if (!Checkpath.exists()) {
            System.out.println("Da tao thu muc: " + strPathSave);
            Checkpath.mkdirs();
        }

        //Xuat file du lieu o day
        DaoKt740 daoQuery = new DaoKt740();
        setQuery(daoQuery.getQuery(save_id, new DaoConnect().getConnect()));
        ImsPlSqlQuery plsql = new ImsPlSqlQuery();
        if (plsql.isOracleStoredProcedure(query)) { //neu la procedure thi chay rieng
            daoQuery.exportExcelQueryPlSql(mapCollectPara, save_id, strPathSave + strFileSave);
        } else {
            if (sPos_cd.isEmpty() || stringParaPos_cd.isEmpty()) {
                daoQuery.exportExcelQuery(paramHashMap, save_id, strPathSave + strFileSave);
            } else {
                daoQuery.getDataExp(save_id, paramHashMap, sPos_cd, stringParaPos_cd, sPosFlag, strPathSave + strFileSave);
            }
        }

        //Kiem tra xem file da tao thanh cong chua
        File filerpt = new File(strPathSave + strFileSave);
        if (!filerpt.exists()) {
            setMessage("Lỗi bạn chưa tạo được file báo cáo " + strFileSave);
            return ERROR;
        }
        fileNamelocal = strPathSave + strFileSave;
        System.gc();
        return SUCCESS;
    }

    public String ViewDataQuery() {
        try {
//            System.err.println("vao ham export report " + query);

            if (query == null | query.isEmpty()) {
                setMessage("Bạn chưa điền query trên form add query nên không thể tạo báo cáo ");
                return ERROR;
            }

            HashMap<String, String> paramHashMap = new HashMap<>();

            Map<String, String[]> prameters = request.getParameterMap();

            //CuongBM: xy lay lay cac tham so cho vao hashmap
            for (String parameter : prameters.keySet()) {
                String[] values = prameters.get(parameter);
//            if (parameter.startsWith("dojo")) {
//                continue;
//            }
                //Tungnv
                //Do neu parameter kieu date thi he thong se sinh them control dojo.date
                //   nen minh can phai loai bo tham so nay di
                if (parameter.indexOf("TEXT") > 0 || parameter.indexOf("DATE") > 0 || parameter.indexOf("LIST") > 0) {
                    if (parameter.indexOf("DATE") > 0) {
                        Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
                        paramHashMap.put(parameter.substring(0, parameter.length() - 5), new SimpleDateFormat("dd-MMM-yyyy").format(sdf));
                        //System.err.println( parameter.substring(0, parameter.length() - 5)+" Tham so: "+new SimpleDateFormat("dd-MMM-yyyy").format(sdf));
                    } else {
                        paramHashMap.put(parameter.substring(0, parameter.length() - 5), values[0]);
                        //System.err.println( parameter.substring(0, parameter.length() - 5)+" Tham so: "+values[0]);
                    }
                }
            }

            //Xuat file du lieu o day
            DaoKt740 daoQuery = new DaoKt740();

            lstObjQuery = daoQuery.ViewReportQuery(paramHashMap, title, query);
        } catch (SQLException ex) {
            Logger.getLogger(Kt740Action.class.getName()).log(Level.SEVERE, null, ex);
            setMessage("Lỗi xảy ra: " + ex.getMessage());
            return ERROR;
        } catch (ParseException ex) {
            Logger.getLogger(Kt740Action.class.getName()).log(Level.SEVERE, null, ex);
            setMessage("Lỗi xảy ra: " + ex.getMessage());
            return ERROR;
        }

        System.gc();
        return SUCCESS;
    }

    private List<String> convertStringtoList(String[] value) {
        List<String> lst = new ArrayList<>();
        try {
            for (int i = 0; i < value.length; i++) {
                if (!value[i].equals("999999") && !value[i].isEmpty()) {
                    lst.add(value[i]);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> convertStringtoList: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> convertStringtoList: " + e.getMessage());
        }
        return lst;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(String grade_id) {
        this.grade_id = grade_id;
    }

    public List<ListValue> getLstObjGroup() {
        return lstObjGroup;
    }

    public void setLstObjGroup(List<ListValue> lstObjGroup) {
        this.lstObjGroup = lstObjGroup;
    }

    public List<ReportParam> getReportParamsList() {
        return reportParamsList;
    }

    public void setReportParamsList(List<ReportParam> reportParamsList) {
        this.reportParamsList = reportParamsList;
    }

    public String getSave_id() {
        return save_id;
    }

    public void setSave_id(String save_id) {
        this.save_id = save_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<ListValue> getLstObjQuery() {
        return lstObjQuery;
    }

    public void setLstObjQuery(List<ListValue> lstObjQuery) {
        this.lstObjQuery = lstObjQuery;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public List<ListValue> getLstParaDesc() {
        return lstParaDesc;
    }

    public void setLstParaDesc(List<ListValue> lstParaDesc) {
        this.lstParaDesc = lstParaDesc;
    }

    public List<ListValue> getLstGrade() {
        return lstGrade;
    }

    public void setLstGrade(List<ListValue> lstGrade) {
        this.lstGrade = lstGrade;
    }

    public String[] getDefaultGrade() {
        return defaultGrade;
    }

    public void setDefaultGrade(String[] defaultGrade) {
        this.defaultGrade = defaultGrade;
    }

    public List<String> getRptGrade() {
        return rptGrade;
    }

    public void setRptGrade(List<String> rptGrade) {
        this.rptGrade = rptGrade;
    }
//</editor-fold>

}
