/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.RptFormula;
import vbsp.ims.model.ValueFormula;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author LION
 */
public class DaoRptFormula {

    public List<ListValue> getLoadAllBranch() {
        List<ListValue> lstBranch = new ArrayList<ListValue>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_formula.sp_get_branch_all1(?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
//                calstatement.setString(1, strSaveId);
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
                //COLUMN_DESC
                while (reset.next()) {
                    lstBranch.add(new ListValue(reset.getString("SKEY"), reset.getString("SKEY") + " -> " + reset.getString("SDESC")));
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
                CoreLogger.error(this.getClass().getName() + " getLoadAllBranch -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getLoadAllBranch " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadAllBranch -> " + e.getMessage());
        }
        return lstBranch;
    }

    public List<ListValue> getLoadAllBranch(String sUser_id) {
        List<ListValue> lstBranch = new ArrayList<ListValue>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_formula.sp_get_branch_all(?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sUser_id);
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
                    lstBranch.add(new ListValue(reset.getString("SKEY"), reset.getString("SKEY") + " -> " + reset.getString("SDESC")));
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
                CoreLogger.error(this.getClass().getName() + " getLoadAllBranch -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getLoadAllBranch " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadAllBranch -> " + e.getMessage());
        }
        return lstBranch;
    }

    public List<ListValue> getLoadBranch(String sSave_id, String sUser_id) {
        List<ListValue> lstBranch = new ArrayList<ListValue>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_formula.SP_GET_BRANCH_CHECK_EDIT(?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sSave_id);
                calstatement.setString(2, sUser_id);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//                int pn_err_cd = calstatement.getInt(1);
                //thu hien lay mo ta loi
//                String strEdd_txt = calstatement.getString(2);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(5);
                //COLUMN_DESC
                while (reset.next()) {

                    lstBranch.add(new ListValue(reset.getString("SKEY"), reset.getString("SKEY") + " -> " + reset.getString("SDESC"), reset.getString("GIATRI")));
//                    System.err.println("Gia tri "+reset.getString("GIATRI"));
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
                CoreLogger.error(this.getClass().getName() + " getLoadBranch -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getLoadBranch " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadBranch -> " + e.getMessage());
        }
        return lstBranch;
    }

    public List<ListValue> getLoadBodyData(String sSave_id) {
        List<ListValue> lstBodyData = new ArrayList<ListValue>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_formula.sp_get_body_data_save(?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sSave_id);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//                int pn_err_cd = calstatement.getInt(1);
                //thu hien lay mo ta loi
//                String strEdd_txt = calstatement.getString(2);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);
                //COLUMN_DESC
                while (reset.next()) {

                    lstBodyData.add(new ListValue(reset.getString("SKEY"), reset.getString("SDESC"), reset.getString("nORDER")));
//                    System.err.println("Gia tri "+reset.getString("GIATRI"));
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
                CoreLogger.error(this.getClass().getName() + " getLoadBodyData -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getLoadBodyData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadBodyData -> " + e.getMessage());
        }
        return lstBodyData;
    }
    
    /*
       PV_ROW_IN       IN     INT,
   PV_COLUMN_IN    IN     INT,
   PV_DISPLAY_IN   IN     VARCHAR2,
   PV_BRANCH_IN    IN     VARCHAR2,
    */

    public HashMap<Integer, List<String>> getLoadBodyDataHashMap(String sSave_id,int rowEdit, int columnEdit, 
            String displayEdit,
            String poscdEdit) {
        HashMap<Integer, List<String>> hmArr = new HashMap<Integer, List<String>>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            //conn = daoconnect.getConnect("10.63.8.59", "vbsprepo", 1521, "intellect", "intellect321#");
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_RPT_FORMULA.sp_get_body_data_save_rpt(?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sSave_id);
                calstatement.setInt(2, rowEdit);
                calstatement.setInt(3, columnEdit);
                calstatement.setString(4, displayEdit);
                calstatement.setString(5, poscdEdit);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//                int pn_err_cd = calstatement.getInt(1);
                //thu hien lay mo ta loi
//                String strEdd_txt = calstatement.getString(2);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(8);
                //COLUMN_DESC
                int ncount = 0;
                while (reset.next()) {
                    String sData = reset.getString("SDESC");
                    List<String> myList = new ArrayList<String>(Arrays.asList(sData.split(",")));
                    //lstBodyData.add(new ListValue(reset.getString("SKEY"), reset.getString("SDESC")));
//                    System.err.println("Gia tri "+reset.getString("GIATRI"));
                    hmArr.put(ncount, myList);
                    ncount++;
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
                CoreLogger.error(this.getClass().getName() + " getLoadBodyData -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getLoadBodyData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadBodyData -> " + e.getMessage());
        }
        return hmArr;
    }
    
    public HashMap<Integer, List<String>> getLoadBodyDataHashMap_edit(String sSave_id) {
        HashMap<Integer, List<String>> hmArr = new HashMap<Integer, List<String>>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            //conn = daoconnect.getConnect("10.63.8.59", "vbsprepo", 1521, "intellect", "intellect321#");
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_formula.sp_get_body_data_save_rpt(?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sSave_id);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//                int pn_err_cd = calstatement.getInt(1);
                //thu hien lay mo ta loi
//                String strEdd_txt = calstatement.getString(2);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);
                //COLUMN_DESC
                int ncount = 0;
                while (reset.next()) {
                    String sData = reset.getString("SDESC");
                    List<String> myList = new ArrayList<String>(Arrays.asList(sData.split(",")));
                    //lstBodyData.add(new ListValue(reset.getString("SKEY"), reset.getString("SDESC")));
//                    System.err.println("Gia tri "+reset.getString("GIATRI"));
                    hmArr.put(ncount, myList);
                    ncount++;
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
                CoreLogger.error(this.getClass().getName() + " getLoadBodyData -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getLoadBodyData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadBodyData -> " + e.getMessage());
        }
        return hmArr;
    }

    public HashMap<Integer, List<String>> getLoadBodyCaculatorHashMap(String sSave_id) {
        HashMap<Integer, List<String>> hmArr = new HashMap<Integer, List<String>>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            //conn = daoconnect.getConnect("10.63.8.59", "vbsprepo", 1521, "intellect", "intellect321#");
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_formula.sp_get_body_data_caculator(?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sSave_id);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//                int pn_err_cd = calstatement.getInt(1);
                //thu hien lay mo ta loi
//                String strEdd_txt = calstatement.getString(2);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);
                //COLUMN_DESC
                int ncount = 0;
                int ncountkey=1000;
                while (reset.next()) {
                    String sData = reset.getString("SDESC");
                    List<String> myList = new ArrayList<String>(Arrays.asList(sData.split(",")));
                    //lstBodyData.add(new ListValue(reset.getString("SKEY"), reset.getString("SDESC")));
                    String sKey=reset.getString("SKEY");
//                    System.err.println("Gia tri "+reset.getString("GIATRI"));
//                    if(sKey.equals("DATA"))
//                    {
//                        hmArr.put(ncountkey, myList);
//                        ncountkey++;
//                    }
//                    else
//                    {
                        hmArr.put(ncount, myList);
                        ncount++;
                    //}
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
                CoreLogger.error(this.getClass().getName() + " getLoadBodyData -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getLoadBodyData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadBodyData -> " + e.getMessage());
        }
        return hmArr;
    }

    public HashMap<Integer, List<String>> getLoadBodyCaculatorHashMap_tmp(String sSave_id, RptFormula rptObj) {
        HashMap<Integer, List<String>> hmArr = new HashMap<Integer, List<String>>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            //conn = daoconnect.getConnect("10.63.8.59", "vbsprepo", 1521, "intellect", "intellect321#");
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_formula.sp_get_body_data_caculator_tmp(?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sSave_id);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//                int pn_err_cd = calstatement.getInt(1);
                //thu hien lay mo ta loi
//                String strEdd_txt = calstatement.getString(2);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);
                //COLUMN_DESC
                int ncount = 0;
                int ncountkey=1000;
                while (reset.next()) {
                    String sData = reset.getString("SDESC");
                    List<String> myList = new ArrayList<String>(Arrays.asList(sData.split(",")));
                    //lstBodyData.add(new ListValue(reset.getString("SKEY"), reset.getString("SDESC")));
                    String sKey=reset.getString("SKEY");
//                    System.err.println("Gia tri "+reset.getString("GIATRI"));
                    if(rptObj.getPos_cd().equals("000100")&&rptObj.getReport_type().equals("02")
                            &&rptObj.getRow_column().equals("1"))
                    {
                        //Voi truong du lieu la cong thuc
                        if(sKey.toLowerCase().startsWith("row_data"))
                        {
                            for(int i=0;i<myList.size();i++)
                            {
                                String pos_cd=myList.get(0);
                                if(i>2)
                                {
                                    String sFormula=pos_cd+"#"+myList.get(i);
                                    myList.remove(i);
                                    myList.add(i, sFormula);
                                }
                            }
                        }
                        
                    }
                        
                        hmArr.put(ncount, myList);
                        ncount++;

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
                CoreLogger.error(this.getClass().getName() + " getLoadBodyData -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getLoadBodyData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadBodyData -> " + e.getMessage());
        }
        return hmArr;
    }
   
    public HashMap<Integer, List<String>> getLoadBodyCaculatorHashMap1(String sSave_id) {
        HashMap<Integer, List<String>> hmArr = new HashMap<Integer, List<String>>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            //conn = daoconnect.getConnect("10.63.8.59", "vbsprepo", 1521, "intellect", "intellect321#");
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_formula.sp_get_body_data_caculator(?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sSave_id);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//                int pn_err_cd = calstatement.getInt(1);
                //thu hien lay mo ta loi
//                String strEdd_txt = calstatement.getString(2);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);
                //COLUMN_DESC
                int ncount = 0;
                while (reset.next()) {
                    String sData = reset.getString("SDESC");
                    List<String> myList = new ArrayList<String>(Arrays.asList(sData.split(",")));
                    //lstBodyData.add(new ListValue(reset.getString("SKEY"), reset.getString("SDESC")));
//                    System.err.println("Gia tri "+reset.getString("GIATRI"));
                    hmArr.put(ncount, myList);
                    ncount++;
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
                CoreLogger.error(this.getClass().getName() + " getLoadBodyData -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getLoadBodyData " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadBodyData -> " + e.getMessage());
        }
        return hmArr;
    }

    public List<ListValue> getBranchReport(String strArr_pos_cd) {
        System.err.println("getBranchReport " + strArr_pos_cd);
        List<ListValue> lstBranch = new ArrayList<ListValue>();
        if (strArr_pos_cd == null || strArr_pos_cd.isEmpty()) {
            return lstBranch;
        }

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_formula.sp_get_branch_report(?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, strArr_pos_cd);
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
                    lstBranch.add(new ListValue(reset.getString("SKEY"), reset.getString("SDESC")));
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
                CoreLogger.error(this.getClass().getName() + " getBranchReport -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getBranchReport " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getBranchReport -> " + e.getMessage());
        }
        return lstBranch;
    }

    /**
     * Hàm này thực hiện lưu lại báo cáo theo công thức các tham số truyền vào
     *
     * @param sSave_id Mã báo cáo (Mục đích truyền mã báo cáo để update dữ liệu
     * trong trường hợp sửa báo cáo)
     * @param sTitle Tiêu đề báo cáo
     * @param sUser_id User ID thực thiện tạo hoặc sửa báo cáo
     * @param sSource_data Nguồn số liệu cho báo cáo (cân đối, Chỉ tiêu)
     * @param sUntilData Đơn vị tính (đồng, nghìn đồng, triệu đồng)
     * @param sReport_Type Loại báo cáo (Chi tiết, Tổng hợp)
     * @param sReport_Times Kỳ báo cáo (Kỳ ngày, Tháng, quý...)
     * @param nNumber_Row Số dòng của báo cáo
     * @param nNumber_Column Số cột của báo cáo
     * @param sDisPlay_Row_Column Hiển thị theo dòng theo cột (1 theo dòng, 2
     * theo cột)
     * @param sArr_Pos_Cd Mảng gồm các pos lấy số liệu cho báo cáo
     * @return
     * @throws SQLException
     */
    public boolean saveRptFormula(String sSave_id, String sTitle, String sUser_id, String sSource_data, String sUntilData,
            String sReport_Type, String sReport_Times, int nNumber_Row, int nNumber_Column, String sDisPlay_Row_Column,
            String sArr_Pos_Cd, List<ListValue> Arr_Body_data, String grade) throws SQLException {
        boolean bSuccess = false;
        //Kiem tra 1 so truong du lieu neu bang null hoac '' thi return
        if (sTitle == null || sTitle.isEmpty() || sUser_id == null || sUser_id.isEmpty() || sSource_data == null || sSource_data.isEmpty()
                || sUntilData == null || sUntilData.isEmpty() || sReport_Type == null || sReport_Type.isEmpty() || sReport_Times == null || sReport_Times.isEmpty()
                || sDisPlay_Row_Column == null || sDisPlay_Row_Column.isEmpty() || sArr_Pos_Cd == null || sArr_Pos_Cd.isEmpty()||grade==null) {
            return false;
        }

        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        //conn = daoconnect.getConnect("10.63.8.59", "vbsprepo", 1521, "intellect", "intellect321#");
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_RPT_FORMULA.SP_SAVE_TITLE_FORMULA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        ResultSet reset = null;

        StructDescriptor structDescriptor = StructDescriptor.createDescriptor("INTELLECT.TYPE_VALUE", conn);

        STRUCT[] structs = null;
        structs = new STRUCT[Arr_Body_data.size()];
        int index = 0;
        for (ListValue value : Arr_Body_data) {
            Object[] params = new Object[2];
            params[0] = value.getsKey();
            params[1] = value.getsDesc();

            STRUCT struct = new STRUCT(structDescriptor,
                    conn, params);
            structs[index] = struct;
            index++;

        }
        //Khoi tao goi store
        calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

        ArrayDescriptor desc = ArrayDescriptor.createDescriptor(
                "INTELLECT.TABLE_TYPE_VALUE", calstatement.getConnection());
        ARRAY oracleArray = new ARRAY(desc, calstatement.getConnection(), structs);

        //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
        calstatement.setString(1, sSave_id);
        calstatement.setString(2, sTitle);
        calstatement.setString(3, sUser_id);
        calstatement.setString(4, sSource_data);
        calstatement.setString(5, sUntilData);
        calstatement.setString(6, sReport_Type);
        calstatement.setString(7, sReport_Times);
        calstatement.setInt(8, nNumber_Row);
        calstatement.setInt(9, nNumber_Column);
        calstatement.setString(10, sDisPlay_Row_Column);
        calstatement.setString(11, sArr_Pos_Cd);
//        calstatement.setString(12, sBody_data);
        calstatement.setArray(12, oracleArray);
        calstatement.setString(13, grade);
        calstatement.registerOutParameter(14, oracle.jdbc.OracleTypes.NUMBER);
        calstatement.registerOutParameter(15, oracle.jdbc.OracleTypes.VARCHAR);
//        calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
        //Thuc hien execute lay du lieu
        bSuccess = calstatement.execute();
        //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//        int pn_err_cd = calstatement.getInt(13);
        //thu hien lay mo ta loi
//        String strEdd_txt = calstatement.getString(14);

        if (reset != null) {
            reset.close();
        }
        if (calstatement != null) {
            calstatement.close();
        }
        if (conn != null) {
            conn.close();
        }
        bSuccess = true;
        return bSuccess;
    }

    public boolean saveRptFormula1(String sSave_id, String sTitle, String sUser_id, String sSource_data, String sUntilData,
            String sReport_Type, String sReport_Times, int nNumber_Row, int nNumber_Column, String sDisPlay_Row_Column,
            String sArr_Pos_Cd, String sBody_data) throws SQLException {
        boolean bSuccess = false;
        //Kiem tra 1 so truong du lieu neu bang null hoac '' thi return
        if (sTitle == null || sTitle.isEmpty() || sUser_id == null || sUser_id.isEmpty() || sSource_data == null || sSource_data.isEmpty()
                || sUntilData == null || sUntilData.isEmpty() || sReport_Type == null || sReport_Type.isEmpty() || sReport_Times == null || sReport_Times.isEmpty()
                || sDisPlay_Row_Column == null || sDisPlay_Row_Column.isEmpty() || sArr_Pos_Cd == null || sArr_Pos_Cd.isEmpty()) {
            return false;
        }

        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_RPT_FORMULA.SP_SAVE_TITLE_FORMULA(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        ResultSet reset = null;
        //Khoi tao goi store
        calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
        calstatement.setString(1, sSave_id);
        calstatement.setString(2, sTitle);
        calstatement.setString(3, sUser_id);
        calstatement.setString(4, sSource_data);
        calstatement.setString(5, sUntilData);
        calstatement.setString(6, sReport_Type);
        calstatement.setString(7, sReport_Times);
        calstatement.setInt(8, nNumber_Row);
        calstatement.setInt(9, nNumber_Column);
        calstatement.setString(10, sDisPlay_Row_Column);
        calstatement.setString(11, sArr_Pos_Cd);
        calstatement.setString(12, sBody_data);
        calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.NUMBER);
        calstatement.registerOutParameter(14, oracle.jdbc.OracleTypes.VARCHAR);
//        calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
        //Thuc hien execute lay du lieu
        bSuccess = calstatement.execute();
        //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//        int pn_err_cd = calstatement.getInt(13);
        //thu hien lay mo ta loi
//        String strEdd_txt = calstatement.getString(14);

        if (reset != null) {
            reset.close();
        }
        if (calstatement != null) {
            calstatement.close();
        }
        if (conn != null) {
            conn.close();
        }
        bSuccess = true;
        return bSuccess;
    }

    public List<ListValue> getLoadAllSaveFormula(String grade) {
        List<ListValue> lstQuery = new ArrayList<ListValue>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_formula.sp_load_edit_del_formula('',?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, grade);
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
                    lstQuery.add(new ListValue(reset.getString("SAVE_ID"), reset.getString("TITLE_NAME"), reset.getString("STT")));
//                    lstQuery.put("SRQ_QUERY",reset.getString("SRQ_QUERY"));   
//                     lstQuery.put("STRF_TITLE_NAME",reset.getString("STRF_TITLE_NAME"));  
//                     hmQuery.put("SRQ_QUERY",reset.getBlob("SRQ_QUERY").toString());   
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
                CoreLogger.error(this.getClass().getName() + " getLoadAllSaveFormula -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getLoadAllSaveFormula " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadAllSaveFormula -> " + e.getMessage());
        }
        return lstQuery;
    }

    public RptFormula getLoadEditFormula(String sSave_id) {
        RptFormula objParaSave = new RptFormula();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            //conn = daoconnect.getConnect("10.63.8.59", "vbsprepo", 1521, "intellect", "intellect321#");
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_formula.sp_load_edit_del_formula(?,'',?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sSave_id);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//                int pn_err_cd = calstatement.getInt(1);
                //thu hien lay mo ta loi
//                String strEdd_txt = calstatement.getString(2);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(4);
                //COLUMN_DESC
                while (reset.next()) {
                    objParaSave.setTitle_name(reset.getString("TITLE_NAME"));
                    objParaSave.setSource_data(reset.getString("SOURCE_DATA"));
                    objParaSave.setUntil_data(reset.getString("UNTIL_DATA"));
                    objParaSave.setReport_type(reset.getString("REPORT_TYPE"));
                    System.err.println("Loai bao cao "+reset.getString("REPORT_TYPE"));
                    objParaSave.setReport_times(reset.getString("REPORT_TIMES"));
                    objParaSave.setNumber_row(Integer.toString(reset.getInt("NUMBER_ROW")));
                    objParaSave.setNumber_column(Integer.toString(reset.getInt("NUMBER_COLUMN")));
                    objParaSave.setRow_column(reset.getString("ROW_COLUMN"));
                    objParaSave.setBranch_cd(reset.getString("BRANCH_CD"));
                    objParaSave.setUser_id(reset.getString("USER_ID"));
                    objParaSave.setPos_cd(reset.getString("POS_CD"));
                    objParaSave.setGrade(reset.getString("GRADE"));
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
                CoreLogger.error(this.getClass().getName() + " getLoadEditFormula -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getLoadEditFormula " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadEditFormula -> " + e.getMessage());
        }
        return objParaSave;
    }

    public RptFormula getLoadHeaderFormula(String sUser_id,String sSave_id) {
        RptFormula objParaSave = new RptFormula();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            //conn = daoconnect.getConnect("10.63.8.59", "vbsprepo", 1521, "intellect", "intellect321#");
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_formula.sp_load_header_formula(?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sUser_id);
                calstatement.setString(2, sSave_id);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//                int pn_err_cd = calstatement.getInt(1);
                //thu hien lay mo ta loi
//                String strEdd_txt = calstatement.getString(2);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(5);
                //COLUMN_DESC
                while (reset.next()) {
                    objParaSave.setTitle_name(reset.getString("TITLE_NAME"));
                    objParaSave.setSource_data(reset.getString("SOURCE_DATA"));
                    objParaSave.setUntil_data(reset.getString("UNTIL_DATA"));
                    objParaSave.setReport_type(reset.getString("REPORT_TYPE"));
                    System.err.println("Loai bao cao "+reset.getString("REPORT_TYPE"));
                    objParaSave.setReport_times(reset.getString("REPORT_TIMES"));
                    objParaSave.setNumber_row(Integer.toString(reset.getInt("NUMBER_ROW")));
                    objParaSave.setNumber_column(Integer.toString(reset.getInt("NUMBER_COLUMN")));
                    objParaSave.setRow_column(reset.getString("ROW_COLUMN"));
                    objParaSave.setBranch_cd(reset.getString("BRANCH_CD"));
                    objParaSave.setUser_id(reset.getString("USER_ID"));
                    objParaSave.setPos_cd(reset.getString("POS_CD"));
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
                CoreLogger.error(this.getClass().getName() + " getLoadHeaderFormula -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getLoadHeaderFormula " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getLoadHeaderFormula -> " + e.getMessage());
        }
        return objParaSave;
    }
    public HashMap<Integer, List<ListValue>> getDmKhac() {
        HashMap<Integer, List<ListValue>> hm = new HashMap<Integer, List<ListValue>>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_formula.sp_get_dmkhac(?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
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

                List<ListValue> lstTemp = new ArrayList<ListValue>();
                int khoa_1 = 0;
                int previous_khoa_1 = 0;
                boolean fistLoop = true;

                while (reset.next()) {
                    khoa_1 = Integer.parseInt(reset.getString("khoa_1"));
                    String key = reset.getString("khoa_2");
                    String des = reset.getString("giatri");
                    String stt = reset.getString("stt");

                    if (fistLoop == true) {
                        //Lan dau tien
                        ListValue valueTmp = new ListValue(key, des, stt);
                        lstTemp.add(valueTmp);

                        fistLoop = false;
                    } else if (khoa_1 == previous_khoa_1) {
                        //Neu khoa 1 chua thay doi
                        ListValue valueTmp = new ListValue(key, des, stt);
                        lstTemp.add(valueTmp);
                    } else {
                        //Neu khoa 1 thay doi
                        hm.put(previous_khoa_1, lstTemp);

                        lstTemp = new ArrayList<ListValue>(); //Loai bo het gia tri trong list
                        ListValue valueTmp = new ListValue(key, des, stt);
                        lstTemp.add(valueTmp);
                    }

                    previous_khoa_1 = khoa_1; //Luu lai khoa 1
                }
                hm.put(previous_khoa_1, lstTemp); //Khi ra khoi vong lap can them gia tri cuoi cung

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
                CoreLogger.error(this.getClass().getName() + " getDmKhac -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDmKhac " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDmKhac -> " + e.getMessage());
        }
        return hm;
    }

    public HashMap<String, Integer> getMappingColumn1() {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        try {
            //DaoConnect daoconnect = new DaoConnect();
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            //conn = daoconnect.getConnect("10.63.8.59", "vbsprepo", 1521, "intellect", "intellect321#");
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_formula.sp_get_mapping_columm(?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
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

                //List<ListValue> lstTemp = new ArrayList<ListValue>();
                while (reset.next()) {

                    //Neu khoa 1 thay doi
                    if (!hm.containsKey(reset.getInt("COLUMN_ID"))) {
                        hm.put(reset.getString("MAP_NAME"), reset.getInt("COLUMN_ID"));
                    }

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
                CoreLogger.error(this.getClass().getName() + " getMappingColumn -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getMappingColumn " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getMappingColumn -> " + e.getMessage());
        }
        return hm;
    }

    public HashMap<String, Integer> getMappingColumn() {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        try {
            //DaoConnect daoconnect = new DaoConnect();
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            //conn=daoconnect.getConnect("10.63.8.59", "vbsprepo", 1521, "intellect", "intellect321#");
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_formula.sp_get_mapping_columm(?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
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

                //List<ListValue> lstTemp = new ArrayList<ListValue>();
                while (reset.next()) {

                    //Neu khoa 1 thay doi
                    if (!hm.containsKey(reset.getInt("COLUMN_ID"))) {
                        hm.put(reset.getString("MAP_NAME"), reset.getInt("COLUMN_ID"));
                    }

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
                CoreLogger.error(this.getClass().getName() + " getMappingColumn -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getMappingColumn " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getMappingColumn -> " + e.getMessage());
        }
        return hm;
    }

    public String getUserCreateReport(String sSave_id) {
        String sUserName = "";
        try {
            Connection connect = null;
            CallableStatement calstatement = null;
            connect = new DaoConnect().getConnect();

            //Khoi tao function se tra ra du lieu la kieu gi
            String strStoreproce = "{?=call vbsp_ims_rpt_formula.f_get_user_create_report(?,?,?)}";
            //Khoi tao ket noi
            if (connect == null) {
                System.err.println("Khong the ket noi voi csdl ham getQuery");
                return sUserName;
            }
            //THuc hien goi ham trong oracle
            calstatement = connect.prepareCall(strStoreproce);
            //dang ky tham so tra du lieu ra la tham so thu nhat, kieu du lieu tra ra la number
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CLOB);
            //Truyen tham so thu 2 vao la mang main_pos
            calstatement.setString(2, sSave_id);
//            calstatement.setString(3, strModule_id);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            int pn_err_cd = calstatement.getInt(3);
//            //thu hien lay mo ta loi
            String strEdd_txt = calstatement.getString(4);

            Clob clob = calstatement.getClob(1);
            //Lay cursor ra resultset
            sUserName = calstatement.getString(1);
            //Get du lieu tra ra tham so thu 1

//            System.err.println(calstatement.getString(1));
            if (calstatement != null) {
                calstatement.close();
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " deleteRptFormula -> " + e.getMessage());
        }
        return sUserName;
    }

    public boolean deleteRptFormula(String strSave_Id, String sUserName) {
        boolean bSuccess = false;
        //System.err.println("Mang cot du lieu "+strSelectCol);
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            String sUserCreaterpt = getUserCreateReport(strSave_Id);
            if (!sUserCreaterpt.toLowerCase().equals(sUserName.toLowerCase())) {
                return bSuccess;
            }
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call vbsp_ims_rpt_formula.sp_delete_rpt_save(?, ?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat module_id 
                calstatement.setString(1, strSave_Id);
                //THam so thu 2 la mang cac cot da chon

                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                //calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(2);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(3);
                //Lay cursor ra resultset

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " deleteRptFormula -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham deleteRptFormula " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " deleteRptFormula -> " + e.getMessage());
        }

        return bSuccess;
    }

    //Lay ra pos dua vao user
    public HashMap<String, ArrayList<String>> getPosExportFormula(String strUserName) {
        HashMap<String, ArrayList<String>> hmPosExp = new HashMap<String, ArrayList<String>>();

        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_RPT_FORMULA.sp_get_pos_export(?, ?, ?, ?)}";
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
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(4);

            //Fill du lieu ra mang
            ArrayList<String> ArrlstPosCD = new ArrayList<String>();
            ArrayList<String> ArrlstPosDesc = new ArrayList<String>();
            // ArrayList<Object> ArrlstColumnDesc = new ArrayList<Object>();
            while (reset.next()) {

                ArrlstPosCD.add(reset.getString("PO_KEY"));
                ArrlstPosDesc.add(reset.getString("PO_DESC"));
                    //ArrlstColumnDesc.add(reset.getString("MCRF_COLUMN_DESC"));

                //System.err.println(reset.getString("MCRF_COLUMN_DESC"));
            }
            hmPosExp.put("POS_CD", ArrlstPosCD);
            hmPosExp.put("POS_DESC", ArrlstPosDesc);

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
            System.err.println("Loi trong ham getPosExportHstdct " + e.getMessage());
            CoreLogger.error(DaoExportHstdct.class.getCanonicalName() + " getPosExportHstdct -> " + e.getMessage());
        }

        return hmPosExp;
    }

    public HashMap<String, String> CaculatorRptFormula(String sSave_id, List<ValueFormula> Arr_Formula, String sUser_id,
            String sArr_Pos_Cd, String sNgaybc) {
        //boolean bSuccess = false;
        HashMap<String, String> hmDataFormula = new HashMap<String, String>();
        try {
            //Kiem tra 1 so truong du lieu neu bang null hoac '' thi return
            if (sSave_id == null || sSave_id.isEmpty() || sUser_id == null || sUser_id.isEmpty() || Arr_Formula == null || Arr_Formula.isEmpty()
                    || sNgaybc == null || sNgaybc.isEmpty() || sArr_Pos_Cd == null || sArr_Pos_Cd.isEmpty()) {
                return hmDataFormula;
            }

            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            //conn = daoconnect.getConnect("10.63.8.59", "vbsprepo", 1521, "intellect", "intellect321#");
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_RPT_FORMULA.sp_caculator_formula(?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            StructDescriptor structDescriptor = StructDescriptor.createDescriptor("INTELLECT.TYPE_FORMULA", conn);

            /*
             skey varchar2(2000),
             sFormula varchar2(32000),
             sData varchar2(32000),
             sfieldData varchar2(2000),
             sWhereData varchar2(2000),
             nOrder int,
             table_name varchar2(50),
             column_name varchar2(50),
             column_where varchar2(50),
             field_select varchar2(2000)
             */
            STRUCT[] structs = null;
            structs = new STRUCT[Arr_Formula.size()];
            int index = 0;
            for (ValueFormula value : Arr_Formula) {
                Object[] params = new Object[10];
                params[0] = value.getsKey();
                params[1] = value.getsFormula();
                params[2] = "";
                params[3] = value.getsFieldData();
                params[4] = value.getsWhereFormula();
                params[5] = value.getnOrder();
                params[6] = "";
                params[7] = "";
                params[8] = "";
                params[9] = "";
                STRUCT struct = new STRUCT(structDescriptor,
                        conn, params);
                structs[index] = struct;
                index++;

            }
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            ArrayDescriptor desc = ArrayDescriptor.createDescriptor(
                    "INTELLECT.TABLE_TYPE_FORMULA", calstatement.getConnection());
            ARRAY oracleArray = new ARRAY(desc, calstatement.getConnection(), structs);

            //sp_caculator_formula(pv_save_id in varchar2,ARR_FORMULA in TABLE_TYPE_FORMULA, 
            //pv_ngaybc in varchar2, arr_pos_cd in varchar2, 
            //pn_err_cd OUT number, pv_err_txt OUT varchar2, crsdata OUT sys_refcursor)
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
            calstatement.setString(1, sSave_id);
            calstatement.setArray(2, oracleArray);
            calstatement.setString(3, sNgaybc);
            calstatement.setString(4, sArr_Pos_Cd);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//        int pn_err_cd = calstatement.getInt(13);
            //thu hien lay mo ta loi
//        String strEdd_txt = calstatement.getString(14);
            reset = (ResultSet) calstatement.getObject(7);
            while (reset.next()) {
//                System.err.println(reset.getString(1));
                hmDataFormula.put(reset.getString(1), reset.getString(2));
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
            System.err.println("Loi trong ham CaculatorRptFormula " + e.getMessage());
            CoreLogger.error(DaoExportHstdct.class.getCanonicalName() + " CaculatorRptFormula -> " + e.getMessage());
        }
        return hmDataFormula;
    }

    public List<String> getTitleUntil(String sSave_id) {
        List<String> lstTitleUntil = new ArrayList<String>();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_IMS_RPT_FORMULA.sp_get_title_until_write(?, ?, ?, ?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos    
            calstatement.setString(1, sSave_id);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //Lay cursor ra resultset
            lstTitleUntil.add(calstatement.getString(2));
            lstTitleUntil.add(calstatement.getString(3));
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
            System.err.println("Loi trong ham getPosExportHstdct " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getPosExportHstdct -> " + e.getMessage());
        }
        return lstTitleUntil;
    }
}
