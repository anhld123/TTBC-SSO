package vbsp.ims.model.khnv;

import java.math.BigDecimal;

/**
 *
 * @author CuongBM0211
 */
public class CTieuKHoachModel {
    private String maPGD;
    private String tenPGD;
    private BigDecimal giaoKh;  //Giao ke hoach
    
    public CTieuKHoachModel(){
    
    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public String getMaPGD() {
        return maPGD;
    }
    
    public void setMaPGD(String maPGD) {
        this.maPGD = maPGD;
    }
    
    public String getTenPGD() {
        return tenPGD;
    }
    
    public void setTenPGD(String tenPGD) {
        this.tenPGD = tenPGD;
    }
    
    public BigDecimal getGiaoKh() {
        return giaoKh;
    }
    
    public void setGiaoKh(BigDecimal giaoKh) {
        this.giaoKh = giaoKh;
    }
    
    
//</editor-fold>    
}
