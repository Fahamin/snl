package saturday.live.snl;

public class TubeDataModel {
    int id;
    String title, link, pdate, key;

    public TubeDataModel() {
    }

    public TubeDataModel(int id, String title, String link, String date, String key) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.pdate = date;
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

