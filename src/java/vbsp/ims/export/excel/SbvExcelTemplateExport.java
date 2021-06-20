/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.export.excel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author Trung
 * sua ngay 14/jan/2015
 */
public class SbvExcelTemplateExport {

    //--------------------------------------------------------------------------

    private static DaoConnect daoConnect;
    private static Connection conn;

    public SbvExcelTemplateExport() {
        daoConnect = new DaoConnect();
        conn = daoConnect.getConnect();
    }
    
    public String generateExcelFile(String sReport,String sPosCode, String sConflag, String sNgaybc,
            String sPeriod, String sPath){
        String lcfullPath = "";
        switch(sReport){
            case "TT31_B29":
                lcfullPath = gen_B29_Report(sPosCode,sConflag,sNgaybc,sPeriod,sPath);
                break;
            case "TT31_B30":
                lcfullPath = gen_B30_Report(sPosCode,sConflag,sNgaybc,sPeriod,sPath);
                break;    
            case "TT31_B20":
                lcfullPath = gen_B20_Report(sPosCode,sConflag,sNgaybc,sPeriod,sPath);
                break;      
            case "TT31_B09":
                lcfullPath = gen_B09_Report(sPosCode,sConflag,sNgaybc,sPeriod,sPath);
                break;          
            case "TT31_B28":
                lcfullPath = gen_B28_Report(sPosCode,sConflag,sNgaybc,sPeriod,sPath);
                break;       
            case "TT31_B35":
                lcfullPath = gen_B35_Report(sPosCode,sConflag,sNgaybc,sPeriod,sPath);
                break;
            case "01_NHCS":
                lcfullPath = gen_01NHSC_Report(sPosCode,sConflag,sNgaybc,sPeriod,sPath);
                break;  
            case "BC_30A":
                lcfullPath = gen_30A_Report(sPosCode,sConflag,sNgaybc,sPeriod,sPath);
                break;    
            case "TT31_B20TM":
                lcfullPath = gen_B20TM_Report(sPosCode, sConflag, sNgaybc, sPeriod, sPath);
                break;  
            case "TT31_B29TM":
                lcfullPath = gen_B29TM_Report(sPosCode, sConflag, sNgaybc, sPeriod, sPath);
                break;    
            case "B65_NHNN":
                lcfullPath = gen_B65_NHNN_Report(sPosCode, sConflag, sNgaybc, sPeriod, sPath);
                break;    
        }
        return lcfullPath;
    }
    
