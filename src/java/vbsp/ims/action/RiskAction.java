/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoProcessRisk;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelRiskProcess;
import vbsp.ims.model.ModelRiskProcess.DescTableBrower;
import vbsp.ims.model.ModelRiskProcess.HistorySendData;
import vbsp.ims.model.ModelRiskProcess.ListRisk;
import vbsp.ims.model.ModelRiskProcess.ListRiskSync;
import vbsp.ims.model.ModelRiskProcess.StatusHistorySend;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.model.Pagination;
import vbsp.ims.nhaptaycn.action.QT_DULIEU_NT_50;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.ImsReadWriteXmlFile;

/**
 *
 * @author LION
 */
public class RiskAction extends ActionSupport implements ModelDriven<ModelRiskProcess> {

    //<editor-fold defaultstate="collapsed" desc="Khai báo các biến">
//List load du lieu cho table hien thi du lieu khi phe duyet
    private List<ModelRiskProcess> lstTableRiskObj = new ArrayList<ModelRiskProcess>();
    //So ku cua khach hang khi nsd check vao chon
    //private String check_legacyid;
    private List<String> check_legacyid;
    private String dot_xlrr;//Lan xu ly no
    private String trangthai_xlrr; //Trang thai xu ly
    private String nhom_xlrr; //Nhom no can xu ly
    //private String poscd;       //Ma xa, huyen, tinh chon load du lieu
    private String nam_xlrr;//Ngay so lieu
    private String chuongtrinh;
    private String nguon_von;
    private String vb_xlrr;//Ngay so lieu
    
    private List<ListValue> lstNNBanKS = new ArrayList<ListValue>();
    //cho form tu day

    //Khoi tao cho treeview
    private TreeNode nodes_pos = new TreeNode();
    //Cho message thong bao loi
    private String message;
    //Lay pos tren treeview
    private String poscd;
    private String soku_reject;
    private String search_soku;
    private String soku;
    
    
    
    //Xử lý check trạng thái dữ liệu theo đợt và năm
    
    private String styledisplay;

    
    //Cho phan phe duyet
    public List<String> dunogoc_xl;
    private List<ListRisk> lstRisk;
    private List<DescTableBrower> lstBrowerView = new ArrayList<DescTableBrower>();
  protected List<QT_DULIEU_NT_50> lstDulieuNt50 = new ArrayList<>();
  protected List<ListValue> lstCanBo = new ArrayList<ListValue>();
  protected List<ListValue> lstNguyenNhanRR = new ArrayList<ListValue>();
    
    //public String dunogoc_xl;
    //Ngay hoac nam bao cao
    //private String export_date;
//    private List<ListValue> lstModuleObj;
//    private String module_table;
    //Cho lop phan trang khoi tao ban dau la 6 row tren table
    private Pagination pagination = new Pagination(10, 1);
    ModelRiskProcess modelRisk = new ModelRiskProcess();

    //--- khai bao cho cac select
    private List<ListValue> lstNamXlrr = new ArrayList<ListValue>();
    private String defaultNamxlrr;
    private List<ListValue> lstTrangthaiXlrr = new ArrayList<ListValue>();
    private List<ListValue> lstNhomXlrr = new ArrayList<ListValue>();
    private List<ListValue> lstDotXlrr = new ArrayList<ListValue>();
    private List<ListValue> lstChuongtrinh = new ArrayList<ListValue>();
    private List<ListValue> lstNguonvon = new ArrayList<ListValue>();
    private List<ListValue> lstVbXlrr = new ArrayList<ListValue>();

    private String reportGrade;
    private String sendData;
    
    private int capPheDuyet;

    private String result_reject;

    private String nguyennhan_tuchoi;
    

    private List<StatusHistorySend> lstModelHist;

    private List<String> macn;
    private List<HistorySendData> lstViewHistorySend;
    
    private String sTenkh;
    private String sNgayvay;
    private String dbMdthiethai;
    private String sNgayrr;
    private String dbDnghi_Tg;
    private String dbPduyet_Tg;
    private String dbHt_Dno;
    private String dbHt_Lai;
    private String dbSolanxl;
    private String dbDnghi_Lai;
    
    private InputStream pageResult;

    public List<ListValue> getLstNNBanKS() {
        return lstNNBanKS;
    }

    public void setLstNNBanKS(List<ListValue> lstNNBanKS) {
        this.lstNNBanKS = lstNNBanKS;
    }

    public String getDbSolanxl() {
        return dbSolanxl;
    }

    public void setDbSolanxl(String dbSolanxl) {
        this.dbSolanxl = dbSolanxl;
    }

    public String getDbDnghi_Lai() {
        return dbDnghi_Lai;
    }

    public void setDbDnghi_Lai(String dbDnghi_Lai) {
        this.dbDnghi_Lai = dbDnghi_Lai;
    }

    public List<QT_DULIEU_NT_50> getLstDulieuNt50() {
        return lstDulieuNt50;
    }

    public void setLstDulieuNt50(List<QT_DULIEU_NT_50> lstDulieuNt50) {
        this.lstDulieuNt50 = lstDulieuNt50;
    }
    
    

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Phê duyệt theo khoản vay">
//Khoi tao cho cac dieu kien du lieu
    public boolean setDmKhac() {
        try {
            DaoProcessRisk daoRisk = new DaoProcessRisk();
            HashMap<Integer, List<ListValue>> hmDmKhac = daoRisk.getDmKhac();
            lstNamXlrr = hmDmKhac.get(50) == null ? new ArrayList<ListValue>() : hmDmKhac.get(50);
            lstDotXlrr = hmDmKhac.get(51) == null ? new ArrayList<ListValue>() : hmDmKhac.get(51);
            lstTrangthaiXlrr = hmDmKhac.get(52) == null ? new ArrayList<ListValue>() : hmDmKhac.get(52);
            lstNhomXlrr = hmDmKhac.get(53) == null ? new ArrayList<ListValue>() : hmDmKhac.get(53);
            lstChuongtrinh = hmDmKhac.get(54) == null ? new ArrayList<ListValue>() : hmDmKhac.get(54);
            lstNguonvon = hmDmKhac.get(55) == null ? new ArrayList<ListValue>() : hmDmKhac.get(55);
            lstVbXlrr = hmDmKhac.get(62) == null ? new ArrayList<ListValue>() : hmDmKhac.get(62);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " setDmKhac -> " + e.getMessage());
            return false;
        }
//        return true;
    }
    
        public boolean setDmKhacQD62() {
        try {
            DaoProcessRisk daoRisk = new DaoProcessRisk();
             Map session = ActionContext.getContext().getSession();
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                 return false;
            }
            HashMap<Integer, List<ListValue>> hmDmKhac = daoRisk.getDmKhacQD62(sUserName);
            lstNamXlrr = hmDmKhac.get(50) == null ? new ArrayList<ListValue>() : hmDmKhac.get(50);
            lstDotXlrr = hmDmKhac.get(51) == null ? new ArrayList<ListValue>() : hmDmKhac.get(51);
            lstTrangthaiXlrr = hmDmKhac.get(52) == null ? new ArrayList<ListValue>() : hmDmKhac.get(52);
            lstNhomXlrr = hmDmKhac.get(53) == null ? new ArrayList<ListValue>() : hmDmKhac.get(53);
            lstChuongtrinh = hmDmKhac.get(54) == null ? new ArrayList<ListValue>() : hmDmKhac.get(54);
            lstNguonvon = hmDmKhac.get(55) == null ? new ArrayList<ListValue>() : hmDmKhac.get(55);
            lstVbXlrr = hmDmKhac.get(62) == null ? new ArrayList<ListValue>() : hmDmKhac.get(62);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " setDmKhac -> " + e.getMessage());
            return false;
        }
