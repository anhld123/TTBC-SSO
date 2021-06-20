/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.model;

import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.CommuneDao;

/**
 *
 * @author Trung
 */
public class CommuneManager {
    //--------------------------------------------------------------------------    
    private static int totalCount = 0;
    private static List<Commune> communes;
    
    private final String user_name;
    private final int report_grade;
            
    public CommuneManager(String user_name,int report_grade){
        this.user_name = user_name;
        this.report_grade = report_grade;
        CommuneDao cmDao = new CommuneDao();
        communes = cmDao.getCommuneList(user_name, report_grade);
        totalCount = communes.size();
    }
    
    public List<Commune> find(int from, int to) {
        ArrayList<Commune> findList = new ArrayList<>();
        to = (to > totalCount) ? totalCount : to;
        from = (from > to || from < 0) ? 0 : from;
        for (int i = from; i < to; i++) {
            findList.add(communes.get(i));
        }
        return findList;
    }

    public Commune getCommune(int index){
        return communes.get(index);
    }
    
    public int getTotalCount() {
        return totalCount;
    }
    
    
    
}
