package vbsp.ims.chatbox;

import java.net.HttpURLConnection;
import java.net.URL;

import static vbsp.ims.chatbox.UserService.getUserGroupId;

public class AIService {

    public static String ask(String userQuestion, String type, String user, String code, String value, String description, String url, String apiKey) {

        String groupId = getUserGroupId(user);

        if ("KHAC".equals(type)) {
            return AIrep(userQuestion, code, value, description, url, apiKey);
        }

        String directResult;

        if ("MENU".equals(type)) {
            directResult = SelectOracer.findInMenu(userQuestion, groupId);
        } else if ("BAOCAO".equals(type)) {
            directResult = SelectOracer.findInBaoCao(userQuestion, groupId);
        } else {
            return "Chưa chọn loại tìm kiếm.";
        }

        if (isFound(directResult)) {
            return directResult;
        }

        if (!isInternetAvailable()) {
            return directResult;
        }

        String aiKeyword = PromptAI.getKeywordFromAI(userQuestion, code, value, description, url, apiKey);

//        System.out.println("AI Keyword: [" + aiKeyword + "]");
        if (aiKeyword != null && !aiKeyword.trim().isEmpty()) {

            String aiResult;

            if ("MENU".equals(type)) {
                aiResult = SelectOracer.findInMenu(aiKeyword, groupId);
            } else {
                aiResult = SelectOracer.findInBaoCao(aiKeyword, groupId);
            }

            if (isFound(aiResult)) {
                return aiResult;
            }
        }

        return directResult;
    }

    private static boolean isFound(String result) {

        return result != null
                && !result.trim().isEmpty()
                && !result.toLowerCase().contains("không tìm thấy");
    }

    private static String AIrep(String userQuestion, String code, String value,
            String description, String url, String apiKey) {

        if (!isInternetAvailable()) {
            return "Trạng thái: Offline | Không có kết nối mạng Internet.";
        }

        String result = PromptAI.callAI(userQuestion, code, value, description, url, apiKey);

//        System.out.println("Response AI [" + code + "]: " + result);
        if (result != null && !result.trim().isEmpty()) {
            System.out.println("Kết nối thành công AI: " + code);
            return result.trim();
        }

        return null;
    }

    private static boolean isInternetAvailable() {

        try {
            URL url = new URL("https://www.google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);

            int responseCode = connection.getResponseCode();

            connection.disconnect();

            return responseCode >= 200 && responseCode < 500;

        } catch (Exception e) {
            return false;
        }
    }
}
