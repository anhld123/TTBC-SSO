/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.loveleaf;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.DefineFun;
import vbsp.ims.dtw.ExcelToTableDetail;
import vbsp.ims.export.excel.ExportExcelFile;

/**
 *
 * @author Trung
 */
public class LoveLeafDao {

    private DaoConnect daoConnect;
    private Connection conn;

    public LoveLeafDao() {
//        daoConnect = new DaoConnect();
    }

    public List<DonateTransaction> list_donate_trans(String pv_tran_dt,
            String period,
            String gendata_FLG) {
        ArrayList<DonateTransaction> donateTrans = new ArrayList<>();
        String strStoreproce
                = "{call app_loveleaf_proj.get_donator_transaction(?, ? , ? , ? , ? , ?)}";
        try {
//            if (conn == null) {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
//            }
            ResultSet rs;
            try (CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                calstatement.setString(1, pv_tran_dt);
                calstatement.setString(2, period);
                calstatement.setString(3, gendata_FLG);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                rs = (ResultSet) calstatement.getObject(6);
                String lv_ref_no;
                String lv_donator_id;
                String lv_donate_dt;
                int amount;
                String lv_donator_ac;
                String lv_cust_remark;
                String lv_donator_name;
                String lv_bank_name;
                String lv_status;
                while (rs.next()) {
                    lv_ref_no = rs.getString("REF_NO");
                    lv_donator_id = rs.getString("DONATE_ID");
                    lv_donate_dt = rs.getString("DONATE_DT");
                    amount = rs.getInt("AMOUNT");
                    lv_donator_ac = rs.getString("DONATOR_AC_NO");
                    lv_cust_remark = rs.getString("CUST_REMARK");
                    lv_donator_name = rs.getString("DONATOR_NAME");
                    lv_status = rs.getString("STATUS");
                    lv_bank_name = rs.getString("BANK_NAME");
                    donateTrans.add(new DonateTransaction(
                            lv_ref_no,
                            lv_donator_id,
                            lv_donate_dt,
                            amount,
                            lv_donator_ac,
                            lv_bank_name,
                            lv_cust_remark,
                            lv_donator_name,
                            lv_status));
                }
                rs.close();
            } catch (SQLException sql_error) {
                System.out.println("Error when execute procedure~" + sql_error.getMessage());
            } finally {
                conn.close();
                return donateTrans;
            }
        } catch (Exception other_error) {
            System.err.println("LoveLeafDao.list_donate_trans-->" + other_error.getMessage());
            return null;
        }
    }

    public List<PoorTransaction> list_poor_trans(String pv_source_file) {
        ArrayList<PoorTransaction> poorTrans = new ArrayList<>();
        String strStoreproce
                = "{call app_loveleaf_proj.get_poor_upload(?, ? , ?)}";
        try {
//            if (conn == null) {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
//            }
            ResultSet rs;
            try (CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                calstatement.setString(1, pv_source_file);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                rs = (ResultSet) calstatement.getObject(3);
                String pos_cd;
                String ref_no;
                String id;
                String name;
                int amount;
                String tran_dt;
                String province;
                String district;
                String commune;
                String status;
                String source_file;
                while (rs.next()) {
                    pos_cd = rs.getString("POS_CD");
                    ref_no = rs.getString("REF_NO");
                    id = rs.getString("POOR_ID");
                    name = rs.getString("POOR_NAME");
                    amount = rs.getInt("AMOUNT");
                    tran_dt = rs.getString("TRAN_DT");
                    province = rs.getString("PROVINCE");
                    district = rs.getString("DISTRICT");
                    commune = rs.getString("COMMUNE");
                    status = rs.getString("STATUS");
                    source_file = rs.getString("SOURCE_FILE");
                    poorTrans.add(new PoorTransaction(
                            pos_cd,
                            ref_no,
                            id,
                            name,
                            amount,
                            tran_dt,
                            province,
                            district,
                            commune,
                            status,
                            source_file));
                }
                rs.close();
            } catch (SQLException sql_error) {
                System.out.println("Error when execute procedure~" + sql_error.getMessage());
            } finally {
                conn.close();
                return poorTrans;
            }
        } catch (Exception other_error) {
            System.err.println("LoveLeafDao.list_donate_trans-->" + other_error.getMessage());
            return null;
        }
    }

