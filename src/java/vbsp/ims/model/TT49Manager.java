/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.TT49AddInforDao;

/**
 *
 * @author Trung
 */
public class TT49Manager {

    private static int totalCount = 0;
    private static List<TT49AddInforView> addInforViewList;

    //-------------------------------------------------------------------------- 
    public static List<TT49AddInforView> find(int from, int to) {
        ArrayList<TT49AddInforView> findList = new ArrayList<>();
        to = (to > totalCount) ? totalCount : to;
        from = (from > to || from < 0) ? 0 : from;
        for (int i = from; i < to; i++) {
            findList.add(addInforViewList.get(i));
        }
        return findList;
    }

    //-------------------------------------------------------------------------- 
    public static List<TT49AddInforView> find(String searchKey, String searchOper) {
        ArrayList<TT49AddInforView> findList = new ArrayList<>();
        if (searchKey == null || searchKey.trim().isEmpty()) {
            findList = (ArrayList) addInforViewList;
        } else {

        }
        return findList;
    }

    //-------------------------------------------------------------------------- 
    public static TT49AddInforView find(int id) {
        return addInforViewList.get(id);
    }

    //-------------------------------------------------------------------------- 
    public static void init() {
        TT49AddInforDao tt49AddInforDao = new TT49AddInforDao();
        addInforViewList = tt49AddInforDao.list();
        totalCount = addInforViewList.size();
    }

    public static int getTotalCount() {
        return totalCount;
    }

    public static String update(String code,String d2,String d3,String d4){
        String status = "";
        TT49AddInforDao tt49AddInforDao = new TT49AddInforDao();
        for (TT49AddInforView tT49AddInforView : addInforViewList) {
            if (tT49AddInforView.getCode().equals(code)){
                status = tt49AddInforDao.update(new TT49AddInforView( 
                        tT49AddInforView.getCode(), 
                        tT49AddInforView.getDescription(), 
                        tT49AddInforView.getD1(), 
                        d2, 
                        d3, 
                        d4
                ));
            }
        }
        return status;
    }
}
