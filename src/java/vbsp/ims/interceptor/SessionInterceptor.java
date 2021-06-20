/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import java.util.Map;

/**
 *
 * @author Trung
 */
public class SessionInterceptor 
extends AbstractInterceptor {
  @Override
  public String intercept(ActionInvocation invocation) throws Exception {
      Map<String,Object> session = invocation.getInvocationContext().getSession();
      if(session.isEmpty())
          return "session"; // session is empty/expired
      return invocation.invoke();
  }
}
