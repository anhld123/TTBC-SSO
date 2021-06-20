/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.sbv;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.log.CoreLogger;
/**
 *
 * @author chudv
 */
public class SBV_016N_TTGS extends actionMainSbv implements sbvInterface {
    public SBV_016N_TTGS() {
    }
    public List<QT_DULIEU_NT> lstData = new ArrayList<>();
    
    public List<QT_DULIEU_NT> getLstData() {
        return lstData;
    }

    public void setLstData(List<QT_DULIEU_NT> lstData) {
        this.lstData = lstData;
    }

    public String load() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap<String, Object> hmPara = getParameter();
            ngay_bc = hmPara.get("ngay_bc").toString();
            //-------------------------------------
            Connection conn = new DaoConnect().getConnect();
            // Thực hiện gọi hàm để lấy dữ liệu ra bảng
            lstData = new ArrayList<>();
            CallableStatement cs = conn.prepareCall("{call IMS_SBV.SP_LOAD_SBV_016N_TTGS(?,?)}");
            cs.setString(1, ngay_bc);
            cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = null;
            rs = (ResultSet) cs.getObject(2);
            while (rs.next()) {
                QT_DULIEU_NT obj = new QT_DULIEU_NT();
                obj.setMA(rs.getString(1));
                obj.setTEN(rs.getString(2));
                obj.setD5(rs.getString(3));
                lstData.add(obj);
            }
            if (conn != null) {
                conn.close();
            }
            return "success";
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SBV_016N_TTGS: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SBV_016N_TTGS: " + e.getMessage());
            return "error";
        }
    }
    
    public String save() {
        try {
             if (!getParaSession()) {
                return ERROR;
            }
            HashMap<String, Object> hmPara = getParameter();
            // Thực hiện chuyển ngày về đúng định dạng DD-MON-YYYY
            ngay_bc = hmPara.get("ngay_bc").toString();
            
            Connection conn = new DaoConnect().getConnect();
            Object array[] = lstData.toArray();
            ArrayDescriptor des = ArrayDescriptor.createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, conn);
            ARRAY array_to_pass = new ARRAY(des, conn, array);
            //-------------------------------------
            CallableStatement cs = conn.prepareCall("{call IMS_SBV.SP_SAVE_SBV_016N_TTGS(?,?)}");
            cs.setString(1, ngay_bc);
            cs.setArray(2, array_to_pass);
            cs.execute();
            if (conn != null) {
                conn.close();
            }
            addActionMessage("Bạn đã lưu dữ liệu thành công!");
        return SUCCESS;
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_SBV_016N_TTGS: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_SBV_016N_TTGS: " + e.getMessage());
            return ERROR;
        }
    }
}
