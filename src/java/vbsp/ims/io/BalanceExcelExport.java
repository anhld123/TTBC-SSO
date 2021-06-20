/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.model.Account;
import vbsp.ims.model.GLAccount;
import vbsp.ims.model.SbvAccount;

/**
 *
 * @author Trung
 */
public class BalanceExcelExport {

    public static final int CLOBBUFFERSIZE = 2048;
    public static final int V_STARTPOINT = 6;

    private void setCellContent(HSSFSheet sheet, int x, int y, String content, int type) {
        HSSFRow row;
        HSSFCell cell;
        row = sheet.getRow(x);
        cell = row.getCell(y);
        switch (type) {
            case 1: // String 
                if (cell == null) {
                    cell = HSSFCellUtil.createCell(row, y, content);
                }
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(content);
                break;
            case 2: // Number
                break;
            case 3: // Date
                break;
        }
    }

    public boolean ToExcel(String strFileName, String report_dt, String report_period,
            String report_type, List<Account> accounts)
            throws FileNotFoundException, IOException, InvalidFormatException, SQLException, ParseException {
        //Mo file excel
        String fullpath = Define.M_ROOT + Define.M_EXCEL_TEMP;

        if (report_type.equals("01") || report_type.equals("02")) {
            fullpath = fullpath + "TEMP_SBV.XLS";
        } else {
            fullpath = fullpath + "TEMP_GL.XLS";
        }
        InputStream input = new FileInputStream(fullpath);
        HSSFWorkbook workbook = new HSSFWorkbook(input);
//        Workbook excelBalance =  WorkbookFactory.create(input);
        HSSFSheet sheet = workbook.getSheetAt(0); //excelBalance.getSheetAt(0);

        int rowindex = V_STARTPOINT;
        int colindex;

        /* Phan ghi tieu de ngay thang */
        setCellContent(sheet, 0, 0, "NGÂN HÀNG CHÍNH SÁCH XÃ HỘI", 1);

        Row row;
        Cell cell;

        String dtStemp = "";

        switch (report_period) {
            case "D":
                dtStemp = "Ngày " + DefineFun.convertStrDateFormat(report_dt, "dd-MMM-yyyy", "dd/MM/yyyy");
                break;
            case "M":
                dtStemp = "Từ " + DefineFun.getFirstDayOfMonth(report_dt, "dd-MMM-yyyy", "dd/MM/yyyy")
                        + " đến " + DefineFun.convertStrDateFormat(report_dt, "dd-MMM-yyyy", "dd/MM/yyyy");
                break;
            case "Q":
                dtStemp = "Từ " + DefineFun.getFirstDayOfQuater(report_dt, "dd-MMM-yyyy", "dd/MM/yyyy")
                        + " đến " + DefineFun.convertStrDateFormat(report_dt, "dd-MMM-yyyy", "dd/MM/yyyy");
                break;
            case "Y":
                dtStemp = "Từ " + DefineFun.getFirstDayOfYear(report_dt, "dd-MMM-yyyy", "dd/MM/yyyy")
                        + " đến " + DefineFun.convertStrDateFormat(report_dt, "dd-MMM-yyyy", "dd/MM/yyyy");
                break;
        }

        setCellContent(sheet, 2, 0, dtStemp, 1);
//        row = sheet.getRow(2);
//        cell = row.getCell(0);
//        cell.setCellType(Cell.CELL_TYPE_STRING);
//        cell.setCellValue(dtStemp);
        /**/
        
        CellStyle numberStyle = workbook.createCellStyle();//Create style        
        DataFormat format = workbook.createDataFormat();
        numberStyle.setDataFormat(format.getFormat("#,##0"));
        
        HSSFFont hSSFFont = workbook.createFont();
        hSSFFont.setFontName("Times New Roman");
        hSSFFont.setFontHeightInPoints((short) 11);
        
        numberStyle.setFont(hSSFFont);
        
        if (report_type.equals("01") || report_type.equals("02")) {
            for (Account account : accounts) {
                if (account != null) {
                    row = sheet.createRow(rowindex);
                    if (account.getAccount_code().trim().length() == 1
                            || account.getAccount_code().trim().length() == 2
                            || account.getAccount_code().trim().length() == 3) {
                        makeRowBold(workbook, sheet, row, account.getAccount_code().trim().length());
                    }
                    try {
                        colindex = 0;
                        for (int j = 0; j < 9; j++) {                            
                            cell = row.createCell(colindex);
                            switch (colindex) {
                                case 0:
                                    cell.setCellType(Cell.CELL_TYPE_STRING);
                                    cell.setCellValue(account.getPos_code());
                                    break;
                                case 1:
                                    cell.setCellType(Cell.CELL_TYPE_STRING);
                                    cell.setCellValue(account.getAccount_code());
                                    break;
                                case 2:
                                    cell.setCellType(Cell.CELL_TYPE_STRING);
                                    cell.setCellValue(account.getAccount_descript());
                                    break;
                                case 3:
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Double.parseDouble(account.getOpen_debit().toString()));
                                    break;
                                case 4:
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Double.parseDouble(account.getOpen_credit().toString()));
                                    cell.setCellStyle(numberStyle);
                                    break;
                                case 5:
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Double.parseDouble(account.getTurn_debit().toString()));
                                    cell.setCellStyle(numberStyle);
                                    break;
                                case 6:
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Double.parseDouble(account.getTurn_credit().toString()));
                                    cell.setCellStyle(numberStyle);
                                    break;
                                case 7:
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Double.parseDouble(account.getClose_debit().toString()));
                                    cell.setCellStyle(numberStyle);
                                    break;
                                case 8:
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Double.parseDouble(account.getClose_credit().toString()));
                                    cell.setCellStyle(numberStyle);
                                    break;
                            }

