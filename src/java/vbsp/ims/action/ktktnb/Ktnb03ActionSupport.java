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
import vbsp.ims.dao.ktnb.DaoKtnb03;
import vbsp.ims.dao.ktnb.ListKTNBDA;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.Ktnb01Model;
import vbsp.ims.model.ktnb.Ktnb03Model;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.syn.ProcessReportSyn;

/**
 *
 * @author CuongBM0211
 */
public class Ktnb03ActionSupport extends ActionSupport implements ServletRequestAware {

    private DaoKtnb03 daoKtnb03 = new DaoKtnb03();
    private ListKTNBDA listKTNBDA = new ListKTNBDA();
    private List<Ktnb03Model> ktnb03ModelList;  //Lay du lieu load len table
    private DaoKtnb01 daoKtnb01 = new DaoKtnb01();
    //Cac truong dung cho luu du lieu
    private List<String> KT_STT_HT;
    private List<String> KT_TIEUCHI;
    private List<String> KT_SDCC_SHVV;
    private List<String> KT_SDCC_ST;
    private List<String> KT_SDDC_SHVV;
    private List<String> KT_SDDC_ST;
    private List<String> KT_TLDC_SHVV;
    private List<String> KT_TLDC_ST;
    private List<String> KT_SSDC_SKH;
    private List<String> KT_SSDC_ST;
    private List<String> KT_SSDC_TLST;
    private List<String> KT_SSDC_GC;
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
    private String userName;

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
    private String selectedPos;
    private TreeNode searchNodes;

    private HttpServletRequest request = null;

