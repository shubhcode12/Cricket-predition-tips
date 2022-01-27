package com.crickpred.app.Util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import com.crickpred.app.R;
import com.facebook.ads.Ad;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;


import es.dmoral.toasty.Toasty;


public class Method {
    // User Loging logs added
    private AdView adView;
    private Ad fbadView;
    private Activity activity;
    public static boolean share = false, loginBack = false, allowPermitionExternalStorage = false, personalization_ad = false;
    private Context _context;
    private DBHelper dbHelper;
    String TAG="KINGSN";
    public com.facebook.ads.InterstitialAd interstitialAd;
    com.facebook.ads.AdView adViewfb;
    public InterstitialAd mInterstitialAd;
    public Dialog loadingDialog;
    private String countryCode;
    private FirebaseAuth auth,mAuth;
    String verificationcodebysystem;
    String codeBySystem,mobileNumber;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    String checksum;
    String orderId;
    String mobile,minRefill;
    private Method method;

    public static final String WEBSITE = "DEFAULT";
    public static final String CHANNEL_ID = "WAP";
    public static final String INDUSTRY_TYPE_ID = "Retail";
    public static final String CALLBACK_URL = "https://pguat.paytm.com/paytmchecksum/paytmCallback.jsp";

    public Method(Context context) {
        this._context = context;
    }
 /*
    public static void UserLoginLogs(final String userid,final String logStatus,final  String deviceID ) {


        String register = RestAPI.API_Login_Logs +"&user_id=" + userid+ "&deviceid=" + deviceID + "&user_ip=" + Constant.PublicIP + "&logs_status=" + logStatus ;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(register, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Log.d("Response", new String(responseBody));
                String res = new String(responseBody);

                try {
                    JSONObject jsonObject = new JSONObject(res);

                    JSONArray jsonArray = jsonObject.getJSONArray(AppSid);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject object = jsonArray.getJSONObject(i);
                        String msg = object.getString("msg");
                        String success = object.getString("success");

                        if (success.equals("1")) {
                            Log.d("Response",msg);
                        } else {
                            Log.d("Response",msg);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Response-F", new String(responseBody));
            }
        });
    }
    // Get User balance
    public static void UserBalance( String UserID,final TextView txt) {
        String login = RestAPI.API_User_Balance + "&user_id=" + UserID;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(login, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Log.d("Response", new String(responseBody));
                String res = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(res);
                    JSONArray jsonArray = jsonObject.getJSONArray(Constant.AppSid);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String success = object.getString("success");
                        if (success.equals("1")) {
                            String bal = object.getString("points");
                            Constant.userBalance=new UserBalance(bal);
                            txt.setText(bal);
                            Log.d("Response",bal);
                        } else {
                            Log.d("Response", new String(responseBody));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // progressDialog.dismiss();
            }
        });
    }

    public static void UserBalance(final String UserID) {
        String login = RestAPI.API_User_Balance + "&user_id=" + UserID;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(login, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Log.d("Response-u", new String(responseBody));
                String res = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(res);
                    JSONArray jsonArray = jsonObject.getJSONArray(Constant.AppSid);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String success = object.getString("success");
                        if (success.equals("1")) {
                            String bal = object.getString("points");

                            Log.d("Response-u", bal);

                        } else {
                            Log.d("Response-u", new String(responseBody));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Response-UF", new String(responseBody));
            }
        });
    }
    // Update User balance
    public static void BalanceUpdate(final String UserID,final String CoinType,final String Coin,final String Ip,final TextView txt,final int pointtype) {

        String login = RestAPI.API_Balance_Update + "&user_id=" + UserID+"&active_type=" + CoinType+"&points=" + Coin+"&user_ip="
                + Ip+"&device_id=" + DeviceID+"&point_type=" + pointtype;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(login, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Log.d("Response-b", new String(responseBody));
                String res = new String(responseBody);

                try {
                    JSONObject jsonObject = new JSONObject(res);
                    JSONArray jsonArray = jsonObject.getJSONArray(Constant.AppSid);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject object = jsonArray.getJSONObject(i);
                        String success = object.getString("success");

                        if (success.equals("1")) {
                            txt.setText("");
                            String bal = object.getString("points");
                            Constant.userBalance =new UserBalance(bal);
                            txt.setText(bal);
                            Log.d("Response",bal);
                        } else {
                            Log.d("Response", new String(responseBody));
                            // Ex.okAlertBox(getResources().getString(R.string.login_failed_message));
                            //Toast.makeText(Login.this, getResources().getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // progressDialog.dismiss();
            }
        });
    }

    public static void BalanceUpdate(final String UserID,final String CoinType,final String Coin,final String Ip,final int pointtype) {

        String login = RestAPI.API_Balance_Update + "&user_id=" + UserID+"&active_type=" + CoinType+"&points=" + Coin+"&user_ip="
                + Ip+"&device_id=" + DeviceID+"&point_type=" + pointtype;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(login, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Log.d("Response-b", new String(responseBody));
                String res = new String(responseBody);

                try {
                    JSONObject jsonObject = new JSONObject(res);
                    JSONArray jsonArray = jsonObject.getJSONArray(Constant.AppSid);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String success = object.getString("success");
                        if (success.equals("1")) {
                            String bal = object.getString("points");
                            Constant.userBalance =new UserBalance(bal);
                            Log.d("Response",bal);
                        } else {
                            Log.d("Response", new String(responseBody));
                            // Ex.okAlertBox(getResources().getString(R.string.login_failed_message));
                            //Toast.makeText(Login.this, getResources().getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // progressDialog.dismiss();
            }
        });
    }

*/
    //---------------Banner Ad---------------//


