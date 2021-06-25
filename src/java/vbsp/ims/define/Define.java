/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.define;

import vbsp.ims.log.CoreLogger;

/**
 *
 * @author LION
 */
public class Define {

    public String strPathRoot;

    public String getStrPathRoot() {
        return strPathRoot;
    }

    public void setStrPathRoot(String strPathRoot) {
        this.strPathRoot = strPathRoot;
    }

    public static String M_ROOT;
    public static final String M_REPORT1 = "Report/";
    public static final String M_REPORT = "REPORTS/";
    public static final String M_UPLOAD_DIR = "UPLOAD_FILE/";
    public static final String M_REPORT_PDF = "EXPORT_REPORT/PDF/";
    public static final String M_REPORT_XLS = "EXPORT_REPORT/XLS/";
    public static final String M_REPORT_TXT = "EXPORT_REPORT/TXT/";
    public static final String M_EXCEL_TEMP = "EXCEL_TEMPLATE/";
    public static final String M_EXCEL_CONFIG = "EXCEL_TEMPLATE/TEMPLATE_CONFIG/";
    
    public static final String M_IMPORT_FILE = "IMPORT_FILE/";
    public static final String M_UNZIP_TMP = M_IMPORT_FILE + "UNZIP_TMP/";

    //Khai bao cho load xlm tu web services truyen ve
    //Duong dan luu file va load file xml
    public static final String M_REPORT_XML = "EXPORT_REPORT/XML/";

    //Node root cua dada
    public static final String XML_ROOT_NOTE = "IMS_REPORT"; //ten Node root trong file xml
    public static final String XML_MAIN_DATA_NOTE = "IMS_REPORT_DATA";//Node chinh luu du lieu trong file xml
    public static final String XML_REPORT_ID = "IMS_REPORT_ID";//Ma bao cao
    public static final String XML_REPORT_TYPE = "TYPE";
    public static final String XML_REPORT_DATE = "IMS_REPORT_DATE";//Ngay bao cao
    public static final String XML_USER_ID = "IMS_REPORT_USER_ID";//User name sua du lieu
    public static final String XML_POS_CD = "IMS_POS_CD";//Ma pos xu ly bao cao
    public static final String XML_MA_CN = "IMS_MA_CN";//Ma CN xu ly bao cao
    public static final String XML_MA_CT = "IMS_MA_CT";//Ma chi tieu
    public static final String XML_GRADE = "IMS_GRADE";//Cap bao cao
    public static final String XML_FLAG_DATA = "IMS_FLAG_DATA";//Co cap nhat du lieu cho KHTD

    public static final String XML_DATA = "IMS_DATA";//Du lieu chinh khi update bao cao

    public static final String XML_DATA_VALUE = "Giatri_data";//Du lieu chinh khi update bao cao
    public static final String XML_SYSDATE = "IMS_SYSDATE"; //Du lieu cho ngay tao bao cao (ngay update so lieu)

    public static final String XML_MA_BCQT="IMS_MA_BCQT";
    
    public static final String XML_TYPE_BCQT="TYPE_BCQT";
    public static final String XML_BCQT_NT="NT";
    public static final String XML_BCQT_TM="TM";
    
    public static final String XML_QUY_BC = "IMS_QUY_BC";
    public static final String XML_NAM_BC = "IMS_NAM_BC";
    public static final String XML_NGAY_BC = "IMS_NGAY_BC";
    public static final String XML_DOT_RR = "DOT_RR";
    public static final String XML_NHOM_RR = "NHOM_RR";
    public static final String XML_KHOA_RR = "RR_KHOA";
    
    //Dinh nghia cho loai bao cao
    //01 Bao cao nhap tay KTNB
    //02 Cho ke hoach tin dung
    //03 Xu ly rui ro
    //04 goi dong bo du lieu
    //05 loai khac

    public static final String PARA_SYN_REPORT_KTNB = "01";
    public static final String PARA_SYN_REPORT_KHTD = "02";
    public static final String PARA_SYN_REPORT_XLRR = "03";
    public static final String PARA_SYN_REPORT_DATA = "04";
    public static final String PARA_SYN_REPORT_KHAC = "05";
    public static final String PARA_SYN_REPORT_KHNV = "06";
    public static final String PARA_SYN_REPORT_VB819 = "07";

    public static final String PARA_SYN_REPORT_DMXA = "08";
    public static final String PARA_SYN_REPORT_DMPOS = "09";

