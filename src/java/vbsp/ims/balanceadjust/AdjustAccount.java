/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.balanceadjust;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Trung
 */
public class AdjustAccount extends Object
        implements Serializable {

    private String tk;
    private String mapos;
    private String kybc;
    private String ngaybc;
    private String tonghop;

    private BigDecimal ddno;
    private BigDecimal ddco;
    private BigDecimal psno;
    private BigDecimal psco;
    private BigDecimal dcno;
    private BigDecimal dcco;

    private BigDecimal ddno_dc;
    private BigDecimal ddco_dc;
    private BigDecimal psno_dc;
    private BigDecimal psco_dc;
    private BigDecimal dcno_dc;
    private BigDecimal dcco_dc;

    public AdjustAccount() {
        //this.tk = "1111111111111";
        this.ddno = new BigDecimal("0");
        this.ddco = new BigDecimal("0");
        this.psno = new BigDecimal("0");
        this.psco = new BigDecimal("0");
        this.dcno = new BigDecimal("0");
        this.dcco = new BigDecimal("0");
        this.ddno_dc = new BigDecimal("0");
        this.ddco_dc = new BigDecimal("0");
        this.psno_dc = new BigDecimal("0");
        this.psco_dc = new BigDecimal("0");
        this.dcno_dc = new BigDecimal("0");
        this.dcco_dc = new BigDecimal("0");
    }

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    public String getMapos() {
        return mapos;
    }

    public void setMapos(String mapos) {
        this.mapos = mapos;
    }

    public String getKybc() {
        return kybc;
    }

    public void setKybc(String kybc) {
        this.kybc = kybc;
    }

    public String getNgaybc() {
        return ngaybc;
    }

    public void setNgaybc(String ngaybc) {
        this.ngaybc = ngaybc;
    }

    public String getTonghop() {
        return tonghop;
    }

    public void setTonghop(String tonghop) {
        this.tonghop = tonghop;
    }

    public BigDecimal getDdno() {
        return ddno;
    }

    public void setDdno(BigDecimal ddno) {
        this.ddno = ddno;
    }

    public BigDecimal getDdco() {
        return ddco;
    }

    public void setDdco(BigDecimal ddco) {
        this.ddco = ddco;
    }

    public BigDecimal getPsno() {
        return psno;
    }

    public void setPsno(BigDecimal psno) {
        this.psno = psno;
    }

    public BigDecimal getPsco() {
        return psco;
    }

    public void setPsco(BigDecimal psco) {
        this.psco = psco;
    }

    public BigDecimal getDcno() {
        return dcno;
    }

    public void setDcno(BigDecimal dcno) {
        this.dcno = dcno;
    }

    public BigDecimal getDcco() {
        return dcco;
    }

    public void setDcco(BigDecimal dcco) {
        this.dcco = dcco;
    }

    public BigDecimal getDdno_dc() {
        return ddno_dc;
    }

    public void setDdno_dc(BigDecimal ddno_dc) {
        this.ddno_dc = ddno_dc;
    }

    public BigDecimal getDdco_dc() {
        return ddco_dc;
    }

    public void setDdco_dc(BigDecimal ddco_dc) {
        this.ddco_dc = ddco_dc;
    }

    public BigDecimal getPsno_dc() {
        return psno_dc;
    }

    public void setPsno_dc(BigDecimal psno_dc) {
        this.psno_dc = psno_dc;
    }

    public BigDecimal getPsco_dc() {
        return psco_dc;
    }

    public void setPsco_dc(BigDecimal psco_dc) {
        this.psco_dc = psco_dc;
    }

    public BigDecimal getDcno_dc() {
        return dcno_dc;
    }

    public void setDcno_dc(BigDecimal dcno_dc) {
        this.dcno_dc = dcno_dc;
    }

    public BigDecimal getDcco_dc() {
        return dcco_dc;
    }

    public void setDcco_dc(BigDecimal dcco_dc) {
        this.dcco_dc = dcco_dc;
    }

    

    @Override
    public String toString() {
        return tk + "~" + mapos + "~" + kybc + "~" + ngaybc + "~" + tonghop;
    }

}
