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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.DcptNoModel;
import vbsp.ims.model.DcptNoModel.ViewTotalCust;
import vbsp.ims.model.DcptNoModel.saveDcNo;
import vbsp.ims.model.DcptNoModel.savePtNo;
import vbsp.ims.model.DcptNoModel.senddcpt;
import vbsp.ims.model.LoveLeafModel;
import vbsp.ims.model.LoveLeafModel.SaveLoveLeaf;
import vbsp.ims.model.ModelRiskProcess;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author BAOANH
 */
public class DaoLoveLeaf {

    //<editor-fold defaultstate="collapsed" desc="Khoi tao cho main form danh muc,tree view, to truong">
    /**
     *
     * @return HashMap <Integer,List<ListValue>>
     */
    public HashMap<Integer, List<ListValue>> getDmKhac() {
        HashMap<Integer, List<ListValue>> hm = new HashMap<Integer, List<ListValue>>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_DCPT_NO.SP_GET_DM(?,?,?)}";
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
    
    
    public List<ListValue> getNguyennhanDm()
    {
        List<ListValue> lstNNDM = new ArrayList<ListValue>();
         try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_DCPT_NO.SP_GET_NGUYENNHANDM(?,?,?)}";
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
                while (reset.next()) {
                    String key = reset.getString(1);
                    String des = reset.getString(2);
//                    String stt = reset.getString("stt");

                   lstNNDM.add(new ListValue(key, des));
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
                CoreLogger.error(this.getClass().getName() + " getNguyennhanDm -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getNguyennhanDm " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getNguyennhanDm -> " + e.getMessage());
        }
         return lstNNDM;
    }
    /**
     *
     * @param strUserName
     * @param sGrade
     * @return List<ModelTreeNode>
     */
    public List<ModelTreeNode> getDataPosTreeNode(String strUserName, String sGrade) {
        List<ModelTreeNode> lstPo = new ArrayList<ModelTreeNode>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_DCPT_NO.SP_GET_TREE_NODE(?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos
                calstatement.setString(1, strUserName);
                calstatement.setString(2, sGrade);
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
                CoreLogger.error(this.getClass().getCanonicalName() + " getDataPosTreeNode -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataPosTreeNode " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataPosTreeNode -> " + e.getMessage());
        }
        return lstPo;
    }
    
    public List<ModelTreeNode> getDataPosTreeNodeGroup(String strUserName, String sGrade, String sMaXa, String sDVUT) {
        List<ModelTreeNode> lstPo = new ArrayList<ModelTreeNode>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_DCPT_NO.SP_GET_TREE_NODE_GROUP(?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos
                calstatement.setString(1, strUserName);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, sMaXa);
                calstatement.setString(4, sDVUT);
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
                CoreLogger.error(this.getClass().getCanonicalName() + " getDataPosTreeNode -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataPosTreeNode " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataPosTreeNode -> " + e.getMessage());
        }
        return lstPo;
    }

    /**
     *
     * @param sUserName
     * @param sGrade
     * @param lstPoscd
     * @param sDvut
     * @return
     */
    
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Lay du lieu va tong so khach hang">
    /**
     *
     * @param conn
     * @param sUserName
     * @param sGrade
     * @param sNgaysl
     * @param lstArrPoscd
     * @param sDvut
     * @param sMato
     * @param sNguonvon
     * @param sChuongtrinh
     * @return int
     */
    public int getCountTotalCustData(Connection conn, String sUserName, String sGrade, String sNgaysl, List<String> lstArrPoscd) {
        int nCountTotalCust = 0;
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call app_loveleaf_proj.F_GET_TOTAL_COUNT_LOVELEAF(?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(2, sUserName);
            calstatement.setString(3, sGrade);
            calstatement.setString(4, sNgaysl);
            calstatement.setArray(5, oracle_arrayPoscd);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            nCountTotalCust = calstatement.getInt(1);
            pn_err_cd = calstatement.getInt(6);
            strEdd_txt = calstatement.getString(7);
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getCountTotalCustData " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getCountTotalCustData -> " + e.getMessage());
        }
        return nCountTotalCust;
    }  
    