    public String gen_B29_Report(String sPosCode, String sConflag, String sNgaybc,
            String sPeriod, String sPath) {
        String sfileName, sfullPath = "", sTimeStamp, sSubTitle, sNameBr;
        //Khai bao truy van        
        try {
            String strQuery = "{call P_APP_REP_13B(?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement calstatement;
            ResultSet reset1, reset2;
            //Thuc hien execute truy van
            calstatement = conn.prepareCall(strQuery, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, "B29");
            calstatement.setString(2, sNgaybc);
            calstatement.setString(3, sPosCode);
            calstatement.setString(4, sConflag);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //Lay cursor ra resultset
            reset1 = (ResultSet) calstatement.getObject(9);
            reset2 = (ResultSet) calstatement.getObject(10);
            sfileName = (String) calstatement.getObject(5);
            sSubTitle = (String) calstatement.getObject(6);
            sTimeStamp = (String) calstatement.getObject(7);
            sNameBr = (String) calstatement.getObject(8);

            ExportExcelFileTT31 exp = new ExportExcelFileTT31();

            sfullPath = sPath + sfileName + ".xls";

            exp.export2FileExcel_Rs(reset1, 10, 3, sfullPath, "B29", 1);
            exp.export2FileExcel_Rs(reset2, 42, 3, sfullPath, "B29", 2);
            exp.setExcelSubTitle(sfullPath, sNameBr, 0, 0, "", 1, 1);
            exp.setExcelSubTitle(sfullPath, sTimeStamp, 3, 0, sSubTitle, 63, 17);

            if (reset1 != null) {
                reset1.close();
            }
            if (reset2 != null) {
                reset2.close();
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return sfullPath;
    }

    public String gen_B30_Report(String sPosCode, String sConflag, String sNgaybc,
            String sPeriod, String sPath) {
        if(sPosCode != "000100")
        {
            return "";
        }
        String sfileName, sfullPath = "", sTimeStamp, sSubTitle;
        //Khai bao truy van        
        try {
            String strQuery = "{call P_APP_REP_B30(?,?,?,?,?)}";
            CallableStatement calstatement;
            ResultSet reset1;
            //Thuc hien execute truy van
            calstatement = conn.prepareCall(strQuery, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sNgaybc);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
                //Lay cursor ra resultset

            sfileName = (String) calstatement.getObject(2);
            sSubTitle = (String) calstatement.getObject(3);
            sTimeStamp = (String) calstatement.getObject(4);
            reset1 = (ResultSet) calstatement.getObject(5);

            ExportExcelFileTT31 exp = new ExportExcelFileTT31();

            sfullPath = sPath + sfileName + ".xls";
            exp.export2FileExcel_Rs(reset1, 15, 3, sfullPath, "B30", 1);
            exp.setExcelSubTitle(sfullPath, sTimeStamp, 5, 0, sSubTitle, 27, 13);
            if (reset1 != null) {
                reset1.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return sfullPath;
    }

    public String gen_B28_Report(String sPosCode, String sConflag, String sNgaybc,
            String sPeriod, String sPath) {
        if(sPosCode != "000100")
        {
            return "";
        }
        String sfileName, sfullPath = "", sTimeStamp, sSubTitle;
        //Khai bao truy van        
        try {
            String strQuery = "{call P_APP_REP_B28(?,?,?,?,?)}";
            CallableStatement calstatement;
            ResultSet reset1;
            //Thuc hien execute truy van
            calstatement = conn.prepareCall(strQuery, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sNgaybc);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
                //Lay cursor ra resultset

            sfileName = (String) calstatement.getObject(2);
            sSubTitle = (String) calstatement.getObject(3);
            sTimeStamp = (String) calstatement.getObject(4);
            reset1 = (ResultSet) calstatement.getObject(5);

            ExportExcelFileTT31 exp = new ExportExcelFileTT31();

            sfullPath = sPath + sfileName + ".xls";
            exp.export2FileExcel_Rs(reset1, 8, 3, sfullPath, "B28", 1);
            exp.setExcelSubTitle(sfullPath, sTimeStamp, 4, 0, sSubTitle, 24, 2);
            if (reset1 != null) {
                reset1.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return sfullPath;
    }

    public String gen_30A_Report(String sPosCode, String sConflag, String sNgaybc,
            String sPeriod, String sPath) {
        String sfileName, sfullPath = "", sTimeStamp, sSubTitle;
        //Khai bao truy van        
        try {
            String strQuery = "{call P_APP_REP_30A(?,?,?,?,?)}";
            CallableStatement calstatement;
            ResultSet reset1;
            //Thuc hien execute truy van
            calstatement = conn.prepareCall(strQuery, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sNgaybc);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
                //Lay cursor ra resultset

            sfileName = (String) calstatement.getObject(2);
            sSubTitle = (String) calstatement.getObject(3);
            sTimeStamp = (String) calstatement.getObject(4);
            reset1 = (ResultSet) calstatement.getObject(5);

            ExportExcelFileTT31 exp = new ExportExcelFileTT31();

            sfullPath = sPath + sfileName + ".xls";
            exp.export2FileExcel_Rs(reset1, 10, 4, sfullPath, "30A", 1);
            exp.setExcelSubTitle(sfullPath, sTimeStamp, 3, 0, sSubTitle, 24, 2);
            if (reset1 != null) {
                reset1.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return sfullPath;
    }

    public String gen_01NHSC_Report(String sPosCode, String sConflag, String sNgaybc,
            String sPeriod, String sPath) {
        String sfileName, sfullPath = "", sTimeStamp, sSubTitle;
        //Khai bao truy van        
        try {
            String strQuery = "{call P_APP_BC01NHCS(?,?,?,?,?)}";
            CallableStatement calstatement;
            ResultSet reset1;
            //Thuc hien execute truy van
            calstatement = conn.prepareCall(strQuery, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sNgaybc);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
                //Lay cursor ra resultset

            sfileName = (String) calstatement.getObject(2);
            sSubTitle = (String) calstatement.getObject(3);
            sTimeStamp = (String) calstatement.getObject(4);
            reset1 = (ResultSet) calstatement.getObject(5);

            ExportExcelFileTT31 exp = new ExportExcelFileTT31();

            sfullPath = sPath + sfileName + ".xls";
            exp.export2FileExcel_Rs(reset1, 11, 3, sfullPath, "01HTD", 1);
//                exp.setExcelSubTitle(sfullPath,sTimeStamp, 4, 0, sSubTitle, 47, 8);
            if (reset1 != null) {
                reset1.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return sfullPath;
    }

    public String gen_B09_Report(String sPosCode, String sConflag, String sNgaybc,
            String sPeriod, String sPath) {
        if(sPosCode != "000100")
        {
            return "";
        }
        String sfileName, sfullPath = "", sTimeStamp, sSubTitle;
        //Khai bao truy van        
        try {
            String strQuery = "{call P_APP_REP_B09(?,?,?,?,?)}";
            CallableStatement calstatement;
            ResultSet reset1;
            //Thuc hien execute truy van
            calstatement = conn.prepareCall(strQuery, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sNgaybc);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
                //Lay cursor ra resultset

            sfileName = (String) calstatement.getObject(2);
            sSubTitle = (String) calstatement.getObject(3);
            sTimeStamp = (String) calstatement.getObject(4);
            reset1 = (ResultSet) calstatement.getObject(5);

            ExportExcelFileTT31 exp = new ExportExcelFileTT31();

            sfullPath = sPath + sfileName + ".xls";
            exp.export2FileExcel_Rs(reset1, 9, 3, sfullPath, "B09", 1);
            exp.setExcelSubTitle(sfullPath, sTimeStamp, 4, 0, sSubTitle, 10, 3);
            if (reset1 != null) {
                reset1.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return sfullPath;
    }
    
    public String gen_B65_NHNN_Report(String sPosCode, String sConflag, String sNgaybc,
            String sPeriod, String sPath) {
        if(sPosCode != "000100")
        {
            return "";
        }
        String sfileName, sfullPath = "", sTimeStamp, sSubTitle;
        //Khai bao truy van        
        try {
            String strQuery = "{call P_APP_REP_B65NHNN(?,?,?,?,?)}";
            CallableStatement calstatement;
            ResultSet reset1;
            //Thuc hien execute truy van
            calstatement = conn.prepareCall(strQuery, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sNgaybc);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
                //Lay cursor ra resultset

            sfileName = (String) calstatement.getObject(2);
            sSubTitle = (String) calstatement.getObject(3);
            sTimeStamp = (String) calstatement.getObject(4);
            reset1 = (ResultSet) calstatement.getObject(5);

            ExportExcelFileTT31 exp = new ExportExcelFileTT31();

            sfullPath = sPath + sfileName + ".xls";
            exp.export2FileExcel_Rs(reset1, 17, 4, sfullPath, "B65", 1);
            exp.setExcelSubTitle(sfullPath, sTimeStamp, 2, 0, sSubTitle, 10, 3);
            if (reset1 != null) {
                reset1.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return sfullPath;
    }

    public String gen_B35_Report(String sPosCode, String sConflag, String sNgaybc,
            String sPeriod, String sPath) {
        if(sPosCode != "000100")
        {
            return "";
        }
        String sfileName, sfullPath = "", sTimeStamp, sSubTitle;
        //Khai bao truy van        
        try {
            String strQuery = "{call P_APP_REP_B35(?,?,?,?,?)}";
            CallableStatement calstatement;
            ResultSet reset1;
            //Thuc hien execute truy van
            calstatement = conn.prepareCall(strQuery, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, sNgaybc);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
                //Lay cursor ra resultset

            sfileName = (String) calstatement.getObject(2);
            sSubTitle = (String) calstatement.getObject(3);
            sTimeStamp = (String) calstatement.getObject(4);
            reset1 = (ResultSet) calstatement.getObject(5);

            ExportExcelFileTT31 exp = new ExportExcelFileTT31();

            sfullPath = sPath + sfileName + ".xls";
            exp.export2FileExcel_Rs(reset1, 10, 3, sfullPath, "B35", 1);
            exp.setExcelSubTitle(sfullPath, sTimeStamp, 5, 0, "", 12, 4);
            if (reset1 != null) {
                reset1.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return sfullPath;
    }

    public String gen_B20_Report(String sPosCode, String sConflag, String sNgaybc,
            String sPeriod, String sPath) {

        String sfileName, sfullPath = "", sTimeStamp, sSubTitle, sNameBr;
        //Khai bao truy van        
        try {
            String strQuery = "{call P_APP_REP_B20_NEW(?,?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement calstatement;
            ResultSet reset1, reset2, reset3;
            //Thuc hien execute truy van
            calstatement = conn.prepareCall(strQuery, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, "B20");
            calstatement.setString(2, sNgaybc);
            calstatement.setString(3, sPosCode);
            calstatement.setString(4, sConflag);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.registerOutParameter(11, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //Lay cursor ra resultset
            reset1 = (ResultSet) calstatement.getObject(9);
            reset2 = (ResultSet) calstatement.getObject(10);
            reset3 = (ResultSet) calstatement.getObject(11);
            sfileName = (String) calstatement.getObject(5);
            sSubTitle = (String) calstatement.getObject(6);
            sTimeStamp = (String) calstatement.getObject(7);
            sNameBr = (String) calstatement.getObject(8);

            ExportExcelFileTT31 exp = new ExportExcelFileTT31();

            sfullPath = sPath + sfileName + ".xls";

            exp.export2FileExcel_Rs(reset1, 63, 3, sfullPath, "B20", 1);
            exp.export2FileExcel_Rs(reset2, 80, 3, sfullPath, "B20", 2);
            exp.export2FileExcel_Rs(reset3, 99, 3, sfullPath, "B20", 2);
            exp.setExcelSubTitle(sfullPath, sNameBr, 0, 0, "", 2, 2);
            exp.setExcelSubTitle(sfullPath, sTimeStamp, 5, 0, sSubTitle, 101, 14);

//                exp.setExcelSubTitle(sfullPath,sTimeStamp, 3, 0, sSubTitle, 63, 17);
            if (reset1 != null) {
                reset1.close();
            }
            if (reset2 != null) {
                reset2.close();
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return sfullPath;
    }

    public String gen_B20TM_Report(String sPosCode, String sConflag, String sNgaybc,
            String sPeriod, String sPath) {

        String sfileName, sfullPath = "", sBran_TQ, sBran_CN, sNameBr;
        //Khai bao truy van        
        try {
            String strQuery = "{call P_APP_REP_B20_TM(?,?,?,?,?,?,?)}";
            CallableStatement calstatement;
            //Thuc hien execute truy van
            calstatement = conn.prepareCall(strQuery, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, "B20");
            calstatement.setString(2, sNgaybc);
            calstatement.setString(3, sPosCode);
            calstatement.setString(4, sConflag);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //Lay cursor ra resultset

            sfileName = (String) calstatement.getObject(5);
            sBran_TQ = (String) calstatement.getObject(6);
            sBran_CN = (String) calstatement.getObject(7);

            sfullPath = sPath + sfileName + ".txt";

            try {
                String data = "";
                File file = new File(sfullPath);
                //if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }

                try {
                    Writer outfile = null;
                    try {
                        outfile = new BufferedWriter(new OutputStreamWriter(
                                new FileOutputStream(sfullPath), "UTF-8"));
                    } catch (UnsupportedEncodingException ex) {
                        CoreLogger.error(ExportFileHstdCt.class.getCanonicalName() + " Loi khi khoi tao UTF-8 ExportFileHstdCt -> " + ex.getMessage());
                    }
                    try {
                        if(sPosCode == "000100")
                        {
                             data = "@HD#01207004#01207000#1#Esign##" +"\r\n"   ;
                        }
                        else
                        {
                            data = "@HD#"+sBran_TQ + "#" + sBran_CN + "#1#Esign##" +"\r\n"   ;
                        }
                        
                        outfile.append(data);
                        String ngbc = "";
                           ngbc = sfileName.substring(4, 10);
                        
                        
                        data = "@B20#"+ngbc+"#Gui lai Bao cao#" +"\r\n";
                        outfile.append(data);
                        
                        if(sPosCode == "000100")
                        {
                             data = "@FT#01207004#01207000#1#"  +"\r\n" ;
                        }
                        else
                        {
                            data = "@FT#"+sBran_TQ + "#" + sBran_CN + "#1#"  +"\r\n" ;
                        }
                        outfile.append(data);

                        System.err.println("Bat dau export file");

                        outfile.flush();
                        outfile.close();
                    } catch (IOException efile) {
                        CoreLogger.error(ExportFileHstdCt.class.getCanonicalName() + " Loi khi ghi file ExportFileHstdCt -> " + efile.getMessage());
                    }
                } catch (Exception e) {
                    CoreLogger.error(ExportFileHstdCt.class.getCanonicalName() + " Loi khi getdata ExportFileHstdCt -> " + e.getMessage());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

//                exp.setExcelSubTitle(sfullPath,sTimeStamp, 3, 0, sSubTitle, 63, 17);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return sfullPath;
    }
    public String gen_B29TM_Report(String sPosCode, String sConflag, String sNgaybc,
            String sPeriod, String sPath) {

        String sfileName, sfullPath = "", sBran_TQ, sBran_CN, sNameBr;
        //Khai bao truy van        
        try {
            String strQuery = "{call P_APP_REP_B29_TM(?,?,?,?,?,?,?)}";
            CallableStatement calstatement;
            //Thuc hien execute truy van
            calstatement = conn.prepareCall(strQuery, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, "B29");
            calstatement.setString(2, sNgaybc);
            calstatement.setString(3, sPosCode);
            calstatement.setString(4, sConflag);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //Lay cursor ra resultset

            sfileName = (String) calstatement.getObject(5);
            sBran_TQ = (String) calstatement.getObject(6);
            sBran_CN = (String) calstatement.getObject(7);

            sfullPath = sPath + sfileName ;
//                    + ".txt";

            try {
                String data = "";
                File file = new File(sfullPath);
                //if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }

                try {
                    Writer outfile = null;
                    try {
                        outfile = new BufferedWriter(new OutputStreamWriter(
                                new FileOutputStream(sfullPath), "UTF-8"));
                    } catch (UnsupportedEncodingException ex) {
                        CoreLogger.error(ExportFileHstdCt.class.getCanonicalName() + " Loi khi khoi tao UTF-8 ExportFileHstdCt -> " + ex.getMessage());
                    }
                    try {
                        if(sPosCode == "000100")
                        {
                             data = "@HD#01207004#01207000#1#Esign##" +"\r\n"   ;
                        }
                        else
                        {
                            data = "@HD#"+sBran_TQ + "#" + sBran_CN + "#1#Esign##" +"\r\n"   ;
                        }
                        
                        outfile.append(data);
                        String ngbc = "";
                           ngbc = sfileName.substring(4, 10);
                        
                        
                        data = "@B29#"+ngbc+"#Gui lai Bao cao#" +"\r\n";
                        outfile.append(data);
                        
                        if(sPosCode == "000100")
                        {
                             data = "@FT#01207004#01207000#1#"  +"\r\n" ;
                        }
                        else
                        {
                            data = "@FT#"+sBran_TQ + "#" + sBran_CN + "#1#"  +"\r\n" ;
                        }
                        outfile.append(data);

                        System.err.println("Bat dau export file");

                        outfile.flush();
                        outfile.close();
                    } catch (IOException efile) {
                        CoreLogger.error(ExportFileHstdCt.class.getCanonicalName() + " Loi khi ghi file ExportFileHstdCt -> " + efile.getMessage());
                    }
                } catch (Exception e) {
                    CoreLogger.error(ExportFileHstdCt.class.getCanonicalName() + " Loi khi getdata ExportFileHstdCt -> " + e.getMessage());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

//                exp.setExcelSubTitle(sfullPath,sTimeStamp, 3, 0, sSubTitle, 63, 17);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
     
        return sfullPath;
    }
    
}