   /* public void showBannerAd(Activity activity, FrameLayout frameLayout) {
        // Log.d("Response-ads", Boolean.toString(Constant.settings.isBanner_ad()));
        if (Constant.settings.getBanner_ad().toLowerCase().equals("addmob")) {
            if (ConsentInformation.getInstance(_context).getConsentStatus() == ConsentStatus.NON_PERSONALIZED) {
                showNonPersonalizedAds(frameLayout);
            } else {
                showPersonalizedAds(frameLayout);
            }

        } else if (Constant.settings.getBanner_ad().toLowerCase().equals("facebook")) {
            //showFbBanner(activity,linearLayout);
           showFbBanner2(activity,frameLayout);
              }
         else if(Constant.settings.getBanner_ad().toLowerCase().equals("both")) {
               // showBoth_BannerAd();
            Log.d(TAG, "showIndustrialAd: "+Constant.banner_switch);
            if (Constant.banner_switch == 1) {
                Log.d(TAG, "showIndustrialAd: indusrtialcall:on both case");
                if (ConsentInformation.getInstance(_context).getConsentStatus() == ConsentStatus.NON_PERSONALIZED) {
                    showNonPersonalizedAds(frameLayout);
                } else {
                    showPersonalizedAds(frameLayout);
                }
                Constant.banner_switch = 0;
            } else {
                Log.d(TAG, "showIndustrialAd: fbindusrtialcall:on both case");
                showFbBanner2(activity,frameLayout);
                Constant.banner_switch = 1;
            }
             }
    }

    private void showPersonalizedAds(FrameLayout frameLayout) {

        if (Constant.settings.getBanner_ad().toLowerCase().equals("addmob")) {
            AdView adView = new AdView(_context);
            AdRequest adRequest = new AdRequest.Builder()
                    .build();
            adView.setAdUnitId(Constant.settings.getBanner_ad_id());
            adView.setAdSize(AdSize.SMART_BANNER);
            frameLayout.addView(adView);
            adView.loadAd(adRequest);
        }
    }


     private void showNonPersonalizedAds(FrameLayout frameLayout) {
        Bundle extras = new Bundle();
        extras.putString("npa", "1");
        if (Constant.settings.getBanner_ad().toLowerCase().equals("addmob")) {
            AdView adView = new AdView(_context);
            AdRequest adRequest = new AdRequest.Builder()
                    .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                    .build();
            adView.setAdUnitId(Constant.settings.getBanner_ad_id());
            adView.setAdSize(AdSize.SMART_BANNER);
            frameLayout.addView(adView);
            adView.loadAd(adRequest);
        }
    }

    public void showFbBanner(Activity activity, LinearLayout linearLayout)
    {

        fbadView = new com.facebook.ads.AdView(activity, Constant.settings.getFb_banner_ad_id(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);

        // Find the Ad Container
        LinearLayout adContainer = (linearLayout);

        // Add the ad view to your activity layout
        adContainer.addView(adView);

        // Request an ad
        fbadView.loadAd();
    }

    public void showFbBanner2(Activity activity, FrameLayout frameLayout)
    {
        Log.d("KINGSN", "showFbBanner2: "+Constant.settings.getFb_banner_ad_id());

        adViewfb = new com.facebook.ads.AdView(activity, Constant.settings.getFb_banner_ad_id(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        // Find the Ad Container
        FrameLayout adContainer = (frameLayout);

        // Add the ad view to your activity layout
        adContainer.addView(adViewfb);

        adViewfb.loadAd();
       // advieww.setVisibility(View.VISIBLE);

    }
*/
    //---------------Banner Ad---------------//

