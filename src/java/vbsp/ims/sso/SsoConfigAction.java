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
import java.util.List;
import javax.servlet.http.HttpSession;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.ListOfValue;

public class SsoConfigAction extends ActionSupport {

    DuLieuNTService _serverAPI = new DuLieuNTService();
    private String ssoUrlResponse;
    private List<ListOfValue> lstConfig;

    public DuLieuNTService getServerAPI() {
        return _serverAPI;
    }

    public void setServerAPI(DuLieuNTService _serverAPI) {
        this._serverAPI = _serverAPI;
    }

    public String getSsoUrlResponse() {
        return ssoUrlResponse;
    }

    public void setSsoUrlResponse(String ssoUrlResponse) {
        this.ssoUrlResponse = ssoUrlResponse;
    }

    public List<ListOfValue> getLstConfig() {
        return lstConfig;
    }

    public void setLstConfig(List<ListOfValue> lstConfig) {
        this.lstConfig = lstConfig;
    }

    public String getSsoUrl() {
        try {
            lstConfig = _serverAPI.getListOfValue("306", "TTBC");
            if (lstConfig == null || lstConfig.isEmpty()) {
                addActionError("Cấu hình không tồn tại!");
                return ERROR;
            }
            HttpSession session = ServletActionContext.getRequest().getSession();
            String authUrl = "", responseType = "", clientId = "", redirectUri = "", scope = "", state = "", nonce = "", codeChallenge = "", codeChallengeMethod = "";

            for (ListOfValue item : lstConfig) {
                String paramName = item.getValue();
                String paramValue = item.getDescription();

                if (paramName != null) {
                    switch (paramName) {
                        case "url":
                            authUrl = paramValue;
                            break;
                        case "response_type":
                            responseType = paramValue;
                            break;
                        case "client_id":
                            clientId = paramValue;
                            break;
                        case "redirect_uri":
                            redirectUri = paramValue;
                            break;
                        case "scope":
                            scope = paramValue;
                            break;
                        case "state":
                            state = paramValue;
                            break;
                        case "nonce":
                            nonce = paramValue;
                            break;
                        case "code_challenge":
                            codeChallenge = paramValue;
                            break;
                        case "code_challenge_method":
                            codeChallengeMethod = paramValue;
                            break;
                    }
                }
            }

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
            ssoUrlResponse = fullUrl.toString();

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("application/json;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"url\": \"" + ssoUrlResponse + "\"}");
                out.flush();
            }

            session.setAttribute("redirect_uri", redirectUri);
            session.setAttribute("url", authUrl);

//            System.out.println("redirect_uri: " + redirectUri);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NONE;
    }
}
