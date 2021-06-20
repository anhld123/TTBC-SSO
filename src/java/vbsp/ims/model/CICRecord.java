/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.model;

/**
 *
 * @author Trung
 */
public class CICRecord {
    private String pos_cd ;
    private String descript;
    private String report_dt;
    private Number cust_total;
    private Number loan_total;
    private Number prin_amt;
    private Number col_total;
    private Number file_total;
    
    public CICRecord(){}
    
    public CICRecord(String l_Pos_cd,String l_Descript,String l_report_dt,Number l_cust_total,
            Number l_loan_total,Number l_prin_amt,Number l_col_total,Number l_file_total){
        this.pos_cd = l_Pos_cd;
        this.descript = l_Descript;
        this.report_dt = l_report_dt;
        this.cust_total = l_cust_total;
        this.loan_total = l_loan_total;
        this.prin_amt = l_prin_amt;
        this.col_total = l_col_total;
        this.file_total = l_file_total;
    }

    public String getPos_cd() {
        return pos_cd;
    }

    public void setPos_cd(String pos_cd) {
        this.pos_cd = pos_cd;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getReport_dt() {
        return report_dt;
    }

    public void setReport_dt(String report_dt) {
        this.report_dt = report_dt;
    }

    public Number getCust_total() {
        return cust_total;
    }

    public void setCust_total(Number cust_total) {
        this.cust_total = cust_total;
    }

    public Number getLoan_total() {
        return loan_total;
    }

    public void setLoan_total(Number loan_total) {
        this.loan_total = loan_total;
    }

    public Number getPrin_amt() {
        return prin_amt;
    }

    public void setPrin_amt(Number prin_amt) {
        this.prin_amt = prin_amt;
    }

    public Number getCol_total() {
        return col_total;
    }

    public void setCol_total(Number col_total) {
        this.col_total = col_total;
    }

    public Number getFile_total() {
        return file_total;
    }

    public void setFile_total(Number file_total) {
        this.file_total = file_total;
    }
                    
}
