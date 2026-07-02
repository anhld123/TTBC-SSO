/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.khnv2021.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.khnv2021.model.DULIEU_NT_100;
import vbsp.ims.khnv2021.model.Mau02Model;
import vbsp.ims.khnv2021.model.DistrictInfo;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author HP
 */
public class DaoMau02 {

    public List<Mau02Model> getExportData(String posCode, String posFlag, String districtCode, String reportDate) {
        List<Mau02Model> lstData = new ArrayList<>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call PK_KHNV_DATA_EXPORT.Export_02(?, ?, ?, ?, ?)}";
            ResultSet cursor = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Truyen vao username
                calstatement.setString(1, posCode);
                calstatement.setString(2, posFlag);
                calstatement.setString(3, districtCode);
                calstatement.setString(4, reportDate);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                cursor = (ResultSet) calstatement.getObject(5);

                while (cursor.next()) {
                    Mau02Model item = new Mau02Model();
                    //item.reportDate = cursor.getString("TEN_THON");
                    item.posCode = posCode;
                    item.posFlag = posFlag;
                    item.order = cursor.getString("THUTU");
                    item.orderDisplay = cursor.getString("TT_HIENTHI");
                    item.code = cursor.getString("MACHITIEU");
                    item.name = cursor.getString("TENCHITIEU");
                    item.editFlag = cursor.getInt("THUCONG");
                    item.level = cursor.getInt("CAPCT");
                    item.levelCode = cursor.getString("CAPCT_MA");
                    item.printType = cursor.getInt("KIEUIN");
                    item.totalFlag = cursor.getInt("CONGCAP");
                    item.d1 = Double.parseDouble(getNumberValueString(cursor.getString("D1")));
                    item.d2 = Double.parseDouble(getNumberValueString(cursor.getString("D2")));
                    item.d3 = Double.parseDouble(getNumberValueString(cursor.getString("D3")));
                    item.d4 = Double.parseDouble(getNumberValueString(cursor.getString("D4")));
                    item.d5 = Double.parseDouble(getNumberValueString(cursor.getString("D5")));
                    item.d6 = Double.parseDouble(getNumberValueString(cursor.getString("D6")));

                    lstData.add(item);
                }

