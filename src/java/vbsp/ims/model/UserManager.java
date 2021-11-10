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
public class UserManager {

    private List<User> users;
    public String userCode;

    public UserManager() {
        users = new ArrayList<>();
        //users = IMSRptDao.getUsers();
    }

    public List<User> getUsers() {
        users = IMSRptDao.getUsers();
        return users;
    }

    public List<User> getUsers(String userName,String managerPosCode) {
        ArrayList<User> listOfUser = new ArrayList<>();
        users = IMSRptDao.getUsers();
        if (managerPosCode.trim().equals("000100")) {
            //listOfUser = (ArrayList<User>)users;
            for (User user : users) {
                if (!user.getPriUserCode().equals(userName)) {
                    listOfUser.add(user);
                }
            }
        } else {
            String lcSequenceOfPos = IMSRptDao.getSequenceStringOfSubPos(managerPosCode);
            for (User user : users) {
                if (lcSequenceOfPos.contains(user.getPriPosCode().trim())
                        && !user.getPriUserCode().equals(userName)) {
                    listOfUser.add(user);
                }
            }
        }
        return listOfUser;
    }

    public void create(User user) {
        //users.add(user);
        IMSRptDao.addUser(user);
//      refresh();
    }

    public User find(String userCode) {
        users = IMSRptDao.getUsers();
        for (User user : users) {
            if (user.getPriUserCode().equals(userCode)) {
                System.out.println("found");
                return user;
            }
        }
        return null;
    }

    public void update(User user, String updateType,String updatePassword) {
        IMSRptDao.updateUser(user, updateType,updatePassword);
//        String lcUserCode = user.getPriUserCode();
//        for (User lcUser : users) {
//            if (lcUser.getPriUserCode().equals(lcUserCode)) {
//                lcUser.setPriUserName(user.getPriUserName());
//                lcUser.setPriAddress(user.getPriAddress());
//                lcUser.setPriMobile(user.getPriMobile());
//                lcUser.setPriOffice(user.getPriOffice());
//                lcUser.setPriPassword(user.getPriPassword());
//                lcUser.setPriPosCode(user.getPriPosCode());
//                lcUser.setPriPubKey(user.getPriPubKey());
//                lcUser.setPriRptGrade(user.getPriRptGrade());
//                lcUser.setPriStatus(user.getPriStatus());
//                lcUser.setPriUserGroup(user.getPriUserGroup());
//                lcUser.setPriMaCanBo(user.getPriMaCanBo());
//                lcUser.setPriNhomCongViec(user.getPriNhomCongViec());
//                IMSRptDao.updateUser(lcUser, updateType,updatePassword);
//                refresh();
//                break;
//            }
//        }
    }

    public void delete(String userCode) {
        IMSRptDao.deleteUser(userCode);
//        for (User user : users) {
//            if (user.getPriUserCode().equals(userCode)) {
//                users.remove(user);
//                vbsp.ims.dao.IMSRptDao.deleteUser(userCode);
//                break;
//            }
//        }
    }
    
//    public static void refresh(){
//        users = IMSRptDao.getUsers();
//    }

}
