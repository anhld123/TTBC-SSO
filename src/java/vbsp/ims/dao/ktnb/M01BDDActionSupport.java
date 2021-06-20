/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao.ktnb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.sbv.actionMainSbv;

/**
 *
 * @author Administrator
 */
public class M01BDDActionSupport extends actionMainSbv {

    private List<QT_DULIEU_NT> lstData;
    private String datepicker;

    public M01BDDActionSupport() {
    }

    public String load() throws Exception {
        //Khai báo các biến hệ thống
        try {
            lstData = new ArrayList<>();
            if (!getParaSession()) {
                return ERROR;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(datepicker);
            DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
            String ngaybc = df3.format(date);
            Connection conn = new DaoConnect().getConnect();
            //-------------------------------------
            CallableStatement cs = conn.prepareCall("{call sp_load_ktktnb_m01bdd(?,?,?,?)}");
            cs.setString(1, ngaybc);
            cs.setString(2, UserName);
            cs.setString(3, Grade);
            cs.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = null;
            rs = (ResultSet) cs.getObject(4);
            while (rs.next()) {
                QT_DULIEU_NT obj = new QT_DULIEU_NT();
                obj.setTT_HIENTHI(rs.getString("TT_HIENTHI"));
                obj.setTHUTU(rs.getInt("THUTU"));
                obj.setMA(rs.getString("MA"));
                obj.setTEN(rs.getString("TEN"));
                obj.setD1(rs.getString("D1"));
                obj.setD2(rs.getString("D2"));
                obj.setD3(rs.getString("D3"));
                obj.setD4(rs.getString("D4"));
                obj.setD5(rs.getString("D5"));
                obj.setD6(rs.getString("D6"));
                obj.setD7(rs.getString("D7"));
                obj.setD8(rs.getString("D8"));
                obj.setD9(rs.getString("D9"));
                obj.setD10(rs.getString("D10"));
                obj.setD11(rs.getString("D11"));
                obj.setD12(rs.getString("D12"));
                obj.setD13(rs.getString("D13"));
                obj.setD14(rs.getString("D14"));
                obj.setD15(rs.getString("D15"));
                obj.setD16(rs.getString("D16"));
                obj.setD17(rs.getString("D17"));
                obj.setD18(rs.getString("D18"));
                obj.setD19(rs.getString("D19"));
                obj.setD20(rs.getString("D20"));
                obj.setD21(rs.getString("D21"));
                obj.setD22(rs.getString("D22"));
                obj.setD23(rs.getString("D23"));
                obj.setD24(rs.getString("D24"));
                obj.setD25(rs.getString("D25"));
                obj.setD26(rs.getString("D26"));
                obj.setD28(rs.getString("D28")); // Cờ nhập tay
                obj.setD29(rs.getString("D29")); // Cấp báo cáo
                lstData.add(obj);
            }
            return "success";
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> load: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> load: " + e.getMessage());
            return "error";
        }
    }

    public String save() throws Exception {
        if (!getParaSession()) {
            return ERROR;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(datepicker);
        DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
        String ngaybc = df3.format(date);
        Connection conn = new DaoConnect().getConnect();
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor.createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, conn);
        ARRAY array_to_pass = new ARRAY(des, conn, array);
        CallableStatement cs = conn.prepareCall("{call sp_save_ktktnb_m01bdd(?,?,?,?)}");
        cs.setString(1, ngaybc);
        cs.setString(2, UserName);
        cs.setString(3, Grade);
        cs.setArray(4, array_to_pass);
        cs.execute();
        return "success";
    }

    public String M01_Redirec() throws Exception {
        return "success";
    }

    public List<QT_DULIEU_NT> getLstData() {
        return lstData;
    }

    public void setLstData(List<QT_DULIEU_NT> lstData) {
        this.lstData = lstData;
    }

    public String getDatepicker() {
        return datepicker;
    }

    public void setDatepicker(String datepicker) {
        this.datepicker = datepicker;
    }

}
