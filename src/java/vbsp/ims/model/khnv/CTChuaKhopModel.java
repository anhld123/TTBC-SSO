package vbsp.ims.model.khnv;

/**
 *
 * @author CuongBM0211
 */
public class CTChuaKhopModel {
    private String maPOS;
    private String tenPOS;
    private String maCT;
    private String tenCT;
    private double khGiaoTW;    //Ke hoach trung uong giao
    private double khGiaoCN;
    
    public CTChuaKhopModel(){
    
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public String getMaPOS() {
        return maPOS;
    }
    
    public void setMaPOS(String maPOS) {
        this.maPOS = maPOS;
    }
    
    public String getTenPOS() {
        return tenPOS;
    }
    
    public void setTenPOS(String tenPOS) {
        this.tenPOS = tenPOS;
    }
    
    public String getMaCT() {
        return maCT;
    }
    
    public void setMaCT(String maCT) {
        this.maCT = maCT;
    }
    
    public String getTenCT() {
        return tenCT;
    }
    
    public void setTenCT(String tenCT) {
        this.tenCT = tenCT;
    }
    
    public double getKhGiaoTW() {
        return khGiaoTW;
    }

    public void setKhGiaoTW(double khGiaoTW) {
        this.khGiaoTW = khGiaoTW;
    }

    public double getKhGiaoCN() {
        return khGiaoCN;
    }

    public void setKhGiaoCN(double khGiaoCN) {
        this.khGiaoCN = khGiaoCN;
    }    
//</editor-fold>

    
}
