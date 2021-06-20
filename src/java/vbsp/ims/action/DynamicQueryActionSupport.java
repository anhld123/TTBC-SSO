package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;

public class DynamicQueryActionSupport extends ActionSupport {
    private String title;
    private String query;
    
    public DynamicQueryActionSupport() {
    }
    
    public String execute() throws Exception {
        return "success";
    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getQuery() {
        return query;
    }
    
    public void setQuery(String query) {
        this.query = query;
    }
//</editor-fold>
}
