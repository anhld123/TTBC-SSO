package vbsp.ims.action.ktktnb;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.BuildPosTreeDao;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.ktnb.DaoKtnb01;
import vbsp.ims.dao.ktnb.ListKTNBDA;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.Ktnb01Model;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.syn.ProcessReportSyn;

public class Ktnb01ActionSupport extends ActionSupport implements ServletRequestAware {

    private DaoKtnb01 daoKtnb01 = new DaoKtnb01();
    private ListKTNBDA listKTNBDA = new ListKTNBDA();
    private List<Ktnb01Model> ktnb01ModelList;
    private List<String> lstPOS;
    private TreeNode nodes;
    private int reportGrade;
    private String userName;
    private String selectedPos;
    private TreeNode searchNodes;
    private List<String> KT_STT_HT;
    private List<String> KT_DKT;
    private List<String> KT_SLT;
    private List<String> KT_SLH;
    private List<String> KT_SL_DGD;
    private List<String> KT_SL_TKVV;
    private List<String> KT_DN;
    private List<String> KT_CO_DINH;
    private List<String> KT_THEM;
    private List<String> KT_XOA;
    private List<String> KT_FONTWEIGHT;
    private List<String> KT_CAPHT;
    private List<String> KT_STT;
    private List<String> NG_CAPNHAT;
    private List<String> KT_KHOA;
    private String userId;
    private String posCD;
    private String quyBc;
    private String namBc;
    private String maCn;
    private String message;
    private HttpServletRequest request = null;

    public TreeNode getSearchNodes() {
        return this.searchNodes;
    }

    public void setSearchNodes(TreeNode searchNodes) {
        this.searchNodes = searchNodes;
    }

    public String getSelectedPos() {
        return this.selectedPos;
    }

    public void setSelectedPos(String selectedPos) {
        this.selectedPos = selectedPos;
    }

    public TreeNode getNodes() {
        return this.nodes;
    }

    public void setNodes(TreeNode nodes) {
        this.nodes = nodes;
    }

    public List<String> getLstPOS() {
        return this.lstPOS;
    }

    public void setLstPOS(List<String> lstPOS) {
        this.lstPOS = lstPOS;
    }

    public List<String> getKT_KHOA() {
        return this.KT_KHOA;
    }

