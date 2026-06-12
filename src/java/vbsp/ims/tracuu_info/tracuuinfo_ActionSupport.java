/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.tracuu_info;

import com.opensymphony.xwork2.ActionSupport;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.restapi.CustomerBlackList;
import vbsp.ims.restapi.DuLieuNTService;

/**
 *
 * @author Administrator
 */
public class tracuuinfo_ActionSupport extends ActionSupport {

    private List<tracuuinfo_dndm> lsinfo;
    private String gentable;
    private String loaitc;
    private List ListDK;
    private List ListVal;
    private List<tracuuinfo_listgt> listgt;
    private String chkexcel;
    private List<CustomerBlackList> lstCustomerBlackList;
    DuLieuNTService _serverAPI = new DuLieuNTService();
    private InputStream excelStream;
    private String maBc;
    private String tenBc;

    public String getMaBc() {
        return maBc;
    }

    public void setMaBc(String maBc) {
        this.maBc = maBc;
    }

    public String getTenBc() {
        return tenBc;
    }

    public void setTenBc(String tenBc) {
        this.tenBc = tenBc;
    }

    public InputStream getExcelStream() {
        return excelStream;
    }

    public tracuuinfo_ActionSupport() {
    }

    @Override
    public String execute() throws Exception {
        // Thực hiện lấy dữ liệu từ bảng định nghĩ loại tra cứu : IMS_DMINFO
        lsinfo = new tracuuinfo_model().get_tracuu_info();
        return "thanhcong";
    }

    public String GenTable() throws SQLException {
        gentable = new tracuuinfo_model().get_tracuu_control(loaitc);
        return "thanhcong";
    }

