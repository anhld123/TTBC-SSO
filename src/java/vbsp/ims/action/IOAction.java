/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import vbsp.ims.dao.*;
import vbsp.ims.define.Define;
import vbsp.ims.model.Menu;
import vbsp.ims.model.MenuItem;
import vbsp.ims.model.MenuManager;
import vbsp.ims.model.UserStaticInfor;

/**
 *
 * @author Trung
 */
public class IOAction extends ActionSupport 
    implements SessionAware,
        ServletRequestAware,
        ServletResponseAware,
        ServletContextAware {

    private String username, password;
    private String unitDescript = "";
    private String reportGrade = "";
    private ServletContext servletContext;
    private HttpServletRequest servletRequest;
    private HttpServletResponse servletResponse;
    private Map sessionMap;
    private Menu menu;
    private List<String> mapgd;
    private String message;
    private List<UserStaticInfor> grade_static;
    private List<MenuItem> menuItems ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getMapgd() {
        return mapgd;
    }

    public void setMapgd(List<String> mapgd) {
        this.mapgd = mapgd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUnitDescript() {
        return unitDescript;
    }

    public void setUnitDescript(String unitDescript) {
        this.unitDescript = unitDescript;
    }

    public String getReportGrade() {
        return reportGrade;
    }

    public void setReportGrade(String reportGrade) {
        this.reportGrade = reportGrade;
    }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    @Override
    public void setSession(Map map) {
        this.sessionMap = map;
    }

    @Override
    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String beforeLogin_Proccess(){      
        DaoLogin daoLogin = new DaoLogin();
        grade_static = daoLogin.getUserStaticInfor();
        return "success";
    }

    public List<UserStaticInfor> getGrade_static() {
        return grade_static;
    }
                    
    public String login() {
        String referrer = servletRequest.getHeader("referer");        
        if (referrer != null && username.trim().length() > 0
                && password.trim().length() > 0) {
            try {
                DaoLogin userLogin = new DaoLogin(username, password);
                if (userLogin.validate()) {
                    setMessage("Đã login thành công");
                    String lcRptGrade = IMSRptDao.getReportGrade(username);                    
                    Define.M_ROOT = servletRequest.getRealPath("/");
                    if(!Define.M_ROOT.endsWith("/"))
                        Define.M_ROOT +="/";
                    if (lcRptGrade.contains(reportGrade)) {
                        int onlineUserCount = 0;
                        synchronized (servletContext) {
                            try {
                                onlineUserCount = (Integer) servletContext
                                        .getAttribute("onlineUserCount");
                            } catch (Exception e) {
                            }
                            servletContext.setAttribute("onlineUserCount",
                                    onlineUserCount + 1);
                            // Set title
                            unitDescript = IMSRptDao.getTitle(username);
                            servletContext.setAttribute("unitDescript", unitDescript);
                            // Cap nhat vao lich su thong ke login
                            String ipaddress = servletRequest.getRemoteAddr();
                            userLogin.updateLoginStatic(username,ipaddress,Integer.parseInt(reportGrade));
                        }
                    } else {
                        String lcRptGradeDecript = "";
                        switch (Integer.parseInt(reportGrade)) {
                            case 1:
                                lcRptGradeDecript = "PGD";
                                break;
                            case 2:
                                lcRptGradeDecript = "Chi nhánh";
                                break;
                            case 3:
                                lcRptGradeDecript = "Toàn quốc";
                                break;
                        }
                        setMessage("Bạn không được quyền đăng nhập ở cấp báo cáo: " + lcRptGradeDecript);
                        return "error";
                    }                    
                    menuItems = IMSRptDao.getMenu(username);                                                
                    MenuManager.setMenus(menuItems);                    
                    return "success";
                } else {
                    setMessage("Tên đăng nhập hoặc mật khẩu không đúng ?");
                    return "error";
                }
            } catch (Exception ex) {
                setMessage(getMessage()+ ex.getMessage());
                return "error";
            }
        } else {
            return "error";
        }
    }

    public String logout() {
        if (sessionMap instanceof SessionMap) {
            ((SessionMap) sessionMap).invalidate();
        }
        int onlineUserCount = 0;
        synchronized (servletContext) {
            try {
                onlineUserCount = (Integer) servletContext
                        .getAttribute("onlineUserCount");
            } catch (Exception e) {
            }
            servletContext.setAttribute("onlineUserCount",
                    onlineUserCount - 1);
        }
        return "success";
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
    
    
}
