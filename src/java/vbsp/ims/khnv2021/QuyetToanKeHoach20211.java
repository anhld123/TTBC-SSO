package vbsp.ims.khnv2021;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.math.BigInteger;
import vbsp.ims.khnv2021.dao.XDKHDao2021;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.action.Utilities;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.define.Define;
import vbsp.ims.khnv2021.excel.ExcelExport;
import vbsp.ims.khnv2021.model.FileExportInfo;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.khnv.POSModel;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.IntDeductionModel;
import vbsp.ims.util.DateUtil;

/**
 *
 * @author CuongBM0211
 */
public class QuyetToanKeHoach20211 extends ActionMainKHNV {

    private XDKHDao2021 daoXdkh = new XDKHDao2021();

    private List<POSModel> custCommuneList = new ArrayList<>();
    private List<POSModel> custSubCommuneList = new ArrayList<>();
    DuLieuNTService service;

    public QuyetToanKeHoach20211() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String get_data_quyettoankh_2021() {

        try {
            namBc = getDefaultYearReport();
            getInfo();
            namBc = String.valueOf(Integer.parseInt(namSau));
            if (!reportGrade.equals("1")) {
                addActionError("Chức năng này chỉ thực hiện cho cấp PGD");
                return ERROR;
            }
            posList = daoXdkh.getPosList(pos_cd_username, maCn, reportGrade);
            subCommuneList = daoXdkh.getSubCommuneList(pos_cd_username, "", reportGrade);
            lstMaBC = daoXdkh.getLOV(userId, Define.LOV_MABC_QT);
            lstNamBC = daoXdkh.getLOV(userId, Define.LOV_NAMBC);
            lstDotBC = daoXdkh.getLOV(userId, Define.LOV_DOTBC);
            lstTongHop = daoXdkh.getLOV(userId, Define.LOV_VIEW_TYPE);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " loi get_data_xaydungkh " + e.getMessage());
            System.err.println(this.getClass().getName() + " loi get_data_xaydungkh " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
    
        public String ExpExcelEcelQtKhnv11() {
        try {
            getInfo();
            request = ServletActionContext.getRequest();
            String savedDir = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");
            ExcelExport excelExport = new ExcelExport();


            FileExportInfo fileInfo = excelExport.xuatExcelMauQT11(pos_cd_username,reportGrade,  "31-DEC-" + namBc, namBc,  savedDir);
            fileNamelocal = fileInfo.fileName;
            filereport = fileInfo.filePath;
            return SUCCESS;
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " ExpExcelEcelQtKhnv11 " + ex.getMessage());
            System.err.println(this.getClass().getName() + " Loi ExpExcelEcelQtKhnv11 " + ex.getMessage());
            return ERROR;
        }
    }

    public String getDataQtKehoach() {
        try {
            HashMap hmParameter = getParameter();
            int yearPre = Integer.parseInt(namBc) - 1;
            int year2Pre = Integer.parseInt(namBc) - 2;
            namBc_pre = String.valueOf(yearPre);
            namBc_2pre = String.valueOf(year2Pre);

            getInfo();        
//                lstDulieuNt = daoXdkh.getDataQtKehoach(maBc, userId, reportGrade, namBc);
            service = new DuLieuNTService();
            ArrayList<DuLieuNTRow> lstData = service.getData(Define.NV_QT, pos_cd_username, reportGrade, namBc +"1231");    
            lstData.sort(Comparator.comparing(o -> o.getOrderValue()));
            for (DuLieuNTRow item : lstData) {
                    try {
                        QT_DULIEU_NT row = new QT_DULIEU_NT();
                        row.setKHOA(Define.NV_QT);
                        row.setTHUTU(Integer.parseInt(item.getOrderValue()));
                        row.setTT_HIENTHI(item.getOrderDescription());
                        row.setTEN(item.getName());

                        Date reportDate = DateUtil.toDate(item.getReportDate());
                        row.setNGAYBC(reportDate);                       
                        row.setMAPGD(item.getPosCode());
                        row.setMACN(item.getBranchCode());
                        
                        row.setD1(item.getD1());
                        row.setD2(item.getD2());
                        row.setD3(item.getD3());
                        row.setD4(item.getD4());
                        row.setD5(item.getD5());
                        row.setD6(item.getD6());
                        row.setD7(item.getD7());
                        row.setD8(item.getD8());
                        row.setD9(item.getD9());
                        row.setD10(item.getD10());
                        
                        row.setD19(item.getD19());
                        lstDulieuNt.add(row);
                    } catch (Exception e) {
                        CoreLogger.error(this.getClass().getName() + " Exception -> HTLS2021: " + e.getMessage());
                        System.err.println(this.getClass().getName() + " Exception -> HTLS2021: " + e.getMessage());            
                    }
                }
            
                return "success";
            
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " getDataQtKehoach " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataQtKehoach " + ex.getMessage());
        }

        return SUCCESS;
    }

    public String guiChinhanh() {
        try {
            getInfo();
            String message = daoXdkh.getCheckInputPGD(maBc, namBc, dotBc, pos_cd_username, reportGrade, userId);
            if (!message.endsWith("AAA")) {
                addActionError("Bạn chưa nhập số liệu mẫu 02 tại pgd!");
                return ERROR;
            }
            addActionMessage("Bạn đã gửi thành công số liệu lên chi nhánh");
            return SUCCESS;
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " ExpExcelKhnv01 " + ex.getMessage());
            System.err.println(this.getClass().getName() + " Loi ExpExcelKhnv01 " + ex.getMessage());
            addActionError("Gửi lỗi!");
            return ERROR;
        }
    }

