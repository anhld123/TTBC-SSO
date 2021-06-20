package vbsp.ims.action.ktktnb;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.BuildPosTreeDao;
import vbsp.ims.dao.ktnb.DaoKtnb09;
import vbsp.ims.dao.ktnb.ListKTNBDA;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.Ktnb04Model;
import vbsp.ims.model.ktnb.Ktnb09Model;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.syn.ProcessReportSyn;

/**
 *
 * @author CuongBM0211
 */
public class Ktnb09ActionSupport extends ActionSupport implements ServletRequestAware {
    private DaoKtnb09 daoKtnb09 = new DaoKtnb09();
    private ListKTNBDA listKTNBDA = new ListKTNBDA();
    private List<Ktnb09Model> ktnb09ModelList;  //Lay du lieu load len table
    
    //Cac truong dung cho luu du lieu
    private List<String> KT_DV;
    private List<String> KT_TN_TS;
    private List<String> KT_TN_TK_NN;
    private List<String> KT_TN_TK_MN;
    private List<String> KT_TN_KT_NN;
    private List<String> KT_TN_KT_MN;
    private List<String> KT_TN_DDK;
    private List<String> KT_PL_ND_KN_HC_T;
    private List<String> KT_PL_ND_KN_HC_DD;
    private List<String> KT_PL_ND_KN_HC_NTS;
    private List<String> KT_PL_ND_KN_HC_CS;
    private List<String> KT_PL_ND_KN_HC_CT;
    private List<String> KT_PL_ND_KN_TP;
    private List<String> KT_PL_ND_KN_D;
    private List<String> KT_PL_ND_TC_T;
    private List<String> KT_PL_ND_TC_HC;
    private List<String> KT_PL_ND_TC_TP;
    private List<String> KT_PL_ND_TC_TN;
    private List<String> KT_PL_ND_TC_D;
    private List<String> KT_PL_ND_TC_K;
    private List<String> KT_PL_TQ_HC;
    private List<String> KT_PL_TQ_TP;
    private List<String> KT_PL_TQ_D;
    private List<String> KT_PL_TT_CGQ;
    private List<String> KT_PL_TT_DGQ1;
    private List<String> KT_PL_TT_GDQN;
    private List<String> KT_DK;
    private List<String> KT_KQ_SVB;
    private List<String> KT_KQ_CTQ;
    private List<String> KT_KQ_SCV;
    private List<String> KT_KQ_TTQ_KN;
    private List<String> KT_KQ_TTQ_TC;
    private List<String> KT_GHICHU;
    private List<String> KT_DN;
    private List<String> KT_CO_DINH;
    private List<String> KT_THEM;
    private List<String> KT_XOA;
    private List<String> KT_FONTWEIGHT;
    private List<String> KT_CAPHT;
    private List<String> KT_STT;
    private List<String> NG_CAPNHAT;
    
    //Cac truong chua thong tin bo xung luu du lieu
    private String userId;
    private String posCD;
    private String quyBc;
    private String namBc;
    private String maCn;
    
    private TreeNode nodes;
    private int reportGrade;
    private String userName;
    private String selectedPos;

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
    private TreeNode searchNodes;
    
    private HttpServletRequest request = null;
    
    public Ktnb09ActionSupport() {
    }
    
