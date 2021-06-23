/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.khnv2021.dao;

import vbsp.ims.dao.khnv.*;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.export.excel.ExportExcelFile;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.khnv.POSModel;
import vbsp.ims.model.khnv.XdkhModel;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author CuongBM0211
 */
public class XDKHDao2021 {
    
    public List<ListValue> getLOV(String username, String type) {
        List<ListValue> lstDMNgNhan = new ArrayList<ListValue>();
        try {
            
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_KHNV2021.SP_GET_LOV(?, ?, ?, ?, ?)}";
            ResultSet reset = null;
            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, username);
                calstatement.setString(2, type);
               
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lấy mã hiệu giá trị lỗi (Nếu có) của thủ tục (procedure)
                int iErr_CD = calstatement.getInt(3);
                //Lấy nội dung thông báo lỗi (Nếu có) của thủ tục (procedure)
                String sEdd_TXT = calstatement.getString(4);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(5);
                while (reset.next()) {
                    String key = reset.getString(1);
                    String des = reset.getString(2);
                    lstDMNgNhan.add(new ListValue(key, des));
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
                CoreLogger.error(this.getClass().getName() + " getNgNhan_KCKNTN -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Doi chieu, Phan loai no -> Loi trong ham getNgNhan_KCKNTN " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getNgNhan_KCKNTN -> " + e.getMessage());
        }
        return lstDMNgNhan;
    }
    
