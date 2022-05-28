/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action.ktktnb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.action.ktktnb.DULIEU_NT_TQ;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.DuLieuNTService;

/**
 *
 * @author NGUYEN PHU VINH
 */
public class PankService {

    private String sGhiChu;

    public PankService() {
    }

    public List<DULIEU_NT_TQ> queryDataByTem(String txtTuNgay, String txtDenNgay, String chkTongHop, String sUser, String sGrade, String sMaBc) {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        List<DULIEU_NT_TQ> lstData = new ArrayList<>();
        try {
            CallableStatement calstatement = null;
            String strStoreproce = "{call PKG_KTKSNB_PAKN.PROC_PAKN_QUERY(?,?,?,?,?,?,?,?)}";
            ResultSet Rset = null;
            try {
                calstatement = con.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, sUser);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, txtTuNgay);
                calstatement.setString(4, txtDenNgay);
                calstatement.setString(5, chkTongHop);
                calstatement.setString(6, sMaBc);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                sGhiChu = (String) calstatement.getObject(7);
                Rset = (ResultSet) calstatement.getObject(8);
                while (Rset.next()) {
                    DULIEU_NT_TQ value = DULIEU_NT_TQ.newInstance();
                    value.setKHOA(Rset.getString("KHOA"));
                    value.setMA(Rset.getString("MA"));
                    value.setTEN(Rset.getString("TEN"));
                    value.setNGAYBC(Rset.getDate("NGAYBC"));
                    value.setMAPGD(Rset.getString("MAPGD"));
                    value.setMACN(Rset.getString("MACN"));
                    value.setD1(Rset.getString("D1"));
                    value.setD2(Rset.getString("D2"));
                    value.setD3(Rset.getString("D3"));
                    value.setD4(Rset.getString("D4"));
                    value.setD5(Rset.getString("D5"));
                    value.setD6(Rset.getString("D6"));
                    value.setD7(Rset.getString("D7"));
                    value.setD8(Rset.getString("D8"));
                    value.setD9(Rset.getString("D9"));
                    value.setD10(Rset.getString("D10"));
                    value.setD11(Rset.getString("D11"));
                    value.setD12(Rset.getString("D12"));
                    value.setD13(Rset.getString("D13"));
                    value.setD14(Rset.getString("D14"));
                    value.setD15(Rset.getString("D15"));
                    value.setD16(Rset.getString("D16"));
                    value.setD17(Rset.getString("D17"));
                    value.setD18(Rset.getString("D18"));
                    value.setD19(Rset.getString("D19"));
                    value.setD20(Rset.getString("D20"));
                    value.setD21(Rset.getString("D21"));
                    value.setD22(Rset.getString("D22"));
                    value.setD23(Rset.getString("D23"));
                    value.setD24(Rset.getString("D24"));
                    value.setD25(Rset.getString("D25"));
                    value.setD26(Rset.getString("D26"));
                    value.setD27(Rset.getString("D27"));
                    value.setD28(Rset.getString("D28"));
                    value.setD29(Rset.getString("D29"));
                    value.setD30(Rset.getString("D30"));
                    value.setD31(Rset.getString("D31"));
                    value.setD32(Rset.getString("D32"));
                    value.setD33(Rset.getString("D33"));
                    value.setD34(Rset.getString("D34"));
                    value.setD35(Rset.getString("D35"));
                    value.setD36(Rset.getString("D36"));
                    value.setD37(Rset.getString("D37"));
                    value.setD38(Rset.getString("D38"));
                    value.setD39(Rset.getString("D39"));
                    value.setD40(Rset.getString("D40"));
                    value.setD41(Rset.getString("D41"));
                    value.setD42(Rset.getString("D42"));
                    value.setD43(Rset.getString("D43"));
                    value.setD44(Rset.getString("D44"));
                    value.setD45(Rset.getString("D45"));
                    value.setD46(Rset.getString("D46"));
                    value.setD47(Rset.getString("D47"));
                    value.setD48(Rset.getString("D48"));
                    value.setD49(Rset.getString("D49"));
                    value.setD50(Rset.getString("D50"));
                    lstData.add(value);
                }

                if (Rset != null) {
                    Rset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }

            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + "getDataByTem -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataByTem " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataByTem -> " + e.getMessage());
        }
        return lstData;
    }

    public List<DULIEU_NT_TQ> getDataByTem(String sNgaybc, String sUser, String sGrade, String sMaBc) {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        List<DULIEU_NT_TQ> lstData = new ArrayList<>();
        try {
            CallableStatement calstatement = null;
            String strStoreproce = "{call PKG_KTKSNB_PAKN.PROC_PAKN_GETDATA(?,?,?,?,?,?)}";
            ResultSet Rset = null;
            try {
                calstatement = con.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, sUser);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, sNgaybc);
                calstatement.setString(4, sMaBc);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                sGhiChu = (String) calstatement.getObject(5);
                Rset = (ResultSet) calstatement.getObject(6);
                while (Rset.next()) {
                    DULIEU_NT_TQ value = DULIEU_NT_TQ.newInstance();
                    value.setKHOA(Rset.getString("KHOA"));
                    value.setMA(Rset.getString("MA"));
                    value.setTEN(Rset.getString("TEN"));
                    value.setNGAYBC(Rset.getDate("NGAYBC"));
                    value.setMAPGD(Rset.getString("MAPGD"));
                    value.setMACN(Rset.getString("MACN"));
                    value.setD1(Rset.getString("D1"));
                    value.setD2(Rset.getString("D2"));
                    value.setD3(Rset.getString("D3"));
                    value.setD4(Rset.getString("D4"));
                    value.setD5(Rset.getString("D5"));
                    value.setD6(Rset.getString("D6"));
                    value.setD7(Rset.getString("D7"));
                    value.setD8(Rset.getString("D8"));
                    value.setD9(Rset.getString("D9"));
                    value.setD10(Rset.getString("D10"));
                    value.setD11(Rset.getString("D11"));
                    value.setD12(Rset.getString("D12"));
                    value.setD13(Rset.getString("D13"));
                    value.setD14(Rset.getString("D14"));
                    value.setD15(Rset.getString("D15"));
                    value.setD16(Rset.getString("D16"));
                    value.setD17(Rset.getString("D17"));
                    value.setD18(Rset.getString("D18"));
                    value.setD19(Rset.getString("D19"));
                    value.setD20(Rset.getString("D20"));
                    value.setD21(Rset.getString("D21"));
                    value.setD22(Rset.getString("D22"));
                    value.setD23(Rset.getString("D23"));
                    value.setD24(Rset.getString("D24"));
                    value.setD25(Rset.getString("D25"));
                    value.setD26(Rset.getString("D26"));
                    value.setD27(Rset.getString("D27"));
                    value.setD28(Rset.getString("D28"));
                    value.setD29(Rset.getString("D29"));
                    value.setD30(Rset.getString("D30"));
                    value.setD31(Rset.getString("D31"));
                    value.setD32(Rset.getString("D32"));
                    value.setD33(Rset.getString("D33"));
                    value.setD34(Rset.getString("D34"));
                    value.setD35(Rset.getString("D35"));
                    value.setD36(Rset.getString("D36"));
                    value.setD37(Rset.getString("D37"));
                    value.setD38(Rset.getString("D38"));
                    value.setD39(Rset.getString("D39"));
                    value.setD40(Rset.getString("D40"));
                    value.setD41(Rset.getString("D41"));
                    value.setD42(Rset.getString("D42"));
                    value.setD43(Rset.getString("D43"));
                    value.setD44(Rset.getString("D44"));
                    value.setD45(Rset.getString("D45"));
                    value.setD46(Rset.getString("D46"));
                    value.setD47(Rset.getString("D47"));
                    value.setD48(Rset.getString("D48"));
                    value.setD49(Rset.getString("D49"));
                    value.setD50(Rset.getString("D50"));
                    lstData.add(value);
                }

                if (Rset != null) {
                    Rset.close();
                }
                if (calstatement != null) {
                    calstatement.close();
                }

            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + "getDataByTem -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham getDataByTem " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " getDataByTem -> " + e.getMessage());
        }
        return lstData;
    }

    public String saveDataByTem(String sNgaybc, String sUser, String sGrade, String sMaBc, List<DULIEU_NT_TQ> ModelList) {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        String code = "";
        try {
            //------Chuyển dạng mảng thành Object của Oracle
            Object array[] = ModelList.toArray();
            ArrayDescriptor des = ArrayDescriptor.createDescriptor(DULIEU_NT_TQ.ORACLE_TABLE_TYPE, con);
            ARRAY array_to_pass = new ARRAY(des, con, array);
            //---------------------------------------------------------------------------
            CallableStatement calstatement = null;
            String strStoreproce = "{call PKG_KTKSNB_PAKN.PROC_PAKN_SAVEDATA(?,?,?,?,?,?)}";
            try {
                calstatement = con.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, sUser);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, sNgaybc);
                calstatement.setString(4, sMaBc);
                calstatement.setArray(5, array_to_pass);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.execute();
                code = (String) calstatement.getString(6);
                if (calstatement != null) {
                    calstatement.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
                CoreLogger.error(this.getClass().getName() + "saveDataByTem -> " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Loi trong ham saveDataByTem " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveDataByTem -> " + e.getMessage());
        }
        return code;
    }

    public void CallApiAddData(String sNgaybc, String sUser, String sGrade, String sMaBc, List<DULIEU_NT_TQ> ModelList) throws ParseException {

        DuLieuNTService service = new DuLieuNTService();
        SimpleDateFormat CvDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        ArrayList<DuLieuNTRow> lstInsert = new ArrayList<>();
        for (int i = 0; i < ModelList.size(); i++) {
            DuLieuNTRow item = new DuLieuNTRow();
            item.setKey(sMaBc);
            item.setCode(ModelList.get(i).getMA());
            item.setBranchCode(ModelList.get(i).getMACN());
            item.setPosCode(ModelList.get(i).getMAPGD());
            item.setName(ModelList.get(i).getTEN());
            item.setReportDate(CvDate.format(new SimpleDateFormat("dd/MM/yyyy").parse(sNgaybc)));
            item.setReportYear(Integer.parseInt(sNgaybc.substring(6, 10)));
            item.setPosFlag("S");
            item.setD1(ModelList.get(i).getD1());
            item.setD2(ModelList.get(i).getD2());
            item.setD3(ModelList.get(i).getD3());
            item.setD4(ModelList.get(i).getD4());
            item.setD5(ModelList.get(i).getD5());
            item.setD6(ModelList.get(i).getD6());
            item.setD7(ModelList.get(i).getD7());
            item.setD8(ModelList.get(i).getD8());
            item.setD9(ModelList.get(i).getD9());
            item.setD10(ModelList.get(i).getD10());
            item.setD11(ModelList.get(i).getD11());
            item.setD12(ModelList.get(i).getD12());
            item.setD13(ModelList.get(i).getD13());
            item.setD14(ModelList.get(i).getD14());
            item.setD15(ModelList.get(i).getD15());
            item.setD16(ModelList.get(i).getD16());
            item.setD17(ModelList.get(i).getD17());
            item.setD18(ModelList.get(i).getD18());
            item.setD19(ModelList.get(i).getD19());
            item.setD20(ModelList.get(i).getD20());
            item.setD21(ModelList.get(i).getD21());
            item.setD22(ModelList.get(i).getD22());
            item.setD23(ModelList.get(i).getD23());
            item.setD24(ModelList.get(i).getD24());
            item.setD25(ModelList.get(i).getD25());
            item.setD26(ModelList.get(i).getD26());
            item.setD27(ModelList.get(i).getD27());
            item.setD28(ModelList.get(i).getD28());
            item.setD29(ModelList.get(i).getD29());
            item.setD30(ModelList.get(i).getD30());
            item.setD31(ModelList.get(i).getD31());
            item.setD32(ModelList.get(i).getD32());
            item.setD33(ModelList.get(i).getD33());
            item.setD34(ModelList.get(i).getD34());
            item.setD35(ModelList.get(i).getD35());
            item.setD36(ModelList.get(i).getD36());
            item.setD37(ModelList.get(i).getD37());
            item.setD38(ModelList.get(i).getD38());
            item.setD39(ModelList.get(i).getD39());
            item.setD40(ModelList.get(i).getD40());
            item.setD41(ModelList.get(i).getD41());
            item.setD42(ModelList.get(i).getD42());
            item.setD43(ModelList.get(i).getD43());
            item.setD44(ModelList.get(i).getD44());
            item.setD45(ModelList.get(i).getD45());
            item.setD46(ModelList.get(i).getD46());
            item.setD47(ModelList.get(i).getD47());
            item.setD48(ModelList.get(i).getD48());
            item.setD49(ModelList.get(i).getD49());
            item.setD50(ModelList.get(i).getD50());
            lstInsert.add(item);
        }
        service.insertData(sUser, "", lstInsert);
    }

    public void CallApiUpdateData(String sNgaybc, String sUser, String sGrade, String sMaBc, List<DULIEU_NT_TQ> ModelList) throws ParseException {
        DuLieuNTService service = new DuLieuNTService();
        SimpleDateFormat CvDate = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat LsDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        ArrayList<DuLieuNTRow> lstInsert = new ArrayList<>();
        for (int i = 0; i < ModelList.size(); i++) {
            DuLieuNTRow item = new DuLieuNTRow();
            item.setKey(sMaBc);
            item.setCode(ModelList.get(i).getMA());
            item.setBranchCode(ModelList.get(i).getMACN());
            item.setPosCode(ModelList.get(i).getMAPGD());
            item.setName(ModelList.get(i).getTEN());
            item.setReportDate(LsDate.format(new SimpleDateFormat("dd/MM/yyyy").parse(sNgaybc)));
            item.setReportYear(Integer.parseInt(sNgaybc.substring(6, 10)));
            item.setPosFlag("S");
            item.setD1(ModelList.get(i).getD1());
            item.setD2(ModelList.get(i).getD2());
            item.setD3(ModelList.get(i).getD3());
            item.setD4(ModelList.get(i).getD4());
            item.setD5(ModelList.get(i).getD5());
            item.setD6(ModelList.get(i).getD6());
            item.setD7(ModelList.get(i).getD7());
            item.setD8(ModelList.get(i).getD8());
            item.setD9(ModelList.get(i).getD9());
            item.setD10(ModelList.get(i).getD10());
            item.setD11(ModelList.get(i).getD11());
            item.setD12(ModelList.get(i).getD12());
            item.setD13(ModelList.get(i).getD13());
            item.setD14(ModelList.get(i).getD14());
            item.setD15(ModelList.get(i).getD15());
            item.setD16(ModelList.get(i).getD16());
            item.setD17(ModelList.get(i).getD17());
            item.setD18(ModelList.get(i).getD18());
            item.setD19(ModelList.get(i).getD19());
            item.setD20(ModelList.get(i).getD20());
            item.setD21(ModelList.get(i).getD21());
            item.setD22(ModelList.get(i).getD22());
            item.setD23(ModelList.get(i).getD23());
            item.setD24(ModelList.get(i).getD24());
            item.setD25(ModelList.get(i).getD25());
            item.setD26(ModelList.get(i).getD26());
            item.setD27(ModelList.get(i).getD27());
            item.setD28(ModelList.get(i).getD28());
            item.setD29(ModelList.get(i).getD29());
            item.setD30(ModelList.get(i).getD30());
            item.setD31(ModelList.get(i).getD31());
            item.setD32(ModelList.get(i).getD32());
            item.setD33(ModelList.get(i).getD33());
            item.setD34(ModelList.get(i).getD34());
            item.setD35(ModelList.get(i).getD35());
            item.setD36(ModelList.get(i).getD36());
            item.setD37(ModelList.get(i).getD37());
            item.setD38(ModelList.get(i).getD38());
            item.setD39(ModelList.get(i).getD39());
            item.setD40(ModelList.get(i).getD40());
            item.setD41(ModelList.get(i).getD41());
            item.setD42(ModelList.get(i).getD42());
            item.setD43(ModelList.get(i).getD43());
            item.setD44(ModelList.get(i).getD44());
            item.setD45(ModelList.get(i).getD45());
            item.setD46(ModelList.get(i).getD46());
            item.setD47(ModelList.get(i).getD47());
            item.setD48(ModelList.get(i).getD48());
            item.setD49(ModelList.get(i).getD49());
            item.setD50(ModelList.get(i).getD50());
            lstInsert.add(item);
        }
        PosMainModel PosCD = new DaoListPosFromUser().get_pos_main_pos(sUser, sGrade);
        service.updateData(sMaBc, PosCD.getPosCd(), "S", CvDate.format(new SimpleDateFormat("dd/MM/yyyy").parse(sNgaybc)), "", "", lstInsert);
    }

    public String getsGhiChu() {
        return sGhiChu;
    }

    public void setsGhiChu(String sGhiChu) {
        this.sGhiChu = sGhiChu;
    }

}
