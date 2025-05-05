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
import vbsp.ims.dao.ktnb.DaoKtnb05;
import vbsp.ims.dao.ktnb.ListKTNBDA;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.Ktnb04Model;
import vbsp.ims.model.ktnb.Ktnb05Model;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.syn.ProcessReportSyn;

/**
 *
 * @author CuongBM0211
 */
public class Ktnb05ActionSupport extends ActionSupport implements ServletRequestAware {

    private DaoKtnb05 daoKtnb05 = new DaoKtnb05();
    private DaoKtnb01 daoKtnb01 = new DaoKtnb01();
    private ListKTNBDA listKTNBDA = new ListKTNBDA();
    private List<Ktnb05Model> ktnb05ModelList;  //Lay du lieu load len table

    //Cac truong dung cho luu du lieu
    private List<String> KT_STT_HT;
    private List<String> KT_TC_UT;
    private List<String> KT_TONG_TO;
    private List<String> KT_TONG_DUNO;
    private List<String> KT_TKVV_ST;
    private List<String> KT_TKVV_DN;
    private List<String> KT_SO_TO_TOT;
    private List<String> KT_DUNO_TOT;
    private List<String> KT_SO_TO_KHA;
    private List<String> KT_DUNO_KHA;
    private List<String> KT_SO_TO_TB;
    private List<String> KT_DUNO_TB;
    private List<String> KT_SO_TO_KEM;
    private List<String> KT_DUNO_KEM;
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

    public int getReportGrade() {
        return reportGrade;
    }

