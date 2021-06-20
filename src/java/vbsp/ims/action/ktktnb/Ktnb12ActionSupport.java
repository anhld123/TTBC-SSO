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
import vbsp.ims.dao.ktnb.DaoKtnb12;
import vbsp.ims.dao.ktnb.ListKTNBDA;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.Ktnb11Model;
import vbsp.ims.model.ktnb.Ktnb12Model;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.syn.ProcessReportSyn;

/**
 *
 * @author CuongBM0211
 */
public class Ktnb12ActionSupport extends ActionSupport implements ServletRequestAware{
    private DaoKtnb12 daoKtnb12 = new DaoKtnb12();
    private ListKTNBDA listKTNBDA = new ListKTNBDA();
    private List<Ktnb12Model> ktnb12ModelList;  //Lay du lieu load len table
    
    //Cac truong dung cho luu du lieu
    private List<String> KT_DV;
    private List<String> KT_SO_VB_NEW;
    private List<String> KT_SO_VB_BS;
    private List<String> KT_SO_LOP_TH;
    private List<String> KT_SO_NG_TH;
    private List<String> KT_SO_CUOC;
    private List<String> KT_SO_DV;
    private List<String> KT_SO_DV_VP;
    private List<String> KT_SO_TOCHUC1;
    private List<String> KT_SO_CANHAN1;
    private List<String> KT_SO_TOCHUC2;
    private List<String> KT_SO_CANHAN2;
    private List<String> KT_TONG_KLTT;
    private List<String> KT_SO_TOCHUC3;
    private List<String> KT_SO_CANHAN3;
    private List<String> KT_SO_TOCHUC4;
    private List<String> KT_SO_CANHAN4;
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
    
