/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.sso;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;

public class SsoConfigAction extends ActionSupport {

    private String ssoUrlResponse;

    public String getSsoUrlResponse() {
        return ssoUrlResponse;
    }

    public void setSsoUrlResponse(String ssoUrlResponse) {
        this.ssoUrlResponse = ssoUrlResponse;
    }

    public String getSsoUrl() {
        try {
            HttpSession session = ServletActionContext.getRequest().getSession();
            
            // Lấy thông tin cấu hình trực tiếp từ SsoConfig
            String authUrl = SsoConfig.AUTH_ENDPOINT;
            String responseType = SsoConfig.RESPONSE_TYPE;
            String clientId = SsoConfig.CLIENT_ID;
            String redirectUri = SsoConfig.REDIRECT_URI;
            String scope = SsoConfig.SCOPE;
            String state = SsoConfig.STATE;
            String nonce = SsoConfig.NONCE;
            String codeChallenge = SsoConfig.CODE_CHALLENGE;
            String codeChallengeMethod = SsoConfig.CODE_CHALLENGE_METHOD;

            String dynamicState = state + "_" + System.currentTimeMillis();

            StringBuilder fullUrl = new StringBuilder();
            fullUrl.append(authUrl);
            fullUrl.append("?response_type=").append(responseType);
            fullUrl.append("&client_id=").append(clientId);
            fullUrl.append("&redirect_uri=").append(redirectUri);
            fullUrl.append("&scope=").append(scope.replace(" ", "%20"));
            fullUrl.append("&state=").append(dynamicState); // Dùng state động
            fullUrl.append("&nonce=").append(nonce);
            fullUrl.append("&code_challenge=").append(codeChallenge);
            fullUrl.append("&code_challenge_method=").append(codeChallengeMethod);

            // Ép buộc bắt buộc đăng nhập lại và cấm dùng dữ liệu cũ
//            fullUrl.append("&prompt=login");
//            fullUrl.append("&max_age=0"); 

            ssoUrlResponse = fullUrl.toString();

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("application/json;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"url\": \"" + ssoUrlResponse + "\"}");
                out.flush();
            }

            session.setAttribute("redirect_uri", redirectUri);
            session.setAttribute("url", authUrl);
            session.setAttribute("code_verifier", SsoConfig.CODE_VERIFIER); // Lưu thêm code_verifier phục vụ bước đổi Token sau này

        } catch (Exception e) {
            e.printStackTrace();
        }
        return NONE;
    }
}