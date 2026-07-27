package vbsp.ims.chatbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchEngine {

    public static List<SearchResult> search(String question, List<SearchItem> list) {

        List<SearchResult> results = new ArrayList<SearchResult>();

        String q = QuestionCleaner.normalize(question);
        String[] words = QuestionCleaner.splitWord(question);

        for (SearchItem item : list) {

            int score = calculateScore(q, words, item.getName());

            System.out.println(item.getId() + " | " + item.getName() + " => " + score);

            if (score > 0) {
                results.add(new SearchResult(item, score));
            }
        }

        Collections.sort(results, new Comparator<SearchResult>() {
            @Override
            public int compare(SearchResult a, SearchResult b) {
                return Integer.compare(b.getScore(), a.getScore());
            }
        });

        return results;
    }

    private static int calculateScore(String question, String[] words, String target) {

        String text = QuestionCleaner.normalize(target);

        int score = 0;

        // Khớp tuyệt đối
        if (question.equals(text)) {
            return 10000;
        }

        // Khớp gần nguyên câu
        if (question.contains(text)) {
            score += 5000;
        } else if (text.contains(question)) {
            score += 3000;
        }

        // Ghép 4 từ liên tiếp
        score += matchGram(text, words, 4, 120);

        // Ghép 3 từ liên tiếp
        score += matchGram(text, words, 3, 60);

        return score;
    }

    private static int matchGram(String text, String[] words, int size, int point) {

        int score = 0;

        if (words.length < size) {
            return 0;
        }

        for (int i = 0; i <= words.length - size; i++) {

            StringBuilder gram = new StringBuilder();

            boolean ok = true;

            for (int j = 0; j < size; j++) {

                String w = words[i + j];

                if (w.length() < 3 || QuestionCleaner.isIgnoreWord(w)) {
                    ok = false;
                    break;
                }

                if (j > 0) {
                    gram.append(" ");
                }

                gram.append(w);
            }

            if (!ok) {
                continue;
            }

            if (text.contains(gram.toString())) {
                score += point;
            }
        }

        return score;
    }

}
