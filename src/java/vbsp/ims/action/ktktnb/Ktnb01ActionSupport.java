package vbsp.ims.action.ktktnb;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.SUCCESS;
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
import vbsp.ims.dao.IMSRptDao;
import vbsp.ims.dao.ktnb.DaoKtnb01;
import vbsp.ims.dao.ktnb.ListKTNBDA;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import static vbsp.ims.define.DefineFun.convert2OracleDateFormat;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.Ktnb01Model;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.syn.ProcessReportSyn;

/**
 *
 * @author CuongBM0211
 */
public class Ktnb01ActionSupport extends ActionSupport  implements ServletRequestAware{
    private DaoKtnb01 daoKtnb01 = new DaoKtnb01();
    private ListKTNBDA listKTNBDA = new ListKTNBDA();
    private List<Ktnb01Model> ktnb01ModelList;  //Lay du lieu load len table
    private List<String> lstPOS;
    private TreeNode nodes;
    private int reportGrade;
    private String userName;
    private String selectedPos;
    private TreeNode searchNodes;

    public TreeNode getSearchNodes() {
        return searchNodes;
    }

    public void setSearchNodes(TreeNode searchNodes) {
        this.searchNodes = searchNodes;
    }

    public String getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(String selectedPos) {
        this.selectedPos = selectedPos;
    }
    
    public TreeNode getNodes() {
        return nodes;
    }

    public void setNodes(TreeNode nodes) {
        this.nodes = nodes;
    }

    public List<String> getLstPOS() {
        return lstPOS;
    }

    public void setLstPOS(List<String> lstPOS) {
        this.lstPOS = lstPOS;
    }
    
