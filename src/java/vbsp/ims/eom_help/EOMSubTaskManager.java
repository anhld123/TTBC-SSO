/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.eom_help;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Trung
 */
public class EOMSubTaskManager {    
    private static List<EOMTaskLog> tasklogs = new ArrayList();    
    public EOMSubTaskManager(){}

        
    public static List<EOMTaskLog> search(String key,String type){
        List<EOMTaskLog> search_list = new ArrayList<>();
        if ("1".equals(type)){
            for(EOMTaskLog tasklog: tasklogs){
                if (tasklog.getPos_cd().contains(key)){
                    search_list.add(tasklog);
                }
            }
        }else {
            for(EOMTaskLog tasklog: tasklogs){
                if (tasklog.getStatus().contains(key)){
                    search_list.add(tasklog);
                }
            }
        }
        return search_list;
    }
    
    public static List<EOMTaskLog> getTasklogs() {
        return tasklogs;
    }

    public static void setTasklogs(List<EOMTaskLog> tasklogs) {
        EOMSubTaskManager.tasklogs = tasklogs;
    }        
}
