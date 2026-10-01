package vbsp.ims.sso;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.ListOfValue;

public class SsoServiceUserInfo {

    private DuLieuNTService _serverAPI = new DuLieuNTService();

    public String getUserInfo(String accessToken) {
        try {
            // 1. Lấy cấu hình từ Database (Mã "306", nhóm "UserSso")
            List<ListOfValue> lstConfig = _serverAPI.getListOfValue("306", "UserSso");
            if (lstConfig == null || lstConfig.isEmpty()) {
                System.err.println("Không tìm thấy cấu hình UserSso!");
                return null;
            }

            // 2. Đưa vào Map để dễ quản lý
            Map<String, String> configMap = new HashMap<>();
            for (ListOfValue item : lstConfig) {
                if (item.getValue() != null) {
                    configMap.put(item.getValue().trim(), item.getDescription() != null ? item.getDescription().trim() : "");
                }
            }

            String userInfoUrl = configMap.getOrDefault("url", "");
            String authPrefix = configMap.getOrDefault("Authorization", "Bearer");
            String cookieSession = configMap.getOrDefault("Cookie", "");

            if (userInfoUrl.isEmpty()) {
                System.err.println("URL cấu hình UserSso đang bị rỗng!");
                return null;
            }

            // 3. Thiết lập kết nối HTTP GET
            URL url = new URL(userInfoUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000); // Timeout kết nối 10 giây
            conn.setReadTimeout(10000);    // Timeout đọc 10 giây

            // 4. Đính kèm các Header giống hệt trên Postman
            conn.setRequestProperty("Authorization", authPrefix + " " + accessToken);
            if (!cookieSession.isEmpty()) {
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