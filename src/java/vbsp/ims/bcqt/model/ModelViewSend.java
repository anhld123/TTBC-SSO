/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.bcqt.model;

/**
 *
 * @author BAOANH
 */
public class ModelViewSend {
    public String mapgd;
    public String tenpgd;
    public String mota_loi;
    public String Status;
    public Integer key;
    
    public static ModelViewSend newInstance()
    {
        return new ModelViewSend();
    }
    public String getMapgd() {
        return mapgd;
    }

    public void setMapgd(String mapgd) {
        this.mapgd = mapgd;
    }

    public String getTenpgd() {
        return tenpgd;
    }

    public void setTenpgd(String tenpgd) {
        this.tenpgd = tenpgd;
    }

    public String getMota_loi() {
        return mota_loi;
    }

    public void setMota_loi(String mota_loi) {
        this.mota_loi = mota_loi;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
    
    
}
