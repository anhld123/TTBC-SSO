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
import java.text.ParseException;
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
public class NQ11CP_001 extends ActionNghiquyet11cpMain
        implements NhaptaycnFunction {

    DuLieuNTService service;

    @Override
    public String load() {
        try {
            if (Grade.equals("1")) {

//            System.err.println("QD23_001");
                if (!getParaSession()) {
                    return ERROR;
                }
                HashMap hmParameter = getParameter();
//            Connection conn = new DaoConnect().getConnect();
//            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
                Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(hmParameter.get("ngay_bc").toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
                String dateStr = sdf.format(date1);
//                setThangbc(sdf1.format(date1));
                posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
                pos_cd_username = posMainModel.getPosCd();
                service = new DuLieuNTService();
                //Kiểm tra xem đã chốt số liệu chưa
//                String chotsl = "0";
                ArrayList<LockSendModel> lstDataLock = service.getDataLockSendNQ11CP(pos_cd_username, "S", dateStr);
                try {
                    setChotsl(lstDataLock.get(0).getStatus());
                } catch (Exception e) {
                    setChotsl("0");
                }

                //                Check xem khóa chưa
//                if (lstDataLock.size() > 0) {
//                    if (lstDataLock.get(0).getStatus().equals("1")) {
//                        chotsl = "1";
//                    }
//                }
                ArrayList<NQ11cpModel> lstData = service.getDataNQ11CP(pos_cd_username, dateStr, hmParameter.get("chuongtrinh").toString(),
                        hmParameter.get("maxa").toString(), hmParameter.get("mato").toString().split("_")[1]);
                if (lstData.size() > 499) {
                    addActionError("Dữ liệu quá lớn. Vui lòng chọn từng tổ để xác nhận.");;
                    return ERROR;
                }
                margerData(lstData, chotsl, hmParameter.get("nha_dt").toString(), hmParameter.get("giaingan").toString());

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
                chotsl = "";
                for (LockSendModel item : lstData) {
                    if (item.getStatus().equals("0")) {
                        setChotsl("0");
                    }
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
                    row.setD25(chotsl.equals("0") ? item.getStatus() : item.getStatus().equals("3") ? "1" : "0");
//                    row.setD7(df.format(item.getDeductionIntTotal()));                   
                    lstDulieuNt.add(row);
                }
                return "cap2_chot";
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> NQ11CP: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> NQ11CP: " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;

    }

    public void margerData(List<NQ11cpModel> lstData, String chotsl, String sNhadt, String sGiaingan) {
        int i = 1;

        QT_DULIEU_NT tong = new QT_DULIEU_NT();
        double dn_tronhan = 0;
        double dn_quahan = 0;
        double dn_khoanh = 0;

        BigInteger lai_t1 = new BigInteger("0");
        BigInteger lai_t2 = new BigInteger("0");
        BigInteger lai_t3 = new BigInteger("0");
        BigInteger lai_t4 = new BigInteger("0");
        BigInteger lai_t5 = new BigInteger("0");
        BigInteger lai_t6 = new BigInteger("0");
        BigInteger lai_t7 = new BigInteger("0");
        BigInteger lai_t8 = new BigInteger("0");
        BigInteger lai_t9 = new BigInteger("0");

        BigInteger lai_t1dc = new BigInteger("0");
        BigInteger lai_t2dc = new BigInteger("0");
        BigInteger lai_t3dc = new BigInteger("0");
        BigInteger lai_t4dc = new BigInteger("0");
        BigInteger lai_t5dc = new BigInteger("0");
        BigInteger lai_t6dc = new BigInteger("0");
        BigInteger lai_t7dc = new BigInteger("0");
        BigInteger lai_t8dc = new BigInteger("0");
        BigInteger lai_t9dc = new BigInteger("0");

//        BigInteger lai_18 = new BigInteger("0");
        BigInteger lai_20 = new BigInteger("0");
        BigInteger lai_21 = new BigInteger("0");
        BigInteger lai_22 = new BigInteger("0");

        DecimalFormat df = new DecimalFormat("#.##");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat dateHienthi = new SimpleDateFormat("dd/MM/yyyy");
        try {
            for (NQ11cpModel item : lstData) {
                try {
                    QT_DULIEU_NT row = new QT_DULIEU_NT();
                    row.setKHOA("SUBS_001");
                    row.setTHUTU(i);
                    
                    row.setTT_HIENTHI(dateHienthi.format(sdf.parse(item.getDisbursalDate())));
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
                    row.setD55(df.format(item.getIntSubsidyM04Amt()));
                    row.setD37(df.format(item.getIntSubsidyM05Amt()));
                    row.setD38(df.format(item.getIntSubsidyM06Amt()));
                    row.setD51(df.format(item.getIntSubsidyM07Amt()));
                    row.setD60(df.format(item.getIntSubsidyM08Amt()));
                    row.setD63(df.format(item.getIntSubsidyM09Amt()));
                    
                    //Bỏ TH món vay đóng và lãi <5k
                    row.setD21(df.format(item.getIntSubsidyAdjustM01Amt()));
                    row.setD22(df.format(item.getIntSubsidyAdjustM02Amt()));
                    row.setD23(df.format(item.getIntSubsidyAdjustM03Amt()));
                    row.setD56(df.format(item.getIntSubsidyAdjustM04Amt()));
                    row.setD39(df.format(item.getIntSubsidyAdjustM05Amt()));
                    row.setD40(df.format(item.getIntSubsidyAdjustM06Amt()));
                    row.setD52(df.format(item.getIntSubsidyAdjustM07Amt()));
                    row.setD61(df.format(item.getIntSubsidyAdjustM08Amt()));
                    row.setD64(df.format(item.getIntSubsidyAdjustM09Amt()));
                    
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
                    row.setD57(item.getM04Status());
                    row.setD41(item.getM05Status());
                    row.setD42(item.getM06Status());

                    row.setD36(item.getPaymentFlag());
                    row.setD43(df.format(item.getIntTotalM01Amt()));
                    row.setD44(df.format(item.getIntTotalM02Amt()));
                    row.setD45(df.format(item.getIntTotalM03Amt()));
                    row.setD58(df.format(item.getIntTotalM04Amt()));
                    row.setD48(df.format(item.getIntTotalM05Amt()));
                    row.setD49(df.format(item.getIntTotalM06Amt()));
                    row.setD53(df.format(item.getIntTotalM07Amt()));
                    row.setD62(df.format(item.getIntTotalM08Amt()));
                    row.setD65(df.format(item.getIntTotalM09Amt()));

                    row.setD46(item.getCommuneId());
                    row.setD47(item.getRejectReason());
                    row.setD50(item.getCustomerName());
                    row.setD59(item.getPaymentMethod());
//                    Lấy món vay xác nhận lãi
                    if (chotsl.equals("0")) {
                        if ((sNhadt.equals("0000") || sNhadt.equals(item.getInvestorCode()))
                                //                                && (sGiaingan.equals("-1") || sdf.parse(item.getDisbursalDate()).after(df1.parse("04/01/2022")))
                                && (sGiaingan.equals("1") ? item.getLoanStatus().equals("C") : sGiaingan.equals("2") ?
                                sdf.parse(item.getDisbursalDate()).after(df1.parse("06/01/2022")) : sdf.parse(item.getDisbursalDate()).after(df1.parse("06/01/1990")))
                                ) {
                            i++;
                            dn_tronhan = dn_tronhan + (long) item.getNormalAmt();
                            dn_quahan = dn_quahan + (long) item.getOverdueAmt();
                            dn_khoanh = dn_khoanh + (long) item.getFreezeAmt();
                            lai_t1 = lai_t1.add(item.getIntSubsidyM01Amt());
                            lai_t2 = lai_t2.add(item.getIntSubsidyM02Amt());
                            lai_t3 = lai_t3.add(item.getIntSubsidyM03Amt());
                            lai_t4 = lai_t4.add(item.getIntSubsidyM04Amt());
                            lai_t5 = lai_t5.add(item.getIntSubsidyM05Amt());
                            lai_t6 = lai_t6.add(item.getIntSubsidyM06Amt());
                            lai_t7 = lai_t7.add(item.getIntSubsidyM07Amt());
                            lai_t8 = lai_t8.add(item.getIntSubsidyM08Amt());
                            lai_t9 = lai_t9.add(item.getIntSubsidyM09Amt());

                            lai_t1dc = lai_t1dc.add(item.getIntSubsidyAdjustM01Amt());
                            lai_t2dc = lai_t2dc.add(item.getIntSubsidyAdjustM02Amt());
                            lai_t3dc = lai_t3dc.add(item.getIntSubsidyAdjustM03Amt());
                            lai_t4dc = lai_t4dc.add(item.getIntSubsidyAdjustM04Amt());
                            lai_t5dc = lai_t5dc.add(item.getIntSubsidyAdjustM05Amt());
                            lai_t6dc = lai_t6dc.add(item.getIntSubsidyAdjustM06Amt());
                            lai_t7dc = lai_t7dc.add(item.getIntSubsidyAdjustM07Amt());
                            lai_t8dc = lai_t8dc.add(item.getIntSubsidyAdjustM08Amt());
                            lai_t9dc = lai_t9dc.add(item.getIntSubsidyAdjustM09Amt());

                            lai_20 = lai_20.add(item.getCasaAmt());
                            lai_21 = lai_21.add(item.getCashAmt());
                            lstDulieuNt.add(row);
                        }
                    } else {
                        if (sNhadt.equals("0000") || sNhadt.equals(item.getInvestorCode())) {
                            if (item.getIntConfirmFlag().equals("1")) {
                                i++;
                                dn_tronhan = dn_tronhan + (long) item.getNormalAmt();
                                dn_quahan = dn_quahan + (long) item.getOverdueAmt();
                                dn_khoanh = dn_khoanh + (long) item.getFreezeAmt();

                                lai_t1 = lai_t1.add(item.getIntSubsidyM01Amt());
                                lai_t2 = lai_t2.add(item.getIntSubsidyM02Amt());
                                lai_t3 = lai_t3.add(item.getIntSubsidyM03Amt());
                                lai_t4 = lai_t4.add(item.getIntSubsidyM04Amt());
                                lai_t5 = lai_t5.add(item.getIntSubsidyM05Amt());
                                lai_t6 = lai_t6.add(item.getIntSubsidyM06Amt());
                                lai_t7 = lai_t7.add(item.getIntSubsidyM07Amt());
                                lai_t8 = lai_t8.add(item.getIntSubsidyM08Amt());
                                lai_t9 = lai_t9.add(item.getIntSubsidyM09Amt());

                                lai_t1dc = lai_t1dc.add(item.getIntSubsidyAdjustM01Amt());
                                lai_t2dc = lai_t2dc.add(item.getIntSubsidyAdjustM02Amt());
                                lai_t3dc = lai_t3dc.add(item.getIntSubsidyAdjustM03Amt());
                                lai_t4dc = lai_t4dc.add(item.getIntSubsidyAdjustM04Amt());
                                lai_t5dc = lai_t5dc.add(item.getIntSubsidyAdjustM05Amt());
                                lai_t6dc = lai_t6dc.add(item.getIntSubsidyAdjustM06Amt());
                                lai_t7dc = lai_t7dc.add(item.getIntSubsidyAdjustM07Amt());
                                lai_t8dc = lai_t8dc.add(item.getIntSubsidyAdjustM08Amt());
                                lai_t9dc = lai_t9dc.add(item.getIntSubsidyAdjustM09Amt());

                                lai_20 = lai_20.add(item.getCasaAmt());
                                lai_21 = lai_21.add(item.getCashAmt());
                                lstDulieuNt.add(row);
                            }
                        }
                    }

                } catch (Exception e) {
                    CoreLogger.error(this.getClass().getName() + " Exception -> HTLS2021: " + e.getMessage());
                    System.err.println(this.getClass().getName() + " Exception -> HTLS2021: " + e.getMessage());
                }
            }
//                lstDulieuNt.sort(Comparator.comparing(o -> o.getD1() + o.getD2() + o.getD3()));

            tong.setD1(df.format(dn_tronhan + dn_quahan + dn_khoanh));
            tong.setD2(df.format(dn_tronhan));
            tong.setD3(df.format(dn_quahan));
            tong.setD4(df.format(dn_khoanh));

            tong.setD5(df.format(lai_t1));
            tong.setD6(df.format(lai_t2));
            tong.setD7(df.format(lai_t3));
            tong.setD15(df.format(lai_t4));
            tong.setD16(df.format(lai_t5));
            tong.setD17(df.format(lai_t6));
            tong.setD18(df.format(lai_t7));
            tong.setD19(df.format(lai_t8));
            tong.setD27(df.format(lai_t9));

            tong.setD8(df.format(lai_t1dc));
            tong.setD9(df.format(lai_t2dc));
            tong.setD10(df.format(lai_t3dc));
            tong.setD20(df.format(lai_t4dc));
            tong.setD21(df.format(lai_t5dc));
            tong.setD22(df.format(lai_t6dc));
            tong.setD23(df.format(lai_t7dc));
            tong.setD24(df.format(lai_t8dc));
            tong.setD25(df.format(lai_t9dc));

            tong.setD11(df.format(lai_20.add(lai_21)));
            tong.setD12(df.format(lai_20));
            tong.setD13(df.format(lai_21));
            lstDulieuNt_tong.add(tong);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> HTLS2021: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> HTLS2021: " + e.getMessage());
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
                    if (lstDataLock.get(0).getStatus().equals("2")) {
                        addActionError("Đơn vị đã chốt số liệu. Bạn không thể điều chỉnh.");;
                        return ERROR;
                    } //Lưu phần xác nhận lãi giảm
                    else {
                        try {
                            setChotsl(lstDataLock.get(0).getStatus());
                        } catch (Exception e) {
                            setChotsl("0");
                        }
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
                                    tempadd.setIntSubsidyAdjustM04Amt(new BigInteger(tmp.getD55()));
                                    tempadd.setIntSubsidyAdjustM05Amt(new BigInteger(tmp.getD37()));
                                    tempadd.setIntSubsidyAdjustM06Amt(new BigInteger(tmp.getD38()));
                                    tempadd.setIntSubsidyAdjustM07Amt(new BigInteger(tmp.getD51()));
                                    tempadd.setIntSubsidyAdjustM08Amt(new BigInteger(tmp.getD60()));
                                    tempadd.setIntSubsidyAdjustM09Amt(new BigInteger(tmp.getD63()));
                                    if(chotsl.equals("1") || chotsl.equals("3")) 
                                        tempadd.setIntConfirmFlag("1") ;
                                    else
                                        tempadd.setIntConfirmFlag("0") ;
                                    tempadd.setRejectReason(tmp.getD47());
                                    lstUpdateDate.add(tempadd);
                                } else {
                                    tempadd.setMainPos(tmp.getMACN());
                                    tempadd.setPosCode(tmp.getMAPGD());
                                    tempadd.setLoanId(tmp.getD3());
                                    tempadd.setIntSubsidyAdjustM01Amt(new BigInteger(tmp.getD18()));
                                    tempadd.setIntSubsidyAdjustM02Amt(new BigInteger(tmp.getD19()));
                                    tempadd.setIntSubsidyAdjustM03Amt(new BigInteger(tmp.getD20()));
                                    tempadd.setIntSubsidyAdjustM04Amt(new BigInteger(tmp.getD55()));
                                    tempadd.setIntSubsidyAdjustM05Amt(new BigInteger(tmp.getD37()));
                                    tempadd.setIntSubsidyAdjustM06Amt(new BigInteger(tmp.getD38()));
                                    tempadd.setIntSubsidyAdjustM07Amt(new BigInteger(tmp.getD51()));
                                    tempadd.setIntSubsidyAdjustM08Amt(new BigInteger(tmp.getD60()));
                                    tempadd.setIntSubsidyAdjustM09Amt(new BigInteger(tmp.getD63()));

                                    tempadd.setM01Status("1");
                                    tempadd.setM02Status("1");
                                    tempadd.setM03Status("1");
                                    tempadd.setM04Status("1");
                                    tempadd.setM05Status("1");
                                    tempadd.setM06Status("1");
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
                                updateRow.setD10(df.format(item.getIntSubsidyAdjustM04Amt()));
                                updateRow.setD11(df.format(item.getIntSubsidyAdjustM05Amt()));
                                updateRow.setD12(df.format(item.getIntSubsidyAdjustM06Amt()));
                                updateRow.setD13(df.format(item.getIntSubsidyAdjustM07Amt()));
                                updateRow.setD14(df.format(item.getIntSubsidyAdjustM08Amt()));
                                updateRow.setD15(df.format(item.getIntSubsidyAdjustM09Amt()));
                                updateRow.setD5(item.getIntConfirmFlag().toString());
                                updateRow.setD6(item.getRejectReason());

                                lstLocalDataUpdate.add(updateRow);
                            }
                            if (!DaoNghiquyet11cp.newInstance().saveNQ11CP_001(UserName, pos_cd_username, strDate1, lstLocalDataUpdate, "1")) {
                                addActionError("Bạn chưa lưu số liệu tại đơn vị. Liên hệ với quản trị để khắc phục");
                                return ERROR;
                            }
                        } else {
                            addActionError("Lỗi cập nhật API trung ương. Liên hệ với quản trị để khắc phục");
                            return ERROR;
                        }
                    }
                }

            } else if (Grade.equals("2")) {
                chotsl = "";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
                String dateStr = sdf.format(date1);
                ArrayList<LockSendModel> lstData = service.getDataLockSendNQ11CP(pos_cd_username, "M", dateStr);
                for (LockSendModel item : lstData) {
                    if (item.getStatus().equals("0")) {
                        setChotsl("0");
                    }
                }

                ArrayList<UpdateLockModel> lstUpdateDateLock = new ArrayList<>();
                for (QT_DULIEU_NT tmp : lstDulieuNt) {
                    UpdateLockModel tempadd = new UpdateLockModel();
                    if (tmp.getD25() == null) {
                        tempadd.setPosCode(tmp.getMAPGD());
                        tempadd.setStatus(chotsl.equals("0") ? "0" : "2");
                        lstUpdateDateLock.add(tempadd);
                    } else {
                        tempadd.setPosCode(tmp.getMAPGD());
                        tempadd.setStatus(chotsl.equals("0") ? "1" : "3");
                        lstUpdateDateLock.add(tempadd);
                    }
                }
                int status = service.updateDataNQ11CP_ChotSL(pos_cd_username, strDate, UserName, lstUpdateDateLock);
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> NQ11CP: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> NQ11CP: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public static void main(String[] args) throws ParseException {
        DuLieuNTService service = new DuLieuNTService();
        String time = "2022-03-29T00:00:00";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date d2 = sdf.parse(time);

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date s1 = df.parse("03/28/2022");
        int wel = s1.compareTo(d2);

        if (d2.before(s1)) {
            String q = "";
        }

//	df1.parse("03/28/2022").after(sdf.parse())
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

        ArrayList<NQ11cpModel> lstData = service.getDataNQ11CP("000601", "20220228", "03",
                "060101", "0091543");
//        margerData(lstData,"0","0000");
    }
}
