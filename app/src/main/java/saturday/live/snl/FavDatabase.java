package saturday.live.snl;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities={FavModel.class},version = 1)
public abstract class FavDatabase extends RoomDatabase {

    public abstract FavoriteDao favoriteDao();
}
