/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.khnv2021;

import vbsp.ims.khnv2021.model.PosClass;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import vbsp.ims.bcqt.model.DULIEU_NT;
import vbsp.ims.dao.DaoConnect;

/**
 *
 * @author Admin
 */
public class AuthorModel {

    //Hàm lấy danh mục đơn vị theo cấp báo cáo
    public List<PosClass> getPosCD(String CapBC, String TenDN) {
        List<PosClass> lst = new ArrayList<>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        try {
            //Thực hiện lấy các biến cần truy cập
            CallableStatement st = con.prepareCall("{call VBSP_IMS_KHNV2021.KHNV2021_GETPOS(?,?,?)}");
            st.setString(1, CapBC);
            st.setString(2, TenDN);
            st.registerOutParameter(3, OracleTypes.CURSOR);
            st.execute();
            ResultSet rs = (ResultSet) st.getObject(3);
            while (rs.next()) {
                PosClass obj = new PosClass();
                lst.add(new PosClass(rs.getString("PO_MA"), rs.getString("PO_TEN")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AuthorClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    //Hàm lấy tải dữ liệu
    public List<DULIEU_NT> getData(String CapBC, String TenDN, String cboDonvi, String cboNam, String cboDot, String cboTonghop, String strNguyennhan) {
        List<DULIEU_NT> lst = new ArrayList<>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        try {
            //Thực hiện lấy các biến cần truy cập
            //new AuthorModel().getData(CapBC, TenDN,cboDonvi,cboNam,cboDot, cboTonghop, strNguyennhan);
            CallableStatement st = con.prepareCall("{call VBSP_IMS_KHNV2021.KHNV2021_GETDATA_PGD(?,?,?,?,?,?,?,?,?,?)}");
            st.setString(1, CapBC);
            st.setString(2, TenDN);
            st.setString(3, cboDonvi);
            st.setString(4, cboNam);
            st.setString(5, cboDot);
            st.setString(6, cboTonghop);
            st.setString(7, strNguyennhan);
            st.registerOutParameter(8, OracleTypes.NUMBER);
            st.registerOutParameter(9, OracleTypes.VARCHAR);
            st.registerOutParameter(10, OracleTypes.CURSOR);
            st.execute();
            ResultSet rs = (ResultSet) st.getObject(10);
            while (rs.next()) {
                DULIEU_NT obj = new DULIEU_NT();
                lst.add(new getDULIEU_NT().getData(obj, rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AuthorClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    //Hàm gửi dữ liệu
    public String sendData(String CapBC, String TenDN) {
        return "10";
    }

    //Hàm hoàn trả
    public String rollBackData(String CapBC, String TenDN) {
        return "20";
    }

}
