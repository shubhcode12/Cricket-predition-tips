package com.crickpred.app.ui.home;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.crickpred.app.DepositActivity;
import com.crickpred.app.LoginPageActivity;
import com.crickpred.app.Models.Profileuser;
import com.crickpred.app.Models.Settings;
import com.crickpred.app.R;
import com.crickpred.app.Util.Ex;
import com.crickpred.app.Util.GlobalVariables;
import com.crickpred.app.Util.Method;
import com.crickpred.app.Util.PrefManager;
import com.crickpred.app.Util.RestAPI;
import com.crickpred.app.databinding.FragmentHomeBinding;
import com.crickpred.app.ui.ids.IdsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class HomeFragment extends Fragment {

    private static final String TAG ="KINGSN" ;
    private FragmentHomeBinding binding;
    private PrefManager prefManager;
    private SharedPreferences preferences,sharedPreferences;
    private SharedPreferences.Editor editor;
    private Method method;
    @SuppressLint("CommitPrefEdits")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        prefManager = new PrefManager(getActivity());
        Log.d(TAG, "onCreateView: "+ prefManager.getValue("OnesignalappKey"));
        preferences = this.requireActivity().getSharedPreferences(GlobalVariables.ADMIN_PREF, MODE_PRIVATE);
        editor = preferences.edit();
        method=new Method(getActivity());
        binding.mainl.setVisibility(View.GONE);

        getAllData();
        // setData();
        return root;

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setData() {


        if((GlobalVariables.settings.getHomeBannerimg1Enabled().toLowerCase().equals("true"))&&
                (!GlobalVariables.settings.getHomeBannerimg1().equals(""))){
            Glide.with(HomeFragment.this)
                    .load(GlobalVariables.settings.getHomeBannerimg1())
                    .placeholder(R.drawable.slider1)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into((ImageView) binding.cardSlider1img);
            binding.cardSlider1img.setVisibility(View.VISIBLE);
        }else{
            binding.cardSlider1img.setVisibility(View.GONE);
        }
        if((GlobalVariables.settings.getHomeBannerimg2Enabled().toLowerCase().equals("true"))&&
                (!GlobalVariables.settings.getHomeBannerimg2().equals(""))){
            Glide.with(HomeFragment.this)
                    .load(GlobalVariables.settings.getHomeBannerimg2())
                    .placeholder(R.drawable.slider1)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into((ImageView) binding.cardSlider2img);
            binding.cardSlider2img.setVisibility(View.VISIBLE);
        }else{
            binding.cardSlider2img.setVisibility(View.GONE);
        }

        binding.helplineTv.setText(GlobalVariables.settings.getAppContact());
        binding.walletbal.setText(GlobalVariables.profileuser.getWallet());
        binding.mainl.setVisibility(View.VISIBLE);

        binding.btnCreateId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(new IdsFragment());


            }
        });

        binding.depositbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), DepositActivity.class);
                intent.putExtra("screenType",GlobalVariables.Deposit);
                intent.putExtra("userName",GlobalVariables.profileuser.getName());
                intent.putExtra("mobile", GlobalVariables.profileuser.getMobile());
                intent.putExtra("wallet_bal",GlobalVariables.profileuser.getWallet());
                requireActivity().startActivity(intent);

            }
        });

        binding.widthrawbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), DepositActivity.class));

                Intent intent = new Intent(requireActivity(), DepositActivity.class);
                intent.putExtra("screenType",GlobalVariables.Withdraw);
                intent.putExtra("userName",GlobalVariables.profileuser.getName());
                intent.putExtra("mobile", GlobalVariables.profileuser.getMobile());
                intent.putExtra("wallet_bal",GlobalVariables.profileuser.getWallet());
                requireActivity().startActivity(intent);


            }
        });

    }

    private void getAllData() {
        method.loadingDialogg(requireActivity());
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
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(requireActivity());
                                    alertDialogBuilder.setTitle(jsonObject.getString("title"));
                                    alertDialogBuilder.setMessage(jsonObject.getString("msg"));
                                    alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
                                    alertDialogBuilder.setPositiveButton(requireActivity().getResources().getString(R.string.ok_message),
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface arg0, int arg1) {
                                                    method.loadingDialog.dismiss();
                                                    // finish();
                                                    startActivity(new Intent(requireActivity(), LoginPageActivity.class));
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
                Ex.AlertBox("Internet Connection Not Available",requireActivity());

            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("users_login", "KINGSN");
                params.put("mobile",preferences.getString(GlobalVariables.USER_MOBILE,"") );
                params.put("device_id",method.getDeviceId(requireActivity()));

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(requireActivity());
        requestQueue.add(stringRequest);
        Log.d(TAG, "login:stringrequest "+stringRequest);


    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_activity_main, fragment,
                fragment.getClass().getSimpleName());
        transaction.commit();
    }
}