    public String getSubcommune() {
        try {
//            commune_cd = request.getParameter("commune_cd");
//            setSubCommuneList(daoXdkh.getSubCommuneList(pos_cd_username, commune_cd, reportGrade));
            getInfo();
            custCommuneList = daoXdkh.getPosList(pos_cd_username, maCn, reportGrade);
            if (commune_cd.isEmpty()) {
                custSubCommuneList = daoXdkh.getSubCommuneList(pos_cd_username, "", reportGrade);
            } else {
                custSubCommuneList = daoXdkh.getSubCommuneList(pos_cd_username, commune_cd, reportGrade);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getSubcommune -> " + e.getMessage());
        }
        return "success";
    }

    public String Lock_Unlock() {
        try {
            getInfo();
            HashMap hmParameter = getParameter();
//            String a =hmParameter.get("lock_unlock").toString();
            if (daoXdkh.setLockUnlockCommune(commune_cd, namBc, dotBc, lock_unlock, userId, reportGrade)) {
                addActionMessage("Bạn đã chốt/mở chốt thành công");
                return SUCCESS;
            } else {
                addActionError("Bạn đã chốt/mở chốt thất bại. Vui lòng liên hệ với quản trị");
                return ERROR;
            }
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " Lock_Unlock " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi Lock_Unlock " + ex.getMessage());
            addActionError("Bạn đã chốt/mở chốt thất bại. Vui lòng liên hệ với quản trị");
            return ERROR;
        }

//            return SUCCESS;
    }

    public String getDataXayCommuneDetai() {
        try {

            getInfo();
            //TH load theo 1 thôn
            lstDulieuNt = daoXdkh.getDataAuthCommune(maBc, userId, reportGrade, namBc, dotBc, commune_detai, "000000");
            return "loadAllSubCommune";

        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " getDataXayCommuneDetai " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataXayCommuneDetai " + ex.getMessage());
            return ERROR;
        }

    }

    public String getDataXaySubCommuneDetai() {
        try {

            getInfo();
            //TH load theo 1 thôn
            lstDulieuNt = daoXdkh.getDataAuthCommune(maBc, userId, reportGrade, namBc, dotBc, commune_detai, subcommune_detail);
            return "loadOneSubCommune";

        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " get_data_xaydungkh " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataXayDungKhDetail " + ex.getMessage());
            return ERROR;
        }

    }

    public String ExpExcelKhnv01() {
        try {
            getInfo();
            request = ServletActionContext.getRequest();
            String savedDir = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");
            ExcelExport excelExport = new ExcelExport();
            List<String> lstCommune = new ArrayList<>();
            if (!commune_cd.equals("000000")) {
                lstCommune.add(commune_cd);
            } else {
                lstCommune = daoXdkh.getAllCommune(pos_cd_username, maBc, namBc, dotBc);
            }
            FileExportInfo fileInfo = excelExport.xuatExcelMau01(lstCommune, savedDir, namBc, dotBc, pos_cd_username);
            fileNamelocal = fileInfo.fileName;
            filereport = fileInfo.filePath;
            return SUCCESS;
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " ExpExcelKhnv01 " + ex.getMessage());
            System.err.println(this.getClass().getName() + " Loi ExpExcelKhnv01 " + ex.getMessage());
            return ERROR;
        }
    }

    public String openExcelUpload() {
        return SUCCESS;
    }
//<editor-fold defaultstate="collapsed" desc="Getter Setter">

//</editor-fold>    
    public List<POSModel> getCustCommuneList() {
        return custCommuneList;
    }

    public void setCustCommuneList(List<POSModel> custCommuneList) {
        this.custCommuneList = custCommuneList;
    }

    public List<POSModel> getCustSubCommuneList() {
        return custSubCommuneList;
    }

    public void setCustSubCommuneList(List<POSModel> custSubCommuneList) {
        this.custSubCommuneList = custSubCommuneList;
    }
}