    public void setKT_KHOA(List<String> KT_KHOA) {
        this.KT_KHOA = KT_KHOA;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String get_data_ktnb01() {
        this.getInfo();
        this.ktnb01ModelList = this.daoKtnb01.get_ktnb01(this.posCD, Integer.parseInt(this.namBc), Integer.parseInt(this.quyBc));
        return "success";
    }

    public String ResetDataInput1() {
        this.getInfo();
        this.ktnb01ModelList = this.daoKtnb01.get_ktnb01_default(this.posCD, Integer.parseInt(this.namBc), Integer.parseInt(this.quyBc));
        return "success";
    }

    public String get_data_ktnb01_auth() throws SQLException {
        this.getInfo();
        return "success";
    }

    private String getListOfPos() {
        String posString = "";
        this.userName = this.request.getSession().getAttribute("username").toString();
        this.reportGrade = Integer.parseInt(this.request.getSession().getAttribute("reportGrade").toString());
        ArrayList<String> pos_stack = new ArrayList();
        ArrayList<String> listOfId = (ArrayList) DefineFun.string2Array(this.selectedPos, ",", 1);
        if (listOfId.size() == 0) {
            return "";
        } else {
            if (listOfId.size() > 0) {
                Iterator var6 = listOfId.iterator();

                while (var6.hasNext()) {
                    String id = (String) var6.next();
                    boolean isAdded = false;
                    String pos_cd = DefineFun.searchInTreeView(id, this.searchNodes);
                    Iterator var8 = pos_stack.iterator();

                    while (var8.hasNext()) {
                        String added_pos = (String) var8.next();
                        if (added_pos.equals(pos_cd)) {
                            isAdded = true;
                            break;
                        }
                    }

                    if (!isAdded && pos_cd != null) {
                        posString = posString + pos_cd + ",";
                        pos_stack.add(pos_cd);
                    }
                }
            }

            posString = posString.substring(0, posString.length() - 1);
            System.err.println(posString);
            return posString;
        }
    }

    public String view1() throws SQLException {
        this.getInfo();
        this.ktnb01ModelList = this.daoKtnb01.get_ktnb01(this.posCD, Integer.parseInt(this.namBc), Integer.parseInt(this.quyBc));
        return "success";
    }

    public String view() throws SQLException {
        System.err.println(" da vao action view");
        if (this.searchNodes == null) {
//         System.err.print("searchNodes is null");

            try {
                this.reportGrade = Integer.parseInt(this.request.getSession().getAttribute("reportGrade").toString());
                this.userName = this.request.getSession().getAttribute("username").toString();
                BuildPosTreeDao buildPosTreeDao = new BuildPosTreeDao(this.reportGrade, this.userName);
                buildPosTreeDao.build();
                this.searchNodes = buildPosTreeDao.getNodes();
            } catch (SQLException var3) {
            }
        }

        String listOfPos = this.getListOfPos();
        if (listOfPos != "" && listOfPos.length() <= 6) {
            String strPosCD = listOfPos.substring(0, 6);
//         System.err.print(strPosCD);
            this.getInfo();
            this.ktnb01ModelList = this.daoKtnb01.get_ktnb01_auth(strPosCD, Integer.parseInt(this.namBc), Integer.parseInt(this.quyBc), "N");
            return "success";
        } else {
            return "error-pos";
        }
    }

    public String save_data_ktnb01() {
        return this.daoKtnb01.save_ktnb01(this.posCD, this.maCn, this.quyBc, this.namBc, this.userId, this.KT_KHOA, this.KT_SLT, this.KT_SLH, this.KT_SL_DGD, this.KT_SL_TKVV, "") ? "success" : "error";
    }

    public String Auth1() {
        return this.daoKtnb01.save_ktnb01(this.posCD, this.maCn, this.quyBc, this.namBc, this.userId, this.KT_KHOA, this.KT_SLT, this.KT_SLH, this.KT_SL_DGD, this.KT_SL_TKVV, "") ? "success" : "error";
    }

    public String Auth() throws SQLException {
        System.err.println(" da vao action auth");
        if (this.searchNodes == null) {
//         System.err.print("searchNodes is null");

            try {
                this.reportGrade = Integer.parseInt(this.request.getSession().getAttribute("reportGrade").toString());
                this.userName = this.request.getSession().getAttribute("username").toString();
                BuildPosTreeDao buildPosTreeDao = new BuildPosTreeDao(this.reportGrade, this.userName);
                buildPosTreeDao.build();
                this.searchNodes = buildPosTreeDao.getNodes();
            } catch (SQLException var7) {
            }
        }
        String reportGrade1 = Integer.toString(reportGrade);
        Connection conn = new DaoConnect().getConnect();
        List<QT_DULIEU_NT> lstDulieuNt = daoKtnb01.getData_lock_ktnb(conn, "KTNB01", userName, maCn, quyBc, namBc, reportGrade1, "");
        System.out.println("para = " + userName + maCn + quyBc + namBc);
        String Check_lock;
        try {
            Check_lock = lstDulieuNt.get(0).getD2();
        } catch (Exception e) {
            Check_lock = "0";
        }
        System.out.println("Check_lock= " + Check_lock);
        if (!Check_lock.equals("0")) {
            return "error-lock";
        }

        String listOfPos = this.getListOfPos();
        String lsPos = this.listKTNBDA.getListPos(this.maCn);
        if (lsPos.length() != listOfPos.length()) {
//            System.err.print("So pos " + lsPos.length());
            return "error-pos";
        } else {
            String[] var3 = listOfPos.split(",");
            int var4 = var3.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                String pos_auth = var3[var5];
//            System.out.println(pos_auth);
                if (!this.daoKtnb01.save_ktnb01_auth(pos_auth, this.quyBc, this.namBc)) {
                    return "error";
                }

                if (!this.maCn.equals("000101") && !this.syn_data_HO(pos_auth)) {
                    return "error-send";
                }
            }

            return "success";
        }
    }

