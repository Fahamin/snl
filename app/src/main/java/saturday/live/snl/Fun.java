package saturday.live.snl;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.TypedValue;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.util.List;


public class Fun {


    public static Activity activity;
    public static DatabaseReference referenceNews = FirebaseDatabase.getInstance().getReference("News");
    public static DatabaseReference referenceFile = FirebaseDatabase.getInstance().getReference("File");
    public static DatabaseReference referenceBuild = FirebaseDatabase.getInstance().getReference("bb");

    public static Context context;

    private static com.facebook.ads.InterstitialAd interstitial;

    public Fun(Context context) {
        this.context = context;
    }



    public static boolean checkInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if (info != null && info.isConnected()) {
            return true;
        } else {
            return false;

        }
    }

    public static void addShow() {
      //  count++;
        //if (count % 2 == 0) {

            interstitial = new com.facebook.ads.InterstitialAd(context, context.getString(R.string.insta));
            interstitial.setAdListener(new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {

                }

                @Override
                public void onError(Ad ad, AdError adError) {

                }

                @Override
                public void onAdLoaded(Ad ad) {
                    SHOWINSTADD();
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            });
            interstitial.loadAd();
        //}

    }

    private static void SHOWINSTADD() {
        if (interstitial.isAdLoaded()) {
            interstitial.show();
        }
    }


}