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
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoDCPLNO;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.DcplnModel;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.model.PLNO_DULIEU;
import vbsp.ims.model.Pagination;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Sr. Chữ, DateCreated: 22.01.2016
 */
public class DcplnAction extends ActionSupport {

    //<editor-fold defaultstate="collapsed" desc="Khai bao bien">
    protected String Grade;
    protected String UserName;
    protected String Message;
    private TreeNode nodes_pos = new TreeNode();

    private String ngay_dcpln;
    private String dvut_dcpln;
    private String totruong_dcpln;
    private String ngvon_dcpln;
    private String chtrinh_dcpln;
    private String soku_dcpln;
    private String trangthai;
    private String ma_ngnhan_dcpln;

    private List<ListValue> lstDvutDcpln = new ArrayList<ListValue>();
    private List<ListValue> lstTotruongDcpln = new ArrayList<ListValue>();
    private List<ListValue> lstChuongtrinh = new ArrayList<ListValue>();
    private List<ListValue> lstNguonvon = new ArrayList<ListValue>();
    private List<DcplnModel> lstDcplnModel = new ArrayList<DcplnModel>();
    private List<ListValue> lstDMNgNhan = new ArrayList<ListValue>();

    private List<DcplnModel.ViewTotalLoan> lstViewTotal = new ArrayList<DcplnModel.ViewTotalLoan>();

    private Pagination pagination = new Pagination(50, 1);
    private List<String> poscd = new ArrayList<String>();
    private List<PLNO_DULIEU> lstSavePln = new ArrayList<PLNO_DULIEU>();

    public List<PLNO_DULIEU> getLstSavePln() {
        return lstSavePln;
    }

    public void setLstSavePln(List<PLNO_DULIEU> lstSavePln) {
        this.lstSavePln = lstSavePln;
    }

    public String getNgay_dcpln() {
        return ngay_dcpln;
    }

    public void setNgay_dcpln(String ngay_dcpln) {
        this.ngay_dcpln = ngay_dcpln;
    }

    public String getDvut_dcpln() {
        return dvut_dcpln;
    }

    public void setDvut_dcpln(String dvut_dcpln) {
        this.dvut_dcpln = dvut_dcpln;
    }

    public String getTotruong_dcpln() {
        return totruong_dcpln;
    }

    public void setTotruong_dcpln(String totruong_dcpln) {
        this.totruong_dcpln = totruong_dcpln;
    }

    public String getNgvon_dcpln() {
        return ngvon_dcpln;
    }

    public void setNgvon_dcpln(String ngvon_dcpln) {
        this.ngvon_dcpln = ngvon_dcpln;
    }

    public String getChtrinh_dcpln() {
        return chtrinh_dcpln;
    }

    public void setChtrinh_dcpln(String chtrinh_dcpln) {
        this.chtrinh_dcpln = chtrinh_dcpln;
    }

    public String getSoku_dcpln() {
        return soku_dcpln;
    }

    public void setSoku_dcpln(String soku_dcpln) {
        this.soku_dcpln = soku_dcpln;
    }

    public List<ListValue> getLstDvutDcpln() {
        return lstDvutDcpln;
    }

    public void setLstDvutDcpln(List<ListValue> lstDvutDcpln) {
        this.lstDvutDcpln = lstDvutDcpln;
    }

    public List<ListValue> getLstTotruongDcpln() {
        return lstTotruongDcpln;
    }

