/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.export.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author LION
 */
public class ExportExcelFileTT31 {

    public void ExportFileExcelFormula(HashMap<Integer, List<String>> hmDataExp, String strTitle, String strUntil, String strFileName) //throws SQLException {
    {
        if (hmDataExp.size() < 1) {
            return;
        }
        //tao workbook cho excel
        try {

            SXSSFWorkbook workbook = new SXSSFWorkbook(100);
            //tao Sheet 
            SXSSFSheet sheet = workbook.createSheet("Formula");
            sheet.trackAllColumnsForAutoSizing();
            //ResultSetMetaData resetMetaData = reset.getMetaData();
            //lay ra so cot cua bang du lieu
            int ncolNum = hmDataExp.get(0).size();

            //Tao cot cho tieu de
            Row row = sheet.createRow((short) 1);
            Cell cellTitle = row.createCell((short) 1);

            cellTitle.setCellValue(strTitle);

            sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, ncolNum));

            //Thiet lap cho title vao giua cua mergin da tao
            CellStyle styleTitle = workbook.createCellStyle();
            //XSSFCellStyle
            styleTitle.setAlignment(CellStyle.ALIGN_CENTER);
            styleTitle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

            //Thiet lap cho font
            Font font = workbook.createFont();
            //Thiet lap font la kieu bolb
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            //Thiet lap font size la 18
            font.setFontHeightInPoints((short) 18);
            //set font cho stype
            styleTitle.setFont(font);
            //Thiet lap mau cho header
            styleTitle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            //////////////
            styleTitle.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            styleTitle.setFillPattern(CellStyle.ALT_BARS);

            ////////////////////
            cellTitle.setCellStyle(styleTitle);
            //////////////////////////////////////////////////
            row = sheet.createRow((short) 3);
            Cell cellUntil = row.createCell((short) ncolNum - 2);

//            ncolNum - 1, 1, ncolNum - 1, ncolNum
            sheet.addMergedRegion(new CellRangeAddress(3, 3, ncolNum - 2, ncolNum));

            //Thiet lap cho title vao giua cua mergin da tao
            CellStyle styleUntil = workbook.createCellStyle();
            //XSSFCellStyle
            styleUntil.setAlignment(CellStyle.ALIGN_CENTER);
            styleUntil.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

