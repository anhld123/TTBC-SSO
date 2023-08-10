/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao.ktnb;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author Trung Nguyen
 */
public class DaoKTKSNBAuth {

    public boolean saveAuth(String reportKey, String reportDate, String posCode, String posFlag, String quyBc, String namBc, String userName, String dataFlag) {

        try {
            //Khai bao các biến để nhận dữ liệu đẩy về
            DaoConnect daoConnect = new DaoConnect();
            Connection con = daoConnect.getConnect();
            Statement stm = con.createStatement();

            String updateQry = "update KTKSNB_DATA set D50 = '" + dataFlag + "' where khoa = '" + reportKey + "' and ngaybc = '" + reportDate + "' and mapgd = '" + posCode + "' and co_tonghop = '" + posFlag + "'";

            stm.executeUpdate(updateQry);

            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoKTKSNBAuth.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Loi trong ham saveAuth " + posCode + " " + ex.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveAuth ---------> " + posCode + " " + ex.getMessage());
            return false;
        }
        return true;
    }
}
