/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.Map;

/**
 *
 * @author Trung
 */
public class LoginInterceptor implements Interceptor{
    private static final long serialVersionUID = 1L;
    
    @Override
    public void destroy(){}
    
    @Override
    public void init(){}
    
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Map <String,Object> sessionAttributes = invocation.getInvocationContext().getSession();
        if (sessionAttributes == null || sessionAttributes.get("username") == null){
            return "login";
        } else {
            if (!((String)sessionAttributes.get("username") == null)){
                return invocation.invoke();
            }else {
                return "login";
            }
        }
    }
}
