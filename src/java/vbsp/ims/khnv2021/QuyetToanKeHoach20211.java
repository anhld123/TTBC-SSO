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
import vbsp.ims.restapi.LockSendModel;
import vbsp.ims.restapi.UpdateLockModel;
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
//            if (!reportGrade.equals("1")) {
//                addActionError("Chức năng này chỉ thực hiện cho cấp PGD");
//                return ERROR;
//            }
            posList = daoXdkh.getPosList(pos_cd_username, maCn, reportGrade);
            subCommuneList = daoXdkh.getSubCommuneList(pos_cd_username, "", reportGrade);
            lstMaBC = daoXdkh.getLOV(userId, Define.LOV_MABC_QT);
            lstNamBC = daoXdkh.getLOV(userId, Define.LOV_NAMBC);
            lstDonvi = daoXdkh.getLOV(userId, Define.LOV_DONVI);
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

            FileExportInfo fileInfo = excelExport.xuatExcelMauQT11(pos_cd_username, reportGrade, "31-DEC-" + namBc, namBc, savedDir, userId);
            fileNamelocal = fileInfo.fileName;
            filereport = fileInfo.filePath;
            return SUCCESS;
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " ExpExcelEcelQtKhnv11 " + ex.getMessage());
            System.err.println(this.getClass().getName() + " Loi ExpExcelEcelQtKhnv11 " + ex.getMessage());
            return ERROR;
        }
    }
