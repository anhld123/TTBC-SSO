/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.define;

/**
 *
 * @author Trung
 */
public class Constant {

    public static class dmxa_constant {

        public static String _COMMUNE_ID = "MA";
        public static String _COMMUNE_NAME = "TEN";
        public static String _GDXFLG = "GDXFLG";
        public static String _XA135FLG = "XA135FLG";
        public static String _STATUS = "TRANGTHAI";
        public static String _COMMUNE_DT = "NGAYGDX";
        public static String _MANAGER_POS = "PGD_QL";
        public static String _NEW_COMMUNE_FLG = "NONGTHONMOI_FLG";
        public static String _CANBOTDPT = "CANBOTDPT";
        public static String _MAKER_ID = "MAKER_ID";
        public static String _MAKER_DT = "MAKER_DT";
    }

    public static class dmpos_constant {

        public static String _POS_CD = "PO_MA";
        public static String _POS_NAME = "PO_TEN";
        public static String _POS_ADDRESS = "PO_DIACHI";
        public static String _POS_FAX = "PO_FAX";
        public static String _POS_MOBILE = "POS_MOBILE";
        public static String _POS_SBVCODE = "PO_MASBV";
        public static String _POS_FLAG = "PO_POSFLG";
        public static String _MAIN_POS = "PO_MACN";
        public static String _STATUS = "PO_STATUS";
        public static String _MAKER_ID = "MAKER_ID";
        public static String _MAKER_DT = "MAKER_DT";

    }

    public static class dmto_constant {

        public static String _TO_MATO = "TO_MATO";
        public static String _TO_TENTT = "TO_TENTT";
        public static String _TO_DVUT = "TO_DVUT";
        public static String _TO_MADP = "TO_MADP";
        public static String _TO_MAPGD = "TO_MAPGD";
        public static String _TRANGTHAI = "TRANGTHAI";
        public static String _TO_TLDUNGQD = "TO_TLDUNGQD";
        public static String _TO_THAMOCD = "TO_THAMOCD";
        public static String _MAKER_ID = "MAKER_ID";
        public static String _MAKER_DT = "MAKER_DT";

    }
    
    
    public static class dtw_insert_table {
        public static String _LOVELEAF_POOR_TRANSACTION = "LOVELEAF_UPLOAD";
        public static String _LOVELEAF_POOR_NOACCOUNT = "LOVELEAF_NOACCOUNT";
        public static String _ACCOUNT_UPOAD = "ACCOUNT_UPLOAD";
        public static String _QTT_DTTH = "QTT_DTTH";
        public static String _QTT_CHUYENTIEN = "QTT_CHUYENTIEN";

        public static String _CUST1_UPOAD = "CUST1_UPLOAD";    
        public static String _CUST2_UPOAD = "CUST2_UPLOAD";    
        
        
        public static String _VVC_DTTH = "VVC_DTTH";
        public static String _VVC_CHUYENTIEN = "VVC_CHUYENTIEN";
        public static String _VVC_CITAD = "VVC_CITAD";
    }
    
    public static class KTNB_STATUS {
        public static String _SUB_POS_MAKER = "1";
        public static String _SUB_POS_AUTH = "2";
        public static String _MAIN_POS_MAKER = "3";
        public static String _MAIN_POS_AUTH = "3";
        public static String _HEAD_POS_MAKER = "4";
        public static String _HEAD_POS_AUTH = "4";
    }
    
    public static class StatusCode {
        public static int OK = 200;
        public static int FAIL = 400;
        public static int NOT_FOUND = 404;
    }
}
