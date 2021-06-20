package vbsp.ims.action.khnv;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.khnv.DaoGiaokh;
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.khnv.CTieuKHoachModel;
import vbsp.ims.model.khnv.ChiTieuDetail;
import vbsp.ims.model.khnv.GiaokhModel;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.syn.ProcessReportSyn;

/**
 *
 * @author CuongBM0211
 */
public class GiaoKHActionSupport extends actionMainKHNV {

    private List<GiaokhModel> giaokhModelList;  //Lay du lieu load len table
    private DaoGiaokh daoGiaokh = new DaoGiaokh();

    //Cac truong chua thong tin bo xung luu du lieu
    //Cac truong dung cho getChiTieuDetail
    private String maCt;    //Ma chi tieu
    private String tenCt;   //Ten chi tieu
    private String ctDP;    //Chi tieu dia phuong
    private double khDuocGiao;  //Ke hoach duoc giao, chi dung khi nguoi dung dang nhap cap chi nhánh
    public List<CTieuKHoachModel> cTieuKHoachModelList;

    private ChiTieuDetail chiTieuDetail;

    //Gia tri khi luu ke hoach
    private List<String> maPGD;
    private List<String> giaoKh;
    
    public GiaoKHActionSupport() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String get_data_giaokh() {

        try {
            //System.out.println("Buoc 1");
            getInfo();  //Lay thong tin   
            
            System.out.println(userId);
            
            giaokhModelList = daoGiaokh.get_data_giaokh(reportGrade, userId);
            lstYearReport = daoGiaokh.getYearReport();
        } catch (Exception e) {
            
            CoreLogger.error(this.getClass().getName() + " loi get_data_giaokh " + e.getMessage());
            System.err.println(this.getClass().getName() + " loi get_data_giaokh " + e.getMessage());
            return ERROR;
        }

        return "success";
    }

    public String getChiTieuDetail() {
        try {

            getInfo();  //Lay thong tin
            chiTieuDetail = daoGiaokh.getChiTieuDetail(maCt, reportGrade.equals("3")?maCn:pos_cd_username, reportGrade, Integer.parseInt(namBc));
            tenCt = chiTieuDetail.getTenCt();
            khDuocGiao = chiTieuDetail.getKhDuocGiao();
            ctDP = chiTieuDetail.getCtDP();
            cTieuKHoachModelList = chiTieuDetail.getcTieuKHoachModelList();

            //Neu la cap chi nhanh, va chi tieu duoc giao = 0, va khong phai la chi tieu dia phuong
            // --> thien thi man hinh thong bao "ban chua nhan duoc ke hoach giao tu trung uong"
            if ((!reportGrade.equalsIgnoreCase("3")) && (khDuocGiao == 0) && (ctDP.equalsIgnoreCase("N"))) {
                return "chuaGiaoKH";
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " loi getChiTieuDetail " + e.getMessage());
            System.err.println(this.getClass().getName() + " loi getChiTieuDetail " + e.getMessage());
        }

        return "success";
    }

    public String save_data_giaokh() {
        try {
            getInfo();  //Lay thong tin
            System.err.println("Giao ke hoach giaoKh="+giaoKh.size()+" "+giaoKh.get(1));
            daoGiaokh.saveGiaoKh(maPGD, giaoKh, maCt, pos_cd_username, maCn, reportGrade, Integer.parseInt(namBc), userId);

            if (reportGrade.equals("2")  ) {
                //Lay duong dan va ten xml se ghi ra
                String pathSave = getPathRoot() + Define.M_REPORT_XML;
                pathSave += pos_cd_username + "_KHNV_GIAOKH.xml";    //Thay ma bao cao
                String reportDate = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());

                HashMap<String, String> hmData = new HashMap<String, String>();

                hmData.put("COT_1", DefineFun.converArrayList2String(maPGD));
                hmData.put("COT_2", DefineFun.converArrayList2String(giaoKh));

                //Tao file xml theo cau truc
                ProcessReportSyn clientWritexml = new ProcessReportSyn();
                boolean bSuccess = clientWritexml.SendFileXmlToWebServicesKHNV(Define.PARA_SYN_REPORT_KHNV,
                        Define.SYN_KHNV_GIAO_KH, reportDate, userId, pos_cd_username, maCn, maCt, reportGrade, "KHNV", hmData, namBc, pathSave);
                if (!bSuccess) {
                    System.err.println("Ban chua dong bo du lieu duoc ve TW");
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " loi save_data_giaokh " + e.getMessage());
            System.err.println(this.getClass().getName() + " loi save_data_giaokh " + e.getMessage());
            return "success";
        }

        return "success";
    }

     //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    
    
    

    public List<GiaokhModel> getGiaokhModelList() {
        return giaokhModelList;
    }

    public void setGiaokhModelList(List<GiaokhModel> giaokhModelList) {
        this.giaokhModelList = giaokhModelList;
    }

    public String getMaCt() {
        return maCt;
    }

    public void setMaCt(String maCt) {
        this.maCt = maCt;
    }

    public String getTenCt() {
        return tenCt;
    }

    public void setTenCt(String tenCt) {
        this.tenCt = tenCt;
    }

    public double getKhDuocGiao() {
        return khDuocGiao;
    }

    public void setKhDuocGiao(double khDuocGiao) {
        this.khDuocGiao = khDuocGiao;
    }

    public List<CTieuKHoachModel> getcTieuKHoachModelList() {
        return cTieuKHoachModelList;
    }

    public void setcTieuKHoachModelList(List<CTieuKHoachModel> cTieuKHoachModelList) {
        this.cTieuKHoachModelList = cTieuKHoachModelList;
    }

    public List<String> getMaPGD() {
        return maPGD;
    }

    public void setMaPGD(List<String> maPGD) {
        this.maPGD = maPGD;
    }

    public List<String> getGiaoKh() {
        return giaoKh;
    }

    public void setGiaoKh(List<String> giaoKh) {
        this.giaoKh = giaoKh;
    }

    public String getCtDP() {
        return ctDP;
    }

    public void setCtDP(String ctDP) {
        this.ctDP = ctDP;
    }

//</editor-fold>
}
