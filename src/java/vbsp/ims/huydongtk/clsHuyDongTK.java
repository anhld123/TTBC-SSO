/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.huydongtk;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.eps.epsAction;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author WELCOME
 */
public class clsHuyDongTK {
    public List<clsCanBo> getCanBo(String capbc, String tendn){
        List<clsCanBo> lst = new ArrayList<>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        try {
            //Thực hiện lấy các biến cần truy cập
            CallableStatement st = con.prepareCall("{call PROC_GET_HSCANBO(?,?,?)}");
            st.setString(1, capbc);
            st.setString(2, tendn);
            st.registerOutParameter(3, OracleTypes.CURSOR);
            st.execute();
            ResultSet rs = (ResultSet) st.getObject(3);
            while (rs.next()) {
                clsCanBo obj = new clsCanBo();
                lst.add(new clsCanBo(rs.getString("IDCANBO"), rs.getString("HOTENCB")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(epsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }
    
    public List<QT_DULIEU_NT> getData(String sNgaybc, String sUser,String sGrade, String cboCanBo) {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        List<QT_DULIEU_NT> lstData = new ArrayList<>();
        try {
            CallableStatement calstatement = null;
            String strStoreproce = "{call PROC_GETALL_HUYDONGCB(?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                calstatement = con.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, sNgaybc);
                calstatement.setString(2, sUser);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, cboCanBo);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                reset = (ResultSet) calstatement.getObject(5);
                while (reset.next()) {
                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setD1(reset.getString("GL_TK"));
                    value.setD2(reset.getString("SO_SERI"));
                    value.setD3(reset.getString("SOTK"));
                    value.setD4(reset.getString("MAKH"));
                    value.setD5(reset.getString("TENKH"));
                    value.setD6(reset.getString("MASP"));
                    value.setD7(reset.getString("SODU_SK"));
                    value.setD8(reset.getString("SODU_HD"));
                    value.setD9(reset.getString("KYHAN"));
                    value.setD10(reset.getString("MACB"));
                    lstData.add(value);
                }
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + "getData -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getData -> " + e.getMessage());
        }
        return lstData;
    }
    
    public String saveData(String sNgaybc, String sUser,String sGrade,String cbocanbo, String chitieu, ArrayList<String> chkChon) {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        String code = "";
        try {
            CallableStatement calstatement = null;
            String strStoreproce = "{call PROC_SAVEALL_HUYDONGCB(?,?,?,?,?,?,?)}";
            try {
                calstatement = con.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, sNgaybc);
                calstatement.setString(2, sUser);
                calstatement.setString(3, sGrade);
                calstatement.setString(4, cbocanbo);
                calstatement.setString(5, chitieu);
                calstatement.setString(6, chkChon.toString());
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.execute();
                code = (String) calstatement.getString(7);
                if (calstatement != null) {
                    calstatement.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + "saveData -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham saveData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveData -> " + e.getMessage());
        }
        return code;
    }
}
