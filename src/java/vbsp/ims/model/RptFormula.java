/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.model;

/**
 *
 * @author LION
 */
public class RptFormula {
    private String source_data; //Nguồn số liệu tạo báo cáo lstRowColumn
    private String until_data;//Đơn vị tính
    private String branch_cd;//Mã chi nhánh (main pos) khi chon cac chi nhánh)
    private String report_type;//Loại báo cáo
    private String report_times;//Ky bao cao
    private String title_name;//Tiêu đề cho báo cáo
    private String number_row;//Số dòng cần tạo báo cáo
    private String number_column;//Số cột báo cáo
    private String row_column;//Radio button cho chi nhanh chi tieu
    private String pos_cd;
    private String user_id;
    private String grade;
    
    public String getPos_cd() {
        return pos_cd;
    }

    public void setPos_cd(String pos_cd) {
        this.pos_cd = pos_cd;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    
    public String getSource_data() {
        return source_data;
    }

    public void setSource_data(String source_data) {
        this.source_data = source_data;
    }

    public String getUntil_data() {
        return until_data;
    }

    public void setUntil_data(String until_data) {
        this.until_data = until_data;
    }

    public String getBranch_cd() {
        return branch_cd;
    }

    public void setBranch_cd(String branch_cd) {
        this.branch_cd = branch_cd;
    }

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public String getReport_times() {
        return report_times;
    }

    public void setReport_times(String report_times) {
        this.report_times = report_times;
    }

    public String getTitle_name() {
        return title_name;
    }

    public void setTitle_name(String title_name) {
        this.title_name = title_name;
    }

    public String getNumber_row() {
        return number_row;
    }

    public void setNumber_row(String number_row) {
        this.number_row = number_row;
    }

    public String getNumber_column() {
        return number_column;
    }

    public void setNumber_column(String number_column) {
        this.number_column = number_column;
    }

    public String getRow_column() {
        return row_column;
    }

    public void setRow_column(String row_column) {
        this.row_column = row_column;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    
}