                            colindex++;
                        }
                    } catch (Exception ex) {
                        System.err.println("Error dong: " + rowindex + "-->" + ex.getMessage());
                    }
                    rowindex++;
                }
            }
        } else {
            for (Account account : accounts) {
//                if (account != null) {
                try {
                    row = sheet.createRow(rowindex);
//                    System.err.println("Print dong: " + rowindex );
                    if (null == account.getAccount_code()
                            || 4 >= account.getAccount_code().trim().length()) {
                        if (null == account.getAccount_code()) {
                            makeRowBold(workbook, sheet, row,
                                    getMappingStyle(0));
                        } else {
                            makeRowBold(workbook, sheet, row,
                                    getMappingStyle(account.getAccount_code().trim().length()));
                        }
                    }
                    try {
                        GLAccount glAccount = (GLAccount) account;
                        colindex = 0;
                        for (int j = 0; j < 11; j++) {
                            cell = row.createCell(colindex);
                            switch (colindex) {
                                case 0:
                                    cell.setCellType(Cell.CELL_TYPE_STRING);
                                    cell.setCellValue(glAccount.getPos_code());
                                    break;
                                case 1:
                                    cell.setCellType(Cell.CELL_TYPE_STRING);
                                    cell.setCellValue(glAccount.getAccount_code());
                                    break;
                                case 2:
                                    cell.setCellType(Cell.CELL_TYPE_STRING);
                                    cell.setCellValue(glAccount.getGl_Sl());
                                    break;
                                case 3:
                                    cell.setCellType(Cell.CELL_TYPE_STRING);
                                    cell.setCellValue(glAccount.getAccount_descript());
                                    break;
                                case 4:
                                    cell.setCellType(Cell.CELL_TYPE_STRING);
                                    cell.setCellValue(glAccount.getGl_Sbv());
                                    break;
                                case 5:
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Double.parseDouble(glAccount.getOpen_debit().toString()));
                                    cell.setCellStyle(numberStyle);
                                    break;
                                case 6:
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Double.parseDouble(glAccount.getOpen_credit().toString()));
                                    cell.setCellStyle(numberStyle);
                                    break;
                                case 7:
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Double.parseDouble(glAccount.getTurn_debit().toString()));
                                    cell.setCellStyle(numberStyle);
                                    break;
                                case 8:
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Double.parseDouble(glAccount.getTurn_credit().toString()));
                                    cell.setCellStyle(numberStyle);
                                    break;
                                case 9:
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Double.parseDouble(glAccount.getClose_debit().toString()));
                                    cell.setCellStyle(numberStyle);
                                    break;
                                case 10:
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Double.parseDouble(account.getClose_credit().toString()));
                                    cell.setCellStyle(numberStyle);
                                    break;
                            }
                            colindex++;
                        }
                    } catch (Exception ex) {
                        System.err.println("Error dong: " + rowindex + "-->" + ex.getMessage());
                    }
                    rowindex++;
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
//            }
        }

        FileOutputStream out = new FileOutputStream(strFileName);
        workbook.write(out);

        return true;
    }

    public boolean ToExcel(String strFileName, String report_dt, String report_period,
            String report_type, ResultSet rs)
            throws FileNotFoundException,
            IOException, InvalidFormatException, SQLException, ParseException {
        boolean isSuccess = false;
        //Mo file excel
        String fullpath = Define.M_ROOT + Define.M_EXCEL_TEMP;

        if (report_type.equals("01") || report_type.equals("02")) {
            fullpath = fullpath + "TEMP_SBV.XLS";
        } else {
            fullpath = fullpath + "TEMP_GL.XLS";
        }
        InputStream input = new FileInputStream(fullpath);
//        Workbook excelBalance = WorkbookFactory.create(input);
//        Sheet sheet = excelBalance.getSheetAt(0);
        HSSFWorkbook workbook = new HSSFWorkbook(input);
//        Workbook excelBalance =  WorkbookFactory.create(input);
        HSSFSheet sheet = workbook.getSheetAt(0); //excelBalance.getSheetAt(0);

        int rowindex = V_STARTPOINT;
        int colindex;

        /* Phần định dạng số */
        CellStyle numberStyle = workbook.createCellStyle();//Create style        
        DataFormat format = workbook.createDataFormat();
        numberStyle.setDataFormat(format.getFormat("#,##0"));

        setCellContent(sheet, 0, 0, "NGÂN HÀNG CHÍNH SÁCH XÃ HỘI", 1);

        /* Phan ghi tieu de ngay thang */
        Row row;
        Cell cell;

        String dtStemp = "";

        switch (report_period) {
            case "D":
                dtStemp = "Ngày " + DefineFun.convertStrDateFormat(report_dt, "dd-MMM-yyyy", "dd/MM/yyyy");
                break;
            case "M":
                dtStemp = "Từ " + DefineFun.getFirstDayOfMonth(report_dt, "dd-MMM-yyyy", "dd/MM/yyyy")
                        + " đến " + DefineFun.convertStrDateFormat(report_dt, "dd-MMM-yyyy", "dd/MM/yyyy");
                break;
            case "Q":
                dtStemp = "Từ " + DefineFun.getFirstDayOfQuater(report_dt, "dd-MMM-yyyy", "dd/MM/yyyy")
                        + " đến " + DefineFun.convertStrDateFormat(report_dt, "dd-MMM-yyyy", "dd/MM/yyyy");
                break;
            case "Y":
                dtStemp = "Từ " + DefineFun.getFirstDayOfYear(report_dt, "dd-MMM-yyyy", "dd/MM/yyyy")
                        + " đến " + DefineFun.convertStrDateFormat(report_dt, "dd-MMM-yyyy", "dd/MM/yyyy");
                break;
        }

//        row = sheet.getRow(2);
//        cell = row.getCell(0);
//        cell.setCellType(Cell.CELL_TYPE_STRING);
//        cell.setCellValue(dtStemp);
        setCellContent(sheet, 2, 0, dtStemp, 1);
        /**/
        if (report_type.equals("01") || report_type.equals("02")) {
            String lcPos_cd, lcaccount_code, lcaccount_descript, lcCcyCode;
            BigDecimal lbopen_debit, lbopen_credit, lbturn_debit, lbturn_credit, lbclose_debit, lbclose_credit;
            while (rs.next()) {
                Account account = null;
                lcPos_cd = rs.getString("POS_CD");
                lcaccount_code = rs.getString("TK");
                lcaccount_descript = rs.getString("TENTK");
                lcCcyCode = "VND";
                lbopen_debit = rs.getBigDecimal("DDNO");
                lbopen_credit = rs.getBigDecimal("DDCO");
                lbturn_debit = rs.getBigDecimal("PSNO");
                lbturn_credit = rs.getBigDecimal("PSCO");
                lbclose_debit = rs.getBigDecimal("DCNO");
                lbclose_credit = rs.getBigDecimal("DCCO");
                if (lcaccount_code.length() == 4) {
                    account = new SbvAccount(lcPos_cd, lcaccount_code, lcaccount_descript,
                            lcCcyCode, lbopen_debit, lbopen_credit,
                            lbturn_debit, lbturn_credit, lbclose_debit, lbclose_credit);
                }

                if (account != null) {
                    row = sheet.createRow(rowindex);
                    try {
                        colindex = 0;
                        for (int j = 0; j < 9; j++) {
                            cell = row.createCell(colindex);
                            switch (colindex) {
                                case 0:
                                    cell.setCellType(Cell.CELL_TYPE_STRING);
                                    cell.setCellValue(account.getPos_code());
                                    break;
                                case 1:
                                    cell.setCellType(Cell.CELL_TYPE_STRING);
                                    cell.setCellValue(account.getAccount_code());
                                    break;
                                case 2:
                                    cell.setCellType(Cell.CELL_TYPE_STRING);
                                    cell.setCellValue(account.getAccount_descript());
                                    break;
                                case 3:
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Double.parseDouble(account.getOpen_debit().toString()));
                                    cell.setCellStyle(numberStyle);
                                    break;
                                case 4:
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Double.parseDouble(account.getOpen_credit().toString()));
                                    cell.setCellStyle(numberStyle);
                                    break;
                                case 5:
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Double.parseDouble(account.getTurn_debit().toString()));
                                    cell.setCellStyle(numberStyle);
                                    break;
                                case 6:
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Double.parseDouble(account.getTurn_credit().toString()));
                                    cell.setCellStyle(numberStyle);
                                    break;
                                case 7:
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Double.parseDouble(account.getClose_debit().toString()));
                                    cell.setCellStyle(numberStyle);
                                    break;
                                case 8:
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Double.parseDouble(account.getClose_credit().toString()));
                                    cell.setCellStyle(numberStyle);
                                    break;
                            }

                            colindex++;
                        }
                        if (account.getAccount_code().trim().length() == 1
                                || account.getAccount_code().trim().length() == 2
                                || account.getAccount_code().trim().length() == 3) {
                            makeRowBold(workbook, sheet, row, account.getAccount_code().trim().length());
                        }
                    } catch (Exception ex) {
                        System.err.println("Error dong: " + rowindex + "-->" + ex.getMessage());
                    }
                    rowindex++;
                }
            }
        } else {
            String lcPos_cd, lcgl_Sl, lcgl_Ccy, lcgl_Sbv, lcaccount_code, lcaccount_descript;
            BigDecimal lbopen_debit, lbopen_credit, lbturn_debit, lbturn_credit, lbclose_debit, lbclose_credit;
            Account account;
//            int lstyle = 4;
            while (rs.next()) {
                try {
                    lcPos_cd = rs.getString("POS_CD");
                    lcaccount_code = rs.getString("TK");
                    lcaccount_descript = rs.getString("TENTK");
                    lcgl_Sl = rs.getString("GL_SL");
                    lcgl_Ccy = rs.getString("LOAITIEN");
                    lcgl_Sbv = rs.getString("TKCAP3");
                    lbopen_debit = rs.getBigDecimal("DDNO");
                    lbopen_credit = rs.getBigDecimal("DDCO");
                    lbturn_debit = rs.getBigDecimal("PSNO");
                    lbturn_credit = rs.getBigDecimal("PSCO");
                    lbclose_debit = rs.getBigDecimal("DCNO");
                    lbclose_credit = rs.getBigDecimal("DCCO");
//                lstyle = rs.getInt("STYLE");
                    account = new GLAccount(lcPos_cd, lcaccount_code, lcaccount_descript,
                            lcgl_Sl, lcgl_Ccy, lcgl_Sbv, lbopen_debit, lbopen_credit,
                            lbturn_debit, lbturn_credit, lbclose_debit, lbclose_credit);

                    row = sheet.createRow(rowindex);//sheet.getRow(rowindex);                                    
                    GLAccount glAccount = (GLAccount) account;
                    colindex = 0;
                    for (int j = 0; j < 11; j++) {
                        cell = row.createCell(colindex);
                        switch (colindex) {
                            case 0:
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                cell.setCellValue(glAccount.getPos_code());
                                break;
                            case 1:
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                cell.setCellValue(glAccount.getAccount_code());
                                break;
                            case 2:
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                cell.setCellValue(glAccount.getGl_Sl());
                                break;
                            case 3:
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                cell.setCellValue(glAccount.getAccount_descript());
                                break;
                            case 4:
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                cell.setCellValue(glAccount.getGl_Sbv());
                                break;
                            case 5:
                                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                cell.setCellValue(Double.parseDouble(glAccount.getOpen_debit().toString()));
                                cell.setCellStyle(numberStyle);
                                break;
                            case 6:
                                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                cell.setCellValue(Double.parseDouble(glAccount.getOpen_credit().toString()));
                                cell.setCellStyle(numberStyle);
                                break;
                            case 7:
                                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                cell.setCellValue(Double.parseDouble(glAccount.getTurn_debit().toString()));
                                cell.setCellStyle(numberStyle);
                                break;
                            case 8:
                                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                cell.setCellValue(Double.parseDouble(glAccount.getTurn_credit().toString()));
                                cell.setCellStyle(numberStyle);
                                break;
                            case 9:
                                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                cell.setCellValue(Double.parseDouble(glAccount.getClose_debit().toString()));
                                cell.setCellStyle(numberStyle);
                                break;
                            case 10:
                                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                cell.setCellValue(Double.parseDouble(account.getClose_credit().toString()));
                                cell.setCellStyle(numberStyle);
                                break;
                        }
                        colindex++;
                    }
                    if (account.getAccount_code() == null
                            || account.getAccount_code().trim().length() <= 4) {
                        if (null == account.getAccount_code()) {
                            makeRowBold(workbook, sheet, row,
                                    getMappingStyle(0));
                        } else {
                            makeRowBold(workbook, sheet, row,
                                    getMappingStyle(account.getAccount_code().trim().length()));
                        }
                    }
                } catch (Exception ex) {
                    System.err.println("Error dong: " + rowindex + "-->" + ex.getMessage());
                }
                rowindex++;
            }
        }

        FileOutputStream out = new FileOutputStream(strFileName);
        workbook.write(out);

        return isSuccess;
    }

    public List<String> getColumnNames(ResultSet rs) throws SQLException {
        List<String> names = new ArrayList<>();
        ResultSetMetaData metadata = rs.getMetaData();
        for (int i = 0; i < metadata.getColumnCount(); i++) {
            names.add(metadata.getColumnName(i + 1));
        }
        return names;
    }

    public List<String> getColumnValues(ResultSet rs)
            throws SQLException, IOException, ParseException {

        List<String> values = new ArrayList<>();
        ResultSetMetaData metadata = rs.getMetaData();

        for (int i = 0; i < metadata.getColumnCount(); i++) {
            values.add(getColumnValue(rs, metadata.getColumnType(i + 1), i + 1));
        }

        return values;
    }

    private String handleObject(Object obj) {
        return obj == null ? "" : String.valueOf(obj);
    }

    private String handleBigDecimal(BigDecimal decimal) {
        return decimal == null ? "" : decimal.toString();
    }

    private String handleLong(ResultSet rs, int columnIndex) throws SQLException {
        long lv = rs.getLong(columnIndex);
        return rs.wasNull() ? "" : Long.toString(lv);
    }

    private String handleInteger(ResultSet rs, int columnIndex) throws SQLException {
        int i = rs.getInt(columnIndex);
        return rs.wasNull() ? "" : Integer.toString(i);
    }

    private String handleDate(ResultSet rs, int columnIndex) throws SQLException, ParseException {
        try {
            if (rs.getString(columnIndex) != null) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date fecha = new Date(formatter.parse(rs.getString(columnIndex)).getTime());
                return formatter.format(fecha);
            } else {
                return "";
            }
        } catch (ParseException e) {
            throw new SQLException("Fecha erronea o faltante");
        }
    }

    private String handleTime(Time time) {
        return time == null ? null : time.toString();
    }

    private String handleTimestamp(Timestamp timestamp) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        return timestamp == null ? null : timeFormat.format(timestamp);
    }

    private String getColumnValue(ResultSet rs, int colType, int colIndex)
            throws SQLException, IOException, ParseException {

        String value = "";

        switch (colType) {

            case Types.BIT:
            case Types.JAVA_OBJECT:
                value = handleObject(rs.getObject(colIndex));
                break;
            case Types.BOOLEAN:
                boolean b = rs.getBoolean(colIndex);
                value = Boolean.toString(b);
                break;
            case Types.NCLOB: // todo : use rs.getNClob
            case Types.CLOB:
                Clob c = rs.getClob(colIndex);
                if (c != null) {
                    value = read(c);
                }
                break;
            case Types.BIGINT:
                value = handleLong(rs, colIndex);
                break;
            case Types.DECIMAL:
            case Types.DOUBLE:
            case Types.FLOAT:
            case Types.REAL:
            case Types.NUMERIC:
                value = handleBigDecimal(rs.getBigDecimal(colIndex));
                break;
            case Types.INTEGER:
            case Types.TINYINT:
            case Types.SMALLINT:
                value = handleInteger(rs, colIndex);
                break;
            case Types.DATE:
                value = handleDate(rs, colIndex);
                break;
            case Types.TIME:
                value = handleTime(rs.getTime(colIndex));
                break;
            case Types.TIMESTAMP:
                value = handleTimestamp(rs.getTimestamp(colIndex));
                break;
            case Types.NVARCHAR: // todo : use rs.getNString
            case Types.NCHAR: // todo : use rs.getNString
            case Types.LONGNVARCHAR: // todo : use rs.getNString
            case Types.LONGVARCHAR:
            case Types.VARCHAR:
            case Types.CHAR:
                value = rs.getString(colIndex);
                break;
            default:
                value = "";
        }

        if (value == null) {
            value = "";
        }

        return value;

    }

    private static String read(Clob c) throws SQLException, IOException {
        StringBuilder sb = new StringBuilder((int) c.length());
        Reader r = c.getCharacterStream();
        char[] cbuf = new char[CLOBBUFFERSIZE];
        int n;
        while ((n = r.read(cbuf, 0, cbuf.length)) != -1) {
            sb.append(cbuf, 0, n);
        }
        return sb.toString();
    }

    private void makeRowBold(HSSFWorkbook workbook, HSSFSheet sheet, Row row, int type) {
        HSSFCellStyle style = workbook.createCellStyle();//Create style
        Font font = workbook.createFont();
        switch (type) {
            case 1:
                font.setBoldweight(Font.BOLDWEIGHT_BOLD);
                break;
            case 2:
                font.setItalic(true);
                break;
            case 3:
                font.setBoldweight(Font.BOLDWEIGHT_BOLD);
                font.setItalic(true);
                break;
            case 4:
                font.setBoldweight(Font.BOLDWEIGHT_BOLD);
                break;
        }
        DataFormat format = workbook.createDataFormat();
        style.setFont(font);
        style.setDataFormat(format.getFormat("#,##0"));
        for (int i = 0; i < row.getLastCellNum(); i++) {
            row.getCell(i).setCellStyle(style);
        }
    }

    private int getMappingStyle(int length) {
        switch (length) {
            case 0:
                return 2;
            case 1:
                return 1;
            case 2:
                return 1;
            case 3:
                return 3;
            case 4:
                return 4;
            default:
                return 4;
        }
    }

}
