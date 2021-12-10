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
import vbsp.ims.dao.ktnb.DaoKtnb11;
import vbsp.ims.dao.ktnb.ListKTNBDA;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.Ktnb10Model;
import vbsp.ims.model.ktnb.Ktnb11Model;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.syn.ProcessReportSyn;

/**
 *
 * @author CuongBM0211
 */
public class Ktnb11ActionSupport extends ActionSupport implements ServletRequestAware{
    private DaoKtnb11 daoKtnb11 = new DaoKtnb11();
    private ListKTNBDA listKTNBDA = new ListKTNBDA();
    private List<Ktnb11Model> ktnb11ModelList;  //Lay du lieu load len table
    
    //Cac truong dung cho luu du lieu
    private List<String> KT_DV;
    private List<String> KT_DKN_TS;
    private List<String> KT_DKN_TD_TK;
    private List<String> KT_DKN_TD_KT;
    private List<String> KT_DKN_TD_TS;
    private List<String> KT_KQ_DGQ_SD;
    private List<String> KT_KQ_DGQ_SVV;
    private List<String> KT_KQ_PT_TCD;
    private List<String> KT_KQ_PT_TCS;
    private List<String> KT_KQ_PT_TCD1;
    private List<String> KT_KQ_KN_T;
    private List<String> KT_KQ_KN_D;
    private List<String> KT_KQ_TL_T;
    private List<String> KT_KQ_TL_D;
    private List<String> KT_KQ_SN;
    private List<String> KT_KQ_KN_TS;
    private List<String> KT_KQ_KN_SN;
    private List<String> KT_KQ_CCQ_SV;
    private List<String> KT_KQ_CCQ_SDT;
    private List<String> KT_KQ_CCQ_KQ_SV;
    private List<String> KT_KQ_CCQ_KQ_SDT;
    private List<String> KT_KQ_CCQ_KQ_DTH;
    private List<String> KT_KQ_CCQ_KQ_QTH;
    private List<String> KT_TH_TS;
    private List<String> KT_TH_DTH;
    private List<String> KT_TH_THNN_PT_T;
    private List<String> KT_TH_THNN_PT_D;
    private List<String> KT_TH_THNN_DT_T;
    private List<String> KT_TH_THNN_DT_D;
    private List<String> KT_TH_TL_PT_T;
    private List<String> KT_TH_TL_PT_D;
    private List<String> KT_TH_TL_DT_T;
    private List<String> KT_TH_TL_DT_D;
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
    
