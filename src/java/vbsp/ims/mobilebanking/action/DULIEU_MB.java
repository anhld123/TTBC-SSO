/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.mobilebanking.action;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.Date;

/**
 *
 * @author LION
 */
public class DULIEU_MB implements SQLData {

    public static final String ORACLE_OBJECT_TYPE = "TYPE_MB";
    public static final String ORACLE_TABLE_TYPE = "TAB_MB";

    public static DULIEU_MB newInstance() {
        return new DULIEU_MB();
    }
    //<editor-fold defaultstate="collapsed" desc="Khao bao bien">
    private String KHOA;
    private Date NGAYBC;
    private String MATINH;
    private String TENTINH;
    private String MAHUYEN;
    private String TENHUYEN;
    
    
    private String MAPGD;
    private String TENPGD;
    private String MAXA;
    private String TENXA;
    private String MATHON;
    private String TENTHON;
    private String MATXN;    
    private String TENTXN;  
    private String NGUOI_TAO;
    private Date NGAY_TAO;
    
    private String NGUOI_SUA_GN;
    private Date NGAY_SUA_GN;
    private String TRANGTHAI;
    
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
    
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Phuong thuc get/set">

    public String getKHOA() {
        return KHOA;
    }

    public void setKHOA(String KHOA) {
        this.KHOA = KHOA;
    }

    public Date getNGAYBC() {
        return NGAYBC;
    }

    public void setNGAYBC(Date NGAYBC) {
        this.NGAYBC = NGAYBC;
    }

    public String getMATINH() {
        return MATINH;
    }

    public void setMATINH(String MATINH) {
        this.MATINH = MATINH;
    }

    public String getTENTINH() {
        return TENTINH;
    }

    public void setTENTINH(String TENTINH) {
        this.TENTINH = TENTINH;
    }

    public String getMAHUYEN() {
        return MAHUYEN;
    }

    public void setMAHUYEN(String MAHUYEN) {
        this.MAHUYEN = MAHUYEN;
    }

    public String getTENHUYEN() {
        return TENHUYEN;
    }

    public void setTENHUYEN(String TENHUYEN) {
        this.TENHUYEN = TENHUYEN;
    }

    public String getMAPGD() {
        return MAPGD;
    }

    public void setMAPGD(String MAPGD) {
        this.MAPGD = MAPGD;
    }

    public String getTENPGD() {
        return TENPGD;
    }

    public void setTENPGD(String TENPGD) {
        this.TENPGD = TENPGD;
    }

    public String getMAXA() {
        return MAXA;
    }

    public void setMAXA(String MAXA) {
        this.MAXA = MAXA;
    }

    public String getTENXA() {
        return TENXA;
    }

    public void setTENXA(String TENXA) {
        this.TENXA = TENXA;
    }

    public String getMATHON() {
        return MATHON;
    }

    public void setMATHON(String MATHON) {
        this.MATHON = MATHON;
    }

    public String getTENTHON() {
        return TENTHON;
    }

    public void setTENTHON(String TENTHON) {
        this.TENTHON = TENTHON;
    }

    public String getMATXN() {
        return MATXN;
    }

    public void setMATXN(String MATXN) {
        this.MATXN = MATXN;
    }

    public String getTENTXN() {
        return TENTXN;
    }

    public void setTENTXN(String TENTXN) {
        this.TENTXN = TENTXN;
    }

    public String getNGUOI_TAO() {
        return NGUOI_TAO;
    }

    public void setNGUOI_TAO(String NGUOI_TAO) {
        this.NGUOI_TAO = NGUOI_TAO;
    }

    public Date getNGAY_TAO() {
        return NGAY_TAO;
    }

    public void setNGAY_TAO(Date NGAY_TAO) {
        this.NGAY_TAO = NGAY_TAO;
    }

    public String getNGUOI_SUA_GN() {
        return NGUOI_SUA_GN;
    }

    public void setNGUOI_SUA_GN(String NGUOI_SUA_GN) {
        this.NGUOI_SUA_GN = NGUOI_SUA_GN;
    }

    public Date getNGAY_SUA_GN() {
        return NGAY_SUA_GN;
    }

    public void setNGAY_SUA_GN(Date NGAY_SUA_GN) {
        this.NGAY_SUA_GN = NGAY_SUA_GN;
    }

    public String getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(String TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
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
    
    
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Thiet lap insert cho lop">
    @Override
    public String getSQLTypeName() throws SQLException {
        return ORACLE_OBJECT_TYPE;
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        setKHOA(stream.readString());
        setNGAYBC(stream.readDate());
        
        setMATINH(stream.readString());
        setTENTINH(stream.readString());
        setMAHUYEN(stream.readString());
        setTENHUYEN(stream.readString());
        setMAPGD(stream.readString());
        setTENPGD(stream.readString());
        setMAXA(stream.readString());
        setTENXA(stream.readString());  
        
        setMATHON(stream.readString());
        setTENTHON(stream.readString());
        setMATXN(stream.readString());
        setTENTXN(stream.readString());
        setNGUOI_TAO(stream.readString());
        setNGAY_TAO(stream.readDate());
        setNGUOI_SUA_GN(stream.readString());  
        setNGAY_SUA_GN(stream.readDate());
        setTRANGTHAI(stream.readString());  
        
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
        
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(getKHOA());
        stream.writeDate(getNGAYBC() != null ? new java.sql.Date(getNGAYBC().getTime()) : null);
        
        stream.writeString(getMATINH());
        stream.writeString(getTENTINH());
        stream.writeString(getMAHUYEN());
        stream.writeString(getTENHUYEN());
        stream.writeString(getMAPGD());
        stream.writeString(getTENPGD());
        stream.writeString(getMAXA());
        stream.writeString(getTENXA());
        stream.writeString(getMATHON());
        stream.writeString(getTENTHON());
        stream.writeString(getMATXN());
        stream.writeString(getTENTXN());
        
        stream.writeString(getNGUOI_TAO());
        stream.writeDate(getNGAY_TAO()!= null ? new java.sql.Date(getNGAY_TAO().getTime()) : null);
        stream.writeString(getNGUOI_SUA_GN());
        stream.writeDate(getNGAY_SUA_GN()!= null ? new java.sql.Date(getNGAY_SUA_GN().getTime()) : null);
        stream.writeString(getTRANGTHAI());
        

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
        
    }
//</editor-fold>
    
    public static class saveDulieuMB {
        
        
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
    
    
}
