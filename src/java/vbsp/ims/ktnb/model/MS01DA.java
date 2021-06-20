/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.ktnb.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import vbsp.ims.dao.DaoConnect;

public class MS01DA {

    public List<MS01Model> getMS01() {
        List<MS01Model> list = new ArrayList();
        // Kết nối CSDL
        DaoConnect db = new DaoConnect();
        try {
            Connection conn = db.getConnect();
            String MProc = "call ";
            CallableStatement stm = conn.prepareCall(MProc);
            // Gán các tham số
            stm.setString(1, "giá trị");
            //Nhận giá trị Cursor của Proc Oracle
            stm.registerOutParameter(2, OracleTypes.CURSOR);
            //Thực hiện câu lệnh
            stm.executeUpdate();
            // Convert giá trị về Reesultset
            ResultSet rs = (ResultSet) stm.getObject(2);
            while (rs.next()) {
                MS01Model obj = new MS01Model();
                obj.setKT_KHOA("Tên trường");
                list.add(obj);
            }
            rs.close();
            stm.close();
            conn.close();
        } catch (Exception ex) {
        }
        return list;
    }

    // Không viết nhiều hàm do đã thống nhất là xóa toàn bộ bảng sau đó insert lại
    public void CapnhatMS01(MS01Model list) {
        DaoConnect db = new DaoConnect();
        try {
            Connection conn = db.getConnect();
            String MProc = "call ";
            CallableStatement stm = conn.prepareCall(MProc);
            // Gán các tham số
            stm.setString(1, list.getKT_CO_DINH());
            //Nhận giá trị Cursor của Proc Oracle
            stm.registerOutParameter(2, OracleTypes.CURSOR);
            //Thực hiện câu lệnh
            stm.executeUpdate();
            stm.close();
            conn.close();
        } catch (Exception ex) {
        }
    }
}
