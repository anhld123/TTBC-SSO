/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action.khnv;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.define.DefineFun;
import vbsp.ims.model.khnv.POSModel;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author BAOANH
 */
public class actionMainKHNV extends ActionSupport {

    //Cac truong chua thong tin bo xung luu du lieu
    protected String userId; //user đăng nhập chương trình
    protected String pos_cd_username; //pos cd user
    protected String namBc;//năm báo cáo
    protected String namSau;//năm báo cáo +1
    protected String maCn;//mã chi nhánh
    protected String reportGrade; //cấp báo cao
    protected List<ListValue> lstXa = new ArrayList<>();
    protected String pos_cd;//lay ra ma pos, ma xa tu combobox
    
    protected List<String> lstYearReport = new ArrayList<String>();
    protected String defaultYearReport;
    protected DaoListPosFromUser listKTNBDA = new DaoListPosFromUser();
    protected PosMainModel posMainModel;
    public List<POSModel> posList = new ArrayList<>();
    protected String xa_pgd;
    
    public String getPathRoot() throws Exception {
        String path = ServletActionContext.getServletContext().getRealPath("/");
        path = DefineFun.backlashReplace(path);
        if (!path.endsWith("/") || !path.endsWith("\\")) {
            path += "/";
        }
        return path;
    }

    public void getInfo() throws Exception {

        Map session = ActionContext.getContext().getSession();

        if (session == null || session.size() == 0 || session.isEmpty()) {
            throw new Exception("Bạn phải đăng nhập lại mới thực hiện được chức năng này");

        }
        //lay ra user
        setUserId(session.get("username").toString());

//            System.err.println("execute sUserName=" + sUserName);
        if (userId == null || userId.isEmpty()) {
            throw new Exception("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");

        }
        setReportGrade(session.get("reportGrade").toString());
        if (reportGrade == null || reportGrade.isEmpty()) {
            throw new Exception("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");

        }

        //Lay thong tin ma phogn giao dich, ma chi nhanh
        posMainModel = listKTNBDA.get_pos_main_pos(userId, reportGrade);
        pos_cd_username = posMainModel.getPosCd();
        maCn = posMainModel.getMainPosCd();
        if (namBc == null) {
            namBc = getDefaultYearReport();
        }
        namSau = Integer.toString(Integer.parseInt(namBc) + 1);
        setLstXa(posMainModel.getLstXa());
    }

    //<editor-fold defaultstate="collapsed" desc="get set du lieu">

    public String getXa_pgd() {
        return xa_pgd;
    }

    public void setXa_pgd(String xa_pgd) {
        this.xa_pgd = xa_pgd;
    }

    
    public String getPos_cd() {
        return pos_cd;
    }

    public void setPos_cd(String pos_cd) {
        this.pos_cd = pos_cd;
    }

    
    public List<POSModel> getPosList() {
        return posList;
    }

    public void setPosList(List<POSModel> posList) {
        this.posList = posList;
    }
    
    
    
    public List<ListValue> getLstXa() {
        return lstXa;
    }

    public void setLstXa(List<ListValue> lstXa) {
        this.lstXa = lstXa;
    }

    public PosMainModel getPosMainModel() {
        return posMainModel;
    }

    public void setPosMainModel(PosMainModel posMainModel) {
        this.posMainModel = posMainModel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPos_cd_username() {
        return pos_cd_username;
    }

    public void setPos_cd_username(String pos_cd_username) {
        this.pos_cd_username = pos_cd_username;
    }

    public String getNamBc() {
        return namBc;
    }

    public void setNamBc(String namBc) {
        this.namBc = namBc;
    }

    public String getNamSau() {
        return namSau;
    }

    public void setNamSau(String namSau) {
        this.namSau = namSau;
    }

    public String getMaCn() {
        return maCn;
    }

    public void setMaCn(String maCn) {
        this.maCn = maCn;
    }

    public String getReportGrade() {
        return reportGrade;
    }

    public void setReportGrade(String reportGrade) {
        this.reportGrade = reportGrade;
    }

    public List<String> getLstYearReport() {
        return lstYearReport;
    }

    public void setLstYearReport(List<String> lstYearReport) {
        this.lstYearReport = lstYearReport;
    }

    public String getDefaultYearReport() {
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    }

    public void setDefaultYearReport(String defaultYearReport) {
        this.defaultYearReport = defaultYearReport;
    }
    //</editor-fold>
}
