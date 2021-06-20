/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelCommune;
import vbsp.ims.model.ModelRiskProcess;
import vbsp.ims.model.ModelRiskProcess.DescTableBrower;
import vbsp.ims.model.ModelRiskProcess.ListRisk;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author LION
 */
public class DaoProcessVb819 {

    //Load du lieu cua xa, pgd, tinh tham so truyen vao la user name
    public List<ModelTreeNode> getDataPosTreeNode(String strUserName,String sGrade) {
        List<ModelTreeNode> lstPo = new ArrayList<ModelTreeNode>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_RPT_COMMUNE.SP_GET_LIST_LOCAL_1(?,?,?,?,?)}";
            ResultSet reset = null;
    
            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la co lay theo pos hay main pos  
                calstatement.setString(1, strUserName);
                calstatement.setString(2, sGrade);
//                calstatement.setString(2, strCommuneFlg);
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
                //COLUMN_DESC
                while (reset.next()) {
                    lstPo.add(new ModelTreeNode(reset.getString("PARENT_CD"), reset.getString("PARENT_DESC"),
                            reset.getString("CHILD_CD"), reset.getString("CHILD_DESC")));
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
                CoreLogger.error(DaoProcessVb819.class.getCanonicalName() + " getDataPosTreeNode -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataPosTreeNode " + e.getMessage());
            CoreLogger.error(DaoProcessVb819.class.getCanonicalName() + " getDataPosTreeNode -> " + e.getMessage());
        }
        return lstPo;
    }

  
    public boolean setStatusVb819_Sync(String strUserName, String sGrade, String sPoscd,
            String sNgaybc, List<ModelCommune> lst819) {
        boolean bSuccess = false;
        if (lst819.size() == 0) {
            return bSuccess;
        }

        try {
//            System.err.println(strStringLagecyId);
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{ call VBSP_IMS_RPT_COMMUNE.SP_VB819_SYNC(?,?,?,?,?)}";
            ResultSet reset = null;

            try {

                StructDescriptor structDescriptor = StructDescriptor.createDescriptor("VB819_TYPE", conn);

                STRUCT[] structs = null;
                structs = new STRUCT[lst819.size()];
                int index = 0;
                for (ModelCommune value : lst819) {
//                    if (value.getCheck_legacyid().toLowerCase().equals("false")) {
//                        continue;
//                    }

                    Object[] params = new Object[9];
                    params[0] = value.getsPoscd();
                    params[1] = value.getsCommuneid();
                    params[2] = value.getsReportdt();
                    params[3] = value.getsCode();
                    params[4] = value.getbValue();
                    params[5] = value.getbMark();
                    params[6] = value.getsMainpos();
                    params[7] = value.getsSubcode();
                    params[8] = value.getsUserid();

                    
                    STRUCT struct = new STRUCT(structDescriptor,
                            conn, params);
                    structs[index] = struct;
                    index++;

                }
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

                ArrayDescriptor desc = ArrayDescriptor.createDescriptor(
                        "VB819_TAB", calstatement.getConnection());
                ARRAY oracleArray = new ARRAY(desc, calstatement.getConnection(), structs);

                //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
                calstatement.setString(1, sPoscd);
                calstatement.setString(2, sNgaybc);
                calstatement.setArray(3, oracleArray);

                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
//        calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                bSuccess = calstatement.execute();
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
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(DaoProcessVb819.class.getCanonicalName() + " setStatusVb819_Sync -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham setStatusVb819_Sync " + e.getMessage());
            CoreLogger.error(DaoProcessVb819.class.getCanonicalName() + " setStatusVb819_Sync -> " + e.getMessage());
        }
        return bSuccess;
    }

    }