    public ArrayList<XdkhModel> get_data_xdkh(String posCD, String capbc,String xa_pgd, int namBc) {
        ArrayList<XdkhModel> dataList = new ArrayList<XdkhModel>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_KHNV2021.p_get_data_xdkh(?, ?, ?, ?, ?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat module_id 
                calstatement.setString(1, posCD);
                calstatement.setString(2, capbc);
                calstatement.setString(3, xa_pgd);
                calstatement.setInt(4, namBc);
        
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
                
                //Thuc hien execute lay du lieu
                calstatement.execute();
                
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(7);
                
                while (reset.next()) {
                    XdkhModel obj = new XdkhModel();
                    
                    obj.setKH_MA_CT(reset.getString("KH_MA_CT"));
                    obj.setKH_STT_HT(reset.getString("KH_STT_HT"));
                    obj.setKH_CHI_TIEU(reset.getString("KH_CHI_TIEU"));
                    obj.setKH_UOC_TH(reset.getDouble("KH_UOC_TH"));
                    obj.setKH_KH_NAM(reset.getDouble("KH_KH_NAM"));
                    obj.setKH_DN(reset.getString("KH_DN"));
                    obj.setKH_FONTWEIGHT(reset.getString("KH_FONTWEIGHT"));
                    obj.setKH_CAPHT(reset.getDouble("KH_CAPHT"));
                    obj.setKH_STT(reset.getDouble("KH_STT"));
                    obj.setKH_CONGTHUC(reset.getString("KH_CONGTHUC"));
                    obj.setKH_CHITIEU_CHAR(reset.getString("KH_CHITIEU_CHA"));
                    //Them vao list
                    dataList.add(obj);
                }
                
//                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//                int pn_err_cd = calstatement.getInt(4);
//                //thu hien lay mo ta loi
//                String strEdd_txt = calstatement.getString(5);
//                //Lay cursor ra resultset

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " get_data_xdkh -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_data_xdkh " + e.getMessage());
            CoreLogger.error(this.getClass().getName()+ " get_data_xdkh -> " + e.getMessage());
        }
        return dataList;
    }
    
    public ArrayList<POSModel> getPosList(String posCD, String maCn, String reportGrade){
        ArrayList<POSModel> posList = new ArrayList<POSModel>();
        
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_KHNV2021.p_get_pos_list(?, ?, ?, ?, ?, ?)}";
            ResultSet rsPosList = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                //Truyen vao username
                calstatement.setString(1, posCD);          
                calstatement.setString(2, maCn);          
                calstatement.setString(3, reportGrade);          
                
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);

                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                rsPosList = (ResultSet) calstatement.getObject(6);

                while (rsPosList.next()) {
                    POSModel p = new POSModel();
                    p.setId(rsPosList.getString("PO_MA"));
                    p.setDesc(rsPosList.getString("PO_MA") + " - " + rsPosList.getString("PO_TEN"));

                    posList.add(p);
                }

                if (rsPosList != null) {
                    rsPosList.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham getPosList " + e.getMessage());
                CoreLogger.error(POSModel.class.getCanonicalName() + " getPosList  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getPosList " + e.getMessage());
            CoreLogger.error(DaoDieuchinhkh.class.getCanonicalName() + " getPosList  -> " + e.getMessage());
        }
        return posList;
    }
    
    public ArrayList<POSModel> getSubCommuneList(String posCD, String commuuneId, String reportGrade){
        ArrayList<POSModel> posList = new ArrayList<POSModel>();
        
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_KHNV2021.P_GET_SUBCOMMUNE_LIST(?, ?, ?, ?, ?, ?)}";
            ResultSet rsPosList = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                //Truyen vao username
                calstatement.setString(1, posCD);          
                calstatement.setString(2, commuuneId);          
                calstatement.setString(3, reportGrade);          
                
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);

                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                rsPosList = (ResultSet) calstatement.getObject(6);

                while (rsPosList.next()) {
                    POSModel p = new POSModel();
                    p.setId(rsPosList.getString("PO_MA"));
                    p.setDesc(rsPosList.getString("PO_MA") + " - " + rsPosList.getString("PO_TEN"));

                    posList.add(p);
                }

                if (rsPosList != null) {
                    rsPosList.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham getPosList " + e.getMessage());
                CoreLogger.error(POSModel.class.getCanonicalName() + " getPosList  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getPosList " + e.getMessage());
            CoreLogger.error(DaoDieuchinhkh.class.getCanonicalName() + " getPosList  -> " + e.getMessage());
        }
        return posList;
    }
    
    public ArrayList<POSModel> getCommuneList(String posCode){
        ArrayList<POSModel> posList = new ArrayList<>();
        
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_KHNV2021.SP_GET_COMMUNEBYUSER(?, ?)}";
            ResultSet rsPosList = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                //Truyen vao username
                calstatement.setString(1, posCode);                         
                
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);

                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                rsPosList = (ResultSet) calstatement.getObject(2);

                while (rsPosList.next()) {
                    POSModel p = new POSModel();
                    p.setId(rsPosList.getString("MA"));
                    p.setDesc(rsPosList.getString("TEN"));

                    posList.add(p);
                }

                if (rsPosList != null) {
                    rsPosList.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham getCommuneList " + e.getMessage());
                CoreLogger.error(POSModel.class.getCanonicalName() + " getPosList  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getCommuneList " + e.getMessage());
            CoreLogger.error(DaoDieuchinhkh.class.getCanonicalName() + " getPosList  -> " + e.getMessage());
        }
        return posList;
    }
    
