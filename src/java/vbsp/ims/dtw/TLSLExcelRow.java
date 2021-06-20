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
public class TLSLExcelRow extends ExcelRow{ 
    private List<ExcelCell> cells;
    
    public TLSLExcelRow(){
        super();
        cells = new ArrayList<>();  
    }
    
    public TLSLExcelRow(int id){
        super(id);
        cells = new ArrayList<>();        
    }

    public List<ExcelCell> getCells() {
        return cells;
    }

    public void setCells(List<ExcelCell> cells) {
        this.cells = cells;
    }
    
    
}
