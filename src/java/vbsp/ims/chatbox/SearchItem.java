package vbsp.ims.chatbox;

public class SearchItem {

    private String id;

    private String name;

    private String parent;

    public SearchItem(String id, String name, String parent) {

        this.id = id;
        this.name = name;
        this.parent = parent;

    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParent() {
        return parent;
    }

}
