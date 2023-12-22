/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.bcqt.action;

import vbsp.ims.nhaptaycn.action.*;
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
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.restapi.CustCicModel;
import vbsp.ims.restapi.IntDeductionModel;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.DuLieuNtMs13AKhoanh;
import vbsp.ims.restapi.LockSendCiCModel;
import vbsp.ims.restapi.LockSendModel;
import vbsp.ims.restapi.NQ11cpModel;
import vbsp.ims.restapi.UpdateLockModel;

import vbsp.ims.util.DateUtil;

/**
 *
 * @author Trung
 */
public class QT_MS13_2023 extends ActionNghiquyet11cpMain
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
                posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
                pos_cd_username = posMainModel.getPosCd();
                service = new DuLieuNTService();
                Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(hmParameter.get("ngay_bc").toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String dateStr = sdf.format(date1);
                ArrayList<LockSendModel> lstDataLock = service.getDataLockManual("QT_MS13_2023", pos_cd_username, "S", dateStr);
                try {
                    setChotsl(lstDataLock.get(0).getStatus());
                } catch (Exception e) {
                    setChotsl("0");
                }
                if (hmParameter.get("maxa").toString().equals("000000")) {
                    addActionError("Vui lòng chọn xã để rà soát số liệu.");;
                    return ERROR;
                }
                DecimalFormat df = new DecimalFormat("#.##");
                List<DuLieuNtMs13AKhoanh> aKhoanhs = new ArrayList<>();
                aKhoanhs = service.getDataMs13aKhoanh(pos_cd_username,
                        hmParameter.get("maxa").toString(), hmParameter.get("mato").toString().split("_")[1], "", dateStr);
                if (aKhoanhs.size() > 150) {
                    setMessageErr("Dữ liệu quá lớn. Vui lòng chọn từng tổ để xác nhận.");
                } else {
                    int i = 0;
                    SimpleDateFormat sdfk = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
                    DateFormat dateHienthi = new SimpleDateFormat("dd/MM/yyyy");
                    int order = 1;
                    for (DuLieuNtMs13AKhoanh item : aKhoanhs) {
                        item.setD1(String.valueOf(order));
                        lstDulieuNtMs13a.add(item);
                        order++;
                    }
                }
                return SUCCESS;
            } else if (Grade.equals("2")) {
                addActionError("Vui lòng vào cấp 1 để nhập số liệu.");;
                    return ERROR;
//                if (!getParaSession()) {
//                    return ERROR;
//                }
//                HashMap hmParameter = getParameter();
//                posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
//                pos_cd_username = posMainModel.getPosCd();
//                service = new DuLieuNTService();
//                ArrayList<LockSendCiCModel> lstData = service.getDataLockSendCic(pos_cd_username, "M", "20231231");
//                int i = 0;
//
//                DecimalFormat df = new DecimalFormat("#.##");
//                chotsl = "";
//                for (LockSendCiCModel item : lstData) {
//                    if (item.getStatus().equals("0")) {
//                        setChotsl("0");
//                    }
//                    i++;
//                    QT_DULIEU_NT row = new QT_DULIEU_NT();
//                    row.setKHOA("CIC_01");
//                    row.setTHUTU(i);
//                    Date reportDate = DateUtil.toDate(item.getReportDate());
//                    row.setNGAYBC(reportDate);
//                    row.setMAPGD(item.getPosCode());
//                    row.setMACN(item.getMainPos());
//                    row.setTEN(item.getPosName());
//                    row.setD1(df.format(item.getCustomerTotal()));
//                    row.setD2(df.format(item.getCustomerNotReviewCount()));
//                    row.setD25(chotsl.equals("0") ? item.getStatus() : item.getStatus().equals("1") ? "1" : "0");
//                    lstDulieuNt.add(row);
//                }
//                return "success_c2";
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> QT_MS13_2023: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> QT_MS13_2023: " + e.getMessage());
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
            Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(hmParameter.get("ngay_bc").toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = sdf.format(date1);
            service = new DuLieuNTService();
            ArrayList<LockSendModel> lstDataLock = service.getDataLockManual("QT_MS13_2023", pos_cd_username, "S", dateStr);
            if (lstDataLock == null || lstDataLock.size() == 0) {
                addActionError("Vui lòng kiểm tra lại kết nối tới Api trung ương");
                return ERROR;
            } else {
                if (lstDataLock.get(0).getStatus().equals("1")) {
                    addActionError("TW đã chốt số liệu. Vui long liên hệ với cấp trên để mở lại");
                    return ERROR;
                }
            }

            if (Grade.equals("1")) {
                ArrayList<DuLieuNtMs13AKhoanh> tmp = new ArrayList<>();
                //Thông tin ra soát với hồ sơ vay vốn

                for (DuLieuNtMs13AKhoanh item : lstDulieuNtMs13a) {
                    if (item.getD21() != null) {
                        DuLieuNtMs13AKhoanh tempadd = new DuLieuNtMs13AKhoanh();
                        tempadd.setPosCode(item.getPosCode());
                        tempadd.setReportDate(item.getReportDate());
                        tempadd.setD5(item.getD5());
                        tempadd.setD7(item.getD7());
                        tempadd.setD8(item.getD8());
                        tempadd.setD9(item.getD9());
                        tempadd.setD10(item.getD10());
                        tempadd.setD11(item.getD11());
                        tempadd.setD12(item.getD12());
                        tempadd.setD13(item.getD13());
                        tempadd.setD14(item.getD14());
                        tempadd.setD15(item.getD15());
                        tempadd.setD16(item.getD16());
                        tempadd.setD17(item.getD17());
                        tempadd.setD18(item.getD18());
                        tempadd.setD19(item.getD19());
                        tempadd.setD20(item.getD20());

                        tmp.add(tempadd);
                    }
                }

                int status = service.updateMs13aKhoanh(pos_cd_username, dateStr, UserName, UserName, tmp);
                if (status == 200) {
                    ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
                    for (DuLieuNtMs13AKhoanh item : tmp) {
                        QT_DULIEU_NT updateRow = new QT_DULIEU_NT();

                        updateRow.setMAPGD(item.getPosCode());
                        updateRow.setD5(item.getD5());

                        updateRow.setD7(item.getD7());
                        updateRow.setD8(item.getD8());
                        updateRow.setD9(item.getD9());
                        updateRow.setD10(item.getD10());

                        updateRow.setD11(item.getD11());
                        updateRow.setD12(item.getD12());
                        updateRow.setD13(item.getD13());
                        updateRow.setD14(item.getD14());
                        updateRow.setD15(item.getD15());
                        updateRow.setD16(item.getD16());
                        updateRow.setD17(item.getD17());
                        updateRow.setD18(item.getD18());
                        updateRow.setD19(item.getD19());
                        updateRow.setD20(item.getD20());

                        lstLocalDataUpdate.add(updateRow);
                    }

                    if (!DaoNghiquyet11cp.newInstance().saveMs13aKhoanh_Local(UserName, pos_cd_username, hmParameter.get("ngay_bc").toString(), lstLocalDataUpdate)) {
                        addActionError("Bạn chưa lưu được báo cáo tại chi nhánh, xin liên hệ với quản trị để khắc phục");
                        return ERROR;
                    }
                } else {
                    addActionError("Lỗi lưu trữ Api lên Tw, xin liên hệ với quản trị để khắc phục");
                    return ERROR;
                }

            } else if (Grade.equals("2")) {
                try {
                    chotsl = "";
                    service = new DuLieuNTService();
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
                        int status = service.updateChotSL("QT_MS13_2023", tmp.getMAPGD(), "S", "20231231", st, UserName, null);
                        if (status != 200) {
                            addActionError("Bạn chưa chốt/ mở chốt được xin liên hệ với quản trị để khắc phục");
                            return ERROR;
                        }
                    }
                } catch (Exception e) {
                    CoreLogger.error(this.getClass().getName() + " Exception -> QT_MS13_2023: " + e.getMessage());
                    System.err.println(this.getClass().getName() + " Exception -> QT_MS13_2023: " + e.getMessage());
                    addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                    return ERROR;
                }
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> QT_MS13_2023: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> QT_MS13_2023: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo <br> xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    static int checkItemInList14(List<CustCicModel> lst, String cust) {
        for (CustCicModel item : lst) {
            try {
                if (item.getC14().equals(cust)) {
                    return 1;
                }
            } catch (Exception e) {
            }
        }
        return 0;
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

    static int checkItemInList20(List<CustCicModel> lst, String cust) {
        for (CustCicModel item : lst) {
            try {
                if (item.getC20().equals(cust)) {
                    return 1;
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }

    static int checkItemInList21(List<CustCicModel> lst, String cust) {
        for (CustCicModel item : lst) {
            try {
                if (item.getC21().equals(cust)) {
                    return 1;
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }

    static int checkItemInList28(List<CustCicModel> lst, String cust) {
        for (CustCicModel item : lst) {
            try {
                if (item.getC28().equals(cust)) {
                    return 1;
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }

    static int checkItemInList29(List<CustCicModel> lst, String cust) {
        for (CustCicModel item : lst) {
            try {
                if (item.getC29().equals(cust)) {
                    return 1;
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }

    static int checkItemInList30(List<CustCicModel> lst, String cust) {
        for (CustCicModel item : lst) {
            try {
                if (item.getC30().equals(cust)) {
                    return 1;
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }

    static int checkItemInList31(List<CustCicModel> lst, String cust) {
        for (CustCicModel item : lst) {
            try {
                if (item.getC31().equals(cust)) {
                    return 1;
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }

    static int checkItemInList33(List<CustCicModel> lst, String cust) {
        for (CustCicModel item : lst) {
            try {
                if (item.getC33().equals(cust)) {
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