    public Ktnb12ActionSupport() {
    }
    
    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public String get_data_ktnb12(){
        getInfo();
        ktnb12ModelList = daoKtnb12.get_ktnb12(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
    }
    
    public String ResetDataInput12(){
        getInfo();
        ktnb12ModelList = daoKtnb12.get_ktnb12_default(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
    }
    
    public String get_data_ktnb12_auth() throws SQLException{
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
        ktnb12ModelList = daoKtnb12.get_ktnb12_auth(strPosCD, Integer.parseInt(namBc), Integer.parseInt(quyBc),"N");
        return "success";
    }
    
    public String save_data_ktnb12(){
        if(daoKtnb12.save_ktnb12(posCD,maCn,quyBc,namBc,userId,KT_DV,KT_SO_VB_NEW,KT_SO_VB_BS,KT_SO_LOP_TH,KT_SO_NG_TH,KT_SO_CUOC,KT_SO_DV,KT_SO_DV_VP,KT_SO_TOCHUC1,KT_SO_CANHAN1,KT_SO_TOCHUC2,KT_SO_CANHAN2,KT_TONG_KLTT,KT_SO_TOCHUC3,KT_SO_CANHAN3,KT_SO_TOCHUC4,KT_SO_CANHAN4,KT_GHICHU,KT_DN,KT_CO_DINH,KT_THEM,KT_XOA,KT_FONTWEIGHT,KT_CAPHT,KT_STT,NG_CAPNHAT,""))
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
//                    pathSave+= posCD + "_BCNT_KTNB_"+"12"+".xml";    //Thay ma bao cao
//            String reportDate =  new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
//            
//            HashMap<String, String> hmData = new HashMap<String, String>();
//            
//            hmData.put("COT_1",DefineFun.converArrayList2String(KT_DV));
//            hmData.put("COT_2",DefineFun.converArrayList2String(KT_SO_VB_NEW));
//            hmData.put("COT_3",DefineFun.converArrayList2String(KT_SO_VB_BS));
//            hmData.put("COT_4",DefineFun.converArrayList2String(KT_SO_LOP_TH));
//            hmData.put("COT_5",DefineFun.converArrayList2String(KT_SO_NG_TH));
//            hmData.put("COT_6",DefineFun.converArrayList2String(KT_SO_CUOC));
//            hmData.put("COT_7",DefineFun.converArrayList2String(KT_SO_DV));
//            hmData.put("COT_8",DefineFun.converArrayList2String(KT_SO_DV_VP));
//            hmData.put("COT_9",DefineFun.converArrayList2String(KT_SO_TOCHUC1));
//            hmData.put("COT_10",DefineFun.converArrayList2String(KT_SO_CANHAN1));
//            hmData.put("COT_11",DefineFun.converArrayList2String(KT_SO_TOCHUC2));
//            hmData.put("COT_12",DefineFun.converArrayList2String(KT_SO_CANHAN2));
//            hmData.put("COT_13",DefineFun.converArrayList2String(KT_TONG_KLTT));
//            hmData.put("COT_14",DefineFun.converArrayList2String(KT_SO_TOCHUC3));
//            hmData.put("COT_15",DefineFun.converArrayList2String(KT_SO_CANHAN3));
//            hmData.put("COT_16",DefineFun.converArrayList2String(KT_SO_TOCHUC4));
//            hmData.put("COT_17",DefineFun.converArrayList2String(KT_SO_CANHAN4));
//            hmData.put("COT_18",DefineFun.converArrayList2String(KT_GHICHU));
//            hmData.put("COT_19",DefineFun.converArrayList2String(KT_DN));
//            hmData.put("COT_20",DefineFun.converArrayList2String(KT_CO_DINH));
//            hmData.put("COT_21",DefineFun.converArrayList2String(KT_THEM));
//            hmData.put("COT_22",DefineFun.converArrayList2String(KT_XOA));
//            hmData.put("COT_23",DefineFun.converArrayList2String(KT_FONTWEIGHT));
//            hmData.put("COT_24",DefineFun.converArrayList2String(KT_CAPHT));
//            hmData.put("COT_25",DefineFun.converArrayList2String(KT_STT));
//            hmData.put("COT_26",DefineFun.converArrayList2String(NG_CAPNHAT));
//            
//            //Tao file xml theo cau truc
//            ProcessReportSyn clientWritexml = new ProcessReportSyn();
//            boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKTNB(Define.PARA_SYN_REPORT_KTNB,
//                    Define.SYN_REPORT_KTNB12, reportDate, userId, posCD, maCn, reporGrade, "BCNT", hmData,quyBc,namBc, pathSave);
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
         if(!daoKtnb12.save_ktnb12_auth(pos_auth,quyBc,namBc))
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
                    pathSave+= sPOS + "_BCNT_KTNB_"+"12"+".xml";    //Thay ma bao cao
            String reportDate =  new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
            
            HashMap<String, String> hmData = new HashMap<String, String>();
            
            List<Ktnb12Model> ktnb12ModelListSend = null;

            List<String> strKT_DV1 = new ArrayList<String>();
            List<String> strKT_SO_VB_NEW2 = new ArrayList<String>();
            List<String> strKT_SO_VB_BS3 = new ArrayList<String>();
            List<String> strKT_SO_LOP_TH4 = new ArrayList<String>();
            List<String> strKT_SO_NG_TH5 = new ArrayList<String>();
            List<String> strKT_SO_CUOC6 = new ArrayList<String>();
            List<String> strKT_SO_DV7 = new ArrayList<String>();
            List<String> strKT_SO_DV_VP8 = new ArrayList<String>();
            List<String> strKT_SO_TOCHUC19 = new ArrayList<String>();
            List<String> strKT_SO_CANHAN110 = new ArrayList<String>();
            List<String> strKT_SO_TOCHUC211 = new ArrayList<String>();
            List<String> strKT_SO_CANHAN212 = new ArrayList<String>();
            List<String> strKT_TONG_KLTT13 = new ArrayList<String>();
            List<String> strKT_SO_TOCHUC314 = new ArrayList<String>();
            List<String> strKT_SO_CANHAN315 = new ArrayList<String>();
            List<String> strKT_SO_TOCHUC416 = new ArrayList<String>();
            List<String> strKT_SO_CANHAN417 = new ArrayList<String>();
            List<String> strKT_GHICHU18 = new ArrayList<String>();
            List<String> strKT_DN19 = new ArrayList<String>();
            List<String> strKT_CO_DINH20 = new ArrayList<String>();
            List<String> strKT_THEM21 = new ArrayList<String>();
            List<String> strKT_XOA22 = new ArrayList<String>();
            List<String> strKT_FONTWEIGHT23 = new ArrayList<String>();
            List<String> strKT_CAPHT24 = new ArrayList<String>();
            List<String> strKT_STT25 = new ArrayList<String>();
            List<String> strNG_CAPNHAT26 = new ArrayList<String>();
            
            
            ktnb12ModelListSend = daoKtnb12.get_ktnb12_auth(sPOS, Integer.parseInt(namBc), Integer.parseInt(quyBc),"Y");
            if(ktnb12ModelListSend.size()<=0)
                return false;
            for (int i = 0; i < ktnb12ModelListSend.size(); i++) {

                strKT_DV1.add(ktnb12ModelListSend.get(i).getKT_DV());
                strKT_SO_VB_NEW2.add(Double.toString(ktnb12ModelListSend.get(i).getKT_SO_VB_NEW()));
                strKT_SO_VB_BS3.add(Double.toString(ktnb12ModelListSend.get(i).getKT_SO_VB_BS()));
                strKT_SO_LOP_TH4.add(Double.toString(ktnb12ModelListSend.get(i).getKT_SO_LOP_TH()));
                strKT_SO_NG_TH5.add(Double.toString(ktnb12ModelListSend.get(i).getKT_SO_NG_TH()));
                strKT_SO_CUOC6.add(Double.toString(ktnb12ModelListSend.get(i).getKT_SO_CUOC()));
                strKT_SO_DV7.add(Double.toString(ktnb12ModelListSend.get(i).getKT_SO_DV()));
                strKT_SO_DV_VP8.add(Double.toString(ktnb12ModelListSend.get(i).getKT_SO_DV_VP()));
                strKT_SO_TOCHUC19.add(Double.toString(ktnb12ModelListSend.get(i).getKT_SO_TOCHUC1()));
                strKT_SO_CANHAN110.add(Double.toString(ktnb12ModelListSend.get(i).getKT_SO_CANHAN1()));
                strKT_SO_TOCHUC211.add(Double.toString(ktnb12ModelListSend.get(i).getKT_SO_TOCHUC2()));
                strKT_SO_CANHAN212.add(Double.toString(ktnb12ModelListSend.get(i).getKT_SO_CANHAN2()));
                strKT_TONG_KLTT13.add(Double.toString(ktnb12ModelListSend.get(i).getKT_TONG_KLTT()));
                strKT_SO_TOCHUC314.add(Double.toString(ktnb12ModelListSend.get(i).getKT_SO_TOCHUC3()));
                strKT_SO_CANHAN315.add(Double.toString(ktnb12ModelListSend.get(i).getKT_SO_CANHAN3()));
                strKT_SO_TOCHUC416.add(Double.toString(ktnb12ModelListSend.get(i).getKT_SO_TOCHUC4()));
                strKT_SO_CANHAN417.add(Double.toString(ktnb12ModelListSend.get(i).getKT_SO_CANHAN4()));
                strKT_GHICHU18.add(ktnb12ModelListSend.get(i).getKT_GHICHU());
                strKT_DN19.add(ktnb12ModelListSend.get(i).getKT_DN());
                strKT_CO_DINH20.add(ktnb12ModelListSend.get(i).getKT_CO_DINH());
                strKT_THEM21.add(ktnb12ModelListSend.get(i).getKT_THEM());
                strKT_XOA22.add(ktnb12ModelListSend.get(i).getKT_XOA());
                strKT_FONTWEIGHT23.add(ktnb12ModelListSend.get(i).getKT_FONTWEIGHT());
                strKT_CAPHT24.add(Double.toString(ktnb12ModelListSend.get(i).getKT_CAPHT()));
                strKT_STT25.add(Double.toString(ktnb12ModelListSend.get(i).getKT_STT()));
                strNG_CAPNHAT26.add(ktnb12ModelListSend.get(i).getNG_CAPNHAT());
                
                
            }
            hmData.put("COT_1",DefineFun.converArrayList2String(strKT_DV1));
            hmData.put("COT_2",DefineFun.converArrayList2String(strKT_SO_VB_NEW2));
            hmData.put("COT_3",DefineFun.converArrayList2String(strKT_SO_VB_BS3));
            hmData.put("COT_4",DefineFun.converArrayList2String(strKT_SO_LOP_TH4));
            hmData.put("COT_5",DefineFun.converArrayList2String(strKT_SO_NG_TH5));
            hmData.put("COT_6",DefineFun.converArrayList2String(strKT_SO_CUOC6));
            hmData.put("COT_7",DefineFun.converArrayList2String(strKT_SO_DV7));
            hmData.put("COT_8",DefineFun.converArrayList2String(strKT_SO_DV_VP8));
            hmData.put("COT_9",DefineFun.converArrayList2String(strKT_SO_TOCHUC19));
            hmData.put("COT_10",DefineFun.converArrayList2String(strKT_SO_CANHAN110));
            hmData.put("COT_11",DefineFun.converArrayList2String(strKT_SO_TOCHUC211));
            hmData.put("COT_12",DefineFun.converArrayList2String(strKT_SO_CANHAN212));
            hmData.put("COT_13",DefineFun.converArrayList2String(strKT_TONG_KLTT13));
            hmData.put("COT_14",DefineFun.converArrayList2String(strKT_SO_TOCHUC314));
            hmData.put("COT_15",DefineFun.converArrayList2String(strKT_SO_CANHAN315));
            hmData.put("COT_16",DefineFun.converArrayList2String(strKT_SO_TOCHUC416));
            hmData.put("COT_17",DefineFun.converArrayList2String(strKT_SO_CANHAN417));
            hmData.put("COT_18",DefineFun.converArrayList2String(strKT_GHICHU18));
            hmData.put("COT_19",DefineFun.converArrayList2String(strKT_DN19));
            hmData.put("COT_20",DefineFun.converArrayList2String(strKT_CO_DINH20));
            hmData.put("COT_21",DefineFun.converArrayList2String(strKT_THEM21));
            hmData.put("COT_22",DefineFun.converArrayList2String(strKT_XOA22));
            hmData.put("COT_23",DefineFun.converArrayList2String(strKT_FONTWEIGHT23));
            hmData.put("COT_24",DefineFun.converArrayList2String(strKT_CAPHT24));
            hmData.put("COT_25",DefineFun.converArrayList2String(strKT_STT25));
            hmData.put("COT_26",DefineFun.converArrayList2String(strNG_CAPNHAT26));
            
            //Tao file xml theo cau truc
            ProcessReportSyn clientWritexml = new ProcessReportSyn();
            boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKTNB(Define.PARA_SYN_REPORT_KTNB,
                    Define.SYN_REPORT_KTNB12, reportDate, userId, sPOS, maCn, reporGrade, "BCNT", hmData,quyBc,namBc, pathSave);
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
     
     public List<Ktnb12Model> getKtnb12ModelList() {
         return ktnb12ModelList;
     }
     
     public void setKtnb12ModelList(List<Ktnb12Model> ktnb12ModelList) {
         this.ktnb12ModelList = ktnb12ModelList;
     }
     
     public List<String> getKT_DV() {
         return KT_DV;
     }
     
     public void setKT_DV(List<String> KT_DV) {
         this.KT_DV = KT_DV;
     }
     
     public List<String> getKT_SO_VB_NEW() {
         return KT_SO_VB_NEW;
     }
     
     public void setKT_SO_VB_NEW(List<String> KT_SO_VB_NEW) {
         this.KT_SO_VB_NEW = KT_SO_VB_NEW;
     }
     
     public List<String> getKT_SO_VB_BS() {
         return KT_SO_VB_BS;
     }
     
     public void setKT_SO_VB_BS(List<String> KT_SO_VB_BS) {
         this.KT_SO_VB_BS = KT_SO_VB_BS;
     }
     
     public List<String> getKT_SO_LOP_TH() {
         return KT_SO_LOP_TH;
     }
     
     public void setKT_SO_LOP_TH(List<String> KT_SO_LOP_TH) {
         this.KT_SO_LOP_TH = KT_SO_LOP_TH;
     }
     
     public List<String> getKT_SO_NG_TH() {
         return KT_SO_NG_TH;
     }
     
     public void setKT_SO_NG_TH(List<String> KT_SO_NG_TH) {
         this.KT_SO_NG_TH = KT_SO_NG_TH;
     }
     
     public List<String> getKT_SO_CUOC() {
         return KT_SO_CUOC;
     }
     
     public void setKT_SO_CUOC(List<String> KT_SO_CUOC) {
         this.KT_SO_CUOC = KT_SO_CUOC;
     }
     
     public List<String> getKT_SO_DV() {
         return KT_SO_DV;
     }
     
     public void setKT_SO_DV(List<String> KT_SO_DV) {
         this.KT_SO_DV = KT_SO_DV;
     }
     
     public List<String> getKT_SO_DV_VP() {
         return KT_SO_DV_VP;
     }
     
     public void setKT_SO_DV_VP(List<String> KT_SO_DV_VP) {
         this.KT_SO_DV_VP = KT_SO_DV_VP;
     }
     
     public List<String> getKT_SO_TOCHUC1() {
         return KT_SO_TOCHUC1;
     }
     
     public void setKT_SO_TOCHUC1(List<String> KT_SO_TOCHUC1) {
         this.KT_SO_TOCHUC1 = KT_SO_TOCHUC1;
     }
     
     public List<String> getKT_SO_CANHAN1() {
         return KT_SO_CANHAN1;
     }
     
     public void setKT_SO_CANHAN1(List<String> KT_SO_CANHAN1) {
         this.KT_SO_CANHAN1 = KT_SO_CANHAN1;
     }
     
     public List<String> getKT_SO_TOCHUC2() {
         return KT_SO_TOCHUC2;
     }
     
     public void setKT_SO_TOCHUC2(List<String> KT_SO_TOCHUC2) {
         this.KT_SO_TOCHUC2 = KT_SO_TOCHUC2;
     }
     
     public List<String> getKT_SO_CANHAN2() {
         return KT_SO_CANHAN2;
     }
     
     public void setKT_SO_CANHAN2(List<String> KT_SO_CANHAN2) {
         this.KT_SO_CANHAN2 = KT_SO_CANHAN2;
     }
     
     public List<String> getKT_TONG_KLTT() {
         return KT_TONG_KLTT;
     }
     
     public void setKT_TONG_KLTT(List<String> KT_TONG_KLTT) {
         this.KT_TONG_KLTT = KT_TONG_KLTT;
     }
     
     public List<String> getKT_SO_TOCHUC3() {
         return KT_SO_TOCHUC3;
     }
     
     public void setKT_SO_TOCHUC3(List<String> KT_SO_TOCHUC3) {
         this.KT_SO_TOCHUC3 = KT_SO_TOCHUC3;
     }
     
     public List<String> getKT_SO_CANHAN3() {
         return KT_SO_CANHAN3;
     }
     
     public void setKT_SO_CANHAN3(List<String> KT_SO_CANHAN3) {
         this.KT_SO_CANHAN3 = KT_SO_CANHAN3;
     }
     
     public List<String> getKT_SO_TOCHUC4() {
         return KT_SO_TOCHUC4;
     }
     
     public void setKT_SO_TOCHUC4(List<String> KT_SO_TOCHUC4) {
         this.KT_SO_TOCHUC4 = KT_SO_TOCHUC4;
     }
     
     public List<String> getKT_SO_CANHAN4() {
         return KT_SO_CANHAN4;
     }
     
     public void setKT_SO_CANHAN4(List<String> KT_SO_CANHAN4) {
         this.KT_SO_CANHAN4 = KT_SO_CANHAN4;
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
