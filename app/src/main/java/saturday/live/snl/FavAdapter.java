/*
package saturday.live.snl;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import us.dev.configaddons.R;
import us.dev.configaddons.activity.MainActivity;
import us.dev.configaddons.dialog.AlertViewDialog;
import us.dev.configaddons.model.FavModel;

import static us.dev.configaddons.classfile.Fun.addShow;
import static us.dev.configaddons.classfile.Fun.downLoadFromLink;


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
        View view = LayoutInflater.from(context).inflate(R.layout.row_item, null, false);
        return new MainHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavAdapter.MainHolder holder, int position) {

        final FavModel favModel = list.get(position);


        holder.name.setText(favModel.getName());
        holder.title.setText(favModel.getTitle());
        holder.icon.setImageResource(favModel.getImage());

        holder.fav_btn.setImageResource(R.drawable.fab_green);
        holder.fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.favDatabase.favoriteDao().delete(favModel);
                Toast.makeText(context, "Remove from Favorite", Toast.LENGTH_SHORT).show();
                holder.fav_btn.setImageResource(R.drawable.fab_white);

            }
        });

        holder.fullM3ULayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogView(favModel.getLink());
                addShow();
            }
        });
    }

    public void alertDialogView(final String ss) {
        AlertViewDialog dialog = new AlertViewDialog();
        dialog.showDialog(context,ss);
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
        TextView title, name;
        ImageView icon;
        ConstraintLayout fullM3ULayout;

        ImageView fav_btn;

        public MainHolder(@NonNull View itemView) {
            super(itemView);

            fav_btn = itemView.findViewById(R.id.fav_btn);
            name = itemView.findViewById(R.id.m3u_nameTV);
            title = itemView.findViewById(R.id.m3u_titleTV);
            icon = itemView.findViewById(R.id.m3u_imageViewID);
            fullM3ULayout = itemView.findViewById(R.id.fullrowLayoutID);
        }
    }
}*/
