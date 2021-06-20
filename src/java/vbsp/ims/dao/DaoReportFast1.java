/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vbsp.ims.export.excel.ExportExcelFile;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author LION
 */
public class DaoReportFast1 {

    public ArrayList<String> getColumnReportFast2() {
        ArrayList<String> LstColumn = new ArrayList<String>();
        try {
            Connection connect = null;
            DaoConnect daoconn = new DaoConnect();

            connect = daoconn.getConnect();
            if (connect == null) {
                System.err.println("Loi khi goi lop ket noi csdl");
                return LstColumn;
            }

            String strQry = "Select * from master_columns_syn";
            Statement stm = connect.createStatement();

            ResultSet reset = stm.executeQuery(strQry);

            while (reset.next()) {
                LstColumn.add(reset.getString(1) + " - " + reset.getString(2) + " - " + reset.getString(3));
            }

            if (connect != null) {
                connect.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (reset != null) {
                reset.close();
            }

        } catch (Exception e) {
            System.err.println("Loi khi lay du lieu " + e.getMessage());
        }
        return LstColumn;
    }

    public List<String> getColumnReportFast1() {
        List<String> LstColumn = new ArrayList<String>();
        try {
            Connection connect = null;
            DaoConnect daoconn = new DaoConnect();

            connect = daoconn.getConnect();
            if (connect == null) {
                System.err.println("Loi khi goi lop ket noi csdl");
                return LstColumn;
            }

            String strQry = "Select * from master_columns_syn";
            Statement stm = connect.createStatement();

            ResultSet reset = stm.executeQuery(strQry);

            while (reset.next()) {
                LstColumn.add(reset.getString(1) + " - " + reset.getString(2) + " - " + reset.getString(3));
            }

            if (connect != null) {
                connect.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (reset != null) {
                reset.close();
            }

        } catch (Exception e) {
            System.err.println("Loi khi lay du lieu " + e.getMessage());
        }
        return LstColumn;
    }
    /*
     Ham nay lay ra tat ca cac cot duoc dua len bao cao fix trong csdl
     */

    public HashMap<String, ArrayList<Object>> getColumnReportFastArrObj(String strModuleID) {
        HashMap<String, ArrayList<Object>> ColumnRptFast = new HashMap<String, ArrayList<Object>>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.sp_get_column_report_fast(?, ?, ?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos    
                calstatement.setString(1, strModuleID);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);

                //Fill du lieu ra mang
                ArrayList<Object> ArrlstTableName = new ArrayList<Object>();
                ArrayList<Object> ArrlstColumnName = new ArrayList<Object>();
                ArrayList<Object> ArrlstColumnDesc = new ArrayList<Object>();
                while (reset.next()) {

                    ArrlstTableName.add(reset.getString("MCRF_TABLE_NAME"));
                    ArrlstColumnName.add(reset.getString("MCRF_COLUMN_NAME"));
                    ArrlstColumnDesc.add(reset.getString("MCRF_COLUMN_DESC"));

                    //System.err.println(reset.getString("MCRF_COLUMN_DESC"));
                }
                ColumnRptFast.put("MCRF_TABLE_NAME", ArrlstTableName);
                ColumnRptFast.put("MCRF_COLUMN_NAME", ArrlstColumnName);
                ColumnRptFast.put("MCRF_COLUMN_DESC", ArrlstColumnDesc);
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham getColumnReportFast " + e.getMessage());
                CoreLogger.error(this.getClass().getCanonicalName() + " getColumnReportFastArrObj -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getColumnReportFast " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getColumnReportFastArrObj -> " + e.getMessage());
        }
        return ColumnRptFast;
    }

    public HashMap<String, ArrayList<Object>> getModuleReportFast() {
        HashMap<String, ArrayList<Object>> ModuleRptFast = new HashMap<String, ArrayList<Object>>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.sp_get_module_report_fast(?, ?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos           
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(1);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(2);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(3);

                //Fill du lieu ra mang
                ArrayList<Object> ArrlstModuleID = new ArrayList<Object>();
                ArrayList<Object> ArrlstModuleName = new ArrayList<Object>();
                ArrayList<Object> ArrlstModuleDesc = new ArrayList<Object>();
                while (reset.next()) {

                    ArrlstModuleID.add(reset.getString("MMRF_MODULE_ID"));
                    ArrlstModuleName.add(reset.getString("MMRF_MODULE_NAME"));
                    ArrlstModuleDesc.add(reset.getString("MMRF_MODULE_DESC"));

                    //System.err.println(strPoscd_PosDesc);
                }
                ModuleRptFast.put("MMRF_MODULE_ID", ArrlstModuleID);
                ModuleRptFast.put("MMRF_MODULE_NAME", ArrlstModuleName);
                ModuleRptFast.put("MMRF_MODULE_DESC", ArrlstModuleDesc);
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getCanonicalName() + " getModuleReportFast -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getModuleReportFast " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getModuleReportFast -> " + e.getMessage());
        }
        return ModuleRptFast;
    }

    public HashMap<String, ArrayList<Object>> getSaveReportFastFromModule(String strModule_id) {
        HashMap<String, ArrayList<Object>> ModuleRptFast = new HashMap<String, ArrayList<Object>>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.sp_get_save_report_fast(?, ?, ?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store STRF_SAVE_ID SAVE_ID, STRF_TITLE_NAME TITLE_NAME,STRF_USER_ID USER_ID,STRF_CREATE_DATE CREATE_DATE
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(1, strModule_id);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(2);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(3);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);

                //Fill du lieu ra mang
                ArrayList<Object> ArrlstSaveID = new ArrayList<Object>();
                ArrayList<Object> ArrlstTitleName = new ArrayList<Object>();
                ArrayList<Object> ArrlstUserID = new ArrayList<Object>();
                ArrayList<Object> ArrlstCreateDate = new ArrayList<Object>();
                while (reset.next()) {

                    ArrlstSaveID.add(reset.getString("SAVE_ID"));
                    ArrlstTitleName.add(reset.getString("TITLE_NAME"));
                    ArrlstUserID.add(reset.getString("USER_ID"));
                    ArrlstCreateDate.add(reset.getString("CREATE_DATE"));

                    //System.err.println(strPoscd_PosDesc);
                    //STRF_USER_ID USER_ID,STRF_CREATE_DATE CREATE_DATE
                }
                ModuleRptFast.put("SAVE_ID", ArrlstSaveID);
                ModuleRptFast.put("TITLE_NAME", ArrlstTitleName);
                ModuleRptFast.put("USER_ID", ArrlstUserID);
                ModuleRptFast.put("CREATE_DATE", ArrlstCreateDate);
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getCanonicalName() + " getSaveReportFastFromModule -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getModuleReportFast " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getSaveReportFastFromModule -> " + e.getMessage());
        }
        return ModuleRptFast;
    }

    public void ExportExcelFromQry(Connection connect, String strQuery, String strFileName) {
        DaoConnect daoConnect = new DaoConnect();
        //Connection connect = null;
        try {
            //connect = daoConnect.getConnect();
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham ExportExcelFromQry");
                return;
            }
            Statement stm = null;
            stm = connect.createStatement();
            if (stm == null) {
                System.err.println("Khong tao duoc createStatement ham ExportExcelFromQry");
                return;
            }
            ResultSet reset = null;
            reset = stm.executeQuery(strQuery);
            if (stm == null) {
                System.err.println("Khong tao duoc executeQuery ham ExportExcelFromQry");
                return;
            }
            //XSSFWorkbook workbook = new XSSFWorkbook(1000);
            SXSSFWorkbook workbook = new SXSSFWorkbook(100);
            Sheet sheet = workbook.createSheet("Bao cao nhanh");
            int rownumber = 0;
            //Cho nay ghi ra phan header
            //Lấy metadata làm column header
            ResultSetMetaData rsmd = reset.getMetaData();
            //tạo dòng tiêu đề
            Row row = sheet.createRow((short) 0);
            int colNum = rsmd.getColumnCount();//số fields có trong bảng
            //duyệt qua các fields lấy làm column header
            for (int i = 0; i < colNum; i++) {
                String col = rsmd.getColumnName(i + 1);

                Cell cell = row.createCell(i);
                cell.setCellValue(col);
            }
            //điền giá trị vào các hàng
            while (reset.next()) {
                //tạo 1 hàng mới
                row = sheet.createRow(reset.getRow());
                for (int i = 0; i < colNum; i++) {
                    String val = reset.getString(i + 1);
                    //tạo 1 ô mới
                    Cell cell = row.createCell(i);
                    cell.setCellValue(val);
                }
                System.err.println("dong thu " + reset.getString(1));
            }
            try {
                FileOutputStream out = new FileOutputStream(new File(strFileName));
                workbook.write(out);
                out.close();
                System.err.println("Da ghi ra file thanh cong " + strFileName);
            } catch (Exception e) {
            }
            if (connect != null) {
                connect.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (reset != null) {
                reset.close();
            }
        } catch (SQLException e) {
            System.err.println("Loi trong ham ExportExcelFromQry " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " ExportExcelFromQry -> " + e.getMessage());
        }
    }

    public void ExportExcelFromQrySaveId(Map<String, String> mapinPara, String strSave_id, String strFileName) {
        if (strSave_id == null || strSave_id.length() < 1) {
            return;
        }
        DaoConnect daoConnect = new DaoConnect();
        Connection connect = null;
        try {
            connect = daoConnect.getConnect();
            String strQuery = getQueryReportFastFromSaveID(strSave_id, connect);
       // System.err.println(strQuery);

            //Lay ra column va desc cho column du lieu trong bao cao
            Map<String, String> mapColumnDesc = get_Desc_column_report_fast(strSave_id, connect);

            String strTitleHeader = mapColumnDesc.get("TITLE_HEADER");
            //System.err.println(strQuery);
        //System.err.println(" tieu de bao cao "+strTitleHeader);

            //replare tham so cho gia tri trong truy van
            Set<String> keys = mapinPara.keySet();
            String strReplace = "";
            for (String key : keys) {
                String value = mapinPara.get(key).trim();
                strQuery = strQuery.replaceAll(key, value);
                if (value.isEmpty() || value == null || value.length() < 1) {
                    strReplace = getReplaceFieldNull(strSave_id, key.substring(8), connect);
                    strReplace = strReplace.replaceAll(key, value);
                    strQuery = strQuery.replaceAll(strReplace, "");
                }
            }
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham ExportExcelFromQrySaveId");
                CoreLogger.error(this.getClass().getCanonicalName() + " Khong the ket noi voi csdl ham ExportExcelFromQrySaveId ");
                return;
            }
            Statement stm = null;
            stm = connect.createStatement();
            if (stm == null) {
                System.err.println("Khong tao duoc createStatement ham ExportExcelFromQrySaveId");
                CoreLogger.error(this.getClass().getCanonicalName() + " Khong tao duoc createStatement ham ExportExcelFromQrySaveId ");
                return;
            }
            ResultSet reset = null;
            reset = stm.executeQuery(strQuery);
            //System.err.println(strQuery);
            if (stm == null) {
                System.err.println("Khong tao duoc executeQuery ham ExportExcelFromQrySaveId");
                CoreLogger.error(this.getClass().getCanonicalName() + " Khong tao duoc executeQuery ham ExportExcelFromQrySaveId ");
                return;
            }
            ExportExcelFile exportExcel = new ExportExcelFile();
            exportExcel.ExportFileExcel(reset, mapColumnDesc, strTitleHeader, strFileName);
            //SXSSFWorkbook workbook = new SXSSFWorkbook(100);
            //Sheet sheet = workbook.createSheet("Bao cao nhanh");
            //Cho nay ghi ra phan header
            //Lấy metadata làm column header
            //ResultSetMetaData rsmd = reset.getMetaData();
            //int colNum = rsmd.getColumnCount();//số fields có trong bảng
            //tạo dòng tiêu đề
            // Row row = sheet.createRow((short)1);
            //Cell titleCell=row.createCell((short)1);
            // titleCell.setCellValue(strTitleHeader);
//             sheet.addMergedRegion(new CellRangeAddress(
//                        1, // mention first row here
//                        1, //mention last row here, it is 4 as we are doing a row wise merging
//                        1, //mention first column of merging
//                        colNum-1  //mention last column to include in merge
//                        ));
//             row = sheet.createRow(4);
            //duyệt qua các fields lấy làm column header
//            for (int i = 0; i < colNum; i++) {
//                String col = rsmd.getColumnName(i + 1);
//                String strNameDesc=mapColumnDesc.get(col);
//                Cell cell = row.createCell(i+1);
//                
//                cell.setCellValue(strNameDesc);
//                //System.err.println("Loai du lieu "+rsmd.getColumnClassName(i+1));
//                //System.err.println("Loai du lieu getColumnTypeName "+rsmd.getColumnTypeName(i+1));
//            }
            //điền giá trị vào các hàng
//            while (reset.next()) {
//                //tạo 1 hàng mới
//                row = sheet.createRow(reset.getRow()+4);
//                for (int i = 0; i < colNum; i++) {
//                    String strDataType=rsmd.getColumnTypeName(i+1);
//                    
//                    String val = reset.getString(i + 1);
//                    //tạo 1 ô mới
//                    Cell cell = row.createCell(i+1);
//                    if(strDataType.equals("NUMBER"))
//                        cell.setCellValue(Double.parseDouble(val));
//                    else
//                        cell.setCellValue(val);
//                }
//                //System.err.println("dong thu " + reset.getString(1));
//            }
//            try {
//                FileOutputStream out = new FileOutputStream(new File(strFileName));
//                workbook.write(out);
//                out.close();
//                System.err.println("Da ghi ra file thanh cong " + strFileName);
//            } catch (Exception e) {
//            }
            if (reset != null) {
                reset.close();
            }
            if (stm != null) {
                stm.close();
            }
            
            if (connect != null) {
                connect.close();
            }
            
            System.gc();
        } catch (SQLException e) {
            System.err.println("Loi trong ham ExportExcelFromQry " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " ExportExcelFromQrySaveId -> " + e.getMessage());
            System.gc();
        }
    }

