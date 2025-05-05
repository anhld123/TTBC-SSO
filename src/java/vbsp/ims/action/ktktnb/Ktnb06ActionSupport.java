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
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.BuildPosTreeDao;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.ktnb.DaoKtnb01;
import vbsp.ims.dao.ktnb.DaoKtnb06;
import vbsp.ims.dao.ktnb.ListKTNBDA;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.Ktnb01Model;
import vbsp.ims.model.ktnb.Ktnb06Model;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.syn.ProcessReportSyn;

/**
 *
 * @author CuongBM0211
 */
public class Ktnb06ActionSupport extends ActionSupport implements ServletRequestAware {

    private DaoKtnb06 daoKtnb06 = new DaoKtnb06();
    private ListKTNBDA listKTNBDA = new ListKTNBDA();
    private List<Ktnb06Model> ktnb06ModelList;  //Lay du lieu load len table
    private DaoKtnb01 daoKtnb01 = new DaoKtnb01();
    //Cac truong dung cho luu du lieu
    private List<String> KT_STT_HT;
    private List<String> KT_DT;
    private List<String> KT_TDDN_SV;
    private List<String> KT_TDDN_ST_G;
    private List<String> KT_TDDN_ST_L;
    private List<String> KT_TDDN_ST_TK;
    private List<String> KT_PHTK_SV;
    private List<String> KT_PHTK_ST_G;
    private List<String> KT_PHTK_ST_L;
    private List<String> KT_PHTK_ST_TK;
    private List<String> KT_DTH_SV;
    private List<String> KT_DTH_ST_G;
    private List<String> KT_DTH_ST_L;
    private List<String> KT_DTH_ST_TK;
    private List<String> KT_TDCK_SV;
    private List<String> KT_TDCK_ST_G;
    private List<String> KT_TDCK_ST_L;
    private List<String> KT_TDCK_ST_TK;
    private List<String> KT_GHICHU;
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