    public Ktnb03ActionSupport() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String get_data_ktnb03() {
        getInfo();
        ktnb03ModelList = daoKtnb03.get_ktnb03(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
    }

    public String ResetDataInput3() {
        getInfo();
        ktnb03ModelList = daoKtnb03.get_ktnb03_default(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
    }

    public String get_data_ktnb03_auth() throws SQLException {
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
        ktnb03ModelList = daoKtnb03.get_ktnb03_auth(strPosCD, Integer.parseInt(namBc), Integer.parseInt(quyBc), "N");
        return "success";
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
        List<QT_DULIEU_NT> lstDulieuNt = daoKtnb01.getData_lock_ktnb(conn, "KTNB03", userName, maCn, quyBc, namBc, reportGrade1, "");
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
                if (!daoKtnb03.save_ktnb03_auth(pos_auth, quyBc, namBc)) {
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
                pathSave += sPOS + "_BCNT_KTNB_" + "03" + ".xml";    //Thay ma bao cao
                String reportDate = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());

                HashMap<String, String> hmData = new HashMap<String, String>();

                List<Ktnb03Model> ktnb03ModelListSend = null;

                List<String> strSTT_HT1 = new ArrayList<String>();
                List<String> strTIEUCHI2 = new ArrayList<String>();
                List<String> strSDCC_SHVV3 = new ArrayList<String>();
                List<String> strSDCC_ST4 = new ArrayList<String>();
                List<String> strSDDC_SHVV5 = new ArrayList<String>();
                List<String> strSDDC_ST6 = new ArrayList<String>();
                List<String> strTLDC_SHVV7 = new ArrayList<String>();
                List<String> strTLDC_ST8 = new ArrayList<String>();
                List<String> strSSDC_SKH9 = new ArrayList<String>();
                List<String> strSSDC_ST10 = new ArrayList<String>();
                List<String> strSSDC_TLST11 = new ArrayList<String>();
                List<String> strSSDC_GC12 = new ArrayList<String>();
                List<String> strDN13 = new ArrayList<String>();
                List<String> strCO_DINH14 = new ArrayList<String>();
                List<String> strTHEM15 = new ArrayList<String>();
                List<String> strXOA16 = new ArrayList<String>();
                List<String> strFONTWEIGHT17 = new ArrayList<String>();
                List<String> strCAPHT18 = new ArrayList<String>();
                List<String> strSTT19 = new ArrayList<String>();
                List<String> strNG_CAPNHAT20 = new ArrayList<String>();
                List<String> strKhOA21 = new ArrayList<String>();

                ktnb03ModelListSend = daoKtnb03.get_ktnb03_auth(sPOS, Integer.parseInt(namBc), Integer.parseInt(quyBc), "Y");
                if (ktnb03ModelListSend.size() <= 0) {
                    return false;
                }
                for (int i = 0; i < ktnb03ModelListSend.size(); i++) {
                    strSTT_HT1.add(ktnb03ModelListSend.get(i).getKT_STT_HT());
                    strTIEUCHI2.add(ktnb03ModelListSend.get(i).getKT_TIEUCHI());
                    strSDCC_SHVV3.add(Double.toString(ktnb03ModelListSend.get(i).getKT_SDCC_SHVV()));
                    strSDCC_ST4.add(Double.toString(ktnb03ModelListSend.get(i).getKT_SDCC_ST()));
                    strSDDC_SHVV5.add(Double.toString(ktnb03ModelListSend.get(i).getKT_SDDC_SHVV()));
                    strSDDC_ST6.add(Double.toString(ktnb03ModelListSend.get(i).getKT_SDDC_ST()));
                    strTLDC_SHVV7.add(Double.toString(ktnb03ModelListSend.get(i).getKT_TLDC_SHVV()));
                    strTLDC_ST8.add(Double.toString(ktnb03ModelListSend.get(i).getKT_TLDC_ST()));
                    strSSDC_SKH9.add(Double.toString(ktnb03ModelListSend.get(i).getKT_SSDC_SKH()));
                    strSSDC_ST10.add(Double.toString(ktnb03ModelListSend.get(i).getKT_SSDC_ST()));
                    strSSDC_TLST11.add(Double.toString(ktnb03ModelListSend.get(i).getKT_SSDC_TLST()));
                    strSSDC_GC12.add(ktnb03ModelListSend.get(i).getKT_SSDC_GC());
                    strDN13.add(ktnb03ModelListSend.get(i).getKT_DN());
                    strCO_DINH14.add(ktnb03ModelListSend.get(i).getKT_CO_DINH());
                    strTHEM15.add(ktnb03ModelListSend.get(i).getKT_THEM());
                    strXOA16.add(ktnb03ModelListSend.get(i).getKT_XOA());
                    strFONTWEIGHT17.add(ktnb03ModelListSend.get(i).getKT_FONTWEIGHT());
                    strCAPHT18.add(Double.toString(ktnb03ModelListSend.get(i).getKT_CAPHT()));
                    strSTT19.add(Double.toString(ktnb03ModelListSend.get(i).getKT_STT()));
                    strNG_CAPNHAT20.add(ktnb03ModelListSend.get(i).getNG_CAPNHAT());
                    strKhOA21.add(ktnb03ModelListSend.get(i).getKT_KHOA());

                }

                hmData.put("COT_1", DefineFun.converArrayList2String(strSTT_HT1));
                hmData.put("COT_2", DefineFun.converArrayList2String(strTIEUCHI2));
                hmData.put("COT_3", DefineFun.converArrayList2String(strSDCC_SHVV3));
                hmData.put("COT_4", DefineFun.converArrayList2String(strSDCC_ST4));
                hmData.put("COT_5", DefineFun.converArrayList2String(strSDDC_SHVV5));
                hmData.put("COT_6", DefineFun.converArrayList2String(strSDDC_ST6));
                hmData.put("COT_7", DefineFun.converArrayList2String(strTLDC_SHVV7));
                hmData.put("COT_8", DefineFun.converArrayList2String(strTLDC_ST8));
                hmData.put("COT_9", DefineFun.converArrayList2String(strSSDC_SKH9));
                hmData.put("COT_10", DefineFun.converArrayList2String(strSSDC_ST10));
                hmData.put("COT_11", DefineFun.converArrayList2String(strSSDC_TLST11));
                hmData.put("COT_12", DefineFun.converArrayList2String(strSSDC_GC12));
                hmData.put("COT_13", DefineFun.converArrayList2String(strDN13));
                hmData.put("COT_14", DefineFun.converArrayList2String(strCO_DINH14));
                hmData.put("COT_15", DefineFun.converArrayList2String(strTHEM15));
                hmData.put("COT_16", DefineFun.converArrayList2String(strXOA16));
                hmData.put("COT_17", DefineFun.converArrayList2String(strFONTWEIGHT17));
                hmData.put("COT_18", DefineFun.converArrayList2String(strCAPHT18));
                hmData.put("COT_19", DefineFun.converArrayList2String(strSTT19));
                hmData.put("COT_20", DefineFun.converArrayList2String(strNG_CAPNHAT20));
                hmData.put("COT_21", DefineFun.converArrayList2String(strKhOA21));

                //Tao file xml theo cau truc
                ProcessReportSyn clientWritexml = new ProcessReportSyn();
                boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKTNB(Define.PARA_SYN_REPORT_KTNB,
                        Define.SYN_REPORT_KTNB03, reportDate, userId, sPOS, maCn, reporGrade, "BCNT", hmData, quyBc, namBc, pathSave);
                if (!bSuccess) {
                    System.err.println("Ban chua dong bo du lieu duoc ve TW");
                    return false;
                } else {
                    daoKtnb01.lock_ktnb(Define.SYN_REPORT_KTNB03, userName, sPOS, quyBc, namBc, reporGrade, reportDate);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Loi tao file vao gui TW " + e.getMessage());
            return false;
        }

        //========== END DONG BO DU LIEU =======================================
        return true;
    }

    public String save_data_ktnb03() {
        if (daoKtnb03.save_ktnb03(posCD, maCn, quyBc, namBc, userId, KT_KHOA, KT_SDCC_SHVV, KT_SDCC_ST, KT_SDDC_SHVV, KT_SDDC_ST, KT_TLDC_SHVV,
                KT_TLDC_ST, KT_SSDC_SKH, KT_SSDC_ST, KT_SSDC_TLST, KT_SSDC_GC, "")) {
            return "success";
        } else {
            return "error";
        }
    }

//        //DONG BO DU LIEU LEN TW
//        HttpSession session = request.getSession();
//        String reporGrade = session.getAttribute("reportGrade").toString();
//        if (!reporGrade.equals("3")) {
//            //Lay duong dan va ten xml se ghi ra
//            String pathSave = !request.getRealPath("/").endsWith("/")
//                    ? request.getRealPath("/") + "/" + Define.M_REPORT_XML
//                    : request.getRealPath("/") + Define.M_REPORT_XML;
//                    pathSave+= posCD + "_BCNT_KTNB_"+"03"+".xml";    //Thay ma bao cao
//            String reportDate =  new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
//            
//            HashMap<String, String> hmData = new HashMap<String, String>();
//            
//            hmData.put("COT_1",DefineFun.converArrayList2String(KT_STT_HT));
//            hmData.put("COT_2",DefineFun.converArrayList2String(KT_TIEUCHI));
//            hmData.put("COT_3",DefineFun.converArrayList2String(KT_SDCC_SHVV));
//            hmData.put("COT_4",DefineFun.converArrayList2String(KT_SDCC_ST));
//            hmData.put("COT_5",DefineFun.converArrayList2String(KT_SDDC_SHVV));
//            hmData.put("COT_6",DefineFun.converArrayList2String(KT_SDDC_ST));
//            hmData.put("COT_7",DefineFun.converArrayList2String(KT_TLDC_SHVV));
//            hmData.put("COT_8",DefineFun.converArrayList2String(KT_TLDC_ST));
//            hmData.put("COT_9",DefineFun.converArrayList2String(KT_SSDC_SKH));
//            hmData.put("COT_10",DefineFun.converArrayList2String(KT_SSDC_ST));
//            hmData.put("COT_11",DefineFun.converArrayList2String(KT_SSDC_TLST));
//            hmData.put("COT_12",DefineFun.converArrayList2String(KT_SSDC_GC));
//            hmData.put("COT_13",DefineFun.converArrayList2String(KT_DN));
//            hmData.put("COT_14",DefineFun.converArrayList2String(KT_CO_DINH));
//            hmData.put("COT_15",DefineFun.converArrayList2String(KT_THEM));
//            hmData.put("COT_16",DefineFun.converArrayList2String(KT_XOA));
//            hmData.put("COT_17",DefineFun.converArrayList2String(KT_FONTWEIGHT));
//            hmData.put("COT_18",DefineFun.converArrayList2String(KT_CAPHT));
//            hmData.put("COT_19",DefineFun.converArrayList2String(KT_STT));
//            hmData.put("COT_20",DefineFun.converArrayList2String(NG_CAPNHAT));
//
//            
//            //Tao file xml theo cau truc
//            ProcessReportSyn clientWritexml = new ProcessReportSyn();
//            boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKTNB(Define.PARA_SYN_REPORT_KTNB,
//                    Define.SYN_REPORT_KTNB03, reportDate, userId, posCD, maCn, reporGrade, "BCNT", hmData,quyBc,namBc, pathSave);
//            if (!bSuccess) {
//                System.err.println("Ban chua dong bo du lieu duoc ve TW");
//            }
//        }
    //========== END DONG BO DU LIEU =======================================
//    }
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
    public List<Ktnb03Model> getKtnb03ModelList() {
        return ktnb03ModelList;
    }

    public void setKtnb03ModelList(List<Ktnb03Model> ktnb03ModelList) {
        this.ktnb03ModelList = ktnb03ModelList;
    }

    public List<String> getKT_STT_HT() {
        return KT_STT_HT;
    }

    public void setKT_STT_HT(List<String> KT_STT_HT) {
        this.KT_STT_HT = KT_STT_HT;
    }

    public List<String> getKT_TIEUCHI() {
        return KT_TIEUCHI;
    }

    public void setKT_TIEUCHI(List<String> KT_TIEUCHI) {
        this.KT_TIEUCHI = KT_TIEUCHI;
    }

    public List<String> getKT_SDCC_SHVV() {
        return KT_SDCC_SHVV;
    }

    public void setKT_SDCC_SHVV(List<String> KT_SDCC_SHVV) {
        this.KT_SDCC_SHVV = KT_SDCC_SHVV;
    }

    public List<String> getKT_SDCC_ST() {
        return KT_SDCC_ST;
    }

    public void setKT_SDCC_ST(List<String> KT_SDCC_ST) {
        this.KT_SDCC_ST = KT_SDCC_ST;
    }

    public List<String> getKT_SDDC_SHVV() {
        return KT_SDDC_SHVV;
    }

    public void setKT_SDDC_SHVV(List<String> KT_SDDC_SHVV) {
        this.KT_SDDC_SHVV = KT_SDDC_SHVV;
    }

    public List<String> getKT_SDDC_ST() {
        return KT_SDDC_ST;
    }

    public void setKT_SDDC_ST(List<String> KT_SDDC_ST) {
        this.KT_SDDC_ST = KT_SDDC_ST;
    }

    public List<String> getKT_TLDC_SHVV() {
        return KT_TLDC_SHVV;
    }

    public void setKT_TLDC_SHVV(List<String> KT_TLDC_SHVV) {
        this.KT_TLDC_SHVV = KT_TLDC_SHVV;
    }

    public List<String> getKT_TLDC_ST() {
        return KT_TLDC_ST;
    }

    public void setKT_TLDC_ST(List<String> KT_TLDC_ST) {
        this.KT_TLDC_ST = KT_TLDC_ST;
    }

    public List<String> getKT_SSDC_SKH() {
        return KT_SSDC_SKH;
    }

    public void setKT_SSDC_SKH(List<String> KT_SSDC_SKH) {
        this.KT_SSDC_SKH = KT_SSDC_SKH;
    }

    public List<String> getKT_SSDC_ST() {
        return KT_SSDC_ST;
    }

    public void setKT_SSDC_ST(List<String> KT_SSDC_ST) {
        this.KT_SSDC_ST = KT_SSDC_ST;
    }

    public List<String> getKT_SSDC_TLST() {
        return KT_SSDC_TLST;
    }

    public void setKT_SSDC_TLST(List<String> KT_SSDC_TLST) {
        this.KT_SSDC_TLST = KT_SSDC_TLST;
    }

    public List<String> getKT_SSDC_GC() {
        return KT_SSDC_GC;
    }

    public void setKT_SSDC_GC(List<String> KT_SSDC_GC) {
        this.KT_SSDC_GC = KT_SSDC_GC;
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
