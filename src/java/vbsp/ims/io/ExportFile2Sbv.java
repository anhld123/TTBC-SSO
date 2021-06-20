/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.io;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import vbsp.ims.define.Define;
import vbsp.ims.export.excel.ExportFileHstdCt;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author Trung
 */
public class ExportFile2Sbv {
    //--------------------------------------------------------------------------
    private static final int _datacol = 8;
    private static final int _b05totalfield = 9;
    //--------------------------------------------------------------------------  
    public boolean ExportFile(String report,String period,String strFileName, ResultSet reset) 
            throws IOException, FileNotFoundException, InvalidFormatException{
        if (report.equals("B05A")){
            return ExportExcelB05(strFileName,period,reset);
        }else {
            return ExportTextFile(strFileName,period,reset);
        }
    }
    public boolean ExportTextFile(String strFileName,String period, ResultSet reset)
    {
        boolean bSuccess=false;
        try {
            Writer outfile = null;
            try {
                outfile = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(strFileName), "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                CoreLogger.error(ExportFileHstdCt.class.getCanonicalName() 
                        + " Loi khi khoi tao UTF-8 ExportFileHstdCt -> " + ex.getMessage());
            }
            try {
                while(reset.next())
                {
                    String strRow = reset.getString(_datacol) + "\r\n";
                    Writer append = outfile.append(strRow);
                }                
                outfile.flush();
                outfile.close();
                bSuccess=true;
            } catch (IOException efile) {
                CoreLogger.error(ExportFileHstdCt.class.getCanonicalName() 
                        + " Loi khi ghi file ExportFile2Sbv -> " + efile.getMessage());
            }
        } catch (FileNotFoundException | SQLException e) {
            CoreLogger.error(ExportFileHstdCt.class.getCanonicalName() 
                    + " Loi khi getdata ExportFile2Sbv -> " + e.getMessage());
        }
        return bSuccess;
    }
    //--------------------------------------------------------------------------    
    public boolean ExportExcelB05(String strFileName,String period,ResultSet reset) 
		throws FileNotFoundException, IOException, InvalidFormatException {
        //Mo file excel
        String fullpath= Define.M_ROOT + Define.M_EXCEL_TEMP;
        boolean isSuccess;
        if (period.equals("A"))
            fullpath = fullpath + "TM_N.XLS";
        else
            fullpath = fullpath + "TM_Q.XLS";
        InputStream inp = new FileInputStream(fullpath);
        Workbook thuyetminh_nam = WorkbookFactory.create(inp);
        Sheet sheet = thuyetminh_nam.getSheetAt(0);
        String strToaDo = "";
        try {
            while (reset.next()) {                
                for (int i = 1; i <= _b05totalfield; i++) {
                    strToaDo = reset.getString("COT" + String.valueOf(i).trim());
                    System.err.println("Toa do: " + strToaDo);
                    if (strToaDo != null) {
                        try {
                            String[] valu = strToaDo.split(",");
                            Row row = sheet.getRow(Integer.parseInt(valu[1].trim()));
                            Cell cell = row.getCell(Integer.parseInt(valu[0].trim()));
                            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                            cell.setCellValue( Double.parseDouble(reset.getString("GIATRI" + String.valueOf(i).trim())));
                        } catch(Exception ex  ){
                            System.err.println("Error toa do: " + strToaDo + "-->" + ex.getMessage());
                        }
                    }
                }
            }
            HSSFFormulaEvaluator.evaluateAllFormulaCells(thuyetminh_nam);
            FileOutputStream out = new FileOutputStream(strFileName);
            thuyetminh_nam.write(out);                       
            isSuccess = true;
        } catch (SQLException | NumberFormatException | IOException ex) {
            isSuccess = false;
            //ex.printStackTrace();
            System.err.println("Error toa do: " + strToaDo);
            System.err.println(ExportFile2Sbv.class.getCanonicalName() + ex.getMessage());
        }
        return isSuccess;
    }
    //--------------------------------------------------------------------------
}
