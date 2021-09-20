/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.ketquaKTDC;

import vbsp.ims.khnv2021.*;
import vbsp.ims.action.khnv.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.DownloadFileInfor;
import vbsp.ims.model.khnv.POSModel;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author HP
 */
public class ActionMainKTDC extends ActionSupport {

    //Cac truong chua thong tin bo xung luu du lieu
    protected String userId; //user đăng nhập chương trình
    protected String pos_cd_username; //pos cd user
    protected String maCn;//mã chi nhánh
    protected String reportGrade; //cấp báo cao
    protected List<QT_DULIEU_NT> lstDulieuNt = new ArrayList<>();
    protected List<QT_DULIEU_NT.saveDulieuNT_Phi> lstsaveNT_DAT = new ArrayList<QT_DULIEU_NT.saveDulieuNT_Phi>();

    protected List<ListValue> lstParameters = new ArrayList<>();

    public List<ListValue> lstMaBaocao = new ArrayList<>();
    public List<ListValue> lstDoituongKT = new ArrayList<>();
    public List<ListValue> lstHinhthucKT = new ArrayList<>();
    public List<ListValue> lstMaxa = new ArrayList<>();
//    public List<ListValue> lstDvut = new ArrayList<>();
//    public List<ListValue> lstMato = new ArrayList<>();
    public List<ListValue> lstMaCanbo = new ArrayList<>();
    public List<ListValue> lstMapgd = new ArrayList<>();
    public List<ListValue> lstTrangthaiKT = new ArrayList<>();

    protected String macn;
    
    protected String mabc;
    protected String ngay_kt;
    protected String ngay_bc;
    protected String doituongkt;
    protected String hinhthuckt;
    protected String canbokt;
    protected String canboinfo;
    protected String maxa;
    protected String dvut;
    protected String mato;
    protected String cust_search;
    
    protected String mapgd;
    protected String trangthai;
    

    public List<ListValue> lstBienphapXuly = new ArrayList<ListValue>();
    public List<ListValue> lstKetQuaHT = new ArrayList<ListValue>();
    public List<ListValue> lstNguyenNhanCL = new ArrayList<ListValue>();

    protected String ten_canbo;
    protected String timkiem;

    protected String defaultYearReport;
    protected DaoListPosFromUser listKTNBDA = new DaoListPosFromUser();
    protected PosMainModel posMainModel;
    public List<POSModel> posList = new ArrayList<>();

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

    protected HashMap<String, Object> getParameter() throws Exception {
        HashMap<String, Object> paramHashMap = new HashMap<>();
        Map<String, String[]> prameters = ServletActionContext.getRequest().getParameterMap();
        for (String parameter : prameters.keySet()) {
            String[] values = prameters.get(parameter);
            if (parameter.indexOf("TEXT") > 0 || parameter.indexOf("DATE") > 0 || parameter.indexOf("LIST") > 0) {
                if (parameter.startsWith("1_")) {
                    parameter = parameter.substring(2, parameter.length());
                }
                if (parameter.indexOf("DATE") > 0) {
                    Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
                    paramHashMap.put(parameter.substring(0, parameter.length() - 5), new SimpleDateFormat("dd-MMM-yyyy").format(sdf));
                    lstParameters.add(new ListValue(parameter, values[0]));
                } else {
                    paramHashMap.put(parameter.substring(0, parameter.length() - 5), values[0]);
                    lstParameters.add(new ListValue(parameter, values[0]));
                }
            } else {
                if (parameter.startsWith("1_")) {
                    parameter = parameter.substring(2, parameter.length());
                }
                if (parameter.equals("poscd")) {
                    paramHashMap.put(parameter, convertStringtoList(values));
                } else {
                    paramHashMap.put(parameter, values[0]);
                    lstParameters.add(new ListValue(parameter, values[0]));
                }
            }
        }
        return paramHashMap;
    }

