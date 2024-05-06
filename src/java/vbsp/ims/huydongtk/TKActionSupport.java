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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
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
    private String cSeach;
    private String checkNgaybc;
    private String checkNgaygs;
    List<clsCanBo> lstCanBo = new ArrayList<>();
    List<QT_DULIEU_NT> lstData = new ArrayList<>();
    List<QT_DULIEU_NT> lstDatatmp = new ArrayList<>();
    private String capbc, tendn;
    private Map session;
    private String displaNone = "200";
    private int lstDataSize;
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
        // displaNone = new clsHuyDongTK().CheckNgayBC(dtNgaybc);  
        String cSeach = ServletActionContext.getRequest().getParameter("cSeach");
        String dateStr = dtNgaybc;
        LocalDate date = LocalDate.parse(dateStr);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String formattedDate = date.format(formatter);
        // Get the current date
        // Get the current date
        LocalDate currentDate = LocalDate.now();

// Subtract one day from the current date to get yesterday's date
        LocalDate yesterday = currentDate.minusDays(1);

// Format yesterday's date as "dd/MM/yyyy"
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate1 = yesterday.format(formatter1);
        String formattedDate2 = date.format(formatter1);
//        setCheckNgaybc(formattedDate1);
        setCheckNgaygs(formattedDate2);
        long daysBetween = ChronoUnit.DAYS.between(date, currentDate);
        setCheckNgaybc(String.valueOf(daysBetween));

        if (flgFilter != null) {
            flgFilter = "on";
        } else {
            flgFilter = "off";
        };
        lstDatatmp = new clsHuyDongTK().getData(formattedDate, tendn, capbc, cboCanBo, flgFilter, cSeach);
        lstDataSize = lstDatatmp.size();
        if (lstDataSize > 500) {
            addActionError("Dữ liệu quá lớn, vui lòng nhập thông tin sổ vào ô tra cứu, tìm kiếm lại để lưu!");
            return ERROR;
        } else if (lstDatatmp == null || lstDatatmp.isEmpty()) {
            addActionError("Không có thông tin sổ cần tìm kiếm!");
            return ERROR;
        } else {
            lstData = new clsHuyDongTK().getData(formattedDate, tendn, capbc, cboCanBo, flgFilter, cSeach);
        }

        System.out.println(formattedDate
                + " " + lstDataSize);
        return SUCCESS;
    }

    public String savedata() {
        String dateStr = dtNgaybc;
        LocalDate date = LocalDate.parse(dateStr);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String formattedDate = date.format(formatter);
//        ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
//        for (QT_DULIEU_NT tmp : lstDulieuNt) {
//            if (chkChon.size() > 0 && chkChon.contains(tmp.getD3())) {
//                lstLocalDataUpdate.add(tmp);
//            }
//        }
        if (lstDulieuNt.size() > 500) {
            String code = String.valueOf(404);
            this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
            System.out.println(pageResult);
        } else {
            String code = new clsHuyDongTK().saveData(formattedDate, tendn, capbc, cboCanBo, txtChitieu, chkChon, lstDulieuNt);
            code = String.valueOf(200);
            this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        }
        /*Hàm gọi REST API để đẩy dữ liệu về TW*/
 /* int Status = new HDTKRestApi().insertHDTK(dtNgaybc, tendn, capbc, cboCanBo,txtChitieu, chkChon); */
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
    public String getcSeach() {
        return cSeach;
    }

    public void setcSeach(String cSeach) {
        this.cSeach = cSeach;
    }

    public int getLstDataSize() {
        return lstDataSize;
    }

    public void setLstDataSize(int lstDataSize) {
        this.lstDataSize = lstDataSize;
    }

    public String getCheckNgaybc() {
        return checkNgaybc;
    }

    public void setCheckNgaybc(String checkNgaybc) {
        this.checkNgaybc = checkNgaybc;
    }

    public String getCheckNgaygs() {
        return checkNgaygs;
    }

    public void setCheckNgaygs(String checkNgaygs) {
        this.checkNgaygs = checkNgaygs;
    }

}
