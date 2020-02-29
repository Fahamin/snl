
package saturday.live.snl;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tubeFavModel")

public class FavModel {


    @PrimaryKey
    private int id;

    @ColumnInfo(name = "link")
    String link;

    @ColumnInfo(name = "title")
    String title;

    @ColumnInfo(name = "pdate")
    String date;

    @ColumnInfo(name = "key")
    String key;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