    private List<String> convertStringtoList(String[] value) {
        List<String> lst = new ArrayList<>();
        try {
            for (int i = 0; i < value.length; i++) {
                if (!value[i].equals("999999") && !value[i].isEmpty()) {
                    lst.add(value[i]);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> convertStringtoList: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> convertStringtoList: " + e.getMessage());
        }
        return lst;
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

    }

    //<editor-fold defaultstate="collapsed" desc="get set du lieu">

    public List<ListValue> getLstMapgd() {
        return lstMapgd;
    }

    public void setLstMapgd(List<ListValue> lstMapgd) {
        this.lstMapgd = lstMapgd;
    }
    
    
    public List<ListValue> getLstBienphapXuly() {
        return lstBienphapXuly;
    }

    public void setLstBienphapXuly(List<ListValue> lstBienphapXuly) {
        this.lstBienphapXuly = lstBienphapXuly;
    }

    public List<QT_DULIEU_NT> getLstDulieuNt() {
        return lstDulieuNt;
    }

    public void setLstDulieuNt(List<QT_DULIEU_NT> lstDulieuNt) {
        this.lstDulieuNt = lstDulieuNt;
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

    public List<POSModel> getPosList() {
        return posList;
    }

    public void setPosList(List<POSModel> posList) {
        this.posList = posList;
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

    public List<ListValue> getLstParameters() {
        return lstParameters;
    }

    public void setLstParameters(List<ListValue> lstParameters) {
        this.lstParameters = lstParameters;
    }

    public String getTen_canbo() {
        return ten_canbo;
    }

    public void setTen_canbo(String ten_canbo) {
        this.ten_canbo = ten_canbo;
    }

    public String getTimkiem() {
        return timkiem;
    }

    public void setTimkiem(String timkiem) {
        this.timkiem = timkiem;
    }

    public DaoListPosFromUser getListKTNBDA() {
        return listKTNBDA;
    }

    public void setListKTNBDA(DaoListPosFromUser listKTNBDA) {
        this.listKTNBDA = listKTNBDA;
    }

    public List<ListValue> getLstMaBaocao() {
        return lstMaBaocao;
    }

    public void setLstMaBaocao(List<ListValue> lstMaBaocao) {
        this.lstMaBaocao = lstMaBaocao;
    }

    public List<ListValue> getLstDoituongKT() {
        return lstDoituongKT;
    }

    public void setLstDoituongKT(List<ListValue> lstDoituongKT) {
        this.lstDoituongKT = lstDoituongKT;
    }

    public List<ListValue> getLstHinhthucKT() {
        return lstHinhthucKT;
    }

    public void setLstHinhthucKT(List<ListValue> lstHinhthucKT) {
        this.lstHinhthucKT = lstHinhthucKT;
    }

    public List<ListValue> getLstMaxa() {
        return lstMaxa;
    }

    public void setLstMaxa(List<ListValue> lstMaxa) {
        this.lstMaxa = lstMaxa;
    }

    public String getMacn() {
        return macn;
    }

    public void setMacn(String macn) {
        this.macn = macn;
    }

    public String getNgay_kt() {
        return ngay_kt;
    }

    public void setNgay_kt(String ngay_kt) {
        this.ngay_kt = ngay_kt;
    }

    public String getNgay_bc() {
        return ngay_bc;
    }

    public void setNgay_bc(String ngay_bc) {
        this.ngay_bc = ngay_bc;
    }

    public String getDoituongkt() {
        return doituongkt;
    }

    public void setDoituongkt(String doituongkt) {
        this.doituongkt = doituongkt;
    }

    public String getHinhthuckt() {
        return hinhthuckt;
    }

    public void setHinhthuckt(String hinhthuckt) {
        this.hinhthuckt = hinhthuckt;
    }

    public String getCanbokt() {
        return canbokt;
    }

    public void setCanbokt(String canbokt) {
        this.canbokt = canbokt;
    }

    public String getCanboinfo() {
        return canboinfo;
    }

    public void setCanboinfo(String canboinfo) {
        this.canboinfo = canboinfo;
    }

    public String getMaxa() {
        return maxa;
    }

    public void setMaxa(String maxa) {
        this.maxa = maxa;
    }

    public String getDvut() {
        return dvut;
    }

    public void setDvut(String dvut) {
        this.dvut = dvut;
    }

    public String getMato() {
        return mato;
    }

    public void setMato(String mato) {
        this.mato = mato;
    }

   

    public String getCust_search() {
        return cust_search;
    }

    public void setCust_search(String cust_search) {
        this.cust_search = cust_search;
    }

    public String getMabc() {
        return mabc;
    }

    public void setMabc(String mabc) {
        this.mabc = mabc;
    }

    public List<QT_DULIEU_NT.saveDulieuNT_Phi> getLstsaveNT_DAT() {
        return lstsaveNT_DAT;
    }

    public void setLstsaveNT_DAT(List<QT_DULIEU_NT.saveDulieuNT_Phi> lstsaveNT_DAT) {
        this.lstsaveNT_DAT = lstsaveNT_DAT;
    }

    public List<ListValue> getLstMaCanbo() {
        return lstMaCanbo;
    }

    public void setLstMaCanbo(List<ListValue> lstMaCanbo) {
        this.lstMaCanbo = lstMaCanbo;
    }

    public List<ListValue> getLstKetQuaHT() {
        return lstKetQuaHT;
    }

    public void setLstKetQuaHT(List<ListValue> lstKetQuaHT) {
        this.lstKetQuaHT = lstKetQuaHT;
    }

    public List<ListValue> getLstNguyenNhanCL() {
        return lstNguyenNhanCL;
    }

    public void setLstNguyenNhanCL(List<ListValue> lstNguyenNhanCL) {
        this.lstNguyenNhanCL = lstNguyenNhanCL;
    }

    public List<ListValue> getLstTrangthaiKT() {
        return lstTrangthaiKT;
    }

    public void setLstTrangthaiKT(List<ListValue> lstTrangthaiKT) {
        this.lstTrangthaiKT = lstTrangthaiKT;
    }

    public String getMapgd() {
        return mapgd;
    }

    public void setMapgd(String mapgd) {
        this.mapgd = mapgd;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
    
    
    
}

    
    //</editor-fold>
