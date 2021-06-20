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
import java.math.BigInteger;
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
import vbsp.ims.dao.DaoProcessRisk;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.DcptNoModel;
import vbsp.ims.model.DcptNoModel.ViewTotalCust;
import vbsp.ims.model.DcptNoModel.saveDcNo;
import vbsp.ims.model.DcptNoModel.savePtNo;
import vbsp.ims.model.DcptNoModel.senddcpt;
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
public class DcptNoAction extends ActionSupport {

    //<editor-fold defaultstate="collapsed" desc="Khai bao cac bien">
    //Khoi tao cho treeview
    private TreeNode nodes_pos = new TreeNode();
    //Cho message thong bao loi
    private String message;
    //Lay pos tren treeview lstSoku
    private List<String> poscd = new ArrayList<String>();
    private List<String> lstSoku = new ArrayList<String>();
    private String ngay_dcpt;

    String dvut_dcpt = null;
    String macn_dcpt = null;
    String mapgd_dcpt = null;
    String maxa_dcpt = null;

    public String getMacn_dcpt() {
        return macn_dcpt;
    }

    public void setMacn_dcpt(String macn_dcpt) {
        this.macn_dcpt = macn_dcpt;
    }

    public String getMapgd_dcpt() {
        return mapgd_dcpt;
    }

    public void setMapgd_dcpt(String mapgd_dcpt) {
        this.mapgd_dcpt = mapgd_dcpt;
    }

    public String getMaxa_dcpt() {
        return maxa_dcpt;
    }

    public void setMaxa_dcpt(String maxa_dcpt) {
        this.maxa_dcpt = maxa_dcpt;
    }
    String totruong_dcpt = null;
    String nguon_von;
    String chuongtrinh;

    private List<ListValue> lstMaCN = new ArrayList<ListValue>();        
    private List<ListValue> lstMaPGD = new ArrayList<ListValue>(); 
    private List<ListValue> lstMaXa = new ArrayList<ListValue>(); 
    private List<ModelRiskProcess.StatusHistorySend> lstModelHist;   
    private List<ListValue> lstNamXlrr = new ArrayList<ListValue>();
    private String defaultNamxlrr;
    private List<ListValue> lstTrangthaiXlrr = new ArrayList<ListValue>();
    private List<ListValue> lstNhomXlrr = new ArrayList<ListValue>();
    private List<ListValue> lstDotXlrr = new ArrayList<ListValue>();
    private List<ListValue> lstDvutDcpt = new ArrayList<ListValue>();
    private List<ListValue> lstTotruongDcpt = new ArrayList<ListValue>();
    private List<ListValue> lstChuongtrinh = new ArrayList<ListValue>();
    private List<ListValue> lstNguonvon = new ArrayList<ListValue>();
    private List<ListValue> lstNgnhanDm = new ArrayList<ListValue>();
    private List<DcptNoModel> lstModelDcptNo = new ArrayList<DcptNoModel>();
    private List<senddcpt> lstsenddcpt = new ArrayList<senddcpt>();

    private List<saveDcNo> lstsaveDcno = new ArrayList<saveDcNo>();
    private List<savePtNo> lstsavePtno = new ArrayList<savePtNo>();

    private List<ViewTotalCust> lstViewTotalCust = new ArrayList<ViewTotalCust>();

    private String reportGrade;
    private List<String> macn;

    public List<String> getMacn() {
        return macn;
    }

    public void setMacn(List<String> macn) {
        this.macn = macn;
    }

    private Pagination pagination = new Pagination(50, 1);

