package vbsp.ims.cictt200;

import java.io.File;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.xdb.XMLType;
import org.w3c.dom.Document;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.report.fast.ListValue;

public class daoBCTCCICTT200 {

    public List<ListValue> getModuleCic() throws SQLException {
        List<ListValue> moduleList = new ArrayList();
        Connection conn = null;
        conn = (new DaoConnect()).getConnect();
        CallableStatement calstatement = null;
        String strStoreproce = "{call ims_cic_tt200.sp_get_module_cic(?)}";
        ResultSet reset = null;

        try {
            calstatement = conn.prepareCall(strStoreproce, 1003, 1007);
            calstatement.registerOutParameter(1, -10);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(1);

            while (reset.next()) {
                moduleList.add(new ListValue(reset.getString("SKEY"), reset.getString("SDESC"), reset.getString("GROUP_ORDER")));
            }
        } catch (SQLException var10) {
            System.err.print(var10.getMessage());
            CoreLogger.error(this.getClass().getName() + " getModuleCic -> " + var10.getMessage());
            throw new SQLException();
        } finally {
            if (reset != null) {
                reset.close();
            }

            if (calstatement != null) {
                calstatement.close();
            }

        }

        return moduleList;
    }

    public List<ListValue> getDoanhnghiepCic(String username, String grade) throws SQLException {
        List<ListValue> doanhNghiepList = new ArrayList();
        Connection conn = null;
        conn = (new DaoConnect()).getConnect();
        CallableStatement calstatement = null;
        String strStoreproce = "{call ims_cic_tt200.sp_get_doanhnghiep(?,?,?)}";
        ResultSet reset = null;

        try {
            calstatement = conn.prepareCall(strStoreproce, 1003, 1007);
            calstatement.setString(1, username);
            calstatement.setString(2, grade);
            calstatement.registerOutParameter(3, -10);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(3);

            while (reset.next()) {
                doanhNghiepList.add(new ListValue(reset.getString("SKEY"), reset.getString("SDESC"), reset.getString("GROUP_ORDER")));
            }
        } catch (SQLException var12) {
            System.err.print(var12.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDoanhnghiepCic -> " + var12.getMessage());
            throw new SQLException();
        } finally {
            if (reset != null) {
                reset.close();
            }

            if (calstatement != null) {
                calstatement.close();
            }

        }

        return doanhNghiepList;
    }

    public List<ListValue> getNambcCic() throws SQLException {
        List<ListValue> nambcList = new ArrayList();
        Connection conn = null;
        conn = (new DaoConnect()).getConnect();
        CallableStatement calstatement = null;
        String strStoreproce = "{call ims_cic_tt200.sp_get_nambc(?)}";
        ResultSet reset = null;

        try {
            calstatement = conn.prepareCall(strStoreproce, 1003, 1007);
            calstatement.registerOutParameter(1, -10);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(1);

            while (reset.next()) {
                nambcList.add(new ListValue(reset.getString(1), reset.getString(1), reset.getString(1)));
            }
        } catch (SQLException var10) {
            System.err.print(var10.getMessage());
            CoreLogger.error(this.getClass().getName() + " getNambcCic -> " + var10.getMessage());
            throw new SQLException();
        } finally {
            if (reset != null) {
                reset.close();
            }

            if (calstatement != null) {
                calstatement.close();
            }

        }

        return nambcList;
    }

    public List<QT_DULIEU_NT> loadDataDoanhnghiep(String Khoa, int nambc, String ma_dn, String username, String grade, List<String> lstMapgd, String sKiemtoan, String sBcao, String sThongtu) throws SQLException {
        List<QT_DULIEU_NT> lstBcqt_NT = new ArrayList();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
        String[] arrayPoscd = (String[]) lstMapgd.toArray(new String[0]);
        ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
        String strStoreproce = "{call ims_cic_tt200.sp_load_data_doanhnghiep(?,?,?,?,?,?,?,?,?,?)}";
        ResultSet reset = null;

        try {
            calstatement = conn.prepareCall(strStoreproce, 1003, 1007);
            calstatement.registerOutParameter(10, OracleTypes.CURSOR);
            calstatement.setString(1, Khoa);
            calstatement.setInt(2, nambc);
            calstatement.setString(3, ma_dn);
            calstatement.setString(4, username);
            calstatement.setString(5, grade);
            calstatement.setArray(6, oracle_arrayPoscd);
            calstatement.setString(7, sKiemtoan);
            calstatement.setString(8, sBcao);
            calstatement.setString(9, sThongtu);
            calstatement.execute();
            reset = (ResultSet) calstatement.getObject(10);

            while (reset.next()) {
                QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                String Ma = reset.getString(2);
                value.setKHOA(reset.getString("KHOA"));
                value.setMA(reset.getString("MA"));
                value.setTHUTU(reset.getInt("THUTU"));
                value.setTEN(reset.getString("TEN"));
                value.setNAMBC(reset.getInt("NAMBC"));
                value.setMAPGD(reset.getString("MAPGD"));
                value.setMACN(reset.getString("MACN"));
                value.setD1(reset.getString("D1"));
                value.setD2(reset.getString("D2"));
                value.setD3(reset.getString("D3"));
                value.setD4(reset.getString("D4"));
                value.setD5(reset.getString("D5"));
                value.setD6(reset.getString("D6"));
                value.setD7(reset.getString("D7"));
                value.setD8(reset.getString("D8"));
                value.setD9(reset.getString("D9"));
                value.setD10(reset.getString("D10"));
                value.setD11(reset.getString("D11"));
                value.setNHAPTAY(reset.getString("NHAPTAY"));
                value.setKIEUIN(reset.getInt("KIEUIN"));
                lstBcqt_NT.add(value);
            }
        } catch (SQLException var21) {
            System.err.print(var21.getMessage());
            CoreLogger.error(this.getClass().getName() + " loadDataDoanhnghiep -> " + var21.getMessage());
            throw new SQLException(var21);
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

        return lstBcqt_NT;
    }

    public String loadDataTotalDoanhnghiep(String Khoa, int nambc, String ma_dn, String username, String grade, List<String> lstMapgd, String sKiemtoan, String sBcao, String sThongtu) throws SQLException {
        new ArrayList();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        String sCountTotalCust = "";

        try {
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = (String[]) lstMapgd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            CallableStatement calstatement = null;
            String strStoreproce = "{?=call ims_cic_tt200.sp_load_total_data_doanhnghiep(?,?,?,?,?,?,?,?,?)}";
            ResultSet reset = null;
            calstatement = conn.prepareCall(strStoreproce, 1003, 1007);
            calstatement.registerOutParameter(1, 12);
            calstatement.setString(2, Khoa);
            calstatement.setInt(3, nambc);
            calstatement.setString(4, ma_dn);
            calstatement.setString(5, username);
            calstatement.setString(6, grade);
            calstatement.setArray(7, oracle_arrayPoscd);
            calstatement.setString(8, sKiemtoan);
            calstatement.setString(9, sBcao);
            calstatement.setString(10, sThongtu);
            calstatement.execute();
            sCountTotalCust = calstatement.getString(1);
            if (reset != null) {
                ((ResultSet) reset).close();
            }

            if (calstatement != null) {
                calstatement.close();
            }
        } catch (Exception var17) {
            System.err.println("Loi trong ham getCountTotalCustData " + var17.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getCountTotalCustData -> " + var17.getMessage());
        }

        return sCountTotalCust;
    }

    public boolean saveDataDoanhnghiep(String khoa, int nambc, String ma_dn, String username, String grade, String sKiemtoan, String sBcao, String sThongtu, List<QT_DULIEU_NT> lstData) throws SQLException {
        Connection connection = (new DaoConnect()).getConnect();
        Object[] array = lstData.toArray();
        ArrayDescriptor des = ArrayDescriptor.createDescriptor("TAB_DULIEU_NT", connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;

        try {
            cs = connection.prepareCall("{call ims_cic_tt200.sp_save_doanhnghiep(?,?, ?, ?, ?,?,?, ?, ?)}");
            cs.setString(1, khoa);
            cs.setInt(2, nambc);
            cs.setString(3, ma_dn);
            cs.setString(4, username);
            cs.setString(5, grade);
            cs.setString(6, sKiemtoan);
            cs.setString(7, sBcao);
            cs.setString(8, sThongtu);
            cs.setArray(9, array_to_pass);
            cs.execute();
        } catch (SQLException var16) {
            var16.printStackTrace();
            System.err.println("Loi trong ham saveDataDoanhnghiep " + var16.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveDataDoanhnghiep -> " + var16.getMessage());
            throw new SQLException(var16);
        } finally {
            if (cs != null) {
                cs.close();
            }

            if (connection != null) {
                connection.close();
            }

        }

        return true;
    }

    public List<String> getDataSendCIC(String type, String khoa, String mapgd, String ngay_bc) throws SQLException {
        List<String> lstData = new ArrayList();
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        conn = daoconnect.getConnect();
        CallableStatement calstatement = null;
        ResultSet reset = null;
        String strStoreproce = "{call ims_cic_tt200.SP_GET_DATA_CIC_SYNC(?,?,?,?,?,?,?)}";

        try {
            calstatement = conn.prepareCall(strStoreproce, 1003, 1007);
            calstatement.registerOutParameter(5, 2);
            calstatement.registerOutParameter(6, 12);
            calstatement.registerOutParameter(7, -10);
            calstatement.setString(1, type);
            calstatement.setString(2, khoa);
            calstatement.setString(3, mapgd);
            calstatement.setString(4, ngay_bc);
            calstatement.execute();
            int pn_err_cd = calstatement.getInt(5);
            String strEdd_txt = calstatement.getString(6);
            reset = (ResultSet) calstatement.getObject(7);

            while (reset.next()) {
                lstData.add(reset.getString(1));
            }
        } catch (SQLException var16) {
            System.err.print(var16.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataSendCIC -> " + var16.getMessage());
            throw new SQLException(var16);
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

        return lstData;
    }

    public boolean putXmlFileCIC(String fileXml, String khoa, String type_bcqt, String mapgd, String ngay_bc, String grade, String username, String ngay_gui, String tt_khoa) throws SQLException {
        boolean bSuccess = false;
        String qry = "{call ims_cic_tt200.SP_PUT_FILEXML_CIC(?,?,?,?,?,?,?,?,?,?,?)}";
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        OraclePreparedStatement sqlStatement = null;
        XMLType xml = null;

        boolean var18;
        try {
            Timestamp date_ngay_gui = new Timestamp((new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss")).parse(ngay_gui).getTime());
            File xmlFile = new File(fileXml);
            if (xmlFile.exists()) {
                BigDecimal value = new BigDecimal(xmlFile.length());
                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(xmlFile);
                conn = daoconnect.getConnect();
                xml = XMLType.createXML(conn, document);
                sqlStatement = (OraclePreparedStatement) conn.prepareStatement(qry);
                sqlStatement.setString(1, khoa);
                sqlStatement.setString(2, type_bcqt);
                sqlStatement.setString(3, mapgd);
                sqlStatement.setString(4, ngay_bc);
                sqlStatement.setString(5, grade);
                sqlStatement.setString(6, username);
                sqlStatement.setTimestamp(7, date_ngay_gui);
                sqlStatement.setBigDecimal(8, value);
                sqlStatement.setString(9, xmlFile.getName());
                sqlStatement.setObject(10, xml);
                sqlStatement.setString(11, tt_khoa);
                sqlStatement.execute();
                bSuccess = true;
                return bSuccess;
            }

            CoreLogger.error(this.getClass().getName() + " putXmlFileCIC -> Khong tim thay filexml " + fileXml);
            var18 = false;
        } catch (Exception var25) {
            System.err.println("Loi trong ham putXmlFileCIC " + var25.getMessage());
            CoreLogger.error(this.getClass().getName() + " putXmlFileCIC -> " + var25.getMessage());
            bSuccess = false;
            return bSuccess;
        } finally {
            if (sqlStatement != null) {
                sqlStatement.close();
            }

            if (xml != null) {
                xml.close();
            }

            if (conn != null) {
                conn.close();
            }

        }

        return var18;
    }

    public List<QT_DULIEU_NT> getStatusSendCn(String type, String Khoa, List<String> lstMapgd, String ngaybc, String tt_khoa) {
        ArrayList lstStatusSendcn = new ArrayList();

        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", conn);
            String[] arrayPoscd = (String[]) lstMapgd.toArray(new String[0]);
            ARRAY oracle_arrayPoscd = new ARRAY(des, conn, arrayPoscd);
            String strStoreproce = "{call ims_cic_tt200.SP_GET_STATUS_SEND_CN(?,?,?,?,?,?)}";
            ResultSet reset = null;

            try {
                calstatement = conn.prepareCall(strStoreproce, 1003, 1007);
                calstatement.registerOutParameter(6, -10);
                calstatement.setString(1, type);
                calstatement.setString(2, Khoa);
                calstatement.setArray(3, oracle_arrayPoscd);
                calstatement.setString(4, ngaybc);
                calstatement.setString(5, tt_khoa);
                calstatement.execute();
                reset = (ResultSet) calstatement.getObject(6);
                ResultSetMetaData resetMeta = reset.getMetaData();
                QT_DULIEU_NT value;
                if (resetMeta.getColumnCount() <= 29 && Khoa.equals("ALL")) {
                    value = QT_DULIEU_NT.newInstance();
                    value.setD1(resetMeta.getColumnName(1));
                    value.setD2(resetMeta.getColumnName(2));
                    value.setD3(resetMeta.getColumnName(3));
                    value.setD4(resetMeta.getColumnName(4));
                    value.setD5(resetMeta.getColumnName(5));
                    value.setD6(resetMeta.getColumnName(6));
                    value.setD7(resetMeta.getColumnName(7));
                    value.setD8(resetMeta.getColumnName(8));
                    value.setD9(resetMeta.getColumnName(9));
                    value.setD10(resetMeta.getColumnName(10));
                    value.setD11(resetMeta.getColumnName(11));
                    value.setD12(resetMeta.getColumnName(12));
                    value.setD13(resetMeta.getColumnName(13));
                    value.setD14(resetMeta.getColumnName(14));
                    value.setD15(resetMeta.getColumnName(15));
                    value.setD16(resetMeta.getColumnName(16));
                    value.setD17(resetMeta.getColumnName(17));
                    value.setD18(resetMeta.getColumnName(18));
                    value.setD19(resetMeta.getColumnName(19));
                    value.setD20(resetMeta.getColumnName(20));
                    value.setD21(resetMeta.getColumnName(21));
                    value.setD22(resetMeta.getColumnName(22));
                    value.setD23(resetMeta.getColumnName(23));
                    value.setD24(resetMeta.getColumnName(24));
                    value.setD25(resetMeta.getColumnName(25));
                    value.setD26(resetMeta.getColumnName(26));
                    value.setD27(resetMeta.getColumnName(27));
                    lstStatusSendcn.add(value);
                }

                for (; reset.next(); lstStatusSendcn.add(value)) {
                    value = QT_DULIEU_NT.newInstance();
                    if (Khoa.equals("ALL")) {
                        value.setD1(reset.getString(1));
                        value.setD2(reset.getString(2));
                        value.setD3(reset.getString(3));
                        value.setD4(reset.getString(4).equals("1") ? "X" : "");
                        value.setD5(reset.getString(5).equals("1") ? "X" : "");
                        value.setD6(reset.getString(6).equals("1") ? "X" : "");
                        value.setD7(reset.getString(7).equals("1") ? "X" : "");
                        value.setD8(reset.getString(8).equals("1") ? "X" : "");
                        value.setD9(reset.getString(9).equals("1") ? "X" : "");
                        value.setD10(reset.getString(10).equals("1") ? "X" : "");
                        value.setD11(reset.getString(11).equals("1") ? "X" : "");
                        value.setD12(reset.getString(12).equals("1") ? "X" : "");
                        value.setD13(reset.getString(13).equals("1") ? "X" : "");
                        value.setD14(reset.getString(14).equals("1") ? "X" : "");
                        value.setD15(reset.getString(15).equals("1") ? "X" : "");
                        value.setD16(reset.getString(16).equals("1") ? "X" : "");
                        value.setD17(reset.getString(17).equals("1") ? "X" : "");
                        value.setD18(reset.getString(18).equals("1") ? "X" : "");
                        value.setD19(reset.getString(19).equals("1") ? "X" : "");
                        value.setD20(reset.getString(20).equals("1") ? "X" : "");
                        value.setD21(reset.getString(21).equals("1") ? "X" : "");
                        value.setD22(reset.getString(22).equals("1") ? "X" : "");
                        value.setD23(reset.getString(23).equals("1") ? "X" : "");
                        value.setD24(reset.getString(24).equals("1") ? "X" : "");
                        value.setD25(reset.getString(25).equals("1") ? "X" : "");
                        value.setD26(reset.getString(26).equals("1") ? "X" : "");
                        value.setD27(reset.getString(27).equals("1") ? "X" : "");
                    } else {
                        value.setD1(reset.getString(1));
                        value.setD2(reset.getString(2));
                        value.setD3(reset.getString(3));
                        value.setD4(reset.getString(4));
                        value.setD5(reset.getString(5));
                        value.setD6(reset.getString(6));
                        value.setD7(reset.getString(7));
                        value.setD8(reset.getString(8));
                        value.setD9(reset.getString(9));
                        value.setD10(reset.getString(10));
                        value.setD11(reset.getString(11));
                        value.setD12(reset.getString(12));
                    }
                }
            } catch (SQLException var21) {
                System.err.print(var21.getMessage());
                CoreLogger.error(this.getClass().getName() + " getDataPL01TL -> " + var21.getMessage());
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
        } catch (Exception var23) {
            System.err.println("Loi trong ham getAllBcqt " + var23.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataPL01TL -> " + var23.getMessage());
        }

        return lstStatusSendcn;
    }

    public static void main(String[] args) {
        daoBCTCCICTT200 cic = new daoBCTCCICTT200();

        try {
            cic.getDoanhnghiepCic("M2721", "2");
            System.out.println(String.valueOf(Calendar.getInstance().get(1) - 1));
        } catch (SQLException var3) {
            Logger.getLogger(daoBCTCCICTT200.class.getName()).log(Level.SEVERE, (String) null, var3);
        }

    }
}
