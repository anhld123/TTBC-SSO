/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import vbsp.ims.dao.ktnb.DaoKtnb04;
import vbsp.ims.dao.ktnb.ListKTNBDA;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.Ktnb01Model;
import vbsp.ims.model.ktnb.Ktnb04Model;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.syn.ProcessReportSyn;

/**
 *
 * @author CuongBM0211
 */
public class Ktnb04ActionSupport extends ActionSupport implements ServletRequestAware{
    private DaoKtnb04 daoKtnb04 = new DaoKtnb04();
    private ListKTNBDA listKTNBDA = new ListKTNBDA();
    private List<Ktnb04Model> ktnb04ModelList;  //Lay du lieu load len table
    
    //Cac truong dung cho luu du lieu
    private List<String> KT_STT_HT;
    private List<String> KT_LOAI_CT;
    private List<String> KT_KT_SCT;
    private List<String> KT_KT_ST;
    private List<String> KT_SS_TS_SCT;
    private List<String> KT_SS_TS_ST;
    private List<String> KT_SS_TS_TLCT;
    private List<String> KT_SS_TS_TLST;
    private List<String> KT_TD_SPL_SCT;
    private List<String> KT_TD_SPL_ST;
    private List<String> KT_TD_KTNV_SL;
    private List<String> KT_TD_KTNV_ST;
    private List<String> KT_TD_SK_SL;
    private List<String> KT_TD_SK_ST;
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
    
