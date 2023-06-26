/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao.ktnb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.ListKTNB;
import vbsp.ims.model.ktnb.PosMainModel;

/**
 *
 * @author Administrator
 */
public class ListKTNBDA {
    // Khai báo hàm danh sách các báo cáo KTNB

    public List<ListKTNB> ListDMKTNB() throws SQLException {
        String CapBC = "01";
        List<ListKTNB> list = new ArrayList<>();
        try {
            DaoConnect db = new DaoConnect();
            Connection conn = db.getConnect();
            String MSQL = "SELECT DM_MABC,DM_TENVT,DM_MOTA,DM_CAPBC,APPLY_FLG,DM_KYBC,DM_LINKBC FROM DMBC_CT WHERE DM_NHOMBC='NHOMBC0023' AND APPLY_FLG='Y' AND DM_LINKBC IS NOT NULL order by DM_MABC";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(MSQL);
            while (rs.next()) {
                ListKTNB obj = new ListKTNB();
                obj.setKYBC(rs.getString("DM_KYBC"));
                obj.setTENVT(rs.getString("DM_TENVT"));
                obj.setMOTA(rs.getString("DM_MOTA"));
                obj.setLINKBC(rs.getString("DM_LINKBC"));
                list.add(obj);
            }
            rs.close();
            stm.close();
            conn.close();
        } catch (Exception ex) {
        }
        return list;
    }
    
    public List<ListKTNB> ListDMKTNBAuth() throws SQLException {
        String CapBC = "01";
        List<ListKTNB> list = new ArrayList<>();
        try {
            DaoConnect db = new DaoConnect();
            Connection conn = db.getConnect();
            String MSQL = "SELECT DM_MABC,DM_TENVT,DM_MOTA,DM_CAPBC,APPLY_FLG,DM_KYBC,DM_LINKBC FROM DMBC_CT WHERE DM_NHOMBC='NHOMBC0023' AND APPLY_FLG='Y' AND DM_LINKBC IS NOT NULL "
                    + "and dm_mabc not in  ('BC00230017','BC00230018','BC00230019','BC00230020','BC00230041','BC00230042','BC00230043','BC00230044','BC00230045','BC00230046','BC00230047') order by DM_MABC";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(MSQL);
            while (rs.next()) {
                ListKTNB obj = new ListKTNB();
                obj.setKYBC(rs.getString("DM_KYBC"));
                obj.setTENVT(rs.getString("DM_TENVT"));
                obj.setMOTA(rs.getString("DM_MOTA"));
                obj.setLINKBC(rs.getString("DM_LINKBC") + "_auth");
                list.add(obj);
            }
            rs.close();
            stm.close();
            conn.close();
        } catch (Exception ex) {
        }
        return list;
    }
    

    public List<String> getYearReport() throws SQLException {
        List<String> list = new ArrayList<String>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.sp_get_year_report( ?)}";
            ResultSet reset = null;
            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);

                //Thuc hien execute lay du lieu
                calstatement.execute();
                reset = (ResultSet) calstatement.getObject(1);
                while (reset.next()) {

                    list.add(reset.getString(1));

                }

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " get_pos_main_pos -> " + e.getMessage());
            }

            return list;
        } catch (Exception e) {
            System.err.println("Loi trong ham get_pos_main_pos " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " get_pos_main_pos -> " + e.getMessage());
        }
        return list;
    }
    
        public String getListPos(String sMainPos) throws SQLException {
        List<String> list = new ArrayList<String>();
        String lsPos = "";
        try {
            DaoConnect db = new DaoConnect();
            Connection conn = db.getConnect();
            String MSQL = "SELECT PO_MA FROM DMPOS WHERE  PO_STATUS = 'O' AND PO_MACN =  " + sMainPos ;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(MSQL);
            while (rs.next()) {
                    list.add(rs.getString(1));
                }
            rs.close();
            stm.close();
            conn.close();
        } catch (Exception ex) {
        }
        for (String temp : list) {
		lsPos = lsPos+temp + ",";
	}
        lsPos = lsPos.substring(0, lsPos.length()-1);
        return lsPos;
    }

    //CuongBM: 04-Oct-14
    //Lay ma phong giao dich, ma chi nhanh dua vao user id
    public PosMainModel get_pos_main_pos(String userId) {
        PosMainModel posMainModel = new PosMainModel();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KTKTNB.p_get_pos_cd(?, ?, ?)}";

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat module_id 
                calstatement.setString(1, userId);

                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);

                //Thuc hien execute lay du lieu
                calstatement.execute();

                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                String posCd = calstatement.getString(2);
                String mainPosCd = calstatement.getString(3);

                posMainModel.setPosCd(posCd);
                posMainModel.setMainPosCd(mainPosCd);

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " get_pos_main_pos -> " + e.getMessage());
            }

            return posMainModel;
        } catch (Exception e) {
            System.err.println("Loi trong ham get_pos_main_pos " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " get_pos_main_pos -> " + e.getMessage());
        }
        return posMainModel;
    }

}
