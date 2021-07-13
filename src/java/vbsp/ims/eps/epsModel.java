/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.eps;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.khnv2021.PosClass;

/**
 *
 * @author ITCVBSP56
 */
public class epsModel {

    public epsModel() {
    }

    public List<PosClass> getPGD(String capbc, String tendn) {
        List<PosClass> lst = new ArrayList<>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        try {
            //Thực hiện lấy các biến cần truy cập
            CallableStatement st = con.prepareCall("{call PROC_GET_DONVI(?,?,?)}");
            st.setString(1, capbc);
            st.setString(2, tendn);
            st.registerOutParameter(3, OracleTypes.CURSOR);
            st.execute();
            ResultSet rs = (ResultSet) st.getObject(3);
            while (rs.next()) {
                PosClass obj = new PosClass();
                lst.add(new PosClass(rs.getString("MAPGD"), rs.getString("TENDV")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(epsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    public List<Object> getAllData(String capbc, String tendn, String madv, String ngaybc, String nghiepvu) {
        List<epsGetSet_NT> lst = new ArrayList<>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        int dachot=0, chuachot=0,chotsai=0;
        try {
            //Thực hiện lấy các biến cần truy cập
            CallableStatement st = con.prepareCall("{call PROC_GETPH_DONVI(?,?,?,?,?,?,?,?,?)}");
            st.setString(1, capbc);
            st.setString(2, tendn);
            st.setString(3, madv);
            st.setString(4, ngaybc);
            st.setString(5, nghiepvu);
            st.registerOutParameter(6, OracleTypes.NUMBER);
            st.registerOutParameter(7, OracleTypes.NUMBER);
            st.registerOutParameter(8, OracleTypes.NUMBER);
            st.registerOutParameter(9, OracleTypes.CURSOR);
            st.execute();
            dachot = st.getInt(6);
            chuachot = st.getInt(7);
            chotsai = st.getInt(8);
            ResultSet rs = (ResultSet) st.getObject(9);
            while (rs.next()) {
                epsGetSet_NT obj = new epsGetSet_NT();
                lst.add(
                        new epsGetSet_NT(
                                rs.getString("KHOA"),
                                rs.getString("MACN"),
                                rs.getString("MAPGD"),
                                rs.getString("NGAYBC"),
                                rs.getString("NGUOI_NHAP"),
                                rs.getString("NGAY_NHAP"),
                                rs.getString("D1"),
                                rs.getString("D2"),
                                rs.getString("D3")
                        ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(epsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Arrays.asList(lst,dachot,chuachot,chotsai);
    }
}
