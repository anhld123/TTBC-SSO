/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.excel_upload;

import java.io.File;
import java.util.List;
import vbsp.ims.define.Constant;
import vbsp.ims.dtw.DirectoryLibrary;
import vbsp.ims.dtw.Dtw_Import;
import vbsp.ims.dtw.ExcelFile;
import vbsp.ims.dtw.ExcelFileLibrary;
import vbsp.ims.dtw.dao.DtwUploadDao;

/**
 *
 * @author Trung Nguyen
 */
public class ExcelUploader {
    
    public ExcelUploader(){}
    
    public void import_directory(String directory_path, String font_format) {
        
        List<File> files = DirectoryLibrary.listFilesForFolder(directory_path);
        ExcelFileLibrary excel_file_library = new ExcelFileLibrary();
        ExcelFile excel_file;
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
            boolean status = dtw_Import.process_file_common(excel_file,strCategory);
            System.err.println("ExcelUploader.import_directory~" + file.getName()+ "~" + status);
            //-------------------------------------------------------------------
        }
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
