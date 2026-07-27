package vbsp.ims.chatbox;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PromptAI {

    public static String callAI(String userQuestion, String code, String value, String description, String url, String apiKey) {
        try {
            String decryptedApiKey = ApiKeyCrypto.decrypt(apiKey);

//            System.out.println("Đang gọi AI chat: " + code);
            if ("GEMINI".equalsIgnoreCase(code)) {
                return callGemini(url + "?key=" + decryptedApiKey, getAIChatPrompt(userQuestion), code);
            }

            if ("GROQ".equalsIgnoreCase(code) || "OPENAI".equalsIgnoreCase(code)) {
                return callOpenAIStyleAI(url, decryptedApiKey, description, getAIChatPrompt(userQuestion), code);
            }

//            System.out.println("Không hỗ trợ AI: " + code);
        } catch (Exception e) {
            System.out.println(code + " bị lỗi: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public static String getKeywordFromAI(String userQuestion, String code, String value, String description, String url, String apiKey) {
        try {
            String decryptedApiKey = ApiKeyCrypto.decrypt(apiKey);

//            System.out.println("Đang gọi AI lấy keyword: " + code);
            String result;

            if ("GEMINI".equalsIgnoreCase(code)) {
                result = callGemini(url + "?key=" + decryptedApiKey, getKeywordPrompt(userQuestion), code);
            } else if ("GROQ".equalsIgnoreCase(code) || "OPENAI".equalsIgnoreCase(code)) {
                result = callOpenAIStyleAI(url, decryptedApiKey, description, getKeywordPrompt(userQuestion), code);
            } else {
                System.out.println("Không hỗ trợ AI: " + code);
                return null;
            }

//            System.out.println("Keyword Result [" + code + "]: [" + result + "]");
            return result != null && !result.trim().isEmpty()
                    ? result.trim()
                    : null;

        } catch (Exception e) {
            System.out.println(code + " lỗi: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    private static String callGemini(String apiUrl, String prompt, String aiName) {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(apiUrl);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(30000);
            connection.setDoOutput(true);

            String requestBody = "{"
                    + "\"contents\":[{"
                    + "\"parts\":[{"
                    + "\"text\":\"" + escapeJson(prompt) + "\""
                    + "}]"
                    + "}],"
                    + "\"tools\":[{"
                    + "\"google_search\":{}"
                    + "}]"
                    + "}";

            try (OutputStream os = connection.getOutputStream()) {
                os.write(requestBody.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = connection.getResponseCode();
            InputStream inputStream = responseCode >= 200 && responseCode < 300
                    ? connection.getInputStream()
                    : connection.getErrorStream();

            String response = inputStream != null ? readResponse(inputStream) : "";

            if (responseCode == 429) {
//                System.out.println(aiName + " hết quota hoặc rate limit.");
                return null;
            }

            if (responseCode == 503) {
//                System.out.println(aiName + " server đang quá tải.");
                return null;
            }

            if (responseCode < 200 || responseCode >= 300) {
//                System.out.println(aiName + " HTTP Error: " + responseCode);
                return null;
            }

            return extractJsonValue(response, "\"text\"");

        } catch (Exception e) {
            System.out.println("Lỗi gọi " + aiName + ": " + e.getMessage());
            return null;

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private static String callOpenAIStyleAI(String apiUrl, String apiKey, String model, String prompt, String aiName) {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(apiUrl.trim());
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey.trim());
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(30000);
            connection.setDoOutput(true);

            String requestBody = "{\"model\":\"" + escapeJson(model) + "\",\"messages\":[{\"role\":\"user\",\"content\":\"" + escapeJson(prompt) + "\"}],\"temperature\":0.3}";

            try (OutputStream os = connection.getOutputStream()) {
                os.write(requestBody.getBytes(StandardCharsets.UTF_8));
                os.flush();
            }

            int responseCode = connection.getResponseCode();

            InputStream inputStream = responseCode >= 200 && responseCode < 300
                    ? connection.getInputStream()
                    : connection.getErrorStream();

            String response = inputStream != null ? readResponse(inputStream) : "";

//            System.out.println("HTTP Code " + aiName + ": " + responseCode);
//            System.out.println("Response " + aiName + ": " + response);
            if (responseCode == 401) {
//                System.out.println(aiName + " API Key không hợp lệ.");
                return null;
            }

            if (responseCode == 403) {
//                System.out.println(aiName + " bị từ chối HTTP 403.");
                return null;
            }

            if (responseCode == 429) {
//                System.out.println(aiName + " hết quota hoặc rate limit.");
                return null;
            }

            if (responseCode >= 500 && responseCode <= 599) {
//                System.out.println(aiName + " lỗi server.");
                return null;
            }

            if (responseCode < 200 || responseCode >= 300) {
//                System.out.println(aiName + " HTTP Error: " + responseCode);
                return null;
            }

            return extractJsonValue(response, "\"content\"");

        } catch (Exception e) {
//            System.out.println("Lỗi gọi " + aiName + ": " + e.getMessage());
            e.printStackTrace();
            return null;

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private static String getAIChatPrompt(String userQuestion) {
        return "Bạn là bộ lọc tìm kiếm của Ngân hàng Chính sách xã hội Việt Nam (VBSP)"
                + "Trả lời bằng tiếng Việt, ngắn gọn, rõ ràng và đúng trọng tâm. "
                + "Luôn ưu tiên dữ liệu mới nhất. Nếu câu hỏi có năm hoặc thời điểm cụ thể, "
                + "Không bịa thông tin; nếu không đủ dữ liệu thì nói rõ.\n\n"
                + "Ngắn gọn trong 200 từ."
                + "Câu hỏi: " + userQuestion;
    }

    private static String getKeywordPrompt(String userQuestion) {
        return "Bạn là bộ lọc tìm kiếm cho hệ thống Menu và Báo cáo của Ngân hàng Chính sách xã hội Việt Nam (VBSP).\n\n"
                + "Hãy rút gọn câu hỏi thành các từ khóa quan trọng nhất để tìm kiếm trong cơ sở dữ liệu.\n"
                + "Bỏ qua các từ như: cho tôi, hãy tìm, tìm giúp, xem, giúp tôi, muốn biết, là gì.\n"
                + "TW nghĩa là trung ương, DP nghĩa là địa phương.\n"
                + "Chỉ trả về từ khóa ngắn gọn, không giải thích, không viết câu đầy đủ.\n\n"
                + "Câu hỏi: "
                + userQuestion;
    }

    private static String readResponse(InputStream inputStream) throws Exception {
        StringBuilder response = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }

        return response.toString();
    }

    private static String extractJsonValue(String response, String key) {
        if (response == null || response.trim().isEmpty()) {
            return null;
        }

        int keyIndex = response.indexOf(key);

        if (keyIndex == -1) {
            return null;
        }

        int colonIndex = response.indexOf(":", keyIndex);
        int firstQuote = response.indexOf("\"", colonIndex + 1);

        if (firstQuote == -1) {
            return null;
        }

        return extractJsonString(response, firstQuote);
    }

    private static String extractJsonString(String response, int firstQuote) {
        StringBuilder result = new StringBuilder();
        boolean escaped = false;

        for (int i = firstQuote + 1; i < response.length(); i++) {
            char c = response.charAt(i);

            if (escaped) {
                switch (c) {
                    case 'n':
                    case 'r':
                    case 't':
                        result.append(" ");
                        break;
                    case '"':
                    case '\\':
                    case '/':
                        result.append(c);
                        break;
                    default:
                        result.append(c);
                        break;
                }

                escaped = false;
                continue;
            }

            if (c == '\\') {
                escaped = true;
                continue;
            }

            if (c == '"') {
                break;
            }

            result.append(c);
        }

        return result.toString().trim();
    }

    private static String escapeJson(String text) {
        return text.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\r", "\\r")
                .replace("\n", "\\n")
                .replace("\t", "\\t");
    }

}
