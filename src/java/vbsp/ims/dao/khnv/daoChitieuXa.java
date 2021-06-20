/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao.khnv;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.model.khnv.ChitieuKHoachXa;
import vbsp.ims.model.khnv.GiaokhModel;
import vbsp.ims.model.khnv.Phanquyen;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author BAOANH
 */
public class daoChitieuXa {

    public static daoChitieuXa newInstance() {
        return new daoChitieuXa();
    }

    public List<ListValue> getchitieucha(String status, String loai_nv) throws SQLException {
        List<ListValue> lstChitieucha = new ArrayList<ListValue>();
        Connection conn = null;
        conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_RPT_KHNV.sp_get_chitieu_cha_xa(?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
            calstatement.setString(1, status);
            calstatement.setString(2, loai_nv);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);

            //Thuc hien execute lay du lieu
            calstatement.execute();

            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(3);
            //COLUMN_DESC
            while (reset.next()) {
                lstChitieucha.add(new ListValue(reset.getString(1), reset.getString(2)));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getchitieucha -> " + e.getMessage());
            throw new SQLException();
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
        return lstChitieucha;
    }

     public List<ListValue> getquyetdinh() throws SQLException {
        List<ListValue> lstQuyetdinh = new ArrayList<ListValue>();
        Connection conn = null;
        conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_RPT_KHNV.sp_lay_quyetdinh(?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);

            //Thuc hien execute lay du lieu
            calstatement.execute();

            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(1);
            //COLUMN_DESC
            while (reset.next()) {
                lstQuyetdinh.add(new ListValue(reset.getString(1), reset.getString(2)));
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getchitieucha -> " + e.getMessage());
            throw new SQLException();
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
        return lstQuyetdinh;
    }
     
    public List<ChitieuKHoachXa> getsuaxoachitieu(String loai_nv, String ma_chitieu) throws SQLException {
        List<ChitieuKHoachXa> lstChitieu = new ArrayList<ChitieuKHoachXa>();
        Connection conn = null;
        conn = new DaoConnect().getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_RPT_KHNV.sp_get_chitieu_suaxoa(?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos   
            calstatement.setString(1, loai_nv);
            calstatement.setString(2, ma_chitieu);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);

            //Thuc hien execute lay du lieu
            calstatement.execute();

            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(3);
            //COLUMN_DESC
            while (reset.next()) {
                ChitieuKHoachXa chitieu = new ChitieuKHoachXa();
                chitieu.setKH_STT(reset.getInt(1));
                chitieu.setKH_MA_CT(reset.getString(2));
                chitieu.setKH_STT_HT(reset.getString(3));
                chitieu.setKH_CHI_TIEU(reset.getString(4));
                chitieu.setKH_FONTWEIGHT(reset.getString(5));
                chitieu.setKH_CONGTHUC(reset.getString(6));
                chitieu.setKH_MAQD(reset.getString(7));
                lstChitieu.add(chitieu);
            }

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getchitieucha -> " + e.getMessage());
            throw new SQLException();
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
        return lstChitieu;
    }

    public List<ModelTreeNode> getDataPosTreeNode(String capbc, String username) throws SQLException {
        List<ModelTreeNode> lstPo = new ArrayList<ModelTreeNode>();

        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_RPT_KHNV.sp_get_tree_xa(?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, capbc);
            calstatement.setString(2, username);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(3);
            //COLUMN_DESC
            while (reset.next()) {
                lstPo.add(new ModelTreeNode(reset.getString("PARENT_CD"), reset.getString("PARENT_DESC"),
                        reset.getString("CHILD_CD"), reset.getString("CHILD_DESC")));
            }

            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataPosTreeNode -> " + e.getMessage());
            throw new SQLException("Error getdata tree username=" + username, e);
        }

        return lstPo;
    }

    public int isCheckmachitieu(String loai_nv, String ma_chitieu) throws SQLException {
        int chitieu = 0;
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_RPT_KHNV.f_check_machitieu(?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            calstatement.setString(2, loai_nv);
            calstatement.setString(3, ma_chitieu);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            chitieu = calstatement.getInt(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " isCheckmachitieu -> " + e.getMessage());
            throw new SQLException(e);
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
        return chitieu;
    }

    public boolean saveChitieuAdd(String loai_nv,String ma_chitieu_cha, String ma_ct_truoc, String ma_chitieu, String kytu_hienthi,
            String ten_chitieu, String loai_ct, String username, String ma_quyetdinh) throws SQLException {
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_RPT_KHNV.sp_save_chitieu_add(?,?,?,?,?,?,?,?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, loai_nv);
             calstatement.setString(2, ma_chitieu_cha);
            calstatement.setString(3, ma_ct_truoc);
            calstatement.setString(4, ma_chitieu);
            calstatement.setString(5, kytu_hienthi);
            calstatement.setString(6, ten_chitieu);
            calstatement.setString(7, loai_ct);
            calstatement.setString(8, username);
             calstatement.setString(9, ma_quyetdinh);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveChitieuAdd -> " + e.getMessage());
            throw new SQLException(e);
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
        return true;
    }

    public boolean saveSuaChitieu(String loai_nv, String ma_chitieu, String kytu_hienthi,
            String ten_chitieu, String loai_ct, String username, String ma_quyetdinh) throws SQLException {
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_RPT_KHNV.SP_SAVE_SUA_CHITIEU(?,?,?,?,?,?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, loai_nv);
            calstatement.setString(2, ma_chitieu);
            calstatement.setString(3, kytu_hienthi);
            calstatement.setString(4, ten_chitieu);
            calstatement.setString(5, loai_ct);
            calstatement.setString(6, username);
            calstatement.setString(7, ma_quyetdinh);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveSuaChitieu -> " + e.getMessage());
            throw new SQLException(e);
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
        return true;
    }

    public boolean saveChitieu_Donvi(String loai_nv, List<String> ma_ct, List<String> ma_donvi, String userName) throws SQLException {
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_RPT_KHNV.SP_SAVE_PHANQUYEN_CHITIEU_XA(?, ?,?,?)}";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("ARRAY_TABLE", conn);

            //CuongBM: Convert List to array
            String[] arrayMaCT = ma_ct.toArray(new String[0]);   //Ma Chi Tieu
            String[] arrayMadv = ma_donvi.toArray(new String[0]); //mã đơn vị (xã)

            //CuongBM: Convert array --> array oracle su dung
            ARRAY oracle_arrayMaCT = new ARRAY(des, conn, arrayMaCT);
            ARRAY oracle_arrayMadv = new ARRAY(des, conn, arrayMadv);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, loai_nv);
            calstatement.setArray(2, oracle_arrayMaCT);
            calstatement.setArray(3, oracle_arrayMadv);
            calstatement.setString(4, userName);

            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveChitieu_Donvi -> " + e.getMessage());
            throw new SQLException(e);
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
        return true;
    }

