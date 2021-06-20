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
import vbsp.ims.model.TT49AddInforView;
import vbsp.ims.model.TT49Manager;

/**
 *
 * @author Trung
 */
public class TT49ViewAction extends ActionSupport
        implements ServletRequestAware{

    private static final long serialVersionUID = 5078264277068533593L;
    //--------------------------------------------------------------------------
    private Integer rows = 0;
    private Integer page = 0;
    private String sord;
    private String id;
    // Search Field
    private String searchField;
    // The Search String
    private String searchString;
    // he Search Operation
    // ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
    private String searchOper;
    private Integer total = 0;
    private Integer records = 0;
    private HttpServletRequest request;
    private List<TT49AddInforView> addInforViewList = new ArrayList<>();
//    private TT49AddInforView viewObj = new TT49AddInforView();
    
    private String d2;
    private String d3;
    private String d4;
    
    public String viewAddInfor() {
        if (searchString != null && searchOper != null) {
//            String key = searchString.trim();            
//            acInforViewList = AccountInforViewManager.find(key, searchOper);
//            records = acInforViewList.size();
//            total = (int) Math.ceil((double) records / (double) rows);
        } else {
            int to = (rows * page);
            int from = to - rows;
            TT49Manager.init();
            records = TT49Manager.getTotalCount();
            addInforViewList = TT49Manager.find(from, to);
            total = (int) Math.ceil((double) records / (double) rows);
        }
        return "success";
    }

    public String editAddInfor() {
        System.err.println(id + "~" + d2 + "~" + d3 + "~" + d4);
        TT49Manager.update(id, d2, d3, d4);
        return "success";
    }

//    @Override
//    public TT49AddInforView getModel() {
//        return viewObj;
//    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<TT49AddInforView> getAddInforViewList() {
        return addInforViewList;
    }

    public void setAddInforViewList(List<TT49AddInforView> addInforViewList) {
        this.addInforViewList = addInforViewList;
    }

    public String getD2() {
        return d2;
    }

    public void setD2(String d2) {
        this.d2 = d2;
    }

    public String getD3() {
        return d3;
    }

    public void setD3(String d3) {
        this.d3 = d3;
    }

    public String getD4() {
        return d4;
    }

    public void setD4(String d4) {
        this.d4 = d4;
    }
              
}
