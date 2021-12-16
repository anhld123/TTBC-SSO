/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.nhaptaycn.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
import java.math.BigInteger;
import vbsp.ims.nhaptaycn.action.*;
import vbsp.ims.nhaptaycn.action.*;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.bcqt.model.DULIEU_NT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.restapi.IntDeductionModel;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.InvestorModel;
import vbsp.ims.restapi.LockSendModel;
import vbsp.ims.restapi.UpdateLockModel;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.tdnn.DaoTdnnMain;
import vbsp.ims.util.DateUtil;
import vbsp.ims.xml.XmlKtgsSync;

/**
 *
 * @author Trung
 */
public class HTLS2021 extends ActionNhaptaycnMain
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
                setThangbc(sdf1.format(date1));
                posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
                pos_cd_username = posMainModel.getPosCd();
                service = new DuLieuNTService();
                //Kiểm tra xem đã chốt số liệu chưa
                String chotsl = "0";
                ArrayList<LockSendModel> lstDataLock = service.getDataLockSendS2021(pos_cd_username, "S", dateStr);
                //                Check xem khóa chưa
                if (lstDataLock.size() > 0) {
                    if (lstDataLock.get(0).getStatus().equals("1")) {
                        chotsl = "1";
                    }
                }

                ArrayList<IntDeductionModel> lstData = service.getDataHTLS2021(pos_cd_username, dateStr, hmParameter.get("chuongtrinh").toString(),
                        hmParameter.get("maxa").toString(), hmParameter.get("mato").toString());
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
//                DecimalFormat df1 = new DecimalFormat("#.#");
                //String formatted = df.format(2.00023);

                String sPhanloai = hmParameter.get("phanloai").toString();
                String sGiaingan = hmParameter.get("giaingan").toString();
                String sNhadt = hmParameter.get("nha_dt").toString();
                for (IntDeductionModel item : lstData) {

//                if (i>10)
//                {
//                    continue;
//                }
                    QT_DULIEU_NT row = new QT_DULIEU_NT();
                    row.setKHOA("ID_001");
                    row.setTHUTU(i);

                    row.setTT_HIENTHI(dateStr);
//                row.setMA(item.getCode());
//                row.setTEN(item.getName());
                    Date reportDate = DateUtil.toDate(item.getReportDate());
                    row.setNGAYBC(reportDate);
                    //row.setNAMBC(item.getReportYear());
                    row.setMAPGD(item.getPosCode());
//                row.setCO_TONGHOP(item.getPosFlag());
                    row.setMACN(item.getMainPos());
//                row.setNGUOI_NHAP(item.getMakerId());
                    //row.setNGAY_NHAP(item.getMakerDate());
//                Date makerDate = DateUtil.toDate( item.getMakerDate());
//                row.setNGAY_NHAP(makerDate);
//                row.setNGUOI_DUYET(item.getAuthoriseId());
                    //row.setNGAY_DUYET(item.getAuthoriseDate());
//                Date authoriseDate = DateUtil.toDate( item.getAuthoriseDate());
//                row.setNGAY_DUYET(authoriseDate);
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
//                    if (i == 107) {
//                        System.err.println("kkkk--" + i);
//                    }
                    row.setD17(df.format(item.getIntDeductionTotalAmt()));
                    BigInteger ad = item.getIntDeductionM10Amt();

                    //Die tư day dong 108
                    row.setD18(df.format(item.getIntDeductionM10Amt()));
                    row.setD19(df.format(item.getIntDeductionM11Amt()));
                    row.setD20(df.format(item.getIntDeductionM12Amt()));

                    row.setD21(df.format(item.getIntDeductionAdjustM10Amt()));
                    row.setD22(df.format(item.getIntDeductionAdjustM11Amt()));
                    row.setD23(df.format(item.getIntDeductionAdjustM12Amt()));

                    row.setD24(item.getPaymentFlag().equals("0") ? "RPA" : "HT phải trả");
                    row.setD25(item.getIntConfirmFlag());
                    row.setD26(item.getDeductionTranRef());
                    row.setD27(item.getDeductionTranDate());

                    row.setD28(df.format(item.getAccountingIntAmt()));
                    row.setD29(df.format(item.getRpaAmt()));
                    row.setD30(df.format(item.getCasaAmt()));
                    row.setD31(df.format(item.getCashAmt()));

//                lai_18 = item.getIntDeductionAdjustM10Amt();
                    row.setD32(item.getPosTranRef());
                    row.setD33(item.getM10Status());
                    row.setD34(item.getM11Status());
                    row.setD35(item.getM12Status());
                    row.setD36(item.getPaymentFlag());
//                row.setD37(item.getIntConfirmFlag());
//                row.setD38(item.getDeductionTranRef());
//                row.setD39(item.getDeductionTranDate());

                    row.setD43(df.format(item.getIntTotalM10Amt()));
                    row.setD44(df.format(item.getIntTotalM11Amt()));
                    row.setD45(df.format(item.getIntTotalM12Amt()));
                    row.setD46(item.getCommuneId());

                    row.setD50(item.getCustomerName());
//                    Lấy món vay xác nhận lãi
                    if (!chotsl.equals("1")) {
                        if ((sPhanloai.equals("-1") || sPhanloai.equals(item.getPaymentFlag()))
                                && (sNhadt.equals("0000") || sNhadt.equals(item.getInvestorCode()))
                                && (sGiaingan.equals("-1") || !item.getDisbursalDate().equals("19000101"))) {
                            i++;
                        dn_tronhan = dn_tronhan + (long) item.getNormalAmt();
                        dn_quahan = dn_quahan + (long) item.getOverdueAmt();
                        dn_khoanh = dn_khoanh + (long) item.getFreezeAmt();

                        lai_t10 = lai_t10.add(item.getIntDeductionM10Amt());
                        lai_t11 = lai_t11.add(item.getIntDeductionM11Amt());
                        lai_t12 = lai_t12.add(item.getIntDeductionM12Amt());

                        lai_t10dc = lai_t10dc.add(item.getIntDeductionAdjustM10Amt());
                        lai_t11dc = lai_t11dc.add(item.getIntDeductionAdjustM11Amt());
                        lai_t12dc = lai_t12dc.add(item.getIntDeductionAdjustM12Amt());

                        lai_20 = lai_20.add(item.getCasaAmt());
                        lai_21 = lai_21.add(item.getCashAmt());
                        lstDulieuNt.add(row);
                        }
                    } else {
                        if ((sPhanloai.equals("-1") || sPhanloai.equals(item.getPaymentFlag()))
                                && (sNhadt.equals("0000") || sNhadt.equals(item.getInvestorCode()))
                                && (sGiaingan.equals("-1") || !item.getDisbursalDate().equals("19000101"))) {
                            if (item.getPaymentFlag().equals("1") && item.getIntConfirmFlag().equals("1")) {
                                i++;
                                dn_tronhan = dn_tronhan + (long) item.getNormalAmt();
                                dn_quahan = dn_quahan + (long) item.getOverdueAmt();
                                dn_khoanh = dn_khoanh + (long) item.getFreezeAmt();

                                lai_t10 = lai_t10.add(item.getIntDeductionM10Amt());
                                lai_t11 = lai_t11.add(item.getIntDeductionM11Amt());
                                lai_t12 = lai_t12.add(item.getIntDeductionM12Amt());

                                lai_t10dc = lai_t10dc.add(item.getIntDeductionAdjustM10Amt());
                                lai_t11dc = lai_t11dc.add(item.getIntDeductionAdjustM11Amt());
                                lai_t12dc = lai_t12dc.add(item.getIntDeductionAdjustM12Amt());

                                lai_20 = lai_20.add(item.getCasaAmt());
                                lai_21 = lai_21.add(item.getCashAmt());
                                lstDulieuNt.add(row);
                            }
                        }
//                        i++;
//                        dn_tronhan = dn_tronhan + (long) item.getNormalAmt();
//                        dn_quahan = dn_quahan + (long) item.getOverdueAmt();
//                        dn_khoanh = dn_khoanh + (long) item.getFreezeAmt();
//
//                        lai_t10 = lai_t10.add(item.getIntDeductionM10Amt());
//                        lai_t11 = lai_t11.add(item.getIntDeductionM11Amt());
//                        lai_t12 = lai_t12.add(item.getIntDeductionM12Amt());
//
//                        lai_t10dc = lai_t10dc.add(item.getIntDeductionAdjustM10Amt());
//                        lai_t11dc = lai_t11dc.add(item.getIntDeductionAdjustM11Amt());
//                        lai_t12dc = lai_t12dc.add(item.getIntDeductionAdjustM12Amt());
//
//                        lai_20 = lai_20.add(item.getCasaAmt());
//                        lai_21 = lai_21.add(item.getCashAmt());    
//                        lstDulieuNt.add(row);
                    }

//                    if (!chotsl.equals("1") && hmParameter.get("phanloai").toString().equals("-1")) {
//                        i++;
//                        dn_tronhan = dn_tronhan + (long) item.getNormalAmt();
//                        dn_quahan = dn_quahan + (long) item.getOverdueAmt();
//                        dn_khoanh = dn_khoanh + (long) item.getFreezeAmt();
//
//                        lai_t10 = lai_t10.add(item.getIntDeductionM10Amt());
//                        lai_t11 = lai_t11.add(item.getIntDeductionM11Amt());
//                        lai_t12 = lai_t12.add(item.getIntDeductionM12Amt());
//
//                        lai_t10dc = lai_t10dc.add(item.getIntDeductionAdjustM10Amt());
//                        lai_t11dc = lai_t11dc.add(item.getIntDeductionAdjustM11Amt());
//                        lai_t12dc = lai_t12dc.add(item.getIntDeductionAdjustM12Amt());
//
//                        lai_20 = lai_20.add(item.getCasaAmt());
//                        lai_21 = lai_21.add(item.getCashAmt());
//                        lstDulieuNt.add(row);
//                    } else if (!chotsl.equals("1") && hmParameter.get("phanloai").toString().equals(item.getPaymentFlag())) {
//                        i++;
//                        dn_tronhan = dn_tronhan + (long) item.getNormalAmt();
//                        dn_quahan = dn_quahan + (long) item.getOverdueAmt();
//                        dn_khoanh = dn_khoanh + (long) item.getFreezeAmt();
//
//                        lai_t10 = lai_t10.add(item.getIntDeductionM10Amt());
//                        lai_t11 = lai_t11.add(item.getIntDeductionM11Amt());
//                        lai_t12 = lai_t12.add(item.getIntDeductionM12Amt());
//
//                        lai_t10dc = lai_t10dc.add(item.getIntDeductionAdjustM10Amt());
//                        lai_t11dc = lai_t11dc.add(item.getIntDeductionAdjustM11Amt());
//                        lai_t12dc = lai_t12dc.add(item.getIntDeductionAdjustM12Amt());
//
//                        lai_20 = lai_20.add(item.getCasaAmt());
//                        lai_21 = lai_21.add(item.getCashAmt());
//                        lstDulieuNt.add(row);
//                    } else if (chotsl.equals("1") && hmParameter.get("phanloai").toString().equals(item.getPaymentFlag()) && item.getIntConfirmFlag().equals("1")) {
//                        i++;
//                        dn_tronhan = dn_tronhan + (long) item.getNormalAmt();
//                        dn_quahan = dn_quahan + (long) item.getOverdueAmt();
//                        dn_khoanh = dn_khoanh + (long) item.getFreezeAmt();
//
//                        lai_t10 = lai_t10.add(item.getIntDeductionM10Amt());
//                        lai_t11 = lai_t11.add(item.getIntDeductionM11Amt());
//                        lai_t12 = lai_t12.add(item.getIntDeductionM12Amt());
//
//                        lai_t10dc = lai_t10dc.add(item.getIntDeductionAdjustM10Amt());
//                        lai_t11dc = lai_t11dc.add(item.getIntDeductionAdjustM11Amt());
//                        lai_t12dc = lai_t12dc.add(item.getIntDeductionAdjustM12Amt());
//
//                        lai_20 = lai_20.add(item.getCasaAmt());
//                        lai_21 = lai_21.add(item.getCashAmt());
//                        lstDulieuNt.add(row);
//                    } else if (chotsl.equals("1") && hmParameter.get("phanloai").toString().equals("-1")) {
//                        if (item.getPaymentFlag().equals("1") && item.getIntConfirmFlag().equals("1")) {
//                            i++;
//                            dn_tronhan = dn_tronhan + (long) item.getNormalAmt();
//                            dn_quahan = dn_quahan + (long) item.getOverdueAmt();
//                            dn_khoanh = dn_khoanh + (long) item.getFreezeAmt();
//
//                            lai_t10 = lai_t10.add(item.getIntDeductionM10Amt());
//                            lai_t11 = lai_t11.add(item.getIntDeductionM11Amt());
//                            lai_t12 = lai_t12.add(item.getIntDeductionM12Amt());
//
//                            lai_t10dc = lai_t10dc.add(item.getIntDeductionAdjustM10Amt());
//                            lai_t11dc = lai_t11dc.add(item.getIntDeductionAdjustM11Amt());
//                            lai_t12dc = lai_t12dc.add(item.getIntDeductionAdjustM12Amt());
//
//                            lai_20 = lai_20.add(item.getCasaAmt());
//                            lai_21 = lai_21.add(item.getCashAmt());
//                            lstDulieuNt.add(row);
//                        }
//                    }
                }
                tong.setD1(df.format(dn_tronhan + dn_quahan + dn_khoanh));
                tong.setD2(df.format(dn_tronhan));
                tong.setD3(df.format(dn_quahan));
                tong.setD4(df.format(dn_khoanh));

                tong.setD5(df.format(lai_t10));
                tong.setD6(df.format(lai_t11));
                tong.setD7(df.format(lai_t12));

                tong.setD8(df.format(lai_t10dc));
                tong.setD9(df.format(lai_t11dc));
                tong.setD10(df.format(lai_t12dc));

