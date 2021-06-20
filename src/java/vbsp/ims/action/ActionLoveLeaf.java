/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoDcptNo;
import vbsp.ims.dao.DaoLoveLeaf;
import vbsp.ims.dao.DaoProcessRisk;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.DcptNoModel;
import vbsp.ims.model.DcptNoModel.ViewTotalCust;
import vbsp.ims.model.DcptNoModel.saveDcNo;
import vbsp.ims.model.DcptNoModel.savePtNo;
import vbsp.ims.model.DcptNoModel.senddcpt;
import vbsp.ims.model.LoveLeafModel;
import vbsp.ims.model.LoveLeafModel.SaveLoveLeaf;
import vbsp.ims.model.ModelRiskProcess;
import vbsp.ims.model.ModelRiskProcess.StatusHistorySend;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.model.Pagination;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.XmlDcptNo;

/**
 *
 * @author BAOANH
 */
public class ActionLoveLeaf extends ActionSupport {

    //<editor-fold defaultstate="collapsed" desc="Khai bao cac bien">
    //Khoi tao cho treeview
    private TreeNode nodes_pos = new TreeNode();
    //Cho message thong bao loi
    private String message;
    //Lay pos tren treeview lstSoku
    private List<LoveLeafModel> lstModelLoveLeaf = new ArrayList<LoveLeafModel>();
    
    private List<SaveLoveLeaf> lstModelSaveLove = new ArrayList<SaveLoveLeaf>();

    public List<SaveLoveLeaf> getLstModelSaveLove() {
        return lstModelSaveLove;
    }

    public void setLstModelSaveLove(List<SaveLoveLeaf> lstModelSaveLove) {
        this.lstModelSaveLove = lstModelSaveLove;
    }

    private String poor;

    public String getPoor() {
        return poor;
    }

    public void setPoor(String poor) {
        this.poor = poor;
    }

    
    
    
    private List<String> poscd = new ArrayList<String>();
    private List<String> lstSoku = new ArrayList<String>();
    private String ngay_dcpt;    

    private String reportGrade;    

    private Pagination pagination = new Pagination(50, 1);

