package vbsp.ims.sso;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpSession;

public class SessionAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    @Override
    public String execute() throws Exception {
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        
        // Kiểm tra xem người dùng đã đăng nhập hoặc session còn hạn không
        if (session == null || session.getAttribute("USER_SESSION") == null) {
            return "login"; 
        }

        return SUCCESS;
    }
}