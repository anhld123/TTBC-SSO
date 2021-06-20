/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.branch;

/**
 *
 * @author BAOANH
 */
public class ModelParameterQuery {
    private int idx;
    private int col_max_len;
    private String col_name;
    private String col_datatype;
    private int col_precision;
    private int col_scale;
    private String col_datatype_java;
    
    
    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getCol_max_len() {
        return col_max_len;
    }

    public void setCol_max_len(int col_max_len) {
        this.col_max_len = col_max_len;
    }

    public String getCol_name() {
        return col_name;
    }

    public void setCol_name(String col_name) {
        this.col_name = col_name;
    }

    public String getCol_datatype() {
        return col_datatype;
    }

    public void setCol_datatype(String col_datatype) {
        this.col_datatype = col_datatype;
    }

    public int getCol_precision() {
        return col_precision;
    }

    public void setCol_precision(int col_precision) {
        this.col_precision = col_precision;
    }

    public int getCol_scale() {
        return col_scale;
    }

    public void setCol_scale(int col_scale) {
        this.col_scale = col_scale;
    }

    public String getCol_datatype_java() {
        return col_datatype_java;
    }

    public void setCol_datatype_java(String col_datatype_java) {
        this.col_datatype_java = col_datatype_java;
    }
    
    
}
