/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.tlsl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class ReportDao {

    private DaoConnect daoConnect;
    private Connection conn;

    public ReportDao() {
//        daoConnect = new DaoConnect();  
    }

    public List<Report> list_report(String pv_mod_cd) {
        ArrayList<Report> reports = new ArrayList<>();
        String strStoreproce
                = "{call rpt_bctlsl.list_report(?, ? , ?)}";
        try {
//            if (conn == null) {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
//            }
            ResultSet rs;
            try (CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                calstatement.setString(1, pv_mod_cd);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                rs = (ResultSet) calstatement.getObject(3);
                String lv_group;
                int ln_no;
                String lv_name;
                while (rs.next()) {
                    lv_group = rs.getString("GROUP");
                    ln_no = rs.getInt("NO");
                    lv_name = rs.getString("NAME");
                    reports.add(new Report(lv_group, ln_no, lv_name));
                }
                rs.close();
            } catch (SQLException sql_error) {
                System.out.println("Error when rpt_bctlsl.list_report ~ "
                        + sql_error.getMessage());
            } finally {
                conn.close();
                return reports;
            }
        } catch (Exception other_error) {
            System.err.println("Error when rpt_bctlsl.list_report --> " + other_error.getMessage());
            return null;
        }
    }

    public List<ListValue> list_group() {
        ArrayList<ListValue> groups = new ArrayList<>();
        groups.add(new ListValue("KTSL", "KTSL - Báo cáo khai thác số liệu"));
        return groups;
    }

    public ExportInfor get_jasper(String pv_group, String pv_no) {
        ExportInfor exportInfor = new ExportInfor();
        String strStoreproce
                = "{call rpt_bctlsl.get_jasper(?, ? , ?)}";
        try {
//            if (conn == null) {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
//            }
            ResultSet rs;
            try (CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                calstatement.setString(1, pv_group);
                calstatement.setString(2, pv_no);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                rs = (ResultSet) calstatement.getObject(3);
                String lv_jasper_file = "", lv_program = "";
                while (rs.next()) {
                    lv_jasper_file = rs.getString("JASPER_FILE");
                    lv_program = rs.getString("PROGRAM");
                }
                exportInfor.setJasper_name(lv_jasper_file);
                exportInfor.setProgram(lv_program);
                rs.close();
            } catch (SQLException sql_error) {
                System.out.println("Error when rpt_bctlsl.list_report ~ "
                        + sql_error.getMessage());
            } finally {
                conn.close();
            }
        } catch (Exception other_error) {
            System.err.println("Error when rpt_bctlsl.lv_jasper_file --> " + other_error.getMessage());

        }
        return exportInfor;
    }
}
