package saturday.live.snl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class HomeFrag extends Fragment {
    List<TubeDataModel> videolist;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    TubeAdapter adapter;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    Activity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.tube_recID);
        videolist = new ArrayList<>();
        activity = getActivity();

        final LinearLayout adContainer = view.findViewById(R.id.banner_container);

       /* if (!checkInternet()) {
            adContainer.setVisibility(View.INVISIBLE);
        }
*/
       /* AdView adView = new AdView(getContext(), getString(R.string.banner), AdSize.BANNER_HEIGHT_50);
        adContainer.addView(adView);
        adView.loadAd();*/



        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading data. Please wait....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        loadData();
    }

    private void loadData() {

    }
}
