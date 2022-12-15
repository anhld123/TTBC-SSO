/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.bcqt.action;

import static com.opensymphony.xwork2.Action.ERROR;
import vbsp.ims.nghiquyet11cp.*;
import vbsp.ims.nhaptaycn.action.*;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.dao.DaoBcqtMain;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.restapi.CommissionMasterModel;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.IntDeductionModel;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.LockSendModel;
import vbsp.ims.restapi.NQ11cpModel;
import vbsp.ims.restapi.UpdateLockModel;

import vbsp.ims.util.DateUtil;

/**
 *
 * @author Trung
 */
public class BCQT_HOAHONG extends ActionBcqtMain
        implements NhaptaycnFunction {

    DuLieuNTService service;

    @Override
     public String load() {
        try {
            if (Grade.equals("1")) {

                if (!getParaSession()) {
                    return ERROR;
                }
                HashMap hmParameter = getParameter();
                Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(hmParameter.get("ngay_bc").toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
                String dateStr = sdf.format(date1);

                posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
                pos_cd_username = posMainModel.getPosCd();
                service = new DuLieuNTService();
                //Kiểm tra xem đã chốt số liệu chưa
                String chotsl = "0";
                ArrayList<LockSendModel> lstDataLock = service.getDataLockSendNQ11CP(pos_cd_username, "S", dateStr);
                //                Check xem khóa chưa
                if (lstDataLock.size() > 0) {
                    if (lstDataLock.get(0).getStatus().equals("1")) {
                        chotsl = "1";
                    }
                }
                String conditions = "";
                String sSoku = "AAA";
                
//                conditions = (hmParameter.get("maxa").toString().equals("000000") ? "" : "D38=" + hmParameter.get("maxa").toString() + "|")
//                        + (hmParameter.get("chuongtrinh").toString().equals("00") ? "" : "D10=" + hmParameter.get("chuongtrinh").toString() + "|")
//                        + (hmParameter.get("nguonvon").toString().equals("-1") ? "" : "D5=" + hmParameter.get("nguonvon").toString() + "|");
//                
                
                
                System.out.println(pos_cd_username + dateStr+conditions);
//                ArrayList<CommissionMasterModel> lstData = service.getDataCommission("BCQT_LAITONAM", "000401", "S", dateStr, conditions);
                ArrayList<CommissionMasterModel> lstData = service.getDataCommission(pos_cd_username,  dateStr, "1","","250101","0209852");
                System.out.println(lstData.size());
                if (lstData.size() > 499) {
                    addActionError("Dữ liệu quá lớn. Vui lòng chọn từng xã để xác nhận.");;
                    return ERROR;
                }
                int iStt = 1;
                for (CommissionMasterModel item : lstData) {
                    QT_DULIEU_NT row = new QT_DULIEU_NT();
                     System.out.println(item.getCustomerName());
                    row.setTHUTU(iStt);
                    iStt++;
                    
                    Date reportDate = DateUtil.toDate(item.getReportDate());
                    row.setNGAYBC(reportDate);
                    //row.setNAMBC(item.getReportYear());
                    row.setMAPGD(item.getPosCode());
                    row.setMACN(item.getMainPos());

                    row.setD1(item.getGroupLeaderName());
                    row.setD2(item.getGroupLeaderCif());
                    row.setD3(item.getCustomerName());
                    row.setD4(item.getCustomerId());
                    row.setD5(item.getLoanId());
                    row.setD6(String. valueOf(item.getPrinTotal()));
//                    row.setD7(item.getD7());
//                    row.setD8(item.getD8());
//                    row.setD9(item.getD9());
//                    row.setD10(item.getD10());
//                    row.setD11(item.getD11());
//                    row.setD12(item.getD12());
//                    row.setD13(item.getD13());
//                    row.setD14(item.getD14());
//                    row.setD15(item.getD15());
//                    row.setD16(item.getD16());
//                    row.setD17(item.getD17());
//                    row.setD18(item.getD18());
//                    row.setD19(item.getD19());
//                    row.setD20(item.getD20());
//                    row.setD21(item.getD21());
//                    row.setD22(item.getD22());
//                    row.setD23(item.getD23());
//                    row.setD24(item.getD24());
//                    row.setD25(item.getD25());
//                    row.setD26(item.getD26());
//                    row.setD27(item.getD27());
//                    row.setD28(item.getD28());
//                    row.setD29(item.getD29());
//                    
//                    row.setD30(item.getD30());
//                    row.setD31(item.getD31());
//                    row.setD32(item.getD32());
//                    row.setD33(item.getD33());
//                    row.setD34(item.getD34());
//                    row.setD35(item.getD35());
//                    row.setD36(item.getD36());
//                    row.setD37(item.getD37());
//                    row.setD38(item.getD38());
//                    row.setNHAPTAY(item.getManualFlag());
//                    row.setFONTFORMAT(item.getFontFormat());
//                    row.setKIEUIN(item.getStyle());
                    lstDulieuNt.add(row);
                }

                return SUCCESS;
            } else if (Grade.equals("2")) {
                if (!getParaSession()) {
                    return ERROR;
                }
                HashMap hmParameter = getParameter();
                Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(hmParameter.get("ngay_bc").toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String dateStr = sdf.format(date1);

                posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
                pos_cd_username = posMainModel.getPosCd();
                service = new DuLieuNTService();
                ArrayList<LockSendModel> lstData = service.getDataLockSendNQ11CP(pos_cd_username, "M", dateStr);
                int i = 0;

                DecimalFormat df = new DecimalFormat("#.##");
                //String formatted = df.format(2.00023);

                for (LockSendModel item : lstData) {
                    i++;
                    QT_DULIEU_NT row = new QT_DULIEU_NT();
                    row.setKHOA("CN11CP_002");
                    row.setTHUTU(i);
                    Date reportDate = DateUtil.toDate(item.getReportDate());
                    row.setNGAYBC(reportDate);
                    //row.setNAMBC(item.getReportYear());
                    row.setMAPGD(item.getPosCode());
//                row.setCO_TONGHOP(item.getPosFlag());
                    row.setMACN(item.getMainPos());
                    row.setTEN(item.getPosName());
                    row.setD1(df.format(item.getLoanTotal()));
                    row.setD2(df.format(item.getPrinTotal()));
                    row.setD3(df.format(item.getIntTotal()));
                    row.setD4(df.format(item.getDeductionIntTotal()));
                    row.setD5(df.format(item.getDeductionLoanTotal()));
                    row.setD6(df.format(item.getNoDeductionLoanTotal()));
                    row.setD7(df.format(item.getNoDeductionIntTotal()));
                    row.setD25(item.getStatus());
//                    row.setD7(df.format(item.getDeductionIntTotal()));                   
                    lstDulieuNt.add(row);
                }
                return "cap2_chot";
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> NQ11CP_02SK: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> NQ11CP_02SK: " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;

    }

   
    @Override
    public String save() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }

            HashMap hmParameter = getParameter();
            String sngaybc = hmParameter.get("ngay_bc").toString();
            Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(sngaybc);

//            Date date = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String strDate = dateFormat.format(date1);

            DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
            String strDate1 = dateFormat1.format(date1);

            service = new DuLieuNTService();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();

            if (Grade.equals("1")) {
                ArrayList<LockSendModel> lstDataLock = service.getDataLockSendNQ11CP(pos_cd_username, "S", strDate);
                ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
                if (lstDataLock.size() > 0) {
                    //Lưu phần phân loại hạch toán
                    if (lstDataLock.get(0).getStatus().equals("1")) {
                        addActionError("Đơn vị đã chốt số liệu. Bạn không thể điều chỉnh.");;
                        return ERROR;
                    } //Lưu phần xác nhận lãi giảm
                }

//                         DecimalFormat df = new DecimalFormat("#.##");
                ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
                for (QT_DULIEU_NT tmp : lstDulieuNt) {
                        DuLieuNTRow tempadd = new DuLieuNTRow();
                        tempadd.setBranchCode(tmp.getMACN());
                        tempadd.setPosCode(tmp.getMAPGD());
                        tempadd.setCode(tmp.getMA());
                        tempadd.setD15(tmp.getD15());
                        tempadd.setD19(tmp.getD19());
                        tempadd.setD37(tmp.getD37());
                        tempadd.setD30(tmp.getD30());
                        tempadd.setD31(tmp.getD32());
                        tempadd.setD32(tmp.getD32());
                        
                        tempadd.setD33(tmp.getD33());
                        tempadd.setD34(tmp.getD34());
                        tempadd.setD35(tmp.getD35());
                        lstUpdateDate.add(tempadd);
                        lstLocalDataUpdate.add(tmp);                    
                }
                int status = service.updateData("BCQT_LAITONAM", pos_cd_username, "S", strDate, UserName, UserName, lstUpdateDate);
                if (status == 200) {
                    System.out.println("vbsp.ims.nghiquyet11cp.BCQT_LAITONAM.save()");                    
                    if (!DaoBcqtMain.newInstance().saveBcqtLaitonAm("BCQT_LAITONAM",UserName, pos_cd_username, strDate1, lstLocalDataUpdate)) {
                        addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                        return ERROR;
                    }
                }
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> NQ11CP_02SK: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> NQ11CP_02SK: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }

        addActionMessage(
                "Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

}
