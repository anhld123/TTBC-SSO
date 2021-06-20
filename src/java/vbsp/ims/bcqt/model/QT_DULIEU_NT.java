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
 * @author LION
 */
public class QT_DULIEU_NT implements SQLData {

    public static final String ORACLE_OBJECT_TYPE = "TYPE_DULIEU_NT";
    public static final String ORACLE_TABLE_TYPE = "TAB_DULIEU_NT";

    public static QT_DULIEU_NT newInstance() {
        return new QT_DULIEU_NT();
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
    private String NHAPTAY;
    private String FONTFORMAT;

    
    private int KIEUIN;
    private String CAP;
    private String CO_CONGCAP;
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Phuong thuc get/set">
    
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

    public String getKHOA() {
        return KHOA;
    }

    public String getCO_CONGCAP() {
        return CO_CONGCAP;
    }

    public void setCO_CONGCAP(String CO_CONGCAP) {
        this.CO_CONGCAP = CO_CONGCAP;
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

    public int getKIEUIN() {
        return KIEUIN;
    }

    public void setKIEUIN(int KIEUIN) {
        this.KIEUIN = KIEUIN;
    }

    public String getCAP() {
        return CAP;
    }

    public void setCAP(String CAP) {
        this.CAP = CAP;
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
        setNHAPTAY(stream.readString());
        setFONTFORMAT(stream.readString());
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
        stream.writeString(getNHAPTAY());
        stream.writeString(getFONTFORMAT());
    }
//</editor-fold>
    
    public static class saveDulieuNT {
        
        
        public String D2;
        public String D13;
        public String D14;

        public String getD2() {
            return D2;
        }

        public void setD2(String D2) {
            this.D2 = D2;
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
        
    }
    
    public static class saveDulieuNT_Phi {
        public String MA;
        public String D1;
        public String D2;
        public String D3;
        public String D4;

        public String getD4() {
            return D4;
        }

        public void setD4(String D4) {
            this.D4 = D4;
        }
                
        public String getMA() {
            return MA;
        }

        public void setMA(String MA) {
            this.MA = MA;
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
    }
}
