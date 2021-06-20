/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.dtw;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Trung
 */
public class ExcelFile {
    
    private List<ExcelRow> rows;
    private String file_name;
    
    public ExcelFile(){
        rows = new ArrayList<>();
    }
    
    public ExcelFile(String file_name){        
        this.file_name = file_name;
        rows = new ArrayList<>();
    }

    public List<ExcelRow> getRows() {
        return rows;
    }

    public void setRows(List<ExcelRow> rows) {
        this.rows = rows;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
    
    
}
