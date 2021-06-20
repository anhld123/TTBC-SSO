/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.tracuu_info;

import vbsp.ims.nhaptaycn.action.*;
import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.model.DULIEU_NT;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.bcqt.model.ModelViewSend;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT.saveDulieuNT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT.saveDulieuNT_Phi;
import vbsp.ims.chamdiemcn.ModelExcelFile;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoRptQuery;
import vbsp.ims.define.Define;
import vbsp.ims.ktgs.dao.DaoKtgsMain;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.model.Pagination;
import vbsp.ims.query.ImsFillParaMeter;
import vbsp.ims.query.ImsPlSqlQuery;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.sbv.daoSbv;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.XmlKtgsSync;
//import vbsp.ims.xml.XmlNhaptaycnSync;

/**
 *
 * @author LION
 */
public class ActionTraCuuKyQuyMain extends ActionSupport {

    //<editor-fold defaultstate="collapsed" desc="Khai bao cac bien">

    
    
    protected String sodienthoai;
    protected String makh;
    protected String trangthai;
    protected String tungay;
    protected String denngay;

    

    
    public HttpServletRequest request = null;    
    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    protected String Grade;
    protected String UserName;    
    
    protected String Message;
    protected String khoa_nhaptaycn;
    protected TreeNode nodes_pos = new TreeNode();
    
    protected List<QT_DULIEU_NT> lstDulieuNt = new ArrayList<>();
   
    
    protected List<String> poscd = new ArrayList<String>();
    protected String poslist;

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getTungay() {
        return tungay;
    }

    public void setTungay(String tungay) {
        this.tungay = tungay;
    }

    public String getDenngay() {
        return denngay;
    }

    public void setDenngay(String denngay) {
        this.denngay = denngay;
    }

    protected Pagination pagination = new Pagination(50, 1);    

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Xu ly cho action">
    //<editor-fold defaultstate="collapsed" desc="Cho phan khoi tao form chinh">
    private boolean setTreeNodeGrade3(List<ModelTreeNode> lstModelTree) {

        try {
            TreeNode nodePar = new TreeNode();
            List<TreeNode> lstTree = new ArrayList<TreeNode>();
            for (int i = 0; i < lstModelTree.size(); i++) {
                //String strPos_key = ArrlstPoscd.get(i);
                ModelTreeNode modelTree = lstModelTree.get(i);

                //Neu la row dau tien thi la node root
                if (i == 0) {
//                    System.err.println("getStrParentCd=" + modelTree.getStrParentCd() + " getStrParentDesc=" + modelTree.getStrParentDesc());
                    nodes_pos.setId("999999");
                    nodes_pos.setTitle(modelTree.getStrParentDesc());
                    nodes_pos.setState(TreeNode.NODE_STATE_OPEN);
                    nodes_pos.setChildren(new LinkedList<TreeNode>());
                } else {
                    if (modelTree.getStrChildCd().equals("999999")) {
                        if (i != 1) {
//                        nodes_pos.getChildren().add(nodePar);
                            lstTree.add(nodePar);
                            nodePar = null;
                            nodePar = new TreeNode();
                        }
//                    nodePar= new TreeNode();
//                        System.err.println("  - nodePar getStrChildCd=" + modelTree.getStrChildCd() + " getStrChildDesc=" + modelTree.getStrChildDesc());
                        nodePar.setId("999999");
                        nodePar.setTitle(modelTree.getStrChildDesc());
                        nodePar.setState(TreeNode.NODE_STATE_CLOSED);
                        nodePar.setChildren(new LinkedList<TreeNode>());
                    } else {
                        //Khoi tao cho node child
//                        System.err.println("      - nodeChild getStrChildCd=" + modelTree.getStrChildCd() + " getStrChildDesc=" + modelTree.getStrChildDesc());
                        TreeNode nodeChild = new TreeNode();
                        nodeChild.setId(modelTree.getStrChildCd());
                        nodeChild.setTitle(modelTree.getStrChildDesc());
                        nodePar.getChildren().add(nodeChild);
                    }
                }

            }
            lstTree.add(nodePar);
            for (TreeNode node : lstTree) {
                nodes_pos.getChildren().add(node);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " setTreeNodeGrade3 TraCuuKyQuy-> " + e.getMessage());
            return false;
        }
        return true;
    }

