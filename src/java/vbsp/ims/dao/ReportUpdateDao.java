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
import vbsp.ims.jasper.JasperParam;
import vbsp.ims.model.ReportInfor;
import vbsp.ims.model.ReportListValue;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class ReportUpdateDao {

    private DaoConnect daoConnect;
    private Connection con;

    public ReportUpdateDao() {
//        daoConnect = new DaoConnect();
    }

    public boolean addReport(ReportInfor reportInfor) {
        int insertedRows = 0;

        try {

            daoConnect = new DaoConnect();
            con = daoConnect.getConnect();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO dmbc_ct (DM_MABC,DM_NHOMBC,DM_TENVT,DM_MOTA,DM_DONVI,DM_KYBC,"
                    + "DM_PLOAI,DM_CAPBC,APPLY_FLG,DM_JASPERFILE,DM_TENFILE) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, reportInfor.getReportCode());
            ps.setString(2, reportInfor.getReportGroupCode());
            ps.setString(3, reportInfor.getShortcutName());
            ps.setString(4, reportInfor.getDescript());
            ps.setInt(5, reportInfor.getReportUnit());
            ps.setString(6, reportInfor.getReportTerm());
            ps.setString(7, reportInfor.getReportType());
            ps.setString(8, reportInfor.getReportGrade());
            ps.setString(9, "Y");
            ps.setString(10, reportInfor.getJaserFileName());
            ps.setString(11, reportInfor.getGeneratedName());
            insertedRows = ps.executeUpdate();
            for (JasperParam param : reportInfor.getParam()) {
                ps = con.prepareStatement(
                        "INSERT INTO dmbc_tso (DM_MABC,DM_TENTRUONG,DM_LOAITSO,DM_MOTA,DM_STT,DM_BANGTC,"
                        + "DM_COTHIENTHI,DM_COTTSO,DM_DKLOC,APPLY_FLG,DM_DKSAPXEP) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, reportInfor.getReportCode());
                ps.setString(2, param.getParamName());
                ps.setString(3, param.getParamType());
                ps.setString(4, param.getParamDescript());
                ps.setInt(5, param.getOrder());
                ps.setString(6, param.getRefTable());
                ps.setString(7, param.getDisplayColumn());
                ps.setString(8, param.getParamColumn());
                ps.setString(9, param.getFilterCondition());
                ps.setString(10, "Y");
                ps.setString(11, param.getOrderCondition());
                insertedRows = ps.executeUpdate();
            }
            //if(rs != null) rs.close();
            if (ps != null) {
                ps.close();
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportUpdateDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return insertedRows > 0;
    }
    /* Hàm thực hiện cập nhật thông tin báo cáo */

    public boolean updateReport(ReportInfor reportInfor) {
        int updatedRows = 0;

        try {

            daoConnect = new DaoConnect();
            con = daoConnect.getConnect();

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE dmbc_ct SET DM_TENVT=?,DM_MOTA=?,DM_DONVI=?,DM_KYBC=?,"
                    + "DM_PLOAI=?,DM_CAPBC=?,APPLY_FLG=?,DM_JASPERFILE=?,DM_TENFILE=? "
                    + "WHERE DM_MABC = ?");
            ps.setString(1, reportInfor.getShortcutName());
            ps.setString(2, reportInfor.getDescript());
            ps.setInt(3, reportInfor.getReportUnit());
            ps.setString(4, reportInfor.getReportTerm());
            ps.setString(5, reportInfor.getReportType());
            ps.setString(6, reportInfor.getReportGrade());
            ps.setString(7, "Y");
            ps.setString(8, reportInfor.getJaserFileName());
            ps.setString(9, reportInfor.getGeneratedName());
            ps.setString(10, reportInfor.getReportCode());
            updatedRows = ps.executeUpdate();
            /* Phần xoá tham số trước khi insert mới vào*/
            ps = con.prepareStatement(
                    "DELETE FROM dmbc_tso WHERE DM_MABC = ?");
            ps.setString(1, reportInfor.getReportCode());
            updatedRows = ps.executeUpdate();
            /* Phần insert tham số */
            for (JasperParam param : reportInfor.getParam()) {
                ps = con.prepareStatement(
                        "INSERT INTO dmbc_tso (DM_MABC,DM_TENTRUONG,DM_LOAITSO,DM_MOTA,DM_STT,DM_BANGTC,"
                        + "DM_COTHIENTHI,DM_COTTSO,DM_DKLOC,APPLY_FLG,DM_DKSAPXEP) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, reportInfor.getReportCode());
                ps.setString(2, param.getParamName());
                ps.setString(3, param.getParamType());
                ps.setString(4, param.getParamDescript());
                ps.setInt(5, param.getOrder());
                ps.setString(6, param.getRefTable());
                ps.setString(7, param.getDisplayColumn());
                ps.setString(8, param.getParamColumn());
                ps.setString(9, param.getFilterCondition());
                ps.setString(10, "Y");
                ps.setString(11, param.getOrderCondition());
                updatedRows = ps.executeUpdate();
//                ps = con.prepareStatement(
//                        "UPDATE dmbc_tso SET DM_LOAITSO=?,DM_MOTA=?,DM_STT=?,DM_BANGTC=?,"
//                        + "DM_COTHIENTHI=?,DM_COTTSO=?,DM_DKLOC=?,APPLY_FLG=?,DM_DKSAPXEP=? "
//                        + "WHERE DM_MABC = ? AND DM_TENTRUONG=?");
//                ps.setString(1, param.getParamType());
//                ps.setString(2, param.getParamDescript());
//                ps.setInt(3, param.getOrder());
//                ps.setString(4, param.getRefTable());
//                ps.setString(5, param.getDisplayColumn());
//                ps.setString(6, param.getParamColumn());
//                ps.setString(7, param.getFilterCondition());
//                ps.setString(8, "Y");
//                ps.setString(9, param.getOrderCondition());
//                ps.setString(10, reportInfor.getReportCode());
//                ps.setString(11, param.getParamName());
//                updatedRows = ps.executeUpdate();
            }
            //if(rs != null) rs.close();
            if (ps != null) {
                ps.close();
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportUpdateDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return updatedRows > 0;
    }

    /* Hàm thực hiện lấy nhóm báo cáo */
    public ArrayList<ListValue> getReportGroupList() {

        ArrayList<ListValue> rptGroupList;
        rptGroupList = new ArrayList();

        String lcQuery = "SELECT DM_NHOMBC,DM_NHOMBC || ' - ' || DM_MOTA AS DM_MOTA "
                + "FROM DMBC WHERE APPLY_FLG = 'Y' ORDER BY 1";
        String lcKey, lcDescript;

        try {

            daoConnect = new DaoConnect();
            con = daoConnect.getConnect();

            Statement ps = con.createStatement();

            ResultSet rs = ps.executeQuery(lcQuery);

            while (rs.next()) {
                lcKey = rs.getString("DM_NHOMBC");
                lcDescript = rs.getString("DM_MOTA");
                rptGroupList.add(new ListValue(lcKey, lcDescript));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println("Loi --> Khong lay duoc danh muc nhom bao cao");
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReportUpdateDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rptGroupList;
    }
    /* Hàm thực hiện lấy mã báo cáo cho phần cài đặt báo cáo */

    public static String getNextReportId(String reportGroup) {
        String lcNextReportId = "";
        DaoConnect dc = new DaoConnect();
        Connection connection = dc.getConnect();
        try {
            String sql = "{ ? = call f_get_next_report_id(?) }";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(2, reportGroup);
            statement.registerOutParameter(1, java.sql.Types.VARCHAR);
            statement.execute();
            lcNextReportId = statement.getString(1);
            statement.close();
        } catch (SQLException e) {
            System.err.println("getNextReportId" + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReportUpdateDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lcNextReportId;
    }
    /* Hàm thực hiện lấy nhóm báo cáo */

    public ArrayList<ReportListValue> getReportList(String reportGroup) {

        ArrayList<ReportListValue> reportList = new ArrayList();
        reportList.add(new ReportListValue(
                "ADDNEW",
                new ListValue("ADDNEW", "<b>Thêm mới báo cáo</b>")));

        String lcQuery;

        if (reportGroup.isEmpty()) {
            lcQuery = "SELECT DM_NHOMBC,DM_MABC,DM_MABC || ' - ' || DM_TENVT || ' < ' || "
                    + "DECODE(APPLY_FLG,'Y','Hoạt động >','Vô hiệu >') AS DM_MOTA "
                    + "FROM DMBC_CT ORDER BY 1,2";
        } else {
            lcQuery = "SELECT DM_NHOMBC,DM_MABC,DM_MABC || ' - ' || DM_TENVT || ' < ' || "
                    + "DECODE(APPLY_FLG,'Y','Hoạt động >','Vô hiệu >') AS DM_MOTA "
                    + "FROM DMBC_CT WHERE DM_NHOMBC = '" + reportGroup + "' ORDER BY 1,2";
        }
        String lcKey, lcDescript, lcReportGroup;
        try {

            daoConnect = new DaoConnect();
            con = daoConnect.getConnect();

            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(lcQuery);
            while (rs.next()) {
                lcReportGroup = rs.getString("DM_NHOMBC");
                lcKey = rs.getString("DM_MABC");
                lcDescript = rs.getString("DM_MOTA");
                reportList.add(new ReportListValue(lcReportGroup, new ListValue(lcKey, lcDescript)));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println("Loi --> Khong lay duoc danh muc nhom bao cao");
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReportUpdateDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return reportList;
    }

    /* Hàm thực hiện chức năng lấy thông tin một báo cáo*/
    public ReportInfor getReportInfor(String reportCode) {

        ReportInfor reportInfor = new ReportInfor();
        reportInfor.setReportCode(reportCode);

        String lcReportGroup, lcShortcutName, lcDescript, lcReportTerm, lcReportType,
                lcReportGrade, lcJasperFile, lcFileName;
        int lcReportUnit;

        try {

            daoConnect = new DaoConnect();
            con = daoConnect.getConnect();

            /* Phần lấy thông tin cơ bản */
            String lcQuery = "SELECT DM_NHOMBC,DM_TENVT,DM_MOTA,DM_DONVI,DM_KYBC,DM_PLOAI,DM_CAPBC,DM_JASPERFILE,DM_TENFILE FROM DMBC_CT "
                    + "WHERE DM_MABC = ? ORDER BY 1,2";
            PreparedStatement ps = con.prepareStatement(lcQuery);
            ps.setString(1, reportCode);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lcReportGroup = rs.getString("DM_NHOMBC");
                lcShortcutName = rs.getString("DM_TENVT");
                lcDescript = rs.getString("DM_MOTA");
                lcReportUnit = rs.getInt("DM_DONVI");
                lcReportTerm = rs.getString("DM_KYBC");
                lcReportType = rs.getString("DM_PLOAI");
                lcReportGrade = rs.getString("DM_CAPBC");
                lcJasperFile = rs.getString("DM_JASPERFILE");
                lcFileName = rs.getString("DM_TENFILE");
                reportInfor.setReportGroupCode(lcReportGroup);
                reportInfor.setShortcutName(lcShortcutName);
                reportInfor.setDescript(lcDescript);
                reportInfor.setReportUnit(lcReportUnit);
                reportInfor.setReportTerm(lcReportTerm);
                reportInfor.setReportType(lcReportType);
                reportInfor.setReportGrade(lcReportGrade);
                reportInfor.setJaserFileName(lcJasperFile);
                reportInfor.setGeneratedName(lcFileName);
            }
            /* Phần lấy thông tin tham số */
            ArrayList<JasperParam> param = new ArrayList<>();
            String lcParamName, lcParamType, lcParamDescript, lcParamRefTable, lcDisplayColumn, lcParamColumn,
                    lcFilterCondition, lcOrderCondition;
            int lcParamOrder;
            lcQuery = "SELECT DM_TENTRUONG,DM_LOAITSO,DM_MOTA,DM_STT,DM_BANGTC,DM_COTHIENTHI,DM_COTTSO,DM_DKLOC,DM_DKSAPXEP FROM DMBC_TSO "
                    + "WHERE DM_MABC = ? ORDER BY DM_STT";
            ps = con.prepareStatement(lcQuery);
            ps.setString(1, reportCode);
            rs = ps.executeQuery();
            while (rs.next()) {
                lcParamName = rs.getString("DM_TENTRUONG");
                lcParamType = rs.getString("DM_LOAITSO");
                lcParamDescript = rs.getString("DM_MOTA");
                lcParamOrder = rs.getInt("DM_STT");
                lcParamRefTable = rs.getString("DM_BANGTC");
                lcDisplayColumn = rs.getString("DM_COTHIENTHI");
                lcParamColumn = rs.getString("DM_COTTSO");
                lcFilterCondition = rs.getString("DM_DKLOC");
                lcOrderCondition = rs.getString("DM_DKSAPXEP");
                param.add(new JasperParam(lcParamName, lcParamType, lcParamDescript, lcParamOrder,
                        lcParamRefTable, lcDisplayColumn, lcParamColumn, lcFilterCondition, lcOrderCondition));
            }
            reportInfor.setParam(param);
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println("getReportInfor" + ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReportUpdateDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return reportInfor;
    }
    /* Hàm thực hiện chức năng disable một báo cáo*/

    public boolean updateReportStatus(String reportCode, String value) {
        int updateRows = 0;
        try {

            daoConnect = new DaoConnect();
            con = daoConnect.getConnect();

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE dmbc_ct SET APPLY_FLG = ? WHERE DM_MABC = ?");
            ps.setString(1, value);
            ps.setString(2, reportCode);
            updateRows = ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error on update " + reportCode);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReportUpdateDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return updateRows > 0;
    }

    /* Hàm thực hiện chức năng lấy thông tin hỗ trợ cho tham số báo cáo*/
    public void suggest(List<JasperParam> params) {
        for (JasperParam param : params) {
            takeParamInfor(param);
        }
    }

    private void takeParamInfor(JasperParam param) {
        try {

            daoConnect = new DaoConnect();
            con = daoConnect.getConnect();

            String lcParamType, lcParamDescript, lcParamRefTable, lcDisplayColumn, lcParamColumn,
                    lcFilterCondition, lcOrderCondition;
            int lcParamOrder;
            String lcQuery = "SELECT DM_LOAITSO,DM_MOTA,DM_STT,DM_BANGTC,DM_COTHIENTHI,DM_COTTSO"
                    + ",DM_DKLOC,DM_DKSAPXEP FROM DMBC_TSO "
                    + "WHERE DM_TENTRUONG like ? AND ROWNUM = 1";

            PreparedStatement ps = con.prepareStatement(lcQuery);
            ps.setString(1, param.getParamName() + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lcParamType = rs.getString("DM_LOAITSO");
                lcParamDescript = rs.getString("DM_MOTA");
                lcParamOrder = rs.getInt("DM_STT");
                lcParamRefTable = rs.getString("DM_BANGTC");
                lcDisplayColumn = rs.getString("DM_COTHIENTHI");
                lcParamColumn = rs.getString("DM_COTTSO");
                lcFilterCondition = rs.getString("DM_DKLOC");
                lcOrderCondition = rs.getString("DM_DKSAPXEP");
                param.setInfor(lcParamType, lcParamDescript, lcParamOrder,
                        lcParamRefTable, lcDisplayColumn, lcParamColumn, lcFilterCondition, lcOrderCondition);
            }

            rs.close();
        } catch (Exception ex) {
            System.err.println("takeParamInfor error~" + ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReportUpdateDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
