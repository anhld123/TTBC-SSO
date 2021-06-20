/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelDsHongheo;
import vbsp.ims.model.ModelMapping;

/**
 *
 * @author LION
 */
public class DaoDsHongheo {

    public HashMap<Integer, ModelMapping> getMappingColumn() {
        HashMap<Integer, ModelMapping> hmOut = new HashMap<Integer, ModelMapping>();

        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_DSHONGHEO.SP_GET_MAPPING(?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
//            calstatement.setString(1, strSaveId);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);

//            reset=calstatement.executeQuery();
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(1);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(2);
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(3);
            //COLUMN_DESC
            while (reset.next()) {
                ModelMapping value = new ModelMapping();
                value.setCol_excel(reset.getInt(2));
                value.setCol_table(reset.getString(3));
                value.setData_type(reset.getString(4));
                value.setData_lenght(reset.getBigDecimal(5).intValue());
                value.setVariable_java(reset.getString(7));
                if (!hmOut.containsKey(value.getCol_excel())) {
                    hmOut.put(value.getCol_excel(), value);
                }
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
            CoreLogger.error(this.getClass().getCanonicalName() + " Exception-> getMappingColumn " + e.getMessage());
            System.err.println(" Exception-> getMappingColumn " + e.getMessage());
        }
        return hmOut;
    }

    public boolean saveDsHongheo(List<Object> lstDsNgheo, String sUserName) throws Exception {
        boolean bSuccess = true;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        PreparedStatement insert = null;
        try {

            String sInsert = "INSERT INTO DS_HONGHEO (DS_MATINH,DS_MAHUYEN,DS_MAXA,DS_MATHON,\n"
                    + "DS_TENKH,DS_GIOITINH,DS_NGAYSINH,DS_SOCMT,DS_NGAYCAP,DS_NOICAP,\n"
                    + "DS_DANTOC,DS_LOAI_KH,DS_NGAYLOAI, DS_MAKH,DS_NGAYNHAP,DS_NGUOINHAP) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?, SYSDATE,?)";
            conn.setAutoCommit(false);
            insert = conn.prepareStatement(sInsert);
            String sQryMaxKh = "{?=call VBSP_IMS_DSHONGHEO.F_GET_MAX_MAKH(?,?,?)}";
            CallableStatement statementMax = conn.prepareCall(sQryMaxKh);
            String sUpdate = "UPDATE DS_HONGHEO SET DS_MAKH=? where DS_MAKH=?";
            CallableStatement statementUpdate = conn.prepareCall(sUpdate);
            for (int i = 0; i < lstDsNgheo.size(); i++) {
                ModelDsHongheo value = (ModelDsHongheo) lstDsNgheo.get(i);
                insert.setString(1, value.getMatinh());
                insert.setString(2, value.getMahuyen());
                insert.setString(3, value.getMaxa());
                insert.setString(4, value.getMathon());
                insert.setString(5, value.getTenkh());
                insert.setString(6, value.getGioitinh());
                insert.setDate(7, new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(value.getNgaysinh()).getTime()));
                insert.setString(8, value.getSocmt());
                insert.setDate(9, new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(value.getNgaycap()).getTime()));
                insert.setString(10, value.getNoicap());
                insert.setString(11, value.getDantoc());
                insert.setString(12, value.getLoai_Kh());
                insert.setDate(13, new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(value.getNgayloai()).getTime()));
                insert.setString(14, Integer.toString(i));
                insert.setString(15, sUserName);
                insert.execute();
                //lay ra so lon nhat cua ma khach hang
                //Lay ra ma khach hang
                statementMax.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
                
                statementMax.setString(2, value.getMatinh());
                statementMax.setString(3, value.getMahuyen());
                statementMax.setString(4, value.getMaxa());
                statementMax.execute();
                String sMakh = statementMax.getString(1);

                //Update ma khach hang
                statementUpdate.setString(1, sMakh);

                statementUpdate.setString(2, Integer.toString(i));
                statementUpdate.execute();

//                System.err.println("i=" + i+" hashCode="+value.getTenkh().hashCode() );
            }
            conn.commit();
            conn.setAutoCommit(true);
            bSuccess = true;
            if (statementMax != null) {
                statementMax.close();
            }
            if (statementUpdate != null) {
                statementUpdate.close();
            }
        } catch (Exception e) {
            if (conn != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    conn.rollback();
                } catch (SQLException excep) {
                    CoreLogger.error(this.getClass().getCanonicalName() + " Exception-> saveDsHongheo " + e.getMessage());
                    System.err.println(" Exception-> saveDsHongheo " + e.getMessage());
                }
            }
            CoreLogger.error(this.getClass().getCanonicalName() + " Exception-> saveDsHongheo " + e.getMessage());
            System.err.println(" Exception-> saveDsHongheo " + e.getMessage());
            bSuccess = false;
            throw new Exception("Loi khi luu du lieu", e);
        } finally {

            try {
                if (insert != null) {
                    insert.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoDsHongheo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bSuccess;
    }
 public boolean saveDsHongheo1(List<Object> lstDsNgheo, String sUserName) throws Exception {
        boolean bSuccess = true;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        PreparedStatement insert = null;
        try {

            String sInsert = "INSERT INTO DS_HONGHEO (DS_MATINH,DS_MAHUYEN,DS_MAXA,DS_MATHON,\n"
                    + "DS_TENKH,DS_GIOITINH,DS_NGAYSINH,DS_SOCMT,DS_NGAYCAP,DS_NOICAP,\n"
                    + "DS_DANTOC,DS_LOAI_KH,DS_NGAYLOAI, DS_MAKH,DS_NGAYNHAP,DS_NGUOINHAP) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?, SYSDATE,?)";
            conn.setAutoCommit(false);
            insert = conn.prepareStatement(sInsert);
            String sQryMaxKh = "{call VBSP_IMS_DSHONGHEO.SP_UPDATE_MAKH(?,?,?,?)}";
            CallableStatement statementMax = conn.prepareCall(sQryMaxKh);
//            String sUpdate = "UPDATE DS_HONGHEO SET DS_MAKH=? where DS_MAKH=?";
//            CallableStatement statementUpdate = conn.prepareCall(sUpdate);
            for (int i = 0; i < lstDsNgheo.size(); i++) {
                ModelDsHongheo value = (ModelDsHongheo) lstDsNgheo.get(i);
                insert.setString(1, value.getMatinh());
                insert.setString(2, value.getMahuyen());
                insert.setString(3, value.getMaxa());
                insert.setString(4, value.getMathon());
                insert.setString(5, value.getTenkh());
                insert.setString(6, value.getGioitinh());
                insert.setDate(7, new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(value.getNgaysinh()).getTime()));
                insert.setString(8, value.getSocmt());
                insert.setDate(9, new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(value.getNgaycap()).getTime()));
                insert.setString(10, value.getNoicap());
                insert.setString(11, value.getDantoc());
                insert.setString(12, value.getLoai_Kh());
                insert.setDate(13, new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(value.getNgayloai()).getTime()));
                insert.setString(14, Integer.toString(i));
                insert.setString(15, sUserName);
                insert.execute();
                //lay ra so lon nhat cua ma khach hang
                //Lay ra ma khach hang
//                statementMax.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
                
                statementMax.setString(1, value.getMatinh());
                statementMax.setString(2, value.getMahuyen());
                statementMax.setString(3, value.getMaxa());
                statementMax.setString(4, Integer.toString(i));
                statementMax.execute();
//                String sMakh = statementMax.getString(1);

                //Update ma khach hang
//                statementUpdate.setString(1, sMakh);
//
//                statementUpdate.setString(2, Integer.toString(i));
//                statementUpdate.execute();

                System.err.println("i=" + i+" hashCode="+value.getTenkh().hashCode() );
            }
            conn.commit();
            conn.setAutoCommit(true);
            bSuccess = true;
            if (statementMax != null) {
                statementMax.close();
            }
//            if (statementUpdate != null) {
//                statementUpdate.close();
//            }
        } catch (Exception e) {
            if (conn != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    conn.rollback();
                } catch (SQLException excep) {
                    CoreLogger.error(this.getClass().getCanonicalName() + " Exception-> saveDsHongheo " + e.getMessage());
                    System.err.println(" Exception-> saveDsHongheo " + e.getMessage());
                }
            }
            CoreLogger.error(this.getClass().getCanonicalName() + " Exception-> saveDsHongheo " + e.getMessage());
            System.err.println(" Exception-> saveDsHongheo " + e.getMessage());
            bSuccess = false;
            throw new Exception("Loi khi luu du lieu", e);
        } finally {

            try {
                if (insert != null) {
                    insert.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoDsHongheo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bSuccess;
    }
    public HashMap<Integer, ModelMapping> getMappingColumn1() {
        HashMap<Integer, ModelMapping> hmOut = new HashMap<Integer, ModelMapping>();

        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;

        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_DSHONGHEO.SP_GET_MAPPING(?,?,?)}";
        ResultSet reset = null;
        int err_cd = 0;
        String err_txt = null;
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce);
//            calstatement=conn.prepareCall(strStoreproce,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
            calstatement.setInt(1, err_cd);
            calstatement.setString(2, err_txt);
//            calstatement.registerOutParameter(strStoreproce, err_cd, err_txt);
//            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
//            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
//            calstatement.executeQuery();
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//            int pn_err_cd = calstatement.getInt(1);
//            //thu hien lay mo ta loi
//            String strEdd_txt = calstatement.getString(2);
            //Lay cursor ra resultset
//Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(3);
            //COLUMN_DESC
            while (reset.next()) {
                ModelMapping value = new ModelMapping();
                value.setCol_excel(reset.getInt(2));
                value.setCol_table(reset.getString(3));
                value.setData_type(reset.getString(4));
                value.setData_lenght(reset.getBigDecimal(5).intValue());
                value.setVariable_java(reset.getString(7));
                if (!hmOut.containsKey(value.getCol_excel())) {
                    hmOut.put(value.getCol_excel(), value);
                }
                System.err.println("reset.getString(3)=" + reset.getString(3));
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
            CoreLogger.error(this.getClass().getCanonicalName() + " Exception-> getMappingColumn " + e.getMessage());
            System.err.println(" Exception-> getMappingColumn " + e.getMessage());
        }
        return hmOut;
    }

    public static void main(String[] args) throws Exception {

        new DaoDsHongheo().getMappingColumn1();

        Object[] obj = null;//ModelMapping.class.getDeclaredFields();

        Class<?> clazz = Class.forName("vbsp.ims.model.ModelMapping");

        Field field = clazz.getDeclaredField("col_excel");
        clazz.getConstructor();
        Constructor ct = clazz.getConstructor();
        Object objCls = ct.newInstance();
        field.setAccessible(true);
        System.err.println(field);
        Class clazzType = field.getType();
        System.err.println("Type=" + clazzType.getName());

        field.setInt(objCls, 123);

        System.err.println("col_excel=" + field.getInt(objCls));
//         
//        for (int i = 0; i < obj.length; i++) {
//            System.err.println("Test thu " + obj[i].toString());
//            try {
//                ModelMapping.class.getDeclaredFields()[i].getInt(123);
//            } catch (IllegalArgumentException ex) {
//                Logger.getLogger(ModelMapping.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IllegalAccessException ex) {
//                Logger.getLogger(ModelMapping.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }
}
