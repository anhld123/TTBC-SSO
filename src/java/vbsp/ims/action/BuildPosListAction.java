/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.action;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.chamdiemtt.dao.DaoChamdiemttMain;
import vbsp.ims.dao.BuildPosTreeDao;
import vbsp.ims.dao.IMSRptDao;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class BuildPosListAction extends ActionSupport 
implements ServletRequestAware{
    
    private TreeNode nodes;
    HttpServletRequest request;
    private List<ListValue> posList = new ArrayList<>();
    private List<ListValue> userGroupList = new ArrayList<>();
    private List<ListValue> userFuncList = new ArrayList<>();

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    public List<ListValue> getPosList() {
        return posList;
    }

    public void setPosList(List<ListValue> posList) {
        this.posList = posList;
    }

    public List<ListValue> getUserGroupList() {
        return userGroupList;
    }

    public void setUserGroupList(List<ListValue> userGroupList) {
        this.userGroupList = userGroupList;
    }

    public List<ListValue> getUserFuncList() {
        return userFuncList;
    }

    public void setUserFuncList(List<ListValue> userFuncList) {
        this.userFuncList = userFuncList;
    }
        
    
    
    
    public String generatePosListCombo() {        
        String userName = request.getSession().getAttribute("username").toString();
        posList = IMSRptDao.getPosList(userName);
        return "success";
    }
    
    public String generateUserGroupListCombo() {        
        String userName = request.getSession().getAttribute("username").toString();
        userGroupList = IMSRptDao.getUserGroupList(userName);
        return "success";
    }
    
    public String generateUserFunctionListCombo() {        
        String userName = request.getSession().getAttribute("username").toString();
        String reportGrade 
                    = request.getSession().getAttribute("reportGrade").toString();
        DaoChamdiemttMain daoMain = new DaoChamdiemttMain();        
        userFuncList = daoMain.getLOV(userName, "98", reportGrade,"");
        return "success";
    }
    
    
    public String buildTreeView_VB96()
            throws Exception {
        if (request.getSession().getAttribute("startTreeGLKHTDRecursive") != null) {
            return SUCCESS;
        } else {
            int reportGrade 
                    = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
            String userName = request.getSession().getAttribute("username").toString();
            BuildPosTreeDao buildPosTreeDao = new BuildPosTreeDao(reportGrade, userName);
            buildPosTreeDao.build_vb96();
            this.nodes = buildPosTreeDao.getNodes();
            request.getSession().setAttribute("startTreeGLKHTDRecursive", "false");
            return SUCCESS;
        }
    }

    public TreeNode getNodes() {
        return nodes;
    }

    public void setNodes(TreeNode nodes) {
        this.nodes = nodes;
    }
    
    
    
}
