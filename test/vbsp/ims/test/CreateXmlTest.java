/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.test;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import vbsp.ims.dao.DaoProcessRisk;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelRiskProcess;
import vbsp.ims.model.ModelRiskProcess.ListRisk;
import vbsp.ims.xml.ImsReadWriteXmlFile;

/**
 *
 * @author BAOANH
 */
public class CreateXmlTest {

    private String sSoku = "soku";
    private String sDuno = "duno";
    private String slai = "lai";

    public boolean createXmlFileRisk(String sReportType,
            String sUserId, String sGrade, String sPosCd, String sNamrr, String sDotrr,
            String sNhomrr, List<ListRisk> lstDataRisk, String sFileName) {
        boolean bSuccess = true;
        String strSysDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date());
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            //Tao root node cho file xml
            Element rootElement = document.createElement(Define.XML_ROOT_NOTE);
            document.appendChild(rootElement);
            //Add cho phan key la loai report cho nay se add la  <IMS_REPORT_DATA TYPE="03">...</IMS_REPORT_DATA>
            Element dataElement = addNodeWithKey(document, rootElement, Define.XML_MAIN_DATA_NOTE, Define.XML_REPORT_TYPE, sReportType);
            //Dinh nghia cho thanh phan data
            //Add cho userid
            addNode(document, dataElement, Define.XML_USER_ID, sUserId);
            //Add cho cap bao cao
            addNode(document, dataElement, Define.XML_GRADE, sGrade);
            //Add data cho Pos cd
            addNode(document, dataElement, Define.XML_POS_CD, sPosCd);

            //Add data cho cap bao cao
            addNode(document, dataElement, Define.XML_NAM_BC, sNamrr);
            //Add cho dot rr
            addNode(document, dataElement, Define.XML_DOT_RR, sDotrr);
            //Add cho nhom bao cao
            addNode(document, dataElement, Define.XML_NHOM_RR, sNhomrr);
            //Add them ngay tao du lieu cho bao cao
            addNode(document, dataElement, Define.XML_SYSDATE, strSysDate);

