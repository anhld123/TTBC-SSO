package vbsp.ims.sso;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SsoServiceUserInfo {

    public String getUserInfo(String accessToken) {
        try {
            // 1. Lấy cấu hình trực tiếp từ SsoConfig
            String userInfoUrl = SsoConfig.USERINFO_ENDPOINT;
            String authPrefix = "Bearer"; // Hoặc có thể định nghĩa thêm trong SsoConfig nếu cần
            String cookieSession = SsoConfig.COOKIE_SESSION;

            if (userInfoUrl == null || userInfoUrl.isEmpty()) {
                System.err.println("URL cấu hình UserInfo đang bị rỗng!");
                return null;
            }

            // 2. Thiết lập kết nối HTTP GET
            URL url = new URL(userInfoUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000); // Timeout kết nối 10 giây
            conn.setReadTimeout(10000);    // Timeout đọc 10 giây

            // 3. Đính kèm các Header giống hệt trên Postman
            conn.setRequestProperty("Authorization", authPrefix + " " + accessToken);
            if (cookieSession != null && !cookieSession.isEmpty()) {
                conn.setRequestProperty("Cookie", cookieSession);
            }

            // 4. Đọc kết quả trả về từ SSO Server
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
                // Xử lý an toàn khi đọc luồng lỗi để tránh NullPointerException
                InputStream errorStream = conn.getErrorStream();
                String errorDetails = "Không có thông tin chi tiết (Error Stream trống)";
                
                if (errorStream != null) {
                    try (BufferedReader errorIn = new BufferedReader(new InputStreamReader(errorStream, StandardCharsets.UTF_8))) {
                        StringBuilder errorResponse = new StringBuilder();
                        String line;
                        while ((line = errorIn.readLine()) != null) {
                            errorResponse.append(line);
                        }
                        errorDetails = errorResponse.toString();
                    }
                }
                
                System.err.println("Lỗi lấy userinfo. HTTP Code: " + responseCode + " - Chi tiết: " + errorDetails);
                return null;
            }

        } catch (Exception e) {
            System.err.println("Ngoại lệ khi gọi API UserInfo: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}