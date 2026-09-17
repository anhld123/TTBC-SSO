/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.gqvl_2023;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.nghiquyet11cp.DaoNghiquyet11cp;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.util.DateUtil;
import vbsp.ims.nhaptaycn.action.*;

/**
 *
 * @author Trung
 */
public class QLNK_2023 extends ActionNhaptaycnMain implements NhaptaycnFunction {

    private Service_GQVL2023 _leaveHomeService;
    private List<DuLieuNTRow> lstData;
    private String txtSoku;
    private String txtGetData;
    private String txtType;
    private String messagePage;
    private String gradeAuthor1;
    private InputStream pageResult;
//<editor-fold defaultstate="collapsed" desc="khai báo get,set">

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
//</editor-fold>

    @Override
    public String load() {
        switch (Grade) {
            case "1":
                return load_c1();
            default:
                return load_null();
        }
    }

    public String load_null() {
        addActionError("Chức năng dành cho user cấp Phòng giao dịch.");
        return ERROR;
    }

    public String load_c1() {
        Connection conn = null;
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            conn = new DaoConnect().getConnect();

            String matoValue = hmParameter.get("mato").toString().split("_")[1];
            if ("0000000".equals(matoValue)) {
                matoValue = "";
            }
            String maxaValue = hmParameter.get("maxa").toString();
            if ("000000".equals(maxaValue)) {
                maxaValue = "";
            }
            if ("000000".equals(hmParameter.get("maxa").toString()) && "".equals(hmParameter.get("txtSoku").toString())) {
                addActionError("Vui lòng nhập mã món hoặc chọn xã để rà soát số liệu.");
                return ERROR;
            }

            txtType = "1";

            _leaveHomeService = new Service_GQVL2023();
            this.lstData = _leaveHomeService.getQLNK(pos_cd_username, "S", hmParameter.get("ngay_bc").toString(), maxaValue, matoValue, txtSoku, txtGetData, txtType);
            setMessagePage(String.valueOf(lstData.size()));

            if (lstData.size() > 150) {
                addActionError("Dữ liệu quá lớn. Vui lòng chọn từng tổ để xác nhận.");
                return ERROR;
            } else {
                int iStt = 1;
                for (DuLieuNTRow item : lstData) {
                    try {
                        String d26 = item.getD26();
                        boolean isGrade1 = "1".equals(gradeAuthor1);
                        boolean condition = isGrade1 ? (d26 == null || !d26.equals("1")) : ("1".equals(d26));

                        if (condition) {
                            QT_DULIEU_NT row = mapToQTDuLieuNt(item, iStt++);
                            lstDulieuNt.add(row);
                        }
                    } catch (Exception e) {
                        // Bắt lỗi từng dòng
                    }
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> QLNK: " + e.getMessage());
        } finally {
            closeConnection(conn);
        }

        return "1".equals(gradeAuthor1) ? SUCCESS : "success_1";
    }

    @Override
    public String save() {
        return processSaveOrLock("0", "Bạn đã lưu dữ liệu thành công");
    }

    public String lock() {
        return processSaveOrLock("1", "Bạn đã phê duyệt dữ liệu thành công");
    }

    public String unlock() {
        return processSaveOrLock("0", "Bạn đã mở khóa dữ liệu thành công");
    }

    private String processSaveOrLock(String actionType, String successMessage) {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

            HashMap hmParameter = getParameter();
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
            Date date1 = dateFormat.parse(hmParameter.get("ngay_bc").toString());
            int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date1));
            String Ngaybc = "";

            // Xác định danh sách key cần truyền: Nếu actionType là "1" (Lock) thì truyền cả "01_QLNK" và "02_QLNK", ngược lại chỉ lưu "01_QLNK"
            String[] keys = "1".equals(actionType) ? new String[]{"01_QLNK", "02_QLNK"} : new String[]{"01_QLNK"};

