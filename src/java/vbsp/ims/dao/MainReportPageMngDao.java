/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ReportListValue;
import vbsp.ims.model.ReportUserGroup;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class MainReportPageMngDao {

    private final DaoConnect daoConnect;
    private final int menuId;

    public MainReportPageMngDao() {
        this.menuId = 0;
        daoConnect = new DaoConnect();
    }

    public MainReportPageMngDao(int menuId) {
        this.menuId = menuId;
        daoConnect = new DaoConnect();
    }

    public ArrayList<ListValue> getListOfReportGroup() {
        ArrayList<ListValue> lstOfRptGroup = new ArrayList<>();
        String lcQuery, lcKey, lcValue;
        lcQuery = "SELECT A.DM_NHOMBC,A.DM_MOTA FROM DMBC A, MENU_NHOMBC B "
                + "WHERE A.DM_NHOMBC = B.NHOMBC AND A.APPLY_FLG = 'Y' AND "
                + "B.TRANGTHAI = 'A' AND MENUID = " + String.valueOf(menuId)
                + " ORDER BY 1";
        try {
            Connection con = daoConnect.getConnect();
            Statement lcStatement;
            lcStatement = con.createStatement();
            ResultSet rs = lcStatement.executeQuery(lcQuery);
            while (rs.next()) {
                lcKey = rs.getString("DM_NHOMBC");
                lcValue = rs.getString("DM_MOTA");
                lstOfRptGroup.add(new ListValue(lcKey, lcValue));
            }
            rs.close();
            lcStatement.close();
            con.close();
        } catch (SQLException ex) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " getUsers  -> " + ex.getMessage());
        }
        return lstOfRptGroup;
    }

    public ArrayList<ReportListValue> getListOfReport() {
        ArrayList<ReportListValue> lstOfRptGroup = new ArrayList<>();
        String lcQuery, lcKey, lcValue, lcReportGroup, lcReportGrade;
        lcQuery = "SELECT A.DM_NHOMBC,A.DM_MABC,A.DM_TENVT || ' - ' || A.DM_MOTA DM_MOTA,DM_CAPBC "
                + "FROM DMBC_CT A,MENU_NHOMBC B "
                + "WHERE A.DM_NHOMBC = B.NHOMBC AND A.APPLY_FLG = 'Y' AND "
                + "B.TRANGTHAI = 'A' AND MENUID = " + String.valueOf(menuId) + " ORDER BY DM_MOTA";
        try {
            try (Connection con = daoConnect.getConnect()) {
                Statement lcStatement;
                lcStatement = con.createStatement();
                try (ResultSet rs = lcStatement.executeQuery(lcQuery)) {
                    while (rs.next()) {
                        lcReportGroup = rs.getString("DM_NHOMBC");
                        lcKey = rs.getString("DM_MABC");
                        lcValue = rs.getString("DM_MOTA");
                        lcReportGrade = rs.getString("DM_CAPBC");
                        lstOfRptGroup.add(new ReportListValue( lcReportGroup,
                                new ListValue(lcKey, lcValue),lcReportGrade) );
                    }
                }
                lcStatement.close();
            }
        } catch (SQLException ex) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " getUsers  -> " + ex.getMessage());
        }
        return lstOfRptGroup;
    }
    
    public ArrayList<ReportListValue> getListOfReport_Group(String groupId, String userGroup) {
        ArrayList<ReportListValue> lstOfRptGroup = new ArrayList<>();
        String lcQuery, lcKey, lcValue, lcReportGroup, lcReportGrade;
        lcQuery = "SELECT A.DM_NHOMBC,A.DM_MABC,A.DM_TENVT || ' - ' || A.DM_MOTA DM_MOTA,DM_CAPBC, "
                + "app_priv_view.f_get_user_group_report_priv('" + userGroup + "', A.DM_MABC) as QUYEN_TC "
                + "FROM DMBC_CT A,MENU_NHOMBC B "
                + "WHERE A.DM_NHOMBC = B.NHOMBC AND A.APPLY_FLG = 'Y' AND "
                + "B.TRANGTHAI = 'A' AND MENUID = " + String.valueOf(menuId) 
                + " AND A.DM_NHOMBC='" + groupId + "' "
                + " ORDER BY DM_MOTA";
        try {
            try (Connection con = daoConnect.getConnect()) {
                Statement lcStatement;
                lcStatement = con.createStatement();
                try (ResultSet rs = lcStatement.executeQuery(lcQuery)) {
                    while (rs.next()) {
                        int i_quyen_tc = rs.getInt("QUYEN_TC");
                        lcReportGroup = rs.getString("DM_NHOMBC");
                        lcKey = rs.getString("DM_MABC");
                        lcValue = rs.getString("DM_MOTA");
                        lcReportGrade = rs.getString("DM_CAPBC");
                        if (i_quyen_tc == 1) {                            
                            lstOfRptGroup.add(new ReportListValue( lcReportGroup,
                                    new ListValue(lcKey, lcValue),lcReportGrade) );
                        }
                    }
                }
                lcStatement.close();
            }
        } catch (SQLException ex) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " getUsers  -> " + ex.getMessage());
        }
        return lstOfRptGroup;
    }
    
    public ArrayList<ListValue> getListOfReport(String sub_groupid) {
        ArrayList<ListValue> lstOfRptGroup = new ArrayList<>();               
        
        String lcQuery, lcKey, lcValue;
        lcQuery = "SELECT A.DM_MABC,A.DM_MOTA FROM DMBC_CT A,MENU_NHOMBC B "
                + "WHERE A.DM_NHOMBC = B.NHOMBC AND A.APPLY_FLG = 'Y' AND "
                + "B.TRANGTHAI = 'A' AND MENUID = " + String.valueOf(menuId)                 
                + " ORDER BY DM_MABC";
        try {
            Connection con = daoConnect.getConnect();
            Statement lcStatement;
            lcStatement = con.createStatement();
            ResultSet rs = lcStatement.executeQuery(lcQuery);
            while (rs.next()) {
                lcKey = rs.getString("DM_MABC");
                lcValue = rs.getString("DM_MOTA");
                lstOfRptGroup.add(new ListValue(lcKey, lcValue));
            }
            rs.close();
            lcStatement.close();
            con.close();
        } catch (SQLException ex) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " getUsers  -> " + ex.getMessage());
        }
        return lstOfRptGroup;
    }       
}
