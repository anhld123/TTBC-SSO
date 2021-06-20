/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.balanceadjust;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.BalanceSheetAdjustDao;
import vbsp.ims.dao.IMSRptDao;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class BalanceSheetAdjustCombo 
extends ActionSupport{
    
    private List<ListValue> posList = new ArrayList<>();
    private List<ListValue> accountList = new ArrayList<>();    
    
    private String acc_type;
//    private AdjustAccount aAccount;
    
     public String buildCombo(){
        posList = IMSRptDao.getPosList("ADMIN");
        

        
        
        return "success";
    }
     
     public String buildAccCombo(){
         System.err.println("buildAccCombo"+acc_type);                  
                 BalanceSheetAdjustDao adjustDao 
                = new BalanceSheetAdjustDao();
        if (acc_type == null 
                || acc_type.isEmpty()
                || acc_type.equals("1")){
            accountList = adjustDao.getAccountList("GL");
        }else {
            accountList = adjustDao.getAccountList("SBV");
        }
//        aAccount = new AdjustAccount();
//        aAccount.setTk("222222222222222222222");
         return SUCCESS;
    }
    
    public List<ListValue> getPosList() {
        return posList;
    }

    public void setPosList(List<ListValue> posList) {
        this.posList = posList;
    }

    public List<ListValue> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<ListValue> accountList) {
        this.accountList = accountList;
    }

    public String getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(String acc_type) {
        this.acc_type = acc_type;
    }

//    public AdjustAccount getaAccount() {
//        return aAccount;
//    }
//
//    public void setaAccount(AdjustAccount aAccount) {
//        this.aAccount = aAccount;
//    }

    
    
    
}
