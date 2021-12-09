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
import java.text.ParseException;
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
    private String flgFilter;
    List<clsCanBo> lstCanBo = new ArrayList<>();
    List<QT_DULIEU_NT> lstData = new ArrayList<>();
    private String capbc, tendn;
    private Map session;
    private String displaNone = "200";
    private List<QT_DULIEU_NT> lstDulieuNt = new ArrayList<>();  

    public TKActionSupport() {
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");
    }

    public String index() {
        lstCanBo = new clsHuyDongTK().getCanBo(capbc, tendn);
        return SUCCESS;
    }

    public String viewdata() throws ParseException {
        displaNone = new clsHuyDongTK().CheckNgayBC(dtNgaybc);
        if(flgFilter != null){flgFilter ="on";}else{flgFilter="off";};
        lstData = new clsHuyDongTK().getData(dtNgaybc, tendn, capbc, cboCanBo, flgFilter);
        return SUCCESS;
    }

    public String savedata() {
        String code = new clsHuyDongTK().saveData(dtNgaybc, tendn, capbc, cboCanBo, txtChitieu, chkChon,lstDulieuNt);
        /*Hàm gọi REST API để đẩy dữ liệu về TW*/
        int Status = new HDTKRestApi().insertHDTK(dtNgaybc, tendn, capbc, cboCanBo,txtChitieu, chkChon);
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

    public String getDisplaNone() {
        return displaNone;
    }

    public void setDisplaNone(String displaNone) {
        this.displaNone = displaNone;
    }
    
    public List<QT_DULIEU_NT> getLstDulieuNt() {
        return lstDulieuNt;
    }

    public void setLstDulieuNt(List<QT_DULIEU_NT> lstDulieuNt) {
        this.lstDulieuNt = lstDulieuNt;
    }
    
    public String getFlgFilter() {
        return flgFilter;
    }

    public void setFlgFilter(String flgFilter) {
        this.flgFilter = flgFilter;
    }

//</editor-fold>  
}