    public Ktnb04ActionSupport() {
    }
    
    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public String get_data_ktnb04(){
        getInfo();
        ktnb04ModelList = daoKtnb04.get_ktnb04(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
    }
    
    public String ResetDataInput4(){
        getInfo();
        ktnb04ModelList = daoKtnb04.get_ktnb04_default(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
    }
    
    public String get_data_ktnb04_auth() throws SQLException{
        getInfo();
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
        ktnb04ModelList = daoKtnb04.get_ktnb04_auth(strPosCD, Integer.parseInt(namBc), Integer.parseInt(quyBc),"N");
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
         if(!daoKtnb04.save_ktnb04_auth(pos_auth,quyBc,namBc))
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
                    pathSave+= sPOS + "_BCNT_KTNB_"+"04"+".xml";    //Thay ma bao cao
            String reportDate =  new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
            
            HashMap<String, String> hmData = new HashMap<String, String>();
            
            List<Ktnb04Model> ktnb04ModelListSend = null;
            
             List<String> strKT_STT_HT1 = new ArrayList<String>();
             List<String> strKT_LOAI_CT2 = new ArrayList<String>();
             List<String> strKT_KT_SCT3 = new ArrayList<String>();
             List<String> strKT_KT_ST4 = new ArrayList<String>();
             List<String> strKT_SS_TS_SCT5 = new ArrayList<String>();
             List<String> strKT_SS_TS_ST6 = new ArrayList<String>();
             List<String> strKT_SS_TS_TLCT7 = new ArrayList<String>();
             List<String> strKT_SS_TS_TLST8 = new ArrayList<String>();
             List<String> strKT_TD_SPL_SCT9 = new ArrayList<String>();
             List<String> strKT_TD_SPL_ST10 = new ArrayList<String>();
             List<String> strKT_TD_KTNV_SL11 = new ArrayList<String>();
             List<String> strKT_TD_KTNV_ST12 = new ArrayList<String>();            
             List<String> strKT_TD_SK_SL13 = new ArrayList<String>();
             List<String> strKT_TD_SK_ST14 = new ArrayList<String>();
            
             List<String> strKT_DN15 = new ArrayList<String>();
             List<String> strKT_CO_DINH16 = new ArrayList<String>();
             List<String> strKT_THEM17 = new ArrayList<String>();
             List<String> strKT_XOA18 = new ArrayList<String>();
             List<String> strKT_FONTWEIGHT19 = new ArrayList<String>();
             List<String> strKT_CAPHT20 = new ArrayList<String>();
             List<String> strKT_STT21 = new ArrayList<String>();
             List<String>  strNG_CAPNHAT22 = new ArrayList<String>();
             List<String> strKT_KHOA23 = new ArrayList<String>();
            
            ktnb04ModelListSend = daoKtnb04.get_ktnb04_auth(sPOS, Integer.parseInt(namBc), Integer.parseInt(quyBc),"Y");
            if(ktnb04ModelListSend.size()<=0)
                return false;
            for (int i = 0; i < ktnb04ModelListSend.size(); i++) {
                strKT_STT_HT1.add(ktnb04ModelListSend.get(i).getKT_STT_HT());
                strKT_LOAI_CT2.add(ktnb04ModelListSend.get(i).getKT_LOAI_CT());
                strKT_KT_SCT3.add(Double.toString(ktnb04ModelListSend.get(i).getKT_KT_SCT()));
                strKT_KT_ST4.add(Double.toString(ktnb04ModelListSend.get(i).getKT_KT_ST()));
                strKT_SS_TS_SCT5.add(Double.toString(ktnb04ModelListSend.get(i).getKT_SS_TS_SCT()));
                strKT_SS_TS_ST6.add(Double.toString(ktnb04ModelListSend.get(i).getKT_SS_TS_ST()));
                strKT_SS_TS_TLCT7.add(Double.toString(ktnb04ModelListSend.get(i).getKT_SS_TS_TLCT()));
                strKT_SS_TS_TLST8.add(Double.toString(ktnb04ModelListSend.get(i).getKT_SS_TS_TLST()));
                strKT_TD_SPL_SCT9.add(Double.toString(ktnb04ModelListSend.get(i).getKT_TD_SPL_SCT()));
                strKT_TD_SPL_ST10.add(Double.toString(ktnb04ModelListSend.get(i).getKT_TD_SPL_ST()));
                strKT_TD_KTNV_SL11.add(Double.toString(ktnb04ModelListSend.get(i).getKT_TD_KTNV_SL()));
                strKT_TD_KTNV_ST12.add(Double.toString(ktnb04ModelListSend.get(i).getKT_TD_KTNV_ST()));            
                strKT_TD_SK_SL13.add(Double.toString(ktnb04ModelListSend.get(i).getKT_TD_SK_SL()));
                strKT_TD_SK_ST14.add(Double.toString(ktnb04ModelListSend.get(i).getKT_TD_SK_ST()));

                strKT_DN15.add(ktnb04ModelListSend.get(i).getKT_DN());
                strKT_CO_DINH16.add(ktnb04ModelListSend.get(i).getKT_CO_DINH());
                strKT_THEM17.add(ktnb04ModelListSend.get(i).getKT_THEM());
                strKT_XOA18.add(ktnb04ModelListSend.get(i).getKT_XOA());
                strKT_FONTWEIGHT19.add(ktnb04ModelListSend.get(i).getKT_FONTWEIGHT());
                strKT_CAPHT20.add(Double.toString(ktnb04ModelListSend.get(i).getKT_CAPHT()));
                strKT_STT21.add(Double.toString(ktnb04ModelListSend.get(i).getKT_STT()));
                strNG_CAPNHAT22.add(ktnb04ModelListSend.get(i).getNG_CAPNHAT());
                strKT_KHOA23.add(ktnb04ModelListSend.get(i).getKT_KHOA());
                
            }
            hmData.put("COT_1",DefineFun.converArrayList2String(strKT_STT_HT1));
            hmData.put("COT_2",DefineFun.converArrayList2String(strKT_LOAI_CT2));
            hmData.put("COT_3",DefineFun.converArrayList2String(strKT_KT_SCT3));
            hmData.put("COT_4",DefineFun.converArrayList2String(strKT_KT_ST4));
            hmData.put("COT_5",DefineFun.converArrayList2String(strKT_SS_TS_SCT5));
            hmData.put("COT_6",DefineFun.converArrayList2String(strKT_SS_TS_ST6));
            hmData.put("COT_7",DefineFun.converArrayList2String(strKT_SS_TS_TLCT7));
            hmData.put("COT_8",DefineFun.converArrayList2String(strKT_SS_TS_TLST8));
            hmData.put("COT_9",DefineFun.converArrayList2String(strKT_TD_SPL_SCT9));
            hmData.put("COT_10",DefineFun.converArrayList2String(strKT_TD_SPL_ST10));
            hmData.put("COT_11",DefineFun.converArrayList2String(strKT_TD_KTNV_SL11));
            hmData.put("COT_12",DefineFun.converArrayList2String(strKT_TD_KTNV_ST12));            
            hmData.put("COT_13",DefineFun.converArrayList2String(strKT_TD_SK_SL13));
            hmData.put("COT_14",DefineFun.converArrayList2String(strKT_TD_SK_ST14));
            
            hmData.put("COT_15",DefineFun.converArrayList2String(strKT_DN15));
            hmData.put("COT_16",DefineFun.converArrayList2String(strKT_CO_DINH16));
            hmData.put("COT_17",DefineFun.converArrayList2String(strKT_THEM17));
            hmData.put("COT_18",DefineFun.converArrayList2String(strKT_XOA18));
            hmData.put("COT_19",DefineFun.converArrayList2String(strKT_FONTWEIGHT19));
            hmData.put("COT_20",DefineFun.converArrayList2String(strKT_CAPHT20));
            hmData.put("COT_21",DefineFun.converArrayList2String(strKT_STT21));
            hmData.put("COT_22",DefineFun.converArrayList2String(strNG_CAPNHAT22));
            hmData.put("COT_23",DefineFun.converArrayList2String(strKT_KHOA23));
            
            //Tao file xml theo cau truc
            ProcessReportSyn clientWritexml = new ProcessReportSyn();
            boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKTNB(Define.PARA_SYN_REPORT_KTNB,
                    Define.SYN_REPORT_KTNB04, reportDate, userId, sPOS, maCn, reporGrade, "BCNT", hmData,quyBc,namBc, pathSave);
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
    
    
    public String save_data_ktnb04(){
        if(daoKtnb04.save_ktnb04(posCD,maCn,quyBc,namBc,userId, KT_KHOA,KT_KT_SCT, KT_KT_ST, KT_SS_TS_SCT, KT_SS_TS_ST, KT_SS_TS_TLCT, KT_SS_TS_TLST, 
        KT_TD_SPL_SCT, KT_TD_SPL_ST, KT_TD_KTNV_SL, KT_TD_KTNV_ST, KT_TD_SK_SL, KT_TD_SK_ST,""))
            return "success";
        else
            return "error";  
        
        //DONG BO DU LIEU LEN TW
//        HttpSession session = request.getSession();
//        String reporGrade = session.getAttribute("reportGrade").toString();
//        if (!reporGrade.equals("3")) {
//            //Lay duong dan va ten xml se ghi ra
//              String pathSave = !request.getRealPath("/").endsWith("/")
//                    ? request.getRealPath("/") + "/" + Define.M_REPORT_XML
//                    : request.getRealPath("/") + Define.M_REPORT_XML;
//                    pathSave+= posCD + "_BCNT_KTNB_"+"04"+".xml";    //Thay ma bao cao
//            String reportDate =  new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
//            
//            HashMap<String, String> hmData = new HashMap<String, String>();
//            
//            hmData.put("COT_1",DefineFun.converArrayList2String(KT_STT_HT));
//            hmData.put("COT_2",DefineFun.converArrayList2String(KT_LOAI_CT));
//            hmData.put("COT_3",DefineFun.converArrayList2String(KT_KT_SCT));
//            hmData.put("COT_4",DefineFun.converArrayList2String(KT_KT_ST));
//            hmData.put("COT_5",DefineFun.converArrayList2String(KT_SS_TS_SCT));
//            hmData.put("COT_6",DefineFun.converArrayList2String(KT_SS_TS_ST));
//            hmData.put("COT_7",DefineFun.converArrayList2String(KT_SS_TS_TLCT));
//            hmData.put("COT_8",DefineFun.converArrayList2String(KT_SS_TS_TLST));
//            hmData.put("COT_9",DefineFun.converArrayList2String(KT_TD_SPL_SCT));
//            hmData.put("COT_10",DefineFun.converArrayList2String(KT_TD_SPL_ST));
//            hmData.put("COT_11",DefineFun.converArrayList2String(KT_TD_KTNV_SL));
//            hmData.put("COT_12",DefineFun.converArrayList2String(KT_TD_KTNV_ST));
//            
//            hmData.put("COT_13",DefineFun.converArrayList2String(KT_TD_SK_SL));
//            hmData.put("COT_14",DefineFun.converArrayList2String(KT_TD_SK_ST));
//            
//            hmData.put("COT_15",DefineFun.converArrayList2String(KT_DN));
//            hmData.put("COT_16",DefineFun.converArrayList2String(KT_CO_DINH));
//            hmData.put("COT_17",DefineFun.converArrayList2String(KT_THEM));
//            hmData.put("COT_18",DefineFun.converArrayList2String(KT_XOA));
//            hmData.put("COT_19",DefineFun.converArrayList2String(KT_FONTWEIGHT));
//            hmData.put("COT_20",DefineFun.converArrayList2String(KT_CAPHT));
//            hmData.put("COT_21",DefineFun.converArrayList2String(KT_STT));
//            hmData.put("COT_22",DefineFun.converArrayList2String(NG_CAPNHAT));
//            
//            //Tao file xml theo cau truc
//            ProcessReportSyn clientWritexml = new ProcessReportSyn();
//            boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKTNB(Define.PARA_SYN_REPORT_KTNB,
//                    Define.SYN_REPORT_KTNB04, reportDate, userId, posCD, maCn, reporGrade, "BCNT", hmData,quyBc,namBc, pathSave);
//            if (!bSuccess) {
//                System.err.println("Ban chua dong bo du lieu duoc ve TW");
//            }
//        }
//        //========== END DONG BO DU LIEU =======================================
//        
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
     public DaoKtnb04 getDaoKtnb04() {
         return daoKtnb04;
     }
     
     public void setDaoKtnb04(DaoKtnb04 daoKtnb04) {
         this.daoKtnb04 = daoKtnb04;
     }
     
     public ListKTNBDA getListKTNBDA() {
         return listKTNBDA;
     }
     
     public void setListKTNBDA(ListKTNBDA listKTNBDA) {
         this.listKTNBDA = listKTNBDA;
     }
     
     public List<Ktnb04Model> getKtnb04ModelList() {
         return ktnb04ModelList;
     }
     
     public void setKtnb04ModelList(List<Ktnb04Model> ktnb04ModelList) {
         this.ktnb04ModelList = ktnb04ModelList;
     }
     
     public List<String> getKT_STT_HT() {
         return KT_STT_HT;
     }
     
     public void setKT_STT_HT(List<String> KT_STT_HT) {
         this.KT_STT_HT = KT_STT_HT;
     }
     
     public List<String> getKT_LOAI_CT() {
         return KT_LOAI_CT;
     }
     
     public void setKT_LOAI_CT(List<String> KT_LOAI_CT) {
         this.KT_LOAI_CT = KT_LOAI_CT;
     }
     
     public List<String> getKT_KT_SCT() {
         return KT_KT_SCT;
     }
     
     public void setKT_KT_SCT(List<String> KT_KT_SCT) {
         this.KT_KT_SCT = KT_KT_SCT;
     }
     
     public List<String> getKT_KT_ST() {
         return KT_KT_ST;
     }
     
     public void setKT_KT_ST(List<String> KT_KT_ST) {
         this.KT_KT_ST = KT_KT_ST;
     }
     
     public List<String> getKT_SS_TS_SCT() {
         return KT_SS_TS_SCT;
     }
     
     public void setKT_SS_TS_SCT(List<String> KT_SS_TS_SCT) {
         this.KT_SS_TS_SCT = KT_SS_TS_SCT;
     }
     
     public List<String> getKT_SS_TS_ST() {
         return KT_SS_TS_ST;
     }
     
     public void setKT_SS_TS_ST(List<String> KT_SS_TS_ST) {
         this.KT_SS_TS_ST = KT_SS_TS_ST;
     }
     
     public List<String> getKT_SS_TS_TLCT() {
         return KT_SS_TS_TLCT;
     }
     
     public void setKT_SS_TS_TLCT(List<String> KT_SS_TS_TLCT) {
         this.KT_SS_TS_TLCT = KT_SS_TS_TLCT;
     }
     
     public List<String> getKT_SS_TS_TLST() {
         return KT_SS_TS_TLST;
     }
     
     public void setKT_SS_TS_TLST(List<String> KT_SS_TS_TLST) {
         this.KT_SS_TS_TLST = KT_SS_TS_TLST;
     }
     
     public List<String> getKT_TD_SPL_SCT() {
         return KT_TD_SPL_SCT;
     }
     
     public void setKT_TD_SPL_SCT(List<String> KT_TD_SPL_SCT) {
         this.KT_TD_SPL_SCT = KT_TD_SPL_SCT;
     }
     
     public List<String> getKT_TD_SPL_ST() {
         return KT_TD_SPL_ST;
     }
     
     public void setKT_TD_SPL_ST(List<String> KT_TD_SPL_ST) {
         this.KT_TD_SPL_ST = KT_TD_SPL_ST;
     }
     
     public List<String> getKT_TD_KTNV_SL() {
         return KT_TD_KTNV_SL;
     }
     
     public void setKT_TD_KTNV_SL(List<String> KT_TD_KTNV_SL) {
         this.KT_TD_KTNV_SL = KT_TD_KTNV_SL;
     }
     
     public List<String> getKT_TD_KTNV_ST() {
         return KT_TD_KTNV_ST;
     }
     
     public void setKT_TD_KTNV_ST(List<String> KT_TD_KTNV_ST) {
         this.KT_TD_KTNV_ST = KT_TD_KTNV_ST;
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
     
    public List<String> getKT_TD_SK_SL() {
        return KT_TD_SK_SL;
    }

    public void setKT_TD_SK_SL(List<String> KT_TD_SK_SL) {
        this.KT_TD_SK_SL = KT_TD_SK_SL;
    }

    public List<String> getKT_TD_SK_ST() {
        return KT_TD_SK_ST;
    }

    public void setKT_TD_SK_ST(List<String> KT_TD_SK_ST) {
        this.KT_TD_SK_ST = KT_TD_SK_ST;
    }
//</editor-fold>

    
}
