/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.define.DefineFun;
import vbsp.ims.model.Account;
import vbsp.ims.model.AccountProperty;
import vbsp.ims.model.GLAccount;
import vbsp.ims.model.SbvAccount;

/**
 *
 * @author Trung
 */
public class BalanceSheetDao {

    private List<Account> accountList;
    private String reportDate;
    private List<String> posCodeList;
    private String consolidateFlag;
    private String termFlag;
    private String accountType;
    private static DaoConnect daoConnect;
    private String posListStr;

    static {
        daoConnect = new DaoConnect();
    }

    public BalanceSheetDao() {
    }

    public BalanceSheetDao(String reportDate, String posCodeList, String consolidateFlag,
            String accountType, String termFlag) {
        this.reportDate = reportDate;
        this.posCodeList = DefineFun.string2Array(posCodeList, ",", 1);
        this.posListStr = posCodeList.trim();
        this.consolidateFlag = consolidateFlag;
        this.accountType = accountType;
        this.termFlag = termFlag;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public void generate4OnePos() {
        Connection connect;
        try {
            String ltermFlag;
            ltermFlag = termFlag.substring(2, 3);
            connect = daoConnect.getConnect();
            if (accountType.equals("03") || accountType.equals("04")) {
                System.err.print("viewBalanceSheet() is started generated data");
                accountList = new ArrayList<>();
                String lcgl_Sl, lcgl_Ccy, lcgl_Sbv, lcaccount_code, lcaccount_descript;
                BigDecimal lbopen_debit, lbopen_credit, lbturn_debit, lbturn_credit, lbclose_debit, lbclose_credit;
                for (String lcPosCode : this.posCodeList) {
                    try {
                        CallableStatement calstatement;
                        String strStoreproce
                                = "{call rpt_vbsp_balance.p_view_balance(?, ?, ?, ?, ?, ?)}";
                        ResultSet accountListResult;
                        try {
                            //Khoi tao goi store
                            System.err.println("generate -->" + lcPosCode + "-" + reportDate
                                    + "-" + consolidateFlag + "-" + ltermFlag
                                    + "-" + accountType);
                            calstatement = connect.prepareCall(strStoreproce,
                                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                            calstatement.setString(1, lcPosCode.trim());
                            calstatement.setString(2, reportDate.trim());
                            calstatement.setString(3, consolidateFlag.trim());
                            calstatement.setString(4, ltermFlag.trim());
                            calstatement.setString(5, accountType.trim());
                            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                            calstatement.execute();
                            accountListResult = (ResultSet) calstatement.getObject(6);
                            while (accountListResult.next()) {
                                lcaccount_code = accountListResult.getString("TK");
                                //System.err.println("viewBalanceSheet() generate account -->" + lcaccount_code );
                                lcaccount_descript = accountListResult.getString("TENTK");
                                lcgl_Sl = accountListResult.getString("GL_SL");
                                lcgl_Ccy = accountListResult.getString("LOAITIEN");
                                lcgl_Sbv = accountListResult.getString("TKCAP3");
                                lbopen_debit = accountListResult.getBigDecimal("DDNO");
                                lbopen_credit = accountListResult.getBigDecimal("DDCO");
                                lbturn_debit = accountListResult.getBigDecimal("PSNO");
                                lbturn_credit = accountListResult.getBigDecimal("PSCO");
                                lbclose_debit = accountListResult.getBigDecimal("DCNO");
                                lbclose_credit = accountListResult.getBigDecimal("DCCO");
                                accountList.add(new GLAccount(lcPosCode, lcaccount_code, lcaccount_descript,
                                        lcgl_Sl, lcgl_Ccy, lcgl_Sbv, lbopen_debit, lbopen_credit,
                                        lbturn_debit, lbturn_credit, lbclose_debit, lbclose_credit));
                            }
                            System.err.println("viewBalanceSheet() is finished generated data" + lcPosCode);
                        } catch (SQLException e) {
                            System.err.println("Error --> " + e.getErrorCode() + ":" + e.getMessage());
                        }
                    } catch (Exception e) {
                        System.err.println("Error --> " + e.getMessage());
                    }
                }
                System.err.println("viewBalanceSheet() is finished generated data");
            } else {
                accountList = new ArrayList<>();
                String lcaccount_code, lcaccount_descript, lcCcyCode;
                BigDecimal lbopen_debit, lbopen_credit, lbturn_debit, lbturn_credit, lbclose_debit, lbclose_credit;
                for (String lcPosCode : this.posCodeList) {
                    try {
                        CallableStatement calstatement;
                        String strStoreproce
                                = "{call rpt_vbsp_balance.p_view_balance(?, ?, ?, ?, ?, ?)}";
                        ResultSet accountListResult;
                        try {
                            System.err.println("generate -->" + lcPosCode + "-" + reportDate
                                    + "-" + consolidateFlag + "-" + ltermFlag
                                    + "-" + accountType);
                            //Khoi tao goi store
                            calstatement = connect.prepareCall(strStoreproce,
                                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                            calstatement.setString(1, lcPosCode);
                            calstatement.setString(2, reportDate.trim());
                            calstatement.setString(3, consolidateFlag);
                            calstatement.setString(4, ltermFlag);
                            calstatement.setString(5, accountType.trim());
                            calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                            calstatement.execute();
                            accountListResult = (ResultSet) calstatement.getObject(6);
                            while (accountListResult.next()) {
                                lcaccount_code = accountListResult.getString("TK");
                                lcaccount_descript = accountListResult.getString("TENTK");
                                lcCcyCode = "VND";
                                lbopen_debit = accountListResult.getBigDecimal("DDNO");
                                lbopen_credit = accountListResult.getBigDecimal("DDCO");
                                lbturn_debit = accountListResult.getBigDecimal("PSNO");
                                lbturn_credit = accountListResult.getBigDecimal("PSCO");
                                lbclose_debit = accountListResult.getBigDecimal("DCNO");
                                lbclose_credit = accountListResult.getBigDecimal("DCCO");
                                if (lcaccount_code.length() == 4) {
                                    accountList.add(new SbvAccount(lcPosCode, lcaccount_code, lcaccount_descript,
                                            lcCcyCode, lbopen_debit, lbopen_credit,
                                            lbturn_debit, lbturn_credit, lbclose_debit, lbclose_credit));
                                }
                            }
                        } catch (SQLException e) {
                            System.err.println("Error --> " + e.getErrorCode() + ":" + e.getMessage());
                        }
                    } catch (Exception e) {
                    }
                }
            }
            System.err.println("viewBalanceSheet() is finished generated data");
            connect.close();
        } catch (SQLException e) {
            System.err.println("Error --> " + e.getMessage());
        }
    }

    public void generate4AllPos() {
        Connection connect;
        try {
            String ltermFlag;
            ltermFlag = termFlag.substring(2, 3);
            connect = daoConnect.getConnect();
            if (accountType.equals("03") || accountType.equals("04")) {
                System.err.print("viewBalanceSheet() is started generated data");
                accountList = new ArrayList<>();
                accountList.clear();
                String lcPos_cd, lcgl_Sl, lcgl_Ccy, lcgl_Sbv, lcaccount_code, lcaccount_descript;
                BigDecimal lbopen_debit, lbopen_credit, lbturn_debit, lbturn_credit, lbclose_debit, lbclose_credit;

                try {
                    CallableStatement calstatement;
                    String strStoreproce
                            = "{call rpt_vbsp_balance.p_view_balance_by_poslist(?, ?, ?, ?, ?, ?)}";
                    ResultSet accountListResult;
                    try {
                        calstatement = connect.prepareCall(strStoreproce,
                                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        calstatement.setString(1, posListStr);
                        calstatement.setString(2, reportDate.trim());
                        calstatement.setString(3, consolidateFlag.trim());
                        calstatement.setString(4, ltermFlag.trim());
                        calstatement.setString(5, accountType.trim());
                        calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                        calstatement.execute();
                        accountListResult = (ResultSet) calstatement.getObject(6);
                        while (accountListResult.next()) {
                            lcPos_cd = accountListResult.getString("POS_CD");
                            lcaccount_code = accountListResult.getString("TK");
                            lcaccount_descript = accountListResult.getString("TENTK");
                            lcgl_Sl = accountListResult.getString("GL_SL");
                            lcgl_Ccy = accountListResult.getString("LOAITIEN");
                            lcgl_Sbv = accountListResult.getString("TKCAP3");
                            lbopen_debit = accountListResult.getBigDecimal("DDNO");
                            lbopen_credit = accountListResult.getBigDecimal("DDCO");
                            lbturn_debit = accountListResult.getBigDecimal("PSNO");
                            lbturn_credit = accountListResult.getBigDecimal("PSCO");
                            lbclose_debit = accountListResult.getBigDecimal("DCNO");
                            lbclose_credit = accountListResult.getBigDecimal("DCCO");
                            accountList.add(new GLAccount(lcPos_cd, lcaccount_code, lcaccount_descript,
                                    lcgl_Sl, lcgl_Ccy, lcgl_Sbv, lbopen_debit, lbopen_credit,
                                    lbturn_debit, lbturn_credit, lbclose_debit, lbclose_credit));
                        }
                        System.err.println("viewBalanceSheet() is finished generated data" + posListStr);
                    } catch (SQLException e) {
                        System.err.println("Error --> " + e.getErrorCode() + ":" + e.getMessage());
                    }
                } catch (Exception e) {
                    System.err.println("Error --> " + e.getMessage());
                }

                System.err.println("viewBalanceSheet() is finished generated data");
            } else {
                accountList = new ArrayList<>();
                accountList.clear();
                String lcPos_cd, lcaccount_code, lcaccount_descript, lcCcyCode;
                BigDecimal lbopen_debit, lbopen_credit, lbturn_debit, lbturn_credit, lbclose_debit, lbclose_credit;

                try {
                    CallableStatement calstatement;
                    String strStoreproce
                            = "{call rpt_vbsp_balance.p_view_balance_by_poslist(?, ?, ?, ?, ?, ?)}";
                    ResultSet accountListResult;
                    try {
                        calstatement = connect.prepareCall(strStoreproce,
                                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        calstatement.setString(1, posListStr);
                        calstatement.setString(2, reportDate.trim());
                        calstatement.setString(3, consolidateFlag);
                        calstatement.setString(4, ltermFlag);
                        calstatement.setString(5, accountType.trim());
                        calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                        calstatement.execute();
                        accountListResult = (ResultSet) calstatement.getObject(6);
                        while (accountListResult.next()) {
                            lcPos_cd = accountListResult.getString("POS_CD");
                            lcaccount_code = accountListResult.getString("TK");
                            lcaccount_descript = accountListResult.getString("TENTK");
                            lcCcyCode = "VND";
                            lbopen_debit = accountListResult.getBigDecimal("DDNO");
                            lbopen_credit = accountListResult.getBigDecimal("DDCO");
                            lbturn_debit = accountListResult.getBigDecimal("PSNO");
                            lbturn_credit = accountListResult.getBigDecimal("PSCO");
                            lbclose_debit = accountListResult.getBigDecimal("DCNO");
                            lbclose_credit = accountListResult.getBigDecimal("DCCO");
                            if (lcaccount_code.length() == 4) {
                                accountList.add(new SbvAccount(lcPos_cd, lcaccount_code, lcaccount_descript,
                                        lcCcyCode, lbopen_debit, lbopen_credit,
                                        lbturn_debit, lbturn_credit, lbclose_debit, lbclose_credit));
                            }
                        }
                    } catch (SQLException e) {
                        System.err.println("Error --> " + e.getErrorCode() + ":" + e.getMessage());
                    }
                } catch (Exception e) {
                }

            }
            System.err.println("viewBalanceSheet() is finished generated data");
            connect.close();
        } catch (SQLException e) {
            System.err.println("Error --> " + e.getMessage());
        }
    }

    public ResultSet generate4AllPos_Rs() {
        Connection connect;
        try {
            String ltermFlag;
            ltermFlag = termFlag.substring(2, 3);
            connect = daoConnect.getConnect();
            if (accountType.equals("03") || accountType.equals("04")) {                                               
                try {
                    CallableStatement calstatement;
                    String strStoreproce
                            = "{call rpt_vbsp_balance.p_view_balance_by_poslist(?, ?, ?, ?, ?, ?)}";
                    ResultSet accountListResult;
                    try {
                        calstatement = connect.prepareCall(strStoreproce,
                                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        calstatement.setString(1, posListStr);
                        calstatement.setString(2, reportDate.trim());
                        calstatement.setString(3, consolidateFlag.trim());
                        calstatement.setString(4, ltermFlag.trim());
                        calstatement.setString(5, accountType.trim());
                        calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                        calstatement.execute();
                        accountListResult = (ResultSet) calstatement.getObject(6);
                       
                        System.err.println("viewBalanceSheet() is finished generated data" + posListStr);
                        return accountListResult;
                    } catch (SQLException e) {
                        System.err.println("Error --> " + e.getErrorCode() + ":" + e.getMessage());
                    }
                } catch (Exception e) {
                    System.err.println("Error --> " + e.getMessage());
                }                           
            } else {                               
                try {
                    CallableStatement calstatement;
                    String strStoreproce
                            = "{call rpt_vbsp_balance.p_view_balance_by_poslist(?, ?, ?, ?, ?, ?)}";
                    ResultSet accountListResult;
                    try {
                        calstatement = connect.prepareCall(strStoreproce,
                                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        calstatement.setString(1, posListStr);
                        calstatement.setString(2, reportDate.trim());
                        calstatement.setString(3, consolidateFlag);
                        calstatement.setString(4, ltermFlag);
                        calstatement.setString(5, accountType.trim());
                        calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                        calstatement.execute();
                        accountListResult = (ResultSet) calstatement.getObject(6);
                        return accountListResult;
                    } catch (SQLException e) {
                        System.err.println("Error --> " + e.getErrorCode() + ":" + e.getMessage());
                    }
                } catch (Exception e) {}                
            }            
            connect.close();
            return null;     
        } catch (SQLException e) {
            System.err.println("Error --> " + e.getMessage());
            return null;
        }
    }
    
    public void generateFullAccount() {
        Connection connect;
        try {
            String ltermFlag;
            ltermFlag = termFlag.substring(2, 3);
            connect = daoConnect.getConnect();
            if (accountType.equals("03") || accountType.equals("04")) {
                System.err.print("viewBalanceSheet() is started generated data");
                accountList = new ArrayList<>();
                accountList.clear();
                String lcPos_cd, lcgl_Sl, lcgl_Ccy, lcgl_Sbv, lcaccount_code, lcaccount_descript;
                BigDecimal lbopen_debit, lbopen_credit, lbturn_debit, lbturn_credit, lbclose_debit, lbclose_credit;

                try {
                    CallableStatement calstatement;
                    String strStoreproce
                            = "{call rpt_vbsp_balance.p_fetch_buffer_2_export_excel(?, ?, ?, ?, ?, ?)}";
                    ResultSet accountListResult;
                    try {
                        calstatement = connect.prepareCall(strStoreproce,
                                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        calstatement.setString(1, getMergeList2String());
                        calstatement.setString(2, reportDate.trim());
                        calstatement.setString(3, consolidateFlag.trim());
                        calstatement.setString(4, ltermFlag.trim());
                        calstatement.setString(5, accountType.trim());
                        calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                        calstatement.execute();
                        accountListResult = (ResultSet) calstatement.getObject(6);
                        while (accountListResult.next()) {
                            lcPos_cd = accountListResult.getString("POS_CD");
                            lcaccount_code = accountListResult.getString("TK");
                            lcaccount_descript = accountListResult.getString("TENTK");
                            lcgl_Sl = accountListResult.getString("GL_SL");
                            lcgl_Ccy = accountListResult.getString("LOAITIEN");
                            lcgl_Sbv = accountListResult.getString("TKCAP3");
                            lbopen_debit = accountListResult.getBigDecimal("DDNO");
                            lbopen_credit = accountListResult.getBigDecimal("DDCO");
                            lbturn_debit = accountListResult.getBigDecimal("PSNO");
                            lbturn_credit = accountListResult.getBigDecimal("PSCO");
                            lbclose_debit = accountListResult.getBigDecimal("DCNO");
                            lbclose_credit = accountListResult.getBigDecimal("DCCO");
                            accountList.add(new GLAccount(lcPos_cd, lcaccount_code, lcaccount_descript,
                                    lcgl_Sl, lcgl_Ccy, lcgl_Sbv, lbopen_debit, lbopen_credit,
                                    lbturn_debit, lbturn_credit, lbclose_debit, lbclose_credit));
                        }
                        System.err.println("viewBalanceSheet() is finished generated data" + posListStr);
                    } catch (SQLException e) {
                        System.err.println("Error --> " + e.getErrorCode() + ":" + e.getMessage());
                    }
                } catch (Exception e) {
                    System.err.println("Error --> " + e.getMessage());
                }

                System.err.println("viewBalanceSheet() is finished generated data");
            } else {
                accountList = new ArrayList<>();
                accountList.clear();
                String lcPos_cd, lcaccount_code, lcaccount_descript, lcCcyCode;
                BigDecimal lbopen_debit, lbopen_credit, lbturn_debit, lbturn_credit, lbclose_debit, lbclose_credit;

                try {
                    CallableStatement calstatement;
                    String strStoreproce
                            = "{call rpt_vbsp_balance.p_fetch_buffer_2_export_excel(?, ?, ?, ?, ?, ?)}";
                    ResultSet accountListResult;
                    try {
                        calstatement = connect.prepareCall(strStoreproce,
                                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        calstatement.setString(1, getMergeList2String());
                        calstatement.setString(2, reportDate.trim());
                        calstatement.setString(3, consolidateFlag);
                        calstatement.setString(4, ltermFlag);
                        calstatement.setString(5, accountType.trim());
                        calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
                        calstatement.execute();
                        accountListResult = (ResultSet) calstatement.getObject(6);
                        while (accountListResult.next()) {
                            lcPos_cd = accountListResult.getString("POS_CD");
                            lcaccount_code = accountListResult.getString("TK");
                            lcaccount_descript = accountListResult.getString("TENTK");
                            lcCcyCode = "VND";
                            lbopen_debit = accountListResult.getBigDecimal("DDNO");
                            lbopen_credit = accountListResult.getBigDecimal("DDCO");
                            lbturn_debit = accountListResult.getBigDecimal("PSNO");
                            lbturn_credit = accountListResult.getBigDecimal("PSCO");
                            lbclose_debit = accountListResult.getBigDecimal("DCNO");
                            lbclose_credit = accountListResult.getBigDecimal("DCCO");
                            accountList.add(new SbvAccount(lcPos_cd, lcaccount_code, lcaccount_descript,
                                    lcCcyCode, lbopen_debit, lbopen_credit,
                                    lbturn_debit, lbturn_credit, lbclose_debit, lbclose_credit));

                        }
                    } catch (SQLException e) {
                        System.err.println("Error --> " + e.getErrorCode() + ":" + e.getMessage());
                    }
                } catch (Exception e) {
                }

            }
            System.err.println("viewBalanceSheet() is finished generated data");
            connect.close();
        } catch (SQLException e) {
            System.err.println("Error --> " + e.getMessage());
        }
    }

    public static List<AccountProperty> getAccountProperty() {
        ArrayList<AccountProperty> accountProperties
                = new ArrayList<>();
        String lcQuery = "SELECT SBV_TKCAP3,SBV_NOCO FROM DMTKSBV WHERE SBV_NOCO IS NOT NULL ORDER BY STT";
        String lcKey, lcDescript;
        try {
            Connection con;
            con = daoConnect.getConnect();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(lcQuery);
            while (rs.next()) {
                lcKey = rs.getString("SBV_TKCAP3");
                lcDescript = rs.getString("SBV_NOCO");
                accountProperties.add(new AccountProperty(lcKey, lcDescript));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println("Loi --> Khong lay duoc danh muc nhom bao cao");
        }
        return accountProperties;
    }

    protected String getMergeList2String() {
        String mergeString = "";
        for (String string : posCodeList) {
            mergeString += "P" + string.trim() + ",";
        }
        return mergeString.substring(0, mergeString.length() - 1);
    }
    
    
    public static List<Account> search(
            String searchAccount,
            String accountType,
            String listOfPos,
            String tran_dt,
            String period,
            String pos_flag) {         
        ArrayList<Account> laccList = new ArrayList<>();
        try {
            String termFlag = period.substring(2, 3);
            Connection connect = daoConnect.getConnect();
                CallableStatement calstatement;
                String strStoreproce
                        = "{call rpt_vbsp_balance.app_search(?,?, ?, ?, ?, ?, ?)}";
                ResultSet accountListResult;
                calstatement = connect.prepareCall(strStoreproce,
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                calstatement.setString(1, searchAccount);
                calstatement.setString(2, accountType);
                calstatement.setString(3, tran_dt);
                calstatement.setString(4, termFlag);
                calstatement.setString(5, listOfPos);
                calstatement.setString(6, pos_flag);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                if (accountType.equals("03") || accountType.equals("04")) {
                    String lcgl_Sl, lcgl_Ccy, lcgl_Sbv, lcbank_ac, lcac_desc, lcPos_cd;
                    BigDecimal lbopen_debit, lbopen_credit, lbturn_debit, lbturn_credit, lbclose_debit, lbclose_credit;
                    try {
                                                                                                         
                            accountListResult = (ResultSet) calstatement.getObject(7);
                            while (accountListResult.next()) {
                                lcPos_cd = accountListResult.getString("POS_CD");                                
                                lcbank_ac = accountListResult.getString("TK");                                
                                lcac_desc = accountListResult.getString("TENTK");
                                lcgl_Sl = accountListResult.getString("GL_SL");
                                lcgl_Ccy = accountListResult.getString("LOAITIEN");
                                lcgl_Sbv = accountListResult.getString("TKCAP3");
                                lbopen_debit = accountListResult.getBigDecimal("DDNO");
                                lbopen_credit = accountListResult.getBigDecimal("DDCO");
                                lbturn_debit = accountListResult.getBigDecimal("PSNO");
                                lbturn_credit = accountListResult.getBigDecimal("PSCO");
                                lbclose_debit = accountListResult.getBigDecimal("DCNO");
                                lbclose_credit = accountListResult.getBigDecimal("DCCO");
                                laccList.add(new GLAccount(lcPos_cd, lcbank_ac, lcac_desc,
                                        lcgl_Sl, lcgl_Ccy, lcgl_Sbv, lbopen_debit, lbopen_credit,
                                        lbturn_debit, lbturn_credit, lbclose_debit, lbclose_credit));
                            }                                                   
                    } catch (SQLException e) {
                        System.err.println("Error --> " + e.getMessage());
                    }
                } else {                   
                    String lcbank_Ac, lcac_Desc, lcCcyCode, lcPos_cd;
                    BigDecimal lbopen_debit, lbopen_credit, lbturn_debit, lbturn_credit, lbclose_debit, lbclose_credit;                    
                    try{
                        accountListResult = (ResultSet) calstatement.getObject(7);
                                while (accountListResult.next()) {
                                    lcPos_cd = accountListResult.getString("POS_CD"); 
                                    lcbank_Ac = accountListResult.getString("TK");
                                    lcac_Desc = accountListResult.getString("TENTK");
                                    lcCcyCode = "VND";
                                    lbopen_debit = accountListResult.getBigDecimal("DDNO");
                                    lbopen_credit = accountListResult.getBigDecimal("DDCO");
                                    lbturn_debit = accountListResult.getBigDecimal("PSNO");
                                    lbturn_credit = accountListResult.getBigDecimal("PSCO");
                                    lbclose_debit = accountListResult.getBigDecimal("DCNO");
                                    lbclose_credit = accountListResult.getBigDecimal("DCCO");
                                    if (lcbank_Ac.length() == 4) {
                                        laccList.add(new SbvAccount(lcPos_cd, lcbank_Ac, lcac_Desc,
                                                lcCcyCode, lbopen_debit, lbopen_credit,
                                                lbturn_debit, lbturn_credit, lbclose_debit, lbclose_credit));
                                    }
                                }                            
                        } catch (SQLException e) {
                            System.err.println("Error --> " + e.getMessage());
                        }                    
                }                            
        } catch (SQLException e) {
            System.err.println("Error --> " + e.getMessage());
        }
        return laccList;
    }
}