//   public String getQueryExpXls(String mabc, String nambc, String dotbc,String maDonvi, String capbc) throws SQLException {
//        String pos_cd = "";
//        CallableStatement calstatement = null;
//        ResultSet reset = null;
//        DaoConnect daoconnect = new DaoConnect();
//        Connection conn = null;
//        conn = daoconnect.getConnect();
////        try {
//        //Khoi tao procedure cung voi tham so truyen vao la dau ?
//        String strStoreproce = "{?=call VBSP_IMS_KHNV2021.F_GET_QUERY_EXP_XLS(?,?,?,?,?)}";
//        try {
//            //Khoi tao goi store
//            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
//            calstatement.setString(2, mabc);
//            calstatement.setString(3, nambc);
//            calstatement.setString(4, dotbc);
//            calstatement.setString(5, maDonvi);
//            calstatement.setString(6, capbc);
//            //Thuc hien execute lay du lieu
//            calstatement.execute();
//            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//            pos_cd = calstatement.getString(1);
//
//        } catch (SQLException e) {
//            System.err.print(e.getMessage());
//            CoreLogger.error(this.getClass().getName() + " getQueryExpXls -> " + e.getMessage());
//            throw new SQLException(e);
//        } finally {
//            if (reset != null) {
//                reset.close();
//            }
//            if (calstatement != null) {
//                calstatement.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return pos_cd;
//    }
//   
   public boolean getDataExp(String sSave_id, Map<String, String> mapinPara,
            String sPos_cd, String stringPara_Poscd, String sPos_Flag, String strFileName, String namBc, String dotBc) {
        List<HashMap<Integer, List<Object>>> lstDataExp = new ArrayList<HashMap<Integer, List<Object>>>();
        if (sSave_id == null || sSave_id.length() < 1) {
            return false;
        }
        DaoConnect daoConnect = new DaoConnect();
        Connection connect = null;
        try {
            connect = daoConnect.getConnect();
            //connect=daoConnect.getConnect("10.63.8.59", "vbsprepo", 1521, "intellect", "intellect321#");
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham exportExcelQuery");
                CoreLogger.error(this.getClass().getName() + " Khong the ket noi voi csdl ham exportExcelQuery ");
                return false;
            }

            //Lay ra title cho bao cao
            String strTitle = "NHU CẦU VAY VỐN TÍN DỤNG CHÍNH SÁCH NĂM " + namBc ;//getTitleQuery(sSave_id, connect);
            //Lay ra truy van cho bao cao
            String strQuery = getQueryKHNV01("","","","","", connect);

            //Lay ra danh sach pos
            List<ListValue> lstPostCd = getPosGeneralReport(connect, "000314", sPos_Flag);

            Integer nValue = 0;
            Integer nCountData = 0;
            //Hashmap luu tru du lieu
            HashMap<Integer, List<Object>> hmData = new HashMap<Integer, List<Object>>();
            //Hashmap luu tru lieu du lieu
            HashMap<Integer, List<Object>> hmMetaTable = new HashMap<Integer, List<Object>>();

            //Hashmap luu tru ten cot du lieu
            HashMap<Integer, List<Object>> hmNameCol = new HashMap<Integer, List<Object>>();

            for (ListValue valuePos : lstPostCd) {
                Map<String, String> mapParaInTmp = new HashMap<String, String>();
                //Dua tham so truyen vao
                mapParaInTmp.putAll(mapinPara);
                mapParaInTmp.put(stringPara_Poscd, valuePos.getsKey());
                String sQueryTmp = strQuery;
                //replare tham so cho gia tri trong truy van
                for (String key : mapParaInTmp.keySet()) {
                    String value = mapParaInTmp.get(key).trim();
                    sQueryTmp = sQueryTmp.replaceAll(key, value);
                    //System.err.println("Value "+value+" Key "+key);
                }

                Statement stm = null;
                stm = connect.createStatement();
                if (stm == null) {
                    System.err.println("Khong tao duoc createStatement ham exportExcelQuery");
                    CoreLogger.error(this.getClass().getName() + " Khong tao duoc createStatement ham exportExcelQuery ");
                    return false;
                }

                ResultSet reset = null;
                reset = stm.executeQuery(sQueryTmp);
                //System.err.println(strQuery);
                if (stm == null) {
                    System.err.println("Khong tao duoc executeQuery ham exportExcelQuery");
                    CoreLogger.error(this.getClass().getName() + " Khong tao duoc executeQuery ham exportExcelQuery ");
                    return false;
                }
                ResultSetMetaData resetMetaData = reset.getMetaData();
                int nCountCol = resetMetaData.getColumnCount();
                if (nValue == 0) {

                    List<Object> lsMetaData = new ArrayList<Object>();
                    List<Object> lstColName = new ArrayList<Object>();
                    for (int i = 1; i <= nCountCol; i++) {
                        lsMetaData.add(resetMetaData.getColumnTypeName(i));
                        lstColName.add(resetMetaData.getColumnName(i));
                    }

                    hmMetaTable.put(9999, lsMetaData);
                    hmNameCol.put(9999, lstColName);
                }

                while (reset.next()) {
                    List<Object> lstData = new ArrayList<Object>();
                    for (int i = 1; i <= nCountCol; i++) {
                        Object sValue = reset.getString(i);
                        if (sValue == null) {
                            lstData.add("");
                        } else {
                            lstData.add(reset.getString(i));
                        }
                    }
                    hmData.put(nCountData, lstData);
                    nCountData++;
                }

                if (reset != null) {
                    reset.close();
                }
                if (stm != null) {
                    stm.close();
                }
                nValue++;
            }
            lstDataExp.add(hmNameCol);
            lstDataExp.add(hmMetaTable);
            lstDataExp.add(hmData);
            //Lay ra column va desc cho column du lieu trong bao cao

            ExportExcelFile exportExcel = new ExportExcelFile();
            exportExcel.ExportFileExcelQueryFromList(lstDataExp, strTitle, strFileName);
            if (connect != null) {
                connect.close();
            }
            lstDataExp.clear();
            System.gc();
        } catch (SQLException e) {
            System.err.println("Loi trong ham getDataExp " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataExp -> " + e.getMessage());
            System.gc();
            return false;
        }
        return true;
    }
   
   public String getQueryKHNV01(String mabc, String nambc, String dotbc,String maDonvi, String capbc, Connection connect) {
        String strQuery = "";

        // Connection connect = null;
        CallableStatement calstatement = null;
        //Khoi tao function se tra ra du lieu la kieu gi
        String strStoreproce = "{?=call VBSP_IMS_KHNV2021.F_GET_QUERY_EXP_XLS(?,?,?,?,?)}";

        try {
            //Khoi tao ket noi
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham getQuery");
                return strQuery;
            }
            //THuc hien goi ham trong oracle
            calstatement = connect.prepareCall(strStoreproce);
            //dang ky tham so tra du lieu ra la tham so thu nhat, kieu du lieu tra ra la number
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CLOB);
            //Truyen tham so thu 2 vao la mang main_pos
            calstatement.setString(2, mabc);
            calstatement.setString(3, nambc);
            calstatement.setString(4, dotbc);
            calstatement.setString(5, maDonvi);
            calstatement.setString(6, capbc);