//        return true;
    }

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

    public String execute() {
        //Lay ra user tu sesstion
        //lay ra session
        try {
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
            //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
            DaoProcessRisk daoRisk = new DaoProcessRisk();
            List<ModelTreeNode> lstModelTree = daoRisk.getDataPosTreeNode(sUserName);
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            if (reportGrade.equals("3")) {
                setTreeNodeGrade3(lstModelTree);
            } else {
                setTreeNodeGrade12(lstModelTree);
            }
            //<editor-fold defaultstate="collapsed" desc="comment">
//        nodes_pos.setId("rootNode");
//        nodes_pos.setTitle("Root Node");
//        nodes_pos.setState(TreeNode.NODE_STATE_OPEN);
//        nodes_pos.setChildren(new LinkedList<TreeNode>());
//        
//        for (int i = 0; i < 3; i++) {
//            TreeNode nodeA = new TreeNode();
//            nodeA.setId("A");
//            nodeA.setTitle("Node A");
//            nodeA.setState(TreeNode.NODE_STATE_OPEN);
//            
//            TreeNode nodeAA = new TreeNode();
//            nodeAA.setId("AA");
//            nodeAA.setTitle("Node AA");
//            
//            TreeNode nodeAB = new TreeNode();
//            nodeAB.setId("AB");
//            nodeAB.setTitle("Node AB");
//            
//            nodeA.setChildren(new LinkedList<TreeNode>());
//            nodeA.getChildren().add(nodeAA);
//            nodeA.getChildren().add(nodeAB);
//            nodes_pos.getChildren().add(nodeA);
//        }
//</editor-fold>
            //Khoi tao cho treenode
//            HashMap<Integer, List<ListValue>> hmDmKhac = daoRisk.getDmKhac();
//            lstNamXlrr = hmDmKhac.get(46) == null ? new ArrayList<ListValue>() : hmDmKhac.get(46);
//            lstDotXlrr = hmDmKhac.get(47) == null ? new ArrayList<ListValue>() : hmDmKhac.get(47);
//            lstTrangthaiXlrr = hmDmKhac.get(48) == null ? new ArrayList<ListValue>() : hmDmKhac.get(48);
//            lstNhomXlrr = hmDmKhac.get(49) == null ? new ArrayList<ListValue>() : hmDmKhac.get(49);
//            lstChuongtrinh = hmDmKhac.get(50) == null ? new ArrayList<ListValue>() : hmDmKhac.get(50);
//            lstNguonvon = hmDmKhac.get(51) == null ? new ArrayList<ListValue>() : hmDmKhac.get(51);
            setDmKhac();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " execute -> " + e.getMessage());
        }

        return SUCCESS;
    }
    
    public String execute62() {
        //Lay ra user tu sesstion
        //lay ra session
        try {
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
            //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
            DaoProcessRisk daoRisk = new DaoProcessRisk();
            List<ModelTreeNode> lstModelTree = daoRisk.getDataPosTreeNode_QD62(sUserName);
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            if (reportGrade.equals("3")) {
                setTreeNodeGrade3(lstModelTree);
            } else {
                setTreeNodeGrade12(lstModelTree);
            }
            capPheDuyet = daoRisk.getCapPheduyetQD62(sUserName, reportGrade);
            
            //<editor-fold defaultstate="collapsed" desc="comment">
//        nodes_pos.setId("rootNode");
//        nodes_pos.setTitle("Root Node");
//        nodes_pos.setState(TreeNode.NODE_STATE_OPEN);
//        nodes_pos.setChildren(new LinkedList<TreeNode>());
//        
//        for (int i = 0; i < 3; i++) {
//            TreeNode nodeA = new TreeNode();
//            nodeA.setId("A");
//            nodeA.setTitle("Node A");
//            nodeA.setState(TreeNode.NODE_STATE_OPEN);
//            
//            TreeNode nodeAA = new TreeNode();
//            nodeAA.setId("AA");
//            nodeAA.setTitle("Node AA");
//            
//            TreeNode nodeAB = new TreeNode();
//            nodeAB.setId("AB");
//            nodeAB.setTitle("Node AB");
//            
//            nodeA.setChildren(new LinkedList<TreeNode>());
//            nodeA.getChildren().add(nodeAA);
//            nodeA.getChildren().add(nodeAB);
//            nodes_pos.getChildren().add(nodeA);
//        }
//</editor-fold>
            //Khoi tao cho treenode
//            HashMap<Integer, List<ListValue>> hmDmKhac = daoRisk.getDmKhac();
//            lstNamXlrr = hmDmKhac.get(46) == null ? new ArrayList<ListValue>() : hmDmKhac.get(46);
//            lstDotXlrr = hmDmKhac.get(47) == null ? new ArrayList<ListValue>() : hmDmKhac.get(47);
//            lstTrangthaiXlrr = hmDmKhac.get(48) == null ? new ArrayList<ListValue>() : hmDmKhac.get(48);
//            lstNhomXlrr = hmDmKhac.get(49) == null ? new ArrayList<ListValue>() : hmDmKhac.get(49);
//            lstChuongtrinh = hmDmKhac.get(50) == null ? new ArrayList<ListValue>() : hmDmKhac.get(50);
//            lstNguonvon = hmDmKhac.get(51) == null ? new ArrayList<ListValue>() : hmDmKhac.get(51);
            setDmKhacQD62();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " execute -> " + e.getMessage());
        }

        return SUCCESS;
    }

    //Xu ly cho button tai du lieu
    public String process_risk() {
        try {
            System.err.println("Tai du lieu process_risk");
            if (vb_xlrr == null || vb_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn văn bản hoặc quyết định xử lý rủi ro !");
                return ERROR;
            }
            if (nam_xlrr == null || nam_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn năm xử lý rủi ro !");
                return ERROR;
            }
            if (dot_xlrr == null || dot_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn đợt xử lý rủi ro !");
                return ERROR;
            }

            if (nhom_xlrr == null || nhom_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn nhóm xử lý rủi ro !");
                return ERROR;
            }
            if (trangthai_xlrr == null || trangthai_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn trạng thái xử lý rủi ro !");
                return ERROR;
            }
//        System.err.println("Mang lay ra day " + date_risk);
//        System.err.println("Check da chon la " + poscd);
//        System.err.println("Dot xu ly " + times_risk);
//        System.err.println("Trang thai " + status_risk);
//        System.err.println("nhom no " + group_risk);
            DaoProcessRisk daoRisk = new DaoProcessRisk();
            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
            //Xu ly cho poscd
            //DO khi chon pos se ra 1 danh sach : pos1, pos2, pos3...
            //Sẽ cat pos nay ra sau do dua vao List
            if (poscd != null && !poscd.isEmpty()) {
                poscd = poscd.replace(" ", "");
                //Dua tu chuoi pos ve dang mang
                ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
            }
            //Do phần nhóm rủi ro có nhóm nợ khoanh sẽ là 02,03 nên sẽ đưa về chuỗi dạng '02','03' sau đó truyền vao
            //procedure để lấy in của chuỗi đó
//        if (group_risk != null && group_risk.indexOf(",") > 0) {
////            ArrayList<String> ArrlstGroupRisk = new ArrayList<String>(Arrays.asList(group_risk.split(",")));
//            group_risk = group_risk.replace(" ", "");
//            group_risk = group_risk.replace(",", "','");
//        }

//        System.err.println("Group khi da chuan hoa " + group_risk);
            //Can lai dinh dang ngay bao cao ve dd-MMM-yyyy
            //String strDateRisk = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(date_risk));
            //Lay ra user hien tai dang dang nhap he thong
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            Connection conn = null;

//        HttpServletRequest request = ServletActionContext.getRequest();
//        System.err.println("nam_xlrr -------> " + request.getParameter("nam_xlrr"));
            //connect lớp Dao sau đó get số liệu
            DaoConnect daoconnect = new DaoConnect();
            conn = daoconnect.getConnect();
            if (conn == null) {
                setMessage("Không thể kết nối cơ sở dữ liệu ");
                return ERROR;
            }
            setLstNguyenNhanRR(new DaoNhaptaycnMain().getCanBo(sUserName, "NGUYENNHANRR"));
            //Lay ra tổng số bản ghi của du liêu để phân trang
            if (pagination.getStart() == 0) {
//            System.err.println("Thiet lap de lay tong so row data " + pagination.getStart() + " thang end " + pagination.getEnd());
                int nTotalRow = daoRisk.getCountTotalRowRisk(conn, sUserName, reportGrade, ArrlstPosCd, nam_xlrr, dot_xlrr,
                        nhom_xlrr, trangthai_xlrr, chuongtrinh, nguon_von, vb_xlrr);
                pagination.setPreperties(nTotalRow);
            }
            //Lay du lieu dua ra list table
            lstTableRiskObj = daoRisk.getDataRisk(conn, sUserName, reportGrade, ArrlstPosCd,
                    nam_xlrr, dot_xlrr, nhom_xlrr, trangthai_xlrr, chuongtrinh, nguon_von,
                    pagination.getStart() + 1, pagination.getStart() + pagination.getEnd(), vb_xlrr);

            //Dua so dong du lieu len table
            pagination.setPage_records(lstTableRiskObj.size());
            setLstBrowerView(daoRisk.getDataViewLoan(conn, sUserName, reportGrade, ArrlstPosCd, nam_xlrr, dot_xlrr,
                    nhom_xlrr, trangthai_xlrr, chuongtrinh, nguon_von, vb_xlrr));
            //Dong csdl
            if (!conn.isClosed()) {
                conn.close();
//            System.err.println("Conn da close ");
            }
//        System.err.println("Pos da chon nao--------------- " + poscd);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " process_risk -> " + e.getMessage());
        }

        return SUCCESS;
    }
    
    public String process_risk62() {
        try {
            System.err.println("Tai du lieu process_risk");
            if (vb_xlrr == null || vb_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn văn bản hoặc quyết định xử lý rủi ro !");
                return ERROR;
            }
            if (nam_xlrr == null || nam_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn năm xử lý rủi ro !");
                return ERROR;
            }
            if (dot_xlrr == null || dot_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn đợt xử lý rủi ro !");
                return ERROR;
            }

            if (nhom_xlrr == null || nhom_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn nhóm xử lý rủi ro !");
                return ERROR;
            }
            if (trangthai_xlrr == null || trangthai_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn trạng thái xử lý rủi ro !");
                return ERROR;
            }
//        System.err.println("Mang lay ra day " + date_risk);
//        System.err.println("Check da chon la " + poscd);
//        System.err.println("Dot xu ly " + times_risk);
//        System.err.println("Trang thai " + status_risk);
//        System.err.println("nhom no " + group_risk);
            DaoProcessRisk daoRisk = new DaoProcessRisk();
            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
            //Xu ly cho poscd
            //DO khi chon pos se ra 1 danh sach : pos1, pos2, pos3...
            //Sẽ cat pos nay ra sau do dua vao List
            if (poscd != null && !poscd.isEmpty()) {
                poscd = poscd.replace(" ", "");
                //Dua tu chuoi pos ve dang mang
                ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
            }
            //Do phần nhóm rủi ro có nhóm nợ khoanh sẽ là 02,03 nên sẽ đưa về chuỗi dạng '02','03' sau đó truyền vao
            //procedure để lấy in của chuỗi đó
//        if (group_risk != null && group_risk.indexOf(",") > 0) {
////            ArrayList<String> ArrlstGroupRisk = new ArrayList<String>(Arrays.asList(group_risk.split(",")));
//            group_risk = group_risk.replace(" ", "");
//            group_risk = group_risk.replace(",", "','");
//        }

//        System.err.println("Group khi da chuan hoa " + group_risk);
            //Can lai dinh dang ngay bao cao ve dd-MMM-yyyy
            //String strDateRisk = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(date_risk));
            //Lay ra user hien tai dang dang nhap he thong
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            Connection conn = null;

//        HttpServletRequest request = ServletActionContext.getRequest();
//        System.err.println("nam_xlrr -------> " + request.getParameter("nam_xlrr"));
            //connect lớp Dao sau đó get số liệu
            DaoConnect daoconnect = new DaoConnect();
            conn = daoconnect.getConnect();
            if (conn == null) {
                setMessage("Không thể kết nối cơ sở dữ liệu ");
                return ERROR;
            }
            capPheDuyet = daoRisk.getCapPheduyetQD62(sUserName, reportGrade);
            if (capPheDuyet != 1 && capPheDuyet != 3)
                return "cbXLN_duyet";
            //Lay ra tổng số bản ghi của du liêu để phân trang
            if (pagination.getStart() == 0) {
//            System.err.println("Thiet lap de lay tong so row data " + pagination.getStart() + " thang end " + pagination.getEnd());
                int nTotalRow = daoRisk.getCountTotalRowRiskQD62(conn, sUserName, reportGrade, ArrlstPosCd, nam_xlrr, dot_xlrr,
                        nhom_xlrr, trangthai_xlrr, chuongtrinh, nguon_von, vb_xlrr);
                pagination.setPreperties(nTotalRow);
            }
            //Lay du lieu dua ra list table
            lstTableRiskObj = daoRisk.getDataRiskQD62(conn, sUserName, reportGrade, ArrlstPosCd,
                    nam_xlrr, dot_xlrr, nhom_xlrr, trangthai_xlrr, chuongtrinh, nguon_von,
                    pagination.getStart() + 1, pagination.getStart() + pagination.getEnd(), vb_xlrr);

            //Dua so dong du lieu len table
            pagination.setPage_records(lstTableRiskObj.size());
            setLstBrowerView(daoRisk.getDataViewLoanQD62(conn, sUserName, reportGrade, ArrlstPosCd, nam_xlrr, dot_xlrr,
                    nhom_xlrr, trangthai_xlrr, chuongtrinh, nguon_von, vb_xlrr));
            //Dong csdl
            if (!conn.isClosed()) {
                conn.close();
//            System.err.println("Conn da close ");
            }
//        System.err.println("Pos da chon nao--------------- " + poscd);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " process_risk -> " + e.getMessage());
        }

        if (capPheDuyet ==1)
            return "cbXLN_duyet";
        else if (capPheDuyet ==2)
            return "ldXLN_duyet_lan1";
        else if (capPheDuyet ==3)
            return "banKS_duyet";
        else if (capPheDuyet ==4)
            return "ldXLN_duyet_lan2";
        else
            return ERROR;
    }

    //Xu ly cho button duyet du lieu
    public String browse_risk() throws Exception {
        try {
            System.err.println("So phan tu " + lstRisk.size());
//        if (check_legacyid == null || check_legacyid.isEmpty()) {
//            setMessage("Bạn phải chọn khách hàng cần duyệt ");
//            return ERROR;
//        }

            HttpServletRequest request = ServletActionContext.getRequest();
//            System.err.println("dunogoc_xl="+dunogoc_xl);
            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
            poscd = request.getParameter("poscd");
            if (poscd != null && !poscd.isEmpty()) {
                poscd = poscd.replace(" ", "");
                //Dua tu chuoi pos ve dang mang
                ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
            }

            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            List<ListRisk> lstDataRisk = new ArrayList<ListRisk>();
            for (ListRisk risk : lstRisk) {
                if (!risk.getCheck_legacyid().toLowerCase().equals("false")) {
                    lstDataRisk.add(risk);
                    System.err.println("Soku=" + risk.getCheck_legacyid() + " duno_rr=" + risk.getDuno_rr() + " lai_rr=" + risk.getLai_rr());
                }

            }
            //String sSoku = request.getParameter("soku_reject");
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            nhom_xlrr = request.getParameter("nhom_xlrr");
            //trangthai_xlrr = request.getParameter("trangthai_xlrr");
            chuongtrinh = request.getParameter("chuongtrinh");
            vb_xlrr = request.getParameter("vb_xlrr");

            if (new DaoProcessRisk().setStatusRisk(sUserName, reportGrade, ArrlstPosCd, nam_xlrr, dot_xlrr, nhom_xlrr, chuongtrinh, lstDataRisk, vb_xlrr)) {
                setMessage("Bạn đã phê duyệt thành công");
            } else {
                setMessage("Bạn phê duyệt chưa thành công xin kiểm tra lại");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " browse_risk -> " + e.getMessage());
        }

        return SUCCESS;
    }
    
    public String browse_risk_62() throws Exception {
        try {
            System.err.println("So phan tu " + lstRisk.size());
//        if (check_legacyid == null || check_legacyid.isEmpty()) {
//            setMessage("Bạn phải chọn khách hàng cần duyệt ");
//            return ERROR;
//        }

            HttpServletRequest request = ServletActionContext.getRequest();
//            System.err.println("dunogoc_xl="+dunogoc_xl);
            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
            poscd = request.getParameter("poscd");
            if (poscd != null && !poscd.isEmpty()) {
                poscd = poscd.replace(" ", "");
                //Dua tu chuoi pos ve dang mang
                ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
            }

            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            List<ListRisk> lstDataRisk = new ArrayList<ListRisk>();
            for (ListRisk risk : lstRisk) {
                if (!risk.getCheck_legacyid().toLowerCase().equals("false")) {
                    lstDataRisk.add(risk);
                    System.err.println("Soku=" + risk.getCheck_legacyid() + " duno_rr=" + risk.getDuno_rr() + " lai_rr=" + risk.getLai_rr());
                }

            }
            //String sSoku = request.getParameter("soku_reject");
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            nhom_xlrr = request.getParameter("nhom_xlrr");
            //trangthai_xlrr = request.getParameter("trangthai_xlrr");
            chuongtrinh = request.getParameter("chuongtrinh");
            vb_xlrr = request.getParameter("vb_xlrr");
            capPheDuyet = new DaoProcessRisk().getCapPheduyetQD62(sUserName, reportGrade);
            if (new DaoProcessRisk().setStatusRiskQd62(sUserName, reportGrade, ArrlstPosCd, nam_xlrr, dot_xlrr, nhom_xlrr, chuongtrinh, lstDataRisk, vb_xlrr)) {
                setMessage("Bạn đã phê duyệt thành công");
            } else {
                setMessage("Bạn phê duyệt chưa thành công xin kiểm tra lại");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " browse_risk -> " + e.getMessage());
        }

        return SUCCESS;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Chi tiết khách hàng và tìm kiếm khách hàng">
    public String getDetialCustomer() {
        try {
            System.err.println("vao ham get chi tiet khach hang");

            HttpServletRequest request = ServletActionContext.getRequest();
//            System.err.println("soku -------> " + request.getParameter("soku"));
//            System.err.println("nam_xlrr -------> " + request.getParameter("nam_xlrr"));
//            System.err.println("dot_xlrr -------> " + request.getParameter("dot_xlrr"));
//            System.err.println("nhom_xlrr -------> " + request.getParameter("nhom_xlrr"));
//            System.err.println("trangthai_xlrr -------> " + request.getParameter("trangthai_xlrr"));
//            System.err.println("chuongtrinh -------> " + request.getParameter("chuongtrinh"));
//            System.err.println("poscd -------> " + request.getParameter("poscd"));
            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
            poscd = request.getParameter("poscd");
            if (poscd != null && !poscd.isEmpty()) {
                poscd = poscd.replace(" ", "");
                //Dua tu chuoi pos ve dang mang
                ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
            }

            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            String sSoku = request.getParameter("soku");
            setSoku_reject(sSoku);
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            nhom_xlrr = request.getParameter("nhom_xlrr");
            trangthai_xlrr = request.getParameter("trangthai_xlrr");
            chuongtrinh = request.getParameter("chuongtrinh");
            nguon_von = request.getParameter("nguon_von");
            vb_xlrr = request.getParameter("vb_xlrr");
            //Lay du lieu dua ra list table
            lstTableRiskObj = new DaoProcessRisk().getDetailCustomer(sUserName, reportGrade, ArrlstPosCd,
                    nam_xlrr, dot_xlrr, nhom_xlrr, trangthai_xlrr, chuongtrinh, sSoku, vb_xlrr);
            
            
            if (lstTableRiskObj == null || lstTableRiskObj.isEmpty()) {
                setMessage("Vui lòng kiểm tra lại Đợt xử và nhóm nợ");
                return ERROR;
            }
            capPheDuyet = new DaoProcessRisk().getCapPheduyetQD62(sUserName, reportGrade);
            if (capPheDuyet == 3)
            {
                return "success_ks";
            }
            else
                return SUCCESS;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDetialCustomer -> " + e.getMessage());
        }
        return SUCCESS;
    }

    public String searchCustomer() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            nhom_xlrr = request.getParameter("nhom_xlrr");
            vb_xlrr = request.getParameter("vb_xlrr");
            //trangthai_xlrr = request.getParameter("trangthai_xlrr");
//            chuongtrinh = request.getParameter("chuongtrinh");
//            String sSoku_Search = request.getParameter("search_soku");
            if (search_soku == null || search_soku.isEmpty()) {
                setMessage("Bạn phải nhập số khế ước cần tìm kiếm");
                return ERROR;
            }
            if (nam_xlrr == null || nam_xlrr.equals("-1") || nam_xlrr.isEmpty()) {
                setMessage("Bạn phải chọn năm xử lý rủi ro !");
                return ERROR;
            }
            if (dot_xlrr == null || dot_xlrr.equals("-1") || dot_xlrr.isEmpty()) {
                setMessage("Bạn phải chọn đợt xử lý rủi ro !");
                return ERROR;
            }
            System.err.println("search_soku=" + search_soku + " nhom_xlrr=" + nhom_xlrr);
            DaoProcessRisk daoRisk = new DaoProcessRisk();
            Connection conn = null;
            DaoConnect daoconnect = new DaoConnect();
            conn = daoconnect.getConnect();
            if (conn == null) {
                setMessage("Không thể kết nối cơ sở dữ liệu ");
                return ERROR;
            }
            setLstNguyenNhanRR(new DaoNhaptaycnMain().getCanBo(sUserName, "NGUYENNHANRR"));
            //Lay ra tổng số bản ghi của du liêu để phân trang
            if (pagination.getStart() == 0) {
//            System.err.println("Thiet lap de lay tong so row data " + pagination.getStart() + " thang end " + pagination.getEnd());
                int nTotalRow = daoRisk.getCountSearchRowRisk(conn, sUserName, reportGrade, nam_xlrr, dot_xlrr, search_soku, vb_xlrr);
                pagination.setPreperties(nTotalRow);
            }
            //Lay du lieu dua ra list table
            lstTableRiskObj = daoRisk.getDataSearchRisk(conn, sUserName, reportGrade, nam_xlrr, dot_xlrr, search_soku,
                    pagination.getStart() + 1, pagination.getStart() + pagination.getEnd(), vb_xlrr);

            //Dua so dong du lieu len table
            pagination.setPage_records(lstTableRiskObj.size());
            //Dong csdl
            if (!conn.isClosed()) {
                conn.close();
//            System.err.println("Conn da close ");
            }
//            setSoku_reject(sSoku_Search);
//            soku_reject=sSoku_Search;
//            System.err.println("soku_reject="+soku_reject);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " searchCustomer -> " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
    
    public String searchCustomer62() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            nhom_xlrr = request.getParameter("nhom_xlrr");
            vb_xlrr = request.getParameter("vb_xlrr");
            //trangthai_xlrr = request.getParameter("trangthai_xlrr");
//            chuongtrinh = request.getParameter("chuongtrinh");
//            String sSoku_Search = request.getParameter("search_soku");
            if (search_soku == null || search_soku.isEmpty()) {
                setMessage("Bạn phải nhập số khế ước cần tìm kiếm");
                return ERROR;
            }
            if (nam_xlrr == null || nam_xlrr.equals("-1") || nam_xlrr.isEmpty()) {
                setMessage("Bạn phải chọn năm xử lý rủi ro !");
                return ERROR;
            }
            if (dot_xlrr == null || dot_xlrr.equals("-1") || dot_xlrr.isEmpty()) {
                setMessage("Bạn phải chọn đợt xử lý rủi ro !");
                return ERROR;
            }
            System.err.println("search_soku=" + search_soku + " nhom_xlrr=" + nhom_xlrr);
            DaoProcessRisk daoRisk = new DaoProcessRisk();
            Connection conn = null;
            DaoConnect daoconnect = new DaoConnect();
            conn = daoconnect.getConnect();
            if (conn == null) {
                setMessage("Không thể kết nối cơ sở dữ liệu ");
                return ERROR;
            }
            capPheDuyet = daoRisk.getCapPheduyetQD62(sUserName, reportGrade);
            setLstNNBanKS(new DaoNhaptaycnMain().getCanBo(sUserName, "NN_BANKS"));
            //Lay ra tổng số bản ghi của du liêu để phân trang
            if (pagination.getStart() == 0) {
//            System.err.println("Thiet lap de lay tong so row data " + pagination.getStart() + " thang end " + pagination.getEnd());
                int nTotalRow = daoRisk.getCountSearchRowRisk62(conn, sUserName, reportGrade, nam_xlrr, dot_xlrr, search_soku, vb_xlrr);
                pagination.setPreperties(nTotalRow);
            }
            //Lay du lieu dua ra list table
            lstTableRiskObj = daoRisk.getDataSearchRisk62(conn, sUserName, reportGrade, nam_xlrr, dot_xlrr, search_soku,
                    pagination.getStart() + 1, pagination.getStart() + pagination.getEnd(), vb_xlrr, nhom_xlrr);

            
            //Dua so dong du lieu len table
            pagination.setPage_records(lstTableRiskObj.size());
            styledisplay = new DaoProcessRisk().checkAutRisk(reportGrade, nam_xlrr, dot_xlrr, sUserName);
            //Dong csdl
            if (!conn.isClosed()) {
                conn.close();
//            System.err.println("Conn da close ");
            }
//            setSoku_reject(sSoku_Search);
//            soku_reject=sSoku_Search;
//            System.err.println("soku_reject="+soku_reject);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " searchCustomer -> " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String getDetialCustomerReject() {
        try {
            System.err.println("vao ham get chi tiet khach hang");

            HttpServletRequest request = ServletActionContext.getRequest();
            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
            poscd = request.getParameter("poscd");
            if (poscd != null && !poscd.isEmpty()) {
                poscd = poscd.replace(" ", "");
                //Dua tu chuoi pos ve dang mang
                ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
            }

            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            String sSoku = request.getParameter("soku");
            setSoku_reject(sSoku);
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            nhom_xlrr = request.getParameter("nhom_xlrr");
            trangthai_xlrr = request.getParameter("trangthai_xlrr");
            chuongtrinh = request.getParameter("chuongtrinh");
            nguon_von = request.getParameter("nguon_von");
            vb_xlrr = request.getParameter("vb_xlrr");
            //Lay du lieu dua ra list table
            lstTableRiskObj = new DaoProcessRisk().getDetailCustomer(sUserName, reportGrade, ArrlstPosCd,
                    nam_xlrr, dot_xlrr, nhom_xlrr, trangthai_xlrr, chuongtrinh, sSoku, vb_xlrr);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDetialCustomer -> " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String getDetialCustomerReject62() {
        try {
            System.err.println("vao ham get chi tiet khach hang");

            HttpServletRequest request = ServletActionContext.getRequest();
            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
            poscd = request.getParameter("poscd");
            if (poscd != null && !poscd.isEmpty()) {
                poscd = poscd.replace(" ", "");
                //Dua tu chuoi pos ve dang mang
                ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
            }

            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            capPheDuyet = new DaoProcessRisk().getCapPheduyetQD62(sUserName, reportGrade);
            String sSoku = request.getParameter("soku");
            setSoku_reject(sSoku);
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            nhom_xlrr = request.getParameter("nhom_xlrr");
            trangthai_xlrr = request.getParameter("trangthai_xlrr");
            chuongtrinh = request.getParameter("chuongtrinh");
            nguon_von = request.getParameter("nguon_von");
            vb_xlrr = request.getParameter("vb_xlrr");
            //Lay du lieu dua ra list table
//            setLstCBNguonVon(daoMain.getCanBo(UserName, "NGUONVON"));
            setLstNNBanKS(new DaoNhaptaycnMain().getCanBo(sUserName, "NN_BANKS"));
            lstTableRiskObj = new DaoProcessRisk().getDetailCustomer(sUserName, reportGrade, ArrlstPosCd,
                    nam_xlrr, dot_xlrr, nhom_xlrr, trangthai_xlrr, chuongtrinh, sSoku, vb_xlrr);
            
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDetialCustomer -> " + e.getMessage());
        }
        return SUCCESS;
    }

    public String getDetialCustomerRejectSearch() {
        try {
            System.err.println("vao ham get chi tiet khach hang getDetialCustomerRejectSearch");

            HttpServletRequest request = ServletActionContext.getRequest();
//            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
//            poscd = request.getParameter("poscd");
//            if (poscd != null && !poscd.isEmpty()) {
//                poscd = poscd.replace(" ", "");
//                //Dua tu chuoi pos ve dang mang
//                ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
//            }

            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            String sSoku = request.getParameter("soku");
            setSoku_reject(sSoku);
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            vb_xlrr = request.getParameter("vb_xlrr");
//            nhom_xlrr = request.getParameter("nhom_xlrr");
//            trangthai_xlrr = request.getParameter("trangthai_xlrr");
//            chuongtrinh = request.getParameter("chuongtrinh");
//            nguon_von=request.getParameter("nguon_von");
            //Lay du lieu dua ra list table
            lstTableRiskObj = new DaoProcessRisk().getDetailCustomerSearch(sUserName, reportGrade,
                    nam_xlrr, dot_xlrr, sSoku, vb_xlrr);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDetialCustomerRejectSearch -> " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String getDetialCustomerRejectSearch62() {
        try {
            System.err.println("vao ham get chi tiet khach hang getDetialCustomerRejectSearch62");

            HttpServletRequest request = ServletActionContext.getRequest();
//            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
//            poscd = request.getParameter("poscd");
//            if (poscd != null && !poscd.isEmpty()) {
//                poscd = poscd.replace(" ", "");
//                //Dua tu chuoi pos ve dang mang
//                ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
//            }

            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            capPheDuyet = new DaoProcessRisk().getCapPheduyetQD62(sUserName, reportGrade);
            setLstNNBanKS(new DaoNhaptaycnMain().getCanBo(sUserName, "NN_BANKS"));
            String sSoku = request.getParameter("soku");
            setSoku_reject(sSoku);
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            vb_xlrr = request.getParameter("vb_xlrr");
//            nhom_xlrr = request.getParameter("nhom_xlrr");
//            trangthai_xlrr = request.getParameter("trangthai_xlrr");
//            chuongtrinh = request.getParameter("chuongtrinh");
//            nguon_von=request.getParameter("nguon_von");
            //Lay du lieu dua ra list table
            lstTableRiskObj = new DaoProcessRisk().getDetailCustomerSearch62(sUserName, reportGrade,
                    nam_xlrr, dot_xlrr, sSoku, vb_xlrr);
            
            styledisplay = new DaoProcessRisk().checkAutRisk(reportGrade, nam_xlrr, dot_xlrr, sUserName);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDetailCustomerSearch62 -> " + e.getMessage());
        }
        return SUCCESS;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Cập nhật chỉnh sửa thông tin rủi ro">
    public String khonglamgica() {
        try {
            System.err.println("Vao action ChangeInfoLoan " + soku_reject);
            if (soku_reject == null || soku_reject.isEmpty()) {
                setMessage("Không lấy được số kế ước để từ chối");
                setResult_reject("error");
                result_reject = "error";
                return ERROR;
            }
            HttpServletRequest request = ServletActionContext.getRequest();
//            System.err.println("dunogoc_xl="+dunogoc_xl);
            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
            poscd = request.getParameter("poscd");
            if (poscd != null && !poscd.isEmpty()) {
                poscd = poscd.replace(" ", "");
                //Dua tu chuoi pos ve dang mang
                ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
            }

            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                setResult_reject("error");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                setResult_reject("error");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                setResult_reject("error");
                return ERROR;
            }
            String sSoku = request.getParameter("soku_reject");
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            nhom_xlrr = request.getParameter("nhom_xlrr");
            trangthai_xlrr = request.getParameter("trangthai_xlrr");
            chuongtrinh = request.getParameter("chuongtrinh");
            vb_xlrr = request.getParameter("vb_xlrr");
            sTenkh = request.getParameter("sTenkh");
            sNgayvay = request.getParameter("sNgayvay");
            dbMdthiethai = request.getParameter("dbMdthiethai");
            sNgayrr = request.getParameter("sNgayrr");
            dbDnghi_Tg = request.getParameter("dbDnghi_Tg");
            dbPduyet_Tg = request.getParameter("dbPduyet_Tg");
            dbHt_Dno = request.getParameter("dbHt_Dno");
            dbHt_Lai = request.getParameter("dbHt_Lai");
            dbSolanxl = request.getParameter("dbSolanxl");
            dbDnghi_Lai = request.getParameter("dbDnghi_Lai");
            String sNguyennhan = request.getParameter("sNguyennhan");
            String dbDnghi_Dno = request.getParameter("dbDnghi_Dno");
            if (new DaoProcessRisk().updateInfoLoan(sUserName, reportGrade, ArrlstPosCd, nam_xlrr, dot_xlrr, nhom_xlrr, chuongtrinh, soku_reject,  vb_xlrr,
                    sTenkh,sNgayvay,dbMdthiethai,sNgayrr,dbDnghi_Tg,dbPduyet_Tg,dbHt_Dno,dbHt_Lai,dbDnghi_Lai,sNguyennhan,dbDnghi_Dno)) {
                setMessage("Bạn đã từ chối thành công khoản vay " + soku_reject);
            } else {
                setMessage("Bạn chưa từ chối được khoản vay này " + soku_reject);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " RejectRisk -> " + e.getMessage());
            setResult_reject("error");
            return ERROR;
        }
        setResult_reject("success");
        result_reject = "success";
        return SUCCESS;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Từ chối khách hàng">

    public String RejectRisk() {
        try {
            System.err.println("Vao action reject risk " + soku_reject);
            if (soku_reject == null || soku_reject.isEmpty()) {
                setMessage("Không lấy được số kế ước để từ chối");
                setResult_reject("error");
                result_reject = "error";
                return ERROR;
            }
            HttpServletRequest request = ServletActionContext.getRequest();
//            System.err.println("dunogoc_xl="+dunogoc_xl);
            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
            poscd = request.getParameter("poscd");
            if (poscd != null && !poscd.isEmpty()) {
                poscd = poscd.replace(" ", "");
                //Dua tu chuoi pos ve dang mang
                ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
            }

            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                setResult_reject("error");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                setResult_reject("error");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                setResult_reject("error");
                return ERROR;
            }
            String sSoku = request.getParameter("soku_reject");
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            nhom_xlrr = request.getParameter("nhom_xlrr");
            trangthai_xlrr = request.getParameter("trangthai_xlrr");
            chuongtrinh = request.getParameter("chuongtrinh");
            vb_xlrr = request.getParameter("vb_xlrr");
            if (new DaoProcessRisk().setStatusReject(sUserName, reportGrade, ArrlstPosCd, nam_xlrr, dot_xlrr, nhom_xlrr, chuongtrinh, soku_reject, nguyennhan_tuchoi, vb_xlrr)) {
                setMessage("Bạn đã từ chối thành công khoản vay " + soku_reject);
            } else {
                setMessage("Bạn chưa từ chối được khoản vay này " + soku_reject);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " RejectRisk -> " + e.getMessage());
            setResult_reject("error");
            return ERROR;
        }
        setResult_reject("success");
        result_reject = "success";
        return SUCCESS;
    }
    
    public String RejectRisk62() {
        try {
            System.err.println("Vao action reject risk " + soku_reject);
            if (soku_reject == null || soku_reject.isEmpty()) {
                setMessage("Không lấy được số kế ước để từ chối");
                setResult_reject("error");
                result_reject = "error";
                return ERROR;
            }
            HttpServletRequest request = ServletActionContext.getRequest();
//            System.err.println("dunogoc_xl="+dunogoc_xl);
            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
            poscd = request.getParameter("poscd");
            if (poscd != null && !poscd.isEmpty()) {
                poscd = poscd.replace(" ", "");
                //Dua tu chuoi pos ve dang mang
                ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
            }

            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                setResult_reject("error");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                setResult_reject("error");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                setResult_reject("error");
                return ERROR;
            }
            String sSoku = request.getParameter("soku_reject");
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            nhom_xlrr = request.getParameter("nhom_xlrr");
            trangthai_xlrr = request.getParameter("trangthai_xlrr");
            chuongtrinh = request.getParameter("chuongtrinh");
            vb_xlrr = request.getParameter("vb_xlrr");
            nguyennhan_tuchoi = request.getParameter("nguyennhan_tuchoi");
            if (new DaoProcessRisk().setStatusRejectQD62(sUserName, reportGrade, ArrlstPosCd, nam_xlrr, dot_xlrr, nhom_xlrr, chuongtrinh, soku_reject, nguyennhan_tuchoi, vb_xlrr)) {
                setMessage("Bạn đã từ chối thành công khoản vay " + soku_reject);
            } else {
                setMessage("Bạn chưa từ chối được khoản vay này " + soku_reject);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " RejectRisk -> " + e.getMessage());
            setResult_reject("error");
            return ERROR;
        }
        setResult_reject("success");
        result_reject = "success";
        return SUCCESS;
    }

    public String getDetialCustomerReject1() {
        try {
            System.err.println("Vao action reject risk " + soku_reject);
            HttpServletRequest request = ServletActionContext.getRequest();
//            System.err.println("dunogoc_xl="+dunogoc_xl);
            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
            poscd = request.getParameter("poscd");
            if (poscd != null && !poscd.isEmpty()) {
                poscd = poscd.replace(" ", "");
                //Dua tu chuoi pos ve dang mang
                ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
            }

            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            String sSoku = request.getParameter("soku_reject");
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            nhom_xlrr = request.getParameter("nhom_xlrr");
            trangthai_xlrr = request.getParameter("trangthai_xlrr");
            chuongtrinh = request.getParameter("chuongtrinh");
            vb_xlrr = request.getParameter("vb_xlrr");
            if (new DaoProcessRisk().setStatusReject(sUserName, reportGrade, ArrlstPosCd, nam_xlrr, dot_xlrr, nhom_xlrr, chuongtrinh, soku_reject, nguyennhan_tuchoi, vb_xlrr)) {
                setMessage("Bạn đã từ chối thành công khoản vay " + soku_reject);
            } else {
                setMessage("Bạn chưa từ chối được khoản vay này " + soku_reject);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " RejectRisk -> " + e.getMessage());
        }
        return SUCCESS;
    }

    public String RejectRiskSearch() {
        try {
//            System.err.println("Vao action RejectRiskSearch " + soku_reject+"RR_NGUYENNHAN_TUCHOI="+nguyennhan_tuchoi);
            HttpServletRequest request = ServletActionContext.getRequest();
//            System.err.println("dunogoc_xl="+dunogoc_xl);
            if (soku_reject == null || soku_reject.isEmpty()) {
                setMessage("Không lấy được số kế ước để từ chối");
                setResult_reject("error");
                result_reject = "error";
                return ERROR;
            }
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                setResult_reject("error");
                result_reject = "error";
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                setResult_reject("error");
                result_reject = "error";
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
//            String sSoku = request.getParameter("soku_reject");
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            vb_xlrr = request.getParameter("vb_xlrr");
            System.err.println("nam=" + nam_xlrr + " dot=" + dot_xlrr);
//            nhom_xlrr = request.getParameter("nhom_xlrr");
//            trangthai_xlrr = request.getParameter("trangthai_xlrr");
//            chuongtrinh = request.getParameter("chuongtrinh");
            if (new DaoProcessRisk().setStatusRejectSearch(sUserName, reportGrade, nam_xlrr, dot_xlrr, soku_reject, nguyennhan_tuchoi, vb_xlrr)) {
                setMessage("Bạn đã từ chối thành công khoản vay " + soku_reject);
            } else {
                setMessage("Bạn chưa từ chối được khoản vay này " + soku_reject);
                setResult_reject("error");
                result_reject = "error";
                return ERROR;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " RejectRiskSearch -> " + e.getMessage());
            setResult_reject("error");
            result_reject = "error";
            return ERROR;
        }
        return SUCCESS;
    }
    public String RejectRiskSearch62() {
        try {
//            System.err.println("Vao action RejectRiskSearch " + soku_reject+"RR_NGUYENNHAN_TUCHOI="+nguyennhan_tuchoi);
            HttpServletRequest request = ServletActionContext.getRequest();
//            System.err.println("dunogoc_xl="+dunogoc_xl);
            if (soku_reject == null || soku_reject.isEmpty()) {
                setMessage("Không lấy được số kế ước để từ chối");
                setResult_reject("error");
                result_reject = "error";
                return ERROR;
            }
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                setResult_reject("error");
                result_reject = "error";
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                setResult_reject("error");
                result_reject = "error";
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
//            String sSoku = request.getParameter("soku_reject");
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            vb_xlrr = request.getParameter("vb_xlrr");
            System.err.println("nam=" + nam_xlrr + " dot=" + dot_xlrr);
//            nhom_xlrr = request.getParameter("nhom_xlrr");
//            trangthai_xlrr = request.getParameter("trangthai_xlrr");
//            chuongtrinh = request.getParameter("chuongtrinh");
            if (new DaoProcessRisk().setStatusRejectSearch62(sUserName, reportGrade, nam_xlrr, dot_xlrr, soku_reject, nguyennhan_tuchoi, vb_xlrr)) {
                setMessage("Bạn đã từ chối thành công khoản vay " + soku_reject);
            } else {
                setMessage("Bạn chưa từ chối được khoản vay này " + soku_reject);
                setResult_reject("error");
                result_reject = "error";
                return ERROR;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " RejectRiskSearch -> " + e.getMessage());
            setResult_reject("error");
            result_reject = "error";
            return ERROR;
        }
        return SUCCESS;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Duyệt theo đơn vị">
    public String BrowerDonvi() {
        //Trong module nay co the bo phan cong chuoi sMessage đi duoc
        String sMessage = "<h2 style=\"color: #029c44;>\">Thống kê danh sách khách hàng phê duyệt</h2> </br>"
                + " <table border=\"1\" class=\"editDelete\"> "
                + "<tr> <th rowspan=\"2\">STT</th>"
                + "<th rowspan=\"2\">Mã Đơn vị</th>"
                + "<th rowspan=\"2\">Tên Đơn vị</th>"
                + "<th rowspan=\"2\">Tổng số món</th>"
                + "<th rowspan=\"2\">Tổng tiền</th>"
                + "<th colspan=\"2\">Trong đó</th>"
                + "<th rowspan=\"2\">Trạng thái</th></tr>"
                + "<tr> <th>Tổng gốc</th>"
                + "<th>Tổng Lãi</th> </tr>";
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            nhom_xlrr = request.getParameter("nhom_xlrr");
            vb_xlrr = request.getParameter("vb_xlrr");
//            String[] arrposcd = request.getParameter("poscd");
            Map<String, String[]> mappos = request.getParameterMap();
            String[] arrposcd = mappos.get("poscd");
            if (nam_xlrr == null || nam_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn năm xử lý rủi ro !");
                return ERROR;
            }
            if (dot_xlrr == null || dot_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn đợt xử lý rủi ro !");
                return ERROR;
            }

            if (nhom_xlrr == null || nhom_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn nhóm xử lý rủi ro !");
                return ERROR;
            }

            if (reportGrade.equals("1")) {
                setMessage("Cấp phòng giao dịch không sử dụng được chức năng này ");
                return ERROR;
            }
            if (arrposcd == null || arrposcd.length == 0) {
                setMessage("Bạn phải chọn chi nhánh cần phê duyệt dữ liệu ");
                return ERROR;
            }
            //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
            DaoProcessRisk daoRisk = new DaoProcessRisk();
            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
            if (reportGrade.equals("2")) {
                //poscd = request.getParameter("poscd");

                //Dua tu chuoi pos ve dang mang
                ArrlstPosCd = new ArrayList<String>(Arrays.asList(arrposcd));
                ArrlstPosCd.remove("999999");
            }
            if (reportGrade.equals("3")) {

                //Dua tu chuoi pos ve dang mang
                ArrayList<String> arrInputPos = new ArrayList<String>(Arrays.asList(arrposcd));
                ArrlstPosCd = daoRisk.getPoscdFromMainPos(arrInputPos);
                ArrlstPosCd.remove("999999");

            }
            int nstt = 1;
            BigDecimal bTonggoc = BigDecimal.ZERO;
            BigDecimal bTonglai = BigDecimal.ZERO;
            BigDecimal bSokh = BigDecimal.ZERO;
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            List<DescTableBrower> lstDescTable = new ArrayList<DescTableBrower>();
            for (String sPoscd : ArrlstPosCd) {
                if (sPoscd.equals("999999")) {
                    continue;
                }
                DescTableBrower objTableDesc = daoRisk.setStatusRiskDv(vb_xlrr, sUserName, reportGrade, sPoscd, nam_xlrr, dot_xlrr, nhom_xlrr, nguon_von);

                bTonggoc = bTonggoc.add(new BigDecimal(objTableDesc.getsTongDuno().replace(",", "")));
                bTonglai = bTonglai.add(new BigDecimal(objTableDesc.getsTongLai().replace(",", "")));
                bSokh = bSokh.add(new BigDecimal(objTableDesc.getsSoKh().replace(",", "")));

                if (objTableDesc.isbSuccess()) {
                    sMessage = sMessage + "<tr><td style=\"text-align: center;\">" + Integer.toString(nstt) + "</td>"
                            + "<td style=\"text-align: center;\">" + sPoscd + "</td>"
                            + "<td style=\"text-align: left;\">" + objTableDesc.getsPosDesc() + "</td>"
                            + "<td style=\"text-align: right;\">" + objTableDesc.getsSoKh() + "</td>"
                            + "<td style=\"text-align: right;\">" + objTableDesc.getsTongtien() + "</td>"
                            + "<td style=\"text-align: right;\">" + objTableDesc.getsTongDuno() + "</td>"
                            + "<td style=\"text-align: right;\">" + objTableDesc.getsTongLai() + "</td>"
                            + "<td style=\"text-align: center;\">" + objTableDesc.getsStatus() + "</td></tr>";
                } else {
                    sMessage = sMessage + "<tr><td style=\"text-align: center; color: red\">" + Integer.toString(nstt) + "</td>"
                            + "<td style=\"text-align: center; color: red\">" + sPoscd + "</td>"
                            + "<td style=\"text-align: left; color: red\">" + objTableDesc.getsPosDesc() + "</td>"
                            + "<td style=\"text-align: right; color: red\">" + objTableDesc.getsSoKh() + "</td>"
                            + "<td style=\"text-align: right; color: red\">" + objTableDesc.getsTongtien() + "</td>"
                            + "<td style=\"text-align: right; color: red\">" + objTableDesc.getsTongDuno() + "</td>"
                            + "<td style=\"text-align: right; color: red\">" + objTableDesc.getsTongLai() + "</td>"
                            + "<td style=\"text-align: center;color: red\">" + objTableDesc.getsStatus() + "</td></tr>";
                }
                objTableDesc.setnStt(nstt);
                lstDescTable.add(objTableDesc);
                nstt++;
            }
            BigDecimal bTongtien = BigDecimal.ZERO;
            bTongtien = bTongtien.add(bTonggoc);
            bTongtien = bTongtien.add(bTonglai);
            DescTableBrower obj = new DescTableBrower();
            obj.setsPoscd("999999");
            obj.setsTongDuno(format.format(bTonggoc));
            obj.setsSoKh(format.format(bSokh));
            obj.setsTongLai(format.format(bTonglai));
            obj.setsTongtien(format.format(bTongtien));
            obj.setsPosDesc("Tổng cộng");
            lstDescTable.add(obj);
            sMessage = sMessage + "<tr><td colspan=\"3\" style=\"text-align: center; color: #007fff; font-weight: bold;\">Tổng cộng</td>\n"
                    + "<td style=\"text-align: right; color: #007fff; font-weight: bold;\">" + format.format(bSokh) + "</td>\n"
                    + "<td style=\"text-align: right; color: #007fff; font-weight: bold;\">" + format.format(bTongtien) + "</td>\n"
                    + "<td style=\"text-align: right; color: #007fff; font-weight: bold;\">" + format.format(bTonggoc) + "</td>\n"
                    + "<td style=\"text-align: right; color: #007fff; font-weight: bold;\">" + format.format(bTonglai) + "</td>";
            sMessage = sMessage + "</tr></table>";
            setSendData("OK");
            setLstBrowerView(lstDescTable);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " RejectRisk -> " + e.getMessage());
        }

        setMessage(sMessage);
        return SUCCESS;
    }
    
    public String BrowerDonvi62() {
        //Trong module nay co the bo phan cong chuoi sMessage đi duoc
        String sMessage = "<h2 style=\"color: #029c44;>\">Thống kê danh sách khách hàng phê duyệt</h2> </br>"
                + " <table border=\"1\" class=\"editDelete\"> "
                + "<tr> <th rowspan=\"2\">STT</th>"
                + "<th rowspan=\"2\">Mã Đơn vị</th>"
                + "<th rowspan=\"2\">Tên Đơn vị</th>"
                + "<th rowspan=\"2\">Tổng số món</th>"
                + "<th rowspan=\"2\">Tổng tiền</th>"
                + "<th colspan=\"2\">Trong đó</th>"
                + "<th rowspan=\"2\">Trạng thái</th></tr>"
                + "<tr> <th>Tổng gốc</th>"
                + "<th>Tổng Lãi</th> </tr>";
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            nhom_xlrr = request.getParameter("nhom_xlrr");
            vb_xlrr = request.getParameter("vb_xlrr");
//            String[] arrposcd = request.getParameter("poscd");
            Map<String, String[]> mappos = request.getParameterMap();
            String[] arrposcd = mappos.get("poscd");
            if (nam_xlrr == null || nam_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn năm xử lý rủi ro !");
                return ERROR;
            }
            if (dot_xlrr == null || dot_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn đợt xử lý rủi ro !");
                return ERROR;
            }

            if (nhom_xlrr == null || nhom_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn nhóm xử lý rủi ro !");
                return ERROR;
            }

            if (reportGrade.equals("1")) {
                setMessage("Cấp phòng giao dịch không sử dụng được chức năng này ");
                return ERROR;
            }
            if (arrposcd == null || arrposcd.length == 0) {
                setMessage("Bạn phải chọn chi nhánh cần phê duyệt dữ liệu ");
                return ERROR;
            }
            //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
            DaoProcessRisk daoRisk = new DaoProcessRisk();
            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
            if (reportGrade.equals("2")) {
                //poscd = request.getParameter("poscd");

                //Dua tu chuoi pos ve dang mang
                ArrlstPosCd = new ArrayList<String>(Arrays.asList(arrposcd));
                ArrlstPosCd.remove("999999");
            }
            if (reportGrade.equals("3")) {

                //Dua tu chuoi pos ve dang mang
                ArrayList<String> arrInputPos = new ArrayList<String>(Arrays.asList(arrposcd));
                ArrlstPosCd = daoRisk.getPoscdFromMainPos(arrInputPos);
                ArrlstPosCd.remove("999999");

            }
            capPheDuyet = daoRisk.getCapPheduyetQD62(sUserName, reportGrade);
            if(capPheDuyet==3)
            {
                setMessage("Với người dùng thuộc Ban kiểm soát vui lòng tìm kiếm và duyệt theo từng món vay!");
                return ERROR;
            }
            
            int nstt = 1;
            BigDecimal bTonggoc = BigDecimal.ZERO;
            BigDecimal bTonglai = BigDecimal.ZERO;
            BigDecimal bSokh = BigDecimal.ZERO;
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            List<DescTableBrower> lstDescTable = new ArrayList<DescTableBrower>();
            for (String sPoscd : ArrlstPosCd) {
                if (sPoscd.equals("999999")) {
                    continue;
                }
                DescTableBrower objTableDesc = daoRisk.setStatusRiskDv62(vb_xlrr, sUserName, reportGrade, sPoscd, nam_xlrr, dot_xlrr, nhom_xlrr, nguon_von);

                bTonggoc = bTonggoc.add(new BigDecimal(objTableDesc.getsTongDuno().replace(",", "")));
                bTonglai = bTonglai.add(new BigDecimal(objTableDesc.getsTongLai().replace(",", "")));
                bSokh = bSokh.add(new BigDecimal(objTableDesc.getsSoKh().replace(",", "")));

                if (objTableDesc.isbSuccess()) {
                    sMessage = sMessage + "<tr><td style=\"text-align: center;\">" + Integer.toString(nstt) + "</td>"
                            + "<td style=\"text-align: center;\">" + sPoscd + "</td>"
                            + "<td style=\"text-align: left;\">" + objTableDesc.getsPosDesc() + "</td>"
                            + "<td style=\"text-align: right;\">" + objTableDesc.getsSoKh() + "</td>"
                            + "<td style=\"text-align: right;\">" + objTableDesc.getsTongtien() + "</td>"
                            + "<td style=\"text-align: right;\">" + objTableDesc.getsTongDuno() + "</td>"
                            + "<td style=\"text-align: right;\">" + objTableDesc.getsTongLai() + "</td>"
                            + "<td style=\"text-align: center;\">" + objTableDesc.getsStatus() + "</td></tr>";
                } else {
                    sMessage = sMessage + "<tr><td style=\"text-align: center; color: red\">" + Integer.toString(nstt) + "</td>"
                            + "<td style=\"text-align: center; color: red\">" + sPoscd + "</td>"
                            + "<td style=\"text-align: left; color: red\">" + objTableDesc.getsPosDesc() + "</td>"
                            + "<td style=\"text-align: right; color: red\">" + objTableDesc.getsSoKh() + "</td>"
                            + "<td style=\"text-align: right; color: red\">" + objTableDesc.getsTongtien() + "</td>"
                            + "<td style=\"text-align: right; color: red\">" + objTableDesc.getsTongDuno() + "</td>"
                            + "<td style=\"text-align: right; color: red\">" + objTableDesc.getsTongLai() + "</td>"
                            + "<td style=\"text-align: center;color: red\">" + objTableDesc.getsStatus() + "</td></tr>";
                }
                objTableDesc.setnStt(nstt);
                lstDescTable.add(objTableDesc);
                nstt++;
            }
            BigDecimal bTongtien = BigDecimal.ZERO;
            bTongtien = bTongtien.add(bTonggoc);
            bTongtien = bTongtien.add(bTonglai);
            DescTableBrower obj = new DescTableBrower();
            obj.setsPoscd("999999");
            obj.setsTongDuno(format.format(bTonggoc));
            obj.setsSoKh(format.format(bSokh));
            obj.setsTongLai(format.format(bTonglai));
            obj.setsTongtien(format.format(bTongtien));
            obj.setsPosDesc("Tổng cộng");
            lstDescTable.add(obj);
            sMessage = sMessage + "<tr><td colspan=\"3\" style=\"text-align: center; color: #007fff; font-weight: bold;\">Tổng cộng</td>\n"
                    + "<td style=\"text-align: right; color: #007fff; font-weight: bold;\">" + format.format(bSokh) + "</td>\n"
                    + "<td style=\"text-align: right; color: #007fff; font-weight: bold;\">" + format.format(bTongtien) + "</td>\n"
                    + "<td style=\"text-align: right; color: #007fff; font-weight: bold;\">" + format.format(bTonggoc) + "</td>\n"
                    + "<td style=\"text-align: right; color: #007fff; font-weight: bold;\">" + format.format(bTonglai) + "</td>";
            sMessage = sMessage + "</tr></table>";
            setSendData("OK");
            setLstBrowerView(lstDescTable);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " RejectRisk -> " + e.getMessage());
        }

        setMessage(sMessage);
        return SUCCESS;
    }

    public String loadBrowerdonvi() throws Exception {
        //Lay ra user tu sesstion
        //lay ra session
        Map session = ActionContext.getContext().getSession();

        if (session == null || session.size() == 0 || session.isEmpty()) {
            setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
            return ERROR;
        }
        //lay ra user
        String sUserName = session.get("username").toString();

//        System.err.println("execute sUserName=" + sUserName);

        if (sUserName == null || sUserName.isEmpty()) {
            setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
            return ERROR;
        }
        //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
        DaoProcessRisk daoRisk = new DaoProcessRisk();
        List<ModelTreeNode> lstModelTree = daoRisk.getDataPosTreeNode(sUserName);
        reportGrade = session.get("reportGrade").toString();
        if (reportGrade == null || reportGrade.isEmpty()) {
            setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
            return ERROR;
        }
        if (reportGrade.equals("3")) {
            setTreeNodeGrade3(lstModelTree);
        } else {
            setTreeNodeGrade12(lstModelTree);
        }

//        HashMap<Integer, List<ListValue>> hmDmKhac = daoRisk.getDmKhac();
//        lstNamXlrr = hmDmKhac.get(46) == null ? new ArrayList<ListValue>() : hmDmKhac.get(46);
//        lstDotXlrr = hmDmKhac.get(47) == null ? new ArrayList<ListValue>() : hmDmKhac.get(47);
//        lstTrangthaiXlrr = hmDmKhac.get(48) == null ? new ArrayList<ListValue>() : hmDmKhac.get(48);
//        lstNhomXlrr = hmDmKhac.get(49) == null ? new ArrayList<ListValue>() : hmDmKhac.get(49);
//        lstChuongtrinh = hmDmKhac.get(50) == null ? new ArrayList<ListValue>() : hmDmKhac.get(50);
//        lstNguonvon = hmDmKhac.get(51) == null ? new ArrayList<ListValue>() : hmDmKhac.get(51);
        setDmKhac();
        return SUCCESS;
    }
    
    public String loadBrowerdonvi62() throws Exception {
        //Lay ra user tu sesstion
        //lay ra session
        Map session = ActionContext.getContext().getSession();

        if (session == null || session.size() == 0 || session.isEmpty()) {
            setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
            return ERROR;
        }
        //lay ra user
        String sUserName = session.get("username").toString();

//        System.err.println("execute sUserName=" + sUserName);

        if (sUserName == null || sUserName.isEmpty()) {
            setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
            return ERROR;
        }
        //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
        DaoProcessRisk daoRisk = new DaoProcessRisk();
        List<ModelTreeNode> lstModelTree = daoRisk.getDataPosTreeNode_QD62(sUserName);
        reportGrade = session.get("reportGrade").toString();
        if (reportGrade == null || reportGrade.isEmpty()) {
            setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
            return ERROR;
        }
        if (reportGrade.equals("3")) {
            setTreeNodeGrade3(lstModelTree);
        } else {
            setTreeNodeGrade12(lstModelTree);
        }

//        HashMap<Integer, List<ListValue>> hmDmKhac = daoRisk.getDmKhac();
//        lstNamXlrr = hmDmKhac.get(46) == null ? new ArrayList<ListValue>() : hmDmKhac.get(46);
//        lstDotXlrr = hmDmKhac.get(47) == null ? new ArrayList<ListValue>() : hmDmKhac.get(47);
//        lstTrangthaiXlrr = hmDmKhac.get(48) == null ? new ArrayList<ListValue>() : hmDmKhac.get(48);
//        lstNhomXlrr = hmDmKhac.get(49) == null ? new ArrayList<ListValue>() : hmDmKhac.get(49);
//        lstChuongtrinh = hmDmKhac.get(50) == null ? new ArrayList<ListValue>() : hmDmKhac.get(50);
//        lstNguonvon = hmDmKhac.get(51) == null ? new ArrayList<ListValue>() : hmDmKhac.get(51);
        setDmKhacQD62();
        return SUCCESS;
    }

    public String loadDataBrowerView() {
//        String sMessage = "<h2 style=\"color: #029c44;>\">Thống kê danh sách khách hàng phê duyệt</h2> </br>"
//                + " <table border=\"1\" class=\"editDelete\"> "
//                + "<tr><th>Mã Đơn vị</th>"
//                + "<th>Tên Đơn vị</th>"
//                + "<th>Tổng số KH</th>"
//                + "<th>Tổng DN</th>"
//                + "<th>Tổng Lãi</th>"
//                + "<th>Trạng thái</th></tr> </table> ";
        try {
            if (vb_xlrr == null || vb_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn quyết định hoặc văn bản xử lý rủi ro !");
                return ERROR;
            }
            if (nam_xlrr == null || nam_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn năm xử lý rủi ro !");
                return ERROR;
            }
            if (dot_xlrr == null || dot_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn đợt xử lý rủi ro !");
                return ERROR;
            }

            if (nhom_xlrr == null || nhom_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn nhóm xử lý rủi ro !");
                return ERROR;
            }
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            if (poscd == null || poscd.isEmpty()) {
                setMessage("Bạn phải chọn đơn vị cần xem số liệu trên cây bên trái !");
                return ERROR;
            }
            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
            if (poscd != null && !poscd.isEmpty()) {
                poscd = poscd.replace(" ", "");
                //Dua tu chuoi pos ve dang mang
                ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
            }

            setLstBrowerView(new DaoProcessRisk().getDataBrowerView(sUserName, reportGrade,
                    ArrlstPosCd, nam_xlrr, dot_xlrr, nhom_xlrr, trangthai_xlrr, nguon_von, vb_xlrr));
//            System.err.println("poscd="+poscd);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadDataBrowerView -> " + e.getMessage());
        }
//        setMessage(sMessage);
        return SUCCESS;
    }
    
    public String loadDataBrowerView62() {
//        String sMessage = "<h2 style=\"color: #029c44;>\">Thống kê danh sách khách hàng phê duyệt</h2> </br>"
//                + " <table border=\"1\" class=\"editDelete\"> "
//                + "<tr><th>Mã Đơn vị</th>"
//                + "<th>Tên Đơn vị</th>"
//                + "<th>Tổng số KH</th>"
//                + "<th>Tổng DN</th>"
//                + "<th>Tổng Lãi</th>"
//                + "<th>Trạng thái</th></tr> </table> ";
        try {
            
            if (vb_xlrr == null || vb_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn quyết định hoặc văn bản xử lý rủi ro !");
                return ERROR;
            }
            if (nam_xlrr == null || nam_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn năm xử lý rủi ro !");
                return ERROR;
            }
            if (dot_xlrr == null || dot_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn đợt xử lý rủi ro !");
                return ERROR;
            }

            if (nhom_xlrr == null || nhom_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn nhóm xử lý rủi ro !");
                return ERROR;
            }
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            if (poscd == null || poscd.isEmpty()) {
                setMessage("Bạn phải chọn đơn vị cần xem số liệu trên cây bên trái !");
                return ERROR;
            }
            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
            if (poscd != null && !poscd.isEmpty()) {
                poscd = poscd.replace(" ", "");
                //Dua tu chuoi pos ve dang mang
                ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
            }
            capPheDuyet = new DaoProcessRisk().getCapPheduyetQD62(sUserName, reportGrade);
            
           
                
            if ((capPheDuyet ==2 ||capPheDuyet ==4) && trangthai_xlrr.equals("W"))
            {
                setLstBrowerView(new DaoProcessRisk().getDataBrowerView62(sUserName, reportGrade,
                    ArrlstPosCd, nam_xlrr, dot_xlrr, nhom_xlrr, trangthai_xlrr, nguon_von, vb_xlrr));
                return "ldXLN_view_2";
            }
            else if (capPheDuyet ==3 && trangthai_xlrr.equals("W"))
            {
                setLstBrowerView(new DaoProcessRisk().getDataBrowerView62(sUserName, reportGrade,
                    ArrlstPosCd, nam_xlrr, dot_xlrr, nhom_xlrr, trangthai_xlrr, nguon_von, vb_xlrr));
                
                styledisplay = new DaoProcessRisk().checkAutRisk(reportGrade, nam_xlrr, dot_xlrr,sUserName);
                return "cbXLN_view";
            }           
            else if ((capPheDuyet ==1 ||capPheDuyet ==2 ||capPheDuyet ==3||capPheDuyet ==4) && trangthai_xlrr.equals("K"))
            {
                lstDulieuNt50 = new DaoProcessRisk().getData_clech(sUserName, reportGrade,ArrlstPosCd, nam_xlrr, dot_xlrr, nhom_xlrr, trangthai_xlrr, nguon_von, vb_xlrr);
                return "ldXLN_view_k";
            }
            else if ((capPheDuyet ==1 ||capPheDuyet ==2 ||capPheDuyet ==3||capPheDuyet ==4) && trangthai_xlrr.equals("D"))
            {
                lstDulieuNt50 = new DaoProcessRisk().getData_clech(sUserName, reportGrade,ArrlstPosCd, nam_xlrr, dot_xlrr, nhom_xlrr, trangthai_xlrr, nguon_von, vb_xlrr);
                return "ldXLN_view_d";
            }
            else if ((capPheDuyet ==2 ||capPheDuyet ==4) && trangthai_xlrr.equals("T"))
            {
                setLstCanBo(new DaoNhaptaycnMain().getCanBo(sUserName, "CANBORR"));
                lstDulieuNt50 = new DaoProcessRisk().getData_clech(sUserName, reportGrade,ArrlstPosCd, nam_xlrr, dot_xlrr, nhom_xlrr, trangthai_xlrr, nguon_von, vb_xlrr);
                return "ldXLN_view_t";
            }
            else
            {
                setLstBrowerView(new DaoProcessRisk().getDataBrowerView62(sUserName, reportGrade,
                    ArrlstPosCd, nam_xlrr, dot_xlrr, nhom_xlrr, trangthai_xlrr, nguon_von, vb_xlrr));
                return "cbXLN_view";
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadDataBrowerView -> " + e.getMessage());
            return "cbXLN_view";
        }        
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Dien thong tin tu choi cho chi nhanh">
    public String loadDataViewRejectMainPos() {
        try {
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
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
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            nhom_xlrr = request.getParameter("nhom_xlrr");
            nguon_von = request.getParameter("nguon_von");
            poscd = request.getParameter("poscd");
            vb_xlrr = request.getParameter("vb_xlrr");

            if (vb_xlrr == null || vb_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn văn bản hoặc quyết định xử lý rủi ro !");
                return ERROR;
            }
            if (nam_xlrr == null || nam_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn năm xử lý rủi ro !");
                return ERROR;
            }
            if (dot_xlrr == null || dot_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn đợt xử lý rủi ro !");
                return ERROR;
            }

            if (nhom_xlrr == null || nhom_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn nhóm xử lý rủi ro !");
                return ERROR;
            }
            if (nguon_von == null || nguon_von.equals("-1")) {
                setMessage("Bạn phải chọn nguồn dữ liệu xử lý rủi ro !");
                return ERROR;
            }
            if (poscd == null || poscd.isEmpty()) {
                setMessage("Bạn phải chọn đơn vị cần xem số liệu trên cây bên trái !");
                return ERROR;
            }
            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
            if (poscd != null && !poscd.isEmpty()) {
                poscd = poscd.replace(" ", "");
                //Dua tu chuoi pos ve dang mang
                ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
            }

            setLstBrowerView(new DaoProcessRisk().getDataRejectMainPos(vb_xlrr, ArrlstPosCd, nam_xlrr, dot_xlrr, nhom_xlrr, nguon_von));
//            System.err.println("poscd="+poscd);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadDataViewRejectMainPos -> " + e.getMessage());
        }
//        setMessage(sMessage);
        return SUCCESS;
    }

    public String setContentRejectMainPos() {
        try {

            HttpServletRequest request = ServletActionContext.getRequest();
//            System.err.println("dunogoc_xl="+dunogoc_xl);
            List<String> ArrlstPosCd = new ArrayList<String>();
            poscd = request.getParameter("poscd");
            if (poscd != null && !poscd.isEmpty()) {
                poscd = poscd.replace(" ", "");
                //Dua tu chuoi pos ve dang mang
                ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
            }

            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                setResult_reject("error");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                setResult_reject("error");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                setResult_reject("error");
                return ERROR;
            }

            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            nhom_xlrr = request.getParameter("nhom_xlrr");
            nguon_von = request.getParameter("nguon_von");
            vb_xlrr = request.getParameter("vb_xlrr");
            if (new DaoProcessRisk().setContentRejectMainPos(vb_xlrr,sUserName, reportGrade, ArrlstPosCd, nam_xlrr, dot_xlrr, nhom_xlrr,
                    nguon_von, nguyennhan_tuchoi)) {
                setMessage("Bạn đã nhập nguyên nhân từ chối cho các chi nhánh thành công ");
//                return SUCCESS;
            } else {
                setMessage("Lỗi bạn liên hệ với quản trị để được hỗ trợ ");
                return ERROR;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " setContentRejectMainPos -> " + e.getMessage());

            return ERROR;
        }
        return SUCCESS;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Send Data TW">

    public String loadFormSendData() {
        try {
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
            DaoProcessRisk daoRisk = new DaoProcessRisk();
            List<ModelTreeNode> lstModelTree = daoRisk.getDataPosTreeNode(sUserName);

            //Khoi tao cho treenode
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

//            HashMap<Integer, List<ListValue>> hmDmKhac = daoRisk.getDmKhac();
//            lstNamXlrr = hmDmKhac.get(46) == null ? new ArrayList<ListValue>() : hmDmKhac.get(46);
//            lstDotXlrr = hmDmKhac.get(47) == null ? new ArrayList<ListValue>() : hmDmKhac.get(47);
//            lstTrangthaiXlrr = hmDmKhac.get(48) == null ? new ArrayList<ListValue>() : hmDmKhac.get(48);
//            lstNhomXlrr = hmDmKhac.get(49) == null ? new ArrayList<ListValue>() : hmDmKhac.get(49);
//            lstChuongtrinh = hmDmKhac.get(50) == null ? new ArrayList<ListValue>() : hmDmKhac.get(50);
//            lstNguonvon = hmDmKhac.get(51) == null ? new ArrayList<ListValue>() : hmDmKhac.get(51);
            setDmKhac();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadFormSendData -> " + e.getMessage());
        }
        return SUCCESS;
    }

    public String ViewDataSend() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            vb_xlrr = request.getParameter("vb_xlrr");
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            nhom_xlrr = request.getParameter("nhom_xlrr");
//            String[] arrposcd = request.getParameter("poscd");
            Map<String, String[]> mappos = request.getParameterMap();
            String[] arrposcd = mappos.get("poscd");
            if (nam_xlrr == null || nam_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn năm xử lý rủi ro !");
                return ERROR;
            }
            if (dot_xlrr == null || dot_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn đợt xử lý rủi ro !");
                return ERROR;
            }

            if (nhom_xlrr == null || nhom_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn nhóm xử lý rủi ro !");
                return ERROR;
            }
            if (vb_xlrr == null || vb_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn loại quyết định xử lý rủi ro !");
                return ERROR;
            }
            if (reportGrade.equals("1") || reportGrade.equals("3")) {
                setMessage("Cấp Trung ương và cấp phòng giao dịch không sử dụng được chức năng này ");
                return ERROR;
            }
            if (arrposcd == null || arrposcd.length == 0) {
                setMessage("Bạn phải chọn đơn vị cần xem dữ liệu ");
                return ERROR;
            }
            setLstBrowerView(new DaoProcessRisk().getDataViewSend(arrposcd, nam_xlrr, dot_xlrr, nhom_xlrr, nguon_von, vb_xlrr));
            setSendData("VIEW");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " ViewDataSend -> " + e.getMessage());
        }
        return SUCCESS;
    }
