/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.bcqt.model;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.Date;

/**
 *
 * NguyetLM
 */
public class CBSS_DULIEU implements SQLData {

    public static final String ORACLE_OBJECT_TYPE = "TYPE_CBSS";
    public static final String ORACLE_TABLE_TYPE = "TAB_CBSS";

    public static CBSS_DULIEU newInstance() {
        return new CBSS_DULIEU();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Khao bao bien">
    private String KHOA_1;
    private String KHOA_2;
    private String MACT;
    private String TENCT;
    private String MACN;
    private String MAPGD;
    private String MAXA;
    private String MATHON;
    private String MATO;
    private String MAKH;
    private String TT_GTRINH;
    private String D1;
    private String D2;
    private String D3;
    private String D4;
    private String D5;
    private String D6;
    private String D7;
    private String D8;
    private String D9;
    private String D10;
    private String D11;
    private String D12;
    private String D13;
    private String D14;
    private String D15;
    private String D16;
    private String D17;
    private String D18;
    private String D19;
    private String D20;
    private String D21;
    private String D22;
    private String D23;
    private String D24;
    private String D25;
    private String D26;
    private String D27;
    private String D28;
    private String D29;
    private String D30;
    private String D31;
    private String D32;
    private String D33;
    private String D34;
    private String D35;
    private String D36;
    private String D37;
    private String D38;
    private String D39;
    private String D40;
    private String D41;
    private String D42;
    private String D43;
    private String D44;
    private String D45;
    private String D46;
    private String D47;
    private String D48;
    private String D49;
    private String D50;
    private String D51;
    private String D52;
    private String D53;
    private String D54;
    private String D55;
    private String D56;
    private String D57;
    private String D58;
    private String D59;
    private String D60;
    private String D61;
    private String D62;
    private String D63;
    private String D64;
    private String D65;
    private String D66;
    private String D67;
    private String D68;
    private String D69;
    private String D70;
    private Date NGAYBC;
    
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Phuong thuc get/set">

    public static String getORACLE_OBJECT_TYPE() {
        return ORACLE_OBJECT_TYPE;
    }

    public static String getORACLE_TABLE_TYPE() {
        return ORACLE_TABLE_TYPE;
    }

    public String getKHOA_1() {
        return KHOA_1;
    }

    public String getKHOA_2() {
        return KHOA_2;
    }

    public String getMACT() {
        return MACT;
    }

    public String getTENCT() {
        return TENCT;
    }

    public String getMACN() {
        return MACN;
    }

    public String getMAPGD() {
        return MAPGD;
    }

    public String getMAXA() {
        return MAXA;
    }

    public String getMATHON() {
        return MATHON;
    }

    public String getMATO() {
        return MATO;
    }

    public String getMAKH() {
        return MAKH;
    }

    public String getTT_GTRINH() {
        return TT_GTRINH;
    }

    public String getD1() {
        return D1;
    }

    public String getD2() {
        return D2;
    }

    public String getD3() {
        return D3;
    }

    public String getD4() {
        return D4;
    }

    public String getD5() {
        return D5;
    }

    public String getD6() {
        return D6;
    }

    public String getD7() {
        return D7;
    }

    public String getD8() {
        return D8;
    }

    public String getD9() {
        return D9;
    }

    public String getD10() {
        return D10;
    }

    public String getD11() {
        return D11;
    }

    public String getD12() {
        return D12;
    }

    public String getD13() {
        return D13;
    }

    public String getD14() {
        return D14;
    }

    public String getD15() {
        return D15;
    }

    public String getD16() {
        return D16;
    }

    public String getD17() {
        return D17;
    }

    public String getD18() {
        return D18;
    }

    public String getD19() {
        return D19;
    }

    public String getD20() {
        return D20;
    }

    public String getD21() {
        return D21;
    }

    public String getD22() {
        return D22;
    }

    public String getD23() {
        return D23;
    }

    public String getD24() {
        return D24;
    }

    public String getD25() {
        return D25;
    }

    public String getD26() {
        return D26;
    }

    public String getD27() {
        return D27;
    }

    public String getD28() {
        return D28;
    }

    public String getD29() {
        return D29;
    }

    public String getD30() {
        return D30;
    }

    public String getD31() {
        return D31;
    }

    public String getD32() {
        return D32;
    }

    public String getD33() {
        return D33;
    }

    public String getD34() {
        return D34;
    }

    public String getD35() {
        return D35;
    }

    public String getD36() {
        return D36;
    }

    public String getD37() {
        return D37;
    }

    public String getD38() {
        return D38;
    }

    public String getD39() {
        return D39;
    }

    public String getD40() {
        return D40;
    }

    public String getD41() {
        return D41;
    }

    public String getD42() {
        return D42;
    }

    public String getD43() {
        return D43;
    }

    public String getD44() {
        return D44;
    }

    public String getD45() {
        return D45;
    }

    public String getD46() {
        return D46;
    }

    public String getD47() {
        return D47;
    }

    public String getD48() {
        return D48;
    }

    public String getD49() {
        return D49;
    }

    public String getD50() {
        return D50;
    }

    public String getD51() {
        return D51;
    }

    public String getD52() {
        return D52;
    }

    public String getD53() {
        return D53;
    }

    public String getD54() {
        return D54;
    }

    public String getD55() {
        return D55;
    }

    public String getD56() {
        return D56;
    }

    public String getD57() {
        return D57;
    }

    public String getD58() {
        return D58;
    }

    public String getD59() {
        return D59;
    }

    public String getD60() {
        return D60;
    }

    public String getD61() {
        return D61;
    }

    public String getD62() {
        return D62;
    }

    public String getD63() {
        return D63;
    }

    public String getD64() {
        return D64;
    }

    public String getD65() {
        return D65;
    }

    public String getD66() {
        return D66;
    }

    public String getD67() {
        return D67;
    }

    public String getD68() {
        return D68;
    }

    public String getD69() {
        return D69;
    }

    public String getD70() {
        return D70;
    }

    public Date getNGAYBC() {
        return NGAYBC;
    }

    public void setKHOA_1(String KHOA_1) {
        this.KHOA_1 = KHOA_1;
    }

    public void setKHOA_2(String KHOA_2) {
        this.KHOA_2 = KHOA_2;
    }

    public void setMACT(String MACT) {
        this.MACT = MACT;
    }

    public void setTENCT(String TENCT) {
        this.TENCT = TENCT;
    }

    public void setMACN(String MACN) {
        this.MACN = MACN;
    }

    public void setMAPGD(String MAPGD) {
        this.MAPGD = MAPGD;
    }

    public void setMAXA(String MAXA) {
        this.MAXA = MAXA;
    }

    public void setMATHON(String MATHON) {
        this.MATHON = MATHON;
    }

    public void setMATO(String MATO) {
        this.MATO = MATO;
    }

    public void setMAKH(String MAKH) {
        this.MAKH = MAKH;
    }

    public void setTT_GTRINH(String TT_GTRINH) {
        this.TT_GTRINH = TT_GTRINH;
    }

    public void setD1(String D1) {
        this.D1 = D1;
    }

    public void setD2(String D2) {
        this.D2 = D2;
    }

    public void setD3(String D3) {
        this.D3 = D3;
    }

    public void setD4(String D4) {
        this.D4 = D4;
    }

    public void setD5(String D5) {
        this.D5 = D5;
    }

    public void setD6(String D6) {
        this.D6 = D6;
    }

    public void setD7(String D7) {
        this.D7 = D7;
    }

    public void setD8(String D8) {
        this.D8 = D8;
    }

    public void setD9(String D9) {
        this.D9 = D9;
    }

    public void setD10(String D10) {
        this.D10 = D10;
    }

    public void setD11(String D11) {
        this.D11 = D11;
    }

    public void setD12(String D12) {
        this.D12 = D12;
    }

    public void setD13(String D13) {
        this.D13 = D13;
    }

    public void setD14(String D14) {
        this.D14 = D14;
    }

    public void setD15(String D15) {
        this.D15 = D15;
    }

    public void setD16(String D16) {
        this.D16 = D16;
    }

    public void setD17(String D17) {
        this.D17 = D17;
    }

    public void setD18(String D18) {
        this.D18 = D18;
    }

    public void setD19(String D19) {
        this.D19 = D19;
    }

    public void setD20(String D20) {
        this.D20 = D20;
    }

    public void setD21(String D21) {
        this.D21 = D21;
    }

    public void setD22(String D22) {
        this.D22 = D22;
    }

    public void setD23(String D23) {
        this.D23 = D23;
    }

    public void setD24(String D24) {
        this.D24 = D24;
    }

    public void setD25(String D25) {
        this.D25 = D25;
    }

    public void setD26(String D26) {
        this.D26 = D26;
    }

    public void setD27(String D27) {
        this.D27 = D27;
    }

    public void setD28(String D28) {
        this.D28 = D28;
    }

    public void setD29(String D29) {
        this.D29 = D29;
    }

    public void setD30(String D30) {
        this.D30 = D30;
    }

    public void setD31(String D31) {
        this.D31 = D31;
    }

    public void setD32(String D32) {
        this.D32 = D32;
    }

    public void setD33(String D33) {
        this.D33 = D33;
    }

    public void setD34(String D34) {
        this.D34 = D34;
    }

    public void setD35(String D35) {
        this.D35 = D35;
    }

    public void setD36(String D36) {
        this.D36 = D36;
    }

    public void setD37(String D37) {
        this.D37 = D37;
    }

    public void setD38(String D38) {
        this.D38 = D38;
    }

    public void setD39(String D39) {
        this.D39 = D39;
    }

    public void setD40(String D40) {
        this.D40 = D40;
    }

    public void setD41(String D41) {
        this.D41 = D41;
    }

    public void setD42(String D42) {
        this.D42 = D42;
    }

    public void setD43(String D43) {
        this.D43 = D43;
    }

    public void setD44(String D44) {
        this.D44 = D44;
    }

    public void setD45(String D45) {
        this.D45 = D45;
    }

    public void setD46(String D46) {
        this.D46 = D46;
    }

    public void setD47(String D47) {
        this.D47 = D47;
    }

    public void setD48(String D48) {
        this.D48 = D48;
    }

    public void setD49(String D49) {
        this.D49 = D49;
    }

    public void setD50(String D50) {
        this.D50 = D50;
    }

    public void setD51(String D51) {
        this.D51 = D51;
    }

    public void setD52(String D52) {
        this.D52 = D52;
    }

    public void setD53(String D53) {
        this.D53 = D53;
    }

    public void setD54(String D54) {
        this.D54 = D54;
    }

    public void setD55(String D55) {
        this.D55 = D55;
    }

    public void setD56(String D56) {
        this.D56 = D56;
    }

    public void setD57(String D57) {
        this.D57 = D57;
    }

    public void setD58(String D58) {
        this.D58 = D58;
    }

    public void setD59(String D59) {
        this.D59 = D59;
    }

    public void setD60(String D60) {
        this.D60 = D60;
    }

    public void setD61(String D61) {
        this.D61 = D61;
    }

    public void setD62(String D62) {
        this.D62 = D62;
    }

    public void setD63(String D63) {
        this.D63 = D63;
    }

    public void setD64(String D64) {
        this.D64 = D64;
    }

    public void setD65(String D65) {
        this.D65 = D65;
    }

    public void setD66(String D66) {
        this.D66 = D66;
    }

    public void setD67(String D67) {
        this.D67 = D67;
    }

    public void setD68(String D68) {
        this.D68 = D68;
    }

    public void setD69(String D69) {
        this.D69 = D69;
    }

    public void setD70(String D70) {
        this.D70 = D70;
    }

    public void setNGAYBC(Date NGAYBC) {
        this.NGAYBC = NGAYBC;
    }

   

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Thiet lap insert cho lop">
    @Override
    public String getSQLTypeName() throws SQLException {
        return ORACLE_OBJECT_TYPE;
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        setKHOA_1(stream.readString());
        setKHOA_2(stream.readString());
        setMACT(stream.readString());
        setTENCT(stream.readString());
        setMACN(stream.readString());
        setMAPGD(stream.readString());
        setMAXA(stream.readString());
        setMATHON(stream.readString());
        setMATO(stream.readString());
        setMAKH(stream.readString());
        setTT_GTRINH(stream.readString());
        setD1(stream.readString());
        setD2(stream.readString());
        setD3(stream.readString());
        setD4(stream.readString());
        setD5(stream.readString());
        setD6(stream.readString());
        setD7(stream.readString());
        setD8(stream.readString());
        setD9(stream.readString());
        setD10(stream.readString());
        setD11(stream.readString());
        setD12(stream.readString());
        setD13(stream.readString());
        setD14(stream.readString());
        setD15(stream.readString());
        setD16(stream.readString());
        setD17(stream.readString());
        setD18(stream.readString());
        setD19(stream.readString());
        setD20(stream.readString());
        setD21(stream.readString());
        setD22(stream.readString());
        setD23(stream.readString());
        setD24(stream.readString());
        setD25(stream.readString());
        setD26(stream.readString());
        setD27(stream.readString());
        setD28(stream.readString());
        setD29(stream.readString());
        setD30(stream.readString());
        setD31(stream.readString());
        setD32(stream.readString());
        setD33(stream.readString());
        setD34(stream.readString());
        setD35(stream.readString());
        setD36(stream.readString());
        setD37(stream.readString());
        setD38(stream.readString());
        setD39(stream.readString());
        setD40(stream.readString());
        setD41(stream.readString());
        setD42(stream.readString());
        setD43(stream.readString());
        setD44(stream.readString());
        setD45(stream.readString());
        setD46(stream.readString());
        setD47(stream.readString());
        setD48(stream.readString());
        setD49(stream.readString());
        setD50(stream.readString());
        setD51(stream.readString());
        setD52(stream.readString());
        setD53(stream.readString());
        setD54(stream.readString());
        setD55(stream.readString());
        setD56(stream.readString());
        setD57(stream.readString());
        setD58(stream.readString());
        setD59(stream.readString());
        setD60(stream.readString());
        setD61(stream.readString());
        setD62(stream.readString());
        setD63(stream.readString());
        setD64(stream.readString());
        setD65(stream.readString());
        setD66(stream.readString());
        setD67(stream.readString());
        setD68(stream.readString());
        setD69(stream.readString());
        setD70(stream.readString());
        setNGAYBC(stream.readDate());
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(getKHOA_1());
        stream.writeString(getKHOA_2());
        stream.writeString(getMACT());
        stream.writeString(getTENCT());
        stream.writeString(getMACN());
        stream.writeString(getMAPGD());
        stream.writeString(getMAXA());
        stream.writeString(getMATHON());
        stream.writeString(getMATO());
        stream.writeString(getMAKH());
        stream.writeString(getTT_GTRINH());
        stream.writeString(getD1());
        stream.writeString(getD2());
        stream.writeString(getD3());
        stream.writeString(getD4());
        stream.writeString(getD5());
        stream.writeString(getD6());
        stream.writeString(getD7());
        stream.writeString(getD8());
        stream.writeString(getD9());
        stream.writeString(getD10());
        stream.writeString(getD11());
        stream.writeString(getD12());
        stream.writeString(getD13());
        stream.writeString(getD14());
        stream.writeString(getD15());
        stream.writeString(getD16());
        stream.writeString(getD17());
        stream.writeString(getD18());
        stream.writeString(getD19());
        stream.writeString(getD20());
        stream.writeString(getD21());
        stream.writeString(getD22());
        stream.writeString(getD23());
        stream.writeString(getD24());
        stream.writeString(getD25());
        stream.writeString(getD26());
        stream.writeString(getD27());
        stream.writeString(getD28());
        stream.writeString(getD29());
        stream.writeString(getD30());        
        stream.writeString(getD31());
        stream.writeString(getD32());
        stream.writeString(getD33());
        stream.writeString(getD34());
        stream.writeString(getD35());
        stream.writeString(getD36());
        stream.writeString(getD37());
        stream.writeString(getD38());
        stream.writeString(getD39());
        stream.writeString(getD40());
        stream.writeString(getD41());
        stream.writeString(getD42());
        stream.writeString(getD43());
        stream.writeString(getD44());
        stream.writeString(getD45());
        stream.writeString(getD46());
        stream.writeString(getD47());
        stream.writeString(getD48());
        stream.writeString(getD49());
        stream.writeString(getD50());
        stream.writeString(getD51());
        stream.writeString(getD52());
        stream.writeString(getD53());
        stream.writeString(getD54());
        stream.writeString(getD55());
        stream.writeString(getD56());
        stream.writeString(getD57());
        stream.writeString(getD58());
        stream.writeString(getD59());
        stream.writeString(getD60());
        stream.writeString(getD61());
        stream.writeString(getD62());
        stream.writeString(getD63());
        stream.writeString(getD64());
        stream.writeString(getD65());
        stream.writeString(getD66());
        stream.writeString(getD67());
        stream.writeString(getD68());
        stream.writeString(getD69());
        stream.writeString(getD70());
        stream.writeDate(getNGAYBC() != null ? new java.sql.Date(getNGAYBC().getTime()) : null);
    }
//</editor-fold>
 
}
