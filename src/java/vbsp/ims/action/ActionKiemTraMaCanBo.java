/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.IMSRptDao;

/**
 *
 * @author Trung Nguyen
 */
public class ActionKiemTraMaCanBo extends ActionSupport{
    
    private static final long serialVersionUID = 1L;
    private List<String> dataList = null;            
    
    public String checkMaCanBo() throws Exception {
        dataList = new ArrayList<>();            
        String sMacanbo = ServletActionContext.getRequest().getParameter("MaCanBo");
        String sUsername = ServletActionContext.getRequest().getParameter("Username");
        int ktMaCanBo = IMSRptDao.checkMaCanBo(sUsername, sMacanbo);
        dataList.add(String.valueOf(ktMaCanBo));
        return "success";    
    }
    
    public String checkDangKy() throws Exception {
        dataList = new ArrayList<>();                    
        String sUsername = ServletActionContext.getRequest().getParameter("Username");        
        int ktDangKy = IMSRptDao.checkDangKy(sUsername);
        dataList.add(String.valueOf(ktDangKy));
        return "success";    
    }

    public List<String> getDataList() {
            return dataList;
    }

    public void setDataList(List<String> dataList) {
            this.dataList = dataList;
    }
}
