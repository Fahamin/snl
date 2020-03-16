package saturday.live.snl.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import me.timos.thuanle.fbnativeadadapter.FBNativeAdAdapter;
import saturday.live.snl.adapterr.FavAdapter;
import saturday.live.snl.activity.MainActivity;
import saturday.live.snl.R;
import saturday.live.snl.database.FavModel;

import static saturday.live.snl.Fun.checkInternet;


public class FavFrag extends Fragment {

    RecyclerView recyclerView;
    public static List<FavModel> list;

    TextView emptyTxt;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emptyTxt = view.findViewById(R.id.notFoundTxt);
        recyclerView = view.findViewById(R.id.favRecviewID);

        list = new ArrayList<>();
     //   new Fun(getContext());
        final LinearLayout adContainer = view.findViewById(R.id.banner_container);

       /* if (!checkInternet()) {
            adContainer.setVisibility(View.INVISIBLE);
        }*/

        AdView adView = new AdView(getContext(), getString(R.string.banner), AdSize.BANNER_HEIGHT_50);
        adContainer.addView(adView);
        adView.loadAd();

        list = MainActivity.favDatabase.favoriteDao().getFavoriteData();


        if (list.size() > 0) {
            prepareForView();
        } else {
            recyclerView.setVisibility(View.GONE);
            emptyTxt.setVisibility(View.VISIBLE);
        }


    }

    private void prepareForView() {

        RecyclerView.LayoutManager layoutManagerBeforeMeal = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManagerBeforeMeal);
        //  recyclerView.addItemDecoration(new ItemDecorate(1, dpToPx(2), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        FavAdapter adapter = new FavAdapter(getContext(), list);
        adapter.notifyDataSetChanged();

        FBNativeAdAdapter fbAdapter = FBNativeAdAdapter.Builder.with(getResources().getString(R.string.nativeadd), adapter)
               .adItemIterval(4)
              .build();

        recyclerView.setAdapter(fbAdapter);

    }
}
