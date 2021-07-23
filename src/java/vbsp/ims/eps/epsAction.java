/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.eps;

import vbsp.ims.khnv2021.PosClass;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class epsAction extends ActionSupport {

    //Cac truong chua thong tin bo xung luu du lieu
    private String capbc, tendn, ngaybc, madv, status, linkReport,matinh;
    private InputStream pageResult;
    private Map session;
    private List<PosClass> lstPos = new ArrayList<>();
    private List<PosClass> lstCN = new ArrayList<>();
    private List<epsGetSetSL> lstDetail = new ArrayList<>();
    private List<String> macn = new ArrayList<>();
    private List<String> mapgd = new ArrayList<>();
    private List<String> makh = new ArrayList<>();
    private List<String> ngaysl = new ArrayList<>();
    private List<String> soku = new ArrayList<>();
    private List<String> chotsl = new ArrayList<>();
    private List<String> nguyennhan = new ArrayList<>();

    @Override
    //Cấp CN và TW
    public String execute() throws Exception {
        //Lấy danh sách đơn vị theo cấp báo cáo
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");
        lstPos = new epsModel().getPGD(capbc, tendn);
        lstCN = new epsModel().getChiNhanh(capbc, tendn);
        return SUCCESS;
    }
    
    public String getCNToPGD() throws Exception {
        //Lấy danh sách đơn vị theo cấp báo cáo
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        lstPos = new epsModel().getCNToPGD(capbc, matinh);
        
        return SUCCESS;
    }

    //Hàm lấy số liệu cho cấp CN và TW
    public String getAllData() throws ParseException {
        //Lấy số liệu phản hồi
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(ngaybc);
        DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
        ngaybc = df3.format(date);
        lstDetail = new epsModel().getAllData(capbc, tendn, madv, ngaybc, status);
        return SUCCESS;
    }

    //Cấp PGD
    public String xemsleps() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(ngaybc);
        DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
        ngaybc = df3.format(date);
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");
        lstDetail = new epsModel().xemsleps(capbc, tendn,ngaybc);
        return SUCCESS;
    }

    public String luusleps() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(ngaybc);
        DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
        ngaybc = df3.format(date);
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");
        new epsModel().luusleps(macn, mapgd, makh, ngaybc, soku, chotsl, nguyennhan);
        lstDetail = new epsModel().xemsleps(capbc, tendn,ngaybc);
        return SUCCESS;
    }
    
    public String xacnhansleps() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(ngaybc);
        DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
        ngaybc = df3.format(date);
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");
        new epsModel().xacnhansleps(macn, mapgd, makh, ngaybc, soku, chotsl, nguyennhan);
        lstDetail = new epsModel().xemsleps(capbc, tendn,ngaybc);
        return SUCCESS;
    }

//<editor-fold defaultstate="collapsed" desc="Thuộc tính GET SET">
    public void setCapbc(String capbc) {
        this.capbc = capbc;
    }

    public String getMatinh() {
        return matinh;
    }

    public void setMatinh(String matinh) {
        this.matinh = matinh;
    }

    public List<PosClass> getLstCN() {
        return lstCN;
    }

    public void setLstCN(List<PosClass> lstCN) {
        this.lstCN = lstCN;
    }

    public List<String> getMacn() {
        return macn;
    }

    public void setMacn(List<String> macn) {
        this.macn = macn;
    }

    public List<String> getMapgd() {
        return mapgd;
    }

    public void setMapgd(List<String> mapgd) {
        this.mapgd = mapgd;
    }

    public List<String> getMakh() {
        return makh;
    }

    public void setMakh(List<String> makh) {
        this.makh = makh;
    }

    public List<String> getNgaysl() {
        return ngaysl;
    }

    public void setNgaysl(List<String> ngaysl) {
        this.ngaysl = ngaysl;
    }

    public List<String> getSoku() {
        return soku;
    }

    public void setSoku(List<String> soku) {
        this.soku = soku;
    }

    public List<String> getChotsl() {
        return chotsl;
    }

    public void setChotsl(List<String> chotsl) {
        this.chotsl = chotsl;
    }

    public List<String> getNguyennhan() {
        return nguyennhan;
    }

    public void setNguyennhan(List<String> nguyennhan) {
        this.nguyennhan = nguyennhan;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    public String getTendn() {
        return tendn;
    }

    public void setTendn(String tendn) {
        this.tendn = tendn;
    }

    public InputStream getPageResult() {
        return pageResult;
    }

    public void setPageResult(InputStream pageResult) {
        this.pageResult = pageResult;
    }

    public String getNgaybc() {
        return ngaybc;
    }

    public void setNgaybc(String ngaybc) {
        this.ngaybc = ngaybc;
    }

    public String getMadv() {
        return madv;
    }

    public void setMadv(String madv) {
        this.madv = madv;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public String getLinkReport() {
        return linkReport;
    }

    public void setLinkReport(String linkReport) {
        this.linkReport = linkReport;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<epsGetSetSL> getLstDetail() {
        return lstDetail;
    }

    public void setLstDetail(List<epsGetSetSL> lstDetail) {
        this.lstDetail = lstDetail;
    }

    public List<PosClass> getLstPos() {
        return lstPos;
    }

    public void setLstPos(List<PosClass> lstPos) {
        this.lstPos = lstPos;
    }

    //</editor-fold> 
}
