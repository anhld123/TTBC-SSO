/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelCommune;
import vbsp.ims.model.ModelCommuneAuth;
import vbsp.ims.model.ModelCommuneAuthErr;
import vbsp.ims.model.ModelCommuneAuthResult;
import vbsp.ims.model.ModelCommuneRejectTW;
import vbsp.ims.model.ModelTreeNode;

/**
 *
 * @author BAOANH
 */
public class DaoCommuneInput {
    public List<ModelTreeNode> getDataPosTreeNode(String strUserName) {
        List<ModelTreeNode> lstPo = new ArrayList<ModelTreeNode>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_RPT_COMMUNE.SP_GET_LIST_LOCAL(?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(1, strUserName);
//                calstatement.setString(2, strCommuneFlg);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(2);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(3);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);
                //COLUMN_DESC
                while (reset.next()) {
                    lstPo.add(new ModelTreeNode(reset.getString("PARENT_CD"), reset.getString("PARENT_DESC"),
                            reset.getString("CHILD_CD"), reset.getString("CHILD_DESC")));
                }

                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getDataPosTreeNode -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataPosTreeNode " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getDataPosTreeNode -> " + e.getMessage());
        }
        return lstPo;
    }
    
    public List<ModelCommune> getDataInputCommune(String sUserid, String sCommuneid, String sNgaybc) {
        List<ModelCommune> lstCommune = new ArrayList<ModelCommune>();
        int pn_err_cd = 0;
        String strEdd_txt="";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_RPT_COMMUNE.SP_GET_DATA_COMMUNE(?,?,?,?,?,?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sUserid);
            calstatement.setString(2, sCommuneid);
            calstatement.setString(3, sNgaybc);
//                calstatement.setString(2, strCommuneFlg);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            pn_err_cd = calstatement.getInt(4);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(5);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(6);
            //COLUMN_DESC
//                HashMap<String, String> hmStatus = getStatusRisk(conn);
            //A.SEQ_NO,A.CODE,A.SUB_CODE, A.DESCRIPTION, A.MARK MARK_STANDARD, C.VALUE, C.MARK MARK_VALUE
            while (reset.next()) {
                ModelCommune commune = new ModelCommune();
//                commune.setsPoscd(reset.getString(1));
                 commune.setsCode(reset.getString(2));
                 commune.setsSubcode(reset.getString(3));
                 commune.setsDescription(reset.getString(4));
                 commune.setsMarkStandard(reset.getString(5));
                 commune.setbValue(reset.getDouble(6));
                 commune.setbMark(reset.getDouble(7));
                 
                lstCommune.add(commune);
            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println(strEdd_txt+" Loi trong ham getDataInputCommune " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " getDataInputCommune -> " + e.getMessage());
        }
        return lstCommune;
    }
    
    public int getPosNumber(String sUser) throws SQLException {
        int pn_posnumber =0;
        int pn_err_cd = 0;
        String strEdd_txt="";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_RPT_COMMUNE.sp_get_posnumber(?,?,?,?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sUser);
//                calstatement.setString(2, strCommuneFlg);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            pn_err_cd = calstatement.getInt(2);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(3);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(4);
            //COLUMN_DESC
//                HashMap<String, String> hmStatus = getStatusRisk(conn);
            //A.SEQ_NO,A.CODE,A.SUB_CODE, A.DESCRIPTION, A.MARK MARK_STANDARD, C.VALUE, C.MARK MARK_VALUE
             while (reset.next()) {
                pn_posnumber = reset.getInt(1);
             }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println(strEdd_txt+" Loi trong ham getDataInputCommune " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " getDataInputCommune -> " + e.getMessage());
        }
        return pn_posnumber;
    }
    
    
    
    public List<ModelCommuneAuth> getDataInputCommuneAuth(String sPos, String sNgaybc) {
        List<ModelCommuneAuth> lstCommune = new ArrayList<ModelCommuneAuth>();
        int pn_err_cd = 0;
        String strEdd_txt="";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_RPT_COMMUNE.SP_GET_DATA_COMMUNE_AUTH(?,?,?,?,?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sPos);
            calstatement.setString(2, sNgaybc);
//                calstatement.setString(2, strCommuneFlg);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            pn_err_cd = calstatement.getInt(3);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(4);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(5);
            //COLUMN_DESC
//                HashMap<String, String> hmStatus = getStatusRisk(conn);
            //A.SEQ_NO,A.CODE,A.SUB_CODE, A.DESCRIPTION, A.MARK MARK_STANDARD, C.VALUE, C.MARK MARK_VALUE
            while (reset.next()) {
                ModelCommuneAuth commune = new ModelCommuneAuth();
//                commune.setsPoscd(reset.getString(1));
                 commune.setsCommuneid(reset.getString(1));
                 commune.setbCrit1(reset.getBigDecimal(2));
//                 commune.setbCrit2(reset.getBigDecimal(3));
//                 commune.setbCrit3(reset.getBigDecimal(4));
                 commune.setbCrit4(reset.getBigDecimal(3));
//                 commune.setbCrit5(reset.getBigDecimal(6));
                 commune.setbCrit6(reset.getBigDecimal(4));
                 commune.setbCrit7(reset.getBigDecimal(5));
                 commune.setbCrit8(reset.getBigDecimal(6));
                 commune.setbCrit9(reset.getBigDecimal(7));     
                 commune.setbCrit10(reset.getBigDecimal(8));
//                 commune.setbTong(reset.getBigDecimal(12));
                 
//                commune.setsCommuneid(reset.getString(2));
//                commune.setsReportdt(reset.getDate(3) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(3)));
//                commune.setsCode(reset.getString(4));
//                commune.setbValue(reset.getBigDecimal(5));
//                commune.setbMark(reset.getBigDecimal(6));
//                commune.setsMainpos(reset.getString(7));
//                commune.setsSubcode(reset.getString(8));
//                System.err.println(commune.getsDescription());
                lstCommune.add(commune);
            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println(strEdd_txt+" Loi trong ham getDataInputCommune " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " getDataInputCommune -> " + e.getMessage());
        }
        return lstCommune;
    }
    
    public List<ModelCommuneAuth> getDataInputCommuneAuthPgd(String sUser, String sNgaybc) {
        List<ModelCommuneAuth> lstCommune = new ArrayList<ModelCommuneAuth>();
        int pn_err_cd = 0;
        String strEdd_txt="";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_RPT_COMMUNE.SP_GET_DATA_COMMUNE_AUTH_PGD(?,?,?,?,?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sUser);
            calstatement.setString(2, sNgaybc);
//                calstatement.setString(2, strCommuneFlg);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            pn_err_cd = calstatement.getInt(3);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(4);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(5);
            //COLUMN_DESC
//                HashMap<String, String> hmStatus = getStatusRisk(conn);
            //A.SEQ_NO,A.CODE,A.SUB_CODE, A.DESCRIPTION, A.MARK MARK_STANDARD, C.VALUE, C.MARK MARK_VALUE
            while (reset.next()) {
                ModelCommuneAuth commune = new ModelCommuneAuth();
//                commune.setsPoscd(reset.getString(1));
                 commune.setsCommuneid(reset.getString(1));
                 commune.setbCrit1(reset.getBigDecimal(2));
//                 commune.setbCrit2(reset.getBigDecimal(3));
//                 commune.setbCrit3(reset.getBigDecimal(4));
                 commune.setbCrit4(reset.getBigDecimal(3));
//                 commune.setbCrit5(reset.getBigDecimal(6));
                 commune.setbCrit6(reset.getBigDecimal(4));
                 commune.setbCrit7(reset.getBigDecimal(5));
                 commune.setbCrit8(reset.getBigDecimal(6));
                 commune.setbCrit9(reset.getBigDecimal(7));     
                 commune.setbCrit10(reset.getBigDecimal(8));

                lstCommune.add(commune);
            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println(strEdd_txt+" Loi trong ham getDataInputCommune " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " getDataInputCommune -> " + e.getMessage());
        }
        return lstCommune;
    }
    
    public List<ModelCommuneRejectTW> getDataCheckTW(String sNgaybc) {
        List<ModelCommuneRejectTW> lstCommune = new ArrayList<ModelCommuneRejectTW>();
        int pn_err_cd = 0;
        String strEdd_txt="";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_RPT_COMMUNE.SP_GET_DATA_REJECT_TW(?,?,?,?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sNgaybc);
//                calstatement.setString(2, strCommuneFlg);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            pn_err_cd = calstatement.getInt(2);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(3);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(4);
            //COLUMN_DESC
//                HashMap<String, String> hmStatus = getStatusRisk(conn);
            //A.SEQ_NO,A.CODE,A.SUB_CODE, A.DESCRIPTION, A.MARK MARK_STANDARD, C.VALUE, C.MARK MARK_VALUE
            while (reset.next()) {
                ModelCommuneRejectTW commune = new ModelCommuneRejectTW();
//                commune.setsPoscd(reset.getString(1));
                 commune.setsCN(reset.getString(1));
                 commune.setsTrangthai(reset.getString(2));

                lstCommune.add(commune);
            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println(strEdd_txt+" Loi trong ham getDataInputCommune " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " getDataInputCommune -> " + e.getMessage());
        }
        return lstCommune;
    }
    
    public List<ModelCommune> getDataInputCommuneAuthSend(String sPos, String sNgaybc) {
        List<ModelCommune> lstCommune = new ArrayList<ModelCommune>();
        int pn_err_cd = 0;
        String strEdd_txt="";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_RPT_COMMUNE.SP_GET_DATA_COMMUNE_AUTH_SEND(?,?,?,?,?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sPos);
            calstatement.setString(2, sNgaybc);
//                calstatement.setString(2, strCommuneFlg);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            pn_err_cd = calstatement.getInt(3);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(4);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(5);
            //COLUMN_DESC
//                HashMap<String, String> hmStatus = getStatusRisk(conn);
            //A.SEQ_NO,A.CODE,A.SUB_CODE, A.DESCRIPTION, A.MARK MARK_STANDARD, C.VALUE, C.MARK MARK_VALUE
            while (reset.next()) {
                ModelCommune commune = new ModelCommune();
//                commune.setsPoscd(reset.getString(1));
                 commune.setsPoscd(reset.getString(1));
                 commune.setsCommuneid(reset.getString(2));
                 commune.setsReportdt(reset.getString(3));
                 commune.setsCode(reset.getString(4));
                 commune.setbValue(reset.getDouble(5));
                 commune.setbMark(reset.getDouble(6));
                 commune.setsMainpos(reset.getString(7));
                 commune.setsSubcode(reset.getString(8));
                 commune.setsUserid(reset.getString(9));

                lstCommune.add(commune);
            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println(strEdd_txt+" Loi trong ham getDataInputCommune " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " getDataInputCommune -> " + e.getMessage());
        }
        return lstCommune;
    }
    
     public List<ModelCommuneAuthResult> getDataAuthResult(String sUser, String sReportDT) {
        List<ModelCommuneAuthResult> lstCommune = new ArrayList<ModelCommuneAuthResult>();
        int pn_err_cd = 0;
        String strEdd_txt="";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_RPT_COMMUNE.SP_GET_DATA_AUTH_RESULT(?,?,?,?,?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sUser);
            calstatement.setString(2, sReportDT);
//                calstatement.setString(2, strCommuneFlg);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            pn_err_cd = calstatement.getInt(3);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(4);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(5);
            //COLUMN_DESC
//                HashMap<String, String> hmStatus = getStatusRisk(conn);
            //A.SEQ_NO,A.CODE,A.SUB_CODE, A.DESCRIPTION, A.MARK MARK_STANDARD, C.VALUE, C.MARK MARK_VALUE
            while (reset.next()) {
                ModelCommuneAuthResult commune = new ModelCommuneAuthResult();
//                commune.setsPoscd(reset.getString(1));
                 commune.setsPGD(reset.getString(1));
                 commune.setdNgayBC(reset.getDate(2));
                 commune.setdNgaytao(reset.getString(3));
                 commune.setbSodong(reset.getBigDecimal(4));
                 
                lstCommune.add(commune);
            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println(strEdd_txt+" Loi trong ham getDataInputCommune " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " getDataInputCommune -> " + e.getMessage());
        }
        return lstCommune;
    }
     
     public List<ModelCommuneAuthResult> getDataAuthResultPGD(String sUser, String sReportDT) {
        List<ModelCommuneAuthResult> lstCommune = new ArrayList<ModelCommuneAuthResult>();
        int pn_err_cd = 0;
        String strEdd_txt="";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_RPT_COMMUNE.SP_GET_DATA_AUTH_RESULT_PGD(?,?,?,?,?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sUser);
            calstatement.setString(2, sReportDT);
//                calstatement.setString(2, strCommuneFlg);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            pn_err_cd = calstatement.getInt(3);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(4);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(5);
            //COLUMN_DESC
//                HashMap<String, String> hmStatus = getStatusRisk(conn);
            //A.SEQ_NO,A.CODE,A.SUB_CODE, A.DESCRIPTION, A.MARK MARK_STANDARD, C.VALUE, C.MARK MARK_VALUE
            while (reset.next()) {
                ModelCommuneAuthResult commune = new ModelCommuneAuthResult();
//                commune.setsPoscd(reset.getString(1));
                 commune.setsPGD(reset.getString(1));
                 commune.setdNgayBC(reset.getDate(2));
                 commune.setdNgaytao(reset.getString(3));
                 commune.setbSodong(reset.getBigDecimal(4));
                 
                lstCommune.add(commune);
            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println(strEdd_txt+" Loi trong ham getDataInputCommune " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " getDataInputCommune -> " + e.getMessage());
        }
        return lstCommune;
    }
     
     public List<ModelCommuneAuthErr> getDataAuthErr_1(String sUser, String sReportDT) {
        List<ModelCommuneAuthErr> lstCommuneErr = new ArrayList<ModelCommuneAuthErr>();
        int pn_err_cd = 0;
        String strEdd_txt="";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_RPT_COMMUNE.SP_GET_DATA_AUTH_ERR(?,?,?,?,?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sUser);
            calstatement.setString(2, sReportDT);
//                calstatement.setString(2, strCommuneFlg);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            pn_err_cd = calstatement.getInt(3);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(4);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(5);
            //COLUMN_DESC
//                HashMap<String, String> hmStatus = getStatusRisk(conn);
            //A.SEQ_NO,A.CODE,A.SUB_CODE, A.DESCRIPTION, A.MARK MARK_STANDARD, C.VALUE, C.MARK MARK_VALUE
            while (reset.next()) {
                ModelCommuneAuthErr commune = new ModelCommuneAuthErr();
//                commune.setsPoscd(reset.getString(1));
                 commune.setsMaPGD(reset.getString(1));
                 commune.setsTenPGD(reset.getString(2));
                 commune.setsMaXa(reset.getString(3));
                 commune.setsTenxa(reset.getString(4));
                 commune.setsSodong(reset.getBigDecimal(5));
                 
                lstCommuneErr.add(commune);
            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println(strEdd_txt+" Loi trong ham getDataInputCommune " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " getDataInputCommune -> " + e.getMessage());
        }
        return lstCommuneErr;
    }
     
     public List<ModelCommuneAuthErr> getDataAuthErr(String sUser, String sReportDT) {
        List<ModelCommuneAuthErr> lstCommuneErr = new ArrayList<ModelCommuneAuthErr>();
        int pn_err_cd = 0;
        String strEdd_txt="";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_RPT_COMMUNE.SP_GET_DATA_AUTH_ERR_1(?,?,?,?,?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sUser);
            calstatement.setString(2, sReportDT);
//                calstatement.setString(2, strCommuneFlg);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            pn_err_cd = calstatement.getInt(3);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(4);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(5);
            //COLUMN_DESC
//                HashMap<String, String> hmStatus = getStatusRisk(conn);
            //A.SEQ_NO,A.CODE,A.SUB_CODE, A.DESCRIPTION, A.MARK MARK_STANDARD, C.VALUE, C.MARK MARK_VALUE
            while (reset.next()) {
                ModelCommuneAuthErr commune = new ModelCommuneAuthErr();
//                commune.setsPoscd(reset.getString(1));
                 commune.setsMaPGD(reset.getString(1));
                 commune.setsTenPGD(reset.getString(2));
                 commune.setsMaXa(reset.getString(3));
                 commune.setsTenxa(reset.getString(4));
                 commune.setsSodong(reset.getBigDecimal(5));
                 
                lstCommuneErr.add(commune);
            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println(strEdd_txt+" Loi trong ham getDataInputCommune " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " getDataInputCommune -> " + e.getMessage());
        }
        return lstCommuneErr;
    }
    
    public boolean saveDataCommune(String sUserid, String sCommuneid, String sNgaybc, List<ModelCommune> lstCommune) {
        boolean bSuccess = false;
        String txtError = "";
        if (lstCommune.size() == 0) {
            return bSuccess;
        }
        try {
            System.err.println(sCommuneid);
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call VBSP_IMS_RPT_COMMUNE.sp_update_data_commune(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {

                StructDescriptor structDescriptor = StructDescriptor.createDescriptor("COMMUNE_TYPE", conn);

                STRUCT[] structs = null;
                structs = new STRUCT[lstCommune.size()];
                int index = 0;
                for (ModelCommune commune : lstCommune) {
                    Object[] params = new Object[4];
                    params[0] = commune.getsCode();
                    params[1] = commune.getsSubcode();
                    params[2] = commune.getbValue();
                    params[3] = commune.getbMark();
                    STRUCT struct = new STRUCT(structDescriptor,
                            conn, params);
                    structs[index] = struct;
                    index++;

                }
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

                ArrayDescriptor desc = ArrayDescriptor.createDescriptor(
                        "COMMUNE_TAB", calstatement.getConnection());
                ARRAY oracleArray = new ARRAY(desc, calstatement.getConnection(), structs);

                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sUserid);
                calstatement.setString(2, sCommuneid);
                calstatement.setString(3, sNgaybc);
                calstatement.setArray(4, oracleArray);

                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
//                txtError=calstatement.getString(6);
//        calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                bSuccess = calstatement.execute();
                String strEdd_txt = calstatement.getString(6);
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " saveDataCommune -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham saveDataCommune " + e.getMessage()+txtError);
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " saveDataCommune -> " + e.getMessage()+txtError);
        }
        return bSuccess;
    }
    
    public boolean saveDataCommunAuth(String sPos, String sNgaybc) {
        boolean bSuccess = false;
        String txtError = "";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call VBSP_IMS_RPT_COMMUNE.sp_auth_data_commune(?,?,?,?)}";
            ResultSet reset = null;
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//             calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            try {               
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sPos);
                calstatement.setString(2, sNgaybc);

                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
