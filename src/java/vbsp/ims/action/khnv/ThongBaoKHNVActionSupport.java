/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action.khnv;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.khnv.DaoGiaokh;
import vbsp.ims.dao.khnv.DaoThongbao;
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.khnv.CTChuaKhopModel;
import vbsp.ims.model.khnv.POSModel;
import vbsp.ims.model.ktnb.PosMainModel;

/**
 *
 * @author CuongBM0211
 */
public class ThongBaoKHNVActionSupport extends actionMainKHNV {

    private DaoThongbao daoThongbao = new DaoThongbao();

    private List<POSModel> posModelList;
    public List<CTChuaKhopModel> cTChuaKhopModelList;

    //Cac truong chua thong tin bo xung luu du lieu

    public ThongBaoKHNVActionSupport() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String get_data_thongbao() {
        try {
            lstYearReport=new DaoGiaokh().getYearReport();
            namBc=getDefaultYearReport();
            getInfo();

            posModelList = daoThongbao.getDataChuaXDKH(pos_cd_username, maCn, reportGrade, Integer.parseInt(namBc));
            cTChuaKhopModelList = daoThongbao.getDataCTCK(pos_cd_username, maCn, reportGrade, Integer.parseInt(namBc));
        } catch (Exception ex) { 
            Logger.getLogger(DieuChinhKHActionSupport.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(this.getClass().getName() + " get_data_thongbao " + ex.getMessage());
        }
        return "success";
    }
        public String getDataThongbaoDetail() {
        try {
            getInfo();

            posModelList = daoThongbao.getDataChuaXDKH(pos_cd_username, maCn, reportGrade, Integer.parseInt(namBc));
            cTChuaKhopModelList = daoThongbao.getDataCTCK(pos_cd_username, maCn, reportGrade, Integer.parseInt(namBc));
        } catch (Exception ex) { 
            Logger.getLogger(DieuChinhKHActionSupport.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(this.getClass().getName() + " getDataThongbaoDetail " + ex.getMessage());
        }
        return "success";
    }
     //<editor-fold defaultstate="collapsed" desc="Getter Setter">
   

    public List<POSModel> getPosModelList() {
        return posModelList;
    }

    public void setPosModelList(List<POSModel> posModelList) {
        this.posModelList = posModelList;
    }

    public List<CTChuaKhopModel> getcTChuaKhopModelList() {
        return cTChuaKhopModelList;
    }

    public void setcTChuaKhopModelList(List<CTChuaKhopModel> cTChuaKhopModelList) {
        this.cTChuaKhopModelList = cTChuaKhopModelList;
    }
//</editor-fold>

}
