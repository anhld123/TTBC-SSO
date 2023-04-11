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
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.XmlKtgsSync;

public class epsAction extends ActionSupport {

    //Cac truong chua thong tin bo xung luu du lieu
    private String capbc, tendn, ngaybc, madv, status, linkReport, matinh, phanhoichung, cbophanhoi;
    private InputStream pageResult;
    private int totalRow;
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
    private List<String> sotk = new ArrayList<>();
    private List<String> nguyennhan = new ArrayList<>();
    private Map<String, String> lstPGD = new HashMap<String, String>();
    private List<ListValue> lstStatus = new ArrayList<>();
    private String searchStatus;

    @Override
    //Cấp CN và TW
    public String execute() throws Exception {
        //Lấy danh sách đơn vị theo cấp báo cáo
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");
        epsModel dao =  new epsModel();
        lstPos = dao.getDonvi(capbc, tendn);
        lstCN = dao.getDonvi(capbc, tendn);
        lstStatus = dao.getSearchStatusList();
        return SUCCESS;
    }

    public String getCNToPGD() throws Exception {
        //Lấy danh sách đơn vị theo cấp báo cáo
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        lstPGD = (Map<String, String>) new epsModel().getDonvi(capbc, tendn);
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
        lstDetail = new epsModel().getAllData(capbc, tendn, madv, ngaybc, status, matinh, searchStatus);
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
        lstDetail = new epsModel().xemsleps(capbc, tendn, ngaybc);
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
        new epsModel().luusleps(macn, mapgd, makh, ngaybc, soku, chotsl, nguyennhan, tendn, phanhoichung, sotk, cbophanhoi);
        lstDetail = new epsModel().xemsleps(capbc, tendn, ngaybc);
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
        new epsModel().xacnhansleps(macn, mapgd, makh, ngaybc, soku, chotsl, nguyennhan, tendn, phanhoichung, sotk, cbophanhoi);
        lstDetail = new epsModel().xemsleps(capbc, tendn, ngaybc);
        sendTw();
        return SUCCESS;
    }

    public String openlock() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(ngaybc);
        DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
        ngaybc = df3.format(date);
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");
        new epsModel().openlock(macn, mapgd, makh, ngaybc, soku, tendn, status);
        return SUCCESS;
    }

    public String updateintellect() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(ngaybc);
        DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
        ngaybc = df3.format(date);
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");
        totalRow = new epsModel().updateintellect(tendn,mapgd, makh, ngaybc, status);
        pageResult = new ByteArrayInputStream(String.valueOf(totalRow).getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }
    
    //Cấp PGD
    public String TopngHopBaoCao() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(ngaybc);
        DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
        ngaybc = df3.format(date);
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        tendn = (String) session.get("username");
        lstDetail = new epsModel().TopngHopBaoCao(capbc, tendn, ngaybc);
        return SUCCESS;
    }
    
    public String sendTw() {
        System.err.println("Vao ham sendTDNN");
        try {
            Map<String, Integer> mapStatusSend = new HashMap();

            for (String mapgd : mapgd) {
                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/")
                        ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : context.getRealPath("/") + Define.M_REPORT_XML;
                strPathSave += "KYQUY_" + mapgd
                        + "_" + tendn + "_"
                        + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";

                List<String> lstData = new ArrayList<>();
                boolean bStatus_file = false;

                lstData = new epsModel().getDataSend("NT", "KYQUY",
                        mapgd, ngaybc);
                if (lstData == null || lstData.size() == 0) {
                    mapStatusSend.put(mapgd, 6);
                    continue;
                }
                bStatus_file = new XmlKtgsSync().createXmlFileKtgs(Define.PARA_SYN_REPORT_KYQUY, "NT",
                        "KYQUY", ngaybc, tendn, capbc,
                        mapgd, lstData, Define.WEB_SERVICES_STATUS_SEND, strPathSave);

                if (!bStatus_file) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi của PGD " + mapgd);
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendTw: Khong tao duoc file " + strPathSave);

                    mapStatusSend.put(mapgd, 1); //1 la tao file xml bi loi
//                    return ERROR;
                }
                //Tao file xml theo cau truc
//
                File checkfile = new File(strPathSave);
                if (!checkfile.exists()) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi. Xin liên hệ với quản trị để khắc phục");
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendTDNN: Khong tao duoc file " + strPathSave);
                    mapStatusSend.put(mapgd, 2); //2 la khong tim thay file xml
//                    return ERROR;
                }
                ProcessReportSyn clientWritexml = new ProcessReportSyn();
                String sStatus = clientWritexml.SendFileXmlToWebServices(strPathSave);
//
                if (sStatus.equals(Define.WEB_SERVICES_STATUS_FAIL)) {
                    System.err.println("Ban chua dong bo du lieu duoc ve TW");
//                    addActionError("Lỗi bạn chưa gửi dữ liệu được về trung ương ");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendTDNN: Khong dong bo duoc file " + strPathSave);
                    mapStatusSend.put(mapgd, 3); //3 la gui file du lieu bi loi
//                    return ERROR;
                } else if (sStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
//                    addActionMessage("Bạn gửi dữ liệu về trung ương thành công");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    mapStatusSend.put(mapgd, 4);  //gui du lieu thanh cong
                } else {
//                    addActionMessage("Bạn không thể gửi dữ liệu lên trung ương do bị khóa </br>Xin liên hệ về Ban KT&QLTC để được gửi lại số liệu ! ");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    mapStatusSend.put(mapgd, 5);  //pgd bi khoa khong gui duoc du lieu
//                    return ERROR;
                }
            }
//            setLstViewSend(getViewStatusSend(lstPos, mapStatusSend));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> sendTw: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendTw: " + e.getMessage());
            addActionError("Bạn chưa gửi được dữ liệu xin liên hệ với quản trị để được khắc phục");
            return ERROR;
        }
//        addActionMessage("Bạn gửi dữ liệu về trung ương thành công !");

        return SUCCESS;
    }
    
//<editor-fold defaultstate="collapsed" desc="Thuộc tính GET SET">

    public void setCapbc(String capbc) {
        this.capbc = capbc;
    }

    public String getCbophanhoi() {
        return cbophanhoi;
    }

    public void setCbophanhoi(String cbophanhoi) {
        this.cbophanhoi = cbophanhoi;
    }

    public Map<String, String> getLstPGD() {
        return lstPGD;
    }

    public void setLstPGD(Map<String, String> lstPGD) {
        this.lstPGD = lstPGD;
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

    public List<String> getSotk() {
        return sotk;
    }

    public void setSotk(List<String> sotk) {
        this.sotk = sotk;
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

    public String getPhanhoichung() {
        return phanhoichung;
    }

    public void setPhanhoichung(String phanhoichung) {
        this.phanhoichung = phanhoichung;
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

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public List<ListValue> getLstStatus() {
        return lstStatus;
    }

    public void setLstStatus(List<ListValue> lstStatus) {
        this.lstStatus = lstStatus;
    }
    
    
    
       

    public String getSearchStatus() {
        return searchStatus;
    }

    public void setSearchStatus(String searchStatus) {
        this.searchStatus = searchStatus;
    }
    
    //</editor-fold> 

}
