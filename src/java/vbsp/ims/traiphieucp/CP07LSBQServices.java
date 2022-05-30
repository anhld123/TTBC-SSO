/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.traiphieucp;

import com.opensymphony.xwork2.ActionContext;
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
public class CP07LSBQServices {
    public List<QT_DULIEU_NT> loadCP07LSBQ(String sUser, String sGrade) {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        List<QT_DULIEU_NT> lstData = new ArrayList<>();
        try {
            CallableStatement calstatement = null;
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_LOAD_TP07LSBQ(?,?,?)}";
            ResultSet Rset = null;
            try {
                calstatement = con.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, sUser);
                calstatement.setString(2, sGrade);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                Rset = (ResultSet) calstatement.getObject(3);
                while (Rset.next()) {
                    QT_DULIEU_NT value = QT_DULIEU_NT.newInstance();
                    value.setKHOA(Rset.getString("KHOA"));
                    value.setMACN(Rset.getString("MACN"));
                    value.setMAPGD(Rset.getString("MAPGD"));
                    value.setNAMBC(Rset.getInt("NAMBC"));
                    value.setD1(Rset.getString("D1"));
                    value.setD2(Rset.getString("D2"));
                    value.setD3(Rset.getString("D3"));
                    value.setD4(Rset.getString("D4"));
                    value.setD5(Rset.getString("D5"));
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
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return lstData;
    }
    public String saveCP07LSBQ(String sUser, String sGrade,List<QT_DULIEU_NT> ModelList) {
        sGrade = (String) ActionContext.getContext().getSession().get("reportGrade");
        sUser = (String) ActionContext.getContext().getSession().get("username");
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        String code = "";
        for (int i = (ModelList.size()-1); i >= 0; i--) {
            if(ModelList.get(i).getNAMBC()== 0){
                ModelList.remove(i);
            }
        }
        try {
            //------Chuyển dạng mảng thành Object của Oracle
            Object array[] = ModelList.toArray();
            ArrayDescriptor des = ArrayDescriptor.createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, con);
            ARRAY array_to_pass = new ARRAY(des, con, array);
            //---------------------------------------------------------------------------
            CallableStatement calstatement = null;
            String strStoreproce = "{call VBSP_IMS_NGHIQUYET11CP.SP_SAVE_TP07LSBQ(?,?,?,?)}";
            try {
                calstatement = con.prepareCall(strStoreproce, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, sUser);
                calstatement.setString(2, sGrade);
                calstatement.setArray(3, array_to_pass);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.execute();
                code = (String) calstatement.getString(4);
                if (calstatement != null) {
                    calstatement.close();
                }
            } catch (SQLException e) {
                System.err.print(e.getMessage());
            }
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
        return code;
    }
}
