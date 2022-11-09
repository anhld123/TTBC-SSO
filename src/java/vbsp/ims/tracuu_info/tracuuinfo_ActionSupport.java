/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.tracuu_info;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class tracuuinfo_ActionSupport extends ActionSupport {
    
    private List<tracuuinfo_dndm> lsinfo;
    private String gentable;
    private String loaitc;
    private List ListDK;
    private List ListVal;
    private List<tracuuinfo_listgt> listgt;
    private String chkexcel;
    public tracuuinfo_ActionSupport() {
    }
    
    @Override
    public String execute() throws Exception {
        // Thực hiện lấy dữ liệu từ bảng định nghĩ loại tra cứu : IMS_DMINFO
        lsinfo = new tracuuinfo_model().get_tracuu_info();
        return  "thanhcong";
    }
    
    public String GenTable() throws SQLException{
        gentable = new tracuuinfo_model().get_tracuu_control(loaitc);
        return "thanhcong";
    }
    
    public String ShowList() throws SQLException{
        //Thực hiện lấy toàn bộ các trường ra List
        Integer maxitem = ListDK.size();
        String dieukien ="";
        for (int i = 0; i < maxitem; i++) {
            dieukien = dieukien + ListVal.get(i) + "/TV/";
        }
        dieukien = dieukien.substring(0,dieukien.length() - 4);
        if(chkexcel == null){
            chkexcel = "OFF";
        }
        listgt = null;
        listgt = new tracuuinfo_model().get_query_info(dieukien,loaitc,chkexcel.toUpperCase());
        return "thanhcong";
    }
    
    //<editor-fold defaultstate="collapsed" desc="Thực thể">
    public List<tracuuinfo_dndm> getLsinfo() {
        return lsinfo;
    }
    
    public void setLsinfo(List<tracuuinfo_dndm> lsinfo) {
        this.lsinfo = lsinfo;
    }
    
    public String getGentable() {
        return gentable;
    }
    
    public void setGentable(String gentable) {
        this.gentable = gentable;
    }
    public String getLoaitc() {
        return loaitc;
    }

    public void setLoaitc(String loaitc) {
        this.loaitc = loaitc;
    }
    
     public List getListDK() {
        return ListDK;
    }

    public void setListDK(List ListDK) {
        this.ListDK = ListDK;
    }

    public List getListVal() {
        return ListVal;
    }

    public void setListVal(List ListVal) {
        this.ListVal = ListVal;
    }
    
    public List<tracuuinfo_listgt> getListgt() {
        return listgt;
    }

    public void setListgt(List<tracuuinfo_listgt> listgt) {
        this.listgt = listgt;
    }
    
    public String getChkexcel() {
        return chkexcel;
    }

    public void setChkexcel(String chkexcel) {
        this.chkexcel = chkexcel;
    }

//</editor-fold>   
}
