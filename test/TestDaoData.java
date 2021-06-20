
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import vbsp.ims.dao.DaoConnect;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author BAOANH
 */
public class TestDaoData {

    public String getTableQuery(String khoa, String mact, String ngaybc, String mapgd) throws SQLException {
        String outtable = "";
        Connection conn = null;
        ResultSet reset = null;
        CallableStatement calstatement = null;
        try {
            conn = new DaoConnect().getConnect();

            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_CHAMDIEMTT.sp_get_table_detail(?,?,?,?,?)}";

            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            calstatement.setString(1, khoa);
            calstatement.setString(2, mact);
            calstatement.setString(3, ngaybc);
            calstatement.setString(4, mapgd);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(5);

            ResultSetMetaData resetMetaData = reset.getMetaData();
            String Tieude = "<tr>";
            for (int i = 0; i < resetMetaData.getColumnCount(); i++) {
                Tieude += "<td>" + resetMetaData.getColumnName(i + 1) + "</td>";
            }
            Tieude += "</tr><tr>";
            while (reset.next()) {
                for (int i = 0; i < resetMetaData.getColumnCount(); i++) {
                    String value = reset.getString(i + 1);
                    String col = value.equals("null") ? "" : value;

                    Tieude += "<td>" + col + "</td>";
                }
            }
            Tieude += "</tr>";
            outtable = Tieude;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return outtable;
    }

    public static void main(String[] args) throws SQLException {
        TestDaoData dd = new TestDaoData();
        String table = dd.getTableQuery("CDTT_PGD", "CDTT01", "30-JUN-2019", "000401");
        System.err.println(table);
    }
}
