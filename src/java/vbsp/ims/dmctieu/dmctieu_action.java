/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dmctieu;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.xml.ws.Holder;

public class dmctieu_action extends ActionSupport {

    private InputStream fileInputStream;
    private String PFile;
    private String nganhang;
    private String FInclude;
    private Map session;
    private String capbc;
    private String tendn;
    private Double pagecount;
    private List<dmctieu_util> lsdonvi;
    private List<dmctieu_util> lskybc;
    private List<dmctieu_util> lsdata;
    //Khai báo các biến dùng cho truy vấn
    private String cbodonvi;
    private String txtmact;
    private String txtngaybc;
    private String cbokybc;
    private String txtnganhang;
    private Integer cbotrang;
    private Double tongct;
    private Double tonggtri;
    //-----------------------------------

    public dmctieu_action() {
    }

    @Override
    public String execute() throws Exception {
        //Lấy danh sách cây chỉ tiêu
        if ("NHCS".equals(nganhang)) {
            FInclude = "<script type=\"text/javascript\" src=\"DMChitieu/js/tree01.js\"></script>";
        } else {
            FInclude = "<script type=\"text/javascript\" src=\"DMChitieu/js/tree02.js\"></script>";
        }
        dmctieu_Model.gentreeview(nganhang);
        //Thực hiện lấy danh sách các PGD,CN theo tên đăng nhâp
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");
        lsdonvi = new dmctieu_Model().getdsdonvi(capbc, tendn);
        //Lấy danh sách các kỳ báo cáo (Chỉ hiển thị cho NHNN)
        lskybc = new dmctieu_Model().getdskybc(nganhang);
        return "thanhcong";
    }

    public String excloact() throws ParseException {
        //nganhang,capbc,madv,tendn,nhomct,ngaybc,nstart,nend,totalpg
        //Lấy danh sách chỉ tiêu
        Holder<Double> getrec = new Holder(pagecount);
        Holder<Double> getsumgtr = new Holder(tonggtri);
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(txtngaybc);
        DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
        txtngaybc = df3.format(date);
        //-----------------------------------
        if (cbotrang == null) {
            cbotrang = 1;
        }
        Integer start = (cbotrang - 1) * (50 + 1);
        Integer end = cbotrang * 50;
        //-----------------------------------
        lsdata = new dmctieu_Model().getnhombc(txtnganhang, capbc, cbodonvi, tendn,txtmact, txtngaybc, start, end, cbokybc, getrec,getsumgtr);
        pagecount = getrec.value;
        tonggtri = getsumgtr.value;
        tongct = pagecount;
        pagecount = Math.ceil(pagecount / 50);
        return "thanhcong";
    }
    
    public String exchitieu() throws ParseException, FileNotFoundException {
        //Lấy danh sách chỉ tiêu
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(txtngaybc);
        DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
        txtngaybc = df3.format(date);
        //-----------------------------------
        Holder<String> FFName = new Holder(PFile);
        String SFile = new dmctieu_Model().xuatexcelct(txtnganhang, capbc, cbodonvi, tendn, 
                txtmact, txtngaybc,cbokybc,FFName);
        PFile = FFName.value;
        fileInputStream = new FileInputStream(new File(SFile));
        return "thanhcong";
    }

    public String getNganhang() {
        return nganhang;
    }

    public void setNganhang(String nganhang) {
        this.nganhang = nganhang;
    }

    public String getFInclude() {
        return FInclude;
    }

    public void setFInclude(String FInclude) {
        this.FInclude = FInclude;
    }

    public List<dmctieu_util> getLsdonvi() {
        return lsdonvi;
    }

    public void setLsdonvi(List<dmctieu_util> lsdonvi) {
        this.lsdonvi = lsdonvi;
    }

    public List<dmctieu_util> getLskybc() {
        return lskybc;
    }

    public void setLskybc(List<dmctieu_util> lskybc) {
        this.lskybc = lskybc;
    }

    public List<dmctieu_util> getLsdata() {
        return lsdata;
    }

    public void setLsdata(List<dmctieu_util> lsdata) {
        this.lsdata = lsdata;
    }

    public Double getPagecount() {
        return pagecount;
    }

    public void setPagecount(Double pagecount) {
        this.pagecount = pagecount;
    }

    public String getCbodonvi() {
        return cbodonvi;
    }

    public void setCbodonvi(String cbodonvi) {
        this.cbodonvi = cbodonvi;
    }

    public String getTxtmact() {
        return txtmact;
    }

    public void setTxtmact(String txtmact) {
        this.txtmact = txtmact;
    }

    public String getTxtngaybc() {
        return txtngaybc;
    }

    public void setTxtngaybc(String txtngaybc) {
        this.txtngaybc = txtngaybc;
    }

    public String getCbokybc() {
        return cbokybc;
    }

    public void setCbokybc(String cbokybc) {
        this.cbokybc = cbokybc;
    }

    public String getTxtnganhang() {
        return txtnganhang;
    }

    public void setTxtnganhang(String txtnganhang) {
        this.txtnganhang = txtnganhang;
    }

    public Integer getCbotrang() {
        return cbotrang;
    }

    public void setCbotrang(Integer cbotrang) {
        this.cbotrang = cbotrang;
    }

    public Double getTongct() {
        return tongct;
    }

    public void setTongct(Double tongct) {
        this.tongct = tongct;
    }

    public Double getTonggtri() {
        return tonggtri;
    }

    public void setTonggtri(Double tonggtri) {
        this.tonggtri = tonggtri;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getPFile() {
        return PFile;
    }

    public void setPFile(String PFile) {
        this.PFile = PFile;
    }

}
