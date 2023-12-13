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
import vbsp.ims.restapi.CheckSendModel;
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
public class BCQT_LAITONAM extends ActionBcqtMain
        implements NhaptaycnFunction {

    DuLieuNTService service;

    @Override
    public String load() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            System.out.println("LAITONAM =" + Grade);
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

                conditions = (hmParameter.get("maxa").toString().equals("000000") ? "" : "D38=" + hmParameter.get("maxa").toString() + "|")
                        + (hmParameter.get("chuongtrinh").toString().equals("00") ? "" : "D25=" + hmParameter.get("chuongtrinh").toString() + "|")
                        + (hmParameter.get("nguonvon").toString().equals("-1") ? "" : "D5=" + hmParameter.get("nguonvon").toString() + "|");

                System.out.println(pos_cd_username + dateStr + conditions);
                ArrayList<DuLieuNTRow> lstData = service.getDataNQ11CP_02SK("BCQT_LAITONAM", pos_cd_username, "S", dateStr, conditions);
                System.out.println(lstData.size());
                if (lstData.size() > 499) {
                    addActionError("Dữ liệu quá lớn. Vui lòng chọn từng xã để xác nhận.");;
                    return ERROR;
                }
                int iStt = 1;
                for (DuLieuNTRow item : lstData) {
                    QT_DULIEU_NT row = new QT_DULIEU_NT();
                    row.setKHOA(item.getKey());
                    row.setTHUTU(iStt);
                    iStt++;
                    row.setTT_HIENTHI(item.getOrderDescription());
                    row.setMA(item.getCode());
                    row.setTEN(item.getName());

                    Date reportDate = DateUtil.toDate(item.getReportDate());
                    row.setNGAYBC(reportDate);
                    //row.setNAMBC(item.getReportYear());
                    row.setMAPGD(item.getPosCode());
                    row.setCO_TONGHOP(item.getPosFlag());
                    row.setMACN(item.getBranchCode());
                    row.setNGUOI_NHAP(item.getMakerId());
                    //row.setNGAY_NHAP(item.getMakerDate());
                    Date makerDate = DateUtil.toDate(item.getMakerDate());
                    row.setNGAY_NHAP(makerDate);
                    row.setNGUOI_DUYET(item.getAuthoriseId());
                    //row.setNGAY_DUYET(item.getAuthoriseDate());
                    Date authoriseDate = DateUtil.toDate(item.getAuthoriseDate());
                    row.setNGAY_DUYET(authoriseDate);
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
                    row.setD11(item.getD11());
                    row.setD12(item.getD12());
                    row.setD13(item.getD13());
                    row.setD14(item.getD14());
                    row.setD15(item.getD15());
//                    row.setD16(item.getD16());
                    row.setD17(item.getD17());
                    row.setD18(item.getD18());
                    row.setD19(item.getD19());
//                    row.setD20(item.getD20());
                    row.setD21(item.getD21());
                    row.setD22(item.getD22());
                    row.setD23(item.getD37());
//                    row.setD23(item.getD23());
//                    row.setD24(item.getD24());
//                    row.setD25(item.getD25());
//                    row.setD26(item.getD26());
                    row.setD27(item.getD27());
                    row.setD28(item.getD28());
                    row.setD24(item.getD29());
                    row.setD25(item.getD30());
                    row.setD26(item.getD31());
                    row.setD20(item.getD32());
                    row.setD16(item.getD33());
                    row.setD29(item.getD34());
//                    row.setD35(item.getD35());
//                    row.setD36(item.getD36());

//                    row.setD38(item.getD38());
                    row.setD39(item.getD39());
                    row.setNHAPTAY(item.getManualFlag());
                    row.setFONTFORMAT(item.getFontFormat());
                    row.setKIEUIN(item.getStyle());
                    lstDulieuNt.add(row);
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

                ArrayList<CheckSendModel> lstData = service.getDataLockLaitonAm(pos_cd_username, "M", dateStr);
                int i = 0;
                System.out.println("vao cap 2" + lstData.size());
                DecimalFormat df = new DecimalFormat("#.##");
                //String formatted = df.format(2.00023);

                for (CheckSendModel item : lstData) {
                    i++;
                    QT_DULIEU_NT row = new QT_DULIEU_NT();
                    row.setKHOA("BCQT_LAITONAM");
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

    @Override
    public String save() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            System.out.println("lưu 2 =" + lstDulieuNt.size());
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
//                ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
                for (QT_DULIEU_NT tmp : lstDulieuNt) {
                    DuLieuNTRow tempadd = new DuLieuNTRow();
                    tempadd.setBranchCode(tmp.getMACN());
                    tempadd.setPosCode(tmp.getMAPGD());
                    tempadd.setCode(tmp.getMA());
                    tempadd.setD15(tmp.getD15());
                    tempadd.setD19(tmp.getD19());
                    tempadd.setD37(tmp.getD23());

                    tempadd.setD29(tmp.getD24());
                    tempadd.setD30(tmp.getD25());
                    tempadd.setD31(tmp.getD26());
                    tempadd.setD32(tmp.getD20());

                    tempadd.setD33(tmp.getD16());
                    tempadd.setD34(tmp.getD29());
                    tempadd.setD35(tmp.getD39());
//                        tempadd.setD35(tmp.getD2());
                    lstUpdateDate.add(tempadd);
//                        lstLocalDataUpdate.add(tmp);                    
                }
                int status = service.updateData("BCQT_LAITONAM", pos_cd_username, "S", strDate, UserName, UserName, lstUpdateDate);
                if (status == 200) {
                    System.out.println("vbsp.ims.nghiquyet11cp.BCQT_LAITONAM.save()");
                    if (!DaoBcqtMain.newInstance().saveBcqtLaitonAm("BCQT_LAITONAM", UserName, pos_cd_username, strDate1, lstDulieuNt)) {
                        addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                        return ERROR;
                    }
                }
            } else if (Grade.equals("2")) {
                System.out.println("Cấp 2 chốt sl");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
                String dateStr = sdf.format(date1);
                ArrayList<LockSendModel> lstData = service.getDataLockManual("BCQT_LAITONAM", pos_cd_username, "M", dateStr);
                for (LockSendModel item : lstData) {
                    if (item.getStatus().equals("2")) {
                        addActionError("Trung ương đã chốt số liệu. Vui lòng liên hệ cấp trên");
                        return ERROR;
                    }
                }

                ArrayList<UpdateLockModel> lstUpdateDateLock = new ArrayList<>();
                for (QT_DULIEU_NT tmp : lstDulieuNt) {

                    UpdateLockModel tempadd = new UpdateLockModel();
                    if (tmp.getD25() == null) {
                        tempadd.setPosCode(tmp.getMAPGD());
                        tempadd.setStatus("0");
//                        lstUpdateDateLock.add(tempadd);
                        int status = service.updateLockManual("BCQT_LAITONAM", tmp.getMAPGD(), "S", strDate, "0", UserName, lstUpdateDateLock);
                    } else {
                        System.out.println("luu =" + tmp.getD25() + "--" + tmp.getMAPGD() + "--" + strDate + "-" + UserName);
                        tempadd.setPosCode(tmp.getMAPGD());
                        tempadd.setStatus("1");
//                        lstUpdateDateLock.add(tempadd);
                        int status = service.updateLockManual("BCQT_LAITONAM", tmp.getMAPGD(), "S", strDate, "1", UserName, lstUpdateDateLock);
//                        System.out.println("status =" + status);
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
