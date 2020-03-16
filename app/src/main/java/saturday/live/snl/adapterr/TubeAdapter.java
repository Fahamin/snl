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

import java.util.ArrayList;
import java.util.List;

import saturday.live.snl.activity.MainActivity;
import saturday.live.snl.R;
import saturday.live.snl.model.TubeDataModel;
import saturday.live.snl.api.YouTubApi;
import saturday.live.snl.database.FavModel;
import saturday.live.snl.activity.playerview;


public class TubeAdapter extends RecyclerView.Adapter<TubeAdapter.Myholder> implements Filterable {

    Context context;
    List<TubeDataModel> videolist, searchList;
    RecyclerView recyclerView;

    public TubeAdapter(Context context, List<TubeDataModel> videolist, RecyclerView recyclerView) {
        this.context = context;
        this.videolist = videolist;
        this.searchList = videolist;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.tube_view, null, false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Myholder myholder, int i) {

        final TubeDataModel dataModel = videolist.get(i);
        myholder.title.setText(dataModel.getTitle());
        myholder.pdate.setText(dataModel.getPdate());

        myholder.thumbnailView.initialize(YouTubApi.getApi_kEY(), new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(dataModel.getLink());
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

        myholder.fullLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              addShow();
                context.startActivity(new Intent(context, playerview.class).putExtra("video_id", dataModel.getLink()));
            }
        });

        if (MainActivity.favDatabase.favoriteDao().isFavorite(dataModel.getId()) == 1) {
            myholder.favBtn.setImageResource(R.drawable.ic_favfull);
        } else {
            myholder.favBtn.setImageResource(R.drawable.ic_fav);

        }

        myholder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FavModel favModel = new FavModel();

                int id = dataModel.getId();
                String title = dataModel.getTitle();
                String link = dataModel.getLink();
                String date = dataModel.getPdate();

                favModel.setId(id);
                favModel.setTitle(title);
                favModel.setLink(link);
                favModel.setDate(date);

                if (MainActivity.favDatabase.favoriteDao().isFavorite(id) != 1) {
                    myholder.favBtn.setImageResource(R.drawable.ic_favfull);
                    MainActivity.favDatabase.favoriteDao().addData(favModel);
                    Toast.makeText(context, " Add to Favorite ", Toast.LENGTH_SHORT).show();
                } else {
                    myholder.favBtn.setImageResource(R.drawable.ic_fav);
                    MainActivity.favDatabase.favoriteDao().delete(favModel);
                    Toast.makeText(context, "Remove From Favorite", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return videolist.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {
        TextView title, pdate;
        YouTubeThumbnailView thumbnailView;
        ConstraintLayout fullLayout;
        ImageView favBtn;

        public Myholder(@NonNull View itemView) {
            super(itemView);
            thumbnailView = itemView.findViewById(R.id.tube_thumbnailViewID);
            title = itemView.findViewById(R.id.tube_titleID);
            pdate = itemView.findViewById(R.id.tubeLengthID);
            fullLayout = itemView.findViewById(R.id.fullTubeViewID);
            favBtn = itemView.findViewById(R.id.favBtn);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    videolist = searchList;
                } else {
                    ArrayList<TubeDataModel> filterList = new ArrayList<>();

                    for (TubeDataModel MovieModel : searchList) {
                        if (MovieModel.getTitle().toLowerCase().contains(charString)) {
                            filterList.add(MovieModel);
                        }
                    }
                    videolist = filterList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = videolist;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                videolist = (ArrayList<TubeDataModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