    //---------------Rewarded video ad---------------//

  /*  public void showVideoAd(final Activity activity,final String uid, final String Device) {
        if (Constant.settings.getRewarded_video_ads().toLowerCase().equals("addmob")) {
            Video_ads_limit_count(activity,uid,Device);
            if (Constant.VIDEO_AD_COUNT <= Integer.parseInt(Constant.settings.getVideo_ads_limit())) {
                final ProgressDialog progressDialog = new ProgressDialog(activity);
                progressDialog.setMessage(activity.getString(R.string.adsloading));
                progressDialog.setCancelable(false);
                progressDialog.show();
                mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(activity);
                if (Constant.settings != null) {
                    if (Constant.settings.getRewarded_video_ads().toLowerCase().equals("addmob")) {
                        if (mRewardedVideoAd != null) {
                            AdRequest adRequest;
                            if (ConsentInformation.getInstance(activity).getConsentStatus() == ConsentStatus.PERSONALIZED) {
                                adRequest = new AdRequest.Builder().build();
                            } else {
                                Bundle extras = new Bundle();
                                extras.putString("npa", "1");
                                adRequest = new AdRequest.Builder()
                                        .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                                        .build();
                            }

                            mRewardedVideoAd.loadAd(Constant.settings.getRewarded_video_ads_id(), adRequest);
                            mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
                                @Override
                                public void onRewarded(RewardItem reward) {
                                    Log.d("reward_video_ad", "reward");

                                    Toast.makeText(activity, "Reward Point Added", Toast.LENGTH_SHORT).show();
                                    BalanceUpdate(uid,"Video Ads",Constant.settings.getVideo_add_point(),Constant.PublicIP,1);
                                    Ads_Count_Update(uid,Device);
                                    Video_ads_limit_count(activity,uid,Device);
                                }

                                @Override
                                public void onRewardedVideoAdLeftApplication() {
                                    Log.d("reward_video_ad", "AdLeftApplication");
                                }

                                @Override
                                public void onRewardedVideoAdFailedToLoad(int i) {
                                    progressDialog.dismiss();
                                    Toast.makeText(activity, "Video Ads load Failed", Toast.LENGTH_SHORT).show();
                                    Log.d("reward_video_ad", "Failed");
                                }

                                @Override
                                public void onRewardedVideoAdClosed() {
                                    Events.VideoAdsReload adsReload = new Events.VideoAdsReload("100");
                                    EventBus.getDefault().post(adsReload);
                                    Log.d("reward_video_ad", "close");


                                }

                                @Override
                                public void onRewardedVideoAdLoaded() {
                                    //  mRewardedVideoAd.show();
                                    progressDialog.dismiss();
                                    Toast.makeText(activity, "Video Ads load", Toast.LENGTH_SHORT).show();
                                    Log.d("reward_video_ad", "Video Ads load");
                                }

                                @Override
                                public void onRewardedVideoAdOpened() {
                                    Log.d("reward_video_ad", "open");
                                }

                                @Override
                                public void onRewardedVideoStarted() {
                                    Log.d("reward_video_ad", "start");
                                }

                                @Override
                                public void onRewardedVideoCompleted() {
                                    Log.d("reward_video_ad", "completed");

                                }
                            });
                        }
                    } else {
                        progressDialog.dismiss();

                    }
                } else {
                    progressDialog.dismiss();
                }
            }else{ Toast.makeText(activity, "Daily Ads Limit Over", Toast.LENGTH_SHORT).show();}
        }else{ Toast.makeText(activity, "Daily Ads Limit Over", Toast.LENGTH_SHORT).show();}
    }*/

    //---------------Rewarded video ad---------------//

    //---------------Interstitial Ad---------------//

