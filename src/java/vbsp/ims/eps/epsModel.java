/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.eps;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    public List<PosClass> getChiNhanh(String capbc, String tendn) {
        List<PosClass> lst = new ArrayList<>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        try {
            //Thực hiện lấy các biến cần truy cập
            CallableStatement st = con.prepareCall("{call PROC_GET_CHINHANH(?,?,?)}");
            st.setString(1, capbc);
            st.setString(2, "000000");
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
    
    public Map<String, String> getCNToPGD(String capbc, String tendn) {
        Map<String, String> lstPGD = new HashMap<String, String>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        try {
            //Thực hiện lấy các biến cần truy cập
            CallableStatement st = con.prepareCall("{call PROC_GET_CHINHANH(?,?,?)}");
            st.setString(1, capbc);
            st.setString(2, tendn);
            st.registerOutParameter(3, OracleTypes.CURSOR);
            st.execute();
            ResultSet rs = (ResultSet) st.getObject(3);
            while (rs.next()) {
               lstPGD.put(rs.getString("MAPGD"), rs.getString("TENDV"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(epsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstPGD;
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

    public List<epsGetSetSL> getAllData(String capbc, String tendn, String madv, String ngaybc, String nghiepvu, String matinh) {
        List<epsGetSetSL> lst = new ArrayList<>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        try {
            //Lưu dữ liệu vào CSDL và trả về kết quả
            CallableStatement st = con.prepareCall("{call PROC_GETPH_DONVI(?,?,?,?,?,?,?)}");
            st.setString(1, capbc);
            st.setString(2, tendn);
            st.setString(3, madv);
            st.setString(4, ngaybc);
            st.setString(5, nghiepvu);
            st.setString(6, matinh);
            st.registerOutParameter(7, OracleTypes.CURSOR);
            st.execute();
            ResultSet rs = (ResultSet) st.getObject(7);
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
    public void luusleps(List<String> macn, List<String> mapgd, List<String> makh, String ngaybc, List<String> soku, List<String> chotsl, List<String> nguyennhan, String tendn, String phanhoi) throws ParseException {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        String flginsert = "Y";
        try {
            //Lưu dữ liệu vào CSDL
            if (macn.size() > 0) {
                CallableStatement st = con.prepareCall("{call PROC_SAVESL_EPSPGD(?,?,?,?,?,?,?,?,?,?)}");
                for (int i = 0; i < macn.size(); i++) {
                    st.setString(1, macn.get(i));
                    st.setString(2, mapgd.get(i));
                    st.setString(3, makh.get(i));
                    st.setString(4, ngaybc);
                    st.setString(5, soku.get(i));
                    st.setString(6, chotsl.get(i));
                    st.setString(7, nguyennhan.get(i));
                    st.setString(8, tendn);
                    st.setString(9, phanhoi);
                    st.setString(10, flginsert);
                    st.executeUpdate();
                    flginsert = "N";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(epsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Lưu số liệu EPS
    public void xacnhansleps(List<String> macn, List<String> mapgd, List<String> makh, String ngaybc, List<String> soku, List<String> chotsl, List<String> nguyennhan, String tendn, String phanhoi) throws ParseException {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        String flginsert = "Y";
        try {
            //Lưu dữ liệu vào CSDL
            if (macn.size() > 0) {
                CallableStatement st = con.prepareCall("{call PROC_XACNHANSL_EPSPGD(?,?,?,?,?,?,?,?,?,?)}");
                for (int i = 0; i < macn.size(); i++) {
                    st.setString(1, macn.get(i));
                    st.setString(2, mapgd.get(i));
                    st.setString(3, makh.get(i));
                    st.setString(4, ngaybc);
                    st.setString(5, soku.get(i));
                    st.setString(6, chotsl.get(i));
                    st.setString(7, nguyennhan.get(i));
                    st.setString(8, tendn);
                    st.setString(9, phanhoi);
                    st.setString(10, flginsert);
                    st.executeUpdate();
                    flginsert = "N";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(epsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Mở khoá đối với PGD
    public void openlock(List<String> macn, List<String> mapgd, List<String> makh, String ngaybc, List<String> soku, String tendn, String status) throws ParseException {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        int i = (int) Double.parseDouble(status)-1;
        try {
            //Lưu dữ liệu vào CSDL
            if (macn.size() > 0) {
                CallableStatement st = con.prepareCall("{call PROC_UNLOCK_EPSPGD(?,?,?,?,?,?)}");
                st.setString(1, macn.get(i));
                st.setString(2, mapgd.get(i));
                st.setString(3, makh.get(i));
                st.setString(4, ngaybc);
                st.setString(5, soku.get(i));
                st.setString(6, tendn);
                st.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(epsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Mở khoá đối với PGD
    public int updateintellect(String tendn, List<String> mapgd, List<String> makh, String ngaybc, String status) throws ParseException {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        int i = (int) Double.parseDouble(status.substring(3));
        int P_RECORD_TOTAL = 0;
        int P_ERR_CODE = 0;
        try {
            //Lưu dữ liệu vào CSDL
            if (mapgd.size() > 0) {
                CallableStatement st = con.prepareCall("{call VBSP_BCTD.P_BCTD_DS_NHANKYQUY_MAKH(?,?,?,?,?,?)}");
                st.setString(1, makh.get(i));
                st.setString(2, mapgd.get(i));
                st.setString(3, ngaybc);
                st.setString(4, tendn);
                st.registerOutParameter(5, OracleTypes.NUMBER);
                st.registerOutParameter(6, OracleTypes.NUMBER);
                st.registerOutParameter(7, OracleTypes.VARCHAR);
                st.execute();
                P_RECORD_TOTAL = (int) st.getObject(5);
                P_ERR_CODE = (int) st.getObject(6);
                String P_ERR_MSG = (String) st.getObject(7);
            }
        } catch (SQLException ex) {
            Logger.getLogger(epsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return P_RECORD_TOTAL;
    }
}