/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.eom_help;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

/**
 *
 * @author TrungNguyen
 */
public class EOMServerInforAction 
extends ActionSupport        {
    
    private ServerInfor serverInfor = new ServerInfor();
    
    Map parameters = null;
    
    private String para_subtask;
    private String para_server;
    
    
    public EOMServerInforAction(){}
    
    @Override
    public String execute(){
        para_subtask = getParameterValue("subtask");
        para_server = getParameterValue("server");
        EOMTaskHelpDao eomTaskDao = new EOMTaskHelpDao();
        serverInfor = eomTaskDao.getServerInfor(para_subtask,para_server);
        return SUCCESS;
    }
    
    public String getParameterValue(String param) {
        Object paramObj = getParameters().get(param);
        if (paramObj == null) {
            return null;
        }
        return ((String[]) paramObj)[0];
    }
    
    public final Map getParameters() {
        parameters = ActionContext.getContext().getParameters();
        return parameters;
    }        

    public ServerInfor getServerInfor() {
        return serverInfor;
    }

    public void setServerInfor(ServerInfor serverInfor) {
        this.serverInfor = serverInfor;
    }
    
    
}
