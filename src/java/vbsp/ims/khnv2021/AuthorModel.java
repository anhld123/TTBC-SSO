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
            CallableStatement st = con.prepareCall("{call VBSP_IMS_KHNV2021.KHNV2021_GETPOS(?,?,?)}");
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
        Connection con = db.getConnect();
        try {
            //Thực hiện lấy các biến cần truy cập
            if (!cboDot.equals("5")) {
                CallableStatement st = con.prepareCall("{call VBSP_IMS_KHNV2021.KHNV2021_GETDATA_PGD(?,?,?,?,?,?,?,?,?,?)}");
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
                ResultSet rs = (ResultSet) st.getObject(10);
                while (rs.next()) {
                    DULIEU_NT obj = new DULIEU_NT();
                    lst.add(new getDULIEU_NT().getData(obj, rs));
                }
            } else {
                CallableStatement st = con.prepareCall("{call VBSP_IMS_KHNV2021.KHNV2024_GETDATA_PGD(?,?,?,?,?,?,?,?,?,?)}");
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
                ResultSet rs = (ResultSet) st.getObject(10);
                while (rs.next()) {
                    DULIEU_NT item = DULIEU_NT.newInstance();
                    item.setKIEUIN(rs.getInt("KIEUIN"));
                    item.setMA(rs.getString("MA"));
                    item.setTT_HIENTHI(rs.getString("TT_HIENTHI"));
                    item.setTEN(rs.getString("TEN"));
                    item.setD1(rs.getString("D1"));
                    item.setD2(rs.getString("D2"));
                    item.setD3(rs.getString("D3"));
                    item.setD4(rs.getString("D4"));
                    item.setD5(rs.getString("D5"));
                    item.setD6(rs.getString("D6"));
                    item.setD7(rs.getString("D7"));
                    item.setD8(rs.getString("D8"));
                    item.setD9(rs.getString("D9"));
                    item.setD10(rs.getString("D10"));
                    item.setD11(rs.getString("D11"));
                    item.setD12(rs.getString("D12"));
                    item.setD13(rs.getString("D13"));
                    item.setD14(rs.getString("D14"));
                    item.setD15(rs.getString("D15"));
                    item.setD16(rs.getString("D16"));
                    item.setD17(rs.getString("D17"));
                    item.setD18(rs.getString("D18"));
                    item.setD19(rs.getString("D19"));
                    item.setD20(rs.getString("THUTU"));
                    lst.add(item);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AuthorModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
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
            if (!cboDot.equals("5")) {
                st = con.prepareCall("{call VBSP_IMS_KHNV2021.KHNV2021_ROLLBACKDATA(?,?,?,?,?,?,?,?)}");
            } else {
                st = con.prepareCall("{call VBSP_IMS_KHNV2021.KHNV2024_ROLLBACKDATA(?,?,?,?,?,?,?,?)}");
            }
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
            if (cboDot.equals("5")) {
                st = con.prepareCall("{call VBSP_IMS_KHNV2021.KHNV2024_SAVEDATAPROVINCE(?,?,?,?,?,?,?,?,?)}");
            } else {
                st = con.prepareCall("{call VBSP_IMS_KHNV2021.KHNV2021_SAVEDATAPROVINCE(?,?,?,?,?,?,?,?,?)}");
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
            CallableStatement st = con.prepareCall("{call VBSP_IMS_KHNV2021.KHNV2021_SHOWMESSAGE(?,?,?,?,?,?,?,?)}");
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
