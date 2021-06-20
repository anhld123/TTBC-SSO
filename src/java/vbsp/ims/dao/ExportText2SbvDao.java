/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import vbsp.ims.io.ExportFile2Sbv;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.Indicator;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class ExportText2SbvDao {

    //--------------------------------------------------------------------------
    private DaoConnect daoConnect;
    private Connection con;

    //--------------------------------------------------------------------------
    public ExportText2SbvDao() {
//        daoConnect = new DaoConnect();
//        if (con == null)
//            con = daoConnect.getConnect();
    }

    //--------------------------------------------------------------------------
    public List<ListValue> getExportGroupReport(String username, int reportGrade) {       
        ArrayList<ListValue> groupList = new ArrayList<>();
        
        String lcNhombc, lcMota;
        String lcQuery;
        
        if (username.toUpperCase().equals("ALL") || reportGrade == -1) {
            lcQuery = "SELECT DM_MABC,DM_TENVT|| '-' ||DM_MOTA AS DM_MOTA "
                    + " FROM DMBC_EX2SBV WHERE APPLY_FLG = 'Y' ORDER BY 1";
        } else {
            lcQuery = "SELECT DM_MABC,DM_TENVT||'-'||DM_MOTA AS DM_MOTA "
                    + " FROM DMBC_EX2SBV WHERE APPLY_FLG = 'Y' AND INSTR(DM_CAPBC,'"+ String.valueOf(reportGrade) + "') > 0 "
                    + " ORDER BY 1";
        }
        try {            
            Statement lcStatement;            
            daoConnect = new DaoConnect();
            con = daoConnect.getConnect();
            lcStatement = con.createStatement();
            ResultSet rs = lcStatement.executeQuery(lcQuery);
            
            while (rs.next()) {
                lcNhombc = rs.getString("DM_MABC");
                lcMota = rs.getString("DM_MOTA");
                groupList.add(new ListValue(lcNhombc, lcMota));
            }            
            
            lcStatement.close();            
            if (!con.isClosed())
                    con.close();
            return groupList;
        } catch (SQLException ex) {
            CoreLogger.error(ExportText2SbvDao.class.getCanonicalName() + " getExportGroupReport  -> " + ex.getMessage() 
            + "~" + lcQuery);                        
            return groupList;
        }         
    }

    //--------------------------------------------------------------------------
    public List<ListValue> getExportPeriod(String report) {

        daoConnect = new DaoConnect();
        con = daoConnect.getConnect();

        ArrayList<ListValue> periodList = new ArrayList<>();

        String lcQuery = "SELECT DM_KYBC FROM dmbc_ex2sbv WHERE DM_MABC = '"
                + report.trim() + "'";

        String periodStr = "/D/10D/15D/M/Q/A/";

        try {
            //Connection con = daoConnect.getConnect();
            Statement lcStatement;
            lcStatement = con.createStatement();
            try (ResultSet rs = lcStatement.executeQuery(lcQuery)) {
                while (rs.next()) {
                    periodStr = rs.getString("DM_KYBC");
                }
            }
            lcStatement.close();
            if (!periodStr.isEmpty()) {
                String[] periods = periodStr.split("/");
                for (String period : periods) {
                    if (!period.trim().isEmpty()) {
                        periodList.add(new ListValue(period, getPeriodDescript(period)));
                    }
                }
            }
        } catch (SQLException ex) {
            CoreLogger.error(IMSRptDao.class.getCanonicalName() + " getExportGroupReport  -> " + ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExportText2SbvDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return periodList;
    }

    //--------------------------------------------------------------------------
    private String getPeriodDescript(String period) {
        String descript;
        switch (period) {
            case "D":
                descript = "Ngày";
                break;
            case "10D":
                descript = "3Kỳ/Tháng";
                break;
            case "15D":
                descript = "2Kỳ/Tháng";
                break;
            case "M":
                descript = "Tháng";
                break;
            case "Q":
                descript = "Quý";
                break;
            case "A":
                descript = "Năm";
                break;
            default:
                descript = "Tất cả";
                break;
        }
        return descript;
    }

    //--------------------------------------------------------------------------
    public String getDataExportFile(String p_Report, String p_PosCode, String p_conFlag,
            String p_Date, String p_Period, String sbvSendIndiGroup, String p_pathFile) {
        String strFullName = "";
        String lc_pathFile = p_pathFile.trim();

         if (!p_pathFile.endsWith("/") || !p_pathFile.endsWith("\\")) {
            p_pathFile += "/";
        }
        try {
            lc_pathFile = p_pathFile.replace("/", "\\");
            lc_pathFile = lc_pathFile.replace("\\\\", "\\");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        daoConnect = new DaoConnect();
        con = daoConnect.getConnect();

        try {
            //Connection conn = daoConnect.getConnect();
            CallableStatement calstatement;
            String strStoreproce = "{call vbsp_rpt_exporter.p_export2sbv(?, ?, ?, ?, ? , ?, ? , ?)}";
            ResultSet reset;
            try {
                //Khoi tao goi store
                calstatement = con.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos    
                calstatement.setString(1, p_Report);
                calstatement.setString(2, p_PosCode);
                calstatement.setString(3, p_conFlag);
                calstatement.setString(4, p_Date);
                calstatement.setString(5, p_Period);
                calstatement.setString(6, sbvSendIndiGroup);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(7);
                //Lay du lieu ra file name
                strFullName = lc_pathFile + calstatement.getString(8);
                ExportFile2Sbv export = new ExportFile2Sbv();
                export.ExportFile(p_Report, p_Period, strFullName, reset);
                if (reset != null) {
                    reset.close();
                }
                calstatement.close();
            } catch (SQLException e) {
                System.err.println("Loi trong ham getDataExportFile " + e.getMessage());
                CoreLogger.error(DaoExportHstdct.class.getCanonicalName()
                        + " getDataExportFile -> " + e.getMessage());
            }
        } catch (IOException | InvalidFormatException e) {
            System.err.println("Loi trong ham getDataExportFile " + e.getMessage());
            CoreLogger.error(DaoExportHstdct.class.getCanonicalName()
                    + " getDataExportFile -> " + e.getMessage());

        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExportText2SbvDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return strFullName;
    }

    // Thu tuc xuat text file thuyet minh chi tieu SBV
    public String getSbvTextFile_TM(String p_Report, String p_PosCode, String p_conFlag,
            String p_Date, String p_Period, String sbvSendIndiGroup, String p_pathFile) {

        daoConnect = new DaoConnect();
        con = daoConnect.getConnect();

        String strFullName = "";
        String lc_pathFile = p_pathFile.trim();

        if (!p_pathFile.endsWith("/") || !p_pathFile.endsWith("\\")) {
            p_pathFile += "/";
        }

        try {
            lc_pathFile = p_pathFile.replace("/", "\\");
            lc_pathFile = lc_pathFile.replace("\\\\", "\\");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try {
            //Connection conn = daoConnect.getConnect();
            CallableStatement calstatement;
            String strStoreproce = "{call SP_CT_SBV_TMINH(?, ?, ?, ?, ? , ?)}";
            ResultSet reset;
            try {
                //Khoi tao goi store
                calstatement = con.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos                    
                calstatement.setString(1, p_PosCode);
                calstatement.setString(2, p_Date);
                calstatement.setString(3, p_Period);
                calstatement.setString(4, sbvSendIndiGroup);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(5);
                //Lay du lieu ra file name
                strFullName = lc_pathFile + calstatement.getString(6);
                ExportFile2Sbv export = new ExportFile2Sbv();
                export.ExportFile(p_Report, p_Period, strFullName, reset);
                if (reset != null) {
                    reset.close();
                }
                calstatement.close();
            } catch (SQLException e) {
                System.err.println("Loi trong ham getDataExportFile " + e.getMessage());
                CoreLogger.error(DaoExportHstdct.class.getCanonicalName()
                        + " getDataExportFile -> " + e.getMessage());
            }
        } catch (IOException | InvalidFormatException e) {
            System.err.println("Loi trong ham getDataExportFile " + e.getMessage());
            CoreLogger.error(DaoExportHstdct.class.getCanonicalName()
                    + " getDataExportFile -> " + e.getMessage());

        }
        finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExportText2SbvDao.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
        }
        return strFullName;
    }

    public String getMappingReport(String report) {
        String lcMapReport = "999999", lcQuery;
        lcQuery = "SELECT NVL(DM_REPORT,'999999') DM_REPORT FROM dmbc_ex2sbv WHERE DM_MABC = '"
                + report.trim() + "'";
        
        daoConnect = new DaoConnect();
        con = daoConnect.getConnect();
        
        try {
            //Connection con = daoConnect.getConnect();
            try (Statement ps = con.createStatement();
                    ResultSet rs = ps.executeQuery(lcQuery)) {
                while (rs.next()) {
                    lcMapReport = rs.getString("DM_REPORT");
                }
                rs.close();
            }
        } catch (SQLException ex) {
            System.err.println("Loi --> Khong lay duoc danh muc Mapping Report");
        }
        finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExportText2SbvDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lcMapReport;
    }

    // Hàm thực hiện lấy danh sách chỉ tiêu gốc
    public List<Indicator> getIndicatorList(int type, int period) {
                
        ArrayList<Indicator> indicatorList = new ArrayList<>();
        CallableStatement calstatement;
        String strStoreproce = "{call vbsp_rpt_exporter.p_get_indicator_list(?, ?, ?)}";
        ResultSet reset;
        
        daoConnect = new DaoConnect();
        con = daoConnect.getConnect();
        
        try {
            calstatement = con.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            calstatement.setInt(1, type);
            calstatement.setInt(2, period);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(3);
            int ji_id, ji_gen_id;
            String js_mapn, js_chitieu, js_name;
            while (reset.next()) {
                ji_id = reset.getInt("id");
                js_mapn = reset.getString("mapn");
                js_chitieu = reset.getString("chitieu");
                js_name = reset.getString("name");
                ji_gen_id = reset.getInt("gen_id");
                indicatorList.add(new Indicator(ji_id, type, period, js_mapn,
                        js_chitieu, js_name, ji_gen_id));
            }
            calstatement.close();
        } catch (SQLException e) {
            System.err.println("getIndicatorList" + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExportText2SbvDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return indicatorList;
    }

    // Hàm thực hiện lấy thuyet minh cho phan nhom chi tieu
    public String getIndicatorNote(String pv_group_id) {
        
        String note = "";
        CallableStatement calstatement;
        
        String strStoreproce = "{call vbsp_rpt_exporter.P_GET_INDICATOR_NOTE(?, ?)}";
        ResultSet reset;
        
        daoConnect = new DaoConnect();
        con = daoConnect.getConnect();
        
        try {
            calstatement = con.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, pv_group_id);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(2);
            while (reset.next()) {
                note = reset.getString("NOTE");
            }
            calstatement.close();
        } catch (Exception e) {
            System.err.println("getIndicatorNote"+e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExportText2SbvDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return note;
    }

    // Hàm thực hiện ghi thuyet minh cho phan nhom chi tieu

    public boolean saveIndicatorNote(String pv_group_id, String pv_note, String mkr_id) {
        
        String status = "";
        CallableStatement calstatement;
        
        String strStoreproce = "{call vbsp_rpt_exporter.P_SAVE_INDICATOR_NOTE(? , ? , ?, ?)}";
        
        daoConnect = new DaoConnect();
        con = daoConnect.getConnect();
        
        try {
            calstatement = con.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, pv_group_id);
            calstatement.setString(2, pv_note);
            calstatement.setString(3, mkr_id);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            //Lay cursor ra resultset
            status = (String) calstatement.getObject(4);
            calstatement.close();
        } catch (SQLException e) {
            System.err.println(""+e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExportText2SbvDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status.equals("SUCCESS");
    }
}
