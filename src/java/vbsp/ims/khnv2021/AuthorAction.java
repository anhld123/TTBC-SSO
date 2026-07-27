package vbsp.ims.khnv2021;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.action.Utilities;
import vbsp.ims.bcqt.model.DULIEU_NT;
import vbsp.ims.bcqt.model.ModelViewSend;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.define.GenericResult;
import vbsp.ims.khnv2021.dao.DaoMau01A;
import vbsp.ims.khnv2021.dao.XDKHDao2021;
import vbsp.ims.ktgs.dao.DaoKtgsMain;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.DuLieuNTRowX;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.LockSendModel;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.XmlKhnv2021Sync;

public class AuthorAction extends ActionSupport {

    private String CapBC;
    private String TenDN;
    private String status;
    private String cboTonghop;
    private String strNguyennhan;
    private String chkSuccess;
    private String dataReult;
    private String cboDonvi;
    private String cboNam;
    private String cboDot;
    private String btnSend;
    private String macn_detail;
    private Map session;
    private List<PosClass> lstPos = new ArrayList();
    private List<DULIEU_NT> lstData = new ArrayList();
    private InputStream pageResult;
    protected List<ModelViewSend> lstViewSend = new ArrayList();
    private String ShowMessage;
    private String namBc_2pre;
    private String namBc_pre;
    private String namBc;
    private String namBc_1;
    private String namBc_2;
    private String namBc_3;
    private String namBc_4;
    private String chotsl;
    private String message;
    DuLieuNTService _service_listts = new DuLieuNTService();
    protected DaoListPosFromUser listKTNBDA = new DaoListPosFromUser();
    protected PosMainModel posMainModel;
    protected String main_pos_username;
    private List<DuLieuNTRow> lstData_Api;
    DuLieuNTService _serverAPI = new DuLieuNTService();
    protected List<QT_DULIEU_NT> lstDulieuNt = new ArrayList();
//<editor-fold defaultstate="collapsed" desc="khai báo get,set">

    public DuLieuNTService getServerAPI() {
        return this._serverAPI;
    }

    public void setServerAPI(DuLieuNTService _serverAPI) {
        this._serverAPI = _serverAPI;
    }

    public List<QT_DULIEU_NT> getLstDulieuNt() {
        return this.lstDulieuNt;
    }

    public void setLstDulieuNt(List<QT_DULIEU_NT> lstDulieuNt) {
        this.lstDulieuNt = lstDulieuNt;
    }

    public DuLieuNTService getService_listts() {
        return this._service_listts;
    }

    public void setService_listts(DuLieuNTService _service_listts) {
        this._service_listts = _service_listts;
    }

    public DaoListPosFromUser getListKTNBDA() {
        return this.listKTNBDA;
    }

    public void setListKTNBDA(DaoListPosFromUser listKTNBDA) {
        this.listKTNBDA = listKTNBDA;
    }

    public PosMainModel getPosMainModel() {
        return this.posMainModel;
    }

    public void setPosMainModel(PosMainModel posMainModel) {
        this.posMainModel = posMainModel;
    }

    public List<DuLieuNTRow> getLstData_Api() {
        return this.lstData_Api;
    }

