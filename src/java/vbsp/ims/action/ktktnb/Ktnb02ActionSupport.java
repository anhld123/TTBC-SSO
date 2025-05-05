package vbsp.ims.action.ktktnb;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.BuildPosTreeDao;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.ktnb.DaoKtnb01;
import vbsp.ims.dao.ktnb.DaoKtnb02;
import vbsp.ims.dao.ktnb.ListKTNBDA;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.Ktnb01Model;
import vbsp.ims.model.ktnb.Ktnb02Model;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.syn.ProcessReportSyn;

/**
 *
 * @author CuongBM0211
 */
public class Ktnb02ActionSupport extends ActionSupport implements ServletRequestAware {

    private DaoKtnb02 daoKtnb02 = new DaoKtnb02();
    private DaoKtnb01 daoKtnb01 = new DaoKtnb01();
    private ListKTNBDA listKTNBDA = new ListKTNBDA();
    private List<Ktnb02Model> ktnb02ModelList;  //Lay du lieu load len table

    //Cac truong dung cho luu du lieu
    private List<String> KT_STT_HT;
    private List<String> KT_DTVV;
    private List<String> KT_HS_THS_SHS;
    private List<String> KT_HS_THS_ST;
    private List<String> KT_HS_KT_TS_SHS;
    private List<String> KT_HS_KT_TS_ST;
    private List<String> KT_HS_KT_TL_SHS;
    private List<String> KT_HS_KT_TL_ST;
    private List<String> KT_HS_HSS_SHS;
    private List<String> KT_HS_HSS_ST;
    private List<String> KT_HS_TLS_SHS;
    private List<String> KT_HS_TLS_ST;
    private List<String> KT_NQH_CC_SHS;
    private List<String> KT_NQH_CC_ST;
    private List<String> KT_NQH_CD;
    private List<String> KT_DN;
    private List<String> KT_CO_DINH;
    private List<String> KT_THEM;
    private List<String> KT_XOA;
    private List<String> KT_FONTWEIGHT;
    private List<String> KT_CAPHT;
    private List<String> KT_STT;
    private List<String> NG_CAPNHAT;
    private List<String> KT_KHOA;

    public List<String> getKT_KHOA() {
        return KT_KHOA;
    }

    public void setKT_KHOA(List<String> KT_KHOA) {
        this.KT_KHOA = KT_KHOA;
    }

    //Cac truong chua thong tin bo xung luu du lieu
    private String userId;
    private String posCD;
    private String quyBc;
    private String namBc;
    private String maCn;

    private TreeNode nodes;
    private int reportGrade;

    public TreeNode getNodes() {
        return nodes;
    }

