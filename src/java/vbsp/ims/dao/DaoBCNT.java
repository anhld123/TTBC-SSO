package vbsp.ims.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vbsp.ims.bcnt.BaoCaoNhapTay;
import vbsp.ims.bcnt.POS;
import vbsp.ims.bcnt.Report;
import vbsp.ims.log.CoreLogger;

public class DaoBCNT {

    public ArrayList<BaoCaoNhapTay> getBCNT(String reportId, String reportDate, String posId, String quarteryear) {
        ArrayList<BaoCaoNhapTay> bcntList = new ArrayList<BaoCaoNhapTay>();
        
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_BCNT.SP_GET_BCNT_GRID(?, ?, ?, ?, ?, ?, ?)}";
            ResultSet rsGrid = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la ma bao cao
                calstatement.setString(1, reportId);
                //Tham so thu 2 truyen vao la ngay bao cao
                calstatement.setString(2, reportDate);
                //Tham so thu 3 truyen vao la ngay bao cao
                calstatement.setString(3, posId);
                //Tham so thu tu truyen vao la quy bao cao
                calstatement.setString(4, quarteryear);
                
                
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);

                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                rsGrid = (ResultSet) calstatement.getObject(7);
                    
                int id = 0; 
                while (rsGrid.next()) {                    
                    BaoCaoNhapTay b = new BaoCaoNhapTay();
                    b.setId(id);
                    b.setMaBC(rsGrid.getString("MA_BC"));
                    b.setMaCT(rsGrid.getString("MA_CT"));
                    b.setMoTa(rsGrid.getString("MO_TA"));                    
                    b.setHieuLuc(rsGrid.getString("HIEU_LUC"));
                    b.setChinhSua(rsGrid.getString("C_SUA"));
                    b.setStt(rsGrid.getString("STT"));
                    b.setTieuDe(rsGrid.getString("TIEU_DE"));
                    b.setMaCtMap(rsGrid.getString("MA_CT_MAP"));
                    b.setMs(rsGrid.getString("MS"));                    
                    b.setMaPgd(rsGrid.getString("MA_PGD"));
                    b.setNgayGt(reportDate);
                    b.setQuyBc(quarteryear);
                    b.setGiaTri(rsGrid.getString("GIA_TRI"));                    
                    b.setCtTong(rsGrid.getString("CT_TONG"));
                    b.setCtTongCongThuc(rsGrid.getString("CT_TONG_CTHUC"));
                    b.setCapHienThi(Integer.parseInt(rsGrid.getString("CAP_HT"))); //Hien tai chua xu ly cap hien thi, hard code = 1

                    bcntList.add(b);
                    id++;
                }

                if (rsGrid != null) {
                    rsGrid.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham getReportList " + e.getMessage());
                CoreLogger.error(DaoBCNT.class.getCanonicalName() + " getReportList  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getReportList " + e.getMessage());
            CoreLogger.error(DaoBCNT.class.getCanonicalName() + " getReportList  -> " + e.getMessage());
        }
        
        
        return bcntList;
    }
    
    public ArrayList<Report> getReportList(){
        ArrayList<Report> reportList = new ArrayList<Report>();
        
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_BCNT.SP_GET_REPORT_BCNT(?, ?, ?)}";
            ResultSet rsReportList = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);

                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                rsReportList = (ResultSet) calstatement.getObject(3);

                while (rsReportList.next()) {
                    Report r = new Report();
                    r.setId(rsReportList.getString("MA_BC"));
                    r.setDesc(rsReportList.getString("MA_BC") + " - " + rsReportList.getString("MO_TA"));

                    reportList.add(r);
                }

                if (rsReportList != null) {
                    rsReportList.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham getReportList " + e.getMessage());
                CoreLogger.error(DaoBCNT.class.getCanonicalName() + " getReportList  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getReportList " + e.getMessage());
            CoreLogger.error(DaoBCNT.class.getCanonicalName() + " getReportList  -> " + e.getMessage());
        }
        return reportList;
    }
        
    public ArrayList<POS> getPosList(String userName){
        ArrayList<POS> posList = new ArrayList<POS>();
        
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_BCNT.SP_GET_POS_LIST(?, ?, ?, ?)}";
            ResultSet rsPosList = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                //Truyen vao username
                calstatement.setString(1, userName);          
                
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);

                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                rsPosList = (ResultSet) calstatement.getObject(4);

                while (rsPosList.next()) {
                    POS p = new POS();
                    p.setId(rsPosList.getString("PO_MA"));
                    p.setDesc(rsPosList.getString("PO_MA") + " - " + rsPosList.getString("PO_TEN"));

                    posList.add(p);
                }

                if (rsPosList != null) {
                    rsPosList.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham getReportList " + e.getMessage());
                CoreLogger.error(DaoBCNT.class.getCanonicalName() + " getReportList  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getReportList " + e.getMessage());
            CoreLogger.error(DaoBCNT.class.getCanonicalName() + " getReportList  -> " + e.getMessage());
        }
        return posList;
    }
    
    public boolean saveGcntGrid_updateSys(String reportId, String posId, String reportDate, 
            String sUserId, String quarteryear, String strSysDate, String data){
       boolean bSuccess=false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_BCNT.SP_SAVE_BCNT_XML_SYN(?, ?, ?, ?, ?, ?, ?,?,?)}";

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la ma bao cao
                calstatement.setString(1, reportId);
                //Tham so thu 2 truyen vao la ngay bao cao
                calstatement.setString(2, posId);
                //Tham so thu 2 truyen vao la ngay bao cao
                calstatement.setString(3, reportDate);
                //Tham so thu 2 truyen vao la ngay bao cao
                calstatement.setString(4, quarteryear);
                //Tham so thu 2 truyen vao la ngay bao cao
                calstatement.setString(5, data);
                calstatement.setString(6, strSysDate);
                calstatement.setString(7, sUserId);
