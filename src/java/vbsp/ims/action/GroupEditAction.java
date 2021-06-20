/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.IMSRptDao;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.Group;
import vbsp.ims.model.GroupManager;
import vbsp.ims.model.RefObject;
import vbsp.ims.syn.ProcessReportSyn;
//import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class GroupEditAction extends ActionSupport
        implements ServletRequestAware {

    //--------------------------------------------------------------------------
    private static final long serialVersionUID = 5078264277068533593L;
    private HttpServletRequest request;
    //--------------------------------------------------------------------------

    private Integer rows = 0;
    private Integer page = 0;
    private String sord;
    private String sidx;
    // Search Field
    private String searchField;
    private String searchString;
    // ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
    private String searchOper;
    private Integer total = 0;
    private Integer records = 0;

    private List<Group> groups = new ArrayList<>();

//    private List<ListValue> mass_org_list;

    static GroupManager groupManager;

    static String session_id;

    private int id;
    private String oper;

    static int view_page = 1;
    static int view_rows = 15;

    private String standard_flg;
    private String corrupt_flg;

    private String message;

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() {

        System.err.println("Vao chuc nang thong tin danh muc to");
        
        int reportGrade = Integer.parseInt(
                request.getSession().getAttribute("reportGrade").toString());
        String userName = request.getSession().getAttribute("username").toString();
        String permit = request.getSession().getAttribute("permit").toString();

        if (session_id == null || session_id.isEmpty()) {
            session_id = (String) request.getSession().getId();
        }

        
        String newsession_id = (String) request.getSession().getId();
        if (groupManager == null || !newsession_id.equals(session_id)) {
            System.err.println("Khoi tao thong tin danh muc to");
            groupManager = new GroupManager(userName, reportGrade);
        }

        if (oper != null && oper.equalsIgnoreCase("edit")) {
            // phan sua doi
//            if (groupManager == null) {
//                groupManager = new GroupManager(userName, reportGrade);
//            }
            Group group = groupManager.getGroup((id + (view_page - 1) * view_rows) - 1);

            System.err.println("Editing..." + group.getGroup_id() + "~" + standard_flg + "~" + corrupt_flg
            + "~" + ((id + (view_page - 1) * view_rows) - 1));

            if (standard_flg != null
                    && !standard_flg.isEmpty()) {
                group.setStandard_flg(standard_flg);
            }

            if (corrupt_flg != null
                    && !corrupt_flg.isEmpty()) {
                group.setCorrupt_flg(corrupt_flg);
            }

            groupManager.update_group(group);

            return SUCCESS;
        } else {
            if (searchOper != null && searchField != null) {
//                System.err.println("Search " + searchOper + "~" + searchField + "~" + searchString);
//                if (groupManager == null) {
//                    groupManager = new GroupManager(userName, reportGrade);
//                }
//                groups.clear();
                groups = groupManager.search(searchOper, searchField, searchString);
                view_page = page;
                view_rows = rows;
                records = groups.size();
                total = (int) Math.ceil((double) records / (double) rows);
                System.err.println("Search " + view_page + "~" + view_rows + "~" + records);
                // phan tim kiem
                return SUCCESS;
            } else {
                int to = (rows * page);
                int from = to - rows;
                view_page = page;
                view_rows = rows;
                records = groupManager.getTotalCount();
                groups = groupManager.find(from, to);
                total = (int) Math.ceil((double) records / (double) rows);
                return SUCCESS;
            }
        }
    }

    public String update_group() {
        String userName = request.getSession().getAttribute("username").toString();
        String pos_cd = IMSRptDao.getPosOfUser(userName);
        int reportGrade = Integer.parseInt(
                request.getSession().getAttribute("reportGrade").toString());

        vbsp.ims.model.RefObject refObj = new RefObject();

        String newsession_id = (String) request.getSession().getId();
        if (groupManager == null || !newsession_id.equals(session_id)) {
            groupManager = new GroupManager(userName, reportGrade);
        }
        int update_row = groupManager.commit_session_edit(pos_cd);
        boolean update_status = sync_data(pos_cd, refObj);
        if (update_status) {
            message = "(*)Tạo và cập nhật dữ liệu thành công. Số bản ghi được cập nhật [" 
                    + update_row + "]";
        } else {
            message = "(*)Tạo và cập nhật dữ liệu thất bại.(*Có thể do không có tổ "
                    + "nào được thay đổi thông tin) ";
        }
        return SUCCESS;
    }

    public String get_mass_org_descript(String key) {
        String lv_mass_org_descript = "Không xác định";
        switch(key){
            case "11":
              lv_mass_org_descript  = "Hội Nông dân";
                break;
            case "12":
              lv_mass_org_descript  = "Hội Phụ nữ";
                break;    
            case "13":
               lv_mass_org_descript  ="Hội Cựu chiến binh";
                break;
              case "14":
               lv_mass_org_descript  ="Đoàn thanh niên";
                break;  
        }       
        return lv_mass_org_descript;
    }

    //PHẦN ĐỒNG BỘ SỐ LIỆU VỀ HSC
    private boolean sync_data(String sync_pos_cd, vbsp.ims.model.RefObject refObj) {
        //DONG BO DU LIEU LEN TW        
        try {
            int reportGrade = Integer.parseInt(
                    request.getSession().getAttribute("reportGrade").toString()
            );
            String reportDate = new SimpleDateFormat("ddMMyyyy").format(new Date());            
            
            if (reportGrade != 3) {
                
                //Lay duong dan va ten xml se ghi ra
                String pathSave = !request.getRealPath("/").endsWith("/")
                        ? request.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : request.getRealPath("/") + Define.M_REPORT_XML;
                pathSave += sync_pos_cd + "_dmto_" + reportDate + ".xml";                                  
                 
//                GroupDao cmDao = new GroupDao();
                
                List<Group> lv_groups  =                         
                        groupManager.getGroupList_sync(sync_pos_cd);
                                
                if (lv_groups.size() <= 0) {
                    return false;
                }
                
                refObj.setInt01(lv_groups.size());

                //Tao file xml theo cau truc
                ProcessReportSyn clientWritexml = new ProcessReportSyn();
                boolean bSuccess = clientWritexml.SendFileXmlToWebServices_Dmto(
                        sync_pos_cd, lv_groups, pathSave);
                if (!bSuccess) {
                    //message = "Dữ liệu đồng bộ về HSC thất bại...";
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            CoreLogger.error(this.getClass().getName() + " Dữ liệu đồng bộ về HSC thất bại " + e.getMessage());
            return false;
        }        
        return true;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public static String getSession_id() {
        return session_id;
    }

    public static void setSession_id(String session_id) {
        GroupEditAction.session_id = session_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchOper() {
        return searchOper;
    }

    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    public String getStandard_flg() {
        return standard_flg;
    }

    public void setStandard_flg(String standard_flg) {
        this.standard_flg = standard_flg;
    }

    public String getCorrupt_flg() {
        return corrupt_flg;
    }

    public void setCorrupt_flg(String corrupt_flg) {
        this.corrupt_flg = corrupt_flg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String isDisable(String permit, int permit_type) {
        if (permit.substring(permit_type - 1, permit_type).equals("1")) {
            return "style='float:right; padding-top: 5px;'";
        } else {
            return "style='float:right; padding-top: 5px;display: none;'";
        }
    }

//    public List<ListValue> getMass_org_list() {
//        return mass_org_list;
//    }
//
//    public void setMass_org_list(List<ListValue> mass_org_list) {
//        this.mass_org_list = mass_org_list;
//    }
    
    
}
