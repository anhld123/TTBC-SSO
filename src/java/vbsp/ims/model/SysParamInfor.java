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
public class SysParamInfor {
    //--------------------------------------------------------------------------
    private int seqno;
    private String tablename;
    private String descript;
    private int differcount;
    private String manualflg;
    private String updateflg;
    //--------------------------------------------------------------------------
    public SysParamInfor(){}
    public SysParamInfor(int seqno,String tablename,String descript,int differcount,
            String manualflg,String updateflg){
        this.seqno = seqno;
        this.tablename = tablename;
        this.descript = descript;
        this.differcount = differcount;
        this.manualflg = manualflg;
        this.updateflg = updateflg;
    }
    //--------------------------------------------------------------------------

    public int getSeqno() {
        return seqno;
    }

    public void setSeqno(int seqno) {
        this.seqno = seqno;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public int getDiffercount() {
        return differcount;
    }

    public void setDiffercount(int differcount) {
        this.differcount = differcount;
    }

    public String getManualflg() {
        return manualflg;
    }

    public void setManualflg(String manualflg) {
        this.manualflg = manualflg;
    }

    public String getUpdateflg() {
        return updateflg;
    }

    public void setUpdateflg(String updateflg) {
        this.updateflg = updateflg;
    }
    
    
}
