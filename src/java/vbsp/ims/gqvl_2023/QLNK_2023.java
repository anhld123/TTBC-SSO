/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.gqvl_2023;

import static com.opensymphony.xwork2.Action.ERROR;
import vbsp.ims.nhaptaycn.action.*;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import vbsp.ims.nhaptaycn.action.*;
import vbsp.ims.nhaptaycn.action.*;
import java.sql.Connection;
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
import vbsp.ims.leavelocal.LeaveHomeDao;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.nghiquyet11cp.DaoNghiquyet11cp;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.DuLieuNTRowX;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.tdnn.DaoTdnnMain;
import vbsp.ims.util.DateUtil;
import vbsp.ims.xml.XmlKtgsSync;

/**
 *
 * @author Trung
 */
public class QLNK_2023 extends ActionNhaptaycnMain
        implements NhaptaycnFunction {

    Service_GQVL2023 _leaveHomeService;
    private List<DuLieuNTRow> lstData;
    private String txtSoku;
    private String txtGetData;
    private String txtType;
    private String messagePage;
    private String gradeAuthor1;
    private InputStream pageResult;

    public String getTxtType() {
        return txtType;
    }

    public void setTxtType(String txtType) {
        this.txtType = txtType;
    }

    public InputStream getPageResult() {
        return pageResult;
    }

    public void setPageResult(InputStream pageResult) {
        this.pageResult = pageResult;
    }

    @Override
    public String getGradeAuthor1() {
        return gradeAuthor1;
    }

    @Override
    public void setGradeAuthor1(String gradeAuthor1) {
        this.gradeAuthor1 = gradeAuthor1;
    }

    public String getMessagePage() {
        return messagePage;
    }

    public void setMessagePage(String messagePage) {
        this.messagePage = messagePage;
    }

    public String getTxtGetData() {
        return txtGetData;
    }

    public void setTxtGetData(String txtGetData) {
        this.txtGetData = txtGetData;
    }

    public String getTxtSoku() {
        return txtSoku;
    }

    public void setTxtSoku(String txtSoku) {
        this.txtSoku = txtSoku;
    }

    @Override
    public String load() {
        try {
//            System.err.println("NTMOI - 01");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            Connection conn = new DaoConnect().getConnect();
            String matoValue = hmParameter.get("mato").toString().split("_")[1];
            if ("0000000".equals(matoValue)) {
                matoValue = "";
            }
            String maxaValue = hmParameter.get("maxa").toString();
            if ("000000".equals(maxaValue)) {
                maxaValue = "";
            }
            if (hmParameter.get("maxa").toString().equals("000000") && hmParameter.get("txtSoku").toString().equals("")) {
                addActionError("Vui lòng nhập mã món hoặc chọn xã để rà soát số liệu.");
                return ERROR;
            }

            if (txtGetData == "1") {
                txtType = "1";
            } else {
                txtType = "1";
            }

            _leaveHomeService = new Service_GQVL2023();
            this.lstData = _leaveHomeService.getQLNK(pos_cd_username, "S", hmParameter.get("ngay_bc").toString(), maxaValue, matoValue, txtSoku, txtGetData, txtType);
            setMessagePage(String.valueOf(lstData.size()));
            if (lstData.size() > 150) {
                addActionError("Dữ liệu quá lớn. Vui lòng chọn từng tổ để xác nhận.");
                return ERROR;
            } else {
                int iStt = 1;
                for (DuLieuNTRow item : lstData) {
                    QT_DULIEU_NT row = new QT_DULIEU_NT();
                    if (gradeAuthor1.equals("1")) {
                        try {
                            if (item.getD26() == null || !item.getD26().equals("1")) {
                                row.setKHOA(item.getKey());
                                row.setTHUTU(iStt);
                                iStt++;
                                row.setTT_HIENTHI(item.getOrderDescription());
                                row.setMA(item.getCode());
                                row.setTEN(item.getName());
                                Date reportDate = DateUtil.toDate(item.getReportDate());
                                row.setNGAYBC(reportDate);
                                row.setNAMBC(item.getReportYear());
                                row.setMAPGD(item.getPosCode());
                                row.setCO_TONGHOP(item.getPosFlag());
                                row.setMACN(item.getBranchCode());
                                row.setNGUOI_NHAP(item.getMakerId());
                                Date makerDate = DateUtil.toDate(item.getMakerDate());
                                row.setNGAY_NHAP(makerDate);
                                row.setNGUOI_DUYET(item.getAuthoriseId());
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
                                row.setD16(item.getD16());
                                row.setD17(item.getD17());
                                row.setD18(item.getD18());
                                row.setD19(item.getD19());
                                row.setD20(item.getD20());
                                row.setD21(item.getD21());
                                row.setD22(item.getD22());
                                row.setD23(item.getD23());
                                row.setD24(item.getD24());
                                row.setD25(item.getD25());
                                row.setD26(item.getD26());
                                row.setD27(item.getD27());
                                row.setD28(item.getD28());
                                row.setD29(item.getD29());
                                row.setD30(item.getD30());
                                row.setNHAPTAY(item.getManualFlag());
                                row.setFONTFORMAT(item.getFontFormat());
                                row.setKIEUIN(item.getStyle());
                                lstDulieuNt.add(row);
                            }
                        } catch (Exception e) {
                        }
                    } else {
                        try {
                            if (item.getD26().equals("1")) {
                                row.setKHOA(item.getKey());
                                row.setTHUTU(iStt);
                                iStt++;
                                row.setTT_HIENTHI(item.getOrderDescription());
                                row.setMA(item.getCode());
                                row.setTEN(item.getName());
                                Date reportDate = DateUtil.toDate(item.getReportDate());
                                row.setNGAYBC(reportDate);
                                row.setNAMBC(item.getReportYear());
                                row.setMAPGD(item.getPosCode());
                                row.setCO_TONGHOP(item.getPosFlag());
                                row.setMACN(item.getBranchCode());
                                row.setNGUOI_NHAP(item.getMakerId());
                                Date makerDate = DateUtil.toDate(item.getMakerDate());
                                row.setNGAY_NHAP(makerDate);
                                row.setNGUOI_DUYET(item.getAuthoriseId());
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
                                row.setD16(item.getD16());
                                row.setD17(item.getD17());
                                row.setD18(item.getD18());
                                row.setD19(item.getD19());
                                row.setD20(item.getD20());
                                row.setD21(item.getD21());
                                row.setD22(item.getD22());
                                row.setD23(item.getD23());
                                row.setD24(item.getD24());
                                row.setD25(item.getD25());
                                row.setD26(item.getD26());
                                row.setD27(item.getD27());
                                row.setD28(item.getD28());
                                row.setD29(item.getD29());
                                row.setD30(item.getD30());
                                row.setNHAPTAY(item.getManualFlag());
                                row.setFONTFORMAT(item.getFontFormat());
                                row.setKIEUIN(item.getStyle());
                                lstDulieuNt.add(row);
                            }
                        } catch (Exception e) {
                        }

                    }
                    if (conn != null) {
                        conn.close();
                    }
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> QLNK: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> QLNK: " + e.getMessage());
        }

        if (gradeAuthor1.equals(
                "1")) {
            return SUCCESS;
        } else {
            return "success_1";
        }
    }

    @Override
    public String save() {
        System.out.println("vao váe");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
            ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            HashMap hmParameter = getParameter();
            Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(hmParameter.get("ngay_bc").toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = sdf.format(date1);
            int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date1));

            ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
//             List<QT_DULIEU_NT> lstDulieuNt_tmp = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                if (tmp.getD18() != null) {
                    DuLieuNTRow tempadd = new DuLieuNTRow();
                    int iStt = 1;
                    iStt++;
                    tempadd.setKey("01_QLNK");
                    tempadd.setOrderValue("");
                    tempadd.setOrderDescription("");
                    tempadd.setCode(tmp.getMA());
                    tempadd.setMakerId(UserName);
                    tempadd.setAuthoriseId(UserName);
                    tempadd.setReportDate(hmParameter.get("ngay_bc").toString());
                    tempadd.setName(tmp.getTEN());
                    tempadd.setReportYear(year);
                    tempadd.setPosCode(pos_cd_username);
                    tempadd.setPosFlag("S");
                    tempadd.setBranchCode(tmp.getMACN());
                    tempadd.setD1(tmp.getD1());
                    tempadd.setD2(tmp.getD2());
                    tempadd.setD3(tmp.getD3());
                    tempadd.setD4(tmp.getD4());
                    tempadd.setD5(tmp.getD5());
                    tempadd.setD6(tmp.getD6());
                    tempadd.setD7(tmp.getD7());
                    tempadd.setD8(tmp.getD8());
                    tempadd.setD9(tmp.getD9());
                    tempadd.setD10(tmp.getD10());
                    tempadd.setD11(tmp.getD11());
                    tempadd.setD12(tmp.getD12());
                    tempadd.setD13(tmp.getD13());
                    tempadd.setD14(tmp.getD14());
                    tempadd.setD15(tmp.getD15());
                    tempadd.setD16(tmp.getD16());
                    tempadd.setD17(tmp.getD17());
                    tempadd.setD19(tmp.getD19());
                    tempadd.setD20(tmp.getD20());
                    tempadd.setD21(tmp.getD21());
                    tempadd.setD22(tmp.getD22());
                    tempadd.setD23(tmp.getD23());
                    tempadd.setD24(tmp.getD24());
                    tempadd.setD25(tmp.getD25());
                    tempadd.setD26(tmp.getD26());
                    tempadd.setD27(tmp.getD27());
                    tempadd.setD28(tmp.getD28());
                    tempadd.setD29(tmp.getD29());
                    tempadd.setD30(tmp.getD30());
                    lstUpdateDate.add(tempadd);
                    tmp.setD26("0");
                    lstLocalDataUpdate.add(tmp);
//                    lstDulieuNt_tmp.add(tmp);
                }
            }
            _leaveHomeService = new Service_GQVL2023();
            int status = _leaveHomeService.saveQLNK(pos_cd_username, "S", hmParameter.get("ngay_bc").toString(), "", "", lstUpdateDate, "1");
            String matoValue1 = hmParameter.get("txtGetData").toString();
            if (status == 200) {
//                if (matoValue1.equals("0")) {
                String matoValue = hmParameter.get("mato").toString().split("_")[1];
                if ("0000000".equals(matoValue)) {
                    matoValue = "";
                }
                String maxaValue = hmParameter.get("maxa").toString();
                if ("000000".equals(maxaValue)) {
                    maxaValue = "";
                }
                if (!daoMain.saveQLNK2023_1("01_QLNK", UserName, Grade, hmParameter.get("ngay_bc").toString(), lstLocalDataUpdate, pos_cd_username, maxaValue, matoValue)) {
                    addActionError("Bạn chưa lưu được báo cáo màn hình Nhập, tại chi nhánh vui lòng liên hệ quản trị viên!!");
                    String code = String.valueOf(2);
                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                    return ERROR;

                }
//                }
//                else {
//                    if (!daoMain.saveQLNK2023("01_QLNK", UserName, Grade, hmParameter.get("ngay_bc").toString(), lstDulieuNt, pos_cd_username)) {
//                        addActionError("Bạn chưa lưu được báo cáo màn hình Danh sách,tại chi nhánh vui lòng liên hệ quản trị viên!");
//                        return ERROR;
//                    }
//                }
            }
        } catch (Exception e) {
//            CoreLogger.error(this.getClass().getName() + " Exception -> SMS_001: " + e.getMessage());
//            System.err.println(this.getClass().getName() + " Exception -> SMS_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            String code = String.valueOf(1);
            this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String delete() {
        System.out.println("vao váe");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
            ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            HashMap hmParameter = getParameter();
            Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(hmParameter.get("ngay_bc").toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = sdf.format(date1);
            int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date1));
            String localD2 = null;
            ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                if (tmp.getD18() != null) {
                    DuLieuNTRow tempadd = new DuLieuNTRow();
                    tempadd.setKey("01_QLNK");
                    tempadd.setOrderValue("");
                    tempadd.setOrderDescription("");
                    tempadd.setCode(tmp.getMA());
                    tempadd.setMakerId(UserName);
                    tempadd.setAuthoriseId(UserName);
                    tempadd.setReportDate(hmParameter.get("ngay_bc").toString());
                    tempadd.setName(tmp.getTEN());
                    tempadd.setReportYear(year);
                    tempadd.setPosCode(pos_cd_username);
                    tempadd.setPosFlag("S");
                    tempadd.setBranchCode(tmp.getMACN());
                    tempadd.setD1(tmp.getD1());
                    tempadd.setD2(tmp.getD2());
                    tempadd.setD3(tmp.getD3());
                    tempadd.setD4(tmp.getD4());
                    tempadd.setD5(tmp.getD5());
                    tempadd.setD6(tmp.getD6());
                    tempadd.setD7(tmp.getD7());
                    tempadd.setD8(tmp.getD8());
                    tempadd.setD9(tmp.getD9());
                    tempadd.setD10(tmp.getD10());
                    tempadd.setD11(tmp.getD11());
                    tempadd.setD12(tmp.getD12());
                    tempadd.setD13(tmp.getD13());
                    tempadd.setD14(tmp.getD14());
                    tempadd.setD15(tmp.getD15());
                    tempadd.setD16(tmp.getD16());
                    tempadd.setD17(tmp.getD17());
                    tempadd.setD19(tmp.getD19());
                    tempadd.setD20(tmp.getD20());
                    tempadd.setD21(tmp.getD21());
                    tempadd.setD22(tmp.getD22());
                    tempadd.setD23(tmp.getD23());
                    tempadd.setD24(tmp.getD24());
                    tempadd.setD25(tmp.getD25());
                    tempadd.setD26(tmp.getD26());
                    tempadd.setD27(tmp.getD27());
                    tempadd.setD28(tmp.getD28());
                    tempadd.setD29(tmp.getD29());
                    tempadd.setD30(tmp.getD30());
                    lstUpdateDate.add(tempadd);
                    lstLocalDataUpdate.add(tmp);
                    localD2 = tmp.getD2();
                }
            }

            _leaveHomeService = new Service_GQVL2023();
            int status = _leaveHomeService.deleteQLNK(pos_cd_username, "S", hmParameter.get("ngay_bc").toString(), "", "", lstUpdateDate, "1");
            String matoValue1 = hmParameter.get("txtGetData").toString();
            if (status == 200) {
                if (!daoMain.deleteQLNK2023("01_QLNK", UserName, Grade, hmParameter.get("ngay_bc").toString(), lstLocalDataUpdate, pos_cd_username, localD2)) {
                    addActionError("Bạn chưa xóa được dữ liệu màn hình Nhập, tại chi nhánh vui lòng liên hệ quản trị viên!!");
                    String code = String.valueOf(2);
                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                    return ERROR;
                }
            }
        } catch (Exception e) {
            addActionError("Bạn chưa xóa được dữ liệu xin liên hệ với quản trị để khắc phục");
            String code = String.valueOf(1);
            this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }
        addActionMessage("Bạn đã xóa dữ liệu thành công");
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }

    public String lock() {
        System.out.println("vao váe");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Bạn chưa phê duyệt được số liệu xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
            ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            HashMap hmParameter = getParameter();
            Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(hmParameter.get("ngay_bc").toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = sdf.format(date1);
            int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date1));

            ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