    public String ShowList() throws SQLException {
        //Thực hiện lấy toàn bộ các trường ra List
        Connection conn = null;
        conn = new DaoConnect().getConnect();
        Integer maxitem = ListDK.size();
        String dieukien = "";

        if (loaitc.equals("DTTN")) {
            for (int i = 0; i < maxitem; i++) {
                dieukien = dieukien + ListVal.get(i) + "$";
            }
            String[] values = dieukien != null ? dieukien.split("\\$") : new String[0];
            String v0 = values.length > 0 && !values[0].trim().isEmpty()
                    ? values[0].trim().toUpperCase()
                    : null;
            String v1 = values.length > 1 && !values[1].trim().isEmpty()
                    ? values[1].trim().toUpperCase()
                    : null;
            String kh_cmt = v0 != null ? v0 : v1;
//            System.out.println("v0 = " + v0 + ", v1 = " + v1);
            System.out.println("kh_cmt truyền API = " + kh_cmt);
            if (kh_cmt != null) {
                List<CustomerBlackList> srcList = _serverAPI.getCustomerBlackList(kh_cmt);

                System.out.println("data = " + srcList.size());

                List<CustomerBlackList> resultList = new ArrayList<>();

                for (CustomerBlackList item : srcList) {
                    CustomerBlackList row = convertPLN_T(item);
                    resultList.add(row);
                }

                lstCustomerBlackList = resultList;

            } else {
                System.out.println("Không có điều kiện hợp lệ để gọi API");
            }
            return "Apithanhcong";
        } else {
            for (int i = 0; i < maxitem; i++) {
                dieukien = dieukien + ListVal.get(i) + "/TV/";
            }
            dieukien = dieukien.substring(0, dieukien.length() - 4);
            if (chkexcel == null) {
                chkexcel = "OFF";
            }
            listgt = null;
            listgt = new tracuuinfo_model().get_query_info(dieukien, loaitc, chkexcel.toUpperCase());
            maBc = null;
            tenBc = null;

            String sql = "SELECT MADM, TENDM FROM IMS_DMINFO WHERE MADM = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, loaitc);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                maBc = rs.getString("MADM");
                tenBc = rs.getString("TENDM");
            }
            rs.close();
            ps.close();
            return loaitc.equals("KHVV") || loaitc.equals("KHUQ") ? "thanhcong2026" : "thanhcong";
        }
    }

    private CustomerBlackList convertPLN_T(CustomerBlackList item) {
        CustomerBlackList row = new CustomerBlackList();

        if (item == null) {
            return row;
        }
        try {
            row.setHoVaTen(item.getHoVaTen());
            row.setNgaySinhh(item.getNgaySinhh());
            row.setCccdHoChieu(item.getCccdHoChieu());
            row.setNoiDkyHktt(item.getNoiDkyHktt());
            row.setNoiO(item.getNoiO());
            row.setCmtNgayCap(item.getCmtNgayCap());
            row.setCmtNoiCap(item.getCmtNoiCap());
            row.setHcNgayCap(item.getHcNgayCap());
            row.setHcNoiCap(item.getHcNoiCap());
            row.setToiDanh(item.getToiDanh());
            row.setHoTenBo(item.getHoTenBo());
            row.setHoTenMe(item.getHoTenMe());
            row.setSoQdtn(item.getSoQdtn());
            row.setNgayRaQdtn(item.getNgayRaQdtn());
            row.setDviCap2RaQdtn(item.getDviCap2RaQdtn());
            row.setLoaiTn(item.getLoaiTn());
            row.setHoTenNoAccent(item.getHoTenNoAccent());
            row.setBiDanh(item.getBiDanh());
            row.setGioiTinh(item.getGioiTinh());
            row.setDanToc(item.getDanToc());
            row.setTonGiao(item.getTonGiao());
            row.setQuocTich(item.getQuocTich());
            row.setNoiSinh(item.getNoiSinh());
            row.setQueQuan(item.getQueQuan());
            row.setChucVu(item.getChucVu());
            row.setThongTinKhac(item.getThongTinKhac());
            row.setToChucKhungBo(item.getToChucKhungBo());
            row.setChucVu2(item.getChucVu2());
            row.setChucVu3(item.getChucVu3());
            row.setPobBlock(item.getPobBlock());
            row.setThongTinNhap(item.getThongTinNhap());
            row.setNguon(item.getNguon());

        } catch (Exception e) {
        }
        return row;
    }

    public String exportExcel() throws Exception {

        // Nếu chưa có dữ liệu thì gọi lại ShowList
        if (lstCustomerBlackList == null || lstCustomerBlackList.isEmpty()) {
            ShowList();
        }

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("BLACK_LIST");

        CellStyle headerStyle = wb.createCellStyle();
        Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        headerStyle.setWrapText(true);
        setBorder(headerStyle);

        CellStyle dataStyle = wb.createCellStyle();
        dataStyle.setAlignment(CellStyle.ALIGN_CENTER);
        dataStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        dataStyle.setWrapText(true);
        setBorder(dataStyle);
        dataStyle.setDataFormat(wb.createDataFormat().getFormat("@")); // ép text

        String[] headers = {"HỌ TÊN", "NGÀY SINH", "NƠI ĐK HKTT", "CCCD", "HỘ CHIẾU", "TỘI DANH", "TÊN BỐ", "TÊN MẸ", "SỐ QĐ", "NGÀY QĐ", "ĐƠN VỊ", "LOẠI TN", "TÊN KO DẤU", "BÍ DANH", "GIỚI TÍNH", "DÂN TỘC", "TÔN GIÁO", "QUỐC TỊCH", "NƠI SINH", "QUÊ QUÁN", "CHỨC VỤ", "THÔNG TIN KHÁC", "TỔ CHỨC KHỦNG BỐ", "CHỨC VỤ 2", "CHỨC VỤ 3", "POB BLOCK", "THÔNG TIN NHẬP", "NGUỒN", "CMT NGÀY CẤP", "CMT NƠI CẤP", "HC NGÀY CẤP", "HC NƠI CẤP"};

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        int rowIdx = 1;
        for (CustomerBlackList c : lstCustomerBlackList) {
            Row row = sheet.createRow(rowIdx++);
            row.setHeight((short) -1); // auto height
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            createCell(row, 0, c.getHoVaTen(), dataStyle);
            createCell(row, 1, c.getNgaySinhh(), dataStyle);
            createCell(row, 2, c.getCccdHoChieu(), dataStyle);
            createCell(row, 3, c.getNoiDkyHktt(), dataStyle);
            createCell(row, 4, c.getNoiO(), dataStyle);

            createCell(row, 5, c.getCmtNgayCap() != null ? sdf.format(c.getCmtNgayCap()) : "", dataStyle);
            createCell(row, 6, c.getCmtNoiCap(), dataStyle);
            createCell(row, 7, c.getHcNgayCap() != null ? sdf.format(c.getHcNgayCap()) : "", dataStyle);
            createCell(row, 8, c.getHcNoiCap(), dataStyle);
            createCell(row, 9, c.getToiDanh(), dataStyle);
            createCell(row, 10, c.getHoTenBo(), dataStyle);
            createCell(row, 11, c.getHoTenMe(), dataStyle);
            createCell(row, 12, c.getSoQdtn(), dataStyle);
            createCell(row, 13, c.getNgayRaQdtn(), dataStyle);
            createCell(row, 14, c.getDviCap2RaQdtn(), dataStyle);
            createCell(row, 15, c.getLoaiTn(), dataStyle);
            createCell(row, 16, c.getHoTenNoAccent(), dataStyle);
            createCell(row, 17, c.getBiDanh(), dataStyle);
            createCell(row, 18, c.getGioiTinh(), dataStyle);
            createCell(row, 19, c.getDanToc(), dataStyle);
            createCell(row, 20, c.getTonGiao(), dataStyle);
            createCell(row, 21, c.getQuocTich(), dataStyle);
            createCell(row, 22, c.getNoiSinh(), dataStyle);
            createCell(row, 23, c.getQueQuan(), dataStyle);
            createCell(row, 24, c.getChucVu(), dataStyle);
            createCell(row, 25, c.getThongTinKhac(), dataStyle);
            createCell(row, 26, c.getToChucKhungBo(), dataStyle);
            createCell(row, 27, c.getChucVu2(), dataStyle);
            createCell(row, 28, c.getChucVu3(), dataStyle);
            createCell(row, 29, c.getPobBlock(), dataStyle);
            createCell(row, 30, c.getThongTinNhap(), dataStyle);
            createCell(row, 31, c.getNguon(), dataStyle);
        }
        int MAX_WIDTH = 50 * 256; // 50 ký tự

        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);                 // tự giãn theo dữ liệu
            int currentWidth = sheet.getColumnWidth(i);
            if (currentWidth > MAX_WIDTH) {          // nếu quá 50
                sheet.setColumnWidth(i, MAX_WIDTH);  // ép về 50
            }
        }

        sheet.protectSheet("12345678");

        HttpServletResponse response = ServletActionContext.getResponse();
        response.reset();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        String fileName;
        if ("DTTN".equals(loaitc)) {
            fileName = "BLACKLIST_DTTN_" + System.currentTimeMillis() + ".xlsx";
        } else {
            fileName = "BLACKLIST_" + loaitc + "_" + System.currentTimeMillis() + ".xlsx";
        }

        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + fileName + "\"");

        OutputStream out = response.getOutputStream();
        wb.write(out);
        out.flush();
        out.close();
        wb.close();

        return null;
    }

    private void setBorder(CellStyle style) {
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
    }

    private void createCell(Row row, int col, String val, CellStyle style) {
        Cell cell = row.createCell(col);
        cell.setCellValue(val == null ? "" : val);
        cell.setCellStyle(style);
    }

