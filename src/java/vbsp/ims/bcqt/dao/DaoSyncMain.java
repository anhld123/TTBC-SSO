/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.bcqt.dao;

import java.io.File;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import oracle.jdbc.OraclePreparedStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.xdb.XMLType;
import org.w3c.dom.Document;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author LION
 */
public class DaoSyncMain {

    public static DaoSyncMain newInstance() {
        return new DaoSyncMain();
    }

    public int getPosSendDataLock(String type, String khoa, String mapgd, String ngay_bc, String tt_khoa) throws SQLException {
        int nPos = 0;
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;

        CallableStatement calstatement = null;
        ResultSet reset = null;
//        try {
        //Khoi tao procedure cung voi tham so truyen vao la dau ?
        String strStoreproce = "{?=call VBSP_IMS_BCQT_SYNC.F_CHECK_POS_LOCK(?,?,?,?,?)}";
        try {
            conn = daoconnect.getConnect();
            //Khoi tao goi store
            calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            calstatement.setString(2, type);
            calstatement.setString(3, khoa);
            calstatement.setString(4, mapgd);
            calstatement.setString(5, ngay_bc);
            calstatement.setString(6, tt_khoa);
            //Thuc hien execute lay du lieu
            calstatement.execute();
            //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
            //Lay cursor ra resultset
            nPos = calstatement.getInt(1);

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getPosSendDataLock -> " + e.getMessage());
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
        return nPos;
    }

    public boolean putXmlFile(String fileXml, String khoa, String type_bcqt, String mapgd, String ngay_bc,
            String grade, String username, String ngay_gui, String tt_khoa) throws SQLException {
        boolean bSuccess = false;
//        String qry = "INSERT INTO BCQT_XML_SYNC (KHOA, MAPGD, NGAY_BC, GRADE, NGUOI_GUI, NGAY_GUI,\n"
//                + " FILE_SIZE, FILE_NAME, XML_DATA, NGAY_TAO) VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
        String qry = "{call VBSP_IMS_BCQT_SYNC.SP_PUT_FILEXML(?,?,?,?,?,?,?,?,?,?,?)}";//SP_PUT_FILEXML
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        OraclePreparedStatement sqlStatement = null;
        XMLType xml = null;
        try {
            //convert date cho ngay_gui
            java.sql.Timestamp date_ngay_gui = new java.sql.Timestamp(new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").parse(ngay_gui).getTime());
            //format date cho ngay bao cao
            java.sql.Date date_ngay_bc = new java.sql.Date(new SimpleDateFormat("dd-MMM-yyyy").parse(ngay_bc).getTime());
            File xmlFile = new File(fileXml);
            if (!xmlFile.exists()) {
                CoreLogger.error(this.getClass().getName() + " putXmlFile -> Khong tim thay filexml " + fileXml);
                return false;
            }
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
            sqlStatement.setDate(4, date_ngay_bc);
            sqlStatement.setString(5, grade);
            sqlStatement.setString(6, username);
//            sqlStatement.setString(6, ngay_gui);
            sqlStatement.setTimestamp(7, date_ngay_gui);
            sqlStatement.setBigDecimal(8, value);
            sqlStatement.setString(9, xmlFile.getName());
            sqlStatement.setObject(10, xml);
            sqlStatement.setString(11, tt_khoa);
            sqlStatement.execute();

            bSuccess = true;
        } catch (Exception e) {
            System.err.println("Loi trong ham putXmlFile " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " putXmlFile -> " + e.getMessage());
            bSuccess = false;
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
        return bSuccess;
    }
    
    public boolean putXmlFileSbv(String fileXml, String khoa, String type_bcqt, String mapgd, String ngay_bc,
            String grade, String username, String ngay_gui, String tt_khoa) throws SQLException {
        boolean bSuccess = false;
//        String qry = "INSERT INTO BCQT_XML_SYNC (KHOA, MAPGD, NGAY_BC, GRADE, NGUOI_GUI, NGAY_GUI,\n"
//                + " FILE_SIZE, FILE_NAME, XML_DATA, NGAY_TAO) VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
        String qry = "{call IMS_SBV.SP_PUT_FILEXML_SBV(?,?,?,?,?,?,?,?,?,?,?)}";//SP_PUT_FILEXML
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        OraclePreparedStatement sqlStatement = null;
        XMLType xml = null;
        try {
            //convert date cho ngay_gui
            java.sql.Timestamp date_ngay_gui = new java.sql.Timestamp(new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").parse(ngay_gui).getTime());
            //format date cho ngay bao cao
            java.sql.Date date_ngay_bc = new java.sql.Date(new SimpleDateFormat("dd-MMM-yyyy").parse(ngay_bc).getTime());
            File xmlFile = new File(fileXml);
            if (!xmlFile.exists()) {
                CoreLogger.error(this.getClass().getName() + " putXmlFileSbv -> Khong tim thay filexml " + fileXml);
                return false;
            }
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
            sqlStatement.setDate(4, date_ngay_bc);
            sqlStatement.setString(5, grade);
            sqlStatement.setString(6, username);
//            sqlStatement.setString(6, ngay_gui);
            sqlStatement.setTimestamp(7, date_ngay_gui);
            sqlStatement.setBigDecimal(8, value);
            sqlStatement.setString(9, xmlFile.getName());
            sqlStatement.setObject(10, xml);
            sqlStatement.setString(11, tt_khoa);
            sqlStatement.execute();

            bSuccess = true;
        } catch (Exception e) {
            System.err.println("Loi trong ham putXmlFileSbv " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " putXmlFileSbv -> " + e.getMessage());
            bSuccess = false;
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
        return bSuccess;
    }
    
    public boolean putXmlFileKtgs(String fileXml, String khoa, String type_bcqt, String mapgd, String ngay_bc,
            String grade, String username, String ngay_gui, String tt_khoa) throws SQLException {
        boolean bSuccess = false;
//        String qry = "INSERT INTO BCQT_XML_SYNC (KHOA, MAPGD, NGAY_BC, GRADE, NGUOI_GUI, NGAY_GUI,\n"
//                + " FILE_SIZE, FILE_NAME, XML_DATA, NGAY_TAO) VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
        String qry = "{call VBSP_IMS_KTGS.SP_PUT_FILEXML_KTGS(?,?,?,?,?,?,?,?,?,?,?)}";//SP_PUT_FILEXML
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        OraclePreparedStatement sqlStatement = null;
        XMLType xml = null;
        try {
            //convert date cho ngay_gui
            java.sql.Timestamp date_ngay_gui = new java.sql.Timestamp(new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").parse(ngay_gui).getTime());
            //format date cho ngay bao cao
            java.sql.Date date_ngay_bc = new java.sql.Date(new SimpleDateFormat("dd-MMM-yyyy").parse(ngay_bc).getTime());
            File xmlFile = new File(fileXml);
            if (!xmlFile.exists()) {
                CoreLogger.error(this.getClass().getName() + " putXmlFileKtgs -> Khong tim thay filexml " + fileXml);
                return false;
            }
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
            sqlStatement.setDate(4, date_ngay_bc);
            sqlStatement.setString(5, grade);
            sqlStatement.setString(6, username);
//            sqlStatement.setString(6, ngay_gui);
            sqlStatement.setTimestamp(7, date_ngay_gui);
            sqlStatement.setBigDecimal(8, value);
            sqlStatement.setString(9, xmlFile.getName());
            sqlStatement.setObject(10, xml);
            sqlStatement.setString(11, tt_khoa);
            sqlStatement.execute();

            bSuccess = true;
        } catch (Exception e) {
            System.err.println("Loi trong ham putXmlFileKtgs " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " putXmlFileKtgs -> " + e.getMessage());
            bSuccess = false;
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
        return bSuccess;
    }
    
    
    public boolean putXmlFileCbss(String fileXml, String khoa, String type_bcqt, String mapgd, String ngay_bc,
            String grade, String username, String ngay_gui, String tt_khoa) throws SQLException {
        boolean bSuccess = false;
//        String qry = "INSERT INTO BCQT_XML_SYNC (KHOA, MAPGD, NGAY_BC, GRADE, NGUOI_GUI, NGAY_GUI,\n"
//                + " FILE_SIZE, FILE_NAME, XML_DATA, NGAY_TAO) VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
        String qry = "{call VBSP_IMS_CANHBAOSS.SP_PUT_FILEXML_CBSS(?,?,?,?,?,?,?,?,?,?,?)}";//SP_PUT_FILEXML
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        OraclePreparedStatement sqlStatement = null;
        XMLType xml = null;
        try {
            //convert date cho ngay_gui
            java.sql.Timestamp date_ngay_gui = new java.sql.Timestamp(new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").parse(ngay_gui).getTime());
            //format date cho ngay bao cao
            java.sql.Date date_ngay_bc = new java.sql.Date(new SimpleDateFormat("dd-MMM-yyyy").parse(ngay_bc).getTime());
            File xmlFile = new File(fileXml);
            if (!xmlFile.exists()) {
                CoreLogger.error(this.getClass().getName() + " putXmlFileCbss -> Khong tim thay filexml " + fileXml);
                return false;
            }
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
            sqlStatement.setDate(4, date_ngay_bc);
            sqlStatement.setString(5, grade);
            sqlStatement.setString(6, username);
//            sqlStatement.setString(6, ngay_gui);
            sqlStatement.setTimestamp(7, date_ngay_gui);
            sqlStatement.setBigDecimal(8, value);
            sqlStatement.setString(9, xmlFile.getName());
            sqlStatement.setObject(10, xml);
            sqlStatement.setString(11, tt_khoa);
            sqlStatement.execute();

            bSuccess = true;
        } catch (Exception e) {
            System.err.println("Loi trong ham putXmlFileCbss " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " putXmlFileCbss -> " + e.getMessage());
            bSuccess = false;
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
        return bSuccess;
    }
    
    public boolean putXmlFileMuasamts(String fileXml, String khoa, String type_bcqt, String mapgd, String ngay_bc,
            String grade, String username, String ngay_gui, String tt_khoa) throws SQLException {
        boolean bSuccess = false;
//        String qry = "INSERT INTO BCQT_XML_SYNC (KHOA, MAPGD, NGAY_BC, GRADE, NGUOI_GUI, NGAY_GUI,\n"
//                + " FILE_SIZE, FILE_NAME, XML_DATA, NGAY_TAO) VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
        String qry = "{call VBSP_IMS_nhaptaycn.SP_PUT_FILEXML_MUSAMTS(?,?,?,?,?,?,?,?,?,?,?)}";//SP_PUT_FILEXML
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        OraclePreparedStatement sqlStatement = null;
        XMLType xml = null;
        try {
            //convert date cho ngay_gui
            java.sql.Timestamp date_ngay_gui = new java.sql.Timestamp(new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").parse(ngay_gui).getTime());
            //format date cho ngay bao cao
            java.sql.Date date_ngay_bc = new java.sql.Date(new SimpleDateFormat("dd-MMM-yyyy").parse("01-jan-2020").getTime());
            File xmlFile = new File(fileXml);
            if (!xmlFile.exists()) {
                CoreLogger.error(this.getClass().getName() + " putXmlFileKtgs -> Khong tim thay filexml " + fileXml);
                return false;
            }
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
            sqlStatement.setDate(4, date_ngay_bc);
            sqlStatement.setString(5, grade);
            sqlStatement.setString(6, username);
//            sqlStatement.setString(6, ngay_gui);
            sqlStatement.setTimestamp(7, date_ngay_gui);
            sqlStatement.setBigDecimal(8, value);
            sqlStatement.setString(9, xmlFile.getName());
            sqlStatement.setObject(10, xml);
            sqlStatement.setString(11, tt_khoa);
            sqlStatement.execute();

            bSuccess = true;
        } catch (Exception e) {
            System.err.println("Loi trong ham putXmlFileKtgs " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " putXmlFileKtgs -> " + e.getMessage());
            bSuccess = false;
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
        return bSuccess;
    }
    
    public boolean putXmlFilePhiUt(String fileXml, String khoa, String type_bcqt, String mapgd, String ngay_bc,
            String grade, String username, String ngay_gui, String tt_khoa) throws SQLException {
        boolean bSuccess = false;
//        String qry = "INSERT INTO BCQT_XML_SYNC (KHOA, MAPGD, NGAY_BC, GRADE, NGUOI_GUI, NGAY_GUI,\n"
//                + " FILE_SIZE, FILE_NAME, XML_DATA, NGAY_TAO) VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
        String qry = "{call VBSP_IMS_NHAPTAYCN.SP_PUT_FILEXML_NHAPTAYCN(?,?,?,?,?,?,?,?,?,?,?)}";//SP_PUT_FILEXML
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        OraclePreparedStatement sqlStatement = null;
        XMLType xml = null;
        try {
            //convert date cho ngay_gui
            java.sql.Timestamp date_ngay_gui = new java.sql.Timestamp(new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").parse(ngay_gui).getTime());
            //format date cho ngay bao cao
            java.sql.Date date_ngay_bc = new java.sql.Date(new SimpleDateFormat("dd-MMM-yyyy").parse(ngay_bc).getTime());
            File xmlFile = new File(fileXml);
            if (!xmlFile.exists()) {
                CoreLogger.error(this.getClass().getName() + " putXmlFileKtgs -> Khong tim thay filexml " + fileXml);
                return false;
            }
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
            sqlStatement.setDate(4, date_ngay_bc);
            sqlStatement.setString(5, grade);
            sqlStatement.setString(6, username);
//            sqlStatement.setString(6, ngay_gui);
            sqlStatement.setTimestamp(7, date_ngay_gui);
            sqlStatement.setBigDecimal(8, value);
            sqlStatement.setString(9, xmlFile.getName());
            sqlStatement.setObject(10, xml);
            sqlStatement.setString(11, tt_khoa);
            sqlStatement.execute();

            bSuccess = true;
        } catch (Exception e) {
            System.err.println("Loi trong ham putXmlFileKtgs " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " putXmlFileKtgs -> " + e.getMessage());
            bSuccess = false;
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
        return bSuccess;
    }
    
    public boolean putXmlFileTdnn(String fileXml, String khoa, String type_bcqt, String mapgd, String ngay_bc,
            String grade, String username, String ngay_gui, String tt_khoa) throws SQLException {
        boolean bSuccess = false;
//        String qry = "INSERT INTO BCQT_XML_SYNC (KHOA, MAPGD, NGAY_BC, GRADE, NGUOI_GUI, NGAY_GUI,\n"
//                + " FILE_SIZE, FILE_NAME, XML_DATA, NGAY_TAO) VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
        String qry = "{call VBSP_IMS_TDNN.SP_PUT_FILEXML_TDNN(?,?,?,?,?,?,?,?,?,?,?)}";//SP_PUT_FILEXML
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        OraclePreparedStatement sqlStatement = null;
        XMLType xml = null;
        try {
            //convert date cho ngay_gui
            java.sql.Timestamp date_ngay_gui = new java.sql.Timestamp(new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").parse(ngay_gui).getTime());
            //format date cho ngay bao cao
            java.sql.Date date_ngay_bc = new java.sql.Date(new SimpleDateFormat("dd-MMM-yyyy").parse(ngay_bc).getTime());
            File xmlFile = new File(fileXml);
            if (!xmlFile.exists()) {
                CoreLogger.error(this.getClass().getName() + " putXmlFileTdnn -> Khong tim thay filexml " + fileXml);
                return false;
            }
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
            sqlStatement.setDate(4, date_ngay_bc);
            sqlStatement.setString(5, grade);
            sqlStatement.setString(6, username);
//            sqlStatement.setString(6, ngay_gui);
            sqlStatement.setTimestamp(7, date_ngay_gui);
            sqlStatement.setBigDecimal(8, value);
            sqlStatement.setString(9, xmlFile.getName());
            sqlStatement.setObject(10, xml);
            sqlStatement.setString(11, tt_khoa);
            sqlStatement.execute();

            bSuccess = true;
        } catch (Exception e) {
            System.err.println("Loi trong ham putXmlFileTdnn " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " putXmlFileTdnn -> " + e.getMessage());
            bSuccess = false;
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
        return bSuccess;
    }
    
    public boolean putXmlFileKyQuy(String fileXml, String khoa, String type_bcqt, String mapgd, String ngay_bc,
            String grade, String username, String ngay_gui, String tt_khoa) throws SQLException {
        boolean bSuccess = false;
//        String qry = "INSERT INTO BCQT_XML_SYNC (KHOA, MAPGD, NGAY_BC, GRADE, NGUOI_GUI, NGAY_GUI,\n"
//                + " FILE_SIZE, FILE_NAME, XML_DATA, NGAY_TAO) VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
        String qry = "{call VBSP_IMS_TDNN.SP_PUT_FILEXML_TDNN(?,?,?,?,?,?,?,?,?,?,?)}";//SP_PUT_FILEXML
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        OraclePreparedStatement sqlStatement = null;
        XMLType xml = null;
        try {
            //convert date cho ngay_gui
            java.sql.Timestamp date_ngay_gui = new java.sql.Timestamp(new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").parse(ngay_gui).getTime());
            //format date cho ngay bao cao
            java.sql.Date date_ngay_bc = new java.sql.Date(new SimpleDateFormat("dd-MMM-yyyy").parse(ngay_bc).getTime());
            File xmlFile = new File(fileXml);
            if (!xmlFile.exists()) {
                CoreLogger.error(this.getClass().getName() + " putXmlFileTdnn -> Khong tim thay filexml " + fileXml);
                return false;
            }
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
            sqlStatement.setDate(4, date_ngay_bc);
            sqlStatement.setString(5, grade);
            sqlStatement.setString(6, username);
//            sqlStatement.setString(6, ngay_gui);
            sqlStatement.setTimestamp(7, date_ngay_gui);
            sqlStatement.setBigDecimal(8, value);
            sqlStatement.setString(9, xmlFile.getName());
            sqlStatement.setObject(10, xml);
            sqlStatement.setString(11, tt_khoa);
            sqlStatement.execute();

            bSuccess = true;
        } catch (Exception e) {
            System.err.println("Loi trong ham putXmlFileTdnn " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " putXmlFileTdnn -> " + e.getMessage());
            bSuccess = false;
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
        return bSuccess;
    }
    
    public boolean putXmlFilePhts(String fileXml, String khoa, String type_bcqt, String mapgd, String ngay_bc,
            String grade, String username, String ngay_gui, String tt_khoa) throws SQLException {
        boolean bSuccess = false;
//        String qry = "INSERT INTO BCQT_XML_SYNC (KHOA, MAPGD, NGAY_BC, GRADE, NGUOI_GUI, NGAY_GUI,\n"
//                + " FILE_SIZE, FILE_NAME, XML_DATA, NGAY_TAO) VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
        String qry = "{call VBSP_IMS_PHTS.SP_PUT_FILEXML_PHTS(?,?,?,?,?,?,?,?,?,?,?)}";//SP_PUT_FILEXML
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        OraclePreparedStatement sqlStatement = null;
        XMLType xml = null;
        try {
            //convert date cho ngay_gui
            java.sql.Timestamp date_ngay_gui = new java.sql.Timestamp(new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").parse(ngay_gui).getTime());
            //format date cho ngay bao cao
            java.sql.Date date_ngay_bc = new java.sql.Date(new SimpleDateFormat("dd-MMM-yyyy").parse(ngay_bc).getTime());
            File xmlFile = new File(fileXml);
            if (!xmlFile.exists()) {
                CoreLogger.error(this.getClass().getName() + " putXmlFileKtgs -> Khong tim thay filexml " + fileXml);
                return false;
            }
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
            sqlStatement.setDate(4, date_ngay_bc);
            sqlStatement.setString(5, grade);
            sqlStatement.setString(6, username);
//            sqlStatement.setString(6, ngay_gui);
            sqlStatement.setTimestamp(7, date_ngay_gui);
            sqlStatement.setBigDecimal(8, value);
            sqlStatement.setString(9, xmlFile.getName());
            sqlStatement.setObject(10, xml);
            sqlStatement.setString(11, tt_khoa);
            sqlStatement.execute();

            bSuccess = true;
        } catch (Exception e) {
            System.err.println("Loi trong ham putXmlFileKtgs " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " putXmlFileKtgs -> " + e.getMessage());
            bSuccess = false;
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
        return bSuccess;
    }
    
    public boolean putXmlFileKhoantc(String fileXml, String khoa, String type_bcqt, String mapgd, String ngay_bc,
            String grade, String username, String ngay_gui, String tt_khoa) throws SQLException {
        boolean bSuccess = false;
//        String qry = "INSERT INTO BCQT_XML_SYNC (KHOA, MAPGD, NGAY_BC, GRADE, NGUOI_GUI, NGAY_GUI,\n"
//                + " FILE_SIZE, FILE_NAME, XML_DATA, NGAY_TAO) VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
        String qry = "{call VBSP_IMS_Khoantc.SP_PUT_FILEXML_Khoantc(?,?,?,?,?,?,?,?,?,?,?)}";//SP_PUT_FILEXML
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        OraclePreparedStatement sqlStatement = null;
        XMLType xml = null;
        try {
            //convert date cho ngay_gui
            java.sql.Timestamp date_ngay_gui = new java.sql.Timestamp(new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").parse(ngay_gui).getTime());
            //format date cho ngay bao cao
            java.sql.Date date_ngay_bc = new java.sql.Date(new SimpleDateFormat("dd-MMM-yyyy").parse(ngay_bc).getTime());
            File xmlFile = new File(fileXml);
            if (!xmlFile.exists()) {
                CoreLogger.error(this.getClass().getName() + " putXmlFileKhoantc -> Khong tim thay filexml " + fileXml);
                return false;
            }
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
            sqlStatement.setDate(4, date_ngay_bc);
            sqlStatement.setString(5, grade);
            sqlStatement.setString(6, username);
//            sqlStatement.setString(6, ngay_gui);
            sqlStatement.setTimestamp(7, date_ngay_gui);
            sqlStatement.setBigDecimal(8, value);
            sqlStatement.setString(9, xmlFile.getName());
            sqlStatement.setObject(10, xml);
            sqlStatement.setString(11, tt_khoa);
            sqlStatement.execute();

            bSuccess = true;
        } catch (Exception e) {
            System.err.println("Loi trong ham putXmlFileKhoantc " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " putXmlFileKhoantc -> " + e.getMessage());
            bSuccess = false;
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
        return bSuccess;
    }

    public List<ListValue> getMainPosLock(Connection conn) {
        List<ListValue> lstAllMainPos = new ArrayList<ListValue>();
        try {
//            DaoConnect daoconnect = new DaoConnect();
//            Connection conn = null;
//            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_BCQT_SYNC.SP_GET_MAIN_POS(?,?)}";
            ResultSet reset = null;

            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                //thu hien lay mo ta loi
                String strEdd_txt = calstatement.getString(1);
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(2);
                while (reset.next()) {
                    String key = reset.getString(1);
                    String des = reset.getString(2);
//                    String stt = reset.getString("stt");

                    lstAllMainPos.add(new ListValue(key, des));
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
                CoreLogger.error(this.getClass().getName() + " getMainPosLock -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getMainPosLock " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getMainPosLock -> " + e.getMessage());
        }
        return lstAllMainPos;
    }

    public List<QT_DULIEU_NT> getStatusSendCn(String type, String Khoa, String macn, String ngaybc, String tt_khoa) {
        List<QT_DULIEU_NT> lstStatusSendcn = new ArrayList<QT_DULIEU_NT>();
        try {
            DaoConnect daoconnect = new DaoConnect();
            Connection conn = null;
            conn = daoconnect.getConnect();
            CallableStatement calstatement = null;
            //Khoi tao procedure cung voi tham so truyen vao la dau ?
            String strStoreproce = "{call VBSP_IMS_BCQT_SYNC.SP_GET_STATUS_SEND_CN(?,?,?,?,?,?)}";
            ResultSet reset = null;
            try {
                //Khoi tao goi store
                calstatement = conn.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.setString(1, type);
                calstatement.setString(2, Khoa);
                calstatement.setString(3, macn);
                calstatement.setString(4, ngaybc);
                calstatement.setString(5, tt_khoa);
                //Thuc hien execute lay du lieu
                calstatement.execute();
                //lay gia tri loi cho procedure (truong hop khi co loi say ra moi can dung den)
                //Lay cursor ra resultset
                reset = (ResultSet) calstatement.getObject(6);
                //<editor-fold defaultstate="collapsed" desc="Tieu de cho cot">

                ResultSetMetaData resetMeta = reset.getMetaData();
                if (resetMeta.getColumnCount() <= 29 && Khoa.equals("ALL")) {
                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
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
//                    value.setD28(resetMeta.getColumnName(28));
//                    value.setD29(resetMeta.getColumnName(29));
                    lstStatusSendcn.add(value);
                }
//</editor-fold>
                while (reset.next()) {
                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
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
//                        value.setD28(reset.getString(28).equals("1") ? "X" : "");
//                        value.setD29(reset.getString(29).equals("1") ? "X" : "");

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
                    }
                    lstStatusSendcn.add(value);
                }

            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + " getDataPL01TL -> " + e.getMessage());
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
        } catch (Exception e) {
            System.err.println("Loi trong ham getAllBcqt " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataPL01TL -> " + e.getMessage());
        }
        return lstStatusSendcn;
    }

    public boolean setStatusLock(String type, String khoa, List<String> lstMapgd, String ngaybc, String tt_khoa, String username, String grade) throws SQLException {
        Connection connection = new DaoConnect().getConnect();
//        java.util.Dictionary map = (java.util.Dictionary) (connection.getTypeMap());
        Object array[] = lstMapgd.toArray();
        ArrayDescriptor des = ArrayDescriptor.createDescriptor("POS_CD", connection);
        ARRAY array_to_pass = new ARRAY(des, connection, array);
        CallableStatement cs = null;
        boolean bSuccess = false;
        try {
            cs = connection.prepareCall("{call VBSP_IMS_BCQT_SYNC.SP_OPEN_PGD(?, ?, ?, ?, ?, ?, ?)}");
            cs.setString(1, type);
            cs.setString(2, khoa);
            cs.setArray(3, array_to_pass);
            cs.setString(4, ngaybc);
            cs.setString(5, tt_khoa);
            cs.setString(6, username);
            cs.setString(7, grade);
            cs.execute();
            bSuccess = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi trong ham setStatusLock " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " setStatusLock -> " + e.getMessage());
            bSuccess = false;
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return bSuccess;
    }
    
    public boolean putXmlFileKHNV2021(String fileXml, String khoa, String type_bcqt, String mapgd, String ngay_bc,
            String grade, String username, String ngay_gui, String tt_khoa) throws SQLException {
        boolean bSuccess = false;
//        String qry = "INSERT INTO BCQT_XML_SYNC (KHOA, MAPGD, NGAY_BC, GRADE, NGUOI_GUI, NGAY_GUI,\n"
//                + " FILE_SIZE, FILE_NAME, XML_DATA, NGAY_TAO) VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
        String qry = "{call VBSP_IMS_KHNV2021.SP_PUT_FILEXML_KHNV(?,?,?,?,?,?,?,?,?,?,?)}";//SP_PUT_FILEXML
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        OraclePreparedStatement sqlStatement = null;
        XMLType xml = null;
        try {
            //convert date cho ngay_gui
            java.sql.Timestamp date_ngay_gui = new java.sql.Timestamp(new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").parse(ngay_gui).getTime());
            //format date cho ngay bao cao
            java.sql.Date date_ngay_bc = new java.sql.Date(new SimpleDateFormat("dd-MMM-yyyy").parse(ngay_bc).getTime());
            File xmlFile = new File(fileXml);
            if (!xmlFile.exists()) {
                CoreLogger.error(this.getClass().getName() + " putXmlFileKtgs -> Khong tim thay filexml " + fileXml);
                return false;
            }
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
            sqlStatement.setDate(4, date_ngay_bc);
            sqlStatement.setString(5, grade);
            sqlStatement.setString(6, username);
//            sqlStatement.setString(6, ngay_gui);
            sqlStatement.setTimestamp(7, date_ngay_gui);
            sqlStatement.setBigDecimal(8, value);
            sqlStatement.setString(9, xmlFile.getName());
            sqlStatement.setObject(10, xml);
            sqlStatement.setString(11, tt_khoa);
            sqlStatement.execute();

            bSuccess = true;
        } catch (Exception e) {
            System.err.println("Loi trong ham putXmlFileKtgs " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " putXmlFileKtgs -> " + e.getMessage());
            bSuccess = false;
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
        return bSuccess;
    }

    public static void main(String[] args) {
        DaoSyncMain dao = newInstance();
        dao.getStatusSendCn("NT", "ALL", "ALL", "30-nov-2015", "SEND");
    }
}
