/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.bcqt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vbsp.ims.bcqt.model.MSQT01Model;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.DefineFun;
import vbsp.ims.dtw.ExcelToTableDetail;
import vbsp.ims.export.excel.ExportExcelFile;
import vbsp.ims.log.CoreLogger;
/**
 *
 * @author chudv
 */
public class BcqtDao {
    private static DaoConnect daoConnect;
    private static Connection conn;
    public static BcqtDao newInstance()
    {
        return new BcqtDao();
    }
    public BcqtDao() 
    {
        daoConnect = new DaoConnect();
    }
    
    public List<MSQT01Model> getDataMSQT01(String _ReportKey, String _Pos_Cd, String _NgayBC, int _NamBC) 
    {
        List<MSQT01Model> lstMSQT01 = new ArrayList<MSQT01Model>();
        int pn_err_cd = 0;
        String strEdd_txt = "";
        try 
        {
            Connection conn = null;
            conn = new DaoConnect().getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{CALL VBSP_IMS_BCQT.SP_GET_QT01(?,?,?,?,?,?)}";
            ResultSet reset = null;

            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);

            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, _ReportKey);
            calstatement.setString(2, _Pos_Cd);
            calstatement.setString(3, _NgayBC);
            calstatement.setInt(4, _NamBC);
            
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(6);
            strEdd_txt = calstatement.getString(5);

            while (reset.next()) 
            {
                MSQT01Model value = new MSQT01Model();
//                System.err.println(reset.getString(4));
                value.setKhoa(reset.getString(1));
                value.setTT_Dong(reset.getInt(2));
                value.setMa(reset.getString(3));
                value.setTen(reset.getString(4));
                value.setCo_TongHop(reset.getString(5));
                value.setCap(reset.getString(6));
                value.setMa_BoSung(reset.getString(7));
                value.setTT_HienThi(reset.getString(8));
                value.setCo_ApDung(reset.getString(9));    
                value.setThuTu(reset.getInt(10));
                value.setNgayBC(reset.getString(11));
                value.setNamBC(reset.getInt(12));
                value.setMaPGD(reset.getString(13));
                value.setMaCN(reset.getString(14));
                value.setD1(reset.getString(15));
                value.setD2(reset.getString(16));
                value.setD3(reset.getString(17));
                value.setD4(reset.getString(18));
                value.setD5(reset.getString(19));
                value.setD6(reset.getString(20));
                lstMSQT01.add(value);
            }
            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
        } 
        catch (Exception e) 
        {
            System.err.println("Loi trong ham getDataMSQT01 " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataMSQT01 -> " + e.getMessage());
        }
        return lstMSQT01;
    }
    
    
    
    public static void main(String[] args)
    {
        BcqtDao.newInstance().getDataMSQT01("BCQT_M01", "000100", "31-oct-2015", 2015);
    }
    
    
    
    
}
