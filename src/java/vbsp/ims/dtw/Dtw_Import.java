/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dtw;

import java.io.File;
import java.util.List;
import vbsp.ims.dtw.dao.DtwUploadDao;
import vbsp.ims.excel_upload.model.ResultModel;
import vbsp.ims.model.RefObject;

/**
 *
 * @author Trung
 */
public class Dtw_Import {

    private DtwUploadDao dtwDao;

    public Dtw_Import() {
    }

    public void import_directory(String directory_path, String font_format) {

//        DateFormat date_format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        dtwDao = new DtwUploadDao();
        List<File> files = DirectoryLibrary.listFilesForFolder(directory_path);
        ExcelFileLibrary excel_file_library = new ExcelFileLibrary();

        ExcelFile excel_file;
        for (File file : files) {

//            Date begin_time = new Date();
            excel_file = new ExcelFile(file.getName());
            if (font_format.equals("TCVN")) {
                excel_file_library.read_file(file.getAbsolutePath(), excel_file);
            } else {
                excel_file_library.read_file_utf8(file.getAbsolutePath(), excel_file);
            }

            // Xử lý phần đọc dữ liệu ở đây
            boolean status = process_file(excel_file);
            //-------------------------------------------------------------------

//            Date end_time = new Date();
//            UploadFileLogObject log_obj
//                    = new UploadFileLogObject(file.getParent(), file.getName(),
//                            String.valueOf(Math.round(file.length() / 1000)) + " KB",
//                            date_format.format(begin_time),
//                            date_format.format(end_time),
//                            status,
//                            "TRUNGNT88",
//                            "");
//            dtwDao.write_log(log_obj);
        }
    }

    public void import_excel_file(String file_path, String font_format) {

        ExcelFileLibrary excel_file_library = new ExcelFileLibrary();

        ExcelFile excel_file;
        File file = new File(file_path);

        excel_file = new ExcelFile(file.getName());
        if (font_format.equals("TCVN")) {
            excel_file_library.read_file(file.getAbsolutePath(), excel_file);
        } else {
            excel_file_library.read_file_utf8(file.getAbsolutePath(), excel_file);
        }

        // Xử lý phần đọc dữ liệu ở đây
        boolean status = process_file(excel_file);
        //-------------------------------------------------------------------

    }

    public ResultModel import_file(String filepath, String pv_table_id) {
        File file = new File(filepath);
        ExcelFile excel_file;
        ExcelFileLibrary excel_file_library = new ExcelFileLibrary();
        excel_file = new ExcelFile(file.getName());
        excel_file_library.read_file_utf8(file.getAbsolutePath(), excel_file);
        ResultModel status = process_file_common(excel_file, pv_table_id);
        return status;
    }

    boolean process_file(ExcelFile excel_file) {
        boolean process_status, import_status = false;
        dtwDao = new DtwUploadDao();
        RefObject row_total = new RefObject();
        RefObject process_row = new RefObject();
        RefObject error_msg = new RefObject();
        process_status = dtwDao.insert_row(excel_file, row_total, process_row, error_msg);
        dtwDao.update_row_process(excel_file.getFile_name(),
                row_total.getInt01(),
                process_row.getInt01(),
                error_msg.getString01());
        if (process_status) {
            import_status = dtwDao.process_raw_data(excel_file.getFile_name());
        }
        return process_status && import_status;
    }

    public ResultModel process_file_common(ExcelFile excel_file, String table_id) {
        ResultModel process_status, import_status ;
        dtwDao = new DtwUploadDao();
        RefObject row_total = new RefObject();
        RefObject process_row = new RefObject();
        RefObject error_msg = new RefObject();
        process_status = dtwDao.insert_row_common(
                excel_file,
                table_id,
                row_total,
                process_row,
                error_msg);
        
        dtwDao.update_row_process(excel_file.getFile_name(),
                row_total.getInt01(),
                process_row.getInt01(),
                error_msg.getString01());
        
        if (process_status.status) {
            import_status = dtwDao.process_raw_data_common(
                    table_id,
                    excel_file.getFile_name());
            return import_status;
        } else {
            return process_status;
        }        
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//        String root_directory = "C:\\Users\\Trung\\Desktop\\Thang7";
//
//    }
}