                if (cursor != null) {
                    cursor.close();
                }

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham DaoMau01A.getExportData " + e.getMessage());
                CoreLogger.error(DaoMau02.class.getCanonicalName() + " getExportData  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham DaoMau01A.getExportData " + e.getMessage());
            CoreLogger.error(DaoMau02.class.getCanonicalName() + " getExportData  -> " + e.getMessage());
        }
        return lstData;
    }

    public List<DistrictInfo> getDistrictByPos(String posCode) {
        List<DistrictInfo> lstData = new ArrayList<>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call PK_KHNV_DATA_EXPORT.GET_DISTRICT_BY_POS(?, ?)}";
            ResultSet cursor = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, posCode);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                cursor = (ResultSet) calstatement.getObject(2);

                while (cursor.next()) {
                    DistrictInfo item = new DistrictInfo();
                    item.posCode = cursor.getString("MA_PGD");
                    item.districtCode = cursor.getString("MA_QUAN_HUYEN");
                    item.districtName = cursor.getString("TEN");
                    lstData.add(item);
                }

                if (cursor != null) {
                    cursor.close();
                }

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham DaoMau02.getDistrictByPos " + e.getMessage());
                CoreLogger.error(DaoMau02.class.getCanonicalName() + " getExportData  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham DaoMau02.getDistrictByPos " + e.getMessage());
            CoreLogger.error(DaoMau02.class.getCanonicalName() + " getExportData  -> " + e.getMessage());
        }
        return lstData;
    }

    public List<DULIEU_NT_100> getExportData_02_2024(String posCode, String posFlag, String districtCode, String reportDate) {
        List<DULIEU_NT_100> lstData = new ArrayList<>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call PK_KHNV_DATA_EXPORT.Export_02_2024(?, ?, ?, ?, ?)}";
            ResultSet cursor = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Truyen vao username
                calstatement.setString(1, posCode);
                calstatement.setString(2, posFlag);
                calstatement.setString(3, districtCode);
                calstatement.setString(4, reportDate);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                cursor = (ResultSet) calstatement.getObject(5);

                while (cursor.next()) {
                    DULIEU_NT_100 item = new DULIEU_NT_100();
                    item.setKIEUIN(cursor.getInt("KIEUIN"));
                    item.setMA(cursor.getString("MA"));
                    item.setTT_HIENTHI(cursor.getString("TT_HIENTHI"));
                    item.setTEN(cursor.getString("TEN"));
                    item.setD1(cursor.getString("D1"));
                    item.setD2(cursor.getString("D2"));
                    item.setD3(cursor.getString("D3"));
                    item.setD4(cursor.getString("D4"));
                    item.setD5(cursor.getString("D5"));
                    item.setD6(cursor.getString("D6"));
                    item.setD7(cursor.getString("D7"));
                    item.setD8(cursor.getString("D8"));
                    item.setD9(cursor.getString("D9"));
                    item.setD10(cursor.getString("D10"));
                    item.setD11(cursor.getString("D11"));
                    item.setD12(cursor.getString("D12"));
                    item.setD13(cursor.getString("D13"));
                    item.setD14(cursor.getString("D14"));
                    item.setD15(cursor.getString("D15"));
                    item.setD16(cursor.getString("D16"));
                    item.setD17(cursor.getString("D17"));
                    item.setD18(cursor.getString("D18"));
                    item.setD19(cursor.getString("D19"));
                    item.setD100(cursor.getString("THUTU"));
                    lstData.add(item);
                }

                if (cursor != null) {
                    cursor.close();
                }

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham 022024.getExportData " + e.getMessage());
                CoreLogger.error(DaoMau02.class.getCanonicalName() + " getExportData  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham 022024.getExportData " + e.getMessage());
            CoreLogger.error(DaoMau02.class.getCanonicalName() + " getExportData  -> " + e.getMessage());
        }
        return lstData;
    }

    public List<DULIEU_NT_100> getExportData_2024(String commune, String subcommune, String districtCode, String reportDate) {
        List<DULIEU_NT_100> lstData = new ArrayList<>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call PK_KHNV_DATA_EXPORT.Export_01_2024(?, ?, ?, ?, ?)}";
            ResultSet cursor = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Truyen vao username
                calstatement.setString(1, commune);
                calstatement.setString(2, subcommune);
                calstatement.setString(3, districtCode);
                calstatement.setString(4, reportDate);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                cursor = (ResultSet) calstatement.getObject(5);

                while (cursor.next()) {
                    DULIEU_NT_100 item = new DULIEU_NT_100();
                    item.setKIEUIN(cursor.getInt("KIEUIN"));
//                    item.setD98(cursor.getString("D98"));
                    item.setD100(cursor.getString("NAMBC"));
                    item.setTT_HIENTHI(cursor.getString("THUTU"));
                    item.setMA(cursor.getString("MA"));
                    item.setTEN(cursor.getString("TEN"));
                    item.setD1(cursor.getString("D1"));
                    item.setD2(cursor.getString("D2"));
                    item.setD3(cursor.getString("D3"));
                    item.setD4(cursor.getString("D4"));
                    item.setD5(cursor.getString("D5"));
                    item.setD6(cursor.getString("D6"));
                    item.setD7(cursor.getString("D7"));
                    item.setD8(cursor.getString("D8"));
                    item.setD9(cursor.getString("D9"));
                    item.setD10(cursor.getString("D10"));
                    item.setD11(cursor.getString("D11"));
                    item.setD12(cursor.getString("D12"));
                    item.setD13(cursor.getString("D13"));
                    item.setD14(cursor.getString("D14"));
                    item.setD15(cursor.getString("D15"));
                    item.setD16(cursor.getString("D16"));
                    item.setD17(cursor.getString("D17"));
                    item.setD18(cursor.getString("D18"));
                    item.setD19(cursor.getString("D19"));
                    item.setD20(cursor.getString("D20"));
                    item.setD21(cursor.getString("D21"));
                    item.setD22(cursor.getString("D22"));
                    item.setD23(cursor.getString("D23"));
                    item.setD24(cursor.getString("D24"));
                    item.setD25(cursor.getString("D25"));
                    item.setD26(cursor.getString("D26"));
                    item.setD27(cursor.getString("D27"));
                    item.setD28(cursor.getString("D28"));
                    item.setD29(cursor.getString("D29"));
                    item.setD30(cursor.getString("D30"));
                    item.setD31(cursor.getString("D31"));
                    item.setD32(cursor.getString("D32"));
                    item.setD33(cursor.getString("D33"));
                    item.setD34(cursor.getString("D34"));
                    item.setD35(cursor.getString("D35"));
                    item.setD36(cursor.getString("D36"));
                    item.setD37(cursor.getString("D37"));
                    item.setD38(cursor.getString("D38"));
                    item.setD39(cursor.getString("D39"));
                    item.setD40(cursor.getString("D40"));
                    item.setD41(cursor.getString("D41"));
                    item.setD42(cursor.getString("D42"));
                    item.setD43(cursor.getString("D43"));
                    item.setD44(cursor.getString("D44"));
                    item.setD45(cursor.getString("D45"));
                    item.setD46(cursor.getString("D46"));
                    item.setD47(cursor.getString("D47"));
                    item.setD48(cursor.getString("D48"));
                    item.setD49(cursor.getString("D49"));
                    item.setD50(cursor.getString("D50"));
                    item.setD51(cursor.getString("D51"));
                    item.setD52(cursor.getString("D52"));
                    item.setD53(cursor.getString("D53"));
                    item.setD54(cursor.getString("D54"));
                    item.setD55(cursor.getString("D55"));
                    item.setD56(cursor.getString("D56"));
                    item.setD57(cursor.getString("D57"));
                    item.setD58(cursor.getString("D58"));
                    item.setD59(cursor.getString("D59"));
                    item.setD60(cursor.getString("D60"));
                    item.setD61(cursor.getString("D61"));
                    item.setD62(cursor.getString("D62"));
                    item.setD63(cursor.getString("D63"));
                    lstData.add(item);
                }

                if (cursor != null) {
                    cursor.close();
                }

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham DaoMau01_2024.getExportData " + e.getMessage());
                CoreLogger.error(DaoMau02.class.getCanonicalName() + " getExportData  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham DaoMau01_2024.getExportData " + e.getMessage());
            CoreLogger.error(DaoMau02.class.getCanonicalName() + " getExportData  -> " + e.getMessage());
        }
        return lstData;
    }

    public List<DULIEU_NT_100> getExportData_2026(String commune, String subcommune, String districtCode, String reportDate) {
        List<DULIEU_NT_100> lstData = new ArrayList<>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_KHNV.Export_01_2026(?, ?, ?, ?, ?)}";
            ResultSet cursor = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Truyen vao username
                calstatement.setString(1, commune);
                calstatement.setString(2, subcommune);
                calstatement.setString(3, districtCode);
                calstatement.setString(4, reportDate);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                cursor = (ResultSet) calstatement.getObject(5);

                while (cursor.next()) {
                    DULIEU_NT_100 item = new DULIEU_NT_100();
                    item.setKIEUIN(cursor.getInt("KIEUIN"));
//                    item.setD98(cursor.getString("D98"));
                    item.setD100(cursor.getString("NAMBC"));
                    item.setTT_HIENTHI(cursor.getString("THUTU"));
                    item.setMA(cursor.getString("MA"));
                    item.setTEN(cursor.getString("TEN"));
                    item.setD1(cursor.getString("D1"));
                    item.setD2(cursor.getString("D2"));
                    item.setD3(cursor.getString("D3"));
                    item.setD4(cursor.getString("D4"));
                    item.setD5(cursor.getString("D5"));
                    item.setD6(cursor.getString("D6"));
                    item.setD7(cursor.getString("D7"));
                    item.setD8(cursor.getString("D8"));
                    item.setD9(cursor.getString("D9"));
                    item.setD10(cursor.getString("D10"));
                    item.setD11(cursor.getString("D11"));
                    item.setD12(cursor.getString("D12"));
                    item.setD13(cursor.getString("D13"));
                    item.setD14(cursor.getString("D14"));
                    item.setD15(cursor.getString("D15"));
                    item.setD16(cursor.getString("D16"));
                    item.setD17(cursor.getString("D17"));
                    item.setD18(cursor.getString("D18"));
                    item.setD19(cursor.getString("D19"));
                    lstData.add(item);
                }

                if (cursor != null) {
                    cursor.close();
                }

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham DaoMau01_2024.getExportData " + e.getMessage());
                CoreLogger.error(DaoMau02.class.getCanonicalName() + " getExportData  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham DaoMau01_2024.getExportData " + e.getMessage());
            CoreLogger.error(DaoMau02.class.getCanonicalName() + " getExportData  -> " + e.getMessage());
        }
        return lstData;
    }

    private String getNumberValueString(String value) {
        if (value == null || value.isEmpty()) {
            return "0";
        } else {
            return value;
        }
    }

    public List<DULIEU_NT_100> getExportData2_2026(String poscd, String posflag, String districtCode, String reportDate, String commune, String subcommune) {
        List<DULIEU_NT_100> lstData = new ArrayList<>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_KHNV.Export_02_2026(?, ?, ?, ?, ?,?,?)}";
            ResultSet cursor = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Truyen vao username
                calstatement.setString(1, poscd);
                calstatement.setString(2, posflag);
                calstatement.setString(3, districtCode);
                calstatement.setString(4, reportDate);
                calstatement.setString(5, commune);
                calstatement.setString(6, subcommune);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                cursor = (ResultSet) calstatement.getObject(7);

                while (cursor.next()) {
                    DULIEU_NT_100 item = new DULIEU_NT_100();
                    item.setKIEUIN(cursor.getInt("KIEUIN"));
//                    item.setD98(cursor.getString("D98"));
                    item.setD100(cursor.getString("NAMBC"));
                    item.setTT_HIENTHI(cursor.getString("THUTU"));
                    item.setMA(cursor.getString("MA"));
                    item.setTEN(cursor.getString("TEN"));
                    item.setD1(cursor.getString("D1"));
                    item.setD2(cursor.getString("D2"));
                    item.setD3(cursor.getString("D3"));
                    item.setD4(cursor.getString("D4"));
                    item.setD5(cursor.getString("D5"));
                    item.setD6(cursor.getString("D6"));
                    item.setD7(cursor.getString("D7"));
                    item.setD8(cursor.getString("D8"));
                    item.setD9(cursor.getString("D9"));
                    item.setD10(cursor.getString("D10"));
                    item.setD11(cursor.getString("D11"));
                    item.setD12(cursor.getString("D12"));
                    item.setD13(cursor.getString("D13"));
                    item.setD14(cursor.getString("D14"));
                    item.setD15(cursor.getString("D15"));
                    item.setD16(cursor.getString("D16"));
                    item.setD17(cursor.getString("D17"));
                    item.setD18(cursor.getString("D18"));
                    item.setD19(cursor.getString("D19"));
                    item.setD20(cursor.getString("D20"));
                    item.setD21(cursor.getString("D21"));
                    item.setD22(cursor.getString("D22"));
                    item.setD23(cursor.getString("D23"));
                    lstData.add(item);
                }

                if (cursor != null) {
                    cursor.close();
                }

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham DaoMau02_2024.getExportData " + e.getMessage());
                CoreLogger.error(DaoMau02.class.getCanonicalName() + " getExportData  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham DaoMau02_2024.getExportData " + e.getMessage());
            CoreLogger.error(DaoMau02.class.getCanonicalName() + " getExportData  -> " + e.getMessage());
        }
        return lstData;
    }
    
    public List<DULIEU_NT_100> getExportData3_2026(String poscd, String posflag, String districtCode, String reportDate, String commune, String subcommune) {
        List<DULIEU_NT_100> lstData = new ArrayList<>();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_KHNV.Export_03_2026(?, ?, ?, ?, ?,?,?)}";
            ResultSet cursor = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Truyen vao username
                calstatement.setString(1, poscd);
                calstatement.setString(2, posflag);
                calstatement.setString(3, districtCode);
                calstatement.setString(4, reportDate);
                calstatement.setString(5, commune);
                calstatement.setString(6, subcommune);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //Lay cursor ra resultset
                cursor = (ResultSet) calstatement.getObject(7);

                while (cursor.next()) {
                    DULIEU_NT_100 item = new DULIEU_NT_100();
                    item.setKIEUIN(cursor.getInt("KIEUIN"));
//                    item.setD98(cursor.getString("D98"));
                    item.setD100(cursor.getString("NAMBC"));
                    item.setTT_HIENTHI(cursor.getString("THUTU"));
                    item.setMA(cursor.getString("MA"));
                    item.setTEN(cursor.getString("TEN"));
                    item.setD1(cursor.getString("D1"));
                    item.setD2(cursor.getString("D2"));
                    item.setD3(cursor.getString("D3"));
                    item.setD4(cursor.getString("D4"));
                    item.setD5(cursor.getString("D5"));
                    item.setD6(cursor.getString("D6"));
                    item.setD7(cursor.getString("D7"));
                    item.setD8(cursor.getString("D8"));
                    item.setD9(cursor.getString("D9"));
                    item.setD10(cursor.getString("D10"));
                    item.setD11(cursor.getString("D11"));
                    item.setD12(cursor.getString("D12"));
                    item.setD13(cursor.getString("D13"));
                    item.setD14(cursor.getString("D14"));
                    item.setD15(cursor.getString("D15"));
                    item.setD16(cursor.getString("D16"));
                    item.setD17(cursor.getString("D17"));
                    item.setD18(cursor.getString("D18"));
                    item.setD19(cursor.getString("D19"));
                    lstData.add(item);
                }

                if (cursor != null) {
                    cursor.close();
                }

                if (calstatement != null) {
                    calstatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Loi trong ham DaoMau02_2024.getExportData " + e.getMessage());
                CoreLogger.error(DaoMau02.class.getCanonicalName() + " getExportData  -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham DaoMau02_2024.getExportData " + e.getMessage());
            CoreLogger.error(DaoMau02.class.getCanonicalName() + " getExportData  -> " + e.getMessage());
        }
        return lstData;
    }
}