    public List<PoorTransaction> list_poor_trans_by_date(String pv_tran_dt) {
        ArrayList<PoorTransaction> poorTrans = new ArrayList<>();
        String strStoreproce
                = "{call app_loveleaf_proj.get_poor_infor_by_date(?, ? , ?)}";
        try {
//            if (conn == null) {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
//            }
            ResultSet rs;
            try (CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                calstatement.setString(1, pv_tran_dt);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                rs = (ResultSet) calstatement.getObject(3);
                String pos_cd;
                String ref_no;
                String id;
                String name;
                int amount;
                String tran_dt;
                String province;
                String district;
                String commune;
                String status;
                String source_file;
                while (rs.next()) {
                    pos_cd = rs.getString("POS_CD");
                    ref_no = rs.getString("REF_NO");
                    id = rs.getString("POOR_ID");
                    name = rs.getString("POOR_NAME");
                    amount = rs.getInt("AMOUNT");
                    tran_dt = rs.getString("TRAN_DT");
                    province = rs.getString("PROVINCE");
                    district = rs.getString("DISTRICT");
                    commune = rs.getString("COMMUNE");
                    status = rs.getString("STATUS");
                    source_file = rs.getString("SOURCE_FILE");
                    poorTrans.add(new PoorTransaction(
                            pos_cd,
                            ref_no,
                            id,
                            name,
                            amount,
                            tran_dt,
                            province,
                            district,
                            commune,
                            status,
                            source_file));
                }
                rs.close();
            } catch (SQLException sql_error) {
                System.out.println("Error when execute procedure~" + sql_error.getMessage());
            } finally {
                conn.close();
                return poorTrans;
            }
        } catch (Exception other_error) {
            System.err.println("LoveLeafDao.list_donate_trans-->" + other_error.getMessage());
            return null;
        }
    }

    public List<PoorTransaction> list_wrong_poor(String pv_tran_dt) {
        ArrayList<PoorTransaction> poorTrans = new ArrayList<>();
        String strStoreproce
                = "{call app_loveleaf_proj.GET_WRONG_POOR_BY_DATE(?, ? , ?)}";
        try {
//            if (conn == null) {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
//            }
            ResultSet rs;
            try (CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                calstatement.setString(1, pv_tran_dt);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                rs = (ResultSet) calstatement.getObject(3);
                String pos_cd;
                String ref_no;
                String id;
                String name;
                int amount;
                String tran_dt;
                String province;
                String district;
                String commune;
                String status;
                String source_file;
                while (rs.next()) {
                    pos_cd = rs.getString("POS_CD");
                    ref_no = rs.getString("REF_NO");
                    id = rs.getString("POOR_ID");
                    name = rs.getString("POOR_NAME");
                    amount = rs.getInt("AMOUNT");
                    tran_dt = rs.getString("TRAN_DT");
                    province = rs.getString("PROVINCE");
                    district = rs.getString("DISTRICT");
                    commune = rs.getString("COMMUNE");
                    status = rs.getString("STATUS");
                    source_file = rs.getString("SOURCE_FILE");
                    poorTrans.add(new PoorTransaction(
                            pos_cd,
                            ref_no,
                            id,
                            name,
                            amount,
                            tran_dt,
                            province,
                            district,
                            commune,
                            status,
                            source_file));
                }
                rs.close();
            } catch (SQLException sql_error) {
                System.out.println("Error when execute procedure~" + sql_error.getMessage());
            } finally {
                conn.close();
                return poorTrans;
            }
        } catch (Exception other_error) {
            System.err.println("LoveLeafDao.list_donate_trans-->" + other_error.getMessage());
            return null;
        }
    }