    public void loadInter(Activity activity) {
      //mInterstitial = new InterstitialAd(activity);

           // Constant.AD_COUNT = Constant.AD_COUNT + 1;

                //Constant.AD_COUNT = 0;
                AdRequest adRequest;

                if (ConsentInformation.getInstance(activity).getConsentStatus() == ConsentStatus.PERSONALIZED) {
                    adRequest = new AdRequest.Builder().build();
                } else {
                    Bundle extras = new Bundle();
                    extras.putString("npa", "1");
                    adRequest = new AdRequest.Builder()
                            .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                            .build();
                }
          InterstitialAd.load(activity, "ca-app-pub-3940256099942544/1033173712", adRequest,
                  new InterstitialAdLoadCallback() {

                      @Override
                      public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                          // The mInterstitialAd reference will be null until
                          // an ad is loaded.
                          mInterstitialAd = interstitialAd;
                          Log.i(TAG, "onAdLoaded");
                      }

                      @Override
                      public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                          // Handle the error
                          Log.i(TAG, loadAdError.getMessage());
                          mInterstitialAd = null;
                      }


                });


    }

    //---------------Interstitial Ad---------------//

   /* public void Video_ads_limit_count(final Activity activity,final String uid, final String Device){
    String login = RestAPI.API_Video_Ads_Count + "&user_id=" + uid + "&device_id=" + Device;
    AsyncHttpClient client = new AsyncHttpClient();
    client.get(login, null, new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            Log.d("Response", new String(responseBody));
            String res = new String(responseBody);
            try {
                JSONObject jsonObject = new JSONObject(res);
                JSONArray jsonArray = jsonObject.getJSONArray(Constant.AppSid);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String success = object.getString("success");
                    if (success.equals("1")) {
                        int  Counter =Integer.parseInt(object.getString("ads_count"));
                        Constant.VIDEO_AD_COUNT = Counter;

                        int CountLimit=Integer.parseInt(Constant.settings.getDaily_spin_limit());

                    }
                    else {

                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

        }
    });
}

    public void Ads_Count_Update(final String uid, final String Device) {
        String login = RestAPI.API_Video_Ads_Count_update + "&user_id=" + uid + "&device_id=" + Device;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(login, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Log.d("Response-count", new String(responseBody));
                String res = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(res);
                    JSONArray jsonArray = jsonObject.getJSONArray(Constant.AppSid);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject object = jsonArray.getJSONObject(i);
                        String success = object.getString("success");

                        if (success.equals("1")) {
                        } else {
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
*/
   /* public void showFacdebookAd(Activity activity1)
    {
        if (interstitialAd != null) {
            interstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
            Constant.AD_COUNT = Constant.AD_COUNT + 1;
            AudienceNetworkAds.initialize(activity1);
            interstitialAd = new com.facebook.ads.InterstitialAd(activity1, Constant.settings.getFb_industrial_ad_id());


        // Create listeners for the Interstitial Ad
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                //Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                //Log.e(TAG, "Interstitial ad dismissed.");
              //  interstitialAd.loadAd();

            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e(TAG, "FBInterstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "FBInterstitial ad is loaded and ready to be displayed!");
                // Show the ad
                //interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                //Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                //Log.d(TAG, "Interstitial ad impression logged!");
            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());
      }
    }
    public void showIndustrialAd(Activity activity1)
    {
       if( Constant.AD_COUNT == Integer.parseInt(Constant.settings.getInterstital_ad_click())) {
           Constant.AD_COUNT = 0;
           if (Constant.settings.getInterstital_ad().toLowerCase().equals("addmob")) {
             //  loadInter(activity1);
               showadmobindus(activity1);

           } else if (Constant.settings.getInterstital_ad().toLowerCase().equals("facebook")) {
               showFacdebookAd(activity1);
           } else if (Constant.settings.getInterstital_ad().toLowerCase().equals("both")) {
               Log.d(TAG, "showIndustrialAd: "+Constant.industrial_switch);
               if (Constant.industrial_switch == 1) {
                   Log.d(TAG, "showIndustrialAd: indusrtialcall:on both case");
                   showadmobindus(activity1);
                   Constant.industrial_switch = 0;
               } else {
                   Log.d(TAG, "showIndustrialAd: fbindusrtialcall:on both case");
                   showFacdebookAd(activity1);
                   Constant.industrial_switch = 1;
               }
           }

       }else{
           Constant.AD_COUNT =Constant.AD_COUNT+1  ;
       }
    }

    public void createInterstitialAd(Activity activity) {

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(activity, "ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
                    }


                });
    }

        public void showadmobindus(Activity activity){
            if (mInterstitialAd != null) {
                mInterstitialAd.show(activity);
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.");
                loadInter(activity);
            }
        }
*/
    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable();
        }
        return false;
    }

    public void loadingDialogg(Activity activity) {
       loadingDialog = new Dialog(activity);
        loadingDialog.setContentView(R.layout.lotiee_loading);
        loadingDialog.setCancelable(false);
        Objects.requireNonNull(loadingDialog.getWindow()).setBackgroundDrawableResource(
                R.color.transparent);
        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        } else {
            loadingDialog = new Dialog(activity);
            loadingDialog.setContentView(R.layout.lotiee_loading);
            loadingDialog.setCancelable(false);
            Objects.requireNonNull(loadingDialog.getWindow()).setBackgroundDrawableResource(
                    R.color.transparent);
            loadingDialog.show();
        }
    }
    public void loadingDialoggDismiss(Activity activity) {
        loadingDialog.dismiss();

    }

    public void showToasty(Activity activity,String Type,String Message){
        if(Type.equals("1")) {
            Toasty.success(activity, ""+Message, Toast.LENGTH_SHORT).show();
        }else{
            Toasty.error(activity, ""+Message, Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("HardwareIds")
    public String getDeviceId(Context context) {

        String deviceId;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            deviceId = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            // Toast.makeText(context,  deviceId, Toast.LENGTH_SHORT).show();

        }else {

            final TelephonyManager mTelephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);



            if (mTelephony.getDeviceId() != null) {
                deviceId = mTelephony.getDeviceId();
            } else {
                deviceId = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
                //  Toast.makeText(LoginActivity.this,  deviceId, Toast.LENGTH_SHORT).show();
            }
        }

        return deviceId;

    }


    public void setupPaymentDialog(Activity activity,String refillAmount,String createId, String TxnType){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View view2 = LayoutInflater.from(activity).inflate(R.layout.dialog_select_payment_system, activity.findViewById(R.id.selectPayment));
        builder.setView(view2);
        final AlertDialog alertDialog = builder.create();
//        EditText etNamePaytmUpi = view2.findViewById(R.id.namePaytmUpiEt);
//        EditText etPaytmUpiNo = view2.findViewById(R.id.paytmUpiNoEt);
        RadioButton checkboxPaymentGateway=view2.findViewById(R.id.checkboxPaymentGateway);
        RadioButton checkboxPaymentScreenshot=view2.findViewById(R.id.checkboxPaymentScreenshot);



        checkboxPaymentGateway.setOnClickListener(view -> {

            checkboxPaymentGateway.setChecked(true);
            checkboxPaymentScreenshot.setChecked(false);
            Toast.makeText(activity, "Paytm Upi submitted!", Toast.LENGTH_SHORT).show();


        });

        checkboxPaymentScreenshot.setOnClickListener(v -> {
            checkboxPaymentScreenshot.setChecked(true);
            checkboxPaymentGateway.setChecked(false);
            Toast.makeText(activity, "Payment Screent submitted", Toast.LENGTH_SHORT).show();
        });


        view2.findViewById(R.id.btnProceedCancel).setOnClickListener(view1 -> {
            alertDialog.dismiss();


        });

        view2.findViewById(R.id.btnProceedPayment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  alertDialog.dismiss();
                GenerateChecksum(activity,refillAmount,createId,TxnType);
            }
        });
        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

    public void GenerateChecksum(Activity activity,String refillAmount,String createId,String TxnType) {
        Random r = new Random(System.currentTimeMillis());
        orderId = "ORDER" + (1 + r.nextInt(2)) * 10000
                + r.nextInt(10000);

        String url=RestAPI.API_Paytm_Url;
        // String url="https://darwinbark.com/paytmsdk/generateChecksum.php";
        // String url="https://darwinbark.com/projects/gopunt/paytmsdk/generateChecksum.php";


        Map<String, String> params = new HashMap<>();
        params.put( "MID" ,GlobalVariables.Paytm_mid);
        params.put( "ORDER_ID" , orderId);
        params.put( "CUST_ID" , GlobalVariables.profileuser.getMobile());
        params.put( "MOBILE_NO" , GlobalVariables.profileuser.getMobile());
        params.put( "EMAIL" , GlobalVariables.profileuser.getEmail());
        params.put( "CHANNEL_ID" , "WAP");
        params.put( "TXN_AMOUNT" , refillAmount);
        params.put( "WEBSITE" , WEBSITE);
        params.put( "INDUSTRY_TYPE_ID" , INDUSTRY_TYPE_ID);
        params.put( "CALLBACK_URL", CALLBACK_URL);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(activity);
// Request a string response from the provided URL.
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Toast.makeText(Wallet.this,response,Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if (jsonObject.has("CHECKSUMHASH"))
                            {
                                checksum=jsonObject.getString("CHECKSUMHASH");
                                onStartTransaction(activity,refillAmount,createId,TxnType);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(activity,"Error...!",Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<String, String>();
                params.put( "MID" , GlobalVariables.Paytm_mid);
                params.put( "ORDER_ID" , orderId);
                params.put( "CUST_ID" , GlobalVariables.profileuser.getMobile());
                params.put( "MOBILE_NO" , GlobalVariables.profileuser.getMobile());
                params.put( "EMAIL" , GlobalVariables.profileuser.getEmail());
                params.put( "CHANNEL_ID" , "WAP");
                params.put( "TXN_AMOUNT" , refillAmount);
                params.put( "WEBSITE" , WEBSITE);
                params.put( "INDUSTRY_TYPE_ID" , INDUSTRY_TYPE_ID);
                params.put( "CALLBACK_URL", CALLBACK_URL);

                return params;
            }
        };


        queue.add(stringRequest);
// Access the RequestQueue through your singleton class.
        //MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    public void onStartTransaction(Activity activity,String refillAmount,String createId,String TxnType) {
        PaytmPGService Service = PaytmPGService.getProductionService();
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put( "MID" ,GlobalVariables.Paytm_mid);
        // Key in your staging and production MID available in your dashboard

        paramMap.put( "ORDER_ID" , orderId);
        paramMap.put( "CUST_ID" , GlobalVariables.profileuser.getMobile());
        paramMap.put( "MOBILE_NO" , GlobalVariables.profileuser.getMobile());
        paramMap.put( "EMAIL" , GlobalVariables.profileuser.getEmail());
        paramMap.put( "CHANNEL_ID" , "WAP");
        paramMap.put( "TXN_AMOUNT" , refillAmount);
        paramMap.put( "WEBSITE" , WEBSITE);
        paramMap.put( "INDUSTRY_TYPE_ID" , INDUSTRY_TYPE_ID);
        paramMap.put( "CALLBACK_URL", CALLBACK_URL);
        paramMap.put( "CHECKSUMHASH" , checksum);



        PaytmOrder Order = new PaytmOrder(paramMap);

		/*PaytmMerchant Merchant = new PaytmMerchant(
				"https://pguat.paytm.com/paytmchecksum/paytmCheckSumGenerator.jsp",
				"https://pguat.paytm.com/paytmchecksum/paytmCheckSumVerify.jsp");*/

        Service.initialize(Order, null);

        Service.startPaymentTransaction(activity, true, true,
                new PaytmPaymentTransactionCallback() {
                    @Override
                    public void someUIErrorOccurred(String inErrorMessage) {
                        // Some UI Error Occurred in Payment Gateway Activity.
                        // // This may be due to initialization of views in
                        // Payment Gateway Activity or may be due to //
                        // initialization of webview. // Error Message details
                        // the error occurred.
                        Log.d(TAG, "someUIErrorOccurred: "+inErrorMessage);
                    }


                    @Override
                    public void onTransactionResponse(Bundle inResponse) {
                        com.paytm.pgsdk.Log.d("KINGSN", "Payment Transaction is successful " + inResponse);

                        //String ip = Constant.PublicIP;



                        System.out.println("===== onTransactionResponse " + inResponse.toString());
                        if (Objects.equals(inResponse.getString("STATUS"), "TXN_SUCCESS")) {
                            //    Payment Success
                            Toasty.success(activity, " Transaction success", Toast.LENGTH_LONG).show();
                            //  method.loadingDialog.dismiss();
                            //uploadData();
                           /* Method.BalanceUpdate(constant.sharedPreferences.getString(constant.profileId, "profileId"),
                                    "Credit", amount, ip, 3);*/
                            //onSuccessPay(orderId);
                            // showPay_Status_AlertDialog(1);
                            // method.loadingDialogg(Payment.this);
                            onSuccessPay(activity,refillAmount,orderId,createId,TxnType);
                        } else if (!inResponse.getBoolean("STATUS")) {
                            //    Payment Failed
                            //  Toast.makeText(Payment.this, " Transaction Failed", Toast.LENGTH_LONG).show();
                            //startActivity(new Intent(getContext(), HomeActivity.class));
                            // finish();
                            showPay_Status_AlertDialog(activity,2,refillAmount,createId,TxnType);
                            //startActivity(getIntent());
                            // method.loadingDialog.dismiss();
                        }
                    }
                    @Override
                    public void networkNotAvailable()
                    { // If network is not
                        //available, then this
                        // method gets called.
                        Log.d(TAG, "networkNotAvailable: ");
                    }

                    @Override
                    public void clientAuthenticationFailed(String inErrorMessage) {
                        // This method gets called if client authentication
                        // failed. // Failure may be due to following reasons //
                        // 1. Server error or downtime. // 2. Server unable to
                        // generate checksum or checksum response is not in
                        // proper format. // 3. Server failed to authenticate
                        // that client. That is value of payt_STATUS is 2. //
                        // Error Message describes the reason for failure.
                        Log.d(TAG, "clientAuthenticationFailed: "+inErrorMessage);
                    }

                    @Override
                    public void onErrorLoadingWebPage(int iniErrorCode,
                                                      String inErrorMessage, String inFailingUrl) {

                    }

                    // had to be added: NOTE
                    @Override
                    public void onBackPressedCancelTransaction() {
                        // Toast.makeText(CreateIdActivity.this,"Back pressed. Transaction cancelled Kingsn",Toast.LENGTH_LONG).show();
                        showPay_Status_AlertDialog(activity,2,refillAmount,createId,TxnType);
                    }

                    @Override
                    public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
                        com.paytm.pgsdk.Log.d("KINGSN", "Payment Transaction Failed " + inErrorMessage);
                        // Toast.makeText(getBaseContext(), "Payment Transaction Failed ", Toast.LENGTH_LONG).show();
                        showPay_Status_AlertDialog(activity,2,refillAmount,createId,TxnType);
                    }

                });
    }


    private void onSuccessPay(Activity activity,String refillAmount ,String orderId,String createId,String TxnType) {

        if(this.orderId ==null){
            //Toast.makeText(HomeActivity.this, "NULL", Toast.LENGTH_LONG).show();
            //editor.putString(GlobalVariables.txn_orderId, orderId);


            Random r = new Random(System.currentTimeMillis());
            this.orderId = "BYCASH" + (1 + r.nextInt(2)) * 1000
                    + r.nextInt(1000);
            // editor.putString(GlobalVariables.txn_orderId, this.orderId);

        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, RestAPI.API_insert_payment_verification,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //   Toast.makeText(Payment.this, "RESPONSE: " + response, Toast.LENGTH_SHORT).show();
                        try {
                            System.out.println(response);
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray jsonArray = jsonObject.getJSONArray(GlobalVariables.AppSid);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject object = jsonArray.getJSONObject(i);
                                String success = object.getString("success");

                                if (success.equals("1")) {
                                    Toast.makeText(activity, "Updated", Toast.LENGTH_SHORT).show();
                                    // finish();
                                    // startActivity(getIntent());
                                    // method.loadingDialog.dismiss();
                                    // loadingDialog.dismiss();
                                    showPay_Status_AlertDialog(activity,1,refillAmount,createId,TxnType);
                                   /* android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(Payment.this);
                                    alertDialogBuilder.setTitle(object.getString("title"));
                                    alertDialogBuilder.setMessage(object.getString("msg"));
                                    alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
                                    alertDialogBuilder.setPositiveButton(Payment.this.getResources().getString(R.string.ok_message),
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface arg0, int arg1) {
                                                    finish();
                                                    method.loadingDialog.dismiss();
                                                    finishAffinity();
                                                }
                                            });

                                    android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                                    alertDialog.show();
                                   // Toast.makeText(Payment.this, object.getString("msg"), Toast.LENGTH_LONG).show();*/


                                } else {
                                    method.loadingDialog.dismiss();
                                    android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(activity);
                                    alertDialogBuilder.setTitle(object.getString("title"));
                                    alertDialogBuilder.setMessage(object.getString("msg"));
                                    alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
                                    alertDialogBuilder.setPositiveButton(activity.getResources().getString(R.string.ok_message),
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface arg0, int arg1) {
                                                    activity.finish();
                                                    method.loadingDialog.dismiss();
                                                   activity. finishAffinity();
                                                }
                                            });

                                    android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                                    alertDialog.show();
                                    //  Toast.makeText(Payment.this, object.getString("msg"), Toast.LENGTH_LONG).show();
                                }

                            }

                            // progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(activity, "Error: " + e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Toast.makeText(Payment.this, "RESPONSE: " + error, Toast.LENGTH_SHORT).show();
                Log.e("Error", "" + error.getMessage());
                //ifvolleyfail();

            }
        }) { @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<>();
            Log.d(TAG, "getParams: "+params);
            if(TxnType.equals("0")){

                params.put("payment_type","idCreation");
                params.put("txnType","0");
                params.put("createdId",createId);
                params.put("idUsername", GlobalVariables.idUsername);
                params.put("idPassword",GlobalVariables.idPassword);


            }else if(TxnType.equals("1")){

                params.put("payment_type","id_deposit");
                params.put("txnType","1");
                params.put("createdId",createId);
                params.put("idUsername", GlobalVariables.idUsername);
                params.put("idPassword",GlobalVariables.idPassword);
            }else if(TxnType.equals("2")){

                params.put("payment_type","Wallet_deposit");
                params.put("txnType","1");
                params.put("createdId",createId);
                params.put("idUsername","");
                params.put("idPassword","");
            }

            params.put("payMentMode","0");
            params.put("mobile", GlobalVariables.profileuser.getMobile());
            params.put("name",  GlobalVariables.profileuser.getName());
            params.put("email",  GlobalVariables.profileuser.getEmail());
            params.put("amount",refillAmount);
            params.put("order_id", orderId);
            params.put("city",   GlobalVariables.profileuser.getCity());

            return params;
        }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);



    }

    @SuppressLint("SetTextI18n")
    public void showPay_Status_AlertDialog(Activity activity,int status,String refillAmount,String createId,String TxnType) {
        // method.loadingDialog.dismiss();
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity);
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.paydilog_status, viewGroup, false);
        ImageView image = dialogView.findViewById(R.id.iv_close);
        Button tvYesPass = dialogView.findViewById(R.id.tvYesPass);
        com.airbnb.lottie.LottieAnimationView animationView= dialogView.findViewById(R.id.animationView);
        TextView ctvbTitle=dialogView.findViewById(R.id.ctvbTitle);
        TextView payDate=dialogView.findViewById(R.id.payDate);
        TextView paymsg=dialogView.findViewById(R.id.paymsg);
        TextView txnid=dialogView.findViewById(R.id.txnid);
        TextView txnamnt=dialogView.findViewById(R.id.txnamnt);
        TextView uemail=dialogView.findViewById(R.id.uemail);
        if(status == 1){
            ctvbTitle.setText("Payment Success ");
            ctvbTitle.setTextColor(activity.getResources().getColor(R.color.successColor));
            paymsg.setText("Deposite Amount Has Been Added !\n Check Your Wallet");
            animationView.setAnimationFromUrl("https://assets7.lottiefiles.com/datafiles/F8yLwPvno9fP0Ag/data.json");
            animationView.setColorFilter(activity.getResources().getColor(R.color.successColor));
        }else{
            ctvbTitle.setText("Payment Failed ");
            ctvbTitle.setTextColor(activity.getResources().getColor(R.color.errorColor));
            paymsg.setText("Oops! Something went wrong !\n Please Try Again !");
            animationView.setAnimationFromUrl("https://assets9.lottiefiles.com/packages/lf20_QwXBUo.json");
            animationView.setColorFilter(activity.getResources().getColor(R.color.errorColor));

        }

        txnid.setText("Transaction Id : "+orderId);
        txnamnt.setText("Transaction Amount : "+refillAmount);
      //  int addpoint=Integer.parseInt(minRefill);
        uemail.setText("Mobile  : "+GlobalVariables.profileuser.getMobile());
        // tvYesPass.setText("YES");
        builder.setView(dialogView);
        builder.setCancelable(true);
        final androidx.appcompat.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        tvYesPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                activity.finish();
               // startActivity(activity.getIntent());
            }
        });


    }

    public void ifvolleyfail(){
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(activity);
        alertDialogBuilder.setTitle("Something Went Wrong");
        alertDialogBuilder.setMessage("Please Try With Active Internet ");
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
        alertDialogBuilder.setPositiveButton(activity.getResources().getString(R.string.ok_message),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        activity.finish();
                      //  method.loadingDialog.dismiss();
                       activity. finishAffinity();
                    }
                });

        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    public static void alertBox(String type,String title ,String message,Activity activity) {

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity);
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.alert_dialog, viewGroup, false);
        builder.setView(dialogView);
        builder.setCancelable(true);
        final androidx.appcompat.app.AlertDialog alertDialog = builder.create();
        ImageView iconImage=dialogView.findViewById(R.id.iconImage);
        TextView alertTitle=dialogView.findViewById(R.id.alertTitle);
        TextView alertMsg=dialogView.findViewById(R.id.alertMsg);

        alertTitle.setText(title);
        alertMsg.setText(message);
        if(type.equals("1")){
            iconImage.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_check));
           // iconImage.getImageTintBlendMode(R.color.black);
            iconImage.setBackground(activity.getResources().getDrawable(R.drawable.round_icon_bg));
        }else{
            iconImage.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_baseline));
            //iconImage.setBackgroundColor(activity.getResources().getColor(R.color.errorColor));
            iconImage.setBackground(activity.getResources().getDrawable(R.drawable.round_icon_bg));
        }




        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

        dialogView.findViewById(R.id.alertBtn).setOnClickListener(view1 -> {
            alertDialog.dismiss();
        });



    }


}