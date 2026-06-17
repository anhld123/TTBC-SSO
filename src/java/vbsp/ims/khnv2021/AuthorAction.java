/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.khnv2021;

import static com.opensymphony.xwork2.Action.SUCCESS;
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
import vbsp.ims.define.Define;
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

    //Cac truong chua thong tin bo xung luu du lieu
    private String CapBC, TenDN, status, cboTonghop, strNguyennhan, chkSuccess, dataReult, cboDonvi, cboNam, cboDot, btnSend;
    private String macn_detail;
    private Map session;
    private List<PosClass> lstPos = new ArrayList<>();
    private List<DULIEU_NT> lstData = new ArrayList<>();
    private InputStream pageResult;
    protected List<ModelViewSend> lstViewSend = new ArrayList<>();
    private String ShowMessage;
    private String namBc_2pre;
    private String namBc_pre;
    private String namBc, namBc_1, namBc_2, namBc_3, namBc_4;
    private String chotsl;
    private String message;
    DuLieuNTService _service_listts = new DuLieuNTService();
    protected DaoListPosFromUser listKTNBDA = new DaoListPosFromUser();
    protected PosMainModel posMainModel;
    protected String main_pos_username;
    private List<DuLieuNTRow> lstData_Api;
    DuLieuNTService _serverAPI = new DuLieuNTService();
    protected List<QT_DULIEU_NT> lstDulieuNt = new ArrayList<>();
    //<editor-fold defaultstate="collapsed" desc="khai báo get,set">

    public DuLieuNTService getServerAPI() {
        return _serverAPI;
    }

    public void setServerAPI(DuLieuNTService _serverAPI) {
        this._serverAPI = _serverAPI;
    }

    public List<QT_DULIEU_NT> getLstDulieuNt() {
        return lstDulieuNt;
    }

    public void setLstDulieuNt(List<QT_DULIEU_NT> lstDulieuNt) {
        this.lstDulieuNt = lstDulieuNt;
    }

    public DuLieuNTService getService_listts() {
        return _service_listts;
    }

    public void setService_listts(DuLieuNTService _service_listts) {
        this._service_listts = _service_listts;
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

    public List<DuLieuNTRow> getLstData_Api() {
        return lstData_Api;
    }

    public void setLstData_Api(List<DuLieuNTRow> lstData_Api) {
        this.lstData_Api = lstData_Api;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMain_pos_username(String main_pos_username) {
        this.main_pos_username = main_pos_username;
    }

    public String getNamBc_2pre() {
        return namBc_2pre;
    }

    public String getChotsl() {
        return chotsl;
    }

    public void setChotsl(String chotsl) {
        this.chotsl = chotsl;
    }

    public void setNamBc_2pre(String namBc_2pre) {
        this.namBc_2pre = namBc_2pre;
    }

    public String getNamBc_pre() {
        return namBc_pre;
    }

    public void setNamBc_pre(String namBc_pre) {
        this.namBc_pre = namBc_pre;
    }

    public String getNamBc() {
        return namBc;
    }

    public void setNamBc(String namBc) {
        this.namBc = namBc;
    }

    public String getNamBc_1() {
        return namBc_1;
    }

    public void setNamBc_1(String namBc_1) {
        this.namBc_1 = namBc_1;
    }

    public String getNamBc_2() {
        return namBc_2;
    }

    public void setNamBc_2(String namBc_2) {
        this.namBc_2 = namBc_2;
    }

    public String getNamBc_3() {
        return namBc_3;
    }

    public void setNamBc_3(String namBc_3) {
        this.namBc_3 = namBc_3;
    }

    public String getNamBc_4() {
        return namBc_4;
    }

    public void setNamBc_4(String namBc_4) {
        this.namBc_4 = namBc_4;
    }
//</editor-fold>

    @Override
    //Lấy danh đơn vị theo cấp báo cáo
    public String execute() throws Exception {
        //Lấy danh sách đơn vị theo cấp báo cáo
        session = ActionContext.getContext().getSession();
        CapBC = (String) session.get("reportGrade");
        TenDN = (String) session.get("username");
        lstPos = new AuthorModel().getPosCD(CapBC, TenDN);
        return SUCCESS;
    }

    //0 - Tải; 1 - Gửi; 2 - Trả lại
    public String SendAction() throws Exception {
        //Lấy danh sách đơn vị theo cấp báo cáo
        session = ActionContext.getContext().getSession();
        CapBC = (String) session.get("reportGrade");
        TenDN = (String) session.get("username");
        int year_2 = Integer.parseInt(cboNam) - 2;
        int year_1 = Integer.parseInt(cboNam) - 1;
        int year1 = Integer.parseInt(cboNam) + 1;
        int year2 = Integer.parseInt(cboNam) + 2;
        int year3 = Integer.parseInt(cboNam) + 3;
        int year4 = Integer.parseInt(cboNam) + 4;
        namBc_2pre = String.valueOf(year_2);
        namBc_pre = String.valueOf(year_1);
        namBc = cboNam;
        namBc_1 = String.valueOf(year1);
        namBc_2 = String.valueOf(year2);
        namBc_3 = String.valueOf(year3);
        namBc_4 = String.valueOf(year4);
        if (status.trim().equals("4")) {
            cboTonghop = "ShowPrint";
        }
        if (cboTonghop.equals("W")) {
            //Lấy dữ liệu phản hồi từ cấp trên
            strNguyennhan = new AuthorModel().ShowMessage(CapBC, TenDN, cboDonvi, cboNam, cboDot, cboTonghop, strNguyennhan);
            chkSuccess = "ShowMessage";

        } else {
            switch (status.trim()) {
                case "0":
                    //Nhớ truyền đủ 7 tham số
                    lstData = new AuthorModel().getData(CapBC, TenDN, cboDonvi, cboNam, cboDot, cboTonghop, strNguyennhan);
//                    System.err.println(CapBC +" 11: "+ TenDN +" 2: "+ cboDonvi +" 3: "+ cboNam+" 4: "+cboDot +" 5: "+ cboTonghop+" 6: "+ strNguyennhan);
                    if (cboTonghop.equals("S") && (CapBC.equals("3") || CapBC.equals("2")) && (lstData != null && !lstData.isEmpty())) //quyennv - tong hop gui nhan
                    {
//                        if (!cboDot.equals("1")) {
//                            chkSuccess = "resultSend";
//                            pageResult = new StringBufferInputStream("00");
//                        } else {
//                            if (CapBC.equals("3")) {
//                                chkSuccess = "resultSend";
//                                pageResult = new StringBufferInputStream("00");
//                            } else {
//                                chkSuccess = "resultSend_2024";
//                                pageResult = new StringBufferInputStream("00");
//                            }
//                        }
                        if (cboDot.equals("1")) {
                            chkSuccess = "resultSend_2024";
                        } else {
                            chkSuccess = "resultSend";
                        }
                        pageResult = new StringBufferInputStream("00");
                    } else if (lstData != null && !lstData.isEmpty()) {
                        if (!cboDot.equals("1")) {
                            chkSuccess = "SuccessLoad";
                        } else {
                            chkSuccess = "SuccessLoad2024";
                        }
                        pageResult = new StringBufferInputStream("00");
                    } else {
                        chkSuccess = "FaildMessage";
                        pageResult = new StringBufferInputStream("01");
                    }
                    break;
                case "1":
                    posMainModel = listKTNBDA.get_pos_main_pos(TenDN, CapBC);
                    main_pos_username = posMainModel.getMainPosCd();
                    String dateStr = new Utilities().fnc_getDateBC(cboNam, cboDot);
                    final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(dateStr));
                    ArrayList<LockSendModel> lstData_tmp = _service_listts.getDataLockManual("KHNV_03_PGD", main_pos_username, "M", _reportDate);
                    try {
                        setChotsl(lstData_tmp.get(0).getStatus());
                    } catch (Exception e) {
                        setChotsl("0");
                    }
                    if (cboDot.equals("1")) {
                        if (!chotsl.equals("2")) {
                            sendTw(CapBC, TenDN, cboDonvi, cboNam, cboDot, cboTonghop, strNguyennhan);
                            chkSuccess = "SuccessMessage";
                            pageResult = new StringBufferInputStream("10");
                        } else {
                            chkSuccess = "ShowMessage_TW";
                        }
                    } else {
                        sendTwKhnv();
                        chkSuccess = "SuccessMessage";
                        pageResult = new StringBufferInputStream("10");
                    }
                    break;
                case "2":
                    dataReult = new AuthorModel().rollBackData(CapBC, TenDN, cboDonvi, cboNam, cboDot, cboTonghop, strNguyennhan);
                    if (dataReult.equals("20")) {
                        chkSuccess = "SuccessRoll";
                        pageResult = new StringBufferInputStream("20");
                    } else {
                        chkSuccess = "FaildMessage";
                        pageResult = new StringBufferInputStream("21");
                    }
                    pageResult = new StringBufferInputStream(dataReult);
                    break;
                case "3":
                    dataReult = new AuthorModel().SaveDataProvince(CapBC, TenDN, cboDonvi, cboNam, cboDot, cboTonghop, strNguyennhan, lstData);
                    if (dataReult.equals("30")) {
                        chkSuccess = "SuccessRoll";
                        pageResult = new StringBufferInputStream("30");
                    } else {
                        chkSuccess = "FaildMessage";
                        pageResult = new StringBufferInputStream("31");
                    }
                    pageResult = new StringBufferInputStream(dataReult);
                    break;
                case "4":
                    dataReult = new AuthorModel().getMenuIdBc();
                    chkSuccess = "SuccessRoll";
                    pageResult = new StringBufferInputStream(dataReult);
                    break;
            }
//            System.out.println(cboDot + " " + chkSuccess);
        }
        return chkSuccess;
    }

    public String ShowDetaiCn() throws Exception {
        //Lấy danh sách đơn vị theo cấp báo cáo
        session = ActionContext.getContext().getSession();
        CapBC = (String) session.get("reportGrade");
        TenDN = (String) session.get("username");
        lstData = new AuthorModel().getData(CapBC, TenDN, macn_detail, cboNam, cboDot, "N", "");
        return SUCCESS;
    }
//<editor-fold defaultstate="collapsed" desc="sendtw 2021 xml">

    public void sendTwKhnv() {
        String chk = "";
        System.err.println("Vao ham sendKTGS");
        try {
            session = ActionContext.getContext().getSession();
            CapBC = (String) session.get("reportGrade");
            TenDN = (String) session.get("username");

            List<String> lstPos = new ArrayList<>();

            Map<String, Integer> mapStatusSend = new HashMap();

            lstPos = new DaoMau01A().getAllPosUser(TenDN);

            for (String mapgd : lstPos) {

                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/")
                        ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : context.getRealPath("/") + Define.M_REPORT_XML;
                strPathSave += "KHNV_03_PGD_" + mapgd
                        + "_" + TenDN + "_"
                        + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";

                List<String> lstData = new ArrayList<>();
                boolean bStatus_file = false;
                String ngayBc = new DaoMau01A().getNgaybc(cboNam, cboDot);
                lstData = new DaoMau01A().getDataSendKhnv("NT", "KHNV_03_PGD", mapgd, ngayBc);
                if (lstData == null || lstData.size() == 0) {
                    mapStatusSend.put(mapgd, 6);
                    continue;
                }
                bStatus_file = new XmlKhnv2021Sync().createXmlFileKhnv2021(Define.PARA_SYN_REPORT_KHNV2021, "NT",
                        "KHNV_03_PGD", ngayBc, TenDN, CapBC,
                        mapgd, lstData, Define.WEB_SERVICES_STATUS_SEND, strPathSave);

                if (!bStatus_file) {
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: Khong tao duoc file " + strPathSave);

                    mapStatusSend.put(mapgd, 1); //1 la tao file xml bi loi
                }
                //Tao file xml theo cau truc
//
                File checkfile = new File(strPathSave);
                if (!checkfile.exists()) {
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: Khong tao duoc file " + strPathSave);
                    mapStatusSend.put(mapgd, 2); //2 la khong tim thay file xml
                }
                ProcessReportSyn clientWritexml = new ProcessReportSyn();
                String sStatus = clientWritexml.SendFileXmlToWebServices(strPathSave);
//
                if (sStatus.equals(Define.WEB_SERVICES_STATUS_FAIL)) {
                    System.err.println("Ban chua dong bo du lieu duoc ve TW");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: Khong dong bo duoc file " + strPathSave);
                    mapStatusSend.put(mapgd, 3); //3 la gui file du lieu bi loi
                } else if (sStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    mapStatusSend.put(mapgd, 4);  //gui du lieu thanh cong
                } else {
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    mapStatusSend.put(mapgd, 5);  //pgd bi khoa khong gui duoc du lieu
                }
            }
            setLstViewSend(getViewStatusSend(lstPos, mapStatusSend));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendKTGS: " + e.getMessage());
            addActionError("Bạn chưa gửi được dữ liệu xin liên hệ với quản trị để được khắc phục");
        }
    }
//</editor-fold>

    public void sendTw(String CapBC, String TenDN, String cboDonvi, String cboNam, String cboDot, String cboTonghop, String strNguyennhan) {
        try {
            session = ActionContext.getContext().getSession();
            AuthorModel daoMain = new AuthorModel();
            XDKHDao2021 daoXdkh = new XDKHDao2021();
            int nambc = Integer.parseInt(cboNam) - 1;

            final String _reportDate = String.valueOf(nambc) + "0630";
            final String _reportDate1 = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00.000").format(new SimpleDateFormat("yyyyMMdd").parse(_reportDate));

            Map<String, Integer> mapStatusSend = new HashMap<>();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            String dateFormat = formatter.format(new Date());

            List<String> lstPos = new ArrayList<>();
            lstPos.add(main_pos_username);

            ArrayList<LockSendModel> lstData_tmp = _serverAPI.getDataLockManual("KHNV_03_PGD", main_pos_username, "M", _reportDate);

            String chotsl;
            try {
                chotsl = lstData_tmp.get(0).getStatus();
            } catch (Exception e) {
                chotsl = "0";
            }
            if ("1".equals(chotsl)) {
                mapStatusSend.put(main_pos_username, 5);
                setLstViewSend(getViewStatus(lstPos, mapStatusSend));
                return;
            }
            lstData = daoMain.getData("2", TenDN, main_pos_username, cboNam, cboDot, "Y", strNguyennhan);
            if (lstData == null || lstData.isEmpty()) {
                mapStatusSend.put(main_pos_username, 6);
                setLstViewSend(getViewStatus(lstPos, mapStatusSend));
                return;
            }
            ArrayList<DuLieuNTRowX> lstUpdateDate = new ArrayList<>();

            for (DULIEU_NT data : lstData) {
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

            _serverAPI = new DuLieuNTService();
            int status = _serverAPI.getGQVL2023("KHNV_03_PGD", main_pos_username, "M", _reportDate, "", TenDN, lstUpdateDate);
            if (status == 200) {
                int skhoa = _serverAPI.updateChotSL("KHNV_03_PGD", main_pos_username, "M", _reportDate, "1", TenDN, null);
                if (skhoa == 200) {
                    String message = daoXdkh.getCheck_2026("CHECK_SEND_TW", cboNam, cboDot, main_pos_username, "2", TenDN, "", "CHECK_SEND_TW");
                    if (message.endsWith("AAA")) {
                        mapStatusSend.put(main_pos_username, 4);
                    } else {
                        mapStatusSend.put(main_pos_username, 2);
                    }
                } else {
                    mapStatusSend.put(main_pos_username, 3);
                }
            } else {
                mapStatusSend.put(main_pos_username, 1);
            }
            setLstViewSend(getViewStatus(lstPos, mapStatusSend));

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> KHNV", e);
            throw new RuntimeException(e);
        }
    }
    //<editor-fold defaultstate="collapsed" desc="sendtw 2024 xml"> 

    public void sendTwKhnv_2024(String CapBC, String TenDN, String cboDonvi, String cboNam, String cboDot, String cboTonghop, String strNguyennhan) {
        try {
            System.err.println("qưe");
            session = ActionContext.getContext().getSession();
            CapBC = (String) session.get("reportGrade");
            TenDN = (String) session.get("username");

            List<String> lstPos = new ArrayList<>();

            Map<String, Integer> mapStatusSend = new HashMap();

            lstPos = new DaoMau01A().getAllPosUser(TenDN);

            for (String mapgd : lstPos) {

                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/")
                        ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : context.getRealPath("/") + Define.M_REPORT_XML;
                strPathSave += "KHNV_03_PGD" + mapgd
                        + "_" + TenDN + "_"
                        + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";

                List<String> lstData = new ArrayList<>();
                boolean bStatus_file = false;
                String ngayBc = new DaoMau01A().getNgaybc(cboNam, cboDot);
                lstData = new DaoMau01A().getDataSendKhnv("NT", "KHNV_03_PGD", mapgd, ngayBc);
                if (lstData == null || lstData.size() == 0) {
                    mapStatusSend.put(mapgd, 6);
                    continue;
                }
                bStatus_file = new XmlKhnv2021Sync().createXmlFileKhnv2021(Define.PARA_SYN_REPORT_KHNV2021, "NT",
                        "KHNV_03_PGD", ngayBc, TenDN, CapBC,
                        mapgd, lstData, Define.WEB_SERVICES_STATUS_SEND, strPathSave);

                if (!bStatus_file) {
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: Khong tao duoc file " + strPathSave);

                    mapStatusSend.put(mapgd, 1); //1 la tao file xml bi loi
                }
                //Tao file xml theo cau truc
//
                File checkfile = new File(strPathSave);
                if (!checkfile.exists()) {
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: Khong tao duoc file " + strPathSave);
                    mapStatusSend.put(mapgd, 2); //2 la khong tim thay file xml
                }
                ProcessReportSyn clientWritexml = new ProcessReportSyn();
                String sStatus = clientWritexml.SendFileXmlToWebServices(strPathSave);
//
                if (sStatus.equals(Define.WEB_SERVICES_STATUS_FAIL)) {
                    System.err.println("Ban chua dong bo du lieu duoc ve TW");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: Khong dong bo duoc file " + strPathSave);
                    mapStatusSend.put(mapgd, 3); //3 la gui file du lieu bi loi
                } else if (sStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    mapStatusSend.put(mapgd, 4);  //gui du lieu thanh cong
                } else {
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    mapStatusSend.put(mapgd, 5);  //pgd bi khoa khong gui duoc du lieu
                }
            }
            setLstViewSend(getViewStatusSend(lstPos, mapStatusSend));

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendKTGS: " + e.getMessage());
            addActionError("Bạn chưa gửi được dữ liệu xin liên hệ với quản trị để được khắc phục");
        }
    }

    private List<ModelViewSend> getViewStatusSend(List<String> lstPos, Map<String, Integer> mapStatus) {
        addActionMessage("Danh sách các PGD gửi dữ liệu và tình trạng dữ liệu");
        List<ModelViewSend> lstStatus = new ArrayList();
        try {
            Map<String, String> mapPosByName = DaoKtgsMain.newInstance().getPosByName(lstPos);

            for (String key : mapStatus.keySet()) {

                if (mapPosByName.get(key) == null) {
                    continue;
                };
                Integer value = mapStatus.get(key);
                ModelViewSend modelview = ModelViewSend.newInstance();
                modelview.setMapgd(key);
                modelview.setKey(value);
                modelview.setTenpgd(mapPosByName.get(key));

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
                        break;
                }
                lstStatus.add(modelview);
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> getViewStatusSend: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> getViewStatusSend: " + e.getMessage());
        }
        return lstStatus;
    }
//</editor-fold>

    private List<ModelViewSend> getViewStatus(List<String> lstPos, Map<String, Integer> mapStatus) {
        addActionMessage("Danh sách các PGD gửi dữ liệu và tình trạng dữ liệu");
        List<ModelViewSend> lstStatus = new ArrayList();
        try {
            Map<String, String> mapPosByName = DaoKtgsMain.newInstance().getPosByName(lstPos);

            for (String key : mapStatus.keySet()) {

                if (mapPosByName.get(key) == null) {
                    continue;
                };
                Integer value = mapStatus.get(key);
                ModelViewSend modelview = ModelViewSend.newInstance();
                modelview.setMapgd(key);
                modelview.setKey(value);
                modelview.setTenpgd(mapPosByName.get(key));

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
                        modelview.setMota_loi("Phòng giao dịch này bị khóa");
                        break;
                    case 6:
                        modelview.setMota_loi("CN chưa lưu dữ liệu tổng hợp");
                        break;
                    default:
                        modelview.setMota_loi("Lỗi không xác định");
                        break;
                }
                lstStatus.add(modelview);
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> getViewStatusSend: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> getViewStatusSend: " + e.getMessage());
        }
        return lstStatus;
    }

    public String lock() {
        try {
            session = ActionContext.getContext().getSession();
            String D1 = (String) session.get("username");
            String D2 = ServletActionContext.getRequest().getParameter("cboNam");
            System.out.println(D1 + " " + D2);
//            String ngayBc = new DaoMau01A().getNgaybc(D2, D1);
            DaoMau01A daoMain = new DaoMau01A();
            GenericResult<String> _result = daoMain.lock_all_pos("KHNV_03_PGD", "000100", D1, D2);

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

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public String getMacn_detail() {
        return macn_detail;
    }

    public void setMacn_detail(String macn_detail) {
        this.macn_detail = macn_detail;
    }

    public List<ModelViewSend> getLstViewSend() {
        return lstViewSend;
    }

    public void setLstViewSend(List<ModelViewSend> lstViewSend) {
        this.lstViewSend = lstViewSend;
    }

    public String getCapBC() {
        return CapBC;
    }

    public void setCapBC(String CapBC) {
        this.CapBC = CapBC;
    }

    public String getTenDN() {
        return TenDN;
    }

    public void setTenDN(String TenDN) {
        this.TenDN = TenDN;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCboTonghop() {
        return cboTonghop;
    }

    public void setCboTonghop(String cboTonghop) {
        this.cboTonghop = cboTonghop;
    }

    public String getStrNguyennhan() {
        return strNguyennhan;
    }

    public void setStrNguyennhan(String strNguyennhan) {
        this.strNguyennhan = strNguyennhan;
    }

    public String getChkSuccess() {
        return chkSuccess;
    }

    public void setChkSuccess(String chkSuccess) {
        this.chkSuccess = chkSuccess;
    }

    public String getDataReult() {
        return dataReult;
    }

    public void setDataReult(String dataReult) {
        this.dataReult = dataReult;
    }

    public String getCboDonvi() {
        return cboDonvi;
    }

    public void setCboDonvi(String cboDonvi) {
        this.cboDonvi = cboDonvi;
    }

    public String getCboNam() {
        return cboNam;
    }

    public void setCboNam(String cboNam) {
        this.cboNam = cboNam;
    }

    public String getCboDot() {
        return cboDot;
    }

    public void setCboDot(String cboDot) {
        this.cboDot = cboDot;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public List<PosClass> getLstPos() {
        return lstPos;
    }

    public void setLstPos(List<PosClass> lstPos) {
        this.lstPos = lstPos;
    }

    public List<DULIEU_NT> getLstData() {
        return lstData;
    }

    public void setLstData(List<DULIEU_NT> lstData) {
        this.lstData = lstData;
    }

    public InputStream getPageResult() {
        return pageResult;
    }

    public void setPageResult(InputStream pageResult) {
        this.pageResult = pageResult;
    }

    public String getBtnSend() {
        return btnSend;
    }

    public void setBtnSend(String btnSend) {
        this.btnSend = btnSend;
    }

    public String getShowMessage() {
        return ShowMessage;
    }

    public void setShowMessage(String ShowMessage) {
        this.ShowMessage = ShowMessage;
    }
    //</editor-fold> 
}