//    Gửi dữ liệu lên tw
    public String sendDataRisk() {
        String sMessage = "<table border=\"1\" class=\"editDelete\"> <tr><th>Phòng giao dịch</th><th>Mô Tả</th><th>Số khách hàng</th></tr>";
        try {
            if (nam_xlrr == null || nam_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn năm xử lý rủi ro !");
                return ERROR;
            }
            if (dot_xlrr == null || dot_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn đợt xử lý rủi ro !");
                return ERROR;
            }

            if (nhom_xlrr == null || nhom_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn nhóm xử lý rủi ro !");
                return ERROR;
            }
            if (nguon_von == null || nguon_von.equals("-1")) {
                setMessage("Bạn phải chọn nguồn vốn xử lý rủi ro !");
                return ERROR;
            }
            if (vb_xlrr == null || vb_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn loại quyết định xử lý rủi ro !");
                return ERROR;
            }
            System.err.println("Tao du lieu gui len chi nhanh");
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();

            //System.err.println("execute sUserName=" + sUserName);
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            if (reportGrade.equals("1") || reportGrade.equals("3")) {
                setMessage("Cấp này không được phép gửi dữ liệu, Chỉ cấp chi nhánh mới được gửi dữ liệu ");
                return ERROR;
            }
            if (poscd == null || poscd.isEmpty()) {
                setMessage("Bạn phải chọn phòng giao dịch cần gửi dữ liệu ");
                return ERROR;
            }
            DaoProcessRisk daoRisk = new DaoProcessRisk();
            //lay ra so pos cua chi nhanh do
            int nCountPos = daoRisk.getDataPosTreeNode(sUserName).size();
            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
            //poscd = request.getParameter("poscd");
            if (poscd != null && !poscd.isEmpty()) {
                poscd = poscd.replace(" ", "");
                //Dua tu chuoi pos ve dang mang
                ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
                ArrlstPosCd.remove("999999");
                System.err.println("So pos da chon " + ArrlstPosCd.size());
            }

            //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
            if (nCountPos != ArrlstPosCd.size()) {
                setMessage("Bạn phải chọn tất cả các phòng giao dịch của toàn chi nhánh để gửi số liệu ");
                return ERROR;
            }
            HashMap<String, DescTableBrower> hmTableDesc = daoRisk.getDataSendDetail(ArrlstPosCd, nam_xlrr, dot_xlrr, nhom_xlrr, nguon_von, vb_xlrr);
            List<DescTableBrower> lstObjtable = new ArrayList<DescTableBrower>();
            for (String sPoscd : ArrlstPosCd) {
                if (sPoscd.equals("999999")) {
                    continue;
                }
                DescTableBrower objtable = hmTableDesc.get(sPoscd);
                if (objtable == null) {
                    objtable = new DescTableBrower();
//                    continue;
                }
                List<ListRiskSync> lstRisk = daoRisk.getDataSend(sUserName, reportGrade,
                        sPoscd, nam_xlrr, dot_xlrr, nhom_xlrr, nguon_von, vb_xlrr);
                if (lstRisk.size() == 0 || lstRisk.isEmpty()) {
                    sMessage = sMessage + "<tr><td style=\"text-align: center;\">" + sPoscd + "</td><td style=\"text-align: center;\">Thành công</td><td style=\"text-align: center;\">" + lstRisk.size() + "</td></tr>";
                    objtable.setbSuccess(true);
//                    continue;
                }
                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/")
                        ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : context.getRealPath("/") + Define.M_REPORT_XML;
                strPathSave += sUserName + "_XLRR_" + sPoscd + "_" + Long.toString(System.currentTimeMillis()) + ".xml";
                //Tao file xml theo cau truc
                new ImsReadWriteXmlFile().createXmlFileRisk(Define.PARA_SYN_REPORT_XLRR, sUserName, reportGrade,
                        sPoscd, nam_xlrr, dot_xlrr, nhom_xlrr, lstRisk, strPathSave, vb_xlrr);
                File checkfile = new File(strPathSave);
                ProcessReportSyn clientWritexml = new ProcessReportSyn();
                String sStatus = clientWritexml.SendFileXmlToWebServices(strPathSave);
                if (sStatus.equals(Define.WEB_SERVICES_STATUS_FAIL)) {
                    System.err.println("Ban chua dong bo du lieu duoc ve TW");
                    setMessage("Bạn chưa gửi dữ liệu được về trung ương lỗi pos " + poscd);
                    sMessage = sMessage + "<tr><td style=\"text-align: center; color: red\">" + sPoscd + "</td><td style=\"text-align: center; color: red\">Gửi dữ liệu lỗi</td><td style=\"text-align: center;color: red\">" + lstRisk.size() + "</td></tr>";
                    objtable.setbSuccess(false);
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                } else if (sStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
                    sMessage = sMessage + "<tr><td style=\"text-align: center;\">" + sPoscd + "</td><td style=\"text-align: center;\">Thành công</td><td style=\"text-align: center;\">" + lstRisk.size() + "</td></tr>";
                    objtable.setbSuccess(true);
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                } else {
                    setMessage("Bạn không thể gửi dữ liệu lên trung ương do bị khóa </br>Xin gọi điện về ban rủi ro để được gửi lại số liệu ! ");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    return ERROR;
                }
                if (lstRisk.size() > 0) {
                    lstObjtable.add(objtable);
                }
            }
            lstObjtable.add(hmTableDesc.get("999999"));
            //#000000
            sMessage = sMessage + "</table>";
            setLstBrowerView(lstObjtable);
            setSendData("OK");
//            if (ArrlstPosCd.size() > 0) {
//                daoRisk.insertHistotySendLog(ArrlstPosCd.get(0), sUserName, reportGrade, nam_xlrr, dot_xlrr, nhom_xlrr,
//                        new BigDecimal(ArrlstPosCd.size()),Define.KHOA_CHECK_RR);
//            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " sendDataRisk -> " + e.getMessage());
            return ERROR;
        }
        //System.err.println("Da gui du lieu thanh cong");
        setMessage(sMessage);
        //setMessage("<span style=\"color:yellow\">Bạn đã gửi dữ liệu thành công của Phòng giao dịch </br>" + poscd+"</span>");
        return SUCCESS;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Thiet lap gui nhan du lieu tu CN -> TW">
    public String loadHistorySendData() {
        try {
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
            DaoProcessRisk daoRisk = new DaoProcessRisk();
            List<ModelTreeNode> lstModelTree = daoRisk.getDataPosTreeNode(sUserName);

            // HashMap<Integer, List<ListValue>> hmDmKhac = daoRisk.getDmKhac();
//            lstNamXlrr = hmDmKhac.get(46) == null ? new ArrayList<ListValue>() : hmDmKhac.get(46);
//            lstDotXlrr = hmDmKhac.get(47) == null ? new ArrayList<ListValue>() : hmDmKhac.get(47);
////            lstTrangthaiXlrr = hmDmKhac.get(48) == null ? new ArrayList<ListValue>() : hmDmKhac.get(48);
//            lstNhomXlrr = hmDmKhac.get(49) == null ? new ArrayList<ListValue>() : hmDmKhac.get(49);
//            lstChuongtrinh = hmDmKhac.get(50) == null ? new ArrayList<ListValue>() : hmDmKhac.get(50);
//            lstNguonvon = hmDmKhac.get(51) == null ? new ArrayList<ListValue>() : hmDmKhac.get(51);
            setDmKhac();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadHistorySendData -> " + e.getMessage());
        }
        return SUCCESS;
    }

    public String loadHistoryView() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }

            if (vb_xlrr == null || vb_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn văn bản hoặc quyết định xử lý rủi ro !");
                return ERROR;
            }
            if (nam_xlrr == null || nam_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn năm xử lý rủi ro !");
                return ERROR;
            }
            if (dot_xlrr == null || dot_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn đợt xử lý rủi ro !");
                return ERROR;
            }

            if (nhom_xlrr == null || nhom_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn nhóm xử lý rủi ro !");
                return ERROR;
            }

            if (reportGrade.equals("1") || reportGrade.equals("2")) {
                setMessage("Cấp Chi nhánh và cấp phòng giao dịch không sử dụng được chức năng này ");
                return ERROR;
            }
            setLstModelHist(new DaoProcessRisk().getStatusHistorySendData(nam_xlrr, dot_xlrr, nhom_xlrr, vb_xlrr));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadHistorySendData -> " + e.getMessage());
        }
        return SUCCESS;
    }

    public String setBlockSend() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }

            if (nam_xlrr == null || nam_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn năm xử lý rủi ro !");
                return ERROR;
            }
            if (dot_xlrr == null || dot_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn đợt xử lý rủi ro !");
                return ERROR;
            }

            if (nhom_xlrr == null || nhom_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn nhóm xử lý rủi ro !");
                return ERROR;
            }

            if (reportGrade.equals("1") || reportGrade.equals("2")) {
                setMessage("Cấp Chi nhánh và cấp phòng giao dịch không sử dụng được chức năng này ");
                return ERROR;
            }
