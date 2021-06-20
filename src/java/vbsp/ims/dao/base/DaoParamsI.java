/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dao.base;

import java.util.Map;

/**
 *
 * @author Le Duc Hung
 */
public interface DaoParamsI {    
    
    public Map<String, String> getBranchInfo(String procedureName);        
    
    public Map<String, String> getPosInfo(String branchCode, String procedureName);       
    
    public Map<String, String> getCommuneInfo(String posCode, String procedureName);        
    
    public Map<String, String> getGroupInfo(String communeCode, String procedureName);
    
    public Map<String, String> getGroupByPos(String posCode, String procedureName);
}
