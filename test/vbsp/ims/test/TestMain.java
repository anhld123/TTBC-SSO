package vbsp.ims.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoCreditPlan;
import vbsp.ims.dao.DaoExportHstdct;
import vbsp.ims.dao.DaoRptQuery;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.jasper.*;
import vbsp.ims.report.fast.ListValue;

public class TestMain {

    /**
     * @param args
     */
    
    public static List<File> listf(String directoryName) {
        File directory = new File(directoryName);

        List<File> resultList = new ArrayList<File>();

        
        // get all the files from a directory
        File[] fList = directory.listFiles();
        //resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isFile()) {
               // System.out.println(file.getAbsolutePath());
                resultList.add(file);
            } else 
                if (file.isDirectory()) {
                resultList.addAll(listf(file.getAbsolutePath()));
            }
        }
        //System.out.println(fList);
        return resultList;
    } 
     public static boolean InsertLog(Connection conn,String spos_cd,String sFileName) throws SQLException
        {
            Statement statement = null;
 
		String insertTableSQL = "insert into file_send(pos_cd,file_name)"
                        + " values('"+spos_cd+"','"+sFileName+"')";
 
		try {
//                        Connection conn = new DaoConnect().getConnect();
			statement = conn.createStatement();
 
			System.out.println(insertTableSQL);
 
			// execute insert SQL stetement
			statement.executeUpdate(insertTableSQL);
 
			System.out.println("Record is inserted into DBUSER table!");
 
		} catch (Exception e) {
 
			System.out.println("Loi dong ket noi"+e.getMessage());
 
		} finally {
 
			if (statement != null) {
				statement.close();
			}
		}
  
            
            return true;
        }
    public static void scanFileXmlSendWebServices(String strPath)
    {
        try {
            if(!strPath.endsWith("/"))
            strPath+="/";
        
        List<File> listFile = DefineFun.listfilexml(strPath);
        if(listFile.isEmpty())
            return;
        Connection conn = new DaoConnect().getConnect();
        for(File file: listFile)
        {
            String sFileName =file.getName();
            String sPoscd = sFileName.substring(0, 6);
            if(file.getName().toUpperCase().endsWith("XML"))
            {
                InsertLog(conn, sPoscd, sFileName);
                System.err.println("sPoscd="+sPoscd+"  -> sFileName="+sFileName);
            }
//                SendFileToWebServices(file);
            
        }
        if(conn !=null)
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }
    public static void main(String[] args) throws SQLException {
        
//         Connection connectdb = null;
//                DaoConnect daoconnect = new DaoConnect();
//                String strSourceFileJasper = "I:\\PROJECT\\IMS_REPORTS\\IMS_REPORTS\\web\\REPORTS\\BCDHTD_01.1.jrxml";
//                HashMap<String, Object> paramHashMap = new HashMap<String, Object>();
//                paramHashMap.put("PARA_DENNGAY", "28-FEB-2015");
//                paramHashMap.put("PARA_MAPGD", "002501");
//                 paramHashMap.put("PARA_TONGHOP", "N");
//                
//                //          paramHashMap.put("EOD_DATE".toUpperCase(), "14-apr-2014");
//                //        paramHashMap.put("POS_CD".toUpperCase(), "000301");
//                //        paramHashMap.put("CONS_FLAG".toUpperCase(), "N");
//                //   */
//                 connectdb = daoconnect.getConnect();
//  
//                String strFileName = "F:\\A01_A_SBV_time_core.pdf";
//                ExportJasperReport exportjasper = new ExportJasperReport();
//                exportjasper.ExportJasperPdf(strSourceFileJasper, paramHashMap, connectdb, strFileName);
//        
//                if(connectdb!=null) connectdb.close();
        
        DaoRptQuery daoQuery = new DaoRptQuery();
        
//        List<ListValue> lstPos = daoQuery.getPosGeneralReport("002721", "N");
//        
//        for(ListValue value:lstPos)
//            System.err.println(value.getsKey()+" -> "+value.getsDesc());
        Map<String,String> paramHashMap=new HashMap<String,String>();
        
        paramHashMap.put("PV_CHTRINH", "01");
       // paramHashMap.put("PV_POS_CD", "002721");
        paramHashMap.put("PD_REPORT_DATE", "28-feb-2015");
        //daoQuery.exportExcelQuery(paramHashMap, "QUERY0000000166", "D:\\test.xlsx");
        
       daoQuery.getDataExp("QUERY0000000166", paramHashMap,
                "002721", "PV_POS_CD", "Y","F:\\test1.xlsx");
//        SimpleDateFormat datetemp = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date cellValue = datetemp.parse("2013-12-05 00:00:00.0");
//            
//            System.err.println(cellValue);
//        } catch (ParseException ex) {
//            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    
//        String strPath="C:\\DATA";
//         if(!strPath.endsWith("\\"))
//         {
//            strPath+="\\";
//             System.err.println(strPath);
//         }
//        List<File> fList = listf("C:\\DATA\\");
        
        //for(File file:fList)
//                String str="(2+10)/2-2*4";
//                //DefineFun.checkCaculator("[1]");
//                
//                System.err.println(DefineFun.isOperator(str));
//                String strs="";
//                if(strs.isEmpty())
//                    System.err.println("Chuoi la rong");
//                 if(DefineFun.isOperator(str))
//                    System.err.println("Chuoi la rong");
                //System.err.println("Ten file "+file.getCanonicalPath());
                
//        Writer out=null;
//        String aString="Nguyễn văn từng";
//        try {
//            out = new BufferedWriter(new OutputStreamWriter(
//                    new FileOutputStream("C:\\hstd.001"), "UTF-8"));
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
//        }
//            
//            try {
//                out.write(aString);
//                 out.close();
//                } catch (IOException ex) {
//                Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            
//        //System.err.println(new Date());
//        
//        //System.err.println("RPTFAST_KU_NGAYBC".substring(8));
//        
//        Connection connectdb = null;
//        DaoConnect daoconnect = new DaoConnect();
//        connectdb = daoconnect.getConnect("10.63.8.58", "DBREPORT", 1521, "intellect", "imsrpt321#");

//        DaoExportHstdct daoExport = new DaoExportHstdct();
                // daoExport.getDataExportFile(connectdb, "002501", "30-apr-2014", "HSKU", "C:\\");
                // DaoCreditPlan daocredit = new DaoCreditPlan();
                // daocredit.getDataPosTreeNode("hagiangth", "Y");
                /*String strMa_pgd="VARCHAR2_MAPGD";
                
                System.err.println("Ma pgd "+strMa_pgd.substring(2, 6));
                String strTime=Long.toString(System.currentTimeMillis());
                
                System.err.println(strTime+" TIme "+strTime.substring(strTime.length()-4, strTime.length()));
                
                Connection connectdb = null;
                DaoConnect daoconnect = new DaoConnect();
                String strSourceFileJasper = "D:\\reports\\A01_A_SBV.jrxml";
                HashMap<String, Object> paramHashMap = new HashMap<String, Object>();
                //paramHashMap.put("para_denngay", "14-apr-2014");
                //paramHashMap.put("para_mapgd", "003401");
                // paramHashMap.put("para_tonghop", "N");
                
                //          paramHashMap.put("EOD_DATE".toUpperCase(), "14-apr-2014");
                //        paramHashMap.put("POS_CD".toUpperCase(), "000301");
                //        paramHashMap.put("CONS_FLAG".toUpperCase(), "N");
                //   */
                /* connectdb = daoconnect.getConnect("10.63.8.59", "vbsprepo", 1521, "intellect", "dbrpt321#");
                DaoReportFast report = new DaoReportFast();
                String strFileName = "D:\\reports\\A01_A_SBV_time.pdf";
                ExportJasperReport exportjasper = new ExportJasperReport();
                exportjasper.ExportJasperPdf(strSourceFileJasper, paramHashMap, connectdb, strFileName);
                
                
                
                
                
                /*  String strPara = "FROM_DATE_DATE";
                
                System.err.println("Lay ra " + strPara.substring(0, strPara.length() - 5));
                
                String sFullPath = "J:\\PROJECT\\IMS_REPORTS\\build\\web\\EXPORT_REPORT\\XLS\\RPTFAST0000000016_1398175125386.xlsx";
                
                //        File file = new File(sFullPath);
                //        System.err.println("Ten file "+file.getName());
                //
                //        System.err.println("Ten file "+file.getParent());
                //
                //          System.err.println("Ten file "+file.getParentFile());
                String str = "%{fieldName}_TEXT";
                if (str.indexOf("TEXT") > 0 || str.indexOf("DATE") > 0 || str.indexOf("LIST") > 0) {
                System.err.println("Dung la du lieu can lay");
                }
                
                String strDate = "2014-04-24T00:00:00+07:00";
                try {
                Date sdf = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
                
                String strPutDate = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);
                System.err.println(strPutDate);
                //        try {
                
                //        String strString="KU_KYQUYFLG - C? ?ánh d?u món vay có thu?c lo?i món vay ký qu?";
                //        ArrayList<String> liststring=new ArrayList<String>(Arrays.asList(strString.split("-",1)));
                //        for(int i=0;i<liststring.size();i++)
                //            System.err.println(liststring.get(i));
                //
                //        System.err.println(strString.substring(0,strString.indexOf(" - ")));
                /*
                // TODO Auto-generated method stub
                String strSourceFileJasper = "E:\\Report_trunggian\\Candoi.jrxml";
                File scrFile = new File(strSourceFileJasper);
                
                System.err.println(scrFile.getParentFile());
                
                String strFileNameSource = scrFile.getName();
                System.err.println(strFileNameSource);
                int pos = strFileNameSource.indexOf('.');
                String strName = strFileNameSource.substring(0, pos);
                System.err.println(strName);
                ExportJasperReport compiler = new ExportJasperReport();
                Connection connectdb = null;
                DaoConnect daoconnect = new DaoConnect();
                Define.M_ROOT = "E:/Report_trunggian/";
                
                HashMap<String, Object> paramHashMap = new HashMap<String, Object>();
                paramHashMap.put("EOD_DATE", "31-mar-2014");
                paramHashMap.put("POS_CD", "000301");
                connectdb = daoconnect.getConnect("10.63.8.58", "dbreport", 1521, "intellect", "intellect");
                DaoReportFast report = new DaoReportFast();
                String strFileName = "F:\\gl_vbsp.xlsx";
                report.ExportExcelFromQry(connectdb, " select * from gl_vbsp", strFileName);
                compiler.ExportPdf(connectdb);
                //connectdb=daoconnect.getConnect("127.0.0.1", "dbreport", 1521, "intellect", "intellect");
                String strFilepdf = compiler.ExportJasperPdf(strSourceFileJasper, paramHashMap, connectdb);
                System.err.println(strFilepdf);
                */
//        } catch (ParseException ex) {
//            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
//        }


    }

		//
//	String strSourceFileJasper="E:\\Report_trunggian\\Candoi.jrxml";
//	HashMap<String, Object> paramHashMap =new HashMap<String, Object>();
//	DaoConnect dao = new DaoConnect();
//	
//	Connection connectdb=dao.getConnect("10.63.8.58", "dbreport", 1521, "intellect", "intellect");
//	
//	CompilerJasper jrcomm = new CompilerJasper();
//	jrcomm.
}
