/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import vbsp.ims.model.ktnb.ListKTNB;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.restapi.GenericResult;

/**
 *
 * @author HP
 */
public class PCTNAuthActionSupport extends ActionSupport implements ServletRequestAware {

    private DaoKtnb01 daoKtnb01 = new DaoKtnb01();
    private ListKTNBDA listKTNBDA = new ListKTNBDA();
    //protected DaoListPosFromUser listKTNBDA = new DaoListPosFromUser();
    private List<Ktnb01Model> ktnb01ModelList;  //Lay du lieu load len table
    private List<String> lstPOS;
    private TreeNode nodes;
    private String reportGrade;
    private String userName;
    private String selectedPos;
    private TreeNode searchNodes;
    protected PosMainModel posMainModel;
    protected String pos_cd_username;
    protected String main_pos_username;
    protected List<QT_DULIEU_NT> lstDulieuNt = new ArrayList<>();
    private String errorMessage;

    private String maBC;
    private ListKTNB reportInfor;

    public TreeNode getSearchNodes() {
        return searchNodes;
    }

    public void setSearchNodes(TreeNode searchNodes) {
        this.searchNodes = searchNodes;
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

    //Cac truong chua thong tin bo xung luu du lieu
    private String userId;
    private String posCD;
    private String quyBc;
    private String namBc;
    private String maCn;
    private String message;

    private HttpServletRequest request = null;

    public PCTNAuthActionSupport() {
    }

    public String loadAuthData() throws SQLException {
        try {
            getInfo();
            return "success";
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "error";
        }
    }

    public String loadDetailData() throws SQLException {
        try {
            getInfo();

            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            posMainModel = listKTNBDA.get_pos_main_pos(userName);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            String _reportDate = getReportDate(quyBc, namBc);

            List<String> lstPosCode = new ArrayList();
            PCTNService _service = new PCTNService();
            String _posFlag;

            if (selectedPos == null || selectedPos.isEmpty()) {
                lstPosCode.add(pos_cd_username);
                if (reportGrade.equals("3")) {
                    _posFlag = "H";
                } else if (reportGrade.equals("2")) {
                    _posFlag = "M";
                } else {
                    _posFlag = "S";
                }
            } else {
                lstPosCode = getSelectedPos(selectedPos);
                if (reportGrade.equals("3")) {
                    _posFlag = "M";
                } else if (reportGrade.equals("2")) {
                    _posFlag = "S";
                } else {
                    _posFlag = "S";
                }
            }

            lstDulieuNt = _service.getDataViewForAuth(maBC, _reportDate, pos_cd_username, _posFlag , quyBc, namBc, userName, Integer.parseInt(reportGrade), lstPosCode);

            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                setErrorMessage("Chưa có dữ liệu.");
                return "error";
            }
            
            return reportInfor.getMABC();
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            setErrorMessage(e.getMessage());
            return "error";
        }
    }
    
    public String authorize(){
        try {
            getInfo();

            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            posMainModel = listKTNBDA.get_pos_main_pos(userName);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            String _reportDate = getReportDate(quyBc, namBc);

            List<String> lstPosCode = new ArrayList();
            PCTNService _service = new PCTNService();
            String _posFlag;

            if (selectedPos == null || selectedPos.isEmpty()) {
                lstPosCode.add(pos_cd_username);
                if (reportGrade.equals("3")) {
                    _posFlag = "H";
                } else if (reportGrade.equals("2")) {
                    _posFlag = "M";
                } else {
                    _posFlag = "S";
                }
            } else {
                lstPosCode = getSelectedPos(selectedPos);
                if (reportGrade.equals("3")) {
                    _posFlag = "M";
                } else if (reportGrade.equals("2")) {
                    _posFlag = "S";
                } else {
                    _posFlag = "S";
                }
            }

            GenericResult _result = _service.saveData(maBC, _reportDate, pos_cd_username, _posFlag , quyBc, namBc, userName, Integer.parseInt(reportGrade), lstPosCode);

            if (_result.getCode() != 200) {
                setErrorMessage(_result.getMessage());
                return "error";
            }
            
            return "success";
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            setErrorMessage(e.getMessage());
            return "error";
        }
        
    }

