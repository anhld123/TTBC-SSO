/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.branch;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.Date;

/**
 *
 * @author LION
 */
public class DULIEU_NT_CN implements SQLData {

    public static final String ORACLE_OBJECT_TYPE = "TYPE_DULIEU_NT_CN";
    public static final String ORACLE_TABLE_TYPE = "TAB_DULIEU_NT_CN";

    public static DULIEU_NT_CN newInstance() {
        return new DULIEU_NT_CN();
    }
    //<editor-fold defaultstate="collapsed" desc="Khao bao bien">
    private String KHOA;
    private int THUTU;
    private String TT_HIENTHI;
    private String MA;
    private String TEN;
    private Date NGAYBC;
    private int NAMBC;
    private String MAPGD;
    private String CO_TONGHOP;
    private String MACN;
    private String NGUOI_NHAP;
    private Date NGAY_NHAP;
    private String NGUOI_DUYET;
    private Date NGAY_DUYET;
    private String THAMSO_1;
    private String THAMSO_2;
    private String THAMSO_3;
    private String THAMSO_4;
    private String THAMSO_5;
    private String THAMSO_6;
    private String THAMSO_7;
    private String THAMSO_8;
    private String THAMSO_9;
    private String THAMSO_10;
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
    private String NHAPTAY;
    private String FONTFORMAT;
    private int KIEUIN;
    private String KIEUDULIEU;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Phuong thuc get/set">
    public String getKHOA() {
        return KHOA;
    }

    public void setKHOA(String KHOA) {
        this.KHOA = KHOA;
    }

    public int getTHUTU() {
        return THUTU;
    }

    public void setTHUTU(int THUTU) {
        this.THUTU = THUTU;
    }

    public String getTT_HIENTHI() {
        return TT_HIENTHI;
    }

    public void setTT_HIENTHI(String TT_HIENTHI) {
        this.TT_HIENTHI = TT_HIENTHI;
    }

    public String getMA() {
        return MA;
    }

    public void setMA(String MA) {
        this.MA = MA;
    }

    public String getTEN() {
        return TEN;
    }

    public void setTEN(String TEN) {
        this.TEN = TEN;
    }

    public Date getNGAYBC() {
        return NGAYBC;
    }

    public void setNGAYBC(Date NGAYBC) {
        this.NGAYBC = NGAYBC;
    }

    public int getNAMBC() {
        return NAMBC;
    }

    public void setNAMBC(int NAMBC) {
        this.NAMBC = NAMBC;
    }

    public String getMAPGD() {
        return MAPGD;
    }

    public void setMAPGD(String MAPGD) {
        this.MAPGD = MAPGD;
    }

    public String getCO_TONGHOP() {
        return CO_TONGHOP;
    }

    public void setCO_TONGHOP(String CO_TONGHOP) {
        this.CO_TONGHOP = CO_TONGHOP;
    }

    public String getMACN() {
        return MACN;
    }

    public void setMACN(String MACN) {
        this.MACN = MACN;
    }

    public String getNGUOI_NHAP() {
        return NGUOI_NHAP;
    }

    public void setNGUOI_NHAP(String NGUOI_NHAP) {
        this.NGUOI_NHAP = NGUOI_NHAP;
    }

    public Date getNGAY_NHAP() {
        return NGAY_NHAP;
    }

    public void setNGAY_NHAP(Date NGAY_NHAP) {
        this.NGAY_NHAP = NGAY_NHAP;
    }

    public String getNGUOI_DUYET() {
        return NGUOI_DUYET;
    }

    public void setNGUOI_DUYET(String NGUOI_DUYET) {
        this.NGUOI_DUYET = NGUOI_DUYET;
    }

    public Date getNGAY_DUYET() {
        return NGAY_DUYET;
    }

    public void setNGAY_DUYET(Date NGAY_DUYET) {
        this.NGAY_DUYET = NGAY_DUYET;
    }

    public String getTHAMSO_1() {
        return THAMSO_1;
    }

    public void setTHAMSO_1(String THAMSO_1) {
        this.THAMSO_1 = THAMSO_1;
    }

    public String getTHAMSO_2() {
        return THAMSO_2;
    }

    public void setTHAMSO_2(String THAMSO_2) {
        this.THAMSO_2 = THAMSO_2;
    }

    public String getTHAMSO_3() {
        return THAMSO_3;
    }

