/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static vbsp.ims.dao.IMSRptDao.findUserGroup;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class BalanceSheetAdjustDao {

    private final DaoConnect daoConnect;
    private Connection conn;

    public BalanceSheetAdjustDao() {
        daoConnect = new DaoConnect();
    }

    public List<ListValue> getAccountList(String account_type) {
        String lcQuery;
        conn = daoConnect.getConnect();
        ArrayList<ListValue> accounts = new ArrayList<>();
        PreparedStatement ps;
        String account, account_name;

        try {
            if (account_type.isEmpty() || account_type.equals("GL")){
                lcQuery = "select bank_ac ,bank_ac||'~'||AC_DESC ac_name "
                        + "from dmtkgl "
                        + "where bank_ac is not null and length(bank_ac) = 10 "
                        + "order by id";
            }else {
                lcQuery = "select sbv_tkcap3 bank_ac,sbv_tkcap3||'~'||SBV_TENTK ac_name "
                        + "from dmtksbv "
                        + "where sbv_tkcap3 is not null and length(sbv_tkcap3) = 4 "
                        + "order by stt";
            }
            ps = conn.prepareStatement(lcQuery);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    account = rs.getString("BANK_AC");
                    account_name = rs.getString("AC_NAME");
                    accounts.add(new ListValue(account, account_name));
                }
            }
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return accounts;
    }
}
