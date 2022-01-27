package com.crickpred.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;
import com.crickpred.app.Interface.ApiServices;
import com.crickpred.app.Models.AppSettings.AppSettings;
import com.crickpred.app.Models.AppSettings.Result;
import com.crickpred.app.Models.CheckUser.CheckUser;
import com.crickpred.app.Util.Ex;
import com.crickpred.app.Util.GlobalVariables;
import com.crickpred.app.Util.Method;
import com.crickpred.app.Util.PrefManager;
import com.crickpred.app.Util.RestAPI;
import com.crickpred.app.databinding.ActivityLoginBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.firebase.auth.PhoneAuthProvider.getCredential;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "KINGSN";
    ActivityLoginBinding binding;
    private PrefManager prefManager;
    private Method method;
    List<Result> BookList;
    private String countryCode;
    private FirebaseAuth auth, mAuth;
    String verificationcodebysystem;
    String codeBySystem, mobileNumber;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context mContext;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
        method = new Method(LoginActivity.this);
        auth = FirebaseAuth.getInstance();
        mAuth=FirebaseAuth.getInstance();
       // editor = preferences.edit();

        countryCode="+"+binding.countryCodePicker.getSelectedCountryCode();
        binding.countryCodePicker.setOnCountryChangeListener(() -> countryCode="+"+binding.countryCodePicker.getSelectedCountryCode());

        binding.btnLogin.setOnClickListener(view -> {
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            finish();
            if (binding.phone.getText().toString().isEmpty()){
                binding.phone.setError("Phone number is Mandatory");
                // Toast.makeText(RegisterActivity.this, "Mobile No. can not be Empty", Toast.LENGTH_SHORT).show();
                 return;
            } else if (binding.phone.getText().toString().length()!=10)
            {
                binding.phone.setError("Insert 10 Digit Phone Number");
                // Toast.makeText(RegisterActivity.this, "Input valid Mobile No.", Toast.LENGTH_SHORT).show();
            }
            else
            {
                mobileNumber=countryCode+""+binding.phone.getText().toString();
              //  mobileNumber="+"+binding.countryCodePicker.getSelectedCountryCode()+""+binding.phone.getText().toString();
                login();
                Log.d(TAG, "onCreate: "+method.getDeviceId(getApplicationContext()));
            }

        });


       binding.btnOTPSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userOtp= Objects.requireNonNull(binding.veriCodeEt.getText()).toString();


                if (userOtp.isEmpty() || userOtp.length() < 6) {
                    binding.veriCodeEt.setError("Wrong OTP...");
                    binding.veriCodeEt.requestFocus();
                    return;
                }else {
                    verifyCode(userOtp);
                    method.loadingDialogg(LoginActivity.this);
                }


                // Toast.makeText(RegisterActivity.this, userOtp+"success"+codeBySystem, Toast.LENGTH_SHORT).show();
                if (getCredential(codeBySystem,codeBySystem).equals(userOtp)){

                    binding.otpLayout.setVisibility(View.GONE);
                    binding.numberlayout.setVisibility(View.VISIBLE);
                    finish();
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    intent.putExtra ("mobile",mobileNumber);
                    startActivity(intent);
                }

                //signInWithPhoneAuthCredential(credential);

            }
        });

    }

    public void SignIn() {

        ApiServices settings1 = RestAPI.getApiService();
        Call<AppSettings> call = settings1.settings();
        //  Log.d(TAG, "SignIn: "+settings1);
        call.enqueue(new Callback<AppSettings>() {


            @Override
            public void onResponse(Call<AppSettings> call, Response<AppSettings> response) {
                Log.d(TAG, "onResponse: " + response);
                Toast.makeText(LoginActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                if (response.code() == 200) {
                    List<Result> resultList = null;
                    if (response.body() != null) {
                        resultList = response.body().getResult();
                    }

                    BookList = new ArrayList<>();
                    assert response.body() != null;
                    BookList = response.body().getResult();
                    Log.e("KINGSN", "BOOKSIZE" + BookList.size());
                    Log.e("KINGSN", "BOOKLIST_add" + resultList.get(0).getAddType());
                    prefManager.setValue(response.body().getResult().get(0).getOnesignalappKey(), response.body().getResult().get(0).getAppAuthor());
                    // BookList = (response.getJSONObject("data").toString());
                    //   Log.e("TAG", "response 33: "+new Gson().toJson(response.body()) );
                    Log.d(TAG, "onCreateView: " + prefManager.getValue("onesignalappKey"));
                    //  AppSettings spin = response.body();
                    // Result result= spin.setResult(spin.getResult());

                    Gson gson = new Gson();
                    String favData = gson.toJson(response.body());

                }
            }

            @Override
            public void onFailure(@NotNull Call<AppSettings> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage() + "error  " + t);
                //  Toast.makeText(LoginActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });
    }

    public void users_login() {

        ApiServices settings1 = RestAPI.getApiService();
        Call<CheckUser> call = settings1.check_user("8251941210", "5178");
        //  Log.d(TAG, "SignIn: "+settings1);
        call.enqueue(new Callback<CheckUser>() {


            @Override
            public void onResponse(Call<CheckUser> call, Response<CheckUser> response) {
                Log.d(TAG, "onResponse: " + response);
                Toast.makeText(LoginActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                if (response.code() == 200) {
                    List<com.crickpred.app.Models.CheckUser.Result> resultList = null;
                    if (response.body() != null) {
                        resultList = response.body().getResult();
                    }

                    BookList = new ArrayList<>();
                    assert response.body() != null;
                    //  BookList = response.body().getResult();
                    Log.e("KINGSN", "BOOKSIZE" + BookList.size());
                    Log.e("KINGSN", "BOOKLIST_add" + resultList.get(0).getName());
                    // prefManager.setValue(response.body().getResult().get(0).getOnesignalappKey(), response.body().getResult().get(0).getAppAuthor());
                    // BookList = (response.getJSONObject("data").toString());
                    Log.e("TAG", "response 33: " + new Gson().toJson(response.body()));
                    Log.d(TAG, "onCreateView: " + prefManager.getValue("onesignalappKey"));
                    //  AppSettings spin = response.body();
                    // Result result= spin.setResult(spin.getResult());

                    Gson gson = new Gson();
                    String favData = gson.toJson(response.body());

                    if (resultList.get(0).getSuccess().equals(String.valueOf(1))) {
                        startActivity(new Intent(LoginActivity.this, OtpLoginActivity.class));
                        finish();
                    }

                }
            }

            @Override
            public void onFailure(@NotNull Call<CheckUser> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage() + "error  " + t);
                //  Toast.makeText(LoginActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });
    }

    private void login() {
        method.loadingDialogg(LoginActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, RestAPI.CHECK_USER,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText(LoginActivity.this, "RESPONSE: " + response, Toast.LENGTH_SHORT).show();
                        try {
                            System.out.println(response);
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray jsonArray = jsonObject.getJSONArray(GlobalVariables.AppSid);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject object = jsonArray.getJSONObject(i);
                                String success = object.getString("success");

                                if (success.equals("1")) {
                                    // progressDialog.dismiss();
                                    //String user_id = object.getString("unique_id");
                                    String name = object.getString("name");
                                    String sendEmail = object.getString("email");
                                    String userPhone = object.getString("phone");
                                    String walletBal = object.getString("wallet");

                                    //Toast.makeText(LoginActivity.this, Constant.userPhone, Toast.LENGTH_LONG).show();
                                    // LoadSettings();
                                    method.loadingDialog.dismiss();
                                    Intent intent = new Intent(LoginActivity.this, LoginPageActivity.class);
                                    intent.putExtra ("mobile",binding.phone.getText().toString() );
                                    startActivity(intent);


                                } else {
                                   // mobileNumber=binding.countryCodePicker.getSelectedCountryCode()+binding.phone.getText().toString();

                                    method.loadingDialog.dismiss();
                                    sendVerificationCodeToUser(mobileNumber);
                                   /* AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                                    alertDialogBuilder.setTitle(object.getString("title"));
                                    alertDialogBuilder.setMessage(object.getString("msg"));
                                    alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
                                    alertDialogBuilder.setPositiveButton(LoginActivity.this.getResources().getString(R.string.ok_message),
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface arg0, int arg1) {
                                                    method.loadingDialog.dismiss();
                                                    startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                                                    //Log.d("Response",msg);
                                                    // finishAffinity();

                                                }
                                            });

                                    AlertDialog alertDialog = alertDialogBuilder.create();
                                    alertDialog.show();
                                    Toast.makeText(LoginActivity.this, object.getString("msg"), Toast.LENGTH_LONG).show();*/
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
                Ex.AlertBox("Internet Connection Not Available",LoginActivity.this);

            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("users_login", "KINGSN");
                params.put("phone",binding.phone.getText().toString() );
                params.put("device_id",method.getDeviceId(getApplicationContext()));

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(stringRequest);
        Log.d(TAG, "login:stringrequest "+stringRequest);


    }

    private void sendVerificationCodeToUser(String mobileNumber) {
        //loadingDialog.dismiss();
        method.loadingDialog.dismiss();

      /*  PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber( mobileNumber)       // Phone number to verify
                        .setTimeout(30L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);*/


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mobileNumber,        // Phone number to verify
                20,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,// Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codeBySystem = s;

                    verificationcodebysystem= s;
                    method.loadingDialogg(LoginActivity.this);
                    binding.numberlayout.setVisibility(View.GONE);
                    binding.otpLayout.setVisibility(View.VISIBLE);
                    binding.otpSentTv.setText("We have sent an OTP on "+mobileNumber);


                      method.showToasty(LoginActivity.this,"1","Otp Has Been Sent");
                   // Toasty.success(LoginActivity.this, "Password reset Code sent, please check", Toast.LENGTH_SHORT).show();
                    method.loadingDialog.dismiss();
                }

                @Override
                public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                    super.onCodeAutoRetrievalTimeOut(s);
                    method.loadingDialog.dismiss();
                   // onBackPressed();
                    //Toast.makeText(ForgotActivity.this, "Timeout", Toast.LENGTH_SHORT).show();
                   // method.showToasty(LoginActivity.this,"2","Otp Retrieval Times Upp..! Try Again");

                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    method.loadingDialogg(LoginActivity.this);
                    if (code != null) {
                        binding.veriCodeEt.setText(code);
                        verifyCode(code);
                       method.loadingDialog.dismiss();



                    }
                }


                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    method.loadingDialog.dismiss();
                  //  Toast.makeText(ForgotActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                   // Toast.makeText(ForgotActivity.this,"verification failed", Toast.LENGTH_SHORT).show();
                    method.showToasty(LoginActivity.this,"2","verification failed");
                    Log.w(TAG, "onVerificationFailed", e);

                    if (e instanceof FirebaseAuthInvalidCredentialsException) {
                        // Invalid request
                        Log.e("Error", "" +"Invalid request");
                    } else if (e instanceof FirebaseTooManyRequestsException) {
                        // The SMS quota for the project has been exceeded
                        Log.e("Error", "" +"The SMS quota for the project has been exceeded");
                    }

                    // Show a message and update the UI
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = getCredential(codeBySystem, code);
        signInWithPhoneAuthCredential(credential);

        method.loadingDialog.dismiss();


    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Verification completed successfully here Either
                            // store the data or do whatever desire
                            Log.d(TAG, "onComplete: mobile int"+mobileNumber);
                            method.loadingDialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                            intent.putExtra ("mobile",binding.phone.getText().toString() );
                            startActivity(intent);
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                method.loadingDialog.dismiss();
                                // Toast.makeText(RegisterActivity.this, "Verification Not Completed! Try again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }


}