    public void setLstData_Api(List<DuLieuNTRow> lstData_Api) {
        this.lstData_Api = lstData_Api;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMain_pos_username(String main_pos_username) {
        this.main_pos_username = main_pos_username;
    }

    public String getNamBc_2pre() {
        return this.namBc_2pre;
    }

    public String getChotsl() {
        return this.chotsl;
    }

    public void setChotsl(String chotsl) {
        this.chotsl = chotsl;
    }

    public void setNamBc_2pre(String namBc_2pre) {
        this.namBc_2pre = namBc_2pre;
    }

    public String getNamBc_pre() {
        return this.namBc_pre;
    }

    public void setNamBc_pre(String namBc_pre) {
        this.namBc_pre = namBc_pre;
    }

    public String getNamBc() {
        return this.namBc;
    }

    public void setNamBc(String namBc) {
        this.namBc = namBc;
    }

    public String getNamBc_1() {
        return this.namBc_1;
    }

    public void setNamBc_1(String namBc_1) {
        this.namBc_1 = namBc_1;
    }

    public String getNamBc_2() {
        return this.namBc_2;
    }

    public void setNamBc_2(String namBc_2) {
        this.namBc_2 = namBc_2;
    }

    public String getNamBc_3() {
        return this.namBc_3;
    }

    public void setNamBc_3(String namBc_3) {
        this.namBc_3 = namBc_3;
    }

    public String getNamBc_4() {
        return this.namBc_4;
    }

    public void setNamBc_4(String namBc_4) {
        this.namBc_4 = namBc_4;
    }

    public String getMacn_detail() {
        return this.macn_detail;
    }

    public void setMacn_detail(String macn_detail) {
        this.macn_detail = macn_detail;
    }

    public List<ModelViewSend> getLstViewSend() {
        return this.lstViewSend;
    }

    public void setLstViewSend(List<ModelViewSend> lstViewSend) {
        this.lstViewSend = lstViewSend;
    }

    public String getCapBC() {
        return this.CapBC;
    }

    public void setCapBC(String CapBC) {
        this.CapBC = CapBC;
    }

    public String getTenDN() {
        return this.TenDN;
    }

    public void setTenDN(String TenDN) {
        this.TenDN = TenDN;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCboTonghop() {
        return this.cboTonghop;
    }

    public void setCboTonghop(String cboTonghop) {
        this.cboTonghop = cboTonghop;
    }

    public String getStrNguyennhan() {
        return this.strNguyennhan;
    }

    public void setStrNguyennhan(String strNguyennhan) {
        this.strNguyennhan = strNguyennhan;
    }

    public String getChkSuccess() {
        return this.chkSuccess;
    }

    public void setChkSuccess(String chkSuccess) {
        this.chkSuccess = chkSuccess;
    }

    public String getDataReult() {
        return this.dataReult;
    }

    public void setDataReult(String dataReult) {
        this.dataReult = dataReult;
    }

    public String getCboDonvi() {
        return this.cboDonvi;
    }

    public void setCboDonvi(String cboDonvi) {
        this.cboDonvi = cboDonvi;
    }

    public String getCboNam() {
        return this.cboNam;
    }

    public void setCboNam(String cboNam) {
        this.cboNam = cboNam;
    }

    public String getCboDot() {
        return this.cboDot;
    }

    public void setCboDot(String cboDot) {
        this.cboDot = cboDot;
    }

    public Map getSession() {
        return this.session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public List<PosClass> getLstPos() {
        return this.lstPos;
    }

    public void setLstPos(List<PosClass> lstPos) {
        this.lstPos = lstPos;
    }

    public List<DULIEU_NT> getLstData() {
        return this.lstData;
    }

    public void setLstData(List<DULIEU_NT> lstData) {
        this.lstData = lstData;
    }

    public InputStream getPageResult() {
        return this.pageResult;
    }

    public void setPageResult(InputStream pageResult) {
        this.pageResult = pageResult;
    }

    public String getBtnSend() {
        return this.btnSend;
    }

    public void setBtnSend(String btnSend) {
        this.btnSend = btnSend;
    }

    public String getShowMessage() {
        return this.ShowMessage;
    }

    public void setShowMessage(String ShowMessage) {
        this.ShowMessage = ShowMessage;
    }
//</editor-fold>

    public String execute() throws Exception {
        this.session = ActionContext.getContext().getSession();
        this.CapBC = (String) this.session.get("reportGrade");
        this.TenDN = (String) this.session.get("username");
        this.lstPos = (new AuthorModel()).getPosCD(this.CapBC, this.TenDN);
        return "success";
    }

    public String SendAction() throws Exception {
        this.session = ActionContext.getContext().getSession();
        this.CapBC = (String) this.session.get("reportGrade");
        this.TenDN = (String) this.session.get("username");
        int year_2 = Integer.parseInt(this.cboNam) - 2;
        int year_1 = Integer.parseInt(this.cboNam) - 1;
        int year1 = Integer.parseInt(this.cboNam) + 1;
        int year2 = Integer.parseInt(this.cboNam) + 2;
        int year3 = Integer.parseInt(this.cboNam) + 3;
        int year4 = Integer.parseInt(this.cboNam) + 4;
        this.namBc_2pre = String.valueOf(year_2);
        this.namBc_pre = String.valueOf(year_1);
        this.namBc = this.cboNam;
        this.namBc_1 = String.valueOf(year1);
        this.namBc_2 = String.valueOf(year2);
        this.namBc_3 = String.valueOf(year3);
        this.namBc_4 = String.valueOf(year4);
        if (this.status.trim().equals("4")) {
            this.cboTonghop = "ShowPrint";
        }

        if (this.cboTonghop.equals("W")) {
            this.strNguyennhan = (new AuthorModel()).ShowMessage(this.CapBC, this.TenDN, this.cboDonvi, this.cboNam, this.cboDot, this.cboTonghop, this.strNguyennhan);
            this.chkSuccess = "ShowMessage";
        } else {
            switch (this.status.trim()) {
                case "0":
                    this.lstData = (new AuthorModel()).getData(this.CapBC, this.TenDN, this.cboDonvi, this.cboNam, this.cboDot, this.cboTonghop, this.strNguyennhan);
                    if (this.cboTonghop.equals("S") && (this.CapBC.equals("3") || this.CapBC.equals("2")) && this.lstData != null && !this.lstData.isEmpty()) {
                        if (this.cboDot.equals("1")) {
                            this.chkSuccess = "resultSend_2024";
                        } else {
                            this.chkSuccess = "resultSend";
                        }

                        this.pageResult = new StringBufferInputStream("00");
                    } else if (this.lstData != null && !this.lstData.isEmpty()) {
                        if (!this.cboDot.equals("1")) {
                            this.chkSuccess = "SuccessLoad";
                        } else {
                            this.chkSuccess = "SuccessLoad2024";
                        }

                        this.pageResult = new StringBufferInputStream("00");
                    } else {
                        this.chkSuccess = "FaildMessage";
                        this.pageResult = new StringBufferInputStream("01");
                    }
                    break;
                case "1":
                    this.posMainModel = this.listKTNBDA.get_pos_main_pos(this.TenDN, this.CapBC);
                    this.main_pos_username = this.posMainModel.getMainPosCd();
                    String dateStr = (new Utilities()).fnc_getDateBC(this.cboNam, this.cboDot);
                    String _reportDate = (new SimpleDateFormat("yyyyMMdd")).format((new SimpleDateFormat("dd-MMM-yyyy")).parse(dateStr));
                    ArrayList<LockSendModel> lstData_tmp = this._service_listts.getDataLockManual("KHNV_03_PGD", this.main_pos_username, "M", _reportDate);

                    try {
                        this.setChotsl(((LockSendModel) lstData_tmp.get(0)).getStatus());
                    } catch (Exception var13) {
                        this.setChotsl("0");
                    }

                    if (this.cboDot.equals("1")) {
                        if (!this.chotsl.equals("2")) {
                            this.sendTw(this.CapBC, this.TenDN, this.cboDonvi, this.cboNam, this.cboDot, this.cboTonghop, this.strNguyennhan);
                            this.chkSuccess = "SuccessMessage";
                            this.pageResult = new StringBufferInputStream("10");
                        } else {
                            this.chkSuccess = "ShowMessage_TW";
                        }
                    } else {
                        this.sendTwKhnv();
                        this.chkSuccess = "SuccessMessage";
                        this.pageResult = new StringBufferInputStream("10");
                    }
                    break;
                case "2":
                    this.dataReult = (new AuthorModel()).rollBackData(this.CapBC, this.TenDN, this.cboDonvi, this.cboNam, this.cboDot, this.cboTonghop, this.strNguyennhan);
                    if (this.dataReult.equals("20")) {
                        this.chkSuccess = "SuccessRoll";
                        this.pageResult = new StringBufferInputStream("20");
                    } else {
                        this.chkSuccess = "FaildMessage";
                        this.pageResult = new StringBufferInputStream("21");
                    }

                    this.pageResult = new StringBufferInputStream(this.dataReult);
                    break;
                case "3":
                    this.dataReult = (new AuthorModel()).SaveDataProvince(this.CapBC, this.TenDN, this.cboDonvi, this.cboNam, this.cboDot, this.cboTonghop, this.strNguyennhan, this.lstData);
                    if (this.dataReult.equals("30")) {
                        this.chkSuccess = "SuccessRoll";
                        this.pageResult = new StringBufferInputStream("30");
                    } else {
                        this.chkSuccess = "FaildMessage";
                        this.pageResult = new StringBufferInputStream("31");
                    }

                    this.pageResult = new StringBufferInputStream(this.dataReult);
                    break;
                case "4":
                    this.dataReult = (new AuthorModel()).getMenuIdBc();
                    this.chkSuccess = "SuccessRoll";
                    this.pageResult = new StringBufferInputStream(this.dataReult);
            }
        }

        return this.chkSuccess;
    }

    public String ShowDetaiCn() throws Exception {
        this.session = ActionContext.getContext().getSession();
        this.CapBC = (String) this.session.get("reportGrade");
        this.TenDN = (String) this.session.get("username");
        this.lstData = (new AuthorModel()).getData(this.CapBC, this.TenDN, this.macn_detail, this.cboNam, this.cboDot, "N", "");
        return "success";
    }

    public void sendTwKhnv() {
        try {
            this.session = ActionContext.getContext().getSession();
            this.CapBC = (String) this.session.get("reportGrade");
            this.TenDN = (String) this.session.get("username");
            new ArrayList();
            Map<String, Integer> mapStatusSend = new HashMap();
            List<String> lstPos = (new DaoMau01A()).getAllPosUser(this.TenDN);

            for (String mapgd : lstPos) {
                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/") ? context.getRealPath("/") + "/" + "EXPORT_REPORT/XML/" : context.getRealPath("/") + "EXPORT_REPORT/XML/";
                strPathSave = strPathSave + "KHNV_03_PGD_" + mapgd + "_" + this.TenDN + "_" + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";
                new ArrayList();
                boolean bStatus_file = false;
                String ngayBc = (new DaoMau01A()).getNgaybc(this.cboNam, this.cboDot);
                List<String> lstData = (new DaoMau01A()).getDataSendKhnv("NT", "KHNV_03_PGD", mapgd, ngayBc);
                if (lstData != null && lstData.size() != 0) {
                    bStatus_file = (new XmlKhnv2021Sync()).createXmlFileKhnv2021("30", "NT", "KHNV_03_PGD", ngayBc, this.TenDN, this.CapBC, mapgd, lstData, "SEND", strPathSave);
                    if (!bStatus_file) {
                        CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: Khong tao duoc file " + strPathSave);
                        mapStatusSend.put(mapgd, 1);
                    }

                    File checkfile = new File(strPathSave);
                    if (!checkfile.exists()) {
                        CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: Khong tao duoc file " + strPathSave);
                        mapStatusSend.put(mapgd, 2);
                    }

                    ProcessReportSyn clientWritexml = new ProcessReportSyn();
                    String sStatus = clientWritexml.SendFileXmlToWebServices(strPathSave);
                    if (sStatus.equals("FAIL")) {
                        System.err.println("Ban chua dong bo du lieu duoc ve TW");
                        if (checkfile.exists()) {
                            checkfile.delete();
                        }

                        CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: Khong dong bo duoc file " + strPathSave);
                        mapStatusSend.put(mapgd, 3);
                    } else if (sStatus.equals("OK")) {
                        if (checkfile.exists()) {
                            checkfile.delete();
                        }

                        mapStatusSend.put(mapgd, 4);
                    } else {
                        if (checkfile.exists()) {
                            checkfile.delete();
                        }

                        mapStatusSend.put(mapgd, 5);
                    }
                } else {
                    mapStatusSend.put(mapgd, 6);
                }
            }

            this.setLstViewSend(this.getViewStatusSend(lstPos, mapStatusSend));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendKTGS: " + e.getMessage());
            this.addActionError("Bạn chưa gửi được dữ liệu xin liên hệ với quản trị để được khắc phục");
        }

    }

    public void sendTw(String CapBC, String TenDN, String cboDonvi, String cboNam, String cboDot, String cboTonghop, String strNguyennhan) {
        try {
            this.session = ActionContext.getContext().getSession();
            AuthorModel daoMain = new AuthorModel();
            XDKHDao2021 daoXdkh = new XDKHDao2021();
            int nambc = Integer.parseInt(cboNam) - 1;
            String _reportDate = nambc + "0630";
            String _reportDate1 = (new SimpleDateFormat("yyyy-MM-dd'T'00:00:00.000")).format((new SimpleDateFormat("yyyyMMdd")).parse(_reportDate));
            Map<String, Integer> mapStatusSend = new HashMap();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            String dateFormat = formatter.format(new Date());
            List<String> lstPos = new ArrayList();
            lstPos.add(this.main_pos_username);
            ArrayList<LockSendModel> lstData_tmp = this._serverAPI.getDataLockManual("KHNV_03_PGD", this.main_pos_username, "M", _reportDate);

            String chotsl;
            try {
                chotsl = ((LockSendModel) lstData_tmp.get(0)).getStatus();
            } catch (Exception var23) {
                chotsl = "0";
            }

            if ("2".equals(chotsl)) {
                mapStatusSend.put(this.main_pos_username, 5);
                this.setLstViewSend(this.getViewStatus(lstPos, mapStatusSend));
            } else {
                this.lstData = daoMain.getData("4", TenDN, this.main_pos_username, cboNam, cboDot, "Y", strNguyennhan);
                if (this.lstData != null && !this.lstData.isEmpty()) {
                    ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList();

                    for (DULIEU_NT data : this.lstData) {
                        DuLieuNTRowX tempadd = new DuLieuNTRowX();
                        tempadd.setKey("KHNV_03_PGD");
                        tempadd.setOrderValue(data.getTHUTU());
                        tempadd.setOrderDescription(data.getTT_HIENTHI());
                        tempadd.setName(data.getTEN());
                        tempadd.setCode(data.getMA());
                        tempadd.setMakerId(data.getNGUOI_NHAP());
                        tempadd.setMakerDate(dateFormat);
                        tempadd.setAuthoriseId(data.getNGUOI_DUYET());
                        tempadd.setAuthoriseDate(dateFormat);
                        tempadd.setReportDate(_reportDate1);
                        tempadd.setReportYear(data.getNAMBC());
                        tempadd.setPosCode(data.getMAPGD());
                        tempadd.setPosFlag(data.getCO_TONGHOP());
                        tempadd.setBranchCode(data.getMACN());
                        tempadd.setD1(data.getD1());
                        tempadd.setD2(data.getD2());
                        tempadd.setD3(data.getD3());
                        tempadd.setD4(data.getD4());
                        tempadd.setD5(data.getD5());
                        tempadd.setD6(data.getD6());
                        tempadd.setD7(data.getD7());
                        tempadd.setD8(data.getD8());
                        tempadd.setD9(data.getD9());
                        tempadd.setD10(data.getD10());
                        tempadd.setD11(data.getD11());
                        tempadd.setD12(data.getD12());
                        tempadd.setD13(data.getD13());
                        tempadd.setD14(data.getD14());
                        tempadd.setD15(data.getD15());
                        tempadd.setD16(data.getD16());
                        tempadd.setD17(data.getD17());
                        tempadd.setD18(data.getD18());
                        tempadd.setD19(data.getD19());
                        tempadd.setD20(data.getD20());
                        tempadd.setD30("Gửi file excel");
                        lstUpdateDate.add(tempadd);
                    }

                    this._serverAPI = new DuLieuNTService();
                    int status = this._serverAPI.getGQVL2023("KHNV_03_PGD", this.main_pos_username, "M", _reportDate, "", TenDN, lstUpdateDate);
                    if (status == 200) {
                        int skhoa = this._serverAPI.updateChotSL("KHNV_03_PGD", this.main_pos_username, "M", _reportDate, "1", TenDN, (ArrayList) null);
                        if (skhoa == 200) {
                            String message = daoXdkh.getCheck_2026("CHECK_SEND_TW", cboNam, cboDot, this.main_pos_username, "2", TenDN, "", "CHECK_SEND_TW");
                            if (message.endsWith("AAA")) {
                                mapStatusSend.put(this.main_pos_username, 4);
                            } else {
                                mapStatusSend.put(this.main_pos_username, 2);
                            }
                        } else {
                            mapStatusSend.put(this.main_pos_username, 3);
                        }
                    } else {
                        mapStatusSend.put(this.main_pos_username, 1);
                    }

                    this.setLstViewSend(this.getViewStatus(lstPos, mapStatusSend));
                } else {
                    mapStatusSend.put(this.main_pos_username, 6);
                    this.setLstViewSend(this.getViewStatus(lstPos, mapStatusSend));
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> KHNV", e);
            throw new RuntimeException(e);
        }
    }

    public void sendTwKhnv_2024(String CapBC, String TenDN, String cboDonvi, String cboNam, String cboDot, String cboTonghop, String strNguyennhan) {
        try {
            System.err.println("qưe");
            this.session = ActionContext.getContext().getSession();
            CapBC = (String) this.session.get("reportGrade");
            TenDN = (String) this.session.get("username");
            new ArrayList();
            Map<String, Integer> mapStatusSend = new HashMap();
            List<String> lstPos = (new DaoMau01A()).getAllPosUser(TenDN);

            for (String mapgd : lstPos) {
                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/") ? context.getRealPath("/") + "/" + "EXPORT_REPORT/XML/" : context.getRealPath("/") + "EXPORT_REPORT/XML/";
                strPathSave = strPathSave + "KHNV_03_PGD" + mapgd + "_" + TenDN + "_" + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";
                new ArrayList();
                boolean bStatus_file = false;
                String ngayBc = (new DaoMau01A()).getNgaybc(cboNam, cboDot);
                List<String> lstData = (new DaoMau01A()).getDataSendKhnv("NT", "KHNV_03_PGD", mapgd, ngayBc);
                if (lstData != null && lstData.size() != 0) {
                    bStatus_file = (new XmlKhnv2021Sync()).createXmlFileKhnv2021("30", "NT", "KHNV_03_PGD", ngayBc, TenDN, CapBC, mapgd, lstData, "SEND", strPathSave);
                    if (!bStatus_file) {
                        CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: Khong tao duoc file " + strPathSave);
                        mapStatusSend.put(mapgd, 1);
                    }

                    File checkfile = new File(strPathSave);
                    if (!checkfile.exists()) {
                        CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: Khong tao duoc file " + strPathSave);
                        mapStatusSend.put(mapgd, 2);
                    }

                    ProcessReportSyn clientWritexml = new ProcessReportSyn();
                    String sStatus = clientWritexml.SendFileXmlToWebServices(strPathSave);
                    if (sStatus.equals("FAIL")) {
                        System.err.println("Ban chua dong bo du lieu duoc ve TW");
                        if (checkfile.exists()) {
                            checkfile.delete();
                        }

                        CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: Khong dong bo duoc file " + strPathSave);
                        mapStatusSend.put(mapgd, 3);
                    } else if (sStatus.equals("OK")) {
                        if (checkfile.exists()) {
                            checkfile.delete();
                        }

                        mapStatusSend.put(mapgd, 4);
                    } else {
                        if (checkfile.exists()) {
                            checkfile.delete();
                        }

                        mapStatusSend.put(mapgd, 5);
                    }
                } else {
                    mapStatusSend.put(mapgd, 6);
                }
            }

            this.setLstViewSend(this.getViewStatusSend(lstPos, mapStatusSend));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendKTGS: " + e.getMessage());
            this.addActionError("Bạn chưa gửi được dữ liệu xin liên hệ với quản trị để được khắc phục");
        }

    }

    private List<ModelViewSend> getViewStatusSend(List<String> lstPos, Map<String, Integer> mapStatus) {
        this.addActionMessage("Danh sách các PGD gửi dữ liệu và tình trạng dữ liệu");
        List<ModelViewSend> lstStatus = new ArrayList();

        try {
            Map<String, String> mapPosByName = DaoKtgsMain.newInstance().getPosByName(lstPos);

            for (String key : mapStatus.keySet()) {
                if (mapPosByName.get(key) != null) {
                    Integer value = (Integer) mapStatus.get(key);
                    ModelViewSend modelview = ModelViewSend.newInstance();
                    modelview.setMapgd(key);
                    modelview.setKey(value);
                    modelview.setTenpgd((String) mapPosByName.get(key));
                    switch (value) {
                        case 1:
                            modelview.setMota_loi("Tạo file xml bị lỗi");
                            break;
                        case 2:
                            modelview.setMota_loi("Không tìm thấy file xml");
                            break;
                        case 3:
                            modelview.setMota_loi("Gửi dữ liệu bị lỗi");
                            break;
                        case 4:
                            modelview.setMota_loi("Thành công");
                            break;
                        case 5:
                            modelview.setMota_loi("Phòng giao dịch này bị khóa");
                            break;
                        case 6:
                            modelview.setMota_loi("Không có dữ liệu");
                            break;
                        default:
                            modelview.setMota_loi("Không đúng trạng thái lỗi");
                    }

                    lstStatus.add(modelview);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> getViewStatusSend: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> getViewStatusSend: " + e.getMessage());
        }

        return lstStatus;
    }

    private List<ModelViewSend> getViewStatus(List<String> lstPos, Map<String, Integer> mapStatus) {
        this.addActionMessage("Danh sách các PGD gửi dữ liệu và tình trạng dữ liệu");
        List<ModelViewSend> lstStatus = new ArrayList();

        try {
            Map<String, String> mapPosByName = DaoKtgsMain.newInstance().getPosByName(lstPos);

            for (String key : mapStatus.keySet()) {
                if (mapPosByName.get(key) != null) {
                    Integer value = (Integer) mapStatus.get(key);
                    ModelViewSend modelview = ModelViewSend.newInstance();
                    modelview.setMapgd(key);
                    modelview.setKey(value);
                    modelview.setTenpgd((String) mapPosByName.get(key));
                    switch (value) {
                        case 1:
                            modelview.setMota_loi("Lỗi kết nối đến TW");
                            break;
                        case 2:
                            modelview.setMota_loi("Lỗi kết nối từ CN");
                            break;
                        case 3:
                            modelview.setMota_loi("Gửi dữ liệu bị lỗi");
                            break;
                        case 4:
                            modelview.setMota_loi("Thành công");
                            break;
                        case 5:
                            modelview.setMota_loi("TW đã khoá gửi dữ liệu");
                            break;
                        case 6:
                            modelview.setMota_loi("CN chưa lưu dữ liệu tổng hợp");
                            break;
                        default:
                            modelview.setMota_loi("Lỗi không xác định");
                    }

                    lstStatus.add(modelview);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> getViewStatusSend: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> getViewStatusSend: " + e.getMessage());
        }

        return lstStatus;
    }

    public String lock() {
        try {
            this.session = ActionContext.getContext().getSession();
            String D1 = (String) this.session.get("username");
            String D2 = ServletActionContext.getRequest().getParameter("cboNam");
            DaoMau01A daoMain = new DaoMau01A();
            GenericResult<String> _result = daoMain.lock_all_pos("KHNV_03_PGD", "000100", D1, D2);
            if (_result.isIsSuccess()) {
                this.status = "1";
                this.message = "";
            } else {
                this.status = "0";
                this.message = _result.getMessage();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> cancelAssign: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> cancelAssign: " + e.getMessage());
            this.status = "0";
            this.message = e.getMessage();
        }

        return "success";
    }

}
