package vbsp.ims.model.khnv;

import java.util.ArrayList;

/**
 *
 * @author CuongBM0211
 */
public class ChiTieuDetail {
    //Cac truong dung cho getChiTieuDetail
    private String maCt;    //Ma chi tieu
    private String tenCt;   //Ten chi tieu
    private String ctDP;    //Chi tieu dia phuong
    private double khDuocGiao;  //Ke hoach duoc giao, chi dung khi nguoi dung dang nhap cap chi nhánh
    private ArrayList<CTieuKHoachModel> cTieuKHoachModelList;
    
    public ChiTieuDetail(){
    
    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public String getMaCt() {
        return maCt;
    }
    
    public void setMaCt(String maCt) {
        this.maCt = maCt;
    }
    
    public String getTenCt() {
        return tenCt;
    }
    
    public void setTenCt(String tenCt) {
        this.tenCt = tenCt;
    }
    
    public double getKhDuocGiao() {
        return khDuocGiao;
    }
    
    public void setKhDuocGiao(double khDuocGiao) {
        this.khDuocGiao = khDuocGiao;
    }
    
    public ArrayList<CTieuKHoachModel> getcTieuKHoachModelList() {
        return cTieuKHoachModelList;
    }
    
    public void setcTieuKHoachModelList(ArrayList<CTieuKHoachModel> cTieuKHoachModelList) {
        this.cTieuKHoachModelList = cTieuKHoachModelList;
    }
    
    
    public String getCtDP() {
        return ctDP;
    }

    public void setCtDP(String ctDP) {
        this.ctDP = ctDP;
    }
//</editor-fold>

    
    
}
