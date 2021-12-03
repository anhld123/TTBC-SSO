/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.nhaptaycn.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
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
                String dateStr = sdf.format(date1);

                posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
                pos_cd_username = posMainModel.getPosCd();
                service = new DuLieuNTService();
                ArrayList<IntDeductionModel> lstData = service.getDataHTLS2021(pos_cd_username, "20211031", hmParameter.get("chuongtrinh").toString(),
                        hmParameter.get("maxa").toString(), hmParameter.get("mato").toString());
                int i = 1;

                QT_DULIEU_NT tong = new QT_DULIEU_NT();
                float dn_tronhan = 0;
                float dn_quahan = 0;
                float dn_khoanh = 0;
                float lai_t10 = 0;
                float lai_t11 = 0;
                float lai_t12 = 0;

                float lai_t10dc = 0;
                float lai_t11dc = 0;
                float lai_t12dc = 0;

                float lai_18 = 0;
                float lai_20 = 0;
                float lai_21 = 0;

                DecimalFormat df = new DecimalFormat("#.##");
                //String formatted = df.format(2.00023);

                for (IntDeductionModel item : lstData) {
                    
//                if (i>10)
//                {
//                    continue;
//                }
                    QT_DULIEU_NT row = new QT_DULIEU_NT();
                    row.setKHOA("ID_001");
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
                    dn_tronhan = dn_tronhan + item.getNormalAmt();
                    dn_quahan = dn_quahan + item.getOverdueAmt();
                    dn_khoanh = dn_khoanh + item.getFreezeAmt();

                    row.setD8(df.format(item.getInterestRate()));
                    row.setD9(item.getLoanProgram());
                    row.setD10(item.getSpecificProductCode());
                    row.setD11(item.getDecisionCode());
                    row.setD12(item.getLoanStatus());
                    row.setD13(item.getCapitalSourceCode());
                    row.setD14(item.getInvestorCode());
                    row.setD15(item.getCasaAccount());
                    row.setD16(df.format(item.getIntTotalAmt()));

                    row.setD17(df.format(item.getIntDeductionTotalAmt()));

                    row.setD18(df.format(item.getIntDeductionM10Amt()));
                    row.setD19(df.format(item.getIntDeductionM11Amt()));
                    row.setD20(df.format(item.getIntDeductionM12Amt()));
                    lai_t10 = lai_t10 + item.getIntDeductionM10Amt();
                    lai_t11 = lai_t11 + item.getIntDeductionM11Amt();
                    lai_t12 = lai_t12 + item.getIntDeductionM12Amt();

                    row.setD21(df.format(item.getIntDeductionAdjustM10Amt()));
                    row.setD22(df.format(item.getIntDeductionAdjustM11Amt()));
                    row.setD23(df.format(item.getIntDeductionAdjustM12Amt()));
                    lai_t10dc = lai_t10dc + item.getIntDeductionAdjustM10Amt();
                    lai_t11dc = lai_t11dc + item.getIntDeductionAdjustM11Amt();
                    lai_t12dc = lai_t12dc + item.getIntDeductionAdjustM12Amt();

                    row.setD24(item.getPaymentFlag().equals("0") ? "RPA" : "HT phải trả");
                    row.setD25(item.getIntConfirmFlag());
                    row.setD26(item.getDeductionTranRef());
                    row.setD27(item.getDeductionTranDate());

                    row.setD28(df.format(item.getAccountingIntAmt()));
                    row.setD29(df.format(item.getRpaAmt()));
                    row.setD30(df.format(item.getCasaAmt()));
                    row.setD31(df.format(item.getCashAmt()));

//                lai_18 = item.getIntDeductionAdjustM10Amt();
                    lai_20 = lai_20 + item.getCasaAmt();
                    lai_21 = lai_21 + item.getCashAmt();

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
                    String lll= hmParameter.get("phanloai").toString();
                    if(hmParameter.get("phanloai").toString().equals("-1"))
                    {
                        i++;
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

                        tong.setD11(df.format(lai_20 + lai_21));
                        tong.setD12(df.format(lai_20));
                        tong.setD13(df.format(lai_21));
                        lstDulieuNt.add(row);
                    }
                    else if (hmParameter.get("phanloai").toString().equals(item.getPaymentFlag()))
                    {
                        i++;
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

                        tong.setD11(df.format(lai_20 + lai_21));
                        tong.setD12(df.format(lai_20));
                        tong.setD13(df.format(lai_21));
                        lstDulieuNt.add(row);
                    }
                    

                }

                

                lstDulieuNt_tong.add(tong);
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
                ArrayList<LockSendModel> lstData = service.getDataLockSendS2021(pos_cd_username,"M", dateStr);
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
                    row.setD1(df.format(item.getLoanTotal()));
                    row.setD2(df.format(item.getPrinTotal()));
                    row.setD3(df.format(item.getIntTotal()));
                    row.setD4(df.format(item.getIntDeductionTotal()));
                    row.setD5(df.format(item.getDeductionLoanTotal()));
                    row.setD6(df.format(item.getNoDeductionLoanTotal()));
                    row.setD7(df.format(item.getNoDeductionIntTotal()));
                    row.setD10(item.getStatus());
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

//            DaoNhaptaycnMain daoMain = DaoNhaptaycnMain.newInstance();
            HashMap hmParameter = getParameter();
            String sngaybc = hmParameter.get("ngay_bc").toString();
            Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(sngaybc);

//            Date date = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String strDate = dateFormat.format(date1);

            service = new DuLieuNTService();
             ArrayList<LockSendModel> lstDataLock = service.getDataLockSendS2021(pos_cd_username,"S", strDate);
                if(lstDataLock.size()>0)
                {
                    if (lstDataLock.get(0).getStatus().equals("1")){
                        addActionError("Chi nhánh đã chốt số liệu. Bạn không thể điều chỉnh.");;
                        return ERROR;
                    }
                }
            
            ArrayList<IntDeductionModel> lstUpdateDate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                IntDeductionModel tempadd = new IntDeductionModel();
                if (tmp.getD25() == null) {
                    tempadd.setMainPos(tmp.getMACN());
                    tempadd.setPosCode(tmp.getMAPGD());
                    tempadd.setLoanId(tmp.getD3());
                    tempadd.setIntDeductionAdjustM10Amt(Float.parseFloat(tmp.getD18()));
                    tempadd.setIntDeductionAdjustM11Amt(Float.parseFloat(tmp.getD19()));
                    tempadd.setIntDeductionAdjustM12Amt(Float.parseFloat(tmp.getD20()));
                    tempadd.setIntConfirmFlag("0");
                    lstUpdateDate.add(tempadd);
                } else {
                    tempadd.setMainPos(tmp.getMACN());
                    tempadd.setPosCode(tmp.getMAPGD());
                    tempadd.setLoanId(tmp.getD3());
                    tempadd.setIntDeductionAdjustM10Amt(Float.parseFloat(tmp.getD18()));
                    tempadd.setIntDeductionAdjustM11Amt(Float.parseFloat(tmp.getD19()));
                    tempadd.setIntDeductionAdjustM12Amt(Float.parseFloat(tmp.getD20()));
                    tempadd.setM10Status(strDate.equals("20211130") ? "1" : "");
                    tempadd.setM11Status(strDate.equals("20211130") ? "1" : "");
                    tempadd.setM12Status(strDate.equals("20211231") ? "1" : "");
                    tempadd.setIntConfirmFlag("1");
                    lstUpdateDate.add(tempadd);
                }
            }
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            int status = service.updateData2021HTLS(pos_cd_username, "20211031", UserName, lstUpdateDate);

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SMS_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SMS_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

}
