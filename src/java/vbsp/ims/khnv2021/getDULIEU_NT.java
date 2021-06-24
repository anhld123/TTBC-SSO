package vbsp.ims.khnv2021;

import java.sql.ResultSet;
import java.sql.SQLException;
import vbsp.ims.bcqt.model.DULIEU_NT;

/**
 *
 * @author Nguyễn Phú Vinh
 */
public class getDULIEU_NT {

    public DULIEU_NT getData(DULIEU_NT obj, ResultSet rs) throws SQLException {
        obj.setKHOA(rs.getString("KHOA"));
        obj.setTHUTU(rs.getInt("THUTU"));
        obj.setTT_HIENTHI(rs.getString("TT_HIENTHI"));
        obj.setMA(rs.getString("MA"));
        obj.setTEN(rs.getString("TEN"));
        obj.setNGAYBC(rs.getDate("NGAYBC"));
        obj.setNAMBC(rs.getInt("NAMBC"));
        obj.setMAPGD(rs.getString("MAPGD"));
        obj.setCO_TONGHOP(rs.getString("CO_TONGHOP"));
        obj.setMACN(rs.getString("MACN"));
        obj.setNGUOI_NHAP(rs.getString("NGUOI_NHAP"));
        obj.setNGAY_NHAP(rs.getDate("NGAY_NHAP"));
        obj.setNGUOI_DUYET(rs.getString("NGUOI_DUYET"));
        obj.setNGAY_DUYET(rs.getDate("NGAY_DUYET"));
        obj.setD1(rs.getString("D1"));
        obj.setD2(rs.getString("D2"));
        obj.setD3(rs.getString("D3"));
        obj.setD4(rs.getString("D4"));
        obj.setD5(rs.getString("D5"));
        obj.setD6(rs.getString("D6"));
        obj.setD7(rs.getString("D7"));
        obj.setD8(rs.getString("D8"));
        obj.setD9(rs.getString("D9"));
        obj.setD10(rs.getString("D10"));
        obj.setD11(rs.getString("D11"));
        obj.setD12(rs.getString("D12"));
        obj.setD13(rs.getString("D13"));
        obj.setD14(rs.getString("D14"));
        obj.setD15(rs.getString("D15"));
        obj.setD16(rs.getString("D16"));
        obj.setD17(rs.getString("D17"));
        obj.setD18(rs.getString("D18"));
        obj.setD19(rs.getString("D19"));
        obj.setD20(rs.getString("D20"));
        obj.setD21(rs.getString("D21"));
        obj.setD22(rs.getString("D22"));
        obj.setD23(rs.getString("D23"));
        obj.setD24(rs.getString("D24"));
        obj.setD25(rs.getString("D25"));
        obj.setD26(rs.getString("D26"));
        obj.setD27(rs.getString("D27"));
        obj.setD28(rs.getString("D28"));
        obj.setD29(rs.getString("D29"));
        obj.setD30(rs.getString("D30"));
        obj.setD31(rs.getString("D31"));
        obj.setD32(rs.getString("D32"));
        obj.setD33(rs.getString("D33"));
        obj.setD34(rs.getString("D34"));
        obj.setD35(rs.getString("D35"));
        obj.setD36(rs.getString("D36"));
        obj.setD37(rs.getString("D37"));
        obj.setD38(rs.getString("D38"));
        obj.setD39(rs.getString("D39"));
        obj.setD40(rs.getString("D40"));
        obj.setD41(rs.getString("D41"));
        obj.setD42(rs.getString("D42"));
        obj.setD43(rs.getString("D43"));
        obj.setD44(rs.getString("D44"));
        obj.setD45(rs.getString("D45"));
        obj.setD46(rs.getString("D46"));
        obj.setD47(rs.getString("D47"));
        obj.setD48(rs.getString("D48"));
        obj.setD49(rs.getString("D49"));
        obj.setD50(rs.getString("D50"));
        obj.setNHAPTAY(rs.getString("NHAPTAY"));
        obj.setFONTFORMAT(rs.getString("FONTFORMAT"));
        obj.setKIEUIN(rs.getInt("KIEUIN"));
        return obj;
    }
}
