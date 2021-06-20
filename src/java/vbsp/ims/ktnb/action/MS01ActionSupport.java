/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.ktnb.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import vbsp.ims.ktnb.model.MS01Model;
import vbsp.ims.ktnb.model.MS01DA;

/**
 * Cần xử lý: 
 * Check tên đăng nhập để lấy ra được Mã CN + Mã PGD
 */
public class MS01ActionSupport extends ActionSupport {
    private String cbokybc;
    private String cbonam;
    private List<MS01Model> DSMS01;
    String Mess = null;

    public MS01ActionSupport() {
    }

    public String execute() throws Exception {
        DSMS01 = new MS01DA().getMS01();
        return SUCCESS;
    }

    public void CapnhatBC() {
        try {
            MS01Model lsms01 = new MS01Model();
            lsms01.setKT_CO_DINH("Tên biến");
            new MS01DA().CapnhatMS01(lsms01);
            Mess = "Cập nhật thành công.";
        } catch (Exception ex) {
            Mess = "Cập nhật thất bại.";
        }
    }

    public List<MS01Model> getDSMS01() {
        return DSMS01;
    }

    public void setDSMS01(List<MS01Model> DSMS01) {
        this.DSMS01 = DSMS01;
    }

    public String getMess() {
        return Mess;
    }

    public void setMess(String Mess) {
        this.Mess = Mess;
    }

    public String getCbokybc() {
        return cbokybc;
    }

    public void setCbokybc(String cbokybc) {
        this.cbokybc = cbokybc;
    }

    public String getCbonam() {
        return cbonam;
    }

    public void setCbonam(String cbonam) {
        this.cbonam = cbonam;
    }

}
