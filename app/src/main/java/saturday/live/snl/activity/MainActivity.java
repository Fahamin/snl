package saturday.live.snl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.facebook.ads.AudienceNetworkAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import saturday.live.snl.R;
import saturday.live.snl.database.FavDatabase;
import saturday.live.snl.fragment.FavFrag;
import saturday.live.snl.fragment.HomeFrag;
import saturday.live.snl.fragment.player_playlist;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    public static FavDatabase favDatabase;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentManager.beginTransaction().replace(R.id.container,new HomeFrag()).commit();
                    setTitle("Video List");

                    return true;
                case R.id.navigation_dashboard:
                    fragmentManager.beginTransaction().replace(R.id.container,new FavFrag()).commit();
                    setTitle("Favorite List");

                    return true;

                case R.id.navigation_notifications:
                    fragmentManager.beginTransaction().replace(R.id.container,new player_playlist()).commit();
                    setTitle("Play List");
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        favDatabase = Room.databaseBuilder(getApplicationContext(), FavDatabase.class, "myfavdb").allowMainThreadQueries().build();

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.container,new HomeFrag()).commit();

        AudienceNetworkAds.initialize(this);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

}