            // Duyệt qua từng key để xử lý gọi API độc lập
            for (String skye : keys) {
                ArrayList<DuLieuNTRow> lstUpdateDateForCurrentKey = new ArrayList<DuLieuNTRow>();
                ArrayList<QT_DULIEU_NT> lstLocalDataUpdateForCurrentKey = new ArrayList<QT_DULIEU_NT>();

                for (QT_DULIEU_NT tmp : lstDulieuNt) {
                    if (tmp.getD18() != null) {
                        // Clone hoàn toàn độc lập ngay từ đầu cho mỗi vòng lặp key để tránh đè dữ liệu
                        QT_DULIEU_NT tmpClone = cloneQTDuLieuNt(tmp);

                        String updatedD30 = tmpClone.getD30(); // Mặc định giữ nguyên D30 khi chỉ Save

                        // CHỈ TĂNG D30 VÀ XỬ LÝ KHÓA 02 KHI LÀ ACTION LOCK ("1")
                        if ("1".equals(actionType)) {
                            String currentD30 = tmpClone.getD30();
                            int nextD30Val = 0;
                            try {
                                if (currentD30 != null && !currentD30.trim().isEmpty()) {
                                    nextD30Val = Integer.parseInt(currentD30.trim()) + 1;
                                }
                            } catch (NumberFormatException nfe) {
                                nextD30Val = 1;
                            }
                            updatedD30 = String.valueOf(nextD30Val);
                            tmpClone.setD30(updatedD30);
                        }

                        // Tạo DuLieuNTRow truyền đúng skye vào hàm khởi tạo
                        DuLieuNTRow tempadd = createDuLieuNTRow(tmpClone, year, dateFormat, skye);
                        tempadd.setKey(skye);
                        tempadd.setD30(updatedD30);

                        if ("1".equals(actionType)) {
                            tempadd.setD26("1");
                            tmpClone.setD26("1");
                        } else {
                            tempadd.setD26("0");
                            tmpClone.setD26("0");
                        }

                        // Nếu là key "02_QLNK" (chỉ chạy khi actionType = "1"), tạo mã ngẫu nhiên 32 ký tự riêng biệt
                        if ("02_QLNK".equals(skye)) {
                            String random32Code = generateUnique32CharsCode();
                            tempadd.setCode(random32Code);
                            tmpClone.setMA(random32Code);
                            tmpClone.setKHOA("02_QLNK");
                        } else {
                            tmpClone.setKHOA("01_QLNK");
                        }

                        lstUpdateDateForCurrentKey.add(tempadd);
                        Ngaybc = dateFormat.format(tmpClone.getNGAYBC());
                        lstLocalDataUpdateForCurrentKey.add(tmpClone);
                    }
                }

                _leaveHomeService = new Service_GQVL2023();
                int status = _leaveHomeService.saveQLNK(skye, pos_cd_username, "S", Ngaybc, "", "", lstUpdateDateForCurrentKey, "1");
                if (status == 200) {
                    String matoValue = hmParameter.get("mato").toString().split("_")[1];
                    if ("0000000".equals(matoValue)) {
                        matoValue = "";
                    }

                    String maxaValue = hmParameter.get("maxa").toString();
                    if ("000000".equals(maxaValue)) {
                        maxaValue = "";
                    }

                    if (!daoMain.saveQLNK2023_1(skye, UserName, Grade, hmParameter.get("ngay_bc").toString(), lstLocalDataUpdateForCurrentKey, pos_cd_username, maxaValue, matoValue)) {
                        addActionError("Bạn chưa lưu được báo cáo màn hình Nhập với key [" + skye + "], tại chi nhánh vui lòng liên hệ quản trị viên!!");
                        setPageResultCode("2");
                        return ERROR;
                    }
                } else {
                    addActionError("Lỗi hệ thống khi gọi API lưu key [" + skye + "]");
                    setPageResultCode("1");
                    return ERROR;
                }

            }

        } catch (Exception e) {
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            setPageResultCode("1");
            return ERROR;
        }

        addActionMessage(successMessage);
        setPageResultCode("200");
        return SUCCESS;
    }

    public String delete() {
        System.out.println("vao delete");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            HashMap hmParameter = getParameter();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
            String sngaybc = hmParameter.get("ngay_bc").toString();
            Date inputDate = dateFormat.parse(sngaybc);
            int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(inputDate));
            String localD2 = null;

            ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<DuLieuNTRow>();
            ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<QT_DULIEU_NT>();

            ArrayList<DuLieuNTRow> lstUpdateDate02 = new ArrayList<DuLieuNTRow>();
            ArrayList<QT_DULIEU_NT> lstLocalDataUpdate02 = new ArrayList<QT_DULIEU_NT>();

