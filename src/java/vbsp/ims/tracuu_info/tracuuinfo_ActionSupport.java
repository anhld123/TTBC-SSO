/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.tracuu_info;

import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
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
            return "thanhcong";
        }
    }

    private CustomerBlackList convertPLN_T(CustomerBlackList item) {
        CustomerBlackList row = new CustomerBlackList();

        if (item == null) {
            return row;
        }
        try {
            row.setOrder(item.getOrder());
            row.setFullName(item.getFullName());
            row.setBirthDate(item.getBirthDate());
            row.setBirthMonth(item.getBirthMonth());
            row.setBirthYear(item.getBirthYear());
            row.setPermanentAddress(item.getPermanentAddress());
            row.setCurrentAddress(item.getCurrentAddress());
            row.setIdNumber(item.getIdNumber());
            row.setIssueDate(item.getIssueDate());
            row.setIssuePlace(item.getIssuePlace());
            row.setPassportId(item.getPassportId());
            row.setPassportIssueDate(item.getPassportIssueDate());
            row.setPassportIssuePlace(item.getPassportIssuePlace());
            row.setCriminalOffense(item.getCriminalOffense());
            row.setFatherName(item.getFatherName());
            row.setMotherName(item.getMotherName());
            row.setDecisionNo(item.getDecisionNo());
            row.setDecisionDate(item.getDecisionDate());
            row.setDecisionPlace(item.getDecisionPlace());
            row.setOffenseType(item.getOffenseType());
            row.setFullNameNoAccent(item.getFullNameNoAccent());

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

        String[] headers = {
            "HỌ TÊN", "NGÀY SINH", "NƠI ĐK HKTT", "CCCD",
            "HỘ CHIẾU", "TỘI DANH", "TÊN BỐ", "TÊN MẸ",
            "SỐ QĐ", "NGÀY QĐ", "ĐƠN VỊ", "LOẠI TN", "TÊN KO DẤU"
        };

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

            createCell(row, 0, c.getFullName(), dataStyle);
            createCell(row, 1, c.getBirthDate() + "/" + c.getBirthMonth() + "/" + c.getBirthYear(), dataStyle);
            createCell(row, 2, c.getPermanentAddress(), dataStyle);
            createCell(row, 3, c.getIdNumber(), dataStyle);
            createCell(row, 4, c.getPassportId(), dataStyle);
            createCell(row, 5, c.getCriminalOffense(), dataStyle);
            createCell(row, 6, c.getFatherName(), dataStyle);
            createCell(row, 7, c.getMotherName(), dataStyle);
            createCell(row, 8, c.getDecisionNo(), dataStyle);
            createCell(row, 9, c.getDecisionDate(), dataStyle);
            createCell(row, 10, c.getDecisionPlace(), dataStyle);
            createCell(row, 11, c.getOffenseType(), dataStyle);
            createCell(row, 12, c.getFullNameNoAccent(), dataStyle);
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
