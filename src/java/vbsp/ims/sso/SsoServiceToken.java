package vbsp.ims.sso;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.ListOfValue;

public class SsoServiceToken {

    private DuLieuNTService _serverAPI = new DuLieuNTService();

    public String exchangeCodeForToken(String code) {
        try {
            // 1. Gọi API lấy cấu hình từ Database
            List<ListOfValue> lstConfig = _serverAPI.getListOfValue("306", "TokenSSo");
            if (lstConfig == null || lstConfig.isEmpty()) {
                System.err.println("[SsoServiceToken] Không tìm thấy cấu hình TokenSSo trong Database!");
                return null;
            }

            // 2. Map trường "value" làm Key và "description" làm Value
            Map<String, String> configMap = new HashMap<>();
            for (ListOfValue item : lstConfig) {
                if (item.getValue() != null) {
                    configMap.put(item.getValue(), item.getDescription());
                }
            }

            // 3. Lấy giá trị cấu hình
            String tokenUrl = configMap.getOrDefault("token_url", "").trim();
            String grantType = configMap.getOrDefault("grant_type", "").trim();
            String clientId = configMap.getOrDefault("client_id", "").trim();
            String clientSecret = configMap.getOrDefault("client_secret", "").trim();
            String redirectUri = configMap.getOrDefault("redirect_uri", "").trim();
            String codeVerifier = configMap.getOrDefault("code_verifier", "").trim();
            String cookieSession = configMap.getOrDefault("cookie_session", "").trim();

            // Log kiểm tra các tham số lấy từ DB
//            System.out.println("=== SSO CONFIG DEBUG ===");
//            System.out.println("tokenUrl: " + tokenUrl);
//            System.out.println("grantType: " + grantType);
//            System.out.println("clientId: " + clientId);
//            System.out.println("redirectUri: " + redirectUri);
//            System.out.println("Received Code: " + code);
//            System.out.println("========================");

            if (tokenUrl.isEmpty() || code == null || code.isEmpty()) {
                System.err.println("[SsoServiceToken] Thiếu tokenUrl hoặc authorization code!");
                return null;
            }

            // 4. Xây dựng các tham số Body chuẩn x-www-form-urlencoded
            StringBuilder params = new StringBuilder();
            params.append("grant_type=").append(URLEncoder.encode(grantType, "UTF-8"));
            params.append("&code=").append(URLEncoder.encode(code, "UTF-8"));
            params.append("&redirect_uri=").append(URLEncoder.encode(redirectUri, "UTF-8"));
            params.append("&client_id=").append(URLEncoder.encode(clientId, "UTF-8"));
            params.append("&client_secret=").append(URLEncoder.encode(clientSecret, "UTF-8"));
            params.append("&code_verifier=").append(URLEncoder.encode(codeVerifier, "UTF-8"));

//            System.out.println("[SsoServiceToken] Request Body gửi đi: " + params.toString());

            // 5. Thiết lập kết nối HTTP POST tới SSO Server
            URL url = new URL(tokenUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setConnectTimeout(10000); // 10 giây timeout
            conn.setReadTimeout(10000);

            // Đặt Header
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            if (cookieSession != null && !cookieSession.isEmpty()) {
                conn.setRequestProperty("Cookie", cookieSession);
            }

            // Gửi request body đi
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = params.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // 6. Nhận phản hồi từ SSO Server
            int responseCode = conn.getResponseCode();
//            System.out.println("[SsoServiceToken] HTTP Response Code từ SSO: " + responseCode);

            StringBuilder response = new StringBuilder();
            if (responseCode >= 200 && responseCode < 300) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }
            } else {
                try (BufferedReader errorIn = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = errorIn.readLine()) != null) {
                        response.append(line);
                    }
                }
                System.err.println("[SsoServiceToken] Lỗi từ SSO Server: " + response.toString());
            }

            String finalResponse = response.toString();
//            System.out.println("[SsoServiceToken] Raw Response từ SSO: " + finalResponse);
            return finalResponse;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[SsoServiceToken] Exception xảy ra: " + e.getMessage());
            return null;
        }
    }
}