    public void setLstTotruongDcpln(List<ListValue> lstTotruongDcpln) {
        this.lstTotruongDcpln = lstTotruongDcpln;
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

    public List<DcplnModel.ViewTotalLoan> getLstViewTotal() {
        return lstViewTotal;
    }

    public void setLstViewTotal(List<DcplnModel.ViewTotalLoan> lstViewTotal) {
        this.lstViewTotal = lstViewTotal;
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Phuong thu get/set Cho bien dung chung">
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

    public TreeNode getNodes_pos() {
        return nodes_pos;
    }

    public void setNodes_pos(TreeNode nodes_pos) {
        this.nodes_pos = nodes_pos;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public List<String> getPoscd() {
        return poscd;
    }

    public void setPoscd(List<String> poscd) {
        this.poscd = poscd;
    }

    public List<DcplnModel> getLstDcplnModel() {
        return lstDcplnModel;
    }

    public void setLstDcplnModel(List<DcplnModel> lstDcplnModel) {
        this.lstDcplnModel = lstDcplnModel;
    }

    public String getMa_ngnhan_dcpln() {
        return ma_ngnhan_dcpln;
    }

    public void setMa_ngnhan_dcpln(String ma_ngnhan_dcpln) {
        this.ma_ngnhan_dcpln = ma_ngnhan_dcpln;
    }

    public List<ListValue> getLstDMNgNhan() {
        return lstDMNgNhan;
    }

    public void setLstDMNgNhan(List<ListValue> lstDMNgNhan) {
        this.lstDMNgNhan = lstDMNgNhan;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Hàm dùng chung Chạy chương trình lúc đầu">
    protected boolean getParaSession() {
        Map session = ActionContext.getContext().getSession();
        if (session == null || session.size() == 0 || session.isEmpty()) {
            setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
            addActionError("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
            return false;
        }
        setUserName(session.get("username").toString());

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

    protected HashMap<String, Object> getParameter() throws Exception {
        HashMap<String, Object> paramHashMap = new HashMap<>();
        Map<String, String[]> prameters = ServletActionContext.getRequest().getParameterMap();
        for (String parameter : prameters.keySet()) {
            String[] values = prameters.get(parameter);
            if (parameter.indexOf("TEXT") > 0 || parameter.indexOf("DATE") > 0 || parameter.indexOf("LIST") > 0) {
                if (parameter.startsWith("1_")) {
                    parameter = parameter.substring(2, parameter.length());
                }
                if (parameter.indexOf("DATE") > 0) {
                    Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
                    paramHashMap.put(parameter.substring(0, parameter.length() - 5), new SimpleDateFormat("dd-MMM-yyyy").format(sdf));
//                    lstParameters.add(new ListValue(parameter, values[0]));
                } else {
                    paramHashMap.put(parameter.substring(0, parameter.length() - 5), values[0]);
//                    lstParameters.add(new ListValue(parameter, values[0]));
                }
            } else {
                if (parameter.startsWith("1_")) {
                    parameter = parameter.substring(2, parameter.length());
                }
                if (parameter.equals("poscd")) {
                    paramHashMap.put(parameter, convertStringtoList(values));
                } else {
                    paramHashMap.put(parameter, values[0]);
//                    lstParameters.add(new ListValue(parameter, values[0]));
                }
            }
        }
        return paramHashMap;
    }

    private boolean setTreeNodeGrade3(List<ModelTreeNode> lstModelTree) {
        try {
            TreeNode nodePar = new TreeNode();
            List<TreeNode> lstTree = new ArrayList<TreeNode>();
            for (int i = 0; i < lstModelTree.size(); i++) {
                ModelTreeNode modelTree = lstModelTree.get(i);
                if (i == 0) //Neu la row dau tien thi la node rootb
                {
                    nodes_pos.setId("999999");
                    nodes_pos.setTitle(modelTree.getStrParentDesc());
                    nodes_pos.setState(TreeNode.NODE_STATE_OPEN);
                    nodes_pos.setChildren(new LinkedList<TreeNode>());
                } else {
                    if (modelTree.getStrChildCd().equals("999999")) {
                        if (i != 1) {
                            lstTree.add(nodePar);
                            nodePar = null;
                            nodePar = new TreeNode();
                        }
                        nodePar.setId("999999");
                        nodePar.setTitle(modelTree.getStrChildDesc());
                        nodePar.setState(TreeNode.NODE_STATE_CLOSED);
                        nodePar.setChildren(new LinkedList<TreeNode>());
                    } else {
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
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            DaoDCPLNO daoRisk = new DaoDCPLNO();
            List<ModelTreeNode> lstModelTree = daoRisk.getDataPosTreeNode(UserName, Grade);
            if (Grade.equals("3")) {
                setTreeNodeGrade3(lstModelTree);
            } else {
                setTreeNodeGrade12(lstModelTree);
            }
            setDmKhac();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " execute -> " + e.getMessage());
        }
        return SUCCESS;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Action Load danh sách thông tin bản ghi Phân loại nợ">
    /**
     * Hàm thực hiện Set danh mục khác Tùy theo chỉ số khác nhau: Quy ước: 53 -
     * ĐVUT; 54 - Chương trình; 55 - Nguồn vốn
     *
     * @return
     */
    public boolean setDmKhac() {
        try {
            DaoDCPLNO daoRisk = new DaoDCPLNO();
            HashMap<Integer, List<ListValue>> hmDmKhac = daoRisk.getDmKhac();
            lstDvutDcpln = hmDmKhac.get(53) == null ? new ArrayList<ListValue>() : hmDmKhac.get(53);
            lstChuongtrinh = hmDmKhac.get(54) == null ? new ArrayList<ListValue>() : hmDmKhac.get(54);
            lstNguonvon = hmDmKhac.get(55) == null ? new ArrayList<ListValue>() : hmDmKhac.get(55);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " setDmKhac -> " + e.getMessage());
            return false;
        }
    }

    public String getTotruong() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }

            HashMap hmPara = getParameter();

            ArrayList<String> ArrlstPosCd = (ArrayList<String>) hmPara.get("poscd");
            if (ArrlstPosCd == null || ArrlstPosCd.size() == 0) {
                setMessage("Bạn phải chọn đơn vị cần tải dữ liệu. Vui lòng kiểm tra lại!");
//                return ERROR;
            } else {
                ArrlstPosCd.remove("999999");
            }

            setDmKhac();
//            //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
            dvut_dcpln = (String) hmPara.get("dvut_dcpln");
            if (dvut_dcpln == null || dvut_dcpln.isEmpty() || dvut_dcpln.equals("-1")) {
//                System.err.println("dvut_dcpln la null " + dvut_dcpln);
                return SUCCESS;
            }
            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(ngay_dcpln);
            String sNgaySl = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);
            DaoDCPLNO daoRisk = new DaoDCPLNO();

            //Khoi tao cho treenode
//            System.err.println("dvut_dcpln=" + dvut_dcpln + " poscd=" + ArrlstPosCd.size());
            //neu don vi uy thac khong phai la truc tiep thi moi load ma to truong hoac du an
            if (!dvut_dcpln.equals("1")) {
                setLstTotruongDcpln(daoRisk.getToTruong(UserName, Grade, ArrlstPosCd, dvut_dcpln,sNgaySl));
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getTotruong -> " + e.getMessage());
        }
        return SUCCESS;
    }

    public String getDataDcPLN() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
            DaoDCPLNO daoPln = new DaoDCPLNO();
            if ((dvut_dcpln == null && totruong_dcpln == null) || (dvut_dcpln.equals("-1") && totruong_dcpln.equals("-1"))) {
                setMessage("Bạn phải chọn đơn vị uy thác hoặc tổ trưởng. Vui lòng kiểm tra lại!");
                addActionError("Bạn phải chọn đơn vị uy thác hoặc tổ trưởng. Vui lòng kiểm tra lại!");
                return ERROR;
            }
            if (dvut_dcpln == null || dvut_dcpln.isEmpty() || dvut_dcpln.equals("-1")) {
                setMessage("Bạn phải chọn đơn vị uy thác. Vui lòng kiểm tra lại!");
                addActionError("Bạn phải chọn đơn vị uy thác. Vui lòng kiểm tra lại!");
                return ERROR;
            }
            //neu chon don vi uy thac la truc tiep
            if (!dvut_dcpln.equals("1")) {
                //se kiem tra xem to truong da chon chua
                if (totruong_dcpln == null || totruong_dcpln.isEmpty() || totruong_dcpln.equals("-1")) {
                    setMessage("Bạn phải chọn tổ trưởng cần tải dữ liệu. Vui lòng kiểm tra lại!");
                    return ERROR;
                }
            } else {
                totruong_dcpln = null;
            }

            if (poscd == null) {
                poscd = new ArrayList<String>();
            }
            Connection conn = null;
            conn = new DaoConnect().getConnect();
            if (conn == null) {
                addActionError("Không thể kết nối cơ sở dữ liệu. Vui lòng kiểm tra lại!");
                return ERROR;
            }
            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(ngay_dcpln);
            String sNgaySl = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);
            System.err.println("trangthai=" + trangthai);
            if (pagination.getStart() == 0) {
                int nCountCust = daoPln.getCountTotalPLN(conn, UserName, Grade,
                        sNgaySl, poscd, dvut_dcpln, totruong_dcpln, ngvon_dcpln, chtrinh_dcpln, trangthai);
                int size = (int) Math.ceil((double) nCountCust / 10);
                pagination.setPage_size(size * 10);
                pagination.setPreperties(nCountCust);

            }
            setLstDcplnModel(daoPln.getDataPLN(conn, UserName, Grade, sNgaySl,
                    poscd, dvut_dcpln, totruong_dcpln, ngvon_dcpln, chtrinh_dcpln, trangthai,
                    pagination.getStart() + 1, pagination.getStart() + pagination.getEnd()));

            pagination.setPage_records(lstDcplnModel.size());
            setLstViewTotal(daoPln.getViewTotalLoanData(conn, UserName, Grade,
                    sNgaySl, poscd, dvut_dcpln, totruong_dcpln, ngvon_dcpln, chtrinh_dcpln, trangthai));
            setLstDMNgNhan(daoPln.getNgNhan_KCKNTN(conn));
            if (!conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataDcNo -> " + e.getMessage());
            addActionError("Lỗi không thể load số liệu chi tiết lỗi " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Action Tìm kiếm theo mã món vay và Lấy thông tin chi tiết">
    public String getDataSearchLoanPLN() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
//          HttpServletRequest request = ServletActionContext.getRequest();
            HashMap hmPara = getParameter();
            ArrayList<String> ArrlstPosCd = (ArrayList<String>) hmPara.get("poscd");
            if (ArrlstPosCd == null || ArrlstPosCd.size() == 0) {
                setMessage("Bạn chưa chọn đơn vị cần Tìm kiếm dữ liệu. Vui lòng kiểm tra lại!");
            } else {
                ArrlstPosCd.remove("999999");
            }

            //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
            ngay_dcpln = hmPara.get("ngay_dcpln").toString();
            dvut_dcpln = hmPara.get("dvut_dcpln").toString();
            soku_dcpln = hmPara.get("soku_dcpln").toString();
            totruong_dcpln = hmPara.get("totruong_dcpln").toString();
            ngvon_dcpln = hmPara.get("ngvon_dcpln").toString();
            chtrinh_dcpln = hmPara.get("chtrinh_dcpln").toString();

            if (dvut_dcpln != null && dvut_dcpln.equals("1")) {
                totruong_dcpln = null;
            }
            if (dvut_dcpln.equals("-1")) {
                dvut_dcpln = null;
                totruong_dcpln = null;
            }

            DaoDCPLNO daoDcPLNO = new DaoDCPLNO();
            if (ngay_dcpln == null || ngay_dcpln.isEmpty() || ngay_dcpln.equals("-1")) {
                setMessage("Không lấy ra được ngày số liệu. Vui lòng kiểm tra lại!");
                return ERROR;
            }
            if (soku_dcpln == null || soku_dcpln.isEmpty()) {
                setMessage("Không lấy ra được mã món vay cần tìm kiếm. Vui lòng kiểm tra lại!");
                return ERROR;
            }

            Connection conn = null;
            conn = new DaoConnect().getConnect();
            if (conn == null) {
                setMessage("Không thể kết nối cơ sở dữ liệu. Vui lòng kiểm tra lại");
                return ERROR;
            }

            Date sdf_dcpln = new SimpleDateFormat("dd/MM/yyyy").parse(ngay_dcpln);
            String sNgaySl = new SimpleDateFormat("dd-MMM-yyyy").format(sdf_dcpln);

            if (pagination.getStart() == 0) {
                int nCountCust = daoDcPLNO.getCountTotalSearchLoan(conn, UserName, Grade, soku_dcpln,
                        sNgaySl, poscd, dvut_dcpln, totruong_dcpln, ngvon_dcpln, chtrinh_dcpln);
                pagination.setPreperties(nCountCust);
            }
            setLstDcplnModel(daoDcPLNO.getDataSearchLoan(conn, UserName, Grade, soku_dcpln, sNgaySl,
                    poscd, dvut_dcpln, totruong_dcpln, ngvon_dcpln, chtrinh_dcpln,
                    pagination.getStart() + 1, pagination.getStart() + pagination.getEnd()));

            pagination.setPage_records(lstDcplnModel.size());
            setLstViewTotal(daoDcPLNO.getViewTotalSearchData(conn, UserName, Grade,
                    sNgaySl, poscd, dvut_dcpln, totruong_dcpln, ngvon_dcpln, chtrinh_dcpln));
            setLstDMNgNhan(daoDcPLNO.getNgNhan_KCKNTN(conn));
            if (!conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataSearchLoanPLN -> " + e.getMessage());
        }
        return SUCCESS;
    }

    /**
     * Hàm thực hiện Action cho sự kiện Xem chi tiết thông tin món vay và Nhập
     * thông tin chênh lệch đối chiếu
     *
     * @return
     */
    public String getDetialLoanDcPLN() {

        try {
            if (!getParaSession()) {
                return ERROR;
            }

            HashMap hmPara = getParameter();

            ngay_dcpln = hmPara.get("ngay_dcpln").toString();
            dvut_dcpln = hmPara.get("dvut_dcpln").toString();
            soku_dcpln = hmPara.get("soku_dcpln").toString();
            totruong_dcpln = hmPara.get("totruong_dcpln").toString();

            if (soku_dcpln == null || soku_dcpln.isEmpty()) {
                addActionError("Không lấy ra được mã món vay để xem chi tiết. Vui lòng kiểm tra lại!");
                return ERROR;
            }
            if (ngay_dcpln == null || ngay_dcpln.isEmpty()) {
                addActionError("Không lấy ra được ngày số liệu để xem chi tiết. Vui lòng kiểm tra lại!");
                return ERROR;
            }
            if (totruong_dcpln.equals("-1")) {
                totruong_dcpln = null;
            }
            System.err.println("vao get khach hang chi tiet soku=" + soku_dcpln);
            DaoDCPLNO daoDcPLNO = new DaoDCPLNO();

            Date sdf_dcpln = new SimpleDateFormat("dd/MM/yyyy").parse(ngay_dcpln);
            String sNgaySl = new SimpleDateFormat("dd-MMM-yyyy").format(sdf_dcpln);

            setLstDcplnModel(daoDcPLNO.getDetailLoan(soku_dcpln, sNgaySl, totruong_dcpln));
            System.err.println("vao get khach hang chi tiet soku1=" + soku_dcpln);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDetialLoanDcPLN -> " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Action Cập nhật thông tin Phân loại nợ">
    /**
     * Hàm thưc hiện cập nhật thông tin Phân loại nợ theo khả năng trả nợ khách hàng
     * @return: SUCCESS - Nếu thành công; ERROR - Nếu có lỗi xẩy ra
     */
    public String saveDataPLNo_KHTN() {
        try {

            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmPara = getParameter();

            ngay_dcpln = hmPara.get("ngay_dcpln").toString();
            dvut_dcpln = hmPara.get("dvut_dcpln").toString();
            totruong_dcpln = hmPara.get("totruong_dcpln").toString();

            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(ngay_dcpln);
            String sNgaySl = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);
            if (sNgaySl == null || sNgaySl.isEmpty() || sNgaySl.equals("-1")) {
                setMessage("Không thể lấy ra được ngày số liệu ! ");
                return ERROR;
            }

            List<PLNO_DULIEU> lstdctmp = new ArrayList<PLNO_DULIEU>();

            for (PLNO_DULIEU value : lstSavePln) {
                if (!value.getsSoku().equals("false")) {
                    lstdctmp.add(value);
                }
            }
            DaoDCPLNO daoPlno = new DaoDCPLNO();
             System.err.println("số --" + lstdctmp.size());
            if (daoPlno.SaveDataPLNO_KHTN(UserName, sNgaySl, totruong_dcpln, lstdctmp)) {
                addActionError("Đã cập nhật số liệu thông tin Phân loại nợ thành công!");
                setMessage("SUCCESS");
            } else {
                addActionError("Bạn chưa cập nhật được số liệu xin liên hệ quản trị để khắc phục lỗi! ");
                setMessage("Bạn chưa cập nhật được số liệu xin liên hệ quản trị để khắc phục lỗi! ");
                return ERROR;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " saveDataDcNo -> " + e.getMessage());
            addActionError("Bạn chưa cập nhật được số liệu xin liên hệ quản trị để khắc phục lỗi!  ");
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * Hàm thực hiện cập nhật thông tin bổ sung về Phân loại nợ (Quan hệ khách hàng, Chênh lệch đối chiếu)
     * @return: SUCCESS - Nếu thành công; ERROR - Nếu có lỗi xẩy ra
     */
    public String saveDataPLNo_DC() {
        try {

            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmPara = getParameter();

            ngay_dcpln = hmPara.get("ngay_dcpln").toString();
            totruong_dcpln = hmPara.get("totruong_dcpln").toString();

            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(ngay_dcpln);
            String sNgaySl = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);
            if (sNgaySl == null || sNgaySl.isEmpty() || sNgaySl.equals("-1")) {
                setMessage("Không thể lấy ra được ngày số liệu. Vui lòng kiểm tra lại!");
                return ERROR;
            }

            List<PLNO_DULIEU> lstdctmp = new ArrayList<PLNO_DULIEU>();
            for (PLNO_DULIEU value : lstSavePln) 
            {
                if (!value.getsSoku().equals("false")) {
                    if(value.getsTrangthai().equals("R") && value.getsNgnhan_Clech().trim().isEmpty())
                    {
                       // addActionError("Bạn phải nhập nguyên nhân với món vay Không đối chiếu được! ");
                        setMessage("Bạn phải nhập nguyên nhân với món vay Không đối chiếu được! ");
                        return ERROR;
                    }
                    lstdctmp.add(value);
                }
            }
            DaoDCPLNO daoPlno = new DaoDCPLNO();
            if (daoPlno.SaveDataPLNO_DC(UserName, sNgaySl, totruong_dcpln, lstdctmp)) {
                addActionError("Đã cập nhật số liệu thông tin Phân loại nợ thành công!");
                setMessage("SUCCESS");
            } else {
                addActionError("Bạn chưa cập nhật được số liệu xin liên hệ quản trị để khắc phục lỗi! ");
                setMessage("Bạn chưa cập nhật được số liệu xin liên hệ quản trị để khắc phục lỗi! ");
                return ERROR;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " saveDataPLNo_DC -> " + e.getMessage());
            addActionError("Bạn chưa cập nhật được số liệu xin liên hệ quản trị để khắc phục lỗi!  ");
            return ERROR;
        }
        return SUCCESS;
    }
    //</editor-fold>
}
