/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelRiskProcess;
import vbsp.ims.model.ModelRiskProcess.DescTableBrower;
import vbsp.ims.model.ModelRiskProcess.HistorySendData;
import vbsp.ims.model.ModelRiskProcess.ListRisk;
import vbsp.ims.model.ModelRiskProcess.ListRiskSync;
import vbsp.ims.model.ModelRiskProcess.StatusHistorySend;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author LION
 */
public class DaoProcessRisk {

    //Load du lieu cua xa, pgd, tinh tham so truyen vao la user name
    /**
     * getDataPosTreeNode
     *
     * @param strUserName
     * @return List<ModelTreeNode>
     */
    public List<ModelTreeNode> getDataPosTreeNode(String strUserName) {
        List<ModelTreeNode> lstPo = new ArrayList<ModelTreeNode>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_GET_LIST_LOCAL(?,?,?,?)}";
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
    
    public List<ModelTreeNode> getDataPosTreeNode_QD62(String strUserName) {
        List<ModelTreeNode> lstPo = new ArrayList<ModelTreeNode>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_GET_LIST_LOCAL_QD62(?,?,?,?)}";
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

    /**
     *
     * @param strUserName
     * @param lstArrPoscd
     * @param strDateRisk
     * @param strTimeRisk
     * @param strStatus
     * @param strGroupRisk
     * @return
     */
    public List<ModelRiskProcess> getDataRisk1(String strUserName, ArrayList<String> lstArrPoscd,
            String strDateRisk, String strTimeRisk, String strStatus, String strGroupRisk) {
        //Duyet list dua ra danh sach main pos la dang "000314","000401","000501"....
        String strStringPosCd = "";
        for (int i = 0; i < lstArrPoscd.size(); i++) {
            if (i != lstArrPoscd.size() - 1) {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\",";
            } else {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\"";
            }
        }
        List<ModelRiskProcess> lstModelRisk = new ArrayList<ModelRiskProcess>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.sp_Load_data_risk(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(1, strUserName);
                calstatement.setString(2, strStringPosCd);
                calstatement.setString(3, strDateRisk);
                calstatement.setString(4, strTimeRisk);
                calstatement.setString(5, strStatus);
                calstatement.setString(6, strGroupRisk);
//                calstatement.setString(2, strCommuneFlg);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(7);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(8);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(9);
                //COLUMN_DESC
                while (reset.next()) {
                    ModelRiskProcess modelRisk = new ModelRiskProcess();
                    /* modelRisk.setsCifNo(reset.getString(1));//Lay ma khach hang
                     modelRisk.setsCustName(reset.getString(2));//ten khach hang
                     modelRisk.setsLegacyId(reset.getString(3));
                     modelRisk.setsProdId(reset.getString(4));
                     modelRisk.setsProdDesc(reset.getString(5));
                     modelRisk.setsDateDisb(reset.getString(6));
                     modelRisk.setsDateDue(reset.getString(7));
                     modelRisk.setsGroupRisk(reset.getString(8));
                     modelRisk.setsCauseRisk(reset.getString(9)); //nguyen nhan rui ro
                     modelRisk.setsPrinDenghi(reset.getBigDecimal(10));
                     modelRisk.setsInterestDenghi(reset.getBigDecimal(11));
                     modelRisk.setsPrinRisk(reset.getBigDecimal(12));
                     modelRisk.setsInterestRisk(reset.getBigDecimal(13));
                     modelRisk.setsCommuneId(reset.getString(14));
                     modelRisk.setsPosCd(reset.getString(15));
                     modelRisk.setsMainPos(reset.getString(16));*/
                    System.err.println(reset.getString(3));
                    lstModelRisk.add(modelRisk);
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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getDataRisk -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataRisk " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getDataRisk -> " + e.getMessage());
        }
        return lstModelRisk;
    }

    //Load du lieu tu bang xu ly rui ruo len table
    /**
     *
     * @param conn
     * @param strUserName
     * @param sGrade
     * @param lstArrPoscd
     * @param sNambc
     * @param sDotrr
     * @param sNhomrr
     * @param sTrangthai
     * @param sChuongtrinh
     * @param sNguon_von
     * @param startRow
     * @param EndRow
     * @return
     */
    public List<ModelRiskProcess> getDataRisk(Connection conn, String strUserName, String sGrade, ArrayList<String> lstArrPoscd,
            String sNambc, String sDotrr, String sNhomrr, String sTrangthai, String sChuongtrinh, String sNguon_von, int startRow, int EndRow, String sVbXln) {
        //Duyet list dua ra danh sach main pos la dang "000314","000401","000501"....
        String strStringPosCd = "";
        for (int i = 0; i < lstArrPoscd.size(); i++) {
            if (i != lstArrPoscd.size() - 1) {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\",";
            } else {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\"";
            }
        }
        List<ModelRiskProcess> lstModelRisk = new ArrayList<ModelRiskProcess>();
        try {
            //DaoConnect daoconnect = new DaoConnect();
            //Connection conn = null;
            //conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.sp_Load_data_risk(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(1, sVbXln);
                calstatement.setString(2, strUserName);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, strStringPosCd);
                calstatement.setString(5, sNambc);
                calstatement.setString(6, sDotrr);
                calstatement.setString(7, sNhomrr);
                calstatement.setString(8, sTrangthai);
                calstatement.setString(9, sChuongtrinh);
                calstatement.setString(10, sNguon_von);
                calstatement.setInt(11, startRow);
                calstatement.setInt(12, EndRow);
//                calstatement.setString(2, strCommuneFlg);
                calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(14, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(15, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(13);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(14);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(15);
                //COLUMN_DESC
                while (reset.next()) {
                    ModelRiskProcess modelRisk = new ModelRiskProcess();
//                    System.err.println("Gia tri So ku: ="+reset.getString(2));
                    modelRisk.setsSoku(reset.getString(2));
                    modelRisk.setsMakh(reset.getString(3));
                    modelRisk.setsSoku(reset.getString(2));
                    modelRisk.setsMakh(reset.getString(3));
                    modelRisk.setsTenkh(reset.getString(4));
                    modelRisk.setsDiachi(reset.getString(5));
                    modelRisk.setsChtrinh(reset.getString(6));
                    modelRisk.setsSprd_Cd(reset.getString(7));
                    modelRisk.setsDq_Stat_Cd(reset.getString(8));
                    modelRisk.setDbDngoc(reset.getBigDecimal(9));
                    modelRisk.setDbLaith(reset.getBigDecimal(10));
                    modelRisk.setDbLaiqh(reset.getBigDecimal(11));
                    modelRisk.setDbDnghi_Dno(reset.getBigDecimal(12));
                    modelRisk.setDbDnghi_Lai(reset.getBigDecimal(13));
                    modelRisk.setDbXl_Duno(reset.getBigDecimal(14));
                    modelRisk.setDbXl_Lai(reset.getBigDecimal(15));
                    modelRisk.setDbHt_Dno(reset.getBigDecimal(16));
                    modelRisk.setDbHt_Lai(reset.getBigDecimal(17));
                    modelRisk.setsNgayvay(reset.getDate(18) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(18))); //new SimpleDateFormat("dd/MM/yyyy").format(reset.getString(18))
                    modelRisk.setsNgaydh(reset.getDate(19) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(19)));
                    modelRisk.setDbThoihanvay(reset.getBigDecimal(20));
                    modelRisk.setDbMdthiethai(reset.getBigDecimal(21));
                    Date str = reset.getDate(28);
                    modelRisk.setsNgayrr(reset.getDate(22) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(22)));
                    modelRisk.setDbDnghi_Tg(reset.getBigDecimal(23));
                    modelRisk.setDbPduyet_Tg(reset.getBigDecimal(24));
                    modelRisk.setsNguyennhan(reset.getString(25));
                    modelRisk.setsMotann(reset.getString(26));
                    modelRisk.setsTrangthai(reset.getString(27));
                    modelRisk.setsPduyet_Ngay_Cn(reset.getDate(28) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(28)));
                    modelRisk.setsPduyet_Nguoi_Cn(reset.getString(29));
                    modelRisk.setsPduyet_Ngay_Tw(reset.getDate(30) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(30)));
                    modelRisk.setsPduyet_Nguoi_Tw(reset.getString(31));
                    modelRisk.setsTaolap_Nguoi(reset.getString(32));
                    modelRisk.setsTaolap_Ngay(reset.getDate(33) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(33)));
                    modelRisk.setsMaqd(reset.getString(34));
                    modelRisk.setsTenqd(reset.getString(35));
                    modelRisk.setsNhomrr(reset.getString(36));
                    modelRisk.setsMapgd(reset.getString(37));
                    modelRisk.setsMacn(reset.getString(38));
                    modelRisk.setsNgaybc(reset.getDate(39) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(39)));
                    modelRisk.setsCapnhat(reset.getDate(40) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(40)));
                    modelRisk.setsNgaytao(reset.getDate(41) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(41)));
                    modelRisk.setsNguoitao(reset.getString(42));
                    modelRisk.setsPduyet_Cap(reset.getString(43));
                    modelRisk.setsNgayhl(reset.getDate(44) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(44)));
                    modelRisk.setsHt_Tkxoano(reset.getString(45));
                    modelRisk.setsNguonvon(reset.getString(46));
                    modelRisk.setDbDotrr(reset.getBigDecimal(47));
                    modelRisk.setsMadp(reset.getString(48));
                    modelRisk.setsMato(reset.getString(49));
                    modelRisk.setDbSolanxl(reset.getBigDecimal(50));
                    lstModelRisk.add(modelRisk);
                }

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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getDataRisk -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataRisk " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getDataRisk -> " + e.getMessage());
        }
        return lstModelRisk;
    }
    
    public List<ModelRiskProcess> getDataRiskQD62(Connection conn, String strUserName, String sGrade, ArrayList<String> lstArrPoscd,
            String sNambc, String sDotrr, String sNhomrr, String sTrangthai, String sChuongtrinh, String sNguon_von, int startRow, int EndRow, String sVbXln) {
        //Duyet list dua ra danh sach main pos la dang "000314","000401","000501"....
        String strStringPosCd = "";
        for (int i = 0; i < lstArrPoscd.size(); i++) {
            if (i != lstArrPoscd.size() - 1) {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\",";
            } else {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\"";
            }
        }
        List<ModelRiskProcess> lstModelRisk = new ArrayList<ModelRiskProcess>();
        try {
            //DaoConnect daoconnect = new DaoConnect();
            //Connection conn = null;
            //conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_LOAD_DATA_RISK_QD62(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(1, sVbXln);
                calstatement.setString(2, strUserName);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, strStringPosCd);
                calstatement.setString(5, sNambc);
                calstatement.setString(6, sDotrr);
                calstatement.setString(7, sNhomrr);
                calstatement.setString(8, sTrangthai);
                calstatement.setString(9, sChuongtrinh);
                calstatement.setString(10, sNguon_von);
                calstatement.setInt(11, startRow);
                calstatement.setInt(12, EndRow);
//                calstatement.setString(2, strCommuneFlg);
                calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(14, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(15, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(13);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(14);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(15);
                //COLUMN_DESC
                while (reset.next()) {
                    ModelRiskProcess modelRisk = new ModelRiskProcess();
//                    System.err.println("Gia tri So ku: ="+reset.getString(2));
                    modelRisk.setsSoku(reset.getString(2));
                    modelRisk.setsMakh(reset.getString(3));
                    modelRisk.setsSoku(reset.getString(2));
                    modelRisk.setsMakh(reset.getString(3));
                    modelRisk.setsTenkh(reset.getString(4));
                    modelRisk.setsDiachi(reset.getString(5));
                    modelRisk.setsChtrinh(reset.getString(6));
                    modelRisk.setsSprd_Cd(reset.getString(7));
                    modelRisk.setsDq_Stat_Cd(reset.getString(8));
                    modelRisk.setDbDngoc(reset.getBigDecimal(9));
                    modelRisk.setDbLaith(reset.getBigDecimal(10));
                    modelRisk.setDbLaiqh(reset.getBigDecimal(11));
                    modelRisk.setDbDnghi_Dno(reset.getBigDecimal(12));
                    modelRisk.setDbDnghi_Lai(reset.getBigDecimal(13));
                    modelRisk.setDbXl_Duno(reset.getBigDecimal(14));
                    modelRisk.setDbXl_Lai(reset.getBigDecimal(15));
                    modelRisk.setDbHt_Dno(reset.getBigDecimal(16));
                    modelRisk.setDbHt_Lai(reset.getBigDecimal(17));
                    modelRisk.setsNgayvay(reset.getDate(18) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(18))); //new SimpleDateFormat("dd/MM/yyyy").format(reset.getString(18))
                    modelRisk.setsNgaydh(reset.getDate(19) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(19)));
                    modelRisk.setDbThoihanvay(reset.getBigDecimal(20));
                    modelRisk.setDbMdthiethai(reset.getBigDecimal(21));
                    Date str = reset.getDate(28);
                    modelRisk.setsNgayrr(reset.getDate(22) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(22)));
                    modelRisk.setDbDnghi_Tg(reset.getBigDecimal(23));
                    modelRisk.setDbPduyet_Tg(reset.getBigDecimal(24));
                    modelRisk.setsNguyennhan(reset.getString(25));
                    modelRisk.setsMotann(reset.getString(26));
                    modelRisk.setsTrangthai(reset.getString(27));
                    modelRisk.setsPduyet_Ngay_Cn(reset.getDate(28) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(28)));
                    modelRisk.setsPduyet_Nguoi_Cn(reset.getString(29));
                    modelRisk.setsPduyet_Ngay_Tw(reset.getDate(30) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(30)));
                    modelRisk.setsPduyet_Nguoi_Tw(reset.getString(31));
                    modelRisk.setsTaolap_Nguoi(reset.getString(32));
                    modelRisk.setsTaolap_Ngay(reset.getDate(33) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(33)));
                    modelRisk.setsMaqd(reset.getString(34));
                    modelRisk.setsTenqd(reset.getString(35));
                    modelRisk.setsNhomrr(reset.getString(36));
                    modelRisk.setsMapgd(reset.getString(37));
                    modelRisk.setsMacn(reset.getString(38));
                    modelRisk.setsNgaybc(reset.getDate(39) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(39)));
                    modelRisk.setsCapnhat(reset.getDate(40) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(40)));
                    modelRisk.setsNgaytao(reset.getDate(41) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(41)));
                    modelRisk.setsNguoitao(reset.getString(42));
                    modelRisk.setsPduyet_Cap(reset.getString(43));
                    modelRisk.setsNgayhl(reset.getDate(44) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(44)));
                    modelRisk.setsHt_Tkxoano(reset.getString(45));
                    modelRisk.setsNguonvon(reset.getString(46));
                    modelRisk.setDbDotrr(reset.getBigDecimal(47));
                    modelRisk.setsMadp(reset.getString(48));
                    modelRisk.setsMato(reset.getString(49));
                    modelRisk.setDbSolanxl(reset.getBigDecimal(50));
                    lstModelRisk.add(modelRisk);
                }

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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getDataRisk -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataRisk " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getDataRisk -> " + e.getMessage());
        }
        return lstModelRisk;
    }

    /**
     *
     * @param strUserName
     * @param sGrade
     * @param lstArrPoscd
     * @param sNambc
     * @param sDotrr
     * @param sNhomrr
     * @param sTrangthai
     * @param sChuongtrinh
     * @param sSoku
     * @return
     */
    public List<ModelRiskProcess> getDetailCustomer(String strUserName, String sGrade, ArrayList<String> lstArrPoscd,
            String sNambc, String sDotrr, String sNhomrr, String sTrangthai, String sChuongtrinh, String sSoku, String sKhoa) {
        //Duyet list dua ra danh sach main pos la dang "000314","000401","000501"....
        String strStringPosCd = "";
        for (int i = 0; i < lstArrPoscd.size(); i++) {
            if (i != lstArrPoscd.size() - 1) {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\",";
            } else {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\"";
            }
        }
        List<ModelRiskProcess> lstModelRisk = new ArrayList<ModelRiskProcess>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_GET_DETAIL_CUSTOMER(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, strUserName);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, strStringPosCd);
                calstatement.setString(5, sNambc);
                calstatement.setString(6, sDotrr);
                calstatement.setString(7, sNhomrr);
                calstatement.setString(8, sTrangthai);
                calstatement.setString(9, sChuongtrinh);
                calstatement.setString(10, sSoku);
