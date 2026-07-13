package vbsp.ims.khnv2021;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.bcqt.model.DULIEU_NT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;

/**
 *
 * @author Admin
 */
public class AuthorModel {

    //Hàm lấy danh mục đơn vị theo cấp báo cáo
    public List<PosClass> getPosCD(String CapBC, String TenDN) {
        List<PosClass> lst = new ArrayList<>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        try {
            //Thực hiện lấy các biến cần truy cập
            CallableStatement st = con.prepareCall("{call VBSP_IMS_KHNV.KHNV_GETPOS(?,?,?)}");
            st.setString(1, CapBC);
            st.setString(2, TenDN);
            st.registerOutParameter(3, OracleTypes.CURSOR);
            st.execute();
            ResultSet rs = (ResultSet) st.getObject(3);
            while (rs.next()) {
                PosClass obj = new PosClass();
                lst.add(new PosClass(rs.getString("PO_MA"), rs.getString("PO_TEN")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AuthorModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    //Hàm lấy tải dữ liệu
    public List<DULIEU_NT> getData(String CapBC, String TenDN, String cboDonvi, String cboNam, String cboDot, String cboTonghop, String strNguyennhan) {

        List<DULIEU_NT> lst = new ArrayList<>();
        DaoConnect db = new DaoConnect();

        try (Connection con = db.getConnect()) {
            if (con == null) {
                return lst;
            }

            String procedure;
            if (!"1".equals(cboDot) && !"5".equals(cboDot)) {
                procedure = "VBSP_IMS_KHNV2021.KHNV2021_GETDATA_PGD";
            } else {
                procedure = "1".equals(cboDot) ? "VBSP_IMS_KHNV.KHNV_GETDATA_PGD" : "VBSP_IMS_KHNV2021.KHNV2024_GETDATA_PGD";
            }

            String sql = "{call " + procedure + "(?,?,?,?,?,?,?,?,?,?)}";

            try (CallableStatement st = con.prepareCall(sql)) {
                // Gán các tham số chung
                st.setString(1, CapBC);
                st.setString(2, TenDN);
                st.setString(3, cboDonvi);
                st.setString(4, cboNam);
                st.setString(5, cboDot);
                st.setString(6, cboTonghop);
                st.setString(7, strNguyennhan);
                st.registerOutParameter(8, OracleTypes.NUMBER);
                st.registerOutParameter(9, OracleTypes.VARCHAR);
                st.registerOutParameter(10, OracleTypes.CURSOR);

                st.execute();

                // 2. Đọc dữ liệu từ ResultSet
                try (ResultSet rs = (ResultSet) st.getObject(10)) {
                    boolean isSpecialCase = !"1".equals(cboDot) && !"5".equals(cboDot);

                    while (rs.next()) {
                        DULIEU_NT item;

                        if (isSpecialCase) {
                            DULIEU_NT obj = new DULIEU_NT();
                            item = new getDULIEU_NT().getData(obj, rs);
                        } else {
                            // Trường hợp cboDot bằng 1 hoặc 5
                            item = DULIEU_NT.newInstance();
                            mapShortFields(item, rs);
                        }

                        lst.add(item);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AuthorModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lst;
    }

    private void mapFullFields(DULIEU_NT item, ResultSet rs) throws SQLException {
        item.setKHOA(rs.getString("KHOA"));
        item.setTHUTU(rs.getInt("THUTU"));
        item.setTT_HIENTHI(rs.getString("TT_HIENTHI"));
        item.setMA(rs.getString("MA"));
        item.setTEN(rs.getString("TEN"));
        item.setNGAYBC(rs.getDate("NGAYBC"));
        item.setNAMBC(rs.getInt("NAMBC"));
        item.setMAPGD(rs.getString("MAPGD"));
        item.setCO_TONGHOP(rs.getString("CO_TONGHOP"));
        item.setMACN(rs.getString("MACN"));
        item.setNGUOI_NHAP(rs.getString("NGUOI_NHAP"));
        item.setNGAY_NHAP(rs.getDate("NGAY_NHAP"));
        item.setNGUOI_DUYET(rs.getString("NGUOI_DUYET"));
        item.setNGAY_DUYET(rs.getDate("NGAY_DUYET"));

        for (int i = 1; i <= 30; i++) {
            try {
                item.getClass().getMethod("setD" + i, String.class).invoke(item, rs.getString("D" + i));
            } catch (Exception e) {
            }
        }
    }

// Hàm phụ gán dữ liệu cho các trường hợp còn lại
    private void mapShortFields(DULIEU_NT item, ResultSet rs) throws SQLException {
        item.setKIEUIN(rs.getInt("KIEUIN"));
        item.setMA(rs.getString("MA"));
        item.setTT_HIENTHI(rs.getString("TT_HIENTHI"));
        item.setTEN(rs.getString("TEN"));

        for (int i = 1; i <= 19; i++) {
            try {
                item.getClass().getMethod("setD" + i, String.class).invoke(item, rs.getString("D" + i));
            } catch (Exception e) {
            }
        }
        item.setD20(rs.getString("THUTU"));
    }
    //Hàm gửi dữ liệu
    // 10: Gửi dữ liệu thành công; 11: Gửi không thành công

    public String sendData(String CapBC, String TenDN, String cboDonvi, String cboNam, String cboDot, String cboTonghop, String strNguyennhan) {
        List<DULIEU_NT> lst = new ArrayList<>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        String ChkSuccess = "10";
        try {
            //Thực hiện lấy các biến cần truy cập
            CallableStatement st = con.prepareCall("{call VBSP_IMS_KHNV2021.KHNV2021_SENDDATA(?,?,?,?,?,?,?,?)}");
            st.setString(1, CapBC);
            st.setString(2, TenDN);
            st.setString(3, cboDonvi);
            st.setString(4, cboNam);
            st.setString(5, cboDot);
            st.setString(6, cboTonghop);
            st.setString(7, strNguyennhan);
            st.registerOutParameter(8, OracleTypes.VARCHAR);
            st.execute();
            ChkSuccess = (String) st.getObject(8);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ChkSuccess;
    }

    //Hàm hoàn trả
    // 20: Gửi dữ liệu thành công; 21: Gửi không thành công
    public String rollBackData(String CapBC, String TenDN, String cboDonvi, String cboNam, String cboDot, String cboTonghop, String strNguyennhan) {
        List<DULIEU_NT> lst = new ArrayList<>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        String ChkSuccess = "20";
        try {
            //Thực hiện lấy các biến cần truy cập
            CallableStatement st;
            String procedure;

            switch (cboDot) {
                case "1":
                    procedure = "VBSP_IMS_KHNV.KHNV_ROLLBACKDATA";
                    break;
                case "5":
                    procedure = "VBSP_IMS_KHNV2021.KHNV2024_ROLLBACKDATA";
                    break;
                default:
                    // Các đợt khác 1 và 5
                    procedure = "VBSP_IMS_KHNV2021.KHNV2021_ROLLBACKDATA";
                    break;
            }

            st = con.prepareCall("{call " + procedure + "(?,?,?,?,?,?,?,?)}");
            st.setString(1, CapBC);
            st.setString(2, TenDN);
            st.setString(3, cboDonvi);
            st.setString(4, cboNam);
            st.setString(5, cboDot);
            st.setString(6, cboTonghop);
            st.setString(7, strNguyennhan);
            st.registerOutParameter(8, OracleTypes.VARCHAR);
            st.execute();
            ChkSuccess = (String) st.getObject(8);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ChkSuccess;
    }

    // 30: Lưu dữ liệu thành công; 31: Lưu không thành công
    public String SaveDataProvince(String CapBC, String TenDN, String cboDonvi, String cboNam, String cboDot, String cboTonghop, String strNguyennhan, List<DULIEU_NT> lstData) throws SQLException {

        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        //------Chuyển rạng mảng thành Object của Oracle
        Object array[] = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor.createDescriptor(DULIEU_NT.ORACLE_TABLE_TYPE, con);
        ARRAY LstArray = new ARRAY(des, con, array);
        //---------------------------------------------------------------------------

        String ChkSuccess = "10";
        try {
            //Lưu dữ liệu vào CSDL và trả về kết quả
            CallableStatement st;
            switch (cboDot) {
                case "5":
                    st = con.prepareCall("{call VBSP_IMS_KHNV2021.KHNV2024_SAVEDATAPROVINCE(?,?,?,?,?,?,?,?,?)}");
                    break;
                case "1":
                    st = con.prepareCall("{call VBSP_IMS_KHNV.KHNV_SAVEDATAPROVINCE(?,?,?,?,?,?,?,?,?)}");
                    break;
                default:
                    st = con.prepareCall("{call VBSP_IMS_KHNV2021.KHNV2021_SAVEDATAPROVINCE(?,?,?,?,?,?,?,?,?)}");
                    break;
            }
            st.setString(1, CapBC);
            st.setString(2, TenDN);
            st.setString(3, cboDonvi);
            st.setString(4, cboNam);
            st.setString(5, cboDot);
            st.setString(6, cboTonghop);
            st.setString(7, strNguyennhan);
            st.setArray(8, LstArray);
            st.registerOutParameter(9, OracleTypes.VARCHAR);
            st.execute();
            ChkSuccess = (String) st.getObject(9);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ChkSuccess;
    }

    // 30: Lưu dữ liệu thành công; 31: Lưu không thành công
    public String ShowMessage(String CapBC, String TenDN, String cboDonvi, String cboNam, String cboDot, String cboTonghop, String strNguyennhan) throws SQLException {

        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        String Message = "";
        try {
            //Lưu dữ liệu vào CSDL và trả về kết quả
            CallableStatement st = con.prepareCall("{call VBSP_IMS_KHNV.KHNV_SHOWMESSAGE(?,?,?,?,?,?,?,?)}");
            st.setString(1, CapBC);
            st.setString(2, TenDN);
            st.setString(3, cboDonvi);
            st.setString(4, cboNam);
            st.setString(5, cboDot);
            st.setString(6, cboTonghop);
            st.setString(7, strNguyennhan);
            st.registerOutParameter(8, OracleTypes.VARCHAR);
            st.execute();
            Message = (String) st.getObject(8);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Message;
    }

    public String getMenuIdBc() {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        String Message = "";
        try {
            //Lưu dữ liệu vào CSDL và trả về kết quả
            CallableStatement st = con.prepareCall("{call PROC_GETMNID_KHTD(?)}");
            st.registerOutParameter(1, OracleTypes.VARCHAR);
            st.execute();
            Message = (String) st.getObject(1);
            Message = "<iframe id=\"ifPrint\" src=\"/IMS_REPORTS/Menu_redirect.action?menuUrl=include_rptmanaget&menuId=" + Message.trim() + "\" width=\"100%\" height=\"100%\" ></iframe>";
        } catch (SQLException ex) {
            Logger.getLogger(AuthorModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Message;
    }
}
