/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.AccountInforViewDao;

/**
 *
 * @author Trung
 */
public class AccountInforViewManager {

    //--------------------------------------------------------------------------    
    private static int totalCount = 0;
    private static List<AccountInforView> acInforViewList;
    private static int type = 1;

    //-------------------------------------------------------------------------- 
    public static List<AccountInforView> find(int from, int to) {
        ArrayList<AccountInforView> findList = new ArrayList<>();
        to = (to > totalCount) ? totalCount : to;
        from = (from > to || from < 0) ? 0 : from;
        for (int i = from; i < to; i++) {
            findList.add(acInforViewList.get(i));
        }
        return findList;
    }

    //-------------------------------------------------------------------------- 
    public static List<AccountInforView> find(String searchKey, String searchOper) {
        ArrayList<AccountInforView> findList = new ArrayList<>();
        if (searchKey == null || searchKey.trim().isEmpty()) {
            findList = (ArrayList)acInforViewList;
        } else {                        
            if (searchOper.equalsIgnoreCase("eq")) {
                for (AccountInforView accInforView : acInforViewList) {
                    if (accInforView.getAccount().equals(searchKey)) {
                        findList.add(accInforView);
                    }
                }
            } else if (searchOper.equalsIgnoreCase("bw")) {
                System.err.println("Search --> " +  searchKey + searchOper);
                for (AccountInforView accInforView : acInforViewList) {                    
                    if( accInforView.getAccount().length() > searchKey.length() &&
                            accInforView.getAccount().substring(0, searchKey.length()).equals(
                            searchKey.trim())) {
                        System.err.println(accInforView.getAccount().substring(0, searchKey.length()));
                        findList.add(accInforView);
                    }
                }
            } else if (searchOper.equalsIgnoreCase("ew")) {
                for (AccountInforView accInforView : acInforViewList) {
                    if (accInforView.getAccount().substring(accInforView.getAccount().length()
                            - searchKey.length()).equals(searchKey)) {
                        findList.add(accInforView);
                    }
                }
            } else if (searchOper.equalsIgnoreCase("cn")) {
                for (AccountInforView accInforView : acInforViewList) {
                    if (accInforView.getAccount().contains(searchKey)) {
                        findList.add(accInforView);
                    }
                }
            }
        }
        return findList;
    }

    //-------------------------------------------------------------------------- 
    public static AccountInforView find(int id) {
        return acInforViewList.get(id);
    }

    //-------------------------------------------------------------------------- 
    public static void init() {
        acInforViewList = AccountInforViewDao.getAccountInforList(type);
        totalCount = acInforViewList.size();
    }

    public static int getTotalCount() {
        return totalCount;
    }

    //-------------------------------------------------------------------------- 
    public static List<AccountInforView> getAcInforViewList() {
        return acInforViewList;
    }

    public static void setAcInforViewList(List<AccountInforView> acInforViewList) {
        AccountInforViewManager.acInforViewList = acInforViewList;
    }

    public static int getType() {
        return type;
    }

    public static void setType(int type) {
        AccountInforViewManager.type = type;
    }
}