//             List<QT_DULIEU_NT> lstDulieuNt_tmp = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                if (tmp.getD18() != null) {
                    DuLieuNTRow tempadd = new DuLieuNTRow();
                    int iStt = 1;
                    iStt++;
                    tempadd.setKey("01_QLNK");
                    tempadd.setOrderValue("");
                    tempadd.setOrderDescription("");
                    tempadd.setCode(tmp.getMA() + "_" + iStt);
                    tempadd.setMakerId(UserName);
                    tempadd.setAuthoriseId(UserName);
                    tempadd.setReportDate(hmParameter.get("ngay_bc").toString());
                    tempadd.setName(tmp.getTEN());
                    tempadd.setReportYear(year);
                    tempadd.setPosCode(pos_cd_username);
                    tempadd.setPosFlag("S");
                    tempadd.setBranchCode(tmp.getMACN());
                    tempadd.setD1(tmp.getD1());
                    tempadd.setD2(tmp.getD2());
                    tempadd.setD3(tmp.getD3());
                    tempadd.setD4(tmp.getD4());
                    tempadd.setD5(tmp.getD5());
                    tempadd.setD6(tmp.getD6());
                    tempadd.setD7(tmp.getD7());
                    tempadd.setD8(tmp.getD8());
                    tempadd.setD9(tmp.getD9());
                    tempadd.setD10(tmp.getD10());
                    tempadd.setD11(tmp.getD11());
                    tempadd.setD12(tmp.getD12());
                    tempadd.setD13(tmp.getD13());
                    tempadd.setD14(tmp.getD14());
                    tempadd.setD15(tmp.getD15());
                    tempadd.setD16(tmp.getD16());
                    tempadd.setD17(tmp.getD17());
                    tempadd.setD19(tmp.getD19());
                    tempadd.setD20(tmp.getD20());
                    tempadd.setD21(tmp.getD21());
                    tempadd.setD22(tmp.getD22());
                    tempadd.setD23(tmp.getD23());
                    tempadd.setD24(tmp.getD24());
                    tempadd.setD25(tmp.getD25());
                    tempadd.setD26("1");
                    tempadd.setD27(tmp.getD27());
                    tempadd.setD28(tmp.getD28());
                    tempadd.setD29(tmp.getD29());
                    tempadd.setD30(tmp.getD30());
                    lstUpdateDate.add(tempadd);
                    tmp.setD26("1");
                    lstLocalDataUpdate.add(tmp);