    private String soku;
    private String ma_nguyennhan;
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Khoi tao cho phan danh muc va tree view">
    
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
            CoreLogger.error(this.getClass().getCanonicalName() + " setTreeNodeGrade3 -> " + e.getMessage());
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
            CoreLogger.error(this.getClass().getCanonicalName() + " setTreeNodeGrade12 -> " + e.getMessage());
            return false;
        }
        return true;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Khoi tao cho formmain dc pt">
    public String execute() {
        //Lay ra user tu sesstion
        //lay ra session
        try {
            System.err.println("Vao ham execute");
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();

//            System.err.println("execute sUserName=" + sUserName);
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
            DaoDcptNo daoRisk = new DaoDcptNo();

            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            List<ModelTreeNode> lstModelTree = daoRisk.getDataPosTreeNode(sUserName, reportGrade);
            if (reportGrade.equals("3")) {
                setTreeNodeGrade3(lstModelTree);
            } else {
                setTreeNodeGrade12(lstModelTree);
            }
            //Khoi tao cho treenode
//            setDmKhac();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " execute -> " + e.getMessage());
        }

        return SUCCESS;
    }

   
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="load dữ liệu cho dc no">
    public String getLoveLeaf() {
        try {
            System.err.println("vao ham getLoveLeaf");
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();

//            System.err.println("execute sUserName=" + sUserName);
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            DaoLoveLeaf daoLoveLeaf = new DaoLoveLeaf();
            if (poscd == null) {
                poscd = new ArrayList<String>();
            }            
            Connection conn = null;
            conn = new DaoConnect().getConnect();
            if (conn == null) {
                setMessage("Không thể kết nối cơ sở dữ liệu ");
                return ERROR;
            }
            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(ngay_dcpt);
            String sNgaySl = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);
            if (pagination.getStart() == 0) {
                int nCountCust = daoLoveLeaf.getCountTotalCustData(conn, sUserName, reportGrade,
                        sNgaySl, poscd);
                int size=(int) Math.ceil((double)nCountCust / 10);
                pagination.setPage_size(size*10);
                pagination.setPreperties(nCountCust);
                
            }
            setLstModelLoveLeaf(daoLoveLeaf.getDataCust(conn, sUserName, reportGrade, sNgaySl,
                    poscd,"SEND",
                    pagination.getStart() + 1, pagination.getStart() + pagination.getEnd()));

            pagination.setPage_records(lstModelLoveLeaf.size());
//            setLstViewTotalCust(daoLoveLeaf.getViewTotalCustData(conn, sUserName, reportGrade,
//                    sNgaySl, poscd, dvut_dcpt, totruong_dcpt, nguon_von, chuongtrinh, trangthai));

            if (!conn.isClosed()) {
                conn.close();
//            System.err.println("Conn da close ");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataDcNo -> " + e.getMessage());
        }
        return SUCCESS;
    }
    
  
    public String getDataSearchPoor() {
        try {
            System.err.println("vao ham getDataSearchPoor");
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();

//            System.err.println("execute sUserName=" + sUserName);
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            DaoLoveLeaf daoLoveLeaf = new DaoLoveLeaf();
            if (poscd == null) {
                poscd = new ArrayList<String>();
            }            
            Connection conn = null;
            conn = new DaoConnect().getConnect();
            if (conn == null) {
                setMessage("Không thể kết nối cơ sở dữ liệu ");
                return ERROR;
            }
            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(ngay_dcpt);
            String sNgaySl = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);
//            if (pagination.getStart() == 0) {
//                int nCountCust = daoLoveLeaf.getCountTotalCustData(conn, sUserName, reportGrade,
//                        sNgaySl, poscd);
//                int size=(int) Math.ceil((double)nCountCust / 10);
//                pagination.setPage_size(size*10);
//                pagination.setPreperties(nCountCust);
//                
//            }
            setLstModelLoveLeaf(daoLoveLeaf.getDataSearch(conn, sUserName, reportGrade, sNgaySl,
                    poscd,"SEND",poor));

            pagination.setPage_records(lstModelLoveLeaf.size());
//            setLstViewTotalCust(daoLoveLeaf.getViewTotalCustData(conn, sUserName, reportGrade,
//                    sNgaySl, poscd, dvut_dcpt, totruong_dcpt, nguon_von, chuongtrinh, trangthai));

            if (!conn.isClosed()) {
                conn.close();
//            System.err.println("Conn da close ");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataDcNo -> " + e.getMessage());
        }
        return SUCCESS;
    }
    
    
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Luu dc,pt no">

    public String saveDataLoveLeaf() {
        try {
            System.err.println("vao ham saveDataLoveLeaf");
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();

//            System.err.println("execute sUserName=" + sUserName);
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
            DaoLoveLeaf daoLoveLeaf = new DaoLoveLeaf();
            
            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(ngay_dcpt);
            String sNgaySl = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);
            if (sNgaySl == null || sNgaySl.isEmpty() || sNgaySl.equals("-1")) {
                setMessage("Không thể lấy ra được ngày số liệu ! ");
                return ERROR;
            }

            List<SaveLoveLeaf> lstdctmp = new ArrayList<SaveLoveLeaf>();
            
//            for (SaveLoveLeaf value : lstModelSaveLove) {
//                String s = value.getsPoorID();
//                if (!value.getsPoorID().equals("false")) {
//                    if(value.getsComment().isEmpty() || value.getsComment().equals("")||
//                            value.getsComment().trim().isEmpty() || value.getsComment().trim().equals(""))
//                    {
//                        setMessage("Bạn chưa nhập nguyên nhân cho lá rách được chọn");
//                        return ERROR;
//                    }
//                }
//            }

//            for (SaveLoveLeaf value : lstModelSaveLove) {
//                String s = value.getsPoorID();
//                if (!value.getsPoorID().equals("false")) {
//                    lstdctmp.add(value);
//                }
//            }

            if (daoLoveLeaf.SaveDataLoveLeaf(sUserName, sNgaySl,  lstModelSaveLove,"S")) {
//                if (daoLoveLeaf.SaveDataLoveLeaf(sUserName, sNgaySl,  lstdctmp,"R")) {
                    setMessage("Đã cập nhật số liệu thành công ! ");
//                }                
            } else {
                setMessage("Bạn chưa cập nhật được số liệu xin liên hệ quản trị để khắc phục lỗi! ");
                return ERROR;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " saveDataLoveLeaf -> " + e.getMessage());
            setMessage("Bạn chưa cập nhật được số liệu xin liên hệ quản trị để khắc phục lỗi!  ");
            return ERROR;
        }
        return SUCCESS;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Tim kiem khach hang">
    public String getDataSearchLoan() {
        try {
            System.err.println("vao ham getDataSearchLoan");
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();

//            System.err.println("execute sUserName=" + sUserName);
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }

            HttpServletRequest request = ServletActionContext.getRequest();

            Map<String, String[]> mappos = request.getParameterMap();
            String[] arrposcd = mappos.get("poscd");
            if (arrposcd == null || arrposcd.length == 0) {
                setMessage("Bạn phải chọn chi nhánh cần phê duyệt dữ liệu ");
//                return ERROR;
                poscd = new ArrayList<String>();
            } else {
                poscd = new ArrayList<String>(Arrays.asList(arrposcd));
                poscd.remove("999999");
            }

            //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
            ngay_dcpt = request.getParameter("ngay_dcpt");
            
            //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
            DaoDcptNo daoLoveLeaf = new DaoDcptNo();
            if (ngay_dcpt == null || ngay_dcpt.isEmpty() || ngay_dcpt.equals("-1")) {
                setMessage("Không lấy ra được ngày số liệu xin kiểm tra lại ! ");
                return ERROR;
            }
            if (soku == null || soku.isEmpty()) {
                setMessage("Không lấy ra được mã món vay cần tìm ! ");
                return ERROR;
            }

            Connection conn = null;
            conn = new DaoConnect().getConnect();
            if (conn == null) {
                setMessage("Không thể kết nối cơ sở dữ liệu ");
                return ERROR;
            }

            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(ngay_dcpt);
            String sNgaySl = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);

