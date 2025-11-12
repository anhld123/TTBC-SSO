/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.gqvl_2023;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.action.ktktnb.DULIEU_NT_TQ;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoLoadReportParams;
import vbsp.ims.dao.khnv.DaoDieuchinhkh;
import vbsp.ims.define.GenericResult;
import vbsp.ims.loadparams.Combo;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.model.khnv.POSModel;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author LION
 */
public class DaoBranchMain {

    public static DaoBranchMain newInstance() {
        return new DaoBranchMain();
    }

    public boolean save_giamlai_2025(String khoa, String ngaybc, String sposcd, String smaxa, String smato, String sngdung, List<DULIEU_NT_TQ> lstData) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor
                .createDescriptor(DULIEU_NT_TQ.ORACLE_TABLE_TYPE, connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        ArrayDescriptor des_ma = ArrayDescriptor.createDescriptor("POS_CD", connection);
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_PROCESS_BRANCH.P_SAVE_DATA_GIAMLAI2025(?, ?, ?, ? ,?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setString(2, ngaybc);
            cs.setString(3, sposcd);
            cs.setString(4, smaxa);
            cs.setString(5, smato);
            cs.setString(6, sngdung);
            cs.setArray(7, array_to_pass);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham save no " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " save no -> " + e.getMessage());
            return false;
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return true;
    }

    public List<QT_DULIEU_NT> get_data_giamlai(Connection conn, String khoa, String smapgd, String ngaybc, String posfl, String type) {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList<QT_DULIEU_NT>();
        try {
            CallableStatement calstatement = null;
            String strStoreproce = "{call VBSP_IMS_PROCESS_BRANCH.SP_GET_DATA_GIAMLAI_TW(?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, khoa);
                calstatement.setString(2, smapgd);
                calstatement.setString(3, ngaybc);
                calstatement.setString(4, posfl);
                calstatement.setString(5, type);

                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(6);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(7);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(8);
                while (reset.next()) {

                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setD1(reset.getString("D1"));
                    value.setD2(reset.getString("D2"));
                    value.setD3(reset.getString("D3"));
                    value.setD4(reset.getString("D4"));
                    value.setD5(reset.getString("D5"));
                    value.setD6(reset.getString("D6"));
                    value.setD7(reset.getString("D7"));
                    value.setD8(reset.getString("D8"));
                    value.setD9(reset.getString("D9"));
                    value.setD10(reset.getString("D10"));
                    value.setD11(reset.getString("D11"));
                    value.setD12(reset.getString("D12"));
                    value.setD13(reset.getString("D13"));
                    value.setD14(reset.getString("D14"));
                    value.setD15(reset.getString("D15"));
                    
                    lstBcqt_NT.add(value);
                }

                if (reset != null) {
                    reset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " get_data_giamlai -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_data_giamlai " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " get_data_giamlai -> " + e.getMessage());
        }
        return lstBcqt_NT;
    }
    public GenericResult<String> unlock_giamlai_tw(String skhoa, String smadgx, String spos_flag, String sngaybc, String skye) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_PROCESS_BRANCH.SP_UNLOCK_GIAMLAI_TW(?, ?, ?, ? ,?, ?, ? )}");
            cs.setString(1, skhoa);
            cs.setString(2, smadgx);
            cs.setString(4, sngaybc);
            cs.setString(3, spos_flag);
            cs.setString(5, skye);
            cs.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
            cs.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
            cs.execute();

            //Lay ma loi neu co
            int errorCode = cs.getInt(6);
            String errorMessage = cs.getString(7);

            if (errorCode == 0) {
                return (new GenericResult<String>()).Success("Success");
            } else {
                return (new GenericResult<String>()).Fail(errorMessage, errorCode);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham cancelAssign " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " cancelAssign -> " + e.getMessage());
            return (new GenericResult<String>()).Fail(e.getMessage(), e.getErrorCode());
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}