//                calstatement.setString(2, strCommuneFlg);
                calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(11);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(12);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(13);
                //COLUMN_DESC
                HashMap<String, String> hmStatus = getStatusRisk(conn);

                while (reset.next()) {
                    ModelRiskProcess modelRisk = new ModelRiskProcess();
//                    System.err.println("Gia tri So ku: ="+reset.getString(2));
                    modelRisk.setsSoku(reset.getString(2));
                    modelRisk.setsMakh(reset.getString(3));
                    modelRisk.setsSoku(reset.getString(2));
                    modelRisk.setsMakh(reset.getString(3));
                    modelRisk.setsTenkh(reset.getString(4));
                    modelRisk.setsDiachi(reset.getString(5));
                    modelRisk.setsChtrinh(reset.getString(6));
                    modelRisk.setsSprd_Cd(reset.getString(7));
                    modelRisk.setsDq_Stat_Cd(reset.getString(8));
                    modelRisk.setDbDngoc(reset.getBigDecimal(9));
                    modelRisk.setDbLaith(reset.getBigDecimal(10));
                    modelRisk.setDbLaiqh(reset.getBigDecimal(11));
                    modelRisk.setDbDnghi_Dno(reset.getBigDecimal(12));
                    modelRisk.setDbDnghi_Lai(reset.getBigDecimal(13));
                    modelRisk.setDbXl_Duno(reset.getBigDecimal(14));
                    modelRisk.setDbXl_Lai(reset.getBigDecimal(15));
                    modelRisk.setDbHt_Dno(reset.getBigDecimal(16));
                    modelRisk.setDbHt_Lai(reset.getBigDecimal(17));
                    modelRisk.setsNgayvay(reset.getDate(18) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(18))); //new SimpleDateFormat("dd/MM/yyyy").format(reset.getString(18))
                    modelRisk.setsNgaydh(reset.getDate(19) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(19)));
                    modelRisk.setDbThoihanvay(reset.getBigDecimal(20));
                    modelRisk.setDbMdthiethai(reset.getBigDecimal(21));
                    Date str = reset.getDate(28);
                    modelRisk.setsNgayrr(reset.getDate(22) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(22)));
                    modelRisk.setDbDnghi_Tg(reset.getBigDecimal(23));
                    modelRisk.setDbPduyet_Tg(reset.getBigDecimal(24));
                    modelRisk.setsNguyennhan(reset.getString(25));
                    modelRisk.setsMotann(reset.getString(26));
                    modelRisk.setsTrangthai(hmStatus.get(reset.getString(27)));
                    modelRisk.setsPduyet_Ngay_Cn(reset.getDate(28) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(28)));
                    modelRisk.setsPduyet_Nguoi_Cn(reset.getString(29));
                    modelRisk.setsPduyet_Ngay_Tw(reset.getDate(30) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(30)));
                    modelRisk.setsPduyet_Nguoi_Tw(reset.getString(31));
                    modelRisk.setsTaolap_Nguoi(reset.getString(32));
                    modelRisk.setsTaolap_Ngay(reset.getDate(33) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(33)));
                    modelRisk.setsMaqd(reset.getString(34));
                    modelRisk.setsTenqd(reset.getString(35));
                    modelRisk.setsNhomrr(reset.getString(36));
                    modelRisk.setsMapgd(reset.getString(37));
                    modelRisk.setsMacn(reset.getString(38));
                    modelRisk.setsNgaybc(reset.getDate(39) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(39)));
                    modelRisk.setsCapnhat(reset.getDate(40) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(40)));
                    modelRisk.setsNgaytao(reset.getDate(41) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(41)));
                    modelRisk.setsNguoitao(reset.getString(42));
                    modelRisk.setsPduyet_Cap(reset.getString(43));
                    modelRisk.setsNgayhl(reset.getDate(44) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(44)));
                    modelRisk.setsHt_Tkxoano(reset.getString(45));
                    modelRisk.setsNguonvon(reset.getString(46));
                    modelRisk.setDbDotrr(reset.getBigDecimal(47));
                    modelRisk.setsMadp(reset.getString(48));
                    modelRisk.setsMato(reset.getString(49));
                    modelRisk.setDbSolanxl(reset.getBigDecimal(50));
                    modelRisk.setsNguoi_pduyet_pgd(reset.getString(51));
                    modelRisk.setsNgay_pduyet_pgd(reset.getDate(52) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(52)));
                    modelRisk.setsNguyennhan_tuchoi(reset.getString(53));
                    modelRisk.setsInt_pduyet_ngay_cn(reset.getDate(54) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(54)));
                    modelRisk.setsInt_pduyet_nguoi_cn(reset.getString(55));
                    modelRisk.setsNguyennhan_tc_cn(reset.getString(56));
                    lstModelRisk.add(modelRisk);
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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getDataRisk -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataRisk " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getDataRisk -> " + e.getMessage());
        }
        return lstModelRisk;
    }

    /**
     *
     * @param strUserName
     * @param sGrade
     * @param sNambc
     * @param sDotrr
     * @param sSoku
     * @return
     */
    public List<ModelRiskProcess> getDetailCustomerSearch(String strUserName, String sGrade,
            String sNambc, String sDotrr, String sSoku, String sKhoa) {
        //Duyet list dua ra danh sach main pos la dang "000314","000401","000501"....

        List<ModelRiskProcess> lstModelRisk = new ArrayList<ModelRiskProcess>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_GET_DETAIL_CUSTOMER_SEARCH(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, strUserName);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, sNambc);
                calstatement.setString(5, sDotrr);
                calstatement.setString(6, sSoku);
//                calstatement.setString(2, strCommuneFlg);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(7);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(8);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(9);
                //COLUMN_DESC
                HashMap<String, String> hmStatus = getStatusRisk(conn);

                while (reset.next()) {
                    ModelRiskProcess modelRisk = new ModelRiskProcess();
//                    System.err.println("Gia tri So ku: ="+reset.getString(2));
                    modelRisk.setsSoku(reset.getString(2));
                    modelRisk.setsMakh(reset.getString(3));
                    modelRisk.setsSoku(reset.getString(2));
                    modelRisk.setsMakh(reset.getString(3));
                    modelRisk.setsTenkh(reset.getString(4));
                    modelRisk.setsDiachi(reset.getString(5));
                    modelRisk.setsChtrinh(reset.getString(6));
                    modelRisk.setsSprd_Cd(reset.getString(7));
                    modelRisk.setsDq_Stat_Cd(reset.getString(8));
                    modelRisk.setDbDngoc(reset.getBigDecimal(9));
                    modelRisk.setDbLaith(reset.getBigDecimal(10));
                    modelRisk.setDbLaiqh(reset.getBigDecimal(11));
                    modelRisk.setDbDnghi_Dno(reset.getBigDecimal(12));
                    modelRisk.setDbDnghi_Lai(reset.getBigDecimal(13));
                    modelRisk.setDbXl_Duno(reset.getBigDecimal(14));
                    modelRisk.setDbXl_Lai(reset.getBigDecimal(15));
                    modelRisk.setDbHt_Dno(reset.getBigDecimal(16));
                    modelRisk.setDbHt_Lai(reset.getBigDecimal(17));
                    modelRisk.setsNgayvay(reset.getDate(18) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(18))); //new SimpleDateFormat("dd/MM/yyyy").format(reset.getString(18))
                    modelRisk.setsNgaydh(reset.getDate(19) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(19)));
                    modelRisk.setDbThoihanvay(reset.getBigDecimal(20));
                    modelRisk.setDbMdthiethai(reset.getBigDecimal(21));
                    Date str = reset.getDate(28);
                    modelRisk.setsNgayrr(reset.getDate(22) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(22)));
                    modelRisk.setDbDnghi_Tg(reset.getBigDecimal(23));
                    modelRisk.setDbPduyet_Tg(reset.getBigDecimal(24));
                    modelRisk.setsNguyennhan(reset.getString(25));
                    modelRisk.setsMotann(reset.getString(26));
                    modelRisk.setsTrangthai(hmStatus.get(reset.getString(27)));
                    modelRisk.setsPduyet_Ngay_Cn(reset.getDate(28) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(28)));
                    modelRisk.setsPduyet_Nguoi_Cn(reset.getString(29));
                    modelRisk.setsPduyet_Ngay_Tw(reset.getDate(30) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(30)));
                    modelRisk.setsPduyet_Nguoi_Tw(reset.getString(31));
                    modelRisk.setsTaolap_Nguoi(reset.getString(32));
                    modelRisk.setsTaolap_Ngay(reset.getDate(33) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(33)));
                    modelRisk.setsMaqd(reset.getString(34));
                    modelRisk.setsTenqd(reset.getString(35));
                    modelRisk.setsNhomrr(reset.getString(36));
                    modelRisk.setsMapgd(reset.getString(37));
                    modelRisk.setsMacn(reset.getString(38));
                    modelRisk.setsNgaybc(reset.getDate(39) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(39)));
                    modelRisk.setsCapnhat(reset.getDate(40) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(40)));
                    modelRisk.setsNgaytao(reset.getDate(41) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(41)));
                    modelRisk.setsNguoitao(reset.getString(42));
                    modelRisk.setsPduyet_Cap(reset.getString(43));
                    modelRisk.setsNgayhl(reset.getDate(44) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(44)));
                    modelRisk.setsHt_Tkxoano(reset.getString(45));
                    modelRisk.setsNguonvon(reset.getString(46));
                    modelRisk.setDbDotrr(reset.getBigDecimal(47));
                    modelRisk.setsMadp(reset.getString(48));
                    modelRisk.setsMato(reset.getString(49));
                    modelRisk.setDbSolanxl(reset.getBigDecimal(50));
                    modelRisk.setsNguoi_pduyet_pgd(reset.getString(51));
                    modelRisk.setsNgay_pduyet_pgd(reset.getDate(52) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(52)));
                    modelRisk.setsNguyennhan_tuchoi(reset.getString(53));
                    lstModelRisk.add(modelRisk);
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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getDetailCustomerSearch -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDetailCustomerSearch " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getDetailCustomerSearch -> " + e.getMessage());
        }
        return lstModelRisk;
    }
    
    public List<ModelRiskProcess> getDetailCustomerSearch62(String strUserName, String sGrade,
            String sNambc, String sDotrr, String sSoku, String sKhoa) {
        //Duyet list dua ra danh sach main pos la dang "000314","000401","000501"....

        List<ModelRiskProcess> lstModelRisk = new ArrayList<ModelRiskProcess>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_GET_DETAIL_CUST_SEARCH_62(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, strUserName);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, sNambc);
                calstatement.setString(5, sDotrr);
                calstatement.setString(6, sSoku);
