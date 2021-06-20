/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import vbsp.ims.model.ReportPrivilege;

/**
 *
 * @author Trung
 */
public class ReportPrivilegeDao {

    private final DaoConnect daoConnect;   

    public ReportPrivilegeDao() {       
        daoConnect = new DaoConnect();
    }
   
    public ArrayList<ReportPrivilege> getPrivilege() {
        ArrayList<ReportPrivilege> privilegeList = new ArrayList<>();
        String lcNhomnd,lcNhombc,lcPhanquyen,lcNgoaile;
        String lcQuery = "SELECT PQ_NHOMND,PQ_NHOMBC,PQ_XEM||PQ_TAO||PQ_GUINHANFILE||PQ_HETHONG PRIVILEGE,PQ_NGOAILE FROM PQ_NGDUNG " +
        " WHERE APPLY_FLG = 'Y'";
        try {
            try (Connection con = daoConnect.getConnect()) {
                Statement lcStatement;
                lcStatement = con.createStatement();
                try (ResultSet rs = lcStatement.executeQuery(lcQuery)) {
                    while (rs.next()) {
                        lcNhomnd = rs.getString("PQ_NHOMND");
                        lcNhombc = rs.getString("PQ_NHOMBC");
                        lcPhanquyen = rs.getString("PRIVILEGE");
                        lcNgoaile = rs.getString("PQ_NGOAILE");
                        privilegeList.add(new ReportPrivilege(lcNhombc,lcNhomnd, lcPhanquyen,lcNgoaile));
                    }
                }
                lcStatement.close();
            }
        } catch (SQLException ex) {}
        return privilegeList;
    }   
}