//            if (pagination.getStart() == 0) {
//                int nCountCust = daoLoveLeaf.getCountTotalSearchCust(conn, sUserName, reportGrade, soku,
//                        sNgaySl, poscd, dvut_dcpt, totruong_dcpt, nguon_von, chuongtrinh);
//                int size=(int) Math.ceil((double)nCountCust / 10);
//                pagination.setPage_size(size*10);
//                pagination.setPreperties(nCountCust);
//                
//               
//            }
//            setLstModelDcptNo(daoLoveLeaf.getDataCustSearchLoan(conn, sUserName, reportGrade, soku, sNgaySl,
//                    poscd, dvut_dcpt, totruong_dcpt, nguon_von, chuongtrinh,
//                    pagination.getStart() + 1, pagination.getStart() + pagination.getEnd()));
//
//            pagination.setPage_records(lstModelDcptNo.size());
            if (!conn.isClosed()) {
                conn.close();
//            System.err.println("Conn da close ");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataSearchLoan -> " + e.getMessage());
        }
        return SUCCESS;
    }
    

//</editor-fold>
    

    //<editor-fold defaultstate="collapsed" desc="Khoi tao get set cho bien local">   


    public String getMa_nguyennhan() {
        return ma_nguyennhan;
    }

    public void setMa_nguyennhan(String ma_nguyennhan) {
        this.ma_nguyennhan = ma_nguyennhan;
    }
    

    public List<String> getLstSoku() {
        return lstSoku;
    }

    public void setLstSoku(List<String> lstSoku) {
        this.lstSoku = lstSoku;
    }

    public String getSoku() {
        return soku;
    }

    public void setSoku(String soku) {
        this.soku = soku;
    }

    public TreeNode getNodes_pos() {
        return nodes_pos;
    }

    public void setNodes_pos(TreeNode nodes_pos) {
        this.nodes_pos = nodes_pos;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getPoscd() {
        return poscd;
    }

    public void setPoscd(List<String> poscd) {
        this.poscd = poscd;
    }

    public String getNgay_dcpt() {
        return ngay_dcpt;
    }

    public void setNgay_dcpt(String ngay_dcpt) {
        this.ngay_dcpt = ngay_dcpt;
    }


    public String getReportGrade() {
        return reportGrade;
    }

    public void setReportGrade(String reportGrade) {
        this.reportGrade = reportGrade;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<LoveLeafModel> getLstModelLoveLeaf() {
        return lstModelLoveLeaf;
    }

    public void setLstModelLoveLeaf(List<LoveLeafModel> lstModelLoveLeaf) {
        this.lstModelLoveLeaf = lstModelLoveLeaf;
    }
    

//</editor-fold>
}
