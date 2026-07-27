package vbsp.ims.chatbox;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionCleaner {

    public static String normalize(String text) {

        if (text == null) {
            return "";
        }

        text = text.toLowerCase();

        text = Normalizer.normalize(text, Normalizer.Form.NFD);

        text = text.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        text = text.replace("đ", "d");

        text = text.replaceAll("[^a-z0-9 ]", " ");

        text = text.replaceAll("\\s+", " ").trim();

        return text;
    }

    public static String[] splitWord(String text) {

        text = normalize(text);

        if (text.isEmpty()) {
            return new String[0];
        }

        return text.split(" ");

    }

    public static List<String> buildGram(String text, int size) {

        String[] words = splitWord(text);

        List<String> list = new ArrayList<>();

        if (words.length < size) {
            return list;
        }

        for (int i = 0; i <= words.length - size; i++) {

            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < size; j++) {

                if (j > 0) {
                    sb.append(" ");
                }

                sb.append(words[i + j]);
            }

            list.add(sb.toString());
        }

        return list;
    }
    private static final Set<String> IGNORE_WORDS = new HashSet<>(Arrays.asList(
            "bao", "cao", "bc",
            "cho", "toi", "xin", "vui", "long", "giup",
            "tim", "kiem", "tra", "cuu",
            "xem", "mo", "vao",
            "menu", "man", "hinh", "chuc", "nang",
            "la", "co", "o", "dau", "theo", "thuoc"
    ));

    public static boolean isIgnoreWord(String word) {
        return IGNORE_WORDS.contains(word);
    }
}