    public List<String> viewReport(List lstArrColumn, String strModule_id, String strAddwhere, String strNgayBC, String strPos_cd) {
        DaoConnect daoConnect = new DaoConnect();
        Connection connect = null;
        String strQuery = null;
        List<String> lstOutView = new ArrayList<String>();
        try {

            strQuery = getQueryReportFastFromArrCol(lstArrColumn, strModule_id, strAddwhere);
            HashMap<String, String> hmColumnDesc = getColumnDescReportFast(lstArrColumn, strModule_id);

            strQuery = strQuery.replaceAll("P_POS_CD", strPos_cd);
            strQuery = strQuery.replaceAll("PV_DATE_SYN", strNgayBC);
//            strQuery += " and rownum<11";
            connect = daoConnect.getConnect();

            //System.err.println("Truy van lay du lieu ra --------" + strQuery);
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham ExportExcelFromQry");
                return lstOutView;
            }
            Statement stm = null;
            stm = connect.createStatement();
            if (stm == null) {
                System.err.println("Khong tao duoc createStatement ham ExportExcelFromQry");
                return lstOutView;
            }
            ResultSet reset = null;
            reset = stm.executeQuery(strQuery);
            if (stm == null) {
                System.err.println("Khong tao duoc executeQuery ham ExportExcelFromQry");
                return lstOutView;
            }

            //Cho nay ghi ra phan header
            //Lấy metadata làm column header
            ResultSetMetaData rsmd = reset.getMetaData();

            int colNum = rsmd.getColumnCount();//số fields có trong bảng
            //duyệt qua các fields lấy làm column header
            String filltable = "";
            for (int i = 0; i < colNum; i++) {
                String col = rsmd.getColumnName(i + 1);
                String strColName = hmColumnDesc.get(col);
                filltable += "<td align=\"center\">" + strColName + "</td>";

            }
            lstOutView.add("<tr>" + filltable + "</tr>");
            //System.err.println("dong thu " + filltable);
            //điền giá trị vào các hàng
            while (reset.next()) {
                //tạo 1 hàng mới
                filltable = "";
                for (int i = 0; i < colNum; i++) {
                    String val = reset.getString(i + 1);
                    //tạo 1 ô mới

                    filltable += "<td align=\"center\">" + val + "</td>";
                }
                lstOutView.add("<tr>" + filltable + "</tr>");
//                System.err.println("dong thu " + filltable);
            }

            if (connect != null) {
                connect.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (reset != null) {
                reset.close();
            }
            System.gc();
        } catch (SQLException e) {
            System.err.println("Loi trong ham viewReport " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " viewReport -> " + e.getMessage());
            System.gc();
        }
        return lstOutView;
    }

