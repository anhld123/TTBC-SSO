/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.branch;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import oracle.jdbc.OraclePreparedStatement;
import oracle.xdb.XMLType;
import org.w3c.dom.Document;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author BAOANH
 */
public class DaoInputBranchSync {
      public static DaoInputBranchSync newInstance() {
        return new DaoInputBranchSync();
    }

    
    public boolean putXmlFile(String fileXml, String khoa, 
            String grade, String username, String ngay_gui, String tt_khoa) throws SQLException {
        boolean bSuccess = false;
//        String qry = "INSERT INTO BCQT_XML_SYNC (KHOA, MAPGD, NGAY_BC, GRADE, NGUOI_GUI, NGAY_GUI,\n"
//                + " FILE_SIZE, FILE_NAME, XML_DATA, NGAY_TAO) VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
        String qry = "{call INPUT_BRANCH_SYNC.SP_PUT_FILEXML(?,?,?,?,?,?,?,?)}";//SP_PUT_FILEXML
        DaoConnect daoconnect = new DaoConnect();
        Connection conn = null;
        OraclePreparedStatement sqlStatement = null;
        XMLType xml = null;
        try {
            //convert date cho ngay_gui
            java.sql.Timestamp date_ngay_gui = new java.sql.Timestamp(new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").parse(ngay_gui).getTime());
            //format date cho ngay bao cao
//            java.sql.Date date_ngay_bc = new java.sql.Date(new SimpleDateFormat("dd-MMM-yyyy").parse(ngay_bc).getTime());
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
//            sqlStatement.setString(2, type_bcqt);
//            sqlStatement.setString(3, mapgd);
//            sqlStatement.setDate(4, date_ngay_bc);
            sqlStatement.setString(2, grade);
            sqlStatement.setString(3, username);
//            sqlStatement.setString(6, ngay_gui);
            sqlStatement.setTimestamp(4, date_ngay_gui);
            sqlStatement.setBigDecimal(5, value);
            sqlStatement.setString(6, xmlFile.getName());
            sqlStatement.setObject(7, xml);
            sqlStatement.setString(8, tt_khoa);
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
}
