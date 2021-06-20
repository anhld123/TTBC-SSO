package vbsp.ims.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import vbsp.ims.loadparams.Combo;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;

public class DaoLoadReportParams {

    //CuongBM: 21-Apr-14
    //Desc: - Lay danh sach bao cao
    public List<Combo> getReportList(String reportGroup) {
        //CuongBM: Danh sach report
        ArrayList<Combo> reportList = new ArrayList<Combo>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call sp_get_report_list(?, ?, ?, ?)}";
            ResultSet rsReportList = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la nhom bao cao 
                calstatement.setString(1, reportGroup);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);

                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                rsReportList = (ResultSet) calstatement.getObject(4);

                while (rsReportList.next()) {
                    Combo cb = new Combo();

                    cb.setKey(rsReportList.getString("KEY"));
                    cb.setValue(rsReportList.getString("VALUE"));
                    cb.setFieldName("reportId");

                    reportList.add(cb);
                }

                if (rsReportList != null) {
                    rsReportList.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham getReportList " + e.getMessage());
                CoreLogger.error(DaoLoadReportParams.class.getCanonicalName() + " getReportList  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getReportList " + e.getMessage());
            CoreLogger.error(DaoLoadReportParams.class.getCanonicalName() + " getReportList  -> " + e.getMessage());
        }
        return reportList;
    }

    //CuongBM: 21-Apr-14
    //Desc: Lay danh sach tham so cho bao cao
    public List<ReportParam> getReportParmams(String strReportId, String strUsername) {
        ArrayList<ReportParam> report_param_list = new ArrayList<>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn ;
            conn = daoconnect.getConnect();
            CallableStatement calstatement;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call app_priv_view.sp_get_parameters(?, ?, ?, ?, ?, ?)}";
            ResultSet rscur_params;
            ResultSet rs_combo;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, 
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos    
                calstatement.setString(1, strReportId);
                calstatement.setString(2, strUsername);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);

                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                rscur_params = (ResultSet) calstatement.getObject(5);
                rs_combo = (ResultSet) calstatement.getObject(6);

                //Do toan bo du lieu tu Resultset vao array list
                List<Combo> combo_list_all = fillResultSetComboToArray(rs_combo);

                while (rscur_params.next()) {
                    ReportParam rp = new ReportParam();

                    rp.setType(rscur_params.getString("DM_LOAITSO"));
                    rp.setFieldName(rscur_params.getString("DM_TENTRUONG"));
                    rp.setLabel(rscur_params.getString("DM_MOTA"));
                    rp.setOrderNumber(Integer.parseInt(rscur_params.getString("DM_STT")));
                    //Neu la kieu list
                    if (rp.getType().equalsIgnoreCase("L")) {
                        ArrayList<Combo> combo_list = new ArrayList<>(); //Loc cac cobo can thiet
                        for (Combo cb : combo_list_all) {
                            if (cb.getFieldName().equalsIgnoreCase(rp.getFieldName())) {
                                combo_list.add(cb);
                            }
                        }
                        rp.setComboList(combo_list);
                    }
                    report_param_list.add(rp);
                }
                if (rscur_params != null) {
                    rscur_params.close();
                }
                if (rs_combo != null) {
                    rs_combo.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham getReportParmams " + e.getMessage());
                CoreLogger.error(DaoLoadReportParams.class.getCanonicalName() + " getReportParmams  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getColumnReportFast " + e.getMessage());
            CoreLogger.error(DaoLoadReportParams.class.getCanonicalName() + " getReportParmams  -> " + e.getMessage());
        }
        return report_param_list;
    }

    //CuongBM: 21-Apr-14
    //Desc: Chuyen du lieu combo ra Array de xu ly
    private List<Combo> fillResultSetComboToArray(ResultSet rs_combo) throws SQLException {

        ArrayList<Combo> combo_list = new ArrayList<Combo>();
        try {
            while (rs_combo.next()) {
                Combo cb = new Combo();

                cb.setKey(rs_combo.getString("PARA_KEY"));
                cb.setValue(rs_combo.getString("PARA_DESC"));
                cb.setFieldName(rs_combo.getString("PARA_FIELD_NAME"));

                combo_list.add(cb);
            }
        } catch (Exception e) {
            CoreLogger.error(DaoLoadReportParams.class.getCanonicalName() + " fillResultSetComboToArray  -> " + e.getMessage());
        }
        return combo_list;
    }

    public HashMap<String, String> getNameFromReportID(String strReport_id) {
        if (strReport_id.length() == 0 || strReport_id == null) {
            return null;
        }

        HashMap<String, String> hmNameReport = new HashMap<String, String>();

        String strNameReport = null;

        Connection connect = null;
        CallableStatement calstatement = null;
        //Khoi tao function se tra ra du lieu la kieu gi
        String strStoreproce = "{?=call vbsp_ims_rpt.f_get_name_report(?, ?)}";

        try {
            //Khoi tao ket noi
            DaoConnect oraconn = new DaoConnect();
            connect = oraconn.getConnect();
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham ExportExcelFromQry");
                return null;
            }
            //THuc hien goi ham trong oracle
            calstatement = connect.prepareCall(strStoreproce);
            //dang ky tham so tra du lieu ra la tham so thu nhat, kieu du lieu tra ra la number
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);

            //Truyen tham so thu 2 vao la mang main_pos
            calstatement.setString(2, strReport_id);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
//            calstatement.setString(3, strModule_id);
//            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
//            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//            int pn_err_cd = calstatement.getInt(4);
//            //thu hien lay mo ta loi
//            String strEdd_txt = calstatement.getString(5);
            //Lay cursor ra resultset
            //Get du lieu tra ra tham so thu 1
            strNameReport = calstatement.getString(1);
            String strNameJasper = calstatement.getString(3);
            hmNameReport.put("NAME_FILE", strNameReport);
            hmNameReport.put("NAME_JASPER", strNameJasper);
//            System.err.println(calstatement.getString(1));
            if (calstatement != null) {
                calstatement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(DaoLoadReportParams.class.getCanonicalName()+" ID bao cao "+strReport_id + " getNameFromReportID  -> " + e.getMessage());
            return null;
        }
        return hmNameReport;
    }
}
