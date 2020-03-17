package saturday.live.snl.adapterr;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;

import saturday.live.snl.activity.MainActivity;
import saturday.live.snl.R;
import saturday.live.snl.api.YouTubApi;
import saturday.live.snl.database.FavModel;
import saturday.live.snl.activity.playerview;

import static saturday.live.snl.Fun.addShow;


public class FavAdapter extends RecyclerView.Adapter<FavAdapter.MainHolder> implements Filterable {

    Context context;
    List<FavModel> list;

    public FavAdapter(Context context, List<FavModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public FavAdapter.MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tube_view, null, false);
        return new MainHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavAdapter.MainHolder holder, int position) {

        final FavModel favModel = list.get(position);


        holder.title.setText(favModel.getTitle());

        holder.thumbnailView.initialize(YouTubApi.getApi_kEY(), new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(favModel.getLink());
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
        holder.fav_btn.setImageResource(R.drawable.ic_favfull);

        holder.fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.favDatabase.favoriteDao().delete(favModel);
                Toast.makeText(context, "Remove from Favorite", Toast.LENGTH_SHORT).show();
                holder.fav_btn.setImageResource(R.drawable.ic_fav);

            }
        });

        holder.fullLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShow();
                context.startActivity(new Intent(context, playerview.class).putExtra("video_id", favModel.getLink()));

            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    class MainHolder extends RecyclerView.ViewHolder {
        TextView title, pdate;
        YouTubeThumbnailView thumbnailView;
        ConstraintLayout fullLayout;

        ImageView fav_btn;

        public MainHolder(@NonNull View itemView) {
            super(itemView);

            fav_btn = itemView.findViewById(R.id.favBtn);
            thumbnailView = itemView.findViewById(R.id.tube_thumbnailViewID);
            title = itemView.findViewById(R.id.tube_titleID);
            pdate = itemView.findViewById(R.id.tubeLengthID);
            fullLayout = itemView.findViewById(R.id.fullTubeViewID);

        }
    }
}
