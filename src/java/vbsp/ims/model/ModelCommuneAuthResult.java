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
public class ModelCommuneAuthResult {
    public String sPGD;
    public Date dNgayBC;
    public String dNgaytao;

    public String getdNgaytao() {
        return dNgaytao;
    }

    public void setdNgaytao(String dNgaytao) {
        this.dNgaytao = dNgaytao;
    }
    public BigDecimal bSodong;
   

    
//</editor-fol

    public String getsPGD() {
        return sPGD;
    }

    public void setsPGD(String sPGD) {
        this.sPGD = sPGD;
    }


    public Date getdNgayBC() {
        return dNgayBC;
    }

    public void setdNgayBC(Date dNgayBC) {
        this.dNgayBC = dNgayBC;
    }

   

    public BigDecimal getbSodong() {
        return bSodong;
    }

    public void setbSodong(BigDecimal bSodong) {
        this.bSodong = bSodong;
    }
}