    public Ktnb06ActionSupport() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String get_data_ktnb06() {
        getInfo();
        ktnb06ModelList = daoKtnb06.get_ktnb06(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
    }

    public String ResetDataInput6() {
        getInfo();
        ktnb06ModelList = daoKtnb06.get_ktnb06_default(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
    }

    public String get_data_ktnb06_auth() throws SQLException {
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
        ktnb06ModelList = daoKtnb06.get_ktnb06_auth(strPosCD, Integer.parseInt(namBc), Integer.parseInt(quyBc), "N");
        return "success";
    }

    public String save_data_ktnb06() {
        if (daoKtnb06.save_ktnb06(posCD, maCn, quyBc, namBc, userId, KT_KHOA, KT_TDDN_SV,
                KT_TDDN_ST_G, KT_TDDN_ST_L, KT_TDDN_ST_TK, KT_PHTK_SV, KT_PHTK_ST_G,
                KT_PHTK_ST_L, KT_PHTK_ST_TK, KT_DTH_SV, KT_DTH_ST_G, KT_DTH_ST_L,
                KT_DTH_ST_TK, KT_TDCK_SV, KT_TDCK_ST_G, KT_TDCK_ST_L, KT_TDCK_ST_TK, "")) {
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
                //HttpServletRequest request = ServletActionContext.getRequest();
//                selectedPos=request.getSession().getAttribute("selectedPos").toString();
                this.searchNodes = buildPosTreeDao.getNodes();
            } catch (SQLException ex) {
            }
        }
        String reportGrade1 = Integer.toString(reportGrade);
        Connection conn = new DaoConnect().getConnect();
        List<QT_DULIEU_NT> lstDulieuNt = daoKtnb01.getData_lock_ktnb(conn, "KTNB06", userName, maCn, quyBc, namBc, reportGrade1, "");
//        System.out.println("para = " + userName + maCn + quyBc + namBc);
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
        String listOfPos = getListOfPos();
        String lsPos = listKTNBDA.getListPos(maCn);
        if (lsPos.length() != listOfPos.length()) {
            return "error-pos";
        }
        String[] var3 = listOfPos.split(",");
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            String pos_auth = var3[var5];
            if (!daoKtnb06.save_ktnb06_auth(pos_auth, quyBc, namBc)) {
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
                pathSave += sPOS + "_BCNT_KTNB_" + "06" + ".xml";    //Thay ma bao cao
                String reportDate = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());

                HashMap<String, String> hmData = new HashMap<String, String>();

                List<Ktnb06Model> ktnb06ModelListSend = null;

                List<String> strKT_STT_HT1 = new ArrayList<String>();
                List<String> strKT_DT2 = new ArrayList<String>();
                List<String> strKT_TDDN_SV3 = new ArrayList<String>();
                List<String> strKT_TDDN_ST_G4 = new ArrayList<String>();
                List<String> strKT_TDDN_ST_L5 = new ArrayList<String>();
                List<String> strKT_TDDN_ST_TK6 = new ArrayList<String>();
                List<String> strKT_PHTK_SV7 = new ArrayList<String>();
                List<String> strKT_PHTK_ST_G8 = new ArrayList<String>();
                List<String> strKT_PHTK_ST_L9 = new ArrayList<String>();
                List<String> strKT_PHTK_ST_TK10 = new ArrayList<String>();
                List<String> strKT_DTH_SV11 = new ArrayList<String>();
                List<String> strKT_DTH_ST_G12 = new ArrayList<String>();
                List<String> strKT_DTH_ST_L13 = new ArrayList<String>();
                List<String> strKT_DTH_ST_TK14 = new ArrayList<String>();
                List<String> strKT_TDCK_SV15 = new ArrayList<String>();
                List<String> strKT_TDCK_ST_G16 = new ArrayList<String>();
                List<String> strKT_TDCK_ST_L17 = new ArrayList<String>();
                List<String> strKT_TDCK_ST_TK18 = new ArrayList<String>();

                List<String> strKT_DN19 = new ArrayList<String>();
                List<String> strKT_CO_DINH20 = new ArrayList<String>();
                List<String> strKT_THEM21 = new ArrayList<String>();
                List<String> strKT_XOA22 = new ArrayList<String>();
                List<String> strKT_FONTWEIGHT23 = new ArrayList<String>();
                List<String> strKT_CAPHT24 = new ArrayList<String>();
                List<String> strKT_STT25 = new ArrayList<String>();
                List<String> strNG_CAPNHAT26 = new ArrayList<String>();
                List<String> strKT_KHOA27 = new ArrayList<String>();

                ktnb06ModelListSend = daoKtnb06.get_ktnb06_auth(sPOS, Integer.parseInt(namBc), Integer.parseInt(quyBc), "Y");
                if (ktnb06ModelListSend.size() <= 0) {
                    return false;
                }
                for (int i = 0; i < ktnb06ModelListSend.size(); i++) {
                    strKT_STT_HT1.add(ktnb06ModelListSend.get(i).getKT_STT_HT());
                    strKT_DT2.add(ktnb06ModelListSend.get(i).getKT_DT());
                    strKT_TDDN_SV3.add(Double.toString(ktnb06ModelListSend.get(i).getKT_TDDN_SV()));
                    strKT_TDDN_ST_G4.add(Double.toString(ktnb06ModelListSend.get(i).getKT_TDDN_ST_G()));
                    strKT_TDDN_ST_L5.add(Double.toString(ktnb06ModelListSend.get(i).getKT_TDDN_ST_L()));
                    strKT_TDDN_ST_TK6.add(Double.toString(ktnb06ModelListSend.get(i).getKT_TDDN_ST_TK()));
                    strKT_PHTK_SV7.add(Double.toString(ktnb06ModelListSend.get(i).getKT_PHTK_SV()));
                    strKT_PHTK_ST_G8.add(Double.toString(ktnb06ModelListSend.get(i).getKT_PHTK_ST_G()));
                    strKT_PHTK_ST_L9.add(Double.toString(ktnb06ModelListSend.get(i).getKT_PHTK_ST_L()));
                    strKT_PHTK_ST_TK10.add(Double.toString(ktnb06ModelListSend.get(i).getKT_PHTK_ST_TK()));
                    strKT_DTH_SV11.add(Double.toString(ktnb06ModelListSend.get(i).getKT_DTH_SV()));
                    strKT_DTH_ST_G12.add(Double.toString(ktnb06ModelListSend.get(i).getKT_DTH_ST_G()));
                    strKT_DTH_ST_L13.add(Double.toString(ktnb06ModelListSend.get(i).getKT_DTH_ST_L()));
                    strKT_DTH_ST_TK14.add(Double.toString(ktnb06ModelListSend.get(i).getKT_DTH_ST_TK()));
                    strKT_TDCK_SV15.add(Double.toString(ktnb06ModelListSend.get(i).getKT_TDCK_SV()));
                    strKT_TDCK_ST_G16.add(Double.toString(ktnb06ModelListSend.get(i).getKT_TDCK_ST_G()));
                    strKT_TDCK_ST_L17.add(Double.toString(ktnb06ModelListSend.get(i).getKT_TDCK_ST_L()));
                    strKT_TDCK_ST_TK18.add(Double.toString(ktnb06ModelListSend.get(i).getKT_TDCK_ST_TK()));

                    strKT_DN19.add(ktnb06ModelListSend.get(i).getKT_DN());
                    strKT_CO_DINH20.add(ktnb06ModelListSend.get(i).getKT_CO_DINH());
                    strKT_THEM21.add(ktnb06ModelListSend.get(i).getKT_THEM());
                    strKT_XOA22.add(ktnb06ModelListSend.get(i).getKT_XOA());
                    strKT_FONTWEIGHT23.add(ktnb06ModelListSend.get(i).getKT_FONTWEIGHT());
                    strKT_CAPHT24.add(Double.toString(ktnb06ModelListSend.get(i).getKT_CAPHT()));
                    strKT_STT25.add(Double.toString(ktnb06ModelListSend.get(i).getKT_STT()));
                    strNG_CAPNHAT26.add(ktnb06ModelListSend.get(i).getNG_CAPNHAT());
                    strKT_KHOA27.add(ktnb06ModelListSend.get(i).getKT_KHOA());
                }
                hmData.put("COT_1", DefineFun.converArrayList2String(strKT_STT_HT1));
                hmData.put("COT_2", DefineFun.converArrayList2String(strKT_DT2));
                hmData.put("COT_3", DefineFun.converArrayList2String(strKT_TDDN_SV3));
                hmData.put("COT_4", DefineFun.converArrayList2String(strKT_TDDN_ST_G4));
                hmData.put("COT_5", DefineFun.converArrayList2String(strKT_TDDN_ST_L5));
                hmData.put("COT_6", DefineFun.converArrayList2String(strKT_TDDN_ST_TK6));
                hmData.put("COT_7", DefineFun.converArrayList2String(strKT_PHTK_SV7));
                hmData.put("COT_8", DefineFun.converArrayList2String(strKT_PHTK_ST_G8));
                hmData.put("COT_9", DefineFun.converArrayList2String(strKT_PHTK_ST_L9));
                hmData.put("COT_10", DefineFun.converArrayList2String(strKT_PHTK_ST_TK10));
                hmData.put("COT_11", DefineFun.converArrayList2String(strKT_DTH_SV11));
                hmData.put("COT_12", DefineFun.converArrayList2String(strKT_DTH_ST_G12));
                hmData.put("COT_13", DefineFun.converArrayList2String(strKT_DTH_ST_L13));
                hmData.put("COT_14", DefineFun.converArrayList2String(strKT_DTH_ST_TK14));
                hmData.put("COT_15", DefineFun.converArrayList2String(strKT_TDCK_SV15));
                hmData.put("COT_16", DefineFun.converArrayList2String(strKT_TDCK_ST_G16));
                hmData.put("COT_17", DefineFun.converArrayList2String(strKT_TDCK_ST_L17));
                hmData.put("COT_18", DefineFun.converArrayList2String(strKT_TDCK_ST_TK18));

                hmData.put("COT_19", DefineFun.converArrayList2String(strKT_DN19));
                hmData.put("COT_20", DefineFun.converArrayList2String(strKT_CO_DINH20));
                hmData.put("COT_21", DefineFun.converArrayList2String(strKT_THEM21));
                hmData.put("COT_22", DefineFun.converArrayList2String(strKT_XOA22));
                hmData.put("COT_23", DefineFun.converArrayList2String(strKT_FONTWEIGHT23));
                hmData.put("COT_24", DefineFun.converArrayList2String(strKT_CAPHT24));
                hmData.put("COT_25", DefineFun.converArrayList2String(strKT_STT25));
                hmData.put("COT_26", DefineFun.converArrayList2String(strNG_CAPNHAT26));
                hmData.put("COT_27", DefineFun.converArrayList2String(strKT_KHOA27));

                //Tao file xml theo cau truc
                ProcessReportSyn clientWritexml = new ProcessReportSyn();
                boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKTNB(Define.PARA_SYN_REPORT_KTNB,
                        Define.SYN_REPORT_KTNB06, reportDate, userId, sPOS, maCn, reporGrade, "BCNT", hmData, quyBc, namBc, pathSave);
                if (!bSuccess) {
                    System.err.println("Ban chua dong bo du lieu duoc ve TW");
                    return false;
                } else {
                    daoKtnb01.lock_ktnb(Define.SYN_REPORT_KTNB06, userName, sPOS, quyBc, namBc, reporGrade, reportDate);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Loi tao file vao gui TW " + e.getMessage());
            return false;
        }

        //========== END DONG BO DU LIEU =======================================
        return true;
    }

//    public String save_data_ktnb06(){
//        daoKtnb06.save_ktnb06(posCD,maCn,quyBc,namBc,userId,KT_STT_HT,KT_DT,KT_TDDN_SV,KT_TDDN_ST_G,KT_TDDN_ST_L,KT_TDDN_ST_TK,KT_PHTK_SV,KT_PHTK_ST_G,KT_PHTK_ST_L,KT_PHTK_ST_TK,KT_DTH_SV,KT_DTH_ST_G,KT_DTH_ST_L,KT_DTH_ST_TK,KT_TDCK_SV,KT_TDCK_ST_G,KT_TDCK_ST_L,KT_TDCK_ST_TK,KT_DN,KT_CO_DINH,KT_THEM,KT_XOA,KT_FONTWEIGHT,KT_CAPHT,KT_STT,NG_CAPNHAT);
//        
//        //DONG BO DU LIEU LEN TW
//        HttpSession session = request.getSession();
//        String reporGrade = session.getAttribute("reportGrade").toString();
//        if (!reporGrade.equals("3")) {
//            //Lay duong dan va ten xml se ghi ra
//            String pathSave = !request.getRealPath("/").endsWith("/")
//                    ? request.getRealPath("/") + "/" + Define.M_REPORT_XML
//                    : request.getRealPath("/") + Define.M_REPORT_XML;
//                    pathSave+= posCD + "_BCNT_KTNB_"+"06"+".xml";    //Thay ma bao cao
//            String reportDate =  new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
//            
//            HashMap<String, String> hmData = new HashMap<String, String>();
//            
//            hmData.put("COT_1",DefineFun.converArrayList2String(KT_STT_HT));
//            hmData.put("COT_2",DefineFun.converArrayList2String(KT_DT));
//            hmData.put("COT_3",DefineFun.converArrayList2String(KT_TDDN_SV));
//            hmData.put("COT_4",DefineFun.converArrayList2String(KT_TDDN_ST_G));
//            hmData.put("COT_5",DefineFun.converArrayList2String(KT_TDDN_ST_L));                
//            hmData.put("COT_6",DefineFun.converArrayList2String(KT_TDDN_ST_TK));                
//            hmData.put("COT_7",DefineFun.converArrayList2String(KT_PHTK_SV));
//            hmData.put("COT_8",DefineFun.converArrayList2String(KT_PHTK_ST_G));
//            hmData.put("COT_9",DefineFun.converArrayList2String(KT_PHTK_ST_L));
//            hmData.put("COT_10",DefineFun.converArrayList2String(KT_PHTK_ST_TK));
//            hmData.put("COT_11",DefineFun.converArrayList2String(KT_DTH_SV));
//            hmData.put("COT_12",DefineFun.converArrayList2String(KT_DTH_ST_G));
//            hmData.put("COT_13",DefineFun.converArrayList2String(KT_DTH_ST_L));
//            hmData.put("COT_14",DefineFun.converArrayList2String(KT_DTH_ST_TK));
//            hmData.put("COT_15",DefineFun.converArrayList2String(KT_TDCK_SV));
//            hmData.put("COT_16",DefineFun.converArrayList2String(KT_TDCK_ST_G));
//            hmData.put("COT_17",DefineFun.converArrayList2String(KT_TDCK_ST_L));
//            hmData.put("COT_18",DefineFun.converArrayList2String(KT_TDCK_ST_TK));
//            
//            hmData.put("COT_19",DefineFun.converArrayList2String(KT_DN));
//            hmData.put("COT_20",DefineFun.converArrayList2String(KT_CO_DINH));
//            hmData.put("COT_21",DefineFun.converArrayList2String(KT_THEM));
//            hmData.put("COT_22",DefineFun.converArrayList2String(KT_XOA));
//            hmData.put("COT_23",DefineFun.converArrayList2String(KT_FONTWEIGHT));
//            hmData.put("COT_24",DefineFun.converArrayList2String(KT_CAPHT));
//            hmData.put("COT_25",DefineFun.converArrayList2String(KT_STT));
//            hmData.put("COT_26",DefineFun.converArrayList2String(NG_CAPNHAT));
//
//            
//            //Tao file xml theo cau truc
//            ProcessReportSyn clientWritexml = new ProcessReportSyn();
//            boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKTNB(Define.PARA_SYN_REPORT_KTNB,
//                    Define.SYN_REPORT_KTNB06, reportDate, userId, posCD, maCn, reporGrade, "BCNT", hmData,quyBc,namBc, pathSave);
//            if (!bSuccess) {
//                System.err.println("Ban chua dong bo du lieu duoc ve TW");
//            }
//        }
//        //========== END DONG BO DU LIEU =======================================
//        
//        
//        return "success";
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

    public List<Ktnb06Model> getKtnb06ModelList() {
        return ktnb06ModelList;
    }

    public void setKtnb06ModelList(List<Ktnb06Model> ktnb06ModelList) {
        this.ktnb06ModelList = ktnb06ModelList;
    }

    public List<String> getKT_STT_HT() {
        return KT_STT_HT;
    }

    public void setKT_STT_HT(List<String> KT_STT_HT) {
        this.KT_STT_HT = KT_STT_HT;
    }

    public List<String> getKT_DT() {
        return KT_DT;
    }

    public void setKT_DT(List<String> KT_DT) {
        this.KT_DT = KT_DT;
    }

    public List<String> getKT_TDDN_SV() {
        return KT_TDDN_SV;
    }

    public void setKT_TDDN_SV(List<String> KT_TDDN_SV) {
        this.KT_TDDN_SV = KT_TDDN_SV;
    }

    public List<String> getKT_TDDN_ST_G() {
        return KT_TDDN_ST_G;
    }

    public void setKT_TDDN_ST_G(List<String> KT_TDDN_ST_G) {
        this.KT_TDDN_ST_G = KT_TDDN_ST_G;
    }

    public List<String> getKT_TDDN_ST_L() {
        return KT_TDDN_ST_L;
    }

    public void setKT_TDDN_ST_L(List<String> KT_TDDN_ST_L) {
        this.KT_TDDN_ST_L = KT_TDDN_ST_L;
    }

    public List<String> getKT_PHTK_SV() {
        return KT_PHTK_SV;
    }

    public void setKT_PHTK_SV(List<String> KT_PHTK_SV) {
        this.KT_PHTK_SV = KT_PHTK_SV;
    }

    public List<String> getKT_PHTK_ST_G() {
        return KT_PHTK_ST_G;
    }

    public void setKT_PHTK_ST_G(List<String> KT_PHTK_ST_G) {
        this.KT_PHTK_ST_G = KT_PHTK_ST_G;
    }

    public List<String> getKT_PHTK_ST_L() {
        return KT_PHTK_ST_L;
    }

    public void setKT_PHTK_ST_L(List<String> KT_PHTK_ST_L) {
        this.KT_PHTK_ST_L = KT_PHTK_ST_L;
    }

    public List<String> getKT_DTH_SV() {
        return KT_DTH_SV;
    }

    public void setKT_DTH_SV(List<String> KT_DTH_SV) {
        this.KT_DTH_SV = KT_DTH_SV;
    }

    public List<String> getKT_DTH_ST_G() {
        return KT_DTH_ST_G;
    }

    public void setKT_DTH_ST_G(List<String> KT_DTH_ST_G) {
        this.KT_DTH_ST_G = KT_DTH_ST_G;
    }

    public List<String> getKT_DTH_ST_L() {
        return KT_DTH_ST_L;
    }

    public void setKT_DTH_ST_L(List<String> KT_DTH_ST_L) {
        this.KT_DTH_ST_L = KT_DTH_ST_L;
    }

    public List<String> getKT_TDCK_SV() {
        return KT_TDCK_SV;
    }

    public void setKT_TDCK_SV(List<String> KT_TDCK_SV) {
        this.KT_TDCK_SV = KT_TDCK_SV;
    }

    public List<String> getKT_TDCK_ST_G() {
        return KT_TDCK_ST_G;
    }

    public void setKT_TDCK_ST_G(List<String> KT_TDCK_ST_G) {
        this.KT_TDCK_ST_G = KT_TDCK_ST_G;
    }

    public List<String> getKT_TDCK_ST_L() {
        return KT_TDCK_ST_L;
    }

    public void setKT_TDCK_ST_L(List<String> KT_TDCK_ST_L) {
        this.KT_TDCK_ST_L = KT_TDCK_ST_L;
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

    public List<String> getKT_GHICHU() {
        return KT_GHICHU;
    }

    public void setKT_GHICHU(List<String> KT_GHICHU) {
        this.KT_GHICHU = KT_GHICHU;
    }

    public List<String> getKT_TDDN_ST_TK() {
        return KT_TDDN_ST_TK;
    }

    public void setKT_TDDN_ST_TK(List<String> KT_TDDN_ST_TK) {
        this.KT_TDDN_ST_TK = KT_TDDN_ST_TK;
    }

    public List<String> getKT_PHTK_ST_TK() {
        return KT_PHTK_ST_TK;
    }

    public void setKT_PHTK_ST_TK(List<String> KT_PHTK_ST_TK) {
        this.KT_PHTK_ST_TK = KT_PHTK_ST_TK;
    }

    public List<String> getKT_DTH_ST_TK() {
        return KT_DTH_ST_TK;
    }

    public void setKT_DTH_ST_TK(List<String> KT_DTH_ST_TK) {
        this.KT_DTH_ST_TK = KT_DTH_ST_TK;
    }

    public List<String> getKT_TDCK_ST_TK() {
        return KT_TDCK_ST_TK;
    }

    public void setKT_TDCK_ST_TK(List<String> KT_TDCK_ST_TK) {
        this.KT_TDCK_ST_TK = KT_TDCK_ST_TK;
    }
//</editor-fold>

}
