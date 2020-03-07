package saturday.live.snl;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import saturday.live.snl.database.FavDatabase;

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

                    return true;
                case R.id.navigation_dashboard:
                    fragmentManager.beginTransaction().replace(R.id.container,new FavFrag()).commit();
                    return true;

               /* case R.id.main_moreID:
                    startActivity(new Intent(MainActivity.this, More_Activity.class));
                    return true;*/
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


        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
