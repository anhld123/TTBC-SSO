/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bcqt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import org.apache.commons.lang.StringUtils;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author LION
 */
public class TestBCQT {
    public static void insert_type() throws SQLException
    {
        String strt="1_ngay_bc_DATE";
        System.err.println(strt.substring(2, strt.length()));
        
        System.err.println("Test thu sp_insert_type");
        Connection connection = new DaoConnect().getConnect();
        Map<String, Class<?>> typeMaps = connection.getTypeMap();
        typeMaps.put(QT_DULIEU_NT.ORACLE_OBJECT_TYPE, QT_DULIEU_NT.class);
        QT_DULIEU_NT value = new QT_DULIEU_NT();
        value.setMA("000000");
        value.setTEN("Dang test thu");
        value.setD2("10000");
        CallableStatement cs = null;
		try {
			cs = connection.prepareCall("{call sp_insert_type(?)}");
			
			cs.setObject(1, value);
                        
			
			cs.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
                    if(cs!=null) cs.close();
                    if(connection!=null) connection.close();
                }
    }
    public static List<QT_DULIEU_NT> getDataPL01TL(Connection conn, String Khoa, String ngaybc, String mapgd, String grade) {
        List<QT_DULIEU_NT> lstBcqtPl01 = new ArrayList<QT_DULIEU_NT>();
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_BCQT.SP_LOAD_DATA_PL01(?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, Khoa);
                calstatement.setString(2, ngaybc);
                calstatement.setString(3, mapgd);
                calstatement.setString(4, grade);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(5);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(6);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(7);
                while (reset.next()) {

                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    String Ma=reset.getString(2);
                    value.setKHOA(reset.getString(1));
                    value.setMA(Ma);
                    value.setTT_HIENTHI(Ma.substring(Ma.length()-1));
                    value.setTEN(reset.getString(3));
                    value.setNGAYBC(reset.getDate(4));
                    value.setNAMBC(reset.getInt(5));
                    value.setMAPGD(reset.getString(6));
                    value.setMACN(reset.getString(7));
                    value.setD2(reset.getString(8));
                    value.setD3(reset.getString(9));
                    value.setD4(reset.getString(10));
                    value.setD5(reset.getString(11));
                    value.setD6(reset.getString(12));
                    value.setD7(reset.getString(13));
                    value.setD8(reset.getString(14));
                    value.setD9(reset.getString(15));
                    value.setD10(reset.getString(16));
                    value.setD11(reset.getString(17));
                    value.setD12(reset.getString(18));
                    value.setD13(reset.getString(19));

                    lstBcqtPl01.add(value);
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
//                CoreLogger.error(this.getClass().getName() + " getAllBcqt -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataPL01TL " + e.getMessage());
//            CoreLogger.error(this.getClass().getName() + " getAllBcqt -> " + e.getMessage());
        }
        return lstBcqtPl01;
    }
    public static void main(String[] args) throws SQLException {
        String group="01,02,03";
        String[] str=group.split(",");
        System.out.println(str.toString());
        
        System.err.println(StringUtils.join(str, ","));
        
        String thamso="LOAITSO_0";
        String ind=thamso.substring(thamso.lastIndexOf("_")+1, thamso.length());
        System.err.println(ind);
//        
//        for(int i=0;i<str.length;i++)
//            System.err.println("str="+str[i]);
//        System.err.println("Test thu sp_insert_type");
//        String strt="1_ngay_bc_DATE";
//        System.err.println(strt.substring(2, strt.length()));
//        
//        Connection connection = new DaoConnect().getConnect();
//        
//        List<QT_DULIEU_NT> lstData= getDataPL01TL(connection,"BCQT_PL01","31-dec-2015","001011","1");
//            java.util.Dictionary map
//                    = (java.util.Dictionary) (connection.getTypeMap());
//       Object array[] = lstData.toArray();
//            ArrayDescriptor des = ArrayDescriptor
//                    .createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, connection);
//            ARRAY array_to_pass = new ARRAY(des, connection, array);
//        CallableStatement cs = null;
//		try {
//			cs = connection.prepareCall("{call sp_insert_tab(?)}");
//			
//			cs.setArray(1, array_to_pass);
//                        
//			
//			cs.execute();
//			
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally{
//                    if(cs!=null) cs.close();
//                    if(connection!=null) connection.close();
//                }
    }
}
