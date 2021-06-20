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
import vbsp.ims.dao.ktnb.DaoKtnb08;
import vbsp.ims.dao.ktnb.ListKTNBDA;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.Ktnb04Model;
import vbsp.ims.model.ktnb.Ktnb08Model;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.syn.ProcessReportSyn;

/**
 *
 * @author CuongBM0211
 */
public class Ktnb08ActionSupport extends ActionSupport implements ServletRequestAware {
    private DaoKtnb08 daoKtnb08 = new DaoKtnb08();
    private ListKTNBDA listKTNBDA = new ListKTNBDA();
    private List<Ktnb08Model> ktnb08ModelList;  //Lay du lieu load len table
    
    //Cac truong dung cho luu du lieu
    private List<String> KT_DV;
    private List<String> KT_TX_L;
    private List<String> KT_TX_N;
    private List<String> KT_TX_VV_C;
    private List<String> KT_TX_VV_M;
    private List<String> KT_TX_DDN_SD;
    private List<String> KT_TX_DDN_N;
    private List<String> KT_TX_DDN_VV_C;
    private List<String> KT_TX_DDN_VV_M;
    private List<String> KT_DK_L;
    private List<String> KT_DK_N;
    private List<String> KT_DK_VV_C;
    private List<String> KT_DK_VV_M;
    private List<String> KT_DK_DDN_SD;
    private List<String> KT_DK_DDN_N;
    private List<String> KT_DK_DDN_VV_C;
    private List<String> KT_DK_DDN_VV_M;
    private List<String> KT_ND_KN_HC_TC;
    private List<String> KT_ND_KN_HC_CS;
    private List<String> KT_ND_KN_HC_NTS;
    private List<String> KT_ND_KN_HC_CD;
    private List<String> KT_ND_KN_TP;
    private List<String> KT_ND_KN_CT;
    private List<String> KT_ND_TC_HC;
    private List<String> KT_ND_TC_TP;
    private List<String> KT_ND_TC_TN;
    private List<String> KT_ND_KHAC;
    private List<String> KT_KQ_CGQ;
    private List<String> KT_KQ_GQ_CCQD;
    private List<String> KT_KQ_GQ_DCQD;
    private List<String> KT_KQ_GQ_DCBA;
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

    public TreeNode getSearchNodes() {
        return searchNodes;
    }

    public void setSearchNodes(TreeNode searchNodes) {
        this.searchNodes = searchNodes;
    }
    private String selectedPos;

    public String getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(String selectedPos) {
        this.selectedPos = selectedPos;
    }
    private TreeNode searchNodes;
    
    private HttpServletRequest request = null;
    