//<editor-fold defaultstate="collapsed" desc="Thực thể">
    public List<CustomerBlackList> getLstCustomerBlackList() {
        return lstCustomerBlackList;
    }

    public void setLstCustomerBlackList(List<CustomerBlackList> lstCustomerBlackList) {
        this.lstCustomerBlackList = lstCustomerBlackList;
    }

    public List<tracuuinfo_dndm> getLsinfo() {
        return lsinfo;
    }

    public void setLsinfo(List<tracuuinfo_dndm> lsinfo) {
        this.lsinfo = lsinfo;
    }

    public String getGentable() {
        return gentable;
    }

    public void setGentable(String gentable) {
        this.gentable = gentable;
    }

    public String getLoaitc() {
        return loaitc;
    }

    public void setLoaitc(String loaitc) {
        this.loaitc = loaitc;
    }

    public List getListDK() {
        return ListDK;
    }

    public void setListDK(List ListDK) {
        this.ListDK = ListDK;
    }

    public List getListVal() {
        return ListVal;
    }

    public void setListVal(List ListVal) {
        this.ListVal = ListVal;
    }

    public List<tracuuinfo_listgt> getListgt() {
        return listgt;
    }

    public void setListgt(List<tracuuinfo_listgt> listgt) {
        this.listgt = listgt;
    }

    public String getChkexcel() {
        return chkexcel;
    }

    public void setChkexcel(String chkexcel) {
        this.chkexcel = chkexcel;
    }

//</editor-fold>   
}