//                calstatement.setString(2, strCommuneFlg);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(7);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(8);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(9);
                //COLUMN_DESC
                HashMap<String, String> hmStatus = getStatusRisk(conn);

                while (reset.next()) {
                    ModelRiskProcess modelRisk = new ModelRiskProcess();
//                    System.err.println("Gia tri So ku: ="+reset.getString(2));
                    modelRisk.setsSoku(reset.getString(2));
                    modelRisk.setsMakh(reset.getString(3));
                    modelRisk.setsSoku(reset.getString(2));
                    modelRisk.setsMakh(reset.getString(3));
                    modelRisk.setsTenkh(reset.getString(4));
                    modelRisk.setsDiachi(reset.getString(5));
                    modelRisk.setsChtrinh(reset.getString(6));
                    modelRisk.setsSprd_Cd(reset.getString(7));
                    modelRisk.setsDq_Stat_Cd(reset.getString(8));
                    modelRisk.setDbDngoc(reset.getBigDecimal(9));
                    modelRisk.setDbLaith(reset.getBigDecimal(10));
                    modelRisk.setDbLaiqh(reset.getBigDecimal(11));
                    modelRisk.setDbDnghi_Dno(reset.getBigDecimal(12));
                    modelRisk.setDbDnghi_Lai(reset.getBigDecimal(13));
                    modelRisk.setDbXl_Duno(reset.getBigDecimal(14));
                    modelRisk.setDbXl_Lai(reset.getBigDecimal(15));
                    modelRisk.setDbHt_Dno(reset.getBigDecimal(16));
                    modelRisk.setDbHt_Lai(reset.getBigDecimal(17));
                    modelRisk.setsNgayvay(reset.getDate(18) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(18))); //new SimpleDateFormat("dd/MM/yyyy").format(reset.getString(18))
                    modelRisk.setsNgaydh(reset.getDate(19) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(19)));
                    modelRisk.setDbThoihanvay(reset.getBigDecimal(20));
                    modelRisk.setDbMdthiethai(reset.getBigDecimal(21));
                    Date str = reset.getDate(28);
                    modelRisk.setsNgayrr(reset.getDate(22) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(22)));
                    modelRisk.setDbDnghi_Tg(reset.getBigDecimal(23));
                    modelRisk.setDbPduyet_Tg(reset.getBigDecimal(24));
                    modelRisk.setsNguyennhan(reset.getString(25));
                    modelRisk.setsMotann(reset.getString(26));
                    modelRisk.setsTrangthai(hmStatus.get(reset.getString(27)));
                    modelRisk.setsPduyet_Ngay_Cn(reset.getDate(28) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(28)));
                    modelRisk.setsPduyet_Nguoi_Cn(reset.getString(29));
                    modelRisk.setsPduyet_Ngay_Tw(reset.getDate(30) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(30)));
                    modelRisk.setsPduyet_Nguoi_Tw(reset.getString(31));
                    modelRisk.setsTaolap_Nguoi(reset.getString(32));
                    modelRisk.setsTaolap_Ngay(reset.getDate(33) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(33)));
                    modelRisk.setsMaqd(reset.getString(34));
                    modelRisk.setsTenqd(reset.getString(35));
                    modelRisk.setsNhomrr(reset.getString(36));
                    modelRisk.setsMapgd(reset.getString(37));
                    modelRisk.setsMacn(reset.getString(38));
                    modelRisk.setsNgaybc(reset.getDate(39) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(39)));
                    modelRisk.setsCapnhat(reset.getDate(40) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(40)));
                    modelRisk.setsNgaytao(reset.getDate(41) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(41)));
                    modelRisk.setsNguoitao(reset.getString(42));
                    modelRisk.setsPduyet_Cap(reset.getString(43));
                    modelRisk.setsNgayhl(reset.getDate(44) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(44)));
                    modelRisk.setsHt_Tkxoano(reset.getString(45));
                    modelRisk.setsNguonvon(reset.getString(46));
                    modelRisk.setDbDotrr(reset.getBigDecimal(47));
                    modelRisk.setsMadp(reset.getString(48));
                    modelRisk.setsMato(reset.getString(49));
                    modelRisk.setDbSolanxl(reset.getBigDecimal(50));
                    modelRisk.setsNguoi_pduyet_pgd(reset.getString(51));
                    modelRisk.setsNgay_pduyet_pgd(reset.getDate(52) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(52)));
                    modelRisk.setsNguyennhan_tuchoi(reset.getString(53));
                    lstModelRisk.add(modelRisk);
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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getDetailCustomerSearch -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDetailCustomerSearch " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getDetailCustomerSearch -> " + e.getMessage());
        }
        return lstModelRisk;
    }

    //lay ra tong so ban ghi cua du lieu khi dua len bang
    /**
     *
     * @param conn
     * @param strUserName
     * @param sGrade
     * @param lstArrPoscd
     * @param sNambc
     * @param sDotrr
     * @param sNhomrr
     * @param sTrangthai
     * @param sChuongtrinh
     * @param sNguon_von
     * @return
     */
    public int getCountTotalRowRisk(Connection conn, String strUserName, String sGrade, ArrayList<String> lstArrPoscd,
            String sNambc, String sDotrr, String sNhomrr, String sTrangthai, String sChuongtrinh, String sNguon_von, String strVbXln) {
        int nRowTotal = 0;
        String strStringPosCd = "";
        for (int i = 0; i < lstArrPoscd.size(); i++) {
            if (i != lstArrPoscd.size() - 1) {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\",";
            } else {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\"";
            }
        }
        try {
            // DaoConnect daoconnect = new DaoConnect();
            //Connection conn = null;
            //conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call vbsp_ims_risk.f_LOAD_TOTAL_COUNT_RISK(?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(2, strVbXln);
                calstatement.setString(3, strUserName);
                calstatement.setString(4, sGrade);
                calstatement.setString(5, strStringPosCd);
                calstatement.setString(6, sNambc);
                calstatement.setString(7, sDotrr);
                calstatement.setString(8, sNhomrr);
                calstatement.setString(9, sTrangthai);
                calstatement.setString(10, sChuongtrinh);
                calstatement.setString(11, sNguon_von);
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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getCountTotalRowRisk -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getCountTotalRowRisk " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getCountTotalRowRisk -> " + e.getMessage());
        }

        return nRowTotal;
    }
    
    public int getCountTotalRowRiskQD62(Connection conn, String strUserName, String sGrade, ArrayList<String> lstArrPoscd,
            String sNambc, String sDotrr, String sNhomrr, String sTrangthai, String sChuongtrinh, String sNguon_von, String strVbXln) {
        int nRowTotal = 0;
        String strStringPosCd = "";
        for (int i = 0; i < lstArrPoscd.size(); i++) {
            if (i != lstArrPoscd.size() - 1) {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\",";
            } else {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\"";
            }
        }
        try {
            // DaoConnect daoconnect = new DaoConnect();
            //Connection conn = null;
            //conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call vbsp_ims_risk.F_LOAD_TOTAL_COUNT_RISK_QD62(?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(2, strVbXln);
                calstatement.setString(3, strUserName);
                calstatement.setString(4, sGrade);
                calstatement.setString(5, strStringPosCd);
                calstatement.setString(6, sNambc);
                calstatement.setString(7, sDotrr);
                calstatement.setString(8, sNhomrr);
                calstatement.setString(9, sTrangthai);
                calstatement.setString(10, sChuongtrinh);
                calstatement.setString(11, sNguon_von);
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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getCountTotalRowRisk -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getCountTotalRowRisk " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getCountTotalRowRisk -> " + e.getMessage());
        }

        return nRowTotal;
    }
    
    public int getCapPheduyetQD62(String strUserName, String sGrade) {
        int nRowTotal = 0;
        String strStringPosCd = "";

        try {
             DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call vbsp_ims_risk.F_CHECK_ROLE_QD62(?,?)}";
            ResultSet reset = null;

            try {
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(2, strUserName);
                calstatement.setString(3, sGrade);                
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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getCapPheduyetQD62 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getCapPheduyetQD62 " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getCapPheduyetQD62 -> " + e.getMessage());
        }

        return nRowTotal;
    }

    /**
     *
     * @param strUserName
     * @param sGrade
     * @param lstArrPoscd
     * @param sNambc
     * @param sDotrr
     * @param sNhomrr
     * @param sChuongtrinh
     * @param sSoku
     * @param snguyennhan_tuchoi
     * @return
     */
    public boolean setStatusReject(String strUserName, String sGrade, ArrayList<String> lstArrPoscd,
            String sNambc, String sDotrr, String sNhomrr, String sChuongtrinh, String sSoku, String snguyennhan_tuchoi,String sKhoa) {
        //Duyet list dua ra danh sach main pos la dang "000314","000401","000501"....
        String strStringPosCd = "";
        for (int i = 0; i < lstArrPoscd.size(); i++) {
            if (i != lstArrPoscd.size() - 1) {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\",";
            } else {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\"";
            }
        }

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call vbsp_ims_risk.sp_reject_risk(?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sKhoa);
            calstatement.setString(2, strUserName);
            calstatement.setString(3, sGrade);
            calstatement.setString(4, strStringPosCd);
            calstatement.setString(5, sNambc);
            calstatement.setString(6, sDotrr);
            calstatement.setString(7, sNhomrr);
            calstatement.setString(8, sChuongtrinh);
            calstatement.setString(9, sSoku);
            calstatement.setString(10, snguyennhan_tuchoi);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.VARCHAR);