//            setLstModelHist(new DaoProcessRisk().getStatusHistorySendData(nam_xlrr, dot_xlrr, nhom_xlrr));
            new DaoProcessRisk().setStatusSendDataHistory(macn.get(0), sUserName, reportGrade, nam_xlrr, dot_xlrr, nhom_xlrr, Define.KHOA_CHECK_RR, vb_xlrr);
            setMessage("Bạn đã khóa pos " + macn.get(0) + " Thành công");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " setBlockSend -> " + e.getMessage());
        }
        return SUCCESS;
    }

    public String ViewHistorySend() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            Map session = ActionContext.getContext().getSession();

            String macn = request.getParameter("macn");
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            nhom_xlrr = request.getParameter("nhom_xlrr");
            vb_xlrr = request.getParameter("vb_xlrr");

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            if (vb_xlrr == null || vb_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn văn bản hoặc quyết định xử lý rủi ro !");
                return ERROR;
            }
            if (nam_xlrr == null || nam_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn năm xử lý rủi ro !");
                return ERROR;
            }
            if (macn == null || macn.equals("-1")) {
                setMessage("Không thể lấy ra được mã chi nhánh !");
                return ERROR;
            }
            if (dot_xlrr == null || dot_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn đợt xử lý rủi ro !");
                return ERROR;
            }

            if (nhom_xlrr == null || nhom_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn nhóm xử lý rủi ro !");
                return ERROR;
            }

            if (reportGrade.equals("1") || reportGrade.equals("2")) {
                setMessage("Cấp Chi nhánh và cấp phòng giao dịch không sử dụng được chức năng này ");
                return ERROR;
            }
            setLstViewHistorySend(new DaoProcessRisk().getViewHistorySend(macn, nam_xlrr, dot_xlrr, nhom_xlrr, "SEND", vb_xlrr));
