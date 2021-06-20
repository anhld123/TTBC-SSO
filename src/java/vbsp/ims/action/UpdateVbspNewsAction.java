/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.model.VbspNews;
import vbsp.ims.model.VbspNewsUpdate;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class UpdateVbspNewsAction extends ActionSupport
implements ServletRequestAware{
    
    private List<ListValue> vbspNewsList;
    private String newsId;
    private String title;
    private String newContent;
    private String message;
    HttpServletRequest request;
    
    public UpdateVbspNewsAction(){}
    
    public UpdateVbspNewsAction(String title,String newContent){
        this.title = title;
        this.newContent = newContent;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    public List<ListValue> getVbspNewsList() {
        return vbspNewsList;
    }

    public void setVbspNewsList(List<ListValue> vbspNewsList) {
        this.vbspNewsList = vbspNewsList;
    }

    public String getTitle() {
        return title;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }
        
    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNewContent() {
        return newContent;
    }

    public void setNewContent(String newContent) {
        this.newContent = newContent;
    }
    
    public String generateVbspNewsCombo(){
        vbspNewsList = VbspNewsUpdate.getVbspNewsList();        
        return "success";
    }
    
    public String viewVbspNews(){
        if (Integer.parseInt(this.newsId) == 0){
            this.title = "<< Tiêu đề >>";
            this.newContent = "<< Nội dung >>";
            VbspNewsUpdate.setUpdateType("01");
            VbspNewsUpdate.setNewsId(0);
        }else {
            VbspNews lcVbspNews = VbspNewsUpdate.findNews(Integer.parseInt(this.newsId));
            this.title = lcVbspNews.getTitle();
            this.newContent = lcVbspNews.getMessage();
            VbspNewsUpdate.setUpdateType("02");
            VbspNewsUpdate.setNewsId(Integer.parseInt(this.newsId));
        }
        return "success";
    }
    
    public String updateVbspNews(){
        String userName = request.getSession().getAttribute("username").toString();
//        System.err.println(VbspNewsUpdate.getUpdateType() + ":" + this.title + "-->" + this.newContent +
        boolean updatestatus = VbspNewsUpdate.update(title, newContent, userName);
        if (updatestatus){
            this.vbspNewsList = VbspNewsUpdate.getVbspNewsList();
            this.message = "(*)Cập nhật thành công";
        }
        else 
            this.message = "(*)Cập nhật thất bại";
        return "success";
    }
    
    
    public String deleteVbspNews(){
        System.err.println("Delete ---> " + this.newsId);     
        if (Integer.parseInt(this.newsId) == 0)
            this.message = "(*)Bạn chưa chọn thông điệp";
        else {
            if (VbspNewsUpdate.delete(Integer.parseInt(this.newsId))){
                this.vbspNewsList = VbspNewsUpdate.getVbspNewsList();
                this.message = "(*)Thông điệp đã được xoá";
            }
            else
                this.message = "(*)Xoá Thông điệp thất bại";
        }
        return "success";
    }
    
}