    public String deleteChitieu(String loai_nv, String ma_chitieu) throws SQLException {
        String message = null;
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_RPT_KHNV.SP_DELETE_CHITIEU(?,?,?)}";
        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, loai_nv);
            calstatement.setString(2, ma_chitieu);
            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.NVARCHAR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            message=calstatement.getString(3);
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " deleteChitieu -> " + e.getMessage());
            throw new SQLException(e);
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
        return message;
    }

    public boolean saveChitieu_DSDonvi(String loai_nv, String ma_ct, List<String> ma_donvi, String userName) throws SQLException {
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_RPT_KHNV.SP_SAVE_PQ_CHITIEU_DSXA(?, ?,?,?)}";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("ARRAY_TABLE", conn);

            //CuongBM: Convert List to array
            String[] arrayMadv = ma_donvi.toArray(new String[0]); //mã đơn vị (xã)

            //CuongBM: Convert array --> array oracle su dung
            ARRAY oracle_arrayMadv = new ARRAY(des, conn, arrayMadv);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, loai_nv);
            calstatement.setString(2, ma_ct);
            calstatement.setArray(3, oracle_arrayMadv);
            calstatement.setString(4, userName);

            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveChitieu_DSDonvi -> " + e.getMessage());
            throw new SQLException(e);
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
        return true;
    }

    public boolean saveDonvi_DSChitieu(String loai_nv, List<String> ma_ct, String ma_donvi, String userName) throws SQLException {
        CallableStatement calstatement = null;
        ResultSet reset = null;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_RPT_KHNV.SP_SAVE_PQ_XA_DSCHITIEU(?, ?,?,?)}";
        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("ARRAY_TABLE", conn);

            //CuongBM: Convert List to array
            String[] arrayMaCT = ma_ct.toArray(new String[0]);   //Ma Chi Tieu

            //CuongBM: Convert array --> array oracle su dung
            ARRAY oracle_arrayMaCT = new ARRAY(des, conn, arrayMaCT);
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.setString(1, loai_nv);
            calstatement.setArray(2, oracle_arrayMaCT);
            calstatement.setString(3, ma_donvi);
            calstatement.setString(4, userName);

            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveDonvi_DSChitieu -> " + e.getMessage());
            throw new SQLException(e);
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
        return true;
    }

    public List<Phanquyen> getDataChitieuDanhsachDonvi(String loai_nv, String ma_ct, String capbc, String username) throws SQLException {
        List<Phanquyen> lstPo = new ArrayList<Phanquyen>();

        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_RPT_KHNV.SP_GET_PQ_CHITIEU_DSDOWNVI(?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, loai_nv);
            calstatement.setString(2, ma_ct);
            calstatement.setString(3, capbc);
            calstatement.setString(4, username);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(5);
            //COLUMN_DESC
            while (reset.next()) {
                Phanquyen pq = new Phanquyen();
                pq.setStt(reset.getInt(1));
                pq.setKhoa(reset.getString(2));
                pq.setHienthi(reset.getString(3));
                pq.setTen(reset.getString(4));
                pq.setFONTWEIGHT(reset.getString(5));
                pq.setTrangthai(reset.getString(6));
                lstPo.add(pq);
            }

            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataChitieuDanhsachDonvi -> " + e.getMessage());
            throw new SQLException("Error getdata tree username=" + username, e);
        }

        return lstPo;
    }

    public List<Phanquyen> getDataDonviDanhsachChitieu(String loai_nv, String ma_donvi, String capbc, String username) throws SQLException {
        List<Phanquyen> lstPo = new ArrayList<Phanquyen>();

        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{call VBSP_RPT_KHNV.SP_GET_PQ_DOWNVI_DSCHITIEU(?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.setString(1, loai_nv);
            calstatement.setString(2, ma_donvi);
            calstatement.setString(3, capbc);
            calstatement.setString(4, username);
            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            //Lay cursor ra resultset
            reset = (ResultSet) calstatement.getObject(5);
            //COLUMN_DESC
            while (reset.next()) {
                Phanquyen pq = new Phanquyen();
                pq.setStt(reset.getInt(1));
                pq.setKhoa(reset.getString(2));
                pq.setHienthi(reset.getString(3));
                pq.setTen(reset.getString(4));
                pq.setFONTWEIGHT(reset.getString(5));
                pq.setTrangthai(reset.getString(6));
                lstPo.add(pq);
            }

            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataDonviDanhsachChitieu -> " + e.getMessage());
            throw new SQLException("Error getdata tree username=" + username, e);
        }

        return lstPo;
    }

    /**
     * 
     * @param loai_nv loại nghiệp vụ
     * @param ma mã truyền vào là mã đơn vị hoặc mã chỉ tiêu
     * @param capbc cấp báo cáo
     * @param username tên đăng nhập
     * @param trangthai trạng thái là Y lấy theo PGD, còn lại là chỉ tiêu
     * @return 
     */
    public String gettenChitieuDonvi(String loai_nv, String ma, String capbc, String username, String trangthai) throws SQLException {
        String name;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_RPT_KHNV.F_GET_TEN_DONVI_CHITIEU(?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //Tham so thu nhat truyen vao la co lay theo pos hay main pos
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calstatement.setString(2, loai_nv);
            calstatement.setString(3, ma);
            calstatement.setString(4, capbc);
            calstatement.setString(5, username);
            calstatement.setString(6, trangthai);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            //Lay cursor ra resultset
            //COLUMN_DESC
            name=calstatement.getString(1);

            if (reset != null) {
                reset.close();
            }
            if (calstatement != null) {
                calstatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataDonviDanhsachChitieu -> " + e.getMessage());
            throw new SQLException("Error getdata tree username=" + username, e);
        }

        return name;
    }

    public static void main(String[] args) throws SQLException {

        System.err.println(daoChitieuXa.newInstance().gettenChitieuDonvi("XDKH_XA", "2", "1", "P0401", "N"));
        daoChitieuXa.newInstance().getDataChitieuDanhsachDonvi("XDKH_XA", "2", "1", "P0401");
        daoChitieuXa.newInstance().getDataDonviDanhsachChitieu("XDKH_XA", "040101", "1", "P0401");

//        daoChitieuXa.newInstance().getDataPosTreeNode("1", "P0401");
//        daoChitieuXa.newInstance().getchitieucha("N", "XDKH_XA");
        List<String> lstMact = new ArrayList<String>();
        lstMact.add("AAAAA");
        lstMact.add("BBBBB");
        lstMact.add("CCCCC");
        List<String> lstMaDV = new ArrayList<String>();
        lstMaDV.add("040101");
        lstMaDV.add("040102");
        lstMaDV.add("040103");
        daoChitieuXa.newInstance().saveChitieu_Donvi("XDKH_XA", lstMact, lstMaDV, "TUNGNV");
    }

}