    public void setTHAMSO_3(String THAMSO_3) {
        this.THAMSO_3 = THAMSO_3;
    }

    public String getTHAMSO_4() {
        return THAMSO_4;
    }

    public void setTHAMSO_4(String THAMSO_4) {
        this.THAMSO_4 = THAMSO_4;
    }

    public String getTHAMSO_5() {
        return THAMSO_5;
    }

    public void setTHAMSO_5(String THAMSO_5) {
        this.THAMSO_5 = THAMSO_5;
    }

    public String getTHAMSO_6() {
        return THAMSO_6;
    }

    public void setTHAMSO_6(String THAMSO_6) {
        this.THAMSO_6 = THAMSO_6;
    }

    public String getTHAMSO_7() {
        return THAMSO_7;
    }

    public void setTHAMSO_7(String THAMSO_7) {
        this.THAMSO_7 = THAMSO_7;
    }

    public String getTHAMSO_8() {
        return THAMSO_8;
    }

    public void setTHAMSO_8(String THAMSO_8) {
        this.THAMSO_8 = THAMSO_8;
    }

    public String getTHAMSO_9() {
        return THAMSO_9;
    }

    public void setTHAMSO_9(String THAMSO_9) {
        this.THAMSO_9 = THAMSO_9;
    }

    public String getTHAMSO_10() {
        return THAMSO_10;
    }

    public void setTHAMSO_10(String THAMSO_10) {
        this.THAMSO_10 = THAMSO_10;
    }

    public String getD1() {
        return D1;
    }

    public void setD1(String D1) {
        this.D1 = D1;
    }

    public String getD2() {
        return D2;
    }

    public void setD2(String D2) {
        this.D2 = D2;
    }

    public String getD3() {
        return D3;
    }

    public void setD3(String D3) {
        this.D3 = D3;
    }

    public String getD4() {
        return D4;
    }

    public void setD4(String D4) {
        this.D4 = D4;
    }

    public String getD5() {
        return D5;
    }

    public void setD5(String D5) {
        this.D5 = D5;
    }

    public String getD6() {
        return D6;
    }

    public void setD6(String D6) {
        this.D6 = D6;
    }

    public String getD7() {
        return D7;
    }

    public void setD7(String D7) {
        this.D7 = D7;
    }

    public String getD8() {
        return D8;
    }

    public void setD8(String D8) {
        this.D8 = D8;
    }

    public String getD9() {
        return D9;
    }

    public void setD9(String D9) {
        this.D9 = D9;
    }

    public String getD10() {
        return D10;
    }

    public void setD10(String D10) {
        this.D10 = D10;
    }

    public String getD11() {
        return D11;
    }

    public void setD11(String D11) {
        this.D11 = D11;
    }

    public String getD12() {
        return D12;
    }

    public void setD12(String D12) {
        this.D12 = D12;
    }

    public String getD13() {
        return D13;
    }

    public void setD13(String D13) {
        this.D13 = D13;
    }

    public String getD14() {
        return D14;
    }

    public void setD14(String D14) {
        this.D14 = D14;
    }

    public String getD15() {
        return D15;
    }

    public void setD15(String D15) {
        this.D15 = D15;
    }

    public String getD16() {
        return D16;
    }

    public void setD16(String D16) {
        this.D16 = D16;
    }

    public String getD17() {
        return D17;
    }

    public void setD17(String D17) {
        this.D17 = D17;
    }

    public String getD18() {
        return D18;
    }

    public void setD18(String D18) {
        this.D18 = D18;
    }

    public String getD19() {
        return D19;
    }

    public void setD19(String D19) {
        this.D19 = D19;
    }

    public String getD20() {
        return D20;
    }

    public void setD20(String D20) {
        this.D20 = D20;
    }

    public String getD21() {
        return D21;
    }

    public void setD21(String D21) {
        this.D21 = D21;
    }

    public String getD22() {
        return D22;
    }

    public void setD22(String D22) {
        this.D22 = D22;
    }

    public String getD23() {
        return D23;
    }

    public void setD23(String D23) {
        this.D23 = D23;
    }

    public String getD24() {
        return D24;
    }

    public void setD24(String D24) {
        this.D24 = D24;
    }

    public String getD25() {
        return D25;
    }

    public void setD25(String D25) {
        this.D25 = D25;
    }