//                calstatement.setString(2, strCommuneFlg);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(11);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(12);
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
            System.err.println("Loi trong ham setStatusReject " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " setStatusReject -> " + e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean setStatusRejectQD62(String strUserName, String sGrade, ArrayList<String> lstArrPoscd,
            String sNambc, String sDotrr, String sNhomrr, String sChuongtrinh, String sSoku, String snguyennhan_tuchoi,String sKhoa) {
        //Duyet list dua ra danh sach main pos la dang "000314","000401","000501"....
        String strStringPosCd = "";
        for (int i = 0; i < lstArrPoscd.size(); i++) {
            if (i != lstArrPoscd.size() - 1) {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\",";
            } else {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\"";
            }
        }

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call vbsp_ims_risk.SP_REJECT_RISK_QD62(?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sKhoa);
            calstatement.setString(2, strUserName);
            calstatement.setString(3, sGrade);
            calstatement.setString(4, strStringPosCd);
            calstatement.setString(5, sNambc);
            calstatement.setString(6, sDotrr);
            calstatement.setString(7, sNhomrr);
            calstatement.setString(8, sChuongtrinh);
            calstatement.setString(9, sSoku);
            calstatement.setString(10, snguyennhan_tuchoi);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.VARCHAR);
//                calstatement.setString(2, strCommuneFlg);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(11);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(12);
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
            System.err.println("Loi trong ham setStatusReject " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " setStatusReject -> " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     *
     * @param strUserName
     * @param sGrade
     * @param sNambc
     * @param sDotrr
     * @param sSoku
     * @param snguyennhan_tuchoi
     * @return
     */
    public boolean setStatusRejectSearch(String strUserName, String sGrade, String sNambc,
            String sDotrr, String sSoku, String snguyennhan_tuchoi, String sKhoa) {
        //Duyet list dua ra danh sach main pos la dang "000314","000401","000501"....

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call vbsp_ims_risk.sp_reject_risk_search(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sKhoa);
            calstatement.setString(2, strUserName);
            calstatement.setString(3, sGrade);
            calstatement.setString(4, sNambc);
            calstatement.setString(5, sDotrr);
            calstatement.setString(6, sSoku);
            calstatement.setString(7, snguyennhan_tuchoi);
//            calstatement.setString(7, sChuongtrinh);
//            calstatement.setString(8, sSoku);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
//                calstatement.setString(2, strCommuneFlg);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(8);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(9);
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
            System.err.println("Loi trong ham setStatusRejectSearch " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " setStatusRejectSearch -> " + e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean setStatusRejectSearch62(String strUserName, String sGrade, String sNambc,
            String sDotrr, String sSoku, String snguyennhan_tuchoi, String sKhoa) {
        //Duyet list dua ra danh sach main pos la dang "000314","000401","000501"....

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call vbsp_ims_risk.SP_REJECT_RISK_SEARCH_62(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sKhoa);
            calstatement.setString(2, strUserName);
            calstatement.setString(3, sGrade);
            calstatement.setString(4, sNambc);
            calstatement.setString(5, sDotrr);
            calstatement.setString(6, sSoku);
            calstatement.setString(7, snguyennhan_tuchoi);
//            calstatement.setString(7, sChuongtrinh);
//            calstatement.setString(8, sSoku);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
//                calstatement.setString(2, strCommuneFlg);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(8);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(9);
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
            System.err.println("Loi trong ham setStatusRejectSearch " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " setStatusRejectSearch -> " + e.getMessage());
            return false;
        }
        return true;
    }
    //Update du lieu khi phe duyet

    /**
     *
     * @param strUserName
     * @param sGrade
     * @param lstArrPoscd
     * @param sNambc
     * @param sDotrr
     * @param sNhomrr
     * @param sChuongtrinh
     * @param lstRisk
     * @return
     */
    public boolean setStatusRisk(String strUserName, String sGrade, ArrayList<String> lstArrPoscd,
            String sNambc, String sDotrr, String sNhomrr, String sChuongtrinh, List<ListRisk> lstRisk, String sKhoa) {
        boolean bSuccess = false;
        String sArrPoscr = "";
        if (lstRisk.size() == 0) {
            return bSuccess;
        }
        for (int i = 0; i < lstArrPoscd.size(); i++) {
            if (i != lstArrPoscd.size() - 1) {
                sArrPoscr += "\"" + lstArrPoscd.get(i).toString() + "\",";
            } else {
                sArrPoscr += "\"" + lstArrPoscd.get(i).toString() + "\"";
            }
        }
        try {
//            System.err.println(strStringLagecyId);
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call vbsp_ims_risk.sp_set_status_risk(?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {

                StructDescriptor structDescriptor = StructDescriptor.createDescriptor("RISK_TYPE", conn);

                STRUCT[] structs = null;
                structs = new STRUCT[lstRisk.size()];
                int index = 0;
                for (ListRisk value : lstRisk) {
                    if (value.getCheck_legacyid().toLowerCase().equals("false")) {
                        continue;
                    }
                    /*
                     STT INT,
                     tenkh varchar2(100),
                     SOKU VARCHAR2(20),
                     DUNO_RR NUMBER(20),
                     LAI_RR NUMBER(20),
                     ngay_vay VARCHAR2(20),
                     ngay_dh VARCHAR2(20),
                     ngay_rr VARCHAR2(20),
                     thiet_hai number(10,3),
                     THANG number(10),
                     nguyen_nhan varchar2(2)
                     */
                    Object[] params = new Object[11];
                    params[0] = index + 1;
                    params[1] = value.getsTenkh();
                    params[2] = value.getCheck_legacyid();
                    params[3] = value.getDuno_rr();
                    params[4] = value.getLai_rr();
                    params[5] = value.getsNgayvay();
                    params[6] = value.getsNgaydh();
                    params[7] = value.getsNgayrr();
                    params[8] = value.getsThiethai() == null ? " " : value.getsThiethai();
                    params[9] = value.getThang() == null ? "0" : value.getThang();
                    params[10] = value.getsNguyennhan() == null ? " " : value.getsNguyennhan();
                    STRUCT struct = new STRUCT(structDescriptor,
                            conn, params);
                    structs[index] = struct;
                    index++;

                }
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

                ArrayDescriptor desc = ArrayDescriptor.createDescriptor(
                        "RISK_TAB", calstatement.getConnection());
                ARRAY oracleArray = new ARRAY(desc, calstatement.getConnection(), structs);

                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, strUserName);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, sArrPoscr);
                calstatement.setString(5, sNambc);
                calstatement.setString(6, sDotrr);
                calstatement.setString(7, sNhomrr);
                calstatement.setString(8, sChuongtrinh);
                calstatement.setArray(9, oracleArray);

                calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " setStatusRisk -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham setStatusRisk " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " setStatusRisk -> " + e.getMessage());
        }
        return bSuccess;
    }
    
    public boolean setStatusRiskQd62(String strUserName, String sGrade, ArrayList<String> lstArrPoscd,
            String sNambc, String sDotrr, String sNhomrr, String sChuongtrinh, List<ListRisk> lstRisk, String sKhoa) {
        boolean bSuccess = false;
        String sArrPoscr = "";
        if (lstRisk.size() == 0) {
            return bSuccess;
        }
        for (int i = 0; i < lstArrPoscd.size(); i++) {
            if (i != lstArrPoscd.size() - 1) {
                sArrPoscr += "\"" + lstArrPoscd.get(i).toString() + "\",";
            } else {
                sArrPoscr += "\"" + lstArrPoscd.get(i).toString() + "\"";
            }
        }
        try {
//            System.err.println(strStringLagecyId);
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call vbsp_ims_risk.SP_SET_STATUS_RISK_QD62(?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {

                StructDescriptor structDescriptor = StructDescriptor.createDescriptor("RISK_TYPE", conn);

                STRUCT[] structs = null;
                structs = new STRUCT[lstRisk.size()];
                int index = 0;
                for (ListRisk value : lstRisk) {
                    if (value.getCheck_legacyid().toLowerCase().equals("false")) {
                        continue;
                    }
                    /*
                     STT INT,
                     tenkh varchar2(100),
                     SOKU VARCHAR2(20),
                     DUNO_RR NUMBER(20),
                     LAI_RR NUMBER(20),
                     ngay_vay VARCHAR2(20),
                     ngay_dh VARCHAR2(20),
                     ngay_rr VARCHAR2(20),
                     thiet_hai number(10,3),
                     THANG number(10),
                     nguyen_nhan varchar2(2)
                     */
                    Object[] params = new Object[11];
                    params[0] = index + 1;
                    params[1] = value.getsTenkh();
                    params[2] = value.getCheck_legacyid();
                    params[3] = value.getDuno_rr();
                    params[4] = value.getLai_rr();
                    params[5] = value.getsNgayvay();
                    params[6] = value.getsNgaydh();
                    params[7] = value.getsNgayrr();
                    params[8] = value.getsThiethai() == null ? " " : value.getsThiethai();
                    params[9] = value.getThang() == null ? "0" : value.getThang();
                    params[10] = value.getsNguyennhan() == null ? " " : value.getsNguyennhan();
                    STRUCT struct = new STRUCT(structDescriptor,
                            conn, params);
                    structs[index] = struct;
                    index++;

                }
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

                ArrayDescriptor desc = ArrayDescriptor.createDescriptor(
                        "RISK_TAB", calstatement.getConnection());
                ARRAY oracleArray = new ARRAY(desc, calstatement.getConnection(), structs);

                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, strUserName);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, sArrPoscr);
                calstatement.setString(5, sNambc);
                calstatement.setString(6, sDotrr);
                calstatement.setString(7, sNhomrr);
                calstatement.setString(8, sChuongtrinh);
                calstatement.setArray(9, oracleArray);

                calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " setStatusRisk -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham setStatusRisk " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " setStatusRisk -> " + e.getMessage());
        }
        return bSuccess;
    }

    /**
     *
     * @param strUserName
     * @param sGrade
     * @param sPoscd
     * @param sNambc
     * @param sDotrr
     * @param sNhomrr
     * @param lstRisk
     * @return
     */
    public boolean setStatusRisk_Sync_old(String strUserName, String sGrade, String sPoscd,
            String sNambc, String sDotrr, String sNhomrr, List<ListRisk> lstRisk, String Vbxlrr) throws Exception {
        boolean bSuccess = false;
        if (lstRisk.size() == 0) {
            //neu du lieu la ko co khach hang thi insert log
            insertHistotySendLog(sPoscd, strUserName, sGrade, sNambc, sDotrr, sNhomrr, new BigDecimal(BigInteger.ZERO), Define.KHOA_SEND_RR, Vbxlrr);
            return true;
        }

        try {
//            System.err.println(strStringLagecyId);
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call vbsp_ims_risk.SP_SET_STATUS_RISK_SYNC(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {

                StructDescriptor structDescriptor = StructDescriptor.createDescriptor("RISK_TYPE", conn);

                STRUCT[] structs = null;
                structs = new STRUCT[lstRisk.size()];
                int index = 0;
                for (ListRisk value : lstRisk) {
                    if (value.getCheck_legacyid().toLowerCase().equals("false")) {
                        continue;
                    }

                    Object[] params = new Object[11];
                    params[0] = index + 1;
                    params[1] = value.getsTenkh();
                    params[2] = value.getCheck_legacyid();
                    params[3] = value.getDuno_rr();
                    params[4] = value.getLai_rr();
                    params[5] = value.getsNgayvay();
                    params[6] = value.getsNgaydh();
                    params[7] = value.getsNgayrr();
                    params[8] = value.getsThiethai();
                    params[9] = value.getThang();
                    params[10] = value.getsNguyennhan();
                    STRUCT struct = new STRUCT(structDescriptor,
                            conn, params);
                    structs[index] = struct;
                    index++;

                }
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

                ArrayDescriptor desc = ArrayDescriptor.createDescriptor(
                        "RISK_TAB", calstatement.getConnection());
                ARRAY oracleArray = new ARRAY(desc, calstatement.getConnection(), structs);

                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, strUserName);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, sPoscd);
                calstatement.setString(4, sNambc);
                calstatement.setString(5, sDotrr);
                calstatement.setString(6, sNhomrr);
                calstatement.setArray(7, oracleArray);

                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " setStatusRisk_Sync -> " + e.getMessage());
                throw new Exception(e);
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham setStatusRisk_Sync " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " setStatusRisk_Sync -> " + e.getMessage());
            throw new Exception(e);
        }
        return bSuccess;
    }

    public boolean setStatusRisk_Sync(String strUserName, String sGrade, String sPoscd,
            String sNambc, String sDotrr, String sNhomrr, List<ListRiskSync> lstRisk, String Vbxlrr) throws Exception {
        boolean bSuccess = false;
        if (lstRisk.size() == 0) {
            //neu du lieu la ko co khach hang thi insert log
            insertHistotySendLog(sPoscd, strUserName, sGrade, sNambc, sDotrr, sNhomrr, new BigDecimal(BigInteger.ZERO), Define.KHOA_SEND_RR, Vbxlrr);
            return true;
        }

        try {
//            System.err.println(strStringLagecyId);
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call vbsp_ims_risk.SP_SET_STATUS_RISK_SYNC(?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {

                StructDescriptor structDescriptor = StructDescriptor.createDescriptor("RISK_TYPE_SYNC", conn);

                STRUCT[] structs = null;
                structs = new STRUCT[lstRisk.size()];
                int index = 0;
                for (ListRiskSync value : lstRisk) {
//                    if (value.getCheck_legacyid().toLowerCase().equals("false")) {
//                        continue;
//                    }

                    Object[] params = new Object[18];
                    params[0] = index + 1;
                    params[1] = value.getsSoku();
                    params[2] = value.getsTenkh();
                    params[3] = value.getsDnghi_Dno();
                    params[4] = value.getsDnghi_Lai();
                    params[5] = value.getsNgayvay();
                    params[6] = value.getsNgaydh();
                    params[7] = value.getsMdthiethai();
                    params[8] = value.getsNgayrr();
                    params[9] = value.getsDnghi_Tg();
                    params[10] = value.getsNguyennhan();

                    params[11] = value.getsTrangthai();
                    params[12] = value.getsPduyet_Ngay_Cn();
                    params[13] = value.getsPduyet_Nguoi_Cn();
                    params[14] = value.getsPduyet_Cap();
                    params[15] = value.getsNguoi_pduyet_pgd();
                    params[16] = value.getsNgay_pduyet_pgd();
                    params[17] = value.getsNguyennhan_tuchoi();
//                    params[18] = value.getSvbxlrr();
                    STRUCT struct = new STRUCT(structDescriptor,
                            conn, params);
                    structs[index] = struct;
                    index++;

                }
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

                ArrayDescriptor desc = ArrayDescriptor.createDescriptor(
                        "RISK_TAB_SYNC", calstatement.getConnection());
                ARRAY oracleArray = new ARRAY(desc, calstatement.getConnection(), structs);

                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                 calstatement.setString(1, Vbxlrr);
                calstatement.setString(2, strUserName);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, sPoscd);
                calstatement.setString(5, sNambc);
                calstatement.setString(6, sDotrr);
                calstatement.setString(7, sNhomrr);
                calstatement.setArray(8, oracleArray);

                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " setStatusRisk_Sync -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham setStatusRisk_Sync " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " setStatusRisk_Sync -> " + e.getMessage());
            throw new Exception(e);
        }
        return bSuccess;
    }
    //Update du lieu khi phe duyet procedure sp_browse_risk_syn(pv_arr_lagecy_id in varchar2, pv_status_risk in varchar2, pv_Sys_date in varchar2, PN_ERR_CD OUT NUMBER, PV_ERR_TXT OUT VARCHAR2)
    /**
     *
     * @param sLagecyId
     * @param Status_Risk
     * @param sSysdate
     * @return
     */
    public boolean setStatusRisk_syn(String sLagecyId, String Status_Risk, String sSysdate) {
        boolean bSuccess = false;
        try {
//            System.err.println(strStringLagecyId);
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call vbsp_ims_risk.sp_browse_risk_syn(?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(1, sLagecyId);
                calstatement.setString(2, Status_Risk);
                calstatement.setString(3, sSysdate);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
//                calstatement.setString(2, strCommuneFlg);
                bSuccess = calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
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
                bSuccess = false;
                System.err.print(e.getMessage());
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " setStatusRisk -> " + e.getMessage());
            }
        } catch (Exception e) {
            bSuccess = false;
            System.err.println("Loi trong ham setStatusRisk " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " setStatusRisk -> " + e.getMessage());
        }
        return bSuccess;
    }

    /**
     *
     * @param conn
     * @return
     */
    private HashMap<String, String> getStatusRisk(Connection conn) {
        HashMap<String, String> hmStatus = new HashMap<String, String>();
        try {

            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.sp_get_status_risk(?,?,?)}";
            ResultSet reset = null;
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(1);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(2);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(3);
            while (reset.next()) {
                String key = reset.getString("khoa_2");
                String desc = reset.getString("giatri");
                hmStatus.put(key, desc);
            }

            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }

        } catch (Exception e) {
            System.err.println("Loi trong ham getStatusRisk " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getStatusRisk -> " + e.getMessage());
        }
        return hmStatus;
    }

    /**
     *
     * @return
     */
    public HashMap<Integer, List<ListValue>> getDmKhac() {
        HashMap<Integer, List<ListValue>> hm = new HashMap<Integer, List<ListValue>>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.sp_get_dm_risk(?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(1);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(2);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(3);

                List<ListValue> lstTemp = new ArrayList<ListValue>();
                int khoa_1 = 0;
                int previous_khoa_1 = 0;
                boolean fistLoop = true;

                while (reset.next()) {
                    khoa_1 = Integer.parseInt(reset.getString("khoa_1"));
                    String key = reset.getString("khoa_2");
                    String des = reset.getString("giatri");
//                    String stt = reset.getString("stt");

                    if (fistLoop == true) {
                        //Lan dau tien
                        ListValue valueTmp = new ListValue(key, des);
                        lstTemp.add(valueTmp);

                        fistLoop = false;
                    } else if (khoa_1 == previous_khoa_1) {
                        //Neu khoa 1 chua thay doi
                        ListValue valueTmp = new ListValue(key, des);
                        lstTemp.add(valueTmp);
                    } else {
                        //Neu khoa 1 thay doi
                        hm.put(previous_khoa_1, lstTemp);

                        lstTemp = new ArrayList<ListValue>(); //Loai bo het gia tri trong list
                        ListValue valueTmp = new ListValue(key, des);
                        lstTemp.add(valueTmp);
                    }

                    previous_khoa_1 = khoa_1; //Luu lai khoa 1
                }
                hm.put(previous_khoa_1, lstTemp); //Khi ra khoi vong lap can them gia tri cuoi cung

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
                CoreLogger.error(this.getClass().getName() + " getDmKhac -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDmKhac " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDmKhac -> " + e.getMessage());
        }
        return hm;
    }
    
    public HashMap<Integer, List<ListValue>> getDmKhacQD62() {
        HashMap<Integer, List<ListValue>> hm = new HashMap<Integer, List<ListValue>>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_GET_DM_RISK_QD62(?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(1);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(2);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(3);

                List<ListValue> lstTemp = new ArrayList<ListValue>();
                int khoa_1 = 0;
                int previous_khoa_1 = 0;
                boolean fistLoop = true;

                while (reset.next()) {
                    khoa_1 = Integer.parseInt(reset.getString("khoa_1"));
                    String key = reset.getString("khoa_2");
                    String des = reset.getString("giatri");
//                    String stt = reset.getString("stt");

                    if (fistLoop == true) {
                        //Lan dau tien
                        ListValue valueTmp = new ListValue(key, des);
                        lstTemp.add(valueTmp);

                        fistLoop = false;
                    } else if (khoa_1 == previous_khoa_1) {
                        //Neu khoa 1 chua thay doi
                        ListValue valueTmp = new ListValue(key, des);
                        lstTemp.add(valueTmp);
                    } else {
                        //Neu khoa 1 thay doi
                        hm.put(previous_khoa_1, lstTemp);

                        lstTemp = new ArrayList<ListValue>(); //Loai bo het gia tri trong list
                        ListValue valueTmp = new ListValue(key, des);
                        lstTemp.add(valueTmp);
                    }

                    previous_khoa_1 = khoa_1; //Luu lai khoa 1
                }
                hm.put(previous_khoa_1, lstTemp); //Khi ra khoi vong lap can them gia tri cuoi cung

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
                CoreLogger.error(this.getClass().getName() + " getDmKhac -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDmKhac " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDmKhac -> " + e.getMessage());
        }
        return hm;
    }

    /**
     *
     * @param strUserName
     * @param sGrade
     * @param sPoscd
     * @param sNambc
     * @param sDotrr
     * @param sNhomrr
     * @param sNguon_von
     * @return
     */
    public List<ListRisk> getDataSendOld(String strUserName, String sGrade, String sPoscd, String sNambc, String sDotrr, String sNhomrr, String sNguon_von) {
        List<ListRisk> lstRisk = new ArrayList<ListRisk>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_GET_DATA_SEND(?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, strUserName);
            calstatement.setString(2, sGrade);
            calstatement.setString(3, sPoscd);
            calstatement.setString(4, sNambc);
            calstatement.setString(5, sDotrr);
            calstatement.setString(6, sNhomrr);
            calstatement.setString(7, sNguon_von);
//          calstatement.setString(2, strCommuneFlg);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            pn_err_cd = calstatement.getInt(8);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(9);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(10);
            //COLUMN_DESC
//                HashMap<String, String> hmStatus = getStatusRisk(conn);
            while (reset.next()) {
                ListRisk modelRisk = new ListRisk();
                modelRisk.setsTenkh(reset.getString(1));
                modelRisk.setCheck_legacyid(reset.getString(2));
                modelRisk.setDuno_rr(reset.getBigDecimal(3).toString());
                modelRisk.setLai_rr(reset.getBigDecimal(4).toString());
                modelRisk.setsNgayvay(reset.getString(5));
                modelRisk.setsNgaydh(reset.getString(6));
                modelRisk.setsNgayrr(reset.getString(7));
                modelRisk.setsThiethai(reset.getString(8));
                modelRisk.setThang(reset.getBigDecimal(9).toString());
                modelRisk.setsNguyennhan(reset.getString(10));
                lstRisk.add(modelRisk);
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
            System.err.println(strEdd_txt + " Loi trong ham getDataSend " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " getDataSend -> " + e.getMessage());
        }
        return lstRisk;
    }

    public List<ListRiskSync> getDataSend(String strUserName, String sGrade, String sPoscd, String sNambc, String sDotrr, String sNhomrr, String sNguon_von, String vb_xlrr) {
        List<ListRiskSync> lstRisk = new ArrayList<ListRiskSync>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_GET_DATA_SEND(?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, vb_xlrr);
            calstatement.setString(2, strUserName);
            calstatement.setString(3, sGrade);
            calstatement.setString(4, sPoscd);
            calstatement.setString(5, sNambc);
            calstatement.setString(6, sDotrr);
            calstatement.setString(7, sNhomrr);
            calstatement.setString(8, sNguon_von);
//          calstatement.setString(2, strCommuneFlg);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            pn_err_cd = calstatement.getInt(9);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(10);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(11);
            //COLUMN_DESC
//                HashMap<String, String> hmStatus = getStatusRisk(conn);
            while (reset.next()) {
                ListRiskSync modelRisk = new ListRiskSync();
                modelRisk.setsSoku(reset.getString(1));
                modelRisk.setsTenkh(reset.getString(2));
                modelRisk.setsDnghi_Dno(reset.getBigDecimal(3).toString());
                modelRisk.setsDnghi_Lai(reset.getBigDecimal(4).toString());

                modelRisk.setsNgayvay(reset.getString(5));
                modelRisk.setsNgaydh(reset.getString(6));
                modelRisk.setsMdthiethai(reset.getString(7));
                modelRisk.setsNgayrr(reset.getString(8));
                modelRisk.setsDnghi_Tg(reset.getBigDecimal(9).toString());
                modelRisk.setsNguyennhan(reset.getString(10));
                modelRisk.setsTrangthai(reset.getString(11));

                modelRisk.setsPduyet_Ngay_Cn(reset.getString(12));
                modelRisk.setsPduyet_Nguoi_Cn(reset.getString(13));
                modelRisk.setsPduyet_Cap(reset.getString(14));
                modelRisk.setsNguoi_pduyet_pgd(reset.getString(15));
                modelRisk.setsNgay_pduyet_pgd(reset.getString(16));
                modelRisk.setsNguyennhan_tuchoi(reset.getString(17));
                modelRisk.setSvbxlrr(reset.getString(18));
                lstRisk.add(modelRisk);
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
            System.err.println(strEdd_txt + " Loi trong ham getDataSend " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " getDataSend -> " + e.getMessage());
        }
        return lstRisk;
    }

    /**
     *
     * @param conn
     * @param sUserName
     * @param sNambc
     * @param sDotrr
     * @param sSoku_Search
     * @return
     */
    public int getCountSearchRowRisk(Connection conn, String sUserName, String sGrade, String sNambc, String sDotrr, String sSoku_Search, String sKhoa) {
        int nRowTotal = 0;
        try {
            // DaoConnect daoconnect = new DaoConnect();
            //Connection conn = null;
            //conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call vbsp_ims_risk.F_COUNT_SEARCH(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(2, sKhoa);
                calstatement.setString(3, sUserName);
                calstatement.setString(4, sGrade);
                calstatement.setString(5, sNambc);
                calstatement.setString(6, sDotrr);
                calstatement.setString(7, sSoku_Search);
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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getCountSearchRowRisk -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getCountSearchRowRisk " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getCountSearchRowRisk -> " + e.getMessage());
        }

        return nRowTotal;
    }
    
    public int getCountSearchRowRisk62(Connection conn, String sUserName, String sGrade, String sNambc, String sDotrr, String sSoku_Search, String sKhoa) {
        int nRowTotal = 0;
        try {
            // DaoConnect daoconnect = new DaoConnect();
            //Connection conn = null;
            //conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call vbsp_ims_risk.F_COUNT_SEARCH_62(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(2, sKhoa);
                calstatement.setString(3, sUserName);
                calstatement.setString(4, sGrade);
                calstatement.setString(5, sNambc);
                calstatement.setString(6, sDotrr);
                calstatement.setString(7, sSoku_Search);
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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getCountSearchRowRisk -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getCountSearchRowRisk " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getCountSearchRowRisk -> " + e.getMessage());
        }

        return nRowTotal;
    }

    /**
     *
     * @param conn
     * @param sUserName
     * @param sNambc
     * @param sDotrr
     * @param sSoku_Search
     * @param startRow
     * @param EndRow
     * @return
     */
    public List<ModelRiskProcess> getDataSearchRisk(Connection conn, String sUserName, String srGrade, String sNambc, String sDotrr, String sSoku_Search, int startRow, int EndRow, String sKhoa) {
        //Duyet list dua ra danh sach main pos la dang "000314","000401","000501"....
        List<ModelRiskProcess> lstModelRisk = new ArrayList<ModelRiskProcess>();
        try {
            //DaoConnect daoconnect = new DaoConnect();
            //Connection conn = null;
            //conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_SEARCH_LOAN(?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, sUserName);
                calstatement.setString(3, srGrade);
                calstatement.setString(4, sNambc);
                calstatement.setString(5, sDotrr);
                calstatement.setString(6, sSoku_Search);
                calstatement.setInt(7, startRow);
                calstatement.setInt(8, EndRow);
//                calstatement.setString(2, strCommuneFlg);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(9);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(10);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(11);
                //COLUMN_DESC
                while (reset.next()) {
                    ModelRiskProcess modelRisk = new ModelRiskProcess();
//                    System.err.println("Gia tri So ku: ="+reset.getString(2));
                    modelRisk.setsSoku(reset.getString(2));
                    modelRisk.setsMakh(reset.getString(3));
                    modelRisk.setsSoku(reset.getString(2));
                    modelRisk.setsMakh(reset.getString(3));
                    modelRisk.setsTenkh(reset.getString(4));
                    modelRisk.setsDiachi(reset.getString(5));
                    modelRisk.setsChtrinh(reset.getString(6));
                    modelRisk.setsSprd_Cd(reset.getString(7));
                    modelRisk.setsDq_Stat_Cd(reset.getString(8));
                    modelRisk.setDbDngoc(reset.getBigDecimal(9));
                    modelRisk.setDbLaith(reset.getBigDecimal(10));
                    modelRisk.setDbLaiqh(reset.getBigDecimal(11));
                    modelRisk.setDbDnghi_Dno(reset.getBigDecimal(12));
                    modelRisk.setDbDnghi_Lai(reset.getBigDecimal(13));
                    modelRisk.setDbXl_Duno(reset.getBigDecimal(14));
                    modelRisk.setDbXl_Lai(reset.getBigDecimal(15));
                    modelRisk.setDbHt_Dno(reset.getBigDecimal(16));
                    modelRisk.setDbHt_Lai(reset.getBigDecimal(17));
                    modelRisk.setsNgayvay(reset.getDate(18) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(18))); //new SimpleDateFormat("dd/MM/yyyy").format(reset.getString(18))
                    modelRisk.setsNgaydh(reset.getDate(19) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(19)));
                    modelRisk.setDbThoihanvay(reset.getBigDecimal(20));
                    modelRisk.setDbMdthiethai(reset.getBigDecimal(21));
                    Date str = reset.getDate(28);
                    modelRisk.setsNgayrr(reset.getDate(22) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(22)));
                    modelRisk.setDbDnghi_Tg(reset.getBigDecimal(23));
                    modelRisk.setDbPduyet_Tg(reset.getBigDecimal(24));
                    modelRisk.setsNguyennhan(reset.getString(25));
                    modelRisk.setsMotann(reset.getString(26));
                    modelRisk.setsTrangthai(reset.getString(27));
                    modelRisk.setsPduyet_Ngay_Cn(reset.getDate(28) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(28)));
                    modelRisk.setsPduyet_Nguoi_Cn(reset.getString(29));
                    modelRisk.setsPduyet_Ngay_Tw(reset.getDate(30) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(30)));
                    modelRisk.setsPduyet_Nguoi_Tw(reset.getString(31));
                    modelRisk.setsTaolap_Nguoi(reset.getString(32));
                    modelRisk.setsTaolap_Ngay(reset.getDate(33) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(33)));
                    modelRisk.setsMaqd(reset.getString(34));
                    modelRisk.setsTenqd(reset.getString(35));
                    modelRisk.setsNhomrr(reset.getString(36));
                    modelRisk.setsMapgd(reset.getString(37));
                    modelRisk.setsMacn(reset.getString(38));
                    modelRisk.setsNgaybc(reset.getDate(39) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(39)));
                    modelRisk.setsCapnhat(reset.getDate(40) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(40)));
                    modelRisk.setsNgaytao(reset.getDate(41) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(41)));
                    modelRisk.setsNguoitao(reset.getString(42));
                    modelRisk.setsPduyet_Cap(reset.getString(43));
                    modelRisk.setsNgayhl(reset.getDate(44) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(44)));
                    modelRisk.setsHt_Tkxoano(reset.getString(45));
                    modelRisk.setsNguonvon(reset.getString(46));
                    modelRisk.setDbDotrr(reset.getBigDecimal(47));
                    modelRisk.setsMadp(reset.getString(48));
                    modelRisk.setsMato(reset.getString(49));
                    modelRisk.setDbSolanxl(reset.getBigDecimal(50));
                    modelRisk.setsNguoi_pduyet_pgd(reset.getString(51));
                    modelRisk.setsNgay_pduyet_pgd(reset.getDate(52) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(52)));
                    modelRisk.setsNguyennhan_tuchoi(reset.getString(53));
                    lstModelRisk.add(modelRisk);
                }

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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getDataSearchRisk -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataSearchRisk " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getDataSearchRisk -> " + e.getMessage());
        }
        return lstModelRisk;
    }
    public List<ModelRiskProcess> getDataSearchRisk62(Connection conn, String sUserName, String srGrade, String sNambc, String sDotrr, String sSoku_Search, int startRow, int EndRow, String sKhoa) {
        //Duyet list dua ra danh sach main pos la dang "000314","000401","000501"....
        List<ModelRiskProcess> lstModelRisk = new ArrayList<ModelRiskProcess>();
        try {
            //DaoConnect daoconnect = new DaoConnect();
            //Connection conn = null;
            //conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_SEARCH_LOAN_62(?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(1, sKhoa);
                calstatement.setString(2, sUserName);
                calstatement.setString(3, srGrade);
                calstatement.setString(4, sNambc);
                calstatement.setString(5, sDotrr);
                calstatement.setString(6, sSoku_Search);
                calstatement.setInt(7, startRow);
                calstatement.setInt(8, EndRow);
//                calstatement.setString(2, strCommuneFlg);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(9);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(10);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(11);
                //COLUMN_DESC
                while (reset.next()) {
                    ModelRiskProcess modelRisk = new ModelRiskProcess();
//                    System.err.println("Gia tri So ku: ="+reset.getString(2));
                    modelRisk.setsSoku(reset.getString(2));
                    modelRisk.setsMakh(reset.getString(3));
                    modelRisk.setsSoku(reset.getString(2));
                    modelRisk.setsMakh(reset.getString(3));
                    modelRisk.setsTenkh(reset.getString(4));
                    modelRisk.setsDiachi(reset.getString(5));
                    modelRisk.setsChtrinh(reset.getString(6));
                    modelRisk.setsSprd_Cd(reset.getString(7));
                    modelRisk.setsDq_Stat_Cd(reset.getString(8));
                    modelRisk.setDbDngoc(reset.getBigDecimal(9));
                    modelRisk.setDbLaith(reset.getBigDecimal(10));
                    modelRisk.setDbLaiqh(reset.getBigDecimal(11));
                    modelRisk.setDbDnghi_Dno(reset.getBigDecimal(12));
                    modelRisk.setDbDnghi_Lai(reset.getBigDecimal(13));
                    modelRisk.setDbXl_Duno(reset.getBigDecimal(14));
                    modelRisk.setDbXl_Lai(reset.getBigDecimal(15));
                    modelRisk.setDbHt_Dno(reset.getBigDecimal(16));
                    modelRisk.setDbHt_Lai(reset.getBigDecimal(17));
                    modelRisk.setsNgayvay(reset.getDate(18) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(18))); //new SimpleDateFormat("dd/MM/yyyy").format(reset.getString(18))
                    modelRisk.setsNgaydh(reset.getDate(19) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(19)));
                    modelRisk.setDbThoihanvay(reset.getBigDecimal(20));
                    modelRisk.setDbMdthiethai(reset.getBigDecimal(21));
                    Date str = reset.getDate(28);
                    modelRisk.setsNgayrr(reset.getDate(22) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(22)));
                    modelRisk.setDbDnghi_Tg(reset.getBigDecimal(23));
                    modelRisk.setDbPduyet_Tg(reset.getBigDecimal(24));
                    modelRisk.setsNguyennhan(reset.getString(25));
                    modelRisk.setsMotann(reset.getString(26));
                    modelRisk.setsTrangthai(reset.getString(27));
                    modelRisk.setsPduyet_Ngay_Cn(reset.getDate(28) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(28)));
                    modelRisk.setsPduyet_Nguoi_Cn(reset.getString(29));
                    modelRisk.setsPduyet_Ngay_Tw(reset.getDate(30) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(30)));
                    modelRisk.setsPduyet_Nguoi_Tw(reset.getString(31));
                    modelRisk.setsTaolap_Nguoi(reset.getString(32));
                    modelRisk.setsTaolap_Ngay(reset.getDate(33) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(33)));
                    modelRisk.setsMaqd(reset.getString(34));
                    modelRisk.setsTenqd(reset.getString(35));
                    modelRisk.setsNhomrr(reset.getString(36));
                    modelRisk.setsMapgd(reset.getString(37));
                    modelRisk.setsMacn(reset.getString(38));
                    modelRisk.setsNgaybc(reset.getDate(39) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(39)));
                    modelRisk.setsCapnhat(reset.getDate(40) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(40)));
                    modelRisk.setsNgaytao(reset.getDate(41) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(41)));
                    modelRisk.setsNguoitao(reset.getString(42));
                    modelRisk.setsPduyet_Cap(reset.getString(43));
                    modelRisk.setsNgayhl(reset.getDate(44) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(44)));
                    modelRisk.setsHt_Tkxoano(reset.getString(45));
                    modelRisk.setsNguonvon(reset.getString(46));
                    modelRisk.setDbDotrr(reset.getBigDecimal(47));
                    modelRisk.setsMadp(reset.getString(48));
                    modelRisk.setsMato(reset.getString(49));
                    modelRisk.setDbSolanxl(reset.getBigDecimal(50));
                    modelRisk.setsNguoi_pduyet_pgd(reset.getString(51));
                    modelRisk.setsNgay_pduyet_pgd(reset.getDate(52) == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(reset.getDate(52)));
                    modelRisk.setsNguyennhan_tuchoi(reset.getString(53));
                    lstModelRisk.add(modelRisk);
                }

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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getDataSearchRisk -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataSearchRisk " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getDataSearchRisk -> " + e.getMessage());
        }
        return lstModelRisk;
    }

    /**
     *
     * @param strUserName
     * @param sGrade
     * @param sPoscd
     * @param sNambc
     * @param sDotrr
     * @param sNhomrr
     * @param sNguon_von
     * @return
     */
    public DescTableBrower setStatusRiskDv(String khoa,String strUserName, String sGrade, String sPoscd, String sNambc, String sDotrr, String sNhomrr, String sNguon_von) {
        DescTableBrower objTableDesc = new DescTableBrower();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_SET_STATUS_DV(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
              calstatement.setString(1, khoa);
            calstatement.setString(2, strUserName);
            calstatement.setString(3, sGrade);
            calstatement.setString(4, sPoscd);
            calstatement.setString(5, sNambc);
            calstatement.setString(6, sDotrr);
            calstatement.setString(7, sNhomrr);
            calstatement.setString(8, sNguon_von);
//          calstatement.setString(2, strCommuneFlg);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(14, oracle.jdbc.OracleTypes.VARCHAR);
//            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            BigDecimal bTonggoc = calstatement.getBigDecimal(9);
            BigDecimal bTonglai = calstatement.getBigDecimal(10);
            BigDecimal bTongtien = BigDecimal.ZERO;
            bTongtien = bTongtien.add(bTonggoc);
            bTongtien = bTongtien.add(bTonglai);
            objTableDesc.setsPoscd(sPoscd);
            objTableDesc.setsTongDuno(format.format(bTonggoc));
            objTableDesc.setsTongLai(format.format(bTonglai));
            objTableDesc.setsTongtien(format.format(bTongtien));
            objTableDesc.setsSoKh(format.format(calstatement.getBigDecimal(11)));
            objTableDesc.setsPosDesc(calstatement.getString(12));
            pn_err_cd = calstatement.getInt(13);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(14);
            //Lay cursor ra resultset
//            reset = (ResultSet) calstatement.getObject(10);
            //COLUMN_DESC
//                HashMap<String, String> hmStatus = getStatusRisk(conn);
//            while (reset.next()) {
//                DescTableBrower modelRisk = new DescTableBrower();
//                modelRisk.setCheck_legacyid(reset.getString(1));
//                modelRisk.setDuno_rr(reset.getBigDecimal(2).toString());
//                modelRisk.setLai_rr(reset.getBigDecimal(3).toString());
//                modelRisk.setThang(reset.getBigDecimal(4).toString());
//                lstRisk.add(modelRisk);
//            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
            objTableDesc.setsStatus("Thành công");
            objTableDesc.setbSuccess(true);
        } catch (Exception e) {
            objTableDesc.setsStatus("Lỗi");
            objTableDesc.setbSuccess(false);
            System.err.println(strEdd_txt + " Loi trong ham setStatusRiskDv " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " setStatusRiskDv -> " + e.getMessage());
        }
        return objTableDesc;
    }
    
    public DescTableBrower setStatusRiskDv62(String khoa,String strUserName, String sGrade, String sPoscd, String sNambc, String sDotrr, String sNhomrr, String sNguon_von) {
        DescTableBrower objTableDesc = new DescTableBrower();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_SET_STATUS_DV_62(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
              calstatement.setString(1, khoa);
            calstatement.setString(2, strUserName);
            calstatement.setString(3, sGrade);
            calstatement.setString(4, sPoscd);
            calstatement.setString(5, sNambc);
            calstatement.setString(6, sDotrr);
            calstatement.setString(7, sNhomrr);
            calstatement.setString(8, sNguon_von);
//          calstatement.setString(2, strCommuneFlg);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(14, oracle.jdbc.OracleTypes.VARCHAR);
//            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            BigDecimal bTonggoc = calstatement.getBigDecimal(9);
            BigDecimal bTonglai = calstatement.getBigDecimal(10);
            BigDecimal bTongtien = BigDecimal.ZERO;
            bTongtien = bTongtien.add(bTonggoc);
            bTongtien = bTongtien.add(bTonglai);
            objTableDesc.setsPoscd(sPoscd);
            objTableDesc.setsTongDuno(format.format(bTonggoc));
            objTableDesc.setsTongLai(format.format(bTonglai));
            objTableDesc.setsTongtien(format.format(bTongtien));
            objTableDesc.setsSoKh(format.format(calstatement.getBigDecimal(11)));
            objTableDesc.setsPosDesc(calstatement.getString(12));
            pn_err_cd = calstatement.getInt(13);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(14);
            //Lay cursor ra resultset
//            reset = (ResultSet) calstatement.getObject(10);
            //COLUMN_DESC
//                HashMap<String, String> hmStatus = getStatusRisk(conn);
//            while (reset.next()) {
//                DescTableBrower modelRisk = new DescTableBrower();
//                modelRisk.setCheck_legacyid(reset.getString(1));
//                modelRisk.setDuno_rr(reset.getBigDecimal(2).toString());
//                modelRisk.setLai_rr(reset.getBigDecimal(3).toString());
//                modelRisk.setThang(reset.getBigDecimal(4).toString());
//                lstRisk.add(modelRisk);
//            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
            objTableDesc.setsStatus("Thành công");
            objTableDesc.setbSuccess(true);
        } catch (Exception e) {
            objTableDesc.setsStatus("Lỗi");
            objTableDesc.setbSuccess(false);
            System.err.println(strEdd_txt + " Loi trong ham setStatusRiskDv " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " setStatusRiskDv -> " + e.getMessage());
        }
        return objTableDesc;
    }

    /**
     *
     * @param strUserName
     * @param sGrade
     * @param lstPoscd
     * @param sNambc
     * @param sDotrr
     * @param sNhomrr
     * @param sTrangthai
     * @param sNguon_von
     * @return
     */
    public List<DescTableBrower> getDataBrowerView(String strUserName, String sGrade, ArrayList<String> lstPoscd,
            String sNambc, String sDotrr, String sNhomrr, String sTrangthai, String sNguon_von,String sKhoa) {
        List<DescTableBrower> lstTableDesc = new ArrayList<DescTableBrower>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_GET_DATA_POS_BY_POS(?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sKhoa);
            calstatement.setString(2, strUserName);
            calstatement.setString(3, sGrade);
            calstatement.setArray(4, oracle_arrayPoscd);
            calstatement.setString(5, sNambc);
            calstatement.setString(6, sDotrr);
            calstatement.setString(7, sNhomrr);
            calstatement.setString(8, sTrangthai);
            calstatement.setString(9, sNguon_von);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.CURSOR);
