package saturday.live.snl.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.timos.thuanle.fbnativeadadapter.FBNativeAdAdapter;
import saturday.live.snl.Fun;
import saturday.live.snl.R;
import saturday.live.snl.adapterr.ListTubeAdapter;
import saturday.live.snl.model.TubeDataModel;

public class player_playlist extends Fragment {
    List<TubeDataModel> videolist;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    ListTubeAdapter adapter;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    Activity activity;

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("playlist");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.favRecviewID);
        videolist = new ArrayList<>();
        activity = getActivity();

        final LinearLayout adContainer = view.findViewById(R.id.banner_container);

     /*   Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
        */


        AdView adView = new AdView(getContext(), getString(R.string.banner), AdSize.BANNER_HEIGHT_50);
        adContainer.addView(adView);
        adView.loadAd();

        new Fun(getContext());

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading data. Please wait....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        loadData();
    }

    private void loadData() {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                videolist.clear();
                Iterable<DataSnapshot> allSingleItem = dataSnapshot.getChildren();

                for (DataSnapshot singleItem : allSingleItem) {
                    TubeDataModel dataModel = singleItem.getValue(TubeDataModel.class);
                    videolist.add(dataModel);
                }

                RecyclerView.LayoutManager layoutManagerBeforeMeal = new GridLayoutManager(getContext(), 1);
                recyclerView.setLayoutManager(layoutManagerBeforeMeal);
                //  recyclerView.addItemDecoration(new ItemDecorate(1, dpToPx(2), true));
                recyclerView.setItemAnimator(new DefaultItemAnimator());


                ListTubeAdapter adapter = new ListTubeAdapter(getContext(), videolist, recyclerView);
                adapter.notifyDataSetChanged();

                FBNativeAdAdapter fbAdapter = FBNativeAdAdapter.Builder.with(getResources().getString(R.string.nativeadd), adapter)
                        .adItemIterval(3)
                        .build();

                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Loading Failed ! Check Network Connection", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }

}