    public String getD26() {
        return D26;
    }

    public void setD26(String D26) {
        this.D26 = D26;
    }

    public String getD27() {
        return D27;
    }

    public void setD27(String D27) {
        this.D27 = D27;
    }

    public String getD28() {
        return D28;
    }

    public void setD28(String D28) {
        this.D28 = D28;
    }

    public String getD29() {
        return D29;
    }

    public void setD29(String D29) {
        this.D29 = D29;
    }

    public String getD30() {
        return D30;
    }

    public void setD30(String D30) {
        this.D30 = D30;
    }

    public String getD31() {
        return D31;
    }

    public void setD31(String D31) {
        this.D31 = D31;
    }

    public String getD32() {
        return D32;
    }

    public void setD32(String D32) {
        this.D32 = D32;
    }

    public String getD33() {
        return D33;
    }

    public void setD33(String D33) {
        this.D33 = D33;
    }

    public String getD34() {
        return D34;
    }

    public void setD34(String D34) {
        this.D34 = D34;
    }

    public String getD35() {
        return D35;
    }

    public void setD35(String D35) {
        this.D35 = D35;
    }

    public String getD36() {
        return D36;
    }

    public void setD36(String D36) {
        this.D36 = D36;
    }

    public String getD37() {
        return D37;
    }

    public void setD37(String D37) {
        this.D37 = D37;
    }

    public String getD38() {
        return D38;
    }

    public void setD38(String D38) {
        this.D38 = D38;
    }

    public String getD39() {
        return D39;
    }

    public void setD39(String D39) {
        this.D39 = D39;
    }

    public String getD40() {
        return D40;
    }

    public void setD40(String D40) {
        this.D40 = D40;
    }

    public String getD41() {
        return D41;
    }

    public void setD41(String D41) {
        this.D41 = D41;
    }

    public String getD42() {
        return D42;
    }

    public void setD42(String D42) {
        this.D42 = D42;
    }

    public String getD43() {
        return D43;
    }

    public void setD43(String D43) {
        this.D43 = D43;
    }

    public String getD44() {
        return D44;
    }

    public void setD44(String D44) {
        this.D44 = D44;
    }

    public String getD45() {
        return D45;
    }

    public void setD45(String D45) {
        this.D45 = D45;
    }

    public String getD46() {
        return D46;
    }

    public void setD46(String D46) {
        this.D46 = D46;
    }

    public String getD47() {
        return D47;
    }

    public void setD47(String D47) {
        this.D47 = D47;
    }

    public String getD48() {
        return D48;
    }

    public void setD48(String D48) {
        this.D48 = D48;
    }

    public String getD49() {
        return D49;
    }

    public void setD49(String D49) {
        this.D49 = D49;
    }

    public String getD50() {
        return D50;
    }

    public void setD50(String D50) {
        this.D50 = D50;
    }

    public String getNHAPTAY() {
        return NHAPTAY;
    }

    public void setNHAPTAY(String NHAPTAY) {
        this.NHAPTAY = NHAPTAY;
    }

    public String getFONTFORMAT() {
        return FONTFORMAT;
    }

    public void setFONTFORMAT(String FONTFORMAT) {
        this.FONTFORMAT = FONTFORMAT;
    }

    public int getKIEUIN() {
        return KIEUIN;
    }

    public void setKIEUIN(int KIEUIN) {
        this.KIEUIN = KIEUIN;
    }

    public String getKIEUDULIEU() {
        return KIEUDULIEU;
    }