    private boolean setTreeNodeGrade12(List<ModelTreeNode> lstModelTree) {

        try {
            for (int i = 0; i < lstModelTree.size(); i++) {
                //String strPos_key = ArrlstPoscd.get(i);
                ModelTreeNode modelTree = lstModelTree.get(i);
                //Neu la row dau tien thi la node root
                if (i == 0) {
                    nodes_pos.setId("999999");
                    nodes_pos.setTitle(modelTree.getStrParentDesc());
                    nodes_pos.setState(TreeNode.NODE_STATE_OPEN);
                    nodes_pos.setChildren(new LinkedList<TreeNode>());
                }
                //Khoi tao cho node child
                TreeNode nodeChild = new TreeNode();
                nodeChild.setId(modelTree.getStrChildCd());
                nodeChild.setTitle(modelTree.getStrChildDesc());
//                System.err.println(ArrlstPosDesc.get(i));
                nodes_pos.getChildren().add(nodeChild);

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " setTreeNodeGrade12 TraCuuKyQuy-> " + e.getMessage());
            return false;
        }
        return true;
    }

    protected boolean getParaSession() {
        Map session = ActionContext.getContext().getSession();

        if (session == null || session.size() == 0 || session.isEmpty()) {
            setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
            addActionError("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
            return false;
        }
        //lay ra user
        setUserName(session.get("username").toString());

//            System.err.println("execute sUserName=" + sUserName);
        if (UserName == null || UserName.isEmpty()) {
            setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
            addActionError("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
            return false;
        }
        setGrade(session.get("reportGrade").toString());
        if (Grade == null || Grade.isEmpty()) {
            setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
            addActionError("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
            return false;
        }
        return true;
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

    public String execute() {
        try {
//            System.err.println("khoa_nhaptaycn=" + khoa_nhaptaycn);
            if (!getParaSession()) {
                return ERROR;
            }
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            //khoi tao cho treeview cac pos
            List<ModelTreeNode> lstModelTree = daoMain.getDataPosTreeNode(conn, UserName, Grade, khoa_nhaptaycn);

            if (Grade.equals("3")) {
                setTreeNodeGrade3(lstModelTree);
            } else if (Grade.equals("1")) {
                setTreeNodeGrade3(lstModelTree);
            } else {
                setTreeNodeGrade12(lstModelTree);
            }

//            lstNhaptaycnParams = daoMain.getReportParmamsNhaptaycn(conn, khoa_nhaptaycn, UserName, Grade);
            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> loadPataTraCuuKyQuy: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> loadPataTraCuuKyQuy: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String load(){
       try {
//            System.err.println("COVID_03");
            if (!getParaSession()) {
                return ERROR;
            }
            String sTungay = tungay;
            String sDenngay = denngay;
            String sSdt = sodienthoai;
            String sMaKH = makh;
            String sTT = trangthai;
//            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();            
                lstDulieuNt = daoMain.getDataTracuuKyQuy(conn,"", UserName,Grade,poscd, tungay, denngay, sodienthoai,makh, trangthai);
//                setLstCBKetoan(daoMain.getCanBo(UserName,"LT"));
            if (conn != null) {
                conn.close();
            }                                       
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> TraCuuKyQuy: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> TraCuuKyQuy: " + e.getMessage());
        }        
        return SUCCESS;
    }
               
    //</editor-fold>    

    public String getPoslist() {
        return poslist;
    }

    public void setPoslist(String poslist) {
        this.poslist = poslist;
    }
  

    //<editor-fold defaultstate="collapsed" desc="Khai bao phuong thuc get/set cho bien">
    

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    

    public List<QT_DULIEU_NT> getLstDulieuNt() {
        return lstDulieuNt;
    }

    public void setLstDulieuNt(List<QT_DULIEU_NT> lstDulieuNt) {
        this.lstDulieuNt = lstDulieuNt;
    }

    

    public TreeNode getNodes_pos() {
        return nodes_pos;
    }

    public void setNodes_pos(TreeNode nodes_pos) {
        this.nodes_pos = nodes_pos;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String Grade) {
        this.Grade = Grade;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    

    public List<String> getPoscd() {
        return poscd;
    }

    public void setPoscd(List<String> poscd) {
        this.poscd = poscd;
    }

    

    public String getKhoa_nhaptaycn() {
        return khoa_nhaptaycn;
    }

    public void setKhoa_nhaptaycn(String khoa_nhaptaycn) {
        this.khoa_nhaptaycn = khoa_nhaptaycn;
    }
    

    //</editor-fold>
}
