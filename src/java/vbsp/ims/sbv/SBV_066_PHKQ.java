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
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author BAOANH
 */
public class SBV_066_PHKQ extends actionMainSbv implements sbvInterface {
    
    public List<QT_DULIEU_NT> lstData = new ArrayList<>();
    
    public String load() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap<String, Object> hmPara = getParameter();
            ngay_bc     = hmPara.get("ngay_bc").toString();
            Connection conn = new DaoConnect().getConnect();
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = poscd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            //-------------------------------------
            CallableStatement cs = conn.prepareCall("{call IMS_SBV.SP_LOAD_SBV_066_PHKQ(?,?,?,?,?)}");
            cs.setString(1, ngay_bc);
            cs.setString(2, UserName);
            cs.setString(3, Grade);
            cs.setArray(4, oracle_arrayPoscd);
            cs.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = null;
            rs = (ResultSet) cs.getObject(5);
            while (rs.next()) {
                QT_DULIEU_NT obj = new QT_DULIEU_NT();
                obj.setMA(rs.getString("MA"));
                obj.setTEN(rs.getString("TEN"));
                obj.setD1(rs.getString("D1"));
                obj.setD2(rs.getString("D2"));
                obj.setD3(rs.getString("D3"));
                obj.setD4(rs.getString("D4"));
                obj.setD5(rs.getString("D5"));
                obj.setD6(rs.getString("D6"));
                obj.setD7(rs.getString("D7"));
                obj.setKIEUIN(rs.getInt("KIEUIN"));
                obj.setTT_HIENTHI(rs.getString("TT_HIENTHI"));
                obj.setD29(rs.getString("D29")); // Cấp bậc
                obj.setD30(rs.getString("D30")); // Cở cộng cấp bậc
                lstData.add(obj);
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> load: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> load: " + e.getMessage());
        }
        return SUCCESS;
    }

    @Override
    public String save() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap<String, Object> hmPara = getParameter();
            ngay_bc     = hmPara.get("ngay_bc").toString();
            Connection conn = new DaoConnect().getConnect();
            //-------------------------------------
            Object array[] = lstData.toArray();
            ArrayDescriptor des = ArrayDescriptor.createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, conn);
            ARRAY array_to_pass = new ARRAY(des, conn, array);
            //-------------------------------------
            CallableStatement cs = conn.prepareCall("{call IMS_SBV.SP_SAVE_SBV_066_PHKQ(?,?,?,?)}");
            cs.setString(1, ngay_bc);
            cs.setString(2, UserName);
            cs.setString(3, Grade);
            cs.setArray(4, array_to_pass);
            cs.execute();
            if (conn != null) {
                conn.close();
            }
            addActionMessage("Bạn đã lưu dữ liệu thành công!");
        return SUCCESS;
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save: " + e.getMessage());
        }
        return SUCCESS;
    }

    public List<QT_DULIEU_NT> getLstData() {
        return lstData;
    }

    public void setLstData(List<QT_DULIEU_NT> lstData) {
        this.lstData = lstData;
    }   
}
