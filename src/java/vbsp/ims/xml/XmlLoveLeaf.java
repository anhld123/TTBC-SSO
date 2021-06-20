/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.DcptNoModel;
import vbsp.ims.model.LoveLeafModel;
import vbsp.ims.model.ModelRiskProcess;

/**
 *
 * @author LION
 */
public class XmlLoveLeaf {

    private String POOR_ID = "POOR_ID";
    private String POS_CD = "POS_CD";
    private String TRAN_DT = "TRAN_DT";
    private String STATUS = "STATUS";
    private String COMMENT = "COMMENT";
    
    
    
    public HashMap<String, Object> readXmlLoveLeaf(String sFileName) throws Exception {
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
                    hmParaXml.put(Define.XML_SYSDATE, elementImsRptData.getElementsByTagName(Define.XML_SYSDATE).item(0).getTextContent());
                }
            }
            hmParaXml.put(Define.XML_DATA, getDataElement(document));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " readXmlDcPt -> " + e.getMessage());
            System.err.println(" readXmlDcPt -> " + e.getMessage());
            throw new ImsException("Lỗi trong hàm readXmlDcPt", e);
        }
        return hmParaXml;
    }
    
//<IMS_DATA DC_SOKU="6000000000017550"><DC_TK_CASA1>0000001052107368</DC_TK_CASA1><DC_TK_CASA2>1000001052107368</DC_TK_CASA2><DC_DVUT>11</DC_DVUT><DC_MATO>0006854</DC_MATO><DC_NOGOC_CLECH>454540</DC_NOGOC_CLECH><DC_NOLAI_CLECH>65565</DC_NOLAI_CLECH><DC_SODUCASA_CLECH>454450</DC_SODUCASA_CLECH><DC_NGUYENNHAN_LECH>Không rõ nguyên nhân</DC_NGUYENNHAN_LECH><DC_THUCTRANG_DTDT>Đúng thực trạng đầu tư</DC_THUCTRANG_DTDT><DC_DNO_CHAYY>0</DC_DNO_CHAYY><DC_DNO_XLRR>0</DC_DNO_XLRR><DC_DNO_SXKD_THUALO>0</DC_DNO_SXKD_THUALO><DC_DNO_KHONG_DC>20000000</DC_DNO_KHONG_DC><DC_DNO_DITU>0</DC_DNO_DITU><DC_DNO_KHNHAN_NO>0</DC_DNO_KHNHAN_NO><DC_DNO_NN_KHAC>0</DC_DNO_NN_KHAC><DC_TRANGTHAI_DC>P</DC_TRANGTHAI_DC><DC_NGUOI_NHAP_DC>P0401</DC_NGUOI_NHAP_DC><DC_NGAY_NHAP_DC>08/09/2015 16:53:04</DC_NGAY_NHAP_DC><DC_NGUOI_NHAP_PT>P0401</DC_NGUOI_NHAP_PT><DC_NGAY_NHAP_PT>08/09/2015 22:04:51</DC_NGAY_NHAP_PT></IMS_DATA>
    private List<LoveLeafModel> getDataElement(Document document) throws Exception {
        List<LoveLeafModel> lstRisk = new ArrayList<LoveLeafModel>();
        try {
            NodeList nodeList = document.getElementsByTagName(Define.XML_DATA);
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element data = (Element) node;
                    LoveLeafModel value = new LoveLeafModel();
                    value.setsPoorID(data.getAttribute(POOR_ID).trim());
                    value.setsPosCd(data.getElementsByTagName(POS_CD).item(0).getTextContent().trim());
                    value.setsTranDT(data.getElementsByTagName(TRAN_DT).item(0).getTextContent().trim());
                    value.setsStatus(data.getElementsByTagName(STATUS).item(0).getTextContent().trim());
                    value.setsComment(data.getElementsByTagName(COMMENT).item(0).getTextContent().trim());                                        
                    lstRisk.add(value);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataElement -> " + e.getMessage());
            System.err.println(" getDataElement -> " + e.getMessage());
            throw new ImsException("Lỗi trong hàm getDataElement -> ", e);
        }
        return lstRisk;
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
}
