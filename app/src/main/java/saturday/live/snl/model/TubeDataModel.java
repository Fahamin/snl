package saturday.live.snl.model;

public class TubeDataModel {
    int id;
    String title, link, pdate;

    public TubeDataModel() {
    }

    public TubeDataModel(int id, String title, String link, String date) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.pdate = date;

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


}