    public Donator getDonator(String pv_donator_id) {

        Donator donator = null;

        String strStoreproce
                = "{call app_loveleaf_proj.get_donator(?, ? , ?)}";
        try {
//            if (conn == null) {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
//            }
            ResultSet rs;
            CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, pv_donator_id);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            rs = (ResultSet) calstatement.getObject(3);
            String lv_donator_id;
            String lv_donator_name;
            String lv_donator_llname;
            String lv_donator_passno;
            String lv_donator_pass_i_plc;
            String lv_donator_pass_i_dt;
            String lv_donator_address;
            while (rs.next()) {
                lv_donator_id = rs.getString("ID");
                lv_donator_name = rs.getString("NAME");
                lv_donator_llname = rs.getString("LL_NAME");
                lv_donator_passno = rs.getString("PASS_NO");
                lv_donator_pass_i_plc = rs.getString("PASS_I_PLC");
                lv_donator_pass_i_dt = rs.getString("PASS_I_DT");
                lv_donator_address = rs.getString("ADDRESS");
                donator = new Donator(
                        lv_donator_id,
                        lv_donator_name,
                        lv_donator_llname,
                        lv_donator_passno,
                        lv_donator_pass_i_plc,
                        lv_donator_pass_i_dt,
                        lv_donator_address);
            }
            rs.close();

            conn.close();

            return donator;

        } catch (Exception error) {
            System.err.println("LoveLeafDao.getDonator-->" + error.getMessage());
            return null;
        }
    }

    public boolean updateDonator(Donator donator) {
        boolean update_status = false;
        String strStoreproce
                = "{call app_loveleaf_proj.UPDATE_DONATOR(?, ? , ? , ?, ?, ? , ? , ?)}";
        try {
//            if (conn == null) {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
//            }
            String message;
            CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, donator.getId());
            calstatement.setString(2, donator.getName());
            calstatement.setString(3, donator.getLl_name());
            calstatement.setString(4, donator.getPass_no());
            calstatement.setString(5, donator.getPass_i_plc());
            calstatement.setString(6, donator.getPass_i_dt());
            calstatement.setString(7, donator.getAddress());
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            message = (String) calstatement.getObject(8);
            if (message.equals("SUCCESS")) {
                update_status = true;
            }
            conn.close();
        } catch (Exception error) {
            System.err.println("LoveLeafDao.updateDonator-->" + error.getMessage());
        }
        return update_status;
    }

    public void export_upload_file(String pv_tran_dt, String pv_file_path) {
        String strStoreproce
                = "{call app_loveleaf_proj.SP_EXPORT_UPLOADFILE(?, ? )}";
        try {
//            if (conn == null) {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
//            }
            ResultSet rs;
            try (CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                calstatement.setString(1, pv_tran_dt);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                rs = (ResultSet) calstatement.getObject(2);

                ExportExcelFile export = new ExportExcelFile();

                export.export2FileExcel_Rs_1(
                        rs,
                        6,
                        1,
                        pv_file_path,
                        "FILEUPLOAD", 1);

                rs.close();
            } catch (SQLException sql_error) {
                System.err.println("LoveLeafDao.export_upload_file-->" + sql_error.getMessage());
            }
        } catch (Exception other_error) {
            System.err.println("LoveLeafDao.export_upload_file-->" + other_error.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoveLeafDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void export_qtt_upload_file(String pv_tran_dt, String pv_file_path) {
        String strStoreproce
                = "{call app_loveleaf_proj.SP_QTT_EXPORT_UPLOADFILE(?, ? )}";
        try {
//            if (conn == null) {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
//            }
            ResultSet rs;
            try (CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                calstatement.setString(1, pv_tran_dt);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                rs = (ResultSet) calstatement.getObject(2);

                ExportExcelFile export = new ExportExcelFile();

                export.export2FileExcel_Rs_1(
                        rs,
                        6,
                        1,
                        pv_file_path,
                        "FILEUPLOAD", 1);

                rs.close();
            } catch (SQLException sql_error) {
                System.err.println("LoveLeafDao.export_upload_file-->" + sql_error.getMessage());
            }
        } catch (Exception other_error) {
            System.err.println("LoveLeafDao.export_upload_file-->" + other_error.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoveLeafDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void export_donator_to_excel(String pv_tran_dt,
            String pv_file_path,
            String period,
            String generate_FLG) {
        String strStoreproce
                = "{call app_loveleaf_proj.get_donator_to_excel(?, ?, ?, ? , ?)}";
        try {
//            if (conn == null) {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
//            }
            ResultSet rs;
            try (CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                calstatement.setString(1, pv_tran_dt);
                calstatement.setString(2, period);
                calstatement.setString(3, generate_FLG);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                rs = (ResultSet) calstatement.getObject(5);

                ExportExcelFile export = new ExportExcelFile();
                if (period.equals("D")) {
                    export.export2FileExcel_Rs_1(
                            rs,
                            10,
                            1,
                            pv_file_path,
                            "CLYT_MAU01", 1);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                    Date date = formatter.parse(pv_tran_dt);
                    String timestemp = "Tháng " + String.valueOf(date.getMonth() + 1)
                            + " năm " + String.valueOf(date.getYear() + 1900);
                    String subtile = "Hà Nội, ngày ... tháng ... năm " + String.valueOf(date.getYear() + 1900);
                    export.setExcelSubTitle_0(pv_file_path, timestemp, 5, 0, subtile, 121, 6);
                } else {
                    export.export2FileExcel_Rs_1(
                            rs,
                            10,
                            1,
                            pv_file_path,
                            "CLYT_MAU01_TH", 1);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                    Date date = formatter.parse(pv_tran_dt);
                    String timestemp = "Tháng " + String.valueOf(date.getMonth() + 1)
                            + " năm " + String.valueOf(date.getYear() + 1900);
                    String subtile = "Hà Nội, ngày ... tháng ... năm " + String.valueOf(date.getYear() + 1900);
//                    export.setExcelSubTitle_0(pv_file_path, timestemp, 5, 0, subtile, 372, 7);
                }

                rs.close();
            } catch (SQLException sql_error) {
                System.err.println("LoveLeafDao.get_export_donator-->" + sql_error.getMessage());
            }
        } catch (Exception other_error) {
            System.err.println("LoveLeafDao.get_export_donator-->" + other_error.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoveLeafDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ExcelToTableDetail getExcelToTableDetail(String pv_table_id) {
        ExcelToTableDetail detail = new ExcelToTableDetail();
        detail.setTable_id(pv_table_id);

        String strStoreproce
                = "{call dtw_upload_file.get_excel_to_table_detail(?, ? , ?)}";

        try {
//            if (conn == null) {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
//            }
            ResultSet rs;
            try (CallableStatement calstatement
                    = conn.prepareCall(strStoreproce,
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                calstatement.setString(1, pv_table_id);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                rs = (ResultSet) calstatement.getObject(3);
                String lv_insert_query;
                int ln_begin_row, ln_end_row, ln_col_total;
                while (rs.next()) {
                    lv_insert_query = rs.getString("INSERT_QUERY");
                    ln_begin_row = rs.getInt("BEGIN_ROW");
                    ln_end_row = rs.getInt("END_ROW");
                    ln_col_total = rs.getInt("COL_TOTAL");
                    detail.setInsert_query(lv_insert_query);
                    detail.setBegin_row(ln_begin_row);
                    detail.setEnd_row(ln_end_row);
                    detail.setTotal_col(ln_col_total);
                }
                rs.close();
            } catch (SQLException sql_error) {
                System.err.println("LoveLeafDao.get_export_donator-->" + sql_error.getMessage());
            }
        } catch (Exception other_error) {
            System.err.println("LoveLeafDao.get_export_donator-->" + other_error.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoveLeafDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return detail;
    }

    public List<MoneyTransaction> get_money_transaction(String pv_tran_dt,
            String pv_user_name, String pv_regenerate_flg) {

        ArrayList<MoneyTransaction> transactions = new ArrayList<>();

        String strStoreproce
                = "{call app_loveleaf_proj.get_money_move_list(? , ? , ? , ? , ?)}";

        try {
//            if (conn == null) {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
//            }
            ResultSet rs;
            try (CallableStatement calstatement
                    = conn.prepareCall(strStoreproce,
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                calstatement.setString(1, pv_tran_dt);
                calstatement.setString(2, pv_regenerate_flg);
                calstatement.setString(3, pv_user_name);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                String msg = (String) calstatement.getObject(4);
                if (msg.equals("SUCCESS")) {
                    rs = (ResultSet) calstatement.getObject(5);
                    String pos_cd;
                    String name;
                    int amount;
                    int poor_total;
                    String val_dt;
                    String status;
                    String mkr_dt;
                    String mkr_id;
                    while (rs.next()) {
                        pos_cd = rs.getString("POS_CD");
                        name = rs.getString("NAME");
                        amount = rs.getInt("AMOUNT");
                        poor_total = rs.getInt("POOR_TOTAL");
                        val_dt = rs.getString("VAL_DT");
                        status = rs.getString("STATUS");
                        mkr_dt = rs.getString("MKR_DT");
                        mkr_id = rs.getString("MKR_ID");
                        transactions.add(new MoneyTransaction(
                                pos_cd, name, amount, poor_total, val_dt, status, mkr_dt, mkr_id));
                    }
                    rs.close();
                }
            } catch (SQLException sql_error) {
                System.err.println("LoveLeafDao.get_money_transaction-->" + sql_error.getMessage());
            }
        } catch (Exception other_error) {
            System.err.println("LoveLeafDao.get_money_transaction-->" + other_error.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoveLeafDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return transactions;
    }

    public String update_pos_move_money(String pv_pos_cd, String pv_tran_dt) {

        String strStoreproce
                = "{call app_loveleaf_proj.update_pos_move_money(? , ? , ? )}";

        try {
//            if (conn == null) {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
//            }
//            ResultSet rs;
            CallableStatement calstatement
                    = conn.prepareCall(strStoreproce,
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, pv_pos_cd);
            calstatement.setString(2, pv_tran_dt);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            String message = (String) calstatement.getObject(3);
            return message;
        } catch (Exception error) {
            System.err.println("LoveLeafDao.update_pos_move_money-->" + error.getMessage());
            return "E";
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoveLeafDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
