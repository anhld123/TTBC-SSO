/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.jasper;

/**
 *
 * @author Trung
 */
public class JasperParam {
    private String paramName;
    private String paramType;
    private String corespondParamType;
    private String paramDescript;
    private int order;
    private String refTable;
    private String displayColumn;
    private String paramColumn;
    private String filterCondition;
    private String orderCondition;
    public JasperParam(){}
    public JasperParam(String paramName,String paramType,
            String paramDescript, int order){
        this.paramName = paramName;
        this.paramType = paramType;
        this.corespondParamType = paramType;
        this.paramDescript =  paramDescript;
        this.order = order;
    }
    public JasperParam(String paramName,String paramType,
            String paramDescript, int order,String refTable,String displayColumn,
            String paramColumn,String filterCondition,String orderCondition){
        this.paramName = paramName;
        this.paramType = paramType;
        this.corespondParamType = paramType;
        this.paramDescript =  paramDescript;
        this.order = order;
        this.refTable = refTable;
        this.displayColumn = displayColumn;
        this.paramColumn = paramColumn;
        this.filterCondition = filterCondition;
        this.orderCondition = orderCondition;
    }
    public String getParamName() {
        return paramName;
    }
    public void setParamName(String paramName) {
        this.paramName = paramName;
    } 
    public String getParamType() {
        return paramType;
    }
    public void setParamType(String paramType) {
        this.paramType = paramType;
    }
    public String getParamDescript() {
        return paramDescript;
    }
    public void setParamDescript(String paramDescript) {
        this.paramDescript = paramDescript;
    }    
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }
    public String getRefTable() {
        return refTable;
    }
    public void setRefTable(String refTable) {
        this.refTable = refTable;
    }
    public String getDisplayColumn() {
        return displayColumn;
    }
    public void setDisplayColumn(String displayColumn) {
        this.displayColumn = displayColumn;
    }
    public String getParamColumn() {
        return paramColumn;
    }
    public void setParamColumn(String paramColumn) {
        this.paramColumn = paramColumn;
    }
    public String getFilterCondition() {
        return filterCondition;
    }
    public void setFilterCondition(String filterCondition) {
        this.filterCondition = filterCondition;
    }
    public String getOrderCondition() {
        return orderCondition;
    }
    public void setOrderCondition(String orderCondition) {
        this.orderCondition = orderCondition;
    }    
    public String getCorespondParamType() {
        return corespondParamType;
    }
    public void setCorespondParamType(String corespondParamType) {
        this.corespondParamType = corespondParamType;
    }    
    public void setInfor(String paramType,
            String paramDescript, int order,String refTable,String displayColumn,
            String paramColumn,String filterCondition,String orderCondition){        
        this.paramType = paramType;
        this.corespondParamType = paramType;
        this.paramDescript =  paramDescript;
        this.order = order;
        this.refTable = refTable;
        this.displayColumn = displayColumn;
        this.paramColumn = paramColumn;
        this.filterCondition = filterCondition;
        this.orderCondition = orderCondition;
    }
}
