/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.nhaptaycn.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import java.math.BigInteger;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.GenericResult;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.nghiquyet11cp.DaoNghiquyet11cp;
import vbsp.ims.restapi.IntDeductionModel;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.ListPosCode;
import vbsp.ims.restapi.LockSendModel;
import vbsp.ims.restapi.UpdateLockModel;
import vbsp.ims.util.DateUtil;

/**
 *
 * @author Trung
 */
public class HTLS2024 extends ActionNhaptaycnMain
        implements NhaptaycnFunction {

    DuLieuNTService service;
    private List<ListPosCode> lstPGD_API;
    protected String main_pos_username;
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMain_pos_username() {
        return main_pos_username;
    }

    public void setMain_pos_username(String main_pos_username) {
        this.main_pos_username = main_pos_username;
    }

    public List<ListPosCode> getLstPGD_API() {
        return lstPGD_API;
    }

    public void setLstPGD_API(List<ListPosCode> lstPGD_API) {
        this.lstPGD_API = lstPGD_API;
    }

    @Override
    public String load() {
        try {
            if (Grade.equals("1")) {
                if (!getParaSession()) {
                    return ERROR;
                }
                HashMap hmParameter = getParameter();
//              Connection conn = new DaoConnect().getConnect();
//              DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
                Date _ngayBc = new SimpleDateFormat("dd-MMM-yyyy").parse(hmParameter.get("ngay_bc").toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
                String dateStr = sdf.format(_ngayBc);
                setThangbc(sdf1.format(_ngayBc));
                posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
                pos_cd_username = posMainModel.getPosCd();
                service = new DuLieuNTService();
                //Kiểm tra xem đã chốt số liệu chưa
                String chotsl = "1";
                ArrayList<LockSendModel> lstDataLock = service.getDataLockSendS2021(pos_cd_username, "S", dateStr);
                //Check xem khóa chưa
                if (!lstDataLock.isEmpty()) {
                    if (lstDataLock.get(0).getStatus().equals("1")) {
                        chotsl = "1";
                    }
                }

                String _chuongTrinh = hmParameter.get("chuongtrinh").toString();
                String _maXa = hmParameter.get("maxa").toString();
                String _maTo = hmParameter.get("mato").toString();

                ArrayList<IntDeductionModel> lstData = service.getDataHTLS2021(pos_cd_username, dateStr, _chuongTrinh, _maXa, _maTo);
                int i = 1;

                QT_DULIEU_NT tong = new QT_DULIEU_NT();
                double dn_tronhan = 0;
                double dn_quahan = 0;
                double dn_khoanh = 0;

                BigInteger lai_t09 = new BigInteger("0");
                BigInteger lai_t10 = new BigInteger("0");
                BigInteger lai_t11 = new BigInteger("0");
                BigInteger lai_t12 = new BigInteger("0");

                BigInteger lai_t09dc = new BigInteger("0");
                BigInteger lai_t10dc = new BigInteger("0");
                BigInteger lai_t11dc = new BigInteger("0");
                BigInteger lai_t12dc = new BigInteger("0");

                //BigInteger lai_18 = new BigInteger("0");
                BigInteger lai_20 = new BigInteger("0");
                BigInteger lai_21 = new BigInteger("0");

                DecimalFormat df = new DecimalFormat("#.##");
//                DecimalFormat df1 = new DecimalFormat("#.#");
                //String formatted = df.format(2.00023);

                String sPhanloai = hmParameter.get("phanloai").toString();
                String sGiaingan = hmParameter.get("giaingan").toString();
                // String sNhadt = hmParameter.get("nha_dt").toString();
                for (IntDeductionModel item : lstData) {
                    try {
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
//                 row.setNGUOI_NHAP(item.getGroupId());
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

                        row.setD17(df.format(item.getIntDeductionTotalAmt()));
                        //BigInteger ad = item.getIntDeductionM10Amt();

                        row.setD47(df.format(item.getIntDeductionM09Amt()));
                        row.setD18(df.format(item.getIntDeductionM10Amt()));
                        row.setD19(df.format(item.getIntDeductionM11Amt()));
                        row.setD20(df.format(item.getIntDeductionM12Amt()));

                        if (item.getLoanStatus().equals("C") && dateStr.equals("20211130") && item.getCasaAccount() == null
                                && item.getIntDeductionM10Amt().add(item.getIntDeductionM11Amt()).compareTo(new BigInteger("5000")) < 0) {
                            row.setMA("1");
                        } else if (item.getLoanStatus().equals("C") && dateStr.equals("20211231") && item.getCasaAccount() == null
                                && item.getIntDeductionM12Amt().compareTo(new BigInteger("5000")) < 0) {
                            row.setMA("1");
                        } else {
                            row.setMA("0");
                        }

                        row.setD48(df.format(item.getIntDeductionAdjustM09Amt()));
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
                        row.setD49(item.getM09Status());
                        row.setD33(item.getM10Status());
                        row.setD34(item.getM11Status());
                        row.setD35(item.getM12Status());
                        row.setD36(item.getPaymentFlag());
//                row.setD37(item.getIntConfirmFlag());
//                row.setD38(item.getDeductionTranRef());
//                row.setD39(item.getDeductionTranDate());

                        row.setD50(df.format(item.getIntTotalM09Amt()));
                        row.setD43(df.format(item.getIntTotalM10Amt()));
                        row.setD44(df.format(item.getIntTotalM11Amt()));
                        row.setD45(df.format(item.getIntTotalM12Amt()));
                        row.setD46(item.getCommuneId());

                        row.setD51(item.getCustomerName());
//                    Lấy món vay xác nhận lãi
                        if (!chotsl.equals("1")) {
                            if ((sPhanloai.equals("-1") || sPhanloai.equals(item.getPaymentFlag()))
                                    //&& (sNhadt.equals("0000") || sNhadt.equals(item.getInvestorCode()))
                                    && (sGiaingan.equals("-1") || item.getDisbursalDate() != null)) {
                                i++;
                                dn_tronhan = dn_tronhan + (long) item.getNormalAmt();
                                dn_quahan = dn_quahan + (long) item.getOverdueAmt();
                                dn_khoanh = dn_khoanh + (long) item.getFreezeAmt();

                                lai_t09 = lai_t09.add(item.getIntDeductionM09Amt());
                                lai_t10 = lai_t10.add(item.getIntDeductionM10Amt());
                                lai_t11 = lai_t11.add(item.getIntDeductionM11Amt());
                                lai_t12 = lai_t12.add(item.getIntDeductionM12Amt());

                                lai_t09dc = lai_t10dc.add(item.getIntDeductionAdjustM09Amt());
                                lai_t10dc = lai_t10dc.add(item.getIntDeductionAdjustM10Amt());
                                lai_t11dc = lai_t11dc.add(item.getIntDeductionAdjustM11Amt());
                                lai_t12dc = lai_t12dc.add(item.getIntDeductionAdjustM12Amt());

                                lai_20 = lai_20.add(item.getCasaAmt());
                                lai_21 = lai_21.add(item.getCashAmt());
                                lstDulieuNt.add(row);
                            }
                        } else {
                            if ((sPhanloai.equals("-1") || sPhanloai.equals(item.getPaymentFlag()))
                                    //&& (sNhadt.equals("0000") || sNhadt.equals(item.getInvestorCode()))
                                    && (sGiaingan.equals("-1") || item.getDisbursalDate() != null)) {
                                if (item.getPaymentFlag().equals("1") && item.getIntConfirmFlag().equals("1")) {
                                    i++;
                                    dn_tronhan = dn_tronhan + (long) item.getNormalAmt();
                                    dn_quahan = dn_quahan + (long) item.getOverdueAmt();
                                    dn_khoanh = dn_khoanh + (long) item.getFreezeAmt();

                                    lai_t09 = lai_t09.add(item.getIntDeductionM09Amt());
                                    lai_t10 = lai_t10.add(item.getIntDeductionM10Amt());
                                    lai_t11 = lai_t11.add(item.getIntDeductionM11Amt());
                                    lai_t12 = lai_t12.add(item.getIntDeductionM12Amt());

                                    lai_t09dc = lai_t10dc.add(item.getIntDeductionAdjustM09Amt());
                                    lai_t10dc = lai_t10dc.add(item.getIntDeductionAdjustM10Amt());
                                    lai_t11dc = lai_t11dc.add(item.getIntDeductionAdjustM11Amt());
                                    lai_t12dc = lai_t12dc.add(item.getIntDeductionAdjustM12Amt());

                                    lai_20 = lai_20.add(item.getCasaAmt());
                                    lai_21 = lai_21.add(item.getCashAmt());
                                    lstDulieuNt.add(row);
                                }
                            }
                        }

                    } catch (Exception e) {
                        CoreLogger.error(this.getClass().getName() + " Exception -> HTLS2024: " + e.getMessage());
                        System.err.println(this.getClass().getName() + " Exception -> HTLS2024: " + e.getMessage());
                    }
                }
//                lstDulieuNt.sort(Comparator.comparing(o -> o.getD1() + o.getD2() + o.getD3()));

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

//                tong.setD11(df.format(lai_20 + lai_21));
                tong.setD12(df.format(lai_20));
                tong.setD13(df.format(lai_21));

                tong.setD14(df.format(lai_t09));
                tong.setD15(df.format(lai_t09dc));

                lstDulieuNt_tong.add(tong);

                if (chotsl.equals("1")) {
                    return "xacnhanht";
                }
            } else if (Grade.equals("2")) {

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
                main_pos_username = posMainModel.getMainPosCd();
                service = new DuLieuNTService();
                lstPGD_API = service.getListPgd(main_pos_username, "");

                if (lstPGD_API == null || lstPGD_API.isEmpty()) {
                    addActionError("Lỗi khi gọi API!");
                    return ERROR;
                }

                for (ListPosCode pgdItem : lstPGD_API) {
                    QT_DULIEU_NT row = new QT_DULIEU_NT();
                    row.setMAPGD(pgdItem.getPosCode());
                    row.setMACN(pgdItem.getMainPos());
                    row.setTEN(pgdItem.getPosName());
                    row.setD1("0");
                    row.setD2("0");
                    row.setD3("0");
                    row.setD4("0");
                    row.setD5("0");
                    row.setD6("0");
                    row.setD7("0");
                    row.setD25("0");
                    try {
                        ArrayList<LockSendModel> lstData_tmp = service.getDataLockManual("QD1990", pgdItem.getPosCode(), "S", dateStr);
                        if (lstData_tmp.isEmpty() || lstData_tmp == null) {
                            service.updateChotSL("QD1990", pgdItem.getPosCode(), "S", dateStr, "0", UserName, null);
                        }
                        ArrayList<LockSendModel> lstData = service.getDataLockSendS2021(pgdItem.getPosCode(), "S", dateStr);

                        if (!lstData.isEmpty()) {
                            int i = 0;
                            DecimalFormat df = new DecimalFormat("#.##");

                            for (LockSendModel dataItem : lstData) {
                                i++;
                                QT_DULIEU_NT dataRow = new QT_DULIEU_NT();
                                dataRow.setKHOA("ID_002");
                                dataRow.setTHUTU(i);
                                Date reportDate = DateUtil.toDate(dataItem.getReportDate());
                                dataRow.setNGAYBC(reportDate);
                                dataRow.setMAPGD(pgdItem.getPosCode());
                                dataRow.setMACN(pgdItem.getMainPos());
                                dataRow.setTEN(pgdItem.getPosName());
                                dataRow.setD1(df.format(dataItem.getLoanTotal()));
                                dataRow.setD2(df.format(dataItem.getPrinTotal()));
                                dataRow.setD3(df.format(dataItem.getIntTotal()));
                                dataRow.setD4(df.format(dataItem.getDeductionIntTotal()));
                                dataRow.setD5(df.format(dataItem.getDeductionLoanTotal()));
                                dataRow.setD6(df.format(dataItem.getNoDeductionLoanTotal()));
                                dataRow.setD7(df.format(dataItem.getNoDeductionIntTotal()));
                                dataRow.setD25(dataItem.getStatus());

                                lstDulieuNt.add(dataRow);
                            }
                        } else {
                            lstDulieuNt.add(row);
                        }
                    } catch (Exception e) {
                        System.err.println("Lỗi khi gọi API chốt dữ liệu: " + e.getMessage());
                    }
                }

                return "cap2_chot";
            } else if (Grade.equals("3")) {
                try {
                    if (!getParaSession()) {
                        return ERROR;
                    }
                    HashMap hmParameter = getParameter();
                    String sngaybc = hmParameter.get("ngay_bc").toString();
                    String smacn = hmParameter.get("lstCN").toString();
                    Connection conn = new DaoConnect().getConnect();
                    DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
                    ActionContext.getContext().getSession().put("sUserName", UserName);
                    ActionContext.getContext().getSession().put("skhoa", "QD1990");
                    if (smacn.equals("000000")) {
                        addActionError("Vui lòng chọn mã chi nhánh!");
                        return ERROR;
                    }
//            System.out.println("txtGetData= "+ txtGetData);
                    lstDulieuNt = daoMain.getData_THTK_c3(conn, sngaybc, "QD1990", smacn, "S");
//            System.out.println(sngaybc + " " + smacn +" " +Pos_Flag);
                    if (conn != null) {
                        conn.close();
                    }

                } catch (Exception e) {
                    CoreLogger.error(this.getClass().getName() + " Exception -> gdx: " + e.getMessage());
                    System.err.println(this.getClass().getName() + " Exception -> gdx: " + e.getMessage());
                }
                return "cap3_chot";
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> HTLS2024: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> HTLS2024: " + e.getMessage());
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
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
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
                if (lstDataLock.isEmpty() || lstDataLock.get(0).getStatus().equals("0")) {
                    for (QT_DULIEU_NT tmp : lstDulieuNt) {
                        if (tmp.getD33() != null) {
                            IntDeductionModel tempadd = new IntDeductionModel();
                            if (tmp.getD25() == null) {
                                tempadd.setMainPos(tmp.getMACN());
                                tempadd.setPosCode(tmp.getMAPGD());
                                tempadd.setLoanId(tmp.getD3());

                                tempadd.setIntDeductionAdjustM09Amt(new BigInteger(tmp.getD47()));
                                tempadd.setIntDeductionAdjustM10Amt(new BigInteger(tmp.getD18()));
                                tempadd.setIntDeductionAdjustM11Amt(new BigInteger(tmp.getD19()));
                                tempadd.setIntDeductionAdjustM12Amt(new BigInteger(tmp.getD20()));

                                tempadd.setIntConfirmFlag("0");

                                lstUpdateDate.add(tempadd);
                            } else {
                                tempadd.setMainPos(tmp.getMACN());
                                tempadd.setPosCode(tmp.getMAPGD());
                                tempadd.setLoanId(tmp.getD3());

                                tempadd.setIntDeductionAdjustM09Amt(new BigInteger(tmp.getD47()));
                                tempadd.setIntDeductionAdjustM10Amt(new BigInteger(tmp.getD18()));
                                tempadd.setIntDeductionAdjustM11Amt(new BigInteger(tmp.getD19()));
                                tempadd.setIntDeductionAdjustM12Amt(new BigInteger(tmp.getD20()));

                                tempadd.setM09Status("1");
                                tempadd.setM10Status("1");
                                tempadd.setM11Status("1");
                                tempadd.setM12Status("1");
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
                            // Bo sung cho thang 9 tai local
                            updateRow.setD6(df.format(item.getIntDeductionAdjustM09Amt()));

                            updateRow.setD2(df.format(item.getIntDeductionAdjustM10Amt()));
                            updateRow.setD3(df.format(item.getIntDeductionAdjustM11Amt()));
                            updateRow.setD4(df.format(item.getIntDeductionAdjustM12Amt()));
                            updateRow.setD5(item.getIntConfirmFlag());

                            lstLocalDataUpdate.add(updateRow);
                        }
                        daoMain.saveGiamLai2024(UserName, pos_cd_username, strDate1, lstLocalDataUpdate, "1");
                    }
                    addActionMessage("Bạn đã lưu dữ liệu thành công");
                } else {
                    addActionError("Chi nhánh dã chốt số liệu. Bạn không thể điều chỉnh.");
                    return ERROR;
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
                        tempadd.setStatus("2");
                        lstUpdateDateLock.add(tempadd);
                    }
                }
                int status = service.updateData2021HTLS_ChotSL(pos_cd_username, strDate, UserName, lstUpdateDateLock);
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> HTLS2024: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> HTLS2024: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }

        return SUCCESS;
    }

    public String save_htl() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
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
                if (!lstDataLock.isEmpty() && lstDataLock.get(0).getStatus().equals("1")) {
                    //Lưu phần phân loại hạch toán

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
                    int status = service.updateData2021HTLS_HT(pos_cd_username, strDate, UserName, lstUpdateDate);
                    if (status == 200) {
                        ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
                        DecimalFormat df = new DecimalFormat("#.##");
                        for (IntDeductionModel item : lstUpdateDate) {
                            QT_DULIEU_NT updateRow = new QT_DULIEU_NT();
                            updateRow.setD1(item.getLoanId());
                            updateRow.setD2(df.format(item.getCasaAmt()));
                            updateRow.setD3(df.format(item.getCashAmt()));
                            updateRow.setD4(item.getPosTranRef());

                            lstLocalDataUpdate.add(updateRow);
                        }
                        daoMain.saveGiamLai2024(UserName, pos_cd_username, strDate1, lstLocalDataUpdate, "2");
                    }
                    //Lưu phần xác nhận lãi giảm     
                    addActionMessage("Bạn đã lưu dữ liệu thành công");
                    return SUCCESS;
                } else {
                    addActionError("Chi nhánh chưa chốt số liệu. Bạn chỉ cập nhật thông tin hạch toán sau khi PGD đã được chốt số liệu!");
                    return ERROR;
                }

            } else {
                addActionError("Chức năng nay chỉ hỗ trợ cấp Phòng giao dịch!");
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> HTLS2021: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> HTLS2021: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }

    }

    public String unlock_c3() {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");
            String D5 = ServletActionContext.getRequest().getParameter("ngaybc");
            String D6 = ServletActionContext.getRequest().getParameter("pos_flag");
            String D7 = ServletActionContext.getRequest().getParameter("key_lock");
            String D8 = ServletActionContext.getRequest().getParameter("skhoa");
            String UserName = (String) ActionContext.getContext().getSession().get("sUserName");
            String skhoa = (String) ActionContext.getContext().getSession().get("skhoa");

            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MMM/yyyy");
            Date date = inputFormat.parse(D5);
            String formattedDate = outputFormat.format(date);
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            GenericResult<String> _result = daoMain.unlock_c3_THTK(skhoa, D1, UserName, formattedDate, D7);

            if (_result.isIsSuccess()) {
                status = "1";
                message = "";
            } else {
                status = "0";
                message = _result.getMessage();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> cancelAssign: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> cancelAssign: " + e.getMessage());
            status = "0";
            message = e.getMessage();
        }
        return SUCCESS;
    }
}