    public boolean syn_data_HO(String sPOS) {
        try {
            HttpSession session = this.request.getSession();
            String reporGrade = session.getAttribute("reportGrade").toString();

            if (!reporGrade.equals("3")) {
                String pathSave = !this.request.getRealPath("/").endsWith("/") ? this.request.getRealPath("/") + "/" + "EXPORT_REPORT/XML/" : this.request.getRealPath("/") + "EXPORT_REPORT/XML/";
                pathSave = pathSave + sPOS + "_BCNT_KTNB_01.xml";
                String reportDate = (new SimpleDateFormat("dd-MMM-yyyy")).format(new Date());
                HashMap<String, String> hmData = new HashMap();
                List<Ktnb01Model> ktnb01ModelListSend = null;
                List<String> strKT_STT_HT1 = new ArrayList();
                List<String> strKT_DKT2 = new ArrayList();
                List<String> strKT_SLT3 = new ArrayList();
                List<String> strKT_SLH4 = new ArrayList();
                List<String> strKT_SL_DGD5 = new ArrayList();
                List<String> strKT_SL_TKVV6 = new ArrayList();
                List<String> strKT_DN7 = new ArrayList();
                List<String> strKT_CO_DINH8 = new ArrayList();
                List<String> strKT_THEM9 = new ArrayList();
                List<String> strKT_XOA10 = new ArrayList();
                List<String> strKT_FONTWEIGHT11 = new ArrayList();
                List<String> strKT_CAPHT12 = new ArrayList();
                List<String> strKT_STT13 = new ArrayList();
                List<String> strNG_CAPNHAT14 = new ArrayList();
                List<String> strKT_KHOA15 = new ArrayList();
                ktnb01ModelListSend = this.daoKtnb01.get_ktnb01_auth(sPOS, Integer.parseInt(this.namBc), Integer.parseInt(this.quyBc), "Y");
                if (ktnb01ModelListSend.size() <= 0) {
                    return false;
                }

                for (int i = 0; i < ktnb01ModelListSend.size(); ++i) {
                    strKT_STT_HT1.add(((Ktnb01Model) ktnb01ModelListSend.get(i)).getKT_STT_HT());
                    strKT_DKT2.add(((Ktnb01Model) ktnb01ModelListSend.get(i)).getKT_DKT());
                    strKT_SLT3.add(Double.toString(((Ktnb01Model) ktnb01ModelListSend.get(i)).getKT_SLT()));
                    strKT_SLH4.add(Double.toString(((Ktnb01Model) ktnb01ModelListSend.get(i)).getKT_SLH()));
                    strKT_SL_DGD5.add(Double.toString(((Ktnb01Model) ktnb01ModelListSend.get(i)).getKT_SL_DGD()));
                    strKT_SL_TKVV6.add(Double.toString(((Ktnb01Model) ktnb01ModelListSend.get(i)).getKT_SL_TKVV()));
                    strKT_DN7.add(((Ktnb01Model) ktnb01ModelListSend.get(i)).getKT_DN());
                    strKT_CO_DINH8.add(((Ktnb01Model) ktnb01ModelListSend.get(i)).getKT_CO_DINH());
                    strKT_THEM9.add(((Ktnb01Model) ktnb01ModelListSend.get(i)).getKT_THEM());
                    strKT_XOA10.add(((Ktnb01Model) ktnb01ModelListSend.get(i)).getKT_XOA());
                    strKT_FONTWEIGHT11.add(((Ktnb01Model) ktnb01ModelListSend.get(i)).getKT_FONTWEIGHT());
                    strKT_CAPHT12.add(Double.toString(((Ktnb01Model) ktnb01ModelListSend.get(i)).getKT_CAPHT()));
                    strNG_CAPNHAT14.add(((Ktnb01Model) ktnb01ModelListSend.get(i)).getNG_CAPNHAT());
                    strKT_STT13.add(Double.toString(((Ktnb01Model) ktnb01ModelListSend.get(i)).getKT_STT()));
                    strKT_KHOA15.add(((Ktnb01Model) ktnb01ModelListSend.get(i)).getKT_KHOA());
                }

                hmData.put("COT_1", DefineFun.converArrayList2String(strKT_STT_HT1));
                hmData.put("COT_2", DefineFun.converArrayList2String(strKT_DKT2));
                hmData.put("COT_3", DefineFun.converArrayList2String(strKT_SLT3));
                hmData.put("COT_4", DefineFun.converArrayList2String(strKT_SLH4));
                hmData.put("COT_5", DefineFun.converArrayList2String(strKT_SL_DGD5));
                hmData.put("COT_6", DefineFun.converArrayList2String(strKT_SL_TKVV6));
                hmData.put("COT_7", DefineFun.converArrayList2String(strKT_DN7));
                hmData.put("COT_8", DefineFun.converArrayList2String(strKT_CO_DINH8));
                hmData.put("COT_9", DefineFun.converArrayList2String(strKT_THEM9));
                hmData.put("COT_10", DefineFun.converArrayList2String(strKT_XOA10));
                hmData.put("COT_11", DefineFun.converArrayList2String(strKT_FONTWEIGHT11));
                hmData.put("COT_12", DefineFun.converArrayList2String(strKT_CAPHT12));
                hmData.put("COT_13", DefineFun.converArrayList2String(strKT_STT13));
                hmData.put("COT_14", DefineFun.converArrayList2String(strNG_CAPNHAT14));
                hmData.put("COT_15", DefineFun.converArrayList2String(strKT_KHOA15));
                ProcessReportSyn clientWritexml = new ProcessReportSyn();
                boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKTNB("01", "KTNB01", reportDate, this.userId, sPOS, this.maCn, reporGrade, "BCNT", hmData, this.quyBc, this.namBc, pathSave);
                System.out.println("bSuccess=" + bSuccess);
                if (!bSuccess) {
                    System.err.println("Ban chua dong bo du lieu duoc ve TW");
                    return false;
                } else {
                    daoKtnb01.lock_ktnb("KTNB01", userName, sPOS, quyBc, namBc, reporGrade, reportDate);
                }
            }

            return true;
        } catch (Exception var25) {
            CoreLogger.error(this.getClass().getName() + " Loi tao file vao gui TW " + var25.getMessage());
            return false;
        }
    }