    public Ktnb08ActionSupport() {
    }
    
    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public String get_data_ktnb08(){
        getInfo();
        ktnb08ModelList = daoKtnb08.get_ktnb08(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
    }
    
    public String ResetDataInput8(){
        getInfo();
        ktnb08ModelList = daoKtnb08.get_ktnb08_default(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
    }
    
    public String save_data_ktnb08(){
        if(daoKtnb08.save_ktnb08(posCD,maCn,quyBc,namBc,userId,KT_DV,KT_TX_L,KT_TX_N,KT_TX_VV_C,KT_TX_VV_M,KT_TX_DDN_SD,KT_TX_DDN_N,KT_TX_DDN_VV_C,KT_TX_DDN_VV_M,KT_DK_L,KT_DK_N,KT_DK_VV_C,KT_DK_VV_M,KT_DK_DDN_SD,KT_DK_DDN_N,KT_DK_DDN_VV_C,KT_DK_DDN_VV_M,KT_ND_KN_HC_TC,KT_ND_KN_HC_CS,KT_ND_KN_HC_NTS,KT_ND_KN_HC_CD,KT_ND_KN_TP,KT_ND_KN_CT,KT_ND_TC_HC,KT_ND_TC_TP,KT_ND_TC_TN,KT_ND_KHAC,KT_KQ_CGQ,KT_KQ_GQ_CCQD,KT_KQ_GQ_DCQD,KT_KQ_GQ_DCBA,KT_GHICHU,KT_DN,KT_CO_DINH,KT_THEM,KT_XOA,KT_FONTWEIGHT,KT_CAPHT,KT_STT,NG_CAPNHAT,""))
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
//                    pathSave+= posCD + "_BCNT_KTNB_"+"08"+".xml";    //Thay ma bao cao
//            String reportDate =  new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
//            
//            HashMap<String, String> hmData = new HashMap<String, String>();
//            
//            hmData.put("COT_1",DefineFun.converArrayList2String(KT_DV));
//            hmData.put("COT_2",DefineFun.converArrayList2String(KT_TX_L));
//            hmData.put("COT_3",DefineFun.converArrayList2String(KT_TX_N));
//            hmData.put("COT_4",DefineFun.converArrayList2String(KT_TX_VV_C));
//            hmData.put("COT_5",DefineFun.converArrayList2String(KT_TX_VV_M));
//            hmData.put("COT_6",DefineFun.converArrayList2String(KT_TX_DDN_SD));
//            hmData.put("COT_7",DefineFun.converArrayList2String(KT_TX_DDN_N));
//            hmData.put("COT_8",DefineFun.converArrayList2String(KT_TX_DDN_VV_C));
//            hmData.put("COT_9",DefineFun.converArrayList2String(KT_TX_DDN_VV_M));
//            hmData.put("COT_10",DefineFun.converArrayList2String(KT_DK_L));
//            hmData.put("COT_11",DefineFun.converArrayList2String(KT_DK_N));
//            hmData.put("COT_12",DefineFun.converArrayList2String(KT_DK_VV_C));
//            hmData.put("COT_13",DefineFun.converArrayList2String(KT_DK_VV_M));
//            hmData.put("COT_14",DefineFun.converArrayList2String(KT_DK_DDN_SD));
//            hmData.put("COT_15",DefineFun.converArrayList2String(KT_DK_DDN_N));
//            hmData.put("COT_16",DefineFun.converArrayList2String(KT_DK_DDN_VV_C));
//            hmData.put("COT_17",DefineFun.converArrayList2String(KT_DK_DDN_VV_M));
//            hmData.put("COT_18",DefineFun.converArrayList2String(KT_ND_KN_HC_TC));
//            hmData.put("COT_19",DefineFun.converArrayList2String(KT_ND_KN_HC_CS));
//            hmData.put("COT_20",DefineFun.converArrayList2String(KT_ND_KN_HC_NTS));
//            hmData.put("COT_21",DefineFun.converArrayList2String(KT_ND_KN_HC_CD));
//            hmData.put("COT_22",DefineFun.converArrayList2String(KT_ND_KN_TP));
//            hmData.put("COT_23",DefineFun.converArrayList2String(KT_ND_KN_CT));
//            hmData.put("COT_24",DefineFun.converArrayList2String(KT_ND_TC_HC));
//            hmData.put("COT_25",DefineFun.converArrayList2String(KT_ND_TC_TP));
//            hmData.put("COT_26",DefineFun.converArrayList2String(KT_ND_TC_TN));
//            hmData.put("COT_27",DefineFun.converArrayList2String(KT_ND_KHAC));
//            hmData.put("COT_28",DefineFun.converArrayList2String(KT_KQ_CGQ));
//            hmData.put("COT_29",DefineFun.converArrayList2String(KT_KQ_GQ_CCQD));
//            hmData.put("COT_30",DefineFun.converArrayList2String(KT_KQ_GQ_DCQD));
//            hmData.put("COT_31",DefineFun.converArrayList2String(KT_KQ_GQ_DCBA));
//            hmData.put("COT_32",DefineFun.converArrayList2String(KT_GHICHU));
//            hmData.put("COT_33",DefineFun.converArrayList2String(KT_DN));
//            hmData.put("COT_34",DefineFun.converArrayList2String(KT_CO_DINH));
//            hmData.put("COT_35",DefineFun.converArrayList2String(KT_THEM));
//            hmData.put("COT_36",DefineFun.converArrayList2String(KT_XOA));
//            hmData.put("COT_37",DefineFun.converArrayList2String(KT_FONTWEIGHT));
//            hmData.put("COT_38",DefineFun.converArrayList2String(KT_CAPHT));
//            hmData.put("COT_39",DefineFun.converArrayList2String(KT_STT));
//            hmData.put("COT_40",DefineFun.converArrayList2String(NG_CAPNHAT));
//
//            
//            //Tao file xml theo cau truc
//            ProcessReportSyn clientWritexml = new ProcessReportSyn();
//            boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKTNB(Define.PARA_SYN_REPORT_KTNB,
//                    Define.SYN_REPORT_KTNB08, reportDate, userId, posCD, maCn, reporGrade, "BCNT", hmData,quyBc,namBc, pathSave);
//            if (!bSuccess) {
//                System.err.println("Ban chua dong bo du lieu duoc ve TW");
//            }
//        }
//        //========== END DONG BO DU LIEU =======================================
//        
//        return "success";
    }
    
    public String get_data_ktnb08_auth() throws SQLException{
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
        ktnb08ModelList = daoKtnb08.get_ktnb08_auth(strPosCD, Integer.parseInt(namBc), Integer.parseInt(quyBc),"N");
        return "success";
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
         if(!daoKtnb08.save_ktnb08_auth(pos_auth,quyBc,namBc))
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
                    pathSave+= sPOS + "_BCNT_KTNB_"+"08"+".xml";    //Thay ma bao cao
            String reportDate =  new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
            
            HashMap<String, String> hmData = new HashMap<String, String>();
            
            List<Ktnb08Model> ktnb08ModelListSend = null;
             
            List<String> strKT_DV1 = new ArrayList<String>();
            List<String> strKT_TX_L2 = new ArrayList<String>();
            List<String> strKT_TX_N3 = new ArrayList<String>();
            List<String> strKT_TX_VV_C4 = new ArrayList<String>();
            List<String> strKT_TX_VV_M5 = new ArrayList<String>();
            List<String> strKT_TX_DDN_SD6 = new ArrayList<String>();
            List<String> strKT_TX_DDN_N7 = new ArrayList<String>();
            List<String> strKT_TX_DDN_VV_C8 = new ArrayList<String>();
            List<String> strKT_TX_DDN_VV_M9 = new ArrayList<String>();
            List<String> strKT_DK_L10 = new ArrayList<String>();
            List<String> strKT_DK_N11 = new ArrayList<String>();
            List<String> strKT_DK_VV_C12 = new ArrayList<String>();
            List<String> strKT_DK_VV_M13 = new ArrayList<String>();
            List<String> strKT_DK_DDN_SD14 = new ArrayList<String>();
            List<String> strKT_DK_DDN_N15 = new ArrayList<String>();
            List<String> strKT_DK_DDN_VV_C16 = new ArrayList<String>();
            List<String> strKT_DK_DDN_VV_M17 = new ArrayList<String>();
            List<String> strKT_ND_KN_HC_TC18 = new ArrayList<String>();
            List<String> strKT_ND_KN_HC_CS19 = new ArrayList<String>();
            List<String> strKT_ND_KN_HC_NTS20 = new ArrayList<String>();
            List<String> strKT_ND_KN_HC_CD21 = new ArrayList<String>();
            List<String> strKT_ND_KN_TP22 = new ArrayList<String>();
            List<String> strKT_ND_KN_CT23 = new ArrayList<String>();
            List<String> strKT_ND_TC_HC24 = new ArrayList<String>();
            List<String> strKT_ND_TC_TP25 = new ArrayList<String>();
            List<String> strKT_ND_TC_TN26 = new ArrayList<String>();
            List<String> strKT_ND_KHAC27 = new ArrayList<String>();
            List<String> strKT_KQ_CGQ28 = new ArrayList<String>();
            List<String> strKT_KQ_GQ_CCQD29 = new ArrayList<String>();
            List<String> strKT_KQ_GQ_DCQD30 = new ArrayList<String>();
            List<String> strKT_KQ_GQ_DCBA31 = new ArrayList<String>();
            List<String> strKT_GHICHU32 = new ArrayList<String>();
            List<String> strKT_DN33 = new ArrayList<String>();
            List<String> strKT_CO_DINH34 = new ArrayList<String>();
            List<String> strKT_THEM35 = new ArrayList<String>();
            List<String> strKT_XOA36 = new ArrayList<String>();
            List<String> strKT_FONTWEIGHT37 = new ArrayList<String>();
            List<String> strKT_CAPHT38 = new ArrayList<String>();
            List<String> strKT_STT39 = new ArrayList<String>();
            List<String> strNG_CAPNHAT40 = new ArrayList<String>();
            
            ktnb08ModelListSend = daoKtnb08.get_ktnb08_auth(sPOS, Integer.parseInt(namBc), Integer.parseInt(quyBc),"Y");
            if(ktnb08ModelListSend.size()<=0)
                return false;
            for (int i = 0; i < ktnb08ModelListSend.size(); i++) {
                strKT_DV1.add(ktnb08ModelListSend.get(i).getKT_DV());
                strKT_TX_L2.add(Double.toString(ktnb08ModelListSend.get(i).getKT_TX_L()));
                strKT_TX_N3.add(Double.toString(ktnb08ModelListSend.get(i).getKT_TX_N()));
                strKT_TX_VV_C4.add(Double.toString(ktnb08ModelListSend.get(i).getKT_TX_VV_C()));
                strKT_TX_VV_M5.add(Double.toString(ktnb08ModelListSend.get(i).getKT_TX_VV_M()));
                strKT_TX_DDN_SD6.add(Double.toString(ktnb08ModelListSend.get(i).getKT_TX_DDN_SD()));
                strKT_TX_DDN_N7.add(Double.toString(ktnb08ModelListSend.get(i).getKT_TX_DDN_N()));
                strKT_TX_DDN_VV_C8.add(Double.toString(ktnb08ModelListSend.get(i).getKT_TX_DDN_VV_C()));
                strKT_TX_DDN_VV_M9.add(Double.toString(ktnb08ModelListSend.get(i).getKT_TX_DDN_VV_M()));
                strKT_DK_L10.add(Double.toString(ktnb08ModelListSend.get(i).getKT_DK_L()));
                strKT_DK_N11.add(Double.toString(ktnb08ModelListSend.get(i).getKT_DK_N()));
                strKT_DK_VV_C12.add(Double.toString(ktnb08ModelListSend.get(i).getKT_DK_VV_C()));
                strKT_DK_VV_M13.add(Double.toString(ktnb08ModelListSend.get(i).getKT_DK_VV_M()));
                strKT_DK_DDN_SD14.add(Double.toString(ktnb08ModelListSend.get(i).getKT_DK_DDN_SD()));
                strKT_DK_DDN_N15.add(Double.toString(ktnb08ModelListSend.get(i).getKT_DK_DDN_N()));
                strKT_DK_DDN_VV_C16.add(Double.toString(ktnb08ModelListSend.get(i).getKT_DK_DDN_VV_C()));
                strKT_DK_DDN_VV_M17.add(Double.toString(ktnb08ModelListSend.get(i).getKT_DK_DDN_VV_M()));
                strKT_ND_KN_HC_TC18.add(Double.toString(ktnb08ModelListSend.get(i).getKT_ND_KN_HC_TC()));
                strKT_ND_KN_HC_CS19.add(Double.toString(ktnb08ModelListSend.get(i).getKT_ND_KN_HC_CS()));
                strKT_ND_KN_HC_NTS20.add(Double.toString(ktnb08ModelListSend.get(i).getKT_ND_KN_HC_NTS()));
                strKT_ND_KN_HC_CD21.add(Double.toString(ktnb08ModelListSend.get(i).getKT_ND_KN_HC_CD()));
                strKT_ND_KN_TP22.add(Double.toString(ktnb08ModelListSend.get(i).getKT_ND_KN_TP()));
                strKT_ND_KN_CT23.add(Double.toString(ktnb08ModelListSend.get(i).getKT_ND_KN_CT()));
                strKT_ND_TC_HC24.add(Double.toString(ktnb08ModelListSend.get(i).getKT_ND_TC_HC()));
                strKT_ND_TC_TP25.add(Double.toString(ktnb08ModelListSend.get(i).getKT_ND_TC_TP()));
                strKT_ND_TC_TN26.add(Double.toString(ktnb08ModelListSend.get(i).getKT_ND_TC_TN()));
                strKT_ND_KHAC27.add(Double.toString(ktnb08ModelListSend.get(i).getKT_ND_KHAC()));
                strKT_KQ_CGQ28.add(Double.toString(ktnb08ModelListSend.get(i).getKT_KQ_CGQ()));
                strKT_KQ_GQ_CCQD29.add(Double.toString(ktnb08ModelListSend.get(i).getKT_KQ_GQ_CCQD()));
                strKT_KQ_GQ_DCQD30.add(Double.toString(ktnb08ModelListSend.get(i).getKT_KQ_GQ_DCQD()));
                strKT_KQ_GQ_DCBA31.add(Double.toString(ktnb08ModelListSend.get(i).getKT_KQ_GQ_DCBA()));
                strKT_GHICHU32.add(ktnb08ModelListSend.get(i).getKT_GHICHU());
                strKT_DN33.add(ktnb08ModelListSend.get(i).getKT_DN());
                strKT_CO_DINH34.add(ktnb08ModelListSend.get(i).getKT_CO_DINH());
                strKT_THEM35.add(ktnb08ModelListSend.get(i).getKT_THEM());
                strKT_XOA36.add(ktnb08ModelListSend.get(i).getKT_XOA());
                strKT_FONTWEIGHT37.add(ktnb08ModelListSend.get(i).getKT_FONTWEIGHT());
                strKT_CAPHT38.add(Double.toString(ktnb08ModelListSend.get(i).getKT_CAPHT()));
                strKT_STT39.add(Double.toString(ktnb08ModelListSend.get(i).getKT_STT()));
                strNG_CAPNHAT40.add(ktnb08ModelListSend.get(i).getNG_CAPNHAT());
                
            }
            hmData.put("COT_1",DefineFun.converArrayList2String(strKT_DV1));
            hmData.put("COT_2",DefineFun.converArrayList2String(strKT_TX_L2));
            hmData.put("COT_3",DefineFun.converArrayList2String(strKT_TX_N3));
            hmData.put("COT_4",DefineFun.converArrayList2String(strKT_TX_VV_C4));
            hmData.put("COT_5",DefineFun.converArrayList2String(strKT_TX_VV_M5));
            hmData.put("COT_6",DefineFun.converArrayList2String(strKT_TX_DDN_SD6));
            hmData.put("COT_7",DefineFun.converArrayList2String(strKT_TX_DDN_N7));
            hmData.put("COT_8",DefineFun.converArrayList2String(strKT_TX_DDN_VV_C8));
            hmData.put("COT_9",DefineFun.converArrayList2String(strKT_TX_DDN_VV_M9));
            hmData.put("COT_10",DefineFun.converArrayList2String(strKT_DK_L10));
            hmData.put("COT_11",DefineFun.converArrayList2String(strKT_DK_N11));
            hmData.put("COT_12",DefineFun.converArrayList2String(strKT_DK_VV_C12));
            hmData.put("COT_13",DefineFun.converArrayList2String(strKT_DK_VV_M13));
            hmData.put("COT_14",DefineFun.converArrayList2String(strKT_DK_DDN_SD14));
            hmData.put("COT_15",DefineFun.converArrayList2String(strKT_DK_DDN_N15));
            hmData.put("COT_16",DefineFun.converArrayList2String(strKT_DK_DDN_VV_C16));
            hmData.put("COT_17",DefineFun.converArrayList2String(strKT_DK_DDN_VV_M17));
            hmData.put("COT_18",DefineFun.converArrayList2String(strKT_ND_KN_HC_TC18));
            hmData.put("COT_19",DefineFun.converArrayList2String(strKT_ND_KN_HC_CS19));
            hmData.put("COT_20",DefineFun.converArrayList2String(strKT_ND_KN_HC_NTS20));
            hmData.put("COT_21",DefineFun.converArrayList2String(strKT_ND_KN_HC_CD21));
            hmData.put("COT_22",DefineFun.converArrayList2String(strKT_ND_KN_TP22));
            hmData.put("COT_23",DefineFun.converArrayList2String(strKT_ND_KN_CT23));
            hmData.put("COT_24",DefineFun.converArrayList2String(strKT_ND_TC_HC24));
            hmData.put("COT_25",DefineFun.converArrayList2String(strKT_ND_TC_TP25));
            hmData.put("COT_26",DefineFun.converArrayList2String(strKT_ND_TC_TN26));
            hmData.put("COT_27",DefineFun.converArrayList2String(strKT_ND_KHAC27));
            hmData.put("COT_28",DefineFun.converArrayList2String(strKT_KQ_CGQ28));
            hmData.put("COT_29",DefineFun.converArrayList2String(strKT_KQ_GQ_CCQD29));
            hmData.put("COT_30",DefineFun.converArrayList2String(strKT_KQ_GQ_DCQD30));
            hmData.put("COT_31",DefineFun.converArrayList2String(strKT_KQ_GQ_DCBA31));
            hmData.put("COT_32",DefineFun.converArrayList2String(strKT_GHICHU32));
            hmData.put("COT_33",DefineFun.converArrayList2String(strKT_DN33));
            hmData.put("COT_34",DefineFun.converArrayList2String(strKT_CO_DINH34));
            hmData.put("COT_35",DefineFun.converArrayList2String(strKT_THEM35));
            hmData.put("COT_36",DefineFun.converArrayList2String(strKT_XOA36));
            hmData.put("COT_37",DefineFun.converArrayList2String(strKT_FONTWEIGHT37));
            hmData.put("COT_38",DefineFun.converArrayList2String(strKT_CAPHT38));
            hmData.put("COT_39",DefineFun.converArrayList2String(strKT_STT39));
            hmData.put("COT_40",DefineFun.converArrayList2String(strNG_CAPNHAT40));
            
            //Tao file xml theo cau truc
            ProcessReportSyn clientWritexml = new ProcessReportSyn();
            boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKTNB(Define.PARA_SYN_REPORT_KTNB,
                    Define.SYN_REPORT_KTNB08, reportDate, userId, sPOS, maCn, reporGrade, "BCNT", hmData,quyBc,namBc, pathSave);
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
     
     public List<Ktnb08Model> getKtnb08ModelList() {
         return ktnb08ModelList;
     }
     
     public void setKtnb08ModelList(List<Ktnb08Model> ktnb08ModelList) {
         this.ktnb08ModelList = ktnb08ModelList;
     }
     
     public List<String> getKT_DV() {
         return KT_DV;
     }
     
     public void setKT_DV(List<String> KT_DV) {
         this.KT_DV = KT_DV;
     }
     
     public List<String> getKT_TX_L() {
         return KT_TX_L;
     }
     
     public void setKT_TX_L(List<String> KT_TX_L) {
         this.KT_TX_L = KT_TX_L;
     }
     
     public List<String> getKT_TX_N() {
         return KT_TX_N;
     }
     
     public void setKT_TX_N(List<String> KT_TX_N) {
         this.KT_TX_N = KT_TX_N;
     }
     
     public List<String> getKT_TX_VV_C() {
         return KT_TX_VV_C;
     }
     
     public void setKT_TX_VV_C(List<String> KT_TX_VV_C) {
         this.KT_TX_VV_C = KT_TX_VV_C;
     }
     
     public List<String> getKT_TX_VV_M() {
         return KT_TX_VV_M;
     }
     
     public void setKT_TX_VV_M(List<String> KT_TX_VV_M) {
         this.KT_TX_VV_M = KT_TX_VV_M;
     }
     
     public List<String> getKT_TX_DDN_SD() {
         return KT_TX_DDN_SD;
     }
     
     public void setKT_TX_DDN_SD(List<String> KT_TX_DDN_SD) {
         this.KT_TX_DDN_SD = KT_TX_DDN_SD;
     }
     
     public List<String> getKT_TX_DDN_N() {
         return KT_TX_DDN_N;
     }
     
     public void setKT_TX_DDN_N(List<String> KT_TX_DDN_N) {
         this.KT_TX_DDN_N = KT_TX_DDN_N;
     }
     
     public List<String> getKT_TX_DDN_VV_C() {
         return KT_TX_DDN_VV_C;
     }
     
     public void setKT_TX_DDN_VV_C(List<String> KT_TX_DDN_VV_C) {
         this.KT_TX_DDN_VV_C = KT_TX_DDN_VV_C;
     }
     
     public List<String> getKT_TX_DDN_VV_M() {
         return KT_TX_DDN_VV_M;
     }
     
     public void setKT_TX_DDN_VV_M(List<String> KT_TX_DDN_VV_M) {
         this.KT_TX_DDN_VV_M = KT_TX_DDN_VV_M;
     }
     
     public List<String> getKT_DK_L() {
         return KT_DK_L;
     }
     
     public void setKT_DK_L(List<String> KT_DK_L) {
         this.KT_DK_L = KT_DK_L;
     }
     
     public List<String> getKT_DK_N() {
         return KT_DK_N;
     }
     
     public void setKT_DK_N(List<String> KT_DK_N) {
         this.KT_DK_N = KT_DK_N;
     }
     
     public List<String> getKT_DK_VV_C() {
         return KT_DK_VV_C;
     }
     
     public void setKT_DK_VV_C(List<String> KT_DK_VV_C) {
         this.KT_DK_VV_C = KT_DK_VV_C;
     }
     
     public List<String> getKT_DK_VV_M() {
         return KT_DK_VV_M;
     }
     
     public void setKT_DK_VV_M(List<String> KT_DK_VV_M) {
         this.KT_DK_VV_M = KT_DK_VV_M;
     }
     
     public List<String> getKT_DK_DDN_SD() {
         return KT_DK_DDN_SD;
     }
     
     public void setKT_DK_DDN_SD(List<String> KT_DK_DDN_SD) {
         this.KT_DK_DDN_SD = KT_DK_DDN_SD;
     }
     
     public List<String> getKT_DK_DDN_N() {
         return KT_DK_DDN_N;
     }
     
     public void setKT_DK_DDN_N(List<String> KT_DK_DDN_N) {
         this.KT_DK_DDN_N = KT_DK_DDN_N;
     }
     
     public List<String> getKT_DK_DDN_VV_C() {
         return KT_DK_DDN_VV_C;
     }
     
     public void setKT_DK_DDN_VV_C(List<String> KT_DK_DDN_VV_C) {
         this.KT_DK_DDN_VV_C = KT_DK_DDN_VV_C;
     }
     
     public List<String> getKT_DK_DDN_VV_M() {
         return KT_DK_DDN_VV_M;
     }
     
     public void setKT_DK_DDN_VV_M(List<String> KT_DK_DDN_VV_M) {
         this.KT_DK_DDN_VV_M = KT_DK_DDN_VV_M;
     }
     
     public List<String> getKT_ND_KN_HC_TC() {
         return KT_ND_KN_HC_TC;
     }
     
     public void setKT_ND_KN_HC_TC(List<String> KT_ND_KN_HC_TC) {
         this.KT_ND_KN_HC_TC = KT_ND_KN_HC_TC;
     }
     
     public List<String> getKT_ND_KN_HC_CS() {
         return KT_ND_KN_HC_CS;
     }
     
     public void setKT_ND_KN_HC_CS(List<String> KT_ND_KN_HC_CS) {
         this.KT_ND_KN_HC_CS = KT_ND_KN_HC_CS;
     }
     
     public List<String> getKT_ND_KN_HC_NTS() {
         return KT_ND_KN_HC_NTS;
     }
     
     public void setKT_ND_KN_HC_NTS(List<String> KT_ND_KN_HC_NTS) {
         this.KT_ND_KN_HC_NTS = KT_ND_KN_HC_NTS;
     }
     
     public List<String> getKT_ND_KN_HC_CD() {
         return KT_ND_KN_HC_CD;
     }
     
     public void setKT_ND_KN_HC_CD(List<String> KT_ND_KN_HC_CD) {
         this.KT_ND_KN_HC_CD = KT_ND_KN_HC_CD;
     }
     
     public List<String> getKT_ND_KN_TP() {
         return KT_ND_KN_TP;
     }
     
     public void setKT_ND_KN_TP(List<String> KT_ND_KN_TP) {
         this.KT_ND_KN_TP = KT_ND_KN_TP;
     }
     
     public List<String> getKT_ND_KN_CT() {
         return KT_ND_KN_CT;
     }
     
     public void setKT_ND_KN_CT(List<String> KT_ND_KN_CT) {
         this.KT_ND_KN_CT = KT_ND_KN_CT;
     }
     
     public List<String> getKT_ND_TC_HC() {
         return KT_ND_TC_HC;
     }
     
     public void setKT_ND_TC_HC(List<String> KT_ND_TC_HC) {
         this.KT_ND_TC_HC = KT_ND_TC_HC;
     }
     
     public List<String> getKT_ND_TC_TP() {
         return KT_ND_TC_TP;
     }
     
     public void setKT_ND_TC_TP(List<String> KT_ND_TC_TP) {
         this.KT_ND_TC_TP = KT_ND_TC_TP;
     }
     
     public List<String> getKT_ND_TC_TN() {
         return KT_ND_TC_TN;
     }
     
     public void setKT_ND_TC_TN(List<String> KT_ND_TC_TN) {
         this.KT_ND_TC_TN = KT_ND_TC_TN;
     }
     
     public List<String> getKT_ND_KHAC() {
         return KT_ND_KHAC;
     }
     
     public void setKT_ND_KHAC(List<String> KT_ND_KHAC) {
         this.KT_ND_KHAC = KT_ND_KHAC;
     }
     
     public List<String> getKT_KQ_CGQ() {
         return KT_KQ_CGQ;
     }
     
     public void setKT_KQ_CGQ(List<String> KT_KQ_CGQ) {
         this.KT_KQ_CGQ = KT_KQ_CGQ;
     }
     
     public List<String> getKT_KQ_GQ_CCQD() {
         return KT_KQ_GQ_CCQD;
     }
     
     public void setKT_KQ_GQ_CCQD(List<String> KT_KQ_GQ_CCQD) {
         this.KT_KQ_GQ_CCQD = KT_KQ_GQ_CCQD;
     }
     
     public List<String> getKT_KQ_GQ_DCQD() {
         return KT_KQ_GQ_DCQD;
     }
     
     public void setKT_KQ_GQ_DCQD(List<String> KT_KQ_GQ_DCQD) {
         this.KT_KQ_GQ_DCQD = KT_KQ_GQ_DCQD;
     }
     
     public List<String> getKT_KQ_GQ_DCBA() {
         return KT_KQ_GQ_DCBA;
     }
     
     public void setKT_KQ_GQ_DCBA(List<String> KT_KQ_GQ_DCBA) {
         this.KT_KQ_GQ_DCBA = KT_KQ_GQ_DCBA;
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
