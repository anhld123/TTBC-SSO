/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.menucnmanager;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import vbsp.ims.dao.DaoConnect;

/**
 *
 * @author Trung
 */
public class MenuManagerDao {

    private TreeNode nodes;
    private int reportGrade;
    private String userName;

    Connection connect;

    public MenuManagerDao() {
        
            connect = new DaoConnect().getConnect();
        
    }

    public MenuManagerDao(int reportGrade, String userName) {
        
        connect = new DaoConnect().getConnect();
        
        this.reportGrade = reportGrade;
        this.userName = userName;
    }

    public List<MenucnItem> listmenu() {
        ArrayList<MenucnItem> menulist = new ArrayList<>();
        CallableStatement calstatement = null;
        ResultSet result = null;
        try {
            
            String strStoreproce
                    = "{call rpt_menucnmanager.p_get_menuview(?, ?, ?)}";
            
            calstatement = connect.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, String.valueOf(reportGrade));
            calstatement.setString(2, userName);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            String lcMota, lcUrl, lcReportGroup;
            int nodeId, parentId;
            result = (ResultSet) calstatement.getObject(3);
            
            if (reportGrade != -1) {           
                while (result.next()) {
                    lcUrl = result.getString("URL");
                    lcMota = result.getString("NODETEXT");
                    nodeId = result.getInt("NODEID");
                    parentId = result.getInt("PARENTID");
                    lcReportGroup = result.getString("REPORTGROUP");
                    menulist.add(new MenucnItem(nodeId, lcMota, lcMota, parentId, lcUrl, parentId, lcReportGroup));
                }
            }
        } catch (Exception e) {
            System.err.println("Loi listmenu" + e.getMessage());
           try {
            if (calstatement != null) {
                    calstatement.close();
                }
                if (result != null) {
                    result.close();
                }
            }catch(SQLException sqlException) {
                System.err.println("Close connection " + sqlException.getMessage());  
            }
        } 
        return menulist;
    }

    public TreeNode getNodes() {
        return nodes;
    }

    public void setNodes(TreeNode nodes) {
        this.nodes = nodes;
    }

    public int getReportGrade() {
        return reportGrade;
    }

    public void setReportGrade(int reportGrade) {
        this.reportGrade = reportGrade;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void build() {
        CallableStatement calstatement = null;
        ResultSet result = null;
        try {           
            String strStoreproce
                    = "{call rpt_menucnmanager.p_get_menuview_tree(?, ?, ?)}";
            calstatement = connect.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            calstatement.setString(1, String.valueOf(reportGrade));
            calstatement.setString(2, userName);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            
            String lcMota, lcUrl;
            int nodeId, parentId;
            
            result = (ResultSet) calstatement.getObject(3);
            if (reportGrade != -1) {            
                while (result.next()) {
                    lcUrl = result.getString("URL");
                    lcMota = result.getString("NODETEXT");
                    nodeId = result.getInt("NODEID");
                    parentId = result.getInt("PARENTID");
                    if (this.nodes == null) {
                        this.nodes = new TreeNode();
                        this.nodes.setId(String.valueOf(nodeId));
                        Map data = new HashMap();
                        data.put("Url", lcUrl);
                        data.put("ParentId", parentId);
                        this.nodes.setData(data);
                        this.nodes.setTitle(lcMota);
                        this.nodes.setState("open");
                        this.nodes.setChildren(new LinkedList<TreeNode>());
                    } else {
                        if (parentId == Integer.parseInt(this.nodes.getId())) {
                            TreeNode nodeChild = new TreeNode();
                            nodeChild.setId(String.valueOf(nodeId));
                            Map data = new HashMap();
                            data.put("Url", lcUrl);
                            data.put("ParentId", parentId);
                            nodeChild.setData(data);
                            nodeChild.setTitle(lcMota);
                            this.nodes.getChildren().add(nodeChild);
                        } else {
                            java.util.Iterator<TreeNode> nodeChilds = this.nodes.getChildren().iterator();
                            while (nodeChilds.hasNext()) {
                                TreeNode child = nodeChilds.next();
                                if (parentId == Integer.parseInt(child.getId())) {
                                    if (child.getChildren() == null) {
                                        child.setChildren(new LinkedList<TreeNode>());
                                    }
                                    TreeNode nodeChildChild = new TreeNode();
                                    nodeChildChild.setId(String.valueOf(nodeId));
                                    Map data = new HashMap();
                                    data.put("Url", lcUrl);
                                    data.put("ParentId", parentId);
                                    nodeChildChild.setData(data);
                                    nodeChildChild.setTitle(lcMota);
                                    child.getChildren().add(nodeChildChild);
                                }
                            }
                        }
                    }

                }
            }
        } catch (Exception e) {
            System.err.println("Loi build" + e.getMessage());
            try {
            if (calstatement != null) {
                    calstatement.close();
                }
                if (result != null) {
                    result.close();
                }
            }catch(SQLException sqlException) {
                System.err.println("Close connection " + sqlException.getMessage());  
            }
        } 
    }

    public MenucnItem getSuggestMenu() {
        
        MenucnItem newMenu = new MenucnItem();
        
        CallableStatement calstatement = null;
        
        try {            
            String strStoreproce
                    = "{call rpt_menucnmanager.p_get_nextmenuid(?)}";
            calstatement = connect.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            calstatement.execute();
            int newMenuId = (int) calstatement.getObject(1);
            newMenu.setMenuId(newMenuId);
        } catch (Exception e) {
            System.err.println("Loi getSuggestMenu " + e.getMessage());
            try {
            if (calstatement != null) {
                    calstatement.close();
                }                
            }catch(SQLException sqlException) {
                System.err.println("Close connection " + sqlException.getMessage());  
            }
        } 
        return newMenu;
    }

    public List<MenuGroup> get_Menugroup(String pv_username,String pv_menuid) {

        ArrayList<MenuGroup> groups = new ArrayList<>();
        CallableStatement calstatement = null;
        ResultSet result = null;
        try {            
            String strStoreproce
                    = "{call rpt_menucnmanager.p_get_menupri(?, ?, ?)}";
            calstatement = connect.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, pv_menuid);
            calstatement.setString(2, pv_username);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            String lcGroupId, lcDescript, lcShortcut, lcPrivileage;
            int lnmenuId = Integer.parseInt(pv_menuid);
            result = (ResultSet) calstatement.getObject(3);

            while (result.next()) {
                lcGroupId = result.getString("GROUP_ID");
                lcDescript = result.getString("DESCRIPT");
                lcShortcut = result.getString("SHORTCUT");
                lcPrivileage = result.getString("PRIVILEAGE");
                groups.add(
                        new MenuGroup(lnmenuId, lcGroupId, lcDescript, lcShortcut, lcPrivileage)
                );
            }

        } catch (Exception e) {
            System.err.println("Loi get_Menugroup" + e.getMessage());
            try {
            if (calstatement != null) {
                    calstatement.close();
                }
                if (result != null) {
                    result.close();
                }
            }catch(SQLException sqlException) {
                System.err.println("Close connection " + sqlException.getMessage());  
            }
        } 
        return groups;
    }
    
    public boolean save_DataTemp(String pv_menuId,String pv_username, String pv_groupStr){        
        CallableStatement calstatement = null;        
        try {
            
            String strStoreproce
                    = "{call rpt_menucnmanager.p_save_data_temp(?,?,?)}";
            calstatement = connect.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, pv_menuId);
            calstatement.setString(2, pv_username);
            calstatement.setString(3, pv_groupStr);
            calstatement.executeUpdate();            
        } catch (Exception e) {
            System.err.println("Loi save_DataTemp " + e.getMessage());
            try {
            if (calstatement != null) {
                    calstatement.close();
                }                
            }catch(SQLException sqlException) {
                System.err.println("Close connection " + sqlException.getMessage());  
            }
            return false;
        }       
        return true;
    }
    
    
    public String save_Data(String pv_type,String pv_menuId,String pv_text,String pv_Url,
            String pv_parentId,String pv_username,String pv_reportGroup){     
        String message="";
        CallableStatement calstatement = null;        
        try {
           
            String strStoreproce
                    = "{call rpt_menucnmanager.p_save_data(?,?,?,?,?,?,?,?)}";
            calstatement = connect.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, pv_type);
            calstatement.setString(2, pv_menuId);
            calstatement.setString(3, pv_text);
            calstatement.setString(4, pv_Url);
            calstatement.setString(5, pv_parentId);
            calstatement.setString(6, pv_username);
            calstatement.setString(7, pv_reportGroup);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();        
            message = (String)calstatement.getObject(8);
        } catch (Exception e) {
            System.err.println("Loi save_Data " + e.getMessage());  
            try {
            if (calstatement != null) {
                    calstatement.close();
                }                
            }catch(SQLException sqlException) {
                System.err.println("Close connection " + sqlException.getMessage());  
            }
        }     
        return message;
    }
}
