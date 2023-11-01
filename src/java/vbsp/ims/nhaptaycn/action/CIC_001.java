/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.nhaptaycn.action;

import vbsp.ims.nghiquyet11cp.*;
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
import vbsp.ims.restapi.CustCicModel;
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
public class CIC_001 extends ActionNghiquyet11cpMain
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
//                Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(hmParameter.get("ngay_bc").toString());
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//                SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
//                String dateStr = sdf.format(date1);
                posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
                pos_cd_username = posMainModel.getPosCd();
                service = new DuLieuNTService();
                //Kiểm tra xem đã chốt số liệu chưa
//                String chotsl = "0";
                ArrayList<LockSendModel> lstDataLock = service.getDataLockManual("CIC_01", pos_cd_username, "S", "20231231");
                try {
                    setChotsl(lstDataLock.get(0).getStatus());
                } catch (Exception e) {
                    setChotsl("0");
                }
                if (hmParameter.get("maxa").toString().equals("000000")) {
                    addActionError("Vui lòng chọn xã để rà soát số liệu.");;
                    return ERROR;
                }
                custCIC = service.getDataCustCIC(pos_cd_username, "20231231",
                        hmParameter.get("maxa").toString(), hmParameter.get("mato").toString().split("_")[1], "");
                if (custCIC.size() > 499) {
                    addActionError("Dữ liệu quá lớn. Vui lòng chọn từng tổ để xác nhận.");;
                    return ERROR;
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
                    row.setD25(chotsl.equals("0") ? item.getStatus() : item.getStatus().equals("1") ? "1" : "0");
//                    row.setD7(df.format(item.getDeductionIntTotal()));                   
                    lstDulieuNt.add(row);
                }
                return "success_c2";
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> CIC_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> CIC_001: " + e.getMessage());
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
                ArrayList<NQ11cpModel> lstUpdateDate = new ArrayList<>();
                if (lstDataLock.size() > 0) {
                    //Lưu phần phân loại hạch toán
                    if (lstDataLock.get(0).getStatus().equals("2")) {
                        addActionError("Đơn vị đã chốt số liệu. Bạn không thể điều chỉnh.");;
                        return ERROR;
                    } //Lưu phần xác nhận lãi giảm
                    else if (lstDataLock.get(0).getStatus().equals("3")) {
                        addActionError("Vui lòng chọn nút cập nhật hạch toán với trường hợp cập nhật hạch toán vào casa hoặc bằng tiền mặt");;
                        return ERROR;
                    } else {
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
                                    tempadd.setIntSubsidyAdjustM10Amt(new BigInteger(tmp.getD66()));
                                    if (chotsl.equals("1") || chotsl.equals("3")) {
                                        tempadd.setIntConfirmFlag("1");
                                    } else {
                                        tempadd.setIntConfirmFlag("0");
                                    }
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
                                    tempadd.setIntSubsidyAdjustM10Amt(new BigInteger(tmp.getD66()));

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
                                updateRow.setD16(df.format(item.getIntSubsidyAdjustM10Amt()));
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
//                        tempadd.setStatus(chotsl.equals("0") ? "0" : "2");
                        tempadd.setStatus("0");
                        lstUpdateDateLock.add(tempadd);
                    } else {
                        tempadd.setPosCode(tmp.getMAPGD());
//                        tempadd.setStatus(chotsl.equals("0") ? "1" : "3");
                        tempadd.setStatus("1");
                        lstUpdateDateLock.add(tempadd);
                    }
                }
                int status = service.updateDataNQ11CP_ChotSL(pos_cd_username, strDate, UserName, lstUpdateDateLock);
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> CIC_001: " + e.getMessage());
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