//                        tong.setD11(df.format(lai_20 + lai_21));
                tong.setD12(df.format(lai_20));
                tong.setD13(df.format(lai_21));
                lstDulieuNt_tong.add(tong);
                if (chotsl.equals("1")) {
                    return "xacnhanht";
                }
            } else if (Grade.equals("2")) {

//            System.err.println("QD23_001");
                if (!getParaSession()) {
                    return ERROR;
                }
                HashMap hmParameter = getParameter();
//            Connection conn = new DaoConnect().getConnect();
//            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
                Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(hmParameter.get("ngay_bc").toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String dateStr = sdf.format(date1);

                posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
                pos_cd_username = posMainModel.getPosCd();
                service = new DuLieuNTService();
                ArrayList<LockSendModel> lstData = service.getDataLockSendS2021(pos_cd_username, "M", dateStr);
                int i = 0;

                DecimalFormat df = new DecimalFormat("#.##");
                //String formatted = df.format(2.00023);

                for (LockSendModel item : lstData) {
                    i++;
//                if (i>10)
//                {
//                    continue;
//                }
                    QT_DULIEU_NT row = new QT_DULIEU_NT();
                    row.setKHOA("ID_002");
                    row.setTHUTU(i);

//                row.setTT_HIENTHI(item.getOrderDescription());
//                row.setMA(item.getCode());
//                row.setTEN(item.getName());
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
            CoreLogger.error(this.getClass().getName() + " Exception -> HTLS2021: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> HTLS2021: " + e.getMessage());
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

            DaoNhaptaycnMain daoMain = DaoNhaptaycnMain.newInstance();
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
                ArrayList<LockSendModel> lstDataLock = service.getDataLockSendS2021(pos_cd_username, "S", strDate);
                ArrayList<IntDeductionModel> lstUpdateDate = new ArrayList<>();
                if (lstDataLock.size() > 0) {
                    //Lưu phần phân loại hạch toán
                    if (lstDataLock.get(0).getStatus().equals("1")) {
                        addActionError("Chi nhánh dã chốt số liệu. Bạn không thể điều chỉnh.");;
                        return ERROR;
                    } //Lưu phần xác nhận lãi giảm
                    else {
//                         DecimalFormat df = new DecimalFormat("#.##");
                        for (QT_DULIEU_NT tmp : lstDulieuNt) {
                            if (tmp.getD33() != null) {

                                IntDeductionModel tempadd = new IntDeductionModel();
                                if (tmp.getD25() == null) {
                                    tempadd.setMainPos(tmp.getMACN());
                                    tempadd.setPosCode(tmp.getMAPGD());
                                    tempadd.setLoanId(tmp.getD3());
                                    if (strDate.equals("20211130")) {
                                        tempadd.setIntDeductionAdjustM10Amt(new BigInteger(tmp.getD18()));
                                        tempadd.setIntDeductionAdjustM11Amt(new BigInteger(tmp.getD19()));
                                    } else {
                                        tempadd.setIntDeductionAdjustM12Amt(new BigInteger(tmp.getD20()));
                                    }

                                    tempadd.setIntConfirmFlag("0");
                                    lstUpdateDate.add(tempadd);
                                } else {
                                    tempadd.setMainPos(tmp.getMACN());
                                    tempadd.setPosCode(tmp.getMAPGD());
                                    tempadd.setLoanId(tmp.getD3());
                                    if (strDate.equals("20211130")) {
                                        tempadd.setIntDeductionAdjustM10Amt(new BigInteger(tmp.getD18()));
                                        tempadd.setIntDeductionAdjustM11Amt(new BigInteger(tmp.getD19()));
                                    } else {
                                        tempadd.setIntDeductionAdjustM12Amt(new BigInteger(tmp.getD20()));
                                    }
                                    tempadd.setM10Status(strDate.equals("20211130") ? "1" : "");
                                    tempadd.setM11Status(strDate.equals("20211130") ? "1" : "");
                                    tempadd.setM12Status(strDate.equals("20211231") ? "1" : "");
                                    tempadd.setIntConfirmFlag("1");
                                    lstUpdateDate.add(tempadd);
                                }
                            }
                        }
                        int status = service.updateData2021HTLS(pos_cd_username, strDate, UserName, lstUpdateDate);
                        if (status == 200) {
                            ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
                            DecimalFormat df = new DecimalFormat("#.##");
                            for (IntDeductionModel item : lstUpdateDate) {
                                QT_DULIEU_NT updateRow = new QT_DULIEU_NT();
                                updateRow.setD1(item.getLoanId());
                                if (strDate.equals("20211130")) {
                                    updateRow.setD2(df.format(item.getIntDeductionAdjustM10Amt()));
                                    updateRow.setD3(df.format(item.getIntDeductionAdjustM11Amt()));
                                } else {
                                    updateRow.setD4(df.format(item.getIntDeductionAdjustM12Amt()));
                                }

                                updateRow.setD5(item.getIntConfirmFlag().toString());

                                lstLocalDataUpdate.add(updateRow);
                            }
                            daoMain.saveGiamLai1990(UserName, pos_cd_username, strDate1, lstLocalDataUpdate, "1");
                        }
                    }
                }

            } else if (Grade.equals("2")) {
                ArrayList<UpdateLockModel> lstUpdateDateLock = new ArrayList<>();
                for (QT_DULIEU_NT tmp : lstDulieuNt) {
                    UpdateLockModel tempadd = new UpdateLockModel();
                    if (tmp.getD25() == null) {
//                        tempadd.setMainPos(tmp.getMACN());
                        tempadd.setPosCode(tmp.getMAPGD());
//                        tempadd.setReportDate(strDate);
//                        tempadd.setPosFlag("S");
                        tempadd.setStatus("0");
                        lstUpdateDateLock.add(tempadd);
                    } else {
//                        tempadd.setMainPos(tmp.getMACN());
                        tempadd.setPosCode(tmp.getMAPGD());
//                        tempadd.setReportDate(strDate);
//                        tempadd.setPosFlag("S");
                        tempadd.setStatus("1");
                        lstUpdateDateLock.add(tempadd);
                    }
                }
                int status = service.updateData2021HTLS_ChotSL(pos_cd_username, strDate, UserName, lstUpdateDateLock);
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> HTLS2021: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> HTLS2021: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String save_htl() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }

            DaoNhaptaycnMain daoMain = DaoNhaptaycnMain.newInstance();
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
                ArrayList<LockSendModel> lstDataLock = service.getDataLockSendS2021(pos_cd_username, "S", strDate);
                ArrayList<IntDeductionModel> lstUpdateDate = new ArrayList<>();
                if (lstDataLock.size() > 0) {
                    //Lưu phần phân loại hạch toán
                    if (lstDataLock.get(0).getStatus().equals("1")) {
                        for (QT_DULIEU_NT tmp : lstDulieuNt) {
                            if (tmp.getD33() != null) {
                                IntDeductionModel tempadd = new IntDeductionModel();
                                tempadd.setMainPos(tmp.getMACN());
                                tempadd.setPosCode(tmp.getMAPGD());
                                tempadd.setLoanId(tmp.getD3());
                                tempadd.setCasaAmt(new BigInteger(tmp.getD30()));
                                tempadd.setCashAmt(new BigInteger(tmp.getD31()));
                                tempadd.setPosTranRef(tmp.getD32());
                                lstUpdateDate.add(tempadd);
                            }
                        }
                        int status = service.updateData2021HTLS(pos_cd_username, strDate, UserName, lstUpdateDate);
                        if (status == 200) {
                            ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
                            DecimalFormat df = new DecimalFormat("#.##");
                            for (IntDeductionModel item : lstUpdateDate) {
                                QT_DULIEU_NT updateRow = new QT_DULIEU_NT();
                                updateRow.setD1(item.getLoanId());
                                updateRow.setD2(df.format(item.getCasaAmt()));
                                updateRow.setD3(df.format(item.getCashAmt()));
//                                updateRow.setD4(df.format(item.getIntDeductionAdjustM12Amt()));
                                updateRow.setD4(item.getPosTranRef());

                                lstLocalDataUpdate.add(updateRow);
                            }
                            daoMain.saveGiamLai1990(UserName, pos_cd_username, strDate1, lstLocalDataUpdate, "2");
                        }
                    } //Lưu phần xác nhận lãi giảm                    
                }

            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> HTLS2021: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> HTLS2021: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }
}
