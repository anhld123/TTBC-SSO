/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.nghiquyet11cp;

import vbsp.ims.nhaptaycn.action.*;
import static com.opensymphony.xwork2.Action.ERROR;
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
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
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
public class NQ11CP_01KH extends ActionNhaptaycnMain
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
                setThangbc(sdf1.format(date1));
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

                ArrayList<NQ11cpModel> lstData = service.getDataNQ11CP_01KH(pos_cd_username, dateStr);
                if (lstData.size() > 499) {
                    addActionError("Dữ liệu quá lớn. Vui lòng chọn từng tổ để xác nhận.");;
                    return ERROR;
                }
                margerData(lstData, chotsl);

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
            CoreLogger.error(this.getClass().getName() + " Exception -> NQ11CP_01KH: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> NQ11CP_01KH: " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;

    }

    public void margerData(List<NQ11cpModel> lstData, String chotsl) {
        int i = 1;

        QT_DULIEU_NT tong = new QT_DULIEU_NT();
        double dn_tronhan = 0;
        double dn_quahan = 0;
        double dn_khoanh = 0;

        BigInteger lai_t10 = new BigInteger("0");
        BigInteger lai_t11 = new BigInteger("0");
        BigInteger lai_t12 = new BigInteger("0");

        BigInteger lai_t10dc = new BigInteger("0");
        BigInteger lai_t11dc = new BigInteger("0");
        BigInteger lai_t12dc = new BigInteger("0");

        BigInteger lai_18 = new BigInteger("0");
        BigInteger lai_20 = new BigInteger("0");
        BigInteger lai_21 = new BigInteger("0");

        DecimalFormat df = new DecimalFormat("#.##");
        try {
            QT_DULIEU_NT row_main = new QT_DULIEU_NT();
            row_main.setKHOA("SUBS_001");
            row_main.setTHUTU(0);

            row_main.setTT_HIENTHI("20220228");
            row_main.setD3("6000000000000");
            row_main.setD50("Tổng");
            lstDulieuNt.add(row_main);
            for (NQ11cpModel item : lstData) {
                try {
                    QT_DULIEU_NT row = new QT_DULIEU_NT();
                    row.setKHOA("SUBS_001");
                    row.setTHUTU(i);

                    row.setTT_HIENTHI("20220228");
                    Date reportDate = DateUtil.toDate(item.getReportDate());
                    row.setNGAYBC(reportDate);
                    row.setMAPGD(item.getPosCode());
                    row.setMACN(item.getMainPos());
                    row.setD1(item.getGroupId());
                    row.setD2(item.getCustomerId());
                    row.setD3(item.getLoanId());
                    row.setD4(df.format(item.getPrinTotal()));
                    row.setD5(df.format(item.getNormalAmt()));
                    row.setD6(df.format(item.getOverdueAmt()));
                    row.setD7(df.format(item.getFreezeAmt()));

                    row.setD8(df.format(item.getInterestRate()));
                    row.setD9(item.getLoanProgram());
                    row.setD10(item.getSpecificProductCode());
                    row.setD11(item.getDecisionCode());
                    row.setD12(item.getLoanStatus());
                    row.setD13(item.getCapitalSourceCode());
                    row.setD14(item.getInvestorCode());
                    row.setD15(item.getCasaAccount());
                    row.setD16(df.format(item.getIntTotalAmt()));
                    row.setD17(df.format(item.getIntSubsidyTotalAmt()));
                    row.setD18(df.format(item.getIntSubsidyM01Amt()));
                    row.setD19(df.format(item.getIntSubsidyM02Amt()));
                    row.setD20(df.format(item.getIntSubsidyM03Amt()));
                    //Bỏ TH món vay đóng và lãi <5k
                    row.setD21(df.format(item.getIntSubsidyAdjustM01Amt()));
                    row.setD22(df.format(item.getIntSubsidyAdjustM02Amt()));
                    row.setD23(df.format(item.getIntSubsidyAdjustM03Amt()));
                    row.setD24(item.getPaymentFlag().equals("0") ? "RPA" : "HT phải trả");
                    row.setD25(item.getIntConfirmFlag());
                    row.setD26(item.getSubsidyTranRef());
                    row.setD27(item.getSubsidyTranDate());

                    row.setD28(df.format(item.getAccountingIntAmt()));
                    row.setD29(df.format(item.getRpaAmt()));
                    row.setD30(df.format(item.getCasaAmt()));
                    row.setD31(df.format(item.getCashAmt()));

                    row.setD32(item.getPosTranRef());
                    row.setD33(item.getM01Status());
                    row.setD34(item.getM02Status());
                    row.setD35(item.getM03Status());
                    row.setD36(item.getPaymentFlag());

                    row.setD43(df.format(item.getIntTotalM01Amt()));
                    row.setD44(df.format(item.getIntTotalM02Amt()));
                    row.setD45(df.format(item.getIntTotalM03Amt()));
                    row.setD46(item.getCommuneId());
                    row.setD47(item.getRejectReason());
                    row.setD50(item.getCustomerName());
                    lstDulieuNt.add(row);
                } catch (Exception e) {
                    CoreLogger.error(this.getClass().getName() + " Exception -> NQ11CP_01KH: " + e.getMessage());
                    System.err.println(this.getClass().getName() + " Exception -> NQ11CP_01KH: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> NQ11CP_01KH: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> NQ11CP_01KH: " + e.getMessage());
        }
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
                ArrayList<NQ11cpModel> lstUpdateDate = new ArrayList<>();
                if (lstDataLock.size() > 0) {
                    //Lưu phần phân loại hạch toán
                    if (lstDataLock.get(0).getStatus().equals("1")) {
                        addActionError("Đơn vị đã chốt số liệu. Bạn không thể điều chỉnh.");;
                        return ERROR;
                    } //Lưu phần xác nhận lãi giảm
                    else {
//                         DecimalFormat df = new DecimalFormat("#.##");
                        for (QT_DULIEU_NT tmp : lstDulieuNt) {
                            if (tmp.getD33() != null) {

                                NQ11cpModel tempadd = new NQ11cpModel();
                                if (tmp.getD25() == null) {
                                    tempadd.setMainPos(tmp.getMACN());
                                    tempadd.setPosCode(tmp.getMAPGD());
                                    tempadd.setLoanId(tmp.getD3());
                                    tempadd.setIntSubsidyAdjustM01Amt(new BigInteger(tmp.getD18()));
                                    tempadd.setIntSubsidyAdjustM02Amt(new BigInteger(tmp.getD19()));
                                    tempadd.setIntSubsidyAdjustM03Amt(new BigInteger(tmp.getD20()));
                                    tempadd.setIntConfirmFlag("0");
                                    tempadd.setRejectReason(tmp.getD47());
                                    lstUpdateDate.add(tempadd);
                                } else {
                                    tempadd.setMainPos(tmp.getMACN());
                                    tempadd.setPosCode(tmp.getMAPGD());
                                    tempadd.setLoanId(tmp.getD3());
                                    tempadd.setIntSubsidyAdjustM01Amt(new BigInteger(tmp.getD18()));
                                    tempadd.setIntSubsidyAdjustM02Amt(new BigInteger(tmp.getD19()));
                                    tempadd.setIntSubsidyAdjustM03Amt(new BigInteger(tmp.getD20()));

                                    tempadd.setM01Status("1");
                                    tempadd.setM02Status("1");
                                    tempadd.setM03Status("1");
                                    tempadd.setIntConfirmFlag("1");
                                    tempadd.setRejectReason(tmp.getD47());
                                    lstUpdateDate.add(tempadd);
                                }
                            }
                        }
                        int status = service.updateDataNQ11CP_001(pos_cd_username, strDate, UserName, lstUpdateDate);
                        if (status == 200) {
                            ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
                            DecimalFormat df = new DecimalFormat("#.##");
                            for (NQ11cpModel item : lstUpdateDate) {
                                QT_DULIEU_NT updateRow = new QT_DULIEU_NT();
                                updateRow.setD1(item.getLoanId());

                                updateRow.setD2(df.format(item.getIntSubsidyAdjustM01Amt()));
                                updateRow.setD3(df.format(item.getIntSubsidyAdjustM02Amt()));
                                updateRow.setD4(df.format(item.getIntSubsidyAdjustM03Amt()));
                                updateRow.setD5(item.getIntConfirmFlag().toString());
                                updateRow.setD6(item.getRejectReason());

                                lstLocalDataUpdate.add(updateRow);
                            }
                            if (!DaoNghiquyet11cp.newInstance().saveNQ11CP_001(UserName, pos_cd_username, strDate1, lstLocalDataUpdate, "1")) {
                                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                                return ERROR;
                            }
                        }
                    }
                }

            } else if (Grade.equals("2")) {
                ArrayList<UpdateLockModel> lstUpdateDateLock = new ArrayList<>();
                for (QT_DULIEU_NT tmp : lstDulieuNt) {
                    UpdateLockModel tempadd = new UpdateLockModel();
                    if (tmp.getD25() == null) {
                        tempadd.setPosCode(tmp.getMAPGD());
                        tempadd.setStatus("0");
                        lstUpdateDateLock.add(tempadd);
                    } else {
                        tempadd.setPosCode(tmp.getMAPGD());
                        tempadd.setStatus("1");
                        lstUpdateDateLock.add(tempadd);
                    }
                }
                int status = service.updateDataNQ11CP_ChotSL(pos_cd_username, strDate, UserName, lstUpdateDateLock);
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> NQ11CP_01KH: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> NQ11CP_01KH: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public static void main(String[] args) {
        DuLieuNTService service = new DuLieuNTService();
//        Date timeServer = service.getTimeServer();

//         Date date = Calendar.getInstance().getTime();  
//                DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
//                String strDate = dateFormat.format(timeServer);  
//        System.out.println("Converted String: " + service.getTimeServer());
//          String file = "NV_QT_000401_S_31122021_quyennv_6283";
//          
//          String[] array = file.split("_", -1);
//          String s1 = array[0];
//          String s2 = array[3];
//          String s3 = array[1];    
//        ArrayList<DuLieuNTRow> lstData = service.getData("COVID_03", "000401", "S", "20210630");
//        System.out.println(lstData.size());
//
//        ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
//        DuLieuNTRow testItem = new DuLieuNTRow();
//        testItem.setKey("COVID_03");
//        testItem.setCode("1004003452");
//        testItem.setReportDate("2021-06-30T00:00:00");
//        testItem.setPosCode("000401");
//        testItem.setPosFlag("S");
//        testItem.setD1("1004003452");
//        testItem.setD2("100");
//        testItem.setReportYear(2021);
//        lstUpdateDate.add(testItem );
//
//        int status = service.updateData("COVID_03", "000401", "S", "20210630", "trungnt", "", lstUpdateDate);
//        List<PLNO_DULIEU> lstPLNo = new ArrayList<>();
//        PLNO_DULIEU plno_dulieu = PLNO_DULIEU.newInstance();
//        plno_dulieu.setsSoku("6600000715491945");
//        plno_dulieu.setsSoku("6600000717477667");
//        lstPLNo.add(plno_dulieu);
//        List<NQ11cpModel> lstDulieuNt = new ArrayList<>();
        List<QT_DULIEU_NT> lstDulieuNt1 = new ArrayList<>();
        service = new DuLieuNTService();
//        lstDulieuNt = service.getDataNQ11CP("000601", "20220228", "03",
//                "060101", "0091543");

        ArrayList<NQ11cpModel> lstData = service.getDataNQ11CP_01KH("000601", "20220228");
//        margerData(lstData,"0","0000");
    }
}
