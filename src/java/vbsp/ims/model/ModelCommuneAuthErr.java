/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author BAOANH
 */
public class ModelCommuneAuthErr {
    public String sMaPGD;
    public String sTenPGD;
    public String sMaXa;
    public String sTenxa;

    public String getsTenxa() {
        return sTenxa;
    }

    public void setsTenxa(String sTenxa) {
        this.sTenxa = sTenxa;
    }
    public BigDecimal sSodong;

    public BigDecimal getsSodong() {
        return sSodong;
    }

    public void setsSodong(BigDecimal sSodong) {
        this.sSodong = sSodong;
    }

    public String getsMaPGD() {
        return sMaPGD;
    }

    public void setsMaPGD(String sMaPGD) {
        this.sMaPGD = sMaPGD;
    }

    public String getsTenPGD() {
        return sTenPGD;
    }

    public void setsTenPGD(String sTenPGD) {
        this.sTenPGD = sTenPGD;
    }

    public String getsMaXa() {
        return sMaXa;
    }

    public void setsMaXa(String sMaXa) {
        this.sMaXa = sMaXa;
    }  

}
