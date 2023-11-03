/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.nhaptaycn.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import vbsp.ims.nghiquyet11cp.*;
import vbsp.ims.nhaptaycn.action.*;
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
import vbsp.ims.gqvl_2023.Service_GQVL2023;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.restapi.CustCicModel;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.DuLieuNTRowX;
import vbsp.ims.restapi.IntDeductionModel;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.LockSendCiCModel;
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
                posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
                pos_cd_username = posMainModel.getPosCd();
                service = new DuLieuNTService();
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
                List<CustCicModel> custCIC_TMP = new ArrayList<>();
                custCIC_TMP = service.getDataCustCIC(pos_cd_username, "20231231",
                        hmParameter.get("maxa").toString(), hmParameter.get("mato").toString().split("_")[1], "");
                DecimalFormat df = new DecimalFormat("#.##");
                if (custCIC_TMP.size() > 499) {
                    setMessageErr("Dữ liệu quá lớn. Vui lòng chọn từng tổ để xác nhận.");
//                    addActionError("Dữ liệu quá lớn. Vui lòng chọn từng tổ để xác nhận.");;
//                    return ERROR;
                } else {
                    int i = 0;               
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
                    DateFormat dateHienthi = new SimpleDateFormat("dd/MM/yyyy");
                    for (CustCicModel item : custCIC_TMP) {
                        i++;
                        item.setBirthDay(dateHienthi.format(sdf.parse(item.getBirthDay())));
                        if (item.getIdExpiredDate() != null) {
                            item.setIdExpiredDate(dateHienthi.format(sdf.parse(item.getIdExpiredDate())));
                        }
                        custCIC.add(item);
                    }
                }
                //Cho phần sum
                ArrayList<LockSendCiCModel> lstData = service.getDataLockSendCic(pos_cd_username, "S", "20231231");

                //String formatted = df.format(2.00023);
                chotsl = "";
                for (LockSendCiCModel item : lstData) {
                    if (item.getStatus().equals("0")) {
                        setChotsl("0");
                    }
//                    i++;
                    QT_DULIEU_NT row = new QT_DULIEU_NT();
                    row.setKHOA("CIC_01");
//                    row.setTHUTU(i);
                    Date reportDate = DateUtil.toDate(item.getReportDate());
                    row.setNGAYBC(reportDate);
                    row.setMAPGD(item.getPosCode());
                    row.setMACN(item.getMainPos());
                    row.setTEN(item.getPosName());
                    row.setD1(df.format(item.getCustomerTotal()));
                    row.setD2(df.format(item.getCustomerNotReviewCount()));
                    row.setD25(chotsl.equals("0") ? item.getStatus() : item.getStatus().equals("1") ? "1" : "0");
                    lstDulieuNt.add(row);
                }

                return SUCCESS;

            } else if (Grade.equals("2")) {
                if (!getParaSession()) {
                    return ERROR;
                }
                HashMap hmParameter = getParameter();
//                Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(hmParameter.get("ngay_bc").toString());
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//                String dateStr = sdf.format(date1);

                posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
                pos_cd_username = posMainModel.getPosCd();
                service = new DuLieuNTService();
                ArrayList<LockSendCiCModel> lstData = service.getDataLockSendCic(pos_cd_username, "M", "20231231");
                int i = 0;

                DecimalFormat df = new DecimalFormat("#.##");
                //String formatted = df.format(2.00023);
                chotsl = "";
                for (LockSendCiCModel item : lstData) {
                    if (item.getStatus().equals("0")) {
                        setChotsl("0");
                    }
                    i++;
                    QT_DULIEU_NT row = new QT_DULIEU_NT();
                    row.setKHOA("CIC_01");
                    row.setTHUTU(i);
                    Date reportDate = DateUtil.toDate(item.getReportDate());
                    row.setNGAYBC(reportDate);
                    row.setMAPGD(item.getPosCode());
                    row.setMACN(item.getMainPos());
                    row.setTEN(item.getPosName());
                    row.setD1(df.format(item.getCustomerTotal()));
                    row.setD2(df.format(item.getCustomerNotReviewCount()));
                    row.setD25(chotsl.equals("0") ? item.getStatus() : item.getStatus().equals("1") ? "1" : "0");
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

    public String save() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            SimpleDateFormat CvDate = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat LsDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            int i = 0;
            if (Grade.equals("1")) {

                ArrayList<CustCicModel> custCIC_TMP = new ArrayList<>();
                for (CustCicModel item : custCIC) {
                    i++;
                    CustCicModel tempadd = new CustCicModel();
                    tempadd.setMainPos(item.getMainPos());
                    tempadd.setPosCode(item.getPosCode());
                    tempadd.setCustomerCode(item.getCustomerCode());
                    tempadd.setCicCode(item.getCicCode());
//                    tempadd.setCustomerName(item.getCustomerName());
//                    tempadd.setIsValidCustomerName(item.getIsValidCustomerName());
//                    tempadd.setBirthDay(item.getBirthDay());
//                    tempadd.setBirthDay(LsDate.format(new SimpleDateFormat("dd/MM/yyyy").parse(item.getBirthDay())));
//                    tempadd.setIdNo(item.getIdNo());
//                    tempadd.setNewIdNo(item.getNewIdNo());
//                    tempadd.setIsValidNewIdNo(item.getIsValidNewIdNo());
//                    tempadd.setOldIdNo(item.getOldIdNo());
//                    tempadd.setIsValidOldIdNo(item.getIsValidOldIdNo());
//                    tempadd.setC06OldIdNo(item.getC06OldIdNo());
//                    tempadd.setC06NewIdNo(item.getC06NewIdNo());
//                    tempadd.setCommuneCode(item.getCommuneCode());
//                    tempadd.setCommuneName(item.getCommuneName());
//                    tempadd.setSubCommuneCode(item.getSubCommuneCode());
//                    tempadd.setSubCommuneName(item.getSubCommuneName());
//                    tempadd.setStatus(item.getStatus());
//                    tempadd.setType(item.getType());
//                    tempadd.setCreatedBy(item.getCreatedBy());
//                    tempadd.setCreatedDate(item.getCreatedDate());
//                    tempadd.setUpdatedBy(item.getUpdatedBy());
//                    tempadd.setUpdatedDate(item.getUpdatedDate());
//                    tempadd.setIntellectUpdateFlag(item.getIntellectUpdateFlag());
//                    tempadd.setGroupCode(item.getGroupCode());
//                    tempadd.setMobileNumber(item.getMobileNumber());
//                    tempadd.setPrincipleBalance(item.getPrincipleBalance());
//                    tempadd.setSavingBalance(item.getSavingBalance());
//                    tempadd.setRemainIntAmount(item.getRemainIntAmount());
//                    tempadd.setIdExpiredDate(item.getIdExpiredDate());
//                    tempadd.setIdExpiredDate(LsDate.format(new SimpleDateFormat("dd/MM/yyyy").parse(item.getIdExpiredDate())));
                    tempadd.setWrongFullNameConfirmFlag(checkItemInList15(lstCombox, item.getCicCode()) == 1 ? 1 : 0);
                    tempadd.setWrongIdNoConfirmFlag(checkItemInList16(lstCombox, item.getCicCode()) == 1 ? 1 : 0);
                    tempadd.setWrongIssueDateConfirmFlag(checkItemInList17(lstCombox, item.getCicCode()) == 1 ? 1 : 0);
                    tempadd.setWrongIssuePlaceConfirmFlag(checkItemInList18(lstCombox, item.getCicCode()) == 1 ? 1 : 0);
                    tempadd.setWrongBirthdayConfirmFlag(checkItemInList19(lstCombox, item.getCicCode()) == 1 ? 1 : 0);
//                    tempadd.setCustomerStatus(item.getCustomerStatus());
//                    tempadd.setCoreBankingIdNo(item.getCoreBankingIdNo());
//                    tempadd.setCoreBankingIssuePlace(item.getCoreBankingIssuePlace());
//                    tempadd.setCoreBankingIssueDate(item.getCoreBankingIssueDate());
//                    tempadd.setCoreBankingCustomerName(item.getCoreBankingCustomerName());
//                    tempadd.setCoreBankingBirthday(item.getCoreBankingBirthday());

                    custCIC_TMP.add(tempadd);
                }
                service = new DuLieuNTService();
                int status = service.updateCIC(pos_cd_username, "20231231", UserName, UserName, custCIC_TMP);
                if (status == 200) {
                    addActionMessage("Thành công");
                    return SUCCESS;
                }
            } else if (Grade.equals("2")) {
                try {
                    chotsl = "";
                    ArrayList<LockSendModel> lstData = service.getDataLockSendNQ11CP(pos_cd_username, "M", "20231231");
                    for (LockSendModel item : lstData) {
                        if (item.getStatus().equals("0")) {
                            setChotsl("0");
                        }
                    }

                    ArrayList<UpdateLockModel> lstUpdateDateLock = new ArrayList<>();
                    for (QT_DULIEU_NT tmp : lstDulieuNt) {
                        UpdateLockModel tempadd = new UpdateLockModel();
                        String st = "0";
                        if (tmp.getD25() == null) {
                            st = "0";
                        } else {
                            st = "1";
                        }
                        int status = service.updateChotSL("CIC_CUSTOMER", tmp.getMAPGD(), "S", "20231231", st, UserName, null);
                        if (status != 200) {
                            addActionError("Bạn chưa chốt/ mở chốt được xin liên hệ với quản trị để khắc phục");
                            return ERROR;
                        }
                    }
                } catch (Exception e) {
                    CoreLogger.error(this.getClass().getName() + " Exception -> CIC_001: " + e.getMessage());
                    System.err.println(this.getClass().getName() + " Exception -> NQ11CP: " + e.getMessage());
                    addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                    return ERROR;
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

    static int checkItemInList15(List<CustCicModel> lst, String cust) {
        for (CustCicModel item : lst) {
            try {
                if (item.getC15().equals(cust)) {
                    return 1;
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }

    static int checkItemInList16(List<CustCicModel> lst, String cust) {
        for (CustCicModel item : lst) {
            try {
                if (item.getC16().equals(cust)) {
                    return 1;
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }

    static int checkItemInList17(List<CustCicModel> lst, String cust) {
        for (CustCicModel item : lst) {
            try {
                if (item.getC17().equals(cust)) {
                    return 1;
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }

    static int checkItemInList18(List<CustCicModel> lst, String cust) {
        for (CustCicModel item : lst) {
            try {
                if (item.getC18().equals(cust)) {
                    return 1;
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }

    static int checkItemInList19(List<CustCicModel> lst, String cust) {
        for (CustCicModel item : lst) {
            try {
                if (item.getC19().equals(cust)) {
                    return 1;
                }
            } catch (Exception e) {
            }
        }
        return 0;
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
