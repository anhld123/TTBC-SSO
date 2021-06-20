package vbsp.ims.dao.khnv;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.khnv.CTieuKHoachModel;
import vbsp.ims.model.khnv.ChiTieuDetail;
import vbsp.ims.model.khnv.GiaokhModel;

/**
 *
 * @author CuongBM0211
 */
public class DaoGiaokh {

    public ArrayList<GiaokhModel> get_data_giaokh(String reportGrade, String username) {
        ArrayList<GiaokhModel> dataList = new ArrayList<GiaokhModel>();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KHNV.get_data_giaokh(?, ?, ?, ?, ?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat module_id 
                calstatement.setString(1, reportGrade);
                 calstatement.setString(2, username);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);

                //Thuc hien execute lay du lieu
                calstatement.execute();

                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(5);

                while (reset.next()) {
                    GiaokhModel obj = new GiaokhModel();

                    obj.setKH_MA_CT(reset.getString("KH_MA_CT"));
                    obj.setKH_STT_HT(reset.getString("KH_STT_HT"));
                    obj.setKH_CHI_TIEU(reset.getString("KH_CHI_TIEU"));

                    String link = "getChiTieuDetail.action?maCt=" + reset.getString("KH_MA_CT");
                    obj.setKH_LINK(link);
                    obj.setKH_DN(reset.getString("KH_DN"));
                    obj.setKH_FONTWEIGHT(reset.getString("KH_FONTWEIGHT"));
                    obj.setKH_CAPHT(reset.getDouble("KH_CAPHT"));
                    obj.setKH_STT(reset.getDouble("KH_STT"));

                    //Them vao list
                    dataList.add(obj);
                }

//                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
//                int pn_err_cd = calstatement.getInt(5);
//                //thu hien lay mo ta loi
//                String strEdd_txt = calstatement.getString(6);
//                //Lay cursor ra resultset
                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " get_data_giaokh -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham get_data_xdkh " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " get_data_giaokh -> " + e.getMessage());
        }
        return dataList;
    }

    public List<String> getYearReport() throws SQLException {
        List<String> list = new ArrayList<String>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KHNV.sp_get_year_report( ?)}";
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

    public ChiTieuDetail getChiTieuDetail(String maCt, String maPGD, String reportGrade, int namBc) {
        ChiTieuDetail cTieuDetail = new ChiTieuDetail();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_RPT_KHNV.get_chi_tieu_detail(?,?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Tham so thu nhat module_id 
                calstatement.setString(1, maCt);
                calstatement.setString(2, maPGD);
                calstatement.setString(3, reportGrade);
                calstatement.setInt(4, namBc);

                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);

                //Thuc hien execute lay du lieu
                calstatement.execute();

                cTieuDetail.setTenCt(calstatement.getString(7));
                cTieuDetail.setKhDuocGiao(calstatement.getDouble(8));
                cTieuDetail.setCtDP(calstatement.getString(9));

                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(10);

                //Lay danh sach: maPGD-tenPGD-khGiao
                ArrayList<CTieuKHoachModel> cTieuKHoachModelList = new ArrayList<CTieuKHoachModel>();
                while (reset.next()) {
                    CTieuKHoachModel obj = new CTieuKHoachModel();

                    obj.setMaPGD(reset.getString("PO_MA"));
                    obj.setTenPGD(reset.getString("PO_TEN"));
                    obj.setGiaoKh(reset.getBigDecimal("KH_GIAO_KH"));
                    //System.err.println(reset.getBigDecimal("KH_GIAO_KH"));
                    //System.err.println("KH_GIAO_KH decimal: "+reset.getBigDecimal("KH_GIAO_KH"));
                    //Them vao list
                    cTieuKHoachModelList.add(obj);
                }

                cTieuDetail.setcTieuKHoachModelList(cTieuKHoachModelList);

                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(5);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(6);
                //Lay cursor ra resultset

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " get_data_giaokh -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getChiTieuDetail " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getChiTieuDetail -> " + e.getMessage());
        }
        return cTieuDetail;
    }

    public boolean saveGiaoKh(List<String> maPGD, List<String> giaoKh, String maCt, String posCD, String maCn, String reportGrade, int namBc, String userId) {
        ChiTieuDetail cTieuDetail = new ChiTieuDetail();
        boolean bSuccess = false;
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("ARRAY_TABLE", conn);

            //CuongBM: Convert List to array
            String[] arrayPGD = maPGD.toArray(new String[0]);
            String[] arrayGiaoKH = giaoKh.toArray(new String[0]);

            //CuongBM: Convert array --> array oracle su dung
            ARRAY oracle_arrayPGD = new ARRAY(des, conn, arrayPGD);
            ARRAY oracle_arrayGiaoKH = new ARRAY(des, conn, arrayGiaoKH);

            //CuongBM: 06Nov14
            String strStoreproce = "{call VBSP_RPT_KHNV.save_giao_kh(?,?,?,?,?,?,?,?,?,?)}";
//            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                calstatement.setArray(1, oracle_arrayPGD);
                calstatement.setArray(2, oracle_arrayGiaoKH);
                calstatement.setString(3, maCt);
                calstatement.setString(4, posCD);
                calstatement.setString(5, maCn);
                calstatement.setString(6, reportGrade);
                calstatement.setInt(7, namBc);
                calstatement.setString(8, userId);

                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.NUMBER);
                calstatement.registerOutParameter(10, oracle.jdbc.OracleTypes.VARCHAR);

                //Thuc hien execute lay du lieu
                calstatement.execute();

                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                int pn_err_cd = calstatement.getInt(9);
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(10);
                //Lay cursor ra resultset

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
                bSuccess = true;
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " saveGiaoKh -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham saveGiaoKh " + posCD + " " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveGiaoKh -> " + posCD + " " + e.getMessage());
        }
        return true;
    }
}
