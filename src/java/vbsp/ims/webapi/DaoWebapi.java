/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.webapi;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author BAOANH
 */
public class DaoWebapi {
    
    public List<ListValue> getModuleWebApi() throws SQLException {
        List<ListValue> moduleList = new ArrayList<ListValue>();
        Connection conn = null;
        conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call IMS_WEBAPI.SP_GET_MODULE_WEBAPI(?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();

            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(1);
            //COLUMN_DESC
            while (reset.next()) {
                moduleList.add(new ListValue(reset.getString("SKEY"), reset.getString("SDESC"), reset.getString("GROUP_ORDER")));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getModuleWebApi -> " + e.getMessage());
            throw new SQLException();
        } finally {
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }

        }
        return moduleList;
    }
     public List<ListValue> getPoscdWebApi(String username, String grade) throws SQLException {
        List<ListValue> poscdList = new ArrayList<ListValue>();
        Connection conn = null;
        conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call IMS_WEBAPI.sp_get_poscd(?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
            calstatement.setString(1, username);
            calstatement.setString(2, grade);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();

            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(3);
            //COLUMN_DESC
            while (reset.next()) {
                poscdList.add(new ListValue(reset.getString("SKEY"), reset.getString("SDESC"), reset.getString("GROUP_ORDER")));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getPoscdWebApi -> " + e.getMessage());
            throw new SQLException();
        } finally {
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }

        }
        return poscdList;
    }
    public HashMap<String,String> getPoscdUrl( String username) throws SQLException {
        HashMap<String,String> hmPosUrl = new HashMap<String,String>();
        Connection conn = null;
        conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call IMS_WEBAPI.f_URL_POS_CD(?,?)}";
        ResultSet reset = null;

        try {


           calstatement = conn.prepareCall(strStoreproce);
            //dang ky tham so tra du lieu ra la tham so thu nhat, kieu du lieu tra ra la number
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            //Truyen tham so thu 2 vao la mang main_pos
            calstatement.setString(2, username);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            
            String pos_cd=calstatement.getString(1);
            String url=calstatement.getString(3);
            
            hmPosUrl.put("POS_CD", pos_cd);
            hmPosUrl.put("URL", url);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getPoscdUrl -> " + e.getMessage());
            throw new SQLException();
        } finally {
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }

        }
        return hmPosUrl;
    }
    
    public static void main(String[] args)
    {
        DaoWebapi dao = new DaoWebapi();
        try {
            HashMap<String,String> hm=dao.getPoscdUrl("M2721");
            System.err.println("pos_cd="+hm.get("POS_CD")+" url="+hm.get("URL"));
        } catch (SQLException ex) {
            Logger.getLogger(DaoWebapi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
