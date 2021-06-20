/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.fastreportconfig;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class FastReportConfigAction extends ActionSupport
        implements ServletRequestAware {

    private List<ListValue> modules = new ArrayList<>();
    private List<ListValue> group_modules = new ArrayList<>();
    private List<ListValue> group_apply_regions = new ArrayList<>();
    private List<ListValue> paraQueryTypes = new ArrayList<>();

    private List<GroupQuery> groupQueries = new ArrayList<>();
//    private List<ParaRptQuery> paraRptQueries = new ArrayList<>();
    private String module;
    private String groupId;
    private String applyRegion;
    private String groupsStr;
    private String userName;
    private GroupQuery groupQuery ;//= new GroupQuery();
//    private ParaRptQuery paraRptQuery = new ParaRptQuery();
    private String message;
    private String action_type;
    
    HttpServletRequest request;

    private static final long serialVersionUID = 5078264277068533596L;
    
    private List<QueryGroup> groups = new ArrayList<>();

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String list_modules() {
        modules.add(new ListValue("GROUP", "Nhóm báo cáo (GROUP_QUERY)"));
        modules.add(new ListValue("PARAM", "Tham số (PARA_RPT_QUERY)"));
        return SUCCESS;
    }

    public String build_group_combo() {
        group_modules.add(new ListValue("QRY", "Query"));
        group_modules.add(new ListValue("EXCEL", "Excel"));

        group_apply_regions.add(new ListValue("LOCAL", "Chi nhánh"));
        group_apply_regions.add(new ListValue("GLOBAL", "Toàn quốc"));

        paraQueryTypes.add(new ListValue("L", "Danh sách chọn"));
        paraQueryTypes.add(new ListValue("D", "Chọn ngày"));
        paraQueryTypes.add(new ListValue("T", "Ô nhập"));
        
        return SUCCESS;
    }
        
    public String get_pri_list(){
        userName = request.getSession().getAttribute("username").toString();
        System.err.println("get_pri_list~"+userName+"~"+module+groupId+applyRegion);
        
        FastReportConfigDao dao = new FastReportConfigDao();        
        groups = dao.get_QueryGroup(userName, groupId, module, applyRegion);
        return SUCCESS;
    }

    public String save_data_temp(){
        System.err.println("save_data_temp~"+module+"~"+applyRegion+"~"+groupId+ "~"+userName+"~"+groupsStr);        
        FastReportConfigDao dao = new FastReportConfigDao();
        boolean isUpdated = dao.save_DataTemp(module,applyRegion,groupId, userName, groupsStr);
        if (isUpdated)
            message = "<xanh>Chú ý: Dữ liệu đã được lưu vào bảng tạm, sau khi thoát màn hình bạn phải chọn lưu trữ để hoàn tất việc lưu dữ liệu. <xanh>";
        else
            message = "<do> Lưu trữ thất bại. Bạn hãy liên hệ với quản trị viên để được hỗ trợ. <do>";
        return SUCCESS;
    }
    
    public String display() {
        System.err.println("display" + module);
        if (module.equals("GROUP")) {
            return SUCCESS;
        } else {
            return INPUT;
        }
    }

    public String listGroupQuery() {
        int lLogGrade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
        String lUserName = request.getSession().getAttribute("username").toString();
        FastReportConfigDao frptcDao = new FastReportConfigDao();
        System.err.println("listGroupQuery" + lUserName + "~" + lLogGrade + "~");
        groupQueries = frptcDao.listGroupQuery(lUserName, lLogGrade);
        return SUCCESS;
    }

//    public String listParaRptQueries(){
//        int lLogGrade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
//        String lUserName = request.getSession().getAttribute("username").toString();
//        FastReportConfigDao frptcDao = new FastReportConfigDao();
//        System.err.println("listParaRptQueries" + lUserName + "~" + lLogGrade + "~");
//        paraRptQueries = frptcDao.listVariables(lUserName, lLogGrade);
//        return SUCCESS;
//    }
    
    public String get_NewCode(){
        if (groupQuery != null){
        System.err.println("get_NewCode~"
                +groupQuery.getGroupQueryPK().getModule() 
                + "~" 
                + groupQuery.getGroupQueryPK().getApplyRegion());
            FastReportConfigDao frcDao = new FastReportConfigDao();
            String lnewCode = frcDao.getNewCode(groupQuery.getGroupQueryPK().getModule(),
                    groupQuery.getGroupQueryPK().getApplyRegion());
            System.err.println("lnewCode~"+lnewCode);
            groupQuery.getGroupQueryPK().setGroupId(lnewCode);        
        } else
        {
                System.err.println("get_NewCode~null");
            groupQuery = new GroupQuery(new GroupQueryPK("", "QRY", "GLOBAL"));                    
        }        
        return SUCCESS;
    }
    
    public String update(){
        String userName = request.getSession().getAttribute("username").toString();
        System.err.println("update~"+userName+action_type+"~"
                +groupQuery.getGroupQueryPK().getGroupId()+"~"
                +groupQuery.getGroupQueryPK().getModule()+ "~" 
                +groupQuery.getGroupQueryPK().getApplyRegion());
        if (userName == null || userName.trim().equals(""))
            userName = "ADMIN";
        
        FastReportConfigDao frpDao = new FastReportConfigDao();        
        String actionMessage = frpDao.update_group( 
                groupQuery.getGroupQueryPK().getGroupId()                ,
                groupQuery.getGroupQueryPK().getModule(), 
                groupQuery.getGroupQueryPK().getApplyRegion(), 
                groupQuery.getGroupDesc(), 
                userName,
                action_type);
        if (actionMessage.equals("ERROR"))
            message = "Cập nhật thất bại.";
        else
            message = "Cập nhật thành công.";
        return SUCCESS;
    }
    
//    public String update_ParaKey(){
//        String userName = request.getSession().getAttribute("username").toString();
//        System.err.println("update~"+userName+action_type+"~"
//                +paraRptQuery.getParaKey()+"~"
//                +paraRptQuery.getParaDesc()+ "~" 
//                +paraRptQuery.getParaType());
//        if (userName == null || userName.trim().equals(""))
//            userName = "ADMIN";
//        
//        FastReportConfigDao frpDao = new FastReportConfigDao();        
//        String actionMessage = 
//                frpDao.update_Para(paraRptQuery, userName, action_type);                
//        if (actionMessage.equals("ERROR"))
//            message = "Cập nhật thất bại.";
//        else
//            message = "Cập nhật thành công.";
//        return SUCCESS;
//    }
    
    //<editor-fold defaultstate="collapsed" desc="Khai bao cac bien">
    public List<ListValue> getModules() {
        return modules;
    }

    public void setModules(List<ListValue> modules) {
        this.modules = modules;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public List<ListValue> getGroup_modules() {
        return group_modules;
    }

    public void setGroup_modules(List<ListValue> group_modules) {
        this.group_modules = group_modules;
    }

    public List<ListValue> getGroup_apply_regions() {
        return group_apply_regions;
    }

    public void setGroup_apply_regions(List<ListValue> group_apply_regions) {
        this.group_apply_regions = group_apply_regions;
    }

    public List<GroupQuery> getGroupQueries() {
        return groupQueries;
    }

    public void setGroupQueries(List<GroupQuery> groupQueries) {
        this.groupQueries = groupQueries;
    }

    public GroupQuery getGroupQuery() {
        return groupQuery;
    }

    public void setGroupQuery(GroupQuery groupQuery) {
        this.groupQuery = groupQuery;
    }

    public List<QueryGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<QueryGroup> groups) {
        this.groups = groups;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getApplyRegion() {
        return applyRegion;
    }

    public void setApplyRegion(String applyRegion) {
        this.applyRegion = applyRegion;
    }

    public String getGroupsStr() {
        return groupsStr;
    }

    public void setGroupsStr(String groupsStr) {
        this.groupsStr = groupsStr;
    }

    
    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    
//    public List<ParaRptQuery> getParaRptQueries() {
//        return paraRptQueries;
//    }
//
//    public void setParaRptQueries(List<ParaRptQuery> paraRptQueries) {
//        this.paraRptQueries = paraRptQueries;
//    }
//
//    public ParaRptQuery getParaRptQuery() {
//        return paraRptQuery;
//    }
//
//    public void setParaRptQuery(ParaRptQuery paraRptQuery) {
//        this.paraRptQuery = paraRptQuery;
//    }
            

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    

    public List<ListValue> getParaQueryTypes() {
        return paraQueryTypes;
    }

    public void setParaQueryTypes(List<ListValue> paraQueryTypes) {
        this.paraQueryTypes = paraQueryTypes;
    }
    //</editor-fold>
}