//            calstatement.setString(3, strModule_id);
//            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
//            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//            int pn_err_cd = calstatement.getInt(3);
////            //thu hien lay mo ta loi
//            String strEdd_txt = calstatement.getString(4);

            Clob clob = calstatement.getClob(1);
            //Lay cursor ra resultset
            //Get du lieu tra ra tham so thu 1
            if (clob != null) {
                strQuery = clob.getSubString(1, (int) clob.length());
                clob.free();

            }
//            System.err.println(calstatement.getString(1));
            if (calstatement != null) {
                calstatement.close();
            }

//            if (connect != null) {
//                connect.close();
//            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getQuery -> " + e.getMessage());
        }
        return strQuery;

    }
   
   public List<ListValue> getPosGeneralReport(Connection conn, String sPos_cd, String sPos_flag) {
        List<ListValue> lstPoscd = new ArrayList<ListValue>();

        try {
            // DaoConnect daoconnect = new DaoConnect();
            //Connection conn = null;
//            conn = daoconnect.getConnect();
            //conn=daoconnect.getConnect("10.63.8.59", "vbsprepo", 1521, "intellect", "intellect321#");
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_query.sp_get_pos_general_report(?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sPos_cd);
                calstatement.setString(2, sPos_flag);
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
                    lstPoscd.add(new ListValue(reset.getString("POS_CD"), reset.getString("POS_DESC")));
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
                CoreLogger.error(this.getClass().getName() + " getLoadAllQuery -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getLoadAllQuery " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadAllQuery -> " + e.getMessage());
        }
        return lstPoscd;
    }
   
   public List<String> getAllCommune(String posUser) throws SQLException {
        List<String> lstData = new ArrayList<>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_KHNV2021.SP_GET_COMMUNEBYUSER(?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, posUser);
            
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//            int pn_err_cd = calstatement.getInt(2);
//            //thu hien lay mo ta loi
//            String strEdd_txt = calstatement.getString(3);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(2);
            while (reset.next()) {

                lstData.add(reset.getString(1));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getAllCommune -> " + e.getMessage());
            throw new SQLException(e);
        } finally {
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return lstData;
    }
   
   public List<String> getAllSubCommune(String posCD, String communeId){
        List<String> lstData = new ArrayList<>();
        
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_KHNV2021.P_GET_SUBCOMMUNE_LIST(?, ?, ?, ?, ?, ?)}";
            ResultSet rsPosList = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                //Truyen vao username
                calstatement.setString(1, posCD);          
                calstatement.setString(2, communeId);          
                calstatement.setString(3, "1");          
                
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);

                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                rsPosList = (ResultSet) calstatement.getObject(6);

                while (rsPosList.next()) {
                    //POSModel p = new POSModel();
                    //p.setId(rsPosList.getString("PO_MA"));
                    //p.setDesc(rsPosList.getString("PO_MA") + " - " + rsPosList.getString("PO_TEN"));
                    String subCommuneId = rsPosList.getString("PO_MA");
                    lstData.add(subCommuneId);
                }

//                if (rsPosList != null) {
//                    rsPosList.close();
//                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham getPosList " + e.getMessage());
                CoreLogger.error(DaoDieuchinhkh.class.getCanonicalName() + " getPosList  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getPosList " + e.getMessage());
            CoreLogger.error(DaoDieuchinhkh.class.getCanonicalName() + " getPosList  -> " + e.getMessage());
        }
        return lstData;
    }
   
   public List<QT_DULIEU_NT> getDataAuthCommune( String sMaBC,  String sUser,
            String sGrade, String namBC, String dotBc, String maxa, String mathon) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_KHNV2021.SP_VIEW_DATA_BY_CODE(?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, sMaBC);
                calstatement.setString(2, sUser);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, namBC);
                calstatement.setString(5, dotBc);
                calstatement.setString(6, maxa);
                calstatement.setString(7, mathon);
                        
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(8);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(9);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(10);
                while (reset.next()) {

                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setKHOA(reset.getString(1));
                    value.setTHUTU(reset.getInt(2));
                    value.setTT_HIENTHI(reset.getString(3));
                    value.setMA(reset.getString(4));
                    value.setTEN(reset.getString(5));
                    value.setNGAYBC(reset.getDate(6));
                    value.setNAMBC(reset.getInt(7));
                    value.setMAPGD(reset.getString(8));
                    value.setMACN(reset.getString(10));
//                    value.setD1(reset.getString(14));
                    value.setD1(reset.getString(15));
                    value.setD2(reset.getString(16));
                    value.setD3(reset.getString(17));
                    value.setD4(reset.getString(18));
                    value.setD5(reset.getString(19));
                    value.setD6(reset.getString(20));
                    value.setD7(reset.getString(21));
                    value.setD8(reset.getString(22));
                    value.setD9(reset.getString(23));
                    value.setD10(reset.getString(24));
                    value.setD11(reset.getString(25));
                    value.setD12(reset.getString(26));
                    value.setD13(reset.getString(27));
                    value.setD14(reset.getString(28));
                    value.setD15(reset.getString(29));
                    value.setD16(reset.getString(30));
                    value.setD17(reset.getString(31));
                    value.setD18(reset.getString(32));
                    value.setD19(reset.getString(33));
                    value.setNHAPTAY(reset.getString(45));
                    value.setFONTFORMAT(reset.getString(46));

                    lstBcqt_NT.add(value);
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
                CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataTDNN_01 " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataTDNN_01 -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }
   
   public boolean setLockUnlockCommune(String maxa, String nambc, String dotbc, String tt_khoa, String username, String grade) throws SQLException {
        Connection connection = new DaoConnect().getConnect();

        CallableStatement cs = null;
        boolean bSuccess = false;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_KHNV2021.SP_LOCK_UNLOCK_COMMUNE(?, ?, ?, ?, ?, ?)}");
            cs.setString(1, maxa);
            cs.setString(2, nambc);
            cs.setString(3, dotbc);
            cs.setString(4, tt_khoa);
            cs.setString(5, username);            
            cs.setString(6, grade);
            cs.execute();
            bSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham setLockUnlockCommune " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " setLockUnlockCommune -> " + e.getMessage());
            bSuccess = false;
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return bSuccess;
    }
}
