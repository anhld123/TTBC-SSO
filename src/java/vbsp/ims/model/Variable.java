/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.model;

/**
 *
 * @author Trung
 */
public class Variable {
    //--------------------------------------------------------------------------   
    String varName;
    String varDesc;
    String varValue;
    String varType;
    String updQry;
    String status;    
    String pos_cd;
    String pos_flag;
    //--------------------------------------------------------------------------   
    
    public Variable(){}
    public Variable(String name,String desc,String value,
            String type,String updQry,String status,String pos_cd,String pos_flag){
        this.varName = name;
        this.varDesc = desc;
        this.varValue = value;
        this.varType = type;
        this.updQry = updQry;
        this.status = status;
        this.pos_cd = pos_cd;
        this.pos_flag = pos_flag;
    }
    
    //--------------------------------------------------------------------------   
    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
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

    public String getUpdQry() {
        return updQry;
    }

    public void setUpdQry(String updQry) {
        this.updQry = updQry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
        
    //--------------------------------------------------------------------------

    public String getPos_cd() {
        return pos_cd;
    }

    public void setPos_cd(String pos_cd) {
        this.pos_cd = pos_cd;
    }

    public String getPos_flag() {
        return pos_flag;
    }

    public void setPos_flag(String pos_flag) {
        this.pos_flag = pos_flag;
    }
}