    public Ktnb11ActionSupport() {
    }
    
    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public String get_data_ktnb11(){
        getInfo();
        ktnb11ModelList = daoKtnb11.get_ktnb11(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
    }
    
    public String ResetDataInput11(){
        getInfo();
        ktnb11ModelList = daoKtnb11.get_ktnb11_default(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
    }
    
    public String get_data_ktnb11_auth() throws SQLException{
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
            } catch (Exception ex) {
                System.err.println("Loi " + ex.getMessage());
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
        ktnb11ModelList = daoKtnb11.get_ktnb11_auth(strPosCD, Integer.parseInt(namBc), Integer.parseInt(quyBc),"N");
        return "success";
    }
    
    public String save_data_ktnb11(){
        if(daoKtnb11.save_ktnb11(posCD,maCn,quyBc,namBc,userId,KT_DV,KT_DKN_TS,KT_DKN_TD_TK,KT_DKN_TD_KT,KT_DKN_TD_TS,KT_KQ_DGQ_SD,KT_KQ_DGQ_SVV,KT_KQ_PT_TCD,KT_KQ_PT_TCS,KT_KQ_PT_TCD1,KT_KQ_KN_T,KT_KQ_KN_D,KT_KQ_TL_T,KT_KQ_TL_D,KT_KQ_SN,KT_KQ_KN_TS,KT_KQ_KN_SN,KT_KQ_CCQ_SV,KT_KQ_CCQ_SDT,KT_KQ_CCQ_KQ_SV,KT_KQ_CCQ_KQ_SDT,KT_KQ_CCQ_KQ_DTH,KT_KQ_CCQ_KQ_QTH,KT_TH_TS,KT_TH_DTH,KT_TH_THNN_PT_T,KT_TH_THNN_PT_D,KT_TH_THNN_DT_T,KT_TH_THNN_DT_D,KT_TH_TL_PT_T,KT_TH_TL_PT_D,KT_TH_TL_DT_T,KT_TH_TL_DT_D,KT_GHICHU,KT_DN,KT_CO_DINH,KT_THEM,KT_XOA,KT_FONTWEIGHT,KT_CAPHT,KT_STT,NG_CAPNHAT,""))
            return "success";
        else
            return "error"; 
        
        //DONG BO DU LIEU LEN TW
//        HttpSession session = request.getSession();
//        String reporGrade = session.getAttribute("reportGrade").toString();
//        if (!reporGrade.equals("3")) {
//            //Lay duong dan va ten xml se ghi ra
//             String pathSave = !request.getRealPath("/").endsWith("/")
//                    ? request.getRealPath("/") + "/" + Define.M_REPORT_XML
//                    : request.getRealPath("/") + Define.M_REPORT_XML;
//                    pathSave+= posCD + "_BCNT_KTNB_"+"11"+".xml";    //Thay ma bao cao
//            String reportDate =  new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
//            
//            HashMap<String, String> hmData = new HashMap<String, String>();
//            
//            hmData.put("COT_1",DefineFun.converArrayList2String(KT_DV));
//            hmData.put("COT_2",DefineFun.converArrayList2String(KT_DKN_TS));
//            hmData.put("COT_3",DefineFun.converArrayList2String(KT_DKN_TD_TK));
//            hmData.put("COT_4",DefineFun.converArrayList2String(KT_DKN_TD_KT));
//            hmData.put("COT_5",DefineFun.converArrayList2String(KT_DKN_TD_TS));
//            hmData.put("COT_6",DefineFun.converArrayList2String(KT_KQ_DGQ_SD));
//            hmData.put("COT_7",DefineFun.converArrayList2String(KT_KQ_DGQ_SVV));
//            hmData.put("COT_8",DefineFun.converArrayList2String(KT_KQ_PT_TCD));
//            hmData.put("COT_9",DefineFun.converArrayList2String(KT_KQ_PT_TCS));
//            hmData.put("COT_10",DefineFun.converArrayList2String(KT_KQ_PT_TCD1));
//            hmData.put("COT_11",DefineFun.converArrayList2String(KT_KQ_KN_T));
//            hmData.put("COT_12",DefineFun.converArrayList2String(KT_KQ_KN_D));
//            hmData.put("COT_13",DefineFun.converArrayList2String(KT_KQ_TL_T));
//            hmData.put("COT_14",DefineFun.converArrayList2String(KT_KQ_TL_D));
//            hmData.put("COT_15",DefineFun.converArrayList2String(KT_KQ_SN));
//            hmData.put("COT_16",DefineFun.converArrayList2String(KT_KQ_KN_TS));
//            hmData.put("COT_17",DefineFun.converArrayList2String(KT_KQ_KN_SN));
//            hmData.put("COT_18",DefineFun.converArrayList2String(KT_KQ_CCQ_SV));
//            hmData.put("COT_19",DefineFun.converArrayList2String(KT_KQ_CCQ_SDT));
//            hmData.put("COT_20",DefineFun.converArrayList2String(KT_KQ_CCQ_KQ_SV));
//            hmData.put("COT_21",DefineFun.converArrayList2String(KT_KQ_CCQ_KQ_SDT));
//            hmData.put("COT_22",DefineFun.converArrayList2String(KT_KQ_CCQ_KQ_DTH));
//            hmData.put("COT_23",DefineFun.converArrayList2String(KT_KQ_CCQ_KQ_QTH));
//            hmData.put("COT_24",DefineFun.converArrayList2String(KT_TH_TS));
//            hmData.put("COT_25",DefineFun.converArrayList2String(KT_TH_DTH));
//            hmData.put("COT_26",DefineFun.converArrayList2String(KT_TH_THNN_PT_T));
//            hmData.put("COT_27",DefineFun.converArrayList2String(KT_TH_THNN_PT_D));
//            hmData.put("COT_28",DefineFun.converArrayList2String(KT_TH_THNN_DT_T));
//            hmData.put("COT_29",DefineFun.converArrayList2String(KT_TH_THNN_DT_D));
//            hmData.put("COT_30",DefineFun.converArrayList2String(KT_TH_TL_PT_T));
//            hmData.put("COT_31",DefineFun.converArrayList2String(KT_TH_TL_PT_D));
//            hmData.put("COT_32",DefineFun.converArrayList2String(KT_TH_TL_DT_T));
//            hmData.put("COT_33",DefineFun.converArrayList2String(KT_TH_TL_DT_D));
//            hmData.put("COT_34",DefineFun.converArrayList2String(KT_GHICHU));
//            hmData.put("COT_35",DefineFun.converArrayList2String(KT_DN));
//            hmData.put("COT_36",DefineFun.converArrayList2String(KT_CO_DINH));
//            hmData.put("COT_37",DefineFun.converArrayList2String(KT_THEM));
//            hmData.put("COT_38",DefineFun.converArrayList2String(KT_XOA));
//            hmData.put("COT_39",DefineFun.converArrayList2String(KT_FONTWEIGHT));
//            hmData.put("COT_40",DefineFun.converArrayList2String(KT_CAPHT));
//            hmData.put("COT_41",DefineFun.converArrayList2String(KT_STT));
//            hmData.put("COT_42",DefineFun.converArrayList2String(NG_CAPNHAT));
//            
//            //Tao file xml theo cau truc
//            ProcessReportSyn clientWritexml = new ProcessReportSyn();
//            boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKTNB(Define.PARA_SYN_REPORT_KTNB,
//                    Define.SYN_REPORT_KTNB11, reportDate, userId, posCD,maCn, reporGrade, "BCNT", hmData,quyBc,namBc, pathSave);
//            if (!bSuccess) {
//                System.err.println("Ban chua dong bo du lieu duoc ve TW");
//            }
//        }
//        //========== END DONG BO DU LIEU =======================================
//        
//        return "success";Bạn phải xem và chọn tất cả các PGD để gửi
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
             System.err.print("so sánh " + lsPos.length() + " - " + listOfPos.length());
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
         if(!daoKtnb11.save_ktnb11_auth(pos_auth,quyBc,namBc))
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
                    pathSave+= sPOS + "_BCNT_KTNB_"+"11"+".xml";    //Thay ma bao cao
            String reportDate =  new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
            
            HashMap<String, String> hmData = new HashMap<String, String>();
            
            List<Ktnb11Model> ktnb11ModelListSend = null;

            List<String> strKT_DV1 = new ArrayList<String>();
            List<String> strKT_DKN_TS2 = new ArrayList<String>();
            List<String> strKT_DKN_TD_TK3 = new ArrayList<String>();
            List<String> strKT_DKN_TD_KT4 = new ArrayList<String>();
            List<String> strKT_DKN_TD_TS5 = new ArrayList<String>();
            List<String> strKT_KQ_DGQ_SD6 = new ArrayList<String>();
            List<String> strKT_KQ_DGQ_SVV7 = new ArrayList<String>();
            List<String> strKT_KQ_PT_TCD8 = new ArrayList<String>();
            List<String> strKT_KQ_PT_TCS9 = new ArrayList<String>();
            List<String> strKT_KQ_PT_TCD110 = new ArrayList<String>();
            List<String> strKT_KQ_KN_T11 = new ArrayList<String>();
            List<String> strKT_KQ_KN_D12 = new ArrayList<String>();
            List<String> strKT_KQ_TL_T13 = new ArrayList<String>();
            List<String> strKT_KQ_TL_D14 = new ArrayList<String>();
            List<String> strKT_KQ_SN15 = new ArrayList<String>();
            List<String> strKT_KQ_KN_TS16 = new ArrayList<String>();
            List<String> strKT_KQ_KN_SN17 = new ArrayList<String>();
            List<String> strKT_KQ_CCQ_SV18 = new ArrayList<String>();
            List<String> strKT_KQ_CCQ_SDT19 = new ArrayList<String>();
            List<String> strKT_KQ_CCQ_KQ_SV20 = new ArrayList<String>();
            List<String> strKT_KQ_CCQ_KQ_SDT21 = new ArrayList<String>();
            List<String> strKT_KQ_CCQ_KQ_DTH22 = new ArrayList<String>();
            List<String> strKT_KQ_CCQ_KQ_QTH23 = new ArrayList<String>();
            List<String> strKT_TH_TS24 = new ArrayList<String>();
            List<String> strKT_TH_DTH25 = new ArrayList<String>();
            List<String> strKT_TH_THNN_PT_T26 = new ArrayList<String>();
            List<String> strKT_TH_THNN_PT_D27 = new ArrayList<String>();
            List<String> strKT_TH_THNN_DT_T28 = new ArrayList<String>();
            List<String> strKT_TH_THNN_DT_D29 = new ArrayList<String>();
            List<String> strKT_TH_TL_PT_T30 = new ArrayList<String>();
            List<String> strKT_TH_TL_PT_D31 = new ArrayList<String>();
            List<String> strKT_TH_TL_DT_T32 = new ArrayList<String>();
            List<String> strKT_TH_TL_DT_D33 = new ArrayList<String>();
            List<String> strKT_GHICHU34 = new ArrayList<String>();
            List<String> strKT_DN35 = new ArrayList<String>();
            List<String> strKT_CO_DINH36 = new ArrayList<String>();
            List<String> strKT_THEM37 = new ArrayList<String>();
            List<String> strKT_XOA38 = new ArrayList<String>();
            List<String> strKT_FONTWEIGHT39 = new ArrayList<String>();
            List<String> strKT_CAPHT40 = new ArrayList<String>();
            List<String> strKT_STT41 = new ArrayList<String>();
            List<String> strNG_CAPNHAT42 = new ArrayList<String>();
            
            
            ktnb11ModelListSend = daoKtnb11.get_ktnb11_auth(sPOS, Integer.parseInt(namBc), Integer.parseInt(quyBc),"Y");
            if(ktnb11ModelListSend.size()<=0)
                return false;
            for (int i = 0; i < ktnb11ModelListSend.size(); i++) {

                strKT_DV1.add(ktnb11ModelListSend.get(i).getKT_DV());
                strKT_DKN_TS2.add(Double.toString(ktnb11ModelListSend.get(i).getKT_DKN_TS()));
                strKT_DKN_TD_TK3.add(Double.toString(ktnb11ModelListSend.get(i).getKT_DKN_TD_TK()));
                strKT_DKN_TD_KT4.add(Double.toString(ktnb11ModelListSend.get(i).getKT_DKN_TD_KT()));
                strKT_DKN_TD_TS5.add(Double.toString(ktnb11ModelListSend.get(i).getKT_DKN_TD_TS()));
                strKT_KQ_DGQ_SD6.add(Double.toString(ktnb11ModelListSend.get(i).getKT_KQ_DGQ_SD()));
                strKT_KQ_DGQ_SVV7.add(Double.toString(ktnb11ModelListSend.get(i).getKT_KQ_DGQ_SVV()));
                strKT_KQ_PT_TCD8.add(Double.toString(ktnb11ModelListSend.get(i).getKT_KQ_PT_TCD()));
                strKT_KQ_PT_TCS9.add(Double.toString(ktnb11ModelListSend.get(i).getKT_KQ_PT_TCS()));
                strKT_KQ_PT_TCD110.add(Double.toString(ktnb11ModelListSend.get(i).getKT_KQ_PT_TCD1()));
                strKT_KQ_KN_T11.add(Double.toString(ktnb11ModelListSend.get(i).getKT_KQ_KN_T()));
                strKT_KQ_KN_D12.add(Double.toString(ktnb11ModelListSend.get(i).getKT_KQ_KN_D()));
                strKT_KQ_TL_T13.add(Double.toString(ktnb11ModelListSend.get(i).getKT_KQ_TL_T()));
                strKT_KQ_TL_D14.add(Double.toString(ktnb11ModelListSend.get(i).getKT_KQ_TL_D()));
                strKT_KQ_SN15.add(Double.toString(ktnb11ModelListSend.get(i).getKT_KQ_SN()));
                strKT_KQ_KN_TS16.add(Double.toString(ktnb11ModelListSend.get(i).getKT_KQ_KN_TS()));
                strKT_KQ_KN_SN17.add(Double.toString(ktnb11ModelListSend.get(i).getKT_KQ_KN_SN()));
                strKT_KQ_CCQ_SV18.add(Double.toString(ktnb11ModelListSend.get(i).getKT_KQ_CCQ_SV()));
                strKT_KQ_CCQ_SDT19.add(Double.toString(ktnb11ModelListSend.get(i).getKT_KQ_CCQ_SDT()));
                strKT_KQ_CCQ_KQ_SV20.add(Double.toString(ktnb11ModelListSend.get(i).getKT_KQ_CCQ_KQ_SV()));
                strKT_KQ_CCQ_KQ_SDT21.add(Double.toString(ktnb11ModelListSend.get(i).getKT_KQ_CCQ_KQ_SDT()));
                strKT_KQ_CCQ_KQ_DTH22.add(Double.toString(ktnb11ModelListSend.get(i).getKT_KQ_CCQ_KQ_DTH()));
                strKT_KQ_CCQ_KQ_QTH23.add(Double.toString(ktnb11ModelListSend.get(i).getKT_KQ_CCQ_KQ_QTH()));
                strKT_TH_TS24.add(Double.toString(ktnb11ModelListSend.get(i).getKT_TH_TS()));
                strKT_TH_DTH25.add(Double.toString(ktnb11ModelListSend.get(i).getKT_TH_DTH()));
                strKT_TH_THNN_PT_T26.add(Double.toString(ktnb11ModelListSend.get(i).getKT_TH_THNN_PT_T()));
                strKT_TH_THNN_PT_D27.add(Double.toString(ktnb11ModelListSend.get(i).getKT_TH_THNN_PT_D()));
                strKT_TH_THNN_DT_T28.add(Double.toString(ktnb11ModelListSend.get(i).getKT_TH_THNN_DT_T()));
                strKT_TH_THNN_DT_D29.add(Double.toString(ktnb11ModelListSend.get(i).getKT_TH_THNN_DT_D()));
                strKT_TH_TL_PT_T30.add(Double.toString(ktnb11ModelListSend.get(i).getKT_TH_TL_PT_T()));
                strKT_TH_TL_PT_D31.add(Double.toString(ktnb11ModelListSend.get(i).getKT_TH_TL_PT_D()));
                strKT_TH_TL_DT_T32.add(Double.toString(ktnb11ModelListSend.get(i).getKT_TH_TL_DT_T()));
                strKT_TH_TL_DT_D33.add(Double.toString(ktnb11ModelListSend.get(i).getKT_TH_TL_DT_D()));
                strKT_GHICHU34.add(ktnb11ModelListSend.get(i).getKT_GHICHU());
                strKT_DN35.add(ktnb11ModelListSend.get(i).getKT_DN());
                strKT_CO_DINH36.add(ktnb11ModelListSend.get(i).getKT_CO_DINH());
                strKT_THEM37.add(ktnb11ModelListSend.get(i).getKT_THEM());
                strKT_XOA38.add(ktnb11ModelListSend.get(i).getKT_XOA());
                strKT_FONTWEIGHT39.add(ktnb11ModelListSend.get(i).getKT_FONTWEIGHT());
                strKT_CAPHT40.add(Double.toString(ktnb11ModelListSend.get(i).getKT_CAPHT()));
                strKT_STT41.add(Double.toString(ktnb11ModelListSend.get(i).getKT_STT()));
                strNG_CAPNHAT42.add(ktnb11ModelListSend.get(i).getNG_CAPNHAT());
                
                
            }
            hmData.put("COT_1",DefineFun.converArrayList2String(strKT_DV1));
            hmData.put("COT_2",DefineFun.converArrayList2String(strKT_DKN_TS2));
            hmData.put("COT_3",DefineFun.converArrayList2String(strKT_DKN_TD_TK3));
            hmData.put("COT_4",DefineFun.converArrayList2String(strKT_DKN_TD_KT4));
            hmData.put("COT_5",DefineFun.converArrayList2String(strKT_DKN_TD_TS5));
            hmData.put("COT_6",DefineFun.converArrayList2String(strKT_KQ_DGQ_SD6));
            hmData.put("COT_7",DefineFun.converArrayList2String(strKT_KQ_DGQ_SVV7));
            hmData.put("COT_8",DefineFun.converArrayList2String(strKT_KQ_PT_TCD8));
            hmData.put("COT_9",DefineFun.converArrayList2String(strKT_KQ_PT_TCS9));
            hmData.put("COT_10",DefineFun.converArrayList2String(strKT_KQ_PT_TCD110));
            hmData.put("COT_11",DefineFun.converArrayList2String(strKT_KQ_KN_T11));
            hmData.put("COT_12",DefineFun.converArrayList2String(strKT_KQ_KN_D12));
            hmData.put("COT_13",DefineFun.converArrayList2String(strKT_KQ_TL_T13));
            hmData.put("COT_14",DefineFun.converArrayList2String(strKT_KQ_TL_D14));
            hmData.put("COT_15",DefineFun.converArrayList2String(strKT_KQ_SN15));
            hmData.put("COT_16",DefineFun.converArrayList2String(strKT_KQ_KN_TS16));
            hmData.put("COT_17",DefineFun.converArrayList2String(strKT_KQ_KN_SN17));
            hmData.put("COT_18",DefineFun.converArrayList2String(strKT_KQ_CCQ_SV18));
            hmData.put("COT_19",DefineFun.converArrayList2String(strKT_KQ_CCQ_SDT19));
            hmData.put("COT_20",DefineFun.converArrayList2String(strKT_KQ_CCQ_KQ_SV20));
            hmData.put("COT_21",DefineFun.converArrayList2String(strKT_KQ_CCQ_KQ_SDT21));
            hmData.put("COT_22",DefineFun.converArrayList2String(strKT_KQ_CCQ_KQ_DTH22));
            hmData.put("COT_23",DefineFun.converArrayList2String(strKT_KQ_CCQ_KQ_QTH23));
            hmData.put("COT_24",DefineFun.converArrayList2String(strKT_TH_TS24));
            hmData.put("COT_25",DefineFun.converArrayList2String(strKT_TH_DTH25));
            hmData.put("COT_26",DefineFun.converArrayList2String(strKT_TH_THNN_PT_T26));
            hmData.put("COT_27",DefineFun.converArrayList2String(strKT_TH_THNN_PT_D27));
            hmData.put("COT_28",DefineFun.converArrayList2String(strKT_TH_THNN_DT_T28));
            hmData.put("COT_29",DefineFun.converArrayList2String(strKT_TH_THNN_DT_D29));
            hmData.put("COT_30",DefineFun.converArrayList2String(strKT_TH_TL_PT_T30));
            hmData.put("COT_31",DefineFun.converArrayList2String(strKT_TH_TL_PT_D31));
            hmData.put("COT_32",DefineFun.converArrayList2String(strKT_TH_TL_DT_T32));
            hmData.put("COT_33",DefineFun.converArrayList2String(strKT_TH_TL_DT_D33));
            hmData.put("COT_34",DefineFun.converArrayList2String(strKT_GHICHU34));
            hmData.put("COT_35",DefineFun.converArrayList2String(strKT_DN35));
            hmData.put("COT_36",DefineFun.converArrayList2String(strKT_CO_DINH36));
            hmData.put("COT_37",DefineFun.converArrayList2String(strKT_THEM37));
            hmData.put("COT_38",DefineFun.converArrayList2String(strKT_XOA38));
            hmData.put("COT_39",DefineFun.converArrayList2String(strKT_FONTWEIGHT39));
            hmData.put("COT_40",DefineFun.converArrayList2String(strKT_CAPHT40));
            hmData.put("COT_41",DefineFun.converArrayList2String(strKT_STT41));
            hmData.put("COT_42",DefineFun.converArrayList2String(strNG_CAPNHAT42));
            
            //Tao file xml theo cau truc
            ProcessReportSyn clientWritexml = new ProcessReportSyn();
            boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKTNB(Define.PARA_SYN_REPORT_KTNB,
                    Define.SYN_REPORT_KTNB11, reportDate, userId, sPOS, maCn, reporGrade, "BCNT", hmData,quyBc,namBc, pathSave);
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
     
     public List<Ktnb11Model> getKtnb11ModelList() {
         return ktnb11ModelList;
     }
     
     public void setKtnb11ModelList(List<Ktnb11Model> ktnb11ModelList) {
         this.ktnb11ModelList = ktnb11ModelList;
     }
     
     public List<String> getKT_DV() {
         return KT_DV;
     }
     
     public void setKT_DV(List<String> KT_DV) {
         this.KT_DV = KT_DV;
     }
     
     public List<String> getKT_DKN_TS() {
         return KT_DKN_TS;
     }
     
     public void setKT_DKN_TS(List<String> KT_DKN_TS) {
         this.KT_DKN_TS = KT_DKN_TS;
     }
     
     public List<String> getKT_DKN_TD_TK() {
         return KT_DKN_TD_TK;
     }
     
     public void setKT_DKN_TD_TK(List<String> KT_DKN_TD_TK) {
         this.KT_DKN_TD_TK = KT_DKN_TD_TK;
     }
     
     public List<String> getKT_DKN_TD_KT() {
         return KT_DKN_TD_KT;
     }
     
     public void setKT_DKN_TD_KT(List<String> KT_DKN_TD_KT) {
         this.KT_DKN_TD_KT = KT_DKN_TD_KT;
     }
     
     public List<String> getKT_DKN_TD_TS() {
         return KT_DKN_TD_TS;
     }
     
     public void setKT_DKN_TD_TS(List<String> KT_DKN_TD_TS) {
         this.KT_DKN_TD_TS = KT_DKN_TD_TS;
     }
     
     public List<String> getKT_KQ_DGQ_SD() {
         return KT_KQ_DGQ_SD;
     }
     
     public void setKT_KQ_DGQ_SD(List<String> KT_KQ_DGQ_SD) {
         this.KT_KQ_DGQ_SD = KT_KQ_DGQ_SD;
     }
     
     public List<String> getKT_KQ_DGQ_SVV() {
         return KT_KQ_DGQ_SVV;
     }
     
     public void setKT_KQ_DGQ_SVV(List<String> KT_KQ_DGQ_SVV) {
         this.KT_KQ_DGQ_SVV = KT_KQ_DGQ_SVV;
     }
     
     public List<String> getKT_KQ_PT_TCD() {
         return KT_KQ_PT_TCD;
     }
     
     public void setKT_KQ_PT_TCD(List<String> KT_KQ_PT_TCD) {
         this.KT_KQ_PT_TCD = KT_KQ_PT_TCD;
     }
     
     public List<String> getKT_KQ_PT_TCS() {
         return KT_KQ_PT_TCS;
     }
     
     public void setKT_KQ_PT_TCS(List<String> KT_KQ_PT_TCS) {
         this.KT_KQ_PT_TCS = KT_KQ_PT_TCS;
     }
     
     public List<String> getKT_KQ_PT_TCD1() {
         return KT_KQ_PT_TCD1;
     }
     
     public void setKT_KQ_PT_TCD1(List<String> KT_KQ_PT_TCD1) {
         this.KT_KQ_PT_TCD1 = KT_KQ_PT_TCD1;
     }
     
     public List<String> getKT_KQ_KN_T() {
         return KT_KQ_KN_T;
     }
     
     public void setKT_KQ_KN_T(List<String> KT_KQ_KN_T) {
         this.KT_KQ_KN_T = KT_KQ_KN_T;
     }
     
     public List<String> getKT_KQ_KN_D() {
         return KT_KQ_KN_D;
     }
     
     public void setKT_KQ_KN_D(List<String> KT_KQ_KN_D) {
         this.KT_KQ_KN_D = KT_KQ_KN_D;
     }
     
     public List<String> getKT_KQ_TL_T() {
         return KT_KQ_TL_T;
     }
     
     public void setKT_KQ_TL_T(List<String> KT_KQ_TL_T) {
         this.KT_KQ_TL_T = KT_KQ_TL_T;
     }
     
     public List<String> getKT_KQ_TL_D() {
         return KT_KQ_TL_D;
     }
     
     public void setKT_KQ_TL_D(List<String> KT_KQ_TL_D) {
         this.KT_KQ_TL_D = KT_KQ_TL_D;
     }
     
     public List<String> getKT_KQ_SN() {
         return KT_KQ_SN;
     }
     
     public void setKT_KQ_SN(List<String> KT_KQ_SN) {
         this.KT_KQ_SN = KT_KQ_SN;
     }
     
     public List<String> getKT_KQ_KN_TS() {
         return KT_KQ_KN_TS;
     }
     
     public void setKT_KQ_KN_TS(List<String> KT_KQ_KN_TS) {
         this.KT_KQ_KN_TS = KT_KQ_KN_TS;
     }
     
     public List<String> getKT_KQ_KN_SN() {
         return KT_KQ_KN_SN;
     }
     
     public void setKT_KQ_KN_SN(List<String> KT_KQ_KN_SN) {
         this.KT_KQ_KN_SN = KT_KQ_KN_SN;
     }
     
     public List<String> getKT_KQ_CCQ_SV() {
         return KT_KQ_CCQ_SV;
     }
     
     public void setKT_KQ_CCQ_SV(List<String> KT_KQ_CCQ_SV) {
         this.KT_KQ_CCQ_SV = KT_KQ_CCQ_SV;
     }
     
     public List<String> getKT_KQ_CCQ_SDT() {
         return KT_KQ_CCQ_SDT;
     }
     
     public void setKT_KQ_CCQ_SDT(List<String> KT_KQ_CCQ_SDT) {
         this.KT_KQ_CCQ_SDT = KT_KQ_CCQ_SDT;
     }
     
     public List<String> getKT_KQ_CCQ_KQ_SV() {
         return KT_KQ_CCQ_KQ_SV;
     }
     
     public void setKT_KQ_CCQ_KQ_SV(List<String> KT_KQ_CCQ_KQ_SV) {
         this.KT_KQ_CCQ_KQ_SV = KT_KQ_CCQ_KQ_SV;
     }
     
     public List<String> getKT_KQ_CCQ_KQ_SDT() {
         return KT_KQ_CCQ_KQ_SDT;
     }
     
     public void setKT_KQ_CCQ_KQ_SDT(List<String> KT_KQ_CCQ_KQ_SDT) {
         this.KT_KQ_CCQ_KQ_SDT = KT_KQ_CCQ_KQ_SDT;
     }
     
     public List<String> getKT_KQ_CCQ_KQ_DTH() {
         return KT_KQ_CCQ_KQ_DTH;
     }
     
     public void setKT_KQ_CCQ_KQ_DTH(List<String> KT_KQ_CCQ_KQ_DTH) {
         this.KT_KQ_CCQ_KQ_DTH = KT_KQ_CCQ_KQ_DTH;
     }
     
     public List<String> getKT_KQ_CCQ_KQ_QTH() {
         return KT_KQ_CCQ_KQ_QTH;
     }
     
     public void setKT_KQ_CCQ_KQ_QTH(List<String> KT_KQ_CCQ_KQ_QTH) {
         this.KT_KQ_CCQ_KQ_QTH = KT_KQ_CCQ_KQ_QTH;
     }
     
     public List<String> getKT_TH_TS() {
         return KT_TH_TS;
     }
     
     public void setKT_TH_TS(List<String> KT_TH_TS) {
         this.KT_TH_TS = KT_TH_TS;
     }
     
     public List<String> getKT_TH_DTH() {
         return KT_TH_DTH;
     }
     
     public void setKT_TH_DTH(List<String> KT_TH_DTH) {
         this.KT_TH_DTH = KT_TH_DTH;
     }
     
     public List<String> getKT_TH_THNN_PT_T() {
         return KT_TH_THNN_PT_T;
     }
     
     public void setKT_TH_THNN_PT_T(List<String> KT_TH_THNN_PT_T) {
         this.KT_TH_THNN_PT_T = KT_TH_THNN_PT_T;
     }
     
     public List<String> getKT_TH_THNN_PT_D() {
         return KT_TH_THNN_PT_D;
     }
     
     public void setKT_TH_THNN_PT_D(List<String> KT_TH_THNN_PT_D) {
         this.KT_TH_THNN_PT_D = KT_TH_THNN_PT_D;
     }
     
     public List<String> getKT_TH_THNN_DT_T() {
         return KT_TH_THNN_DT_T;
     }
     
     public void setKT_TH_THNN_DT_T(List<String> KT_TH_THNN_DT_T) {
         this.KT_TH_THNN_DT_T = KT_TH_THNN_DT_T;
     }
     
     public List<String> getKT_TH_THNN_DT_D() {
         return KT_TH_THNN_DT_D;
     }
     
     public void setKT_TH_THNN_DT_D(List<String> KT_TH_THNN_DT_D) {
         this.KT_TH_THNN_DT_D = KT_TH_THNN_DT_D;
     }
     
     public List<String> getKT_TH_TL_PT_T() {
         return KT_TH_TL_PT_T;
     }
     
     public void setKT_TH_TL_PT_T(List<String> KT_TH_TL_PT_T) {
         this.KT_TH_TL_PT_T = KT_TH_TL_PT_T;
     }
     
     public List<String> getKT_TH_TL_PT_D() {
         return KT_TH_TL_PT_D;
     }
     
     public void setKT_TH_TL_PT_D(List<String> KT_TH_TL_PT_D) {
         this.KT_TH_TL_PT_D = KT_TH_TL_PT_D;
     }
     
     public List<String> getKT_TH_TL_DT_T() {
         return KT_TH_TL_DT_T;
     }
     
     public void setKT_TH_TL_DT_T(List<String> KT_TH_TL_DT_T) {
         this.KT_TH_TL_DT_T = KT_TH_TL_DT_T;
     }
     
     public List<String> getKT_TH_TL_DT_D() {
         return KT_TH_TL_DT_D;
     }
     
     public void setKT_TH_TL_DT_D(List<String> KT_TH_TL_DT_D) {
         this.KT_TH_TL_DT_D = KT_TH_TL_DT_D;
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
