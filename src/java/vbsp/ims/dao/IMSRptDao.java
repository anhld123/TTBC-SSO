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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.define.DefineFun;
import vbsp.ims.encrypt.DES;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.MenuItem;
import vbsp.ims.model.ReportGroup;
import vbsp.ims.model.ReportUserGroup;
import vbsp.ims.model.TNode;
import vbsp.ims.model.User;
import vbsp.ims.model.UserGroup;
import vbsp.ims.model.VbspNews;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class IMSRptDao {

    private static final DaoConnect daoConnect;

    static {
        daoConnect = new DaoConnect();
    }

    // Ham kiem tra username khi dang nhap
//    public static boolean validate(String username, String password) {
//        boolean status = false;
//        int totalcount = 0;
//        try {
//            Connection con = null;
//            con = daoConnect.getConnect();
//            PreparedStatement ps = con.prepareStatement(
//                    "SELECT COUNT(*) TOTAL_COUNT FROM NG_DUNG WHERE ND_MA = ? AND ND_MATKHAU = ?");
//            ps.setString(1, username);
//            ps.setString(2, password);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                totalcount = rs.getInt("TOTAL_COUNT");
//            }
//            status = totalcount > 0;
//            
//            if(rs != null) rs.close();
//            if(ps != null) ps.close();
//            if(con != null) con.close();
//        } catch (SQLException e) {
//            //e.printStackTrace();
//            CoreLogger.error(IMSRptDao.class.getCanonicalName()+" validate  -> "+e.getMessage());
//            
//        }
//        return status;
//    }
    public static ArrayList<User> getUsers() {
        String lcQuery, lcUserCode, lcUserName, lcAddress, lcMobile, lcOffice, lcPassword,
                lcPubKey, lcUserGroup, lcRptGrade, lcPosCode, lcStatus, lcMaCanBo, lcNhomCongViec;
        int iValidFlag;
        ArrayList<User> users = new ArrayList<>();
        lcQuery = "SELECT ND_MA,ND_TEN,ND_DIACHI,ND_MOBILE,ND_CHUCVU,ND_MATKHAU,ND_KHOACK,"
                + "ND_NHOMND,ND_CAPBC,ND_MADV,ND_TTHAI, ND_MACB, ND_CHUCNANG, pk_dky_chucnang_ngdung.f_is_valid_nhom(ND_MACB, ND_CHUCNANG) AS VALID_FLAG "
                + "FROM NG_DUNG ORDER BY ND_MADV,ND_MA";
        try {
            Connection con;
            con = daoConnect.getConnect();
            Statement lcStatement;
            lcStatement = con.createStatement();
            ResultSet rs = lcStatement.executeQuery(lcQuery);
            while (rs.next()) {
                lcUserCode = rs.getString("ND_MA");
                lcUserName = rs.getString("ND_TEN");
                lcAddress = rs.getString("ND_DIACHI");
                lcMobile = rs.getString("ND_MOBILE");
                lcOffice = rs.getString("ND_CHUCVU");
                lcPassword = rs.getString("ND_MATKHAU");
                lcPubKey = rs.getString("ND_KHOACK");
                lcUserGroup = rs.getString("ND_NHOMND");
                lcRptGrade = rs.getString("ND_CAPBC");
                lcPosCode = rs.getString("ND_MADV");
                lcStatus = rs.getString("ND_TTHAI");
                lcMaCanBo = rs.getString("ND_MACB");
                lcNhomCongViec = rs.getString("ND_CHUCNANG");
                iValidFlag = Integer.parseInt(rs.getString("VALID_FLAG"));
                
                users.add(new User(lcUserCode, lcUserName, lcAddress, lcMobile, lcOffice,
                        lcPassword, lcPubKey, lcUserGroup, lcRptGrade, lcPosCode, lcStatus, lcMaCanBo, lcNhomCongViec,iValidFlag));
            }
            rs.close();
            lcStatement.close();
            con.close();
        } catch (SQLException ex) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " getUsers  -> " + ex.getMessage());
        }
        return users;
    }

    public static boolean addUser(String paraUserCode, String paraUserName, String paraAddress, String paraMobile,
            String paraOffice, String paraPassword, String paraPubKey, String paraUserGroup,
            String paraRptGrade, String paraPosCode, String paraStatus, String paraMaCanBo, String paraNhomCongViec) {
        int insertedRows = 0;
        try {
            Connection con;
            con = daoConnect.getConnect();
            String encryptPwd;
            encryptPwd = encryptPassword(paraPassword);
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO NG_DUNG(ND_MA, ND_TEN,ND_DIACHI,ND_MOBILE,ND_CHUCVU,ND_MATKHAU,ND_KHOACK,ND_NHOMND,ND_CAPBC,ND_MADV,ND_TTHAI,ND_MACB, ND_CHUCNANG)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?, ?,?)");
            ps.setString(1, paraUserCode);
            ps.setString(2, paraUserName);
            ps.setString(3, paraAddress);
            ps.setString(4, paraMobile);
            ps.setString(5, paraOffice);
            ps.setString(6, encryptPwd);
            ps.setString(7, paraPubKey);
            ps.setString(8, paraUserGroup);
            ps.setString(9, paraRptGrade);
            ps.setString(10, paraPosCode);
            ps.setString(11, paraStatus);
            ps.setString(12, paraMaCanBo);
            ps.setString(13, paraNhomCongViec);
            
            
            insertedRows = ps.executeUpdate();

            //if(rs != null) rs.close();
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " addUser  -> " + e.getMessage());
        }
        return insertedRows > 0;
    }

    public static boolean addUser(User user) {
        return addUser(
                user.getPriUserCode(), 
                user.getPriUserName(),
                user.getPriAddress(), 
                user.getPriMobile(), 
                user.getPriOffice(),
                user.getPriPassword(), 
                user.getPriPubKey(), 
                user.getPriUserGroup(),
                user.getPriRptGrade(), 
                user.getPriPosCode(), 
                user.getPriStatus(),
                user.getPriMaCanBo(),
                user.getPriNhomCongViec()
                );
    }

    public static boolean deleteUser(String userCode) {
        int deletedRows = 0;
        try {
            Connection con;
            con = daoConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM NG_DUNG WHERE ND_MA = ? ");
            ps.setString(1, userCode);
            deletedRows = ps.executeUpdate();
            ps.close();
            con.close();

        } catch (SQLException e) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " deleteUser  -> " + e.getMessage());
        }
        return deletedRows > 0;
    }

    /* Hàm lấy danh sách nhóm người dùng */
    public static ArrayList<UserGroup> getUserGroups() {
        String lcQuery, lcUserGroup, lcGroupDescript, lcGroupAlias, lcGroupPrivilege, lcGroupStatus,
                js_childgroup,js_viewtype;
        ArrayList<UserGroup> userGroups = new ArrayList<>();
        lcQuery = "SELECT ND_NHOM, ND_MOTA,ND_TENVT,ND_PQUYEN,ND_TTHAI,NHOM_QUYEN,VIEW_TYPE "
                + "FROM NHOM_NGD ORDER BY 1";
        try {
            Connection con;
            con = daoConnect.getConnect();
            Statement lcStatement;
            lcStatement = con.createStatement();
            try (ResultSet rs = lcStatement.executeQuery(lcQuery)) {
                while (rs.next()) {
                    lcUserGroup = rs.getString("ND_NHOM");
                    lcGroupDescript = rs.getString("ND_MOTA");
                    lcGroupAlias = rs.getString("ND_TENVT");
                    lcGroupPrivilege = rs.getString("ND_PQUYEN");
                    lcGroupStatus = rs.getString("ND_TTHAI");
                    js_childgroup = rs.getString("NHOM_QUYEN");
                    js_viewtype = rs.getString("VIEW_TYPE");
                    userGroups.add(new UserGroup(lcUserGroup, lcGroupDescript,
                            lcGroupAlias, lcGroupPrivilege, lcGroupStatus, js_childgroup,js_viewtype));
                }
            }
            lcStatement.close();
            con.close();
        } catch (SQLException ex) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " getUserGroup  -> " + ex.getMessage());
        }
        return userGroups;
    }

    /* Hàm lấy danh sách nhóm báo cáo */
    public static ArrayList<ReportGroup> getReportGroups() {
        String lcQuery, lcReportGroup, lcGroupDescript, lcGroupAlias, lcGroupType, lcGroupStatus;
        int liMenuId;
        ArrayList<ReportGroup> reportGroups = new ArrayList<>();
        lcQuery = "SELECT DM_NHOMBC, DM_MOTA,DM_TENVT,DM_NHCS,APPLY_FLG,B.MENUID FROM DMBC A, MENU_NHOMBC B "
                + "WHERE A.DM_NHOMBC = B.NHOMBC(+) ORDER BY 1";
        try {
            Connection con;
            con = daoConnect.getConnect();
            Statement lcStatement;
            lcStatement = con.createStatement();
            ResultSet rs = lcStatement.executeQuery(lcQuery);
            while (rs.next()) {
                lcReportGroup = rs.getString("DM_NHOMBC");
                lcGroupDescript = rs.getString("DM_MOTA");
                lcGroupAlias = rs.getString("DM_TENVT");
                lcGroupType = rs.getString("DM_NHCS");
                lcGroupStatus = rs.getString("APPLY_FLG");
                liMenuId = rs.getInt("MENUID");
                reportGroups.add(new ReportGroup(lcReportGroup, lcGroupDescript,
                        lcGroupAlias, lcGroupType, lcGroupStatus, liMenuId));
            }
            rs.close();
            lcStatement.close();
            con.close();
        } catch (SQLException ex) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " getReportGroup  -> " + ex.getMessage());
        }
        return reportGroups;
    }
    /* Hàm tạo mới nhóm người dùng*/

    public static boolean addGroupUser(String pUserGroup, String pGroupDescript,
            String pGroupAlias, String pGroupPrivilege, String pGroupStatus,String pViewType, String pMaker) {
        int insertedRows = 0;
        try {
            Connection con;
            con = daoConnect.getConnect();
            try (PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO NHOM_NGD(ND_NHOM,ND_MOTA,ND_TENVT,ND_PQUYEN,ND_TTHAI,APPLY_FLG,VIEW_TYPE)"
                    + "VALUES(?,?,?,?,?,?,?)")) {
                ps.setString(1, pUserGroup);
                ps.setString(2, pGroupDescript);
                ps.setString(3, pGroupAlias);
                ps.setString(4, pGroupPrivilege);
                ps.setString(5, pGroupStatus);
                ps.setString(6, "Y");
                ps.setString(7, pViewType);
                insertedRows = ps.executeUpdate();

                /*Cap nhat vao bang PQ_NGDUNG */
                if (insertedRows > 0) {
                    String strQuery = "{call p_update_usergrp_privileage(?,?,?)}";
                    CallableStatement calstatement;
                    //Thuc hien execute truy van
                    calstatement = con.prepareCall(strQuery);
                    calstatement.setString(1, pUserGroup);
                    calstatement.setString(2, pMaker);
                    calstatement.setString(3, "1");
                    int ji_updaterow = calstatement.executeUpdate();
                    calstatement.close();
                }
            }
            con.close();

        } catch (SQLException e) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " addUserGroup  -> " + e.getMessage());
        }
        return insertedRows > 0;
    }

    public static String[] checkUserGroup(String jp_usergroup) {
        String js_return_str[] = new String[3];
        if (jp_usergroup == null || jp_usergroup.isEmpty()) {
            js_return_str[0] = "E";
            js_return_str[1] = "(*)Mã nhóm không được để trống ";
            js_return_str[2] = "ERROR";
        } else {
            try {
                try (Connection con = daoConnect.getConnect()) {
                    String strQuery = "{call p_check_usergroup(?,?,?)}";
                    CallableStatement calstatement;
                    //Thuc hien execute truy van
                    calstatement = con.prepareCall(strQuery);
                    calstatement.setString(1, jp_usergroup);
                    calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                    calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                    calstatement.execute();
                    String js_status_str = (String) calstatement.getObject(2);
                    String js_suggess_str = (String) calstatement.getObject(3);
                    if (js_status_str.equals("EXISTS")) {
                        js_return_str[0] = "E";
                        js_return_str[1] = "(*)Mã nhóm đã tồn tại, bạn nên thử mã sau: " + js_suggess_str;
                        js_return_str[2] = js_suggess_str;
                    } else {
                        if (js_status_str.equals("INVALID")) {
                            js_return_str[0] = "E";
                            js_return_str[1] = "(*)Mã nhóm đã không đúng định dạng, "
                                    + "bạn nên thử mã sau: " + js_suggess_str;
                            js_return_str[2] = js_suggess_str;
                        } else {
                            js_return_str[0] = "S";
                            js_return_str[1] = "(*)Mã nhóm hợp lệ";
                            js_return_str[2] = jp_usergroup;
                        }
                    }
                    calstatement.close();
                }
            } catch (SQLException e) {
            }
        }
        return js_return_str;
    }

    public static int checkMaCanBo(String sUsername, String sMaCanBo){
        try {
                Connection con = daoConnect.getConnect();
                String strQuery = "{call pk_dky_chucnang_ngdung.p_check_macanbo(?,?,?)}";
                CallableStatement calstatement;
                //Thuc hien execute truy van
                calstatement = con.prepareCall(strQuery);
                calstatement.setString(1, sUsername);
                calstatement.setString(2, sMaCanBo);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.INTEGER);                    
                calstatement.execute();
                int iStatus = Integer.parseInt(calstatement.getObject(3).toString());                                       
                calstatement.close();
                return iStatus;                
        } catch (Exception e) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " checkMaCanBo  -> " + e.getMessage());
            return 1;
        }
    }
    
    public static int checkDangKy(String sUsername){
        try {
                Connection con = daoConnect.getConnect();
                String strQuery = "{call pk_dky_chucnang_ngdung.p_check_dangky(?,?)}";
                CallableStatement calstatement;
                //Thuc hien execute truy van
                calstatement = con.prepareCall(strQuery);
                calstatement.setString(1, sUsername);                
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.INTEGER);                    
                calstatement.execute();
                int iStatus = Integer.parseInt(calstatement.getObject(2).toString());                                       
                calstatement.close();
                return iStatus;                
        } catch (Exception e) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " checkMaCanBo  -> " + e.getMessage());
            return 1;
        }
    }
    
    public static String[] checkUser(String jp_usergroup,String jp_maker) {
        String js_return_str[] = new String[3];
        if (jp_usergroup == null || jp_usergroup.isEmpty()) {
            js_return_str[0] = "E";
            js_return_str[1] = "(*)Mã nhóm không được để trống ";
            js_return_str[2] = "ERROR";
        } else {
            try {
                try (Connection con = daoConnect.getConnect()) {
                    String strQuery = "{call p_check_user(?,?,?,?)}";
                    CallableStatement calstatement;
                    //Thuc hien execute truy van
                    calstatement = con.prepareCall(strQuery);
                    calstatement.setString(1, jp_usergroup);
                    calstatement.setString(2, jp_maker);
                    calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                    calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                    calstatement.execute();
                    String js_status_str = (String) calstatement.getObject(3);
                    String js_suggess_str = (String) calstatement.getObject(4);
                    if (js_status_str.equals("EXISTS")) {
                        js_return_str[0] = "E";
                        js_return_str[1] = "(*)Mã người dùng đã tồn tại, bạn nên thử mã sau: " 
                                + js_suggess_str;
                        js_return_str[2] = js_suggess_str;
                    } else {
                        if (js_status_str.equals("INVALID")) {
                            js_return_str[0] = "E";
                            js_return_str[1] = "(*)Mã người dùng đã không đúng định dạng, "
                                    + "bạn nên thử mã sau: " + js_suggess_str;
                            js_return_str[2] = js_suggess_str;
                        } else {
                            js_return_str[0] = "S";
                            js_return_str[1] = "(*)Mã người dùng hợp lệ";
                            js_return_str[2] = jp_usergroup;
                        }
                    }
                    calstatement.close();
                }
            } catch (SQLException e) {}
        }
        return js_return_str;
    }
    
    public static String getMenuString(String pMaker) {
        String js_menustr = "";
        try {
            Connection con;
            con = daoConnect.getConnect();
            String strQuery = "{call p_get_menu_str(?,?)}";
            CallableStatement calstatement;
            //Thuc hien execute truy van
            calstatement = con.prepareCall(strQuery);
            calstatement.setString(1, pMaker);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            js_menustr = (String) calstatement.getObject(2);
            calstatement.close();
            con.close();
        } catch (SQLException e) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " addUserGroup  -> " + e.getMessage());
        }
        return js_menustr;
    }

    /* Hàm tạo mới nhóm báo cáo */
    /* Hàm tạo mới nhóm người dùng*/
    public static boolean addReportGroup(String pReportGroup, String pGroupDescript,
            String pGroupAlias, String pGroupType, String pGroupStatus, int pMenuId) {
        int insertedRows = 0;
        try {
            Connection con;
            con = daoConnect.getConnect();

            //Cập nhật vào bảng DMBC
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO DMBC(DM_NHOMBC,DM_MOTA,DM_TENVT,DM_NHCS,APPLY_FLG)"
                    + "VALUES(?,?,?,?,?)");
            ps.setString(1, pReportGroup);
            ps.setString(2, pGroupDescript);
            ps.setString(3, pGroupAlias);
            ps.setString(4, pGroupType);
            ps.setString(5, pGroupStatus);
            insertedRows = ps.executeUpdate();

            // Cập nhật vào bảng MENU_NHOMBC
            ps = con.prepareStatement(
                    "INSERT INTO MENU_NHOMBC(MENUID,NHOMBC,TRANGTHAI)"
                    + "VALUES(?,?,?)");
            ps.setInt(1, pMenuId);
            ps.setString(2, pReportGroup);
            ps.setString(3, "A");
            insertedRows = ps.executeUpdate();

            ps.close();
            con.close();

        } catch (SQLException e) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " addUserGroup  -> " + e.getMessage());
        }
        return insertedRows > 0;
    }

    public static boolean addUserGroup(UserGroup userGroup, String pMaker) {
        return addGroupUser(userGroup.getPriGroupCode(), userGroup.getPriGroupDesc(),
                userGroup.getPriGroupAlias(),
                userGroup.getPriPrivilege(),
                userGroup.getPriGroupStatus(),
                userGroup.getPriviewType(),
                pMaker);
    }

    public static boolean addReportGroup(ReportGroup reportGroup) {
        return addReportGroup(reportGroup.getPriGroupCode(), reportGroup.getPriGroupDesc(),
                reportGroup.getPriGroupAlias(), reportGroup.getPriGroupType(),
                reportGroup.getPriGroupStatus(), reportGroup.getPriMenuId());
    }
    /* Hàm cập nhật thông tin nhóm người dùng */

    public static boolean updateUserGroup(UserGroup userGroup, String pMaker) {
        int updateRows = 0;
        try {
            Connection con;
            con = daoConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE NHOM_NGD SET ND_MOTA = ?, ND_TENVT = ?,ND_PQUYEN = ?,ND_TTHAI = ?, VIEW_TYPE = ?"
                    + " WHERE ND_NHOM = ? ");
            ps.setString(1, userGroup.getPriGroupDesc());
            ps.setString(2, userGroup.getPriGroupAlias());
            ps.setString(3, userGroup.getPriPrivilege());
            ps.setString(4, userGroup.getPriGroupStatus());            
            ps.setString(5, userGroup.getPriviewType());
            ps.setString(6, userGroup.getPriGroupCode());
            updateRows = ps.executeUpdate();
            if (updateRows > 0) {
                String strQuery = "{call p_update_usergrp_privileage(?,?,?)}";
                CallableStatement calstatement;
                //Thuc hien execute truy van
                calstatement = con.prepareCall(strQuery);
                calstatement.setString(1, userGroup.getPriGroupCode());
                calstatement.setString(2, pMaker);
                calstatement.setString(3, "2");
                int ji_updaterow = calstatement.executeUpdate();
                //calstatement.close();
                
                strQuery = "{call sp_generate_menu()}";             
                calstatement = con.prepareCall(strQuery);                
                ji_updaterow = calstatement.executeUpdate();
                calstatement.close();
            }
        } catch (SQLException e) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " addUserGroup  -> " + e.getMessage());
        }
        return updateRows > 0;
    }

    /* Hàm cập nhật thông tin nhóm báo cáo */
    public static boolean updateReportGroup(ReportGroup reportGroup) {
        int updateRows = 0;
        try {
            Connection con;
            con = daoConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE DMBC SET DM_MOTA = ?, DM_TENVT = ?,DM_NHCS = ?,APPLY_FLG = ?"
                    + " WHERE DM_NHOMBC = ? ");
            ps.setString(1, reportGroup.getPriGroupDesc());
            ps.setString(2, reportGroup.getPriGroupAlias());
            ps.setString(3, reportGroup.getPriGroupType());
            ps.setString(4, reportGroup.getPriGroupStatus());
            ps.setString(5, reportGroup.getPriGroupCode());
            updateRows = ps.executeUpdate();

            ps = con.prepareStatement(
                    "UPDATE MENU_NHOMBC SET MENUID = ?"
                    + " WHERE NHOMBC = ? ");
            ps.setInt(1, reportGroup.getPriMenuId());
            ps.setString(2, reportGroup.getPriGroupCode());
            updateRows = ps.executeUpdate();
        } catch (SQLException e) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " addUserGroup  -> " + e.getMessage());
        }
        return updateRows > 0;
    }

    /* Hàm xoá nhóm người dùng */
    public static boolean deleteUserGroup(String userGroupId) {
        int deletedRows = 0;
        try {
            try (Connection con = daoConnect.getConnect()) {
                String strQuery = "{call p_delete_usergroup(?)}";
                CallableStatement calstatement;
                //Thuc hien execute truy van
                calstatement = con.prepareCall(strQuery);
                calstatement.setString(1, userGroupId);
                deletedRows = calstatement.executeUpdate();
                calstatement.close();
            }
        } catch (SQLException e) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " deleteUserGroup  -> " + e.getMessage());
        }
        return deletedRows > 0;
    }

    /* Hàm xoá nhóm báo cáo */
    public static boolean deleteReportGroup(String reportGroupId) {
        int deletedRows = 0;
        try {
            Connection con;
            con = daoConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM DMBC WHERE DM_NHOMBC = ? ");
            ps.setString(1, reportGroupId);
            deletedRows = ps.executeUpdate();

            ps = con.prepareStatement(
                    "DELETE FROM MENU_NHOMBC WHERE NHOMBC = ? ");
            ps.setString(1, reportGroupId);
            deletedRows = ps.executeUpdate();

            ps.close();
            con.close();
        } catch (SQLException e) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " deleteUserGroup  -> " + e.getMessage());
        }
        return deletedRows > 0;
    }

    public static String getTitle(String username) {
        String lcQuery, lcTitle = "";
        lcQuery = "SELECT PO_TEN FROM NG_DUNG A, DMPOS B WHERE A.ND_MADV = B.PO_MA AND A.ND_MA = ?";
        try {
            Connection con;
            con = daoConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(lcQuery);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lcTitle = rs.getString("PO_TEN");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
        }
        return lcTitle;
    }

    public static String getPosOfUser(String username) {
        String lcQuery, lcPosCode = "";
        lcQuery = "SELECT PO_MA FROM NG_DUNG A, DMPOS B WHERE A.ND_MADV = B.PO_MA AND A.ND_MA = ?";
        try {
            Connection con;
            con = daoConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(lcQuery);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lcPosCode = rs.getString("PO_MA");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
        }
        return lcPosCode;
    }

    public static String getReportGrade(String username) {
        String lcQuery, lcTitle = "";
        lcQuery = "SELECT ND_CAPBC FROM NG_DUNG A WHERE A.ND_MA = ? AND A.ND_TTHAI = 'A'";
        try {
            Connection con;
            con = daoConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(lcQuery);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lcTitle = rs.getString("ND_CAPBC");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            CoreLogger.error(IMSRptDao.class.getClass() + " setTitleUser  -> " + ex.getMessage());
        }
        return lcTitle;
    }

    public static String getUserGroup(String username) {
        String lcQuery, lcUserGroup = "";
        lcQuery = "SELECT ND_NHOMND FROM NG_DUNG A WHERE A.ND_MA = ? AND A.ND_TTHAI = 'A'";
        try {
            Connection con;
            con = daoConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(lcQuery);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lcUserGroup = rs.getString("ND_NHOMND");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            CoreLogger.error(IMSRptDao.class.getClass() + " getUserGroup  -> " + ex.getMessage());
        }
        return lcUserGroup;
    }

    public static ArrayList<MenuItem> getMenu(String userId) {
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        String lcQuery = "SELECT A.MENUID,A.TEXT,A.DESCRIPTION,A.PARENTID,A.NAVIGATEURL,NVL(B.CHILDTOTAL,0) CHILDTOTAL FROM menu_tmp A, "
                + "(SELECT PARENTID,COUNT(*) CHILDTOTAL FROM menu_tmp "
                + "GROUP BY PARENTID "
                + "ORDER BY PARENTID) B "
                + "WHERE A.MENUID = B.PARENTID(+) AND A.GROUPID = (select ND_NHOMND from ng_dung where nd_ma = ?) "
                + "ORDER BY MENUID";
        try {
            Connection con;
            con = daoConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(lcQuery);
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            String text, description, navigateUrl;
            int menuId, parentId, childTotal;
            while (rs.next()) {
                menuId = rs.getInt("MENUID");
                text = rs.getString("TEXT");
                description = rs.getString("DESCRIPTION");
                parentId = rs.getInt("PARENTID");
                navigateUrl = rs.getString("NAVIGATEURL");
                childTotal = rs.getInt("CHILDTOTAL");
                menuItems.add(new MenuItem(menuId, text, description, parentId, navigateUrl, childTotal));
            }
            rs.close();
            ps.close();
            con.close();
            return menuItems;
        } catch (SQLException ex) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " getMenu  -> " + ex.getMessage());
            return null;
        }
    }

    public static ArrayList<TNode> getPosTree(String mapos, int capbc) {
        ArrayList<TNode> posList = new ArrayList<>();
        String lcQuery = "";
        switch (capbc) {
            case 1:
                lcQuery = "select 1 ID,0 PARENT,PO_MA CODE,PO_TEN NAME from dmpos where po_ma = ? ";
                break;
            case 2:
                lcQuery = "select 1 ID,0 PARENT,PO_MA CODE,PO_TEN NAME from dmpos where po_ma = ? "
                        + "UNION "
                        + "select ROWNUM + 1 ID,1 PARENT,PO_MA CODE,PO_TEN NAME from dmpos where po_macn = ?";
                break;
            case 3:
                lcQuery = "";
                break;
        }
        return posList;
    }

    public static String getPassword(String userId) {
        String lcQuery, lcPassword = "";
        lcQuery = "select ND_MATKHAU from ng_dung where nd_ma = ?";
        try {
            Connection con;
            con = daoConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(lcQuery);
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lcPassword = rs.getString("ND_MATKHAU");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " getPassword  -> " + ex.getMessage());
        }
        return lcPassword;
    }

    public static boolean updatePassword(String userId, String newPass) {
        int updateRows = 0;
        try {
            Connection con;
            con = daoConnect.getConnect();
            String encryptPwd;
            encryptPwd = encryptPassword(newPass);
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE NG_DUNG SET ND_MATKHAU = ? WHERE ND_MA = ? ");
            ps.setString(1, encryptPwd);
            ps.setString(2, userId);
            updateRows = ps.executeUpdate();
        } catch (SQLException e) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " updatePassword  -> " + e.getMessage());
        }
        return updateRows > 0;
    }

    public static boolean updateUser(User user, String updateType, String updatePassword) {
        int updateRows = 0;
        try {
            Connection con = daoConnect.getConnect();
            PreparedStatement ps;
            if (updateType.equals("2")) {
                String sSql = 
                        "UPDATE NG_DUNG SET ND_TEN = ?, ND_DIACHI = ?, ND_MOBILE = ?, ND_CHUCVU = ?, ND_KHOACK = ?, ND_MACB= ?, ND_CHUCNANG = ?"
                        + " WHERE ND_MA = ?";
                ps = con.prepareStatement(sSql);
                ps.setString(1, user.getPriUserName());
                ps.setString(2, user.getPriAddress());
                ps.setString(3, user.getPriMobile());
                ps.setString(4, user.getPriOffice());
                ps.setString(5, user.getPriPubKey());
                ps.setString(6, user.getPriMaCanBo());
                ps.setString(7, user.getPriNhomCongViec());
                ps.setString(8, user.getPriUserCode());
                
            } else {
                String encryptPwd;
                if (updatePassword.equals("Y")) {
                    encryptPwd = encryptPassword(user.getPriPassword());
                } else {
                    encryptPwd = user.getPriPassword().trim();
                }
                String sSql = 
                        "UPDATE NG_DUNG SET ND_TEN = ?, ND_DIACHI = ?, ND_MOBILE = ?, ND_CHUCVU = ?,ND_MATKHAU = ?, ND_KHOACK = ?,"
                        + "ND_NHOMND = ?, ND_CAPBC = ?, ND_MADV = ?, ND_TTHAI = ?, ND_MACB = ?, ND_CHUCNANG = ? WHERE ND_MA = ?";
                ps = con.prepareStatement(sSql);
                ps.setString(1, user.getPriUserName());
                ps.setString(2, user.getPriAddress());
                ps.setString(3, user.getPriMobile());
                ps.setString(4, user.getPriOffice());
                ps.setString(5, encryptPwd);
                ps.setString(6, user.getPriPubKey());
                ps.setString(7, user.getPriUserGroup());
                ps.setString(8, user.getPriRptGrade());
                ps.setString(9, user.getPriPosCode());
                ps.setString(10, user.getPriStatus());
                ps.setString(11, user.getPriMaCanBo());
                ps.setString(12, user.getPriNhomCongViec());
                ps.setString(13, user.getPriUserCode());
            }
            updateRows = ps.executeUpdate();
        } catch (SQLException e) {
            CoreLogger.error(IMSRptDao.class.getClass() + " updateUser  -> " + e.getMessage());
        }
        return updateRows > 0;
    }

    //public static ArrayList<ListValue> getGroup
    public static String encryptPassword(String password) {
        try {
            String key = DefineFun.getKeyDes("1");
            DES crypt = new DES(key);
            String encryptKey = crypt.encrypt(password);
            return encryptKey;
        } catch (Exception ex) {
            Logger.getLogger(IMSRptDao.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    public static ArrayList<ListValue> getPeriodList() {
        ArrayList<ListValue> periodList;
        periodList = new ArrayList<>();
        String lcQuery = "SELECT KHOA_2,GIATRI FROM DMKHAC WHERE KHOA_1 = '08' AND TRANGTHAI = 'O'";
        String lcKey, lcDescript;
        try {
            Connection con;
            con = daoConnect.getConnect();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(lcQuery);
            while (rs.next()) {
                lcKey = rs.getString("GIATRI");
                lcDescript = rs.getString("KHOA_2");
                periodList.add(new ListValue(lcKey, lcDescript));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            //CoreLogger.error(IMSRptDao.class.getCanonicalName() + " getPassword  -> " + ex.getMessage());
            System.err.println("Loi --> Khong lay duoc danh muc ky bao cao");
        }
        return periodList;
    }

    /* Ham lay chuoi Pos_Code cua mot chi nhanh */
    public static String getSequenceStringOfSubPos(String main_pos) {
        String lcQuery, lcSequenceString = "/", lcPos_code;
        lcQuery = "select PO_MA from dmpos where po_macn = ? ORDER BY 1";
        try {
            Connection con;
            con = daoConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(lcQuery);
            ps.setString(1, main_pos);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lcPos_code = rs.getString("PO_MA");
                lcSequenceString += lcPos_code.trim() + "/";
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(IMSRptDao.class.getCanonicalName() + " getStringOfPos  -> " + ex.getMessage());
        }
        return lcSequenceString;
    }
    /* Ham lay danh sach menu id */

    public static ArrayList<ListValue> getMenuIdList() {
        ArrayList<ListValue> periodList;
        periodList = new ArrayList<>();
        String lcQuery = "SELECT MENUID,MENUID || ' - ' || DESCRIPTION AS DESCRIPT FROM MENU "
                + "WHERE PARENTID IS NOT NULL ORDER BY MENUID";
        String lcKey, lcDescript;
        try {
            Connection con;
            con = daoConnect.getConnect();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(lcQuery);
            while (rs.next()) {
                lcKey = String.valueOf(rs.getInt("MENUID"));
                lcDescript = rs.getString("DESCRIPT");
                periodList.add(new ListValue(lcKey, lcDescript));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            //CoreLogger.error(IMSRptDao.class.getCanonicalName() + " getPassword  -> " + ex.getMessage());
            System.err.println("Loi --> Khong lay duoc danh muc ky bao cao");
        }
        return periodList;
    }

    public static List<MenuItem> getMenuItemList() {
        ArrayList<MenuItem> lstOfMenu = new ArrayList<>();
        String lcQuery = "select MENUID,TEXT,DESCRIPTION,NVL(PARENTID,-1) PARENTID,NAVIGATEURL from menu ORDER BY 1";
        int ji_menuid, ji_mnparentid;
        String js_mntext, js_mndesc, js_mnurl;
        try {
            Connection con = daoConnect.getConnect();
            try (Statement ps = con.createStatement()) {
                ResultSet rs = ps.executeQuery(lcQuery);
                while (rs.next()) {
                    ji_menuid = rs.getInt("MENUID");
                    ji_mnparentid = rs.getInt("PARENTID");
                    js_mntext = rs.getString("TEXT");
                    js_mndesc = rs.getString("DESCRIPTION");
                    js_mnurl = rs.getString("NAVIGATEURL");
                    lstOfMenu.add(new MenuItem(ji_menuid, js_mntext, js_mndesc, ji_mnparentid, js_mnurl, 0));

                }
                rs.close();
            }
        } catch (SQLException ex) {
            //CoreLogger.error(IMSRptDao.class.getCanonicalName() + " getPassword  -> " + ex.getMessage());
            System.err.println("Loi --> Khong lay duoc danh muc menu");
        }
        return lstOfMenu;
    }

    public static List<ListValue> getPosList(String userName) {
        String userGroup = findUserGroup(userName);
        String lcQuery;
        Connection con = daoConnect.getConnect();
        ArrayList<ListValue> posList = new ArrayList<>();
        PreparedStatement ps;
        String lcPosCode, lcPosDescript;
        if (userGroup.equals("USRGRP01")) {
            try {
                lcQuery = "select po_ma,po_ma || ' - ' || po_ten po_ten from dmpos order by 1";
                ps = con.prepareStatement(lcQuery);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    lcPosCode = rs.getString("po_ma");
                    lcPosDescript = rs.getString("po_ten");
                    posList.add(new ListValue(lcPosCode, lcPosDescript));
                }
                rs.close();
                ps.close();
            } catch (SQLException ex) {
            }
        } else {
            try {
                lcQuery = "select po_ma,po_ma || ' - ' || po_ten po_ten from dmpos where po_macn = "
                        + "(select ND_MADV from ng_dung where nd_ma = ?) order by 1";
                ps = con.prepareStatement(lcQuery);
                ps.setString(1, userName);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    lcPosCode = rs.getString("po_ma");
                    lcPosDescript = rs.getString("po_ten");
                    posList.add(new ListValue(lcPosCode, lcPosDescript));
                }
                rs.close();
                ps.close();
            } catch (SQLException ex) {
            }
        }
        return posList;
    }

    public static String findUserGroup(String userName) {
        String lcQuery = "select ND_NHOMND from ng_dung where ND_MA = ?";
        String lcUserGroup = "";
        try {
            Connection con;
            con = daoConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(lcQuery);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lcUserGroup = rs.getString("ND_NHOMND");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return lcUserGroup;
    }

    public static String findPrivilegeByUsrGrp(String userGroup) {
        String lcQuery = "select NHOM_QUYEN from nhom_ngd where ND_NHOM = ?";
        String lcPrivilege = "";
        try {
            Connection con;
            con = daoConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(lcQuery);
            ps.setString(1, userGroup);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lcPrivilege = rs.getString("NHOM_QUYEN");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return lcPrivilege;
    }

    public static String findPrivilegeByUsr(String userName) {
        System.err.println("findPrivilegeByUsr~"+userName);
        String lcQuery = "select NHOM_QUYEN from nhom_ngd where ND_NHOM "
                + "= (select ND_NHOMND from NG_DUNG WHERE ND_MA = ? )";
        String lcPrivilege = "";
        try {
            Connection con;
            con = daoConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(lcQuery);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lcPrivilege = rs.getString("NHOM_QUYEN");
            }
            System.err.println("lcPrivilege~"+lcPrivilege);
            rs.close();
            ps.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return lcPrivilege;
    }

    public static List<ListValue> getUserGroupList(String userName) {
        String userGroup = findUserGroup(userName);
        String groupPrivilege = findPrivilegeByUsrGrp(userGroup);
        ArrayList<ListValue> groupPrivilegeList = new ArrayList<>();
        if (!groupPrivilege.isEmpty()) {
            String lcQuery;
            Connection con = daoConnect.getConnect();
            PreparedStatement ps;
            String lcUsrGroup, lcGroupDescript;
            try {
                lcQuery = "select ND_NHOM,ND_NHOM || ' - ' || ND_MOTA ND_MOTA from nhom_ngd "
                        + " where instr(?,ND_NHOM) > 0 order by 1";
                ps = con.prepareStatement(lcQuery);
                ps.setString(1, groupPrivilege);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        lcUsrGroup = rs.getString("ND_NHOM");
                        lcGroupDescript = rs.getString("ND_MOTA");
                        groupPrivilegeList.add(new ListValue(lcUsrGroup, lcGroupDescript));
                    }
                }
                ps.close();
            } catch (SQLException ex) {
            }
        } else {
            groupPrivilegeList.add(new ListValue("ERROR", "ERROR"));
        }
        return groupPrivilegeList;
    }
    /* */

    public static ArrayList<VbspNews> getVbspNewsList() {
        ArrayList<VbspNews> vbspNewsList = new ArrayList<>();
        vbspNewsList.add(new VbspNews(0, "<< Thêm mới bản tin >>", ""));
        String lcQuery;

        lcQuery = "select id, title, message from vbsp_news";

        int linewid;
        String lctitle, lcmessage;
        try {
            Connection con = daoConnect.getConnect();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(lcQuery);
            while (rs.next()) {
                linewid = rs.getInt("id");
                lctitle = rs.getString("title");
                lcmessage = rs.getString("message");
                vbspNewsList.add(new VbspNews(linewid, lctitle, lcmessage));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println("Loi --> Khong lay duoc danh muc tin tuc");
        }
        return vbspNewsList;
    }

    public static boolean addVbspNews(String title, String content, String userName) {
        int insertedRows = 0;
        try {
            Connection con = daoConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO VBSP_NEWS (ID,TITLE,MESSAGE,MAKER_ID,MAKER_DATE,REC_ST) "
                    + "VALUES ((SELECT NVL(MAX(ID),0) + 1 FROM VBSP_NEWS), ? , ? , ? , SYSDATE, 'O')");
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setString(3, userName);
            insertedRows = ps.executeUpdate();
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {

        }
        return insertedRows > 0;
    }

    public static boolean updateVbspNews(int newsId, String title, String content, String userName) {
        int updatedRows = 0;
        try {
            Connection con = daoConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE VBSP_NEWS SET TITLE = ?, MESSAGE = ?, MAKER_DATE = SYSDATE "
                    + "WHERE ID = ?");
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setInt(3, newsId);
            updatedRows = ps.executeUpdate();
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {

        }
        return updatedRows > 0;
    }

    public static boolean deleteVbspNews(int newsId) {
        int deletedRows = 0;
        try {
            Connection con = daoConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM VBSP_NEWS WHERE ID = ? ");
            ps.setInt(1, newsId);
            deletedRows = ps.executeUpdate();
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            return false;
        }
        return deletedRows > 0;
    }

    /*Hàm lấy thông điệp ngẫu nhiên*/
    public static String randomMessage() {
        String lcMessage = "", lcQuery;

        lcQuery = "SELECT MESSAGE FROM INTELLECT.VBSP_NEWS "
                + "    WHERE ID = round (DBMS_RANDOM.VALUE (1, (select count(*) from intellect.vbsp_news )))";

        try {
            Connection con = daoConnect.getConnect();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(lcQuery);
            while (rs.next()) {
                lcMessage = rs.getString("MESSAGE");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println("Loi --> Khong lay duoc danh muc tin tuc");
        }
        return lcMessage;
    }

    /*Hàm lấy mã Pos từ ID của cây Treeview*/
    public static String getPosbyTreeId(int treeId) {
        String lcPosCode = "999999", lcQuery;
        lcQuery = "SELECT NVL(POS_CODE,'999999') POS_CODE FROM PROVINCE WHERE ID = " + treeId;

        try {
            Connection con = daoConnect.getConnect();
            try (Statement ps = con.createStatement();
                    ResultSet rs = ps.executeQuery(lcQuery)) {
                while (rs.next()) {
                    lcPosCode = rs.getString("POS_CODE");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Loi --> Khong lay duoc danh muc tin tuc");
        }
        return lcPosCode;
    }
    
    public ArrayList<ReportUserGroup> getListOfReportByUserGroup(String userGroup) {
        ArrayList<ReportUserGroup> lstOfReport = new ArrayList<>();    
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        String strStoreproce = "{call app_priv_view.p_get_report_priv(?,?)}";
        ResultSet reset = null;
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            calstatement.setString(1, userGroup);                
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();                
            reset = (ResultSet) calstatement.getObject(2);
            while (reset.next()) {

                ReportUserGroup value = new ReportUserGroup();
                value.setNhomND(reset.getString(1));
                value.setNhomBC(reset.getString(2));
                value.setMaBC(reset.getString(3));
                value.setTenBC(reset.getString(4));
                value.setQuyenTC(reset.getInt(5));

                lstOfReport.add(value);
            }

            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getListOfReportByUserGroup -> " + e.getMessage());
        }   
        return lstOfReport;
    }
    
    public String updateGroupOwnerListReport(String userGroup, List<String> reportList) 
    {
        String error_message;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = daoconnect.getConnect();
            CallableStatement calstatement;
            String strStoreproce = "{call app_priv_view.p_set_report_priv(?,?,?)}";
            String[] arrayReports ;
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("ARRAY_TABLE", conn);
            ARRAY oracle_arrayReports;
            if (reportList != null){                
                arrayReports = reportList.toArray(new String[0]);
                oracle_arrayReports = new ARRAY(des, conn, arrayReports);
            } else {
                oracle_arrayReports = new ARRAY(des, conn, null);
            }
            
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, userGroup);
            calstatement.setArray(2, oracle_arrayReports);        
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            error_message = (String)calstatement.getObject(3);
            
        } catch (SQLException ex) {
            Logger.getLogger(IMSRptDao.class.getName()).log(Level.SEVERE, null, ex);
            error_message = ex.getMessage();
        }
        
        return error_message;
    }
            
}
