/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.menu_dcpln.menu_sp;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoDCPLNO;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.define.GenericResult;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.model.PLNO_DULIEU;
import vbsp.ims.model.Pagination;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.nghiquyet11cp.DaoNghiquyet11cp;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.ListCommune;
import vbsp.ims.restapi.ListMainPos;
import vbsp.ims.restapi.ListOfValue;
import vbsp.ims.restapi.ListPosCode;
import vbsp.ims.restapi.DuLieuPLN;
import vbsp.ims.restapi.DuLieuPLNResp_T;
import vbsp.ims.restapi.DuLieuPLN_Save;
import vbsp.ims.restapi.DuLieuPLN_T;
import vbsp.ims.restapi.Meta_PLN;
import vbsp.ims.menu_dcpln.DaoPlnMain;
import vbsp.ims.restapi.AcceptancePln;
import vbsp.ims.restapi.DuLieuNTRowX;
import vbsp.ims.restapi.LockSendModel;

public class DcplnAction_2 extends ActionSupport {

    DuLieuNTService service;

    //<editor-fold defaultstate="collapsed" desc="Khai bao bien">
    protected PosMainModel posMainModel;
    protected String Grade;
    protected String UserName;
    protected String message;
    private TreeNode nodes_pos = new TreeNode();
    private String status;
    private String ngay_dcpln;
    private String dvut_dcpln;
    private String totruong_dcpln;
    private String ngvon_dcpln;
    private String chtrinh_dcpln;
    private String soku_dcpln;
    private String trangthai;
    private String ma_ngnhan_dcpln;
    private List<ListPosCode> lstPGD_API;
    private List<ListMainPos> lstCN_API;
    private List<ListCommune> lstXa_API;
    private List<DuLieuPLN> lstPhanLoaiNo;
    private String pos_cd;
    private String main_pos;
    protected DaoListPosFromUser listKTNBDA = new DaoListPosFromUser();

    private Pagination pagination = new Pagination(50, 1);
    private List<String> poscd = new ArrayList<String>();
    private List<PLNO_DULIEU> lstSavePln = new ArrayList<PLNO_DULIEU>();
    private List<ListOfValue> lstDmKhac;
    private List<ListOfValue> lstDmKhac17;
    private List<ListOfValue> lstDmKhac197;
    private List<ListOfValue> lstDmKhac26;
    private List<ListOfValue> lstDmKhac57;
    private List<ListOfValue> lstDmKhac106;
    protected List<ListValue> lstMaxa = new ArrayList<ListValue>();
    protected List<ListValue> lstMato = new ArrayList<ListValue>();
    protected List<ListValue> lstMato_T = new ArrayList<ListValue>();
    protected List<QT_DULIEU_NT> lstDulieuNt = new ArrayList<>();
    protected List<DuLieuPLN> lstDulieuNtPLN = new ArrayList<>();
    protected List<DuLieuPLN_T> lstDulieuNtPLN_T = new ArrayList<>();
    private List<DuLieuPLN_T> lstPhanLoaiNo_T;
    protected List<DuLieuPLNResp_T> lstDulieuNtPLN_TT = new ArrayList<>();
    protected String pos_cd_username;
    protected String main_pos_username;
    DuLieuNTService _serverAPI = new DuLieuNTService();
    DaoPlnMain daoMain = new DaoPlnMain();
    protected String tong_kh;
    protected String tong_monvay;
    protected long tong_duno;
    protected long tong_than;
    protected long tong_qhan;
    protected long tong_khoanh;
    protected long tong_nlai;
    protected String mato_to;
    private InputStream pageResult;
    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private int from;
    private int to;
    private String txtGetData;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTxtGetData() {
        return txtGetData;
    }

    public void setTxtGetData(String txtGetData) {
        this.txtGetData = txtGetData;
    }

    public List<ListOfValue> getLstDmKhac() {
        return lstDmKhac;
    }

    public void setLstDmKhac(List<ListOfValue> lstDmKhac) {
        this.lstDmKhac = lstDmKhac;
    }

    public List<DuLieuPLNResp_T> getLstDulieuNtPLN_TT() {
        return lstDulieuNtPLN_TT;
    }

