/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.tracuu;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class Tracuu_action extends ActionSupport {

    private List<Tracuu_Utill> lstdieukien;
    private String LControl;
    private ArrayList<String> listdk;
    private ArrayList<String> listgt;
    private String indexctl;
    private int i, e;
    private List<Tracuu_Utill> lstcontrol;
    private List<Tracuu_viewdata> lstviewcontrol;
    private Integer totalpage;
    private Integer pagenum;
    private String ds_makh;
    private List<Tracuu_edit_util> lst_edit_dshn;
    private InputStream inputStream;

    public Tracuu_action() {
    }

    @Override
    public String execute() throws Exception {
        lstdieukien = new Tracuu_Model().getdanhsachdk();
        return "thanhcong";
    }

    public String loadcontrol() throws SQLException {
        //Thực hiện lấy giao diện các đối tượng ở đây
        lstcontrol = null;
        int i = Integer.parseInt(indexctl);
        getcontrol contrl = new getcontrol();
        // Xác định user và cấp để lấy dữ liệu theo cấp
        Map session = ActionContext.getContext().getSession();
        String sUserName = session.get("username").toString();
        String reportGrade = session.get("reportGrade").toString();
        contrl = new Tracuu_Model().getvaluedk(listdk.get(i),sUserName,reportGrade);
        LControl = contrl.getLControl();
        lstcontrol = contrl.getLscontrol();
        return "thanhcong";
    }

    public String getvalue() throws SQLException, ParseException {
        String MDK = "";
        for (i = 0; i < listgt.size(); i++) {
            if (!"CHON".equals(listdk.get(i))) {
                switch (listdk.get(i)) {
                    case "DS_NGAYBC":
                        MDK = MDK + "TRUNC(" + listdk.get(i) + ") = TO_DATE('" + listgt.get(i) + "','DD/MM/YYYY') AND ";
                        break;
                    case "DS_MAHUYEN":
                        MDK = MDK + listdk.get(i) + " = '" + listgt.get(i).trim().substring(2, 4) + "' AND "; //1011
                        break;
                    case "DS_MAXA":
                        MDK = MDK + listdk.get(i) + " = '" + listgt.get(i).trim().substring(4, 6) + " AND "; //101100
                        break;
                    case "DS_MATHON":
                        MDK = MDK + listdk.get(i) + " = '" + listgt.get(i).trim().substring(6, 8) + " AND "; // 10112345
                        break;
                    default:
                        MDK = MDK + listdk.get(i) + " = '" + listgt.get(i) + "' AND ";
                        break;
                }
            }
        }
        MDK = MDK + " 1 = 1";
        Tracuu_Model getlist = new Tracuu_Model();
        totalpage = getlist.loadviewcontent(MDK, pagenum).getTotalpage();
        lstviewcontrol = getlist.loadviewcontent(MDK, pagenum).getLsview();
        return "thanhcong";
    }

    public String edit_ds_hongheo() throws SQLException {
        Tracuu_Model listedit = new Tracuu_Model();
        lst_edit_dshn = listedit.edit_dshongheo(ds_makh);
        return "thanhcong";
    }
    
    public String save_ds_hongheo() throws SQLException {
        Tracuu_Model listedit = new Tracuu_Model();
        Integer chk = listedit.save_dshongheo(lst_edit_dshn, ds_makh);

        if (!chk.equals(1)) {
            inputStream = new StringBufferInputStream("failed");
        } else {
            inputStream = new StringBufferInputStream("successfully");
        }
        return SUCCESS;
    }
    
    public String add_ds_hongheo() throws SQLException {
        Tracuu_Model listedit = new Tracuu_Model();
        Integer chk = listedit.add_dshongheo(lst_edit_dshn);

        if (!chk.equals(1)) {
            inputStream = new StringBufferInputStream("failed");
        } else {
            inputStream = new StringBufferInputStream("successfully");
        }
        return SUCCESS;
    }

    public Integer getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(Integer totalpage) {
        this.totalpage = totalpage;
    }

    public Integer getPagenum() {
        return pagenum;
    }

    public void setPagenum(Integer pagenum) {
        this.pagenum = pagenum;
    }

    public String getDs_makh() {
        return ds_makh;
    }

    public void setDs_makh(String ds_makh) {
        this.ds_makh = ds_makh;
    }

    public List<Tracuu_edit_util> getLst_edit_dshn() {
        return lst_edit_dshn;
    }

    public void setLst_edit_dshn(List<Tracuu_edit_util> lst_edit_dshn) {
        this.lst_edit_dshn = lst_edit_dshn;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    //<editor-fold defaultstate="collapsed" desc="GET_SET_UTIL">
    public List<Tracuu_viewdata> getLstviewcontrol() {
        return lstviewcontrol;
    }

    public void setLstviewcontrol(List<Tracuu_viewdata> lstviewcontrol) {
        this.lstviewcontrol = lstviewcontrol;
    }

    public List<Tracuu_Utill> getLstdieukien() {
        return lstdieukien;
    }

    public void setLstdieukien(List<Tracuu_Utill> lstdieukien) {
        this.lstdieukien = lstdieukien;
    }

    public String getLControl() {
        return LControl;
    }

    public void setLControl(String LControl) {
        this.LControl = LControl;
    }

    public ArrayList<String> getListdk() {
        return listdk;
    }

    public void setListdk(ArrayList<String> listdk) {
        this.listdk = listdk;
    }

    public ArrayList<String> getListgt() {
        return listgt;
    }

    public void setListgt(ArrayList<String> listgt) {
        this.listgt = listgt;
    }

    public String getIndexctl() {
        return indexctl;
    }

    public void setIndexctl(String indexctl) {
        this.indexctl = indexctl;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    public List<Tracuu_Utill> getLstcontrol() {
        return lstcontrol;
    }

    public void setLstcontrol(List<Tracuu_Utill> lstcontrol) {
        this.lstcontrol = lstcontrol;
    }
//</editor-fold> 
}
