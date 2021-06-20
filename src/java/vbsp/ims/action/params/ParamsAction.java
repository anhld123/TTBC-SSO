/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action.params;

import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import vbsp.ims.dao.base.DaoParamsFactory;

/**
 *
 * @author Le Duc Hung
 */
public class ParamsAction extends ActionSupport{
    private Map<String, String> branchList = new HashMap<String, String>();
    private Map<String, String> posList = new HashMap<String, String>();
    private Map<String, String> communeList = new HashMap<String, String>();
    private Map<String, String> groupList = new HashMap<String, String>();    
    private String branchCode;
    private String branchName;
    private String posCode;
    private String posName;
    private String communeCode;
    private String communeName;
    private String groupCode;
    private String groupName;       
    
    private String impClassName;    
    private String procedureName;
                        
    public String loadBranchInfo(){
        try {
            branchList = DaoParamsFactory.createDaoParam(impClassName).getBranchInfo(procedureName);                        
            return SUCCESS;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(ParamsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ERROR;
    }
    
    public String loadPosInfo(){
        try {
            posList = DaoParamsFactory.createDaoParam(impClassName).getPosInfo(branchCode, procedureName);                        
            return SUCCESS;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(ParamsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ERROR;
    }
    
    public String loadCommuneInfo(){
        try {
            communeList = DaoParamsFactory.createDaoParam(impClassName).getCommuneInfo(posCode, procedureName);                        
            return SUCCESS;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(ParamsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ERROR;
    }
    
    public String loadGroupInfo(){
        try {
            groupList = DaoParamsFactory.createDaoParam(impClassName).getGroupInfo(communeCode, procedureName);                        
            return SUCCESS;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(ParamsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ERROR;
    }
    
    public String loadGroupByPos(){
        try {
            groupList = DaoParamsFactory.createDaoParam(impClassName).getGroupByPos(posCode, procedureName);                        
            return SUCCESS;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(ParamsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ERROR;
    }
    
    /**
     * @return the branchList
     */
    public Map<String, String> getBranchList() {
        return branchList;
    }

    /**
     * @param branchList the branchList to set
     */
    public void setBranchList(Map<String, String> branchList) {
        this.branchList = branchList;
    }

    /**
     * @return the posList
     */
    public Map<String, String> getPosList() {
        return posList;
    }

    /**
     * @param posList the posList to set
     */
    public void setPosList(Map<String, String> posList) {
        this.posList = posList;
    }

    /**
     * @return the communeList
     */
    public Map<String, String> getCommuneList() {
        return communeList;
    }

    /**
     * @param communeList the communeList to set
     */
    public void setCommuneList(Map<String, String> communeList) {
        this.communeList = communeList;
    }

    /**
     * @return the groupList
     */
    public Map<String, String> getGroupList() {
        return groupList;
    }

    /**
     * @param groupList the groupList to set
     */
    public void setGroupList(Map<String, String> groupList) {
        this.groupList = groupList;
    }

    /**
     * @return the branchCode
     */
    public String getBranchCode() {
        return branchCode;
    }

    /**
     * @param branchCode the branchCode to set
     */
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    /**
     * @return the branchName
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * @param branchName the branchName to set
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * @return the posCode
     */
    public String getPosCode() {
        return posCode;
    }

    /**
     * @param posCode the posCode to set
     */
    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    /**
     * @return the posName
     */
    public String getPosName() {
        return posName;
    }

    /**
     * @param posName the posName to set
     */
    public void setPosName(String posName) {
        this.posName = posName;
    }

    /**
     * @return the communeCode
     */
    public String getCommuneCode() {
        return communeCode;
    }

    /**
     * @param communeCode the communeCode to set
     */
    public void setCommuneCode(String communeCode) {
        this.communeCode = communeCode;
    }

    /**
     * @return the communeName
     */
    public String getCommuneName() {
        return communeName;
    }

    /**
     * @param communeName the communeName to set
     */
    public void setCommuneName(String communeName) {
        this.communeName = communeName;
    }

    /**
     * @return the groupCode
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * @param groupCode the groupCode to set
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }            

    /**
     * @return the impClassName
     */
    public String getImpClassName() {
        return impClassName;
    }

    /**
     * @param impClassName the impClassName to set
     */
    public void setImpClassName(String impClassName) {
        this.impClassName = impClassName;
    }

    /**
     * @return the procedureName
     */
    public String getProcedureName() {
        return procedureName;
    }

    /**
     * @param procedureName the procedureName to set
     */
    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }
}
