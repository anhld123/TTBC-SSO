/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.huydongtk;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;

/**
 *
 * @author WELCOME
 */
public class TKActionSupport extends ActionSupport {
    
    private InputStream pageResult;
    private ArrayList<String> chkChon = new ArrayList<>();
    private String dtNgaybc;
    private String txtChitieu;
    private String cboCanBo;
    List<clsCanBo> lstCanBo = new ArrayList<>();
    List<QT_DULIEU_NT> lstData = new ArrayList<>();
    private String capbc, tendn, ngaybc;
    private Map session;
    

    public TKActionSupport() {
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");
    }
    
    public String index() {
        lstCanBo = new clsHuyDongTK().getCanBo(capbc,tendn);
        return SUCCESS;
    }
    
    public String viewdata(){
        lstData = new clsHuyDongTK().getData(dtNgaybc, tendn, capbc, cboCanBo);
        return SUCCESS;
    }
    
    public String savedata(){
        String code = new clsHuyDongTK().saveData(dtNgaybc, tendn, capbc, cboCanBo,txtChitieu, chkChon);
        pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    //<editor-fold defaultstate="collapsed" desc="Phương thức Get set">
    public InputStream getPageResult() {
        return pageResult;
    }

    public void setPageResult(InputStream pageResult) {
        this.pageResult = pageResult;
    }

    public ArrayList<String> getChkChon() {
        return chkChon;
    }

    public void setChkChon(ArrayList<String> chkChon) {
        this.chkChon = chkChon;
    }

    public String getDtNgaybc() {
        return dtNgaybc;
    }

    public void setDtNgaybc(String dtNgaybc) {
        this.dtNgaybc = dtNgaybc;
    }

    public String getTxtChitieu() {
        return txtChitieu;
    }

    public void setTxtChitieu(String txtChitieu) {
        this.txtChitieu = txtChitieu;
    }
    
    public List<clsCanBo> getLstCanBo() {
        return lstCanBo;
    }

    public void setLstCanBo(List<clsCanBo> lstCanBo) {
        this.lstCanBo = lstCanBo;
    }
    
    public List<QT_DULIEU_NT> getLstData() {
        return lstData;
    }

    public void setLstData(List<QT_DULIEU_NT> lstData) {
        this.lstData = lstData;
    }
     public String getCboCanBo() {
        return cboCanBo;
    }

    public void setCboCanBo(String cboCanBo) {
        this.cboCanBo = cboCanBo;
    }

//</editor-fold>  
}
