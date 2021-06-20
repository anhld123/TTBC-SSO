/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.define.DefineFun;
import vbsp.ims.encrypt.DES;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.UserStaticInfor;

/**
 *
 * @author LION
 */
public class DaoLogin {

    private String strUserName;
    private String strPassword;
    private final DaoConnect daoConnect;

    public DaoLogin(String strUserName, String strPassword) {
        this.strPassword = strPassword;
        this.strUserName = strUserName;
        daoConnect = new DaoConnect();
    }

    public DaoLogin() {
        daoConnect = new DaoConnect();
    }

    public String getStrUserName() {
        return strUserName;
    }

    public void setStrUserName(String strUserName) {
        this.strUserName = strUserName;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public boolean validate() throws Exception {
        boolean status = false;
        int totalcount = 0;
        try {
            /* Phần xử lý chuỗi mật khẩu trước khi kiểm tra trong CSDL */
            System.err.println("--> Vao phan dang nhap");
            String key = DefineFun.getKeyDes("1");
            System.err.println("DefineFun.getKeyDes(\"1\")" + key);
            DES crypt = new DES(key);
            String encryptKey = crypt.encrypt(this.strPassword);
            System.err.println("DefineFun.encryptKey -->" + encryptKey);
            Connection con;
            con = daoConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT COUNT(*) TOTAL_COUNT FROM NG_DUNG WHERE (ND_MA = ?) AND (ND_MATKHAU = ?)"
                    + " AND ND_TTHAI = 'A'");
            ps.setString(1, strUserName);
            ps.setString(2, encryptKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                totalcount = rs.getInt("TOTAL_COUNT");
            }
            status = totalcount > 0;

            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " validate  -> " + e.getMessage());

        }
        return status;
    }

    public List<UserStaticInfor> getUserStaticInfor() {
        ArrayList<UserStaticInfor> js_userInfors = new ArrayList<>();
        Connection conn = daoConnect.getConnect();        
        CallableStatement calstatement;
        String strStoreproce = "{call p_userlogin_static(?)}";
        ResultSet reset;
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);            
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);                       
            calstatement.execute();            
            reset = (ResultSet) calstatement.getObject(1);
            String username; int grade;
            while(reset.next()){
                username = reset.getString("username");
                grade = reset.getInt("grade");
                js_userInfors.add(new UserStaticInfor(username, grade));
            }
            calstatement.close();
        } catch (SQLException e) {            
            CoreLogger.error("getUserStaticInfor()" + e.getMessage());
        }
        return js_userInfors;
    }
    
    public void updateLoginStatic(String username,String ipaddress,int loginGrade){
        Connection conn = daoConnect.getConnect();        
        CallableStatement calstatement;
        String strStoreproce = "{call p_usrlog_update_static_infor(?,?,?)}";        
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);            
            calstatement.setString(1, username);
            calstatement.setString(2, ipaddress);
            calstatement.setInt(3, loginGrade);
            calstatement.executeUpdate();                                    
            calstatement.close();
        } catch (SQLException e) {            
            CoreLogger.error("updateLoginStatic()" + e.getMessage());
        }
    }
}