//            new DaoProcessRisk().setStatusSendDataHistory(macn.get(0), sUserName, reportGrade, nam_xlrr, dot_xlrr, nhom_xlrr, Define.KHOA_CHECK_RR);
//            setMessage("Bạn đã khóa pos " + macn.get(0) + " Thành công");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " setBlockSend -> " + e.getMessage());
        }
        return SUCCESS;
    }

    public String setOpenSend() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }

            if (nam_xlrr == null || nam_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn năm xử lý rủi ro !");
                return ERROR;
            }
            if (dot_xlrr == null || dot_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn đợt xử lý rủi ro !");
                return ERROR;
            }

            if (nhom_xlrr == null || nhom_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn nhóm xử lý rủi ro !");
                return ERROR;
            }

            if (reportGrade.equals("1") || reportGrade.equals("2")) {
                setMessage("Cấp Chi nhánh và cấp phòng giao dịch không sử dụng được chức năng này ");
                return ERROR;
            }
//            setLstModelHist(new DaoProcessRisk().getStatusHistorySendData(nam_xlrr, dot_xlrr, nhom_xlrr));
            new DaoProcessRisk().setStatusSendDataHistory(macn.get(0), sUserName, reportGrade, nam_xlrr, dot_xlrr, nhom_xlrr, Define.KHOA_UNCHECK_RR, vb_xlrr);
            setMessage("Bạn đã mở khóa pos " + macn.get(0) + " Thành công");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " setOpenSend -> " + e.getMessage());
        }
        return SUCCESS;
    }

    public String setBlockSendAllPos() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }

            if (nam_xlrr == null || nam_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn năm xử lý rủi ro !");
                return ERROR;
            }
            if (dot_xlrr == null || dot_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn đợt xử lý rủi ro !");
                return ERROR;
            }

            if (nhom_xlrr == null || nhom_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn nhóm xử lý rủi ro !");
                return ERROR;
            }

            if (reportGrade.equals("1") || reportGrade.equals("2")) {
                setMessage("Cấp Chi nhánh và cấp phòng giao dịch không sử dụng được chức năng này ");
                return ERROR;
            }
