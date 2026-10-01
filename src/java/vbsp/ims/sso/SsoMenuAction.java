package vbsp.ims.sso;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SsoMenuAction {

    public String getUserInfo(String accessToken) {
        try {
            // 1. Lấy cấu hình trực tiếp từ SsoConfig
            String permissionsUrl = SsoConfig.PERMISSIONS_ENDPOINT;
            String clientId = SsoConfig.CLIENT_ID;
            String authPrefix = "Bearer";
            String cookieSession = SsoConfig.COOKIE_SESSION;

            if (permissionsUrl == null || permissionsUrl.isEmpty()) {
                System.err.println("URL cấu hình Permissions/Menu đang bị rỗng!");
                return null;
            }

            // 2. Xây dựng URL hoàn chỉnh kèm theo query parameter client-id
            StringBuilder fullUrl = new StringBuilder(permissionsUrl);
            if (clientId != null && !clientId.isEmpty()) {
                if (!permissionsUrl.contains("?")) {
                    fullUrl.append("?");
                } else {
                    if (!permissionsUrl.endsWith("&") && !permissionsUrl.endsWith("?")) {
                        fullUrl.append("&");
                    }
                }
                fullUrl.append("client-id=").append(clientId);
            }

            // 3. Thiết lập kết nối HTTP GET
            URL url = new URL(fullUrl.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);

            // 4. Đính kèm các Header
            conn.setRequestProperty("Authorization", authPrefix + " " + accessToken);
            if (cookieSession != null && !cookieSession.isEmpty()) {
                conn.setRequestProperty("Cookie", cookieSession);
            }

            // 5. Đọc kết quả trả về từ SSO Server
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    return response.toString();
                }
            } else {
                // Đọc lỗi an toàn tránh NullPointerException nếu getErrorStream() bị null
                InputStream errorStream = conn.getErrorStream();
                if (errorStream != null) {
                    try (BufferedReader errorIn = new BufferedReader(new InputStreamReader(errorStream, StandardCharsets.UTF_8))) {
                        StringBuilder errorResponse = new StringBuilder();
                        String line;
                        while ((line = errorIn.readLine()) != null) {
                            errorResponse.append(line);
                        }
                        System.err.println("Lỗi lấy menu. HTTP Code: " + responseCode + " - Chi tiết: " + errorResponse.toString());
                    }
                } else {
                    System.err.println("Lỗi lấy menu. HTTP Code: " + responseCode);
                }
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}