    public static final String PARA_SYN_REPORT_DCPT = "10";
    public static final String PARA_SYN_REPORT_DCPT_LOCK = "12";
    public static final String PARA_SYN_REPORT_DMTO = "11";
    public static final String PARA_SYN_REPORT_LOVELEAF = "13";
    
    public static final String PARA_SYN_REPORT_PLNO = "16";

    public static final String PARA_SYN_REPORT_BCQT= "15";
    public static final String PARA_SYN_REPORT_SBV= "17";
    public static final String PARA_SYN_REPORT_KTGS= "18";
    public static final String PARA_SYN_REPORT_KTNB_ADD = "19";
    public static final String PARA_SYN_REPORT_KHOANTC = "20";
    public static final String PARA_SYN_REPORT_CIC = "21";
    public static final String PARA_SYN_REPORT_PHTS= "22";
    public static final String PARA_SYN_REPORT_TDNN= "23";
    public static final String PARA_SYN_REPORT_PHIUT= "24";
    public static final String PARA_SYN_REPORT_CDTT= "25";
    public static final String PARA_SYN_REPORT_CTLT= "26"; //Chương trình loại trừ
    public static final String PARA_SYN_REPORT_MUASAMTS= "27";
    public static final String PARA_SYN_REPORT_CBSS= "28";
    public static final String PARA_SYN_INPUT_BRANCH= "29";
    public static final String PARA_SYN_REPORT_KHNV2021= "30";
    
    
    //Dinh nghia cho khnv
    public static final String SYN_KHNV_XDKH = "XDKH";
    public static final String SYN_KHNV_GIAO_KH = "GIAO_KH";
    public static final String SYN_KHNV_DIEU_CHINH_KH = "DCKH";

    //Dinh nghia cho loai ma bao cao nhap tay
    public static final String SYN_REPORT_KTNB01 = "KTNB01";
    public static final String SYN_REPORT_KTNB02 = "KTNB02";
    public static final String SYN_REPORT_KTNB03 = "KTNB03";
    public static final String SYN_REPORT_KTNB04 = "KTNB04";
    public static final String SYN_REPORT_KTNB05 = "KTNB05";
    public static final String SYN_REPORT_KTNB06 = "KTNB06";
    public static final String SYN_REPORT_KTNB07 = "KTNB07";
    public static final String SYN_REPORT_KTNB08 = "KTNB08";
    public static final String SYN_REPORT_KTNB09 = "KTNB09";
    public static final String SYN_REPORT_KTNB10 = "KTNB10";
    public static final String SYN_REPORT_KTNB11 = "KTNB11";
    public static final String SYN_REPORT_KTNB12 = "KTNB12";

    public static final String SYN_REPORT_VB819 = "VB819";
    //Trang thai tra ve cua web services
    public static final String WEB_SERVICES_STATUS_OK = "OK";
    public static final String WEB_SERVICES_STATUS_SEND = "SEND";
    public static final String WEB_SERVICES_STATUS_FAIL = "FAIL";

    //Khoa gui du lieu cho phan rui ro
    public static final String KHOA_SEND_RR = "SEND";
    public static final String KHOA_CHECK_RR = "CHECK";
    public static final String KHOA_UNCHECK_RR = "UNCHECK";

    public static final String KHOA_CHECK_DCPT = "LOCK";
    public static final String KHOA_UNCHECK_DCPT = "UNLOCK";

    public static final String SPACE_STRING = "&";

    public static final char[] VALID_OPERATORS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '/', '*', '+', '-', '.', ',', '(', ')'};

    public static final CoreLogger log = new CoreLogger();
    
//    Định nghĩa các trạng thái chấm điểm tập thể
    public static final String CDTT_PGD_LOCK = "1";          //PGD chốt số liệu    
    public static final String CDTT_CN_UNLOCK_PGD = "0";     //Chi nhánh mở khóa PGD - trả lại    
    public static final String CDTT_CN_SEND_LOCK_CMNV = "4";           //Chi nhánh gửi số liệu 
    public static final String CDTT_TW_UNLOCK = "5";         //Chi nhánh mở khóa CN - trả lại
    
// Định nghĩa cho KHNV    
    public static final String LOV_NAMBC = "NAMBC"; 
    public static final String LOV_MABC = "MABC"; 
    public static final String LOV_DOTBC = "DOTBC"; 
    public static final String LOV_VIEW_TYPE = "VIEW"; 
}