    public int getDataCheckLock( String sUserName,  List<String> lstArrPoscd, String sMato) {
        int nCountTotalCust = 0;
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call VBSP_IMS_DCPT_NO.F_GET_CHECK_LOCK(?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(2, sUserName);
            calstatement.setArray(3, oracle_arrayPoscd);
            calstatement.setString(4, sMato);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            nCountTotalCust = calstatement.getInt(1);
            pn_err_cd = calstatement.getInt(5);
            strEdd_txt = calstatement.getString(6);
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataCheckLock " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataCheckLock -> " + e.getMessage());
        }
        return nCountTotalCust;
    }  
    
    public int getDataCheckLockSend( String sPosCD,  String sMato) {
        int nCountTotalCust = 0;
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call VBSP_IMS_DCPT_NO.F_GET_CHECK_LOCK_SEND(?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(2, sPosCD);
            calstatement.setString(3, sMato);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            nCountTotalCust = calstatement.getInt(1);
            pn_err_cd = calstatement.getInt(4);
            strEdd_txt = calstatement.getString(5);
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataCheckLock " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataCheckLock -> " + e.getMessage());
        }
        return nCountTotalCust;
    }  
    
    public int getCountTotalPt(Connection conn, String sUserName, String sGrade, String sNgaysl, List<String> lstArrPoscd,
            String sDvut, String sMato, String sNguonvon, String sChuongtrinh, String trangthai) {
        int nCountTotalCust = 0;
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call VBSP_IMS_DCPT_NO.F_GET_TOTAL_COUNT_PT(?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.VARCHAR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(2, sUserName);
            calstatement.setString(3, sGrade);
            calstatement.setString(4, sNgaysl);
            calstatement.setArray(5, oracle_arrayPoscd);
            calstatement.setString(6, sDvut);
            calstatement.setString(7, sMato);
            calstatement.setString(8, sNguonvon);
            calstatement.setString(9, sChuongtrinh);
            calstatement.setString(10, trangthai);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            nCountTotalCust = calstatement.getInt(1);
            pn_err_cd = calstatement.getInt(11);
            strEdd_txt = calstatement.getString(12);
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getCountTotalPt " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getCountTotalPt -> " + e.getMessage());
        }
        return nCountTotalCust;
    }

    public List<LoveLeafModel> getDataCust(Connection conn, String sUserName, String sGrade, String sNgaysl, List<String> lstArrPoscd,
            String sTrangthai, int nStartRow, int nEndRow) {
        List<LoveLeafModel> lstLoveLeaf = new ArrayList<LoveLeafModel>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{CALL app_loveleaf_proj.SP_GET_DATA_LOVELEAF(?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setString(3, sNgaysl);
            calstatement.setArray(4, oracle_arrayPoscd);
            calstatement.setString(5, sTrangthai);
            calstatement.setInt(6, nStartRow);
            calstatement.setInt(7, nEndRow);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(10);

            pn_err_cd = calstatement.getInt(8);
            strEdd_txt = calstatement.getString(9);

            while (reset.next()) {
                LoveLeafModel value = new LoveLeafModel();
//                value.setnStt(reset.getInt(1));
                value.setsPosCd(reset.getString(1));
                value.setsRefNo(reset.getString(2));
                value.setsPoorID(reset.getString(3));
                value.setsPoorName(reset.getString(4));
                value.setsAmount(reset.getString(5));
                value.setsTranDT(reset.getString(6));
                value.setsCommnue(reset.getString(7));
//                value.setsDistrict(reset.getString(8));
//                value.setsCommnue(reset.getString(9));
                value.setsStatus(reset.getString(8));      
                value.setsComment(reset.getString(9));  
                value.setsPoor_Hidden(reset.getString(10));    

                lstLoveLeaf.add(value);
            }

            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataCust " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataCust -> " + e.getMessage());
        }
        return lstLoveLeaf;
    }
    
    public List<LoveLeafModel> getDataSearch(Connection conn, String sUserName, String sGrade, String sNgaysl, List<String> lstArrPoscd,
            String sTrangthai,String sPoor) {
        List<LoveLeafModel> lstLoveLeaf = new ArrayList<LoveLeafModel>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{CALL app_loveleaf_proj.SP_GET_DATA_LOVELEAF_SEARCH(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setString(3, sNgaysl);
            calstatement.setArray(4, oracle_arrayPoscd);
            calstatement.setString(5, sTrangthai);
            calstatement.setString(9, sPoor);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(8);

            pn_err_cd = calstatement.getInt(6);
            strEdd_txt = calstatement.getString(7);

            while (reset.next()) {
                LoveLeafModel value = new LoveLeafModel();
//                value.setnStt(reset.getInt(1));
                value.setsPosCd(reset.getString(1));
                value.setsRefNo(reset.getString(2));
                value.setsPoorID(reset.getString(3));
                value.setsPoorName(reset.getString(4));
                value.setsAmount(reset.getString(5));
                value.setsTranDT(reset.getString(6));
                value.setsCommnue(reset.getString(7));
//                value.setsDistrict(reset.getString(8));
//                value.setsCommnue(reset.getString(9));
                value.setsStatus(reset.getString(8));      
                value.setsComment(reset.getString(9));  
                value.setsPoor_Hidden(reset.getString(10));    

                lstLoveLeaf.add(value);
            }

            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataCust " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataCust -> " + e.getMessage());
        }
        return lstLoveLeaf;
    }
    
    public List<DcptNoModel> getDataPt(Connection conn, String sUserName, String sGrade, String sNgaysl, List<String> lstArrPoscd,
            String sDvut, String sMato, String sNguonvon, String sChuongtrinh, String trangthai, int nStartRow, int nEndRow) {
        List<DcptNoModel> lstDcptNo = new ArrayList<DcptNoModel>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{CALL VBSP_IMS_DCPT_NO.SP_GET_DATA_PT(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(14, oracle.jdbc.OracleTypes.CURSOR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setString(3, sNgaysl);
            calstatement.setArray(4, oracle_arrayPoscd);
            calstatement.setString(5, sDvut);
            calstatement.setString(6, sMato);
            calstatement.setString(7, sNguonvon);
            calstatement.setString(8, sChuongtrinh);
            calstatement.setString(9, trangthai);
            calstatement.setInt(10, nStartRow);
            calstatement.setInt(11, nEndRow);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(14);

            pn_err_cd = calstatement.getInt(12);
            strEdd_txt = calstatement.getString(13);

            while (reset.next()) {
                DcptNoModel value = new DcptNoModel();
                value.setnStt(reset.getInt(1));
                value.setsSoku(reset.getString(2));
                value.setsMakh(reset.getString(3));
                value.setsTenkh(reset.getString(4));
                BigDecimal bTongtien = BigDecimal.ZERO;
                value.setsDno_Than(reset.getBigDecimal(5).toString());
                value.setsDno_Qhan(reset.getBigDecimal(6).toString());
                value.setsDno_Khoanh(reset.getBigDecimal(7).toString());
                bTongtien = bTongtien.add(reset.getBigDecimal(5));
                bTongtien = bTongtien.add(reset.getBigDecimal(6));
                bTongtien = bTongtien.add(reset.getBigDecimal(7));
                value.setsTongsotien(bTongtien.toString());
                value.setsLaiton_Than(reset.getBigDecimal(8).toString());
                value.setsLaiton_Qhan(reset.getBigDecimal(9).toString());
                value.setsLai_Ton(reset.getBigDecimal(10).toString());
                value.setsMato(reset.getString(11));
                value.setsDvut(reset.getString(12));
                value.setsMa_Spham(reset.getString(13));
                value.setsMaqd(reset.getString(14));
                value.setsCtrinh(reset.getString(15));
                value.setsNogoc_Clech(reset.getBigDecimal(16).toString());
                value.setsNolai_Clech(reset.getBigDecimal(17).toString());
                value.setsNguyennhan_Lech(reset.getString(18));
                value.setsThuctrang_Dtdt(reset.getString(19));
                value.setsTongduno_Knt(reset.getBigDecimal(20).toString());
                value.setsDno_Chayy(reset.getBigDecimal(21).toString());
                value.setsDno_Xlrr(reset.getBigDecimal(22).toString());
                value.setsDno_Sxkd_Thualo(reset.getBigDecimal(23).toString());
                value.setsDno_Rr_Kquan(reset.getBigDecimal(24).toString());
                value.setsDno_Moi(reset.getBigDecimal(25).toString());
                value.setsDno_Khong_Dc(reset.getBigDecimal(26).toString());
                value.setsDno_Ditu(reset.getBigDecimal(27).toString());
                value.setsDno_Khnhan_No(reset.getBigDecimal(28).toString());
                value.setsDno_Khconhan_No(reset.getBigDecimal(29).toString());
                value.setsDno_Xkld_Nn(reset.getBigDecimal(30).toString());
                value.setsDno_Nn_Khac(reset.getBigDecimal(31).toString());
                value.setsNguonvon(reset.getString(32));
                value.setsTrangthai_Dc(reset.getString(33));
                value.setsTt_Monvay(reset.getString(34));
                value.setsNgaybc(reset.getString(35));
                value.setsNguoi_Nhap_Dc(reset.getString(36));
                value.setsNgay_Nhap_Dc(reset.getString(37));
                value.setsNguoi_Nhap_Pt(reset.getString(38));
                value.setsNgay_Nhap_Pt(reset.getString(39));
                value.setsMadp(reset.getString(40));
                value.setsMapgd(reset.getString(41));
                value.setsMacn(reset.getString(42));
                value.setsCapnhat(reset.getString(43));
                value.setsMaNN(reset.getString(44));
                value.setsSotien(reset.getString(45));

                lstDcptNo.add(value);
            }

            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataPt " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataPt -> " + e.getMessage());
        }
        return lstDcptNo;
    }

    public List<DcptNoModel> getDetailCustomer(String sSoku, String sNgaysl, String sMato) {
        List<DcptNoModel> lstDcptNo = new ArrayList<DcptNoModel>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            Connection conn = null;
            conn = new DaoConnect().getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{CALL VBSP_IMS_DCPT_NO.SP_GET_DETAIL_CUSTOMER(?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sSoku);
            calstatement.setString(2, sNgaysl);
            calstatement.setString(3, sMato);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(6);

            pn_err_cd = calstatement.getInt(4);
            strEdd_txt = calstatement.getString(5);

            while (reset.next()) {
                DcptNoModel value = new DcptNoModel();
                value.setnStt(reset.getInt(1));
                value.setsSoku(reset.getString(2));
                value.setsTk_Casa1(reset.getString(3));
                value.setsTk_Casa2(reset.getString(4));
                value.setsMakh(reset.getString(5));
                value.setsTenkh(reset.getString(6));
                value.setsDno_Than(reset.getBigDecimal(7).toString());
                value.setsDno_Qhan(reset.getBigDecimal(8).toString());
                value.setsDno_Khoanh(reset.getBigDecimal(9).toString());
                value.setsSodu_Casa(reset.getBigDecimal(10).toString());
                value.setsLaiton_Than(reset.getBigDecimal(11).toString());
                value.setsLaiton_Qhan(reset.getBigDecimal(12).toString());
                value.setsLai_Ton(reset.getBigDecimal(13).toString());
                value.setsMato(reset.getString(14));
                value.setsDvut(reset.getString(15));
                value.setsMa_Spham(reset.getString(16));
                value.setsMaqd(reset.getString(17));
                value.setsCtrinh(reset.getString(18));
                value.setsNogoc_Clech(reset.getBigDecimal(19).toString());
                value.setsNolai_Clech(reset.getBigDecimal(20).toString());
                value.setsSoducasa_Clech(reset.getBigDecimal(21).toString());
                value.setsNguyennhan_Lech(reset.getString(22).trim());
                value.setsThuctrang_Dtdt(reset.getString(23).trim());
                value.setsTongduno_Knt(reset.getBigDecimal(24).toString());
                value.setsDno_Chayy(reset.getBigDecimal(25).toString());
                value.setsDno_Xlrr(reset.getBigDecimal(26).toString());
                value.setsDno_Sxkd_Thualo(reset.getBigDecimal(27).toString());
                value.setsDno_Rr_Kquan(reset.getBigDecimal(28).toString());
                value.setsDno_Moi(reset.getBigDecimal(29).toString());
                value.setsDno_Khong_Dc(reset.getBigDecimal(30).toString());
                value.setsDno_Ditu(reset.getBigDecimal(31).toString());
                value.setsDno_Khnhan_No(reset.getBigDecimal(32).toString());
                value.setsDno_Khconhan_No(reset.getBigDecimal(33).toString());
                value.setsDno_Xkld_Nn(reset.getBigDecimal(34).toString());
                value.setsDno_Nn_Khac(reset.getBigDecimal(35).toString());
                value.setsNguonvon(reset.getString(36));
                value.setsTrangthai_Dc(reset.getString(37));
                value.setsTt_Monvay(reset.getString(38));
                value.setsNgaybc(reset.getString(39));
                value.setsNguoi_Nhap_Dc(reset.getString(40));
                value.setsNgay_Nhap_Dc(reset.getString(41));
                value.setsNguoi_Nhap_Pt(reset.getString(42));
                value.setsNgay_Nhap_Pt(reset.getString(43));
                value.setsMadp(reset.getString(44));
                value.setsMapgd(reset.getString(45));
                value.setsMacn(reset.getString(46));
                value.setsCapnhat(reset.getString(47));
                value.setsTongsotien(reset.getString(48));

                lstDcptNo.add(value);
            }

            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataCust " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataCust -> " + e.getMessage());
        }
        return lstDcptNo;
    }

    public List<ViewTotalCust> getViewTotalCustData(Connection conn, String sUserName, String sGrade, String sNgaysl, List<String> lstArrPoscd,
            String sDvut, String sMato, String sNguonvon, String sChuongtrinh, String trangthai) {
        List<ViewTotalCust> lstViewCust = new ArrayList<ViewTotalCust>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{CALL VBSP_IMS_DCPT_NO.SP_GET_VIEW_TOTAL_CUST(?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.CURSOR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setString(3, sNgaysl);
            calstatement.setArray(4, oracle_arrayPoscd);
            calstatement.setString(5, sDvut);
            calstatement.setString(6, sMato);
            calstatement.setString(7, sNguonvon);
            calstatement.setString(8, sChuongtrinh);
            calstatement.setString(9, trangthai);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)

            pn_err_cd = calstatement.getInt(10);
            strEdd_txt = calstatement.getString(11);
            reset = (ResultSet) calstatement.getObject(12);
            while (reset.next()) {
                ViewTotalCust value = new ViewTotalCust();
                value.setsSoKh(Integer.toString(reset.getInt(1)));
                value.setsTongtien(DefineFun.FormatNumber(reset.getBigDecimal(2)));
                value.setsNothan(DefineFun.FormatNumber(reset.getBigDecimal(3)));
                value.setsNoqhan(DefineFun.FormatNumber(reset.getBigDecimal(4)));
                value.setsNokhoanh(DefineFun.FormatNumber(reset.getBigDecimal(5)));
                value.setsNolai(DefineFun.FormatNumber(reset.getBigDecimal(8)));
                value.setsSoduCasa(DefineFun.FormatNumber(reset.getBigDecimal(9)));
                System.err.println("Tong so kh=" + Integer.toString(reset.getInt(1)));
                lstViewCust.add(value);
            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getViewTotalCustData " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getViewTotalCustData -> " + e.getMessage());
        }
        return lstViewCust;
    }
    
    public List<ModelRiskProcess.StatusHistorySend> getStatusHistorySendDcpt(String sMacn, String sMaPGD, String sMaXa) {

        List<ModelRiskProcess.StatusHistorySend> lstModelHist = new ArrayList<ModelRiskProcess.StatusHistorySend>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_dcpt_no.SP_GET_ALL_CN_SEND(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  

                calstatement.setString(1, sMacn);
                calstatement.setString(2, sMaPGD);
                calstatement.setString(3, sMaXa);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(4);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(5);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);
                //COLUMN_DESC
//                HashMap<String, String> hmStatus = getStatusRisk(conn);
                int nStt = 1;
                while (reset.next()) {
                    ModelRiskProcess.StatusHistorySend modelHist = new ModelRiskProcess.StatusHistorySend();
//                    System.err.println("Gia tri So ku: ="+reset.getString(2));
                    modelHist.setnStt(nStt);
                    modelHist.setsMacn(reset.getString(1));
                    modelHist.setsTencn(reset.getString(2));
                    modelHist.setsSend(reset.getString(3));
                    modelHist.setsStatus(reset.getString(4));
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
    
    
    
    public List<ViewTotalCust> getViewTotalCustDataSendGroup(Connection conn, String sUserName, String sGrade, String sNgaysl, List<String> lstArrPoscd,
            String sDvut, String sMato, String sNguonvon, String sChuongtrinh) {
        List<ViewTotalCust> lstViewCust = new ArrayList<ViewTotalCust>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{CALL VBSP_IMS_DCPT_NO.SP_GET_VIEW_TOTAL_SEND_GROUP(?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.CURSOR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setString(3, sNgaysl);
            calstatement.setArray(4, oracle_arrayPoscd);
            calstatement.setString(5, sDvut);
            calstatement.setString(6, sMato);
            calstatement.setString(7, sNguonvon);
            calstatement.setString(8, sChuongtrinh);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)

            pn_err_cd = calstatement.getInt(9);
            strEdd_txt = calstatement.getString(10);
            reset = (ResultSet) calstatement.getObject(11);
            while (reset.next()) {
                ViewTotalCust value = new ViewTotalCust();
                value.setsSoKh(Integer.toString(reset.getInt(1)));
                value.setsTongtien(DefineFun.FormatNumber(reset.getBigDecimal(2)));
                value.setsNothan(DefineFun.FormatNumber(reset.getBigDecimal(3)));
                value.setsNoqhan(DefineFun.FormatNumber(reset.getBigDecimal(4)));
                value.setsNokhoanh(DefineFun.FormatNumber(reset.getBigDecimal(5)));
                value.setsNolai(DefineFun.FormatNumber(reset.getBigDecimal(8)));
                value.setsSoduCasa(DefineFun.FormatNumber(reset.getBigDecimal(9)));
                value.setsNoNCK(DefineFun.FormatNumber(reset.getBigDecimal(10)));
                value.setsNoKCKN(DefineFun.FormatNumber(reset.getBigDecimal(11)));
                System.err.println("Tong so kh=" + Integer.toString(reset.getInt(1)));
                lstViewCust.add(value);
            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getViewTotalCustData " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getViewTotalCustData -> " + e.getMessage());
        }
        return lstViewCust;
    }
    
    public List<ViewTotalCust> getViewTotalPt(Connection conn, String sUserName, String sGrade, String sNgaysl, List<String> lstArrPoscd,
            String sDvut, String sMato, String sNguonvon, String sChuongtrinh, String trangthai) {
        List<ViewTotalCust> lstViewCust = new ArrayList<ViewTotalCust>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{CALL VBSP_IMS_DCPT_NO.SP_GET_VIEW_TOTAL_PT(?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.CURSOR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setString(3, sNgaysl);
            calstatement.setArray(4, oracle_arrayPoscd);
            calstatement.setString(5, sDvut);
            calstatement.setString(6, sMato);
            calstatement.setString(7, sNguonvon);
            calstatement.setString(8, sChuongtrinh);
            calstatement.setString(9, trangthai);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)

            pn_err_cd = calstatement.getInt(10);
            strEdd_txt = calstatement.getString(11);
            reset = (ResultSet) calstatement.getObject(12);
            while (reset.next()) {
                ViewTotalCust value = new ViewTotalCust();
                value.setsSoKh(Integer.toString(reset.getInt(1)));
                value.setsTongtien(DefineFun.FormatNumber(reset.getBigDecimal(2)));
                value.setsNothan(DefineFun.FormatNumber(reset.getBigDecimal(3)));
                value.setsNoqhan(DefineFun.FormatNumber(reset.getBigDecimal(4)));
                value.setsNokhoanh(DefineFun.FormatNumber(reset.getBigDecimal(5)));
                value.setsNolai(DefineFun.FormatNumber(reset.getBigDecimal(8)));
                System.err.println("Tong so kh=" + Integer.toString(reset.getInt(1)));
                lstViewCust.add(value);
            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getViewTotalPt " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getViewTotalPt -> " + e.getMessage());
        }
        return lstViewCust;
    }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Luu du lieu cho doi chieu va phan tich">
    
    public boolean SaveDataLoveLeaf(String sUserName, String sNgaysl, List<SaveLoveLeaf> lstLove,String sTrangthai) {
        if (lstLove == null || lstLove.size() == 0) {
            return false;
        }
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call app_loveleaf_proj.SP_SAVE_LOVE_LEAF(?,?,?,?,?,?)}";
            ResultSet reset = null;
            StructDescriptor structDescriptor = StructDescriptor.createDescriptor("LOVELEAF_TYPE", conn);
            
            STRUCT[] structs = null;
            structs = new STRUCT[lstLove.size()];
            int index = 0;
            for (SaveLoveLeaf value : lstLove) {
                Object[] params = new Object[5];
                params[0] = value.getsPoorID();
                params[1] = value.getsTranDT();
                params[2] = value.getsComment();
                params[3] = value.getsPoor_Hidden();
                 params[4] = value.getsStatus();
                STRUCT struct = new STRUCT(structDescriptor,
                        conn, params);
                structs[index] = struct;
                index++;
            }
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            
            ArrayDescriptor desc = ArrayDescriptor.createDescriptor(
                    "LOVELEAF_TAB", calstatement.getConnection());
            ARRAY oracleArray = new ARRAY(desc, calstatement.getConnection(), structs);
            
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sNgaysl);
            calstatement.setString(3, sTrangthai);
            calstatement.setArray(4, oracleArray);
            
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
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
            System.err.println("Loi trong ham SaveDataLoveLeaf " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " SaveDataLoveLeaf -> " + e.getMessage());
            return false;
        }
        return true;
    }
    
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Cho tim kiem khoan vay">
    /**
     *
     * @param conn
     * @param sUserName
     * @param sGrade
     * @param sNgaysl
     * @param lstArrPoscd
     * @param sDvut
     * @param sMato
     * @param sNguonvon
     * @param sChuongtrinh
     * @return int
     */
    public int getCountTotalSearchCust(Connection conn, String sUserName, String sGrade, String sSoku, String sNgaysl, List<String> lstArrPoscd,
            String sDvut, String sMato, String sNguonvon, String sChuongtrinh) {
        int nCountTotalCust = 0;
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call VBSP_IMS_DCPT_NO.F_GET_TOTAL_SEARCH_LOAN(?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.VARCHAR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(2, sUserName);
            calstatement.setString(3, sGrade);
            calstatement.setString(4, sSoku);
            calstatement.setString(5, sNgaysl);
            calstatement.setArray(6, oracle_arrayPoscd);
            calstatement.setString(7, sDvut);
            calstatement.setString(8, sMato);
            calstatement.setString(9, sNguonvon);
            calstatement.setString(10, sChuongtrinh);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            nCountTotalCust = calstatement.getInt(1);
            pn_err_cd = calstatement.getInt(11);
            strEdd_txt = calstatement.getString(12);
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getCountTotalSearchCust " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getCountTotalSearchCust -> " + e.getMessage());
        }
        return nCountTotalCust;
    }

    public int getCountTotalSearchCustPt(Connection conn, String sUserName, String sGrade, String sSoku, String sNgaysl, List<String> lstArrPoscd,
            String sDvut, String sMato, String sNguonvon, String sChuongtrinh) {
        int nCountTotalCust = 0;
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{?=call VBSP_IMS_DCPT_NO.F_GET_TOTAL_SEARCH_LOAN_PT(?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.VARCHAR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(2, sUserName);
            calstatement.setString(3, sGrade);
            calstatement.setString(4, sSoku);
            calstatement.setString(5, sNgaysl);
            calstatement.setArray(6, oracle_arrayPoscd);
            calstatement.setString(7, sDvut);
            calstatement.setString(8, sMato);
            calstatement.setString(9, sNguonvon);
            calstatement.setString(10, sChuongtrinh);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            nCountTotalCust = calstatement.getInt(1);
            pn_err_cd = calstatement.getInt(11);
            strEdd_txt = calstatement.getString(12);
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getCountTotalSearchCustPt " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getCountTotalSearchCustPt -> " + e.getMessage());
        }
        return nCountTotalCust;
    }

    public List<DcptNoModel> getDataCustSearchLoan(Connection conn, String sUserName, String sGrade, String sSoku, String sNgaysl, List<String> lstArrPoscd,
            String sDvut, String sMato, String sNguonvon, String sChuongtrinh, int nStartRow, int nEndRow) {
        List<DcptNoModel> lstDcptNo = new ArrayList<DcptNoModel>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{CALL VBSP_IMS_DCPT_NO.SP_SEARCH_LOAN(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(14, oracle.jdbc.OracleTypes.CURSOR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setString(3, sSoku);
            calstatement.setString(4, sNgaysl);
            calstatement.setArray(5, oracle_arrayPoscd);
            calstatement.setString(6, sDvut);
            calstatement.setString(7, sMato);
            calstatement.setString(8, sNguonvon);
            calstatement.setString(9, sChuongtrinh);
            calstatement.setInt(10, nStartRow);
            calstatement.setInt(11, nEndRow);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(14);

            pn_err_cd = calstatement.getInt(12);
            strEdd_txt = calstatement.getString(13);

            while (reset.next()) {
                DcptNoModel value = new DcptNoModel();
                value.setnStt(reset.getInt(1));
                value.setsSoku(reset.getString(2));
                value.setsTk_Casa1(reset.getString(3));
                value.setsTk_Casa2(reset.getString(4));
                value.setsMakh(reset.getString(5));
                value.setsTenkh(reset.getString(6));
                value.setsDno_Than(reset.getBigDecimal(7).toString());
                value.setsDno_Qhan(reset.getBigDecimal(8).toString());
                value.setsDno_Khoanh(reset.getBigDecimal(9).toString());
                BigDecimal bTongtien = BigDecimal.ZERO;
                bTongtien = bTongtien.add(reset.getBigDecimal(7));
                bTongtien = bTongtien.add(reset.getBigDecimal(8));
                bTongtien = bTongtien.add(reset.getBigDecimal(9));
                value.setsTongsotien(bTongtien.toString());
                value.setsSodu_Casa(reset.getBigDecimal(10).toString());
                value.setsLaiton_Than(reset.getBigDecimal(11).toString());
                value.setsLaiton_Qhan(reset.getBigDecimal(12).toString());
                value.setsLai_Ton(reset.getBigDecimal(13).toString());
                value.setsMato(reset.getString(14));
                value.setsDvut(reset.getString(15));
                value.setsMa_Spham(reset.getString(16));
                value.setsMaqd(reset.getString(17));
                value.setsCtrinh(reset.getString(18));
                value.setsNogoc_Clech(reset.getBigDecimal(19).toString());
                value.setsNolai_Clech(reset.getBigDecimal(20).toString());
                value.setsSoducasa_Clech(reset.getBigDecimal(21).toString());
                value.setsNguyennhan_Lech(reset.getString(22));
                value.setsThuctrang_Dtdt(reset.getString(23));
                value.setsTongduno_Knt(reset.getBigDecimal(24).toString());
                value.setsDno_Chayy(reset.getBigDecimal(25).toString());
                value.setsDno_Xlrr(reset.getBigDecimal(26).toString());
                value.setsDno_Sxkd_Thualo(reset.getBigDecimal(27).toString());
                value.setsDno_Rr_Kquan(reset.getBigDecimal(28).toString());
                value.setsDno_Moi(reset.getBigDecimal(29).toString());
                value.setsDno_Khong_Dc(reset.getBigDecimal(30).toString());
                value.setsDno_Ditu(reset.getBigDecimal(31).toString());
                value.setsDno_Khnhan_No(reset.getBigDecimal(32).toString());
                value.setsDno_Khconhan_No(reset.getBigDecimal(33).toString());
                value.setsDno_Xkld_Nn(reset.getBigDecimal(34).toString());
                value.setsDno_Nn_Khac(reset.getBigDecimal(35).toString());
                value.setsNguonvon(reset.getString(36));
                value.setsTrangthai_Dc(reset.getString(37));
                value.setsTt_Monvay(reset.getString(38));
                value.setsNgaybc(reset.getString(39));
                value.setsNguoi_Nhap_Dc(reset.getString(40));
                value.setsNgay_Nhap_Dc(reset.getString(41));
                value.setsNguoi_Nhap_Pt(reset.getString(42));
                value.setsNgay_Nhap_Pt(reset.getString(43));
                value.setsMadp(reset.getString(44));
                value.setsMapgd(reset.getString(45));
                value.setsMacn(reset.getString(46));
                value.setsCapnhat(reset.getString(47));
                lstDcptNo.add(value);
            }

            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataCust " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataCust -> " + e.getMessage());
        }
        return lstDcptNo;
    }

    public List<DcptNoModel> getDataCustSearchLoanPt(Connection conn, String sUserName, String sGrade, String sSoku, String sNgaysl, List<String> lstArrPoscd,
            String sDvut, String sMato, String sNguonvon, String sChuongtrinh, int nStartRow, int nEndRow) {
        List<DcptNoModel> lstDcptNo = new ArrayList<DcptNoModel>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstArrPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{CALL VBSP_IMS_DCPT_NO.SP_SEARCH_LOAN_PT(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(12, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(14, oracle.jdbc.OracleTypes.CURSOR);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setString(3, sSoku);
            calstatement.setString(4, sNgaysl);
            calstatement.setArray(5, oracle_arrayPoscd);
            calstatement.setString(6, sDvut);
            calstatement.setString(7, sMato);
            calstatement.setString(8, sNguonvon);
            calstatement.setString(9, sChuongtrinh);
            calstatement.setInt(10, nStartRow);
            calstatement.setInt(11, nEndRow);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(14);

            pn_err_cd = calstatement.getInt(12);
            strEdd_txt = calstatement.getString(13);

            while (reset.next()) {
                DcptNoModel value = new DcptNoModel();
                value.setnStt(reset.getInt(1));
                value.setsSoku(reset.getString(2));
                value.setsMakh(reset.getString(3));
                value.setsTenkh(reset.getString(4));
                BigDecimal bTongtien = BigDecimal.ZERO;
                value.setsDno_Than(reset.getBigDecimal(5).toString());
                value.setsDno_Qhan(reset.getBigDecimal(6).toString());
                value.setsDno_Khoanh(reset.getBigDecimal(7).toString());
                bTongtien = bTongtien.add(reset.getBigDecimal(5));
                bTongtien = bTongtien.add(reset.getBigDecimal(6));
                bTongtien = bTongtien.add(reset.getBigDecimal(7));
                value.setsTongsotien(bTongtien.toString());
                value.setsLaiton_Than(reset.getBigDecimal(8).toString());
                value.setsLaiton_Qhan(reset.getBigDecimal(9).toString());
                value.setsLai_Ton(reset.getBigDecimal(10).toString());
                value.setsMato(reset.getString(11));
                value.setsDvut(reset.getString(12));
                value.setsMa_Spham(reset.getString(13));
                value.setsMaqd(reset.getString(14));
                value.setsCtrinh(reset.getString(15));
                value.setsNogoc_Clech(reset.getBigDecimal(16).toString());
                value.setsNolai_Clech(reset.getBigDecimal(17).toString());
                value.setsNguyennhan_Lech(reset.getString(18));
                value.setsThuctrang_Dtdt(reset.getString(19));
                value.setsTongduno_Knt(reset.getBigDecimal(20).toString());
                value.setsDno_Chayy(reset.getBigDecimal(21).toString());
                value.setsDno_Xlrr(reset.getBigDecimal(22).toString());
                value.setsDno_Sxkd_Thualo(reset.getBigDecimal(23).toString());
                value.setsDno_Rr_Kquan(reset.getBigDecimal(24).toString());
                value.setsDno_Moi(reset.getBigDecimal(25).toString());
                value.setsDno_Khong_Dc(reset.getBigDecimal(26).toString());
                value.setsDno_Ditu(reset.getBigDecimal(27).toString());
                value.setsDno_Khnhan_No(reset.getBigDecimal(28).toString());
                value.setsDno_Khconhan_No(reset.getBigDecimal(29).toString());
                value.setsDno_Xkld_Nn(reset.getBigDecimal(30).toString());
                value.setsDno_Nn_Khac(reset.getBigDecimal(31).toString());
                value.setsNguonvon(reset.getString(32));
                value.setsTrangthai_Dc(reset.getString(33));
                value.setsTt_Monvay(reset.getString(34));
                value.setsNgaybc(reset.getString(35));
                value.setsNguoi_Nhap_Dc(reset.getString(36));
                value.setsNgay_Nhap_Dc(reset.getString(37));
                value.setsNguoi_Nhap_Pt(reset.getString(38));
                value.setsNgay_Nhap_Pt(reset.getString(39));
                value.setsMadp(reset.getString(40));
                value.setsMapgd(reset.getString(41));
                value.setsMacn(reset.getString(42));
                value.setsCapnhat(reset.getString(43));
                value.setsMaNN(reset.getString(44));
                value.setsSotien(reset.getString(45));
                lstDcptNo.add(value);
            }

            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataCustSearchLoanPt " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataCustSearchLoanPt -> " + e.getMessage());
        }
        return lstDcptNo;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Cho phần gửi dữ liệu từ CN -> TW">
      public HashMap<Integer,Object> getDataSendLove(String sUserName, String sGrade,List<String> lstPoscd, String sNgaysl, 
             List<String> lstPoor) {
        HashMap<Integer,Object> hmObjOut = new HashMap<Integer,Object>();
        List<String> lstDataSend = new ArrayList<String>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call app_loveleaf_proj.SP_GET_DATA_SEND_LOVE(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;
            //Khoi tao goi store
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = lstPoscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            
            ArrayDescriptor des_soku = ArrayDescriptor.createDescriptor("ARRAY_TABLE", conn);
            String[] arraySoku= lstPoor.toArray(new String[0]);
            ARRAY oracle_arraySoku= new ARRAY(des_soku, conn, arraySoku);
            
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sUserName);
            calstatement.setString(2, sGrade);
            calstatement.setArray(3, oracle_arrayPoscd);
            calstatement.setString(4, sNgaysl);
            calstatement.setArray(5, oracle_arraySoku);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            String sPos_cd=calstatement.getString(8);
            reset = (ResultSet) calstatement.getObject(9);
            while (reset.next()) {
                lstDataSend.add(reset.getString(1));
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
            hmObjOut.put(1, sPos_cd==null?"999999":sPos_cd==""?"999999":sPos_cd);
            hmObjOut.put(2, lstDataSend);
        } catch (Exception e) {
            System.err.println(" Loi trong ham getDataSend " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataSend -> " + e.getMessage());
        }
        return hmObjOut;
    }
      
      public boolean updateData_Sync_LoveLeaf(String strUserName, String sGrade, String sPoscd, List<LoveLeafModel> lstDcptNo) {
        boolean bSuccess = false;
        if (lstDcptNo.size() == 0) {
            //neu du lieu la ko co khach hang thi insert log
//            insertHistotySendLog(sPoscd, strUserName, sGrade, sNambc, sDotrr, sNhomrr, new BigDecimal(BigInteger.ZERO), Define.KHOA_SEND_RR);
            return true;
        }

        try {
//            System.err.println(strStringLagecyId);
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call app_loveleaf_proj.SP_UPDATE_SYNC_LOVE(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {

                StructDescriptor structDescriptor = StructDescriptor.createDescriptor("LOVE_TYPE_SYNC", conn);

                STRUCT[] structs = null;
                structs = new STRUCT[lstDcptNo.size()];
                int index = 0;
                for (LoveLeafModel value : lstDcptNo) {

                    Object[] params = new Object[5];
                    params[0]=value.getsPoorID();
                    params[1]=value.getsPosCd();
                    params[2]=value.getsTranDT();
                    params[3]=value.getsStatus();
                    params[4]=value.getsComment();
                    

                    STRUCT struct = new STRUCT(structDescriptor,
                            conn, params);
                    structs[index] = struct;
                    index++;

                }
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

                ArrayDescriptor desc = ArrayDescriptor.createDescriptor(
                        "LOVE_TAB_SYNC", calstatement.getConnection());
                ARRAY oracleArray = new ARRAY(desc, calstatement.getConnection(), structs);

                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, strUserName);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, sPoscd);
                calstatement.setArray(4, oracleArray);

                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
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
                CoreLogger.error(this.getClass().getName() + " updateData_Sync_LoveLeaf -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham updateData_Sync_LoveLeaf " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " updateData_Sync_LoveLeaf -> " + e.getMessage());
        }
        return bSuccess;
    }
    
    
  
//</editor-fold>
    
    
    public static void main(String[] args) {
        Connection conn = new DaoConnect().getConnect();
        List<DcptNoModel> lstDcpt = new DaoLoveLeaf().getDataCustSearchLoan(conn, "P2501", "1", "60000025000003", "28-feb-2015", new ArrayList<String>(), "11", "0145950", "1", "01", 1, 10);
        for (DcptNoModel value : lstDcpt) {
            System.err.println(value.getsTenkh());
        }
        int sokh = new DaoLoveLeaf().getCountTotalSearchCust(conn, "P2501", "1", "60000025000003", "28-feb-2015", new ArrayList<String>(), "11", "0145950", "1", "01");
        System.err.println("Tong so KH=" + sokh);

//        List<DcptNoModel> lstDcpt = new DaoDcptNo().getDetailCustomer("6600000702677664", "28-feb-2015", "0145953");
        for (DcptNoModel value : lstDcpt) {
            System.err.println(value.getsTenkh());
        }
    }
}
