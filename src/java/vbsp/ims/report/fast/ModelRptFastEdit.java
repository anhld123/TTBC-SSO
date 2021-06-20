/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.report.fast;

import java.util.List;

/**
 *
 * @author LION
 */
public class ModelRptFastEdit {
    private String save_id;
    private String Module_id;
    private String title;
    private String add_where;
    //Select tranfer column
    private List<ListValue> leftModuleColumnList;
    private List<ListValue> rightModuleColumnList;
    //Select tranfer date
    private List<ListValue> leftModuleDateList;
    private List<ListValue> rightModuleDateList;

    public String getSave_id() {
        return save_id;
    }

    public void setSave_id(String save_id) {
        this.save_id = save_id;
    }

    public String getModule_id() {
        return Module_id;
    }

    public void setModule_id(String Module_id) {
        this.Module_id = Module_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdd_where() {
        return add_where;
    }

    public void setAdd_where(String add_where) {
        this.add_where = add_where;
    }

    public List<ListValue> getLeftModuleColumnList() {
        return leftModuleColumnList;
    }

    public void setLeftModuleColumnList(List<ListValue> leftModuleColumnList) {
        this.leftModuleColumnList = leftModuleColumnList;
    }

    public List<ListValue> getRightModuleColumnList() {
        return rightModuleColumnList;
    }

    public void setRightModuleColumnList(List<ListValue> rightModuleColumnList) {
        this.rightModuleColumnList = rightModuleColumnList;
    }

    public List<ListValue> getLeftModuleDateList() {
        return leftModuleDateList;
    }

    public void setLeftModuleDateList(List<ListValue> leftModuleDateList) {
        this.leftModuleDateList = leftModuleDateList;
    }

    public List<ListValue> getRightModuleDateList() {
        return rightModuleDateList;
    }

    public void setRightModuleDateList(List<ListValue> rightModuleDateList) {
        this.rightModuleDateList = rightModuleDateList;
    }
    
    
}