//            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
//            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.NUMBER);
//            calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.VARCHAR);
//            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            pn_err_cd = calstatement.getInt(10);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(11);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(12);

            while (reset.next()) {
                DescTableBrower modelRisk = new DescTableBrower();
                BigDecimal bTonggoc = reset.getBigDecimal(4);
                BigDecimal bTonglai = reset.getBigDecimal(5);
                BigDecimal bTongtien = BigDecimal.ZERO;
                bTongtien = bTongtien.add(bTonggoc);
                bTongtien = bTongtien.add(bTonglai);
                modelRisk.setnStt(reset.getInt(1));
                modelRisk.setsPoscd(reset.getString(2));
                modelRisk.setsPosDesc(reset.getString(3));
                modelRisk.setsTongDuno(format.format(bTonggoc));
                modelRisk.setsTongLai(format.format(bTonglai));
                modelRisk.setsSoKh(format.format(reset.getBigDecimal(6)));
                modelRisk.setsTongtien(format.format(bTongtien));
                lstTableDesc.add(modelRisk);
                System.err.println("pos_cd=" + modelRisk.getsPoscd());
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
            System.err.println(strEdd_txt + " Loi trong ham getDataBrowerView " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " getDataBrowerView -> " + e.getMessage());
        }
        return lstTableDesc;
    }
    
    public List<DescTableBrower> getDataBrowerView62(String strUserName, String sGrade, ArrayList<String> lstPoscd,
            String sNambc, String sDotrr, String sNhomrr, String sTrangthai, String sNguon_von,String sKhoa) {
        List<DescTableBrower> lstTableDesc = new ArrayList<DescTableBrower>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_GET_DATA_POS_BY_POS_62(?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sKhoa);
            calstatement.setString(2, strUserName);
            calstatement.setString(3, sGrade);
            calstatement.setArray(4, oracle_arrayPoscd);
            calstatement.setString(5, sNambc);
            calstatement.setString(6, sDotrr);
            calstatement.setString(7, sNhomrr);
            calstatement.setString(8, sTrangthai);
            calstatement.setString(9, sNguon_von);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.CURSOR);
