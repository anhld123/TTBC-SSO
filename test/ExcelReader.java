
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author BAOANH
 */
public class ExcelReader {

    public static final String SAMPLE_XLSX_FILE_PATH = "d://dulieu_khnv.xls";

    public static void main(String[] args) throws IOException, InvalidFormatException {

        
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
      
        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        /*
           =============================================================
           Iterating over all the sheets in the workbook (Multiple ways)
           =============================================================
         */
        // 1. You can obtain a sheetIterator and iterate over it
//        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
//        System.out.println("Retrieving Sheets using Iterator");
//        while (sheetIterator.hasNext()) {
//            Sheet sheet = sheetIterator.next();
//            
//            System.out.println("=> " + sheet.getSheetName());
//        }

        // 2. Or you can use a for-each loop
        System.out.println("Retrieving Sheets using for-each loop");
        for (Sheet sheet : workbook) {
            
            System.out.println(" => " + sheet.getSheetName());
        }

        // 3. Or you can use a Java 8 forEach with lambda
//        System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
//        workbook.forEach(sheet -> {
//            System.out.println("=> " + sheet.getSheetName());
//        });

        /*
           ==================================================================
           Iterating over all the rows and columns in a Sheet (Multiple ways)
           ==================================================================
         */
        // Getting the Sheet at index zero
        
   
         Sheet sheet =null;
         for (Sheet sheetfor : workbook) {
            String Sheetname=sheetfor.getSheetName();
            System.out.println(" => " + Sheetname);
            if(Sheetname.contains("T1")) 
            {
                System.out.println("--------------------------------------------- T1");
                sheet=sheetfor;
            }
        }
        System.out.println("Sheet name="+sheet.getSheetName());
        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();
//        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        // 1. You can obtain a rowIterator and columnIterator and iterate over them
        System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            System.out.println(" row.getRowNum()=" + row.getRowNum());
            // Now let's iterate over the columns of the current row
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
//                String cellValue = dataFormatter.formatCellValue(cell);
//                System.out.print(cellValue + "\t");
                int cellType = cell.getCellType();
                
                switch (cellType) {
                   
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        System.out.print("\t");
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        System.out.print("");
                        System.out.print("\t");
                        break;
                    case Cell.CELL_TYPE_FORMULA:

                        // Công thức
                        System.out.print(cell.getNumericCellValue());
                        
                        System.out.print("<== \t");

                       // FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

                        // In ra giá trị từ công thức
                        //System.out.print(evaluator.evaluate(cell).getNumberValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        System.out.print("\t");
                        break;
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue());
                        System.out.print("\t");
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        System.out.print("!");
                        System.out.print("\t");
                        break;
                }
            }
            System.out.println();
        }

        // 2. Or you can use a for-each loop to iterate over the rows and columns
//        System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
//        for (Row row : sheet) {
//            for (Cell cell : row) {
//                String cellValue = dataFormatter.formatCellValue(cell);
//                System.out.print(cellValue + "\t");
//            }
//            System.out.println();
//        }
        // 3. Or you can use Java 8 forEach loop with lambda
        System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");
//        sheet.forEach(row -> {
//            row.forEach(cell -> {
//                String cellValue = dataFormatter.formatCellValue(cell);
//                System.out.print(cellValue + "\t");
//            });
//            
//            
//            System.out.println();
//        });

        // Closing the workbook
        workbook.close();
    }
}