    private String getReportDate(String sQuyBC, String sNamBC) {
        String ngay_bc = "";
        if (sQuyBC.equals("1")) {
            ngay_bc = "31-MAR-" + sNamBC;
        } else if (sQuyBC.equals("2")) {
            ngay_bc = "30-JUN-" + sNamBC;
        } else if (sQuyBC.equals("3")) {
            ngay_bc = "30-SEP-" + sNamBC;
        } else if (sQuyBC.equals("4")) {
            ngay_bc = "30-NOV-" + sNamBC;
        }
        return ngay_bc;
    }

    List<String> getSelectedPos(String strPosList) {
        
        if (this.searchNodes == null) {
            System.err.println("searchNodes is null");
            try {
                int _reportGrade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
                String _userName = request.getSession().getAttribute("username").toString();
                BuildPosTreeDao buildPosTreeDao = new BuildPosTreeDao(_reportGrade, _userName);
                buildPosTreeDao.build();
                this.searchNodes = buildPosTreeDao.getNodes();
            } catch (SQLException ex) {
                System.err.println("searchNodes: " + ex.getMessage());
            }
        }
        
        String posCode;
        ArrayList<String> lstPos = new ArrayList<>();
        ArrayList<String> listOfId = (ArrayList<String>) DefineFun.string2Array(strPosList, ",", 1);
        boolean isAdded;
        if (listOfId.size() > 0) {
            for (String id : listOfId) {
                isAdded = false;
                posCode = DefineFun.searchInTreeView(id, this.searchNodes);
                for (String addedPos : lstPos) {
                    if (addedPos.equals(posCode)) {
                        isAdded = true;
                        break;
                    }
                }
                if (!isAdded && posCode != null) {
                    lstPos.add(posCode);
                }
            }
        }
        return lstPos;
    }

//    private String getListOfPos() {
//        String posString = "";
//        userName = request.getSession().getAttribute("username").toString();
//        reportGrade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());        
//        String pos_cd;
//        ArrayList<String> pos_stack = new ArrayList<>();
//        ArrayList<String> listOfId
//                = (ArrayList<String>) DefineFun.string2Array(selectedPos, ",", 1);
//        boolean isAdded;
//        if(listOfId.isEmpty()){
//            return "";
//        }
//        if (listOfId.size() > 0) {
//            for (String id : listOfId) {
//                isAdded = false;
//                pos_cd = DefineFun.searchInTreeView(id, this.searchNodes);
//                for (String added_pos : pos_stack) {
//                    if (added_pos.equals(pos_cd)) {
//                        isAdded = true;
//                        break;
//                    }
//                }
//                if (!isAdded && pos_cd != null) {
//                    posString += pos_cd + ",";
//                    pos_stack.add(pos_cd);
//                }
//            }
//        }
//        posString = posString.substring(0, posString.length() - 1);
//        
//        System.err.println(posString);
//        return posString;
//    }
    public void getInfo() throws SQLException {
        try {
            //Lay username
            HttpSession session = request.getSession();
            userId = session.getAttribute("username").toString();
            //Lay thong tin ma phogn giao dich, ma chi nhanh
            PosMainModel posMainModel = listKTNBDA.get_pos_main_pos(userId);
            posCD = posMainModel.getPosCd();
            maCn = posMainModel.getMainPosCd();
            reportInfor = listKTNBDA.getReportInforById(maBC).get(0);
        } catch (Exception e) {
            throw e;
        }

    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }            
    
    public String getPos_cd_username() {
        return pos_cd_username;
    }

    public List<QT_DULIEU_NT> getLstDulieuNt() {
        return lstDulieuNt;
    }

    public void setLstDulieuNt(List<QT_DULIEU_NT> lstDulieuNt) {
        this.lstDulieuNt = lstDulieuNt;
    }

    public void setPos_cd_username(String pos_cd_username) {
        this.pos_cd_username = pos_cd_username;
    }

    public String getMain_pos_username() {
        return main_pos_username;
    }

    public void setMain_pos_username(String main_pos_username) {
        this.main_pos_username = main_pos_username;
    }

    public List<String> getKT_KHOA() {
        return KT_KHOA;
    }

    public void setKT_KHOA(List<String> KT_KHOA) {
        this.KT_KHOA = KT_KHOA;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getMaBC() {
        return maBC;
    }

    public void setMaBC(String maBC) {
        this.maBC = maBC;
    }

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

    public ListKTNB getReportInfor() {
        return reportInfor;
    }

    public void setReportInfor(ListKTNB reportInfor) {
        this.reportInfor = reportInfor;
    }

//</editor-fold>
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

}
