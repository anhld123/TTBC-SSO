/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.khnv2021;

import vbsp.ims.khnv2021.model.PosClass;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import vbsp.ims.bcqt.model.DULIEU_NT;

public class AuthorAction extends ActionSupport {

    //Cac truong chua thong tin bo xung luu du lieu
    private String CapBC, TenDN, status, cboTonghop, strNguyennhan, chkSuccess, dataReult,cboDonvi,cboNam,cboDot;
    private Map session;
    private List<PosClass> lstPos = new ArrayList<>();
    private List<DULIEU_NT> lstData = new ArrayList<>();
    private InputStream pageResult;
    
    @Override
    //Lấy danh đơn vị theo cấp báo cáo
    public String execute() throws Exception {
        //Lấy danh sách đơn vị theo cấp báo cáo
        session = ActionContext.getContext().getSession();
        CapBC = (String) session.get("reportGrade");
        TenDN = (String) session.get("username");
        lstPos = new AuthorModel().getPosCD(CapBC, TenDN);
        return SUCCESS;
    }

    //0 - Tải; 1 - Gửi; 2 - Trả lại
    public String SendAction() throws Exception {
        //Lấy danh sách đơn vị theo cấp báo cáo
        session = ActionContext.getContext().getSession();
        CapBC = (String) session.get("reportGrade");
        TenDN = (String) session.get("username");
        switch (status.trim()) {
            case "0":
                //Nhớ truyền đủ 7 tham số
                lstData = new AuthorModel().getData(CapBC, TenDN,cboDonvi,cboNam,cboDot, cboTonghop, strNguyennhan);
                if (lstData != null && !lstData.isEmpty()) {
                    chkSuccess = "SuccessLoad";
                    pageResult = new StringBufferInputStream("00");
                } else {
                    chkSuccess = "FaildMessage";
                    pageResult = new StringBufferInputStream("01");
                }
                break;
            case "1":
                dataReult = new AuthorModel().sendData(CapBC, TenDN);
                if (dataReult != null) {
                    chkSuccess = "SuccessMessage";
                    pageResult = new StringBufferInputStream("10");
                } else {
                    chkSuccess = "FaildMessage";
                    pageResult = new StringBufferInputStream("11");
                }
                pageResult = new StringBufferInputStream(dataReult);
                break;
            case "2":
                dataReult = new AuthorModel().rollBackData(CapBC, TenDN);
                if (dataReult != null) {
                    chkSuccess = "SuccessMessage";
                    pageResult = new StringBufferInputStream("20");
                } else {
                    chkSuccess = "FaildMessage";
                    pageResult = new StringBufferInputStream("21");
                }
                pageResult = new StringBufferInputStream(dataReult);
                break;
        }
        return chkSuccess;
    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
     public String getCapBC() {
        return CapBC;
    }

    public void setCapBC(String CapBC) {
        this.CapBC = CapBC;
    }

    public String getTenDN() {
        return TenDN;
    }

    public void setTenDN(String TenDN) {
        this.TenDN = TenDN;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCboTonghop() {
        return cboTonghop;
    }

    public void setCboTonghop(String cboTonghop) {
        this.cboTonghop = cboTonghop;
    }

    public String getStrNguyennhan() {
        return strNguyennhan;
    }

    public void setStrNguyennhan(String strNguyennhan) {
        this.strNguyennhan = strNguyennhan;
    }

    public String getChkSuccess() {
        return chkSuccess;
    }

    public void setChkSuccess(String chkSuccess) {
        this.chkSuccess = chkSuccess;
    }

    public String getDataReult() {
        return dataReult;
    }

    public void setDataReult(String dataReult) {
        this.dataReult = dataReult;
    }

    public String getCboDonvi() {
        return cboDonvi;
    }

    public void setCboDonvi(String cboDonvi) {
        this.cboDonvi = cboDonvi;
    }

    public String getCboNam() {
        return cboNam;
    }

    public void setCboNam(String cboNam) {
        this.cboNam = cboNam;
    }

    public String getCboDot() {
        return cboDot;
    }

    public void setCboDot(String cboDot) {
        this.cboDot = cboDot;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public List<PosClass> getLstPos() {
        return lstPos;
    }

    public void setLstPos(List<PosClass> lstPos) {
        this.lstPos = lstPos;
    }

    public List<DULIEU_NT> getLstData() {
        return lstData;
    }

    public void setLstData(List<DULIEU_NT> lstData) {
        this.lstData = lstData;
    }

    public InputStream getPageResult() {
        return pageResult;
    }

    public void setPageResult(InputStream pageResult) {
        this.pageResult = pageResult;
    }

    //</editor-fold> 

   
}
