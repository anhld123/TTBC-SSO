/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.bcqt.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import vbsp.ims.nghiquyet11cp.*;
import vbsp.ims.nhaptaycn.action.*;
import java.io.File;
import java.math.BigInteger;
import java.sql.Connection;
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
import vbsp.ims.restapi.CheckSendModel;
import vbsp.ims.restapi.CommissionDetailModel;
import vbsp.ims.restapi.CommissionMasterModel;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.IntDeductionModel;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.LockSendModel;
import vbsp.ims.restapi.NQ11cpModel;
import vbsp.ims.restapi.UpdateCommBenModel;
import vbsp.ims.restapi.UpdateCommissionModel;
import vbsp.ims.restapi.UpdateLockModel;

import vbsp.ims.util.DateUtil;

/**
 *
 * @author Trung
 */
public class BCQT_HOAHONG extends ActionBcqtMain
        implements NhaptaycnFunction {

    DuLieuNTService service;
    static ArrayList<CommissionMasterModel> _lstHH = new ArrayList<>();

    @Override
    public String load() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (Grade.equals("1")) {

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

                 lstDulieuHoahong = service.getDataCommission(pos_cd_username, dateStr, 
                        hmParameter.get("nguonvon").toString().equals("-1") ? "" : hmParameter.get("nguonvon").toString(),
                        hmParameter.get("chuongtrinh").toString(), 
                        hmParameter.get("maxa").toString().equals("000000") ? "" : hmParameter.get("maxa").toString(), 
                        hmParameter.get("mato").toString().equals("000000_0000000") ? "" : hmParameter.get("mato").toString());
//                lstDulieuHoahong = service.getDataCommission(pos_cd_username, "20221231",
//                        "2",
//                        "",
//                        "250101",
//                        "0145973");

                _lstHH = lstDulieuHoahong;

                System.out.println("So luong" + lstDulieuHoahong.size());
                if (lstDulieuNt.size() > 99) {
                    addActionError("Dữ liệu quá lớn. Vui lòng chọn từng xã, tổ để xác nhận.");;
                    return ERROR;
                }
                return SUCCESS;
            } else if (Grade.equals("2")) {
                HashMap hmParameter = getParameter();
                Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(hmParameter.get("ngay_bc").toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String dateStr = sdf.format(date1);

                posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
                pos_cd_username = posMainModel.getPosCd();
                service = new DuLieuNTService();
                System.out.println("vao cap 2" + pos_cd_username + dateStr);

                ArrayList<CheckSendModel> lstData = service.getDataLockHoahongBs(pos_cd_username, "M", dateStr);
                int i = 0;
                System.out.println("vao cap 2" + lstData.size());
                DecimalFormat df = new DecimalFormat("#.##");
                //String formatted = df.format(2.00023);

                for (CheckSendModel item : lstData) {
                    i++;
                    QT_DULIEU_NT row = new QT_DULIEU_NT();
                    row.setKHOA("HTLS_HOAHONG_BS");
                    row.setTHUTU(i);
                    Date reportDate = DateUtil.toDate(item.getReportDate());
                    row.setNGAYBC(reportDate);
                    //row.setNAMBC(item.getReportYear());
                    row.setMAPGD(item.getPosCode());
//                row.setCO_TONGHOP(item.getPosFlag());
                    row.setMACN(item.getMainPos());
                    row.setTEN(item.getPosName());
                    row.setD1(df.format(item.getD1()));
                    row.setD2(df.format(item.getD2()));
                    row.setD3(df.format(item.getD3()));
                    row.setD4(df.format(item.getD4()));
                    row.setD5(df.format(item.getD5()));
                    row.setD6(df.format(item.getD6()));
//                    row.setD7(df.format(item.getD3()));

                    row.setD25(item.getStatus());
//                    row.setD7(df.format(item.getDeductionIntTotal()));                   
                    lstDulieuNt.add(row);
                }
                return SUCCESS;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> NQ11CP_02SK: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> NQ11CP_02SK: " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;

    }

    public String loadChitietHoahong() {

        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
//            System.out.println("Vao loadChitietHoahong==" + hmParameter.get("mapgd").toString() + hmParameter.get("ngay_bc").toString()+ hmParameter.get("soku").toString());
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(hmParameter.get("ngay_bc").toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
            String dateStr = sdf.format(date1);

            service = new DuLieuNTService();
            hoahongMaster = service.getDataCommissionByLoan(hmParameter.get("mapgd").toString(), dateStr, hmParameter.get("soku").toString());
            lstHHDetail = hoahongMaster.get(0).getBenCommissionDetails();

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> loadChitietHoahong: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> loadChitietHoahong: " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String saveHoahongDetail() {
        System.err.println("saveHoahongDetail");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstHHDetail == null || lstHHDetail.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }

            HashMap hmParameter = getParameter();
            String sngaybc = hmParameter.get("ngay_bc").toString();
            System.out.println("ngay_bc=" + sngaybc + "-" + hmParameter.get("mapgd").toString());
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sngaybc);

//            Date date = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String strDate = dateFormat.format(date1);

            DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
            String strDate1 = dateFormat1.format(date1);

            service = new DuLieuNTService();

            if (Grade.equals("1")) {
                ArrayList<LockSendModel> lstDataLock = service.getDataLockSendNQ11CP(hmParameter.get("mapgd").toString(), "S", strDate);
                ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
                if (lstDataLock.size() > 0) {
                    //Lưu phần phân loại hạch toán
                    if (lstDataLock.get(0).getStatus().equals("1")) {
                        addActionError("Đơn vị đã chốt số liệu. Bạn không thể điều chỉnh.");;
                        return ERROR;
                    } //Lưu phần xác nhận lãi giảm
                }

//                         DecimalFormat df = new DecimalFormat("#.##");
                ArrayList<UpdateCommissionModel> lstUpdateMaster = new ArrayList<>();
                ArrayList<UpdateCommBenModel> lstUpdateDetai = new ArrayList<>();
                ArrayList<QT_DULIEU_NT> lstDulieuMaster = new ArrayList<>();
                ArrayList<QT_DULIEU_NT> lstDulieuDetail = new ArrayList<>();
                //Lấy dữ liệu detail lưu xuống
                for (CommissionDetailModel tmp : lstHHDetail) {
                    UpdateCommBenModel tempComModel = new UpdateCommBenModel();

                    QT_DULIEU_NT tempDulieuDetail = new QT_DULIEU_NT();
                    System.out.println("tmp.getMainPos() = " + tmp.getMainPos());
                    tempDulieuDetail.setMACN(hoahongMaster.get(0).getMainPos());
                    tempDulieuDetail.setMAPGD(hoahongMaster.get(0).getPosCode());
                    tempDulieuDetail.setMA(hoahongMaster.get(0).getLoanId());
                    tempDulieuDetail.setD1(String.valueOf(tmp.getBenefitRate()));
                    tempDulieuDetail.setD2(String.valueOf(tmp.getBenefitAmount()));
                    tempDulieuDetail.setD3(tmp.getDebitAccount());
                    tempDulieuDetail.setD4(tmp.getCreditAccount());
                    tempDulieuDetail.setD10(tmp.getBenKey());
                    lstDulieuDetail.add(tempDulieuDetail);

                    tempComModel.setMainPos(hoahongMaster.get(0).getMainPos());
                    tempComModel.setPosCode(hoahongMaster.get(0).getPosCode());
                    tempComModel.setLoanId(hoahongMaster.get(0).getLoanId());
                    tempComModel.setBenefitRateAdjust(tmp.getBenefitRate());
                    tempComModel.setBenefitAmountAdjust(tmp.getBenefitAmount());
                    tempComModel.setDebitAccount(tmp.getDebitAccount());
                    tempComModel.setCreditAccount(tmp.getCreditAccount());
//                    tempComModel.setBenefitName(tmp.getBenefitName());
//                    tempComModel.setLevelFlag(tmp.getLevelFlag());
                    tempComModel.setBenKey(tmp.getBenKey());
                    lstUpdateDetai.add(tempComModel);

                }
                QT_DULIEU_NT tempDulieuMaster = new QT_DULIEU_NT();
//                Thông tin dữ liệu master
                System.out.println("hoahongMaster.getMainPos() = " + hoahongMaster.get(0).getMainPos());
                tempDulieuMaster.setMACN(hoahongMaster.get(0).getMainPos());
                tempDulieuMaster.setMAPGD(hoahongMaster.get(0).getPosCode());
                tempDulieuMaster.setMA(hoahongMaster.get(0).getLoanId());
                tempDulieuMaster.setD1(String.valueOf(hoahongMaster.get(0).getCommisionRate()));
                tempDulieuMaster.setD2(String.valueOf(hoahongMaster.get(0).getCommisionTotalAmount()));
                tempDulieuMaster.setD3(String.valueOf(hoahongMaster.get(0).getCommisionGroupAmount()));
                tempDulieuMaster.setD4(String.valueOf(hoahongMaster.get(0).getCommisionDistrictAmount()));
                tempDulieuMaster.setD5(String.valueOf(hoahongMaster.get(0).getCommisionProvinceAmount()));
                lstDulieuMaster.add(tempDulieuMaster);

                UpdateCommissionModel tempCommMaster = new UpdateCommissionModel();
                tempCommMaster.setMainPos(hoahongMaster.get(0).getMainPos());
                tempCommMaster.setPosCode(hoahongMaster.get(0).getPosCode());
                tempCommMaster.setLoanId(hoahongMaster.get(0).getLoanId());
                tempCommMaster.setCommisionRateAdjust(hoahongMaster.get(0).getCommisionRate());
                tempCommMaster.setCommisionTotalAmountAdjust(hoahongMaster.get(0).getCommisionTotalAmount());
                tempCommMaster.setCommisionGroupAmountAdjust(hoahongMaster.get(0).getCommisionGroupAmount());
                tempCommMaster.setCommisionDistrictAmountAdjust(hoahongMaster.get(0).getCommisionDistrictAmount());
                tempCommMaster.setCommisionProvinceAmountAdjust(hoahongMaster.get(0).getCommisionProvinceAmount());
                lstUpdateMaster.add(tempCommMaster);

                posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
                pos_cd_username = posMainModel.getPosCd();

                System.out.println("lstUpdateMaster = " + lstUpdateMaster.size() + "lstDulieu=" + lstDulieuMaster.size() + "pos=" + pos_cd_username + "lstUpdateDetai=" + lstUpdateDetai.size());

                int status = service.updateHoahongMaster(pos_cd_username, strDate, UserName, lstUpdateMaster);
                int status1 = service.updateHoahongDetail(pos_cd_username, strDate, UserName, lstUpdateDetai);
                System.out.println("status=" + status + " --status1 = " + status);
                if (status == 200 && status1 == 200) {
                    System.out.println("saveBcqtHoahongMaster");
                    if (!DaoBcqtMain.newInstance().saveBcqtHoahongMasterDiaphuong("", UserName, pos_cd_username, strDate1, lstDulieuMaster)
                            || !DaoBcqtMain.newInstance().saveBcqtHoahongDetail("", UserName, pos_cd_username, strDate1, lstDulieuDetail)) {
                        addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                        return ERROR;
                    }
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> saveHoahongDetail: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> saveHoahongDetail: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    @Override
    public String save() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuHoahong == null || lstDulieuHoahong.size() == 0) {
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
                ArrayList<UpdateCommissionModel> lstLocalDataUpdate = new ArrayList<>();
                ArrayList<QT_DULIEU_NT> lstDulieu = new ArrayList<>();

                for (CommissionMasterModel tmp : lstDulieuHoahong) {
                    if (tmp.getD33() != null) {
                        System.out.println("getMainPos=" + tmp.getMainPos() + "-soluong = " + lstDulieuHoahong.size());
                        UpdateCommissionModel tempadd = new UpdateCommissionModel();
                        QT_DULIEU_NT tempDulieu = new QT_DULIEU_NT();
                        tempadd.setMainPos(tmp.getMainPos());
                        tempadd.setPosCode(tmp.getPosCode());
                        tempadd.setLoanId(tmp.getLoanId());
                        tempadd.setCommisionRateAdjust(tmp.getCommisionRate());
                        tempadd.setCommisionTotalAmountAdjust(tmp.getCommisionTotalAmount());
                        tempadd.setCommisionGroupAmountAdjust(tmp.getCommisionGroupAmount());
                        lstLocalDataUpdate.add(tempadd);

                        tempDulieu.setMACN(tmp.getMainPos());
                        tempDulieu.setMAPGD(tmp.getPosCode());
                        tempDulieu.setMA(tmp.getLoanId());
                        tempDulieu.setD1(String.valueOf(tmp.getCommisionRate()));
                        tempDulieu.setD2(String.valueOf(tmp.getCommisionTotalAmount()));
                        tempDulieu.setD3(String.valueOf(tmp.getCommisionGroupAmount()));
                        lstDulieu.add(tempDulieu);
                        System.out.println("CommissionMasterModel = " + lstLocalDataUpdate.size() + "lstDulieu=" + lstDulieu.size());
                    }
                }
                int status = service.updateHoahongMaster(pos_cd_username, strDate, UserName, lstLocalDataUpdate);
                System.out.println("status=" + status);
                if (status == 200) {
                    System.out.println("saveBcqtHoahongMaster");
                    if (!DaoBcqtMain.newInstance().saveBcqtHoahongMaster("", UserName, pos_cd_username, strDate1, lstDulieu)) {
                        addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                        return ERROR;
                    }
                }
            } else if (Grade.equals("2")) {
                System.out.println("Cấp 2 chốt sl");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
                String dateStr = sdf.format(date1);
                ArrayList<LockSendModel> lstData = service.getDataLockManual("HTLS_HOAHONG_BS", pos_cd_username, "M", dateStr);
                for (LockSendModel item : lstData) {
                    if (item.getStatus().equals("2")) {
                        addActionError("Trung ương đã chốt số liệu. Vui lòng liên hệ cấp trên");
                        return ERROR;
                    }
                }

                ArrayList<UpdateLockModel> lstUpdateDateLock = new ArrayList<>();
//                for (QT_DULIEU_NT tmp : lstDulieuNt) {
//                    System.out.println("luu =" + tmp.getD25() + "--" + tmp.getMAPGD());
//                    UpdateLockModel tempadd = new UpdateLockModel();
//                    if (tmp.getD25() == null) {
//                         System.out.println("vao 0");
//                        tempadd.setPosCode(tmp.getMAPGD());
//                        tempadd.setStatus("0");
////                        lstUpdateDateLock.add(tempadd);
//                        int status = service.updateLockManual("HTLS_HOAHONG_BS", tmp.getMAPGD(), "S", strDate, "0",UserName,lstUpdateDateLock);
//                    } else {
//                        System.out.println("vao 1");
//                        tempadd.setPosCode(tmp.getMAPGD());
//                        tempadd.setStatus("1");
////                        lstUpdateDateLock.add(tempadd);
//                        int status = service.updateLockManual("HTLS_HOAHONG_BS", tmp.getMAPGD(), "S", strDate, "1",UserName,lstUpdateDateLock);
//                    }
//                }

                System.out.println("Kết thúc");
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> BCQT_HOAHONG: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> BCQT_HOAHONG: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }

        addActionMessage(
                "Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

}
