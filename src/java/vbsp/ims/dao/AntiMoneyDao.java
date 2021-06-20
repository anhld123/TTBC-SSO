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
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.model.AntiMoney;

/**
 *
 * @author Tom
 */
public class AntiMoneyDao {
    private List<AntiMoney> antiMoneyList;
    private static DaoConnect daoConnect;
    private String reportDate;
    private String accountType;
    private String searchType;
    private String searchKey;
    
    static {
        daoConnect = new DaoConnect();
    }

    public List<AntiMoney> getAntiMoneyList() {
        return antiMoneyList;
    }

    public void setAntiMoneyList(List<AntiMoney> antiMoneyList) {
        this.antiMoneyList = antiMoneyList;
    }
    
    public AntiMoneyDao(String reportDate, String accountType) {
        this.reportDate = reportDate;
        this.accountType = accountType;
    }
    
    public AntiMoneyDao(String reportDate, String accountType, String searchType, String searchKey) {
        this.reportDate = reportDate;
        this.accountType = accountType;
        this.searchType = searchType;
        this.searchKey = searchKey;
    }
        
    public void generateAntiReport() {
        Connection connect;
        try {
            connect = daoConnect.getConnect();
          //  if (accountType.equals("01") || accountType.equals("04"))
                antiMoneyList = new ArrayList<>();
                String crt_magd, crt_mapgd, crt_lenhct,
                         crt_noidungct, crt_tenta, crt_diachi, crt_kieukh, crt_cmt, crt_sohc,
                         crt_sotk, crt_loaitk, crt_tttk, crt_mahn, crt_sotkth, crt_mst, crt_ngaygd, crt_loaibc ;
                BigDecimal crt_sotien, crt_sotienquydoi;
                    try {
                        CallableStatement calstatement;
                        String strStoreproce
                                = "{call CRT_ALL(?,?, ?)}";
                        ResultSet accountListResult;
                        try {
                            //Khoi tao goi store

                            calstatement = connect.prepareCall(strStoreproce,
                                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                            calstatement.setString(1, reportDate.trim());
                            calstatement.setString(2, accountType);
                            calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                            calstatement.execute();
                            accountListResult = (ResultSet) calstatement.getObject(3);
                            while (accountListResult.next()) {
                                crt_magd = accountListResult.getString("CRT_MAGD");
                                //System.err.println("viewBalanceSheet() generate account -->" + lcaccount_code );
                                crt_mapgd = accountListResult.getString("CRT_MAPGD");  
                                crt_lenhct = accountListResult.getString("CRT_LENHCT");
                                crt_sotien = accountListResult.getBigDecimal("CRT_SOTIEN");
                                crt_sotienquydoi = accountListResult.getBigDecimal("CRT_SOTIEN_QD");
                                crt_noidungct = accountListResult.getString("CRT_NOIDUNGCT");
                                crt_tenta = accountListResult.getString("CRT_TENTA");
                                crt_diachi = accountListResult.getString("CRT_DIACHI");
                                crt_kieukh = accountListResult.getString("CRT_KIEU_KH");
                                crt_cmt = accountListResult.getString("CRT_CMT");
                                crt_sohc = accountListResult.getString("CRT_SOHC");
                                crt_sotk = accountListResult.getString("CRT_SOTK");
                                crt_loaitk = accountListResult.getString("CRT_LOAI_TK");
                                crt_tttk = accountListResult.getString("CRT_TTTK"); 
                                crt_mahn = accountListResult.getString("CRT_MANH");
                                crt_sotkth = accountListResult.getString("CRT_SOTK_TH");
                                crt_mst = accountListResult.getString("CRT_MST");
                                crt_ngaygd = accountListResult.getString("CRT_NGAYGD");
                                crt_loaibc = accountListResult.getString("CRT_LOAIBC");
                                antiMoneyList.add(new AntiMoney(crt_magd,crt_mapgd,crt_lenhct,crt_sotien,crt_sotienquydoi,
                                crt_noidungct,crt_tenta,crt_diachi,crt_kieukh,crt_cmt,crt_sohc,crt_sotk,crt_loaitk,crt_tttk,
                                crt_mahn,crt_sotkth,crt_mst,crt_ngaygd,crt_loaibc));
                            }
                            //System.err.println("viewBalanceSheet() is finished generated data" + lcPosCode);
                        } catch (SQLException e) {
                            System.err.println("Error --> " + e.getErrorCode() + ":" + e.getMessage());
                        }
                    } catch (Exception e) {
                        System.err.println("Error --> " + e.getMessage());
                    }
                System.err.println("viewBalanceSheet() is finished generated data");            
            System.err.println("viewBalanceSheet() is finished generated data");
            connect.close();
        } catch (Exception e) {
            System.err.println("Error --> " + e.getMessage());
        }
    }   
    
     public void generateSearchAntiReport() {
        Connection connect;
        try {
            connect = daoConnect.getConnect();
          //  if (accountType.equals("01") || accountType.equals("04"))
                antiMoneyList = new ArrayList<>();
                String crt_magd, crt_mapgd, crt_lenhct,
                         crt_noidungct, crt_tenta, crt_diachi, crt_kieukh, crt_cmt, crt_sohc,
                         crt_sotk, crt_loaitk, crt_tttk, crt_mahn, crt_sotkth,crt_mst, crt_ngaygd,crt_loaibc ;
                BigDecimal crt_sotien, crt_sotienquydoi;
                    try {
                        CallableStatement calstatement;
                        String strStoreproce
                                = "{call CRT_SEARCH(?,?,?,?,?)}";
                        ResultSet accountListResult;
                        try {
                            //Khoi tao goi store

                            calstatement = connect.prepareCall(strStoreproce,
                                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                            calstatement.setString(1, reportDate.trim());
                            calstatement.setString(2, accountType);
                            calstatement.setString(3, searchType);
                            calstatement.setString(4, searchKey);
                            calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                            calstatement.execute();
                            accountListResult = (ResultSet) calstatement.getObject(5);
                            while (accountListResult.next()) {
                                crt_magd = accountListResult.getString("CRT_MAGD");
                                //System.err.println("viewBalanceSheet() generate account -->" + lcaccount_code );
                                crt_mapgd = accountListResult.getString("CRT_MAPGD");  
                                crt_lenhct = accountListResult.getString("CRT_LENHCT");
                                crt_sotien = accountListResult.getBigDecimal("CRT_SOTIEN");
                                crt_sotienquydoi = accountListResult.getBigDecimal("CRT_SOTIEN_QD");
                                crt_noidungct = accountListResult.getString("CRT_NOIDUNGCT");
                                crt_tenta = accountListResult.getString("CRT_TENTA");
                                crt_diachi = accountListResult.getString("CRT_DIACHI");
                                crt_kieukh = accountListResult.getString("CRT_KIEU_KH");
                                crt_cmt = accountListResult.getString("CRT_CMT");
                                crt_sohc = accountListResult.getString("CRT_SOHC");
                                crt_sotk = accountListResult.getString("CRT_SOTK");
                                crt_loaitk = accountListResult.getString("CRT_LOAI_TK");
                                crt_tttk = accountListResult.getString("CRT_TTTK"); 
                                crt_mahn = accountListResult.getString("CRT_MANH");
                                crt_sotkth = accountListResult.getString("CRT_SOTK_TH");
                                crt_mst = accountListResult.getString("CRT_MST");
                                crt_ngaygd = accountListResult.getString("CRT_NGAYGD");
                                crt_loaibc = accountListResult.getString("CRT_LOAIBC");
                                antiMoneyList.add(new AntiMoney(crt_magd,crt_mapgd,crt_lenhct,crt_sotien,crt_sotienquydoi,
                                crt_noidungct,crt_tenta,crt_diachi,crt_kieukh,crt_cmt,crt_sohc,crt_sotk,crt_loaitk,crt_tttk,
                                crt_mahn,crt_sotkth,crt_mst,crt_ngaygd,crt_loaibc));
                            }
                            //System.err.println("viewBalanceSheet() is finished generated data" + lcPosCode);
                        } catch (SQLException e) {
                            System.err.println("Error --> " + e.getErrorCode() + ":" + e.getMessage());
                        }
                    } catch (Exception e) {
                        System.err.println("Error --> " + e.getMessage());
                    }
                System.err.println("viewBalanceSheet() is finished generated data");            
            System.err.println("viewBalanceSheet() is finished generated data");
            connect.close();
        } catch (Exception e) {
            System.err.println("Error --> " + e.getMessage());
        }
    } 
    
}
