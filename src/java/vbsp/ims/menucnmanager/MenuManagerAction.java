/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.menucnmanager;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.SUCCESS;
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
public class MenuManagerAction extends ActionSupport
        implements ServletRequestAware {

    //private static final long serialVersionUID = 6518221459701336965L;

    private TreeNode nodes;
    HttpServletRequest request;
    private List<MenucnItem> menus = new ArrayList<>();
    
    private MenucnItem menu = new MenucnItem();    
    private String message;
    private String action_type;
    
    private List<MenuGroup> groups = new ArrayList<>();
    private String menuId;
    private String groupsStr;
    private String userName;
    private String selectedGroup;
    
    private List<ListValue> rptGroups = new ArrayList<>();
    
    //------------------------------------------------------------------------------------------
    public String buildMenuTree()
            throws Exception {
        if (request.getSession().getAttribute("startTreeGLKHTDRecursive") != null) {
            return SUCCESS;
        } else {
            int rptgrade = Integer.parseInt(
                    request.getSession().getAttribute("reportGrade").toString()
            );
            userName = request.getSession().getAttribute("username").toString();
            MenuManagerDao dao = new MenuManagerDao(rptgrade, userName);
            dao.build();
            this.nodes = dao.getNodes();
            request.getSession().setAttribute("startTreeGLKHTDRecursive", "false");
            return SUCCESS;
        }
    }

    public String listmenu() {
        try {
        int rptgrade = Integer.parseInt(
                request.getSession().getAttribute("reportGrade").toString()
        );
        userName = request.getSession().getAttribute("username").toString();
        MenuManagerDao dao = new MenuManagerDao(rptgrade, userName);
        menus = dao.listmenu();
        } catch(Exception e){
            System.err.println("Loi listmenu " + e.getMessage());  
        }
        return SUCCESS;
    }
    
    
    public String check_id(){
        System.err.println("Vao ham kiem tra menuid...");
        if (menu == null)
            menu = new MenucnItem();
        MenuManagerDao dao = new MenuManagerDao();
        menu = dao.getSuggestMenu();
        return SUCCESS;
    }

    
    public String update(){
        System.err.println("Vao ham update menuid...~"+menu.getMenuId()+"~"+selectedGroup);
        MenuManagerDao dao = new MenuManagerDao();      
        userName = request.getSession().getAttribute("username").toString();
        message = dao.save_Data(action_type, 
                String.valueOf(menu.getMenuId()) , 
                menu.getText(), 
                menu.getNavigateUrl(),
                String.valueOf(menu.getParentId()),
                userName,
                selectedGroup);        
//        (String pv_type,String pv_menuId,String pv_text,String pv_Url,
//            String pv_parentId,String pv_username)
//        message = "test~" + action_type + "~" + groupsStr;
        return SUCCESS;
    }
    
    
    public String save_data_temp(){
        System.err.println("save_data_temp~"+menuId+"~"+userName+"~"+groupsStr);        
        MenuManagerDao dao = new MenuManagerDao();
        boolean isUpdated = dao.save_DataTemp(menuId, userName, groupsStr);
        if (isUpdated)
//            message = "<xanh> save_data_temp~"+menuId+"~"+userName+"~"+groupsStr+"~thành công <xanh>";
        message = "<xanh> Dữ liệu đã được lưu vào bảng tạm, sau khi thoát màn hình bạn nên chọn lưu trữ để hoàn tất việc lưu dữ liệu. <xanh>";
        else
//            message = "<do> save_data_temp~"+menuId+"~"+userName+"~"+groupsStr+"~thất bại <do>";
            message = "<do> Lưu trữ thất bại. Bạn hãy liên hệ với quản trị viên để được hỗ trợ. <do>";
        return SUCCESS;
    }
    
    public String get_pri_list(){
        userName = request.getSession().getAttribute("username").toString();
        MenuManagerDao dao = new MenuManagerDao();
        System.err.println("get_pri_list~"+userName+"~"+menuId);
        groups = dao.get_Menugroup(userName,menuId);
        return SUCCESS;
    }
            
    //------------------------------------------------------------------------------------------

    public TreeNode getNodes() {
        return nodes;
    }

    public void setNodes(TreeNode nodes) {
        this.nodes = nodes;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public List<MenucnItem> getMenus() {
        return menus;
    }

    public void setMenus(List<MenucnItem> menus) {
        this.menus = menus;
    }

    public MenucnItem getMenu() {
        return menu;
    }

    public void setMenu(MenucnItem menu) {
        this.menu = menu;
    }

    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    public List<MenuGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<MenuGroup> groups) {
        this.groups = groups;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getGroupsStr() {
        return groupsStr;
    }

    public void setGroupsStr(String groupsStr) {
        this.groupsStr = groupsStr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ListValue> getRptGroups() {
        return rptGroups;
    }

    public void setRptGroups(List<ListValue> rptGroups) {
        this.rptGroups = rptGroups;
    }

    public String getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(String selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

   

}
