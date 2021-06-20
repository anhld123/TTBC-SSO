/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.model.AccountInforView;
import vbsp.ims.model.AccountInforViewManager;

/**
 *
 * @author Trung
 */
public class AccountInforViewAction extends ActionSupport
        implements ServletRequestAware {

    private static final long serialVersionUID = 5078264277068533593L;
    //--------------------------------------------------------------------------
    private Integer rows = 0;
    private Integer page = 0;
    private String sord;
    private String sidx;
    // Search Field
    private String searchField;
    // The Search String
    private String searchString;
    // he Search Operation
    // ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
    private String searchOper;
    private Integer total = 0;
    private Integer records = 0;
    private String accountType;
    private HttpServletRequest request;
    private List<AccountInforView> acInforViewList = new ArrayList<>();

    public String buildRadio() {
        System.err.println("accountType -->" + accountType);
        if (accountType.equals("1")) {
            AccountInforViewManager.setType(1);
        } else {
            AccountInforViewManager.setType(2);
        }
        return "success";
    }

    public String listAccountInfor() {
//        System.err.println("accountType -->" + accountType);
        System.err.println("Search --> " + searchString);        
        if (searchString != null && searchOper != null) {
            String key = searchString.trim();            
            acInforViewList = AccountInforViewManager.find(key, searchOper);
            records = acInforViewList.size();
            total = (int) Math.ceil((double) records / (double) rows);
        } else {
            int to = (rows * page);
            int from = to - rows;
            AccountInforViewManager.init();
            records = AccountInforViewManager.getTotalCount();
            acInforViewList = AccountInforViewManager.find(from, to);
            total = (int) Math.ceil((double) records / (double) rows);
        }
        return "success";
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchOper() {
        return searchOper;
    }

    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public List<AccountInforView> getAcInforViewList() {
        return acInforViewList;
    }

    public void setAcInforViewList(List<AccountInforView> acInforViewList) {
        this.acInforViewList = acInforViewList;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