    public void getInfo() {
        HttpSession session = this.request.getSession();
        this.userId = session.getAttribute("username").toString();
        PosMainModel posMainModel = this.listKTNBDA.get_pos_main_pos(this.userId);
        this.posCD = posMainModel.getPosCd();
        this.maCn = posMainModel.getMainPosCd();
    }

    public List<Ktnb01Model> getKtnb01ModelList() {
        return this.ktnb01ModelList;
    }

    public void setKtnb01ModelList(List<Ktnb01Model> ktnb01ModelList) {
        this.ktnb01ModelList = ktnb01ModelList;
    }

    public DaoKtnb01 getDaoKtnb01() {
        return this.daoKtnb01;
    }

    public void setDaoKtnb01(DaoKtnb01 daoKtnb01) {
        this.daoKtnb01 = daoKtnb01;
    }

    public List<String> getKT_STT_HT() {
        return this.KT_STT_HT;
    }

    public void setKT_STT_HT(List<String> KT_STT_HT) {
        this.KT_STT_HT = KT_STT_HT;
    }

    public List<String> getKT_DKT() {
        return this.KT_DKT;
    }

    public void setKT_DKT(List<String> KT_DKT) {
        this.KT_DKT = KT_DKT;
    }

    public List<String> getKT_SLT() {
        return this.KT_SLT;
    }

    public void setKT_SLT(List<String> KT_SLT) {
        this.KT_SLT = KT_SLT;
    }

    public List<String> getKT_SLH() {
        return this.KT_SLH;
    }

    public void setKT_SLH(List<String> KT_SLH) {
        this.KT_SLH = KT_SLH;
    }

    public List<String> getKT_SL_DGD() {
        return this.KT_SL_DGD;
    }

    public void setKT_SL_DGD(List<String> KT_SL_DGD) {
        this.KT_SL_DGD = KT_SL_DGD;
    }

    public List<String> getKT_SL_TKVV() {
        return this.KT_SL_TKVV;
    }

    public void setKT_SL_TKVV(List<String> KT_SL_TKVV) {
        this.KT_SL_TKVV = KT_SL_TKVV;
    }

    public List<String> getKT_DN() {
        return this.KT_DN;
    }

    public void setKT_DN(List<String> KT_DN) {
        this.KT_DN = KT_DN;
    }

    public List<String> getKT_CO_DINH() {
        return this.KT_CO_DINH;
    }

    public void setKT_CO_DINH(List<String> KT_CO_DINH) {
        this.KT_CO_DINH = KT_CO_DINH;
    }

    public List<String> getKT_THEM() {
        return this.KT_THEM;
    }

    public void setKT_THEM(List<String> KT_THEM) {
        this.KT_THEM = KT_THEM;
    }

    public List<String> getKT_XOA() {
        return this.KT_XOA;
    }

    public void setKT_XOA(List<String> KT_XOA) {
        this.KT_XOA = KT_XOA;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPosCD() {
        return this.posCD;
    }

    public void setPosCD(String posCD) {
        this.posCD = posCD;
    }

    public String getQuyBc() {
        return this.quyBc;
    }

    public void setQuyBc(String quyBc) {
        this.quyBc = quyBc;
    }

    public String getNamBc() {
        return this.namBc;
    }

    public void setNamBc(String namBc) {
        this.namBc = namBc;
    }

    public String getMaCn() {
        return this.maCn;
    }

    public void setMaCn(String maCn) {
        this.maCn = maCn;
    }

    public List<String> getKT_FONTWEIGHT() {
        return this.KT_FONTWEIGHT;
    }

    public void setKT_FONTWEIGHT(List<String> KT_FONTWEIGHT) {
        this.KT_FONTWEIGHT = KT_FONTWEIGHT;
    }

    public List<String> getKT_CAPHT() {
        return this.KT_CAPHT;
    }

    public void setKT_CAPHT(List<String> KT_CAPHT) {
        this.KT_CAPHT = KT_CAPHT;
    }

    public List<String> getKT_STT() {
        return this.KT_STT;
    }

    public void setKT_STT(List<String> KT_STT) {
        this.KT_STT = KT_STT;
    }

    public List<String> getNG_CAPNHAT() {
        return this.NG_CAPNHAT;
    }

    public void setNG_CAPNHAT(List<String> NG_CAPNHAT) {
        this.NG_CAPNHAT = NG_CAPNHAT;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
}