//            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
//            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.NUMBER);
//            calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.VARCHAR);
//            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            pn_err_cd = calstatement.getInt(10);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(11);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(12);

            while (reset.next()) {
                DescTableBrower modelRisk = new DescTableBrower();
                BigDecimal bTonggoc = reset.getBigDecimal(4);
                BigDecimal bTonglai = reset.getBigDecimal(5);
                BigDecimal bTongtien = BigDecimal.ZERO;
                bTongtien = bTongtien.add(bTonggoc);
                bTongtien = bTongtien.add(bTonglai);
                modelRisk.setnStt(reset.getInt(1));
                modelRisk.setsPoscd(reset.getString(2));
                modelRisk.setsPosDesc(reset.getString(3));
                modelRisk.setsTongDuno(format.format(bTonggoc));
                modelRisk.setsTongLai(format.format(bTonglai));
                modelRisk.setsSoKh(format.format(reset.getBigDecimal(6)));
                modelRisk.setsTongtien(format.format(bTongtien));
                
                BigDecimal bTonggoc_1 = reset.getBigDecimal(7);
                BigDecimal bTonglai_1 = reset.getBigDecimal(8);
                modelRisk.setsSoKh_1(format.format(reset.getBigDecimal(9)));
                modelRisk.setsTongDuno_1(format.format(bTonggoc_1));
                modelRisk.setsTongLai_1(format.format(bTonglai_1));
                BigDecimal bTongtien_1 = BigDecimal.ZERO;
                bTongtien_1 = bTongtien_1.add(bTonggoc_1);
                bTongtien_1 = bTongtien_1.add(bTonglai_1);
                 modelRisk.setsTongtien_1(format.format(bTongtien_1));
                lstTableDesc.add(modelRisk);
                System.err.println("pos_cd=" + modelRisk.getsPoscd());
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
            System.err.println(strEdd_txt + " Loi trong ham getDataBrowerView " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " getDataBrowerView -> " + e.getMessage());
        }
        return lstTableDesc;
    }

    public List<DescTableBrower> getDataRejectMainPos(String khoa,ArrayList<String> lstPoscd,
            String sNambc, String sDotrr, String sNhomrr, String sNguon_von) {
        List<DescTableBrower> lstTableDesc = new ArrayList<DescTableBrower>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_GET_DATA_REJECT_MAIN_POS(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, khoa);
            calstatement.setArray(2, oracle_arrayPoscd);
            calstatement.setString(3, sNambc);
            calstatement.setString(4, sDotrr);
            calstatement.setString(5, sNhomrr);
            calstatement.setString(6, sNguon_von);

            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);

            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            pn_err_cd = calstatement.getInt(7);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(8);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(9);
            int index = 1;
            while (reset.next()) {
                DescTableBrower modelRisk = new DescTableBrower();
                modelRisk.setnStt(index);
                modelRisk.setsPoscd(reset.getString(1));
                modelRisk.setsPosDesc(reset.getString(2));
                modelRisk.setsSoKh(format.format(reset.getBigDecimal(3)));
                modelRisk.setsTongtien(format.format(reset.getBigDecimal(4)));
                modelRisk.setsTongDuno(format.format(reset.getBigDecimal(5)));
                modelRisk.setsTongLai(format.format(reset.getBigDecimal(6)));
                modelRisk.setsTongtienDn(format.format(reset.getBigDecimal(7)));
                modelRisk.setsTongDunoDn(format.format(reset.getBigDecimal(8)));
                modelRisk.setsTongLaiDn(format.format(reset.getBigDecimal(9)));
                modelRisk.setsNguyennhan_tc_cn(reset.getString(10));
                lstTableDesc.add(modelRisk);
                System.err.println("pos_cd=" + modelRisk.getsPosDesc());
                index++;
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
            System.err.println(strEdd_txt + " Loi trong ham getDataRejectMainPos " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " getDataRejectMainPos -> " + e.getMessage());
        }
        return lstTableDesc;
    }

    /**
     *
     * @param strUserName
     * @param sGrade
     * @param lstPoscd
     * @param sNambc
     * @param sDotrr
     * @param sNhomrr
     * @param sNguon_von
     * @return
     */
    public List<DescTableBrower> getDataBrowerView1(String strUserName, String sGrade, ArrayList<String> lstPoscd, String sNambc, String sDotrr, String sNhomrr, String sNguon_von) {
        List<DescTableBrower> lstTableDesc = new ArrayList<DescTableBrower>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_GET_DATA_POS_BY_POS(?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, strUserName);
            calstatement.setString(2, sGrade);
            calstatement.setArray(3, oracle_arrayPoscd);
            calstatement.setString(4, sNambc);
            calstatement.setString(5, sDotrr);
            calstatement.setString(6, sNhomrr);
            calstatement.setString(7, sNguon_von);
//          calstatement.setString(2, strCommuneFlg);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);
//            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
//            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.NUMBER);
//            calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.VARCHAR);
//            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            pn_err_cd = calstatement.getInt(8);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(9);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(10);

            while (reset.next()) {
                DescTableBrower modelRisk = new DescTableBrower();
                modelRisk.setnStt(reset.getInt(1));
                modelRisk.setsPoscd(reset.getString(2));
                modelRisk.setsPosDesc(reset.getString(3));
                modelRisk.setsTongDuno(format.format(reset.getBigDecimal(4)));
                modelRisk.setsTongLai(format.format(reset.getBigDecimal(5)));
                modelRisk.setsSoKh(format.format(reset.getBigDecimal(6)));
                lstTableDesc.add(modelRisk);
                System.err.println("pos_cd=" + modelRisk.getsPoscd());
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
            System.err.println(strEdd_txt + " Loi trong ham getDataBrowerView " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " getDataBrowerView -> " + e.getMessage());
        }
        return lstTableDesc;
    }

    /**
     *
     * @param ArrlstPosCd
     * @return
     */
    public ArrayList<String> getPoscdFromMainPos(ArrayList<String> ArrlstPosCd) {
        ArrayList<String> arrPoscd = new ArrayList<String>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            ResultSet reset = null;
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = ArrlstPosCd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);

            String strStoreproce = "{call vbsp_ims_risk.sp_get_pos_from_mainpos(?,?,?,?)}";
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setArray(1, oracle_arrayPoscd);
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
                arrPoscd.add(reset.getString(1));
                System.err.println(reset.getString(2));
            }
            //Lay cursor ra resultset
            System.err.print(strEdd_txt);
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
            System.err.println(" Loi trong ham getPoscdFromMainPos " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getPoscdFromMainPos -> " + e.getMessage());
        }
        return arrPoscd;
    }

    /**
     *
     * @param arrayPoscd
     * @param sNambc
     * @param sDotrr
     * @param sNhomrr
     * @param sNguon_von
     * @return
     */
    public List<DescTableBrower> getDataViewSend(String[] arrayPoscd,
            String sNambc, String sDotrr, String sNhomrr, String sNguon_von, String svbxlrr) {
        List<DescTableBrower> lstTableDesc = new ArrayList<DescTableBrower>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_GET_DATA_VIEW_SEND(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
//            String[] arrayPoscd = lstPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
//            calstatement.setString(1, strUserName);
//            calstatement.setString(2, sGrade);
            calstatement.setString(1, svbxlrr);
            calstatement.setArray(2, oracle_arrayPoscd);
            calstatement.setString(3, sNambc);
            calstatement.setString(4, sDotrr);
            calstatement.setString(5, sNhomrr);
            calstatement.setString(6, sNguon_von);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);
//            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
//            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.NUMBER);
//            calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.VARCHAR);
//            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            pn_err_cd = calstatement.getInt(7);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(8);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(9);

            while (reset.next()) {
                DescTableBrower modelRisk = new DescTableBrower();
                BigDecimal bTonggoc = reset.getBigDecimal(4);
                BigDecimal bTonglai = reset.getBigDecimal(5);
                BigDecimal bTongtien = BigDecimal.ZERO;
                bTongtien = bTongtien.add(bTonggoc);
                bTongtien = bTongtien.add(bTonglai);
                modelRisk.setnStt(reset.getInt(1));
                modelRisk.setsPoscd(reset.getString(2));
                modelRisk.setsPosDesc(reset.getString(3));
                modelRisk.setsTongDuno(format.format(bTonggoc));
                modelRisk.setsTongLai(format.format(bTonglai));
                modelRisk.setsSoKh(format.format(reset.getBigDecimal(6)));
                modelRisk.setsTongtien(format.format(bTongtien));
                lstTableDesc.add(modelRisk);
                System.err.println("pos_cd=" + modelRisk.getsPoscd());
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
            System.err.println(strEdd_txt + " Loi trong ham getDataBrowerView " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " getDataBrowerView -> " + e.getMessage());
        }
        return lstTableDesc;
    }

    /**
     *
     * @param conn
     * @param strUserName
     * @param sGrade
     * @param lstArrPoscd
     * @param sNambc
     * @param sDotrr
     * @param sNhomrr
     * @param sTrangthai
     * @param sChuongtrinh
     * @param sNguon_von
     * @return
     */
    public List<DescTableBrower> getDataViewLoan(Connection conn, String strUserName, String sGrade, ArrayList<String> lstArrPoscd,
            String sNambc, String sDotrr, String sNhomrr, String sTrangthai, String sChuongtrinh, String sNguon_von , String sVbXln) {
        List<DescTableBrower> lstTableDesc = new ArrayList<DescTableBrower>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        String strStringPosCd = "";
        for (int i = 0; i < lstArrPoscd.size(); i++) {
            if (i != lstArrPoscd.size() - 1) {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\",";
            } else {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\"";
            }
        }
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_LOAD_DATA_TOTAL_RISK_VIEW(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

//            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
//            String[] arrayPoscd = lstPoscd.toArray(new String[0]);
//            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sVbXln);
            calstatement.setString(2, strUserName);
            calstatement.setString(3, sGrade);
            calstatement.setString(4, strStringPosCd);
            calstatement.setString(5, sNambc);
            calstatement.setString(6, sDotrr);
            calstatement.setString(7, sNhomrr);
            calstatement.setString(8, sTrangthai);
            calstatement.setString(9, sChuongtrinh);
            calstatement.setString(10, sNguon_von);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.CURSOR);
//            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
//            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.NUMBER);
//            calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.VARCHAR);
//            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            pn_err_cd = calstatement.getInt(11);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(12);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(13);
            int nStt = 1;
            BigDecimal bTongallkh = BigDecimal.ZERO;
            BigDecimal bTongalldn = BigDecimal.ZERO;
            BigDecimal bTongalllai = BigDecimal.ZERO;
            BigDecimal bTongalltien = BigDecimal.ZERO;
            while (reset.next()) {
                DescTableBrower modelRisk = new DescTableBrower();
                BigDecimal bTonggoc = reset.getBigDecimal(4);
                BigDecimal bTonglai = reset.getBigDecimal(5);
                BigDecimal bTongtien = BigDecimal.ZERO;
                bTongtien = bTongtien.add(bTonggoc);
                bTongtien = bTongtien.add(bTonglai);

                bTongallkh = bTongallkh.add(reset.getBigDecimal(3));
                bTongalldn = bTongalldn.add(bTonggoc);
                bTongalllai = bTongalllai.add(bTonglai);
                bTongalltien = bTongalltien.add(bTongtien);

                modelRisk.setnStt(nStt);
                modelRisk.setsPoscd(reset.getString(1));
                modelRisk.setsPosDesc(reset.getString(2));
                modelRisk.setsTongDuno(format.format(bTonggoc));
                modelRisk.setsTongLai(format.format(bTonglai));
                modelRisk.setsSoKh(format.format(reset.getBigDecimal(3)));
                modelRisk.setsTongtien(format.format(bTongtien));
                lstTableDesc.add(modelRisk);
                System.err.println("pos_cd=" + modelRisk.getsPoscd());
                nStt++;
            }
            DescTableBrower modelRisk = new DescTableBrower();
            modelRisk.setsPoscd("999999");
            modelRisk.setsPosDesc("Tổng cộng");
            modelRisk.setsSoKh(format.format(bTongallkh));
            modelRisk.setsTongDuno(format.format(bTongalldn));
            modelRisk.setsTongLai(format.format(bTongalllai));
            modelRisk.setsTongtien(format.format(bTongalltien));
            lstTableDesc.add(modelRisk);
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
//            if (conn != null) {
//                conn.close();
//            }

        } catch (Exception e) {
            System.err.println(strEdd_txt + " Loi trong ham getDataViewLoan " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " getDataViewLoan -> " + e.getMessage());
        }
        return lstTableDesc;
    }
    
    public List<DescTableBrower> getDataViewLoanQD62(Connection conn, String strUserName, String sGrade, ArrayList<String> lstArrPoscd,
            String sNambc, String sDotrr, String sNhomrr, String sTrangthai, String sChuongtrinh, String sNguon_von , String sVbXln) {
        List<DescTableBrower> lstTableDesc = new ArrayList<DescTableBrower>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        String strStringPosCd = "";
        for (int i = 0; i < lstArrPoscd.size(); i++) {
            if (i != lstArrPoscd.size() - 1) {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\",";
            } else {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\"";
            }
        }
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_TOTAL_RISK_VIEW_QD62(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

//            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
//            String[] arrayPoscd = lstPoscd.toArray(new String[0]);
//            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sVbXln);
            calstatement.setString(2, strUserName);
            calstatement.setString(3, sGrade);
            calstatement.setString(4, strStringPosCd);
            calstatement.setString(5, sNambc);
            calstatement.setString(6, sDotrr);
            calstatement.setString(7, sNhomrr);
            calstatement.setString(8, sTrangthai);
            calstatement.setString(9, sChuongtrinh);
            calstatement.setString(10, sNguon_von);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.CURSOR);
//            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
//            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.NUMBER);
//            calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.VARCHAR);
//            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            pn_err_cd = calstatement.getInt(11);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(12);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(13);
            int nStt = 1;
            BigDecimal bTongallkh = BigDecimal.ZERO;
            BigDecimal bTongalldn = BigDecimal.ZERO;
            BigDecimal bTongalllai = BigDecimal.ZERO;
            BigDecimal bTongalltien = BigDecimal.ZERO;
            while (reset.next()) {
                DescTableBrower modelRisk = new DescTableBrower();
                BigDecimal bTonggoc = reset.getBigDecimal(4);
                BigDecimal bTonglai = reset.getBigDecimal(5);
                BigDecimal bTongtien = BigDecimal.ZERO;
                bTongtien = bTongtien.add(bTonggoc);
                bTongtien = bTongtien.add(bTonglai);

                bTongallkh = bTongallkh.add(reset.getBigDecimal(3));
                bTongalldn = bTongalldn.add(bTonggoc);
                bTongalllai = bTongalllai.add(bTonglai);
                bTongalltien = bTongalltien.add(bTongtien);

                modelRisk.setnStt(nStt);
                modelRisk.setsPoscd(reset.getString(1));
                modelRisk.setsPosDesc(reset.getString(2));
                modelRisk.setsTongDuno(format.format(bTonggoc));
                modelRisk.setsTongLai(format.format(bTonglai));
                modelRisk.setsSoKh(format.format(reset.getBigDecimal(3)));
                modelRisk.setsTongtien(format.format(bTongtien));
                lstTableDesc.add(modelRisk);
                System.err.println("pos_cd=" + modelRisk.getsPoscd());
                nStt++;
            }
            DescTableBrower modelRisk = new DescTableBrower();
            modelRisk.setsPoscd("999999");
            modelRisk.setsPosDesc("Tổng cộng");
            modelRisk.setsSoKh(format.format(bTongallkh));
            modelRisk.setsTongDuno(format.format(bTongalldn));
            modelRisk.setsTongLai(format.format(bTongalllai));
            modelRisk.setsTongtien(format.format(bTongalltien));
            lstTableDesc.add(modelRisk);
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
//            if (conn != null) {
//                conn.close();
//            }

        } catch (Exception e) {
            System.err.println(strEdd_txt + " Loi trong ham getDataViewLoan " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " getDataViewLoan -> " + e.getMessage());
        }
        return lstTableDesc;
    }

    /**
     *
     * @param lstPoscd
     * @param sNambc
     * @param sDotrr
     * @param sNhomrr
     * @param sNguon_von
     * @return
     */
    public HashMap<String, DescTableBrower> getDataSendDetail(ArrayList<String> lstPoscd,
            String sNambc, String sDotrr, String sNhomrr, String sNguon_von, String sVbxlrr) throws Exception {
        HashMap<String, DescTableBrower> hmTableDesc = new HashMap<String, DescTableBrower>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_GET_DATA_VIEW_SEND(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
//            calstatement.setString(1, strUserName);
            calstatement.setString(1, sVbxlrr);
            calstatement.setArray(2, oracle_arrayPoscd);
            calstatement.setString(3, sNambc);
            calstatement.setString(4, sDotrr);
            calstatement.setString(5, sNhomrr);
            calstatement.setString(6, sNguon_von);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);
//            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
//            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.NUMBER);
//            calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.VARCHAR);
//            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            pn_err_cd = calstatement.getInt(7);
            //thu hien lay mo ta loi
            strEdd_txt = calstatement.getString(8);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(9);

            while (reset.next()) {
                DescTableBrower modelRisk = new DescTableBrower();
                BigDecimal bTonggoc = reset.getBigDecimal(4);
                BigDecimal bTonglai = reset.getBigDecimal(5);
                BigDecimal bTongtien = BigDecimal.ZERO;
                bTongtien = bTongtien.add(bTonggoc);
                bTongtien = bTongtien.add(bTonglai);
                modelRisk.setnStt(reset.getInt(1));
                modelRisk.setsPoscd(reset.getString(2));
                modelRisk.setsPosDesc(reset.getString(3));
                modelRisk.setsTongDuno(format.format(bTonggoc));
                modelRisk.setsTongLai(format.format(bTonglai));
                modelRisk.setsSoKh(format.format(reset.getBigDecimal(6)));
                modelRisk.setsTongtien(format.format(bTongtien));
                hmTableDesc.put(modelRisk.getsPoscd(), modelRisk);
                System.err.println("pos_cd=" + modelRisk.getsPoscd());
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
            System.err.println(strEdd_txt + " Loi trong ham getDataSendDetail " + e.getMessage());
            CoreLogger.error(strEdd_txt + this.getClass().getName() + " getDataSendDetail -> " + e.getMessage());
            throw new Exception(e);
        }
        return hmTableDesc;
    }

    /**
     * ham insertHistotySendLog
     *
     * @param sPoscd
     * @param strUserName
     * @param sGrade
     * @param sNambc
     * @param sDotrr
     * @param sNhomrr
     * @return boolean
     */
    public boolean insertHistotySendLog(String sPoscd, String strUserName, String sGrade,
            String sNambc, String sDotrr, String sNhomrr, BigDecimal bSomon, String sKhoa, String Vbxlrr) throws Exception {
        boolean bSuccess = false;

        try {
//            System.err.println(strStringLagecyId);
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call vbsp_ims_risk.SP_INSERT_HISTORY_SEND_LOG(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

                //Tham so thu nhat truyen vao la co lay theo pos hay main pos 
                calstatement.setString(1, Vbxlrr);
                calstatement.setString(2, sPoscd);
                calstatement.setString(3, strUserName);
                calstatement.setString(4, sGrade);
                calstatement.setString(5, sNambc);
                calstatement.setString(6, sDotrr);
                calstatement.setString(7, sNhomrr);
                calstatement.setBigDecimal(8, bSomon);
                calstatement.setString(9, sKhoa);
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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " insertHistotySendLog -> " + e.getMessage());
                throw new Exception(e);
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham insertHistotySendLog " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " insertHistotySendLog -> " + e.getMessage());
            throw new Exception(e);
        }
        return bSuccess;
    }

    /**
     *
     * @param sPoscd
     * @param sNambc
     * @param sDotrr
     * @param sNhomrr
     * @param sKhoa
     * @return
     */
    public List<HistorySendData> getHistorySendData(String sPoscd,
            String sNambc, String sDotrr, String sNhomrr, String sKhoa, String vbxlrr) {

        List<HistorySendData> lstModelHist = new ArrayList<HistorySendData>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.sp_get_log_send_history(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                 calstatement.setString(1, vbxlrr);
                calstatement.setString(2, sPoscd);
                calstatement.setString(3, sNambc);
                calstatement.setString(4, sDotrr);
                calstatement.setString(5, sNhomrr);
                calstatement.setString(6, sKhoa);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(7);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(8);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(9);
                //COLUMN_DESC
//                HashMap<String, String> hmStatus = getStatusRisk(conn);

                while (reset.next()) {
                    HistorySendData modelHist = new HistorySendData();
//                    System.err.println("Gia tri So ku: ="+reset.getString(2));
                    modelHist.setsMapgd(reset.getString(1));
                    modelHist.setsMacn(reset.getString(2));
                    modelHist.setsUserid(reset.getString(3));
                    modelHist.setsCapbc(reset.getString(4));
                    modelHist.setsNamrr(reset.getString(5));
                    modelHist.setsDotrr(reset.getString(6));
                    modelHist.setsNhomrr(reset.getString(7));
                    modelHist.setsNgaytao(reset.getString(8));
                    modelHist.setsSomon(reset.getString(9));
                    lstModelHist.add(modelHist);
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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getHistorySendData -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getHistorySendData " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getHistorySendData -> " + e.getMessage());
        }
        return lstModelHist;
    }

    /**
     *
     * @param sMacn
     * @param sUserName
     * @param sGrade
     * @param sNambc
     * @param sDotrr
     * @param sNhomrr
     * @param sKhoa
     * @return
     */
    public boolean setStatusSendDataHistory(String sMacn, String sUserName, String sGrade,
            String sNambc, String sDotrr, String sNhomrr, String sKhoa, String sKhoaRR) {
        boolean bSuccess = false;

        try {
//            System.err.println(strStringLagecyId);
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call vbsp_ims_risk.sp_update_status_send_data(?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sKhoaRR);
                calstatement.setString(2, sMacn);
                calstatement.setString(3, sUserName);
                calstatement.setString(4, sGrade);
                calstatement.setString(5, sNambc);
                calstatement.setString(6, sDotrr);
                calstatement.setString(7, sNhomrr);
                calstatement.setString(8, sKhoa);

                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
                //Thuc hien execute lay du lieu
                bSuccess = calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(9);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(10);

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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " setStatusSendDataHistory -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham setStatusSendDataHistory " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " setStatusSendDataHistory -> " + e.getMessage());
        }
        return bSuccess;
    }

    public List<ModelRiskProcess.StatusHistorySend> getStatusHistorySendData(String sNambc, String sDotrr, String sNhomrr, String sKhoa) {

        List<StatusHistorySend> lstModelHist = new ArrayList<StatusHistorySend>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_GET_ALL_CN_SEND(?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(1, sKhoa);        
                calstatement.setString(2, sNambc);
                calstatement.setString(3, sDotrr);
                calstatement.setString(4, sNhomrr);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(5);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(6);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(7);
                //COLUMN_DESC
//                HashMap<String, String> hmStatus = getStatusRisk(conn);
                int nStt = 1;
                while (reset.next()) {
                    StatusHistorySend modelHist = new StatusHistorySend();
//                    System.err.println("Gia tri So ku: ="+reset.getString(2));
                    modelHist.setnStt(nStt);
                    modelHist.setsMacn(reset.getString(1));
                    modelHist.setsTencn(reset.getString(2));
                    modelHist.setsSend(reset.getString(3));
                    modelHist.setsStatus(reset.getString(4));
                    modelHist.setVb_xlrr(sKhoa);
                    lstModelHist.add(modelHist);
                    nStt += 1;
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
                CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getStatusHistorySendData -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getStatusHistorySendData " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " getStatusHistorySendData -> " + e.getMessage());
        }
        return lstModelHist;
    }

    public boolean setBlockAllPos(List<String> lstMacn, String sUserName, String sGrade,
            String sNambc, String sDotrr, String sNhomrr, String sKhoa, String sKhoaRR) {
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            ResultSet reset = null;
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstMacn.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);

            String strStoreproce = "{call vbsp_ims_risk.sp_Block_all_POS(?,?,?,?,?,?,?,?,?,?)}";
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, sKhoaRR);
            calstatement.setArray(2, oracle_arrayPoscd);
            calstatement.setString(3, sUserName);
            calstatement.setString(4, sGrade);
            calstatement.setString(5, sNambc);
            calstatement.setString(6, sDotrr);
            calstatement.setString(7, sNhomrr);
            calstatement.setString(8, sKhoa);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
            
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(9);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(10);
            //Lay cursor ra resultset

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
            System.err.println(" Loi trong ham setBlockAllPos " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " setBlockAllPos -> " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean setContentRejectMainPos(String vb_xlrr,String sUserName, String sGrade, List<String> lstMacn,
            String sNambc, String sDotrr, String sNhomrr, String sNguon_von, String sNguyennhan) {
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            ResultSet reset = null;
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstMacn.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);

            String strStoreproce = "{call vbsp_ims_risk.SP_SET_CONTENT_REJECT_MAIN_POS(?,?,?,?,?,?,?,?,?)}";
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, vb_xlrr);
            calstatement.setArray(2, oracle_arrayPoscd);
            calstatement.setString(3, sNambc);
            calstatement.setString(4, sDotrr);
            calstatement.setString(5, sNhomrr);
            calstatement.setString(6, sNguon_von);
            calstatement.setString(7, sNguyennhan);

            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(8);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(9);
            //Lay cursor ra resultset

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
            System.err.println(" Loi trong ham setContentRejectMainPos " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " setContentRejectMainPos -> " + e.getMessage());
            return false;
        }
        return true;
    }

     public boolean setContentRejectMainPos1(String sUserName, String sGrade, List<String> lstMacn,
            String sNambc, String sDotrr, String sNhomrr, String sNguon_von, String sNguyennhan) {
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            ResultSet reset = null;
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstMacn.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);

            String strStoreproce = "{call vbsp_ims_risk.SP_SET_CONTENT_REJECT_MAIN_POS(?,?,?,?,?,?,?,?)}";
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setArray(1, oracle_arrayPoscd);
            calstatement.setString(2, sNambc);
            calstatement.setString(3, sDotrr);
            calstatement.setString(4, sNhomrr);
            calstatement.setString(5, sNguon_von);
            calstatement.setString(6, sNguyennhan);

            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(7);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(8);
            //Lay cursor ra resultset

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
            System.err.println(" Loi trong ham setContentRejectMainPos " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " setContentRejectMainPos -> " + e.getMessage());
            return false;
        }
        return true;
    }
    public List<HistorySendData> getViewHistorySend(String macn, String nam_rr, String dot_rr, String nhom_rr, String khoa, String sKhoaRR) {
        List<HistorySendData> lstViewHis = new ArrayList<HistorySendData>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_risk.SP_VIEW_HISTORY_SEND(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sKhoaRR);
            calstatement.setString(2, macn);
            calstatement.setString(3, nam_rr);
            calstatement.setString(4, dot_rr);
            calstatement.setString(5, nhom_rr);
            calstatement.setString(6, khoa);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(7);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(8);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(9);
            //COLUMN_DESC
//                HashMap<String, String> hmStatus = getStatusRisk(conn);

            while (reset.next()) {
                HistorySendData modelHist = new HistorySendData();
//                    System.err.println("Gia tri So ku: ="+reset.getString(2));
                modelHist.setsMapgd(reset.getString(1));
                modelHist.setsMacn(reset.getString(2));
                modelHist.setsUserid(reset.getString(3));
                modelHist.setsCapbc(reset.getString(4));
                modelHist.setsNamrr(reset.getString(5));
                modelHist.setsDotrr(reset.getString(6));
                modelHist.setsNhomrr(reset.getString(7));
                modelHist.setsNgaytao(reset.getString(8));
                modelHist.setsSomon(reset.getString(9));
                modelHist.setsTenpgd(reset.getString(10));
                lstViewHis.add(modelHist);
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
            System.err.println(" Loi trong ham getViewHistorySend " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getViewHistorySend -> " + e.getMessage());
        }
        return lstViewHis;
    }
    
    public boolean updateInfoLoan(String strUserName, String sGrade, ArrayList<String> lstArrPoscd,
            String sNambc, String sDotrr, String sNhomrr, String sChuongtrinh, String sSoku, String sKhoa,
            String sTenkh,String sNgayvay, String dbMdthiethai,String sNgayrr,String dbDnghi_Tg,String dbPduyet_Tg,
            String dbHt_Dno,String dbHt_Lai,  String dbDnghi_Lai) {
        //Duyet list dua ra danh sach main pos la dang "000314","000401","000501"....
        String strStringPosCd = "";
        for (int i = 0; i < lstArrPoscd.size(); i++) {
            if (i != lstArrPoscd.size() - 1) {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\",";
            } else {
                strStringPosCd += "\"" + lstArrPoscd.get(i).toString() + "\"";
            }
        }

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call vbsp_ims_risk.SP_UPDATE_INFO_RISK(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
            calstatement.setString(1, sKhoa);
            calstatement.setString(2, strUserName);
            calstatement.setString(3, sGrade);
            calstatement.setString(4, strStringPosCd);
            calstatement.setString(5, sNambc);
            calstatement.setString(6, sDotrr);
            calstatement.setString(7, sNhomrr);
            calstatement.setString(8, sChuongtrinh);
            calstatement.setString(9, sSoku);
            calstatement.setString(10, sTenkh);
            calstatement.setString(11, sNgayvay);
            calstatement.setString(12, dbMdthiethai);
            calstatement.setString(13, sNgayrr);
            calstatement.setString(14, dbDnghi_Tg);
            calstatement.setString(15, dbPduyet_Tg);
            calstatement.setString(16, dbHt_Dno);
            calstatement.setString(17, dbHt_Lai);
            calstatement.registerOutParameter(18, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(19, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(20, dbDnghi_Lai);
//            calstatement.setString(21, dbDnghi_Lai);
//                calstatement.setString(2, strCommuneFlg);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(18);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(19);
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
            System.err.println("Loi trong ham updateInfoLoan " + e.getMessage());
            CoreLogger.error(DaoProcessRisk.class.getCanonicalName() + " updateInfoLoan -> " + e.getMessage());
            return false;
        }
        return true;
    }

}
