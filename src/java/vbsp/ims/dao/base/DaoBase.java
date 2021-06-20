/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao.base;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vbsp.ims.dao.DaoConnect;

/**
 *
 * @author Le Duc Hung
 */
public abstract class DaoBase {

    protected Connection conn = null;
    protected CallableStatement pstmt = null;
    protected ResultSet rs = null;
    protected String sql = null;

    protected void openConnection() {
        conn = new DaoConnect().getConnect();
    }

    protected void closeConnection() {
        try {
            if (!conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
