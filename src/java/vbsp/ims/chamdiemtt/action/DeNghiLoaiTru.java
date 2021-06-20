/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chamdiemtt.action;

/**
 *
 * @author HP
 */
public class DeNghiLoaiTru {
    
    private String Key;
    private String Code;
    
    private String MaPGD;
    private String NgayBC;    
    private Boolean CN_DeNghi_LoaiTru;
    private String CN_LyDo;
    private Boolean TW_Duyet_DeNghi;
    private String TW_LyDo;
    
    
    public DeNghiLoaiTru() {}

   

    public String getNgayBC() {
        return NgayBC;
    }

    public void setNgayBC(String NgayBC) {
        this.NgayBC = NgayBC;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String Key) {
        this.Key = Key;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getMaPGD() {
        return MaPGD;
    }

    public void setMaPGD(String MaPGD) {
        this.MaPGD = MaPGD;
    }

   
    
    public Boolean getCN_DeNghi_LoaiTru() {
        return CN_DeNghi_LoaiTru;
    }

    public void setCN_DeNghi_LoaiTru(Boolean CN_DeNghi_LoaiTru) {
        this.CN_DeNghi_LoaiTru = CN_DeNghi_LoaiTru;
    }

    public String getCN_LyDo() {
        return CN_LyDo;
    }

    public void setCN_LyDo(String CN_LyDo) {
        this.CN_LyDo = CN_LyDo;
    }

    public Boolean getTW_Duyet_DeNghi() {
        return TW_Duyet_DeNghi;
    }

    public void setTW_Duyet_DeNghi(Boolean TW_Duyet_DeNghi) {
        this.TW_Duyet_DeNghi = TW_Duyet_DeNghi;
    }

    public String getTW_LyDo() {
        return TW_LyDo;
    }

    public void setTW_LyDo(String TW_LyDo) {
        this.TW_LyDo = TW_LyDo;
    }
    
    
    
}
