/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import com.opensymphony.xwork2.validator.annotations.DoubleRangeFieldValidator;
import java.math.BigDecimal;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

/**
 *
 * @author BAOANH
 */
public class ModelCommuneRejectTW {

    public String getsCN() {
        return sCN;
    }

    public void setsCN(String sCN) {
        this.sCN = sCN;
    }

    public String getsTrangthai() {
        return sTrangthai;
    }

    public void setsTrangthai(String sTrangthai) {
        this.sTrangthai = sTrangthai;
    }
    public String sCN;
    public String sTrangthai;    
}
