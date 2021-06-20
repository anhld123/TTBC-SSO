/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.smsbanking;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.DaoConnect;

/**
 *
 * @author Trung
 */
public class CustomerQueryDao {
    
    private static DaoConnect daoConnect;
    private static Connection conn;
    
    public CustomerQueryDao(){
        daoConnect = new DaoConnect();
    }
    
    public List<Customer> getCustomers(String pv_keyStr){
        
        ArrayList<Customer> customers = new ArrayList<>();
        
        String strStoreproce
                = "{call mb_appmaster.p_getCustomers(?,?)}";
        try {
                        
            
            ResultSet rs;
            
            conn = daoConnect.getSMSBankingConnect();            
            
            CallableStatement calstatement 
                    = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
                calstatement.setString(1, pv_keyStr);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                
                rs = (ResultSet) calstatement.getObject(2);  
                
                Customer customer ;
                while (rs.next()) {
                    
                    customer = new Customer();
                    
                    customer.setPosCode(
                            rs.getString("POSCODE")
                    );
                    
                    customer.setCifNo(
                             rs.getString("CIFNO")
                    );
                    
                    customer.setCustomerType(
                            rs.getString("CUSTTYPE")
                    );
                    
                    customer.setName(
                            rs.getString("NAME")
                    );
                    
                    customer.setBankAccount(
                            rs.getString("LEGACYAC")
                    );
                    
                    customer.setPhoneNumber(
                            rs.getString("PHONENUMBER")
                    );
                    
                    customer.setStatus(
                            rs.getString("STATUS")
                    );
                    
                    customers.add(customer);
                }
                
            
            rs.close(); 
        } catch (Exception ex) {
            
            System.err.println("getCustomers-->" + ex.getMessage());           
            
        } finally {
                      
                
            
        }
        return customers;
    }
    
    public Customer getCustomer(String pv_cifNo,String pv_bankAccount){
        
        ArrayList<Customer> customers = new ArrayList<>();
        
        String strStoreproce
                = "{call mb_appmaster.p_searchCustomer(?, ?, ?)}";
        try {
                        
            
            ResultSet rs;
            
            conn = daoConnect.getSMSBankingConnect();            
            
            CallableStatement calstatement 
                    = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
                calstatement.setString(1, pv_cifNo);
                calstatement.setString(2, pv_bankAccount);
                calstatement.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                
                rs = (ResultSet) calstatement.getObject(3);  
                
                Customer customer ;
                while (rs.next()) {
                    
                    customer = new Customer();
                    
                    customer.setPosCode(
                            rs.getString("POSCODE")
                    );
                    
                    customer.setCifNo(
                             rs.getString("CIFNO")
                    );
                    
                    customer.setCustomerType(
                            rs.getString("CUSTTYPE")
                    );
                    
                    customer.setName(
                            rs.getString("NAME")
                    );
                    
                    customer.setBankAccount(
                            rs.getString("LEGACYAC")
                    );
                    
                    customer.setPhoneNumber(
                            rs.getString("PHONENUMBER")
                    );
                    
                    customer.setStatus(
                            rs.getString("STATUS")
                    );
                    
                    customers.add(customer);
                }
                
            
            rs.close(); 
        } catch (Exception ex) {
            
            System.err.println("getCustomers-->" + ex.getMessage());           
            
        } finally {
                      
                
            
        }
        return customers.get(0);
    }
    
    public String setCustomer(Customer customer) {
        
        String strMessage = "";
        
        String strStoreproce
                = "{call mb_appmaster.p_updateCustomer(?, ?, ?, ?, ? , ?, ?)}";
        try {
                        
                                    
            conn = daoConnect.getSMSBankingConnect();            
            
            CallableStatement calstatement 
                    = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
                calstatement.setString(1, customer.getPosCode());
                calstatement.setString(2, customer.getCifNo());
                calstatement.setString(3, customer.getName());
                calstatement.setString(4, customer.getBankAccount());
                calstatement.setString(5, customer.getPhoneNumber());
                calstatement.setString(6, customer.getStatus());
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.executeUpdate();
                
                strMessage = (String) calstatement.getObject(7);  
                                                        
           
        } catch (Exception ex) {
            
            System.err.println("getCustomers-->" + ex.getMessage());           
            
        } finally {
                                 
        }        
        
        return strMessage;
    }
}
