/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dtw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vbsp.ims.dtw.dao.DtwUploadDao;
import vbsp.ims.font.FontType;
import vbsp.ims.font.ImsConverter;

/**
 *
 * @author Trung
 */
public class ExcelFileLibrary extends FileLibrary {

    private DtwUploadDao dtwDao;

    @Override
    public int read_file(String file_path, ExcelFile excel_file) {
        Date begin_time = new Date();
        DateFormat date_format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dtwDao = new DtwUploadDao();
        FileInputStream file_input = null;

        File file = new File(file_path);

        UploadFileLogObject log_obj
                = new UploadFileLogObject(file.getParent(), file.getName(),
                        String.valueOf(Math.round(file.length() / 1000)) + " KB",
                        date_format.format(begin_time),
                        "NONE",
                        "WAIT",
                        "TRUNGNT88",
                        "");

        int row_cnt = 0;
        try {
//            File file = new File(file_path);
            file_input = new FileInputStream(file);
            Workbook workbook = null;
            if (file.getName().toLowerCase().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(file_input);
            } else if (file.getName().toLowerCase().endsWith("xls")) {
                workbook = new HSSFWorkbook(file_input);
            }

            Sheet sheet;
            sheet = workbook.getSheetAt(0);

            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                TLSLExcelRow row_obj = new TLSLExcelRow(row_cnt);
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    ExcelCell excel_cell = new ExcelCell();
                    excel_cell.setRow_id(row_cnt);
                    excel_cell.setCol_id(cell.getColumnIndex());
                    excel_cell.setCell_type(cell.getCellType());
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            excel_cell.setValue(cell.getNumericCellValue());
                            break;
                        case Cell.CELL_TYPE_STRING:
                            excel_cell.setValue(
                                    ImsConverter.convert_font(FontType.UNICODE,
                                            cell.getStringCellValue())
                            );
                            break;
//                        case Cell.CELL_TYPE_BLANK:
//                            break;
//                        case Cell.CELL_TYPE_ERROR:
//                            //System.out.println(cell.getErrorCellValue());
//                            break;
                        // CELL_TYPE_FORMULA will never occur
                        case Cell.CELL_TYPE_FORMULA:
                            //evaluator.evaluateFormulaCell(cell);
                            CellValue cellValue = evaluator.evaluate(cell);
                            String strCellValue = getCellValue(cellValue);
                            excel_cell.setValue(strCellValue);
                            break;
                    }
                    row_obj.getCells().add(excel_cell);
                }
                excel_file.getRows().add(row_obj);
                row_cnt++;
            }
            file_input.close();

            Date end_time = new Date();
            log_obj.setDwh_process_time(date_format.format(end_time));
            log_obj.setDwh_upload_status("DONE");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelFileLibrary.class.getName()).log(Level.SEVERE, null, ex);
            log_obj.setDwh_upload_status("ERROR");
        } catch (IOException ex) {
            Logger.getLogger(ExcelFileLibrary.class.getName()).log(Level.SEVERE, null, ex);
            log_obj.setDwh_upload_status("ERROR");
        } finally {
            try {
                file_input.close();
                dtwDao.write_log(log_obj);
            } catch (IOException ex) {
                Logger.getLogger(ExcelFileLibrary.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return row_cnt;
    }

    private String getCellValue(CellValue cellValue) {
        String val = "";
        if (cellValue.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            val = String.valueOf(cellValue.getNumberValue()) ;
        } else if (cellValue.getCellType() == Cell.CELL_TYPE_STRING) {
            val = cellValue.getStringValue();
        } else if (cellValue.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            val = "";//cellValue.getBooleanValue().toString();
        } else if (cellValue.getCellType() == Cell.CELL_TYPE_ERROR) {
            val = "";//cellValue.getErrorValue();
        }
        return val;
    }

    public int read_file_utf8(String file_path, ExcelFile excel_file) {
        Date begin_time = new Date();
        DateFormat date_format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DateFormat date_format_0 = new SimpleDateFormat("dd-MMM-yyyy");
        dtwDao = new DtwUploadDao();
        FileInputStream file_input = null;

        File file = new File(file_path);

        UploadFileLogObject log_obj
                = new UploadFileLogObject(file.getParent(), file.getName(),
                        String.valueOf(Math.round(file.length() / 1000)) + " KB",
                        date_format.format(begin_time),
                        "NONE",
                        "WAIT",
                        "TRUNGNT88",
                        "");

        int row_cnt = 0;
        try {
//            File file = new File(file_path);
            file_input = new FileInputStream(file);
            Workbook workbook = null;
            if (file.getName().toLowerCase().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(file_input);
            } else if (file.getName().toLowerCase().endsWith("xls")) {
                workbook = new HSSFWorkbook(file_input);
            }

            Sheet sheet;
            sheet = workbook.getSheetAt(0);
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                TLSLExcelRow row_obj = new TLSLExcelRow(row_cnt);
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    ExcelCell excel_cell = new ExcelCell();
                    excel_cell.setRow_id(row_cnt);
                    excel_cell.setCol_id(cell.getColumnIndex());
                    excel_cell.setCell_type(cell.getCellType());
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                excel_cell.setValue(
                                        date_format_0.format(date)
                                );
                            } else {
                                excel_cell.setValue(cell.getNumericCellValue());
                            }
                            break;
                        case Cell.CELL_TYPE_STRING:
                            excel_cell.setValue(
                                    cell.getStringCellValue()
                            );
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            //evaluator.evaluateFormulaCell(cell);
                            CellValue cellValue = evaluator.evaluate(cell);
                            String strCellValue = getCellValue(cellValue);
                            excel_cell.setValue(strCellValue);
                            break;
                        default:
                            excel_cell.setValue(
                                    ""
                            );
                            break;
                    }
                    row_obj.getCells().add(excel_cell);
                }
                excel_file.getRows().add(row_obj);
                row_cnt++;
            }
            file_input.close();

            Date end_time = new Date();
            log_obj.setDwh_process_time(date_format.format(end_time));
            log_obj.setDwh_upload_status("DONE");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelFileLibrary.class.getName()).log(Level.SEVERE, null, ex);
            log_obj.setDwh_upload_status("ERROR");
        } catch (IOException ex) {
            Logger.getLogger(ExcelFileLibrary.class.getName()).log(Level.SEVERE, null, ex);
            log_obj.setDwh_upload_status("ERROR");
        } finally {
            try {
                file_input.close();
                dtwDao.write_log(log_obj);
            } catch (IOException ex) {
                Logger.getLogger(ExcelFileLibrary.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return row_cnt;
    }

}
