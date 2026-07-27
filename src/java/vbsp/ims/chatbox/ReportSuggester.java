package vbsp.ims.chatbox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.text.similarity.JaroWinklerSimilarity;
import vbsp.ims.dao.DaoConnect;

public class ReportSuggester {

    private static final Pattern ACCENT_PATTERN = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    private static final Map<String, List<String>> allReportNames = new HashMap<>();
    private static final Map<String, List<String>> allMenuNames = new HashMap<>();

    public static String removeAccent(String s) {
        if (s == null) {
            return "";
        }
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        return ACCENT_PATTERN.matcher(temp).replaceAll("").replace('đ', 'd').replace('Đ', 'D');
    }

    public static String getBestMatch(String input, List<String> allReports) {
        if (input == null || allReports == null || allReports.isEmpty()) {
            return null;
        }

        JaroWinklerSimilarity jws = new JaroWinklerSimilarity();
        String bestMatch = null;
        double maxSimilarity = 0.0;
        String cleanInput = removeAccent(input).toLowerCase();

        for (String report : allReports) {
            if (report == null) {
                continue;
            }

            String cleanReport = removeAccent(report).toLowerCase();
            double similarity = jws.apply(cleanInput, cleanReport);

            if (similarity > 0.85 && similarity > maxSimilarity) {
                maxSimilarity = similarity;
                bestMatch = report;
            }
        }

        return bestMatch;
    }

    public static List<String> getReportNames(String groupId) {
        if (!allReportNames.containsKey(groupId)) {
            List<String> reportNames = new ArrayList<>();

            String sql = "SELECT A.DM_MOTA "
                    + "FROM DMBC_CT A "
                    + "LEFT JOIN MENU_NHOMBC B ON A.DM_NHOMBC = B.NHOMBC "
                    + "LEFT JOIN MENU_TMP C ON B.MENUID = C.MENUID "
                    + "WHERE C.GROUPID = ? "
                    + "AND C.PARENTID BETWEEN 0 AND 1000 "
                    + "AND A.APPLY_FLG = 'Y'";

            loadFromDB(sql, groupId, reportNames);
            allReportNames.put(groupId, reportNames);
        }

        return allReportNames.get(groupId);
    }

    public static List<String> getMenuNames(String groupId) {
        if (!allMenuNames.containsKey(groupId)) {
            List<String> menuNames = new ArrayList<>();

            String sql = "SELECT TEXT FROM MENU "
                    + "WHERE TEXT IS NOT NULL "
                    + "AND MENUID IN ("
                    + "SELECT MENUID FROM MENU_TMP "
                    + "WHERE GROUPID = ? "
                    + "AND PARENTID BETWEEN 0 AND 1000)";

            loadFromDB(sql, groupId, menuNames);
            allMenuNames.put(groupId, menuNames);
        }

        return allMenuNames.get(groupId);
    }

    private static void loadFromDB(String sql, String groupId, List<String> list) {
        try (Connection conn = new DaoConnect().getConnect();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, groupId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getString(1));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
