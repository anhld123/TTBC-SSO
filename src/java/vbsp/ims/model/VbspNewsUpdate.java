/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.model;

import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.IMSRptDao;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class VbspNewsUpdate {    
    private static List<VbspNews> vbspNewsList;
    private static String updateType;
    private static int newsId;
    static {
        vbspNewsList = IMSRptDao.getVbspNewsList();
        updateType = "01";
    }

    public static List<ListValue> getVbspNewsList() {
        ArrayList<ListValue> vbspNewsLst = new ArrayList<>();
        for(VbspNews vbspNews : vbspNewsList){
            vbspNewsLst.add(vbspNews.getListValue());
        }
        return vbspNewsLst;
    }
    
    public static VbspNews findNews(int id){
        for(VbspNews vbspNews : vbspNewsList){
            if(vbspNews.getId() == id){
                return vbspNews;
            }
        }
        return null;
    }

    public static String getUpdateType() {
        return updateType;
    }

    public static void setUpdateType(String updateType) {
        VbspNewsUpdate.updateType = updateType;
    }               

    public static int getNewsId() {
        return newsId;
    }

    public static void setNewsId(int newsId) {
        VbspNewsUpdate.newsId = newsId;
    }        
    
    public static boolean update(String title,String content,String userName){
        boolean updatestatus;
        if (updateType.equals("01")){
            updatestatus = IMSRptDao.addVbspNews(title, content, userName);            
        }else {
            updatestatus = IMSRptDao.updateVbspNews(newsId, title, content, userName);            
        }
        vbspNewsList = IMSRptDao.getVbspNewsList();
        return updatestatus;
    }
    
    public static boolean delete(int id){
        for(VbspNews vbspNews : vbspNewsList){
            if(vbspNews.getId() == id){
                vbspNewsList.remove(vbspNews);
                break;
            }
        }
        return IMSRptDao.deleteVbspNews(id);
    }
}
