/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import vbsp.ims.dao.ExportText2SbvDao;

/**
 *
 * @author Trung
 */
public class SbvIndicatorNote extends ActionSupport{
    private static final long serialVersionUID = 5078264277068533593L;
    
    private String group_id;
    private String message;
    private String note;
    private String username;
    
    public SbvIndicatorNote(){}
    
    @Override
    public String execute(){        
        group_id = getParameterValue("group_id");
        username = getParameterValue("username");
        ExportText2SbvDao exportDao = new ExportText2SbvDao();
        System.err.println("Lay thuyet minh chi tieu " + group_id);
        note = exportDao.getIndicatorNote(group_id);
        return SUCCESS;
    }
    
    public String save_data(){
        System.err.println("Ghi thuyet minh chi tieu " +  group_id + username);
        ExportText2SbvDao exportDao = new ExportText2SbvDao();        
        boolean saveStatus = exportDao.saveIndicatorNote(group_id, note, username);
        if (saveStatus)
            message = "<xanh> <dam>(*) Cập nhật thành công. <dam> <xanh> ";
        else
            message = "<do> <dam>(*) Bạn chưa lưu được dữ liệu. Hãy kiểm tra lại. <dam> <do>";
        return SUCCESS;
    };
    
    public String getParameterValue(String param) {
        Object paramObj = getParameters().get(param);
        if (paramObj == null) {
            return null;
        }
        return ((String[]) paramObj)[0];
    }
 
    public final Map getParameters() {
        Map parameters = null;
        parameters = ActionContext.getContext().getParameters();
        return parameters;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
