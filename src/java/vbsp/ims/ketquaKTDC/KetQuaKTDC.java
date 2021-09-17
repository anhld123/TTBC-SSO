package vbsp.ims.ketquaKTDC;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import vbsp.ims.ketquaKTDC.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.action.Utilities;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.khnv.POSModel;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;

/**
 *
 * @author CuongBM0211
 */
public class KetQuaKTDC extends ActionMainKTDC {

    private KetQuaKtdcDao daoKtdc = new KetQuaKtdcDao();

    private List<POSModel> lstDvut = new ArrayList<>();
    private List<POSModel> lstMato = new ArrayList<>();

    public KetQuaKTDC() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String get_parameter() {

        try {
            getInfo();
            lstMaBaocao = daoKtdc.getLOV(userId, Define.KTDC_BAOCAO);
            lstDoituongKT = daoKtdc.getLOV(userId, Define.KTDC_DOITUONG);
            lstHinhthucKT = daoKtdc.getLOV(userId, Define.KTDC_HINHTHUC);
            lstMaxa = daoKtdc.getLOV(userId, Define.KTDC_MAXA);
//            lstDvut = daoKtdc.getLOV(userId, Define.KTDC_DVUT);
//            lstMato = daoKtdc.getLOV(userId, Define.KTDC_MATO);
            lstMaCanbo = daoKtdc.getLOV(userId, Define.KTDC_CANBO);

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " loi get_parameter KetQuaKTDC " + e.getMessage());
            System.err.println(this.getClass().getName() + " loi get_parameter KetQuaKTDC " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }


    
    public String getMato() {
        try {
            getInfo();
            lstDvut = daoKtdc.getListCombobox(userId, Define.KTDC_DVUT);
           
            lstMato = daoKtdc.getListTo(maxa, dvut);
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getSubcommune -> " + e.getMessage());
        }
        return "success";
    }

    public String getDataKTDC() {
        try {
            HashMap hmParameter = getParameter();

            getInfo();
            if(mabc.equals("KTDC01"))
            {
                lstBienphapXuly = daoKtdc.getLOV(userId, Define.KTDC_BIENPHAPXL);
                lstKetQuaHT = daoKtdc.getLOV(userId, Define.KTDC_KETQUAHT);
                lstDulieuNt = daoKtdc.getDataKiemtraLoan(userId, reportGrade, ngay_bc, ngay_kt, doituongkt, hinhthuckt,canbokt,maxa, dvut, mato,cust_search, mabc);
                return "kiemtraLoan";
            }
            else if (mabc.equals("KTDC02"))
            {
                lstNguyenNhanCL = daoKtdc.getLOV(userId, Define.KTDC_KTDC_NGUYENNHANCL);
                lstDulieuNt = daoKtdc.getDataKiemtraLoan(userId, reportGrade, ngay_bc, ngay_kt, doituongkt, hinhthuckt,canbokt,maxa, dvut, mato,cust_search, mabc);
                return "doichieuLoan";
            }
            else if (mabc.equals("KTDC03"))
            {
//                lstNguyenNhanCL = daoKtdc.getLOV(userId, Define.KTDC_KTDC_NGUYENNHANCL);
                lstDulieuNt = daoKtdc.getDataKiemtraLoan(userId, reportGrade, ngay_bc, ngay_kt, doituongkt, hinhthuckt,canbokt,maxa, dvut, mato,cust_search, mabc);
                return "kiemtraTo";
            }
            else
            {
//                lstNguyenNhanCL = daoKtdc.getLOV(userId, Define.KTDC_KTDC_NGUYENNHANCL);
                lstDulieuNt = daoKtdc.getDataKiemtraLoan(userId, reportGrade, ngay_bc, ngay_kt, doituongkt, hinhthuckt,canbokt,maxa, dvut, mato,cust_search, mabc);
                return "kiemtraHoi";
            }
                    
               

        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " get_data_xaydungkh " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataXayDungKhDetail " + ex.getMessage());
        }

        return SUCCESS;
    }
    
    public String saveKTDC() {
        System.err.println("Save - GSCMR_001");
        try {
           getInfo();
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            List<String> lstDat = new ArrayList<String>();
            for (QT_DULIEU_NT.saveDulieuNT_Phi value : lstsaveNT_DAT) {
                if(value != null)                    
                    if (!value.getD3().equals("false")) {
                        lstDat.add(value.getD3());
                    }
            }
            
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();    
            HashMap hmParameter = getParameter();            
            if(!daoKtdc.saveKTDC(mabc, userId, reportGrade,ngay_bc, ngay_kt,doituongkt,hinhthuckt,canbokt,maxa, dvut,mato, lstDulieuNt, lstDat))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            } 
                        

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> QLDB_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> QLDB_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }    

    public String openExcelUpload() {
        return SUCCESS;
    }
//<editor-fold defaultstate="collapsed" desc="Getter Setter">

//</editor-fold>    

    public List<POSModel> getLstDvut() {
        return lstDvut;
    }

    public void setLstDvut(List<POSModel> lstDvut) {
        this.lstDvut = lstDvut;
    }

    public List<POSModel> getLstMato() {
        return lstMato;
    }

    public void setLstMato(List<POSModel> lstMato) {
        this.lstMato = lstMato;
    }

}
