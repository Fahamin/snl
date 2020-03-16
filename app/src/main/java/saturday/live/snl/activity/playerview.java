package saturday.live.snl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import saturday.live.snl.R;
import saturday.live.snl.api.YouTubApi;

public class playerview extends YouTubeBaseActivity {

    YouTubePlayerView playerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerview);

        playerView = findViewById(R.id.playerID);

        final String videoLink = getIntent().getStringExtra("video_id");
        final boolean pl = getIntent().getBooleanExtra("key", false);

        //   final String   videoLink = "yBMEoUNbT6w";
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                if(pl)
                {

                    youTubePlayer.loadPlaylist(videoLink);

                }
                else
                {
                    youTubePlayer.loadVideo(videoLink);

                }

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        playerView.initialize(YouTubApi.getApi_kEY(), onInitializedListener);

    }
}