//                txtError=calstatement.getString(6);
//        calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                bSuccess = calstatement.execute();
                int pn_err_cd = calstatement.getInt(3);
            //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(4);
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " saveDataCommunAuth -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham saveDataCommune " + e.getMessage()+txtError);
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " saveDataCommunAuth -> " + e.getMessage()+txtError);
        }
        return bSuccess;
    }
    
    public boolean saveDataCommunAuthPgd(String sUser, String sNgaybc) {
        boolean bSuccess = false;
        String txtError = "";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call VBSP_IMS_RPT_COMMUNE.sp_auth_data_commune_pgd(?,?,?,?)}";
            ResultSet reset = null;
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//             calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            try {               
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sUser);
                calstatement.setString(2, sNgaybc);

                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
//                txtError=calstatement.getString(6);
//        calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                bSuccess = calstatement.execute();
                int pn_err_cd = calstatement.getInt(3);
            //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(4);
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " saveDataCommunAuth -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham saveDataCommune " + e.getMessage()+txtError);
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " saveDataCommunAuth -> " + e.getMessage()+txtError);
        }
        return bSuccess;
    }
    
    public boolean RejectAuth(String sGrade, String sPos, String sNgaybc) {
        boolean bSuccess = false;
        String txtError = "";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call VBSP_IMS_RPT_COMMUNE.sp_reject_auth(?,?,?,?,?)}";
            ResultSet reset = null;
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//             calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            try {               
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sGrade);
                calstatement.setString(2, sPos);
                calstatement.setString(3, sNgaybc);

                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
