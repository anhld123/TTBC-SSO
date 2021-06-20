/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.xml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import vbsp.ims.dao.DaoDcptNo;
import vbsp.ims.dao.DaoProcessRisk;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.Branch;
import vbsp.ims.model.DcptNoModel;
import vbsp.ims.model.Group;
import vbsp.ims.model.ModelCommune;
import vbsp.ims.model.ModelRiskProcess;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author LION
 */
public class ImsReadWriteXmlFile {

    /**
     * Hàm này đọc file XML khi client gửi về sau đó dựa vào các note được khai
     * báo trong lớp Define để lấy dữ liệu ra Hàm trả ra 1 mảng kiểu HashMap key
     * là key note trong file XML String sFileName Chi ten file xml ghi ra
     */
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

    public HashMap<String, String> readFileXML(String strFileName) {
        HashMap<String, String> hmParaXml = new HashMap<String, String>();
        try {
            File xmlFile = new File(strFileName);
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName(Define.XML_MAIN_DATA_NOTE);
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);

//                System.err.println("Ten node Cha " + node.getNodeName());
//                System.out.println("\nElement type :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element elementImsRptData = (Element) node;
//                    System.err.println("student.getNodeName " + student.getNodeName());
//                    System.out.println("XML_REPORT_TYPE: " + elementImsRptData.getAttribute(Define.XML_REPORT_TYPE));
                    hmParaXml.put(Define.XML_REPORT_TYPE, elementImsRptData.getAttribute(Define.XML_REPORT_TYPE));

//                    System.out.println("XML_REPORT_ID : " + elementImsRptData.getElementsByTagName(Define.XML_REPORT_ID).item(0).getTextContent());
                    hmParaXml.put(Define.XML_REPORT_ID, elementImsRptData.getElementsByTagName(Define.XML_REPORT_ID).item(0).getTextContent());

                    hmParaXml.put(Define.XML_REPORT_DATE, elementImsRptData.getElementsByTagName(Define.XML_REPORT_DATE).item(0).getTextContent());
//                    System.out.println("XML_USER_ID : " + elementImsRptData.getElementsByTagName(Define.XML_USER_ID).item(0).getTextContent());
                    hmParaXml.put(Define.XML_USER_ID, elementImsRptData.getElementsByTagName(Define.XML_USER_ID).item(0).getTextContent());

//                    System.out.println("XML_POS_CD : " + elementImsRptData.getElementsByTagName(Define.XML_POS_CD).item(0).getTextContent());
                    hmParaXml.put(Define.XML_POS_CD, elementImsRptData.getElementsByTagName(Define.XML_POS_CD).item(0).getTextContent());

//                    System.out.println("XML_GRADE : " + elementImsRptData.getElementsByTagName(Define.XML_GRADE).item(0).getTextContent());
                    hmParaXml.put(Define.XML_GRADE, elementImsRptData.getElementsByTagName(Define.XML_GRADE).item(0).getTextContent());

//                    System.out.println("XML_FLAG_DATA : " + elementImsRptData.getElementsByTagName(Define.XML_FLAG_DATA).item(0).getTextContent());
                    hmParaXml.put(Define.XML_FLAG_DATA, elementImsRptData.getElementsByTagName(Define.XML_FLAG_DATA).item(0).getTextContent());

//                    System.out.println("XML_DATA : " + elementImsRptData.getElementsByTagName(Define.XML_DATA).item(0).getTextContent());
                    hmParaXml.put(Define.XML_DATA, elementImsRptData.getElementsByTagName(Define.XML_DATA).item(0).getTextContent());

//                    System.out.println("XML_SYSDATE : " + elementImsRptData.getElementsByTagName(Define.XML_SYSDATE).item(0).getTextContent());
                    hmParaXml.put(Define.XML_SYSDATE, elementImsRptData.getElementsByTagName(Define.XML_SYSDATE).item(0).getTextContent());

                    hmParaXml.put(Define.XML_QUY_BC, elementImsRptData.getElementsByTagName(Define.XML_QUY_BC).item(0).getTextContent());
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ImsReadWriteXmlFile.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " readFileXML -> " + ex.getMessage());
        } catch (SAXException ex) {
            Logger.getLogger(ImsReadWriteXmlFile.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " readFileXML -> " + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ImsReadWriteXmlFile.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " readFileXML -> " + ex.getMessage());
        }

        return hmParaXml;
    }

    /**
     *
     * @param strFileName
     * @return
     */
    public String getReportTypeForFileXml(String strFileName) {
        String sReportType = "";
        try {
            File xmlFile = new File(strFileName);
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName(Define.XML_MAIN_DATA_NOTE);
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);

//                System.err.println("Ten node Cha " + node.getNodeName());
//                System.out.println("\nElement type :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element elementImsRptData = (Element) node;
//                    System.err.println("student.getNodeName " + student.getNodeName());
//                    System.out.println("XML_REPORT_TYPE: " + elementImsRptData.getAttribute(Define.XML_REPORT_TYPE));
                    sReportType = elementImsRptData.getAttribute(Define.XML_REPORT_TYPE);

//                 }
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ImsReadWriteXmlFile.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " getReportTypeForFileXml -> " + ex.getMessage());
        } catch (SAXException ex) {
            Logger.getLogger(ImsReadWriteXmlFile.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " getReportTypeForFileXml -> " + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ImsReadWriteXmlFile.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " getReportTypeForFileXml -> " + ex.getMessage());
        }

        return sReportType;
    }

    public String getPoscdFromFileXml(String strFileName) {
        String sPosCd = "";
        try {
            File xmlFile = new File(strFileName);
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName(Define.XML_MAIN_DATA_NOTE);
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element elementImsRptData = (Element) node;
                    sPosCd = elementImsRptData.getAttribute(Define.XML_POS_CD);

                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ImsReadWriteXmlFile.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " getReportTypeForFileXml -> " + ex.getMessage());
        } catch (SAXException ex) {
            Logger.getLogger(ImsReadWriteXmlFile.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " getReportTypeForFileXml -> " + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ImsReadWriteXmlFile.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " getReportTypeForFileXml -> " + ex.getMessage());
        }

        return sPosCd;
    }

    public int getPosSendData(String sFileXml) {
        int nCount = 0;
        try {
            String sMapgd = null;
            String sNamrr = null;
            String sDotrr = null;
            String sNhomrr = null;
            String sVbxlrr = null;
            File xmlFile = new File(sFileXml);
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName(Define.XML_MAIN_DATA_NOTE);
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element elementImsRptData = (Element) node;
                    sMapgd = elementImsRptData.getElementsByTagName(Define.XML_POS_CD).item(0).getTextContent();
                    sNamrr = elementImsRptData.getElementsByTagName(Define.XML_NAM_BC).item(0).getTextContent();
//                    elementImsRptData.getAttribute(Define.XML_NAM_BC);
                    sDotrr = elementImsRptData.getElementsByTagName(Define.XML_DOT_RR).item(0).getTextContent();
//                    elementImsRptData.getAttribute(Define.XML_DOT_RR);
                    sNhomrr = elementImsRptData.getElementsByTagName(Define.XML_NHOM_RR).item(0).getTextContent();
                    sVbxlrr = elementImsRptData.getElementsByTagName(Define.XML_KHOA_RR).item(0).getTextContent();
//                    elementImsRptData.getAttribute(Define.XML_NHOM_RR);
                }
            }
            nCount = new DaoProcessRisk().getHistorySendData(sMapgd, sNamrr, sDotrr, sNhomrr, Define.KHOA_CHECK_RR, sVbxlrr).size();
        } catch (Exception ex) {
//             Logger.getLogger(ImsReadWriteXmlFile.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " getPosSendData -> " + ex.getMessage());
            nCount = 0;
        }
        return nCount;
    }

    public int getPosSendDataLock(String sFileXml) {
        HashMap<String, Object> hmParaXml = new HashMap<String, Object>();
        int icheck = 0;
        try {
            File xmlFile = new File(sFileXml);
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
            NodeList nodeList1 = document.getElementsByTagName(Define.XML_DATA);
            List<String> lstMto = new ArrayList<String>();
            String sMaPGD = "";
            String sMaTO = "";
            DaoDcptNo daoDcptNo = new DaoDcptNo();
            for (int temp = 0; temp < nodeList1.getLength(); temp++) {
                Node node = nodeList1.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element data = (Element) node;
                    DcptNoModel value = new DcptNoModel();
                    String sPGD = data.getElementsByTagName(Ma_PGD).item(0).getTextContent().trim();
                    String sMto = data.getElementsByTagName(Mto).item(0).getTextContent().trim();
                    System.err.println(sPGD + " - " + sMto);
                    sMaPGD = sPGD;
                    sMaTO = sMto;

                    if (sMto.equals("-1")) {
                        System.err.println(sPGD + " - " + sMto);
                        if (daoDcptNo.getDataCheckLockSend(sMaPGD, "-1") > 0) {
                            return 1;
                        }
                    } else {
                        if (!lstMto.contains(sMto)) {
                            lstMto.add(sMto);
                        }
                    }
                }
            }

            for (int i = 0; i < lstMto.size(); i++) {
                System.err.println(" List Mato:  - " + lstMto.get(i));
                if (daoDcptNo.getDataCheckLockSend(sMaPGD, lstMto.get(i)) > 0) {
                    icheck = icheck + 1;
                }
            }
            if (icheck == lstMto.size() && !sMaTO.equals("-1")) {
                return 1;
            } else {
                return 0;
            }
//            hmParaXml.put(Define.XML_DATA, getDataElementLock(document));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " readXmlDcPtLock -> " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return 0;
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

                    value.setsMapgd(data.getElementsByTagName(Ma_PGD).item(0).getTextContent().trim());
                    value.setsMto(data.getElementsByTagName(Mto).item(0).getTextContent().trim());

                    lstRisk.add(value);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataElementLock -> " + e.getMessage());
            System.err.println(" getDataElementLock -> " + e.getMessage());
            throw new ImsException("Lỗi trong hàm getDataElementLock -> ", e);
        }
        return lstRisk;
    }

    public HashMap<String, String> readFileXMLBCNT(String strFileName) {
        HashMap<String, String> hmParaXml = new HashMap<String, String>();
        try {
            File xmlFile = new File(strFileName);
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            Element elementRoot = document.getDocumentElement();
            NodeList nodeListRoot = elementRoot.getElementsByTagName(Define.XML_MAIN_DATA_NOTE);

            for (int temp = 0; temp < nodeListRoot.getLength(); temp++) {
                Node node = nodeListRoot.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementImsRptData = (Element) nodeListRoot.item(temp);
//                System.err.println("Lay ra ten cac node " + elementChild.getNodeName());

                    //System.err.println(elementImsRptData.getAttribute(Define.XML_DATA));
//                    System.out.println("XML_REPORT_TYPE: " + elementImsRptData.getAttribute(Define.XML_REPORT_TYPE));
                    hmParaXml.put(Define.XML_REPORT_TYPE, elementImsRptData.getAttribute(Define.XML_REPORT_TYPE));

//                    System.out.println("XML_REPORT_ID : " + elementImsRptData.getElementsByTagName(Define.XML_REPORT_ID).item(0).getTextContent());
                    hmParaXml.put(Define.XML_REPORT_ID, elementImsRptData.getElementsByTagName(Define.XML_REPORT_ID).item(0).getTextContent());

                    hmParaXml.put(Define.XML_REPORT_DATE, elementImsRptData.getElementsByTagName(Define.XML_REPORT_DATE).item(0).getTextContent());
//                    System.out.println("XML_USER_ID : " + elementImsRptData.getElementsByTagName(Define.XML_USER_ID).item(0).getTextContent());
                    hmParaXml.put(Define.XML_USER_ID, elementImsRptData.getElementsByTagName(Define.XML_USER_ID).item(0).getTextContent());

//                    System.out.println("XML_POS_CD : " + elementImsRptData.getElementsByTagName(Define.XML_POS_CD).item(0).getTextContent());
//                    String strMacn=elementImsRptData.getElementsByTagName(Define.XML_MA_CN).item(0).getTextContent();
                    hmParaXml.put(Define.XML_POS_CD, elementImsRptData.getElementsByTagName(Define.XML_POS_CD).item(0).getTextContent());
                    hmParaXml.put(Define.XML_MA_CN, elementImsRptData.getElementsByTagName(Define.XML_MA_CN).item(0).getTextContent());

//                    System.out.println("XML_GRADE : " + elementImsRptData.getElementsByTagName(Define.XML_GRADE).item(0).getTextContent());
                    hmParaXml.put(Define.XML_GRADE, elementImsRptData.getElementsByTagName(Define.XML_GRADE).item(0).getTextContent());

//                    System.out.println("XML_FLAG_DATA : " + elementImsRptData.getElementsByTagName(Define.XML_FLAG_DATA).item(0).getTextContent());
                    hmParaXml.put(Define.XML_FLAG_DATA, elementImsRptData.getElementsByTagName(Define.XML_FLAG_DATA).item(0).getTextContent());

//                    System.out.println("XML_DATA : " + elementImsRptData.getElementsByTagName(Define.XML_DATA).item(0).getTextContent());
//                    hmParaXml.put(Define.XML_DATA, elementImsRptData.getElementsByTagName(Define.XML_DATA).item(0).getTextContent());
                    NodeList ListData = elementImsRptData.getElementsByTagName(Define.XML_DATA);
//                    System.err.println("Do dai cua chuoi ben trong " + Integer.toString(ListData.getLength()));
                    if (ListData != null && ListData.getLength() > 0) {
                        for (int j = 0; j < ListData.getLength(); j++) {
                            Element elementChildData = (Element) ListData.item(j);
//                            getDataElement(elementChildData);
                            NodeList childNodeListData = elementChildData.getElementsByTagName("*");
                            if (childNodeListData != null && childNodeListData.getLength() > 0) {
                                for (int child = 0; child < childNodeListData.getLength(); child++) {
                                    Element eledata = (Element) childNodeListData.item(child);
                                    String datachild = eledata.getFirstChild().getNodeValue();
//                                    System.out.println(eledata.getNodeName() + " = " + datachild);
                                    hmParaXml.put(eledata.getNodeName(), datachild);
                                }
                            } else {
                                hmParaXml.put(Define.XML_DATA, elementImsRptData.getElementsByTagName(Define.XML_DATA).item(0).getTextContent());
                            }
                        }
                    }
//                    System.out.println("XML_SYSDATE : " + elementImsRptData.getElementsByTagName(Define.XML_SYSDATE).item(0).getTextContent());
                    hmParaXml.put(Define.XML_SYSDATE, elementImsRptData.getElementsByTagName(Define.XML_SYSDATE).item(0).getTextContent());

                    hmParaXml.put(Define.XML_QUY_BC, elementImsRptData.getElementsByTagName(Define.XML_QUY_BC).item(0).getTextContent());
                    hmParaXml.put(Define.XML_NAM_BC, elementImsRptData.getElementsByTagName(Define.XML_NAM_BC).item(0).getTextContent());
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ImsReadWriteXmlFile.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " readFileXMLBCNT -> " + ex.getMessage());
        } catch (SAXException ex) {
            Logger.getLogger(ImsReadWriteXmlFile.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " readFileXMLBCNT -> " + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ImsReadWriteXmlFile.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " readFileXMLBCNT -> " + ex.getMessage());
        }

        return hmParaXml;
    }

    public HashMap<String, String> readFileXMLKHNV(String strFileName) {
        HashMap<String, String> hmParaXml = new HashMap<String, String>();
        try {
            File xmlFile = new File(strFileName);
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            Element elementRoot = document.getDocumentElement();
            NodeList nodeListRoot = elementRoot.getElementsByTagName(Define.XML_MAIN_DATA_NOTE);

            for (int temp = 0; temp < nodeListRoot.getLength(); temp++) {
                Node node = nodeListRoot.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementImsRptData = (Element) nodeListRoot.item(temp);
//                System.err.println("Lay ra ten cac node " + elementChild.getNodeName());

                    //System.err.println(elementImsRptData.getAttribute(Define.XML_DATA));
//                    System.out.println("XML_REPORT_TYPE: " + elementImsRptData.getAttribute(Define.XML_REPORT_TYPE));
                    hmParaXml.put(Define.XML_REPORT_TYPE, elementImsRptData.getAttribute(Define.XML_REPORT_TYPE));

//                    System.out.println("XML_REPORT_ID : " + elementImsRptData.getElementsByTagName(Define.XML_REPORT_ID).item(0).getTextContent());
                    hmParaXml.put(Define.XML_REPORT_ID, elementImsRptData.getElementsByTagName(Define.XML_REPORT_ID).item(0).getTextContent());

                    hmParaXml.put(Define.XML_REPORT_DATE, elementImsRptData.getElementsByTagName(Define.XML_REPORT_DATE).item(0).getTextContent());
//                    System.out.println("XML_USER_ID : " + elementImsRptData.getElementsByTagName(Define.XML_USER_ID).item(0).getTextContent());
                    hmParaXml.put(Define.XML_USER_ID, elementImsRptData.getElementsByTagName(Define.XML_USER_ID).item(0).getTextContent());

//                    System.out.println("XML_POS_CD : " + elementImsRptData.getElementsByTagName(Define.XML_POS_CD).item(0).getTextContent());
//                    String strMacn=elementImsRptData.getElementsByTagName(Define.XML_MA_CN).item(0).getTextContent();
                    hmParaXml.put(Define.XML_POS_CD, elementImsRptData.getElementsByTagName(Define.XML_POS_CD).item(0).getTextContent());
                    hmParaXml.put(Define.XML_MA_CN, elementImsRptData.getElementsByTagName(Define.XML_MA_CN).item(0).getTextContent());
                    hmParaXml.put(Define.XML_MA_CT, elementImsRptData.getElementsByTagName(Define.XML_MA_CT).item(0).getTextContent());

//                    System.out.println("XML_GRADE : " + elementImsRptData.getElementsByTagName(Define.XML_GRADE).item(0).getTextContent());
                    hmParaXml.put(Define.XML_GRADE, elementImsRptData.getElementsByTagName(Define.XML_GRADE).item(0).getTextContent());

//                    System.out.println("XML_FLAG_DATA : " + elementImsRptData.getElementsByTagName(Define.XML_FLAG_DATA).item(0).getTextContent());
                    hmParaXml.put(Define.XML_FLAG_DATA, elementImsRptData.getElementsByTagName(Define.XML_FLAG_DATA).item(0).getTextContent());

//                    System.out.println("XML_DATA : " + elementImsRptData.getElementsByTagName(Define.XML_DATA).item(0).getTextContent());
//                    hmParaXml.put(Define.XML_DATA, elementImsRptData.getElementsByTagName(Define.XML_DATA).item(0).getTextContent());
                    NodeList ListData = elementImsRptData.getElementsByTagName(Define.XML_DATA);
//                    System.err.println("Do dai cua chuoi ben trong " + Integer.toString(ListData.getLength()));
                    if (ListData != null && ListData.getLength() > 0) {
                        for (int j = 0; j < ListData.getLength(); j++) {
                            Element elementChildData = (Element) ListData.item(j);
//                            getDataElement(elementChildData);
                            NodeList childNodeListData = elementChildData.getElementsByTagName("*");
                            if (childNodeListData != null && childNodeListData.getLength() > 0) {
                                for (int child = 0; child < childNodeListData.getLength(); child++) {
                                    Element eledata = (Element) childNodeListData.item(child);
                                    String datachild = eledata.getFirstChild().getNodeValue();
//                                    System.out.println(eledata.getNodeName() + " = " + datachild);
                                    hmParaXml.put(eledata.getNodeName(), datachild);
                                }
                            } else {
                                hmParaXml.put(Define.XML_DATA, elementImsRptData.getElementsByTagName(Define.XML_DATA).item(0).getTextContent());
                            }
                        }
                    }
//                    System.out.println("XML_SYSDATE : " + elementImsRptData.getElementsByTagName(Define.XML_SYSDATE).item(0).getTextContent());
                    hmParaXml.put(Define.XML_SYSDATE, elementImsRptData.getElementsByTagName(Define.XML_SYSDATE).item(0).getTextContent());

                    hmParaXml.put(Define.XML_NAM_BC, elementImsRptData.getElementsByTagName(Define.XML_NAM_BC).item(0).getTextContent());
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ImsReadWriteXmlFile.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " readFileXMLBCNT -> " + ex.getMessage());
        } catch (SAXException ex) {
            Logger.getLogger(ImsReadWriteXmlFile.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " readFileXMLBCNT -> " + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ImsReadWriteXmlFile.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " readFileXMLBCNT -> " + ex.getMessage());
        }

        return hmParaXml;
    }

    public void getDataElement(Element ElementData) {
        NodeList dataNodeList = ElementData.getElementsByTagName("*");
        System.out.println("dependantNodeList.getLength() do dai = " + dataNodeList.getLength());
        // I believe the problem is somewhere within this if statement.  
        if (dataNodeList != null && dataNodeList.getLength() > 0) {
            for (int i = 0; i < dataNodeList.getLength(); i++) {
                Element eledata = (Element) dataNodeList.item(i);
                String dependant = eledata.getFirstChild().getNodeValue();
                System.out.println(eledata.getNodeName() + " = " + dependant);

            }
        }
    }

    private String getTextValue(Element ele, String tagName) {
        String textVal = null;
        NodeList nl = ele.getElementsByTagName(tagName);
        System.err.println(ele.getNodeName());
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            textVal = el.getFirstChild().getNodeValue();

        }
        return (null == textVal) ? "" : textVal;
    }

    /**
     * String sReportType Loai bao cao 01,02,03.. String sReportId, Ma bao cao
     * neu co String sReportDate, Ngay bao cao nguoi su dung chon String
     * sUserId, User nhap tay bao cao String sPosCd, Ma pgd nhap tay bao cao
     * String sGrade, Cap bao cao 1, 2, 3 String sFlagData, Co du lieu cho bieu
     * KHTD: xaydungkh, giaokh, dieuchinhkh String sDataReport, day la phan du
     * lieu gui len server trung uong cap nhat vao csdl String sFileName Chi ten
     * file xml ghi ra
     */
    public boolean createFileXML(String sReportType, String sReportId, String sReportDate, String sUserId, String sPosCd, String sGrade,
            String sFlagData, String sDataReport, String sQuyBC, String sFileName) throws IOException {
        String strSysDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date());
        boolean bSuccess = false;
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            //Tao root node cho file xml
            Element rootElement = document.createElement(Define.XML_ROOT_NOTE);
            document.appendChild(rootElement);

            //Dinh nghia cho thanh phan data
            Element dataElement = document.createElement(Define.XML_MAIN_DATA_NOTE);
            rootElement.appendChild(dataElement);

            //Add loai bao cao vao 01,02,03...
            Attr attribute = document.createAttribute(Define.XML_REPORT_TYPE);
            attribute.setValue(sReportType);
            dataElement.setAttributeNode(attribute);
            //add cac thanh phan data ben trong
            //Add cho report id
            Element reportId = document.createElement(Define.XML_REPORT_ID);
            reportId.appendChild(document.createTextNode(sReportId));
            dataElement.appendChild(reportId);

            //Add cho report date
            Element reportDate = document.createElement(Define.XML_REPORT_DATE);
            reportDate.appendChild(document.createTextNode(sReportDate));
            dataElement.appendChild(reportDate);

            //Add cho userid
            Element userId = document.createElement(Define.XML_USER_ID);
            userId.appendChild(document.createTextNode(sUserId));
            dataElement.appendChild(userId);

            //Add data cho Pos cd
            Element poscd = document.createElement(Define.XML_POS_CD);
            poscd.appendChild(document.createTextNode(sPosCd));
            dataElement.appendChild(poscd);

            //Add data cho cap bao cao
            Element grade = document.createElement(Define.XML_GRADE);
            grade.appendChild(document.createTextNode(sGrade));
            dataElement.appendChild(grade);

            //Co du lieu update cho khtd
            Element FlagData = document.createElement(Define.XML_FLAG_DATA);
            FlagData.appendChild(document.createTextNode(sFlagData));
            dataElement.appendChild(FlagData);

            //Add du lieu chinh
            Element Data = document.createElement(Define.XML_DATA);
            Data.appendChild(document.createTextNode(sDataReport));
            dataElement.appendChild(Data);

            //Add them ngay tao du lieu cho bao cao
            Element SysDate = document.createElement(Define.XML_SYSDATE);
            SysDate.appendChild(document.createTextNode(strSysDate));
            dataElement.appendChild(SysDate);

            Element Quybc = document.createElement(Define.XML_QUY_BC);
            Quybc.appendChild(document.createTextNode(sQuyBC));
            dataElement.appendChild(Quybc);

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
        } catch (TransformerException tex) {
//            CoreLogger.error(ImsCreateXml.class.getCanonicalName() + " createFileXML -> " + tex.getMessage());
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " createFileXML -> " + tex.getMessage());
        } catch (ParserConfigurationException ex) {
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " createFileXML -> " + ex.getMessage());
        }
        return bSuccess;
    }

    public boolean createFileXMLBCNT(String sReportType, String sReportId, String sReportDate, String sUserId, String sPosCd, String sMaCN, String sGrade,
            String sFlagData, HashMap<String, String> hmDataReport, String sQuyBC, String sNamBC, String sFileName) throws IOException {
        String strSysDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date());
        boolean bSuccess = false;
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            //Tao root node cho file xml
            Element rootElement = document.createElement(Define.XML_ROOT_NOTE);
            document.appendChild(rootElement);

            //Dinh nghia cho thanh phan data
            Element dataElement = document.createElement(Define.XML_MAIN_DATA_NOTE);
            rootElement.appendChild(dataElement);

            //Add loai bao cao vao 01,02,03...
            Attr attribute = document.createAttribute(Define.XML_REPORT_TYPE);
            attribute.setValue(sReportType);
            dataElement.setAttributeNode(attribute);
            //add cac thanh phan data ben trong
            //Add cho report id
            Element reportId = document.createElement(Define.XML_REPORT_ID);
            reportId.appendChild(document.createTextNode(sReportId));
            dataElement.appendChild(reportId);

            //Add cho report date
            Element reportDate = document.createElement(Define.XML_REPORT_DATE);
            reportDate.appendChild(document.createTextNode(sReportDate));
            dataElement.appendChild(reportDate);

            //Add cho userid
            Element userId = document.createElement(Define.XML_USER_ID);
            userId.appendChild(document.createTextNode(sUserId));
            dataElement.appendChild(userId);

            //Add data cho Pos cd
            Element poscd = document.createElement(Define.XML_POS_CD);
            poscd.appendChild(document.createTextNode(sPosCd));
            dataElement.appendChild(poscd);

            //Add data cho ma CN
            Element maCn = document.createElement(Define.XML_MA_CN);
            maCn.appendChild(document.createTextNode(sMaCN));
            dataElement.appendChild(maCn);

            //Add data cho cap bao cao
            Element grade = document.createElement(Define.XML_GRADE);
            grade.appendChild(document.createTextNode(sGrade));
            dataElement.appendChild(grade);

            //Co du lieu update cho khtd
            Element FlagData = document.createElement(Define.XML_FLAG_DATA);
            FlagData.appendChild(document.createTextNode(sFlagData));
            dataElement.appendChild(FlagData);

            //Add du lieu chinh
            Element Data = document.createElement(Define.XML_DATA);