//    Lấy số liệu từ api
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
            ArrayList<DuLieuNTRow> lstData = new ArrayList<>();
            if (reportGrade.equals("1")) {
                lstData = service.getData(Define.NV_QT, pos_cd_username, "S", namBc + "1231");
            }
            else if(donvi.equals("000000") && reportGrade.equals("2"))
            {
                lstData = service.getData(Define.NV_QT, pos_cd_username, "M", namBc + "1231");
            }
            else if(!donvi.equals("000000") && reportGrade.equals("2"))
            {
                lstData = service.getData(Define.NV_QT, donvi, "S", namBc + "1231");
            }
           
                                            
            
            lstData.sort(Comparator.comparing(o -> Integer.parseInt(o.getOrderValue())));
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
                    CoreLogger.error(this.getClass().getName() + " Exception -> getDataQtKehoach: " + e.getMessage());
                    System.err.println(this.getClass().getName() + " Exception -> getDataQtKehoach: " + e.getMessage());
                }
            }

            return "success";

        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " getDataQtKehoach " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataQtKehoach " + ex.getMessage());
        }

        return SUCCESS;
    }
    
    public String TongHopQtKehoach() {
        try {
            HashMap hmParameter = getParameter();
            int yearPre = Integer.parseInt(namBc) - 1;
            int year2Pre = Integer.parseInt(namBc) - 2;
            namBc_pre = String.valueOf(yearPre);
            namBc_2pre = String.valueOf(year2Pre);

            getInfo();
            service = new DuLieuNTService();
            
            
            ArrayList<DuLieuNTRow> lstData = new ArrayList<>();
            int sTonghop =service.summaryData(pos_cd_username,  "M", namBc + "1231", userId);
            
            lstData = service.getData(Define.NV_QT, pos_cd_username, "M", namBc + "1231");
                                            
            
            lstData.sort(Comparator.comparing(o -> Integer.parseInt(o.getOrderValue())));
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
                    CoreLogger.error(this.getClass().getName() + " Exception -> getDataQtKehoach: " + e.getMessage());
                    System.err.println(this.getClass().getName() + " Exception -> getDataQtKehoach: " + e.getMessage());
                }
            }

            return "success";

        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " getDataQtKehoach " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataQtKehoach " + ex.getMessage());
        }

        return SUCCESS;
    }

    public String chotQtKehoach() {
        try {
            getInfo();

            ArrayList<UpdateLockModel> lstUpdateDateLock = new ArrayList<>();
            service = new DuLieuNTService();
            if (reportGrade.equals("1")) {
                service.getSetLockDataManual(Define.NV_QT,pos_cd_username,"S",namBc + "1231",Define.NHAPTAY_CHOT,userId);
            }   
            else if(donvi.equals("000000") && reportGrade.equals("2"))
            {       
                service.getSetLockDataManual(Define.NV_QT,pos_cd_username,"M",namBc + "1231",Define.NHAPTAY_CHOT,userId);
            }
            else if(!donvi.equals("000000") && reportGrade.equals("2"))
            {
                service.getSetLockDataManual(Define.NV_QT,donvi,"S",namBc + "1231",Define.NHAPTAY_CHOT,userId);
            }
                                 
            addActionMessage("Bạn đã chốt thành công số liệu.");
            return SUCCESS;
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " ExpExcelKhnv01 " + ex.getMessage());
            System.err.println(this.getClass().getName() + " Loi ExpExcelKhnv01 " + ex.getMessage());
            addActionError("Gửi lỗi!");
            return ERROR;
        }
    }
    
    public String MoChotQtKehoach() {
        try {
            getInfo();

            ArrayList<UpdateLockModel> lstUpdateDateLock = new ArrayList<>();
            if(donvi.equals("000000") && reportGrade.equals("2"))
            {       
//                service.getSetLockDataManual(Define.NV_QT,pos_cd_username,reportGrade,namBc + "1231",Define.NHAPTAY_MOCHOT,userId);
                 addActionError("Không thể mở dữ liệu của chi nhánh!");
                return ERROR;   
            }
            else if(!donvi.equals("000000") && reportGrade.equals("2"))
            {
                service.getSetLockDataManual(Define.NV_QT,donvi,"S",namBc + "1231",Define.NHAPTAY_MOCHOT,userId);
            }
            else if (reportGrade.equals("1")) {
                service.getSetLockDataManual(Define.NV_QT,pos_cd_username,"S",namBc + "1231",Define.NHAPTAY_MOCHOT,userId);
            }                        
            addActionMessage("Bạn đã chốt thành công số liệu.");
            return SUCCESS;
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " ExpExcelKhnv01 " + ex.getMessage());
            System.err.println(this.getClass().getName() + " Loi ExpExcelKhnv01 " + ex.getMessage());
            addActionError("Gửi lỗi!");
            return ERROR;
        }
    }



    public String Lock_Unlock() {
        try {
            getInfo();
            service = new DuLieuNTService();
            ArrayList<UpdateLockModel> lstUpdateDateLock = new ArrayList<>();
            int icheck = service.updateLockManual(Define.NV_QT, pos_cd_username, "S", namBc + "1231", "1",userId,lstUpdateDateLock);

        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " ExpExcelKhnv01 " + ex.getMessage());
            System.err.println(this.getClass().getName() + " Loi ExpExcelKhnv01 " + ex.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }



    public String openExcelUploadQtKh() {
        try {
            getInfo();
            service = new DuLieuNTService();
            ArrayList<LockSendModel> lstDataLock = new ArrayList<>();
            if (reportGrade.equals("1"))
                lstDataLock = service.getDataLockManual(Define.NV_QT, pos_cd_username, "S", namBc + "1231");
            else if (reportGrade.equals("2"))
                lstDataLock = service.getDataLockManual(Define.NV_QT, pos_cd_username, "M", namBc + "1231");
            if (lstDataLock.size() > 0) {
                if (lstDataLock.get(0).getStatus().equals("1")) {
                    addActionError("Đơn vị đã chốt số liệu. Vui lòng liên hệ với cấp trên để mở khóa");
                    return ERROR;
                }
            }
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " ExpExcelKhnv01 " + ex.getMessage());
            System.err.println(this.getClass().getName() + " Loi ExpExcelKhnv01 " + ex.getMessage());
            return ERROR;
        }
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
