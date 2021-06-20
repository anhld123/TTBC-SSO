/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.dtw;

import java.util.List;

/**
 *
 * @author Trung
 */
public abstract class FileLibrary {    
    public FileLibrary(){}    
    public int read_file(String file_path,ExcelFile file){ return 0;} // Hàm đọc và trả về số dòng đọc được
}