//            Data.appendChild(document.createTextNode(Define.XML_DATA_VALUE));
            dataElement.appendChild(Data);
            Attr attr = document.createAttribute("id");
            attr.setValue(Define.XML_DATA_VALUE);
            Data.setAttributeNode(attr);
            for (String key : hmDataReport.keySet()) {

                Element Datachild = document.createElement(key);
                Datachild.appendChild(document.createTextNode(hmDataReport.get(key)));
                Data.appendChild(Datachild);
//            System.err.println("Gia tri " + hmModuleTable.get(key));
            }

            //Add them ngay tao du lieu cho bao cao
            Element SysDate = document.createElement(Define.XML_SYSDATE);
            SysDate.appendChild(document.createTextNode(strSysDate));
            dataElement.appendChild(SysDate);

            Element Quybc = document.createElement(Define.XML_QUY_BC);
            Quybc.appendChild(document.createTextNode(sQuyBC));
            dataElement.appendChild(Quybc);

            Element Nambc = document.createElement(Define.XML_NAM_BC);
            Nambc.appendChild(document.createTextNode(sNamBC));
            dataElement.appendChild(Nambc);

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
        } catch (TransformerException tex) {
//            CoreLogger.error(ImsCreateXml.class.getCanonicalName() + " createFileXML -> " + tex.getMessage());
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " createFileXML -> " + tex.getMessage());
        } catch (ParserConfigurationException ex) {
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " createFileXML -> " + ex.getMessage());
        }
        return bSuccess;
    }

    public boolean createFileXMLVB819(String sReportType, String sReportId, String sReportDate, String sUserId, String sPosCd, String sMaCN, String sGrade,
            String sFlagData, HashMap<String, String> hmDataReport, String sQuyBC, String sNamBC, String sFileName) throws IOException {
        String strSysDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date());
        boolean bSuccess = false;
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            //Tao root node cho file xml
            Element rootElement = document.createElement(Define.XML_ROOT_NOTE);
            document.appendChild(rootElement);

            //Dinh nghia cho thanh phan data
            Element dataElement = document.createElement(Define.XML_MAIN_DATA_NOTE);
            rootElement.appendChild(dataElement);

            //Add loai bao cao vao 01,02,03...
            Attr attribute = document.createAttribute(Define.XML_REPORT_TYPE);
            attribute.setValue(sReportType);
            dataElement.setAttributeNode(attribute);
            //add cac thanh phan data ben trong
            //Add cho report id
            Element reportId = document.createElement(Define.XML_REPORT_ID);
            reportId.appendChild(document.createTextNode(sReportId));
            dataElement.appendChild(reportId);

            //Add cho report date
            Element reportDate = document.createElement(Define.XML_REPORT_DATE);
            reportDate.appendChild(document.createTextNode(sReportDate));
            dataElement.appendChild(reportDate);

            //Add cho userid
            Element userId = document.createElement(Define.XML_USER_ID);
            userId.appendChild(document.createTextNode(sUserId));
            dataElement.appendChild(userId);

            //Add data cho Pos cd
            Element poscd = document.createElement(Define.XML_POS_CD);
            poscd.appendChild(document.createTextNode(sPosCd));
            dataElement.appendChild(poscd);

            //Add data cho ma CN
            Element maCn = document.createElement(Define.XML_MA_CN);
            maCn.appendChild(document.createTextNode(sMaCN));
            dataElement.appendChild(maCn);

            //Add data cho cap bao cao
            Element grade = document.createElement(Define.XML_GRADE);
            grade.appendChild(document.createTextNode(sGrade));
            dataElement.appendChild(grade);

            //Co du lieu update cho khtd
            Element FlagData = document.createElement(Define.XML_FLAG_DATA);
            FlagData.appendChild(document.createTextNode(sFlagData));
            dataElement.appendChild(FlagData);

            //Add du lieu chinh
            Element Data = document.createElement(Define.XML_DATA);