    public void ExportExcelFromQry1(List lstArrColumn, String strModule_id, String strAddwhere, String strNgayBC, String strPos_cd, String strFileName) {
        DaoConnect daoConnect = new DaoConnect();
        Connection connect = null;
        String strQuery = null;
        try {
            strQuery = getQueryReportFastFromArrCol(lstArrColumn, strModule_id, strAddwhere);
            HashMap<String, String> hmColumnDesc = getColumnDescReportFast(lstArrColumn, strModule_id);

            strQuery = strQuery.replaceAll("P_POS_CD", strPos_cd);
            strQuery = strQuery.replaceAll("PV_DATE_SYN", strNgayBC);
            connect = daoConnect.getConnect();

            System.err.println("Truy van lay du lieu ra --------" + strQuery);
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham ExportExcelFromQry");
                return;
            }
            Statement stm = null;
            stm = connect.createStatement();
            if (stm == null) {
                System.err.println("Khong tao duoc createStatement ham ExportExcelFromQry");
                return;
            }
            ResultSet reset = null;
            reset = stm.executeQuery(strQuery);
            if (stm == null) {
                System.err.println("Khong tao duoc executeQuery ham ExportExcelFromQry");
                return;
            }
            //XSSFWorkbook workbook = new XSSFWorkbook(1000);
            SXSSFWorkbook workbook = new SXSSFWorkbook(100);
            Sheet sheet = workbook.createSheet("Bao cao nhanh");
            int rownumber = 0;
            //Cho nay ghi ra phan header
            //Lấy metadata làm column header
            ResultSetMetaData rsmd = reset.getMetaData();
            //tạo dòng tiêu đề
            Row row = sheet.createRow((short) 0);
            int colNum = rsmd.getColumnCount();//số fields có trong bảng
            //duyệt qua các fields lấy làm column header
            for (int i = 0; i < colNum; i++) {
                String col = rsmd.getColumnName(i + 1);
                String strColName = hmColumnDesc.get(col);
                Cell cell = row.createCell(i);
                cell.setCellValue(strColName);
            }
            //điền giá trị vào các hàng
            while (reset.next()) {
                //tạo 1 hàng mới
                row = sheet.createRow(reset.getRow());
                for (int i = 0; i < colNum; i++) {
                    String val = reset.getString(i + 1);
                    //tạo 1 ô mới
                    Cell cell = row.createCell(i);
                    cell.setCellValue(val);
                }
                System.err.println("dong thu " + reset.getString(1));
            }
            try {
                FileOutputStream out = new FileOutputStream(new File(strFileName));
                workbook.write(out);
                out.close();
                System.err.println("Da ghi ra file thanh cong " + strFileName);
            } catch (Exception e) {
            }
            if (connect != null) {
                connect.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (reset != null) {
                reset.close();
            }
        } catch (SQLException e) {
            System.err.println("Loi trong ham ExportExcelFromQry " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " ExportExcelFromQry1 -> " + e.getMessage());
        }
    }

