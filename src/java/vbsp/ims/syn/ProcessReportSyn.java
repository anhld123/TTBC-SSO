/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.syn;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import vbsp.ims.bcqt.dao.DaoSyncMain;
import vbsp.ims.bctccic.daoBCTCCIC;
import vbsp.ims.branch.DaoInputBranchSync;
import vbsp.ims.chamdiemtt.dao.DaoChamdiemttMain;
import vbsp.ims.dao.DaoBCNT;
import vbsp.ims.dao.DaoCreditPlan;
import vbsp.ims.dao.DaoDCPLNO;
import vbsp.ims.dao.DaoDcptNo;
import vbsp.ims.dao.DaoLoveLeaf;
import vbsp.ims.dao.DaoProcessRisk;
import vbsp.ims.dao.DaoProcessVb819;
import vbsp.ims.dao.khnv.DaoDieuchinhkh;
import vbsp.ims.dao.khnv.DaoGiaokh;
import vbsp.ims.dao.khnv.DaoXdkh;
import vbsp.ims.dao.ktnb.*;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.Branch;
import vbsp.ims.model.DcplnModel;
import vbsp.ims.model.DcptNoModel;
import vbsp.ims.model.Group;
import vbsp.ims.model.LoveLeafModel;
import vbsp.ims.model.ModelCommune;
import vbsp.ims.model.ModelRiskProcess;
import vbsp.ims.web.services.ImsServices;
import vbsp.ims.web.services.ImsServices_Service;
import vbsp.ims.xml.ImsException;
import vbsp.ims.xml.ImsReadWriteXmlFile;
import vbsp.ims.xml.InputBranchXml;
import vbsp.ims.xml.XmlBcqtSync;
import vbsp.ims.xml.XmlChuongtrinhLoaitru;
import vbsp.ims.xml.XmlDcpln;
import vbsp.ims.xml.XmlDcptNo;
import vbsp.ims.xml.XmlLoveLeaf;
import vbsp.ims.xml.XmlSbvSync;

/**
 *
 * @author LION
 */
public class ProcessReportSyn {

    /**
     * Hàm này thực hiện việc update dữ liệu từ client (chi nhánh gửi về) Mảng
     * HashMap được đọc ra từ file xml chi nhánh gửi về
     *
     * @param strFileName
     * @param hmParaRpt
     * @return
     */
    public boolean UpdateSynReport(String strFileName) {
        boolean bSuccess;
        try {
            ImsReadWriteXmlFile readFileXML = new ImsReadWriteXmlFile();
            HashMap<String, String> hmParaRpt;
            //Lay ra loai bao cao 01,02,03...
            String strParaType = readFileXML.getReportTypeForFileXml(strFileName);

            CoreLogger.debug(" -------------------> " + strFileName);

            switch (strParaType) {
                case Define.PARA_SYN_REPORT_KTNB:
                    hmParaRpt = readFileXML.readFileXMLBCNT(strFileName);
                    System.err.println("Bao cao ktnb");
                    bSuccess = UpdateDataBCNTKTNB(hmParaRpt);
                    break;
                case Define.PARA_SYN_REPORT_KHNV:
                    hmParaRpt = readFileXML.readFileXMLKHNV(strFileName);
                    System.err.println("Bao cao KHVN");
                    bSuccess = UpdateDataKHNV(hmParaRpt);
                    break;
                case Define.PARA_SYN_REPORT_KHTD:
                    hmParaRpt = readFileXML.readFileXML(strFileName);
                    System.err.println("Bao cao KHTD");
                    bSuccess = UpdateDataKHTD(hmParaRpt);
                    break;
                case Define.PARA_SYN_REPORT_XLRR:
                    HashMap<String, Object> hmObj = readFileXML.readXmlRisk(strFileName);
                    System.err.println("Bao cao XLRR");
                    bSuccess = UpdateDataXlRR(hmObj);
                    break;
                case Define.PARA_SYN_REPORT_VB819:
                    HashMap<String, Object> hmObj819 = readFileXML.readXmlVB819(strFileName);
                    System.err.println("Bao cao XLRR");
                    bSuccess = UpdateData819(hmObj819);
                    break;
                case Define.PARA_SYN_REPORT_DATA:
                    System.err.println("Bao cao SynData");
                    bSuccess = true;
                    break;

                case Define.PARA_SYN_REPORT_DMXA:
                    System.err.println("Danh mục xã");
                    HashMap<String, Object> hmObj_dmxa = readFileXML.readXml_Dmxa(strFileName);
                    bSuccess = UpdateData_Dmxa(hmObj_dmxa);
                    break;
                case Define.PARA_SYN_REPORT_DMTO:
                    System.err.println("Danh mục tổ");
                    HashMap<String, Object> hmObj_dmto = readFileXML.readXml_Dmto(strFileName);
                    bSuccess = UpdateData_Dmto(hmObj_dmto);
                    break;
                case Define.PARA_SYN_REPORT_DMPOS:
                    System.err.println("Danh mục POS");
                    HashMap<String, Object> hmObj_dmpos = readFileXML.readXml_DmPos(strFileName);
                    bSuccess = UpdateData_Dmpos(hmObj_dmpos);
                    break;
                case Define.PARA_SYN_REPORT_DCPT:
                    System.err.println("Nhan du lieu dcpt");
//                    HashMap<String, Object> hmObjDcpt = new XmlDcptNo().readXmlDcPt(strFileName);
//                    bSuccess = UpdateDataDcPt(hmObjDcpt);
                    bSuccess = false;
                    break;
                case Define.PARA_SYN_REPORT_PLNO:
                    System.err.println("Nhan du lieu pln");
                    HashMap<String, Object> hmObjPln = new XmlDcpln().readXmlPln(strFileName);
                    bSuccess = UpdateDataPlno(hmObjPln);
                    break;
                case Define.PARA_SYN_REPORT_DCPT_LOCK:
                    System.err.println("Khoa du lieu ptdc");
                    HashMap<String, Object> hmObjDcptLock = new XmlDcptNo().readXmlDcPtLock(strFileName);
                    bSuccess = UpdateDataDcPtLock(hmObjDcptLock);
                    break;
                case Define.PARA_SYN_REPORT_LOVELEAF:
                    System.err.println("Nhan du lieu dcpt");
                    HashMap<String, Object> hmObjLove = new XmlLoveLeaf().readXmlLoveLeaf(strFileName);
                    bSuccess = UpdateDataLoveLeaf(hmObjLove);
                    break;
                case Define.PARA_SYN_REPORT_BCQT: {
                    HashMap<String, Object> hmHeader = new XmlBcqtSync().readXmlBCQT(strFileName);
                    DaoSyncMain daoSync = DaoSyncMain.newInstance();
                    bSuccess = daoSync.putXmlFile(strFileName, hmHeader.get(Define.XML_MA_BCQT).toString(),
                            hmHeader.get(Define.XML_TYPE_BCQT).toString(),
                            hmHeader.get(Define.XML_POS_CD).toString(), hmHeader.get(Define.XML_NGAY_BC).toString(),
                            hmHeader.get(Define.XML_GRADE).toString(), hmHeader.get(Define.XML_USER_ID).toString(), hmHeader.get(Define.XML_SYSDATE).toString(),
                            hmHeader.get(Define.WEB_SERVICES_STATUS_SEND).toString());

                }
                break;
                case Define.PARA_SYN_REPORT_SBV: {
                    HashMap<String, Object> hmHeader = new XmlSbvSync().readXmlSBV(strFileName);
                    DaoSyncMain daoSync = DaoSyncMain.newInstance();
                    bSuccess = daoSync.putXmlFileSbv(strFileName, hmHeader.get(Define.XML_MA_BCQT).toString(),
                            hmHeader.get(Define.XML_TYPE_BCQT).toString(),
                            hmHeader.get(Define.XML_POS_CD).toString(), hmHeader.get(Define.XML_NGAY_BC).toString(),
                            hmHeader.get(Define.XML_GRADE).toString(), hmHeader.get(Define.XML_USER_ID).toString(), hmHeader.get(Define.XML_SYSDATE).toString(),
                            hmHeader.get(Define.WEB_SERVICES_STATUS_SEND).toString());
                    break;
                }
                case Define.PARA_SYN_REPORT_KTNB_ADD: {
                    HashMap<String, Object> hmHeader = new XmlSbvSync().readXmlSBV(strFileName);
                    DaoKTNBMain daoSync = DaoKTNBMain.newInstance();
                    bSuccess = daoSync.putXmlFileKtnb(strFileName, hmHeader.get(Define.XML_MA_BCQT).toString(),
                            hmHeader.get(Define.XML_TYPE_BCQT).toString(),
                            hmHeader.get(Define.XML_POS_CD).toString(), hmHeader.get(Define.XML_NGAY_BC).toString(),
                            hmHeader.get(Define.XML_GRADE).toString(), hmHeader.get(Define.XML_USER_ID).toString(), hmHeader.get(Define.XML_SYSDATE).toString(),
                            hmHeader.get(Define.WEB_SERVICES_STATUS_SEND).toString());
                    break;
                }
                case Define.PARA_SYN_REPORT_KTGS: {
                    System.err.println("Bao cao KTGS");
                    HashMap<String, Object> hmHeader = new XmlSbvSync().readXmlSBV(strFileName);
                    DaoSyncMain daoSync = DaoSyncMain.newInstance();
                    bSuccess = daoSync.putXmlFileKtgs(strFileName, hmHeader.get(Define.XML_MA_BCQT).toString(),
                            hmHeader.get(Define.XML_TYPE_BCQT).toString(),
                            hmHeader.get(Define.XML_POS_CD).toString(), hmHeader.get(Define.XML_NGAY_BC).toString(),
                            hmHeader.get(Define.XML_GRADE).toString(), hmHeader.get(Define.XML_USER_ID).toString(), hmHeader.get(Define.XML_SYSDATE).toString(),
                            hmHeader.get(Define.WEB_SERVICES_STATUS_SEND).toString());
                    break;
                }  
                case Define.PARA_SYN_REPORT_KHNV2021: {
                    System.err.println("Bao cao KHNV2021");
                    HashMap<String, Object> hmHeader = new XmlSbvSync().readXmlSBV(strFileName);
                    DaoSyncMain daoSync = DaoSyncMain.newInstance();
                    bSuccess = daoSync.putXmlFileKHNV2021(strFileName, hmHeader.get(Define.XML_MA_BCQT).toString(),
                            hmHeader.get(Define.XML_TYPE_BCQT).toString(),
                            hmHeader.get(Define.XML_POS_CD).toString(), hmHeader.get(Define.XML_NGAY_BC).toString(),
                            hmHeader.get(Define.XML_GRADE).toString(), hmHeader.get(Define.XML_USER_ID).toString(), hmHeader.get(Define.XML_SYSDATE).toString(),
                            hmHeader.get(Define.WEB_SERVICES_STATUS_SEND).toString());
                    break;
                }  
                
                case Define.PARA_SYN_REPORT_CBSS: {
                    System.err.println("Bao cao CBSS");
                    HashMap<String, Object> hmHeader = new XmlSbvSync().readXmlSBV(strFileName);
                    DaoSyncMain daoSync = DaoSyncMain.newInstance();
                    bSuccess = daoSync.putXmlFileCbss(strFileName, hmHeader.get(Define.XML_MA_BCQT).toString(),
                            hmHeader.get(Define.XML_TYPE_BCQT).toString(),
                            hmHeader.get(Define.XML_POS_CD).toString(), hmHeader.get(Define.XML_NGAY_BC).toString(),
                            hmHeader.get(Define.XML_GRADE).toString(), hmHeader.get(Define.XML_USER_ID).toString(), hmHeader.get(Define.XML_SYSDATE).toString(),
                            hmHeader.get(Define.WEB_SERVICES_STATUS_SEND).toString());
                    break;
                }  
                
                case Define.PARA_SYN_REPORT_MUASAMTS: {
                    System.err.println("Bao cao Muasamts");
                    HashMap<String, Object> hmHeader = new XmlSbvSync().readXmlSBV(strFileName);
                    DaoSyncMain daoSync = DaoSyncMain.newInstance();
                    bSuccess = daoSync.putXmlFileMuasamts(strFileName, hmHeader.get(Define.XML_MA_BCQT).toString(),
                            hmHeader.get(Define.XML_TYPE_BCQT).toString(),
                            hmHeader.get(Define.XML_POS_CD).toString(), hmHeader.get(Define.XML_NGAY_BC).toString(),
                            hmHeader.get(Define.XML_GRADE).toString(), hmHeader.get(Define.XML_USER_ID).toString(), hmHeader.get(Define.XML_SYSDATE).toString(),
                            hmHeader.get(Define.WEB_SERVICES_STATUS_SEND).toString());
                    break;
                }    
                
                case Define.PARA_SYN_REPORT_CDTT: {
                    System.err.println("Bao cao CDTT");
                    HashMap<String, Object> hmHeader = new XmlSbvSync().readXmlSBV(strFileName);
                    DaoChamdiemttMain daoSync = DaoChamdiemttMain.newInstance();
                    bSuccess = daoSync.putXmlFileCDTT(strFileName, hmHeader.get(Define.XML_MA_BCQT).toString(),
                            hmHeader.get(Define.XML_TYPE_BCQT).toString(),
                            hmHeader.get(Define.XML_POS_CD).toString(), hmHeader.get(Define.XML_NGAY_BC).toString(),
                            hmHeader.get(Define.XML_GRADE).toString(), hmHeader.get(Define.XML_USER_ID).toString(), hmHeader.get(Define.XML_SYSDATE).toString(),
                            hmHeader.get(Define.WEB_SERVICES_STATUS_SEND).toString());
                    break;
                }
                case Define.PARA_SYN_REPORT_PHIUT: {
                    System.err.println("Bao cao PHIUT");
                    HashMap<String, Object> hmHeader = new XmlSbvSync().readXmlSBV(strFileName);
                    DaoSyncMain daoSync = DaoSyncMain.newInstance();
                    bSuccess = daoSync.putXmlFilePhiUt(strFileName, hmHeader.get(Define.XML_MA_BCQT).toString(),
                            hmHeader.get(Define.XML_TYPE_BCQT).toString(),
                            hmHeader.get(Define.XML_POS_CD).toString(), hmHeader.get(Define.XML_NGAY_BC).toString(),
                            hmHeader.get(Define.XML_GRADE).toString(), hmHeader.get(Define.XML_USER_ID).toString(), hmHeader.get(Define.XML_SYSDATE).toString(),
                            hmHeader.get(Define.WEB_SERVICES_STATUS_SEND).toString());
                    break;
                }
                case Define.PARA_SYN_REPORT_TDNN: {
                    System.err.println("Bao cao TDNN");
                    HashMap<String, Object> hmHeader = new XmlSbvSync().readXmlSBV(strFileName);
                    DaoSyncMain daoSync = DaoSyncMain.newInstance();
                    bSuccess = daoSync.putXmlFileTdnn(strFileName, hmHeader.get(Define.XML_MA_BCQT).toString(),
                            hmHeader.get(Define.XML_TYPE_BCQT).toString(),
                            hmHeader.get(Define.XML_POS_CD).toString(), hmHeader.get(Define.XML_NGAY_BC).toString(),
                            hmHeader.get(Define.XML_GRADE).toString(), hmHeader.get(Define.XML_USER_ID).toString(), hmHeader.get(Define.XML_SYSDATE).toString(),
                            hmHeader.get(Define.WEB_SERVICES_STATUS_SEND).toString());
                    break;
                }
                case Define.PARA_SYN_REPORT_PHTS: {
                    System.err.println("Bao cao PHTS");
                    HashMap<String, Object> hmHeader = new XmlSbvSync().readXmlSBV(strFileName);
                    DaoSyncMain daoSync = DaoSyncMain.newInstance();
                    bSuccess = daoSync.putXmlFilePhts(strFileName, hmHeader.get(Define.XML_MA_BCQT).toString(),
                            hmHeader.get(Define.XML_TYPE_BCQT).toString(),
                            hmHeader.get(Define.XML_POS_CD).toString(), hmHeader.get(Define.XML_NGAY_BC).toString(),
                            hmHeader.get(Define.XML_GRADE).toString(), hmHeader.get(Define.XML_USER_ID).toString(), hmHeader.get(Define.XML_SYSDATE).toString(),
                            hmHeader.get(Define.WEB_SERVICES_STATUS_SEND).toString());
                    break;
                }
                case Define.PARA_SYN_REPORT_KHOANTC: {
                    System.err.println("Bao cao KHOANTC");
                    HashMap<String, Object> hmHeader = new XmlSbvSync().readXmlSBV(strFileName);
                    DaoSyncMain daoSync = DaoSyncMain.newInstance();
                    bSuccess = daoSync.putXmlFileKhoantc(strFileName, hmHeader.get(Define.XML_MA_BCQT).toString(),
                            hmHeader.get(Define.XML_TYPE_BCQT).toString(),
                            hmHeader.get(Define.XML_POS_CD).toString(), hmHeader.get(Define.XML_NGAY_BC).toString(),
                            hmHeader.get(Define.XML_GRADE).toString(), hmHeader.get(Define.XML_USER_ID).toString(), hmHeader.get(Define.XML_SYSDATE).toString(),
                            hmHeader.get(Define.WEB_SERVICES_STATUS_SEND).toString());
                    break;
                }
                case Define.PARA_SYN_REPORT_CIC: {
                    System.err.println("Bao cao CIC");
                    HashMap<String, Object> hmHeader = new XmlSbvSync().readXmlSBV(strFileName);
                    daoBCTCCIC daoSync = new daoBCTCCIC();
                    bSuccess = daoSync.putXmlFileCIC(strFileName, hmHeader.get(Define.XML_MA_BCQT).toString(),
                            hmHeader.get(Define.XML_TYPE_BCQT).toString(),
                            hmHeader.get(Define.XML_POS_CD).toString(), hmHeader.get(Define.XML_NGAY_BC).toString(),
                            hmHeader.get(Define.XML_GRADE).toString(), hmHeader.get(Define.XML_USER_ID).toString(), hmHeader.get(Define.XML_SYSDATE).toString(),
                            hmHeader.get(Define.WEB_SERVICES_STATUS_SEND).toString());
                    break;
                }
                 case Define.PARA_SYN_REPORT_CTLT: {
                    System.err.println("Bao cao Chuong trinh loai tru");
                    HashMap<String, Object> hmHeader = new XmlChuongtrinhLoaitru().readXmlBCQT(strFileName);
                    DaoChamdiemttMain daoSync = new DaoChamdiemttMain();
                    bSuccess = daoSync.putXmlFileCTLT(strFileName, 
                            hmHeader.get(Define.XML_POS_CD).toString(), hmHeader.get(Define.XML_NGAY_BC).toString(),
                            hmHeader.get(Define.XML_GRADE).toString(), hmHeader.get(Define.XML_USER_ID).toString(), hmHeader.get(Define.XML_SYSDATE).toString(),
                            Define.WEB_SERVICES_STATUS_SEND);
                    break;
                }
                 case Define.PARA_SYN_INPUT_BRANCH: {
                    HashMap<String, Object> hmHeader = new InputBranchXml().readXml(strFileName);
                     DaoInputBranchSync daoSync = DaoInputBranchSync.newInstance();
                    bSuccess = daoSync.putXmlFile(strFileName, hmHeader.get(Define.XML_MA_BCQT).toString(),
//                            hmHeader.get(Define.XML_TYPE_BCQT).toString(),
//                            hmHeader.get(Define.XML_POS_CD).toString(), hmHeader.get(Define.XML_NGAY_BC).toString(),
                            hmHeader.get(Define.XML_GRADE).toString(), hmHeader.get(Define.XML_USER_ID).toString(), hmHeader.get(Define.XML_SYSDATE).toString(),
                            hmHeader.get(Define.WEB_SERVICES_STATUS_SEND).toString());

                }
                break;
                case Define.PARA_SYN_REPORT_KHAC:
                    System.err.println("Bao cao Khac");
                    bSuccess = true;
                    break;
                default:
                    System.err.println("Bao cao khong thuoc loai nao ca");
                    bSuccess = true;
                    break;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " UpdateSynReport " + e.getMessage());
            bSuccess = false;
            throw new ImsException("Lỗi trong hàm UpdateSynReport File=" + strFileName, e);
        }

        return bSuccess;
    }

