/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.nghiquyet11cp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.action.ktktnb.DULIEU_NT_TQ;

/**
 *
 * @author NGUYEN PHU VINH
 */
public class NQ11_DCKH_CNTW extends ActionSupport {

    private String sUser, sGrade, NamBc, code, dieuchinh;
    private InputStream pageResult;
    private List<DULIEU_NT_TQ> ModelList = new ArrayList<>();
    
    //<editor-fold defaultstate="collapsed" desc="Hàm load form lần đầu">
    public NQ11_DCKH_CNTW() {
        sGrade = (String) ActionContext.getContext().getSession().get("reportGrade");
        sUser = (String) ActionContext.getContext().getSession().get("username");
    }
//</editor-fold>
    public String loadDCHTLS() throws Exception {
        if(sGrade.equals("2")){
            dieuchinh = new NQ11_DCKHService().getDCHTLS_API(sUser, sGrade, NamBc);
        }
        ModelList = new NQ11_DCKHService().getDCHTLS(sUser,sGrade,NamBc);
        return "loadDCHTLS";
    }
     public String saveDCHTLS() throws Exception {
        //Do không có cập nhật ngược về TW nên sẽ không gọi API lưu ở đây
        code = new NQ11_DCKHService().saveDCHTLS(sUser,sGrade,NamBc,ModelList);
        pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return "saveDCHTLS";
    }
    //<editor-fold defaultstate="collapsed" desc="Khai báo biến">
     public String getsUser() {
        return sUser;
    }

    public void setsUser(String sUser) {
        this.sUser = sUser;
    }

    public String getsGrade() {
        return sGrade;
    }

    public void setsGrade(String sGrade) {
        this.sGrade = sGrade;
    }

    public String getNamBc() {
        return NamBc;
    }

    public void setNamBc(String NamBc) {
        this.NamBc = NamBc;
    }

    public InputStream getPageResult() {
        return pageResult;
    }

    public void setPageResult(InputStream pageResult) {
        this.pageResult = pageResult;
    }

    public List<DULIEU_NT_TQ> getModelList() {
        return ModelList;
    }

    public void setModelList(List<DULIEU_NT_TQ> ModelList) {
        this.ModelList = ModelList;
    }
     public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public String getDieuchinh() {
        return dieuchinh;
    }

    public void setDieuchinh(String dieuchinh) {
        this.dieuchinh = dieuchinh;
    }
    //</editor-fold>
}
