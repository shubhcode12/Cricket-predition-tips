package com.crickpred.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.crickpred.app.Models.Profileuser;
import com.crickpred.app.Util.Ex;
import com.crickpred.app.Util.GlobalVariables;
import com.crickpred.app.Util.Method;
import com.crickpred.app.Util.RestAPI;
import com.crickpred.app.databinding.ActivityLoginPageBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPageActivity extends AppCompatActivity {

    private static final String TAG = "KINGSN";
    ActivityLoginPageBinding binding;
    private String mobileNumber;
    private Method method;
    private Profileuser profileuser;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_page);
        Bundle extras=getIntent().getExtras();
        assert extras != null;
        mobileNumber=extras.getString("mobile"); //if data you are sending is String.
        binding.loginPagePhoneET.setText(mobileNumber);
        method=new Method(LoginPageActivity.this);
        sharedPreferences=getSharedPreferences(GlobalVariables.ADMIN_PREF, MODE_PRIVATE);
        editor = sharedPreferences.edit();
       // editor = sharedPreferences.edit();
        binding.btnLogin.setOnClickListener(view -> {
            //startActivity(new Intent(LoginPageActivity.this, MainActivity.class));
            if (binding.passPageEt.getText().toString().isEmpty()) {
                binding.passPageEt.setError("Please Enter Password");
                return;
            }else {
                login();
                method.loadingDialogg(LoginPageActivity.this);
            }


        });
        binding.btnLoginWithOtp.setOnClickListener(view -> {
            //startActivity(new Intent(LoginPageActivity.this, OtpLoginActivity.class));
           // sendVerificationCodeToUser(mobileNumber);
        });
    }

    private void login() {
        method.loadingDialogg(LoginPageActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, RestAPI.API_Login,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText(LoginActivity.this, "RESPONSE: " + response, Toast.LENGTH_SHORT).show();
                        try {
                            System.out.println(response);
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

                                    profileuser=new Profileuser( id,  mobile,  password,  name,  city,  email,  wallet,  totalPaid,
                                            totalRedeemed,  userReferalCode,  refferedBy,  refferedPaid,  totalReferals,  allow,
                                            deviceId,  profilePic,  activeDate,  onesignalPlayerid,  onesignalPushtoken,  joiningTime);

                                    GlobalVariables.profileuser=profileuser;
                                    editor.apply();
                                    method.loadingDialog.dismiss();

                                    editor.putBoolean(String.valueOf(GlobalVariables.USER_IS_LOGIN),true);
                                    editor.putString(GlobalVariables.USER_MOBILE,mobile);
                                    editor.apply();
                                   /* Intent intent = new Intent(LoginPageActivity.this, LoginPageActivity.class);
                                   // intent.putExtra ("mobile",binding.phone.getText().toString() );
                                    startActivity(intent);*/
                                    startActivity(new Intent(LoginPageActivity.this, MainActivity.class));


                                } else {
                                    // mobileNumber=binding.countryCodePicker.getSelectedCountryCode()+binding.phone.getText().toString();

                                    method.loadingDialog.dismiss();
                                    //sendVerificationCodeToUser(mobileNumber);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginPageActivity.this);
                                    alertDialogBuilder.setTitle(jsonObject.getString("title"));
                                    alertDialogBuilder.setMessage(jsonObject.getString("msg"));
                                    alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
                                    alertDialogBuilder.setPositiveButton(LoginPageActivity.this.getResources().getString(R.string.ok_message),
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface arg0, int arg1) {
                                                    method.loadingDialog.dismiss();
                                                    finish();
                                                    startActivity(new Intent(LoginPageActivity.this, LoginPageActivity.class));
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
                Ex.AlertBox("Internet Connection Not Available",LoginPageActivity.this);

            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("users_login", "KINGSN");
                params.put("phone",mobileNumber );
                params.put("password",binding.passPageEt.getText().toString() );
                params.put("device_id",method.getDeviceId(getApplicationContext()));

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(LoginPageActivity.this);
        requestQueue.add(stringRequest);
        Log.d(TAG, "login:stringrequest "+stringRequest);


    }



}