    //<editor-fold defaultstate="collapsed" desc="KTKTNB">
    public boolean updatebcnt01(HashMap<String, String> hmDatarpt) {
        boolean bSuccess = false;
        if (hmDatarpt.isEmpty()) {
            return bSuccess;
        }
        try {
            DaoKtnb01 daoktnb01 = new DaoKtnb01();
            String sReportId = hmDatarpt.get(Define.XML_REPORT_ID); //id cho bao cao
            String sReportDate = hmDatarpt.get(Define.XML_REPORT_DATE);//ngay bao cao
            String sUserId = hmDatarpt.get(Define.XML_USER_ID);//user id
            String sPosCd = hmDatarpt.get(Define.XML_POS_CD);//ma pgd
            String sMaCN = hmDatarpt.get(Define.XML_MA_CN);//ma CN
            String sGrade = hmDatarpt.get(Define.XML_GRADE);//cap bao cao
            String sFlagData = hmDatarpt.get(Define.XML_FLAG_DATA);//co cho bao cao khtd
//            String sDataReport = hmDatarpt.get(Define.XML_DATA);//du lieu update cho bao cao
            ArrayList<String> KT_STT_HT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_1"), "#");
            ArrayList<String> KT_DKT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_2"), "#");
            ArrayList<String> KT_SLT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_3"), "#");
            ArrayList<String> KT_SLH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_4"), "#");
            ArrayList<String> KT_SL_DGD = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_5"), "#");
            ArrayList<String> KT_SL_TKVV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_6"), "#");
            ArrayList<String> KT_DN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_7"), "#");
            ArrayList<String> KT_CO_DINH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_8"), "#");
            ArrayList<String> KT_THEM = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_9"), "#");
            ArrayList<String> KT_XOA = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_10"), "#");
            ArrayList<String> KT_FONTWEIGHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_11"), "#");
            ArrayList<String> KT_CAPHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_12"), "#");
            ArrayList<String> KT_STT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_13"), "#");
            ArrayList<String> NG_CAPNHAT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_14"), "#");
            ArrayList<String> KT_KHOA = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_15"), "#");

            String sQuyBC = hmDatarpt.get(Define.XML_QUY_BC);//quy bao cao
            String sNamBC = hmDatarpt.get(Define.XML_NAM_BC);//quy bao cao
            String sSysDate = hmDatarpt.get(Define.XML_SYSDATE);//ngay gio update so lieus
            bSuccess = daoktnb01.save_ktnb01(sPosCd, sMaCN, sQuyBC, sNamBC, sUserId, KT_KHOA, KT_SLT, KT_SLH, KT_SL_DGD, KT_SL_TKVV, "W");
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " updatebcnt01 Loi khi server dong bo bao cao nhap tay ve " + e.getMessage());
            return bSuccess;
        }
        return bSuccess;
    }

    public boolean updatebcnt02(HashMap<String, String> hmDatarpt) {
        boolean bSuccess = false;
        if (hmDatarpt.isEmpty()) {
            return bSuccess;
        }
        try {
            DaoKtnb02 daoktnb02 = new DaoKtnb02();
            String sReportId = hmDatarpt.get(Define.XML_REPORT_ID); //id cho bao cao
            String sReportDate = hmDatarpt.get(Define.XML_REPORT_DATE);//ngay bao cao
            String sUserId = hmDatarpt.get(Define.XML_USER_ID);//user id
            String sPosCd = hmDatarpt.get(Define.XML_POS_CD);//ma pgd
            String sMaCN = hmDatarpt.get(Define.XML_MA_CN);//ma CN
            String sGrade = hmDatarpt.get(Define.XML_GRADE);//cap bao cao
            String sFlagData = hmDatarpt.get(Define.XML_FLAG_DATA);//co cho bao cao khtd
//            String sDataReport = hmDatarpt.get(Define.XML_DATA);//du lieu update cho bao cao
            ArrayList<String> KT_STT_HT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_1"), "#");
            ArrayList<String> KT_DTVV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_2"), "#");
            ArrayList<String> KT_HS_THS_SHS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_3"), "#");
            ArrayList<String> KT_HS_THS_ST = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_4"), "#");
            ArrayList<String> KT_HS_KT_TS_SHS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_5"), "#");
            ArrayList<String> KT_HS_KT_TS_ST = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_6"), "#");
            ArrayList<String> KT_HS_KT_TL_SHS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_7"), "#");
            ArrayList<String> KT_HS_KT_TL_ST = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_8"), "#");
            ArrayList<String> KT_HS_HSS_SHS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_9"), "#");
            ArrayList<String> KT_HS_HSS_ST = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_10"), "#");
            ArrayList<String> KT_HS_TLS_SHS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_11"), "#");
            ArrayList<String> KT_HS_TLS_ST = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_12"), "#");
            ArrayList<String> KT_NQH_CC_SHS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_13"), "#");
            ArrayList<String> KT_NQH_CC_ST = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_14"), "#");
            ArrayList<String> KT_NQH_CD = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_15"), "#");
            ArrayList<String> KT_DN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_16"), "#");
            ArrayList<String> KT_CO_DINH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_17"), "#");
            ArrayList<String> KT_THEM = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_18"), "#");
            ArrayList<String> KT_XOA = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_19"), "#");
            ArrayList<String> KT_FONTWEIGHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_20"), "#");
            ArrayList<String> KT_CAPHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_21"), "#");
            ArrayList<String> KT_STT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_22"), "#");
            ArrayList<String> NG_CAPNHAT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_23"), "#");
            ArrayList<String> KT_KHOA = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_24"), "#");

            String sQuyBC = hmDatarpt.get(Define.XML_QUY_BC);//quy bao cao
            String sNamBC = hmDatarpt.get(Define.XML_NAM_BC);//quy bao cao
            String sSysDate = hmDatarpt.get(Define.XML_SYSDATE);//ngay gio update so lieu

            bSuccess = daoktnb02.save_ktnb02(sPosCd, sMaCN, sQuyBC, sNamBC, sUserId,
                    KT_KHOA, KT_HS_THS_SHS, KT_HS_THS_ST, KT_HS_KT_TS_SHS,
                    KT_HS_KT_TS_ST, KT_HS_KT_TL_SHS,
                    KT_HS_KT_TL_ST, KT_HS_HSS_SHS, KT_HS_HSS_ST,
                    KT_HS_TLS_SHS, KT_HS_TLS_ST, "W");
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " updatebcnt01 Loi khi server dong bo bao cao nhap tay ve " + e.getMessage());
            return bSuccess;
        }
        return bSuccess;
    }

    public boolean updatebcnt03(HashMap<String, String> hmDatarpt) {
        boolean bSuccess = false;
        if (hmDatarpt.isEmpty()) {
            return bSuccess;
        }
        try {
            DaoKtnb03 daoktnb03 = new DaoKtnb03();
            String sReportId = hmDatarpt.get(Define.XML_REPORT_ID); //id cho bao cao
            String sReportDate = hmDatarpt.get(Define.XML_REPORT_DATE);//ngay bao cao
            String sUserId = hmDatarpt.get(Define.XML_USER_ID);//user id
            String sPosCd = hmDatarpt.get(Define.XML_POS_CD);//ma pgd
            String sMaCN = hmDatarpt.get(Define.XML_MA_CN);//ma CN
            String sGrade = hmDatarpt.get(Define.XML_GRADE);//cap bao cao
            String sFlagData = hmDatarpt.get(Define.XML_FLAG_DATA);//co cho bao cao khtd
//            String sDataReport = hmDatarpt.get(Define.XML_DATA);//du lieu update cho bao cao
            ArrayList<String> KT_STT_HT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_1"), "#");
            ArrayList<String> KT_TIEUCHI = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_2"), "#");
            ArrayList<String> KT_SDCC_SHVV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_3"), "#");
            ArrayList<String> KT_SDCC_ST = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_4"), "#");
            ArrayList<String> KT_SDDC_SHVV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_5"), "#");
            ArrayList<String> KT_SDDC_ST = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_6"), "#");
            ArrayList<String> KT_TLDC_SHVV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_7"), "#");
            ArrayList<String> KT_TLDC_ST = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_8"), "#");
            ArrayList<String> KT_SSDC_SKH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_9"), "#");
            ArrayList<String> KT_SSDC_ST = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_10"), "#");
            ArrayList<String> KT_SSDC_TLST = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_11"), "#");
            ArrayList<String> KT_SSDC_GC = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_12"), "#");
            ArrayList<String> KT_DN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_13"), "#");
            ArrayList<String> KT_CO_DINH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_14"), "#");
            ArrayList<String> KT_THEM = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_15"), "#");
            ArrayList<String> KT_XOA = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_16"), "#");
            ArrayList<String> KT_FONTWEIGHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_17"), "#");
            ArrayList<String> KT_CAPHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_18"), "#");
            ArrayList<String> KT_STT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_19"), "#");
            ArrayList<String> NG_CAPNHAT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_20"), "#");
            ArrayList<String> KT_KHOA = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_21"), "#");

            String sQuyBC = hmDatarpt.get(Define.XML_QUY_BC);//quy bao cao
            String sNamBC = hmDatarpt.get(Define.XML_NAM_BC);//quy bao cao
            String sSysDate = hmDatarpt.get(Define.XML_SYSDATE);//ngay gio update so lieu

            bSuccess = daoktnb03.save_ktnb03(sPosCd, sMaCN, sQuyBC, sNamBC, sUserId, KT_KHOA, KT_SDCC_SHVV, KT_SDCC_ST, KT_SDDC_SHVV, KT_SDDC_ST, KT_TLDC_SHVV,
                    KT_TLDC_ST, KT_SSDC_SKH, KT_SSDC_ST, KT_SSDC_TLST, KT_SSDC_GC, "W");
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " updatebcnt01 Loi khi server dong bo bao cao nhap tay ve " + e.getMessage());
            return bSuccess;
        }
        return bSuccess;
    }

    public boolean updatebcnt04(HashMap<String, String> hmDatarpt) {
        boolean bSuccess = false;
        if (hmDatarpt.isEmpty()) {
            return bSuccess;
        }
        try {
            DaoKtnb04 daoktnb04 = new DaoKtnb04();
            String sReportId = hmDatarpt.get(Define.XML_REPORT_ID); //id cho bao cao
            String sReportDate = hmDatarpt.get(Define.XML_REPORT_DATE);//ngay bao cao
            String sUserId = hmDatarpt.get(Define.XML_USER_ID);//user id
            String sPosCd = hmDatarpt.get(Define.XML_POS_CD);//ma pgd
            String sMaCN = hmDatarpt.get(Define.XML_MA_CN);//ma CN
            String sGrade = hmDatarpt.get(Define.XML_GRADE);//cap bao cao
            String sFlagData = hmDatarpt.get(Define.XML_FLAG_DATA);//co cho bao cao khtd
//            String sDataReport = hmDatarpt.get(Define.XML_DATA);//du lieu update cho bao cao
            ArrayList<String> KT_STT_HT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_1"), "#");
            ArrayList<String> KT_LOAI_CT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_2"), "#");
            ArrayList<String> KT_KT_SCT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_3"), "#");
            ArrayList<String> KT_KT_ST = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_4"), "#");
            ArrayList<String> KT_SS_TS_SCT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_5"), "#");
            ArrayList<String> KT_SS_TS_ST = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_6"), "#");
            ArrayList<String> KT_SS_TS_TLCT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_7"), "#");
            ArrayList<String> KT_SS_TS_TLST = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_8"), "#");
            ArrayList<String> KT_TD_SPL_SCT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_9"), "#");
            ArrayList<String> KT_TD_SPL_ST = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_10"), "#");
            ArrayList<String> KT_TD_KTNV_SL = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_11"), "#");
            ArrayList<String> KT_TD_KTNV_ST = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_12"), "#");

            ArrayList<String> KT_TD_SK_SL = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_13"), "#");
            ArrayList<String> KT_TD_SK_ST = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_14"), "#");

            ArrayList<String> KT_DN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_15"), "#");
            ArrayList<String> KT_CO_DINH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_16"), "#");
            ArrayList<String> KT_THEM = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_17"), "#");
            ArrayList<String> KT_XOA = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_18"), "#");
            ArrayList<String> KT_FONTWEIGHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_19"), "#");
            ArrayList<String> KT_CAPHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_20"), "#");
            ArrayList<String> KT_STT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_21"), "#");
            ArrayList<String> NG_CAPNHAT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_22"), "#");
            ArrayList<String> KT_KHOA = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_23"), "#");

            String sQuyBC = hmDatarpt.get(Define.XML_QUY_BC);//quy bao cao
            String sNamBC = hmDatarpt.get(Define.XML_NAM_BC);//quy bao cao
            String sSysDate = hmDatarpt.get(Define.XML_SYSDATE);//ngay gio update so lieu

            bSuccess = daoktnb04.save_ktnb04(sPosCd, sMaCN, sQuyBC, sNamBC, sUserId, KT_KHOA, KT_KT_SCT, KT_KT_ST, KT_SS_TS_SCT, KT_SS_TS_ST, KT_SS_TS_TLCT, KT_SS_TS_TLST,
                    KT_TD_SPL_SCT, KT_TD_SPL_ST, KT_TD_KTNV_SL, KT_TD_KTNV_ST, KT_TD_SK_SL, KT_TD_SK_ST, "W");
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " updatebcnt01 Loi khi server dong bo bao cao nhap tay ve " + e.getMessage());
            return bSuccess;
        }
        return bSuccess;
    }

    public boolean updatebcnt06(HashMap<String, String> hmDatarpt) {
        boolean bSuccess = false;
        if (hmDatarpt.isEmpty()) {
            return bSuccess;
        }
        try {
            DaoKtnb06 daoktnb06 = new DaoKtnb06();
            String sReportId = hmDatarpt.get(Define.XML_REPORT_ID); //id cho bao cao
            String sReportDate = hmDatarpt.get(Define.XML_REPORT_DATE);//ngay bao cao
            String sUserId = hmDatarpt.get(Define.XML_USER_ID);//user id
            String sPosCd = hmDatarpt.get(Define.XML_POS_CD);//ma pgd
            String sMaCN = hmDatarpt.get(Define.XML_MA_CN);//ma CN
            String sGrade = hmDatarpt.get(Define.XML_GRADE);//cap bao cao
            String sFlagData = hmDatarpt.get(Define.XML_FLAG_DATA);//co cho bao cao khtd
//            String sDataReport = hmDatarpt.get(Define.XML_DATA);//du lieu update cho bao cao
            ArrayList<String> KT_STT_HT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_1"), "#");
            ArrayList<String> KT_DT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_2"), "#");
            ArrayList<String> KT_TDDN_SV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_3"), "#");
            ArrayList<String> KT_TDDN_ST_G = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_4"), "#");
            ArrayList<String> KT_TDDN_ST_L = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_5"), "#");
            ArrayList<String> KT_TDDN_ST_TK = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_6"), "#");
            ArrayList<String> KT_PHTK_SV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_7"), "#");
            ArrayList<String> KT_PHTK_ST_G = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_8"), "#");
            ArrayList<String> KT_PHTK_ST_L = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_9"), "#");
            ArrayList<String> KT_PHTK_ST_TK = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_10"), "#");
            ArrayList<String> KT_DTH_SV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_11"), "#");
            ArrayList<String> KT_DTH_ST_G = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_12"), "#");
            ArrayList<String> KT_DTH_ST_L = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_13"), "#");
            ArrayList<String> KT_DTH_ST_TK = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_14"), "#");
            ArrayList<String> KT_TDCK_SV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_15"), "#");
            ArrayList<String> KT_TDCK_ST_G = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_16"), "#");
            ArrayList<String> KT_TDCK_ST_L = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_17"), "#");
            ArrayList<String> KT_TDCK_ST_TK = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_18"), "#");
            ArrayList<String> KT_DN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_19"), "#");
            ArrayList<String> KT_CO_DINH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_20"), "#");
            ArrayList<String> KT_THEM = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_21"), "#");
            ArrayList<String> KT_XOA = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_22"), "#");
            ArrayList<String> KT_FONTWEIGHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_23"), "#");
            ArrayList<String> KT_CAPHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_24"), "#");
            ArrayList<String> KT_STT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_25"), "#");
            ArrayList<String> NG_CAPNHAT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_26"), "#");
            ArrayList<String> KT_KHOA = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_27"), "#");

            String sQuyBC = hmDatarpt.get(Define.XML_QUY_BC);//quy bao cao
            String sNamBC = hmDatarpt.get(Define.XML_NAM_BC);//quy bao cao
            String sSysDate = hmDatarpt.get(Define.XML_SYSDATE);//ngay gio update so lieu

            bSuccess = daoktnb06.save_ktnb06(sPosCd, sMaCN, sQuyBC, sNamBC, sUserId, KT_KHOA, KT_TDDN_SV,
                    KT_TDDN_ST_G, KT_TDDN_ST_L, KT_TDDN_ST_TK, KT_PHTK_SV, KT_PHTK_ST_G,
                    KT_PHTK_ST_L, KT_PHTK_ST_TK, KT_DTH_SV, KT_DTH_ST_G, KT_DTH_ST_L,
                    KT_DTH_ST_TK, KT_TDCK_SV, KT_TDCK_ST_G, KT_TDCK_ST_L, KT_TDCK_ST_TK, "W");
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " updatebcnt01 Loi khi server dong bo bao cao nhap tay ve " + e.getMessage());
            return bSuccess;
        }
        return bSuccess;
    }

    public boolean updatebcnt05(HashMap<String, String> hmDatarpt) {
        boolean bSuccess = false;
        if (hmDatarpt.isEmpty()) {
            return bSuccess;
        }
        try {
            DaoKtnb05 daoktnb05 = new DaoKtnb05();
            String sReportId = hmDatarpt.get(Define.XML_REPORT_ID); //id cho bao cao
            String sReportDate = hmDatarpt.get(Define.XML_REPORT_DATE);//ngay bao cao
            String sUserId = hmDatarpt.get(Define.XML_USER_ID);//user id
            String sPosCd = hmDatarpt.get(Define.XML_POS_CD);//ma pgd
            String sMaCN = hmDatarpt.get(Define.XML_MA_CN);//ma CN
            String sGrade = hmDatarpt.get(Define.XML_GRADE);//cap bao cao
            String sFlagData = hmDatarpt.get(Define.XML_FLAG_DATA);//co cho bao cao khtd
//            String sDataReport = hmDatarpt.get(Define.XML_DATA);//du lieu update cho bao cao
            ArrayList<String> KT_STT_HT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_1"), "#");
            ArrayList<String> KT_TC_UT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_2"), "#");
            ArrayList<String> KT_TONG_TO = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_3"), "#");
            ArrayList<String> KT_TONG_DUNO = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_4"), "#");
            ArrayList<String> KT_TKVV_ST = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_5"), "#");
            ArrayList<String> KT_TKVV_DN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_6"), "#");
            ArrayList<String> KT_SO_TO_TOT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_7"), "#");
            ArrayList<String> KT_DUNO_TOT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_8"), "#");
            ArrayList<String> KT_SO_TO_KHA = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_9"), "#");
            ArrayList<String> KT_DUNO_KHA = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_10"), "#");
            ArrayList<String> KT_SO_TO_TB = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_11"), "#");
            ArrayList<String> KT_DUNO_TB = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_12"), "#");
            ArrayList<String> KT_SO_TO_KEM = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_13"), "#");
            ArrayList<String> KT_DUNO_KEM = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_14"), "#");
            ArrayList<String> KT_DN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_15"), "#");
            ArrayList<String> KT_CO_DINH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_16"), "#");
            ArrayList<String> KT_THEM = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_17"), "#");
            ArrayList<String> KT_XOA = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_18"), "#");
            ArrayList<String> KT_FONTWEIGHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_19"), "#");
            ArrayList<String> KT_CAPHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_20"), "#");
            ArrayList<String> KT_STT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_21"), "#");
            ArrayList<String> NG_CAPNHAT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_22"), "#");
            ArrayList<String> KT_KHOA = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_23"), "#");

            String sQuyBC = hmDatarpt.get(Define.XML_QUY_BC);//quy bao cao
            String sNamBC = hmDatarpt.get(Define.XML_NAM_BC);//quy bao cao
            String sSysDate = hmDatarpt.get(Define.XML_SYSDATE);//ngay gio update so lieu

            bSuccess = daoktnb05.save_ktnb05(sPosCd, sMaCN, sQuyBC, sNamBC, sUserId, KT_KHOA, KT_TONG_TO, KT_TONG_DUNO, KT_TKVV_ST, KT_TKVV_DN, KT_SO_TO_TOT, KT_DUNO_TOT,
                    KT_SO_TO_KHA, KT_DUNO_KHA, KT_SO_TO_TB, KT_DUNO_TB, KT_SO_TO_KEM, KT_DUNO_KEM, "W");
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " updatebcnt01 Loi khi server dong bo bao cao nhap tay ve " + e.getMessage());
            return bSuccess;
        }
        return bSuccess;
    }

    public boolean updatebcnt08(HashMap<String, String> hmDatarpt) {
        boolean bSuccess = false;
        if (hmDatarpt.isEmpty()) {
            return bSuccess;
        }
        try {
            DaoKtnb08 daoktnb08 = new DaoKtnb08();
            String sReportId = hmDatarpt.get(Define.XML_REPORT_ID); //id cho bao cao
            String sReportDate = hmDatarpt.get(Define.XML_REPORT_DATE);//ngay bao cao
            String sUserId = hmDatarpt.get(Define.XML_USER_ID);//user id
            String sPosCd = hmDatarpt.get(Define.XML_POS_CD);//ma pgd
            String sMaCN = hmDatarpt.get(Define.XML_MA_CN);//ma CN
            String sGrade = hmDatarpt.get(Define.XML_GRADE);//cap bao cao
            String sFlagData = hmDatarpt.get(Define.XML_FLAG_DATA);//co cho bao cao khtd
//            String sDataReport = hmDatarpt.get(Define.XML_DATA);//du lieu update cho bao cao
            ArrayList<String> KT_DV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_1"), "#");
            ArrayList<String> KT_TX_L = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_2"), "#");
            ArrayList<String> KT_TX_N = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_3"), "#");
            ArrayList<String> KT_TX_VV_C = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_4"), "#");
            ArrayList<String> KT_TX_VV_M = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_5"), "#");
            ArrayList<String> KT_TX_DDN_SD = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_6"), "#");
            ArrayList<String> KT_TX_DDN_N = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_7"), "#");
            ArrayList<String> KT_TX_DDN_VV_C = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_8"), "#");
            ArrayList<String> KT_TX_DDN_VV_M = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_9"), "#");
            ArrayList<String> KT_DK_L = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_10"), "#");
            ArrayList<String> KT_DK_N = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_11"), "#");
            ArrayList<String> KT_DK_VV_C = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_12"), "#");
            ArrayList<String> KT_DK_VV_M = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_13"), "#");
            ArrayList<String> KT_DK_DDN_SD = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_14"), "#");
            ArrayList<String> KT_DK_DDN_N = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_15"), "#");
            ArrayList<String> KT_DK_DDN_VV_C = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_16"), "#");
            ArrayList<String> KT_DK_DDN_VV_M = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_17"), "#");
            ArrayList<String> KT_ND_KN_HC_TC = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_18"), "#");
            ArrayList<String> KT_ND_KN_HC_CS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_19"), "#");
            ArrayList<String> KT_ND_KN_HC_NTS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_20"), "#");
            ArrayList<String> KT_ND_KN_HC_CD = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_21"), "#");
            ArrayList<String> KT_ND_KN_TP = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_22"), "#");
            ArrayList<String> KT_ND_KN_CT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_23"), "#");
            ArrayList<String> KT_ND_TC_HC = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_24"), "#");
            ArrayList<String> KT_ND_TC_TP = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_25"), "#");
            ArrayList<String> KT_ND_TC_TN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_26"), "#");
            ArrayList<String> KT_ND_KHAC = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_27"), "#");
            ArrayList<String> KT_KQ_CGQ = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_28"), "#");
            ArrayList<String> KT_KQ_GQ_CCQD = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_29"), "#");
            ArrayList<String> KT_KQ_GQ_DCQD = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_30"), "#");
            ArrayList<String> KT_KQ_GQ_DCBA = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_31"), "#");
            ArrayList<String> KT_GHICHU = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_32"), "#");
            ArrayList<String> KT_DN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_33"), "#");
            ArrayList<String> KT_CO_DINH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_34"), "#");
            ArrayList<String> KT_THEM = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_35"), "#");
            ArrayList<String> KT_XOA = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_36"), "#");
            ArrayList<String> KT_FONTWEIGHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_37"), "#");
            ArrayList<String> KT_CAPHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_38"), "#");
            ArrayList<String> KT_STT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_39"), "#");
            ArrayList<String> NG_CAPNHAT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_40"), "#");

            String sQuyBC = hmDatarpt.get(Define.XML_QUY_BC);//quy bao cao
            String sNamBC = hmDatarpt.get(Define.XML_NAM_BC);//quy bao cao
            String sSysDate = hmDatarpt.get(Define.XML_SYSDATE);//ngay gio update so lieu

            bSuccess = daoktnb08.save_ktnb08(sPosCd, sMaCN, sQuyBC, sNamBC, sUserId, KT_DV, KT_TX_L, KT_TX_N, KT_TX_VV_C, KT_TX_VV_M, KT_TX_DDN_SD, KT_TX_DDN_N, KT_TX_DDN_VV_C, KT_TX_DDN_VV_M, KT_DK_L, KT_DK_N, KT_DK_VV_C, KT_DK_VV_M, KT_DK_DDN_SD, KT_DK_DDN_N, KT_DK_DDN_VV_C, KT_DK_DDN_VV_M, KT_ND_KN_HC_TC, KT_ND_KN_HC_CS, KT_ND_KN_HC_NTS, KT_ND_KN_HC_CD, KT_ND_KN_TP, KT_ND_KN_CT, KT_ND_TC_HC, KT_ND_TC_TP, KT_ND_TC_TN, KT_ND_KHAC, KT_KQ_CGQ, KT_KQ_GQ_CCQD, KT_KQ_GQ_DCQD, KT_KQ_GQ_DCBA, KT_GHICHU, KT_DN, KT_CO_DINH, KT_THEM, KT_XOA, KT_FONTWEIGHT, KT_CAPHT, KT_STT, NG_CAPNHAT, "W");
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " updatebcnt01 Loi khi server dong bo bao cao nhap tay ve " + e.getMessage());
            return bSuccess;
        }
        return bSuccess;
    }

    public boolean updatebcnt09(HashMap<String, String> hmDatarpt) {
        boolean bSuccess = false;
        if (hmDatarpt.isEmpty()) {
            return bSuccess;
        }
        try {
            DaoKtnb09 daoktnb09 = new DaoKtnb09();
            String sReportId = hmDatarpt.get(Define.XML_REPORT_ID); //id cho bao cao
            String sReportDate = hmDatarpt.get(Define.XML_REPORT_DATE);//ngay bao cao
            String sUserId = hmDatarpt.get(Define.XML_USER_ID);//user id
            String sPosCd = hmDatarpt.get(Define.XML_POS_CD);//ma pgd
            String sMaCN = hmDatarpt.get(Define.XML_MA_CN);//ma CN
            String sGrade = hmDatarpt.get(Define.XML_GRADE);//cap bao cao
            String sFlagData = hmDatarpt.get(Define.XML_FLAG_DATA);//co cho bao cao khtd
//            String sDataReport = hmDatarpt.get(Define.XML_DATA);//du lieu update cho bao cao
            ArrayList<String> KT_DV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_1"), "#");
            ArrayList<String> KT_TN_TS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_2"), "#");
            ArrayList<String> KT_TN_TK_NN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_3"), "#");
            ArrayList<String> KT_TN_TK_MN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_4"), "#");
            ArrayList<String> KT_TN_KT_NN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_5"), "#");
            ArrayList<String> KT_TN_KT_MN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_6"), "#");
            ArrayList<String> KT_TN_DDK = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_7"), "#");
            ArrayList<String> KT_PL_ND_KN_HC_T = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_8"), "#");
            ArrayList<String> KT_PL_ND_KN_HC_DD = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_9"), "#");
            ArrayList<String> KT_PL_ND_KN_HC_NTS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_10"), "#");
            ArrayList<String> KT_PL_ND_KN_HC_CS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_11"), "#");
            ArrayList<String> KT_PL_ND_KN_HC_CT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_12"), "#");
            ArrayList<String> KT_PL_ND_KN_TP = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_13"), "#");
            ArrayList<String> KT_PL_ND_KN_D = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_14"), "#");
            ArrayList<String> KT_PL_ND_TC_T = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_15"), "#");
            ArrayList<String> KT_PL_ND_TC_HC = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_16"), "#");
            ArrayList<String> KT_PL_ND_TC_TP = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_17"), "#");
            ArrayList<String> KT_PL_ND_TC_TN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_18"), "#");
            ArrayList<String> KT_PL_ND_TC_D = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_19"), "#");
            ArrayList<String> KT_PL_ND_TC_K = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_20"), "#");
            ArrayList<String> KT_PL_TQ_HC = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_21"), "#");
            ArrayList<String> KT_PL_TQ_TP = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_22"), "#");
            ArrayList<String> KT_PL_TQ_D = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_23"), "#");
            ArrayList<String> KT_PL_TT_CGQ = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_24"), "#");
            ArrayList<String> KT_PL_TT_DGQ1 = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_25"), "#");
            ArrayList<String> KT_PL_TT_GDQN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_26"), "#");
            ArrayList<String> KT_DK = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_27"), "#");
            ArrayList<String> KT_KQ_SVB = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_28"), "#");
            ArrayList<String> KT_KQ_CTQ = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_29"), "#");
            ArrayList<String> KT_KQ_SCV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_30"), "#");
            ArrayList<String> KT_KQ_TTQ_KN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_31"), "#");
            ArrayList<String> KT_KQ_TTQ_TC = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_32"), "#");
            ArrayList<String> KT_GHICHU = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_33"), "#");
            ArrayList<String> KT_DN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_34"), "#");
            ArrayList<String> KT_CO_DINH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_35"), "#");
            ArrayList<String> KT_THEM = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_36"), "#");
            ArrayList<String> KT_XOA = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_37"), "#");
            ArrayList<String> KT_FONTWEIGHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_38"), "#");
            ArrayList<String> KT_CAPHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_39"), "#");
            ArrayList<String> KT_STT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_40"), "#");
            ArrayList<String> NG_CAPNHAT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_41"), "#");

            String sQuyBC = hmDatarpt.get(Define.XML_QUY_BC);//quy bao cao
            String sNamBC = hmDatarpt.get(Define.XML_NAM_BC);//quy bao cao
            String sSysDate = hmDatarpt.get(Define.XML_SYSDATE);//ngay gio update so lieu

            bSuccess = daoktnb09.save_ktnb09(sPosCd, sMaCN, sQuyBC, sNamBC, sUserId, KT_DV, KT_TN_TS, KT_TN_TK_NN, KT_TN_TK_MN, KT_TN_KT_NN, KT_TN_KT_MN, KT_TN_DDK, KT_PL_ND_KN_HC_T, KT_PL_ND_KN_HC_DD, KT_PL_ND_KN_HC_NTS, KT_PL_ND_KN_HC_CS, KT_PL_ND_KN_HC_CT, KT_PL_ND_KN_TP, KT_PL_ND_KN_D, KT_PL_ND_TC_T, KT_PL_ND_TC_HC, KT_PL_ND_TC_TP, KT_PL_ND_TC_TN, KT_PL_ND_TC_D, KT_PL_ND_TC_K, KT_PL_TQ_HC, KT_PL_TQ_TP, KT_PL_TQ_D, KT_PL_TT_CGQ, KT_PL_TT_DGQ1, KT_PL_TT_GDQN, KT_DK, KT_KQ_SVB, KT_KQ_CTQ, KT_KQ_SCV, KT_KQ_TTQ_KN, KT_KQ_TTQ_TC, KT_GHICHU, KT_DN, KT_CO_DINH, KT_THEM, KT_XOA, KT_FONTWEIGHT, KT_CAPHT, KT_STT, NG_CAPNHAT, "W");
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " updatebcnt01 Loi khi server dong bo bao cao nhap tay ve " + e.getMessage());
            return bSuccess;
        }
        return bSuccess;
    }

    public boolean updatebcnt10(HashMap<String, String> hmDatarpt) {
        boolean bSuccess = false;
        if (hmDatarpt.isEmpty()) {
            return bSuccess;
        }
        try {
            DaoKtnb10 daoktnb10 = new DaoKtnb10();
            String sReportId = hmDatarpt.get(Define.XML_REPORT_ID); //id cho bao cao
            String sReportDate = hmDatarpt.get(Define.XML_REPORT_DATE);//ngay bao cao
            String sUserId = hmDatarpt.get(Define.XML_USER_ID);//user id
            String sPosCd = hmDatarpt.get(Define.XML_POS_CD);//ma pgd
            String sMaCN = hmDatarpt.get(Define.XML_MA_CN);//ma CN
            String sGrade = hmDatarpt.get(Define.XML_GRADE);//cap bao cao
            String sFlagData = hmDatarpt.get(Define.XML_FLAG_DATA);//co cho bao cao khtd
//            String sDataReport = hmDatarpt.get(Define.XML_DATA);//du lieu update cho bao cao
            ArrayList<String> KT_DV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_1"), "#");
            ArrayList<String> KT_DKN_TS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_2"), "#");
            ArrayList<String> KT_DKN_TD_TK = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_3"), "#");
            ArrayList<String> KT_DKN_TD_KT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_4"), "#");
            ArrayList<String> KT_DKN_TD_TS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_5"), "#");
            ArrayList<String> KT_KQ_DGQ_SD = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_6"), "#");
            ArrayList<String> KT_KQ_DGQ_SVV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_7"), "#");
            ArrayList<String> KT_KQ_DGQ_HC = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_8"), "#");
            ArrayList<String> KT_KQ_DGQ_TP = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_9"), "#");
            ArrayList<String> KT_KQ_PT_KND = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_10"), "#");
            ArrayList<String> KT_KQ_PT_KNS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_11"), "#");
            ArrayList<String> KT_KQ_PT_KND1 = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_12"), "#");
            ArrayList<String> KT_KQ_PT_GQL1 = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_13"), "#");
            ArrayList<String> KT_KQ_PT_GQL2_CN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_14"), "#");
            ArrayList<String> KT_KQ_PT_GQL2_HCS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_15"), "#");
            ArrayList<String> KT_KQ_KN_T = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_16"), "#");
            ArrayList<String> KT_KQ_KN_D = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_17"), "#");
            ArrayList<String> KT_KQ_TL_T = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_18"), "#");
            ArrayList<String> KT_KQ_TL_D = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_19"), "#");
            ArrayList<String> KT_KQ_SN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_20"), "#");
            ArrayList<String> KT_KQ_KN_TS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_21"), "#");
            ArrayList<String> KT_KQ_KN_SN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_22"), "#");
            ArrayList<String> KT_KQ_CCQ_SV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_23"), "#");
            ArrayList<String> KT_KQ_CCQ_SDT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_24"), "#");
            ArrayList<String> KT_KQ_CCQ_KQ_SV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_25"), "#");
            ArrayList<String> KT_KQ_CCQ_KQ_SDT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_26"), "#");
            ArrayList<String> KT_KQ_CCQ_KQ_DTH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_27"), "#");
            ArrayList<String> KT_KQ_CCQ_KQ_QTH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_28"), "#");
            ArrayList<String> KT_TH_TS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_29"), "#");
            ArrayList<String> KT_TH_DTH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_30"), "#");
            ArrayList<String> KT_TH_THNN_PT_T = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_31"), "#");
            ArrayList<String> KT_TH_THNN_PT_D = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_32"), "#");
            ArrayList<String> KT_TH_THNN_DT_T = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_33"), "#");
            ArrayList<String> KT_TH_THNN_DT_D = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_34"), "#");
            ArrayList<String> KT_TH_TL_PT_T = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_35"), "#");
            ArrayList<String> KT_TH_TL_PT_D = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_36"), "#");
            ArrayList<String> KT_TH_TL_DT_T = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_37"), "#");
            ArrayList<String> KT_TH_TL_DT_D = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_38"), "#");
            ArrayList<String> KT_GHICHU = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_39"), "#");
            ArrayList<String> KT_DN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_40"), "#");
            ArrayList<String> KT_CO_DINH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_41"), "#");
            ArrayList<String> KT_THEM = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_42"), "#");
            ArrayList<String> KT_XOA = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_43"), "#");
            ArrayList<String> KT_FONTWEIGHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_44"), "#");
            ArrayList<String> KT_CAPHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_45"), "#");
            ArrayList<String> KT_STT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_46"), "#");
            ArrayList<String> NG_CAPNHAT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_47"), "#");

            String sQuyBC = hmDatarpt.get(Define.XML_QUY_BC);//quy bao cao
            String sNamBC = hmDatarpt.get(Define.XML_NAM_BC);//quy bao cao
            String sSysDate = hmDatarpt.get(Define.XML_SYSDATE);//ngay gio update so lieu

            bSuccess = daoktnb10.save_ktnb10(sPosCd, sMaCN, sQuyBC, sNamBC, sUserId, KT_DV, KT_DKN_TS, KT_DKN_TD_TK, KT_DKN_TD_KT, KT_DKN_TD_TS, KT_KQ_DGQ_SD, KT_KQ_DGQ_SVV, KT_KQ_DGQ_HC, KT_KQ_DGQ_TP, KT_KQ_PT_KND, KT_KQ_PT_KNS, KT_KQ_PT_KND1, KT_KQ_PT_GQL1, KT_KQ_PT_GQL2_CN, KT_KQ_PT_GQL2_HCS, KT_KQ_KN_T, KT_KQ_KN_D, KT_KQ_TL_T, KT_KQ_TL_D, KT_KQ_SN, KT_KQ_KN_TS, KT_KQ_KN_SN, KT_KQ_CCQ_SV, KT_KQ_CCQ_SDT, KT_KQ_CCQ_KQ_SV, KT_KQ_CCQ_KQ_SDT, KT_KQ_CCQ_KQ_DTH, KT_KQ_CCQ_KQ_QTH, KT_TH_TS, KT_TH_DTH, KT_TH_THNN_PT_T, KT_TH_THNN_PT_D, KT_TH_THNN_DT_T, KT_TH_THNN_DT_D, KT_TH_TL_PT_T, KT_TH_TL_PT_D, KT_TH_TL_DT_T, KT_TH_TL_DT_D, KT_GHICHU, KT_DN, KT_CO_DINH, KT_THEM, KT_XOA, KT_FONTWEIGHT, KT_CAPHT, KT_STT, NG_CAPNHAT, "W");
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " updatebcnt01 Loi khi server dong bo bao cao nhap tay ve " + e.getMessage());
            return bSuccess;
        }
        return bSuccess;
    }

    public boolean updatebcnt11(HashMap<String, String> hmDatarpt) {
        boolean bSuccess = false;
        if (hmDatarpt.isEmpty()) {
            return bSuccess;
        }
        try {
            DaoKtnb11 daoktnb11 = new DaoKtnb11();
            String sReportId = hmDatarpt.get(Define.XML_REPORT_ID); //id cho bao cao
            String sReportDate = hmDatarpt.get(Define.XML_REPORT_DATE);//ngay bao cao
            String sUserId = hmDatarpt.get(Define.XML_USER_ID);//user id
            String sPosCd = hmDatarpt.get(Define.XML_POS_CD);//ma pgd
            String sMaCN = hmDatarpt.get(Define.XML_MA_CN);//ma CN
            String sGrade = hmDatarpt.get(Define.XML_GRADE);//cap bao cao
            String sFlagData = hmDatarpt.get(Define.XML_FLAG_DATA);//co cho bao cao khtd
//            String sDataReport = hmDatarpt.get(Define.XML_DATA);//du lieu update cho bao cao
            ArrayList<String> KT_DV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_1"), "#");
            ArrayList<String> KT_DKN_TS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_2"), "#");
            ArrayList<String> KT_DKN_TD_TK = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_3"), "#");
            ArrayList<String> KT_DKN_TD_KT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_4"), "#");
            ArrayList<String> KT_DKN_TD_TS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_5"), "#");
            ArrayList<String> KT_KQ_DGQ_SD = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_6"), "#");
            ArrayList<String> KT_KQ_DGQ_SVV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_7"), "#");
            ArrayList<String> KT_KQ_PT_TCD = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_8"), "#");
            ArrayList<String> KT_KQ_PT_TCS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_9"), "#");
            ArrayList<String> KT_KQ_PT_TCD1 = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_10"), "#");
            ArrayList<String> KT_KQ_KN_T = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_11"), "#");
            ArrayList<String> KT_KQ_KN_D = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_12"), "#");
            ArrayList<String> KT_KQ_TL_T = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_13"), "#");
            ArrayList<String> KT_KQ_TL_D = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_14"), "#");
            ArrayList<String> KT_KQ_SN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_15"), "#");
            ArrayList<String> KT_KQ_KN_TS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_16"), "#");
            ArrayList<String> KT_KQ_KN_SN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_17"), "#");
            ArrayList<String> KT_KQ_CCQ_SV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_18"), "#");
            ArrayList<String> KT_KQ_CCQ_SDT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_19"), "#");
            ArrayList<String> KT_KQ_CCQ_KQ_SV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_20"), "#");
            ArrayList<String> KT_KQ_CCQ_KQ_SDT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_21"), "#");
            ArrayList<String> KT_KQ_CCQ_KQ_DTH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_22"), "#");
            ArrayList<String> KT_KQ_CCQ_KQ_QTH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_23"), "#");
            ArrayList<String> KT_TH_TS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_24"), "#");
            ArrayList<String> KT_TH_DTH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_25"), "#");
            ArrayList<String> KT_TH_THNN_PT_T = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_26"), "#");
            ArrayList<String> KT_TH_THNN_PT_D = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_27"), "#");
            ArrayList<String> KT_TH_THNN_DT_T = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_28"), "#");
            ArrayList<String> KT_TH_THNN_DT_D = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_29"), "#");
            ArrayList<String> KT_TH_TL_PT_T = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_30"), "#");
            ArrayList<String> KT_TH_TL_PT_D = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_31"), "#");
            ArrayList<String> KT_TH_TL_DT_T = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_32"), "#");
            ArrayList<String> KT_TH_TL_DT_D = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_33"), "#");
            ArrayList<String> KT_GHICHU = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_34"), "#");
            ArrayList<String> KT_DN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_35"), "#");
            ArrayList<String> KT_CO_DINH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_36"), "#");
            ArrayList<String> KT_THEM = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_37"), "#");
            ArrayList<String> KT_XOA = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_38"), "#");
            ArrayList<String> KT_FONTWEIGHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_39"), "#");
            ArrayList<String> KT_CAPHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_40"), "#");
            ArrayList<String> KT_STT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_41"), "#");
            ArrayList<String> NG_CAPNHAT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_42"), "#");

            String sQuyBC = hmDatarpt.get(Define.XML_QUY_BC);//quy bao cao
            String sNamBC = hmDatarpt.get(Define.XML_NAM_BC);//quy bao cao
            String sSysDate = hmDatarpt.get(Define.XML_SYSDATE);//ngay gio update so lieu

            bSuccess = daoktnb11.save_ktnb11(sPosCd, sMaCN, sQuyBC, sNamBC, sUserId, KT_DV, KT_DKN_TS, KT_DKN_TD_TK, KT_DKN_TD_KT, KT_DKN_TD_TS, KT_KQ_DGQ_SD, KT_KQ_DGQ_SVV, KT_KQ_PT_TCD, KT_KQ_PT_TCS, KT_KQ_PT_TCD1, KT_KQ_KN_T, KT_KQ_KN_D, KT_KQ_TL_T, KT_KQ_TL_D, KT_KQ_SN, KT_KQ_KN_TS, KT_KQ_KN_SN, KT_KQ_CCQ_SV, KT_KQ_CCQ_SDT, KT_KQ_CCQ_KQ_SV, KT_KQ_CCQ_KQ_SDT, KT_KQ_CCQ_KQ_DTH, KT_KQ_CCQ_KQ_QTH, KT_TH_TS, KT_TH_DTH, KT_TH_THNN_PT_T, KT_TH_THNN_PT_D, KT_TH_THNN_DT_T, KT_TH_THNN_DT_D, KT_TH_TL_PT_T, KT_TH_TL_PT_D, KT_TH_TL_DT_T, KT_TH_TL_DT_D, KT_GHICHU, KT_DN, KT_CO_DINH, KT_THEM, KT_XOA, KT_FONTWEIGHT, KT_CAPHT, KT_STT, NG_CAPNHAT, "W");
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " updatebcnt01 Loi khi server dong bo bao cao nhap tay ve " + e.getMessage());
            return bSuccess;
        }
        return bSuccess;
    }

    public boolean updatebcnt12(HashMap<String, String> hmDatarpt) {
        boolean bSuccess = false;
        if (hmDatarpt.isEmpty()) {
            return bSuccess;
        }
        try {
            DaoKtnb12 daoktnb12 = new DaoKtnb12();
            String sReportId = hmDatarpt.get(Define.XML_REPORT_ID); //id cho bao cao
            String sReportDate = hmDatarpt.get(Define.XML_REPORT_DATE);//ngay bao cao
            String sUserId = hmDatarpt.get(Define.XML_USER_ID);//user id
            String sPosCd = hmDatarpt.get(Define.XML_POS_CD);//ma pgd
            String sMaCN = hmDatarpt.get(Define.XML_MA_CN);//ma CN
            String sGrade = hmDatarpt.get(Define.XML_GRADE);//cap bao cao
            String sFlagData = hmDatarpt.get(Define.XML_FLAG_DATA);//co cho bao cao khtd
//            String sDataReport = hmDatarpt.get(Define.XML_DATA);//du lieu update cho bao cao
            ArrayList<String> KT_DV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_1"), "#");
            ArrayList<String> KT_SO_VB_NEW = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_2"), "#");
            ArrayList<String> KT_SO_VB_BS = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_3"), "#");
            ArrayList<String> KT_SO_LOP_TH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_4"), "#");
            ArrayList<String> KT_SO_NG_TH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_5"), "#");
            ArrayList<String> KT_SO_CUOC = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_6"), "#");
            ArrayList<String> KT_SO_DV = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_7"), "#");
            ArrayList<String> KT_SO_DV_VP = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_8"), "#");
            ArrayList<String> KT_SO_TOCHUC1 = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_9"), "#");
            ArrayList<String> KT_SO_CANHAN1 = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_10"), "#");
            ArrayList<String> KT_SO_TOCHUC2 = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_11"), "#");
            ArrayList<String> KT_SO_CANHAN2 = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_12"), "#");
            ArrayList<String> KT_TONG_KLTT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_13"), "#");
            ArrayList<String> KT_SO_TOCHUC3 = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_14"), "#");
            ArrayList<String> KT_SO_CANHAN3 = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_15"), "#");
            ArrayList<String> KT_SO_TOCHUC4 = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_16"), "#");
            ArrayList<String> KT_SO_CANHAN4 = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_17"), "#");
            ArrayList<String> KT_GHICHU = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_18"), "#");
            ArrayList<String> KT_DN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_19"), "#");
            ArrayList<String> KT_CO_DINH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_20"), "#");
            ArrayList<String> KT_THEM = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_21"), "#");
            ArrayList<String> KT_XOA = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_22"), "#");
            ArrayList<String> KT_FONTWEIGHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_23"), "#");
            ArrayList<String> KT_CAPHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_24"), "#");
            ArrayList<String> KT_STT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_25"), "#");
            ArrayList<String> NG_CAPNHAT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_26"), "#");

            String sQuyBC = hmDatarpt.get(Define.XML_QUY_BC);//quy bao cao
            String sNamBC = hmDatarpt.get(Define.XML_NAM_BC);//quy bao cao
            String sSysDate = hmDatarpt.get(Define.XML_SYSDATE);//ngay gio update so lieu

            bSuccess = daoktnb12.save_ktnb12(sPosCd, sMaCN, sQuyBC, sNamBC, sUserId, KT_DV, KT_SO_VB_NEW, KT_SO_VB_BS, KT_SO_LOP_TH, KT_SO_NG_TH, KT_SO_CUOC, KT_SO_DV, KT_SO_DV_VP, KT_SO_TOCHUC1, KT_SO_CANHAN1, KT_SO_TOCHUC2, KT_SO_CANHAN2, KT_TONG_KLTT, KT_SO_TOCHUC3, KT_SO_CANHAN3, KT_SO_TOCHUC4, KT_SO_CANHAN4, KT_GHICHU, KT_DN, KT_CO_DINH, KT_THEM, KT_XOA, KT_FONTWEIGHT, KT_CAPHT, KT_STT, NG_CAPNHAT, "W");
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " updatebcnt01 Loi khi server dong bo bao cao nhap tay ve " + e.getMessage());
            return bSuccess;
        }
        return bSuccess;
    }