//            Data.appendChild(document.createTextNode(Define.XML_DATA_VALUE));
            dataElement.appendChild(Data);
            Attr attr = document.createAttribute("id");
            attr.setValue(Define.XML_DATA_VALUE);
            Data.setAttributeNode(attr);
            for (String key : hmDataReport.keySet()) {

                Element Datachild = document.createElement(key);
                Datachild.appendChild(document.createTextNode(hmDataReport.get(key)));
                Data.appendChild(Datachild);
//            System.err.println("Gia tri " + hmModuleTable.get(key));
            }

            //Add them ngay tao du lieu cho bao cao
            Element SysDate = document.createElement(Define.XML_SYSDATE);
            SysDate.appendChild(document.createTextNode(strSysDate));
            dataElement.appendChild(SysDate);

            Element Quybc = document.createElement(Define.XML_QUY_BC);
            Quybc.appendChild(document.createTextNode(sQuyBC));
            dataElement.appendChild(Quybc);

            Element Nambc = document.createElement(Define.XML_NAM_BC);
            Nambc.appendChild(document.createTextNode(sNamBC));
            dataElement.appendChild(Nambc);

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
        } catch (TransformerException tex) {
//            CoreLogger.error(ImsCreateXml.class.getCanonicalName() + " createFileXML -> " + tex.getMessage());
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " createFileXMLVB819 -> " + tex.getMessage());
        } catch (ParserConfigurationException ex) {
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " createFileXMLVB819 -> " + ex.getMessage());
        }
        return bSuccess;
    }

    public boolean createFileXMLKHNV(String sReportType, String sReportId, String sReportDate, String sUserId, String sPosCd, String sMaCN, String sMaCT, String sGrade,
            String sFlagData, HashMap<String, String> hmDataReport, String sNamBC, String sFileName) throws IOException {
        String strSysDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date());
        boolean bSuccess = false;
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            //Tao root node cho file xml
            Element rootElement = document.createElement(Define.XML_ROOT_NOTE);
            document.appendChild(rootElement);

            //Dinh nghia cho thanh phan data
            Element dataElement = document.createElement(Define.XML_MAIN_DATA_NOTE);
            rootElement.appendChild(dataElement);

            //Add loai bao cao vao 01,02,03...
            Attr attribute = document.createAttribute(Define.XML_REPORT_TYPE);
            attribute.setValue(sReportType);
            dataElement.setAttributeNode(attribute);
            //add cac thanh phan data ben trong
            //Add cho report id
            Element reportId = document.createElement(Define.XML_REPORT_ID);
            reportId.appendChild(document.createTextNode(sReportId));
            dataElement.appendChild(reportId);

            //Add cho report date
            Element reportDate = document.createElement(Define.XML_REPORT_DATE);
            reportDate.appendChild(document.createTextNode(sReportDate));
            dataElement.appendChild(reportDate);

            //Add cho userid
            Element userId = document.createElement(Define.XML_USER_ID);
            userId.appendChild(document.createTextNode(sUserId));
            dataElement.appendChild(userId);

            //Add data cho Pos cd
            Element poscd = document.createElement(Define.XML_POS_CD);
            poscd.appendChild(document.createTextNode(sPosCd));
            dataElement.appendChild(poscd);

            //Add data cho ma CN
            Element maCn = document.createElement(Define.XML_MA_CN);
            maCn.appendChild(document.createTextNode(sMaCN));
            dataElement.appendChild(maCn);

            //Add data cho ma Chi Tieu
            Element maCt = document.createElement(Define.XML_MA_CT);
            maCt.appendChild(document.createTextNode(sMaCT));
            dataElement.appendChild(maCt);

            //Add data cho cap bao cao
            Element grade = document.createElement(Define.XML_GRADE);
            grade.appendChild(document.createTextNode(sGrade));
            dataElement.appendChild(grade);

            //Co du lieu update cho khtd
            Element FlagData = document.createElement(Define.XML_FLAG_DATA);
            FlagData.appendChild(document.createTextNode(sFlagData));
            dataElement.appendChild(FlagData);

            //Add du lieu chinh
            Element Data = document.createElement(Define.XML_DATA);
