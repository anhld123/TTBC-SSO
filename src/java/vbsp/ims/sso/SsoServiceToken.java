package vbsp.ims.sso;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SsoServiceToken {

    public String exchangeCodeForToken(String code) {
        try {
            // 1. Lấy giá trị cấu hình trực tiếp từ SsoConfig
            String tokenUrl = SsoConfig.TOKEN_ENDPOINT;
            String grantType = SsoConfig.GRANT_TYPE;
            String clientId = SsoConfig.CLIENT_ID;
            String clientSecret = SsoConfig.CLIENT_SECRET;
            String redirectUri = SsoConfig.REDIRECT_URI;
            String codeVerifier = SsoConfig.CODE_VERIFIER;
            String cookieSession = SsoConfig.COOKIE_SESSION;

            if (tokenUrl == null || tokenUrl.isEmpty() || code == null || code.isEmpty()) {
                System.err.println("[SsoServiceToken] Thiếu tokenUrl hoặc authorization code!");
                return null;
            }

            // 2. Xây dựng các tham số Body chuẩn x-www-form-urlencoded
            StringBuilder params = new StringBuilder();
            params.append("grant_type=").append(URLEncoder.encode(grantType, "UTF-8"));
            params.append("&code=").append(URLEncoder.encode(code, "UTF-8"));
            params.append("&redirect_uri=").append(URLEncoder.encode(redirectUri, "UTF-8"));
            params.append("&client_id=").append(URLEncoder.encode(clientId, "UTF-8"));
            params.append("&client_secret=").append(URLEncoder.encode(clientSecret, "UTF-8"));
            params.append("&code_verifier=").append(URLEncoder.encode(codeVerifier, "UTF-8"));

            // 3. Thiết lập kết nối HTTP POST tới SSO Server
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

            // 4. Nhận phản hồi từ SSO Server
            int responseCode = conn.getResponseCode();

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

            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[SsoServiceToken] Exception xảy ra: " + e.getMessage());
            return null;
        }
    }
}