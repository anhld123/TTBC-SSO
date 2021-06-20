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
import vbsp.ims.model.ModelRiskProcess;

/**
 *
 * @author LION
 */
public class XmlDcptNo {

    private String Soku = "DC_SOKU";
    private String Tk_Casa1 = "CASA1";
    private String Tk_Casa2 = "CASA2";
    private String Dvut = "DVUT";
    private String Mato = "MATO";
    private String Nogoc_Clech = "GOCL";
    private String Nolai_Clech = "LAIL";
    private String Soducasa_Clech = "CSL";
    private String Nguyennhan_Lech = "NNL";
    private String Thuctrang_Dtdt = "TTDT";
    private String Dno_Chayy = "CHAYY";
    private String Dno_Xlrr = "XLRR";
    private String Dno_Sxkd_Thualo = "THUALO";
    private String Dno_Khong_Dc = "KDC";
    private String Dno_Ditu = "DITU";
    private String Dno_Khnhan_No = "KNHAN";
    private String Dno_Nn_Khac = "KHAC";
    private String Trangthai_Dc = "TT";
    private String Nguoi_Nhap_Dc = "NIDC";
    private String Ngay_Nhap_Dc = "NYDC";
    private String Nguoi_Nhap_Pt = "NIPT";
    private String Ngay_Nhap_Pt = "NYPT";
    
    private String Ma_PGD = "MAPGD";
    private String Mto = "MTO";
    private String So_Mon = "SMON";

    public boolean createXmlFileDcpt(String sReportType,
            String sUserId, String sGrade, String sPos_cd, List<String> lstData, String sFileName) {
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
            addNode(document, dataElement, Define.XML_POS_CD, sPos_cd);
            //Add them ngay tao du lieu cho bao cao
            addNode(document, dataElement, Define.XML_SYSDATE, strSysDate);

            for (String strData : lstData) {

                Document doc2 = documentBuilder.parse(new ByteArrayInputStream(strData.getBytes("UTF-8")));
                Node node = document.importNode(doc2.getDocumentElement(), true);
                dataElement.appendChild(node);
                System.err.println(strData);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();

            DOMSource domSource = new DOMSource(document);

//            StreamResult streamResult = new StreamResult(new File(sFileName));
//            transformer.transform(domSource, streamResult);
            Writer writer = null;
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(sFileName), "UTF8"));
            StreamResult streamResult = new StreamResult(writer);
            transformer.transform(domSource, streamResult);
            if (writer != null) {
                writer.close();
            }

//            System.err.println("Ghi ra file thanh cong");
            bSuccess = true;
        } catch (Exception tex) {
            CoreLogger.error(this.getClass().getCanonicalName() + " createXmlFileDcpt -> " + tex.getMessage());
            System.err.println(tex.getMessage());
            bSuccess = false;
            throw new ImsException("Lỗi trong ham createXmlFileDcpt", tex);
        }
        return bSuccess;
    }

    public HashMap<String, Object> readXmlDcPt(String sFileName) throws Exception {
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
            CoreLogger.error(this.getClass().getCanonicalName() + " readXmlDcPt -> " + sFileName + " " + e.getMessage());
            System.err.println(" readXmlDcPt -> " + e.getMessage());
            throw new ImsException(" Lỗi trong hàm readXmlDcPt file=" + sFileName, e);
        }
        return hmParaXml;
    }
    

    public HashMap<String, Object> readXmlDcPtLock(String sFileName) {
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
            hmParaXml.put(Define.XML_DATA, getDataElementLock(document));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " readXmlDcPtLock -> " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return hmParaXml;
    }
