/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chamdiemcn;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import vbsp.ims.nhaptaycn.action.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import vbsp.ims.action.ktktnb.DULIEU_NT_TQ;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.chtrinh_cn.ActionChtrinhcnMain;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.menu_dcpln.DaoPlnMain;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.DuLieuPLN;
import vbsp.ims.restapi.DuLieuPLNResp_T;
import vbsp.ims.restapi.DuLieuPLN_Save;
import vbsp.ims.restapi.DuLieuPLN_T;
import vbsp.ims.restapi.ListCommune;
import vbsp.ims.restapi.ListMainPos;
import vbsp.ims.restapi.ListOfValue;
import vbsp.ims.restapi.ListPosCode;
import vbsp.ims.restapi.LockSendModel;

/**
 *
 * @author ducanh
 */
public class CDCN_CN02PL extends ActionChtrinhcnMain
        implements NhaptaycnFunction {
//<editor-fold defaultstate="collapsed" desc="khai biến">

    private List<QT_DULIEU_NT> lstData;
    private List<DuLieuNTRow> lstData_Api;
    private List<ListPosCode> lstPGD_API;
    private List<LockSendModel> lstData_tmp;
    private List<ListCommune> lstXa_API;
    protected String main_pos_username;
    private InputStream pageResult;
    DuLieuNTService _serverAPI = new DuLieuNTService();
    DaoPlnMain daoMain11 = new DaoPlnMain();
    private String chotsl;
    private String chotsl_tw;
    private String txtGetData;
    private String status;
    private String message;
    private String check_cn;
    private String title1;
    private String tento;
    private String tenxa;
    private String skhoa;
    private String sCode;
    protected String tong_kh;
    protected String tong_monvay;
    protected long tong_duno;
    protected long tong_than;
    protected long tong_qhan;
    protected long tong_khoanh;
    protected long tong_nlai;
    protected String mato_to;
    private List<ListMainPos> lstCN_API;
    protected List<DuLieuPLN_T> lstDulieuNtPLN_T = new ArrayList<>();
    private List<DuLieuPLN_T> lstPhanLoaiNo_T;
    protected List<DuLieuPLNResp_T> lstDulieuNtPLN_TT = new ArrayList<>();
    private List<ListOfValue> lstDmKhac57;
    private List<ListOfValue> lstDmKhac106;
    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private int from;
    private int to;
    private List<DuLieuPLN> lstPhanLoaiNo;
    protected List<DuLieuPLN> lstDulieuNtPLN = new ArrayList<>();
    private String thongbao;

    public String getThongbao() {
        return thongbao;
    }

    public void setThongbao(String thongbao) {
        this.thongbao = thongbao;
    }

    public List<DuLieuPLN> getLstPhanLoaiNo() {
        return lstPhanLoaiNo;
    }

    public void setLstPhanLoaiNo(List<DuLieuPLN> lstPhanLoaiNo) {
        this.lstPhanLoaiNo = lstPhanLoaiNo;
    }

    public List<DuLieuPLN> getLstDulieuNtPLN() {
        return lstDulieuNtPLN;
    }

    public void setLstDulieuNtPLN(List<DuLieuPLN> lstDulieuNtPLN) {
        this.lstDulieuNtPLN = lstDulieuNtPLN;
    }

    //</editor-fold>
//<editor-fold defaultstate="collapsed" desc="khai báo get,set">
    public List<ListMainPos> getLstCN_API() {
        return lstCN_API;
    }

    public void setLstCN_API(List<ListMainPos> lstCN_API) {
        this.lstCN_API = lstCN_API;
    }

    public List<DuLieuPLN_T> getLstDulieuNtPLN_T() {
        return lstDulieuNtPLN_T;
    }

    public void setLstDulieuNtPLN_T(List<DuLieuPLN_T> lstDulieuNtPLN_T) {
        this.lstDulieuNtPLN_T = lstDulieuNtPLN_T;
    }

    public List<DuLieuPLN_T> getLstPhanLoaiNo_T() {
        return lstPhanLoaiNo_T;
    }

    public void setLstPhanLoaiNo_T(List<DuLieuPLN_T> lstPhanLoaiNo_T) {
        this.lstPhanLoaiNo_T = lstPhanLoaiNo_T;
    }

    public List<DuLieuPLNResp_T> getLstDulieuNtPLN_TT() {
        return lstDulieuNtPLN_TT;
    }

    public void setLstDulieuNtPLN_TT(List<DuLieuPLNResp_T> lstDulieuNtPLN_TT) {
        this.lstDulieuNtPLN_TT = lstDulieuNtPLN_TT;
    }

    public List<ListOfValue> getLstDmKhac57() {
        return lstDmKhac57;
    }

    public void setLstDmKhac57(List<ListOfValue> lstDmKhac57) {
        this.lstDmKhac57 = lstDmKhac57;
    }

    public List<ListOfValue> getLstDmKhac106() {
        return lstDmKhac106;
    }

    public void setLstDmKhac106(List<ListOfValue> lstDmKhac106) {
        this.lstDmKhac106 = lstDmKhac106;
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

    public String getMato_to() {
        return mato_to;
    }

    public void setMato_to(String mato_to) {
        this.mato_to = mato_to;
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

    public String getsCode() {
        return sCode;
    }

    public void setsCode(String sCode) {
        this.sCode = sCode;
    }

    public String getSkhoa() {
        return skhoa;
    }

    public void setSkhoa(String skhoa) {
        this.skhoa = skhoa;
    }

    public List<DuLieuNTRow> getLstData_Api() {
        return lstData_Api;
    }

    public void setLstData_Api(List<DuLieuNTRow> lstData_Api) {
        this.lstData_Api = lstData_Api;
    }

    public List<ListCommune> getLstXa_API() {
        return lstXa_API;
    }

    public void setLstXa_API(List<ListCommune> lstXa_API) {
        this.lstXa_API = lstXa_API;
    }

    public List<LockSendModel> getLstData_tmp() {
        return lstData_tmp;
    }

    public void setLstData_tmp(List<LockSendModel> lstData_tmp) {
        this.lstData_tmp = lstData_tmp;
    }

    public String getChotsl_tw() {
        return chotsl_tw;
    }

    public void setChotsl_tw(String chotsl_tw) {
        this.chotsl_tw = chotsl_tw;
    }

    public String getTento() {
        return tento;
    }

    public void setTento(String tento) {
        this.tento = tento;
    }

    public String getTenxa() {
        return tenxa;
    }

    public void setTenxa(String tenxa) {
        this.tenxa = tenxa;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getCheck_cn() {
        return check_cn;
    }

    public void setCheck_cn(String check_cn) {
        this.check_cn = check_cn;
    }

    public List<ListPosCode> getLstPGD_API() {
        return lstPGD_API;
    }

    public void setLstPGD_API(List<ListPosCode> lstPGD_API) {
        this.lstPGD_API = lstPGD_API;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTxtGetData() {
        return txtGetData;
    }

    public void setTxtGetData(String txtGetData) {
        this.txtGetData = txtGetData;
    }

    public String getChotsl() {
        return chotsl;
    }

    public void setChotsl(String chotsl) {
        this.chotsl = chotsl;
    }

    public DuLieuNTService getServerAPI() {
        return _serverAPI;
    }

    public void setServerAPI(DuLieuNTService _serverAPI) {
        this._serverAPI = _serverAPI;
    }

    public InputStream getPageResult() {
        return pageResult;
    }

    public void setPageResult(InputStream pageResult) {
        this.pageResult = pageResult;
    }

    public List<QT_DULIEU_NT> getLstData() {
        return lstData;
    }

    public void setLstData(List<QT_DULIEU_NT> lstData) {
        this.lstData = lstData;
    }

    public String getMain_pos_username() {
        return main_pos_username;
    }

    public void setMain_pos_username(String main_pos_username) {
        this.main_pos_username = main_pos_username;
    }
//</editor-fold>

    @Override
    public String load() {
        Connection conn = null;
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap<String, Object> hmParameter = getParameter();
            conn = new DaoConnect().getConnect();
            DaoChamdiemcnMain daoMain = new DaoChamdiemcnMain();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            String ngay_bc = hmParameter.get("ngay_bc").toString();
            lstDulieuNt = daoMain.getDataPL02_2025(conn, "CDTT_CN02PL", UserName, Grade, ngay_bc, pos_cd_username);
            System.out.println("lstDulieuNt= " + lstDulieuNt.size());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataDcNo -> " + e.getMessage());
            addActionError("Lỗi không thể load số liệu chi tiết lỗi " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    @Override
    public String save() {
        System.out.println("vao váe");
        Connection conn = null;
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            conn = new DaoConnect().getConnect();
            DaoChamdiemcnMain daoMain = new DaoChamdiemcnMain();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            String ngay_bc = hmParameter.get("ngay_bc").toString();
            String macb = hmParameter.get("cboCanBo").toString();
            String check = hmParameter.get("flgFilter").toString();
            ArrayList<DULIEU_NT_TQ> lstLocalDataUpdate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                if (!"1".equals(check) && "1".equals(tmp.getD21())) { // So sánh an toàn, tránh NPE
                    DULIEU_NT_TQ tempadd = new DULIEU_NT_TQ();
                    tempadd.setKHOA("CDTT_CN01PL");
                    tempadd.setMAPGD(pos_cd_username);
                    tempadd.setMACN(main_pos_username);

                    for (int i = 1; i <= 20; i++) {
                        Method getter = tmp.getClass().getMethod("getD" + i);
                        Method setter = tempadd.getClass().getMethod("setD" + i, String.class);
                        setter.invoke(tempadd, (String) getter.invoke(tmp));
                    }
                    lstLocalDataUpdate.add(tempadd);
                }
                if ("1".equals(check) && !"1".equals(tmp.getD21())) { // So sánh an toàn, tránh NPE
                    DULIEU_NT_TQ tempadd = new DULIEU_NT_TQ();
                    tempadd.setKHOA("CDTT_CN01PL");
                    tempadd.setMAPGD(pos_cd_username);
                    tempadd.setMACN(main_pos_username);

                    for (int i = 1; i <= 20; i++) {
                        Method getter = tmp.getClass().getMethod("getD" + i);
                        Method setter = tempadd.getClass().getMethod("setD" + i, String.class);
                        setter.invoke(tempadd, (String) getter.invoke(tmp));
                    }
                    lstLocalDataUpdate.add(tempadd);
                }
            }
            System.out.println("lll= " + lstLocalDataUpdate.size());
            if (lstLocalDataUpdate.size() > 400) {
                this.pageResult = new ByteArrayInputStream("300".getBytes(StandardCharsets.UTF_8));
                return SUCCESS;
            } //            public boolean save_PL01_2025(String khoa, String user, String sposcd, String scap, String sngay, String smacb, List<QT_DULIEU_NT> lstData)
            else if (!daoMain.save_PL01_2025("CDTT_CN01PL", UserName, pos_cd_username, Grade, ngay_bc, macb, check, lstLocalDataUpdate)) {
                this.pageResult = new ByteArrayInputStream("400".getBytes(StandardCharsets.UTF_8));
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
}