            //Thiet lap cho font
            Font fontUntil = workbook.createFont();
            //Thiet lap font la kieu bolb
            fontUntil.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
            //Thiet lap font size la 18
            fontUntil.setFontHeightInPoints((short) 12);
            //set font cho stype
            styleUntil.setFont(fontUntil);
            //Thiet lap mau cho header
            styleUntil.setFillForegroundColor(HSSFColor.BRIGHT_GREEN.index);
//////////////
            styleUntil.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
            styleUntil.setFillPattern(CellStyle.ALT_BARS);

////////////////////
            cellUntil.setCellStyle(styleUntil);
            cellUntil.setCellValue("Đơn vị tính: " + strUntil);
            //Thiet lap cho phan header
            //Bat dau ghi du lieu cho phan header tu cot thu 4
            //tao vong lap de duyet het cac column cua header
            for (int i = 0; i < hmDataExp.size(); i++) {
                row = sheet.createRow(i + 4);
                List<String> lstRowData = hmDataExp.get(i);
                for (int j = 0; j < lstRowData.size(); j++) {
                    if (i == 0) {
                        CellStyle styleHeader = workbook.createCellStyle();
                        //Lay ra ten header cua bang du lieu
                        String strColData = lstRowData.get(j).replace("**", ",");
                        //Lay ten hien thi tu Map voi key la ten cua cot

                        //Bo 1 column ngoai cung va bat dau dua du lieu vao tu column thu 2
                        Cell cell = row.createCell(j + 1);
                        cell.setCellValue(strColData);
                        //Set font cho header
                        font = workbook.createFont();
                        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
                        styleHeader.setFont(font);
                        //set cho phan background
                        styleHeader.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); //co the cho mau nay ORANGE
                        styleHeader.setFillPattern(CellStyle.SOLID_FOREGROUND);

                        //Set do dong auto cho column
                        sheet.autoSizeColumn(i + 1);

                        cell.setCellStyle(styleHeader);
                    }
                    if (j == 0) {
                        CellStyle styleHeader = workbook.createCellStyle();
                        //Lay ra ten header cua bang du lieu
                        String strColData = lstRowData.get(j).replace("**", ",");
                        //Lay ten hien thi tu Map voi key la ten cua cot

                        //Bo 1 column ngoai cung va bat dau dua du lieu vao tu column thu 2
                        Cell cell = row.createCell(j + 1);
                        cell.setCellValue(strColData);
                        //Set font cho header
                        font = workbook.createFont();
                        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
                        styleHeader.setFont(font);
                        //set cho phan background
                        styleHeader.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); //co the cho mau nay ORANGE
                        styleHeader.setFillPattern(CellStyle.SOLID_FOREGROUND);

                        //Set do dong auto cho column
                        sheet.autoSizeColumn(i + 1);

                        cell.setCellStyle(styleHeader);
                    }
                    if (i != 0 && j != 0) {
                        String strColData = lstRowData.get(j);
                        //Lay ten hien thi tu Map voi key la ten cua cot

                        //Bo 1 column ngoai cung va bat dau dua du lieu vao tu column thu 2
                        Cell cell = row.createCell(j + 1);
                        if (DefineFun.isNumeric(strColData)) {
                            cell.setCellValue(Double.parseDouble(strColData));
                        } else {
                            cell.setCellValue(strColData);
                        }
                    }
                }

            }
            //Thiet lap cho phan ghi du lieu ra
            FileOutputStream out = new FileOutputStream(new File(strFileName));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            System.err.println("Loi roi " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " ExportFileExcelFormula -> " + e.getMessage());
        }
    }

    public void ExportFileExcel(ResultSet reset, Map<String, String> hmColumn, String strTitle, String strFileName) //throws SQLException {
    {

        //tao workbook cho excel
        try {

            SXSSFWorkbook workbook = new SXSSFWorkbook(100);
            //tao Sheet 
            SXSSFSheet sheet = workbook.createSheet("bao cao nhanh");
            sheet.trackAllColumnsForAutoSizing();
            ResultSetMetaData resetMetaData = reset.getMetaData();
            //lay ra so cot cua bang du lieu
            int ncolNum = resetMetaData.getColumnCount();

            //Tao cot cho tieu de
            Row row = sheet.createRow((short) 1);
            Cell cellTitle = row.createCell((short) 1);

            cellTitle.setCellValue(strTitle);

            sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, ncolNum - 1));

            //Thiet lap cho title vao giua cua mergin da tao
            CellStyle styleTitle = workbook.createCellStyle();
            //XSSFCellStyle
            styleTitle.setAlignment(CellStyle.ALIGN_CENTER);
            styleTitle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

            //Thiet lap cho font
            Font font = workbook.createFont();
            //Thiet lap font la kieu bolb
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            //Thiet lap font size la 18
            font.setFontHeightInPoints((short) 18);
            //set font cho stype
            styleTitle.setFont(font);
            //Thiet lap mau cho header
            styleTitle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            //////////////
            styleTitle.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            styleTitle.setFillPattern(CellStyle.ALT_BARS);

            ////////////////////
            cellTitle.setCellStyle(styleTitle);

            //Thiet lap cho phan header
            //Bat dau ghi du lieu cho phan header tu cot thu 4
            row = sheet.createRow((short) 4);

            //tao vong lap de duyet het cac column cua header
            for (int i = 0; i < ncolNum; i++) {
                CellStyle styleHeader = workbook.createCellStyle();
                //Lay ra ten header cua bang du lieu
                String strColName = resetMetaData.getColumnName(i + 1);
                //Lay ten hien thi tu Map voi key la ten cua cot

                String strColDesc = hmColumn.get(strColName); // strColName;//
                //Bo 1 column ngoai cung va bat dau dua du lieu vao tu column thu 2
                Cell cell = row.createCell(i + 1);
                cell.setCellValue(strColDesc);
                //Set font cho header
                font = workbook.createFont();
                font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
                styleHeader.setFont(font);
                //set cho phan background
                styleHeader.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); //co the cho mau nay ORANGE
                styleHeader.setFillPattern(CellStyle.SOLID_FOREGROUND);

                //Set do dong auto cho column
                sheet.autoSizeColumn(i + 1);

                cell.setCellStyle(styleHeader);
            }
            //Thiet lap cho phan ghi du lieu ra

            while (reset.next()) {
                //tao 1 row moi sau do dua du lieu vao
                row = sheet.createRow(reset.getRow() + 4);

                for (int i = 0; i < ncolNum; i++) {
                    //CellStyle styleBody = workbook.createCellStyle();
                    //Lay ra kieu du lieu cua truong
                    String strDataType = resetMetaData.getColumnTypeName(i + 1);
                    //Lay ra gia tri cua cot
                    String strValue = reset.getString(i + 1);

                    //tao cell
                    Cell cell = row.createCell(i + 1);
                    //neu kieu du lieu la so di convert sang kieu double

                    // System.err.println("Kieu du lieu :"+strDataType);
                    if (strDataType.equals("NUMBER")) {
                        if (strValue == null) {
                            strValue = "0";
                        }
                        cell.setCellValue(Double.parseDouble(strValue));

                    } //                    else if (strDataType.equals("DATE"))
                    //                    {
                    //                        cell.setCellValue(new Date(strValue));
                    //                        System.err.println("Gia tri kieu date "+strValue);
                    //                    }
                    else {
                        cell.setCellValue(strValue);
                    }

                    //Set border cho cell
                }
            }

            FileOutputStream out = new FileOutputStream(new File(strFileName));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            System.err.println("Loi roi " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " ExportFileExcelFormula -> " + e.getMessage());
        }
    }

    public void ExportFileExcelQuery(ResultSet reset, String strTitle, String strFileName) //throws SQLException {
    {
        //tao workbook cho excel
        //CoreLogger.error(ExportExcelFile.class.getClass()+" -> TITLE "+strTitle);
        int nRowCount = 0;
        try {
            SXSSFWorkbook workbook = new SXSSFWorkbook(100);
            //tao Sheet 
            SXSSFSheet sheet = workbook.createSheet("BCQUERY");
            sheet.trackAllColumnsForAutoSizing();
            ResultSetMetaData resetMetaData = reset.getMetaData();
            //lay ra so cot cua bang du lieu
            int ncolNum = resetMetaData.getColumnCount();
            //resetMetaData.get
            //Tao cot cho tieu de
            Row row = sheet.createRow((short) 1);
            Cell cellTitle = row.createCell((short) 1);

            // cellTitle.setCellValue(strTitle);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, ncolNum));

            //Thiet lap cho title vao giua cua mergin da tao
            CellStyle styleTitle = workbook.createCellStyle();
            //XSSFCellStyle
            styleTitle.setAlignment(CellStyle.ALIGN_CENTER);
            styleTitle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

            //Thiet lap cho font
            Font font = workbook.createFont();
            font.setFontName("Times New Roman");
            //Thiet lap font la kieu bolb
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            //Thiet lap font size la 18
            font.setFontHeightInPoints((short) 18);
            //set font cho stype
            styleTitle.setFont(font);
            //Thiet lap mau cho header
            styleTitle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            //////////////
            styleTitle.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            styleTitle.setFillPattern(CellStyle.ALT_BARS);

            ////////////////////
            cellTitle.setCellStyle(styleTitle);
            cellTitle.setCellValue(strTitle);
            //Thiet lap cho phan header
            //Bat dau ghi du lieu cho phan header tu cot thu 4
            row = sheet.createRow((short) 4);

            //tao vong lap de duyet het cac column cua header
            for (int i = 0; i < ncolNum; i++) {
                CellStyle styleHeader = workbook.createCellStyle();
                //Lay ra ten header cua bang du lieu
                String strColName = resetMetaData.getColumnName(i + 1);
                //Lay ten hien thi tu Map voi key la ten cua cot

                //String strColDesc = hmColumn.get(strColName); // strColName;//
                //Bo 1 column ngoai cung va bat dau dua du lieu vao tu column thu 2
                Cell cell = row.createCell(i + 1);
                cell.setCellValue(strColName);
                //Set font cho header
                font = workbook.createFont();
                font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
                styleHeader.setFont(font);
                //set cho phan background
                styleHeader.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); //co the cho mau nay ORANGE
                styleHeader.setFillPattern(CellStyle.SOLID_FOREGROUND);

                //Set do dong auto cho column
                sheet.autoSizeColumn(i + 1);
                //CoreLogger.error(strColName+" >>> "+strColDesc);
//                //Set border cho cell
//                styleHeader.setBorderBottom(CellStyle.BORDER_THIN);
//                //style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
//                styleHeader.setBorderLeft(CellStyle.BORDER_THIN);
//                //style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
//                styleHeader.setBorderRight(CellStyle.BORDER_THIN);
//                // style.setRightBorderColor(IndexedColors.BLUE.getIndex());
//                styleHeader.setBorderTop(CellStyle.BORDER_MEDIUM_DASHED);
//            //style.setTopBorderColor(IndexedColors.BLACK.getIndex());

                cell.setCellStyle(styleHeader);
            }
            //Thiet lap cho phan ghi du lieu ra
            int currRow = reset.getRow() + 5;
            while (reset.next()) {
                //row = sheet.createRow(reset.getRow() + 4);
                nRowCount++;
                //int sodong =reset.getRow();
                if (reset.getRow() % 1000000 == 0) {
                    sheet = workbook.createSheet();
                    //System.err.println("vao tao sheet moi");
                    row = sheet.createRow(0);
                    currRow = 1;
                } else //tao 1 row moi sau do dua du lieu vao
                {
                    row = sheet.createRow(currRow++);
                }
                //System.err.println("Row -> "+currRow);
                for (int i = 0; i < ncolNum; i++) {
                    //CellStyle styleBody = workbook.createCellStyle();
                    //Lay ra kieu du lieu cua truong
                    String strDataType = resetMetaData.getColumnTypeName(i + 1);
                    //Lay ra gia tri cua cot
                    String strValue = reset.getString(i + 1);

                    //tao cell
                    Cell cell = row.createCell(i + 1);
                    //neu kieu du lieu la so di convert sang kieu double

                    // System.err.println("Kieu du lieu :"+strDataType);
                    if (strDataType.equals("NUMBER")) {
                        if (strValue == null) {
                            strValue = "0";
                        }
                        cell.setCellValue(Double.parseDouble(strValue));

                    } //                    else if (strDataType.equals("DATE"))
                    //                    {
                    //                        cell.setCellValue(Date.parse(strValue));
                    //                        System.err.println("Gia tri kieu date "+strValue);
                    //                    }
                    else if (strDataType.equals("DATE")) {
                        //dd/MM/yyyy 
                        //SimpleDateFormat datetemp = new SimpleDateFormat("yyyy-MM-dd");
                        if (strValue == null || strValue.isEmpty()) {
                            continue;
                        }

                        Date sdf = new SimpleDateFormat("yyyy-MM-dd").parse(strValue);
                        //Date cellValue = datetemp.parse(strValue);
                        cell.setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(sdf));
//                        System.err.println("Gia tri kieu date "+strValue);
                    } else {
                        if (strValue == null) {
                            strValue = "";
                        }
                        cell.setCellValue(strValue);
                    }

                    //Set border cho cell
//                styleBody.setBorderBottom(CellStyle.BORDER_THIN);
//                //style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
//                styleBody.setBorderLeft(CellStyle.BORDER_THIN);
//                //style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
//                styleBody.setBorderRight(CellStyle.BORDER_THIN);
//                // style.setRightBorderColor(IndexedColors.BLUE.getIndex());
//                styleBody.setBorderTop(CellStyle.BORDER_MEDIUM_DASHED);
//                
//                cell.setCellStyle(styleBody);
                }
            }
            File writefile = new File(strFileName);
            FileOutputStream out = new FileOutputStream(writefile);
            workbook.write(out);
            out.close();
            if (nRowCount == 0) {
                writefile.delete();
            }
        } catch (Exception e) {
            System.err.println("Loi roi " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " ExportFileExcelFormula -> " + e.getMessage());
        }
    }

    public void ExportFileExcelQueryFromList(List<HashMap<Integer, List<Object>>> lstDataExp, String strTitle, String strFileName) //throws SQLException {
    {
        if (lstDataExp == null) {
            return;
        }
        HashMap<Integer, List<Object>> hmColName = lstDataExp.get(0);
        HashMap<Integer, List<Object>> hmMetaDataTable = lstDataExp.get(1);
        HashMap<Integer, List<Object>> hmDataRow = lstDataExp.get(2);

        List<Object> lstMetaTable = hmMetaDataTable.get(9999);
        List<Object> lstColName = hmColName.get(9999);
        //tao workbook cho excel
        //CoreLogger.error(ExportExcelFile.class.getClass()+" -> TITLE "+strTitle);
        int nRowCount = 0;
        try {
            SXSSFWorkbook workbook = new SXSSFWorkbook(100);
            //tao Sheet 
            SXSSFSheet sheet = workbook.createSheet("BCQUERY");
            sheet.trackAllColumnsForAutoSizing();
            //ResultSetMetaData resetMetaData = lstMetaTable.size();
            //lay ra so cot cua bang du lieu
            int ncolNum = lstMetaTable.size();
            //resetMetaData.get
            //Tao cot cho tieu de
            Row row = sheet.createRow((short) 1);
            Cell cellTitle = row.createCell((short) 1);

            // cellTitle.setCellValue(strTitle);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, ncolNum));

            //Thiet lap cho title vao giua cua mergin da tao
            CellStyle styleTitle = workbook.createCellStyle();
            //XSSFCellStyle
            styleTitle.setAlignment(CellStyle.ALIGN_CENTER);
            styleTitle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

            //Thiet lap cho font
            Font font = workbook.createFont();
            font.setFontName("Times New Roman");
            //Thiet lap font la kieu bolb
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            //Thiet lap font size la 18
            font.setFontHeightInPoints((short) 18);
            //set font cho stype
            styleTitle.setFont(font);
            //Thiet lap mau cho header
            styleTitle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            //////////////
            styleTitle.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            styleTitle.setFillPattern(CellStyle.ALT_BARS);

            ////////////////////
            cellTitle.setCellStyle(styleTitle);
            cellTitle.setCellValue(strTitle);
            //Thiet lap cho phan header
            //Bat dau ghi du lieu cho phan header tu cot thu 4
            row = sheet.createRow((short) 4);

            //tao vong lap de duyet het cac column cua header
            for (int i = 0; i < ncolNum; i++) {
                CellStyle styleHeader = workbook.createCellStyle();
                //Lay ra ten header cua bang du lieu
                String strColName = lstColName.get(i).toString();
                //Lay ten hien thi tu Map voi key la ten cua cot

                //String strColDesc = hmColumn.get(strColName); // strColName;//
                //Bo 1 column ngoai cung va bat dau dua du lieu vao tu column thu 2
                Cell cell = row.createCell(i + 1);
                cell.setCellValue(strColName);
                //Set font cho header
                font = workbook.createFont();
                font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
                styleHeader.setFont(font);
                //set cho phan background
                styleHeader.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); //co the cho mau nay ORANGE
                styleHeader.setFillPattern(CellStyle.SOLID_FOREGROUND);

                //Set do dong auto cho column
                sheet.autoSizeColumn(i + 1);
                //CoreLogger.error(strColName+" >>> "+strColDesc);
//                //Set border cho cell
//                styleHeader.setBorderBottom(CellStyle.BORDER_THIN);
//                //style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
//                styleHeader.setBorderLeft(CellStyle.BORDER_THIN);
//                //style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
//                styleHeader.setBorderRight(CellStyle.BORDER_THIN);
//                // style.setRightBorderColor(IndexedColors.BLUE.getIndex());
//                styleHeader.setBorderTop(CellStyle.BORDER_MEDIUM_DASHED);
//            //style.setTopBorderColor(IndexedColors.BLACK.getIndex());

                cell.setCellStyle(styleHeader);
            }
            //Thiet lap cho phan ghi du lieu ra
            int currRow = 5;
            for (int k = 0; k < hmDataRow.size(); k++) {
                //row = sheet.createRow(reset.getRow() + 4);
                nRowCount++;
                //int sodong =reset.getRow();
                if (k % 1000000 == 0 && k != 0) {
                    sheet = workbook.createSheet();
                    //System.err.println("vao tao sheet moi");
                    row = sheet.createRow(0);
                    currRow = 1;
                } else //tao 1 row moi sau do dua du lieu vao
                {
                    row = sheet.createRow(currRow++);
                }
                List<Object> lstDataExpObj = hmDataRow.get(k);

                //System.err.println("Row -> "+currRow);
                for (int i = 0; i < ncolNum; i++) {

                    //CellStyle styleBody = workbook.createCellStyle();
                    //Lay ra kieu du lieu cua truong
                    String strDataType = lstMetaTable.get(i).toString();
                    //Lay ra gia tri cua cot
                    String strValue = lstDataExpObj.get(i).toString();

                    //tao cell
                    Cell cell = row.createCell(i + 1);
                    //neu kieu du lieu la so di convert sang kieu double

                    // System.err.println("Kieu du lieu :"+strDataType);
                    if (strDataType.equals("NUMBER")) {
                        if (strValue == null || strValue == "") {
                            strValue = "0";
                        }
                        cell.setCellValue(Double.parseDouble(strValue));

                    } //                    else if (strDataType.equals("DATE"))
                    //                    {
                    //                        cell.setCellValue(Date.parse(strValue));
                    //                        System.err.println("Gia tri kieu date "+strValue);
                    //                    }
                    else if (strDataType.equals("DATE")) {
                        //dd/MM/yyyy 
                        //SimpleDateFormat datetemp = new SimpleDateFormat("yyyy-MM-dd");
                        if (strValue == null || strValue.isEmpty()) {
                            continue;
                        }

                        Date sdf = new SimpleDateFormat("yyyy-MM-dd").parse(strValue);
                        //Date cellValue = datetemp.parse(strValue);
                        cell.setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(sdf));
//                        System.err.println("Gia tri kieu date "+strValue);
                    } else {
                        if (strValue == null) {
                            strValue = "";
                        }
                        cell.setCellValue(strValue);
                    }

                    //Set border cho cell
//                styleBody.setBorderBottom(CellStyle.BORDER_THIN);
//                //style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
//                styleBody.setBorderLeft(CellStyle.BORDER_THIN);
//                //style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
//                styleBody.setBorderRight(CellStyle.BORDER_THIN);
//                // style.setRightBorderColor(IndexedColors.BLUE.getIndex());
//                styleBody.setBorderTop(CellStyle.BORDER_MEDIUM_DASHED);
//                
//                cell.setCellStyle(styleBody);
                }
            }
            File writefile = new File(strFileName);
            FileOutputStream out = new FileOutputStream(writefile);
            workbook.write(out);
            out.close();