    private String soku;
    private String ma_nguyennhan;
    private String trangthai;
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Khoi tao cho phan danh muc va tree view">
    public boolean setDmKhac() {
        try {
            DaoDcptNo daoRisk = new DaoDcptNo();
            HashMap<Integer, List<ListValue>> hmDmKhac = daoRisk.getDmKhac();

            lstDvutDcpt = hmDmKhac.get(53) == null ? new ArrayList<ListValue>() : hmDmKhac.get(53);
            lstChuongtrinh = hmDmKhac.get(54) == null ? new ArrayList<ListValue>() : hmDmKhac.get(54);
            lstNguonvon = hmDmKhac.get(55) == null ? new ArrayList<ListValue>() : hmDmKhac.get(55);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " setDmKhac -> " + e.getMessage());
            return false;
        }
//        return true;
    }
    
    public boolean setDmKhac1() {
        try {
            DaoProcessRisk daoRisk = new DaoProcessRisk();
            HashMap<Integer, List<ListValue>> hmDmKhac = daoRisk.getDmKhac();
            lstNamXlrr = hmDmKhac.get(50) == null ? new ArrayList<ListValue>() : hmDmKhac.get(50);
            lstDotXlrr = hmDmKhac.get(51) == null ? new ArrayList<ListValue>() : hmDmKhac.get(51);
            lstTrangthaiXlrr = hmDmKhac.get(52) == null ? new ArrayList<ListValue>() : hmDmKhac.get(52);
            lstNhomXlrr = hmDmKhac.get(53) == null ? new ArrayList<ListValue>() : hmDmKhac.get(53);
            lstChuongtrinh = hmDmKhac.get(54) == null ? new ArrayList<ListValue>() : hmDmKhac.get(54);
            lstNguonvon = hmDmKhac.get(55) == null ? new ArrayList<ListValue>() : hmDmKhac.get(55);
            lstMaCN = hmDmKhac.get(56) == null ? new ArrayList<ListValue>() : hmDmKhac.get(56);
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
            setDmKhac();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " execute -> " + e.getMessage());
        }

        return SUCCESS;
    }

    public String getTotruong() {
        try {
            System.err.println("Vao ham getTotruong");
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
            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
            if (arrposcd == null || arrposcd.length == 0) {
                setMessage("Bạn phải chọn chi nhánh cần phê duyệt dữ liệu ");
//                return ERROR;
            } else {
                ArrlstPosCd = new ArrayList<String>(Arrays.asList(arrposcd));
                ArrlstPosCd.remove("999999");
            }

            setDmKhac();
            //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
            dvut_dcpt = request.getParameter("dvut_dcpt");
            if (dvut_dcpt == null || dvut_dcpt.isEmpty() || dvut_dcpt.equals("-1")) {
//                System.err.println("dvut_dcpt la null " + dvut_dcpt);
                return SUCCESS;
            }

            DaoDcptNo daoRisk = new DaoDcptNo();

            //Khoi tao cho treenode
//            System.err.println("dvut_dcpt=" + dvut_dcpt + " poscd=" + ArrlstPosCd.size());
            //neu don vi uy thac khong phai la truc tiep thi moi load ma to truong hoac du an
            if (!dvut_dcpt.equals("1")) {
                setLstTotruongDcpt(daoRisk.getTotruong(sUserName, reportGrade, ArrlstPosCd, dvut_dcpt));
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getTotruong -> " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String getPGD() {
        try {
            System.err.println("Vao ham getPGD");
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

            setDmKhac1();
            //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
            macn_dcpt = request.getParameter("macn_dcpt");
            if (macn_dcpt == null || macn_dcpt.isEmpty() || macn_dcpt.equals("-1")) {
                return SUCCESS;
            }
            DaoDcptNo daoRisk = new DaoDcptNo();
            if (!macn_dcpt.equals("1")) {
                setLstMaPGD(daoRisk.getPGD(macn_dcpt));
            }
            
            mapgd_dcpt = request.getParameter("mapgd_dcpt");
            if (mapgd_dcpt == null || mapgd_dcpt.isEmpty() || mapgd_dcpt.equals("-1")) {
                return SUCCESS;
            }
            if (!mapgd_dcpt.equals("1")) {
                setLstMaXa(daoRisk.getXa(mapgd_dcpt));
            }
            

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getPGD -> " + e.getMessage());
        }
        return SUCCESS;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="load dữ liệu cho dc no">
    public String getDataDcNo() {
        try {
            System.err.println("vao ham getDataDcNo");
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
            DaoDcptNo daoDcptNo = new DaoDcptNo();
            if ((dvut_dcpt == null && totruong_dcpt == null) || (dvut_dcpt.equals("-1") && totruong_dcpt.equals("-1"))) {
                setMessage("Bạn phải chọn đơn vị uy thác hoặc tổ trưởng ! ");
                return ERROR;
            }
            if (dvut_dcpt == null || dvut_dcpt.isEmpty() || dvut_dcpt.equals("-1")) {
                setMessage("Bạn phải chọn đơn vị uy thác ! ");
                return ERROR;
            }
            //neu chon don vi uy thac la truc tiep
            if (!dvut_dcpt.equals("1")) {
                //se kiem tra xem to truong da chon chua
                if (totruong_dcpt == null || totruong_dcpt.isEmpty() || totruong_dcpt.equals("-1")) {
                    setMessage("Bạn phải chọn tổ trưởng cần xem dữ liệu! ");
                    return ERROR;
                }
            } else {
                //gan to truong chon la null
                totruong_dcpt = null;
            }

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
            System.err.println("trangthai="+trangthai);
            if (pagination.getStart() == 0) {
                int nCountCust = daoDcptNo.getCountTotalCustData(conn, sUserName, reportGrade,
                        sNgaySl, poscd, dvut_dcpt, totruong_dcpt, nguon_von, chuongtrinh,trangthai);
                int size=(int) Math.ceil((double)nCountCust / 10);
                pagination.setPage_size(size*10);
                pagination.setPreperties(nCountCust);
                
            }
            setLstModelDcptNo(daoDcptNo.getDataCust(conn, sUserName, reportGrade, sNgaySl,
                    poscd, dvut_dcpt, totruong_dcpt, nguon_von, chuongtrinh,trangthai,
                    pagination.getStart() + 1, pagination.getStart() + pagination.getEnd()));

            pagination.setPage_records(lstModelDcptNo.size());
            setLstViewTotalCust(daoDcptNo.getViewTotalCustData(conn, sUserName, reportGrade,
                    sNgaySl, poscd, dvut_dcpt, totruong_dcpt, nguon_von, chuongtrinh, trangthai));

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
    
    public String loadHistorySendDcpt() {  
        System.err.println("vao ham loadHistorySendDcpt");
        setDmKhac1();
        return SUCCESS;
    }
    
    public String loadHistoryViewDcpt() {      
        try {
            System.err.println("vao ham loadHistoryViewDcpt");
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

            if (reportGrade.equals("1") || reportGrade.equals("2")) {
                setMessage("Cấp Chi nhánh và cấp phòng giao dịch không sử dụng được chức năng này ");
                return ERROR;
            }
            macn_dcpt = request.getParameter("macn_dcpt");
            mapgd_dcpt = request.getParameter("mapgd_dcpt");
            maxa_dcpt = request.getParameter("maxa_dcpt");
            setLstModelHist(new DaoDcptNo().getStatusHistorySendDcpt(macn_dcpt, mapgd_dcpt, maxa_dcpt));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadHistorySendData -> " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String setOpenSendDcpt() {
        try {
             System.err.println("vao ham setOpenSendDcpt");
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

            
//            setLstModelHist(new DaoProcessRisk().getStatusHistorySendData(nam_xlrr, dot_xlrr, nhom_xlrr));
//            new DaoProcessRisk().setStatusSendDataHistory(macn.get(0), sUserName, reportGrade, nam_xlrr, dot_xlrr, nhom_xlrr, Define.KHOA_UNCHECK_RR);
            setMessage("Bạn đã mở khóa pos " + macn.get(0) + " Thành công");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " setOpenSend -> " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String setBlockSendAllPosDcpt() {
        try {
            System.err.println("vao ham setBlockSendAllPosDcpt");
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
            List<String> lstMacn = new ArrayList<>();

            for (ModelRiskProcess.StatusHistorySend value : lstModelHist) {
                if (!value.getsMacn().equals("false")) {
                    lstMacn.add(value.sMacn);
                }
            }
            
            
//            setLstModelHist(new DaoProcessRisk().getStatusHistorySendData(nam_xlrr, dot_xlrr, nhom_xlrr));
            boolean bSuccess =  new DaoDcptNo().setBlockAllPosDcpt(lstMacn, sUserName, reportGrade, macn_dcpt, mapgd_dcpt, maxa_dcpt, Define.KHOA_CHECK_DCPT);
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
    
    public String setOpenAllPosDcpt() {
        try {
            System.err.println("vao ham setOpenAllPosDcpt");
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
            
            List<String> lstMacn = new ArrayList<String>();

            for (ModelRiskProcess.StatusHistorySend value : lstModelHist) {
                if (!value.getsMacn().equals("false")) {
                    lstMacn.add(value.sMacn);
                }
            }

            if (reportGrade.equals("1") || reportGrade.equals("2")) {
                setMessage("Cấp Chi nhánh và cấp phòng giao dịch không sử dụng được chức năng này ");
                return ERROR;
            }
//            setLstModelHist(new DaoProcessRisk().getStatusHistorySendData(nam_xlrr, dot_xlrr, nhom_xlrr));
            boolean bSuccess =  new DaoDcptNo().setBlockAllPosDcpt(lstMacn, sUserName, reportGrade, macn_dcpt, mapgd_dcpt, maxa_dcpt, Define.KHOA_UNCHECK_DCPT);
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
    
    public String setBlockSendDcpt() {
        try {
            System.err.println("vao ham setBlockSendDcpt");
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

           

            if (reportGrade.equals("1") || reportGrade.equals("2")) {
                setMessage("Cấp Chi nhánh và cấp phòng giao dịch không sử dụng được chức năng này ");
                return ERROR;
            }
            String s = macn.get(0);
//            setLstModelHist(new DaoProcessRisk().getStatusHistorySendData(nam_xlrr, dot_xlrr, nhom_xlrr));
//            new DaoProcessRisk().setStatusSendDataHistory(macn.get(0), sUserName, reportGrade, nam_xlrr, dot_xlrr, nhom_xlrr, Define.KHOA_CHECK_RR);
            setMessage("Bạn đã khóa pos " + macn.get(0) + " Thành công");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " setBlockSend -> " + e.getMessage());
        }
        return SUCCESS;
    }

    public String getDataViewSend() {
        try {
            System.err.println("vao ham getDataSend");
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
            if (poscd == null || poscd.size() <= 0) {
                setMessage("Bạn phải chọn xã để gửi số liệu ! ");
                return ERROR;
            } else {
                if ((dvut_dcpt.equals("-1") && totruong_dcpt.equals("-1"))
                        || (!dvut_dcpt.equals("-1")) && !dvut_dcpt.equals("1") && totruong_dcpt.equals("-1")) {

                    DaoDcptNo daoDcptNo = new DaoDcptNo();
                    Connection conn = null;
                    conn = new DaoConnect().getConnect();
                    if (conn == null) {
                        setMessage("Không thể kết nối cơ sở dữ liệu ");
                        return ERROR;
                    }
                    Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(ngay_dcpt);
                    String sNgaySl = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);

                    if (pagination.getStart() == 0) {
                        int nCountCust = daoDcptNo.getCountTotalCustDataSend(conn, sUserName, reportGrade,
                                sNgaySl, poscd, dvut_dcpt);
                        pagination.setPreperties(nCountCust);
                    }
                    setLstsenddcpt(daoDcptNo.getDataCustSend(conn, sUserName, reportGrade, sNgaySl,
                            poscd, dvut_dcpt,
                            pagination.getStart() + 1, pagination.getStart() + pagination.getEnd()));

                    pagination.setPage_records(lstModelDcptNo.size());
                    setLstViewTotalCust(daoDcptNo.getViewTotalCustDataSend(conn, sUserName, reportGrade,
                            sNgaySl, poscd, dvut_dcpt));

                    if (!conn.isClosed()) {
                        conn.close();
                        //            System.err.println("Conn da close ");
                    }

                    return "notgroup";

                } else {
                    System.err.println("Gửi theo tổ");
                }
            }

            //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
            DaoDcptNo daoDcptNo = new DaoDcptNo();
            if ((dvut_dcpt == null && totruong_dcpt == null) || (dvut_dcpt.equals("-1") && totruong_dcpt.equals("-1"))) {
                setMessage("Bạn phải chọn đơn vị uy thác hoặc tổ trưởng ! ");
                return ERROR;
            }
            if (dvut_dcpt == null || dvut_dcpt.isEmpty() || dvut_dcpt.equals("-1")) {
                setMessage("Bạn phải chọn đơn vị uy thác ! ");
                return ERROR;
            }
            //neu chon don vi uy thac la truc tiep
            if (!dvut_dcpt.equals("1")) {
                //se kiem tra xem to truong da chon chua
                if (totruong_dcpt == null || totruong_dcpt.isEmpty() || totruong_dcpt.equals("-1")) {
                    setMessage("Bạn phải chọn tổ trưởng cần xem dữ liệu! ");
                    return ERROR;
                }
            } else {
                //gan to truong chon la null
                totruong_dcpt = null;

            }

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
            pagination.setPage_size(100);
            if (pagination.getStart() == 0) {
                int nCountCust = daoDcptNo.getCountTotalCustDataSendGroup(conn, sUserName, reportGrade,
                        sNgaySl, poscd, dvut_dcpt, totruong_dcpt, nguon_von, chuongtrinh);
                pagination.setPreperties(nCountCust);
            }
            setLstModelDcptNo(daoDcptNo.getDataCustSendGroup(conn, sUserName, reportGrade, sNgaySl,
                    poscd, dvut_dcpt, totruong_dcpt, nguon_von, chuongtrinh,
                    pagination.getStart() + 1, pagination.getStart() + pagination.getEnd()));

            pagination.setPage_records(lstModelDcptNo.size());
            setLstViewTotalCust(daoDcptNo.getViewTotalCustDataSendGroup(conn, sUserName, reportGrade,
                    sNgaySl, poscd, dvut_dcpt, totruong_dcpt, nguon_von, chuongtrinh));

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

    public String getDataPtNo() {
        try {
            System.err.println("vao ham getDataPtNo");
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
            DaoDcptNo daoDcptNo = new DaoDcptNo();
            if ((dvut_dcpt == null && totruong_dcpt == null) || (dvut_dcpt.equals("-1") && totruong_dcpt.equals("-1"))) {
                setMessage("Bạn phải chọn đơn vị uy thác hoặc tổ trưởng ! ");
                return ERROR;
            }
            if (dvut_dcpt == null || dvut_dcpt.isEmpty() || dvut_dcpt.equals("-1")) {
                setMessage("Bạn phải chọn đơn vị uy thác ! ");
                return ERROR;
            }
            //neu chon don vi uy thac la truc tiep
            if (!dvut_dcpt.equals("1")) {
                //se kiem tra xem to truong da chon chua
                if (totruong_dcpt == null || totruong_dcpt.isEmpty() || totruong_dcpt.equals("-1")) {
                    setMessage("Bạn phải chọn tổ trưởng cần xem dữ liệu! ");
                    return ERROR;
                }
            } else {
                //gan to truong chon la null
                totruong_dcpt = null;
            }

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
                int nCountCust = daoDcptNo.getCountTotalPt(conn, sUserName, reportGrade,
                        sNgaySl, poscd, dvut_dcpt, totruong_dcpt, nguon_von, chuongtrinh, trangthai);
                pagination.setPreperties(nCountCust);
            }
            setLstModelDcptNo(daoDcptNo.getDataPt(conn, sUserName, reportGrade, sNgaySl,
                    poscd, dvut_dcpt, totruong_dcpt, nguon_von, chuongtrinh,trangthai,
                    pagination.getStart() + 1, pagination.getStart() + pagination.getEnd()));

            pagination.setPage_records(lstModelDcptNo.size());
            setLstViewTotalCust(daoDcptNo.getViewTotalPt(conn, sUserName, reportGrade,
                    sNgaySl, poscd, dvut_dcpt, totruong_dcpt, nguon_von, chuongtrinh,trangthai));

            if (!conn.isClosed()) {
                conn.close();
//            System.err.println("Conn da close ");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataPtNo -> " + e.getMessage());
        }
        return SUCCESS;
    }

    public String getDetialCustomerDcNo() {
        try {
            System.err.println("vao ham getDetialCustomerDcNo");
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
            DaoDcptNo daoDcptNo = new DaoDcptNo();
            HttpServletRequest request = ServletActionContext.getRequest();

            totruong_dcpt = request.getParameter("totruong_dcpt");
            soku = request.getParameter("soku");
            ngay_dcpt = request.getParameter("ngay_dcpt");
            dvut_dcpt = request.getParameter("dvut_dcpt");
            if (soku == null || soku.isEmpty()) {
                setMessage("Không lấy ra được mã món vay xin liên hệ với quản trị để được hỗ trợ ! ");
                return ERROR;
            }
            if (ngay_dcpt == null || ngay_dcpt.isEmpty()) {
                setMessage("Không lấy ra được ngày số liệu xin liên hệ với quản trị để được hỗ trợ ! ");
                return ERROR;
            }
            /*if(dvut_dcpt.equals("-1") && totruong_dcpt.equals("-1"))
             {
             totruong_dcpt = null;
             dvut_dcpt=null;
             }
             if (!dvut_dcpt.equals("1")) {
             if (totruong_dcpt == null || totruong_dcpt.isEmpty() || totruong_dcpt.equals("-1")) {
             setMessage("Bạn phải chọn tổ trưởng xin liên hệ với quản trị để được hỗ trợ! ");
             return ERROR;
             }
             } else {
             totruong_dcpt = null;
             }*/
            if (totruong_dcpt.equals("-1")) {
                totruong_dcpt = null;
            }
            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(ngay_dcpt);
            String sNgaySl = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);
            setLstModelDcptNo(daoDcptNo.getDetailCustomer(soku, sNgaySl, totruong_dcpt));
            setLstNgnhanDm(daoDcptNo.getNguyennhanDm());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDetialCustomerDcNo -> " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Luu dc,pt no">

    public String saveDataDcNo() {
        try {
            System.err.println("vao ham getDataDcNo");
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
            DaoDcptNo daoDcptNo = new DaoDcptNo();
//            if (totruong_dcpt == null || totruong_dcpt.isEmpty() || totruong_dcpt.equals("-1")) {
//                setMessage("Bạn phải chọn tổ trưởng ! ");
//                return ERROR;
//            }

            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(ngay_dcpt);
            String sNgaySl = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);
            if (sNgaySl == null || sNgaySl.isEmpty() || sNgaySl.equals("-1")) {
                setMessage("Không thể lấy ra được ngày số liệu ! ");
                return ERROR;
            }

            List<saveDcNo> lstdctmp = new ArrayList<saveDcNo>();

            for (saveDcNo value : lstsaveDcno) {
                if (!value.getsSoku().equals("false")) {
                    lstdctmp.add(value);
                }
            }

            if (daoDcptNo.SaveDataDcNo(sUserName, sNgaySl, totruong_dcpt, lstdctmp)) {
                setMessage("Đã cập nhật số liệu thành công ! ");
            } else {
                setMessage("Bạn chưa cập nhật được số liệu xin liên hệ quản trị để khắc phục lỗi! ");
                return ERROR;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " saveDataDcNo -> " + e.getMessage());
            setMessage("Bạn chưa cập nhật được số liệu xin liên hệ quản trị để khắc phục lỗi!  ");
            return ERROR;
        }
        return SUCCESS;
    }

    public String saveDataPtNo() {
        try {
            System.err.println("vao ham getDataDcNo");
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
            DaoDcptNo daoDcptNo = new DaoDcptNo();
//            if (totruong_dcpt == null || totruong_dcpt.isEmpty() || totruong_dcpt.equals("-1")) {
//                setMessage("Bạn phải chọn tổ trưởng ! ");
//                return ERROR;
//            }

            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(ngay_dcpt);
            String sNgaySl = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);
            if (sNgaySl == null || sNgaySl.isEmpty() || sNgaySl.equals("-1")) {
                setMessage("Không thể lấy ra được ngày số liệu ! ");
                return ERROR;
            }

            for (savePtNo val : lstsavePtno) {
                System.err.println("3432" + val.getsSoku());
                if (val.getbSotien().intValue() > 0 && Integer.parseInt(val.getsMaNN()) == 0) {
                    setMessage("Bạn chưa chọn mã nguyên nhân cho món vay " + val.getsSoku());
                    return ERROR;
                }
            }
            List<savePtNo> lstdctmp = new ArrayList<savePtNo>();

            for (savePtNo value : lstsavePtno) {
                if (!value.getsSoku().equals("false")) {
                    lstdctmp.add(value);
                }
            }

            if (daoDcptNo.SaveDataPtNo(sUserName, sNgaySl, totruong_dcpt, lstdctmp)) {
                setMessage("Đã cập nhật số liệu thành công ! ");
            } else {
                setMessage("Bạn chưa cập nhật được số liệu xin liên hệ quản trị để khắc phục lỗi! ");
                return ERROR;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " saveDataPtNo -> " + e.getMessage());
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
            dvut_dcpt = request.getParameter("dvut_dcpt");
            soku = request.getParameter("soku").trim();
            totruong_dcpt = request.getParameter("totruong_dcpt");
            nguon_von = request.getParameter("nguon_von");
            chuongtrinh = request.getParameter("chuongtrinh");
//             if (dvut_dcpt == null || dvut_dcpt.isEmpty() || dvut_dcpt.equals("-1")) {
//                setMessage("Bạn phải chọn đơn vị ủy thác  ! ");
//                return ERROR;
//            }
            if (dvut_dcpt != null && dvut_dcpt.equals("1")) {
                totruong_dcpt = null;
            }
            if (dvut_dcpt.equals("-1")) {
                dvut_dcpt = null;
                totruong_dcpt = null;
            }

            //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
            DaoDcptNo daoDcptNo = new DaoDcptNo();
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

            if (pagination.getStart() == 0) {
                int nCountCust = daoDcptNo.getCountTotalSearchCust(conn, sUserName, reportGrade, soku,
                        sNgaySl, poscd, dvut_dcpt, totruong_dcpt, nguon_von, chuongtrinh);
                int size=(int) Math.ceil((double)nCountCust / 10);
                pagination.setPage_size(size*10);
                pagination.setPreperties(nCountCust);
                
               
            }
            setLstModelDcptNo(daoDcptNo.getDataCustSearchLoan(conn, sUserName, reportGrade, soku, sNgaySl,
                    poscd, dvut_dcpt, totruong_dcpt, nguon_von, chuongtrinh,
                    pagination.getStart() + 1, pagination.getStart() + pagination.getEnd()));

            pagination.setPage_records(lstModelDcptNo.size());
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

    public String getDataSearchLoanPt() {
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
            dvut_dcpt = request.getParameter("dvut_dcpt");
            soku = request.getParameter("soku");
            totruong_dcpt = request.getParameter("totruong_dcpt");
            nguon_von = request.getParameter("nguon_von");
            chuongtrinh = request.getParameter("chuongtrinh");
//             if (dvut_dcpt == null || dvut_dcpt.isEmpty() || dvut_dcpt.equals("-1")) {
//                setMessage("Bạn phải chọn đơn vị ủy thác  ! ");
//                return ERROR;
//            }
            if (dvut_dcpt != null && dvut_dcpt.equals("1")) {
                totruong_dcpt = null;
            }
            if (dvut_dcpt.equals("-1")) {
                dvut_dcpt = null;
                totruong_dcpt = null;
            }

            //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
            DaoDcptNo daoDcptNo = new DaoDcptNo();
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

            if (pagination.getStart() == 0) {
                int nCountCust = daoDcptNo.getCountTotalSearchCustPt(conn, sUserName, reportGrade, soku,
                        sNgaySl, poscd, dvut_dcpt, totruong_dcpt, nguon_von, chuongtrinh);
                pagination.setPreperties(nCountCust);
            }
            setLstModelDcptNo(daoDcptNo.getDataCustSearchLoanPt(conn, sUserName, reportGrade, soku, sNgaySl,
                    poscd, dvut_dcpt, totruong_dcpt, nguon_von, chuongtrinh,
                    pagination.getStart() + 1, pagination.getStart() + pagination.getEnd()));

            pagination.setPage_records(lstModelDcptNo.size());
            if (!conn.isClosed()) {
                conn.close();
//            System.err.println("Conn da close ");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataSearchLoanPt -> " + e.getMessage());
        }
        return SUCCESS;
    }
//</editor-fold>
    
    public String SendDataDcPt() {
        try {
            System.err.println("vao ham SendDataDcPt");
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

            if (lstSoku != null || !lstSoku.isEmpty()) {
                 List<String> lstSoku_tmp = new ArrayList<String>();
                for (String value : lstSoku) {
                   
                    if (!value.trim().equals("false")) {
                        lstSoku_tmp.add(value);
//                        lstSoku.remove(value);
                    }
                }
                lstSoku.clear();
                lstSoku.addAll(lstSoku_tmp);
            }
            //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
            DaoDcptNo daoDcptNo = new DaoDcptNo();
//            if (totruong_dcpt == null || totruong_dcpt.isEmpty() || totruong_dcpt.equals("-1")) {
//                setMessage("Bạn phải chọn tổ trưởng ! ");
//                return ERROR;
//            }
            if (dvut_dcpt.equals("-1")) {
                dvut_dcpt = "";
            }
            if (totruong_dcpt.equals("-1")) {
                totruong_dcpt = "";
            }
            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(ngay_dcpt);
            String sNgaySl = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);
            if (sNgaySl == null || sNgaySl.isEmpty() || sNgaySl.equals("-1")) {
                setMessage("Không thể lấy ra được ngày số liệu ! ");
                return ERROR;
            }
            
            int n = 3;
            Random r = new Random();
            byte[] b = new byte[n];
            r.nextBytes(b);
            BigInteger iRandom = new BigInteger(b);
            System.out.println(iRandom);

            HashMap<Integer, Object> hmObjData = daoDcptNo.getDataSend(sUserName, reportGrade, poscd,
                    sNgaySl, dvut_dcpt, totruong_dcpt, lstSoku);

            String sPoscd = (String) hmObjData.get(1);
            List<String> lstData = (List<String>) hmObjData.get(2);

            ServletContext context = ServletActionContext.getServletContext();
            String strPathSave = !context.getRealPath("/").endsWith("/")
                    ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                    : context.getRealPath("/") + Define.M_REPORT_XML;
            strPathSave += sUserName + "_DCPTNO_" + sPoscd + "_" + Long.toString(System.currentTimeMillis()) + ".xml";
            //Tao file xml theo cau truc
            new XmlDcptNo().createXmlFileDcpt(Define.PARA_SYN_REPORT_DCPT, sUserName, reportGrade,
                    sPoscd, lstData, strPathSave);
            File checkfile = new File(strPathSave);
            ProcessReportSyn clientWritexml = new ProcessReportSyn();
            String sStatus = clientWritexml.SendFileXmlToWebServices(strPathSave);

            if (sStatus.equals(Define.WEB_SERVICES_STATUS_FAIL)) {
                System.err.println("Ban chua dong bo du lieu duoc ve TW");
                setMessage("Lỗi bạn chưa gửi dữ liệu được về trung ương ");
                if (checkfile.exists()) {
                    checkfile.delete();
                }
            } else if (sStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
                    setMessage("Bạn gửi dữ liệu về trung ương thành công");
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
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " saveDataDcNo -> " + e.getMessage());
            setMessage("Bạn chưa gửi được số liệu xin liên hệ quản trị để khắc phục lỗi!  ");
            return ERROR;
        }
        return SUCCESS;
    }
    
    public String LockDataDcPt() {
        try {
            System.err.println("vao ham LockDataDcPt");
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

            DaoDcptNo daoDcptNo = new DaoDcptNo();

            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(ngay_dcpt);
            String sNgaySl = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);
            if (sNgaySl == null || sNgaySl.isEmpty() || sNgaySl.equals("-1")) {
                setMessage("Không thể lấy ra được ngày số liệu ! ");
                return ERROR;
            }
            
            if(daoDcptNo.getDataCheckLock(sUserName,poscd,totruong_dcpt) > 0){
                setMessage("Vẫn còn món vay chưa được phân tích ! ");
                return ERROR;
            }

            HashMap<Integer, Object> hmObjData = daoDcptNo.getDataSendlOCK(sUserName,  poscd, totruong_dcpt);

            String sPoscd = (String) hmObjData.get(1);
            List<String> lstData = (List<String>) hmObjData.get(2);

            ServletContext context = ServletActionContext.getServletContext();
            String strPathSave = !context.getRealPath("/").endsWith("/")
                    ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                    : context.getRealPath("/") + Define.M_REPORT_XML;
            strPathSave += sUserName + "_DCPTNO_LOCK_" + sPoscd + "_" + Long.toString(System.currentTimeMillis()) + ".xml";
            //Tao file xml theo cau truc
            new XmlDcptNo().createXmlFileDcpt(Define.PARA_SYN_REPORT_DCPT_LOCK, sUserName, reportGrade,
                    sPoscd, lstData, strPathSave);
            File checkfile = new File(strPathSave);
            ProcessReportSyn clientWritexml = new ProcessReportSyn();
            String sStatus = clientWritexml.SendFileXmlToWebServices(strPathSave);

            if (sStatus.equals(Define.WEB_SERVICES_STATUS_FAIL)) {
                System.err.println("Ban chua dong bo du lieu duoc ve TW");
                setMessage("Lỗi bạn chưa gửi dữ liệu được về trung ương ");
                if (checkfile.exists()) {
                    checkfile.delete();
                }
            } else if (sStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
                setMessage("Bạn gửi dữ liệu về trung ương thành công");
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
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " saveDataDcNo -> " + e.getMessage());
            setMessage("Bạn chưa cập nhật được số liệu xin liên hệ quản trị để khắc phục lỗi!  ");
            return ERROR;
        }
        return SUCCESS;
    }

    //<editor-fold defaultstate="collapsed" desc="Khoi tao get set cho bien local">
    public List<ListValue> getLstMaCN() {
        return lstMaCN;
    }

    public void setLstMaCN(List<ListValue> lstMaCN) {
        this.lstMaCN = lstMaCN;
    }

    public List<ListValue> getLstNamXlrr() {
        return lstNamXlrr;
    }

    public void setLstNamXlrr(List<ListValue> lstNamXlrr) {
        this.lstNamXlrr = lstNamXlrr;
    }

    public String getDefaultNamxlrr() {
        return defaultNamxlrr;
    }

    public void setDefaultNamxlrr(String defaultNamxlrr) {
        this.defaultNamxlrr = defaultNamxlrr;
    }

    public List<ListValue> getLstTrangthaiXlrr() {
        return lstTrangthaiXlrr;
    }

    public void setLstTrangthaiXlrr(List<ListValue> lstTrangthaiXlrr) {
        this.lstTrangthaiXlrr = lstTrangthaiXlrr;
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
    
    public List<ViewTotalCust> getLstViewTotalCust() {
        return lstViewTotalCust;
    }

    public void setLstViewTotalCust(List<ViewTotalCust> lstViewTotalCust) {
        this.lstViewTotalCust = lstViewTotalCust;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getMa_nguyennhan() {
        return ma_nguyennhan;
    }

    public void setMa_nguyennhan(String ma_nguyennhan) {
        this.ma_nguyennhan = ma_nguyennhan;
    }
    
    public List<ListValue> getLstNgnhanDm() {
        return lstNgnhanDm;
    }

    public void setLstNgnhanDm(List<ListValue> lstNgnhanDm) {
        this.lstNgnhanDm = lstNgnhanDm;
    }
    
    public List<saveDcNo> getLstsaveDcno() {
        return lstsaveDcno;
    }

    public void setLstsaveDcno(List<saveDcNo> lstsaveDcno) {
        this.lstsaveDcno = lstsaveDcno;
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

    public String getDvut_dcpt() {
        return dvut_dcpt;
    }

    public void setDvut_dcpt(String dvut_dcpt) {
        this.dvut_dcpt = dvut_dcpt;
    }

    public String getTotruong_dcpt() {
        return totruong_dcpt;
    }

    public void setTotruong_dcpt(String totruong_dcpt) {
        this.totruong_dcpt = totruong_dcpt;
    }

    public String getNguon_von() {
        return nguon_von;
    }

    public void setNguon_von(String nguon_von) {
        this.nguon_von = nguon_von;
    }

    public String getChuongtrinh() {
        return chuongtrinh;
    }

    public void setChuongtrinh(String chuongtrinh) {
        this.chuongtrinh = chuongtrinh;
    }

    public List<ListValue> getLstDvutDcpt() {
        return lstDvutDcpt;
    }

    public void setLstDvutDcpt(List<ListValue> lstDvutDcpt) {
        this.lstDvutDcpt = lstDvutDcpt;
    }

    public List<ListValue> getLstTotruongDcpt() {
        return lstTotruongDcpt;
    }

    public void setLstTotruongDcpt(List<ListValue> lstTotruongDcpt) {
        this.lstTotruongDcpt = lstTotruongDcpt;
    }

    public List<ListValue> getLstChuongtrinh() {
        return lstChuongtrinh;
    }

    public void setLstChuongtrinh(List<ListValue> lstChuongtrinh) {
        this.lstChuongtrinh = lstChuongtrinh;
    }

    public List<ListValue> getLstNguonvon() {
        return lstNguonvon;
    }

    public void setLstNguonvon(List<ListValue> lstNguonvon) {
        this.lstNguonvon = lstNguonvon;
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

    public List<DcptNoModel> getLstModelDcptNo() {
        return lstModelDcptNo;
    }

    public void setLstModelDcptNo(List<DcptNoModel> lstModelDcptNo) {
        this.lstModelDcptNo = lstModelDcptNo;
    }

    public List<savePtNo> getLstsavePtno() {
        return lstsavePtno;
    }

    public void setLstsavePtno(List<savePtNo> lstsavePtno) {
        this.lstsavePtno = lstsavePtno;
    }

    public List<senddcpt> getLstsenddcpt() {
        return lstsenddcpt;
    }

    public void setLstsenddcpt(List<senddcpt> lstsenddcpt) {
        this.lstsenddcpt = lstsenddcpt;
    }
    
    public List<ListValue> getLstMaPGD() {
        return lstMaPGD;
    }

    public void setLstMaPGD(List<ListValue> lstMaPGD) {
        this.lstMaPGD = lstMaPGD;
    }
    
     public List<ModelRiskProcess.StatusHistorySend> getLstModelHist() {
        return lstModelHist;
    }

    public void setLstModelHist(List<ModelRiskProcess.StatusHistorySend> lstModelHist) {
        this.lstModelHist = lstModelHist;
    }

    public List<ListValue> getLstMaXa() {
        return lstMaXa;
    }

    public void setLstMaXa(List<ListValue> lstMaXa) {
        this.lstMaXa = lstMaXa;
    }

//</editor-fold>
}
