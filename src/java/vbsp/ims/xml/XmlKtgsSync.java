/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.xml;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author LION
 */
public class XmlKtgsSync {

    public static XmlKtgsSync newInstance() {
        return new XmlKtgsSync();
    }

    public boolean createXmlFileKtgs(String sReportType, String typeBcqt, String khoa, String ngay_bc,
            String sUserId, String sGrade, String sPos_cd, List<String> lstData, String tt_lock, String sFileName) {
        boolean bSuccess = true;
        String strSysDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date());
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
//            Document document = documentBuilder.parse(new File(sFileName));
            //Tao root node cho file xml
            Element rootElement = document.createElement(Define.XML_ROOT_NOTE);
            document.appendChild(rootElement);
            //Add cho phan key la loai report cho nay se add la  <IMS_REPORT_DATA TYPE="03">...</IMS_REPORT_DATA>
//            Element dataElement = addNodeWithKey(document, rootElement, Define.XML_MAIN_DATA_NOTE, Define.XML_REPORT_TYPE, sReportType);

            Element dataElement = document.createElement(Define.XML_MAIN_DATA_NOTE);
            rootElement.appendChild(dataElement);
            Attr attrType = document.createAttribute(Define.XML_TYPE_BCQT);
            attrType.setValue(typeBcqt);
            dataElement.setAttributeNode(attrType);
            Attr attReportType = document.createAttribute(Define.XML_REPORT_TYPE);
            attReportType.setValue(sReportType);
            dataElement.setAttributeNode(attReportType);

            //Dinh nghia cho thanh phan data
//            addNodeWithKey(document, dataElement, Define.XML_MA_BCQT, "TYPE_BCQT", "NT");
            addNode(document, dataElement, Define.XML_MA_BCQT, khoa);
            addNode(document, dataElement, Define.XML_NGAY_BC, ngay_bc);

            addNode(document, dataElement, Define.XML_USER_ID, sUserId);
            //Add cho cap bao cao
            addNode(document, dataElement, Define.XML_GRADE, sGrade);
            //Add data cho Pos cd
            addNode(document, dataElement, Define.XML_POS_CD, sPos_cd);
            //Add them ngay tao du lieu cho bao cao
            addNode(document, dataElement, Define.XML_SYSDATE, strSysDate);
            addNode(document, dataElement, Define.WEB_SERVICES_STATUS_SEND, tt_lock);
            for (String strData : lstData) {

                Document doc2 = documentBuilder.parse(new ByteArrayInputStream(strData.getBytes("UTF-8")));
                Node node = document.importNode(doc2.getDocumentElement(), true);
                dataElement.appendChild(node);
//                System.err.println(strData);

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();

            DOMSource domSource = new DOMSource(document);
//            StreamResult streamResult = new StreamResult(new File(sFileName));
//            transformer.transform(domSource, streamResult);

            Writer writer = null;
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(sFileName), "UTF8"));
//            writer = new FileWriter(sFileName);
            StreamResult streamResult = new StreamResult(writer);
            transformer.transform(domSource, streamResult);
            if (writer != null) {
                writer.close();
            }
            bSuccess = true;
        } catch (Exception tex) {
            CoreLogger.error(this.getClass().getCanonicalName() + " createXmlFileKtgs -> " + tex.getMessage());
            System.err.println(tex.getMessage());
            bSuccess = false;
            throw new ImsException("Lỗi trong ham createXmlFileKtgs", tex);
        }
        return bSuccess;
    }

    private static Node addNode(Document doc, Element element, String key, String value) throws Exception {
        Element node = doc.createElement(key);
        try {
            node.appendChild(doc.createTextNode(value));
            if (element != null) {
                element.appendChild(node);
            }
        } catch (Exception e) {
            System.err.println("loi addNode " + e.getMessage());
            throw new ImsException("Lỗi trong hàm addNode -> ", e);
        }
        return node;
    }

    private static Element addNodeWithKey(Document doc, Element rootElement, String nameRoot, String keyRoot, String valueRoot) {
        Element element = doc.createElement(nameRoot);
        try {
            rootElement.appendChild(element);
            element.setAttribute(keyRoot, valueRoot);
        } catch (Exception e) {
            System.err.println("loi addNode " + e.getMessage());
            throw new ImsException("Lỗi trong hàm addNodeWithKey -> ", e);
        }

        return element;
    }

    public HashMap<String, Object> readXmlBCQT(String sFileName) {
        HashMap<String, Object> hmParaXml = new HashMap<String, Object>();
        try {
            File xmlFile = new File(sFileName);
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName(Define.XML_MAIN_DATA_NOTE);
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementImsRptData = (Element) node;
                    hmParaXml.put(Define.XML_REPORT_TYPE, elementImsRptData.getAttribute(Define.XML_REPORT_TYPE));
                    hmParaXml.put(Define.XML_TYPE_BCQT, elementImsRptData.getAttribute(Define.XML_TYPE_BCQT));
                    hmParaXml.put(Define.XML_MA_BCQT, elementImsRptData.getElementsByTagName(Define.XML_MA_BCQT).item(0).getTextContent());
                    hmParaXml.put(Define.XML_NGAY_BC, elementImsRptData.getElementsByTagName(Define.XML_NGAY_BC).item(0).getTextContent());
                    hmParaXml.put(Define.XML_USER_ID, elementImsRptData.getElementsByTagName(Define.XML_USER_ID).item(0).getTextContent());
                    hmParaXml.put(Define.XML_GRADE, elementImsRptData.getElementsByTagName(Define.XML_GRADE).item(0).getTextContent());
                    hmParaXml.put(Define.XML_POS_CD, elementImsRptData.getElementsByTagName(Define.XML_POS_CD).item(0).getTextContent());
                    hmParaXml.put(Define.XML_SYSDATE, elementImsRptData.getElementsByTagName(Define.XML_SYSDATE).item(0).getTextContent());
                    hmParaXml.put(Define.WEB_SERVICES_STATUS_SEND, elementImsRptData.getElementsByTagName(Define.WEB_SERVICES_STATUS_SEND).item(0).getTextContent());
                }
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " readXmlBCQT -> " + e.getMessage());
            System.err.println(e.getMessage());
            throw new ImsException("loi khi doc file " + sFileName, e);
        }
        return hmParaXml;
    }

    public static void main(String[] args) {
        
        String pos=",000405,000406,000412,000410,000407,000404,000402,000408,000403,000409,000411";
        if(pos.startsWith(",")) pos=pos.substring(1);
        System.err.println("pos="+pos);
        List<String> items = Arrays.asList(pos.split("\\s*,\\s*"));
        
        for(String str:items)
        {
            System.err.println(str);
        }
               
        XmlKtgsSync.newInstance().createXmlFileKtgs("15","NT", "BCQT_PL01", "31-dec-2015",
                "M2505", "2", "002501", new ArrayList<String>(), "SEND", "I:\\PROJECT\\IMS_REPORTS\\IMS_REPORTS\\build\\web\\EXPORT_REPORT\\XML\\tungnv.xml");
//        createXmlFileBcqt(String sReportType, String khoa, String ngay_bc,
//            String sUserId, String sGrade, String sPos_cd, List<String> lstData, String sFileName)

        HashMap<String, Object> HM = XmlKtgsSync.newInstance().readXmlBCQT("I:\\PROJECT\\IMS_REPORTS\\IMS_REPORTS\\build\\web\\EXPORT_REPORT\\XML\\tungnv.xml");
        
    }
}
