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
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ErrorObject;
import vbsp.ims.model.SysParamInfor;

/**
 *
 * @author Trung
 */
public class SysParamDao {

    private static DaoConnect daoConnect;
    private static Connection con;

    //--------------------------------------------------------------------------
    public SysParamDao() {
        daoConnect = new DaoConnect();
        con = daoConnect.getConnect();
    }

    public List<SysParamInfor> getSysParams() {
        List<SysParamInfor> sysParams = new ArrayList<>();
        CallableStatement calstatement;
        String strStoreproce = "{call vbsp_sysparam_update.p_check_update(?)}";
        ResultSet reset;
        try {
            //Khoi tao goi store
            calstatement = con.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(1);
            //Lay du lieu ra file name
            String table_name, descript, manual_flg, status;
            int seq_no, diff_record;
            while (reset.next()) {
                seq_no = reset.getInt("seq_no");
                table_name = reset.getString("table_name");
                descript = reset.getString("descript");
                diff_record = reset.getInt("diff_record");
                manual_flg = reset.getString("manual_flg");
                if (diff_record == 0) {
                    status = "Đồng bộ";
                } else {
                    status = "Khác biệt";
                }
                sysParams.add(new SysParamInfor(seq_no, table_name, descript,
                        diff_record, manual_flg, status));
            }
            calstatement.close();
        } catch (SQLException e) {
            System.err.println("Loi trong ham getSysParams " + e.getMessage());
            CoreLogger.error(SysParamDao.class.getCanonicalName() + " getSysParams -> " + e.getMessage());
        }
        return sysParams;
    }

    public int updateTable(int objId,ErrorObject error_obj) {
        CallableStatement calstatement;
        String strStoreproce = "{call vbsp_sysparam_update.p_update_table(?,?,?,?)}";
        int updatedRow = 0;
        int error_cd;
        String error_msg;
        try {
            //Khoi tao goi store
            calstatement = con.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            calstatement.setInt(1, objId);
            calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
            calstatement.executeUpdate();     
            error_cd = calstatement.getInt(2);
            error_msg = calstatement.getString(3);
            //System.err.println("Error" + error_msg);
            error_obj.setError_cd(error_cd);
            error_obj.setError_msg(error_msg);
            updatedRow = calstatement.getInt(4);
            calstatement.close();            
        } catch (SQLException e) {
            System.err.println("Loi trong ham updateTable " + e.getMessage());
            CoreLogger.error(SysParamDao.class.getCanonicalName() + " updateTable -> " + e.getMessage());
        }
        return updatedRow;
    }
}