//            setLstModelHist(new DaoProcessRisk().getStatusHistorySendData(nam_xlrr, dot_xlrr, nhom_xlrr));
            boolean bSuccess = new DaoProcessRisk().setBlockAllPos(macn, sUserName, reportGrade, nam_xlrr, dot_xlrr, nhom_xlrr, Define.KHOA_CHECK_RR, vb_xlrr);
            if (bSuccess) {
                setMessage("Bạn đã khóa chi nhánh Thành công");
            } else {
                setMessage("Khóa pos bị lỗi xin liên hệ quản trị để khắc phục");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " setBlockSendAllPos -> " + e.getMessage());
        }
        return SUCCESS;
    }

    public String setOpenAllPos() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            nam_xlrr = request.getParameter("nam_xlrr");
            dot_xlrr = request.getParameter("dot_xlrr");
            nhom_xlrr = request.getParameter("nhom_xlrr");
            vb_xlrr = request.getParameter("vb_xlrr");
            List<String> lstMacn = new ArrayList<String>();

            Map<String, String[]> mappos = request.getParameterMap();
            String[] arrMacn = mappos.get("macn");

//            String sMacn = request.getParameter("macn");
            if (arrMacn != null && arrMacn.length > 0) {
//                sMacn = sMacn.replace(" ", "");
                //Dua tu chuoi pos ve dang mang
                lstMacn = new ArrayList<String>(Arrays.asList(arrMacn));
            }
            if (nam_xlrr == null || nam_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn năm xử lý rủi ro !");
                return ERROR;
            }
            if (dot_xlrr == null || dot_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn đợt xử lý rủi ro !");
                return ERROR;
            }

            if (nhom_xlrr == null || nhom_xlrr.equals("-1")) {
                setMessage("Bạn phải chọn nhóm xử lý rủi ro !");
                return ERROR;
            }

            if (reportGrade.equals("1") || reportGrade.equals("2")) {
                setMessage("Cấp Chi nhánh và cấp phòng giao dịch không sử dụng được chức năng này ");
                return ERROR;
            }