    public void setKIEUDULIEU(String KIEUDULIEU) {
        this.KIEUDULIEU = KIEUDULIEU;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Thiet lap insert cho lop">
    @Override
    public String getSQLTypeName() throws SQLException {
        return ORACLE_OBJECT_TYPE;
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        setKHOA(stream.readString());
        setTHUTU(stream.readInt());
        setTT_HIENTHI(stream.readString());
        setMA(stream.readString());
        setTEN(stream.readString());
        setNGAYBC(stream.readDate());
        setNAMBC(stream.readInt());
        setMAPGD(stream.readString());
        setCO_TONGHOP(stream.readString());
        setMACN(stream.readString());
        setNGUOI_NHAP(stream.readString());
        setNGAY_NHAP(stream.readDate());
        setNGUOI_DUYET(stream.readString());
        setNGAY_DUYET(stream.readDate());
        setTHAMSO_1(stream.readString());
        setTHAMSO_2(stream.readString());
        setTHAMSO_3(stream.readString());
        setTHAMSO_4(stream.readString());
        setTHAMSO_5(stream.readString());
        setTHAMSO_6(stream.readString());
        setTHAMSO_7(stream.readString());
        setTHAMSO_8(stream.readString());
        setTHAMSO_9(stream.readString());
        setTHAMSO_10(stream.readString());
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
        setNHAPTAY(stream.readString());
        setFONTFORMAT(stream.readString());
        setKIEUIN(stream.readInt());
        setKIEUDULIEU(stream.readNString());
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(getKHOA());
        stream.writeInt(getTHUTU());
        stream.writeString(getTT_HIENTHI());
        stream.writeString(getMA());
        stream.writeString(getTEN());
        stream.writeDate(getNGAYBC() != null ? new java.sql.Date(getNGAYBC().getTime()) : null);
        stream.writeInt(getNAMBC());
        stream.writeString(getMAPGD());
        stream.writeString(getCO_TONGHOP());
        stream.writeString(getMACN());
        stream.writeString(getNGUOI_NHAP());
        stream.writeDate(getNGAY_NHAP() != null ? new java.sql.Date(getNGAY_NHAP().getTime()) : null);
        stream.writeString(getNGUOI_DUYET());
        stream.writeDate(getNGAY_DUYET() != null ? new java.sql.Date(getNGAY_DUYET().getTime()) : null);
        stream.writeString(getTHAMSO_1());
        stream.writeString(getTHAMSO_2());
        stream.writeString(getTHAMSO_3());
        stream.writeString(getTHAMSO_4());
        stream.writeString(getTHAMSO_5());
        stream.writeString(getTHAMSO_6());
        stream.writeString(getTHAMSO_7());
        stream.writeString(getTHAMSO_8());
        stream.writeString(getTHAMSO_9());
        stream.writeString(getTHAMSO_10());
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
        stream.writeString(getNHAPTAY());
        stream.writeString(getFONTFORMAT());
        stream.writeInt(getKIEUIN());
        stream.writeString(getKIEUDULIEU());
    }
//</editor-fold>

    @Override
    public String toString() {
        return getKHOA() + " " + getTHUTU() + " " + getTT_HIENTHI() + " " + getMA() + " " + 
                getTEN() + " " + getNGAYBC() + " " + getNAMBC() + " " + getMAPGD() + " " + 
                getCO_TONGHOP() + " " + getMACN() + " " + getNGUOI_NHAP() + " " + getNGAY_NHAP() + " " + 
                getNGUOI_DUYET() + " " + getNGAY_DUYET() + " " + getTHAMSO_1() + " " + getTHAMSO_2() + " " + 
                getTHAMSO_3() + " " + getTHAMSO_4() + " " + getTHAMSO_5() + " " + getTHAMSO_6() + " " + 
                getTHAMSO_7() + " " + getTHAMSO_8() + " " + getTHAMSO_9() + " " + getTHAMSO_10() + " " + 
                getD1() + " " + getD2() + " " + getD3() + " " + getD4() + " " + getD5() + " " + getD6() + " " + 
                getD7() + " " + getD8() + " " + getD9() + " " + getD10() + " " + getD11() + " " + getD12() + " " + 
                getD13() + " " + getD14() + " " + getD15() + " " + getD16() + " " + getD17() + " " + 
                getD18() + " " + getD19() + " " + getD20() + " " + getD21() + " " + getD22() + " " + 
                getD23() + " " + getD24() + " " + getD25() + " " + getD26() + " " + getD27() + " " + 
                getD28() + " " + getD29() + " " + getD30() + " " + getD31() + " " + getD32() + " " + 
                getD33() + " " + getD34() + " " + getD35() + " " + getD36() + " " + getD37() + " " + 
                getD38() + " " + getD39() + " " + getD40() + " " + getD41() + " " + getD42() + " " + 
                getD43() + " " + getD44() + " " + getD45() + " " + getD46() + " " + getD47() + " " + 
                getD48() + " " + getD49() + " " + getD50() + " " + getNHAPTAY() + " " + getFONTFORMAT() + " " + 
                getKIEUIN() + " " + getKIEUDULIEU();
    }
}
