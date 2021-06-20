
import java.io.File;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.formula.*;
import org.apache.poi.ss.formula.ptg.*;
import org.apache.poi.ss.formula.EvaluationWorkbook.ExternalSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author BAOANH
 */
public class DocExcel {

    public static void main(String[] args) {
        try {
            new DocExcel().convertStringToDate("30-JUN-2019");
            String SAMPLE_XLSX_FILE_PATH = "d://dulieu_khnv.xls";
            // FileInputStream file = new FileInputStream(new File(filename));
//             Workbook workbook = null;
//             if (filename.toLowerCase().endsWith("xlsx")) {
//                workbook = new XSSFWorkbook(file);
//            } else if (filename.toLowerCase().endsWith("xls")) {//
//                workbook = new HSSFWorkbook(file);
//            }
            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
//                     if (cell.getColumnIndex() >= EndCell) {
//                        continue;
//                    }
                    System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + cell.getColumnIndex());
                    //Check the cell type after eveluating formulae
                    //If it is formula cell, it will be evaluated otherwise no change will happen
                    switch (evaluator.evaluateInCell(cell).getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            //Not again
                            break;
                    }
                }
                System.out.println("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Date convertStringToDate(String dateString) {
        Date date = null;
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            date = df.parse(dateString);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return date;
    }
    /*
    public static void main(String[] args) throws Exception {

        String filePath = "d://dulieu_khnv.xls";
//        String filePath = "TestExternalLinks.xlsx";
        // String filePath = "TestExternalLinks.xls";

        Workbook workbook = WorkbookFactory.create(new FileInputStream(filePath));
        
        
        EvaluationWorkbook evalWorkbook = null;
        if (workbook instanceof HSSFWorkbook) {
            evalWorkbook = HSSFEvaluationWorkbook.create((HSSFWorkbook) workbook);
        } else if (workbook instanceof XSSFWorkbook) {
            evalWorkbook = XSSFEvaluationWorkbook.create((XSSFWorkbook) workbook);
        }

        Sheet sheet = workbook.getSheetAt(0);
        EvaluationSheet evalSheet = evalWorkbook.getSheet(0);

        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                    String cellFormula = cell.getCellFormula();
                    System.out.println(cellFormula);

                    EvaluationCell evaluationCell = evalSheet.getCell(cell.getRowIndex(), cell.getColumnIndex());
                    Ptg[] formulaTokens = evalWorkbook.getFormulaTokens(evaluationCell);
                    for (Ptg formulaToken : formulaTokens) {
                        int externalSheetIndex = -1;
                        if (formulaToken instanceof Ref3DPtg) {
                            Ref3DPtg refToken = (Ref3DPtg) formulaToken;
                            externalSheetIndex = refToken.getExternSheetIndex();
                        } else if (formulaToken instanceof Area3DPtg) {
                            Area3DPtg refToken = (Area3DPtg) formulaToken;
                            externalSheetIndex = refToken.getExternSheetIndex();
                        } else if (formulaToken instanceof Ref3DPxg) {
                            Ref3DPxg refToken = (Ref3DPxg) formulaToken;
                            externalSheetIndex = refToken.getExternalWorkbookNumber();
                        } else if (formulaToken instanceof Area3DPxg) {
                            Area3DPxg refToken = (Area3DPxg) formulaToken;
                            externalSheetIndex = refToken.getExternalWorkbookNumber();
                        }

                        if (externalSheetIndex >= 0) {
                            System.out.print("We have extrenal sheet index: " + externalSheetIndex
                                    + ". So this formula refers an external sheet in workbook: ");

                            ExternalSheet externalSheet = null;
                            if (workbook instanceof HSSFWorkbook) {
                                externalSheet = evalWorkbook.getExternalSheet(externalSheetIndex);
                            } else if (workbook instanceof XSSFWorkbook) {
                                externalSheet = evalWorkbook.getExternalSheet(null, null, externalSheetIndex);
                            }
                            String linkedFileName = externalSheet.getWorkbookName();
                            System.out.println(linkedFileName);

                        }
                    }
                }
            }
        }

        workbook.close();
    }
     */
}
