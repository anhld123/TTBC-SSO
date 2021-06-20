/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.model;

import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.VariableDao;

/**
 *
 * @author Trung
 */
public class VariableManager {
    //--------------------------------------------------------------------------    
    private int totalCount = 0;
    private static List<Variable> variableList;
    private String pos_cd;
    private String pos_flag;       
    static VariableDao variableDao ;//= new VariableDao();
    //-------------------------------------------------------------------------- 
    public VariableManager(){        
        variableDao = new VariableDao()   ; 
        variableDao.initTempTable();
    }
        
    
    public VariableManager(String pos_cd,String pos_flag){
        this.pos_cd = pos_cd;
        this.pos_flag = pos_flag;
        variableDao.initTempTable();
    }
    
    public List<Variable> find(int from, int to){
        ArrayList<Variable> findList = new ArrayList<>();
        to = (to > totalCount) ? totalCount : to;
        from = (from > to || from < 0) ? 0 : from;
        for(int i = from; i < to; i++){
            findList.add(variableList.get(i));
        }
        return findList;
    }
    //-------------------------------------------------------------------------- 
    public Variable find(int id){        
        return variableList.get(id);
    }
    //-------------------------------------------------------------------------- 
    public void update(Variable variable,String mkr_id){
        for(Variable var: variableList){
            if (var.getVarName().trim().equals(variable.getVarName())){
                var.setVarDesc(variable.getVarDesc());
                var.setVarValue(variable.getVarValue());
                var.setVarType(variable.getVarType());
                break;
            }
        }
        variableDao.updateVariable(variable,mkr_id);
//        init();
    }
    //-------------------------------------------------------------------------- 
    public void createVariableList(String pos_cd,String pos_flag){
        this.pos_cd = pos_cd;
        this.pos_flag = pos_flag;        
        variableList = variableDao.getVariableList(pos_cd,pos_flag);
        totalCount = variableList.size();        
    }
    //-------------------------------------------------------------------------- 
     public String updateMainTable(String pos_cd,String pos_flg,String effect_dt){
         return variableDao.updateMainTable(pos_cd, pos_flg, effect_dt);
     }
     //-------------------------------------------------------------------------- 
    public  int getTotalCount() {
        return totalCount;
    }
    public List<Variable> getVariableList() {
        return variableList;
    }    
    public void setPos_cd(String pos_cd) {
        this.pos_cd = pos_cd;
    }
    public void setPos_flag(String pos_flag) {
        this.pos_flag = pos_flag;
    }
    //-------------------------------------------------------------------------- 
   
}