//                    lstDulieuNt_tmp.add(tmp);
                }
            }
            _leaveHomeService = new Service_GQVL2023();
            int status = _leaveHomeService.saveQLNK(pos_cd_username, "S", hmParameter.get("ngay_bc").toString(), "", "", lstUpdateDate, "1");
            String matoValue1 = hmParameter.get("txtGetData").toString();
            if (status == 200) {
//                if (matoValue1.equals("0")) {
                String matoValue = hmParameter.get("mato").toString().split("_")[1];
                if ("0000000".equals(matoValue)) {
                    matoValue = "";
                }
                String maxaValue = hmParameter.get("maxa").toString();
                if ("000000".equals(maxaValue)) {
                    maxaValue = "";
                }
                if (!daoMain.saveQLNK2023_1("01_QLNK", UserName, Grade, hmParameter.get("ngay_bc").toString(), lstLocalDataUpdate, pos_cd_username, maxaValue, matoValue)) {
                    addActionError("Bạn chưa lưu được báo cáo màn hình Nhập, tại chi nhánh vui lòng liên hệ quản trị viên!!");
                    String code = String.valueOf(2);
                    this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
                    return ERROR;

                }
//                }
//                else {
//                    if (!daoMain.saveQLNK2023("01_QLNK", UserName, Grade, hmParameter.get("ngay_bc").toString(), lstDulieuNt, pos_cd_username)) {
//                        addActionError("Bạn chưa lưu được báo cáo màn hình Danh sách,tại chi nhánh vui lòng liên hệ quản trị viên!");
//                        return ERROR;
//                    }
//                }
            }
        } catch (Exception e) {
//            CoreLogger.error(this.getClass().getName() + " Exception -> SMS_001: " + e.getMessage());
//            System.err.println(this.getClass().getName() + " Exception -> SMS_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            String code = String.valueOf(1);
            this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        String code = String.valueOf(200);
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }
}
