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

public class SsoMenuAction {

    private DuLieuNTService _serverAPI = new DuLieuNTService();

    public String getUserInfo(String accessToken) {
        try {
            // 1. Lấy cấu hình từ Database (Mã "306", nhóm "MenuSso")
            List<ListOfValue> lstConfig = _serverAPI.getListOfValue("306", "MenuSso");
            if (lstConfig == null || lstConfig.isEmpty()) {
                System.err.println("Không tìm thấy cấu hình MenuSso!");
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
            String clientId = configMap.getOrDefault("client-id", "");
            String authPrefix = configMap.getOrDefault("Authorization", "Bearer");
            String cookieSession = configMap.getOrDefault("Cookie", "");

            // 3. Xây dựng URL hoàn chỉnh trước khi khởi tạo đối tượng URL
            StringBuilder fullUrl = new StringBuilder(userInfoUrl);
            if (!clientId.isEmpty()) {
                // Kiểm tra xem URL đã có dấu '?' hay chưa
                if (!userInfoUrl.contains("?")) {
                    fullUrl.append("?");
                } else {
                    // Nếu URL đã có sẵn tham số trước đó thì nối bằng dấu '&'
                    if (!userInfoUrl.endsWith("&") && !userInfoUrl.endsWith("?")) {
                        fullUrl.append("&");
                    }
                }
                fullUrl.append("client-id=").append(clientId);
            }

            URL url = new URL(fullUrl.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 4. Đính kèm các Header
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