            //Add du lieu chinh add nhu sau 
//            <IMS_DATA soku="6000002500006092">
//                    <duno>1500000</duno>
//                    <lai>1176043</lai>
//            </IMS_DATA>
            for (ListRisk value : lstDataRisk) {
                Element elementData = addNodeWithKey(document, dataElement, Define.XML_DATA, sSoku, value.getCheck_legacyid());
                addNode(document, elementData, sDuno, value.getDuno_rr());
                addNode(document, elementData, slai, value.getLai_rr());
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();

            DOMSource domSource = new DOMSource(document);

            StreamResult streamResult = new StreamResult(new File(sFileName));
            transformer.transform(domSource, streamResult);

//            System.err.println("Ghi ra file thanh cong");
            bSuccess = true;
        } catch (Exception tex) {
            CoreLogger.error(this.getClass().getCanonicalName() + " createXmlFileRisk -> " + tex.getMessage());
            System.err.println(tex.getMessage());
        }
        return bSuccess;
    }

    private static Node addNode(Document doc, Element element, String key, String value) {
//        try {
        Element node = doc.createElement(key);
        node.appendChild(doc.createTextNode(value));
        if (element != null) {
            element.appendChild(node);
        }
//        } catch (Exception e) {
//            System.err.println("loi addNode "+e.getMessage());
//        }
        return node;
    }

    private static Element addNodeWithKey(Document doc, Element rootElement, String nameRoot, String keyRoot, String valueRoot) {
        Element element = doc.createElement(nameRoot);
        rootElement.appendChild(element);
        element.setAttribute(keyRoot, valueRoot);
        return element;
    }

    public HashMap<String, Object> readXmpRisk(String sFileName) {
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
//                    System.out.println("XML_REPORT_TYPE: " + elementImsRptData.getAttribute(Define.XML_REPORT_TYPE));
                    hmParaXml.put(Define.XML_REPORT_TYPE, elementImsRptData.getAttribute(Define.XML_REPORT_TYPE));

//                   
//                    System.out.println("XML_USER_ID : " + elementImsRptData.getElementsByTagName(Define.XML_USER_ID).item(0).getTextContent());
                    hmParaXml.put(Define.XML_USER_ID, elementImsRptData.getElementsByTagName(Define.XML_USER_ID).item(0).getTextContent());

//                    System.out.println("XML_POS_CD : " + elementImsRptData.getElementsByTagName(Define.XML_POS_CD).item(0).getTextContent());
                    hmParaXml.put(Define.XML_POS_CD, elementImsRptData.getElementsByTagName(Define.XML_POS_CD).item(0).getTextContent());

//                    System.out.println("XML_GRADE : " + elementImsRptData.getElementsByTagName(Define.XML_GRADE).item(0).getTextContent());
                    hmParaXml.put(Define.XML_GRADE, elementImsRptData.getElementsByTagName(Define.XML_GRADE).item(0).getTextContent());

//                    System.out.println("XML_FLAG_DATA : " + elementImsRptData.getElementsByTagName(Define.XML_FLAG_DATA).item(0).getTextContent());
//                    hmParaXml.put(Define.XML_FLAG_DATA, elementImsRptData.getElementsByTagName(Define.XML_FLAG_DATA).item(0).getTextContent());
//                    System.out.println("XML_DATA : " + elementImsRptData.getElementsByTagName(Define.XML_DATA).item(0).getTextContent());
//                    hmParaXml.put(Define.XML_DATA, elementImsRptData.getElementsByTagName(Define.XML_DATA).item(0).getTextContent());
//                    System.out.println("XML_SYSDATE : " + elementImsRptData.getElementsByTagName(Define.XML_SYSDATE).item(0).getTextContent());
                    hmParaXml.put(Define.XML_SYSDATE, elementImsRptData.getElementsByTagName(Define.XML_SYSDATE).item(0).getTextContent());

                    hmParaXml.put(Define.XML_NAM_BC, elementImsRptData.getElementsByTagName(Define.XML_NAM_BC).item(0).getTextContent());

                    hmParaXml.put(Define.XML_DOT_RR, elementImsRptData.getElementsByTagName(Define.XML_DOT_RR).item(0).getTextContent());
                    hmParaXml.put(Define.XML_NHOM_RR, elementImsRptData.getElementsByTagName(Define.XML_NHOM_RR).item(0).getTextContent());
                }
            }
            hmParaXml.put(Define.XML_DATA, getDataElement(document));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " readXmpRisk -> " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return hmParaXml;
    }

    private List<ListRisk> getDataElement(Document document) {
        List<ListRisk> lstRisk = new ArrayList<ListRisk>();
        try {
            NodeList nodeList = document.getElementsByTagName(Define.XML_DATA);
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element data = (Element) node;
                    ListRisk risk = new ListRisk();
                    risk.setCheck_legacyid(data.getAttribute(sSoku));
                    risk.setDuno_rr(data.getElementsByTagName(sDuno).item(0).getTextContent());
                    risk.setLai_rr(data.getElementsByTagName(slai).item(0).getTextContent());
                    lstRisk.add(risk);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataElement -> " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return lstRisk;
    }

    public static void main(String[] args) {

//         public boolean createXmlFileRisk(String sReportType,
//            String sUserId, String sGrade, String sPosCd, String sNamrr, String sDotrr,
//            String sNhomrr, List<ListRisk> lstDataRisk, String sFileName)
        String sReportType = "03";
        String sUserId = "M2505";
        String sGrade = "2";
        String sPosCd = "002501";
        String sNamrr = "2015";
        String sDotrr = "1";
        String sNhomrr = "03";
        CreateXmlTest tao = new CreateXmlTest();
        //List<ListRisk> lstDataRisk = new DaoProcessRisk().getDataSend(sUserId, sGrade, sPosCd, sNamrr, sDotrr, sNhomrr);
        String sFileName = "F:\\tungnv.xml";
        //tao.createXmlFileRisk(sReportType, sUserId, sGrade, sPosCd, sNamrr, sDotrr, sNhomrr, lstDataRisk, sFileName);
        HashMap<String, Object> hmObj = tao.readXmpRisk(sFileName);

        System.err.println("XML_REPORT_TYPE=" + hmObj.get(Define.XML_REPORT_TYPE).toString());
        System.err.println("XML_USER_ID=" + hmObj.get(Define.XML_USER_ID).toString());
        System.err.println("XML_GRADE=" + hmObj.get(Define.XML_GRADE).toString());
        System.err.println("XML_NAM_BC=" + hmObj.get(Define.XML_NAM_BC).toString());
        System.err.println("XML_DOT_RR=" + hmObj.get(Define.XML_DOT_RR).toString());
        System.err.println("XML_NHOM_RR=" + hmObj.get(Define.XML_NHOM_RR).toString());
        System.err.println("XML_SYSDATE=" + hmObj.get(Define.XML_SYSDATE).toString());
        System.err.println("XML_POS_CD=" + hmObj.get(Define.XML_POS_CD).toString());
        List<ListRisk> lstRisk = (List<ListRisk>) hmObj.get(Define.XML_DATA);
        int count=1;
        for(ListRisk risk:lstRisk)
            System.err.println("STT="+count++ +" Soku="+risk.getCheck_legacyid()+" duno="+risk.getDuno_rr()+" lai="+risk.getLai_rr());
    }
}
