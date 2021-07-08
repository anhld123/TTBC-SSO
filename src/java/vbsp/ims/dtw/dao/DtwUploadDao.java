/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dtw.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vbsp.ims.dao.*;
import vbsp.ims.dtw.ExcelCell;
import vbsp.ims.dtw.ExcelFile;
import vbsp.ims.dtw.ExcelRow;
import vbsp.ims.dtw.ExcelToTableDetail;
import vbsp.ims.dtw.TLSLExcelRow;
import vbsp.ims.dtw.UploadFileLogObject;
import vbsp.ims.excel_upload.model.ResultModel;
import vbsp.ims.loveleaf.LoveLeafDao;
import vbsp.ims.model.RefObject;

/**
 *
 * @author Trung
 */
public class DtwUploadDao {

    private static final int _size = 1;
    private static final int _start_row = 8;
    private static final int _end_row = 80;

//    private static final int _start_row_common = 3;
//    private static final int _end_row_common = 100;
//    private static final int _total_col_common = 20;
    //--------------------------------------------------------------------------    
    private DaoConnect daoConnect;
    private Connection conn;

    public DtwUploadDao() {
//        daoConnect = new DaoConnect();
    }

    public void write_log(UploadFileLogObject obj) {
        String strStoreproce
                = "{call dtw_upload_file.write_log(?, ?, ?, ?, ?, ?, ?, ?)}";
        try {
            
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
            
            try (CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

                calstatement.setString(1, obj.getDwh_file_path());
                calstatement.setString(2, obj.getDwh_filename());
                calstatement.setString(3, obj.getDwh_filesize());
                calstatement.setString(4, obj.getDwh_upload_time());
                calstatement.setString(5, obj.getDwh_process_time());
                calstatement.setString(6, obj.getDwh_upload_status());
                calstatement.setString(7, obj.getDwh_mkr_id());
                calstatement.setString(8, obj.getDwh_comment());
                calstatement.executeUpdate();

            }
        } catch (SQLException ex) {
            System.err.println("DtwUploadDao.write_log-->" + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DtwUploadDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean insert_row(ExcelFile excel_file,
            RefObject row_total, RefObject process_row,
            RefObject error_msg) {

        boolean returnValue = false;

        try {

            // xoa du lieu truoc khi insert
            delete_raw_data(excel_file.getFile_name());

            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();

            conn.setAutoCommit(false);

            String sql = "insert into dwh_raw_data "
                    + "(dwh_row_id,dwh_name ,dwh_d1 ,dwh_d2 ,dwh_d3 ,dwh_d4 ,dwh_d5 ,"
                    + "dwh_d6 ,dwh_d7 ,dwh_d8 ,dwh_d9 ,dwh_d10 ,dwh_d11 ,"
                    + "dwh_d12,dwh_d13,source_file )"
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement prest
                    = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            List<ExcelRow> rows = excel_file.getRows();

            int row_cnt = 0;
            int cnt_1 = 0;
            int cnt_2 = 0;
            String err_msg = "";

            for (ExcelRow row : rows) {
                if (row.getId() >= _start_row
                        && row.getId() <= _end_row) {
                    TLSLExcelRow tlsl_row = (TLSLExcelRow) row;
//                    if (row.getId() == 25){
//                        //System.err.println("test");
//                    }
                    try {
                        if (tlsl_row.getCells().size() > 8) {
                            if (tlsl_row.getCells().get(1).getValue() != null
                                    && !tlsl_row.getCells().get(1).getValue().toString().isEmpty()) {
                                List<ExcelCell> cells = tlsl_row.getCells();
                                if (cells.size() > 12) {
                                    prest.setInt(1, row.getId());
                                    for (int i = 1; i <= 13; i++) {
                                        if (cells.get(i).getValue() != null) {
                                            String cell_value = cells.get(i).getValue().toString();
                                            prest.setString(i + 1, cell_value);
                                            System.err.print(cell_value);
                                        } else {
                                            prest.setString(i + 1, "");
                                            System.err.print("");
                                        }
                                    }
                                    if (cells.size() >= 15 && cells.get(14) != null
                                            && cells.get(14).getValue() != null) {
                                        String cell_value = cells.get(14).getValue().toString();
                                        prest.setString(15, cell_value);
                                        System.err.print(cell_value);
                                    } else {
                                        prest.setString(15, "");
                                        System.err.print("");
                                    }
                                    prest.setString(16, excel_file.getFile_name());
                                    prest.addBatch();
                                    System.err.println("");
                                    row_cnt++;
                                }
                                cnt_2++;
                            }
                        }
                        if (row_cnt >= _size) {
                            prest.executeBatch();
                            row_cnt = 0;
                        }
                    } catch (Exception e1) {
                        System.err.println("Loi[1]~" + e1.getMessage() + row.getId());
                        err_msg = err_msg + e1.getMessage() + "\n";
                    }
                    cnt_1++;
                }
            }
            
            row_total.setInt01(cnt_1);
            process_row.setInt01(cnt_2);
            error_msg.setString01(err_msg);
            prest.executeBatch();
            conn.commit();
            
            //conn.close();
            returnValue = true;
            
        } catch (Exception e2) {
            
            System.err.println("Loi[2]~" + e2.getMessage());
            returnValue = false;
            
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DtwUploadDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return returnValue;
    }

    public ResultModel insert_row_common(ExcelFile excel_file, String table_id,
            RefObject row_total, RefObject process_row,
            RefObject error_msg) {

        ResultModel returnValue = new ResultModel();

        try {

            LoveLeafDao leafDao = new LoveLeafDao();

            ExcelToTableDetail tableDetail = leafDao.getExcelToTableDetail(table_id);

            // xoa du lieu truoc khi insert
            delete_raw_data_common(excel_file.getFile_name());

            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
            conn.setAutoCommit(false);

            String sql = tableDetail.getInsert_query();
            System.err.println("Insert query -->" + sql);
//            String sql = "insert into dwh_raw_data_common (dwh_row_id,dwh_insert_dt,source_file ,"
//                    + "dwh_d1 ,dwh_d2 ,dwh_d3 ,dwh_d4 ,dwh_d5 ,"
//                    + "dwh_d6 ,dwh_d7 ,dwh_d8 ,dwh_d9 ,dwh_d10 ,dwh_d11 ,"
//                    + "dwh_d12 , dwh_d13, dwh_d14, dwh_d15, dwh_d16, dwh_d17 )"
//                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement prest
                    = conn.prepareStatement(sql,
                            ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
            List<ExcelRow> rows = excel_file.getRows();

            int row_cnt = 0;
            int cnt_1 = 0;
            int cnt_2 = 0;
            String err_msg = "";

            String strSysDate = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());

            for (ExcelRow row : rows) {
                if (row.getId() >= tableDetail.getBegin_row() //_start_row_common
                        && row.getId() <= tableDetail.getEnd_row()//_end_row_common
                        ) {
                    TLSLExcelRow tlsl_row = (TLSLExcelRow) row;
                    try {
                        if (tlsl_row.getCells().size() > 2) {
                            if (tlsl_row.getCells().get(0).getValue() != null
                                    && !tlsl_row.getCells().get(0).getValue().toString().isEmpty()) {
                                List<ExcelCell> cells = tlsl_row.getCells();

                                prest.setInt(1, row.getId());
                                prest.setString(2, strSysDate);
                                prest.setString(3, excel_file.getFile_name());
                                for (int i = 1; i <= tableDetail.getTotal_col() - 3 //_total_col_common - 3
                                        ; i++) {
                                    String cell_value = " ";
                                    for (ExcelCell cell : cells) {
                                        if (cell != null && cell.getCol_id() == i - 1) {
                                            cell_value = cell.getValue() == null ? " " : cell.getValue().toString();
                                        }
                                    }
//                                    System.err.println("Chay den cell ~" + i);
                                    prest.setString(i + 3, cell_value);
                                }
                                prest.addBatch();
                                row_cnt++;

                                cnt_2++;
                            }
                        }
                        if (row_cnt >= _size) {
                            prest.executeBatch();
                            row_cnt = 0;
                        }
                    } catch (Exception e1) {
                        System.err.println("Loi[1]~" + e1.getMessage());
                        err_msg = err_msg + e1.getMessage() + "\n";
                    }
                    cnt_1++;
                }
            }
            row_total.setInt01(cnt_1);
            process_row.setInt01(cnt_2);
            error_msg.setString01(err_msg);
            prest.executeBatch();

            conn.commit();
            //conn.close();

            returnValue.status = true;
            returnValue.message ="SUCCESS";

        } catch (Exception e2) {
            System.err.println("Loi[2]~" + e2.getMessage());
            //return false;
            returnValue.status = false;
            returnValue.message ="Lỗi: " + e2.getMessage();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DtwUploadDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return returnValue;

    }

    public boolean process_raw_data(String file_name) {
        String strStoreproce
                = "{call dtw_upload_file.sp_process_data(?, ?)}";

        boolean returnValue = false;

        try {
            String message = "NONE";

            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();

            try (CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

                calstatement.setString(1, file_name);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.execute();
                message = (String) calstatement.getObject(2);

                System.err.println("process_raw_data~" + message);
            } catch (SQLException e1) {
                System.out.println("Error when execute procedure~" + e1.getMessage());
            } finally {
                if (message.equals("PROCESSED")) {
                    import_to_market(file_name);
                    returnValue = true;
                }
            }

            returnValue = false;
        } catch (Exception ex) {
            System.err.println("dtw_upload_file.sp_process_data-->" + ex.getMessage());
            returnValue = false;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DtwUploadDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return returnValue;
    }

    public ResultModel process_raw_data_common(String pv_table_id, String file_name) {

        String strStoreproce
                = "{call dtw_upload_file.sp_convert_raw_to_table(?, ?, ?)}";

        //boolean returnValue = false;
        ResultModel returnValue = new ResultModel();

        try {
            String message;

            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();

            CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, pv_table_id);
            calstatement.setString(2, file_name);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            message = (String) calstatement.getObject(3);
            //returnValue = message.equals("SUCCESS");
            
            returnValue.message = message;
            if (message.equals("SUCCESS")){
                returnValue.status = true;
            }
            else {
                returnValue.status = false;
            }
//                System.err.println("process_raw_data~" + message);                        
        } catch (Exception ex) {
            
            System.err.println("dtw_upload_file.sp_process_data-->" + ex.getMessage());
            returnValue.message =  "Lỗi: " + ex.getMessage();
            returnValue.status = false;            
            
        }
//        finally {
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(DtwUploadDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

        return returnValue;
    }

    public void import_to_market(String file_name) {
        String strStoreproce
                = "{call dtw_upload_file.sp_import_to_market(?,?)}";
        try {

            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();

            CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, file_name);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            String message = (String) calstatement.getObject(2);
            System.err.println("import_to_market~" + message);

        } catch (Exception e) {
            System.err.println("dtw_upload_file.sp_import_to_market-->" + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DtwUploadDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void update_row_process(String file_name,
            int total_row, int process_row,
            String error_msg) {
        String strStoreproce
                = "{call dtw_upload_file.sp_update_row_process(?,?,?,?)}";
        try {

            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
            CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, file_name);
            calstatement.setInt(2, total_row);
            calstatement.setInt(3, process_row);
            calstatement.setString(4, error_msg);
            calstatement.execute();
        } catch (Exception e) {
            System.err.println("dtw_upload_file.sp_update_row_process-->" + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DtwUploadDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void delete_raw_data(String file_name) {
        String strStoreproce
                = "{call dtw_upload_file.sp_Delete_raw_data(?)}";
        try {

            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();

            CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, file_name);
            calstatement.execute();

        } catch (Exception e) {
            System.err.println("delete_raw_data-->" + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DtwUploadDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void delete_raw_data_common(String file_name) {
        String strStoreproce
                = "{call dtw_upload_file.sp_Delete_raw_data_common(?)}";
        try {

            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
            CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, file_name);
            calstatement.execute();

        } catch (Exception e) {
            System.err.println("delete_raw_data-->" + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DtwUploadDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void get_table_insert_detail() {

    }
    
    public String get_category_by_filename(String pv_file_name){
               
        String strStoreproce
                = "{call dtw_upload_file.get_category_by_filename(?, ? , ?)}";

        String strCategory = "";
        
        try {

            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();

            ResultSet rs;
            
            try (CallableStatement calstatement
                    = conn.prepareCall(strStoreproce,
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                calstatement.setString(1, pv_file_name);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                rs = (ResultSet) calstatement.getObject(3);                                
                
                while (rs.next()) {
                    strCategory = rs.getString("VALUE");                    
                }
                rs.close();
                
            } catch (SQLException sql_error) {
                System.err.println("dtw_upload_file.get_category_by_filename-->" 
                        + sql_error.getMessage());
            }
        } catch (Exception other_error) {
            System.err.println("dtw_upload_file.get_category_by_filename-->" 
                    + other_error.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoveLeafDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return strCategory;   
    }

    public List<UploadFileLogObject> get_uploaded_log(String dir_path, String dir_type) {
        ArrayList<UploadFileLogObject> logs = new ArrayList<>();
        String strStoreproce
                = "{call dtw_upload_file.sp_view_upload_log(?,?,?)}";
        try {
            ResultSet rs;

            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();

            CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, dir_path);
            calstatement.setString(2, dir_type);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            rs = (ResultSet) calstatement.getObject(3);
            
            String pv_filename, pv_filesize, pv_uploadtime, pv_upload_status;
            int row_total, processed_row;
            
            while (rs.next()) {
                
                pv_filename = rs.getString("DWH_FILENAME");
                pv_filesize = rs.getString("DWH_FILESIZE");
                pv_uploadtime = rs.getString("DWH_UPLOAD_TIME");
                pv_upload_status = rs.getString("DWH_UPLOAD_STATUS");
                row_total = rs.getInt("ROW_TOTAL");
                processed_row = rs.getInt("PROCESSED_ROW");
                
                logs.add(new UploadFileLogObject(
                        pv_filename,
                        pv_filesize,
                        pv_uploadtime,
                        pv_upload_status,
                        row_total, processed_row));
            }
            rs.close();
            
        } catch (Exception e) {
            System.err.println("get_upload_log-->" + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DtwUploadDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return logs;
    }
}
