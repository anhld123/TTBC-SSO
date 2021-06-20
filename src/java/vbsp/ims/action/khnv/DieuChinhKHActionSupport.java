package vbsp.ims.action.khnv;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.khnv.DaoDieuchinhkh;
import vbsp.ims.dao.khnv.DaoGiaokh;
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.khnv.DieuchinhkhModel;
import vbsp.ims.model.khnv.POSModel;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.syn.ProcessReportSyn;

/**
 *
 * @author CuongBM0211
 */
public class DieuChinhKHActionSupport extends actionMainKHNV {

    private DaoDieuchinhkh daoDieuchinhkh = new DaoDieuchinhkh();
    private List<DieuchinhkhModel> dieuchinhkhModelList;  //Lay du lieu load len table
    //Cac truong chua thong tin bo xung luu du lieu

    //Gia tri khi luu dieu chinh ke hoach
    private List<String> KH_MA_CT;
    private List<String> KH_DC;

    private String vbsprandom;

    public DieuChinhKHActionSupport() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String get_data_select_pos() {
        return "success";
    }

    public String get_data_dieuchinhkh() {
        try {
//            System.err.println(" get_data_dieuchinhkh vbsprandom="+vbsprandom);   
            getInfo();
            posList = daoDieuchinhkh.getPosList(pos_cd_username, maCn, reportGrade);
            lstYearReport = new DaoGiaokh().getYearReport();
            namBc = getDefaultYearReport();
        } catch (Exception ex) {
            Logger.getLogger(DieuChinhKHActionSupport.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(this.getClass().getName() + " get_data_dieuchinhkh " + ex.getMessage());
        }

        return "success";
    }

    public String getDataDcKhDetail() {
        try {
//            System.err.println(" getDataDcKhDetail gia tri "+vbsprandom);  
            getInfo();

            dieuchinhkhModelList = daoDieuchinhkh.get_data_dc_kh(pos_cd, Integer.parseInt(namBc), reportGrade);
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + " loi getDataDcKhDetail " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataDcKhDetail " + e.getMessage());
        }

        return "success";
    }

    public String save_data_dieuchinhkh() {
        try {
//            System.err.println(" save_data_dieuchinhkh gia tri " + vbsprandom);

            getInfo();

            daoDieuchinhkh.saveDieuChinhKh(KH_MA_CT, KH_DC, pos_cd, maCn, reportGrade, Integer.parseInt(namBc), userId, vbsprandom);

            //DONG BO DU LIEU LEN TW
            if (reportGrade.equals("2")) {
                //Lay duong dan va ten xml se ghi ra

                String pathSave = getPathRoot()+ Define.M_REPORT_XML;
                pathSave += pos_cd_username + "_KHNV_DCKH.xml";    //Thay ma bao cao
                String reportDate = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());

                HashMap<String, String> hmData = new HashMap<String, String>();

                hmData.put("COT_1", DefineFun.converArrayList2String(KH_MA_CT));
                hmData.put("COT_2", DefineFun.converArrayList2String(KH_DC));

                //Tao file xml theo cau truc
                ProcessReportSyn clientWritexml = new ProcessReportSyn();
                boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKHNV(Define.PARA_SYN_REPORT_KHNV,
                        Define.SYN_KHNV_DIEU_CHINH_KH, reportDate, userId, pos_cd, maCn, vbsprandom, reportGrade, "KHNV", hmData, namBc, pathSave);
                if (!bSuccess) {
                    System.err.println("Ban chua dong bo du lieu duoc ve TW");
                }
            }
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + " loi getDataDcKhDetail " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataDcKhDetail " + e.getMessage());
        }

        return "success";
    }

    public String populatePos() {
        try {
            
            getInfo();

            posList = daoDieuchinhkh.getPosList(pos_cd_username, maCn, reportGrade);
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + " loi populatePos " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " populatePos " + e.getMessage());
        }

        return "success";
    }

  
    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public String getVbsprandom() {
        return vbsprandom;
    }

    public void setVbsprandom(String vbsprandom) {
        this.vbsprandom = vbsprandom;
    }

    

    public List<DieuchinhkhModel> getDieuchinhkhModelList() {
        return dieuchinhkhModelList;
    }

    public void setDieuchinhkhModelList(List<DieuchinhkhModel> dieuchinhkhModelList) {
        this.dieuchinhkhModelList = dieuchinhkhModelList;
    }
    public List<String> getKH_MA_CT() {
        return KH_MA_CT;
    }

    public void setKH_MA_CT(List<String> KH_MA_CT) {
        this.KH_MA_CT = KH_MA_CT;
    }

    public List<String> getKH_DC() {
        return KH_DC;
    }

    public void setKH_DC(List<String> KH_DC) {
        this.KH_DC = KH_DC;
    }
//</editor-fold>
}