//                txtError=calstatement.getString(6);
//        calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                bSuccess = calstatement.execute();
                int pn_err_cd = calstatement.getInt(4);
            //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(5);
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " RejectAuth -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham RejectAuth " + e.getMessage()+txtError);
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " RejectAuth -> " + e.getMessage()+txtError);
        }
        return bSuccess;
    }
    
    public boolean UnRejectAuth(String sGrade, String sPos, String sNgaybc) {
        boolean bSuccess = false;
        String txtError = "";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call VBSP_IMS_RPT_COMMUNE.sp_unreject_auth(?,?,?,?,?)}";
            ResultSet reset = null;
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//             calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            try {               
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sGrade);
                calstatement.setString(2, sPos);
                calstatement.setString(3, sNgaybc);

                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
//                txtError=calstatement.getString(6);
//        calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                bSuccess = calstatement.execute();
                int pn_err_cd = calstatement.getInt(4);
            //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(5);
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " RejectAuth -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham RejectAuth " + e.getMessage()+txtError);
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " RejectAuth -> " + e.getMessage()+txtError);
        }
        return bSuccess;
    }
    
    public List<ModelTreeNode> getDataPosTreeNode(String strUserName,String sGrade) {
        List<ModelTreeNode> lstPo = new ArrayList<ModelTreeNode>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_RPT_COMMUNE.SP_GET_LIST_LOCAL_1(?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(1, strUserName);
                calstatement.setString(2, sGrade);
//                calstatement.setString(2, strCommuneFlg);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(3);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(4);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(5);
                //COLUMN_DESC
                while (reset.next()) {
                    lstPo.add(new ModelTreeNode(reset.getString("PARENT_CD"), reset.getString("PARENT_DESC"),
                            reset.getString("CHILD_CD"), reset.getString("CHILD_DESC")));
                }

                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(DaoProcessVb819.class.getCanonicalName() + " getDataPosTreeNode -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataPosTreeNode " + e.getMessage());
            CoreLogger.error(DaoProcessVb819.class.getCanonicalName() + " getDataPosTreeNode -> " + e.getMessage());
        }
        return lstPo;
    }
    
    public int getCountTotalRow(String sCommune, String sNgaybc) {
        int nRowTotal = 0;
        try {
             DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call VBSP_IMS_RPT_COMMUNE.f_LOAD_TOTAL_COUNT(?,?)}";
            ResultSet reset = null;

            try {
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.setString(2, sCommune);
                calstatement.setString(3, sNgaybc);
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                nRowTotal = calstatement.getInt(1);
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
//                if (conn != null) {
//                    conn.close();
//                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(DaoProcessVb819.class.getCanonicalName() + " getCountTotalRow -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getCountTotalRow " + e.getMessage());
            CoreLogger.error(DaoProcessVb819.class.getCanonicalName() + " getCountTotalRow -> " + e.getMessage());
        }

        return nRowTotal;
    }
    
    public int getCountTotalRowAuth(String sPos, String sNgaybc) {
        int nRowTotal = 0;
        try {
             DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call VBSP_IMS_RPT_COMMUNE.F_LOAD_TOTAL_COUNT_AUTH(?,?)}";
            ResultSet reset = null;

            try {
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.setString(2, sPos);
                calstatement.setString(3, sNgaybc);
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                nRowTotal = calstatement.getInt(1);
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
//                if (conn != null) {
//                    conn.close();
//                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(DaoProcessVb819.class.getCanonicalName() + " getCountTotalRow -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getCountTotalRow " + e.getMessage());
            CoreLogger.error(DaoProcessVb819.class.getCanonicalName() + " getCountTotalRow -> " + e.getMessage());
        }

        return nRowTotal;
    }
    
    public int CheckRejectTW(String sPos, String sNgaybc) {
        int nRowTotal = 0;
        try {
             DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call VBSP_IMS_RPT_COMMUNE.F_CHECK_REJECT_SEND_TW(?,?)}";
            ResultSet reset = null;

            try {
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.setString(2, sPos);
                calstatement.setString(3, sNgaybc);
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                nRowTotal = calstatement.getInt(1);
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
//                if (conn != null) {
//                    conn.close();
//                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(DaoProcessVb819.class.getCanonicalName() + " getCountTotalRow -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getCountTotalRow " + e.getMessage());
            CoreLogger.error(DaoProcessVb819.class.getCanonicalName() + " getCountTotalRow -> " + e.getMessage());
        }

        return nRowTotal;
    }
    
    
    public String getCheckAllAuth(String sPos, String sNgaybc) {
//        int nRowTotal = 0;
        String lsPos = "";
        try {
             DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call VBSP_IMS_RPT_COMMUNE.F_CHECK_AUTH_ALL(?,?)}";
            ResultSet reset = null;

            try {
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.setString(2, sPos);
                calstatement.setString(3, sNgaybc);
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                lsPos = calstatement.getString(1);
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
//                if (conn != null) {
//                    conn.close();
//                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(DaoProcessVb819.class.getCanonicalName() + " getCountTotalRow -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getCountTotalRow " + e.getMessage());
            CoreLogger.error(DaoProcessVb819.class.getCanonicalName() + " getCountTotalRow -> " + e.getMessage());
        }

        return lsPos;
    }
    
    public int getCountsResend(String sPos, String sNgaybc) {
        int nRowTotal = 0;
        try {
             DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call VBSP_IMS_RPT_COMMUNE.F_CHECK_SEND(?,?)}";
            ResultSet reset = null;

            try {
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.setString(2, sPos);
                calstatement.setString(3, sNgaybc);
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                nRowTotal = calstatement.getInt(1);
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
//                if (conn != null) {
//                    conn.close();
//                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(DaoProcessVb819.class.getCanonicalName() + " getCountTotalRow -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getCountTotalRow " + e.getMessage());
            CoreLogger.error(DaoProcessVb819.class.getCanonicalName() + " getCountTotalRow -> " + e.getMessage());
        }

        return nRowTotal;
    }
    
    public int getCountsResendPGD(String sUser, String sNgaybc) {
        int nRowTotal = 0;
        try {
             DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call VBSP_IMS_RPT_COMMUNE.F_CHECK_SEND_PGD(?,?)}";
            ResultSet reset = null;

            try {
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.setString(2, sUser);
                calstatement.setString(3, sNgaybc);
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                nRowTotal = calstatement.getInt(1);
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
//                if (conn != null) {
//                    conn.close();
//                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(DaoProcessVb819.class.getCanonicalName() + " getCountTotalRow -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getCountTotalRow " + e.getMessage());
            CoreLogger.error(DaoProcessVb819.class.getCanonicalName() + " getCountTotalRow -> " + e.getMessage());
        }

        return nRowTotal;
    }
  
    public boolean setStatusVb819_Sync(String strUserName, String sGrade, String sPoscd,
            String sNgaybc, List<ModelCommune> lst819) {
        boolean bSuccess = false;
        if (lst819.size() == 0) {
            return bSuccess;
        }

        try {
//            System.err.println(strStringLagecyId);
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call VBSP_IMS_RPT_COMMUNE.SP_VB819_SYNC(?,?,?,?,?)}";
            ResultSet reset = null;

            try {

                StructDescriptor structDescriptor = StructDescriptor.createDescriptor("VB819_TYPE", conn);

                STRUCT[] structs = null;
                structs = new STRUCT[lst819.size()];
                int index = 0;
                for (ModelCommune value : lst819) {
//                    if (value.getCheck_legacyid().toLowerCase().equals("false")) {
//                        continue;
//                    }

                    Object[] params = new Object[9];
                    params[0] = value.getsPoscd();
                    params[1] = value.getsCommuneid();
                    params[2] = value.getsReportdt();
                    params[3] = value.getsCode();
                    params[4] = value.getbValue();
                    params[5] = value.getbMark();
                    params[6] = value.getsMainpos();
                    params[7] = value.getsSubcode();
                    params[8] = value.getsUserid();

                    
                    STRUCT struct = new STRUCT(structDescriptor,
                            conn, params);
                    structs[index] = struct;
                    index++;

                }
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

                ArrayDescriptor desc = ArrayDescriptor.createDescriptor(
                        "VB819_TAB", calstatement.getConnection());
                ARRAY oracleArray = new ARRAY(desc, calstatement.getConnection(), structs);

                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sPoscd);
                calstatement.setString(2, sNgaybc);
                calstatement.setArray(3, oracleArray);

                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
//        calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                bSuccess = calstatement.execute();
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(DaoProcessVb819.class.getCanonicalName() + " setStatusVb819_Sync -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham setStatusVb819_Sync " + e.getMessage());
            CoreLogger.error(DaoProcessVb819.class.getCanonicalName() + " setStatusVb819_Sync -> " + e.getMessage());
        }
        return bSuccess;
    }
}
