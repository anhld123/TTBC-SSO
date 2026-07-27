package vbsp.ims.chatbox;

public class SearchResult {

    private SearchItem item;
    private int score;

    public SearchResult(
            SearchItem item,
            int score
    ) {
        this.item = item;
        this.score = score;
    }

    public SearchItem getItem() {
        return item;
    }

    public int getScore() {
        return score;
    }
}