//            if (nRowCount == 0) {
//                writefile.delete();
//            }
        } catch (Exception e) {
            System.err.println("Loi roi " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " ExportFileExcelFormula -> " + e.getMessage());
        }
    }

    public void setExcelSubTitle(String excelFile, String timeStamp, int row1, int col1,
            String subTitle, int row2, int col2) {
        FileInputStream fis = null;
        try {
            File excelInputFile = new File(excelFile);
            fis = new FileInputStream(excelInputFile);
            HSSFWorkbook book = new HSSFWorkbook(fis);
            Sheet sheet = book.getSheetAt(0);
            Row row = sheet.getRow(row1);
            Cell cell = row.getCell(col1);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue(timeStamp);
            row = sheet.getRow(row2);
            cell = row.getCell(col2);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue(subTitle);
            try (FileOutputStream os = new FileOutputStream(excelFile)) {
                book.write(os);
                os.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExportExcelFileTT31.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(this.getClass().getName() + " setExcelSubTitle -> " + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ExportExcelFileTT31.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(this.getClass().getName() + " setExcelSubTitle -> " + ex.getMessage());
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(ExportExcelFileTT31.class.getName()).log(Level.SEVERE, null, ex);
                CoreLogger.error(this.getClass().getName() + " setExcelSubTitle -> " + ex.getMessage());
            }
        }
    }

    public void export2FileExcel_Rs(ResultSet reset, int nrow, int ncol,
            String strFileName, String excelTemp, int time1st) {
        try {
            //Lay ra file excel template
            File excelInputFile;
            if (time1st == 1) {
                String fullpath = Define.M_ROOT + Define.M_EXCEL_TEMP;
                String excelTemplateFile = fullpath + excelTemp + ".XLS";
                excelInputFile = new File(excelTemplateFile);
            } else {
                excelInputFile = new File(strFileName);
            }
            try (FileInputStream fis = new FileInputStream(excelInputFile)) {
                HSSFWorkbook book = new HSSFWorkbook(fis);
                //SXSSFWorkbook book = new SXSSFWorkbook(xlsbook,100);
                //lay ra sheet o vi tri so 0
                Sheet sheet = book.getSheetAt(0);

                if (time1st == 1) {

                }
                //Lay ra cau truc cua reset
                ResultSetMetaData resetMetaData = reset.getMetaData();
                //lay ra so cot cua bang du lieu
                int ncolNum = resetMetaData.getColumnCount();
                //Duyet du lieu
                while (reset.next()) {
                    int sodong = reset.getRow();
                    try {
//                Row row = sheet.createRow(nrow + sodong - 2);
                        Row row = sheet.getRow(nrow + sodong - 2);
                        for (int i = 0; i < ncolNum; i++) {

                            //Lay ra kieu du lieu cua cot
                            String strDataType = resetMetaData.getColumnTypeName(i + 1);
                            //lay ra gia tri cua cot
                            String strValue = reset.getString(i + 1);                            
                            //Tao 1 cell (o cho du lieu)
//                    Cell cell = row.createCell(i + ncol - 1);
                            Cell cell = row.getCell(i + ncol - 1);
                            //Neu kieu du lieu la number
                            if (strDataType.equals("NUMBER")) {
                                //Neu la null thi gan gia tri la 0
                                if (strValue == null) {
                                    strValue = "0";
                                }
                                cell.setCellValue(Double.parseDouble(strValue));

                            } //Kieu du lieu la date
                            else if (strDataType.equals("DATE")) {
                                if (strValue.isEmpty()) {
                                    cell.setCellValue(" ");
                                } else {
                                    Date datevalue = new SimpleDateFormat("yyyy-MM-dd").parse(strValue);
                                    CreationHelper createHelper = book.getCreationHelper();
                                    CellStyle dateCellStyle = book.createCellStyle();
                                    cell.setCellValue(datevalue);
                                    dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
                                }
                            } //Kieu du lieu la varchar2
                            else {
                                cell.setCellValue(strValue);
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Loi khi fill excel " + sodong + "~" + e.getMessage());
                    }
                }
                try (FileOutputStream os = new FileOutputStream(strFileName)) {
                    book.write(os);
                    os.close();
                }
            }
        } catch (Exception e) {
            System.err.println("Loi khi fill excel" + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " export2FileExcel_Rs -> " + e.getMessage());
        }

    }
    
    
    
    public void export2FileExcel_Rs_1(ResultSet reset, int nrow, int ncol,
            String strFileName, String excelTemp, int time1st) {
        try {
            //Lay ra file excel template
            File excelInputFile;
            if (time1st == 1) {
                String fullpath = Define.M_ROOT + Define.M_EXCEL_TEMP;
                String excelTemplateFile = fullpath + excelTemp + ".XLS";
                excelInputFile = new File(excelTemplateFile);
            } else {
                excelInputFile = new File(strFileName);
            }
            try (FileInputStream fis = new FileInputStream(excelInputFile)) {
                HSSFWorkbook book = new HSSFWorkbook(fis);
                //SXSSFWorkbook book = new SXSSFWorkbook(xlsbook,100);
                //lay ra sheet o vi tri so 0
                Sheet sheet = book.getSheetAt(0);
                
                HSSFDataFormat df;

                if (time1st == 1) {

                }
                //Lay ra cau truc cua reset
                ResultSetMetaData resetMetaData = reset.getMetaData();
                //lay ra so cot cua bang du lieu
                int ncolNum = resetMetaData.getColumnCount();                
                //Duyet du lieu
                while (reset.next()) {
                    int sodong = reset.getRow();
                    try {
//                Row row = sheet.createRow(nrow + sodong - 2);
                        Row row = sheet.getRow(nrow + sodong - 2);
                        for (int i = 0; i < ncolNum; i++) {

                            //Lay ra kieu du lieu cua cot
                            String strDataType = resetMetaData.getColumnTypeName(i + 1);
                            //lay ra gia tri cua cot
//                            String strValue = reset.getString(i + 1);                            
                            //Tao 1 cell (o cho du lieu)
//                    Cell cell = row.createCell(i + ncol - 1);
                            Cell cell = row.createCell(i + ncol - 1);
                            //Neu kieu du lieu la number
                            if (strDataType.equals("NUMBER")) {
                                Double strValue = reset.getDouble(i + 1);
                                //Neu la null thi gan gia tri la 0                               
                                cell.setCellValue(strValue);                                
                                    HSSFCellStyle dateCellStyle = book.createCellStyle();  
                                    df = book.createDataFormat();
                                dateCellStyle.setDataFormat(df.getFormat("#,##0"));
                                cell.setCellStyle(dateCellStyle);
                            } //Kieu du lieu la date
                            else if (strDataType.equals("DATE")) {
                                Date strValue = reset.getDate(i + 1);
                                if (strValue == null) {
                                    cell.setCellValue(" ");
                                } else {                                    
                                    DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");                                                                     
                                    String datestr = dateformat.format(strValue);
                                    cell.setCellValue(datestr);                                  
                                     HSSFCellStyle dateCellStyle = book.createCellStyle();  
                                    df = book.createDataFormat();
                                dateCellStyle.setDataFormat(df.getFormat("dd/MM/yyyy"));
                                cell.setCellStyle(dateCellStyle);
                                }
                            } //Kieu du lieu la varchar2
                            else {
                                String strValue = reset.getString(i + 1);   
                                cell.setCellValue(strValue);
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Loi khi fill excel " + sodong + "~" + e.getMessage());
                    }
                }
                try (FileOutputStream os = new FileOutputStream(strFileName)) {
                    book.write(os);
                    os.close();
                }
            }
        } catch (Exception e) {
            System.err.println("Loi khi fill excel" + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " export2FileExcel_Rs -> " + e.getMessage());
        }

    }
}