    public void setNodes(TreeNode nodes) {
        this.nodes = nodes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(String selectedPos) {
        this.selectedPos = selectedPos;
    }

    public TreeNode getSearchNodes() {
        return searchNodes;
    }

    public void setSearchNodes(TreeNode searchNodes) {
        this.searchNodes = searchNodes;
    }
    private String userName;
    private String selectedPos;
    private TreeNode searchNodes;

    private HttpServletRequest request = null;

    public Ktnb02ActionSupport() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String get_data_ktnb02() {
        getInfo();
        ktnb02ModelList = daoKtnb02.get_ktnb02(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
    }

    public String ResetDataInput2() {
        getInfo();
        ktnb02ModelList = daoKtnb02.get_ktnb02_default(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
    }

    public String get_data_ktnb02_auth() throws SQLException {
        getInfo();
        return "success";
    }

    public String view() throws SQLException {
        System.err.println(" da vao action");
        if (this.searchNodes == null) {
            System.err.print("searchNodes is null");
            try {
                reportGrade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
                userName = request.getSession().getAttribute("username").toString();
                BuildPosTreeDao buildPosTreeDao = new BuildPosTreeDao(reportGrade, userName);
                buildPosTreeDao.build();
                this.searchNodes = buildPosTreeDao.getNodes();
            } catch (SQLException ex) {
            }
        }
        String listOfPos = getListOfPos();
        if (listOfPos == "" || listOfPos.length() > 6) {
            return "error-pos";
        }
        String strPosCD = listOfPos.substring(0, 6);
        System.err.print(strPosCD);
        getInfo();
        ktnb02ModelList = daoKtnb02.get_ktnb02_auth(strPosCD, Integer.parseInt(namBc), Integer.parseInt(quyBc), "N");
        return "success";
    }

    public String save_data_ktnb02() {

        if (daoKtnb02.save_ktnb02(posCD, maCn, quyBc, namBc, userId, KT_KHOA, KT_HS_THS_SHS, KT_HS_THS_ST, KT_HS_KT_TS_SHS, KT_HS_KT_TS_ST, KT_HS_KT_TL_SHS,
                KT_HS_KT_TL_ST, KT_HS_HSS_SHS, KT_HS_HSS_ST, KT_HS_TLS_SHS, KT_HS_TLS_ST, "")) {
            return "success";
        } else {
            return "error";
        }
    }

    public String Auth() throws SQLException {
        System.err.println(" da vao action");
        if (this.searchNodes == null) {
            System.err.print("searchNodes is null");
            try {
                reportGrade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
                userName = request.getSession().getAttribute("username").toString();
                BuildPosTreeDao buildPosTreeDao = new BuildPosTreeDao(reportGrade, userName);
                buildPosTreeDao.build();
                this.searchNodes = buildPosTreeDao.getNodes();
            } catch (SQLException ex) {
            }
        }
        String reportGrade1 = Integer.toString(reportGrade);
        Connection conn = new DaoConnect().getConnect();
        List<QT_DULIEU_NT> lstDulieuNt = daoKtnb01.getData_lock_ktnb(conn, "KTNB02", userName, maCn, quyBc, namBc, reportGrade1, "");
//        System.out.println("para = " + userName + maCn + quyBc + namBc);
        String Check_lock;
        try {
            Check_lock = lstDulieuNt.get(0).getD2();
        } catch (Exception e) {
            Check_lock = "0";
        }
//        System.out.println("Check_lock= " + Check_lock);
        if (!Check_lock.equals("0")) {
            return "error-lock";
        }
        String listOfPos = getListOfPos();
        String lsPos = listKTNBDA.getListPos(maCn);
        if (lsPos.length() != listOfPos.length()) {
            return "error-pos";
        } else {
            String[] var3 = listOfPos.split(",");
            int var4 = var3.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                String pos_auth = var3[var5];
                if (!daoKtnb02.save_ktnb02_auth(pos_auth, quyBc, namBc)) {
                    return "error";
                }
                boolean status = this.syn_data_HO(pos_auth);
                if (var5 == 0 && status != true && !this.maCn.equals("000101")) {
                    return "error-send";
                }
            }
        }
        return "success";
    }

    public boolean syn_data_HO(String sPOS) {
        //DONG BO DU LIEU LEN TW
        try {
            HttpSession session = request.getSession();
            String reporGrade = session.getAttribute("reportGrade").toString();
            if (!reporGrade.equals("3")) {
                //Lay duong dan va ten xml se ghi ra
                String pathSave = !request.getRealPath("/").endsWith("/")
                        ? request.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : request.getRealPath("/") + Define.M_REPORT_XML;
                pathSave += sPOS + "_BCNT_KTNB_" + "02" + ".xml";    //Thay ma bao cao
                String reportDate = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());

                HashMap<String, String> hmData = new HashMap<String, String>();

                List<Ktnb02Model> ktnb02ModelListSend = null;

                List<String> strKT_STT_HT1 = new ArrayList<String>();
                List<String> strKT_DTVV2 = new ArrayList<String>();
                List<String> strKT_HS_THS_SHS3 = new ArrayList<String>();
                List<String> strKT_HS_THS_ST4 = new ArrayList<String>();
                List<String> strKT_HS_KT_TS_SHS5 = new ArrayList<String>();
                List<String> strKT_HS_KT_TS_ST6 = new ArrayList<String>();
                List<String> strKT_HS_KT_TL_SHS7 = new ArrayList<String>();
                List<String> strKT_HS_KT_TL_ST8 = new ArrayList<String>();
                List<String> strKT_HS_HSS_SHS9 = new ArrayList<String>();
                List<String> strKT_HS_HSS_ST10 = new ArrayList<String>();
                List<String> strKT_HS_TLS_SHS11 = new ArrayList<String>();
                List<String> strKT_HS_TLS_ST12 = new ArrayList<String>();
                List<String> strKT_NQH_CC_SHS13 = new ArrayList<String>();
                List<String> strKT_NQH_CC_ST14 = new ArrayList<String>();
                List<String> strKT_NQH_CD15 = new ArrayList<String>();
                List<String> strKT_DN16 = new ArrayList<String>();
                List<String> strKT_CO_DINH17 = new ArrayList<String>();
                List<String> strKT_THEM18 = new ArrayList<String>();
                List<String> strKT_XOA19 = new ArrayList<String>();
                List<String> strKT_FONTWEIGHT20 = new ArrayList<String>();
                List<String> strKT_CAPHT21 = new ArrayList<String>();
                List<String> strKT_STT22 = new ArrayList<String>();
                List<String> strNG_CAPNHAT23 = new ArrayList<String>();
                List<String> strKT_KHOA24 = new ArrayList<String>();

                ktnb02ModelListSend = daoKtnb02.get_ktnb02_auth(sPOS, Integer.parseInt(namBc), Integer.parseInt(quyBc), "Y");
                if (ktnb02ModelListSend.size() <= 0) {
                    return false;
                }
                for (int i = 0; i < ktnb02ModelListSend.size(); i++) {

                    strKT_STT_HT1.add(ktnb02ModelListSend.get(i).getKT_STT_HT());
                    strKT_DTVV2.add(ktnb02ModelListSend.get(i).getKT_DTVV());
                    strKT_HS_THS_SHS3.add(ktnb02ModelListSend.get(i).getKT_HS_THS_SHS());
                    strKT_HS_THS_ST4.add(ktnb02ModelListSend.get(i).getKT_HS_THS_ST());
                    strKT_HS_KT_TS_SHS5.add(Double.toString(ktnb02ModelListSend.get(i).getKT_HS_KT_TS_SHS()));
                    strKT_HS_KT_TS_ST6.add(Double.toString(ktnb02ModelListSend.get(i).getKT_HS_KT_TS_ST()));
                    strKT_HS_KT_TL_SHS7.add(Double.toString(ktnb02ModelListSend.get(i).getKT_HS_KT_TL_SHS()));
                    strKT_HS_KT_TL_ST8.add(Double.toString(ktnb02ModelListSend.get(i).getKT_HS_KT_TL_ST()));
                    strKT_HS_HSS_SHS9.add(Double.toString(ktnb02ModelListSend.get(i).getKT_HS_HSS_SHS()));
                    strKT_HS_HSS_ST10.add(Double.toString(ktnb02ModelListSend.get(i).getKT_HS_HSS_ST()));
                    strKT_HS_TLS_SHS11.add(Double.toString(ktnb02ModelListSend.get(i).getKT_HS_TLS_SHS()));
                    strKT_HS_TLS_ST12.add(Double.toString(ktnb02ModelListSend.get(i).getKT_HS_TLS_ST()));
                    strKT_NQH_CC_SHS13.add(Double.toString(ktnb02ModelListSend.get(i).getKT_NQH_CC_SHS()));
                    strKT_NQH_CC_ST14.add(Double.toString(ktnb02ModelListSend.get(i).getKT_NQH_CC_ST()));
                    strKT_NQH_CD15.add(Double.toString(ktnb02ModelListSend.get(i).getKT_NQH_CD()));
                    strKT_DN16.add(ktnb02ModelListSend.get(i).getKT_DN());
                    strKT_CO_DINH17.add(ktnb02ModelListSend.get(i).getKT_CO_DINH());
                    strKT_THEM18.add(ktnb02ModelListSend.get(i).getKT_THEM());
                    strKT_XOA19.add(ktnb02ModelListSend.get(i).getKT_XOA());
                    strKT_FONTWEIGHT20.add(ktnb02ModelListSend.get(i).getKT_FONTWEIGHT());
                    strKT_CAPHT21.add(Double.toString(ktnb02ModelListSend.get(i).getKT_CAPHT()));
                    strKT_STT22.add(Double.toString(ktnb02ModelListSend.get(i).getKT_STT()));
                    strNG_CAPNHAT23.add(ktnb02ModelListSend.get(i).getNG_CAPNHAT());
                    strKT_KHOA24.add(ktnb02ModelListSend.get(i).getKT_KHOA());
                }

                hmData.put("COT_1", DefineFun.converArrayList2String(strKT_STT_HT1));
                hmData.put("COT_2", DefineFun.converArrayList2String(strKT_DTVV2));
                hmData.put("COT_3", DefineFun.converArrayList2String(strKT_HS_THS_SHS3));
                hmData.put("COT_4", DefineFun.converArrayList2String(strKT_HS_THS_ST4));
                hmData.put("COT_5", DefineFun.converArrayList2String(strKT_HS_KT_TS_SHS5));
                hmData.put("COT_6", DefineFun.converArrayList2String(strKT_HS_KT_TS_ST6));
                hmData.put("COT_7", DefineFun.converArrayList2String(strKT_HS_KT_TL_SHS7));
                hmData.put("COT_8", DefineFun.converArrayList2String(strKT_HS_KT_TL_ST8));
                hmData.put("COT_9", DefineFun.converArrayList2String(strKT_HS_HSS_SHS9));
                hmData.put("COT_10", DefineFun.converArrayList2String(strKT_HS_HSS_ST10));
                hmData.put("COT_11", DefineFun.converArrayList2String(strKT_HS_TLS_SHS11));
                hmData.put("COT_12", DefineFun.converArrayList2String(strKT_HS_TLS_ST12));
                hmData.put("COT_13", DefineFun.converArrayList2String(strKT_NQH_CC_SHS13));
                hmData.put("COT_14", DefineFun.converArrayList2String(strKT_NQH_CC_ST14));
                hmData.put("COT_15", DefineFun.converArrayList2String(strKT_NQH_CD15));
                hmData.put("COT_16", DefineFun.converArrayList2String(strKT_DN16));
                hmData.put("COT_17", DefineFun.converArrayList2String(strKT_CO_DINH17));
                hmData.put("COT_18", DefineFun.converArrayList2String(strKT_THEM18));
                hmData.put("COT_19", DefineFun.converArrayList2String(strKT_XOA19));
                hmData.put("COT_20", DefineFun.converArrayList2String(strKT_FONTWEIGHT20));
                hmData.put("COT_21", DefineFun.converArrayList2String(strKT_CAPHT21));
                hmData.put("COT_22", DefineFun.converArrayList2String(strKT_STT22));
                hmData.put("COT_23", DefineFun.converArrayList2String(strNG_CAPNHAT23));
                hmData.put("COT_24", DefineFun.converArrayList2String(strKT_KHOA24));

                //Tao file xml theo cau truc
                ProcessReportSyn clientWritexml = new ProcessReportSyn();
                boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKTNB(Define.PARA_SYN_REPORT_KTNB,
                        Define.SYN_REPORT_KTNB02, reportDate, userId, sPOS, maCn, reporGrade, "BCNT", hmData, quyBc, namBc, pathSave);
                if (!bSuccess) {
                    System.err.println("Ban chua dong bo du lieu duoc ve TW");
                } else {
                    daoKtnb01.lock_ktnb(Define.SYN_REPORT_KTNB02, userName, sPOS, quyBc, namBc, reporGrade, reportDate);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Loi tao file vao gui TW " + e.getMessage());
            return false;
        }

        //========== END DONG BO DU LIEU =======================================
        return true;
    }

    private String getListOfPos() {
        String posString = "";
        userName = request.getSession().getAttribute("username").toString();
        reportGrade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
        String pos_cd;
        ArrayList<String> pos_stack = new ArrayList<>();
        ArrayList<String> listOfId
                = (ArrayList<String>) DefineFun.string2Array(selectedPos, ",", 1);
        boolean isAdded;
        if (listOfId.size() == 0) {
            return "";
        }
        if (listOfId.size() > 0) {
            for (String id : listOfId) {
                isAdded = false;
                pos_cd = DefineFun.searchInTreeView(id, this.searchNodes);
                for (String added_pos : pos_stack) {
                    if (added_pos.equals(pos_cd)) {
                        isAdded = true;
                        break;
                    }
                }
                if (!isAdded && pos_cd != null) {
                    posString += pos_cd + ",";
                    pos_stack.add(pos_cd);
                }
            }
        }
        posString = posString.substring(0, posString.length() - 1);

        System.err.println(posString);
        return posString;
    }

    public void getInfo() {
        //Lay username
        HttpSession session = request.getSession();
        userId = session.getAttribute("username").toString();

        //Lay thong tin ma phogn giao dich, ma chi nhanh
        PosMainModel posMainModel;
        posMainModel = listKTNBDA.get_pos_main_pos(userId);
        posCD = posMainModel.getPosCd();
        maCn = posMainModel.getMainPosCd();
    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public List<Ktnb02Model> getKtnb02ModelList() {
        return ktnb02ModelList;
    }

    public void setKtnb02ModelList(List<Ktnb02Model> ktnb02ModelList) {
        this.ktnb02ModelList = ktnb02ModelList;
    }

    public List<String> getKT_STT_HT() {
        return KT_STT_HT;
    }

    public void setKT_STT_HT(List<String> KT_STT_HT) {
        this.KT_STT_HT = KT_STT_HT;
    }

    public List<String> getKT_DTVV() {
        return KT_DTVV;
    }

    public void setKT_DTVV(List<String> KT_DTVV) {
        this.KT_DTVV = KT_DTVV;
    }

    public List<String> getKT_HS_THS_SHS() {
        return KT_HS_THS_SHS;
    }

    public void setKT_HS_THS_SHS(List<String> KT_HS_THS_SHS) {
        this.KT_HS_THS_SHS = KT_HS_THS_SHS;
    }

    public List<String> getKT_HS_THS_ST() {
        return KT_HS_THS_ST;
    }

    public void setKT_HS_THS_ST(List<String> KT_HS_THS_ST) {
        this.KT_HS_THS_ST = KT_HS_THS_ST;
    }

    public List<String> getKT_HS_KT_TS_SHS() {
        return KT_HS_KT_TS_SHS;
    }

    public void setKT_HS_KT_TS_SHS(List<String> KT_HS_KT_TS_SHS) {
        this.KT_HS_KT_TS_SHS = KT_HS_KT_TS_SHS;
    }

    public List<String> getKT_HS_KT_TS_ST() {
        return KT_HS_KT_TS_ST;
    }

    public void setKT_HS_KT_TS_ST(List<String> KT_HS_KT_TS_ST) {
        this.KT_HS_KT_TS_ST = KT_HS_KT_TS_ST;
    }

    public List<String> getKT_HS_KT_TL_SHS() {
        return KT_HS_KT_TL_SHS;
    }

    public void setKT_HS_KT_TL_SHS(List<String> KT_HS_KT_TL_SHS) {
        this.KT_HS_KT_TL_SHS = KT_HS_KT_TL_SHS;
    }

    public List<String> getKT_HS_KT_TL_ST() {
        return KT_HS_KT_TL_ST;
    }

    public void setKT_HS_KT_TL_ST(List<String> KT_HS_KT_TL_ST) {
        this.KT_HS_KT_TL_ST = KT_HS_KT_TL_ST;
    }

    public List<String> getKT_HS_HSS_SHS() {
        return KT_HS_HSS_SHS;
    }

    public void setKT_HS_HSS_SHS(List<String> KT_HS_HSS_SHS) {
        this.KT_HS_HSS_SHS = KT_HS_HSS_SHS;
    }

    public List<String> getKT_HS_HSS_ST() {
        return KT_HS_HSS_ST;
    }

    public void setKT_HS_HSS_ST(List<String> KT_HS_HSS_ST) {
        this.KT_HS_HSS_ST = KT_HS_HSS_ST;
    }

    public List<String> getKT_HS_TLS_SHS() {
        return KT_HS_TLS_SHS;
    }

    public void setKT_HS_TLS_SHS(List<String> KT_HS_TLS_SHS) {
        this.KT_HS_TLS_SHS = KT_HS_TLS_SHS;
    }

    public List<String> getKT_HS_TLS_ST() {
        return KT_HS_TLS_ST;
    }

    public void setKT_HS_TLS_ST(List<String> KT_HS_TLS_ST) {
        this.KT_HS_TLS_ST = KT_HS_TLS_ST;
    }

    public List<String> getKT_NQH_CC_SHS() {
        return KT_NQH_CC_SHS;
    }

    public void setKT_NQH_CC_SHS(List<String> KT_NQH_CC_SHS) {
        this.KT_NQH_CC_SHS = KT_NQH_CC_SHS;
    }

    public List<String> getKT_NQH_CC_ST() {
        return KT_NQH_CC_ST;
    }

    public void setKT_NQH_CC_ST(List<String> KT_NQH_CC_ST) {
        this.KT_NQH_CC_ST = KT_NQH_CC_ST;
    }

    public List<String> getKT_NQH_CD() {
        return KT_NQH_CD;
    }

    public void setKT_NQH_CD(List<String> KT_NQH_CD) {
        this.KT_NQH_CD = KT_NQH_CD;
    }

    public List<String> getKT_DN() {
        return KT_DN;
    }

    public void setKT_DN(List<String> KT_DN) {
        this.KT_DN = KT_DN;
    }

    public List<String> getKT_CO_DINH() {
        return KT_CO_DINH;
    }

    public void setKT_CO_DINH(List<String> KT_CO_DINH) {
        this.KT_CO_DINH = KT_CO_DINH;
    }

    public List<String> getKT_THEM() {
        return KT_THEM;
    }

    public void setKT_THEM(List<String> KT_THEM) {
        this.KT_THEM = KT_THEM;
    }

    public List<String> getKT_XOA() {
        return KT_XOA;
    }

    public void setKT_XOA(List<String> KT_XOA) {
        this.KT_XOA = KT_XOA;
    }

    public List<String> getKT_FONTWEIGHT() {
        return KT_FONTWEIGHT;
    }

    public void setKT_FONTWEIGHT(List<String> KT_FONTWEIGHT) {
        this.KT_FONTWEIGHT = KT_FONTWEIGHT;
    }

    public List<String> getKT_CAPHT() {
        return KT_CAPHT;
    }

    public void setKT_CAPHT(List<String> KT_CAPHT) {
        this.KT_CAPHT = KT_CAPHT;
    }

    public List<String> getKT_STT() {
        return KT_STT;
    }

    public void setKT_STT(List<String> KT_STT) {
        this.KT_STT = KT_STT;
    }

    public List<String> getNG_CAPNHAT() {
        return NG_CAPNHAT;
    }

    public void setNG_CAPNHAT(List<String> NG_CAPNHAT) {
        this.NG_CAPNHAT = NG_CAPNHAT;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPosCD() {
        return posCD;
    }

    public void setPosCD(String posCD) {
        this.posCD = posCD;
    }

    public String getQuyBc() {
        return quyBc;
    }

    public void setQuyBc(String quyBc) {
        this.quyBc = quyBc;
    }

    public String getNamBc() {
        return namBc;
    }

    public void setNamBc(String namBc) {
        this.namBc = namBc;
    }

    public String getMaCn() {
        return maCn;
    }

    public void setMaCn(String maCn) {
        this.maCn = maCn;
    }

//</editor-fold>
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

}
