/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.dtw;

/**
 *
 * @author Trung
 */
public class ExcelCell {
    
    private int row_id;
    private int col_id;
    private int cell_type;
    private String font_type;
    private Object value;
    
    public ExcelCell(){}
    
    public ExcelCell(int row_id,int col_id,int cell_type,String font_type,Object value){
        this.row_id = row_id;
        this.col_id = col_id;
        this.cell_type = cell_type;
        this.font_type = font_type;
        this.value = value;
    }

    public int getRow_id() {
        return row_id;
    }

    public void setRow_id(int row_id) {
        this.row_id = row_id;
    }

    public int getCol_id() {
        return col_id;
    }

    public void setCol_id(int col_id) {
        this.col_id = col_id;
    }

    public int getCell_type() {
        return cell_type;
    }

    public void setCell_type(int cell_type) {
        this.cell_type = cell_type;
    }

    public String getFont_type() {
        return font_type;
    }

    public void setFont_type(String font_type) {
        this.font_type = font_type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    
}
