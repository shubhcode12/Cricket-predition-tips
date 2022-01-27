package com.crickpred.app;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.crickpred.app.Models.Profileuser;
import com.crickpred.app.Models.Settings;
import com.crickpred.app.Util.Ex;
import com.crickpred.app.Util.GlobalVariables;
import com.crickpred.app.Util.Method;
import com.crickpred.app.Util.RestAPI;
import com.crickpred.app.databinding.ActivityMainBinding;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "KINGSN";
    ActivityMainBinding binding;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navViewSide;
    DrawerLayout drawerLayout;
    NavController navController;
    private SharedPreferences preferences, preferences1;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Method method;
    private TextView userPhoneTv,walletbal;


    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toolbar = findViewById(R.id.toolbarMain);
        drawerLayout = findViewById(R.id.drawer_layout);
        navViewSide = findViewById(R.id.sideNav_view);

        preferences = getSharedPreferences(GlobalVariables.ADMIN_PREF, MODE_PRIVATE);
        editor = preferences.edit();
        method=new Method(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_1);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_offers, R.id.navigation_passbook, R.id.navigation_ids)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        setupNavigationDrawer();
        getAllData();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        userPhoneTv=findViewById(R.id.userPhoneTv);
        walletbal=findViewById(R.id.walletbal);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_withdrawDetails:
                startActivity(new Intent(MainActivity.this, WithdrawDetailsActivity.class));
                // navController.navigate(R.id.navigation_ids);
               // Toast.makeText(this, "" + item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_notification:
                startActivity(new Intent(MainActivity.this, NotificationsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }


    private void setupNavigationDrawer() {
        navViewSide.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("WrongConstant")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_profile:
                        drawerLayout.closeDrawer(Gravity.START, false);
                        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                        break;
                    case R.id.drawer_withdrawalDetails:
                        drawerLayout.closeDrawer(Gravity.START, false);
                        startActivity(new Intent(MainActivity.this, WithdrawDetailsActivity.class));
                        break;
                    case R.id.drawer_create_id:
                        drawerLayout.closeDrawer(Gravity.START, false);
                        navController.navigate(R.id.navigation_ids);
                        break;
                    case R.id.drawer_refer:
                        drawerLayout.closeDrawer(Gravity.START, false);
                        startActivity(new Intent(MainActivity.this, ReferAndEarnActivity.class));
                        break;
                    case R.id.drawer_terms:
                        drawerLayout.closeDrawer(Gravity.START, false);
                        startActivity(new Intent(MainActivity.this, TermsActivity.class));
                        break;
                    case R.id.drawer_help:
                        drawerLayout.closeDrawer(Gravity.START, false);
                        startActivity(new Intent(MainActivity.this, HelpActivity.class));
                        break;

                    case R.id.drawer_logout:
                        drawerLayout.closeDrawer(Gravity.START, false);
                        setupLogoutDialog();
                        break;

                }
                return false;
            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    private void getAllData() {
        method.loadingDialogg(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, RestAPI.API_Settings,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText(LoginActivity.this, "RESPONSE: " + response, Toast.LENGTH_SHORT).show();
                        try {
                            //  System.out.println(response);
                            JSONObject jsonObject = new JSONObject(response);

                            JSONObject jsonObject1 = jsonObject.getJSONObject(GlobalVariables.AppSid);
                            JSONArray jsonArray2 = jsonObject1.getJSONArray("Results");
                            Log.d(TAG, "onResponse: "+jsonArray2);
                            for (int i = 0; i < jsonArray2.length(); i++) {

                                JSONObject object = jsonArray2.getJSONObject(i);
                                String success = jsonObject1.getString("success");
                                if (success.equals("1")) {
                                    // progressDialog.dismiss();
                                    //String user_id = object.getString("unique_id");

                                    String id = object.getString("id");
                                    String mobile = object.getString("mobile");
                                    String password = object.getString("password");
                                    String name = object.getString("name");
                                    Log.d(TAG, "onResponse: "+name);
                                    String city = object.getString("city");
                                    String email = object.getString("email");
                                    String wallet = object.getString("wallet");
                                    String totalPaid = object.getString("total_paid");
                                    String totalRedeemed = object.getString("total_redeemed");
                                    String userReferalCode = object.getString("user_referal_code");
                                    String refferedBy = object.getString("reffered_by");
                                    String refferedPaid = object.getString("reffered_paid");
                                    String totalReferals = object.getString("total_referals");
                                    String allow = object.getString("allow");
                                    String deviceId = object.getString("device_id");
                                    String profilePic = object.getString("profile_pic");
                                    String activeDate = object.getString("active_date");
                                    String onesignalPlayerid = object.getString("onesignal_playerid");
                                    String onesignalPushtoken = object.getString("onesignal_pushtoken");
                                    String joiningTime = object.getString("joining_time");

                                    //constant.sharedEditor.putBoolean(constant.isLogin, true);

                                    Profileuser profileuser = new Profileuser(id, mobile, password, name, city, email, wallet, totalPaid,
                                            totalRedeemed, userReferalCode, refferedBy, refferedPaid, totalReferals, allow,
                                            deviceId, profilePic, activeDate, onesignalPlayerid, onesignalPushtoken, joiningTime);

                                    String appName = object.getString("app_name");
                                    String appLogo = object.getString("app_logo");
                                    String appDescription = object.getString("app_description");
                                    String appVersion = object.getString("app_version");
                                    String appAuthor = object.getString("app_author");
                                    String appContact = object.getString("app_contact");
                                    String appEmail = object.getString("app_email");
                                    String appWebsite = object.getString("app_website");
                                    String appDevelopedBy = object.getString("app_developed_by");
                                    String redeemCurrency = object.getString("redeem_currency");
                                    String homeBannerimg1Enabled = object.getString("bannerimg1_enabled");
                                    String homeBannerimg1 = object.getString("home_bannerimg1");
                                    String homeBannerimg2Enabled = object.getString("home_bannerimg2_enabled");
                                    String homeBannerimg2 = object.getString("home_bannerimg2");
                                    String homeBannerimg3 = object.getString("home_bannerimg3");
                                    String onesignalappId = object.getString("onesignalapp_id");
                                    String onesignalappKey = object.getString("onesignalapp_key");
                                    String referTxt = object.getString("refer_txt");
                                    String image = object.getString("image");
                                    String joiningBonus = object.getString("joining_bonus");
                                    String perRefer = object.getString("per_refer");
                                    String minDepositcoin = object.getString("minDepositcoin");
                                    String hourlyQuizCoin = object.getString("hourly_quiz_coin");
                                    String mathsQuizCoin = object.getString("maths_quiz_coin");
                                    String maxmMathsQuestn = object.getString("maxm_maths_questn");
                                    String hourlySpinLimit = object.getString("hourly_spin_limit");
                                    String hourlyMathsquizLimit = object.getString("hourly_mathsquiz_limit");
                                    String mathsQuizUnlockMin = object.getString("mathsQuiz_unlockMin");
                                    String perNewsCoin = object.getString("per_news_coin");
                                    String minimumWidthrawal = object.getString("minimum_widthrawal");
                                    String minRedeemAmount = object.getString("min_redeem_amount");
                                    String telegramlink = object.getString("telegramlink");
                                    String youtubeLink = object.getString("youtube_link");
                                    String facebookPage = object.getString("facebook_page");
                                    String newVersion = object.getString("new_version");
                                    String updateLink = object.getString("update_link");
                                    String adminMsg = object.getString("admin_msg");
                                    String joinGroup = object.getString("join_group");
                                    String appPromo1 = object.getString("app_promo1");
                                    String appPromo2 = object.getString("app_promo2");
                                    String date = object.getString("date");
                                    String kid  =object.getString("kid");
                                    String kmobile  =object.getString("kmobile");
                                    String userName  =object.getString("userName");
                                    String bankDetailsStatus  =object.getString("bankDetailsStatus");
                                    String bankName  =object.getString("bankName");
                                    String bankAccNo  =object.getString("bankAccNo");
                                    String bankIfsc  =object.getString("bankIfsc");
                                    String bankAccHolderName  =object.getString("BankAccHolderName");
                                    String paytmName  =object.getString("paytmName");
                                    String paytmNo  =object.getString("paytmNo");
                                    String gPayName  =object.getString("gPayName");
                                    String gPayNo  =object.getString("gPayNo");
                                    String phonePayName  =object.getString("phonePayName");
                                    String phonePayNo  =object.getString("phonePayNo");
                                    String patymUpiName  =object.getString("patymUpiName");
                                    String patymUpiNo  =object.getString("patymUpiNo");
                                    String upiName  =object.getString("upiName");
                                    String upiNo  =object.getString("upiNo");
                                    String kcreationDate  =object.getString("kcreationDate");
                                    String kupdatedDate  =object.getString("kupdatedDate");

                        Settings  settings=new Settings( appName,  appLogo,  appDescription,  appVersion,  appAuthor,  appContact,
                                appEmail,  appWebsite,  appDevelopedBy,  redeemCurrency, homeBannerimg1Enabled, homeBannerimg1,
                                homeBannerimg2Enabled , homeBannerimg2, homeBannerimg3,  onesignalappId,  onesignalappKey,  referTxt,
                                image,  joiningBonus,  perRefer,  minDepositcoin,  hourlyQuizCoin,  mathsQuizCoin,  maxmMathsQuestn,
                                hourlySpinLimit,  hourlyMathsquizLimit,  mathsQuizUnlockMin,  perNewsCoin,  minimumWidthrawal,
                                minRedeemAmount,  telegramlink,  youtubeLink,  facebookPage,  newVersion,  updateLink,  adminMsg,
                                joinGroup,  appPromo1,  appPromo2,  date, kid, kmobile, userName, bankDetailsStatus, bankName, bankAccNo,
                                bankIfsc, bankAccHolderName, paytmName, paytmNo, gPayName, gPayNo,
                                phonePayName, phonePayNo, patymUpiName, patymUpiNo, upiName, upiNo,
                                kcreationDate, kupdatedDate);

                                    GlobalVariables.profileuser=profileuser;
                                    GlobalVariables.settings=settings;
                                    editor.apply();
                                    method.loadingDialog.dismiss();

                                    editor.putBoolean(String.valueOf(GlobalVariables.USER_IS_LOGIN),true);
                                    editor.putString(GlobalVariables.USER_MOBILE,mobile);
                                    editor.apply();
                                   /* Intent intent = new Intent(LoginPageActivity.this, LoginPageActivity.class);
                                   // intent.putExtra ("mobile",binding.phone.getText().toString() );
                                    startActivity(intent);*/
                                    setData();

                                } else {
                                    // mobileNumber=binding.countryCodePicker.getSelectedCountryCode()+binding.phone.getText().toString();

                                    method.loadingDialog.dismiss();
                                    //sendVerificationCodeToUser(mobileNumber);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                                    alertDialogBuilder.setTitle(jsonObject.getString("title"));
                                    alertDialogBuilder.setMessage(jsonObject.getString("msg"));
                                    alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
                                    alertDialogBuilder.setPositiveButton(MainActivity.this.getResources().getString(R.string.ok_message),
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface arg0, int arg1) {
                                                    method.loadingDialog.dismiss();
                                                    finish();
                                                    startActivity(new Intent(MainActivity.this, LoginPageActivity.class));
                                                    //Log.d("Response",msg);
                                                    // finishAffinity();

                                                }
                                            });

                                    AlertDialog alertDialog = alertDialogBuilder.create();
                                    alertDialog.show();
                                    // Toast.makeText(LoginPageActivity.this, object.getString("msg"), Toast.LENGTH_LONG).show();
                                }

                            }

                            // progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            //Toast.makeText(LoginActivity.this, "Error: " + e.getMessage(),
                            // Toast.LENGTH_SHORT).show();
                        }

                    }

                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                method.loadingDialog.dismiss();
                method.loadingDialog.dismiss();
                Log.e("Error", "" + error.getMessage());
                Ex.AlertBox("Internet Connection Not Available",MainActivity.this);

            }
        }) {
                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<>();
                    params.put("users_login", "KINGSN");
                    params.put("mobile",preferences.getString(GlobalVariables.USER_MOBILE,"") );
                    params.put("device_id",method.getDeviceId(getApplicationContext()));

                    return params;
                }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
        Log.d(TAG, "login:stringrequest "+stringRequest);


    }

    @SuppressLint("SetTextI18n")
    private void setData() {

        userPhoneTv.setText(GlobalVariables.profileuser.getMobile());
        walletbal.setText(GlobalVariables.profileuser.getWallet());
        // userPhoneTv.setText("8251941210");

        Glide.with(MainActivity.this)
                .load(GlobalVariables.profileuser.getProfilePic())
                .placeholder(R.drawable.dummyuser_image)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into((ImageView) binding.sideNavView.findViewById(R.id.profileImageView));


    }

    public void setupLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_logout, findViewById(R.id.logoutView));
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.btnLogoutDialog).setOnClickListener(view1 -> {
            Toast.makeText(MainActivity.this, "Logging out!", Toast.LENGTH_SHORT).show();
            preferences.edit().clear().apply();
            finish();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));

        });
        view.findViewById(R.id.btnCancelDialog).setOnClickListener(view1 -> {
            alertDialog.dismiss();
        });
        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }


}