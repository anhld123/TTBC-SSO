package vbsp.ims.sso;

public class SsoConfig {
    public static final String SERVER_BASE_URL = "http://10.63.48.185";
    
    // Endpoints
    public static final String AUTH_ENDPOINT = SERVER_BASE_URL + "/api/v1/oauth2/authorize";
    public static final String TOKEN_ENDPOINT = SERVER_BASE_URL + "/api/v1/oauth2/token";
    public static final String USERINFO_ENDPOINT = SERVER_BASE_URL + "/api/v1/oauth2/userinfo";
    public static final String PERMISSIONS_ENDPOINT = SERVER_BASE_URL + "/api/v1/admin/user/permissions";
    
    // Client Credentials & Parameters
    public static final String CLIENT_ID = "TTBC";
    public static final String CLIENT_SECRET = "Kpejw6ueGE4ppujuDsoZzC1kUkTcyrOb";
    public static final String REDIRECT_URI = "http://localhost:8080/IMS_REPORTS/beforeLogin_Proccess.action";
    public static final String SCOPE = "openid profile email";
    public static final String CODE_CHALLENGE_METHOD = "S256";
    public static final String GRANT_TYPE = "authorization_code";
    public static final String RESPONSE_TYPE = "code";
    
    // Security & Session Constants
    public static final String STATE = "6e4cfa71ee572b1bec4f54aac45efbc7";
    public static final String NONCE = "acb91979d5a1af7c1a99adce29669c4e";
    public static final String CODE_CHALLENGE = "W3_uiYNmMOrdjVHh0nGtSyJcSwv-vnJPKmDaxtqGUC0";
    public static final String CODE_VERIFIER = "pw19dsP6jNTwRet5YMPolX_suWgZzyXNZY4wBpHIEWdHWCR4k7Jn4gEpkcLcjuKO";
    public static final String COOKIE_SESSION = "SESSION=ZmFjY2I1YjAtNDllNy00ZDU0LWE5NDctZDIwMTYyYWE0NTM1";
}