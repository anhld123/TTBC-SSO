/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.xmltype;

/**
 *
 * @author LION
 */
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import oracle.xdb.XMLType;
import oracle.jdbc.*;
import vbsp.ims.dao.DaoConnect;

public class jdbcXmltype {

    public void doInsert(Connection conn, Document doc, BigDecimal size_file, String file_name, String SQLTEXT)
            throws Exception {
//        String SQLTEXT = "INSERT INTO purchaseorder VALUES (?)";
        XMLType xml = null;
        xml = XMLType.createXML(conn, doc);
        OraclePreparedStatement sqlStatement = null;
        sqlStatement = (OraclePreparedStatement) conn.prepareStatement(SQLTEXT);
        sqlStatement.setObject(1, xml);
        sqlStatement.setBigDecimal(2, size_file);
        sqlStatement.setString(3, file_name);
        sqlStatement.execute();
        if(sqlStatement != null) sqlStatement.close();
    }
    public void readxml(Connection conn) throws Exception
    {
        OraclePreparedStatement stmt = (OraclePreparedStatement) conn.prepareStatement("SELECT A.TESTFIELD FROM JDBC_XML A");
        ResultSet rset = stmt.executeQuery();
        OracleResultSet orset = (OracleResultSet) rset;
        Document xml_column = null;
        while (orset.next()) {
            XMLType poxml = XMLType.createXML(orset.getOPAQUE("TESTFIELD"));
            //print the full xmltype
            System.out.println(poxml.getStringVal());
                                //below method is deprecated
            //xml_column = (Document)poxml.getDOM();
            xml_column = (Document) poxml.getDocument();
        }
        /* We have only one row in the XMLTYPE column, so the while loop will get executed once */
        /* Let us now try to print the value of id tag */

        /* Print the root element */
        System.out.println("Root element: " + xml_column.getDocumentElement().getNodeName());
        /* Print ID tag. You should try and use XPath approaches rather */
        NodeList nodeList = xml_column.getElementsByTagName("set");
        /* Loop through the "set" node */
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            NodeList nodeList_1 = element.getElementsByTagName("id").item(0).getChildNodes();
            Node node_2 = nodeList_1.item(0);
            System.out.println(node_2.getNodeValue());
        }
        /* Close all DB related objects */
        orset.close();
        stmt.close();
    }
    public static void main(String[] args) throws Exception {


        Connection conn = new DaoConnect().getConnect();
        String sqltext ="insert into test_xml (xml_data, size_file, file_name) values(?,?,?)";
        String sFileName ="H:\\M5720_DCPTNO_005720_1446679172953.xml";
         File xmlFile = new File(sFileName);
//            long size_file= Math.round(xmlFile.length()/1024,2);
            BigDecimal value = new BigDecimal(xmlFile.length());

            System.err.println("value="+value+" size="+xmlFile.length());
            
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(xmlFile);
            jdbcXmltype dao = new jdbcXmltype();
            dao.doInsert(conn, document, value, xmlFile.getName(), sqltext);
        conn.close();
    }
}