//            Data.appendChild(document.createTextNode(Define.XML_DATA_VALUE));
            dataElement.appendChild(Data);
            Attr attr = document.createAttribute("id");
            attr.setValue(Define.XML_DATA_VALUE);
            Data.setAttributeNode(attr);
            for (String key : hmDataReport.keySet()) {

                Element Datachild = document.createElement(key);
                Datachild.appendChild(document.createTextNode(hmDataReport.get(key)));
                Data.appendChild(Datachild);
//            System.err.println("Gia tri " + hmModuleTable.get(key));
            }

            //Add them ngay tao du lieu cho bao cao
            Element SysDate = document.createElement(Define.XML_SYSDATE);
            SysDate.appendChild(document.createTextNode(strSysDate));
            dataElement.appendChild(SysDate);

            Element Nambc = document.createElement(Define.XML_NAM_BC);
            Nambc.appendChild(document.createTextNode(sNamBC));
            dataElement.appendChild(Nambc);

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
        } catch (TransformerException tex) {
//            CoreLogger.error(ImsCreateXml.class.getCanonicalName() + " createFileXML -> " + tex.getMessage());
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " createFileXML -> " + tex.getMessage());
        } catch (ParserConfigurationException ex) {
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " createFileXML -> " + ex.getMessage());
        }
        return bSuccess;
    }

    public byte[] readFileByte(String strFilename) {

        byte[] outbyte = null;
        File file = new File(strFilename);
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(file);
            outbyte = new byte[(int) file.length()];
            // Reads up to certain bytes of data from this input stream into an array of bytes.
            fin.read(outbyte);
            //create string from byte array
//            String s = new String(outbyte);
//            System.out.println("File content: " + s);
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " readFileByte -> " + e.getMessage());
        } catch (IOException ioe) {
            System.out.println("Exception while reading file " + ioe);
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " readFileByte -> " + ioe.getMessage());
        } finally {
            // close the streams using close method
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);
                CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " readFileByte -> " + ioe.getMessage());
            }
        }

        return outbyte;
    }

    public boolean WriteFileFromByte(byte[] inbyte, String strFullPath) {
        boolean bSuccess = false;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(strFullPath);
            fos.write(inbyte);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImsReadWriteXmlFile.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " WriteFileFromByte -> " + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ImsReadWriteXmlFile.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " WriteFileFromByte -> " + ex.getMessage());
        } finally {
            // close the streams using close method
            try {
                if (fos != null) {
                    fos.close();
                }
                bSuccess = true;
            } catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);
                CoreLogger.error(ImsReadWriteXmlFile.class.getCanonicalName() + " WriteFileFromByte -> " + ioe.getMessage());
            }
        }
        return bSuccess;
    }

    public boolean createXmlFileVB819(String sReportType,
            String sUserId, String sGrade, String sPosCd, String sNgayBC, List<ModelCommune> lstData819, String sFileName) {
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
            addNode(document, dataElement, Define.XML_NGAY_BC, sNgayBC);
            //Add cho dot rr
//            addNode(document, dataElement, Define.XML_DOT_RR, sDotrr);
            //Add cho nhom bao cao
//            addNode(document, dataElement, Define.XML_NHOM_RR, sNhomrr);
            //Add them ngay tao du lieu cho bao cao
            addNode(document, dataElement, Define.XML_SYSDATE, strSysDate);

            //Add du lieu chinh add nhu sau
//            <IMS_DATA soku="6000002500006092">
//                    <duno>1500000</duno>
//                    <lai>1176043</lai>
//            </IMS_DATA>
            for (ModelCommune value : lstData819) {
                Element elementData = addNodeWithKey(document, dataElement, Define.XML_DATA, "sPos", value.getsPoscd());
                addNode(document, elementData, "sCommune", value.getsCommuneid());
                addNode(document, elementData, "sReportDt", value.getsReportdt());
                addNode(document, elementData, "sCode", value.getsCode());
                addNode(document, elementData, "bValue", Double.toString(value.getbValue()));
                addNode(document, elementData, "bMark", Double.toString(value.getbMark()));
                addNode(document, elementData, "sMainPos", value.getsMainpos());
                addNode(document, elementData, "sSubCode", value.getsSubcode());
                addNode(document, elementData, "sUserID", value.getsUserid());

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
            CoreLogger.error(this.getClass().getCanonicalName() + " createXmlFile819 -> " + tex.getMessage());
            System.err.println(tex.getMessage());
        }
        return bSuccess;
    }

    public HashMap<String, Object> readXmlVB819(String sFileName) {
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

                    hmParaXml.put(Define.XML_NGAY_BC, elementImsRptData.getElementsByTagName(Define.XML_NGAY_BC).item(0).getTextContent());

//                    hmParaXml.put(Define.XML_DOT_RR, elementImsRptData.getElementsByTagName(Define.XML_DOT_RR).item(0).getTextContent());
//                    hmParaXml.put(Define.XML_NHOM_RR, elementImsRptData.getElementsByTagName(Define.XML_NHOM_RR).item(0).getTextContent());
                }
            }
            hmParaXml.put(Define.XML_DATA, getDataElementVB819(document));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " readXmpRisk -> " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return hmParaXml;
    }

    private List<ModelCommune> getDataElementVB819(Document document) {
        List<ModelCommune> lstRisk = new ArrayList<ModelCommune>();
        try {
            NodeList nodeList = document.getElementsByTagName(Define.XML_DATA);
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element data = (Element) node;
                    ModelCommune risk = new ModelCommune();
                    risk.setsPoscd(data.getAttribute("sPos"));
                    risk.setsCommuneid(data.getElementsByTagName("sCommune").item(0).getTextContent());
                    risk.setsReportdt(data.getElementsByTagName("sReportDt").item(0).getTextContent());
                    risk.setsCode(data.getElementsByTagName("sCode").item(0).getTextContent());
                    risk.setbValue(Double.parseDouble(data.getElementsByTagName("bValue").item(0).getTextContent()));
                    risk.setbMark(Double.parseDouble(data.getElementsByTagName("bMark").item(0).getTextContent()));
                    risk.setsMainpos(data.getElementsByTagName("sMainPos").item(0).getTextContent());
                    risk.setsSubcode(data.getElementsByTagName("sSubCode").item(0).getTextContent());
                    risk.setsUserid(data.getElementsByTagName("sUserID").item(0).getTextContent());

                    lstRisk.add(risk);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataElementVB819 -> " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return lstRisk;
    }

    //<editor-fold defaultstate="collapsed" desc="Doc ghi XML cho risk">
    private String sVb_xlrr = "vb_xlrr";
    private String sSoku = "soku";
    private String sTenkh = "TenKH";
    private String sDnghi_Dno = "duno";
    private String sDnghi_Lai = "lai";
    private String sNgayvay = "Ngayvay";
    private String sNgayDh = "ngaydenhan";
    private String sMdthiethai = "Thiethai";
    private String sNgayrr = "Ngayruiro";
    private String sDnghi_Tg = "thang_dn";
    private String sNguyenNhan = "Nguyennhan";
    private String sTrangthai = "Trangthai";
    private String sPduyet_Ngay_Cn = "Ngay_pd_cn";
    private String sPduyet_Nguoi_Cn = "Nguoi_pd_cn";
    private String sPduyet_Cap = "Cap_pd";
    private String sNguoi_pduyet_pgd = "Nguoi_pd_pgd";
    private String sNgay_pduyet_pgd = "Ngay_pd_pgd";
    private String sNguyennhan_tuchoi = "Nguyennhan_tuchoi";

    public boolean createXmlFileRisk(String sReportType,
            String sUserId, String sGrade, String sPosCd, String sNamrr, String sDotrr,
            String sNhomrr, List<ModelRiskProcess.ListRiskSync> lstDataRisk, String sFileName, String Vbxlrr) {
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
            addNode(document, dataElement, Define.XML_KHOA_RR, Vbxlrr);
            //Add them ngay tao du lieu cho bao cao
            addNode(document, dataElement, Define.XML_SYSDATE, strSysDate);

            //Add du lieu chinh add nhu sau
//            <IMS_DATA soku="6000002500006092">
//                    <duno>1500000</duno>
//                    <lai>1176043</lai>
//            </IMS_DATA>
            for (ModelRiskProcess.ListRiskSync value : lstDataRisk) {
                Element elementData = addNodeWithKey(document, dataElement, Define.XML_DATA, sSoku, value.getsSoku());
                addNode(document, elementData, sVb_xlrr, value.getSvbxlrr());
                addNode(document, elementData, sTenkh, value.getsTenkh());
                addNode(document, elementData, sDnghi_Dno, value.getsDnghi_Dno());
                addNode(document, elementData, sDnghi_Lai, value.getsDnghi_Lai());
                addNode(document, elementData, sNgayvay, value.getsNgayvay());
                addNode(document, elementData, sNgayDh, value.getsNgaydh());
                addNode(document, elementData, sMdthiethai, value.getsMdthiethai());
                addNode(document, elementData, sNgayrr, value.getsNgayrr());
                addNode(document, elementData, sDnghi_Tg, value.getsDnghi_Tg());
                addNode(document, elementData, sNguyenNhan, value.getsNguyennhan());

                addNode(document, elementData, sTrangthai, value.getsTrangthai());
                addNode(document, elementData, sPduyet_Ngay_Cn, value.getsPduyet_Ngay_Cn());
                addNode(document, elementData, sPduyet_Nguoi_Cn, value.getsPduyet_Nguoi_Cn());
                addNode(document, elementData, sPduyet_Cap, value.getsPduyet_Cap());
                addNode(document, elementData, sNguoi_pduyet_pgd, value.getsNguoi_pduyet_pgd());
                addNode(document, elementData, sNgay_pduyet_pgd, value.getsNgay_pduyet_pgd());
                addNode(document, elementData, sNguyennhan_tuchoi, value.getsNguyennhan_tuchoi());
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

    public HashMap<String, Object> readXmlRisk(String sFileName) {
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
                    hmParaXml.put(Define.XML_KHOA_RR, elementImsRptData.getElementsByTagName(Define.XML_KHOA_RR).item(0).getTextContent());
                }
            }
            hmParaXml.put(Define.XML_DATA, getDataElement(document));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " readXmpRisk -> " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return hmParaXml;
    }

    private List<ModelRiskProcess.ListRiskSync> getDataElement(Document document) {
        List<ModelRiskProcess.ListRiskSync> lstRisk = new ArrayList<ModelRiskProcess.ListRiskSync>();
        try {
            NodeList nodeList = document.getElementsByTagName(Define.XML_DATA);
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element data = (Element) node;
                    ModelRiskProcess.ListRiskSync risk = new ModelRiskProcess.ListRiskSync();
                    risk.setsSoku(data.getAttribute(sSoku));
                    risk.setSvbxlrr(data.getElementsByTagName(sVb_xlrr).item(0).getTextContent());
                    risk.setsTenkh(data.getElementsByTagName(sTenkh).item(0).getTextContent());
                    risk.setsDnghi_Dno(data.getElementsByTagName(sDnghi_Dno).item(0).getTextContent());
                    risk.setsDnghi_Lai(data.getElementsByTagName(sDnghi_Lai).item(0).getTextContent());
                    risk.setsNgayvay(data.getElementsByTagName(sNgayvay).item(0).getTextContent());
                    risk.setsNgaydh(data.getElementsByTagName(sNgayDh).item(0).getTextContent());
                    risk.setsMdthiethai(data.getElementsByTagName(sMdthiethai).item(0).getTextContent());
                    risk.setsNgayrr(data.getElementsByTagName(sNgayrr).item(0).getTextContent());
                    risk.setsDnghi_Tg(data.getElementsByTagName(sDnghi_Tg).item(0).getTextContent());

                    risk.setsNguyennhan(data.getElementsByTagName(sNguyenNhan).item(0).getTextContent());
                    risk.setsTrangthai(data.getElementsByTagName(sTrangthai).item(0).getTextContent());
                    risk.setsPduyet_Ngay_Cn(data.getElementsByTagName(sPduyet_Ngay_Cn).item(0).getTextContent());
                    risk.setsPduyet_Nguoi_Cn(data.getElementsByTagName(sPduyet_Nguoi_Cn).item(0).getTextContent());
                    risk.setsPduyet_Cap(data.getElementsByTagName(sPduyet_Cap).item(0).getTextContent());
                    risk.setsNguoi_pduyet_pgd(data.getElementsByTagName(sNguoi_pduyet_pgd).item(0).getTextContent());
                    risk.setsNgay_pduyet_pgd(data.getElementsByTagName(sNgay_pduyet_pgd).item(0).getTextContent());
                    risk.setsNguyennhan_tuchoi(data.getElementsByTagName(sNguyennhan_tuchoi).item(0).getTextContent());
                    lstRisk.add(risk);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataElement -> " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return lstRisk;
    }

    private List<vbsp.ims.model.Commune> getDataElement_Dmxa(Document document) {
        List<vbsp.ims.model.Commune> communes = new ArrayList<>();
        try {
            NodeList nodeList = document.getElementsByTagName(Define.XML_DATA);
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element data = (Element) node;
                    vbsp.ims.model.Commune commune = new vbsp.ims.model.Commune();
                    commune.setCommune_id(data.getAttribute(
                            vbsp.ims.define.Constant.dmxa_constant._COMMUNE_ID));
                    commune.setCommune_name(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmxa_constant._COMMUNE_NAME
                    ).item(0).getTextContent());
                    commune.setGdx_flg(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmxa_constant._GDXFLG).item(0).getTextContent());
                    commune.setXa135_flg(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmxa_constant._XA135FLG
                    ).item(0).getTextContent());
                    commune.setStatus(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmxa_constant._STATUS
                    ).item(0).getTextContent());
                    commune.setNgaygdx(Integer.parseInt(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmxa_constant._COMMUNE_DT
                    ).item(0).getTextContent()));
                    commune.setPgd_ql(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmxa_constant._MANAGER_POS
                    ).item(0).getTextContent());
                    commune.setNongthonmoi_flg(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmxa_constant._NEW_COMMUNE_FLG
                    ).item(0).getTextContent());
                     commune.setCanbotdpt(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmxa_constant._CANBOTDPT
                    ).item(0).getTextContent());
                    commune.setMaker_id(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmxa_constant._MAKER_ID
                    ).item(0).getTextContent());
                    commune.setMaker_dt(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmxa_constant._MAKER_DT
                    ).item(0).getTextContent());
                    communes.add(commune);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataElement_Dmxa -> " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return communes;
    }
    //</editor-fold>

    public boolean createFileXML_Dmxa(String sync_pos_cd,
            List<vbsp.ims.model.Commune> communes, String file_name) throws IOException {
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
            Element dataElement = addNodeWithKey(document, rootElement, Define.XML_MAIN_DATA_NOTE,
                    Define.XML_REPORT_TYPE, Define.PARA_SYN_REPORT_DMXA);
            //Dinh nghia cho thanh phan data
//            //Add cho userid
//            addNode(document, dataElement, Define.XML_USER_ID, sUserId);
//            //Add cho cap bao cao
//            addNode(document, dataElement, Define.XML_GRADE, sGrade);
            //Add data cho Pos cd
            addNode(document, dataElement, Define.XML_POS_CD, sync_pos_cd);

            //Add data cho cap bao cao            
            //Add them ngay tao du lieu cho bao cao
            addNode(document, dataElement, Define.XML_SYSDATE, strSysDate);

            //Add du lieu chinh add nhu sau
//            <IMS_DATA soku="6000002500006092">
//                    <duno>1500000</duno>
//                    <lai>1176043</lai>
//            </IMS_DATA>
            for (vbsp.ims.model.Commune value : communes) {
                Element elementData = addNodeWithKey(document, dataElement, Define.XML_DATA,
                        vbsp.ims.define.Constant.dmxa_constant._COMMUNE_ID, value.getCommune_id());
                addNode(document, elementData,
                        vbsp.ims.define.Constant.dmxa_constant._COMMUNE_NAME, value.getCommune_name());
                addNode(document, elementData,
                        vbsp.ims.define.Constant.dmxa_constant._GDXFLG, value.getGdx_flg());
                addNode(document, elementData,
                        vbsp.ims.define.Constant.dmxa_constant._XA135FLG, value.getXa135_flg());
                addNode(document, elementData,
                        vbsp.ims.define.Constant.dmxa_constant._STATUS, value.getStatus());
                addNode(document, elementData,
                        vbsp.ims.define.Constant.dmxa_constant._COMMUNE_DT, String.valueOf(value.getNgaygdx()));
                addNode(document, elementData,
                        vbsp.ims.define.Constant.dmxa_constant._MANAGER_POS, value.getPgd_ql());
                addNode(document, elementData,
                        vbsp.ims.define.Constant.dmxa_constant._NEW_COMMUNE_FLG, value.getNongthonmoi_flg());
                addNode(document, elementData,
                        vbsp.ims.define.Constant.dmxa_constant._CANBOTDPT, value.getCanbotdpt());
                addNode(document, elementData,
                        vbsp.ims.define.Constant.dmxa_constant._MAKER_ID, value.getMaker_id().trim() + "-FW");
                addNode(document, elementData,
                        vbsp.ims.define.Constant.dmxa_constant._MAKER_DT, strSysDate);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();

            DOMSource domSource = new DOMSource(document);

//            StreamResult streamResult;
//            streamResult = new StreamResult(new File(file_name));
//            transformer.transform(domSource, streamResult);
            Writer writer = null;
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file_name), "UTF8"));
            StreamResult streamResult = new StreamResult(writer);
            transformer.transform(domSource, streamResult);
            if (writer != null) {
                writer.close();
            }
            bSuccess = true;
        } catch (ParserConfigurationException | DOMException | TransformerException tex) {
            CoreLogger.error(this.getClass().getCanonicalName() + " createFileXML_Dmxa -> " + tex.getMessage());
            System.err.println(tex.getMessage());
        }
        return bSuccess;

    }

    public HashMap<String, Object> readXml_Dmxa(String file_name) {
        HashMap<String, Object> hmParaXml = new HashMap<>();
        try {
            File xmlFile = new File(file_name);
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
                    hmParaXml.put(Define.XML_POS_CD, elementImsRptData.getElementsByTagName(Define.XML_POS_CD).item(0).getTextContent());
                    hmParaXml.put(Define.XML_SYSDATE, elementImsRptData.getElementsByTagName(Define.XML_SYSDATE).item(0).getTextContent());
                }
            }
            Object obj = getDataElement_Dmxa(document);
            hmParaXml.put(Define.XML_DATA, obj);
        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " readXml_Dmxa -> " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return hmParaXml;
    }

    public boolean createFileXML_Dmpos(String sync_pos_cd, Branch value, String file_name) throws IOException {
        boolean bSuccess = true;
        String strSysDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date());
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            //Tao root node cho file xml
            Element rootElement = document.createElement(Define.XML_ROOT_NOTE);
            document.appendChild(rootElement);
            Element dataElement = addNodeWithKey(document, rootElement, Define.XML_MAIN_DATA_NOTE,
                    Define.XML_REPORT_TYPE, Define.PARA_SYN_REPORT_DMPOS);
            addNode(document, dataElement, Define.XML_POS_CD, sync_pos_cd);
            //Add data cho cap bao cao            
            //Add them ngay tao du lieu cho bao cao
            addNode(document, dataElement, Define.XML_SYSDATE, strSysDate);

            //Add du lieu chinh add nhu sau
            Element elementData = addNodeWithKey(document, dataElement, Define.XML_DATA,
                    vbsp.ims.define.Constant.dmpos_constant._POS_CD, value.getPos_cd());
            addNode(document, elementData,
                    vbsp.ims.define.Constant.dmpos_constant._POS_NAME, value.getPos_name());
            addNode(document, elementData,
                    vbsp.ims.define.Constant.dmpos_constant._POS_ADDRESS, value.getPos_address());
            addNode(document, elementData,
                    vbsp.ims.define.Constant.dmpos_constant._POS_FAX, value.getPos_fax());
            addNode(document, elementData,
                    vbsp.ims.define.Constant.dmpos_constant._POS_MOBILE, value.getPos_mobile());
            addNode(document, elementData,
                    vbsp.ims.define.Constant.dmpos_constant._POS_SBVCODE, value.getPos_sbvcode());
            addNode(document, elementData,
                    vbsp.ims.define.Constant.dmpos_constant._POS_FLAG, value.getPos_fax());
            addNode(document, elementData,
                    vbsp.ims.define.Constant.dmpos_constant._MAIN_POS, value.getMain_pos());
            addNode(document, elementData,
                    vbsp.ims.define.Constant.dmpos_constant._STATUS, value.getStatus());
            addNode(document, elementData,
                    vbsp.ims.define.Constant.dmxa_constant._MAKER_ID, value.getMaker_id().trim() + "-FW");
            addNode(document, elementData,
                    vbsp.ims.define.Constant.dmpos_constant._MAKER_DT, strSysDate);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
