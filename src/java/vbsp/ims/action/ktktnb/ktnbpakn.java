/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action.ktktnb;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;

/**
 *
 * @author NGUYEN PHU VINH
 */
public class ktnbpakn extends ActionSupport {

    String txtNgaybc, sUser, sGrade, txtMaBc, jsp;
    ByteArrayInputStream sResult;
    List<QT_DULIEU_NT> ModelList = new ArrayList<>();

    //<editor-fold defaultstate="collapsed" desc="Hàm load form lần đầu">
    public ktnbpakn() {
    }

    public String get_data_pakn01tcd() throws Exception {
        return "thanhcong";
    }

    //</editor-fold>
    public String loadDataByTem() throws Exception {
        sGrade = (String) ActionContext.getContext().getSession().get("reportGrade");
        sUser = (String) ActionContext.getContext().getSession().get("username");
        ModelList = new PankService().getDataByTem(txtNgaybc, sUser, sGrade, txtMaBc);
        jsp = txtMaBc + ".jsp";
        return "ViewData";
    }
     public String saveDataByTem() throws Exception {
        sGrade = (String) ActionContext.getContext().getSession().get("reportGrade");
        sUser = (String) ActionContext.getContext().getSession().get("username");
        sResult = new ByteArrayInputStream("Thành công: Lưu dữ liệu.".getBytes(StandardCharsets.UTF_8));
        return "SaveData";
    }
    //<editor-fold defaultstate="collapsed" desc="Khai báo biến">

    public String getTxtNgaybc() {
        return txtNgaybc;
    }

    public void setTxtNgaybc(String txtNgaybc) {
        this.txtNgaybc = txtNgaybc;
    }

    public String getTxtMaBc() {
        return txtMaBc;
    }

    public void setTxtMaBc(String txtMaBc) {
        this.txtMaBc = txtMaBc;
    }

    public List<QT_DULIEU_NT> getModelList() {
        return ModelList;
    }

    public void setModelList(List<QT_DULIEU_NT> ModelList) {
        this.ModelList = ModelList;
    }

    public String getJsp() {
        return jsp;
    }

    public void setJsp(String jsp) {
        this.jsp = jsp;
    }
    
    public ByteArrayInputStream getsResult() {
        return sResult;
    }

    public void setsResult(ByteArrayInputStream sResult) {
        this.sResult = sResult;
    }
    //</editor-fold>    

    
}
