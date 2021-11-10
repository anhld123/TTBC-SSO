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
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author WELCOME
 */
public class TKActionSupport extends ActionSupport {
    
    private InputStream pageResult;
    private ArrayList<String> chkChon = new ArrayList<>();
    private String dtNgaybc;
    private String txtChitieu;
    List<clsCanBo> lstCanBo = new ArrayList<>();
    List<QT_DULIEU_NT> lstData = new ArrayList<>();
    private String capbc, tendn, ngaybc;
    private Map session;
    
    public TKActionSupport() {
    }
    
    @Override
    public String execute() throws Exception {
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");
        lstCanBo = new clsHuyDongTK().getCanBo(capbc,tendn);
        return "thanhcong";
    }
    
    public String viewdata(){
        //session = ActionContext.getContext().getSession();
        //capbc = (String) session.get("reportGrade");
        //tendn = (String) session.get("username");
        String sNgayBC = ServletActionContext.getRequest().getParameter("ReportDate");
        String sUserName = ServletActionContext.getRequest().getParameter("Username");
        String sCapBC = ServletActionContext.getRequest().getParameter("ReportGrade");
        lstData = new clsHuyDongTK().getData(sNgayBC, sUserName, sCapBC);
        return "thanhcong";
    }
    
    public String savedata(){
        pageResult = new ByteArrayInputStream("Lưu dữ liệu thành công.".getBytes(StandardCharsets.UTF_8));
        System.out.println(chkChon.toString());
        return "thanhcong";
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
    
//</editor-fold>

    

    
}
