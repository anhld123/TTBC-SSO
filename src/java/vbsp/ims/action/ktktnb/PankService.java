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
import java.util.ArrayList;
import java.util.List;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author NGUYEN PHU VINH
 */
public class PankService {

    public PankService() {
    }

    public List<QT_DULIEU_NT> getDataByTem(String sNgaybc, String sUser, String sGrade, String sMaBc) {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        List<QT_DULIEU_NT> lstData = new ArrayList<>();
        try {
            CallableStatement calstatement = null;
            String strStoreproce = "{call PKG_KTKSNB_PAKN.PROC_PAKN_GETDATA(?,?,?,?,?)}";
            ResultSet Rset = null;
            try {
                calstatement = con.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, sUser);
                calstatement.setString(2, sGrade);
                calstatement.setString(3, sNgaybc);
                calstatement.setString(4, sMaBc);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                Rset = (ResultSet) calstatement.getObject(5);
                while (Rset.next()) {
                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setKHOA(Rset.getString("KHOA"));
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
    public String saveDataByTem(String sNgaybc, String sUser, String sGrade, String sMaBc, List<QT_DULIEU_NT> ModelList) {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        String code = "";
        try {
            //------Chuyển dạng mảng thành Object của Oracle
            Object array[] = ModelList.toArray();
            ArrayDescriptor des = ArrayDescriptor.createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, con);
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
}