//            StreamResult streamResult;
//            streamResult = new StreamResult(new File(file_name));
//            transformer.transform(domSource, streamResult);
            Writer writer = null;
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file_name), "UTF8"));
            StreamResult streamResult = new StreamResult(writer);
            transformer.transform(domSource, streamResult);
            if (writer != null) {
                writer.close();
            }
            bSuccess = true;
        } catch (ParserConfigurationException | DOMException | TransformerException tex) {
            CoreLogger.error(this.getClass().getCanonicalName() + " createFileXML_Dmpos -> " + tex.getMessage());
            System.err.println(tex.getMessage());
        }
        return bSuccess;
    }

    public HashMap<String, Object> readXml_DmPos(String file_name) {
        HashMap<String, Object> hmParaXml = new HashMap<>();
        try {
            File xmlFile = new File(file_name);
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
                    hmParaXml.put(Define.XML_POS_CD, elementImsRptData.getElementsByTagName(Define.XML_POS_CD).item(0).getTextContent());
                    hmParaXml.put(Define.XML_SYSDATE, elementImsRptData.getElementsByTagName(Define.XML_SYSDATE).item(0).getTextContent());
                }
            }
            Object obj = getDataElement_Dmpos(document);
            hmParaXml.put(Define.XML_DATA, obj);
        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " readXml_Dmxa -> " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return hmParaXml;
    }

    private Object getDataElement_Dmpos(Document document) {
        List<vbsp.ims.model.Branch> branchs = new ArrayList<>();
        try {
            NodeList nodeList = document.getElementsByTagName(Define.XML_DATA);
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element data = (Element) node;
                    vbsp.ims.model.Branch branch = new vbsp.ims.model.Branch();
                    branch.setPos_cd(data.getAttribute(
                            vbsp.ims.define.Constant.dmpos_constant._POS_CD));
                    branch.setPos_name(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmpos_constant._POS_NAME
                    ).item(0).getTextContent());
                    branch.setPos_address(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmpos_constant._POS_ADDRESS
                    ).item(0).getTextContent());
                    branch.setPos_fax(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmpos_constant._POS_FAX
                    ).item(0).getTextContent());
                    branch.setPos_mobile(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmpos_constant._POS_MOBILE
                    ).item(0).getTextContent());
                    branch.setMain_pos(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmpos_constant._MAIN_POS
                    ).item(0).getTextContent());
                    branch.setStatus(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmpos_constant._STATUS
                    ).item(0).getTextContent());
                    branch.setMaker_id(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmpos_constant._MAKER_ID
                    ).item(0).getTextContent());
                    branch.setMaker_dt(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmpos_constant._MAKER_DT
                    ).item(0).getTextContent());
                    branchs.add(branch);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataElement_Dmpos -> " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return branchs;
    }

    public boolean createFileXML_Dmto(String sync_pos_cd, List<Group> groups, String file_name) throws IOException {
        boolean bSuccess = true;
        String strSysDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date());
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            //Tao root node cho file xml
            Element rootElement = document.createElement(Define.XML_ROOT_NOTE);
            document.appendChild(rootElement);
            //Add cho phan key la loai report cho nay se add la  <IMS_REPORT_DATA TYPE="03">
            //...</IMS_REPORT_DATA>
            Element dataElement = addNodeWithKey(document, rootElement, Define.XML_MAIN_DATA_NOTE,
                    Define.XML_REPORT_TYPE, Define.PARA_SYN_REPORT_DMTO);
            //Dinh nghia cho thanh phan data
