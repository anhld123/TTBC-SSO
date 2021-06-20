/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.AntiMoneyDao;
import vbsp.ims.dao.BalanceSheetDao;
import vbsp.ims.define.DefineFun;

/**
 *
 * @author Trung
 */
public class BalanceSheetManager {

    private static List<Account> accountList;
    private static List<AntiMoney> antiList;
    private static List<Account> fullAccountList;
    private static Account totalAccount;
    private static List<Account> searchList;
    private static List<Account> wrongPropertyList;
    private static final List<AccountProperty> acPropertyList = BalanceSheetDao.getAccountProperty();
    public static boolean hasViewed = false;

    static {
        accountList = new ArrayList<>();
        searchList = new ArrayList<>();
        wrongPropertyList = new ArrayList<>();
    }

    public static List<Account> getAccountList() {
        return accountList;
    }

    public static List<Account> getSearchList() {
        return searchList;
    }

    public static List<AccountProperty> getAcPropertyList() {
        return acPropertyList;
    }

    public static List<Account> filter(String searchAccount,
            String balanceSheetType,
            String listOfPos,
            String tran_dt,
            String period,
            String pos_flag,
            String searchType
    ) {
        searchList = new ArrayList<>();
        if (balanceSheetType.equals("03")
                || balanceSheetType.equals("04")) {
            totalAccount = new GLAccount("999999", "999999",
                    "Total", "9999", "VND", "9999",
                    BigDecimal.ZERO, BigDecimal.ZERO,
                    BigDecimal.ZERO, BigDecimal.ZERO,
                    BigDecimal.ZERO, BigDecimal.ZERO);
        } else {
            totalAccount = new SbvAccount("999999", "999999",
                    "Total", "VND", BigDecimal.ZERO, BigDecimal.ZERO,
                    BigDecimal.ZERO, BigDecimal.ZERO,
                    BigDecimal.ZERO, BigDecimal.ZERO);
        }
        if (searchAccount.trim().isEmpty()) {
            searchList.clear();
            for (Account account : accountList) {
                totalAccount.plus(account);
            }
            searchList = accountList;
        } else {
            if (searchType.equals("1")) {
                searchList.clear();
                for (Account account : accountList) {
                    if (DefineFun.match(account.getAccount_code(),
                            DefineFun.convertString2Regex(searchAccount))) {
                        //System.err.println("found");
                        searchList.add(account);
                        totalAccount.plus(account);
                    }
                }
            } else {                               
               accountList = BalanceSheetDao.search(searchAccount, balanceSheetType, 
                       listOfPos, tran_dt, period, pos_flag);
               searchList.clear();
                for (Account account : accountList) {
                    if (DefineFun.match(account.getAccount_code(),
                            DefineFun.convertString2Regex(searchAccount))) {
                        //System.err.println("found");
                        searchList.add(account);
                        totalAccount.plus(account);
                    }
                }
                accountList.clear();
            }
        }
        return searchList;
    }
    /* Liet ke danh sach tai khoan */

    public static List<Account> list(String reportDate, String posCodeList, String consolidateFlag,
            String balanceSheetType, String selectedPeriod) {
        accountList = new ArrayList<>();
        accountList.clear();
        BalanceSheetDao balanceSheetDao
                = new BalanceSheetDao(DefineFun.convert2OracleDateFormat(reportDate),
                        posCodeList, consolidateFlag, balanceSheetType, selectedPeriod);
        if (posCodeList.length() >= 9) {
            balanceSheetDao.generate4AllPos();
        } else {
            balanceSheetDao.generate4OnePos();
        }
        accountList = balanceSheetDao.getAccountList();
        hasViewed = true;
        return accountList;
    }

    public static List<AntiMoney> list_anti(String reportDate, String launderingType) {
        antiList = new ArrayList<>();
        antiList.clear();
        AntiMoneyDao balanceSheetDao
                = new AntiMoneyDao(DefineFun.convert2OracleDateFormat(reportDate), launderingType);
        balanceSheetDao.generateAntiReport();
        antiList = balanceSheetDao.getAntiMoneyList();
        hasViewed = true;
        return antiList;
    }

    public static List<AntiMoney> list_SearchAnti(String reportDate, String launderingType, String searchType, String searchKey) {
        antiList = new ArrayList<>();
        antiList.clear();
        AntiMoneyDao balanceSheetDao
                = new AntiMoneyDao(DefineFun.convert2OracleDateFormat(reportDate), launderingType, searchType, searchKey);
        balanceSheetDao.generateSearchAntiReport();
        antiList = balanceSheetDao.getAntiMoneyList();
        hasViewed = true;
        return antiList;
    }

    public static ResultSet list_Rs(String reportDate, String posCodeList, String consolidateFlag,
            String balanceSheetType, String selectedPeriod) {
        ResultSet rs ;
        BalanceSheetDao balanceSheetDao
                = new BalanceSheetDao(DefineFun.convert2OracleDateFormat(reportDate),
                        posCodeList, consolidateFlag, balanceSheetType, selectedPeriod);
        rs = balanceSheetDao.generate4AllPos_Rs();
        return rs;
    }

    /* Liet ke danh sach tai khoan day du */
    public static List<Account> listFullAccount(String reportDate, String posCodeList, String consolidateFlag,
            String balanceSheetType, String selectedPeriod) {
        fullAccountList = new ArrayList<>();
        fullAccountList.clear();
        BalanceSheetDao balanceSheetDao
                = new BalanceSheetDao(DefineFun.convert2OracleDateFormat(reportDate),
                        posCodeList, consolidateFlag, balanceSheetType, selectedPeriod);
        balanceSheetDao.generateFullAccount();
        fullAccountList = balanceSheetDao.getAccountList();
        return fullAccountList;
    }
    /* Liet ke danh sach tai khoan sai tinh chat No, Co */

    public static List<Account> checkProperty() {
        wrongPropertyList.clear();
        if (accountList.size() > 0) {
            for (AccountProperty accountProperty : acPropertyList) {
                for (Account account : accountList) {
                    if (account.getAccount_code().equals(accountProperty.getAccount())
                            && accountProperty.getProperty().equals("N")
                            && (account.getOpen_credit().compareTo(BigDecimal.ZERO) != 0
                            || account.getClose_credit().compareTo(BigDecimal.ZERO) != 0)) {
                        account.setAccount_code(account.getAccount_code() + " (*)t/c nợ phát sinh có");
                        wrongPropertyList.add(account);
                    } else {
                        if (account.getAccount_code().equals(accountProperty.getAccount())
                                && accountProperty.getProperty().equals("C")
                                && (account.getOpen_debit().compareTo(BigDecimal.ZERO) != 0
                                || account.getClose_debit().compareTo(BigDecimal.ZERO) != 0)) {
                            account.setAccount_code(account.getAccount_code() + " (*)t/c có phát sinh nợ");
                            wrongPropertyList.add(account);
                        }
                    }
                }
            }
        }
        return wrongPropertyList;
    }

    public static Account getTotalAccount() {
        return totalAccount;
    }

    //-------------------------------------------------------------------------------------
    public static List<AntiMoney> list(String reportDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
