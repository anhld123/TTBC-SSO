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
public class ExcelToTableDetail {
    
    private String table_id;
    private String insert_query;
    private int begin_row;
    private int end_row;
    private int total_col;
    
    public ExcelToTableDetail(){
    }
    
    public ExcelToTableDetail(
         String table_id,
            String insert_query,
            int begin_row,
            int end_row,
            int total_col
    ){
        this.table_id = table_id;
        this.insert_query = insert_query;
        this.begin_row = begin_row;
        this.end_row = end_row;
        this.total_col = total_col;
    }

    public String getTable_id() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }

    public String getInsert_query() {
        return insert_query;
    }

    public void setInsert_query(String insert_query) {
        this.insert_query = insert_query;
    }

    public int getBegin_row() {
        return begin_row;
    }

    public void setBegin_row(int begin_row) {
        this.begin_row = begin_row;
    }

    public int getEnd_row() {
        return end_row;
    }

    public void setEnd_row(int end_row) {
        this.end_row = end_row;
    }

    public int getTotal_col() {
        return total_col;
    }

    public void setTotal_col(int total_col) {
        this.total_col = total_col;
    }
    
    
}