    public String getQueryReportFastFromArrCol(List lstArrColumn, String strModule_id, String strAddwhere) {
        if (lstArrColumn.size() == 0) {
            return null;
        }
        String strQueryReportFast = null;
        String strColumnName = "";
        //Duyet list dua ra danh sach main pos la dang "000314","000401","000501"....
        for (int i = 0; i < lstArrColumn.size(); i++) {
            if (i != lstArrColumn.size() - 1) {
                strColumnName += "\"" + lstArrColumn.get(i).toString() + "\",";
            } else {
                strColumnName += "\"" + lstArrColumn.get(i).toString() + "\"";
            }

        }
        // System.err.println(strColumnName);
        Connection connect = null;
        CallableStatement calstatement = null;
        //Khoi tao function se tra ra du lieu la kieu gi
        String strStoreproce = "{?=call vbsp_ims_rpt.f_get_query_from_array_column(?, ?,?,?,?)}";

        try {
            //Khoi tao ket noi
            DaoConnect oraconn = new DaoConnect();
            connect = oraconn.getConnect();
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham ExportExcelFromQry");
                return strQueryReportFast;
            }
            //THuc hien goi ham trong oracle
            calstatement = connect.prepareCall(strStoreproce);
            //dang ky tham so tra du lieu ra la tham so thu nhat, kieu du lieu tra ra la number
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            //Truyen tham so thu 2 vao la mang main_pos
            calstatement.setString(2, strColumnName);
            calstatement.setString(3, strModule_id);
            calstatement.setString(4, strAddwhere);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(5);
            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(6);
            //Lay cursor ra resultset
            //Get du lieu tra ra tham so thu 1
            strQueryReportFast = calstatement.getString(1);
//            System.err.println(calstatement.getString(1));
            if (calstatement != null) {
                calstatement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getQueryReportFastFromArrCol -> " + e.getMessage());
        }
        return strQueryReportFast;
    }

