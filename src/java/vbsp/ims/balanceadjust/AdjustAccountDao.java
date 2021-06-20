/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.balanceadjust;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import vbsp.ims.dao.DaoConnect;

/**
 *
 * @author Trung
 */
public class AdjustAccountDao {

    private final DaoConnect daoConnect;
    private Connection conn;

    public AdjustAccountDao() {
        daoConnect = new DaoConnect();
    }

    public void getAccountValue(AdjustAccount account, String pv_user) {
        if (account != null) {

            String strStoreproce
                    = "{call rpt_upload_account.sp_get_account_value(?, ?, ?,?, ?, ?,?)}";
            try {
                
                conn = daoConnect.getConnect();
                
                CallableStatement calstatement = conn.prepareCall(strStoreproce,
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                calstatement.setString(1, account.getTk());
                calstatement.setString(2, account.getMapos());
                calstatement.setString(3, account.getTonghop());
                calstatement.setString(4, account.getKybc());
                calstatement.setString(5, account.getNgaybc());
                calstatement.setString(6, pv_user);
                
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                
                try (ResultSet result = (ResultSet) calstatement.getObject(7)) {
                    BigDecimal dDdno, dDdco, dPsno, dPsco, dDcno, dDcco;
                    while (result.next()) {
                        
                        dDdno = result.getBigDecimal("DDNO");
                        dDdco = result.getBigDecimal("DDCO");
                        dPsno = result.getBigDecimal("PSNO");
                        dPsco = result.getBigDecimal("PSCO");
                        dDcno = result.getBigDecimal("DCNO");
                        dDcco = result.getBigDecimal("DCCO");
                        
                        account.setDdno(dDdno);
                        account.setDdco(dDdco);
                        account.setPsno(dPsno);
                        account.setPsco(dPsco);
                        account.setDcno(dDcno);
                        account.setDcco(dDcco);
                        
                        account.setDdno_dc(dDdno);
                        account.setDdco_dc(dDdco);
                        account.setPsno_dc(dPsno);
                        account.setPsco_dc(dPsco);
                        account.setDcno_dc(dDcno);
                        account.setDcco_dc(dDcco);
                    }
                }

            } catch (Exception ex) {
                System.err.println("setAccountValue-->" + ex.getMessage());                
            }

        } else {
            System.err.println("setAccountValue--> account is null");                
        }
    }
    
    public String setAccountValue(AdjustAccount account,String pv_user) {
        // Cập nhật vào CSDL
        String resultStr ="" ;
        
        if (account != null) {

            String strStoreproce
                    = "{call rpt_upload_account.sp_set_account_value(?, ?, ?,?, ?, ?,?, ?, ?,?, ?, ?,?)}";
            try {
                
                conn = daoConnect.getConnect();
                
                CallableStatement calstatement = conn.prepareCall(strStoreproce,
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                calstatement.setString(1, account.getTk());
                calstatement.setString(2, account.getMapos());
                calstatement.setString(3, account.getTonghop());
                calstatement.setString(4, account.getKybc());
                calstatement.setString(5, account.getNgaybc());                
                
                calstatement.setString(6, account.getDdno_dc().toString());
                calstatement.setString(7, account.getDdco_dc().toString());
                calstatement.setString(8, account.getPsno_dc().toString());
                calstatement.setString(9, account.getPsco_dc().toString());
                calstatement.setString(10, account.getDcno_dc().toString());
                calstatement.setString(11, account.getDcco_dc().toString());
                
                calstatement.setString(12,pv_user);
                
                calstatement.registerOutParameter(13, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.execute();
                
                resultStr = (String) calstatement.getObject(13) ;
                                   
            } catch (Exception ex) {
                resultStr = ex.getMessage();                
                System.err.println("setAccountValue-->" + ex.getMessage());                
            }
            finally {
                return resultStr;
            }

        } else {
            System.err.println("setAccountValue--> account is null");                
            resultStr = "account is null";
            return resultStr;            
        }
    }
}