//</editor-fold>
    public boolean UpdateDataDcPt(HashMap<String, Object> hmObj) {
        boolean bSuccess = false;
        try {
            String sReportType = hmObj.get(Define.XML_REPORT_TYPE).toString();
            String sUserId = hmObj.get(Define.XML_USER_ID).toString();//user id
            String sPosCd = hmObj.get(Define.XML_POS_CD).toString();//ma pgd
            String sGrade = hmObj.get(Define.XML_GRADE).toString();//cap bao cao
            String sSysDate = hmObj.get(Define.XML_SYSDATE).toString();//Nam bao cao

            List<DcptNoModel> lstDataDcpt = (List<DcptNoModel>) hmObj.get(Define.XML_DATA);
            DaoDcptNo daoDcpt = new DaoDcptNo();

            bSuccess = daoDcpt.updateData_Sync(sUserId, sGrade, sPosCd, lstDataDcpt);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " UpdateDataDcPt -> " + e.getMessage());
            bSuccess = false;
            throw new ImsException("Lỗi trong hàm UpdateDataDcPt", e);
        }

//        public boolean setStatusRisk_syn(String sLagecyId, String Status_Risk, String sSysdate)
        return bSuccess;
    }

    public boolean UpdateDataPlno(HashMap<String, Object> hmObj) {
        boolean bSuccess = false;
        try {
            String sReportType = hmObj.get(Define.XML_REPORT_TYPE).toString();
            String sUserId = hmObj.get(Define.XML_USER_ID).toString();//user id
            String sPosCd = hmObj.get(Define.XML_POS_CD).toString();//ma pgd
            String sGrade = hmObj.get(Define.XML_GRADE).toString();//cap bao cao
            String sSysDate = hmObj.get(Define.XML_SYSDATE).toString();//Nam bao cao

            List<DcplnModel> lstDataDcpt = (List<DcplnModel>) hmObj.get(Define.XML_DATA);
            DaoDCPLNO daoDcpt = new DaoDCPLNO();

            bSuccess = daoDcpt.updateData_Sync(sUserId, sGrade, sPosCd, lstDataDcpt);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " UpdateDataDcPt -> " + e.getMessage());
            bSuccess = false;
            throw new ImsException("Lỗi trong hàm UpdateDataDcPt", e);
        }