    public void setLstDulieuNtPLN_TT(List<DuLieuPLNResp_T> lstDulieuNtPLN_TT) {
        this.lstDulieuNtPLN_TT = lstDulieuNtPLN_TT;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public InputStream getPageResult() {
        return pageResult;
    }

    public void setPageResult(InputStream pageResult) {
        this.pageResult = pageResult;
    }

    public String getMato_to() {
        return mato_to;
    }

    public void setMato_to(String mato_to) {
        this.mato_to = mato_to;
    }

    public String getTong_kh() {
        return tong_kh;
    }

    public void setTong_kh(String tong_kh) {
        this.tong_kh = tong_kh;
    }

    public String getTong_monvay() {
        return tong_monvay;
    }

    public void setTong_monvay(String tong_monvay) {
        this.tong_monvay = tong_monvay;
    }

    public long getTong_duno() {
        return tong_duno;
    }

    public void setTong_duno(long tong_duno) {
        this.tong_duno = tong_duno;
    }

    public long getTong_than() {
        return tong_than;
    }

    public void setTong_than(long tong_than) {
        this.tong_than = tong_than;
    }

    public long getTong_qhan() {
        return tong_qhan;
    }

    public void setTong_qhan(long tong_qhan) {
        this.tong_qhan = tong_qhan;
    }

    public long getTong_khoanh() {
        return tong_khoanh;
    }

    public void setTong_khoanh(long tong_khoanh) {
        this.tong_khoanh = tong_khoanh;
    }

    public long getTong_nlai() {
        return tong_nlai;
    }

    public void setTong_nlai(long tong_nlai) {
        this.tong_nlai = tong_nlai;
    }

    public void setTong_nlai(int tong_nlai) {
        this.tong_nlai = tong_nlai;
    }

    public List<DuLieuPLN_T> getLstPhanLoaiNo_T() {
        return lstPhanLoaiNo_T;
    }

    public void setLstPhanLoaiNo_T(List<DuLieuPLN_T> lstPhanLoaiNo_T) {
        this.lstPhanLoaiNo_T = lstPhanLoaiNo_T;
    }

    public List<DuLieuPLN_T> getLstDulieuNtPLN_T() {
        return lstDulieuNtPLN_T;
    }

    public void setLstDulieuNtPLN_T(List<DuLieuPLN_T> lstDulieuNtPLN_T) {
        this.lstDulieuNtPLN_T = lstDulieuNtPLN_T;
    }

    public List<ListOfValue> getLstDmKhac106() {
        return lstDmKhac106;
    }

    public void setLstDmKhac106(List<ListOfValue> lstDmKhac106) {
        this.lstDmKhac106 = lstDmKhac106;
    }

    public List<ListOfValue> getLstDmKhac57() {
        return lstDmKhac57;
    }

    public void setLstDmKhac57(List<ListOfValue> lstDmKhac57) {
        this.lstDmKhac57 = lstDmKhac57;
    }

    public List<DuLieuPLN> getLstDulieuNtPLN() {
        return lstDulieuNtPLN;
    }

    public void setLstDulieuNtPLN(List<DuLieuPLN> lstDulieuNtPLN) {
        this.lstDulieuNtPLN = lstDulieuNtPLN;
    }

    public DuLieuNTService getServerAPI() {
        return _serverAPI;
    }

    public void setServerAPI(DuLieuNTService _serverAPI) {
        this._serverAPI = _serverAPI;
    }

    public List<DuLieuPLN> getLstPhanLoaiNo() {
        return lstPhanLoaiNo;
    }

    public void setLstPhanLoaiNo(List<DuLieuPLN> lstPhanLoaiNo) {
        this.lstPhanLoaiNo = lstPhanLoaiNo;
    }

    public List<QT_DULIEU_NT> getLstDulieuNt() {
        return lstDulieuNt;
    }

    public void setLstDulieuNt(List<QT_DULIEU_NT> lstDulieuNt) {
        this.lstDulieuNt = lstDulieuNt;
    }

    public String getPos_cd_username() {
        return pos_cd_username;
    }

    public void setPos_cd_username(String pos_cd_username) {
        this.pos_cd_username = pos_cd_username;
    }

    public String getMain_pos_username() {
        return main_pos_username;
    }

    public void setMain_pos_username(String main_pos_username) {
        this.main_pos_username = main_pos_username;
    }

    public List<ListValue> getLstMaxa() {
        return lstMaxa;
    }

    public void setLstMaxa(List<ListValue> lstMaxa) {
        this.lstMaxa = lstMaxa;
    }

    public List<ListValue> getLstMato() {
        return lstMato;
    }

    public void setLstMato(List<ListValue> lstMato) {
        this.lstMato = lstMato;
    }

    public List<ListOfValue> getLstDmKhac17() {
        return lstDmKhac17;
    }

    public void setLstDmKhac17(List<ListOfValue> lstDmKhac17) {
        this.lstDmKhac17 = lstDmKhac17;
    }

    public List<ListOfValue> getLstDmKhac197() {
        return lstDmKhac197;
    }

    public void setLstDmKhac197(List<ListOfValue> lstDmKhac197) {
        this.lstDmKhac197 = lstDmKhac197;
    }

    public List<ListOfValue> getLstDmKhac26() {
        return lstDmKhac26;
    }

    public void setLstDmKhac26(List<ListOfValue> lstDmKhac26) {
        this.lstDmKhac26 = lstDmKhac26;
    }

    public DuLieuNTService getService() {
        return service;
    }

    public void setService(DuLieuNTService service) {
        this.service = service;
    }

    public List<ListPosCode> getLstPGD_API() {
        return lstPGD_API;
    }

    public void setLstPGD_API(List<ListPosCode> lstPGD_API) {
        this.lstPGD_API = lstPGD_API;
    }

    public List<ListMainPos> getLstCN_API() {
        return lstCN_API;
    }

    public void setLstCN_API(List<ListMainPos> lstCN_API) {
        this.lstCN_API = lstCN_API;
    }

    public List<ListCommune> getLstXa_API() {
        return lstXa_API;
    }

    public void setLstXa_API(List<ListCommune> lstXa_API) {
        this.lstXa_API = lstXa_API;
    }

    public String getPos_cd() {
        return pos_cd;
    }

    public void setPos_cd(String pos_cd) {
        this.pos_cd = pos_cd;
    }

    public String getMain_pos() {
        return main_pos;
    }

    public void setMain_pos(String main_pos) {
        this.main_pos = main_pos;
    }

    public DaoListPosFromUser getListKTNBDA() {
        return listKTNBDA;
    }

    public void setListKTNBDA(DaoListPosFromUser listKTNBDA) {
        this.listKTNBDA = listKTNBDA;
    }

    public PosMainModel getPosMainModel() {
        return posMainModel;
    }

    public void setPosMainModel(PosMainModel posMainModel) {
        this.posMainModel = posMainModel;
    }

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
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getMa_ngnhan_dcpln() {
        return ma_ngnhan_dcpln;
    }

    public void setMa_ngnhan_dcpln(String ma_ngnhan_dcpln) {
        this.ma_ngnhan_dcpln = ma_ngnhan_dcpln;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Hàm dùng chung Chạy chương trình lúc đầu">
    protected boolean getParaSession() {
        Map session = ActionContext.getContext().getSession();
        if (session == null || session.isEmpty() || session.isEmpty()) {
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

    public String execute() {
        lstDmKhac = _serverAPI.getListOfValue("196", "PLN_KNTN_CL");
        if (lstDmKhac.get(0).getValue().equals("1")) {
            addActionError("Chương trình hiện tại chưa được quyền khai thác!");
            return ERROR;
        }
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            DaoDCPLNO daoRisk = new DaoDCPLNO();
//            List<ModelTreeNode> lstModelTree = daoRisk.getDataPosTreeNode(UserName, Grade);
            DuLieuNTService _serverAPI = new DuLieuNTService();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            ActionContext.getContext().getSession().put("UserName", UserName);
            pos_cd = posMainModel.getPosCd();
            main_pos = posMainModel.getMainPosCd();
            DaoPlnMain daoMain11 = new DaoPlnMain();

            List<ModelTreeNode> lstModelTree = new ArrayList<>();
            switch (Grade) {
                case "3":
                    lstCN_API = _serverAPI.getListCn("");
                    break;
                case "2":
                    lstXa_API = _serverAPI.getListXa(main_pos.substring(2, 4), "", "", "");
                    lstPGD_API = _serverAPI.getListPgd(main_pos, "");
                    break;
                default:
                    setLstMato(daoMain11.getDanhMuc(UserName, "MATO_PLN", Grade));
                    setLstMaxa(daoMain11.getDanhMuc(UserName, "MAXA", Grade));
                    break;
            }
//           hoi doan the
            lstDmKhac17 = _serverAPI.getListOfValue("17", "");
            lstDmKhac17.removeIf(item -> "10".equals(item.getCode()));
            lstDmKhac17.sort((a, b) -> Integer.compare(
                    Integer.parseInt(a.getCode()),
                    Integer.parseInt(b.getCode())
            ));

//            dm san pham
            lstDmKhac197 = _serverAPI.getListOfValue("197", "");
            lstDmKhac197.sort((a, b)
                    -> Integer.compare(a.getSortOrder(), b.getSortOrder())
            );
//            nguonvon
//            lstDmKhac26 = _serverAPI.getListOfValue("26", "");

            String actionName = ServletActionContext.getActionMapping().getName();
//            System.out.println("actionName= " + actionName + " Grade= " + Grade);
            if ("loadFormMainPLN".equals(actionName) && !"1".equals(Grade)) {
                addActionError("Menu chỉ dành cho cấp phòng giao dịch!");
                return ERROR;
            } else if ("loadDataSendPLN".equals(actionName) && !"2".equals(Grade)) {
                addActionError("Menu chỉ dành cho cấp chi nhánh!");
                return ERROR;
            } else if ("loadTwFormMainPLN".equals(actionName) && !"3".equals(Grade)) {
                addActionError("Menu chỉ dành cho cấp TW!");
                return ERROR;
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " execute -> " + e.getMessage());
        }
        return SUCCESS;
    }

    public String getDataDcPLN() {
        String soku = "";
        String txtGetData = "0";
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap<String, Object> hmParameter = getParameter();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            ActionContext.getContext().getSession().put("UserName", UserName);
            ActionContext.getContext().getSession().put("pos_cd_username", pos_cd_username);
            lstDmKhac106 = _serverAPI.getListOfValue("192", "");
            lstDmKhac57 = _serverAPI.getListOfValue("193", "");
            String dateStr = hmParameter.get("ngay_bc").toString();
            ActionContext.getContext().getSession().put("dateStr", dateStr);
            txtGetData = hmParameter.get("txtGetData").toString();
            String mahoi = "";
            String mato = "";

            soku = (hmParameter.get("soku") == null || hmParameter.get("soku").toString().trim().isEmpty()) ? "" : hmParameter.get("soku").toString();
            if (!soku.trim().equals("")) {
                lstPhanLoaiNo_T = _serverAPI.postDataPLN(pos_cd_username, "S", dateStr, "", "", soku, "", "", "", "");
                lstCN_API = _serverAPI.getListCn("");
                lstPGD_API = _serverAPI.getListPgd("", "");
            } else {
                String nguonvon = (hmParameter.get("nguonvon") == null || "0".equals(hmParameter.get("nguonvon").toString())) ? "" : hmParameter.get("nguonvon").toString();
                String trangthai = (hmParameter.get("trangthai") == null || "0".equals(hmParameter.get("trangthai").toString())) ? "" : hmParameter.get("trangthai").toString();
                String chtrinh = (hmParameter.get("chtrinh") == null || "0".equals(hmParameter.get("chtrinh").toString())) ? "" : hmParameter.get("chtrinh").toString();
                mahoi = hmParameter.get("mahoi").toString();
                if (mahoi.equals("0")) {
                    addActionError("Bạn chưa chọn Hội ủy thác!");
                    return ERROR;
                }
                mato = hmParameter.get("mato").toString();
                String[] values = mato.split("\\_");
                String smato = values[2];
                if (mato.equals("0000000")) {
                    addActionError("Bạn chưa chọn tổ TK&VV!");
                    return ERROR;
                }
                final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));

                lstPhanLoaiNo_T = _serverAPI.postDataPLN(pos_cd_username, "S", dateStr, mahoi, smato.equals("NOGROUP") ? "" : smato, "", trangthai, nguonvon, chtrinh, "");

            }

//            System.out.println("lstPhanLoaiNo_T=" + lstPhanLoaiNo_T.size());
            if (lstPhanLoaiNo_T == null || lstPhanLoaiNo_T.isEmpty()) {
                addActionError("Không có dữ liệu đối chiếu!");
                return ERROR;
            }

            for (DuLieuPLN_T item : lstPhanLoaiNo_T) {
                DuLieuPLN_T row = convertPLN_T(item);
                mato = item.getPlnMato();
                mahoi = item.getPlnDvut();
                lstDulieuNtPLN_T.add(row);
            }
            lstDulieuNtPLN_T.sort(
                    Comparator.comparing(obj -> layTen(obj.getPlnTenkh()), String.CASE_INSENSITIVE_ORDER)
            );
            List<DuLieuPLN_T> lstData = _serverAPI.postDataPLN(pos_cd_username, "S", dateStr, mahoi, mato, "", "", "", "", "");
            Set<String> setKhachHang = new HashSet<>();
            long tongThan = 0, tongQhan = 0, tongKhoanh = 0, tongDuno = 0, tongNlai = 0, tongMonvay = 0;
            String stmato_to = "";
            for (DuLieuPLN_T item : lstData) {
                long dnoThan = item.getPlnDnothan();
                long dnoQhan = item.getPlnDnoqhan();
                long dnoKhoanh = item.getPlnDnokhoanh();
                long laiTon = item.getPlnTonglaiton();

                tongThan += dnoThan;
                tongQhan += dnoQhan;
                tongKhoanh += dnoKhoanh;
                tongNlai += laiTon;
                tongMonvay++;

                stmato_to = item.getPlnMato() + " - " + item.getPlnTentt();

                if (item.getPlnMakh() != null && !item.getPlnMakh().isEmpty()) {
                    setKhachHang.add(item.getPlnMakh());
                }
            }

            tongDuno = tongThan + tongQhan + tongKhoanh;

            this.tong_monvay = String.valueOf(tongMonvay);
            this.tong_kh = String.valueOf(setKhachHang.size());
            this.tong_than = tongThan;
            this.tong_qhan = tongQhan;
            this.tong_khoanh = tongKhoanh;
            this.tong_duno = tongDuno;
            this.tong_nlai = tongNlai;
            this.mato_to = stmato_to.toUpperCase();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataDcNo -> " + e.getMessage());
            addActionError("Lỗi không thể load số liệu chi tiết lỗi " + e.getMessage());
            return ERROR;
        }
        System.out.println("txtGetData= " + txtGetData);
        return !soku.trim().equals("") && txtGetData.equals("1") ? "success_1" : SUCCESS;
    }

