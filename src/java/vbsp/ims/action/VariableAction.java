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
import vbsp.ims.dao.IMSRptDao;
import vbsp.ims.define.DefineFun;
import vbsp.ims.model.Variable;
import vbsp.ims.model.VariableManager;

/**
 *
 * @author Trung
 */
public class VariableAction extends ActionSupport
        implements ServletRequestAware {

    //--------------------------------------------------------------------------
    private static final long serialVersionUID = 5078264277068533593L;
    //--------------------------------------------------------------------------
    private Integer rows = 0;
    private Integer page = 0;
    private String sord;
    private String sidx;
    private String searchField;
    private String searchString;
    private String searchOper;
    private Integer total = 0;
    private Integer records = 0;
    //--------------------------------------------------------------------------
    private int id;
    private String oper;
    private String varName;
    private String varDesc;
    private String varValue;
    private String varType;
    private String updQry;
    private String app_effectDate;
    private String message;
    //--------------------------------------------------------------------------
    private List<Variable> variableList = new ArrayList<>();
    private HttpServletRequest request;

    static VariableManager variableManager = new VariableManager();
    static int view_page = 1;
    static int view_rows = 15;

    public VariableAction() {
        //variableManager = new VariableManager();
    }

    //--------------------------------------------------------------------------   
    @Override
    public String execute() throws Exception {

        System.err.println("Vao phan execution...");

        int reportGrade = Integer.parseInt(
                request.getSession().getAttribute("reportGrade").toString()
        );

        String userName = request.getSession().getAttribute(
                "username").toString();

        String pos_flag;
        switch (reportGrade) {
            case 1:
                pos_flag = "S";
                break;
            case 2:
                pos_flag = "M";
                break;
            default:
                pos_flag = "H";
                break;
        }

        //variableManager.initTempTable(userName);

        if (oper != null && oper.equalsIgnoreCase("edit")) {

            System.err.println("execute~" + oper + "~" + id + "~" + view_page + "~" + view_rows);

            variableManager.createVariableList(
                    IMSRptDao.getPosOfUser(userName), pos_flag);
            Variable var = variableManager.find((id + (view_page - 1) * view_rows) - 1);
//            var.setVarDesc(varDesc);
            var.setVarValue(varValue);
            var.setVarType(varType);
            variableManager.update(var, userName);

        } else {
            int to = (rows * page);
            int from = to - rows;
            view_page = page;
            view_rows = rows;

            variableManager.createVariableList(
                    IMSRptDao.getPosOfUser(userName), pos_flag);

            records = variableManager.getTotalCount();
            variableList = variableManager.find(from, to);
            total = (int) Math.ceil((double) records / (double) rows);
        }
        return "success";
    }

    public String updatMainTable() {

        String userName = request.getSession().getAttribute("username").toString();

        int reportGrade = Integer.parseInt(
                request.getSession().getAttribute("reportGrade").toString());
        String pos_flag;
        switch (reportGrade) {
            case 1:
                pos_flag = "S";
                break;
            case 2:
                pos_flag = "M";
                break;
            default:
                pos_flag = "H";
                break;
        }
        
        System.err.println("updatMainTable~" + app_effectDate);
        
        message = variableManager.updateMainTable(
                IMSRptDao.getPosOfUser(userName), pos_flag,
                DefineFun.convert2OracleDateFormat(app_effectDate));
        return SUCCESS;
    }

    public String reloadData() {

        String userName = request.getSession().getAttribute(
                "username").toString();

        variableManager = new VariableManager();
        //variableManager.initTempTable(userName);

        return SUCCESS;
    }

//    public String listVariable(){
//        System.err.println("listVariable -->" + oper );
//        int to = (rows * page);
//        int from = to - rows;      
//        int reportGrade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
//        String userName = request.getSession().getAttribute("username").toString();        
//        VariableManager.setPos_cd(IMSRptDao.getPosOfUser(userName));
//        String pos_flag;
//        switch(reportGrade) {
//            case 1:
//                pos_flag = "S";
//                break;
//            case 2:
//                pos_flag = "M";
//                break;
//             default:
//                 pos_flag = "H";
//                break;
//        }                    
//        VariableManager.setPos_flag(userName);
//        VariableManager.setPos_flag(pos_flag);
//        VariableManager.init();
//        records = VariableManager.getTotalCount();        
//        variableList = VariableManager.find(from, to);
//        total =(int) Math.ceil((double)records / (double)rows);
//        return "success";
//    }
//    //--------------------------------------------------------------------------
//    public String editVariable(){                
//        Variable var = VariableManager.find(id-1);
//        System.err.println("editVariable -->" + (id-1) + var.getVarName() + 
//                "~" + varDesc + "~" + varValue + "~" + varType
//        + "~" + app_effectDate + "~" + oper);
//        var.setVarDesc(varDesc);
//        var.setVarValue(varValue);
//        var.setVarType(varType);
//        VariableManager.update(var);
//        return "success";
//    }
    //-------------------------------------------------------------------------- 
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

    public String getApp_effectDate() {
        return app_effectDate;
    }

    public void setApp_effectDate(String app_effectDate) {
        this.app_effectDate = app_effectDate;
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

    public List<Variable> getVariableList() {
        return variableList;
    }

    public void setVariableList(List<Variable> variableList) {
        this.variableList = variableList;
    }

    public String getVarDesc() {
        return varDesc;
    }

    public void setVarDesc(String varDesc) {
        this.varDesc = varDesc;
    }

    public String getVarValue() {
        return varValue;
    }

    public void setVarValue(String varValue) {
        this.varValue = varValue;
    }

    public String getVarType() {
        return varType;
    }

    public void setVarType(String varType) {
        this.varType = varType;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getUpdQry() {
        return updQry;
    }

    public void setUpdQry(String updQry) {
        this.updQry = updQry;
    }

    //--------------------------------------------------------------------------       
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
