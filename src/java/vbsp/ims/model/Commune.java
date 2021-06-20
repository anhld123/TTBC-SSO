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
public class Commune {
    private String commune_id;
    private String commune_name;
    private String gdx_flg;
    private String xa135_flg;
    private String status;
    private int ngaygdx;
    private String pgd_ql;
    private String nongthonmoi_flg;
    private String canbotdpt;
    private String maker_id;
    private String maker_dt;
    
    public Commune(){}
    
    public Commune(
            String commune_id,
            String commune_name, 
            String gdx_flg, 
            String xa135_flg,
            String status, 
            int ngaygdx,
            String pgd_ql,
            String nongthonmoi_flg,
            String canbotdpt){
        this.commune_id = commune_id;
        this.commune_name = commune_name;
        this.gdx_flg = gdx_flg;
        this.xa135_flg = xa135_flg;
        this.status = status;
        this.ngaygdx = ngaygdx;
        this.pgd_ql = pgd_ql;
        this.nongthonmoi_flg = nongthonmoi_flg;
        this.canbotdpt = canbotdpt;
    }
    
    
    public Commune(String commune_id,
            String commune_name, 
            String gdx_flg, 
            String xa135_flg,
            String status, 
            int ngaygdx,
            String pgd_ql,
            String nongthonmoi_flg,
            String canbotdpt,
            String maker_id,
            String maker_dt){
        this.commune_id = commune_id;
        this.commune_name = commune_name;
        this.gdx_flg = gdx_flg;
        this.xa135_flg = xa135_flg;
        this.status = status;
        this.ngaygdx = ngaygdx;
        this.pgd_ql = pgd_ql;
        this.nongthonmoi_flg = nongthonmoi_flg;
        this.maker_dt =maker_dt;
        this.maker_id = maker_id;
        this.canbotdpt = canbotdpt;
    }

    public String getCommune_id() {
        return commune_id;
    }

    public void setCommune_id(String commune_id) {
        this.commune_id = commune_id;
    }

    public String getCommune_name() {
        return commune_name;
    }

    public void setCommune_name(String commune_name) {
        this.commune_name = commune_name;
    }

    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNgaygdx() {
        return ngaygdx;
    }

    public void setNgaygdx(int ngaygdx) {
        this.ngaygdx = ngaygdx;
    }

    public String getPgd_ql() {
        return pgd_ql;
    }

    public void setPgd_ql(String pgd_ql) {
        this.pgd_ql = pgd_ql;
    }

    public String getGdx_flg() {
        return gdx_flg;
    }

    public void setGdx_flg(String gdx_flg) {
        this.gdx_flg = gdx_flg;
    }

    public String getXa135_flg() {
        return xa135_flg;
    }

    public void setXa135_flg(String xa135_flg) {
        this.xa135_flg = xa135_flg;
    }

    public String getNongthonmoi_flg() {
        return nongthonmoi_flg;
    }

    public void setNongthonmoi_flg(String nongthongmoi_flg) {
        this.nongthonmoi_flg = nongthongmoi_flg;
    }

    public String getMaker_id() {
        return maker_id;
    }

    public void setMaker_id(String maker_id) {
        this.maker_id = maker_id;
    }

    public String getMaker_dt() {
        return maker_dt;
    }

    public void setMaker_dt(String maker_dt) {
        this.maker_dt = maker_dt;
    }

    public String getCanbotdpt() {
        return canbotdpt;
    }

    public void setCanbotdpt(String canbotdpt) {
        this.canbotdpt = canbotdpt;
    }
    
    
    
}
