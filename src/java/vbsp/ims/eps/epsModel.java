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
import java.text.ParseException;
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
        List<epsGetSetSL> lst = new ArrayList<>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        int dachot = 0, chuachot = 0, chotsai = 0;
        try {
            //Lưu dữ liệu vào CSDL và trả về kết quả
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
                lst.add(new epsGetSetSL(
                        rs.getString("KHOA"),
                        rs.getString("MACN"),
                        rs.getString("MAPGD"),
                        rs.getString("MAKH"),
                        rs.getString("TENKH"),
                        rs.getString("NGAYSINH"),
                        rs.getString("GIOITINH"),
                        rs.getString("CMT_SO"),
                        rs.getString("CMT_NOICAP"),
                        rs.getString("CMT_NGAYCAP"),
                        rs.getString("DIACHI"),
                        rs.getString("NGAYKYQUY"),
                        rs.getString("SOTIENKYQUY"),
                        rs.getString("NGAYBC"),
                        rs.getString("SOKU"),
                        rs.getString("D1"),
                        rs.getString("D2"),
                        rs.getString("D3"),
                        rs.getString("D4"),
                        rs.getString("D5"),
                        rs.getString("D6"),
                        rs.getString("D7"),
                        rs.getString("D8"),
                        rs.getString("D9"),
                        rs.getString("D10")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(epsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Arrays.asList(lst, dachot, chuachot, chotsai);
    }

    //Xem số liệu EPS
    public List<epsGetSetSL> xemsleps(String capbc, String tendn, String ngaybc) {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        List<epsGetSetSL> lst = new ArrayList<>();
        try {
            //Lưu dữ liệu vào CSDL và trả về kết quả
            CallableStatement st = con.prepareCall("{call PROC_GETSL_EPSPGD(?,?,?,?)}");
            st.setString(1, capbc);
            st.setString(2, tendn);
            st.setString(3, ngaybc);
            st.registerOutParameter(4, OracleTypes.CURSOR);
            st.execute();
            ResultSet rs = (ResultSet) st.getObject(4);
            while (rs.next()) {
                lst.add(new epsGetSetSL(
                        rs.getString("KHOA"),
                        rs.getString("MACN"),
                        rs.getString("MAPGD"),
                        rs.getString("MAKH"),
                        rs.getString("TENKH"),
                        rs.getString("NGAYSINH"),
                        rs.getString("GIOITINH"),
                        rs.getString("CMT_SO"),
                        rs.getString("CMT_NOICAP"),
                        rs.getString("CMT_NGAYCAP"),
                        rs.getString("DIACHI"),
                        rs.getString("NGAYKYQUY"),
                        rs.getString("SOTIENKYQUY"),
                        rs.getString("NGAYBC"),
                        rs.getString("SOKU"),
                        rs.getString("D1"),
                        rs.getString("D2"),
                        rs.getString("D3"),
                        rs.getString("D4"),
                        rs.getString("D5"),
                        rs.getString("D6"),
                        rs.getString("D7"),
                        rs.getString("D8"),
                        rs.getString("D9"),
                        rs.getString("D10")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(epsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    //Lưu số liệu EPS
    public void luusleps(List<String> macn, List<String> mapgd, List<String> makh, String ngaybc, List<String> soku, List<String> chotsl, List<String> nguyennhan) throws ParseException {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        try {
            //Lưu dữ liệu vào CSDL
            if (macn.size() > 0) {
                CallableStatement st = con.prepareCall("{call PROC_SAVESL_EPSPGD(?,?,?,?,?,?,?)}");
                for (int i = 0; i < macn.size(); i++) {
                    st.setString(1, macn.get(i));
                    st.setString(2, mapgd.get(i));
                    st.setString(3, makh.get(i));
                    st.setString(4, ngaybc);
                    st.setString(5, soku.get(i));
                    st.setString(6, chotsl.get(i));
                    st.setString(7, nguyennhan.get(i));
                    st.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(epsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Lưu số liệu EPS
    public void xacnhansleps(List<String> macn, List<String> mapgd, List<String> makh, String ngaybc, List<String> soku, List<String> chotsl, List<String> nguyennhan) throws ParseException {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        try {
            //Lưu dữ liệu vào CSDL
            if (macn.size() > 0) {
                CallableStatement st = con.prepareCall("{call PROC_XACNHANSL_EPSPGD(?,?,?,?,?,?,?)}");
                for (int i = 0; i < macn.size(); i++) {
                    st.setString(1, macn.get(i));
                    st.setString(2, mapgd.get(i));
                    st.setString(3, makh.get(i));
                    st.setString(4, ngaybc);
                    st.setString(5, soku.get(i));
                    st.setString(6, chotsl.get(i));
                    st.setString(7, nguyennhan.get(i));
                    st.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(epsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}