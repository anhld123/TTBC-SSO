/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.export.excel;

import com.opensymphony.xwork2.Result;
import groovy.xml.Entity;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author LION
 */
public class ExportFileHstdCt {
    
   /* Writer out=null;
        String aString="Nguyễn văn từng";
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("C:\\hstd.001"), "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            try {
                out.write(aString);
                 out.close();
                } catch (IOException ex) {
                Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
            }
                
             */   
    public boolean ExportFileHstdCt(String strFileName, ResultSet reset)
    {
        boolean bSuccess=false;
        String strData = "";
        try {
            Writer outfile = null;
            try {
                outfile = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(strFileName), "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                CoreLogger.error(ExportFileHstdCt.class.getCanonicalName() + " Loi khi khoi tao UTF-8 ExportFileHstdCt -> " + ex.getMessage());
            }
            try {
                
                while(reset.next())
                {
                    String strRow = reset.getString(1)+"\n";
                    outfile.append(strRow);
//                    System.err.println(strRow);
                }
                System.err.println("Bat dau export file");

                outfile.flush();
                outfile.close();
                bSuccess=true;
            } catch (IOException efile) {
                CoreLogger.error(ExportFileHstdCt.class.getCanonicalName() + " Loi khi ghi file ExportFileHstdCt -> " + efile.getMessage());
            }
        } catch (Exception e) {
            CoreLogger.error(ExportFileHstdCt.class.getCanonicalName() + " Loi khi getdata ExportFileHstdCt -> " + e.getMessage());
        }
        return bSuccess;
    }
    
    public boolean ExportFileHstdCt_CRT(String reportDate,String reportType ,String rowNumber,String strFileName, ResultSet reset)
    {
        boolean bSuccess=false;
        String strData = "";
        try {
            Writer outfile = null;
            try {
                outfile = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(strFileName), "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                CoreLogger.error(ExportFileHstdCt.class.getCanonicalName() + " Loi khi khoi tao UTF-8 ExportFileHstdCt -> " + ex.getMessage());
            }
            try {
                if(reportType.equals("02"))
                {
                   // String fileName = strFileName.substring(strFileName.length()-12,strFileName.length()-4);
                   // String row1 = "DWT#DWT" + fileName.substring(2, 8) +"#"+ fileName + "#01207000#" + rowNumber + "\r\n";
                    rowNumber = rowNumber  ;
                    outfile.append(rowNumber);
                }
                else 
                {
                   // CTR#CTR1207000#20140602#01207000#143
                   // String fileName = strFileName.substring(strFileName.length()-12,strFileName.length()-4);
                   // String row1 = "CTR#CTR1207000#" + fileName + "#01207000#" + rowNumber + "\r\n";
                    rowNumber = rowNumber  ;
                    outfile.append(rowNumber);
                }
                while(reset.next())
                {
                    String strRow = "\r\n" + reset.getString(1);
                    outfile.append(strRow);
//                    System.err.println(strRow);
                }
                System.err.println("Bat dau export file");

                outfile.flush();
                outfile.close();
                bSuccess=true;
            } catch (IOException efile) {
                CoreLogger.error(ExportFileHstdCt.class.getCanonicalName() + " Loi khi ghi file ExportFileHstdCt -> " + efile.getMessage());
            }
        } catch (Exception e) {
            CoreLogger.error(ExportFileHstdCt.class.getCanonicalName() + " Loi khi getdata ExportFileHstdCt -> " + e.getMessage());
        }
        return bSuccess;
    }
}
