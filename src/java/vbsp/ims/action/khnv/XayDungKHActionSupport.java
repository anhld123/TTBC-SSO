package vbsp.ims.action.khnv;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.khnv.DaoDieuchinhkh;
import vbsp.ims.dao.khnv.DaoGiaokh;
import vbsp.ims.dao.khnv.DaoXdkh;
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.khnv.XdkhModel;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.syn.ProcessReportSyn;

/**
 *
 * @author CuongBM0211
 */
public class XayDungKHActionSupport extends actionMainKHNV {

    private DaoXdkh daoXdkh = new DaoXdkh();
    private List<XdkhModel> xdkhModelList;  //Lay du lieu load len table
    private String xa_pgd;
    //Cac truong dung cho luu du lieu
    private List<String> KH_MA_CT;
    private List<String> KH_STT_HT;
    private List<String> KH_CHI_TIEU;
    private List<String> KH_UOC_TH;
    private List<String> KH_KH_NAM;
    private List<String> KH_DN;
    private List<String> KH_FONTWEIGHT;
    private List<String> KH_CAPHT;
    private List<String> KH_STT;

    public XayDungKHActionSupport() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String get_data_xaydungkh() {

        try {
            namBc = getDefaultYearReport();
            getInfo();
            lstYearReport = new DaoGiaokh().getYearReport();
            posList = new DaoDieuchinhkh().getPosList(pos_cd_username, maCn, reportGrade);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " loi get_data_xaydungkh " + e.getMessage());
            System.err.println(this.getClass().getName() + " loi get_data_xaydungkh " + e.getMessage());
        }

//        getInfo();        
//        xdkhModelList = daoXdkh.get_data_xdkh(posCD, Integer.parseInt(namBc));
        return "success";
    }

    public String getDataXayDungKhDetail() {
        try {

            getInfo();
            //xa_pgd 1 la xa, 2 là pgd
            if (reportGrade.equals("1")&&xa_pgd.equals("1")) {
                xdkhModelList = daoXdkh.get_data_xdkh(pos_cd, reportGrade,xa_pgd, Integer.parseInt(namBc));
            } else {
                xdkhModelList = daoXdkh.get_data_xdkh(pos_cd_username, reportGrade,xa_pgd, Integer.parseInt(namBc));
            }
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " get_data_xaydungkh " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataXayDungKhDetail " + ex.getMessage());
        }

        return SUCCESS;
    }

    public String save_data_xaydungkh() {
        try {
            System.err.println("Vao ham luu xay dung ke hoach");
            getInfo();
            if (reportGrade.equals("1")&&xa_pgd.equals("1")) {
                daoXdkh.save_xdkh(pos_cd, maCn, namBc, userId,reportGrade,xa_pgd, KH_MA_CT, KH_STT_HT, KH_CHI_TIEU, KH_UOC_TH, KH_KH_NAM, KH_DN, KH_FONTWEIGHT, KH_CAPHT, KH_STT);
            } else {
                daoXdkh.save_xdkh(pos_cd_username, maCn, namBc, userId,reportGrade,xa_pgd, KH_MA_CT, KH_STT_HT, KH_CHI_TIEU, KH_UOC_TH, KH_KH_NAM, KH_DN, KH_FONTWEIGHT, KH_CAPHT, KH_STT);
            }

            if (reportGrade.equals("2")) {
                //Lay duong dan va ten xml se ghi ra
                String pathSave = getPathRoot() + Define.M_REPORT_XML;
                pathSave += reportGrade.equals("1") == true && xa_pgd.equals("1")==true ?
                        pos_cd+ "_KHNV_XDKH_"+String.valueOf(new Random(1000).nextInt(1000))+".xml" 
                        : pos_cd_username + "_KHNV_XDKH_"+String.valueOf(new Random(1000).nextInt(1000))+".xml";    //Thay ma bao cao
                String reportDate = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());

                HashMap<String, String> hmData = new HashMap<String, String>();

                hmData.put("COT_1", DefineFun.converArrayList2String(KH_MA_CT));
                hmData.put("COT_2", DefineFun.converArrayList2String(KH_STT_HT));
                hmData.put("COT_3", DefineFun.converArrayList2String(KH_CHI_TIEU));
                hmData.put("COT_4", DefineFun.converArrayList2String(KH_UOC_TH));
                hmData.put("COT_5", DefineFun.converArrayList2String(KH_KH_NAM));
                hmData.put("COT_6", DefineFun.converArrayList2String(KH_DN));
                hmData.put("COT_7", DefineFun.converArrayList2String(KH_FONTWEIGHT));
                hmData.put("COT_8", DefineFun.converArrayList2String(KH_CAPHT));
                hmData.put("COT_9", DefineFun.converArrayList2String(KH_STT));

                //Tao file xml theo cau truc
                ProcessReportSyn clientWritexml = new ProcessReportSyn();
                boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKHNV(Define.PARA_SYN_REPORT_KHNV,
                        Define.SYN_KHNV_XDKH, reportDate, userId, reportGrade.equals("1") == true ? pos_cd : pos_cd_username
                        , maCn, "aaabbb", reportGrade, "KHNV", hmData, namBc, pathSave);
                if (!bSuccess) {
                    System.err.println("Ban chua dong bo du lieu duoc ve TW");
                }
            }
            //========== END DONG BO DU LIEU =======================================
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " save_data_xaydungkh " + e.getMessage());
            System.err.println(this.getClass().getName() + " loi save_data_xaydungkh " + e.getMessage());
            return ERROR;
        }

        return "success";
    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">

    public String getXa_pgd() {
        return xa_pgd;
    }

    public void setXa_pgd(String xa_pgd) {
        this.xa_pgd = xa_pgd;
    }
    
    
    public List<XdkhModel> getXdkhModelList() {
        return xdkhModelList;
    }

    public void setXdkhModelList(List<XdkhModel> xdkhModelList) {
        this.xdkhModelList = xdkhModelList;
    }

    public List<String> getKH_MA_CT() {
        return KH_MA_CT;
    }

    public void setKH_MA_CT(List<String> KH_MA_CT) {
        this.KH_MA_CT = KH_MA_CT;
    }

    public List<String> getKH_STT_HT() {
        return KH_STT_HT;
    }

    public void setKH_STT_HT(List<String> KH_STT_HT) {
        this.KH_STT_HT = KH_STT_HT;
    }

    public List<String> getKH_CHI_TIEU() {
        return KH_CHI_TIEU;
    }

    public void setKH_CHI_TIEU(List<String> KH_CHI_TIEU) {
        this.KH_CHI_TIEU = KH_CHI_TIEU;
    }

    public List<String> getKH_UOC_TH() {
        return KH_UOC_TH;
    }

    public void setKH_UOC_TH(List<String> KH_UOC_TH) {
        this.KH_UOC_TH = KH_UOC_TH;
    }

    public List<String> getKH_KH_NAM() {
        return KH_KH_NAM;
    }

    public void setKH_KH_NAM(List<String> KH_KH_NAM) {
        this.KH_KH_NAM = KH_KH_NAM;
    }

    public List<String> getKH_DN() {
        return KH_DN;
    }

    public void setKH_DN(List<String> KH_DN) {
        this.KH_DN = KH_DN;
    }

    public List<String> getKH_FONTWEIGHT() {
        return KH_FONTWEIGHT;
    }

    public void setKH_FONTWEIGHT(List<String> KH_FONTWEIGHT) {
        this.KH_FONTWEIGHT = KH_FONTWEIGHT;
    }

    public List<String> getKH_CAPHT() {
        return KH_CAPHT;
    }

    public void setKH_CAPHT(List<String> KH_CAPHT) {
        this.KH_CAPHT = KH_CAPHT;
    }

    public List<String> getKH_STT() {
        return KH_STT;
    }

    public void setKH_STT(List<String> KH_STT) {
        this.KH_STT = KH_STT;
    }
//</editor-fold>    

}