//        public boolean setStatusRisk_syn(String sLagecyId, String Status_Risk, String sSysdate)
        return bSuccess;
    }

    public boolean UpdateDataLoveLeaf(HashMap<String, Object> hmObj) {
        boolean bSuccess = false;
        try {
            String sReportType = hmObj.get(Define.XML_REPORT_TYPE).toString();
            String sUserId = hmObj.get(Define.XML_USER_ID).toString();//user id
            String sPosCd = hmObj.get(Define.XML_POS_CD).toString();//ma pgd
            String sGrade = hmObj.get(Define.XML_GRADE).toString();//cap bao cao
            String sSysDate = hmObj.get(Define.XML_SYSDATE).toString();//Nam bao cao

            List<LoveLeafModel> lstDataLove = (List<LoveLeafModel>) hmObj.get(Define.XML_DATA);
            DaoLoveLeaf daoLove = new DaoLoveLeaf();

            bSuccess = daoLove.updateData_Sync_LoveLeaf(sUserId, sGrade, sPosCd, lstDataLove);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getCanonicalName() + " UpdateDataLoveLeaf -> " + e.getMessage());
            bSuccess = false;
            throw new ImsException("Lỗi trong hàm UpdateDataLoveLeaf", e);
        }

//        public boolean setStatusRisk_syn(String sLagecyId, String Status_Risk, String sSysdate)
        return bSuccess;
    }

    public boolean UpdateDataDcPtLock(HashMap<String, Object> hmObj) {
        boolean bSuccess = false;
        String sReportType = hmObj.get(Define.XML_REPORT_TYPE).toString();
        String sUserId = hmObj.get(Define.XML_USER_ID).toString();//user id
        String sPosCd = hmObj.get(Define.XML_POS_CD).toString();//ma pgd
        String sGrade = hmObj.get(Define.XML_GRADE).toString();//cap bao cao
        String sSysDate = hmObj.get(Define.XML_SYSDATE).toString();//Nam bao cao

        List<DcptNoModel> lstDataDcpt = (List<DcptNoModel>) hmObj.get(Define.XML_DATA);
        DaoDcptNo daoDcpt = new DaoDcptNo();

        bSuccess = daoDcpt.updateData_Sync_Lock(sUserId, sGrade, sPosCd, lstDataDcpt);

//        public boolean setStatusRisk_syn(String sLagecyId, String Status_Risk, String sSysdate)
        return bSuccess;
    }

    public boolean UpdateData819(HashMap<String, Object> hmObj) {
        boolean bSuccess = false;
        String sReportType = hmObj.get(Define.XML_REPORT_TYPE).toString();
        String sUserId = hmObj.get(Define.XML_USER_ID).toString();//user id
        String sPosCd = hmObj.get(Define.XML_POS_CD).toString();//ma pgd
        String sGrade = hmObj.get(Define.XML_GRADE).toString();//cap bao cao
        String sNgaybc = hmObj.get(Define.XML_NGAY_BC).toString();//Nam bao cao
//        String sDotrr = hmObj.get(Define.XML_DOT_RR).toString();//dot rui ro
//        String sNhomrr = hmObj.get(Define.XML_NHOM_RR).toString();//nhom bao cao
//        String sSysDate = hmObj.get(Define.XML_SYSDATE).toString();//gio dong bo du lieu

        List<ModelCommune> lst819 = (List<ModelCommune>) hmObj.get(Define.XML_DATA);
        DaoProcessVb819 dao819 = new DaoProcessVb819();
        bSuccess = dao819.setStatusVb819_Sync(sUserId, sGrade, sPosCd, sNgaybc, lst819);

//        public boolean setStatusRisk_syn(String sLagecyId, String Status_Risk, String sSysdate)
        return bSuccess;
    }

    public boolean UpdateDataBCNTKTNB(HashMap<String, String> hmParaRpt) {
        boolean bSuccess = false;
        if (hmParaRpt == null || hmParaRpt.size() == 0) {
            return bSuccess;
        }
        DaoBCNT daoBCNT = new DaoBCNT();
        String sReportType = hmParaRpt.get(Define.XML_REPORT_ID);
        switch (sReportType) {
            case Define.SYN_REPORT_KTNB01: {
                bSuccess = updatebcnt01(hmParaRpt);
            }
            break;
            case Define.SYN_REPORT_KTNB02: {
                bSuccess = updatebcnt02(hmParaRpt);
            }
            break;
            case Define.SYN_REPORT_KTNB03: {
                bSuccess = updatebcnt03(hmParaRpt);
            }
            break;
            case Define.SYN_REPORT_KTNB04: {
                bSuccess = updatebcnt04(hmParaRpt);
            }
            break;
            case Define.SYN_REPORT_KTNB06: {
                bSuccess = updatebcnt06(hmParaRpt);
            }
            break;
            case Define.SYN_REPORT_KTNB05: {
                bSuccess = updatebcnt05(hmParaRpt);
            }
            break;
            case Define.SYN_REPORT_KTNB08: {
                bSuccess = updatebcnt08(hmParaRpt);
            }
            break;
            case Define.SYN_REPORT_KTNB09: {
                bSuccess = updatebcnt09(hmParaRpt);
            }
            break;
            case Define.SYN_REPORT_KTNB10: {
                bSuccess = updatebcnt10(hmParaRpt);
            }
            break;
            case Define.SYN_REPORT_KTNB11: {
                bSuccess = updatebcnt11(hmParaRpt);
            }
            break;
            case Define.SYN_REPORT_KTNB12: {
                bSuccess = updatebcnt12(hmParaRpt);
            }
            break;
            default:
                System.err.println("Ma bao cao nhap tay kiem toan noi bo khong dung ");

                break;
        }

        // public boolean saveGcntGrid_updateSys(String reportId, String posId, String reportDate, 
//            String sUserId, String quarteryear, String strSysDate, String data)
        return bSuccess;
    }

    public boolean UpdateDataKHNV(HashMap<String, String> hmParaRpt) {
        boolean bSuccess = false;
        if (hmParaRpt == null || hmParaRpt.size() == 0) {
            return bSuccess;
        }

        String sReportType = hmParaRpt.get(Define.XML_REPORT_ID);
        switch (sReportType) {
            case Define.SYN_KHNV_XDKH: {
                bSuccess = updateKhnvXdkh(hmParaRpt);
            }
            break;
            case Define.SYN_KHNV_GIAO_KH: {
                bSuccess = updateKhnvGiaoKh(hmParaRpt);
            }
            break;
            case Define.SYN_KHNV_DIEU_CHINH_KH: {
                bSuccess = updateKhnvDieuChinhKh(hmParaRpt);
            }
            break;
            default:
                System.err.println("Ma bao cao nhap tay kiem toan noi bo khong dung ");

                break;
        }

        return bSuccess;
    }

    public boolean updateKhnvXdkh(HashMap<String, String> hmDatarpt) {
        boolean bSuccess = false;
        if (hmDatarpt.isEmpty()) {
            return bSuccess;
        }
        try {
            DaoXdkh daoXdkh = new DaoXdkh();
            String sReportId = hmDatarpt.get(Define.XML_REPORT_ID); //id cho bao cao
            String sReportDate = hmDatarpt.get(Define.XML_REPORT_DATE);//ngay bao cao
            String sUserId = hmDatarpt.get(Define.XML_USER_ID);//user id
            String sPosCd = hmDatarpt.get(Define.XML_POS_CD);//ma pgd
            String sMaCN = hmDatarpt.get(Define.XML_MA_CN);//ma CN
            String sGrade = hmDatarpt.get(Define.XML_GRADE);//cap bao cao
            String sFlagData = hmDatarpt.get(Define.XML_FLAG_DATA);//co cho bao cao khtd
//            String sDataReport = hmDatarpt.get(Define.XML_DATA);//du lieu update cho bao cao

            ArrayList<String> KH_MA_CT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_1"), "#");
            ArrayList<String> KH_STT_HT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_2"), "#");
            ArrayList<String> KH_CHI_TIEU = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_3"), "#");
            ArrayList<String> KH_UOC_TH = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_4"), "#");
            ArrayList<String> KH_KH_NAM = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_5"), "#");
            ArrayList<String> KH_DN = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_6"), "#");
            ArrayList<String> KH_FONTWEIGHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_7"), "#");
            ArrayList<String> KH_CAPHT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_8"), "#");
            ArrayList<String> KH_STT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_9"), "#");

            String sNamBC = hmDatarpt.get(Define.XML_NAM_BC);//quy bao cao
            String sSysDate = hmDatarpt.get(Define.XML_SYSDATE);//ngay gio update so lieu

            bSuccess = daoXdkh.save_xdkh(sPosCd, sMaCN, sNamBC, sUserId, KH_MA_CT, KH_STT_HT, KH_CHI_TIEU, KH_UOC_TH, KH_KH_NAM, KH_DN, KH_FONTWEIGHT, KH_CAPHT, KH_STT);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " updatebcnt01 Loi khi server dong bo bao cao nhap tay ve " + e.getMessage());
            return bSuccess;
        }
        return bSuccess;
    }

    public boolean updateKhnvGiaoKh(HashMap<String, String> hmDatarpt) {
        boolean bSuccess = false;
        if (hmDatarpt.isEmpty()) {
            return bSuccess;
        }
        try {
            DaoGiaokh daoGiaokh = new DaoGiaokh();
            String sReportId = hmDatarpt.get(Define.XML_REPORT_ID); //id cho bao cao
            String sReportDate = hmDatarpt.get(Define.XML_REPORT_DATE);//ngay bao cao
            String sUserId = hmDatarpt.get(Define.XML_USER_ID);//user id
            String sPosCd = hmDatarpt.get(Define.XML_POS_CD);//ma pgd
            String sMaCN = hmDatarpt.get(Define.XML_MA_CN);//ma CN
            String sMaCT = hmDatarpt.get(Define.XML_MA_CT);//ma chi tieu
            String sGrade = hmDatarpt.get(Define.XML_GRADE);//cap bao cao
            String sFlagData = hmDatarpt.get(Define.XML_FLAG_DATA);//co cho bao cao khtd
//            String sDataReport = hmDatarpt.get(Define.XML_DATA);//du lieu update cho bao cao

            ArrayList<String> maPGD = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_1"), "#");
            ArrayList<String> giaoKh = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_2"), "#");

            String sNamBC = hmDatarpt.get(Define.XML_NAM_BC);//quy bao cao
            String sSysDate = hmDatarpt.get(Define.XML_SYSDATE);//ngay gio update so lieu

            bSuccess = daoGiaokh.saveGiaoKh(maPGD, giaoKh, sMaCT, sPosCd, sMaCN, sGrade, Integer.parseInt(sNamBC), sUserId);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " updatebcnt01 Loi khi server dong bo bao cao nhap tay ve " + e.getMessage());
            return bSuccess;
        }
        return bSuccess;
    }

    public boolean updateKhnvDieuChinhKh(HashMap<String, String> hmDatarpt) {
        boolean bSuccess = false;
        if (hmDatarpt.isEmpty()) {
            return bSuccess;
        }
        try {
            DaoDieuchinhkh daoDieuchinhkh = new DaoDieuchinhkh();
            String sReportId = hmDatarpt.get(Define.XML_REPORT_ID); //id cho bao cao
            String sReportDate = hmDatarpt.get(Define.XML_REPORT_DATE);//ngay bao cao
            String sUserId = hmDatarpt.get(Define.XML_USER_ID);//user id
            String sPosCd = hmDatarpt.get(Define.XML_POS_CD);//ma pgd
            String sMaCN = hmDatarpt.get(Define.XML_MA_CN);//ma CN
            String sKey = hmDatarpt.get(Define.XML_MA_CT);//ma chi tieu
            String sGrade = hmDatarpt.get(Define.XML_GRADE);//cap bao cao
            String sFlagData = hmDatarpt.get(Define.XML_FLAG_DATA);//co cho bao cao khtd
//            String sDataReport = hmDatarpt.get(Define.XML_DATA);//du lieu update cho bao cao

            ArrayList<String> KH_MA_CT = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_1"), "#");
            ArrayList<String> KH_DC = DefineFun.SplitStringToArrayList(hmDatarpt.get("COT_2"), "#");

            String sNamBC = hmDatarpt.get(Define.XML_NAM_BC);//quy bao cao
            String sSysDate = hmDatarpt.get(Define.XML_SYSDATE);//ngay gio update so lieu

            bSuccess = daoDieuchinhkh.saveDieuChinhKh(KH_MA_CT, KH_DC, sPosCd, sMaCN, sGrade, Integer.parseInt(sNamBC), sUserId, sKey);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " updatebcnt01 Loi khi server dong bo bao cao nhap tay ve " + e.getMessage());
            return bSuccess;
        }
        return bSuccess;
    }

    public boolean UpdateDataBCNTKTNB1(HashMap<String, String> hmParaRpt) {
        boolean bSuccess = false;
        if (hmParaRpt == null || hmParaRpt.size() == 0) {
            return bSuccess;
        }
        DaoBCNT daoBCNT = new DaoBCNT();
        String sReportType = hmParaRpt.get(Define.XML_REPORT_ID);
        String sReportId = hmParaRpt.get(Define.XML_REPORT_ID); //id cho bao cao
        String sReportDate = hmParaRpt.get(Define.XML_REPORT_DATE);//ngay bao cao
        String sUserId = hmParaRpt.get(Define.XML_USER_ID);//user id
        String sPosCd = hmParaRpt.get(Define.XML_POS_CD);//ma pgd
        String sGrade = hmParaRpt.get(Define.XML_GRADE);//cap bao cao
        String sFlagData = hmParaRpt.get(Define.XML_FLAG_DATA);//co cho bao cao khtd
        String sDataReport = hmParaRpt.get(Define.XML_DATA);//du lieu update cho bao cao
        String sQuyBC = hmParaRpt.get(Define.XML_QUY_BC);//quy bao cao
        String sSysDate = hmParaRpt.get(Define.XML_SYSDATE);//ngay gio update so lieu
        bSuccess = daoBCNT.saveGcntGrid_updateSys(sReportId, sPosCd, sReportDate,
                sUserId, sQuyBC, sSysDate, sDataReport);

        // public boolean saveGcntGrid_updateSys(String reportId, String posId, String reportDate, 
//            String sUserId, String quarteryear, String strSysDate, String data)
        return bSuccess;
    }

    public boolean UpdateDataKHTD(HashMap<String, String> hmParaRpt) {
        boolean bSuccess = false;
        String sReportType = hmParaRpt.get(Define.XML_REPORT_ID);
        String sReportId = hmParaRpt.get(Define.XML_REPORT_ID); //id cho bao cao
        String sReportDate = hmParaRpt.get(Define.XML_REPORT_DATE);//ngay bao cao
        String sUserId = hmParaRpt.get(Define.XML_USER_ID);//user id
        String sPosCd = hmParaRpt.get(Define.XML_POS_CD);//ma pgd
        String sGrade = hmParaRpt.get(Define.XML_GRADE);//cap bao cao
        String sFlagData = hmParaRpt.get(Define.XML_FLAG_DATA);//co cho bao cao khtd
        String sDataReport = hmParaRpt.get(Define.XML_DATA);//du lieu update cho bao cao
        String sQuyBC = hmParaRpt.get(Define.XML_QUY_BC);//quy bao cao
        String sSysDate = hmParaRpt.get(Define.XML_SYSDATE);//ngay gio update so lieu
        DaoCreditPlan daoKHTD = new DaoCreditPlan();

        bSuccess = daoKHTD.saveKhtdGrid_Syn(sPosCd, sReportDate, sUserId, sGrade,
                sFlagData, sDataReport, sSysDate);

//        saveKhtdGrid_Syn(String maPGD, String ngayBC, String userId, String reportGrade, 
//                String kieuLuu, String data, String sSysdate){
        return bSuccess;
    }

    /*
    
     */

    public boolean UpdateDataXlRR(HashMap<String, Object> hmObj) throws Exception {
        boolean bSuccess = false;
        
        String sReportType = hmObj.get(Define.XML_REPORT_TYPE).toString();
        String sUserId = hmObj.get(Define.XML_USER_ID).toString();//user id
        String sPosCd = hmObj.get(Define.XML_POS_CD).toString();//ma pgd
        String sGrade = hmObj.get(Define.XML_GRADE).toString();//cap bao cao
        String sNambc = hmObj.get(Define.XML_NAM_BC).toString();//Nam bao cao
        String sDotrr = hmObj.get(Define.XML_DOT_RR).toString();//dot rui ro
        String sNhomrr = hmObj.get(Define.XML_NHOM_RR).toString();//nhom bao cao
        String sSysDate = hmObj.get(Define.XML_SYSDATE).toString();//gio dong bo du lieu
        String sVbxlrr=hmObj.get(Define.XML_KHOA_RR).toString();
//        System.err.println("XML_REPORT_TYPE=" + hmObj.get(Define.XML_REPORT_TYPE).toString());
//        System.err.println("XML_USER_ID=" + hmObj.get(Define.XML_USER_ID).toString());
//        System.err.println("XML_GRADE=" + hmObj.get(Define.XML_GRADE).toString());
//        System.err.println("XML_NAM_BC=" + hmObj.get(Define.XML_NAM_BC).toString());
//        System.err.println("XML_DOT_RR=" + hmObj.get(Define.XML_DOT_RR).toString());
//        System.err.println("XML_NHOM_RR=" + hmObj.get(Define.XML_NHOM_RR).toString());
//        System.err.println("XML_SYSDATE=" + hmObj.get(Define.XML_SYSDATE).toString());
//        System.err.println("XML_POS_CD=" + hmObj.get(Define.XML_POS_CD).toString());
        List<ModelRiskProcess.ListRiskSync> lstRisk = (List<ModelRiskProcess.ListRiskSync>) hmObj.get(Define.XML_DATA);
        DaoProcessRisk daoRisk = new DaoProcessRisk();
        bSuccess = daoRisk.setStatusRisk_Sync(sUserId, sGrade, sPosCd, sNambc, sDotrr, sNhomrr, lstRisk, sVbxlrr);

//        public boolean setStatusRisk_syn(String sLagecyId, String Status_Risk, String sSysdate)
        return bSuccess;
    }

    /**
     * Hàm này xử lý dữ liệu khi upload lên server
     *
     * @param sReportType
     * @param sReportId
     * @param sReportDate
     * @param sUserId
     * @param sPosCd
     * @param sGrade
     * @param sFlagData
     * @param sDataReport
     * @param sQuyBC
     * @param sFileName
     * @return
     */
    public boolean SendFileXmlToWebServicesKTNB(String sReportType, String sReportId, String sReportDate, String sUserId, String sPosCd, String sMaCN, String sGrade,
            String sFlagData, HashMap<String, String> hmDataReport, String sQuyBC, String sNamBC, String sFileName) {
        boolean bSuccess = false;
        try {
            //Tạo fil xml từ dữ liệu khi nhấn lưu
            ImsReadWriteXmlFile createFileXML = new ImsReadWriteXmlFile();
            bSuccess = createFileXML.createFileXMLBCNT(sReportType, sReportId, sReportDate, sUserId, sPosCd, sMaCN, sGrade, sFlagData, hmDataReport, sQuyBC, sNamBC, sFileName);
            //Không thành công thì return
            if (!bSuccess) {
                System.err.println("Ban chua tao duoc file XML");
                CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices Chua tao duoc file xml");
                return bSuccess;
            }
            //Nếu thành công thì upload lên server
            File checkFile = new File(sFileName);
            if (!checkFile.exists()) {
                System.err.println("Ban chua tao duoc file XML");
                CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices Chua tao duoc file xml");
                return bSuccess;
            }
            //Convert tu file sang kieu byte
            byte[] outbytexml = createFileXML.readFileByte(sFileName);
            String strStatus = ReceivesXmlFile(outbytexml, checkFile.getName());
            //Neu trang thai tra ve la thanh cong thi xoa file xml di
            if (strStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
                checkFile.delete();
                bSuccess = true;
            } else {
                bSuccess = false;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices " + e.getMessage());
            bSuccess = false;
        }

        return bSuccess;
    }

    public String SendFileXmlToWebServices(String sFileName) {
        try {
            //Tạo fil xml từ dữ liệu khi nhấn lưu
            ImsReadWriteXmlFile createFileXML = new ImsReadWriteXmlFile();
            //Nếu thành công thì upload lên server
            File checkFile = new File(sFileName);
            if (!checkFile.exists()) {
                System.err.println("Ban chua tao duoc file XML");
                CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices Chua tao duoc file xml");
                return Define.WEB_SERVICES_STATUS_FAIL;
            }
            //Convert tu file sang kieu byte
            byte[] outbytexml = createFileXML.readFileByte(sFileName);
            //String strStatus = ReceivesXmlFile(outbytexml, checkFile.getName());
            return ReceivesXmlFile(outbytexml, checkFile.getName());
            //Neu trang thai tra ve la thanh cong thi xoa file xml di

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices " + e.getMessage());
            return Define.WEB_SERVICES_STATUS_FAIL;
        }
    }

    public String SendFileXmlToWebServices819(String sFileName) {
        String bSuccess = "";
        try {
            //Tạo fil xml từ dữ liệu khi nhấn lưu
            ImsReadWriteXmlFile createFileXML = new ImsReadWriteXmlFile();
//            bSuccess = createFileXML.createFileXMLKHNV(sReportType, sReportId, sReportDate, sUserId, sPosCd, sMaCN, sMaCt, sGrade, sFlagData, hmDataReport, sNamBC, sFileName);
//            //Không thành công thì return
//            if (!bSuccess) {
//                System.err.println("Ban chua tao duoc file XML");
//                CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices Chua tao duoc file xml");
//                return bSuccess;
//            }
            //Nếu thành công thì upload lên server
            File checkFile = new File(sFileName);
            if (!checkFile.exists()) {
                System.err.println("Ban chua tao duoc file XML");
                CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices Chua tao duoc file xml");
                return "success";
            }
            //Convert tu file sang kieu byte
            byte[] outbytexml = createFileXML.readFileByte(sFileName);
            String strStatus = ReceivesXmlFile(outbytexml, checkFile.getName());
            //Neu trang thai tra ve la thanh cong thi xoa file xml di
            if (strStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
                checkFile.delete();
                bSuccess = "success";
            } else if (strStatus.equals(Define.WEB_SERVICES_STATUS_SEND)) {
                return "send";
            } else {
                bSuccess = "fail";
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices " + e.getMessage());
            bSuccess = "fail";
        }

        return bSuccess;
    }

    public boolean SendFileXmlToWebServicesKHNV(String sReportType, String sReportId, String sReportDate, String sUserId, String sPosCd, String sMaCN,
            String sMaCt, String sGrade, String sFlagData, HashMap<String, String> hmDataReport, String sNamBC, String sFileName) {
        boolean bSuccess = false;
        try {
            //Tạo fil xml từ dữ liệu khi nhấn lưu
            ImsReadWriteXmlFile createFileXML = new ImsReadWriteXmlFile();
            bSuccess = createFileXML.createFileXMLKHNV(sReportType, sReportId, sReportDate, sUserId, sPosCd, sMaCN, sMaCt, sGrade, sFlagData, hmDataReport, sNamBC, sFileName);
            //Không thành công thì return
            if (!bSuccess) {
                System.err.println("Ban chua tao duoc file XML");
                CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices Chua tao duoc file xml");
                return bSuccess;
            }
            //Nếu thành công thì upload lên server
            File checkFile = new File(sFileName);
            if (!checkFile.exists()) {
                System.err.println("Ban chua tao duoc file XML");
                CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices Chua tao duoc file xml");
                return bSuccess;
            }
            //Convert tu file sang kieu byte
            byte[] outbytexml = createFileXML.readFileByte(sFileName);
            String strStatus = ReceivesXmlFile(outbytexml, checkFile.getName());
            //Neu trang thai tra ve la thanh cong thi xoa file xml di
            if (strStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
                checkFile.delete();
                bSuccess = true;
            } else {
                bSuccess = false;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices " + e.getMessage());
            bSuccess = false;
        }

        return bSuccess;
    }

    public boolean SendFileXmlToWebServices(String sReportType, String sReportId, String sReportDate, String sUserId, String sPosCd, String sGrade,
            String sFlagData, String sDataReport, String sQuyBC, String sFileName) {
        boolean bSuccess = false;
        try {
            //Tạo fil xml từ dữ liệu khi nhấn lưu
            ImsReadWriteXmlFile createFileXML = new ImsReadWriteXmlFile();
            bSuccess = createFileXML.createFileXML(sReportType, sReportId, sReportDate, sUserId,
                    sPosCd, sGrade, sFlagData, sDataReport, sQuyBC, sFileName);
            //Không thành công thì return
            if (!bSuccess) {
                System.err.println("Ban chua tao duoc file XML");
                CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices Chua tao duoc file xml");
                return bSuccess;
            }
            //Nếu thành công thì upload lên server
            File checkFile = new File(sFileName);
            if (!checkFile.exists()) {
                System.err.println("Ban chua tao duoc file XML");
                CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices Chua tao duoc file xml");
                return bSuccess;
            }
            //Convert tu file sang kieu byte
            byte[] outbytexml = createFileXML.readFileByte(sFileName);
            String strStatus = ReceivesXmlFile(outbytexml, checkFile.getName());
            //Neu trang thai tra ve la thanh cong thi xoa file xml di
            if (strStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
                checkFile.delete();
                bSuccess = true;
            } else {
                bSuccess = false;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices " + e.getMessage());
            bSuccess = false;
        }

        return bSuccess;
    }

    /**
     * Hàm này gọi và gửi dữ liệu cho web services khi đã ghi ra file xml thành
     * công Nếu update lên server thành công sẽ trả về OK nếu không thành công
     * trả về FAIL
     *
     * @param inbyte
     * @param strFileName
     * @return
     */
    public String ReceivesXmlFile(byte[] inbyte, String strFileName) {
        ImsServices_Service imsServices = new ImsServices_Service();
        ImsServices portServices = imsServices.getImsServicesPort();
        System.err.println("File la " + strFileName);
        return portServices.receivesXmlFile(inbyte, strFileName);
    }

    // PHAN BO SUNG CAP NHAT THONG TIN ADD-INFOR
    // TRUNGNT88
    public boolean SendFileXmlToWebServices_Dmxa(String sync_pos_cd,
            List<vbsp.ims.model.Commune> hmDataReport, String file_name) {
        boolean bSuccess;
        try {
            //Tạo fil xml từ dữ liệu khi nhấn lưu
            ImsReadWriteXmlFile createFileXML = new ImsReadWriteXmlFile();
            bSuccess = createFileXML.createFileXML_Dmxa(sync_pos_cd, hmDataReport, file_name);
            //Không thành công thì return
            if (!bSuccess) {
                System.err.println("Ban chua tao duoc file XML");
                CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices Chua tao duoc file xml");
                return bSuccess;
            }
            //Nếu thành công thì upload lên server
            File checkFile = new File(file_name);
            if (!checkFile.exists()) {
                System.err.println("Ban chua tao duoc file XML");
                CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices Chua tao duoc file xml");
                return bSuccess;
            }
            //Convert tu file sang kieu byte
            byte[] outbytexml = createFileXML.readFileByte(file_name);
            String strStatus = ReceivesXmlFile(outbytexml, checkFile.getName());
            //Neu trang thai tra ve la thanh cong thi xoa file xml di
            if (strStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
                checkFile.delete();
                bSuccess = true;
            } else {
                bSuccess = false;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices " + e.getMessage());
            bSuccess = false;
        }

        return bSuccess;
    }

    private boolean UpdateData_Dmxa(HashMap<String, Object> hmObj_dmxa) {
        boolean bSuccess;
        String report_type = hmObj_dmxa.get(Define.XML_REPORT_TYPE).toString();
        String report_dt = hmObj_dmxa.get(Define.XML_SYSDATE).toString();//gio dong bo du lieu

        List<vbsp.ims.model.Commune> communes = (List<vbsp.ims.model.Commune>) hmObj_dmxa.get(Define.XML_DATA);
        vbsp.ims.dao.CommuneDao communeDao = new vbsp.ims.dao.CommuneDao();
        bSuccess = communeDao.updateCommuneTable(communes);
        return bSuccess;
    }

    public boolean SendFileXmlToWebServices_Dmpos(String sync_pos_cd, Branch new_branch, String file_name) {
        boolean bSuccess;
        try {
            //Tạo fil xml từ dữ liệu khi nhấn lưu
            ImsReadWriteXmlFile createFileXML = new ImsReadWriteXmlFile();
            bSuccess = createFileXML.createFileXML_Dmpos(sync_pos_cd, new_branch, file_name);
            //Không thành công thì return
            if (!bSuccess) {
                System.err.println("Ban chua tao duoc file XML");
                CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices Chua tao duoc file xml");
                return bSuccess;
            }
            //Nếu thành công thì upload lên server
            File checkFile = new File(file_name);
            if (!checkFile.exists()) {
                System.err.println("Ban chua tao duoc file XML");
                CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices Chua tao duoc file xml");
                return bSuccess;
            }
            //Convert tu file sang kieu byte
            byte[] outbytexml = createFileXML.readFileByte(file_name);
            String strStatus = ReceivesXmlFile(outbytexml, checkFile.getName());
            //Neu trang thai tra ve la thanh cong thi xoa file xml di
            if (strStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
                checkFile.delete();
                bSuccess = true;
            } else {
                bSuccess = false;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices " + e.getMessage());
            bSuccess = false;
        }

        return bSuccess;

    }

    private boolean UpdateData_Dmpos(HashMap<String, Object> hmObj_dmpos) {
        boolean bSuccess;
        String report_type = hmObj_dmpos.get(Define.XML_REPORT_TYPE).toString();
        String report_dt = hmObj_dmpos.get(Define.XML_SYSDATE).toString();//gio dong bo du lieu

        List<vbsp.ims.model.Branch> branches = (List<vbsp.ims.model.Branch>) hmObj_dmpos.get(Define.XML_DATA);
        vbsp.ims.dao.BranchDao branchDao = new vbsp.ims.dao.BranchDao();
        bSuccess = branchDao.updateBranchTable(branches);
        return bSuccess;
    }

    public boolean SendFileXmlToWebServices_Dmto(String sync_pos_cd, List<Group> lv_groups, String file_name) {
        boolean bSuccess;
        try {
            //Tạo fil xml từ dữ liệu khi nhấn lưu
            ImsReadWriteXmlFile createFileXML = new ImsReadWriteXmlFile();
            bSuccess = createFileXML.createFileXML_Dmto(sync_pos_cd, lv_groups, file_name);
            //Không thành công thì return
            if (!bSuccess) {
                System.err.println("Ban chua tao duoc file XML");
                CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices Chua tao duoc file xml");
                return bSuccess;
            }
            //Nếu thành công thì upload lên server
            File checkFile = new File(file_name);
            if (!checkFile.exists()) {
                System.err.println("Ban chua tao duoc file XML");
                CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices Chua tao duoc file xml");
                return bSuccess;
            }
            //Convert tu file sang kieu byte
            byte[] outbytexml = createFileXML.readFileByte(file_name);
            String strStatus = ReceivesXmlFile(outbytexml, checkFile.getName());
            //Neu trang thai tra ve la thanh cong thi xoa file xml di
            if (strStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
                checkFile.delete();
                bSuccess = true;
            } else {
                bSuccess = false;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " SendFileXmlToWebServices " + e.getMessage());
            bSuccess = false;
        }

        return bSuccess;
    }

    private boolean UpdateData_Dmto(HashMap<String, Object> hmObj_dmto) {
        boolean bSuccess;

        String report_type = hmObj_dmto.get(Define.XML_REPORT_TYPE).toString();
        String report_dt = hmObj_dmto.get(Define.XML_SYSDATE).toString();//gio dong bo du lieu

        List<vbsp.ims.model.Group> groups = (List<vbsp.ims.model.Group>) hmObj_dmto.get(Define.XML_DATA);
        vbsp.ims.dao.GroupDao groupDao = new vbsp.ims.dao.GroupDao();
        bSuccess = groupDao.updateGroupTable_HO(groups);
        return bSuccess;
    }

    public static void main(String[] args) {
        try {
            List<File> file_xml = DefineFun.listfilexml("E:\\xml\\");
            ProcessReportSyn prod = new ProcessReportSyn();
            String file = "e://BCQT_M01_000303_PHUONGLD_087444.xml";
            int dem = 1;
            for (File xml : file_xml) {
                file = xml.getAbsolutePath();
                System.err.println(dem + " -->  " + file);
                prod.UpdateSynReport(file);
                dem++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
