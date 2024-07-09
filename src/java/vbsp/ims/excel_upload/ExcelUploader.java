/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.excel_upload;

import java.io.File;
import java.util.List;
import vbsp.ims.define.Constant;
import vbsp.ims.define.DefineFun;
import vbsp.ims.dtw.DirectoryLibrary;
import vbsp.ims.dtw.Dtw_Import;
import vbsp.ims.dtw.ExcelFile;
import vbsp.ims.dtw.ExcelFileLibrary;
import vbsp.ims.dtw.dao.DtwUploadDao;
import vbsp.ims.excel_upload.model.ResultModel;

/**
 *
 * @author Trung Nguyen
 */
public class ExcelUploader {
    
    public ExcelUploader(){}
    
    public ResultModel import_directory(String directory_path, String font_format) throws Exception {
        
        List<File> files = DirectoryLibrary.listFilesForFolder(directory_path);
        ExcelFileLibrary excel_file_library = new ExcelFileLibrary();
        ExcelFile excel_file;
        
        boolean isExcel = true;
        
        for (File file : files) {
            //Kiểm tra file xem có đúng là xls ko
            if (!DefineFun.isFileExcel(file.getAbsolutePath())) {
                isExcel = false;
                break;
            }
        }
        //Nếu có file không phải là file excel thì xóa hết file đi
        if (!isExcel) {
            for (File file : files) {
                file.delete();
            }
            throw new Exception("Trong file upload có file không phải là định dạng excel");
        }
        ResultModel status = new ResultModel(); 
        for (File file : files) {
            excel_file = new ExcelFile(file.getName());
            if (font_format.equals("TCVN")) {
                excel_file_library.read_file(file.getAbsolutePath(), excel_file);
            } else {
                excel_file_library.read_file_utf8(file.getAbsolutePath(),excel_file);
            }           
            String strCategory = getCategoryFile(file.getName());
            Dtw_Import dtw_Import = new Dtw_Import();
            // Xử lý phần đọc dữ liệu ở đây
            status = dtw_Import.process_file_common(excel_file,strCategory);
            System.err.println("ExcelUploader.import_directory~" + file.getName()+ "~" + status);
            if (status.status == false) {
                return status;                
            }
            //-------------------------------------------------------------------
        }
        return status;
    }
    
    public ResultModel import_file(String filePath, String font_format) {                
        ExcelFileLibrary excel_file_library = new ExcelFileLibrary();
        ExcelFile excel_file;
        File file = new File(filePath);
       
        excel_file = new ExcelFile(file.getName());
        if (font_format.equals("TCVN")) {
            excel_file_library.read_file(file.getAbsolutePath(), excel_file);
        } else {
            excel_file_library.read_file_utf8(file.getAbsolutePath(),excel_file);
        }           
        String strCategory = getCategoryFile(file.getName());
        Dtw_Import dtw_Import = new Dtw_Import();
        // Xử lý phần đọc dữ liệu ở đây
        ResultModel status = dtw_Import.process_file_common(excel_file,strCategory);
        System.err.println("ExcelUploader.import_file~" + file.getName()+ "~" + status);
        return status;
        //-------------------------------------------------------------------        
    }
        
    public String getCategoryFile(String filename){
        String strCategory ;        
        DtwUploadDao dao = new DtwUploadDao();        
        strCategory = dao.get_category_by_filename(filename);        
//        if (filename.startsWith("KH_MAU1"))
//            strCustType = Constant.dtw_insert_table._CUST1_UPOAD;
//        else
//            if (filename.startsWith("KH_MAU2"))
//            strCustType = Constant.dtw_insert_table._CUST1_UPOAD;
//           else
//                strCustType = "UNKNOWN";                
        return strCategory;
    }
    
    
}
