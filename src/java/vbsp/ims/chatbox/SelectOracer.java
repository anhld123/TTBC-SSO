package vbsp.ims.chatbox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import vbsp.ims.dao.DaoConnect;

public class SelectOracer {

    private static final String ACCENT
            = "áàảãạăắằẳẵặâấầẩẫậđéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵ"
            + "ÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬĐÉÈẺẼẸÊẾỀỂỄỆÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỮỰÝỲỶỸỴ";

    private static final String NO_ACCENT
            = "aaaaaaaaaaaaaaaaadeeeeeeeeeeeiiiiioooooooooooooooooouuuuuuuuuuuyyyyy"
            + "AAAAAAAAAAAAAAAAADEEEEEEEEEEEIIIIIOOOOOOOOOOOOOOOOOUUUUUUUUUUUYYYYY";

    private static String removeAccent(String column) {
        return "UPPER(TRANSLATE(" + column + ",'" + ACCENT + "','" + NO_ACCENT + "'))";
    }

    private static boolean isValidWord(String word) {
        return word != null
                && word.length() >= 2
                && !QuestionCleaner.isIgnoreWord(word);
    }

    private static String getSuggestion(String keyword, String groupId, boolean report) {
        String suggestion = ReportSuggester.getBestMatch(
                keyword,
                report
                        ? ReportSuggester.getReportNames(groupId)
                        : ReportSuggester.getMenuNames(groupId)
        );

        return suggestion != null
                ? "Không tìm thấy "
                + (report ? "báo cáo" : "menu")
                + ". Có phải ý bạn là: <b>"
                + suggestion
                + "</b>?"
                : "Không tìm thấy "
                + (report ? "báo cáo" : "menu")
                + " hoặc tài khoản chưa được phân quyền truy cập.";
    }

    public static String findInMenu(String keyword, String groupId) {

        StringBuilder sql = new StringBuilder(
                "SELECT TRIM(NVL(c.TEXT || ' > ','') "
                + "|| NVL(b.TEXT || ' > ','') "
                + "|| NVL(a.TEXT,'')) MENU_CHA "
                + "FROM MENU a "
                + "LEFT JOIN MENU b ON a.PARENTID = b.MENUID "
                + "LEFT JOIN MENU c ON b.PARENTID = c.MENUID "
                + "WHERE a.PARENTID < 1000 "
                + "AND a.MENUID IN (SELECT MENUID FROM menu_tmp WHERE GROUPID = ? AND PARENTID BETWEEN 0 AND 1000) "
                + "AND a.TEXT IS NOT NULL"
        );

        String[] words = QuestionCleaner.splitWord(keyword);

        for (String word : words) {
            if (isValidWord(word)) {
                sql.append(" AND ")
                        .append(removeAccent("a.TEXT"))
                        .append(" LIKE '%' || ")
                        .append(removeAccent("?"))
                        .append(" || '%'");
            }
        }

        try (Connection conn = new DaoConnect().getConnect();
                PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int index = 1;
            ps.setString(index++, groupId);

            for (String word : words) {
                if (isValidWord(word)) {
                    ps.setString(index++, word);
                }
            }

            try (ResultSet rs = ps.executeQuery()) {

                StringBuilder result = new StringBuilder(
                        "Kết quả tra cứu Menu:<br>"
                );

                int count = 0;

                while (rs.next()) {
                    result.append(++count)
                            .append(". ")
                            .append(rs.getString("MENU_CHA"))
                            .append("<br>");
                }

                return count > 0
                        ? result.toString()
                        : getSuggestion(keyword, groupId, false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Không tìm thấy menu.";
        }
    }

    public static String findInBaoCao(String keyword, String groupId) {

        StringBuilder sql = new StringBuilder(
                "SELECT NVL(B.DM_MABC,'') || ' - ' || NVL(B.DM_MOTA,'') TEN, "
                + "TRIM(NVL(MMM.TEXT || ' > ','') "
                + "|| NVL(MM.TEXT || ' > ','') "
                + "|| NVL(M.TEXT,'')) MENU_CHA "
                + "FROM MENU_NHOMBC A "
                + "JOIN DMBC_CT B ON A.NHOMBC = B.DM_NHOMBC "
                + "LEFT JOIN MENU M ON A.MENUID = M.MENUID "
                + "LEFT JOIN MENU MM ON M.PARENTID = MM.MENUID "
                + "LEFT JOIN MENU MMM ON MM.PARENTID = MMM.MENUID "
                + "WHERE A.MENUID IN (SELECT MENUID FROM menu_tmp WHERE GROUPID = ? AND PARENTID BETWEEN 0 AND 1000) "
                + "AND M.TEXT IS NOT NULL "
                + "AND B.APPLY_FLG = 'Y'"
        );

        String[] words = QuestionCleaner.splitWord(keyword);

        for (String word : words) {

            if (!isValidWord(word)) {
                continue;
            }

            if (word.toUpperCase().matches("BC\\d{8}")) {

                sql.append(" AND ")
                        .append(removeAccent("B.DM_MABC"))
                        .append(" = ")
                        .append(removeAccent("?"));

            } else {

                sql.append(" AND (")
                        .append(removeAccent("B.DM_MOTA"))
                        .append(" LIKE '%' || ")
                        .append(removeAccent("?"))
                        .append(" || '%' OR ")
                        .append(removeAccent("B.DM_MABC"))
                        .append(" LIKE '%' || ")
                        .append(removeAccent("?"))
                        .append(" || '%')");
            }
        }

        try (Connection conn = new DaoConnect().getConnect();
                PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int index = 1;
            ps.setString(index++, groupId);

            for (String word : words) {

                if (!isValidWord(word)) {
                    continue;
                }

                ps.setString(index++, word);

                if (!word.toUpperCase().matches("BC\\d{8}")) {
                    ps.setString(index++, word);
                }
            }

            try (ResultSet rs = ps.executeQuery()) {

                StringBuilder result = new StringBuilder(
                        "Kết quả tra cứu Báo cáo:<br>"
                );

                int count = 0;

                while (rs.next() && count < 50) {

                    result.append(++count)
                            .append(". ")
                            .append(rs.getString("TEN"))
                            .append("<br>Menu: ")
                            .append(rs.getString("MENU_CHA"))
                            .append("<br>");
                }

                if (count > 0) {
                    return result.toString();
                }

                if (keyword.trim().toUpperCase().matches("BC\\d{8}")) {
                    return "Không tìm thấy báo cáo với mã: "
                            + keyword.trim() + ".";
                }

                return getSuggestion(keyword, groupId, true);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Không tìm thấy báo cáo.";
        }
    }
}
