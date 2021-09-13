package vbsp.ims.ketquaKTDC;

import vbsp.ims.ketquaKTDC.*;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.action.Utilities;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.khnv.POSModel;

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
            lstDvut = daoKtdc.getListCombobox(pos_cd_username, maCn, reportGrade);
           
            lstMato = daoKtdc.getSubCommuneList(pos_cd_username, "140801", reportGrade);
            
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
                lstDulieuNt = daoKtdc.getDataKiemtraLoan("", userId, reportGrade, "", "", "", "","", "", "","", mabc);
                return "kiemtraLoan";
            }
            else
            {
                lstBienphapXuly = daoKtdc.getLOV(userId, Define.KTDC_BIENPHAPXL);
                lstDulieuNt = daoKtdc.getDataKiemtraLoan("", userId, reportGrade, "", "", "", "","", "", "","", mabc);
                return "kiemtra-to-dvut";
            }
                    
            
//            setDotBc(dotBc);
//            setNamBc(namBc);
//            setReasonReject(daoXdkh.getReason(maBc, namBc, dotBc, pos_cd_username, reportGrade));
//            //TH load mẫu 02 theo pos
//            if (maBc.equals("KHNV_02")) {
//                lstDulieuNt = daoXdkh.getDataAuthCommune(maBc, userId, reportGrade, namBc, dotBc, commune_cd, subcommune_cd);
//                return "load02Pos";
//            }
//            //TH load theo 1 thôn
//            if (maBc.equals("KHNV_01A") && !commune_cd.equals("000000") && !subcommune_cd.equals("000000")) {
//                lstDulieuNt = daoXdkh.getDataAuthCommune(maBc, userId, reportGrade, namBc, dotBc, commune_cd, subcommune_cd);
//                return "loadOneSubCommune";
//            }
//            //TH load các thôn trong xã
//            if (maBc.equals("KHNV_01A") && !commune_cd.equals("000000") && subcommune_cd.equals("000000")) {
//                lstDulieuNt = daoXdkh.getDataAuthCommune(maBc, userId, reportGrade, namBc, dotBc, commune_cd, subcommune_cd);
//                return "loadAllSubCommune";
//            }
//            //TH load các xã
//            if (maBc.equals("KHNV_01A") && commune_cd.equals("000000")) {
//                lstDulieuNt = daoXdkh.getDataAuthCommune(maBc, userId, reportGrade, namBc, dotBc, commune_cd, subcommune_cd);
//                lstDulieuNt2 = daoXdkh.getDataAuthCommuneSum(maBc, userId, reportGrade, namBc, dotBc, "000000", "000000");
//                return "loadAllCommuneAuth";
//            }            

        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " get_data_xaydungkh " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataXayDungKhDetail " + ex.getMessage());
        }

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