    public String get_data_ktnb09(){
        getInfo();
        ktnb09ModelList = daoKtnb09.get_ktnb09(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
    }
    
    public String ResetDataInput9(){
        getInfo();
        ktnb09ModelList = daoKtnb09.get_ktnb09_default(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
    }
    
    public String get_data_ktnb09_auth() throws SQLException{
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
            if(listOfId.size() == 0)
                return "";
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
    
    public String view() throws SQLException{
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
        if (listOfPos == "" || listOfPos.length() >6)
        {
            return "error-pos";
        }
        String strPosCD = listOfPos.substring(0, 6);
         System.err.print(strPosCD);
        getInfo();
        ktnb09ModelList = daoKtnb09.get_ktnb09_auth(strPosCD, Integer.parseInt(namBc), Integer.parseInt(quyBc),"N");
        return "success";
    }
    
    public String save_data_ktnb09(){
        if(daoKtnb09.save_ktnb09(posCD,maCn,quyBc,namBc,userId,KT_DV,KT_TN_TS,KT_TN_TK_NN,KT_TN_TK_MN,KT_TN_KT_NN,KT_TN_KT_MN,KT_TN_DDK,KT_PL_ND_KN_HC_T,KT_PL_ND_KN_HC_DD,KT_PL_ND_KN_HC_NTS,KT_PL_ND_KN_HC_CS,KT_PL_ND_KN_HC_CT,KT_PL_ND_KN_TP,KT_PL_ND_KN_D,KT_PL_ND_TC_T,KT_PL_ND_TC_HC,KT_PL_ND_TC_TP,KT_PL_ND_TC_TN,KT_PL_ND_TC_D,KT_PL_ND_TC_K,KT_PL_TQ_HC,KT_PL_TQ_TP,KT_PL_TQ_D,KT_PL_TT_CGQ,KT_PL_TT_DGQ1,KT_PL_TT_GDQN,KT_DK,KT_KQ_SVB,KT_KQ_CTQ,KT_KQ_SCV,KT_KQ_TTQ_KN,KT_KQ_TTQ_TC,KT_GHICHU,KT_DN,KT_CO_DINH,KT_THEM,KT_XOA,KT_FONTWEIGHT,KT_CAPHT,KT_STT,NG_CAPNHAT,""))
                return "success";
        else
            return "error";  
        
        //DONG BO DU LIEU LEN TW
//        HttpSession session = request.getSession();
//        String reporGrade = session.getAttribute("reportGrade").toString();
//        if (!reporGrade.equals("3")) {
//            //Lay duong dan va ten xml se ghi ra
//            String pathSave = !request.getRealPath("/").endsWith("/")
//                    ? request.getRealPath("/") + "/" + Define.M_REPORT_XML
//                    : request.getRealPath("/") + Define.M_REPORT_XML;
//                    pathSave+= posCD + "_BCNT_KTNB_"+"09"+".xml";    //Thay ma bao cao
//            String reportDate =  new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
//            
//            HashMap<String, String> hmData = new HashMap<String, String>();
//            
//            hmData.put("COT_1",DefineFun.converArrayList2String(KT_DV));
//            hmData.put("COT_2",DefineFun.converArrayList2String(KT_TN_TS));
//            hmData.put("COT_3",DefineFun.converArrayList2String(KT_TN_TK_NN));
//            hmData.put("COT_4",DefineFun.converArrayList2String(KT_TN_TK_MN));
//            hmData.put("COT_5",DefineFun.converArrayList2String(KT_TN_KT_NN));
//            hmData.put("COT_6",DefineFun.converArrayList2String(KT_TN_KT_MN));
//            hmData.put("COT_7",DefineFun.converArrayList2String(KT_TN_DDK));
//            hmData.put("COT_8",DefineFun.converArrayList2String(KT_PL_ND_KN_HC_T));
//            hmData.put("COT_9",DefineFun.converArrayList2String(KT_PL_ND_KN_HC_DD));
//            hmData.put("COT_10",DefineFun.converArrayList2String(KT_PL_ND_KN_HC_NTS));
//            hmData.put("COT_11",DefineFun.converArrayList2String(KT_PL_ND_KN_HC_CS));
//            hmData.put("COT_12",DefineFun.converArrayList2String(KT_PL_ND_KN_HC_CT));
//            hmData.put("COT_13",DefineFun.converArrayList2String(KT_PL_ND_KN_TP));
//            hmData.put("COT_14",DefineFun.converArrayList2String(KT_PL_ND_KN_D));
//            hmData.put("COT_15",DefineFun.converArrayList2String(KT_PL_ND_TC_T));
//            hmData.put("COT_16",DefineFun.converArrayList2String(KT_PL_ND_TC_HC));
//            hmData.put("COT_17",DefineFun.converArrayList2String(KT_PL_ND_TC_TP));
//            hmData.put("COT_18",DefineFun.converArrayList2String(KT_PL_ND_TC_TN));
//            hmData.put("COT_19",DefineFun.converArrayList2String(KT_PL_ND_TC_D));
//            hmData.put("COT_20",DefineFun.converArrayList2String(KT_PL_ND_TC_K));
//            hmData.put("COT_21",DefineFun.converArrayList2String(KT_PL_TQ_HC));
//            hmData.put("COT_22",DefineFun.converArrayList2String(KT_PL_TQ_TP));
//            hmData.put("COT_23",DefineFun.converArrayList2String(KT_PL_TQ_D));
//            hmData.put("COT_24",DefineFun.converArrayList2String(KT_PL_TT_CGQ));
//            hmData.put("COT_25",DefineFun.converArrayList2String(KT_PL_TT_DGQ1));
//            hmData.put("COT_26",DefineFun.converArrayList2String(KT_PL_TT_GDQN));
//            hmData.put("COT_27",DefineFun.converArrayList2String(KT_DK));
//            hmData.put("COT_28",DefineFun.converArrayList2String(KT_KQ_SVB));
//            hmData.put("COT_29",DefineFun.converArrayList2String(KT_KQ_CTQ));
//            hmData.put("COT_30",DefineFun.converArrayList2String(KT_KQ_SCV));
//            hmData.put("COT_31",DefineFun.converArrayList2String(KT_KQ_TTQ_KN));
//            hmData.put("COT_32",DefineFun.converArrayList2String(KT_KQ_TTQ_TC));
//            hmData.put("COT_33",DefineFun.converArrayList2String(KT_GHICHU));
//            hmData.put("COT_34",DefineFun.converArrayList2String(KT_DN));
//            hmData.put("COT_35",DefineFun.converArrayList2String(KT_CO_DINH));
//            hmData.put("COT_36",DefineFun.converArrayList2String(KT_THEM));
//            hmData.put("COT_37",DefineFun.converArrayList2String(KT_XOA));
//            hmData.put("COT_38",DefineFun.converArrayList2String(KT_FONTWEIGHT));
//            hmData.put("COT_39",DefineFun.converArrayList2String(KT_CAPHT));
//            hmData.put("COT_40",DefineFun.converArrayList2String(KT_STT));
//            hmData.put("COT_41",DefineFun.converArrayList2String(NG_CAPNHAT));
//
//            
//            //Tao file xml theo cau truc
//            ProcessReportSyn clientWritexml = new ProcessReportSyn();
//            boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKTNB(Define.PARA_SYN_REPORT_KTNB,
//                    Define.SYN_REPORT_KTNB09, reportDate, userId, posCD, maCn, reporGrade, "BCNT", hmData,quyBc,namBc, pathSave);
//            if (!bSuccess) {
//                System.err.println("Ban chua dong bo du lieu duoc ve TW");
//            }
//        }
//        //========== END DONG BO DU LIEU =======================================
//        
//        return "success";
    }
    
    public String Auth() throws SQLException{        
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
        String lsPos = listKTNBDA.getListPos(maCn);
        if (lsPos.length() != listOfPos.length())
        {
            return "error-pos";
        }
//        String listOfPos = getListOfPos();
//        if (listOfPos == "" || listOfPos.length() >6)
//        {
//            return "error-pos";
//        }
//        if(KT_STT_HT == null)
//            return "error-click";
        for (String pos_auth: listOfPos.split(",")){
         System.out.println(pos_auth);
         if(!daoKtnb09.save_ktnb09_auth(pos_auth,quyBc,namBc))
             return "error";
         if(!maCn.equals("000101") )
         {
         if(!syn_data_HO(pos_auth))       
             return "error-send";
         }
      }
        return "success";
    }
    
    public boolean syn_data_HO(String sPOS)
    {
    //DONG BO DU LIEU LEN TW
        try
        {
        HttpSession session = request.getSession();
        String reporGrade = session.getAttribute("reportGrade").toString();
        if (!reporGrade.equals("3")) {
            //Lay duong dan va ten xml se ghi ra
                String pathSave = !request.getRealPath("/").endsWith("/")
                    ? request.getRealPath("/") + "/" + Define.M_REPORT_XML
                    : request.getRealPath("/") + Define.M_REPORT_XML;
                    pathSave+= sPOS + "_BCNT_KTNB_"+"09"+".xml";    //Thay ma bao cao
            String reportDate =  new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
            
            HashMap<String, String> hmData = new HashMap<String, String>();
            
            List<Ktnb09Model> ktnb09ModelListSend = null;
            
            List<String> strKT_DV1 = new ArrayList<String>();
            List<String> strKT_TN_TS2 = new ArrayList<String>();
            List<String> strKT_TN_TK_NN3 = new ArrayList<String>();
            List<String> strKT_TN_TK_MN4 = new ArrayList<String>();
            List<String> strKT_TN_KT_NN5 = new ArrayList<String>();
            List<String> strKT_TN_KT_MN6 = new ArrayList<String>();
            List<String> strKT_TN_DDK7 = new ArrayList<String>();
            List<String> strKT_PL_ND_KN_HC_T8 = new ArrayList<String>();
            List<String> strKT_PL_ND_KN_HC_DD9 = new ArrayList<String>();
            List<String> strKT_PL_ND_KN_HC_NTS10 = new ArrayList<String>();
            List<String> strKT_PL_ND_KN_HC_CS11 = new ArrayList<String>();
            List<String> strKT_PL_ND_KN_HC_CT12 = new ArrayList<String>();
            List<String> strKT_PL_ND_KN_TP13 = new ArrayList<String>();
            List<String> strKT_PL_ND_KN_D14 = new ArrayList<String>();
            List<String> strKT_PL_ND_TC_T15 = new ArrayList<String>();
            List<String> strKT_PL_ND_TC_HC16 = new ArrayList<String>();
            List<String> strKT_PL_ND_TC_TP17 = new ArrayList<String>();
            List<String> strKT_PL_ND_TC_TN18 = new ArrayList<String>();
            List<String> strKT_PL_ND_TC_D19 = new ArrayList<String>();
            List<String> strKT_PL_ND_TC_K20 = new ArrayList<String>();
            List<String> strKT_PL_TQ_HC21 = new ArrayList<String>();
            List<String> strKT_PL_TQ_TP22 = new ArrayList<String>();
            List<String> strKT_PL_TQ_D23 = new ArrayList<String>();
            List<String> strKT_PL_TT_CGQ24 = new ArrayList<String>();
            List<String> strKT_PL_TT_DGQ125 = new ArrayList<String>();
            List<String> strKT_PL_TT_GDQN26 = new ArrayList<String>();
            List<String> strKT_DK27 = new ArrayList<String>();
            List<String> strKT_KQ_SVB28 = new ArrayList<String>();
            List<String> strKT_KQ_CTQ29 = new ArrayList<String>();
            List<String> strKT_KQ_SCV30 = new ArrayList<String>();
            List<String> strKT_KQ_TTQ_KN31 = new ArrayList<String>();
            List<String> strKT_KQ_TTQ_TC32 = new ArrayList<String>();
            List<String> strKT_GHICHU33 = new ArrayList<String>();
            List<String> strKT_DN34 = new ArrayList<String>();
            List<String> strKT_CO_DINH35 = new ArrayList<String>();
            List<String> strKT_THEM36 = new ArrayList<String>();
            List<String> strKT_XOA37 = new ArrayList<String>();
            List<String> strKT_FONTWEIGHT38 = new ArrayList<String>();
            List<String> strKT_CAPHT39 = new ArrayList<String>();
            List<String> strKT_STT40 = new ArrayList<String>();
            List<String> strNG_CAPNHAT41 = new ArrayList<String>();
            
            ktnb09ModelListSend = daoKtnb09.get_ktnb09_auth(sPOS, Integer.parseInt(namBc), Integer.parseInt(quyBc),"Y");
            if(ktnb09ModelListSend.size()<=0)
                return false;
            for (int i = 0; i < ktnb09ModelListSend.size(); i++) {
                strKT_DV1.add(ktnb09ModelListSend.get(i).getKT_DV());
                strKT_TN_TS2.add(Double.toString(ktnb09ModelListSend.get(i).getKT_TN_TS()));
                strKT_TN_TK_NN3.add(Double.toString(ktnb09ModelListSend.get(i).getKT_TN_TK_NN()));
                strKT_TN_TK_MN4.add(Double.toString(ktnb09ModelListSend.get(i).getKT_TN_TK_MN()));
                strKT_TN_KT_NN5.add(Double.toString(ktnb09ModelListSend.get(i).getKT_TN_KT_NN()));
                strKT_TN_KT_MN6.add(Double.toString(ktnb09ModelListSend.get(i).getKT_TN_KT_MN()));
                strKT_TN_DDK7.add(Double.toString(ktnb09ModelListSend.get(i).getKT_TN_DDK()));
                strKT_PL_ND_KN_HC_T8.add(Double.toString(ktnb09ModelListSend.get(i).getKT_PL_ND_KN_HC_T()));
                strKT_PL_ND_KN_HC_DD9.add(Double.toString(ktnb09ModelListSend.get(i).getKT_PL_ND_KN_HC_DD()));
                strKT_PL_ND_KN_HC_NTS10.add(Double.toString(ktnb09ModelListSend.get(i).getKT_PL_ND_KN_HC_NTS()));
                strKT_PL_ND_KN_HC_CS11.add(Double.toString(ktnb09ModelListSend.get(i).getKT_PL_ND_KN_HC_CS()));
                strKT_PL_ND_KN_HC_CT12.add(Double.toString(ktnb09ModelListSend.get(i).getKT_PL_ND_KN_HC_CT()));
                strKT_PL_ND_KN_TP13.add(Double.toString(ktnb09ModelListSend.get(i).getKT_PL_ND_KN_TP()));
                strKT_PL_ND_KN_D14.add(Double.toString(ktnb09ModelListSend.get(i).getKT_PL_ND_KN_D()));
                strKT_PL_ND_TC_T15.add(Double.toString(ktnb09ModelListSend.get(i).getKT_PL_ND_TC_T()));
                strKT_PL_ND_TC_HC16.add(Double.toString(ktnb09ModelListSend.get(i).getKT_PL_ND_TC_HC()));
                strKT_PL_ND_TC_TP17.add(Double.toString(ktnb09ModelListSend.get(i).getKT_PL_ND_TC_TP()));
                strKT_PL_ND_TC_TN18.add(Double.toString(ktnb09ModelListSend.get(i).getKT_PL_ND_TC_TN()));
                strKT_PL_ND_TC_D19.add(Double.toString(ktnb09ModelListSend.get(i).getKT_PL_ND_TC_D()));
                strKT_PL_ND_TC_K20.add(Double.toString(ktnb09ModelListSend.get(i).getKT_PL_ND_TC_K()));
                strKT_PL_TQ_HC21.add(Double.toString(ktnb09ModelListSend.get(i).getKT_PL_TQ_HC()));
                strKT_PL_TQ_TP22.add(Double.toString(ktnb09ModelListSend.get(i).getKT_PL_TQ_TP()));
                strKT_PL_TQ_D23.add(Double.toString(ktnb09ModelListSend.get(i).getKT_PL_TQ_D()));
                strKT_PL_TT_CGQ24.add(Double.toString(ktnb09ModelListSend.get(i).getKT_PL_TT_CGQ()));
                strKT_PL_TT_DGQ125.add(Double.toString(ktnb09ModelListSend.get(i).getKT_PL_TT_DGQ1()));
                strKT_PL_TT_GDQN26.add(Double.toString(ktnb09ModelListSend.get(i).getKT_PL_TT_GDQN()));
                strKT_DK27.add(Double.toString(ktnb09ModelListSend.get(i).getKT_DK()));
                strKT_KQ_SVB28.add(Double.toString(ktnb09ModelListSend.get(i).getKT_KQ_SVB()));
                strKT_KQ_CTQ29.add(Double.toString(ktnb09ModelListSend.get(i).getKT_KQ_CTQ()));
                strKT_KQ_SCV30.add(Double.toString(ktnb09ModelListSend.get(i).getKT_KQ_SCV()));
                strKT_KQ_TTQ_KN31.add(Double.toString(ktnb09ModelListSend.get(i).getKT_KQ_TTQ_KN()));
                strKT_KQ_TTQ_TC32.add(Double.toString(ktnb09ModelListSend.get(i).getKT_KQ_TTQ_TC()));
                strKT_GHICHU33.add(ktnb09ModelListSend.get(i).getKT_GHICHU());
                strKT_DN34.add(ktnb09ModelListSend.get(i).getKT_DN());
                strKT_CO_DINH35.add(ktnb09ModelListSend.get(i).getKT_CO_DINH());
                strKT_THEM36.add(ktnb09ModelListSend.get(i).getKT_THEM());
                strKT_XOA37.add(ktnb09ModelListSend.get(i).getKT_XOA());
                strKT_FONTWEIGHT38.add(ktnb09ModelListSend.get(i).getKT_FONTWEIGHT());
                strKT_CAPHT39.add(Double.toString(ktnb09ModelListSend.get(i).getKT_CAPHT()));
                strKT_STT40.add(Double.toString(ktnb09ModelListSend.get(i).getKT_STT()));
                strNG_CAPNHAT41.add(ktnb09ModelListSend.get(i).getNG_CAPNHAT());
            }
            
            hmData.put("COT_1",DefineFun.converArrayList2String(strKT_DV1));
            hmData.put("COT_2",DefineFun.converArrayList2String(strKT_TN_TS2));
            hmData.put("COT_3",DefineFun.converArrayList2String(strKT_TN_TK_NN3));
            hmData.put("COT_4",DefineFun.converArrayList2String(strKT_TN_TK_MN4));
            hmData.put("COT_5",DefineFun.converArrayList2String(strKT_TN_KT_NN5));
            hmData.put("COT_6",DefineFun.converArrayList2String(strKT_TN_KT_MN6));
            hmData.put("COT_7",DefineFun.converArrayList2String(strKT_TN_DDK7));
            hmData.put("COT_8",DefineFun.converArrayList2String(strKT_PL_ND_KN_HC_T8));
            hmData.put("COT_9",DefineFun.converArrayList2String(strKT_PL_ND_KN_HC_DD9));
            hmData.put("COT_10",DefineFun.converArrayList2String(strKT_PL_ND_KN_HC_NTS10));
            hmData.put("COT_11",DefineFun.converArrayList2String(strKT_PL_ND_KN_HC_CS11));
            hmData.put("COT_12",DefineFun.converArrayList2String(strKT_PL_ND_KN_HC_CT12));
            hmData.put("COT_13",DefineFun.converArrayList2String(strKT_PL_ND_KN_TP13));
            hmData.put("COT_14",DefineFun.converArrayList2String(strKT_PL_ND_KN_D14));
            hmData.put("COT_15",DefineFun.converArrayList2String(strKT_PL_ND_TC_T15));
            hmData.put("COT_16",DefineFun.converArrayList2String(strKT_PL_ND_TC_HC16));
            hmData.put("COT_17",DefineFun.converArrayList2String(strKT_PL_ND_TC_TP17));
            hmData.put("COT_18",DefineFun.converArrayList2String(strKT_PL_ND_TC_TN18));
            hmData.put("COT_19",DefineFun.converArrayList2String(strKT_PL_ND_TC_D19));
            hmData.put("COT_20",DefineFun.converArrayList2String(strKT_PL_ND_TC_K20));
            hmData.put("COT_21",DefineFun.converArrayList2String(strKT_PL_TQ_HC21));
            hmData.put("COT_22",DefineFun.converArrayList2String(strKT_PL_TQ_TP22));
            hmData.put("COT_23",DefineFun.converArrayList2String(strKT_PL_TQ_D23));
            hmData.put("COT_24",DefineFun.converArrayList2String(strKT_PL_TT_CGQ24));
            hmData.put("COT_25",DefineFun.converArrayList2String(strKT_PL_TT_DGQ125));
            hmData.put("COT_26",DefineFun.converArrayList2String(strKT_PL_TT_GDQN26));
            hmData.put("COT_27",DefineFun.converArrayList2String(strKT_DK27));
            hmData.put("COT_28",DefineFun.converArrayList2String(strKT_KQ_SVB28));
            hmData.put("COT_29",DefineFun.converArrayList2String(strKT_KQ_CTQ29));
            hmData.put("COT_30",DefineFun.converArrayList2String(strKT_KQ_SCV30));
            hmData.put("COT_31",DefineFun.converArrayList2String(strKT_KQ_TTQ_KN31));
            hmData.put("COT_32",DefineFun.converArrayList2String(strKT_KQ_TTQ_TC32));
            hmData.put("COT_33",DefineFun.converArrayList2String(strKT_GHICHU33));
            hmData.put("COT_34",DefineFun.converArrayList2String(strKT_DN34));
            hmData.put("COT_35",DefineFun.converArrayList2String(strKT_CO_DINH35));
            hmData.put("COT_36",DefineFun.converArrayList2String(strKT_THEM36));
            hmData.put("COT_37",DefineFun.converArrayList2String(strKT_XOA37));
            hmData.put("COT_38",DefineFun.converArrayList2String(strKT_FONTWEIGHT38));
            hmData.put("COT_39",DefineFun.converArrayList2String(strKT_CAPHT39));
            hmData.put("COT_40",DefineFun.converArrayList2String(strKT_STT40));
            hmData.put("COT_41",DefineFun.converArrayList2String(strNG_CAPNHAT41));
            
            //Tao file xml theo cau truc
            ProcessReportSyn clientWritexml = new ProcessReportSyn();
            boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKTNB(Define.PARA_SYN_REPORT_KTNB,
                    Define.SYN_REPORT_KTNB09, reportDate, userId, sPOS, maCn, reporGrade, "BCNT", hmData,quyBc,namBc, pathSave);
            if (!bSuccess) {
                System.err.println("Ban chua dong bo du lieu duoc ve TW");
                return false;
            }
        }
      } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Loi tao file vao gui TW " + e.getMessage());
            return false;
        }

        //========== END DONG BO DU LIEU =======================================
        return true;
        }
    
    public void getInfo(){
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
     
     public List<Ktnb09Model> getKtnb09ModelList() {
         return ktnb09ModelList;
     }
     
     public void setKtnb09ModelList(List<Ktnb09Model> ktnb09ModelList) {
         this.ktnb09ModelList = ktnb09ModelList;
     }
     
     public List<String> getKT_DV() {
         return KT_DV;
     }
     
     public void setKT_DV(List<String> KT_DV) {
         this.KT_DV = KT_DV;
     }
     
     public List<String> getKT_TN_TS() {
         return KT_TN_TS;
     }
     
     public void setKT_TN_TS(List<String> KT_TN_TS) {
         this.KT_TN_TS = KT_TN_TS;
     }
     
     public List<String> getKT_TN_TK_NN() {
         return KT_TN_TK_NN;
     }
     
     public void setKT_TN_TK_NN(List<String> KT_TN_TK_NN) {
         this.KT_TN_TK_NN = KT_TN_TK_NN;
     }
     
     public List<String> getKT_TN_TK_MN() {
         return KT_TN_TK_MN;
     }
     
     public void setKT_TN_TK_MN(List<String> KT_TN_TK_MN) {
         this.KT_TN_TK_MN = KT_TN_TK_MN;
     }
     
     public List<String> getKT_TN_KT_NN() {
         return KT_TN_KT_NN;
     }
     
     public void setKT_TN_KT_NN(List<String> KT_TN_KT_NN) {
         this.KT_TN_KT_NN = KT_TN_KT_NN;
     }
     
     public List<String> getKT_TN_KT_MN() {
         return KT_TN_KT_MN;
     }
     
     public void setKT_TN_KT_MN(List<String> KT_TN_KT_MN) {
         this.KT_TN_KT_MN = KT_TN_KT_MN;
     }
     
     public List<String> getKT_TN_DDK() {
         return KT_TN_DDK;
     }
     
     public void setKT_TN_DDK(List<String> KT_TN_DDK) {
         this.KT_TN_DDK = KT_TN_DDK;
     }
     
     public List<String> getKT_PL_ND_KN_HC_T() {
         return KT_PL_ND_KN_HC_T;
     }
     
     public void setKT_PL_ND_KN_HC_T(List<String> KT_PL_ND_KN_HC_T) {
         this.KT_PL_ND_KN_HC_T = KT_PL_ND_KN_HC_T;
     }
     
     public List<String> getKT_PL_ND_KN_HC_DD() {
         return KT_PL_ND_KN_HC_DD;
     }
     
     public void setKT_PL_ND_KN_HC_DD(List<String> KT_PL_ND_KN_HC_DD) {
         this.KT_PL_ND_KN_HC_DD = KT_PL_ND_KN_HC_DD;
     }
     
     public List<String> getKT_PL_ND_KN_HC_NTS() {
         return KT_PL_ND_KN_HC_NTS;
     }
     
     public void setKT_PL_ND_KN_HC_NTS(List<String> KT_PL_ND_KN_HC_NTS) {
         this.KT_PL_ND_KN_HC_NTS = KT_PL_ND_KN_HC_NTS;
     }
     
     public List<String> getKT_PL_ND_KN_HC_CS() {
         return KT_PL_ND_KN_HC_CS;
     }
     
     public void setKT_PL_ND_KN_HC_CS(List<String> KT_PL_ND_KN_HC_CS) {
         this.KT_PL_ND_KN_HC_CS = KT_PL_ND_KN_HC_CS;
     }
     
     public List<String> getKT_PL_ND_KN_HC_CT() {
         return KT_PL_ND_KN_HC_CT;
     }
     
     public void setKT_PL_ND_KN_HC_CT(List<String> KT_PL_ND_KN_HC_CT) {
         this.KT_PL_ND_KN_HC_CT = KT_PL_ND_KN_HC_CT;
     }
     
     public List<String> getKT_PL_ND_KN_TP() {
         return KT_PL_ND_KN_TP;
     }
     
     public void setKT_PL_ND_KN_TP(List<String> KT_PL_ND_KN_TP) {
         this.KT_PL_ND_KN_TP = KT_PL_ND_KN_TP;
     }
     
     public List<String> getKT_PL_ND_KN_D() {
         return KT_PL_ND_KN_D;
     }
     
     public void setKT_PL_ND_KN_D(List<String> KT_PL_ND_KN_D) {
         this.KT_PL_ND_KN_D = KT_PL_ND_KN_D;
     }
     
     public List<String> getKT_PL_ND_TC_T() {
         return KT_PL_ND_TC_T;
     }
     
     public void setKT_PL_ND_TC_T(List<String> KT_PL_ND_TC_T) {
         this.KT_PL_ND_TC_T = KT_PL_ND_TC_T;
     }
     
     public List<String> getKT_PL_ND_TC_HC() {
         return KT_PL_ND_TC_HC;
     }
     
     public void setKT_PL_ND_TC_HC(List<String> KT_PL_ND_TC_HC) {
         this.KT_PL_ND_TC_HC = KT_PL_ND_TC_HC;
     }
     
     public List<String> getKT_PL_ND_TC_TP() {
         return KT_PL_ND_TC_TP;
     }
     
     public void setKT_PL_ND_TC_TP(List<String> KT_PL_ND_TC_TP) {
         this.KT_PL_ND_TC_TP = KT_PL_ND_TC_TP;
     }
     
     public List<String> getKT_PL_ND_TC_TN() {
         return KT_PL_ND_TC_TN;
     }
     
     public void setKT_PL_ND_TC_TN(List<String> KT_PL_ND_TC_TN) {
         this.KT_PL_ND_TC_TN = KT_PL_ND_TC_TN;
     }
     
     public List<String> getKT_PL_ND_TC_D() {
         return KT_PL_ND_TC_D;
     }
     
     public void setKT_PL_ND_TC_D(List<String> KT_PL_ND_TC_D) {
         this.KT_PL_ND_TC_D = KT_PL_ND_TC_D;
     }
     
     public List<String> getKT_PL_ND_TC_K() {
         return KT_PL_ND_TC_K;
     }
     
     public void setKT_PL_ND_TC_K(List<String> KT_PL_ND_TC_K) {
         this.KT_PL_ND_TC_K = KT_PL_ND_TC_K;
     }
     
     public List<String> getKT_PL_TQ_HC() {
         return KT_PL_TQ_HC;
     }
     
     public void setKT_PL_TQ_HC(List<String> KT_PL_TQ_HC) {
         this.KT_PL_TQ_HC = KT_PL_TQ_HC;
     }
     
     public List<String> getKT_PL_TQ_TP() {
         return KT_PL_TQ_TP;
     }
     
     public void setKT_PL_TQ_TP(List<String> KT_PL_TQ_TP) {
         this.KT_PL_TQ_TP = KT_PL_TQ_TP;
     }
     
     public List<String> getKT_PL_TQ_D() {
         return KT_PL_TQ_D;
     }
     
     public void setKT_PL_TQ_D(List<String> KT_PL_TQ_D) {
         this.KT_PL_TQ_D = KT_PL_TQ_D;
     }
     
     public List<String> getKT_PL_TT_CGQ() {
         return KT_PL_TT_CGQ;
     }
     
     public void setKT_PL_TT_CGQ(List<String> KT_PL_TT_CGQ) {
         this.KT_PL_TT_CGQ = KT_PL_TT_CGQ;
     }
     
     public List<String> getKT_PL_TT_DGQ1() {
         return KT_PL_TT_DGQ1;
     }
     
     public void setKT_PL_TT_DGQ1(List<String> KT_PL_TT_DGQ1) {
         this.KT_PL_TT_DGQ1 = KT_PL_TT_DGQ1;
     }
     
     public List<String> getKT_PL_TT_GDQN() {
         return KT_PL_TT_GDQN;
     }
     
     public void setKT_PL_TT_GDQN(List<String> KT_PL_TT_GDQN) {
         this.KT_PL_TT_GDQN = KT_PL_TT_GDQN;
     }
     
     public List<String> getKT_DK() {
         return KT_DK;
     }
     
     public void setKT_DK(List<String> KT_DK) {
         this.KT_DK = KT_DK;
     }
     
     public List<String> getKT_KQ_SVB() {
         return KT_KQ_SVB;
     }
     
     public void setKT_KQ_SVB(List<String> KT_KQ_SVB) {
         this.KT_KQ_SVB = KT_KQ_SVB;
     }
     
     public List<String> getKT_KQ_CTQ() {
         return KT_KQ_CTQ;
     }
     
     public void setKT_KQ_CTQ(List<String> KT_KQ_CTQ) {
         this.KT_KQ_CTQ = KT_KQ_CTQ;
     }
     
     public List<String> getKT_KQ_SCV() {
         return KT_KQ_SCV;
     }
     
     public void setKT_KQ_SCV(List<String> KT_KQ_SCV) {
         this.KT_KQ_SCV = KT_KQ_SCV;
     }
     
     public List<String> getKT_KQ_TTQ_KN() {
         return KT_KQ_TTQ_KN;
     }
     
     public void setKT_KQ_TTQ_KN(List<String> KT_KQ_TTQ_KN) {
         this.KT_KQ_TTQ_KN = KT_KQ_TTQ_KN;
     }
     
     public List<String> getKT_KQ_TTQ_TC() {
         return KT_KQ_TTQ_TC;
     }
     
     public void setKT_KQ_TTQ_TC(List<String> KT_KQ_TTQ_TC) {
         this.KT_KQ_TTQ_TC = KT_KQ_TTQ_TC;
     }
     
     public List<String> getKT_GHICHU() {
         return KT_GHICHU;
     }
     
     public void setKT_GHICHU(List<String> KT_GHICHU) {
         this.KT_GHICHU = KT_GHICHU;
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
     
     
//</editor-fold>
    
}