    public void setReportGrade(int reportGrade) {
        this.reportGrade = reportGrade;
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

    public Ktnb05ActionSupport() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String get_data_ktnb05() {
        getInfo();
        ktnb05ModelList = daoKtnb05.get_ktnb05(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
    }

    public String ResetDataInput5() {
        getInfo();
        ktnb05ModelList = daoKtnb05.get_ktnb05_default(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
    }

    public String get_data_ktnb05_auth() throws SQLException {
        getInfo();
//        ktnb01ModelList = daoKtnb01.get_ktnb01(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
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
        ktnb05ModelList = daoKtnb05.get_ktnb05_auth(strPosCD, Integer.parseInt(namBc), Integer.parseInt(quyBc), "N");
        return "success";
    }

    public String save_data_ktnb05() {
        if (daoKtnb05.save_ktnb05(posCD, maCn, quyBc, namBc, userId, KT_KHOA, KT_TONG_TO, KT_TONG_DUNO, KT_TKVV_ST, KT_TKVV_DN, KT_SO_TO_TOT, KT_DUNO_TOT,
                KT_SO_TO_KHA, KT_DUNO_KHA, KT_SO_TO_TB, KT_DUNO_TB, KT_SO_TO_KEM, KT_DUNO_KEM, "")) {
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
        List<QT_DULIEU_NT> lstDulieuNt = daoKtnb01.getData_lock_ktnb(conn, "KTNB05", userName, maCn, quyBc, namBc, reportGrade1, "");
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
        String[] var3 = listOfPos.split(",");
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            String pos_auth = var3[var5];
            if (!daoKtnb05.save_ktnb05_auth(pos_auth, quyBc, namBc)) {
                return "error";
            }
            boolean status = this.syn_data_HO(pos_auth);
            if (var5 == 0 && status != true && !this.maCn.equals("000101")) {
                return "error-send";
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
                pathSave += sPOS + "_BCNT_KTNB_" + "05" + ".xml";    //Thay ma bao cao
                String reportDate = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());

                HashMap<String, String> hmData = new HashMap<String, String>();

                List<Ktnb05Model> ktnb05ModelListSend = null;

                List<String> strKT_STT_HT1 = new ArrayList<String>();
                List<String> strKT_TC_UT2 = new ArrayList<String>();
                List<String> strKT_TONG_TO3 = new ArrayList<String>();
                List<String> strKT_TONG_DUNO4 = new ArrayList<String>();
                List<String> strKT_TKVV_ST5 = new ArrayList<String>();
                List<String> strKT_TKVV_DN6 = new ArrayList<String>();
                List<String> strKT_SO_TO_TOT7 = new ArrayList<String>();
                List<String> strKT_DUNO_TOT8 = new ArrayList<String>();
                List<String> strKT_SO_TO_KHA9 = new ArrayList<String>();
                List<String> strKT_DUNO_KHA10 = new ArrayList<String>();
                List<String> strKT_SO_TO_TB11 = new ArrayList<String>();
                List<String> strKT_DUNO_TB12 = new ArrayList<String>();
                List<String> strKT_SO_TO_KEM13 = new ArrayList<String>();
                List<String> strKT_DUNO_KEM14 = new ArrayList<String>();
                List<String> strKT_DN15 = new ArrayList<String>();
                List<String> strKT_CO_DINH16 = new ArrayList<String>();
                List<String> strKT_THEM17 = new ArrayList<String>();
                List<String> strKT_XOA18 = new ArrayList<String>();
                List<String> strKT_FONTWEIGHT19 = new ArrayList<String>();
                List<String> strKT_CAPHT20 = new ArrayList<String>();
                List<String> strKT_STT21 = new ArrayList<String>();
                List<String> strNG_CAPNHAT22 = new ArrayList<String>();
                List<String> strKT_KHOA23 = new ArrayList<String>();

                ktnb05ModelListSend = daoKtnb05.get_ktnb05_auth(sPOS, Integer.parseInt(namBc), Integer.parseInt(quyBc), "Y");
                if (ktnb05ModelListSend.size() <= 0) {
                    return false;
                }
                for (int i = 0; i < ktnb05ModelListSend.size(); i++) {

                    strKT_STT_HT1.add(ktnb05ModelListSend.get(i).getKT_STT_HT());
                    strKT_TC_UT2.add(ktnb05ModelListSend.get(i).getKT_TC_UT());
                    strKT_TONG_TO3.add(Double.toString(ktnb05ModelListSend.get(i).getKT_TONG_TO()));
                    strKT_TONG_DUNO4.add(Double.toString(ktnb05ModelListSend.get(i).getKT_TONG_DUNO()));
                    strKT_TKVV_ST5.add(Double.toString(ktnb05ModelListSend.get(i).getKT_TKVV_ST()));
                    strKT_TKVV_DN6.add(Double.toString(ktnb05ModelListSend.get(i).getKT_TKVV_DN()));
                    strKT_SO_TO_TOT7.add(Double.toString(ktnb05ModelListSend.get(i).getKT_SO_TO_TOT()));
                    strKT_DUNO_TOT8.add(Double.toString(ktnb05ModelListSend.get(i).getKT_DUNO_TOT()));
                    strKT_SO_TO_KHA9.add(Double.toString(ktnb05ModelListSend.get(i).getKT_SO_TO_KHA()));
                    strKT_DUNO_KHA10.add(Double.toString(ktnb05ModelListSend.get(i).getKT_DUNO_KHA()));
                    strKT_SO_TO_TB11.add(Double.toString(ktnb05ModelListSend.get(i).getKT_SO_TO_TB()));
                    strKT_DUNO_TB12.add(Double.toString(ktnb05ModelListSend.get(i).getKT_DUNO_TB()));
                    strKT_SO_TO_KEM13.add(Double.toString(ktnb05ModelListSend.get(i).getKT_SO_TO_KEM()));
                    strKT_DUNO_KEM14.add(Double.toString(ktnb05ModelListSend.get(i).getKT_DUNO_KEM()));
                    strKT_DN15.add(ktnb05ModelListSend.get(i).getKT_DN());
                    strKT_CO_DINH16.add(ktnb05ModelListSend.get(i).getKT_CO_DINH());
                    strKT_THEM17.add(ktnb05ModelListSend.get(i).getKT_THEM());
                    strKT_XOA18.add(ktnb05ModelListSend.get(i).getKT_XOA());
                    strKT_FONTWEIGHT19.add(ktnb05ModelListSend.get(i).getKT_FONTWEIGHT());
                    strKT_CAPHT20.add(Double.toString(ktnb05ModelListSend.get(i).getKT_CAPHT()));
                    strKT_STT21.add(Double.toString(ktnb05ModelListSend.get(i).getKT_STT()));
                    strNG_CAPNHAT22.add(ktnb05ModelListSend.get(i).getNG_CAPNHAT());
                    strKT_KHOA23.add(ktnb05ModelListSend.get(i).getKT_KHOA());

                }
                hmData.put("COT_1", DefineFun.converArrayList2String(strKT_STT_HT1));
                hmData.put("COT_2", DefineFun.converArrayList2String(strKT_TC_UT2));
                hmData.put("COT_3", DefineFun.converArrayList2String(strKT_TONG_TO3));
                hmData.put("COT_4", DefineFun.converArrayList2String(strKT_TONG_DUNO4));
                hmData.put("COT_5", DefineFun.converArrayList2String(strKT_TKVV_ST5));
                hmData.put("COT_6", DefineFun.converArrayList2String(strKT_TKVV_DN6));
                hmData.put("COT_7", DefineFun.converArrayList2String(strKT_SO_TO_TOT7));
                hmData.put("COT_8", DefineFun.converArrayList2String(strKT_DUNO_TOT8));
                hmData.put("COT_9", DefineFun.converArrayList2String(strKT_SO_TO_KHA9));
                hmData.put("COT_10", DefineFun.converArrayList2String(strKT_DUNO_KHA10));
                hmData.put("COT_11", DefineFun.converArrayList2String(strKT_SO_TO_TB11));
                hmData.put("COT_12", DefineFun.converArrayList2String(strKT_DUNO_TB12));
                hmData.put("COT_13", DefineFun.converArrayList2String(strKT_SO_TO_KEM13));
                hmData.put("COT_14", DefineFun.converArrayList2String(strKT_DUNO_KEM14));
                hmData.put("COT_15", DefineFun.converArrayList2String(strKT_DN15));
                hmData.put("COT_16", DefineFun.converArrayList2String(strKT_CO_DINH16));
                hmData.put("COT_17", DefineFun.converArrayList2String(strKT_THEM17));
                hmData.put("COT_18", DefineFun.converArrayList2String(strKT_XOA18));
                hmData.put("COT_19", DefineFun.converArrayList2String(strKT_FONTWEIGHT19));
                hmData.put("COT_20", DefineFun.converArrayList2String(strKT_CAPHT20));
                hmData.put("COT_21", DefineFun.converArrayList2String(strKT_STT21));
                hmData.put("COT_22", DefineFun.converArrayList2String(strNG_CAPNHAT22));
                hmData.put("COT_23", DefineFun.converArrayList2String(strKT_KHOA23));

                //Tao file xml theo cau truc
                ProcessReportSyn clientWritexml = new ProcessReportSyn();
                boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKTNB(Define.PARA_SYN_REPORT_KTNB,
                        Define.SYN_REPORT_KTNB05, reportDate, userId, sPOS, maCn, reporGrade, "BCNT", hmData, quyBc, namBc, pathSave);
                if (!bSuccess) {
                    System.err.println("Ban chua dong bo du lieu duoc ve TW");
                    return false;
                } else {
                    daoKtnb01.lock_ktnb(Define.SYN_REPORT_KTNB05, userName, sPOS, quyBc, namBc, reporGrade, reportDate);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Loi tao file vao gui TW " + e.getMessage());
            return false;
        }

        //========== END DONG BO DU LIEU =======================================
        return true;
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

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public ListKTNBDA getListKTNBDA() {
        return listKTNBDA;
    }

    public void setListKTNBDA(ListKTNBDA listKTNBDA) {
        this.listKTNBDA = listKTNBDA;
    }

    public List<Ktnb05Model> getKtnb05ModelList() {
        return ktnb05ModelList;
    }

    public void setKtnb05ModelList(List<Ktnb05Model> ktnb05ModelList) {
        this.ktnb05ModelList = ktnb05ModelList;
    }

    public List<String> getKT_STT_HT() {
        return KT_STT_HT;
    }

    public void setKT_STT_HT(List<String> KT_STT_HT) {
        this.KT_STT_HT = KT_STT_HT;
    }

    public List<String> getKT_TC_UT() {
        return KT_TC_UT;
    }

    public void setKT_TC_UT(List<String> KT_TC_UT) {
        this.KT_TC_UT = KT_TC_UT;
    }

    public List<String> getKT_TONG_TO() {
        return KT_TONG_TO;
    }

    public void setKT_TONG_TO(List<String> KT_TONG_TO) {
        this.KT_TONG_TO = KT_TONG_TO;
    }

    public List<String> getKT_TONG_DUNO() {
        return KT_TONG_DUNO;
    }

    public void setKT_TONG_DUNO(List<String> KT_TONG_DUNO) {
        this.KT_TONG_DUNO = KT_TONG_DUNO;
    }

    public List<String> getKT_TKVV_ST() {
        return KT_TKVV_ST;
    }

    public void setKT_TKVV_ST(List<String> KT_TKVV_ST) {
        this.KT_TKVV_ST = KT_TKVV_ST;
    }

    public List<String> getKT_TKVV_DN() {
        return KT_TKVV_DN;
    }

    public void setKT_TKVV_DN(List<String> KT_TKVV_DN) {
        this.KT_TKVV_DN = KT_TKVV_DN;
    }

    public List<String> getKT_SO_TO_TOT() {
        return KT_SO_TO_TOT;
    }

    public void setKT_SO_TO_TOT(List<String> KT_SO_TO_TOT) {
        this.KT_SO_TO_TOT = KT_SO_TO_TOT;
    }

    public List<String> getKT_DUNO_TOT() {
        return KT_DUNO_TOT;
    }

    public void setKT_DUNO_TOT(List<String> KT_DUNO_TOT) {
        this.KT_DUNO_TOT = KT_DUNO_TOT;
    }

    public List<String> getKT_SO_TO_TB() {
        return KT_SO_TO_TB;
    }

    public void setKT_SO_TO_TB(List<String> KT_SO_TO_TB) {
        this.KT_SO_TO_TB = KT_SO_TO_TB;
    }

    public List<String> getKT_DUNO_TB() {
        return KT_DUNO_TB;
    }

    public void setKT_DUNO_TB(List<String> KT_DUNO_TB) {
        this.KT_DUNO_TB = KT_DUNO_TB;
    }

    public List<String> getKT_SO_TO_KEM() {
        return KT_SO_TO_KEM;
    }

    public void setKT_SO_TO_KEM(List<String> KT_SO_TO_KEM) {
        this.KT_SO_TO_KEM = KT_SO_TO_KEM;
    }

    public List<String> getKT_DUNO_KEM() {
        return KT_DUNO_KEM;
    }

    public void setKT_DUNO_KEM(List<String> KT_DUNO_KEM) {
        this.KT_DUNO_KEM = KT_DUNO_KEM;
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

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public List<String> getKT_SO_TO_KHA() {
        return KT_SO_TO_KHA;
    }

    public void setKT_SO_TO_KHA(List<String> KT_SO_TO_KHA) {
        this.KT_SO_TO_KHA = KT_SO_TO_KHA;
    }

    public List<String> getKT_DUNO_KHA() {
        return KT_DUNO_KHA;
    }

    public void setKT_DUNO_KHA(List<String> KT_DUNO_KHA) {
        this.KT_DUNO_KHA = KT_DUNO_KHA;
    }

//</editor-fold>
}
