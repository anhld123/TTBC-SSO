/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Trung
 */
public class BuildPosTreeDao {

    private TreeNode nodes;
    private int reportGrade;
    private String userName;

    public BuildPosTreeDao() {
        nodes = new TreeNode();
    }

    public BuildPosTreeDao(int reportGrade, String userName)
            throws SQLException {
        this.reportGrade = reportGrade;
        this.userName = userName;
        //build();
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

    public void build() throws SQLException {
        Connection connect;
        CallableStatement calstatement = null;
        ResultSet result = null;
        try {
            connect = new DaoConnect().getConnect();
            
            String strStoreproce
                    = "{call app_priv_view.p_get_priv_4balance(?, ?, ?)}";
            calstatement = connect.prepareCall(strStoreproce,
                                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                            calstatement.setString(1, String.valueOf(reportGrade));
                            calstatement.setString(2, userName);                            
                            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                            calstatement.execute();
            String lcMa, lcMota;
            int nodeId, parentId;
            result = (ResultSet) calstatement.getObject(3);
            if (reportGrade == -1) {

            } else {
                while (result.next()) {
                    lcMa = result.getString("POS_CODE");
                    lcMota = result.getString("NAME");
                    nodeId = result.getInt("NODEID");
                    parentId = result.getInt("PARENTID");
                    if (this.nodes == null) {
                        this.nodes = new TreeNode();
                        this.nodes.setId(String.valueOf(nodeId));
                        Map data = new HashMap();
                        data.put("Pos_Code", lcMa);
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
                            data.put("Pos_Code", lcMa);
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
                                    data.put("Pos_Code", lcMa);
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
            System.err.println("Loi " + e.getMessage());
        } finally {
            try {
                if (calstatement != null) {
                    calstatement.close();
                }
                if (result != null) {
                    result.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi " + e.getMessage());
            }
        }
    }
    
    
    public void build_vb96() throws SQLException {
        Connection connect;
        CallableStatement calstatement = null;
        ResultSet result = null;
        try {
            connect = new DaoConnect().getConnect();
            
            String strStoreproce
                    = "{call sp_get_pos_tree_vb96(?, ?, ?)}";
            calstatement = connect.prepareCall(strStoreproce,
                                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                            calstatement.setString(1, String.valueOf(reportGrade));
                            calstatement.setString(2, userName);                            
                            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                            calstatement.execute();
            String lcMa, lcMota;
            int nodeId, parentId;
            result = (ResultSet) calstatement.getObject(3);
            if (reportGrade == -1) {

            } else {
                while (result.next()) {
                    lcMa = result.getString("POS_CODE");
                    lcMota = result.getString("NAME");
                    nodeId = result.getInt("NODEID");
                    parentId = result.getInt("PARENTID");
                    if (this.nodes == null) {
                        this.nodes = new TreeNode();
                        this.nodes.setId(String.valueOf(nodeId));
                        Map data = new HashMap();
                        data.put("Pos_Code", lcMa);
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
                            data.put("Pos_Code", lcMa);
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
                                    data.put("Pos_Code", lcMa);
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
            System.err.println("Loi " + e.getMessage());
        } finally {
            try {
                if (calstatement != null) {
                    calstatement.close();
                }
                if (result != null) {
                    result.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi " + e.getMessage());
            }
        }
    }

}