//            setLstModelHist(new DaoProcessRisk().getStatusHistorySendData(nam_xlrr, dot_xlrr, nhom_xlrr));
            boolean bSuccess = new DaoProcessRisk().setBlockAllPos(lstMacn, sUserName, reportGrade, nam_xlrr, dot_xlrr, nhom_xlrr, Define.KHOA_UNCHECK_RR, vb_xlrr);
            if (bSuccess) {
                setMessage("Bạn đã mở chi nhánh Thành công");
            } else {
                setMessage("Mở pos bị lỗi xin liên hệ quản trị để khắc phục");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " setOpenAllPos -> " + e.getMessage());
        }
        return SUCCESS;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Thiet lap phuong thuc get, set">

    public List<ListValue> getLstNguyenNhanRR() {
        return lstNguyenNhanRR;
    }

    public void setLstNguyenNhanRR(List<ListValue> lstNguyenNhanRR) {
        this.lstNguyenNhanRR = lstNguyenNhanRR;
    }
    

    public List<ListValue> getLstCanBo() {
        return lstCanBo;
    }

    public void setLstCanBo(List<ListValue> lstCanBo) {
        this.lstCanBo = lstCanBo;
    }

 
    public String getDbHt_Dno() {
        return dbHt_Dno;
    }

    public void setDbHt_Dno(String dbHt_Dno) {
        this.dbHt_Dno = dbHt_Dno;
    }

    public String getDbHt_Lai() {
        return dbHt_Lai;
    }

    public void setDbHt_Lai(String dbHt_Lai) {
        this.dbHt_Lai = dbHt_Lai;
    }
    
        
    public String getsTenkh() {
        return sTenkh;
    }

    public void setsTenkh(String sTenkh) {
        this.sTenkh = sTenkh;
    }

    public String getsNgayvay() {
        return sNgayvay;
    }

    public void setsNgayvay(String sNgayvay) {
        this.sNgayvay = sNgayvay;
    }

    public String getDbMdthiethai() {
        return dbMdthiethai;
    }

    public void setDbMdthiethai(String dbMdthiethai) {
        this.dbMdthiethai = dbMdthiethai;
    }

    public String getsNgayrr() {
        return sNgayrr;
    }

    public void setsNgayrr(String sNgayrr) {
        this.sNgayrr = sNgayrr;
    }

    public String getDbDnghi_Tg() {
        return dbDnghi_Tg;
    }

    public void setDbDnghi_Tg(String dbDnghi_Tg) {
        this.dbDnghi_Tg = dbDnghi_Tg;
    }

    public String getDbPduyet_Tg() {
        return dbPduyet_Tg;
    }

    public void setDbPduyet_Tg(String dbPduyet_Tg) {
        this.dbPduyet_Tg = dbPduyet_Tg;
    }
    
    
    public String getSoku() {
        return soku;
    }

    public void setSoku(String soku) {
        this.soku = soku;
    }
    
    public List<ListValue> getLstVbXlrr() {
        return lstVbXlrr;
    }

    public void setLstVbXlrr(List<ListValue> lstVbXlrr) {
        this.lstVbXlrr = lstVbXlrr;
    }

    public List<ListRisk> getLstRisk() {
        return lstRisk;
    }

    public void setLstRisk(List<ListRisk> lstRisk) {
        this.lstRisk = lstRisk;
    }

    public List<String> getMacn() {
        return macn;
    }

    public void setMacn(List<String> macn) {
        this.macn = macn;
    }

    public List<StatusHistorySend> getLstModelHist() {
        return lstModelHist;
    }

    public void setLstModelHist(List<StatusHistorySend> lstModelHist) {
        this.lstModelHist = lstModelHist;
    }

    public String getNguyennhan_tuchoi() {
        return nguyennhan_tuchoi;
    }

    public void setNguyennhan_tuchoi(String nguyennhan_tuchoi) {
        this.nguyennhan_tuchoi = nguyennhan_tuchoi;
    }

    public String getResult_reject() {
        return result_reject;
    }

    public void setResult_reject(String result_reject) {
        this.result_reject = result_reject;
    }

    public List<DescTableBrower> getLstBrowerView() {
        return lstBrowerView;
    }

    public void setLstBrowerView(List<DescTableBrower> lstBrowerView) {
        this.lstBrowerView = lstBrowerView;
    }

    public List<ListValue> getLstNguonvon() {
        return lstNguonvon;
    }

    public String getSendData() {
        return sendData;
    }

    public void setSendData(String sendData) {
        this.sendData = sendData;
    }

    public void setLstNguonvon(List<ListValue> lstNguonvon) {
        this.lstNguonvon = lstNguonvon;
    }

    public String getDefaultNamxlrr() {
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    }

    public void setDefaultNamxlrr(String defaultNamxlrr) {
        this.defaultNamxlrr = defaultNamxlrr;
    }

    public List<String> getDunogoc_xl() {
        return dunogoc_xl;
    }

    public void setDunogoc_xl(List<String> dunogoc_xl) {
        this.dunogoc_xl = dunogoc_xl;
    }

    public String getSoku_reject() {
        return soku_reject;
    }

    public void setSoku_reject(String soku_reject) {
        this.soku_reject = soku_reject;
    }

    public String getReportGrade() {
        return reportGrade;
    }

    public void setReportGrade(String reportGrade) {
        this.reportGrade = reportGrade;
    }

    public List<ModelRiskProcess> getLstTableRiskObj() {
        return lstTableRiskObj;
    }

    public void setLstTableRiskObj(List<ModelRiskProcess> lstTableRiskObj) {
        this.lstTableRiskObj = lstTableRiskObj;
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

    public String getPoscd() {
        return poscd;
    }

    public void setPoscd(String poscd) {
        this.poscd = poscd;
    }

    public String getDot_xlrr() {
        return dot_xlrr;
    }

    public void setDot_xlrr(String dot_xlrr) {
        this.dot_xlrr = dot_xlrr;
    }

    public String getTrangthai_xlrr() {
        return trangthai_xlrr;
    }

    public void setTrangthai_xlrr(String trangthai_xlrr) {
        this.trangthai_xlrr = trangthai_xlrr;
    }

    public String getNhom_xlrr() {
        return nhom_xlrr;
    }

    public void setNhom_xlrr(String nhom_xlrr) {
        this.nhom_xlrr = nhom_xlrr;
    }

    public String getNam_xlrr() {
        return nam_xlrr;
    }

    public void setNam_xlrr(String nam_xlrr) {
        this.nam_xlrr = nam_xlrr;
    }

    public String getChuongtrinh() {
        return chuongtrinh;
    }

    public void setChuongtrinh(String chuongtrinh) {
        this.chuongtrinh = chuongtrinh;
    }

    public List<ListValue> getLstNamXlrr() {
        return lstNamXlrr;
    }

    public void setLstNamXlrr(List<ListValue> lstNamXlrr) {
        this.lstNamXlrr = lstNamXlrr;
    }

    public List<ListValue> getLstNhomXlrr() {
        return lstNhomXlrr;
    }

    public void setLstNhomXlrr(List<ListValue> lstNhomXlrr) {
        this.lstNhomXlrr = lstNhomXlrr;
    }

    public List<ListValue> getLstDotXlrr() {
        return lstDotXlrr;
    }

    public void setLstDotXlrr(List<ListValue> lstDotXlrr) {
        this.lstDotXlrr = lstDotXlrr;
    }

    public List<ListValue> getLstTrangthaiXlrr() {
        return lstTrangthaiXlrr;
    }

    public void setLstTrangthaiXlrr(List<ListValue> lstTrangthaiXlrr) {
        this.lstTrangthaiXlrr = lstTrangthaiXlrr;
    }

    public List<ListValue> getLstChuongtrinh() {
        return lstChuongtrinh;
    }

    public void setLstChuongtrinh(List<ListValue> lstChuongtrinh) {
        this.lstChuongtrinh = lstChuongtrinh;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public ModelRiskProcess getModelRisk() {
        return modelRisk;
    }

    public void setModelRisk(ModelRiskProcess modelRisk) {
        this.modelRisk = modelRisk;
    }

    @Override
    public ModelRiskProcess getModel() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return modelRisk;
    }

//    public String getCheck_legacyid() {
//        return check_legacyid;
//    }
//    
//    public void setCheck_legacyid(String check_legacyid) {
//        this.check_legacyid = check_legacyid;
//    }
    public List<String> getCheck_legacyid() {
        return check_legacyid;
    }

    public void setCheck_legacyid(List<String> check_legacyid) {
        this.check_legacyid = check_legacyid;
    }

    public String getNguon_von() {
        return nguon_von;
    }

    public void setNguon_von(String nguon_von) {
        this.nguon_von = nguon_von;
    }

    private String defaultNguonvon;

    public String getDefaultNguonvon() {
        return "1";
    }

    public void setDefaultNguonvon(String defaultNguonvon) {
        this.defaultNguonvon = defaultNguonvon;
    }

    public String getSearch_soku() {
        return search_soku;
    }

    public void setSearch_soku(String search_soku) {
        this.search_soku = search_soku;
    }

    public List<HistorySendData> getLstViewHistorySend() {
        return lstViewHistorySend;
    }

    public void setLstViewHistorySend(List<HistorySendData> lstViewHistorySend) {
        this.lstViewHistorySend = lstViewHistorySend;
    }

    public String getVb_xlrr() {
        return vb_xlrr;
    }

    public void setVb_xlrr(String vb_xlrr) {
        this.vb_xlrr = vb_xlrr;
    }
    
        public int getCapPheDuyet() {
        return capPheDuyet;
    }

    public void setCapPheDuyet(int capPheDuyet) {
        this.capPheDuyet = capPheDuyet;
    }
    
    
//</editor-fold>

    public String getStyledisplay() {
        return styledisplay;
    }

    public void setStyledisplay(String styledisplay) {
        this.styledisplay = styledisplay;
    }


    public String xacnhansolieu(){
        
        Map session = ActionContext.getContext().getSession();
        String sUserName = session.get("username").toString();
        reportGrade = session.get("reportGrade").toString();
        ArrayList<String> ArrlstPosCd = new ArrayList<String>();
        poscd = poscd.replace(" ", "");
        //Dua tu chuoi pos ve dang mang
        ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
        String Code = new DaoProcessRisk().AUTDataRisk(sUserName, reportGrade, nam_xlrr, dot_xlrr);
        pageResult = new ByteArrayInputStream(Code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
        
    }

    public InputStream getPageResult() {
        return pageResult;
    }

    public void setPageResult(InputStream pageResult) {
        this.pageResult = pageResult;
    }

    
}
