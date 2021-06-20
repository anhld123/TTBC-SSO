/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.smsbanking;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;

/**
 *
 * @author Trung
 */
public class CustomerQueryAction
        extends ActionSupport {
    
    private String keyStr; // posCode hoac cifNo
    private List<Customer> customers;
    private String cifNo;
    private String bankAccount;
    private Customer customer;
    private String message;
            
    
    public CustomerQueryAction(){}

    
    public String listCustomers(){
        
        CustomerQueryDao queryDao 
                = new CustomerQueryDao();
        
        customers = queryDao.getCustomers(keyStr);
        
        return SUCCESS;
    }

    public String viewCustomer(){
        customer = new Customer();
        
        CustomerQueryDao queryDao 
                = new CustomerQueryDao();
        
        customer = queryDao.getCustomer(cifNo, bankAccount);
//        customer.setCifNo(cifNo);
//        customer.setBankAccount(bankAccount);
//        customer.setStatus("C");
        
        return SUCCESS;
    }
    
    public String updateCustomer(){
        
        CustomerQueryDao queryDao 
                = new CustomerQueryDao();
        
        String strMessage = queryDao.setCustomer(customer);
        
        if (strMessage.equals("SUCCESS"))        
            message = "(**)Cập nhật [<dam><xanh>" + customer.getCifNo()+ "<xanh><dam>] thành công.";
        else
            message = "(**)Cập nhật thất bại [<do>" + strMessage + "<do>]";
        
        return SUCCESS;
    }
    
    public String getKeyStr() {
        return keyStr;
    }

    public void setKeyStr(String keyStr) {
        this.keyStr = keyStr;
    }
    
    

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public String getCifNo() {
        return cifNo;
    }

    public void setCifNo(String cifNo) {
        this.cifNo = cifNo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
    
}