// Vòng lặp chỉ chạy 1 lần qua các dòng dữ liệu từ giao diện gửi lên
            for (QT_DULIEU_NT tmp : lstDulieuNt) {
                if (tmp.getD18() != null) {

                    // 1. Xử lý cho 01_QLNK (Dùng để xóa theo vòng lặp tháng)
                    DuLieuNTRow tempadd = createDuLieuNTRow(tmp, year, dateFormat, "01_QLNK");
                    lstUpdateDate.add(tempadd);
                    lstLocalDataUpdate.add(tmp);
                    localD2 = tmp.getD2();

                    // 2. Xử lý cho 02_QLNK (Mỗi dòng tmp chỉ sinh ra ĐÚNG 1 dòng tương ứng cho 02)
                    String currentD30 = tmp.getD30();
                    int nextD30Val = 0;
                    try {
                        if (currentD30 != null && !currentD30.trim().isEmpty()) {
                            nextD30Val = Integer.parseInt(currentD30.trim()) + 1;
                        }
                    } catch (NumberFormatException nfe) {
                        nextD30Val = 1;
                    }
                    String updatedD30 = String.valueOf(nextD30Val);

                    // Clone và gán giá trị mới cho 02
                    QT_DULIEU_NT tmp02 = cloneQTDuLieuNt(tmp);
                    tmp02.setKHOA("02_QLNK");
                    tmp02.setD30(updatedD30);
                    tmp02.setD26("0");

                    // Sinh mã 32 ký tự DUY NHẤT cho dòng này của 02
                    String random32Code = generateUnique32CharsCode();
                    tmp02.setMA(random32Code);

                    DuLieuNTRow tempadd02 = createDuLieuNTRow(tmp02, year, dateFormat, "02_QLNK");
                    tempadd02.setKey("02_QLNK");
                    tempadd02.setCode(random32Code);
                    tempadd02.setD30(updatedD30);
                    tempadd02.setD26("0");

                    // Add vào list của 02
                    lstUpdateDate02.add(tempadd02);
                    lstLocalDataUpdate02.add(tmp02);
                }
            }

            _leaveHomeService = new Service_GQVL2023();

            // Lấy ngày đầu tiên và cuối cùng của tháng để chạy vòng lặp cho 01_QLNK
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(inputDate);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Date startDate = calendar.getTime();

            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            Date endDate = calendar.getTime();

            calendar.setTime(startDate);
            while (calendar.getTime().compareTo(endDate) <= 0) {
                String ngay = dateFormat.format(calendar.getTime());

                int status = _leaveHomeService.deleteQLNK(pos_cd_username, "S", localD2, ngay, "", "", lstUpdateDate, "1");

                if (status == 200) {
                    if (!daoMain.deleteQLNK2023("01_QLNK", UserName, Grade, ngay, lstLocalDataUpdate, pos_cd_username, localD2)) {
                        addActionError("Bạn chưa xóa được dữ liệu màn hình Nhập, tại chi nhánh vui lòng liên hệ quản trị viên!!");
                        setPageResultCode("2");
                        return ERROR;
                    }
                } else {
                    addActionError("Lỗi hệ thống khi gọi API xóa");
                    setPageResultCode("1");
                    return ERROR;
                }
                calendar.add(Calendar.DATE, 1);
            }

            if (!lstUpdateDate02.isEmpty()) {
                int status02 = _leaveHomeService.saveQLNK("QLNK_02", pos_cd_username, "S", sngaybc, "", "", lstUpdateDate02, "1");
                if (status02 == 200) {
                    String matoValue = hmParameter.get("mato").toString().split("_")[1];
                    if ("0000000".equals(matoValue)) {
                        matoValue = "";
                    }
                    String maxaValue = hmParameter.get("maxa").toString();
                    if ("000000".equals(maxaValue)) {
                        maxaValue = "";
                    }

                    daoMain.saveQLNK2023_1("02_QLNK", UserName, Grade, sngaybc, lstLocalDataUpdate02, pos_cd_username, maxaValue, matoValue);
                }
            }
        } catch (Exception e) {
            addActionError("Bạn chưa xóa được dữ liệu xin liên hệ với quản trị để khắc phục");
            setPageResultCode("1");
            return ERROR;
        }

        addActionMessage("Bạn đã xóa và cập nhật dữ liệu thành công");
        setPageResultCode("200");
        return SUCCESS;
    }

    private String generateUnique32CharsCode() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private QT_DULIEU_NT mapToQTDuLieuNt(DuLieuNTRow item, int iStt) {
        QT_DULIEU_NT row = new QT_DULIEU_NT();
        row.setKHOA(item.getKey());
        row.setTHUTU(iStt);
        row.setTT_HIENTHI(item.getOrderDescription());
        row.setMA(item.getCode());
        row.setTEN(item.getName());
        row.setNGAYBC(DateUtil.toDate(item.getReportDate()));
        row.setNAMBC(item.getReportYear());
        row.setMAPGD(item.getPosCode());
        row.setCO_TONGHOP(item.getPosFlag());
        row.setMACN(item.getBranchCode());
        row.setNGUOI_NHAP(item.getMakerId());
        row.setNGAY_NHAP(DateUtil.toDate(item.getMakerDate()));
        row.setNGUOI_DUYET(item.getAuthoriseId());
        row.setNGAY_DUYET(DateUtil.toDate(item.getAuthoriseDate()));

        setDFieldsToRow(row, item);
        row.setNHAPTAY(item.getManualFlag());
        row.setFONTFORMAT(item.getFontFormat());
        row.setKIEUIN(item.getStyle());
        return row;
    }

    private QT_DULIEU_NT cloneQTDuLieuNt(QT_DULIEU_NT src) {
        QT_DULIEU_NT row = new QT_DULIEU_NT();
        row.setKHOA(src.getKHOA());
        row.setTHUTU(src.getTHUTU());
        row.setTT_HIENTHI(src.getTT_HIENTHI());
        row.setMA(src.getMA());
        row.setTEN(src.getTEN());
        row.setNGAYBC(src.getNGAYBC());
        row.setNAMBC(src.getNAMBC());
        row.setMAPGD(src.getMAPGD());
        row.setCO_TONGHOP(src.getCO_TONGHOP());
        row.setMACN(src.getMACN());
        row.setNGUOI_NHAP(src.getNGUOI_NHAP());
        row.setNGAY_NHAP(src.getNGAY_NHAP());
        row.setNGUOI_DUYET(src.getNGUOI_DUYET());
        row.setNGAY_DUYET(src.getNGAY_DUYET());
        row.setD1(src.getD1());
        row.setD2(src.getD2());
        row.setD3(src.getD3());
        row.setD4(src.getD4());
        row.setD5(src.getD5());
        row.setD6(src.getD6());
        row.setD7(src.getD7());
        row.setD8(src.getD8());
        row.setD9(src.getD9());
        row.setD10(src.getD10());
        row.setD11(src.getD11());
        row.setD12(src.getD12());
        row.setD13(src.getD13());
        row.setD14(src.getD14());
        row.setD15(src.getD15());
        row.setD16(src.getD16());
        row.setD17(src.getD17());
        row.setD18(src.getD18());
        row.setD19(src.getD19());
        row.setD20(src.getD20());
        row.setD21(src.getD21());
        row.setD22(src.getD22());
        row.setD23(src.getD23());
        row.setD24(src.getD24());
        row.setD25(src.getD25());
        row.setD26(src.getD26());
        row.setD27(src.getD27());
        row.setD28(src.getD28());
        row.setD29(src.getD29());
        row.setD30(src.getD30());
        row.setNHAPTAY(src.getNHAPTAY());
        row.setFONTFORMAT(src.getFONTFORMAT());
        row.setKIEUIN(src.getKIEUIN());
        return row;
    }

    private DuLieuNTRow createDuLieuNTRow(QT_DULIEU_NT tmp, int year, SimpleDateFormat dateFormat, String key) {
        DuLieuNTRow tempadd = new DuLieuNTRow();
        tempadd.setKey(key); // Nhận trực tiếp key truyền vào thay vì fix cứng
        tempadd.setOrderValue("");
        tempadd.setOrderDescription("");
        tempadd.setCode(tmp.getMA());
        tempadd.setMakerId(UserName);
        tempadd.setAuthoriseId(UserName);
        tempadd.setReportDate(dateFormat.format(tmp.getNGAYBC()));
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
        tempadd.setD8(tmp.getD8() != null && !tmp.getD8().isEmpty() ? tmp.getD8() : "0");
        tempadd.setD9(tmp.getD9() != null && !tmp.getD9().isEmpty() ? tmp.getD9() : "0");
        tempadd.setD10(tmp.getD10());
        tempadd.setD11(tmp.getD11());
        tempadd.setD12(tmp.getD12());
        tempadd.setD13(tmp.getD13());
        tempadd.setD14(tmp.getD14() != null && !tmp.getD14().isEmpty() ? tmp.getD14() : "0");
        tempadd.setD15(tmp.getD15() != null && !tmp.getD15().isEmpty() ? tmp.getD15() : "0");
        tempadd.setD16(tmp.getD16());
        tempadd.setD17(tmp.getD17());
        tempadd.setD19(tmp.getD19());
        tempadd.setD20(tmp.getD20());
        tempadd.setD21(tmp.getD21());
        tempadd.setD22(tmp.getD22());
        tempadd.setD23(tmp.getD23());
        tempadd.setD24(tmp.getD24());
        tempadd.setD25(tmp.getD25());
        tempadd.setD26(tmp.getD26() != null && !tmp.getD26().isEmpty() ? tmp.getD26() : "0");
        tempadd.setD27(tmp.getD27() != null && !tmp.getD27().isEmpty() ? tmp.getD27() : "0");
        tempadd.setD28(tmp.getD28() != null && !tmp.getD28().isEmpty() ? tmp.getD28() : "0");
        tempadd.setD29(tmp.getD29());
        tempadd.setD30(tmp.getD30());
        return tempadd;
    }

    private void setDFieldsToRow(QT_DULIEU_NT row, DuLieuNTRow item) {
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
    }

    private void setPageResultCode(String code) {
        this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
    }

    private void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                // Ignore
            }
        }
    }
}