    //Cac truong dung cho luu du lieu
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
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    private HttpServletRequest request = null;

    
    public Ktnb01ActionSupport() {
       
    }
    
    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public String get_data_ktnb01(){
        getInfo();
        ktnb01ModelList = daoKtnb01.get_ktnb01(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
    }
    
    public String ResetDataInput1(){
        getInfo();
        ktnb01ModelList = daoKtnb01.get_ktnb01_default(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
    }
    
    public String get_data_ktnb01_auth() throws SQLException{
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
    
    public String view1() throws SQLException{       
        getInfo();
        ktnb01ModelList = daoKtnb01.get_ktnb01(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
        return "success";
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
        ktnb01ModelList = daoKtnb01.get_ktnb01_auth(strPosCD, Integer.parseInt(namBc), Integer.parseInt(quyBc),"N");
        return "success";
    }
    
    public String save_data_ktnb01(){
        if(daoKtnb01.save_ktnb01(posCD,maCn,quyBc,namBc,userId,KT_KHOA,KT_SLT,KT_SLH,KT_SL_DGD,KT_SL_TKVV,""))
            return "success";
        else
            return "error";        
    }
    
    public String Auth1(){
        if(daoKtnb01.save_ktnb01(posCD,maCn,quyBc,namBc,userId,KT_KHOA,KT_SLT,KT_SLH,KT_SL_DGD,KT_SL_TKVV,""))
            return "success";
        else
            return "error";        
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
//        if(KT_STT_HT == null)
//            return "error-click";
        for (String pos_auth: listOfPos.split(",")){
         System.out.println(pos_auth);
         if(!daoKtnb01.save_ktnb01_auth(pos_auth,quyBc,namBc))
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
                    pathSave+= sPOS + "_BCNT_KTNB_"+"01"+".xml";    //Thay ma bao cao
            String reportDate =  new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
            
            HashMap<String, String> hmData = new HashMap<String, String>();
            
            List<Ktnb01Model> ktnb01ModelListSend = null;
            List<String> strKT_STT_HT1 = new ArrayList<String>();
            List<String> strKT_DKT2 = new ArrayList<String>();
            List<String> strKT_SLT3= new ArrayList<String>();
            List<String> strKT_SLH4= new ArrayList<String>();
            List<String> strKT_SL_DGD5= new ArrayList<String>();
            List<String> strKT_SL_TKVV6= new ArrayList<String>();
            List<String> strKT_DN7= new ArrayList<String>();
            List<String> strKT_CO_DINH8= new ArrayList<String>();
            List<String> strKT_THEM9= new ArrayList<String>();
            List<String> strKT_XOA10= new ArrayList<String>();
            List<String> strKT_FONTWEIGHT11= new ArrayList<String>();
            List<String> strKT_CAPHT12= new ArrayList<String>();
            List<String> strKT_STT13= new ArrayList<String>();
            List<String> strNG_CAPNHAT14= new ArrayList<String>();
            List<String> strKT_KHOA15= new ArrayList<String>();
            
            ktnb01ModelListSend = daoKtnb01.get_ktnb01_auth(sPOS, Integer.parseInt(namBc), Integer.parseInt(quyBc),"Y");
            if(ktnb01ModelListSend.size()<=0)
                return false;
            for (int i = 0; i < ktnb01ModelListSend.size(); i++) {

                strKT_STT_HT1.add(ktnb01ModelListSend.get(i).getKT_STT_HT());
                strKT_DKT2.add(ktnb01ModelListSend.get(i).getKT_DKT());
                strKT_SLT3.add(Double.toString(ktnb01ModelListSend.get(i).getKT_SLT()));
                strKT_SLH4.add(Double.toString(ktnb01ModelListSend.get(i).getKT_SLH()));
                strKT_SL_DGD5.add(Double.toString(ktnb01ModelListSend.get(i).getKT_SL_DGD()));
                strKT_SL_TKVV6.add(Double.toString(ktnb01ModelListSend.get(i).getKT_SL_TKVV()));
                strKT_DN7.add(ktnb01ModelListSend.get(i).getKT_DN());
                strKT_CO_DINH8.add(ktnb01ModelListSend.get(i).getKT_CO_DINH());
                strKT_THEM9.add(ktnb01ModelListSend.get(i).getKT_THEM());
                strKT_XOA10.add(ktnb01ModelListSend.get(i).getKT_XOA());
                strKT_FONTWEIGHT11.add(ktnb01ModelListSend.get(i).getKT_FONTWEIGHT());
                strKT_CAPHT12.add(Double.toString(ktnb01ModelListSend.get(i).getKT_CAPHT()));
                strNG_CAPNHAT14.add(ktnb01ModelListSend.get(i).getNG_CAPNHAT());
                strKT_STT13.add(Double.toString(ktnb01ModelListSend.get(i).getKT_STT()));
                strKT_KHOA15.add(ktnb01ModelListSend.get(i).getKT_KHOA());
            }
            hmData.put("COT_1",DefineFun.converArrayList2String(strKT_STT_HT1));
            hmData.put("COT_2",DefineFun.converArrayList2String(strKT_DKT2));
            hmData.put("COT_3",DefineFun.converArrayList2String(strKT_SLT3));
            hmData.put("COT_4",DefineFun.converArrayList2String(strKT_SLH4));
            hmData.put("COT_5",DefineFun.converArrayList2String(strKT_SL_DGD5));
            hmData.put("COT_6",DefineFun.converArrayList2String(strKT_SL_TKVV6));
            hmData.put("COT_7",DefineFun.converArrayList2String(strKT_DN7));
            hmData.put("COT_8",DefineFun.converArrayList2String(strKT_CO_DINH8));
            hmData.put("COT_9",DefineFun.converArrayList2String(strKT_THEM9));
            hmData.put("COT_10",DefineFun.converArrayList2String(strKT_XOA10));
            hmData.put("COT_11",DefineFun.converArrayList2String(strKT_FONTWEIGHT11));
            hmData.put("COT_12",DefineFun.converArrayList2String(strKT_CAPHT12));
            hmData.put("COT_13",DefineFun.converArrayList2String(strKT_STT13));
            hmData.put("COT_14",DefineFun.converArrayList2String(strNG_CAPNHAT14));
            hmData.put("COT_15",DefineFun.converArrayList2String(strKT_KHOA15));
            
            //Tao file xml theo cau truc
            ProcessReportSyn clientWritexml = new ProcessReportSyn();
            boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKTNB(Define.PARA_SYN_REPORT_KTNB,
                    Define.SYN_REPORT_KTNB01, reportDate, userId, sPOS, maCn, reporGrade, "BCNT", hmData,quyBc,namBc, pathSave);
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
//    
//    public boolean save_ktnb01()
//    {
//        List<Ktnb01Model> ktnb01ModelListSave = null;
//        for (int i = 0; i < KT_STT_HT.size(); i++) {
//            Ktnb01Model obj = new Ktnb01Model();
//            
//                obj.setKT_STT_HT(KT_STT_HT.get(i));
//                obj.setKT_DKT(KT_DKT.get(i));
//                obj.setKT_SLT(Double.parseDouble(KT_SLT.get(i)));
//                obj.setKT_SLH(Double.parseDouble(KT_SLH.get(i)));
//                obj.setKT_SL_DGD(Double.parseDouble(KT_SL_DGD.get(i)));
//                
//                obj.setKT_SL_TKVV(Double.parseDouble(KT_SL_TKVV.get(i)));
//                obj.setKT_DN(KT_DN.get(i));
//                obj.setKT_CO_DINH(KT_CO_DINH.get(i));
//                obj.setKT_THEM(KT_THEM.get(i));
//                obj.setKT_XOA(KT_XOA.get(i));               
//                obj.setKT_FONTWEIGHT(KT_FONTWEIGHT.get(i));
//                obj.setKT_CAPHT(Double.parseDouble(KT_CAPHT.get(i)));
//                obj.setKT_STT(Double.parseDouble(KT_STT.get(i)));
//                obj.setNG_CAPNHAT(NG_CAPNHAT.get(i));   
//                ktnb01ModelListSave.add(obj);
//		}
//        return true;
//    }
    
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

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public List<Ktnb01Model> getKtnb01ModelList() {
        return ktnb01ModelList;
    }
    
    public void setKtnb01ModelList(List<Ktnb01Model> ktnb01ModelList) {
        this.ktnb01ModelList = ktnb01ModelList;
    }
    
    public DaoKtnb01 getDaoKtnb01() {
        return daoKtnb01;
    }
    
    public void setDaoKtnb01(DaoKtnb01 daoKtnb01) {
        this.daoKtnb01 = daoKtnb01;
    }
    
    public List<String> getKT_STT_HT() {
        return KT_STT_HT;
    }
    
    public void setKT_STT_HT(List<String> KT_STT_HT) {
        this.KT_STT_HT = KT_STT_HT;
    }
    
    public List<String> getKT_DKT() {
        return KT_DKT;
    }
    
    public void setKT_DKT(List<String> KT_DKT) {
        this.KT_DKT = KT_DKT;
    }
    
    public List<String> getKT_SLT() {
        return KT_SLT;
    }
    
    public void setKT_SLT(List<String> KT_SLT) {
        this.KT_SLT = KT_SLT;
    }
    
    public List<String> getKT_SLH() {
        return KT_SLH;
    }
    
    public void setKT_SLH(List<String> KT_SLH) {
        this.KT_SLH = KT_SLH;
    }
    
    public List<String> getKT_SL_DGD() {
        return KT_SL_DGD;
    }
    
    public void setKT_SL_DGD(List<String> KT_SL_DGD) {
        this.KT_SL_DGD = KT_SL_DGD;
    }
    
    public List<String> getKT_SL_TKVV() {
        return KT_SL_TKVV;
    }
    
    public void setKT_SL_TKVV(List<String> KT_SL_TKVV) {
        this.KT_SL_TKVV = KT_SL_TKVV;
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
    
    
//</editor-fold>

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
    
}
