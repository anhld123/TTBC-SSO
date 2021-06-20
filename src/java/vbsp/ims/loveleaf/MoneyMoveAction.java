/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.loveleaf;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import vbsp.ims.define.DefineFun;

/**
 *
 * @author Trung
 */
public class MoneyMoveAction extends ActionSupport {

    private List<MoneyTransaction> moneyTransactions;
    private String query_dt;
    private String regenerate_FLG;
    private String submit_POS;
    private String submit_DATE;
    private String message;
    private String status;

    @Override
    public String execute() {
        LoveLeafDao leafDao = new LoveLeafDao();
        String lv_mkr_id = "ADMIN";
        String regenerate_FLG_val = "N" ;
        if (regenerate_FLG.toLowerCase().equals("true"))
            regenerate_FLG_val = "Y";
        System.err.println("MoneyMoveAction~execute"
                +query_dt
                +"~"
                +regenerate_FLG
                +"~"
                +regenerate_FLG_val);        
        moneyTransactions = leafDao.get_money_transaction(
                DefineFun.convert2OracleDateFormat(query_dt),
                lv_mkr_id,
                regenerate_FLG_val);
        return SUCCESS;
    }

    public String update_status(){
        
        System.err.println("MoneyMoveAction~update_status"
                +submit_POS
                +"~"
                +submit_DATE); 
        
        LoveLeafDao loveDao = new LoveLeafDao();
        status = loveDao.update_pos_move_money(submit_POS, 
                submit_DATE
                );                
        
        if (status.equals("D")
                || status.equals("N"))
            message = "<xanh> (*) Câp nhật trạng thái thành công. <xanh>";        
        else            
            message = "<do> (*) Câp nhật trạng thái thất bại. <do>";        
        
        return SUCCESS;                
    }
    
    public List<MoneyTransaction> getMoneyTransactions() {
        return moneyTransactions;
    }

    public void setMoneyTransactions(List<MoneyTransaction> moneyTransactions) {
        this.moneyTransactions = moneyTransactions;
    }

    public String getQuery_dt() {
        return query_dt;
    }

    public void setQuery_dt(String query_dt) {
        this.query_dt = query_dt;
    }

    public String getRegenerate_FLG() {
        return regenerate_FLG;
    }

    public void setRegenerate_FLG(String regenerate_FLG) {
        this.regenerate_FLG = regenerate_FLG;
    }

    public String getSubmit_POS() {
        return submit_POS;
    }

    public void setSubmit_POS(String submit_POS) {
        this.submit_POS = submit_POS;
    }

    public String getSubmit_DATE() {
        return submit_DATE;
    }

    public void setSubmit_DATE(String submit_DATE) {
        this.submit_DATE = submit_DATE;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    
}