    public String getQueryReportFastFromSaveID(String strSave_id, Connection connect) {
        if (strSave_id.length() == 0 || strSave_id == null) {
            return null;
        }
        String strQueryReportFast = null;

        // Connection connect = null;
        CallableStatement calstatement = null;
        //Khoi tao function se tra ra du lieu la kieu gi
        String strStoreproce = "{?=call vbsp_ims_rpt.f_get_query_report(?)}";

        try {
            //Khoi tao ket noi
            //DaoConnect oraconn = new DaoConnect();
            //connect = oraconn.getConnect();
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham ExportExcelFromQry");
                return strQueryReportFast;
            }
            //THuc hien goi ham trong oracle
            calstatement = connect.prepareCall(strStoreproce);
            //dang ky tham so tra du lieu ra la tham so thu nhat, kieu du lieu tra ra la number
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            //Truyen tham so thu 2 vao la mang main_pos
            calstatement.setString(2, strSave_id);
//            calstatement.setString(3, strModule_id);
//            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
//            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//            int pn_err_cd = calstatement.getInt(4);
//            //thu hien lay mo ta loi
//            String strEdd_txt = calstatement.getString(5);
            //Lay cursor ra resultset
            //Get du lieu tra ra tham so thu 1
            strQueryReportFast = calstatement.getString(1);
//            System.err.println(calstatement.getString(1));
            if (calstatement != null) {
                calstatement.close();
            }
//            if (connect != null) {
//                connect.close();
//            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getQueryReportFastFromArrCol -> " + e.getMessage());
        }
        return strQueryReportFast;
    }

    public String getReplaceFieldNull(String strSave_id, String strFieldName, Connection connect) {
        if (strSave_id.length() == 0 || strSave_id == null || strFieldName.length() == 0 || strFieldName == null) {
            return null;
        }
        String strReplaceField = null;

        //Connection connect = null;
        CallableStatement calstatement = null;
        //Khoi tao function se tra ra du lieu la kieu gi
        String strStoreproce = "{?=call vbsp_ims_rpt.f_get_replace_field_null(?,?,?,?)}";

        try {
            //Khoi tao ket noi
            // DaoConnect oraconn = new DaoConnect();
            //connect = oraconn.getConnect();
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham getReplaceFieldNull");
                return strReplaceField;
            }
            //THuc hien goi ham trong oracle
            calstatement = connect.prepareCall(strStoreproce);
            //dang ky tham so tra du lieu ra la tham so thu nhat, kieu du lieu tra ra la number
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            //Truyen tham so thu 2 vao la mang main_pos
            calstatement.setString(2, strSave_id);
            calstatement.setString(3, strFieldName);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(4);
//            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(5);
            //Lay cursor ra resultset
            //Get du lieu tra ra tham so thu 1
            strReplaceField = calstatement.getString(1);
//            System.err.println(calstatement.getString(1));
            if (calstatement != null) {
                calstatement.close();
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getReplaceFieldNull -> " + e.getMessage());
        }
        return strReplaceField;
    }

    public HashMap<String, ArrayList<Object>> getColumnDescReportFastArrObj(List lstArrColumn, String strModule_id) {

        if (lstArrColumn.size() == 0) {
            return null;
        }
        String strColumnName = "";
        //Duyet list dua ra danh sach main pos la dang "000314","000401","000501"....
        for (int i = 0; i < lstArrColumn.size(); i++) {
            if (i != lstArrColumn.size() - 1) {
                strColumnName += "\"" + lstArrColumn.get(i).toString() + "\",";
            } else {
                strColumnName += "\"" + lstArrColumn.get(i).toString() + "\"";
            }

        }
        HashMap<String, ArrayList<Object>> ModuleRptFast = new HashMap<String, ArrayList<Object>>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.sp_get_column_desc_from_array(?, ?, ?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, strColumnName);
                calstatement.setString(2, strModule_id);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(3);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(4);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(5);

                //Fill du lieu ra mang
                ArrayList<Object> ArrlstColumnName = new ArrayList<Object>();
                ArrayList<Object> ArrlstColumnDesc = new ArrayList<Object>();
                ArrayList<Object> ArrlstSttColumn = new ArrayList<Object>();
                while (reset.next()) {

                    ArrlstColumnName.add(reset.getString("COLUMN_NAME"));
                    ArrlstColumnDesc.add(reset.getString("COLUMN_DESC"));
                    ArrlstSttColumn.add(reset.getString("STT_COLUMN"));

                    //System.err.println(strPoscd_PosDesc);
                }
                ModuleRptFast.put("COLUMN_NAME", ArrlstColumnName);
                ModuleRptFast.put("COLUMN_DESC", ArrlstColumnDesc);
                ModuleRptFast.put("STT_COLUMN", ArrlstSttColumn);
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getCanonicalName() + " getColumnDescReportFastArrObj -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getColumnDescReportFastArrObj " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getColumnDescReportFastArrObj -> " + e.getMessage());
        }
        return ModuleRptFast;
    }

    public HashMap<String, String> getColumnDescReportFast(List lstArrColumn, String strModule_id) {

        if (lstArrColumn.size() == 0) {
            return null;
        }
        String strColumnName = "";
        //Duyet list dua ra danh sach main pos la dang "000314","000401","000501"....
        for (int i = 0; i < lstArrColumn.size(); i++) {
            if (i != lstArrColumn.size() - 1) {
                strColumnName += "\"" + lstArrColumn.get(i).toString() + "\",";
            } else {
                strColumnName += "\"" + lstArrColumn.get(i).toString() + "\"";
            }

        }
        HashMap<String, String> ColumnDescRptFast = new HashMap<String, String>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.sp_get_column_desc_from_array(?, ?, ?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, strColumnName);
                calstatement.setString(2, strModule_id);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(3);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(4);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(5);

                while (reset.next()) {
                    ColumnDescRptFast.put(reset.getString(1), reset.getString(2));
                }

                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getCanonicalName() + " getColumnDescReportFast -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getColumnDescReportFast " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getColumnDescReportFast -> " + e.getMessage());
        }
        return ColumnDescRptFast;
    }

    public HashMap<String, ArrayList<Object>> getColumnDateReportFast(String strModule_id) {

        if (strModule_id.length() == 0 || strModule_id == null) {
            return null;
        }

        HashMap<String, ArrayList<Object>> ColumnDateRptFast = new HashMap<String, ArrayList<Object>>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.sp_get_column_date_module(?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, strModule_id);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(2);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(3);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);
                ArrayList<Object> ArrlstColumnName = new ArrayList<Object>();
                ArrayList<Object> ArrlstColumnDesc = new ArrayList<Object>();
                ArrayList<Object> ArrlstTableName = new ArrayList<Object>();

                while (reset.next()) {
                    ArrlstTableName.add(reset.getString("TABLE_NAME"));
                    ArrlstColumnName.add(reset.getString("COLUMN_NAME"));
                    ArrlstColumnDesc.add(reset.getString("COLUMN_DESC"));
                }
                ColumnDateRptFast.put("TABLE_NAME", ArrlstTableName);
                ColumnDateRptFast.put("COLUMN_DESC", ArrlstColumnDesc);
                ColumnDateRptFast.put("COLUMN_NAME", ArrlstColumnName);
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getCanonicalName() + " getColumnDateReportFast -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getColumnDateReportFast " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getColumnDateReportFast -> " + e.getMessage());
        }
        return ColumnDateRptFast;
    }

    public boolean saveReportFastArr(String strModule_id, List lstArrSelectCol, List lstArrWhereCol,
            String strTitle, String strAddwhere, String strUser_id) {
        boolean bSuccess = false;

        //neu mang cot da chon la null thi return
        if (lstArrSelectCol.size() == 0) {
            return bSuccess;
        }
        String strWhereCol = "";
        //Duyet list dua ra danh sach cac cot cua dieu kien where la dang "col1","col2","col3"....
        for (int i = 0; i < lstArrWhereCol.size(); i++) {
            if (i != lstArrWhereCol.size() - 1) {
                strWhereCol += "\"" + lstArrWhereCol.get(i).toString() + "\",";
            } else {
                strWhereCol += "\"" + lstArrWhereCol.get(i).toString() + "\"";
            }

        }
        //System.err.println("Mang cot dieu kien "+strWhereCol);
        String strSelectCol = "";
        //Duyet list dua ra danh sach cac cot cua da lay du lieu la dang "col1","col2","col3"....
        for (int i = 0; i < lstArrSelectCol.size(); i++) {
            if (i != lstArrSelectCol.size() - 1) {
                strSelectCol += "\"" + lstArrSelectCol.get(i).toString() + "\",";
            } else {
                strSelectCol += "\"" + lstArrSelectCol.get(i).toString() + "\"";
            }

        }
        //System.err.println("Mang cot du lieu "+strSelectCol);
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.sp_save_report(?, ?, ?, ?, ?, ?, ?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat module_id 
                calstatement.setString(1, strModule_id);
                //THam so thu 2 la mang cac cot da chon
                calstatement.setString(2, strSelectCol);
                //THam so thu 3 la mang cac cot sau dieu kien where
                calstatement.setString(3, strWhereCol);
                //THam so thu 4 la tieu de cua bao cao
                calstatement.setString(4, strTitle);

                calstatement.setString(5, strAddwhere);
                //User id tao bao cao
                calstatement.setString(6, strUser_id);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.VARCHAR);
                //calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(7);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(8);
                //Lay cursor ra resultset
//                reset = (ResultSet) calstatement.getObject(5);
//
//                while (reset.next()) {
//                    ColumnDescRptFast.put(reset.getString(1), reset.getString(2));
//                }

//                if (reset != null) {
//                    reset.close();
//                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getCanonicalName() + " saveReportFastArr -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham saveReportFastArr " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " saveReportFastArr -> " + e.getMessage());
        }

        return bSuccess;
    }

    //Ham nay get tat ca cac tham so truyen vao cho bao cao

    public HashMap<String, ArrayList<Object>> getColumnParaReportFast(String strSave_id) {

        if (strSave_id.length() == 0 || strSave_id == null) {
            System.err.println("tra ra la null");
            return null;
        }

        HashMap<String, ArrayList<Object>> ColumnParaRptFast = new HashMap<String, ArrayList<Object>>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.sp_get_para_report_fast(?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, strSave_id);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(2);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(3);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);
                ArrayList<Object> ArrlstColumnName = new ArrayList<Object>();
                ArrayList<Object> ArrlstColumnDesc = new ArrayList<Object>();
                ArrayList<Object> ArrlstParaWhere = new ArrayList<Object>();
                ArrayList<Object> ArrlstDataType = new ArrayList<Object>();
                //COLUMN_DESC
                while (reset.next()) {
                    ArrlstDataType.add(reset.getString("DATA_TYPE"));
                    ArrlstColumnName.add(reset.getString("COLUMN_NAME"));
                    ArrlstParaWhere.add(reset.getString("PARA_WHERE"));
                    ArrlstColumnDesc.add(reset.getString("COLUMN_DESC"));

                }
                ColumnParaRptFast.put("DATA_TYPE", ArrlstDataType);
                ColumnParaRptFast.put("COLUMN_DESC", ArrlstColumnDesc);
                ColumnParaRptFast.put("PARA_WHERE", ArrlstParaWhere);
                ColumnParaRptFast.put("COLUMN_NAME", ArrlstColumnName);
                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getCanonicalName() + " getColumnParaReportFast -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getColumnParaReportFast " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getColumnParaReportFast -> " + e.getMessage());
        }
        return ColumnParaRptFast;
    }

    public List<ListValue> get_pos_report_fast(String strUserName) {
        List<ListValue> lstPo = new ArrayList<ListValue>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.sp_get_pos_report_fast(?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(1, strUserName);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(2);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(3);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);
                //COLUMN_DESC
                while (reset.next()) {
                    lstPo.add(new ListValue(reset.getString("PO_KEY"), reset.getString("PO_DESC")));
                }

                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getCanonicalName() + " get_pos_report_fast -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_pos_report_fast " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " get_pos_report_fast -> " + e.getMessage());
        }
        return lstPo;

    }

    public Map<String, String> get_Desc_column_report_fast(String strSave_id,Connection conn ) {
        Map<String, String> mapColumnDesc = new HashMap<String, String>();

        try {
            //DaoConnect daoconnect = new DaoConnect();
            //Connection conn = null;
            //conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt.sp_get_Column_Desc_report_fast(?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, strSave_id);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                String strTitle = calstatement.getString(2);
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(3);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(4);
                mapColumnDesc.put("TITLE_HEADER", strTitle);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(5);
                //COLUMN_DESC
                while (reset.next()) {
                    mapColumnDesc.put(reset.getString("COLUMN_NAME"), reset.getString("COLUMN_DESC"));
                }

                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
//                if (conn != null) {
//                    conn.close();
//                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getCanonicalName() + " get_Desc_column_report_fast -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_Desc_column_report_fast " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " get_Desc_column_report_fast -> " + e.getMessage());
        }
        return mapColumnDesc;

    }
}
