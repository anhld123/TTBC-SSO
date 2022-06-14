/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.nghiquyet11cp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.action.ktktnb.DULIEU_NT_TQ;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author NGUYEN PHU VINH
 */
public class NQ11_DCKHService {
    
    private String txtError;
    private Number txtCode;
    
    public List<DULIEU_NT_TQ> getDCHTLS(String sUser, String sGrade, String sNamBC){
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        List<DULIEU_NT_TQ> lstData = new ArrayList<>();
        try {
            CallableStatement calstatement = null;
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_LOAD_DCKH_CNTW(?,?,?,?,?,?)}";
            ResultSet Rset = null;
            try {
                calstatement = con.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, sUser);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, sNamBC);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                txtCode = (Number) calstatement.getObject(4);
                txtError = (String) calstatement.getObject(5);
                Rset = (ResultSet) calstatement.getObject(6);
                while (Rset.next()) {
                    DULIEU_NT_TQ value = DULIEU_NT_TQ.newInstance();
                    value.setMA(Rset.getString("MA"));
                    value.setTEN(Rset.getString("TEN"));
                    value.setTT_HIENTHI(Rset.getString("TT_HIENTHI"));
                    value.setMAPGD(Rset.getString("MAPGD"));
                    value.setMACN(Rset.getString("MACN"));
                    value.setNAMBC(Rset.getInt("NAMBC"));
                    value.setD1(Rset.getString("D1"));
                    value.setD2(Rset.getString("D2"));
                    value.setD3(Rset.getString("D3"));
                    value.setD4(Rset.getString("D4"));
                    value.setD5(Rset.getString("D5"));
                    value.setD6(Rset.getString("D6"));
                    value.setD7(Rset.getString("D7"));
                    value.setCO_TONGHOP(Rset.getString("CO_TONGHOP"));
                    lstData.add(value);
                }

                if (Rset != null) {
                    Rset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }

            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + "getDCHTLS -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDCHTLS " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDCHTLS -> " + e.getMessage());
        }
        return lstData;
    }
    
    public String saveDCHTLS(String sUser, String sGrade, String sNamBC, List<DULIEU_NT_TQ> ModelList) {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        String code = "";
        try {
            //------Chuyển dạng mảng thành Object của Oracle
            Object array[] = ModelList.toArray();
            ArrayDescriptor des = ArrayDescriptor.createDescriptor(DULIEU_NT_TQ.ORACLE_TABLE_TYPE, con);
            ARRAY array_to_pass = new ARRAY(des, con, array);
            //---------------------------------------------------------------------------
            CallableStatement calstatement = null;
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_DCKH_CNTW(?,?,?,?,?)}";
            try {
                calstatement = con.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, sUser);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, sNamBC);
                calstatement.setArray(4, array_to_pass);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.execute();
                code = (String) calstatement.getString(5);
                if (calstatement != null) {
                    calstatement.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + "saveDCHTLS -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham saveDCHTLS " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveDCHTLS -> " + e.getMessage());
        }
        return code;
    }
}
