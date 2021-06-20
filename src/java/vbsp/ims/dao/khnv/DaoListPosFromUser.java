/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao.khnv;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author BAOANH
 */
public class DaoListPosFromUser {
    public PosMainModel get_pos_main_pos(String userId, String capbc) {
        PosMainModel posMainModel = new PosMainModel();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KHNV.p_get_pos_cd(?, ?, ?,?,?)}";
            ResultSet reset = null;
            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat module_id 
                calstatement.setString(1, userId);
                calstatement.setString(2, capbc);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);

                //Thuc hien execute lay du lieu
                calstatement.execute();

                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                String posCd = calstatement.getString(3);
                String mainPosCd = calstatement.getString(4);
                
                posMainModel.setPosCd(posCd);
                posMainModel.setMainPosCd(mainPosCd);
                reset=(ResultSet)calstatement.getObject(5);
                List<ListValue> lstdata = new ArrayList<>();
                while(reset.next())
                {
                    lstdata.add(new ListValue(reset.getString(2), reset.getString(3), reset.getString(1)));
                }
                posMainModel.setLstXa(lstdata);
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " get_pos_main_pos -> " + e.getMessage());
            }

            return posMainModel;
        } catch (Exception e) {
            System.err.println("Loi trong ham get_pos_main_pos " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " get_pos_main_pos -> " + e.getMessage());
        }
        return posMainModel;
    }
}
