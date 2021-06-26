/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.web.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import vbsp.ims.bcqt.dao.DaoSyncMain;
import vbsp.ims.dao.DaoCommuneInput;
import vbsp.ims.define.Define;
import vbsp.ims.khnv2021.dao.XDKHDao2021;
import vbsp.ims.ktgs.dao.DaoKtgsMain;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.ImsReadWriteXmlFile;
import vbsp.ims.xml.InputBranchXml;
import vbsp.ims.xml.XmlBcqtSync;

/**
 *
 * @author LION
 */
@WebService(serviceName = "ims_services")
@Stateless()
public class ims_services {

    /**
     * Web service operation
     *
     * @param inbyte
     * @param strFileName
     * @return
     */
    @WebMethod(operationName = "ReceivesXmlFile")
    public String ReceivesXmlFile(@WebParam(name = "inbyte") byte[] inbyte,
            @WebParam(name = "strFileName") String strFileName) {
        //TODO write your implementation code here:
        String strFulPathFile = "";
        try {
            System.err.println("Vao day ReceivesXmlFile");
            //Lay ra duong dan url toi package cua lop
            URL url = this.getClass().getResource("/vbsp/ims/web/services/");

            //Convert ve duong dan file
            File file = new File(url.toURI());

            //Lay ra duong dan tu file
            String strPathRoot = file.getPath();

            //Cat duong dan lay ve thu muc root
            strPathRoot = strPathRoot.substring(0, strPathRoot.indexOf("WEB-INF"));

            //Cong them duong dan xml vao
            String strPath = strPathRoot + Define.M_REPORT_XML + "Webservices/";
            strFulPathFile = strPath + strFileName;
//            System.err.println(strPath);
            File Checkpath = new File(strPath);
            //kiem tra neu chua co thu muc thi tao thu muc
            if (!Checkpath.exists()) {
                System.out.println("Da tao thu muc: " + strPath);
                Checkpath.mkdirs();
            }

            //Ghi file vao thu muc
            FileOutputStream fos = new FileOutputStream(strFulPathFile);
            fos.write(inbyte);
            fos.close();
            //Doan nay xu ly cho file xml. doc file va update vao db tuong ung voi cac mau bao cao
            File filecheck = new File(strFulPathFile);

            if (!filecheck.exists()) {
                CoreLogger.error(ims_services.class.getCanonicalName() + " ReceivesXmlFile -> Khong tao duoc file " + strFulPathFile);
                return Define.WEB_SERVICES_STATUS_FAIL;
            }
//            System.err.println("File du lieu -----------------------  "+strFulPathFile);
            ProcessReportSyn updateSyn = new ProcessReportSyn();
            /*
             QuyenNV them phan nay cho xu ly theo vb819
             */
            ImsReadWriteXmlFile readFileXML = new ImsReadWriteXmlFile();
            String strParaType = readFileXML.getReportTypeForFileXml(strFulPathFile);
//            String sPoscd = readFileXML.getPoscdFromFileXml(strFulPathFile);

            switch (strParaType) {
                case Define.PARA_SYN_REPORT_KTNB:
                    break;
                case Define.PARA_SYN_REPORT_KHNV:
                    break;
                case Define.PARA_SYN_REPORT_KHTD:
                    break;
                case Define.PARA_SYN_REPORT_XLRR: {
                    if (readFileXML.getPosSendData(strFulPathFile) > 0) {
                        return Define.WEB_SERVICES_STATUS_SEND;
                    }
                }
                break;
                case Define.PARA_SYN_REPORT_VB819: {
                    int icount = new DaoCommuneInput().getCountsResend(strFileName.split("_")[2], strFileName.split("_")[3]);
                    if (icount > 0) {
                        return Define.WEB_SERVICES_STATUS_SEND;
                    }
                }
                break;
                case Define.PARA_SYN_REPORT_KTGS: {
                    HashMap<String, Object> hmHeader = new XmlBcqtSync().readXmlBCQT(strFulPathFile);
                    int icount = new DaoKtgsMain().getPosSendDataLockKtgs(hmHeader.get(Define.XML_TYPE_BCQT).toString(),
                            hmHeader.get(Define.XML_MA_BCQT).toString(),
                            hmHeader.get(Define.XML_POS_CD).toString(),
                            hmHeader.get(Define.XML_NGAY_BC).toString(),
                            Define.WEB_SERVICES_STATUS_SEND);
                    if (icount > 0) {
                        return Define.WEB_SERVICES_STATUS_SEND;
                    }
                }
                
                case Define.PARA_SYN_REPORT_KHNV2021: {
                    HashMap<String, Object> hmHeader = new XmlBcqtSync().readXmlBCQT(strFulPathFile);
                    int icount = new XDKHDao2021().getPosSendDataLockKHNV(hmHeader.get(Define.XML_TYPE_BCQT).toString(),
                            hmHeader.get(Define.XML_MA_BCQT).toString(),
                            hmHeader.get(Define.XML_POS_CD).toString(),
                            hmHeader.get(Define.XML_NGAY_BC).toString(),
                            Define.WEB_SERVICES_STATUS_SEND);
                    if (icount > 0) {
                        return Define.WEB_SERVICES_STATUS_SEND;
                    }
                }
                
                break;
                case Define.PARA_SYN_REPORT_DATA:
                    break;
                case Define.PARA_SYN_REPORT_DMXA:
                    break;
                case Define.PARA_SYN_REPORT_DMTO:
                    break;
                case Define.PARA_SYN_REPORT_DMPOS:
                    break;
                case Define.PARA_SYN_REPORT_DCPT:
                    break;
                case Define.PARA_SYN_REPORT_DCPT_LOCK: {
                    if (readFileXML.getPosSendDataLock(strFulPathFile) > 0) {
                        return Define.WEB_SERVICES_STATUS_SEND;
                    }
                }
                break;
                case Define.PARA_SYN_REPORT_LOVELEAF:
                    break;
                case Define.PARA_SYN_REPORT_BCQT: {
                    HashMap<String, Object> hmHeader = new XmlBcqtSync().readXmlBCQT(strFulPathFile);
                    DaoSyncMain daoSync = DaoSyncMain.newInstance();
                    if (daoSync.getPosSendDataLock(hmHeader.get(Define.XML_TYPE_BCQT).toString(),
                            hmHeader.get(Define.XML_MA_BCQT).toString(),
                            hmHeader.get(Define.XML_POS_CD).toString(),
                            hmHeader.get(Define.XML_NGAY_BC).toString(),
                            Define.WEB_SERVICES_STATUS_SEND) > 0) {
                        return Define.WEB_SERVICES_STATUS_SEND;
                    }
                }
                break;
                case Define.PARA_SYN_REPORT_MUASAMTS: {
                    int icount = new DaoNhaptaycnMain().getCheckSendData(strFileName);
                    if (icount > 0) {
                        return Define.WEB_SERVICES_STATUS_SEND;
                    }
                }
                break;
                
//                 case Define.PARA_SYN_INPUT_BRANCH: {
//                    HashMap<String, Object> hmHeader = new InputBranchXml().readXml(strFulPathFile);
//                    DaoSyncMain daoSync = DaoSyncMain.newInstance();
//                    if (daoSync.getPosSendDataLock(hmHeader.get(Define.XML_TYPE_BCQT).toString(),
//                            hmHeader.get(Define.XML_MA_BCQT).toString(),
//                            hmHeader.get(Define.XML_POS_CD).toString(),
//                            hmHeader.get(Define.XML_NGAY_BC).toString(),
//                            Define.WEB_SERVICES_STATUS_SEND) > 0) {
//                        return Define.WEB_SERVICES_STATUS_SEND;
//                    }
//                }
//                break;
                
                case Define.PARA_SYN_REPORT_KHAC:
                    break;
                default:
                    break;
            }

            boolean bSuccess = updateSyn.UpdateSynReport(strFulPathFile);
            if (!bSuccess) {
                return Define.WEB_SERVICES_STATUS_FAIL;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ims_services.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(ims_services.class.getCanonicalName() + " ReceivesXmlFile -> strFulPathFile=" + strFulPathFile + " " + ex.getMessage());
            System.err.println("Loi nhan file strFulPathFile=" + strFulPathFile);
            return Define.WEB_SERVICES_STATUS_FAIL;
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(ims_services.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error(ims_services.class.getCanonicalName() + " ReceivesXmlFile -> strFulPathFile=" + strFulPathFile + " " + ex.getMessage());
            System.err.println("Loi nhan file strFulPathFile=" + strFulPathFile);
            return Define.WEB_SERVICES_STATUS_FAIL;
        } catch (Exception e) {
            CoreLogger.error(ims_services.class.getCanonicalName() + " ReceivesXmlFile -> strFulPathFile=" + strFulPathFile + " " + e.getMessage());
            //System.err.println("Loi nhan file strFulPathFile=" + strFulPathFile);
            return Define.WEB_SERVICES_STATUS_FAIL;
        }

        return Define.WEB_SERVICES_STATUS_OK;
    }
}
