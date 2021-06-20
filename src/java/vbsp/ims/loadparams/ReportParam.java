package vbsp.ims.loadparams;

import java.util.List;

//CuongBM: 16-Apr-14
//Desc: Các trường của báo cáo
public class ReportParam {
    private String type;    //Kieu: T: text, L:list, D: date
    private String label;  //Label: nhan bao cao
    private String fieldName; //field_name: Ten truong tham so
    private List<Combo> comboList;  //Truong nay chi dung cho selectbox, day la gia tri hien thi tren select
    private int orderNumber; // So thu tu
    private String action;
    
    public String getType() {
        return type;
    } 

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public List<Combo> getComboList() {
        return comboList;
    }

    public void setComboList(List<Combo> comboList) {
        this.comboList = comboList;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public ReportParam(){
    
    }            
}