//            //Add cho userid
//            addNode(document, dataElement, Define.XML_USER_ID, sUserId);
//            //Add cho cap bao cao
//            addNode(document, dataElement, Define.XML_GRADE, sGrade);
            //Add data cho Pos cd
            addNode(document, dataElement, Define.XML_POS_CD, sync_pos_cd);

            //Add data cho cap bao cao            
            //Add them ngay tao du lieu cho bao cao
            addNode(document, dataElement, Define.XML_SYSDATE, strSysDate);

            //Add du lieu chinh add nhu sau
//            <IMS_DATA soku="6000002500006092">
//                    <duno>1500000</duno>
//                    <lai>1176043</lai>
//            </IMS_DATA>
            for (vbsp.ims.model.Group value : groups) {
                Element elementData = addNodeWithKey(document, dataElement, Define.XML_DATA,
                        vbsp.ims.define.Constant.dmto_constant._TO_MATO, value.getGroup_id());
                addNode(document, elementData,
                        vbsp.ims.define.Constant.dmto_constant._TO_MAPGD, value.getPos_cd());
                addNode(document, elementData,
                        vbsp.ims.define.Constant.dmto_constant._TO_TLDUNGQD, value.getStandard_flg());
                addNode(document, elementData,
                        vbsp.ims.define.Constant.dmto_constant._TO_THAMOCD, value.getCorrupt_flg());
                addNode(document, elementData,
                        vbsp.ims.define.Constant.dmto_constant._MAKER_ID, value.getMaker_id().trim() + "-FW");
                addNode(document, elementData,
                        vbsp.ims.define.Constant.dmto_constant._MAKER_DT, strSysDate);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();

            DOMSource domSource = new DOMSource(document);

//            StreamResult streamResult;
//            streamResult = new StreamResult(new File(file_name));
//            transformer.transform(domSource, streamResult);
            Writer writer = null;
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file_name), "UTF8"));
            StreamResult streamResult = new StreamResult(writer);
            transformer.transform(domSource, streamResult);
            if (writer != null) {
                writer.close();
            }
            bSuccess = true;
        } catch (ParserConfigurationException | DOMException | TransformerException tex) {
            CoreLogger.error(this.getClass().getCanonicalName() + " createFileXML_Dmto -> " + tex.getMessage());
            System.err.println(tex.getMessage());
        }
        return bSuccess;
    }

    public HashMap<String, Object> readXml_Dmto(String file_name) {

        HashMap<String, Object> hmParaXml = new HashMap<>();
        try {
            File xmlFile = new File(file_name);
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName(Define.XML_MAIN_DATA_NOTE);
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementImsRptData = (Element) node;
                    hmParaXml.put(Define.XML_REPORT_TYPE, elementImsRptData.getAttribute(
                            Define.XML_REPORT_TYPE));
                    hmParaXml.put(Define.XML_POS_CD, elementImsRptData.getElementsByTagName(
                            Define.XML_POS_CD).item(0).getTextContent());
                    hmParaXml.put(Define.XML_SYSDATE, elementImsRptData.getElementsByTagName(
                            Define.XML_SYSDATE).item(0).getTextContent());
                }
            }
            Object obj = getDataElement_Dmto(document);
            hmParaXml.put(Define.XML_DATA, obj);
        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " readXml_Dmto -> " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return hmParaXml;

    }

    private Object getDataElement_Dmto(Document document) {
        List<vbsp.ims.model.Group> groups = new ArrayList<>();
        try {
            NodeList nodeList = document.getElementsByTagName(Define.XML_DATA);
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element data = (Element) node;
                    vbsp.ims.model.Group group = new vbsp.ims.model.Group();
                    group.setGroup_id(data.getAttribute(
                            vbsp.ims.define.Constant.dmto_constant._TO_MATO));
                    group.setPos_cd(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmto_constant._TO_MAPGD
                    ).item(0).getTextContent());
                    group.setStandard_flg(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmto_constant._TO_TLDUNGQD
                    ).item(0).getTextContent());
                    group.setCorrupt_flg(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmto_constant._TO_THAMOCD
                    ).item(0).getTextContent());
                    group.setMaker_id(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmxa_constant._MAKER_ID
                    ).item(0).getTextContent());
                    group.setMaker_dt(data.getElementsByTagName(
                            vbsp.ims.define.Constant.dmxa_constant._MAKER_DT
                    ).item(0).getTextContent());
                    groups.add(group);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataElement_Dmto -> " + e.getMessage());
            System.err.println(e.getMessage());
        }
        return groups;
    }

    public static void main(String[] args) {
        String sFile = "G:\\P0615_BGD_DCPTNO_LOCK_000615_1445488066361.xml";
        new ImsReadWriteXmlFile().getReportTypeForFileXml(sFile);
    }
}