//                System.err.println("reorptId " + reportId);
//                System.err.println("posId " + posId);
//                System.err.println("reportDate " + reportDate);
//                System.err.println("quarteryear " + quarteryear);
//                System.err.println("data " + data);
                
           
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);

                //Thuc hien execute luu du lieu
                calstatement.execute();
                
                int err_cd=calstatement.getInt(8);
                String err_txt=calstatement.getString(9);
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess=true;
            } catch (SQLException e) {
                System.err.println("Loi trong ham saveGcntGrid_updateSys " + e.getMessage());
                CoreLogger.error(this.getClass().getName() + " saveGcntGrid_updateSys  -> " + e.getMessage());
                bSuccess=false;
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham saveGcntGrid_updateSys " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveGcntGrid_updateSys  -> " + e.getMessage());
            bSuccess=false;
        }
        return bSuccess;
    }
    
    public void saveGcntGrid(String reportId, String posId, String reportDate, String quarteryear, String data){
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_BCNT.SP_SAVE_BCNT_GRID(?, ?, ?, ?, ?, ?, ?)}";

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat truyen vao la ma bao cao
                calstatement.setString(1, reportId);
                //Tham so thu 2 truyen vao la ngay bao cao
                calstatement.setString(2, posId);
                //Tham so thu 2 truyen vao la ngay bao cao
                calstatement.setString(3, reportDate);
                //Tham so thu 2 truyen vao la ngay bao cao
                calstatement.setString(4, quarteryear);
                //Tham so thu 2 truyen vao la ngay bao cao
                calstatement.setString(5, data);
                
//                System.err.println("reorptId " + reportId);
//                System.err.println("posId " + posId);
//                System.err.println("reportDate " + reportDate);
//                System.err.println("quarteryear " + quarteryear);
//                System.err.println("data " + data);
                
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);

                //Thuc hien execute luu du lieu
                calstatement.execute();
                
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham getReportList " + e.getMessage());
                CoreLogger.error(DaoBCNT.class.getCanonicalName() + " getReportList  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getReportList " + e.getMessage());
            CoreLogger.error(DaoBCNT.class.getCanonicalName() + " getReportList  -> " + e.getMessage());
        }
    }
}
