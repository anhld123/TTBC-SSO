/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;
import vbsp.ims.ctieu.DonViCT;
import vbsp.ims.dao.DaoConnect;

/**
 *
 * @author ITCVBSP56
 */
public class Utilities {
    String ngaybc="";
    public String fnc_getDateBC(String namBC, String dotBC) throws SQLException {
        Connection con = new DaoConnect().getConnect();
        CallableStatement st = con.prepareCall("SELECT VBSP_IMS_KHNV2021.F_GET_NGAYBC(?,?) NGAYBC FROM DUAL");
            st.setString(1, namBC);
            st.setString(2, dotBC);            
            ResultSet rs = st.executeQuery();
        while(rs.next()){
            ngaybc = rs.getString("NGAYBC");
        }
        return ngaybc;
    }
}
