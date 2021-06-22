/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.khnv2021;

import vbsp.ims.action.khnv.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.define.DefineFun;
import vbsp.ims.model.DownloadFileInfor;
import vbsp.ims.model.khnv.POSModel;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author BAOANH
 */
public class ctionMainKHNV2021 extends ActionSupport {

    //Cac truong chua thong tin bo xung luu du lieu
    protected String userId; //user đăng nhập chương trình
    protected String pos_cd_username; //pos cd user
    protected String namBc;//năm báo cáo
    protected String namSau;//năm báo cáo +1
    protected String maCn;//mã chi nhánh
    protected String reportGrade; //cấp báo cao
    protected List<ListValue> lstXa = new ArrayList<>();
    protected String pos_cd;//lay ra ma pos, ma xa tu combobox
    protected String commune_cd;
    protected String lock_unlock;
    protected String commune_detai;
    protected String subcommune_cd;
    protected String maBc;
//    protected String namBc;
    protected String dotBc;
    protected List<QT_DULIEU_NT> lstDulieuNt = new ArrayList<>();
    
    
    protected String defaultYearReport;
    protected DaoListPosFromUser listKTNBDA = new DaoListPosFromUser();
    protected PosMainModel posMainModel;
    public List<POSModel> posList = new ArrayList<>();
    public List<POSModel> subCommuneList = new ArrayList<>();
    protected List<ListValue> lstMaBC = new ArrayList<>();
    protected List<ListValue> lstNamBC = new ArrayList<>();
    protected List<ListValue> lstDotBC = new ArrayList<>();
    protected List<ListValue> lstTongHop = new ArrayList<>();
    protected String namBc_pre;
    
    
    protected HttpServletRequest request = null;
    protected String filereport;
    protected String query;
    protected String fileNamelocal;
    protected List<String> lstOfTextFile = new ArrayList<>();
    protected List<DownloadFileInfor> filesList = new ArrayList<>();
    protected List<String> zipFileList = new ArrayList<>();
    
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

    public String getLock_unlock() {
        return lock_unlock;
    }

    public void setLock_unlock(String lock_unlock) {
        this.lock_unlock = lock_unlock;
    }
    

    public String getCommune_detai() {
        return commune_detai;
    }

    public void setCommune_detai(String commune_detai) {
        this.commune_detai = commune_detai;
    }
    
    

    public List<QT_DULIEU_NT> getLstDulieuNt() {
        return lstDulieuNt;
    }

    public void setLstDulieuNt(List<QT_DULIEU_NT> lstDulieuNt) {
        this.lstDulieuNt = lstDulieuNt;
    }
        

    public String getCommune_cd() {
        return commune_cd;
    }

    public void setCommune_cd(String commune_cd) {
        this.commune_cd = commune_cd;
    }

    public String getSubcommune_cd() {
        return subcommune_cd;
    }

    public void setSubcommune_cd(String subcommune_cd) {
        this.subcommune_cd = subcommune_cd;
    }

    public String getMaBc() {
        return maBc;
    }

    public void setMaBc(String maBc) {
        this.maBc = maBc;
    }

    public String getDotBc() {
        return dotBc;
    }

    public void setDotBc(String dotBc) {
        this.dotBc = dotBc;
    }
    
    

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getFilereport() {
        return filereport;
    }

    public void setFilereport(String filereport) {
        this.filereport = filereport;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getFileNamelocal() {
        return fileNamelocal;
    }

    public void setFileNamelocal(String fileNamelocal) {
        this.fileNamelocal = fileNamelocal;
    }

    public List<String> getLstOfTextFile() {
        return lstOfTextFile;
    }

    public void setLstOfTextFile(List<String> lstOfTextFile) {
        this.lstOfTextFile = lstOfTextFile;
    }

    public List<DownloadFileInfor> getFilesList() {
        return filesList;
    }

    public void setFilesList(List<DownloadFileInfor> filesList) {
        this.filesList = filesList;
    }

    public List<String> getZipFileList() {
        return zipFileList;
    }

    public void setZipFileList(List<String> zipFileList) {
        this.zipFileList = zipFileList;
    }
    
    

    public List<ListValue> getLstTongHop() {
        return lstTongHop;
    }

    public void setLstTongHop(List<ListValue> lstTongHop) {
        this.lstTongHop = lstTongHop;
    }
    
    

    public List<ListValue> getLstDotBC() {
        return lstDotBC;
    }

    public void setLstDotBC(List<ListValue> lstDotBC) {
        this.lstDotBC = lstDotBC;
    }

    
    public List<ListValue> getLstMaBC() {
        return lstMaBC;
    }

    public void setLstMaBC(List<ListValue> lstMaBC) {
        this.lstMaBC = lstMaBC;
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


    public String getDefaultYearReport() {
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    }

    public void setDefaultYearReport(String defaultYearReport) {
        this.defaultYearReport = defaultYearReport;
    }
    
        public List<POSModel> getSubCommuneList() {
        return subCommuneList;
    }

    public void setSubCommuneList(List<POSModel> subCommuneList) {
        this.subCommuneList = subCommuneList;
    }
        public List<ListValue> getLstNamBC() {
        return lstNamBC;
    }

    public void setLstNamBC(List<ListValue> lstNamBC) {
        this.lstNamBC = lstNamBC;
    }

    public String getNamBc_pre() {
        return namBc_pre;
    }

    public void setNamBc_pre(String namBc_pre) {
        this.namBc_pre = namBc_pre;
    }
    
    //</editor-fold>





}