//<IMS_DATA DC_SOKU="6000000000017550"><DC_TK_CASA1>0000001052107368</DC_TK_CASA1><DC_TK_CASA2>1000001052107368</DC_TK_CASA2><DC_DVUT>11</DC_DVUT><DC_MATO>0006854</DC_MATO><DC_NOGOC_CLECH>454540</DC_NOGOC_CLECH><DC_NOLAI_CLECH>65565</DC_NOLAI_CLECH><DC_SODUCASA_CLECH>454450</DC_SODUCASA_CLECH><DC_NGUYENNHAN_LECH>Không rõ nguyên nhân</DC_NGUYENNHAN_LECH><DC_THUCTRANG_DTDT>Đúng thực trạng đầu tư</DC_THUCTRANG_DTDT><DC_DNO_CHAYY>0</DC_DNO_CHAYY><DC_DNO_XLRR>0</DC_DNO_XLRR><DC_DNO_SXKD_THUALO>0</DC_DNO_SXKD_THUALO><DC_DNO_KHONG_DC>20000000</DC_DNO_KHONG_DC><DC_DNO_DITU>0</DC_DNO_DITU><DC_DNO_KHNHAN_NO>0</DC_DNO_KHNHAN_NO><DC_DNO_NN_KHAC>0</DC_DNO_NN_KHAC><DC_TRANGTHAI_DC>P</DC_TRANGTHAI_DC><DC_NGUOI_NHAP_DC>P0401</DC_NGUOI_NHAP_DC><DC_NGAY_NHAP_DC>08/09/2015 16:53:04</DC_NGAY_NHAP_DC><DC_NGUOI_NHAP_PT>P0401</DC_NGUOI_NHAP_PT><DC_NGAY_NHAP_PT>08/09/2015 22:04:51</DC_NGAY_NHAP_PT></IMS_DATA>

    private List<DcptNoModel> getDataElement(Document document) throws Exception {
        List<DcptNoModel> lstRisk = new ArrayList<DcptNoModel>();
        try {
            NodeList nodeList = document.getElementsByTagName(Define.XML_DATA);
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element data = (Element) node;
                    DcptNoModel value = new DcptNoModel();
                    value.setsSoku(data.getAttribute(Soku).trim());
                    value.setsTk_Casa1(data.getElementsByTagName(Tk_Casa1).item(0).getTextContent().trim());
                    value.setsTk_Casa2(data.getElementsByTagName(Tk_Casa2).item(0).getTextContent().trim());
                    value.setsDvut(data.getElementsByTagName(Dvut).item(0).getTextContent().trim());
                    value.setsMato(data.getElementsByTagName(Mato).item(0).getTextContent().trim());
                    value.setsNogoc_Clech(data.getElementsByTagName(Nogoc_Clech).item(0).getTextContent().trim());
                    value.setsNolai_Clech(data.getElementsByTagName(Nolai_Clech).item(0).getTextContent().trim());
                    value.setsSoducasa_Clech(data.getElementsByTagName(Soducasa_Clech).item(0).getTextContent().trim());
                    value.setsNguyennhan_Lech(data.getElementsByTagName(Nguyennhan_Lech).item(0).getTextContent().trim());
                    value.setsThuctrang_Dtdt(data.getElementsByTagName(Thuctrang_Dtdt).item(0).getTextContent().trim());
                    value.setsDno_Chayy(data.getElementsByTagName(Dno_Chayy).item(0).getTextContent().trim());
                    value.setsDno_Xlrr(data.getElementsByTagName(Dno_Xlrr).item(0).getTextContent().trim());
                    value.setsDno_Sxkd_Thualo(data.getElementsByTagName(Dno_Sxkd_Thualo).item(0).getTextContent().trim());
                    value.setsDno_Khong_Dc(data.getElementsByTagName(Dno_Khong_Dc).item(0).getTextContent().trim());
                    value.setsDno_Ditu(data.getElementsByTagName(Dno_Ditu).item(0).getTextContent().trim());
                    value.setsDno_Khnhan_No(data.getElementsByTagName(Dno_Khnhan_No).item(0).getTextContent().trim());
                    value.setsDno_Nn_Khac(data.getElementsByTagName(Dno_Nn_Khac).item(0).getTextContent().trim());
                    value.setsTrangthai_Dc(data.getElementsByTagName(Trangthai_Dc).item(0).getTextContent().trim());
                    value.setsNguoi_Nhap_Dc(data.getElementsByTagName(Nguoi_Nhap_Dc).item(0).getTextContent().trim());
                    value.setsNgay_Nhap_Dc(data.getElementsByTagName(Ngay_Nhap_Dc).item(0).getTextContent().trim());
                    value.setsNguoi_Nhap_Pt(data.getElementsByTagName(Nguoi_Nhap_Pt).item(0).getTextContent().trim());
                    value.setsNgay_Nhap_Pt(data.getElementsByTagName(Ngay_Nhap_Pt).item(0).getTextContent().trim());

//                    
//                    value.setsTk_Casa1(data.getElementsByTagName(Tk_Casa1).item(0).getTextContent().trim());
//                    value.setsTk_Casa2(data.getElementsByTagName(Tk_Casa2).item(0).getTextContent());
//                    value.setsDvut(data.getElementsByTagName(Dvut).item(0).getTextContent());
//                    value.setsMato(data.getElementsByTagName(Mato).item(0).getTextContent());
//                    value.setsNogoc_Clech(data.getElementsByTagName(Nogoc_Clech).item(0).getTextContent());
//                    value.setsNolai_Clech(data.getElementsByTagName(Nolai_Clech).item(0).getTextContent());
//                    value.setsSoducasa_Clech(data.getElementsByTagName(Soducasa_Clech).item(0).getTextContent());
//                    value.setsNguyennhan_Lech(data.getElementsByTagName(Nguyennhan_Lech).item(0).getTextContent());
//                    value.setsThuctrang_Dtdt(data.getElementsByTagName(Thuctrang_Dtdt).item(0).getTextContent());
//                    value.setsDno_Chayy(data.getElementsByTagName(Dno_Chayy).item(0).getTextContent());
//                    value.setsDno_Xlrr(data.getElementsByTagName(Dno_Xlrr).item(0).getTextContent());
//                    value.setsDno_Sxkd_Thualo(data.getElementsByTagName(Dno_Sxkd_Thualo).item(0).getTextContent());
//                    value.setsDno_Khong_Dc(data.getElementsByTagName(Dno_Khong_Dc).item(0).getTextContent());
//                    value.setsDno_Ditu(data.getElementsByTagName(Dno_Ditu).item(0).getTextContent());
//                    value.setsDno_Khnhan_No(data.getElementsByTagName(Dno_Khnhan_No).item(0).getTextContent());
//                    value.setsDno_Nn_Khac(data.getElementsByTagName(Dno_Nn_Khac).item(0).getTextContent());
//                    value.setsTrangthai_Dc(data.getElementsByTagName(Trangthai_Dc).item(0).getTextContent());
//                    value.setsNguoi_Nhap_Dc(data.getElementsByTagName(Nguoi_Nhap_Dc).item(0).getTextContent());
//                    value.setsNgay_Nhap_Dc(data.getElementsByTagName(Ngay_Nhap_Dc).item(0).getTextContent());
//                    value.setsNguoi_Nhap_Pt(data.getElementsByTagName(Nguoi_Nhap_Pt).item(0).getTextContent());
//                    value.setsNgay_Nhap_Pt(data.getElementsByTagName(Ngay_Nhap_Pt).item(0).getTextContent());
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

    private List<DcptNoModel> getDataElementLock(Document document) {
        List<DcptNoModel> lstRisk = new ArrayList<DcptNoModel>();
        try {
            NodeList nodeList = document.getElementsByTagName(Define.XML_DATA);
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element data = (Element) node;
                    DcptNoModel value = new DcptNoModel();
                    value.setsSoku(data.getAttribute(Soku).trim());
                    value.setsTk_Casa1(data.getElementsByTagName(Tk_Casa1).item(0).getTextContent().trim());
                    value.setsTk_Casa2(data.getElementsByTagName(Tk_Casa2).item(0).getTextContent().trim());
                    value.setsDvut(data.getElementsByTagName(Dvut).item(0).getTextContent().trim());
                    value.setsMato(data.getElementsByTagName(Mato).item(0).getTextContent().trim());
                    value.setsNogoc_Clech(data.getElementsByTagName(Nogoc_Clech).item(0).getTextContent().trim());
                    value.setsNolai_Clech(data.getElementsByTagName(Nolai_Clech).item(0).getTextContent().trim());
                    value.setsSoducasa_Clech(data.getElementsByTagName(Soducasa_Clech).item(0).getTextContent().trim());
                    value.setsNguyennhan_Lech(data.getElementsByTagName(Nguyennhan_Lech).item(0).getTextContent().trim());
                    value.setsThuctrang_Dtdt(data.getElementsByTagName(Thuctrang_Dtdt).item(0).getTextContent().trim());
                    value.setsDno_Chayy(data.getElementsByTagName(Dno_Chayy).item(0).getTextContent().trim());
                    value.setsDno_Xlrr(data.getElementsByTagName(Dno_Xlrr).item(0).getTextContent().trim());
                    value.setsDno_Sxkd_Thualo(data.getElementsByTagName(Dno_Sxkd_Thualo).item(0).getTextContent().trim());
                    value.setsDno_Khong_Dc(data.getElementsByTagName(Dno_Khong_Dc).item(0).getTextContent().trim());
                    value.setsDno_Ditu(data.getElementsByTagName(Dno_Ditu).item(0).getTextContent().trim());
                    value.setsDno_Khnhan_No(data.getElementsByTagName(Dno_Khnhan_No).item(0).getTextContent().trim());
                    value.setsDno_Nn_Khac(data.getElementsByTagName(Dno_Nn_Khac).item(0).getTextContent().trim());
                    value.setsTrangthai_Dc(data.getElementsByTagName(Trangthai_Dc).item(0).getTextContent().trim());
                    value.setsNguoi_Nhap_Dc(data.getElementsByTagName(Nguoi_Nhap_Dc).item(0).getTextContent().trim());
                    value.setsNgay_Nhap_Dc(data.getElementsByTagName(Ngay_Nhap_Dc).item(0).getTextContent().trim());
                    value.setsNguoi_Nhap_Pt(data.getElementsByTagName(Nguoi_Nhap_Pt).item(0).getTextContent().trim());
                    value.setsNgay_Nhap_Pt(data.getElementsByTagName(Ngay_Nhap_Pt).item(0).getTextContent().trim());
                    value.setsMapgd(data.getElementsByTagName(Ma_PGD).item(0).getTextContent().trim());
                    value.setsMto(data.getElementsByTagName(Mto).item(0).getTextContent().trim());
                    value.setsSomon(data.getElementsByTagName(So_Mon).item(0).getTextContent().trim());
                    lstRisk.add(value);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataElementLock -> " + e.getMessage());
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

    public static void main(String[] agrs) {

    }
}
