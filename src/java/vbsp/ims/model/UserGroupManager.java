/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import vbsp.ims.dao.IMSRptDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Trung
 */
public class UserGroupManager {

    private static List<UserGroup> userGroups;
    public static String userGroupCode;    

    public UserGroupManager() {
        userGroups = new ArrayList<>();
        userGroups = IMSRptDao.getUserGroups();
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }
    
    public List<UserGroup> getUserGroups(String userName) {
        String js_privileage = IMSRptDao.findPrivilegeByUsr(userName);
        System.err.println("UserGroupAction~" + js_privileage);
        List<UserGroup> jl_usergroup = new ArrayList<>();
        for (UserGroup userGrp : userGroups){
            if(js_privileage.contains(userGrp.getPriGroupCode())){
                jl_usergroup.add(userGrp);
            }
        }
        System.err.println("UserGroupAction~" + jl_usergroup.size());
        return jl_usergroup;
    }

    public void create(UserGroup userGroup,String pMaker) {
        userGroups.add(userGroup);
        IMSRptDao.addUserGroup(userGroup,pMaker);
    }

    public UserGroup find(String userGroupCode) {
        for (UserGroup usegroup : userGroups) {
            if (usegroup.getPriGroupCode().equals(userGroupCode)) {
//                System.out.println("found");
                return usegroup;
            }
        }
        return null;
    }

    public void update(UserGroup pUserGroup,String pMaker) {
        
        String lcUserGroupCode = pUserGroup.getPriGroupCode();
        
        for (UserGroup usegroup : userGroups) {
            if (usegroup.getPriGroupCode().equals(lcUserGroupCode)) {
                usegroup.setPriGroupDesc(pUserGroup.getPriGroupDesc());
                usegroup.setPriGroupAlias(pUserGroup.getPriGroupAlias());
                usegroup.setPriPrivilege(pUserGroup.getPriPrivilege());
                usegroup.setPriGroupStatus(pUserGroup.getPriGroupStatus());
                usegroup.setPriviewType(pUserGroup.getPriviewType());
                // Cập nhật vào Database
                IMSRptDao.updateUserGroup(usegroup,pMaker);
            }
        }
    }

    public void delete(String userGroupCode) {
        for (UserGroup usegroup : userGroups) {
            if (usegroup.getPriGroupCode().equals(userGroupCode)) {
                userGroups.remove(usegroup);
                IMSRptDao.deleteUserGroup(userGroupCode);
                break;
            }
        }
    }
    
    public List<MenuItem> getListOfMenuItem(){
        return IMSRptDao.getMenuItemList();
    }
    
    
}
