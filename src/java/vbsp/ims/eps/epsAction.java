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
import com.sun.xml.bind.StringInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class epsAction extends ActionSupport {

    //Cac truong chua thong tin bo xung luu du lieu
    private String capbc, tendn, ngaybc, madv, status, linkReport;
    private int dachot, chuachot, chotsai;
    private InputStream pageResult;
    private Map session;
    private List<PosClass> lstPos = new ArrayList<>();
    private List<epsGetSet_NT> lstData = new ArrayList<>();

    @Override
    //Lấy danh đơn vị theo cấp báo cáo
    public String execute() throws Exception {
        //Lấy danh sách đơn vị theo cấp báo cáo
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");
        lstPos = new epsModel().getPGD(capbc, tendn);
        return SUCCESS;
    }

    public String getAllData() throws ParseException {
        //Lấy số liệu phản hồi
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(ngaybc);
        DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
        ngaybc = df3.format(date);
        List<Object> obj = new epsModel().getAllData(capbc, tendn, madv, ngaybc, status);
        lstData = (List<epsGetSet_NT>) obj.get(0);
        dachot = (int) obj.get(1);
        chuachot = (int) obj.get(2);
        chotsai = (int) obj.get(3);
        return SUCCESS;
    }

    public String getMenuReport() {
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");
        linkReport = new epsModel().getMenuIdBc();
        pageResult = new StringBufferInputStream(linkReport);
        return SUCCESS;
    }
    
    public String sendatatw() {
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");
        linkReport = new epsModel().sendatatw();
        pageResult = new StringInputStream(linkReport);
        return SUCCESS;
    }

//<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public void setCapbc(String capbc) {
        this.capbc = capbc;
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

    public int getDachot() {
        return dachot;
    }

    public void setDachot(int dachot) {
        this.dachot = dachot;
    }

    public int getChuachot() {
        return chuachot;
    }

    public void setChuachot(int chuachot) {
        this.chuachot = chuachot;
    }

    public int getChotsai() {
        return chotsai;
    }

    public void setChotsai(int chotsai) {
        this.chotsai = chotsai;
    }

    public List<PosClass> getLstPos() {
        return lstPos;
    }

    public void setLstPos(List<PosClass> lstPos) {
        this.lstPos = lstPos;
    }

    public List<epsGetSet_NT> getLstData() {
        return lstData;
    }

    public void setLstData(List<epsGetSet_NT> lstData) {
        this.lstData = lstData;
    }
    //</editor-fold> 
}
