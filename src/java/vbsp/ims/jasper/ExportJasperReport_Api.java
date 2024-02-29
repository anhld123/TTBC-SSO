/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.jasper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRProperties;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.restapi.DuLieuNTService_Api1;
import vbsp.ims.restapi.ReportApi;
import vbsp.ims.restapi.LstParameter;
//import vbsp.ims.restapi.ReportApi.LstParameter;

/**
 *
 * @author LION
 */
public class ExportJasperReport_Api {

    private static DaoConnect oracleConn;
    DuLieuNTService_Api1 _apiReport;

    static {
        oracleConn = new DaoConnect();
    }
// quyennv 29/02/2024
    public void ExportJasperPdf(String strSourceFileJasper, HashMap<String, Object> paramHashMap, Connection connectdb, String strTargetFilePdf, String reportId) {
//        System.err.println("ExportJasperPdf -->" + paramHashMap.get("PARA_KYBC"));
        //lay ra duong dan cua file duoi dang File
        File scrFile = new File(strSourceFileJasper);
        //Kiem tra file xem co ton tai hay khong
        if (!scrFile.exists()) {
            System.err.println("Khong tim thay file " + strSourceFileJasper);
            CoreLogger.error(ExportJasperReport_Api.class.getCanonicalName() + " -->> ExportJasperPdf -->> Khong tim thay file " + strSourceFileJasper);
            return;
        }
        //Lay ra ten trong duong dan file vi du file truyen vao la E:\\Report_trunggian\\Candoi.jrxml
        //thi chi lay ra ten la Candoi
        String strNameFile = scrFile.getName().toUpperCase().replaceAll(".JRXML", "");
        //Khoi tao ngay tao file
        Date date = new Date();
        // long TimeReport = date.getTime();
        //Lay ra ten file can tao
        File localFile2 = null;
        File localFile3 = null;
        localFile2 = new File(strSourceFileJasper);
        localFile3 = new File(scrFile.getParentFile(), strNameFile + ".jasper");
        String strfilejasper = "";
        //System.err.println(localFile2.toString());
        //System.err.println(localFile3.toString());

        try {
            //thiet lap cac tham so
            JRProperties.setProperty(JRProperties.QUERY_EXECUTER_FACTORY_PREFIX + "plsql",
                    "com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");
            //kiem tra neu file da bien dich roi thi bo qua khong bien dich nua
            if (localFile3.exists()) {
                strfilejasper = localFile3.getCanonicalPath();
                //System.err.println("Da co file Jasper khong bien dich lai "+strfilejasper);
            } else {//truong hop chua bien dich file (chua co file .jasper)
                try {
                    //Bien dich ra file .jasper va duoc luu vao bien strfilejasper
                    strfilejasper = JasperCompileManager.compileReportToFile(localFile2.getCanonicalPath());
                } catch (JRException localJRException) {
                    //str12 = "999";
                    //System.err.println( "Technical JRException Occurred" + localJRException.getMessage();
                    System.err.println("Exception in JasperCompileManager :" + localJRException.toString());
                    CoreLogger.error(ExportJasperReport_Api.class.getCanonicalName() + " -->> ExportJasperPdf -->>  " + localJRException.getMessage());
                }
            }
            /*Bổ sung phần định dạng số khi xuất báo cáo - TrungNT88 */
            paramHashMap.put(JRParameter.REPORT_LOCALE, Locale.GERMANY);
            //bien dich file ra duoi dang .jspprint            
            JasperPrint localJasperPrint = JasperFillManager.fillReport(strfilejasper, paramHashMap, connectdb);
       
            Set<String> keySet = paramHashMap.keySet();
            ArrayList<String> listOfKeys = new ArrayList<String>(keySet);
            Collection<Object> values = paramHashMap.values();
            ArrayList<Object> listOfValues = new ArrayList<>(values);

            ArrayList<LstParameter> _LstParameter = new ArrayList<>();
            // lấy các tham số para trên màn hình
            int k = paramHashMap.size();
            for (int i = 0; i < k; i++) {
                String key = listOfKeys.get(i);
                if (key.startsWith("PARA_")) {
                    LstParameter row = new LstParameter();
                    row.setParaName(key);
                    row.setParaValue(listOfValues.get(i).toString());
                    row.setParaType("");
//                    if (key.equals("PARA_MAPGD")) {
//                        row.setParaType("C");
//                    } else if (key.equals("PARA_DENNGAY")) {
//                        row.setParaType("D");
//                    } else if (key.equals("PARA_TONGHOP")) {
//                        row.setParaType("C");
//                    }

                    _LstParameter.add(row);
                }
            }
            _apiReport = new DuLieuNTService_Api1();
            ReportApi _reportApi = new ReportApi();
            _reportApi.setReportId(reportId);
            _reportApi.setLstParameter(_LstParameter);
            // gọi API lấy chuỗi 64          
            ReportApi kk = _apiReport.Report_Api(reportId, _reportApi);

            File file = new File(strTargetFilePdf);
            
            try (FileOutputStream fos = new FileOutputStream(file);) {
                // To be short I use a corrupted PDF string, so make sure to use a valid one if you want to preview the PDF file
                String b64 = kk.data;
                byte[] decoder = Base64.getDecoder().decode(b64);

                fos.write(decoder);
                System.out.println("PDF File Saved");
                
                //tao ra file pdf
//            JasperExportManager.exportReportToPdfFile(localJasperPrint, strTargetFilePdf);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
            // TODO: handle exception
            CoreLogger.error(ExportJasperReport_Api.class.getCanonicalName() + " -->> ExportJasperPdf -->>  " + scrFile.getName() + "  -> " + e.getMessage());
            System.gc();
            return;
        } catch (JRException e) {
            System.err.println(e.getMessage());
            // TODO: handle exception
            CoreLogger.error(ExportJasperReport_Api.class.getCanonicalName() + " -->> ExportJasperPdf -->>  " + scrFile.getName() + "  -> " + e.getMessage());
            System.gc();
            return;
        }

        System.gc();
    }

    public void ExportJasperPdf(String strSourceFileJasper, HashMap<String, Object> paramHashMap, String strTargetFilePdf, String reportId) {
        ExportJasperPdf(strSourceFileJasper, paramHashMap, oracleConn.getConnect(), strTargetFilePdf, reportId);
    }

    public void ExportJasperExcel(String strSourceFileJasper, HashMap<String, Object> paramHashMap, Connection connectdb, String strFileName, String reportId) {
        //lay ra duong dan cua file duoi dang File
        File scrFile = new File(strSourceFileJasper);
        //Kiem tra file xem co ton tai hay khong
        if (!scrFile.exists()) {
            System.err.println("Khong tim thay file " + strSourceFileJasper);
            CoreLogger
                    .error(ExportJasperReport_Api.class
                            .getCanonicalName() + " -->> ExportJasperExcel -->> Khong tim thay file " + strSourceFileJasper);
            return;
        }
        //Lay ra ten trong duong dan file vi du file truyen vao la 
        //thi chi lay ra ten la Candoi
        String strNameFile = scrFile.getName().toUpperCase().replaceAll(".JRXML", "");
        //Khoi tao ngay tao file
        Date date = new Date();
        //long TimeReport = date.getTime();
        //Lay ra ten file can tao

        System.err.println(strFileName);
        File localFile2;
        File localFile3;
        localFile2 = new File(strSourceFileJasper);
        localFile3 = new File(scrFile.getParentFile(), strNameFile + ".jasper");
        String strfilejasper = "";
        //System.err.println(localFile2.toString());
        //System.err.println(localFile3.toString());

        try {
            //thiet lap cac tham so
            JRProperties.setProperty("net.sf.jasperreports.xpath.executer.factory", "net.sf.jasperreports.engine.util.xml.JaxenXPathExecuterFactory");
            JRProperties.setProperty(JRProperties.QUERY_EXECUTER_FACTORY_PREFIX + "plsql",
                    "com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");
//            JRProperties.setProperty(JRXlsExporterParameter.PROPERTY_IGNORE_PAGE_MARGINS, strNameFile);

            //kiem tra neu file da bien dich roi thi bo qua khong bien dich nua
            if (localFile3.exists()) {
                strfilejasper = localFile3.getCanonicalPath();
                //System.err.println("Da co file Jasper khong bien dich lai "+strfilejasper);
            } else {//truong hop chua bien dich file (chua co file .jasper)
                try {
                    //Bien dich ra file .jasper va duoc luu vao bien strfilejasper
                    strfilejasper = JasperCompileManager.compileReportToFile(localFile2.getCanonicalPath());
                } catch (JRException localJRException) {
                    //str12 = "999";
                    //System.err.println( "Technical JRException Occurred" + localJRException.getMessage();
                    System.err.println("Exception in JasperCompileManager :" + localJRException.toString());

                    CoreLogger
                            .error(ExportJasperReport_Api.class
                                    .getCanonicalName() + " -->> ExportJasperExcel -->> " + scrFile.getName() + "  -> " + localJRException.getMessage());
                }
            }

            /*Bổ sung phần định dạng số khi xuất báo cáo - TrungNT88 */
            paramHashMap.put(JRParameter.REPORT_LOCALE, Locale.GERMANY);
            //bien dich file ra duoi dang .jspprint
            JasperPrint localJasperPrint = JasperFillManager.fillReport(strfilejasper, paramHashMap, connectdb);
            OutputStream output = new FileOutputStream(new File(strFileName));
            JRXlsxExporter exporterXls = new JRXlsxExporter();
            exporterXls.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
//            exporterXls.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, true);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, true);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true);
            exporterXls.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
            exporterXls.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
            exporterXls.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
            exporterXls.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, Boolean.FALSE);
            exporterXls.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BACKGROUND, Boolean.FALSE);
//            exporterXls.setParameter(JRXlsExporterParameter.PROPERTY_IGNORE_PAGE_MARGINS);
            // exporterXls.setParameter(JRXlsExporterParameter., print);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, true);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, false);
            exporterXls.setParameter(JRXlsExporterParameter.JASPER_PRINT, localJasperPrint);
            exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
            exporterXls.exportReport();

        } catch (IOException | JRException e) {
            System.err.println(e.getMessage());
            // TODO: handle exception
            CoreLogger
                    .error(ExportJasperReport_Api.class
                            .getCanonicalName() + " -->> ExportJasperExcel -->> " + scrFile.getName() + "  -> " + e.getMessage());
            System.gc();
            return;
        }
        System.gc();
    }

    public void ExportJasperExcel(String strSourceFileJasper, HashMap<String, Object> paramHashMap, String strFileName, String reportId) {
        ExportJasperExcel(strSourceFileJasper, paramHashMap, oracleConn.getConnect(), strFileName, reportId);
    }
}
