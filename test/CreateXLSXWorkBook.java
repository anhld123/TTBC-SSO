/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BAOANH
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class CreateXLSXWorkBook {

    public static void main(String[] args) throws IOException {
        thunhe("e://excel//test_ims.xlsx");
        // Create a new XSSFWorkbook from scratch (start here)
        XSSFWorkbook workbook = new XSSFWorkbook();
        // Create a new sheet inside the workbook
        XSSFSheet spreadsheet = workbook.createSheet("First Sheet");
        // Create a new Row in the spread sheet with row number starting from 0
        XSSFRow row = spreadsheet.createRow(0);
        // Create a new Cell in the Row with the cell number starting from 0
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("Hello World");
        XSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("Welcome");
        // Create file system using specific name
        FileOutputStream out = new FileOutputStream(new File("E://excel//XSSFWorkBook_ims.xlsx"));
        // write the excel file to the file output stream
        workbook.write(out);
        out.close();
        workbook.close();
    }

    public static void thunhe(String sfile) throws FileNotFoundException, IOException {
        File myFile = new File(sfile);
//        FileInputStream fis = new FileInputStream(myFile); // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook (); // Return first sheet from the XLSX workbook 
        XSSFSheet mySheet = myWorkBook.createSheet("thunhe");

        Map<String, Object[]> data = new HashMap<String, Object[]>();
        data.put("7", new Object[]{7d, "Sonya", "75K", "SALES", "Rupert"});
        data.put("8", new Object[]{8d, "Kris", "85K", "SALES", "Rupert"});
        data.put("9", new Object[]{9d, "Dave", "90K", "SALES", "Rupert"}); // Set to Iterate and add rows into XLS file 
        Set<String> newRows = data.keySet(); // get the last row number to append new data
        int rownum = mySheet.getLastRowNum();
        for (String key : newRows) { // Creating a new Row in existing XLSX sheet 
            Row row = mySheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Boolean) {
                    cell.setCellValue((Boolean) obj);
                } else if (obj instanceof Date) {
                    cell.setCellValue((Date) obj);
                } else if (obj instanceof Double) {
                    cell.setCellValue((Double) obj);
                }
            }
        } // open an OutputStream to save written data into XLSX file 
        FileOutputStream os = new FileOutputStream(myFile); 
        myWorkBook.write(os); 
        System.out.println("Writing on XLSX file Finished ..."); 
        myWorkBook.close();

    }
}