    public String sendSupportDcPln() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            String makh = ServletActionContext.getRequest().getParameter("makh");
            String macn_sp = ServletActionContext.getRequest().getParameter("macn_sp");
            String mapgd_sp = ServletActionContext.getRequest().getParameter("mapgd_sp");
            String mapgd = ServletActionContext.getRequest().getParameter("mapgd");
            String dateStr = (String) ActionContext.getContext().getSession().get("dateStr");
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            lstPhanLoaiNo_T = _serverAPI.postDataPLN(mapgd, "S", dateStr, "", "", makh, "", "", "", "");
            ArrayList<DuLieuPLN_Save> lstUpdateDate = new ArrayList<>();
            for (DuLieuPLN_T tmp : lstDulieuNtPLN_T) {
                DuLieuPLN_Save tempadd = new DuLieuPLN_Save();
                tempadd.setLoanId(tmp.getPlnSoku());
                tempadd.setCustomerId(tmp.getPlnMakh());
                tempadd.setD1(macn_sp);
                tempadd.setD2(mapgd_sp);
                tempadd.setD7("1");
                lstUpdateDate.add(tempadd);
            }
            _serverAPI = new DuLieuNTService();
            int status = _serverAPI.savePLN_2025(mapgd, _reportDate, UserName, lstUpdateDate);
            if (status != 200) {
                this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> send 23: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> send 23: " + e.getMessage());
            this.pageResult = new ByteArrayInputStream("500".getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String getDataViewSendPLN() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap<String, Object> hmParameter = getParameter();
            if (hmParameter.size() < 3) {
                addActionError("Bạn chưa chọn Phòng giao dịch!");
                return ERROR;
            }
            lstDmKhac106 = _serverAPI.getListOfValue("192", "");
            lstDmKhac57 = _serverAPI.getListOfValue("193", "");

            String dateStr = hmParameter.get("ngay_bc").toString();
            String maxa_key1 = hmParameter.get("lstXa").toString();
            if (maxa_key1.equals("000000")) {
                addActionError("Bạn chưa chọn xã!");
                return ERROR;
            }
//            System.out.println("maxa_key= " + maxa_key1);
            String[] values = maxa_key1.split("\\|");
            String mapgd = values[0];  // giá trị posCode
            String maxa = values[1];   // giá trị posName

            String mahoi = hmParameter.get("mahoi").toString();
            if (mahoi.equals("0")) {
                addActionError("Bạn chưa chọn Hội ủy thác!");
                return ERROR;
            }
            String mato = hmParameter.get("mato_data").toString();
//            System.out.println("11 mato= " + mato);
            String[] values1 = mato.split("\\_");
            String smato = values1[2];
            if (mato.equals("0000000")) {
                addActionError("Bạn chưa chọn tổ TK&VV!");
                return ERROR;
            }
            poscd = new ArrayList<>();
            DuLieuPLNResp_T response = _serverAPI.postDataPLN2(mapgd, "S", poscd, dateStr, mahoi, smato.equals("NOGROUP") ? "" : smato, 0, 0, 0);

            List<DuLieuPLN_T> lstPhanLoaiNo_T = response.getResult(); // danh sách dữ liệu
            Meta_PLN metaInfo = response.getMeta(); // thông tin meta nếu cần
            setTotal(metaInfo.getTotal());
            setPer_page(metaInfo.getPer_page());
            setCurrent_page(metaInfo.getCurrent_page());
            setLast_page(metaInfo.getLast_page());
            setFrom(metaInfo.getFrom());
            setTo(metaInfo.getTo());
            if (lstPhanLoaiNo_T == null || lstPhanLoaiNo_T.isEmpty()) {
                addActionError("Không có dữ liệu đối chiếu!");
                return ERROR;
            }
            for (DuLieuPLN_T item : lstPhanLoaiNo_T) {
                DuLieuPLN_T row = convertPLN_T(item);
                lstDulieuNtPLN_T.add(row);
            }
            lstDulieuNtPLN_T.sort(
                    Comparator.comparing(obj -> layTen(obj.getPlnTenkh()), String.CASE_INSENSITIVE_ORDER)
            );
            List<DuLieuPLN_T> lstData = _serverAPI.postDataPLN(mapgd, "S", dateStr, mahoi, smato.equals("NOGROUP") ? "" : smato, "", "", "", "", "");
            Set<String> setKhachHang = new HashSet<>();
            long tongThan = 0, tongQhan = 0, tongKhoanh = 0, tongDuno = 0, tongNlai = 0, tongMonvay = 0;
            String stmato_to = "";
            for (DuLieuPLN_T item : lstData) {
                long dnoThan = item.getPlnDnothan();
                long dnoQhan = item.getPlnDnoqhan();
                long dnoKhoanh = item.getPlnDnokhoanh();
                long laiTon = item.getPlnTonglaiton();

                tongThan += dnoThan;
                tongQhan += dnoQhan;
                tongKhoanh += dnoKhoanh;
                tongNlai += laiTon;
                tongMonvay++;

                stmato_to = item.getPlnMato() + " - " + item.getPlnTentt();

                if (item.getPlnMakh() != null && !item.getPlnMakh().isEmpty()) {
                    setKhachHang.add(item.getPlnMakh());
                }
            }

            tongDuno = tongThan + tongQhan + tongKhoanh;

            this.tong_monvay = String.valueOf(tongMonvay);
            this.tong_kh = String.valueOf(setKhachHang.size());
            this.tong_than = tongThan;
            this.tong_qhan = tongQhan;
            this.tong_khoanh = tongKhoanh;
            this.tong_duno = tongDuno;
            this.tong_nlai = tongNlai;
            this.mato_to = stmato_to.toUpperCase();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataDcNo -> " + e.getMessage());
            addActionError("Lỗi không thể load số liệu chi tiết lỗi " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String getDetialLoanDcPLN() {
        String lock = "";
        try {
            if (!getParaSession()) {
                return ERROR;
            }

            HashMap hmParameter = getParameter();
            String dateStr = hmParameter.get("ngay_bc").toString();
            String soku = hmParameter.get("soku").toString();
            String poscd = hmParameter.get("poscd").toString();
            lock = hmParameter.get("lock").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd")
                    .format(new SimpleDateFormat("dd/MM/yyyy").parse(dateStr));

            // Lưu các tham số vào session
            ActionContext.getContext().getSession().put("_reportDate", _reportDate);
            ActionContext.getContext().getSession().put("soku", soku);
            ActionContext.getContext().getSession().put("poscd", poscd);

            // Gọi dữ liệu phân loại nợ
            lstPhanLoaiNo = _serverAPI.getDataPLN(poscd, soku, _reportDate);
            if (lstPhanLoaiNo == null || lstPhanLoaiNo.isEmpty()) {
                addActionError("Chưa có dữ liệu, liên hệ TTCNTT để được hỗ trợ");
                return ERROR;
            }

            for (DuLieuPLN item : lstPhanLoaiNo) {
                DuLieuPLN row = convertPLN(item);
                lstDulieuNtPLN.add(row);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDetialLoanDcPLN -> " + e.getMessage());
            return ERROR;
        }
        return "1".equals(Grade) || !lock.equals("1") ? "success" : "success_1";
    }

    private DuLieuPLN convertPLN(DuLieuPLN item) {
        DuLieuPLN row = new DuLieuPLN();

        if (item == null) {
            return row;
        }

        try {
            row.setPlnSoku(item.getPlnSoku());
            row.setPlnMakh(item.getPlnMakh());
            row.setPlnTenkh(item.getPlnTenkh());
            row.setPlnMato(item.getPlnMato());
            row.setPlnTentt(item.getPlnTentt());
            row.setPlnDvut(item.getPlnDvut());
            row.setPlnDvutTen(item.getPlnDvutTen());
            row.setPlnMadp(item.getPlnMadp());
            row.setPlnNguonvon(item.getPlnNguonvon());
            row.setPlnNguonvonTen(item.getPlnNguonvonTen());

            row.setPlnSprdCd(item.getPlnSprdCd());
            row.setPlnSprdCdTen(item.getPlnSprdCdTen());
            row.setPlnChtrinh(item.getPlnChtrinh());
            row.setPlnChtrinhTen(item.getPlnChtrinhTen());
            row.setPlnChtrinhTenvt(item.getPlnChtrinhTenvt());

            int dnothan = safeInt(item.getPlnDnothan());
            int dnoqhan = safeInt(item.getPlnDnoqhan());
            int dnokhoanh = safeInt(item.getPlnDnokhoanh());
            row.setPlnDnothan(dnothan);
            row.setPlnDnoqhan(dnoqhan);
            row.setPlnDnokhoanh(dnokhoanh);
            row.setPlnTongDno(dnothan + dnoqhan + dnokhoanh);

            row.setPlnLaitonthan(safeInt(item.getPlnLaitonthan()));
            row.setPlnLaitonqhan(safeInt(item.getPlnLaitonqhan()));
            row.setPlnTonglaiton(safeInt(item.getPlnTonglaiton()));
            row.setPlnTonglaiTt(safeInt(item.getPlnTonglaiTt()));
            row.setPlnCKntnSodu(safeInt(item.getPlnCKntnSodu()));
            row.setPlnKKntnSodu(safeInt(item.getPlnKKntnSodu()));
            row.setNgnhanKckntn(item.getNgnhanKckntn());

            row.setPlnQuanheKh(item.getPlnQuanheKh());
            row.setPlnTrangthai(item.getPlnTrangthai());
            row.setPlnTrangthaiTen(item.getPlnTrangthaiTen());
            row.setPlnNogocClech(safeInt(item.getPlnNogocClech()));
            row.setPlnNolaiClech(safeInt(item.getPlnNolaiClech()));
            row.setPlnNgnhanClech(item.getPlnNgnhanClech());
            row.setPlnTtMonvay(item.getPlnTtMonvay());
            row.setPlnNgaybc(item.getPlnNgaybc());
            row.setPlnNguoiPln(item.getPlnNguoiPln());
            row.setPlnNgayPln(item.getPlnNgayPln());
            row.setPlnTrangthaino(item.getPlnTrangthaino());
            row.setPlnTrangthainoTen(item.getPlnTrangthainoTen());
            row.setTchatNo(item.getTchatNo());
            row.setTchatNoTen(item.getTchatNoTen());
            row.setPlnLoaito(item.getPlnLoaito());
            row.setPlnLoaitoTen(item.getPlnLoaitoTen());
            row.setPlnNgaycn(item.getPlnNgaycn());
            row.setPlnMacn(item.getPlnMacn());
            row.setPlnMapgd(item.getPlnMapgd());
            row.setPlnMaxa(item.getPlnMaxa());
        } catch (Exception e) {
            // Không log
        }
        return row;
    }

    private DuLieuPLN_T convertPLN_T(DuLieuPLN_T item) {
        DuLieuPLN_T row = new DuLieuPLN_T();

        if (item == null) {
            return row;
        }

        try {
            row.setPlnMacn(item.getPlnMacn());
            row.setPlnMapgd(item.getPlnMapgd());
            row.setPlnMaxa(item.getPlnMaxa());
            row.setPlnNguonvon(item.getPlnNguonvon());
            row.setPlnNguonvonTen(item.getPlnNguonvonTen());
            row.setPlnDvut(item.getPlnDvut());
            row.setPlnDvutTen(item.getPlnDvutTen());
            row.setPlnChtrinh(item.getPlnChtrinh());
            row.setPlnChtrinhTen(item.getPlnChtrinhTen());
            row.setPlnChtrinhTenvt(item.getPlnChtrinhTenvt());
            row.setPlnMato(item.getPlnMato());
            row.setPlnTentt(item.getPlnTentt());
            row.setPlnMakh(item.getPlnMakh());
            row.setPlnTenkh(item.getPlnTenkh());
            row.setPlnSoku(item.getPlnSoku());

            int dnothan = safeInt(item.getPlnDnothan());
            int dnoqhan = safeInt(item.getPlnDnoqhan());
            int dnokhoanh = safeInt(item.getPlnDnokhoanh());
            row.setPlnDnothan(dnothan);
            row.setPlnDnoqhan(dnoqhan);
            row.setPlnDnokhoanh(dnokhoanh);
            row.setPlnTongDno(dnothan + dnoqhan + dnokhoanh);

            row.setPlnTonglaiton(safeInt(item.getPlnTonglaiton()));
            row.setPlnCKntnSodu(safeInt(item.getPlnCKntnSodu()));

            row.setNgnhanKckntn(item.getNgnhanKckntn());
            row.setkNgnhanKh(item.getkNgnhanKh());
            row.setPlnQuanheKh(item.getPlnQuanheKh());
            row.setPlnTrangthai(item.getPlnTrangthai());
            row.setPlnNogocClech(safeInt(item.getPlnNogocClech()));
            row.setPlnNolaiClech(safeInt(item.getPlnNolaiClech()));
            row.setPlnNgnhanClech(item.getPlnNgnhanClech());
            row.setPlnTtMonvay(item.getPlnTtMonvay());
            row.setPlnNgaybc(item.getPlnNgaybc());
            row.setPlnNguoiPln(item.getPlnNguoiPln());
            row.setPlnNgayPln(item.getPlnNgayPln());
            row.setPlnNguyennhanC2(item.getPlnNguyennhanC2());

            String kntnSodu01 = item.getkKntnSodu01();
            row.setPlnKKntnSodu(parseIntSafe(kntnSodu01));

            row.setkKntnSodu02(item.getkKntnSodu02());
            row.setkKntnSodu03(item.getkKntnSodu03());
            row.setkKntnSodu04(item.getkKntnSodu04());
            row.setkKntnSodu05(item.getkKntnSodu05());
            row.setkKntnSodu06(item.getkKntnSodu06());
            row.setkKntnSodu07(item.getkKntnSodu07());
            row.setkKntnSodu08(item.getkKntnSodu08());
            row.setkKntnSodu09(item.getkKntnSodu09());
            row.setkKntnSodu10(item.getkKntnSodu10());
            row.setkKntnSodu11(item.getkKntnSodu11());

            row.setD1(item.getD1());
            row.setD2(item.getD2());
            row.setD3(item.getD3());
            row.setD4(item.getD4());
            row.setD5(item.getD5());
            row.setD6(item.getD6());
            row.setD7(item.getD7());
            row.setD8(item.getD8());
            row.setD9(item.getD9());
            row.setD10(item.getD10());

            row.setCheckrow("0");
        } catch (Exception e) {
            // Không log, chỉ bỏ qua lỗi
        }
        return row;
    }

    private int safeInt(Integer val) {
        return val != null ? val : 0;
    }

    private int parseIntSafe(String val) {
        if (val == null || val.isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getDb_mato() {
        try {
            System.err.println("Vào hàm getDb_mato");

            HashMap<String, Object> hmParameter = getParameter();
            if (hmParameter == null) {
                return ERROR;
            }

            String UserName = (String) ActionContext.getContext().getSession().get("UserName");
            if (UserName == null || UserName.isEmpty()) {
                return ERROR;
            }

            Object maxaObj = hmParameter.get("maxa");
            Object mahoiObj = hmParameter.get("mahoi");

            if (maxaObj == null || mahoiObj == null) {
                return ERROR;
            }

            String maxa_key = maxaObj.toString();
            String[] values = maxa_key.split("\\|");
            if (values.length < 2) {
                return ERROR;
            }

            String mapgd = values[0];
            String maxa = values[1];
            String mahoi = mahoiObj.toString();

            if (mapgd.isEmpty() || maxa.isEmpty() || mahoi.isEmpty()) {
                return ERROR;
            }

            String danhMucKey = "2_" + mapgd + "_" + maxa + "_" + mahoi;
            DaoNghiquyet11cp daoMain11 = new DaoNghiquyet11cp();
            setLstMato_T(daoMain11.getDanhMuc(UserName, "MATO_PLN", danhMucKey));

        } catch (Exception e) {
            System.err.println("Lỗi trong getDb_mato: " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDb_mato -> " + e.getMessage(), e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String saveDataDcPLN() {
        System.out.println("vao váe");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
            String date2 = LocalDateTime.now().format(formatter);

            ArrayList<DuLieuPLN_Save> lstUpdateDate = new ArrayList<>();
            for (DuLieuPLN_T tmp : lstDulieuNtPLN_T) {
                DuLieuPLN_Save tempadd = new DuLieuPLN_Save();
                if ("1".equals(tmp.getCheckrow())) {
                    tempadd.setLoanId(tmp.getPlnSoku());
                    tempadd.setCustomerId(tmp.getPlnMakh());
//                    -- 4 trường màn hình cha
                    tempadd.setReason_Deviant(tmp.getPlnNgnhanClech());
                    tempadd.setUpdateTime(date2);
                    tempadd.setAble_ToPay_Amt(tmp.getPlnCKntnSodu());
                    tempadd.setReason_Deviant02(tmp.getPlnNguyennhanC2() == null ? "0" : tmp.getPlnNguyennhanC2());
                    tempadd.setD1(tmp.getD1());
                    tempadd.setD2(tmp.getD2());
                    tempadd.setD3(tmp.getD3());
                    tempadd.setD4(tmp.getD4());
                    tempadd.setD5(tmp.getD5());
                    tempadd.setD7(tmp.getD7());
                    lstUpdateDate.add(tempadd);
                }

            }
            int status = _serverAPI.savePLN_2025(pos_cd_username, _reportDate, UserName, lstUpdateDate);
            if (status != 200) {
                this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> send 123: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> send 123: " + e.getMessage());
            this.pageResult = new ByteArrayInputStream("500".getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String saveDataDcPLN_Loan() {
        System.out.println("vao váe");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            String _reportDate = (String) ActionContext.getContext().getSession().get("_reportDate");
            String soku = (String) ActionContext.getContext().getSession().get("soku");
            String pos_cd_username = (String) ActionContext.getContext().getSession().get("pos_cd_username");
            String UserName = (String) ActionContext.getContext().getSession().get("UserName");
//            System.out.println("dateStr= " + _reportDate + " poscd= " + pos_cd_username + " UserName= " + UserName);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
            String date2 = LocalDateTime.now().format(formatter);

            ArrayList<DuLieuPLN_Save> lstUpdateDate = new ArrayList<>();
            for (DuLieuPLN_T tmp : lstDulieuNtPLN_T) {
                DuLieuPLN_Save tempadd = new DuLieuPLN_Save();
                tempadd.setLoanId(soku);
                tempadd.setCustomerId(tmp.getPlnMakh());
//              lưu  màn hình con
                tempadd.setDeviant_Amt(tmp.getPlnNogocClech());
                tempadd.setDeviant_Int(tmp.getPlnNolaiClech());
                tempadd.setStatus(tmp.getPlnTrangthai());
                tempadd.setReason_Deviant(tmp.getPlnNgnhanClech());
                tempadd.setCustRelationship(tmp.getPlnQuanheKh());
                tempadd.setUpdateTime(date2);

                lstUpdateDate.add(tempadd);
            }

            _serverAPI = new DuLieuNTService();
            int status = _serverAPI.savePLN_2025(pos_cd_username, _reportDate, UserName, lstUpdateDate);
            if (status != 200) {
                this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> send 23: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> send 23: " + e.getMessage());
            this.pageResult = new ByteArrayInputStream("500".getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }
        String code = String.valueOf(200);

        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String sendDataDcPLN() {
        System.out.println("vao váe sendata");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            String maxa_key1 = hmParameter.get("lstXa").toString();
            String[] values = maxa_key1.split("\\|");
            String mapgd = values[0];  // giá trị posCode
            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
            String date2 = LocalDateTime.now().format(formatter);

            ArrayList<DuLieuPLN_Save> lstUpdateDate = new ArrayList<>();
            for (DuLieuPLN_T tmp : lstDulieuNtPLN_T) {
                DuLieuPLN_Save tempadd = new DuLieuPLN_Save();
                if ("1".equals(tmp.getCheckrow())) {
                    tempadd.setLoanId(tmp.getPlnSoku());
                    tempadd.setCustomerId(tmp.getPlnMakh());
                    tempadd.setUpdateTimeByBranch(date2);
                    tempadd.setD6("1");
                    lstUpdateDate.add(tempadd);
                }
            }

            _serverAPI = new DuLieuNTService();
            int status = _serverAPI.savePLN_2025(mapgd, _reportDate, UserName, lstUpdateDate);
            if (status != 200) {
                this.pageResult = new ByteArrayInputStream(String.valueOf(status).getBytes(StandardCharsets.UTF_8));
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> send 23: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> send 23: " + e.getMessage());
            this.pageResult = new ByteArrayInputStream("500".getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    private long parseSafeLong(String s) {
        if (s == null || s.trim().isEmpty()) {
            return Long.MAX_VALUE;
        }
        try {
            return Long.parseLong(s.trim());
        } catch (NumberFormatException e) {
            System.err.println("Cannot parse long from: [" + s + "]");
            return Long.MAX_VALUE;
        }
    }

    public List<ListValue> getLstMato_T() {
        return lstMato_T;
    }

    public void setLstMato_T(List<ListValue> lstMato_T) {
        this.lstMato_T = lstMato_T;
    }

    public String getDataTwDcPLN() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            String sngaybc = hmParameter.get("ngay_bc").toString();
            String smacn = hmParameter.get("lstCN").toString();
            Connection conn = new DaoConnect().getConnect();
            DaoPlnMain daoMain = new DaoPlnMain();
            ActionContext.getContext().getSession().put("sUserName", UserName);
            lstDulieuNt = daoMain.getDataPlnTW(conn, sngaybc, "PLN_KNTN_CL", smacn, "S");
            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + "getDataTwDcPLN: " + e.getMessage());
            System.err.println(this.getClass().getName() + " getDataTwDcPLN: " + e.getMessage());
        }
        return "success";
    }

    private static String layTen(String hoTen) {
        if (hoTen == null || hoTen.trim().isEmpty()) {
            return "";
        }
        String[] parts = hoTen.trim().split("\\s+");
        return parts[parts.length - 1]; // lấy tên cuối
    }

    public String popupTableDcpln() throws Exception {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");
            String D12 = ServletActionContext.getRequest().getParameter("ngaybc");
            String type = ServletActionContext.getRequest().getParameter("type");
            setTxtGetData(type);
            Connection conn = new DaoConnect().getConnect();
            DaoPlnMain daoMain = new DaoPlnMain();
//            ActionContext.getContext().getSession().put("sUserName", UserName);
            lstDulieuNt = daoMain.getDataPlnTW(conn, D12, "PLN_KNTN_TW", D1, type);
            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            System.err.println("Loi trong ham saveDataaa " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveDataaa -> " + e.getMessage());
        }
        return "success";

    }

    public String popupTableDcplnTo() throws Exception {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");
            String D12 = ServletActionContext.getRequest().getParameter("ngaybc");
            String type = ServletActionContext.getRequest().getParameter("type");
            setTxtGetData(type);
            Connection conn = new DaoConnect().getConnect();
            DaoPlnMain daoMain = new DaoPlnMain();
//            ActionContext.getContext().getSession().put("sUserName", UserName);
            lstDulieuNt = daoMain.getDataPlnTW(conn, D12, "PLN_KNTN_TW", D1, type);
            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            System.err.println("Loi trong ham saveDataaa " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveDataaa -> " + e.getMessage());
        }
        return "success";

    }

    
    public String unlock_pLN() {
        try {
            String mapgd = ServletActionContext.getRequest().getParameter("mapgd");
            String makh = ServletActionContext.getRequest().getParameter("makh");
            String soku = ServletActionContext.getRequest().getParameter("soku");
            String D5 = ServletActionContext.getRequest().getParameter("ngaybc");
            String khoa = ServletActionContext.getRequest().getParameter("lock");
//            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
//            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MMM/yyyy");
//            Date date = inputFormat.parse(D5);
//            String formattedDate = outputFormat.format(date);
            DaoPlnMain daoMain = new DaoPlnMain();
            String sUserName = (String) ActionContext.getContext().getSession().get("sUserName");
//            System.out.println("sUserName= " + sUserName + " UserName= " + UserName);
            GenericResult<String> _result = daoMain.unlock_Pln(khoa + "_" + sUserName, mapgd, makh, soku, D5);
            if (_result.isIsSuccess()) {
                status = "1";
                message = "";
            } else {
                status = "0";
                message = _result.getMessage();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> cancelAssign: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> cancelAssign: " + e.getMessage());
            status = "0";
            message = e.getMessage();
        }
        return SUCCESS;
    }

    public String Mass_application() {
        Connection conn = null;
        try {
            if (!getParaSession()) {
                return ERROR;
            }

            // Lấy tham số và định dạng ngày
            HashMap hmParameter = getParameter();
            String sngaybc = hmParameter.get("ngay_bc").toString();
            DateFormat dateHienthi = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(sngaybc));
            String mapgd = hmParameter.get("smapgd").toString();
            // Lấy thông tin chính từ posMainModel
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();

            conn = new DaoConnect().getConnect();
            // Kiểm tra và xử lý dữ liệu
            if (mapgd.isEmpty() || mapgd == null) {
                addActionError("Chọn phòng giao dịch để gửi dữ liệu!");
                return ERROR;
            }
            String maxa_key1 = hmParameter.get("lstXa").toString();
            if (maxa_key1.equals("000000")) {
                addActionError("Bạn chưa chọn xã!");
                return ERROR;
            }
            String[] values = maxa_key1.split("\\|");
            String maxa = values[1];   // giá trị posName

            lstDulieuNt = daoMain.getDataPlnCn(conn, sngaybc, "PLN_KNTN_CN", mapgd, maxa);

            for (QT_DULIEU_NT item : lstDulieuNt) {
                String smato = item.getD1();
                String smahoi = item.getD2();

                long tongThan = 0, tongQhan = 0, tongKhoanh = 0, tongDuno = 0, tongNlai = 0, tongMonvay = 0;
                Set<String> setKhachHang = new HashSet<>();

                List<DuLieuPLN_T> lstData = _serverAPI.postDataPLN(
                        mapgd, "S", sngaybc,
                        smahoi,
                        smato.equals("NOGROUP") ? "" : smato,
                        "", "", "", "", ""
                );

                for (DuLieuPLN_T dt : lstData) {
                    tongThan += dt.getPlnDnothan();
                    tongQhan += dt.getPlnDnoqhan();
                    tongKhoanh += dt.getPlnDnokhoanh();
                    tongNlai += dt.getPlnTonglaiton();
                    tongMonvay++;

                    if (dt.getPlnMakh() != null && !dt.getPlnMakh().isEmpty()) {
                        setKhachHang.add(dt.getPlnMakh());
                    }
                }

                tongDuno = tongThan + tongQhan + tongKhoanh;

                item.setD16(String.valueOf(tongMonvay));           // tổng món vay
                item.setD17(String.valueOf(setKhachHang.size()));  // tổng KH
                item.setD18(String.valueOf(tongThan));             // tổng dư nợ trong hạn
                item.setD19(String.valueOf(tongQhan));             // tổng dư nợ quá hạn
                item.setD20(String.valueOf(tongKhoanh));           // tổng dư nợ khoanh
                item.setD21(String.valueOf(tongDuno));             // tổng dư nợ
                item.setD22(String.valueOf(tongNlai));             // tổng lãi tồn
                item.setD23("0");
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> seach 2024 : " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> seach 2024: " + e.getMessage());
            return ERROR;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                CoreLogger.error(this.getClass().getName() + " Exception in closing connection: " + e.getMessage());
            }
        }
        return SUCCESS;
    }

    public String sendDataPlnCn() {
        System.out.println("vao váe sendDataPlnCn");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);

            String maxa_key1 = hmParameter.get("lstXa").toString();
            String[] values = maxa_key1.split("\\|");
            String mapgd = values[0];  // giá trị posCode
            String maxa = values[1];

            String dateStr = hmParameter.get("ngay_bc").toString();
            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
            String date2 = LocalDateTime.now().format(formatter);
            // Gom tất cả groupId thỏa điều kiện
            ArrayList<String> groupIds = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                if ("1".equals(tmp.getD23())) {
                    groupIds.add(tmp.getD1());
                }
            }
            System.out.println("groupIds= " + groupIds);
            if (groupIds.isEmpty()) {
                this.pageResult = new ByteArrayInputStream("999".getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            } else if (!groupIds.isEmpty()) {
                // Tạo 1 object duy nhất
                AcceptancePln tempadd = new AcceptancePln();
                tempadd.setCommuneCode(maxa);
                tempadd.setReportDate(_reportDate);
                tempadd.setPosCode(mapgd);
                tempadd.setLstGroupId(groupIds);
                _serverAPI = new DuLieuNTService();
                int status = _serverAPI.updatePlnStatus(_reportDate, UserName, tempadd);
                if (status == 200) {
                    boolean saved = daoMain.saveChotPln(mapgd, maxa, dateStr, groupIds, UserName);
                    if (saved) {
                        Connection conn = new DaoConnect().getConnect();
                        lstDulieuNt = daoMain.getDataPlnTW(conn, dateStr, "PLN_KNTN_CHOT", mapgd, maxa);
                        ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList<>();
                        for (QT_DULIEU_NT tmp : lstDulieuNt) {
                            DuLieuNTRowX tempadd1 = new DuLieuNTRowX();
                            tempadd1.setKey("PLN_KNTN_CHOT");
                            tempadd1.setName(tmp.getD3());
                            tempadd1.setCode(tmp.getD2());
                            tempadd1.setMakerId(UserName);
                            tempadd1.setMakerDate(date2);
                            tempadd1.setAuthoriseId(UserName);
                            tempadd1.setAuthoriseDate(date2);
                            tempadd1.setReportDate(_reportDate1);
                            tempadd1.setPosCode(tmp.getD6());
                            tempadd1.setPosFlag("S");
                            tempadd1.setBranchCode(tmp.getD8());
                            tempadd1.setD1(tmp.getD13());
                            tempadd1.setD2(tmp.getD14());
                            tempadd1.setD3(tmp.getD15());
                            lstUpdateDate.add(tempadd1);
                        }

                        int status1 = _serverAPI.getGQVL2023("PLN_KNTN_CHOT", mapgd, "S", _reportDate, UserName, UserName, lstUpdateDate);
                        if (status1 == 200) {
                            this.pageResult = new ByteArrayInputStream("200".getBytes(StandardCharsets.UTF_8));
                            return SUCCESS;
                        }
                    } else {
                        this.pageResult = new ByteArrayInputStream("500".getBytes(StandardCharsets.UTF_8));
                        return ERROR;
                    }
                }
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> send 23: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> send 23: " + e.getMessage());
            this.pageResult = new ByteArrayInputStream("500".getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }

        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String unlock_pLN_to() {
        try {
            String mato = ServletActionContext.getRequest().getParameter("mato");
            String maxa = ServletActionContext.getRequest().getParameter("maxa");
            String mapgd = ServletActionContext.getRequest().getParameter("mapgd");
            String ngaybc = ServletActionContext.getRequest().getParameter("ngaybc");
            String khoa = ServletActionContext.getRequest().getParameter("lock");
            DaoPlnMain daoMain = new DaoPlnMain();
            String sUserName = (String) ActionContext.getContext().getSession().get("sUserName");
            GenericResult<String> _result = daoMain.unlock_Pln(khoa + "_" + sUserName, mapgd, mato, maxa, ngaybc);
            if (_result.isIsSuccess()) {
                status = "1";
                message = "";
            } else {
                status = "0";
                message = _result.getMessage();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> cancelAssign: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> cancelAssign: " + e.getMessage());
            status = "0";
            message = e.getMessage();
        }
        return SUCCESS;
    }
}
