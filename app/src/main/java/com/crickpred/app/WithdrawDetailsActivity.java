package com.crickpred.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.crickpred.app.Util.Ex;
import com.crickpred.app.Util.GlobalVariables;
import com.crickpred.app.Util.Method;
import com.crickpred.app.Util.PrefManager;
import com.crickpred.app.Util.RestAPI;
import com.crickpred.app.databinding.ActivityWithdrawDetailsBinding;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WithdrawDetailsActivity extends AppCompatActivity {
    private static final String TAG = "KINGSN";
    ActivityWithdrawDetailsBinding binding;
    private SharedPreferences preferences,sharedPreferences;
    private SharedPreferences.Editor editor;
    private PrefManager prefManager;
    private Method method;
    private HashMap<String, String> params = new HashMap<>();
    AlertDialog alertDialog;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_withdraw_details);
        setSupportActionBar(binding.toolbarWithdrawDetails);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        prefManager = new PrefManager(this);
        Log.d(TAG, "onCreateView: "+ prefManager.getValue("OnesignalappKey"));
        preferences = WithdrawDetailsActivity.this.getSharedPreferences(GlobalVariables.ADMIN_PREF, MODE_PRIVATE);
        editor = preferences.edit();
        method=new Method(this);
        setUiDetails();

        binding.toolbarWithdrawDetails.setNavigationOnClickListener(view -> {
            super.onBackPressed();
        });

        binding.btnAddNewBank.setOnClickListener(view -> {
            setupAddBankDialog();
        });

        binding.btnAddPaytmNo.setOnClickListener(view -> {
            setupAddPaytmWalletDialog();
        });

        binding.btnAddGPay.setOnClickListener(view -> {
            setupGooglePayDialog();
        });

        binding.btnAddPhonePe.setOnClickListener(view -> {
            setupPhonePeDialog();
        });

        binding.btnAddPaytmUPI.setOnClickListener(view -> {
            setupPaytmUpiDialog();
        });

        binding.btnAddUPI.setOnClickListener(view -> {
            setupUPIDialog();
        });

    }

    private void setUiDetails() {
        binding.userNameTv.setText(GlobalVariables.profileuser.getName());
        binding.NumberDetailTv.setText(GlobalVariables.profileuser.getMobile());

        if(Objects.equals(GlobalVariables.settings.getBankDetailsStatus(), "0")){
            binding.btnAddNewBank.setText(getResources().getString(R.string.add_new));

        }else{
            binding.btnAddNewBank.setWidth(110);
            binding.btnAddNewBank.setText(getResources().getString(R.string.edit));
        }
        if(Objects.equals(GlobalVariables.settings.getPaytmNo(), "")){
            binding.btnAddPaytmNo.setText(getResources().getString(R.string.add_new));

        }else{
            binding.btnAddPaytmNo.setWidth(110);
            binding.btnAddPaytmNo.setText(getResources().getString(R.string.edit));
        }
        if(Objects.equals(GlobalVariables.settings.getgPayNo(), "")){
            binding.btnAddGPay.setText(getResources().getString(R.string.add_new));

        }else{
            binding.btnAddGPay.setWidth(110);
            binding.btnAddGPay.setText(getResources().getString(R.string.edit));
        }
        if(Objects.equals(GlobalVariables.settings.getPhonePayNo(), "")){
            binding.btnAddPhonePe.setText(getResources().getString(R.string.add_new));

        }else{
            binding.btnAddPhonePe.setWidth(110);
            binding.btnAddPhonePe.setText(getResources().getString(R.string.edit));
        }
        if(Objects.equals(GlobalVariables.settings.getPatymUpiNo(), "")){
            binding.btnAddPaytmUPI.setText(getResources().getString(R.string.add_new));

        }else{
            binding.btnAddPaytmUPI.setWidth(110);
            binding.btnAddPaytmUPI.setText(getResources().getString(R.string.edit));
        }
        if(Objects.equals(GlobalVariables.settings.getUpiNo(), "")){
            binding.btnAddUPI.setText(getResources().getString(R.string.add_new));

        }else{
           // binding.btnAddUPI.setMinWidth(110);
            binding.btnAddUPI.setWidth(110);
            binding.btnAddUPI.setText(getResources().getString(R.string.edit));
        }
    }

    void setupAddBankDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(WithdrawDetailsActivity.this).inflate(R.layout.dialog_add_bank_account, findViewById(R.id.addBankView));
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        EditText BankName = view.findViewById(R.id.autoCompleteTvBankName);
        EditText etAccountNo = view.findViewById(R.id.accountNoEt);
        EditText etIfse = view.findViewById(R.id.ifscEt);
        EditText etAccHolderName = view.findViewById(R.id.accountHolderNameEt);
        BankName.setText(GlobalVariables.settings.getBankName());
        etAccHolderName.setText(GlobalVariables.settings.getBankAccHolderName());
        etIfse.setText(GlobalVariables.settings.getBankIfsc());
        etAccountNo.setText(GlobalVariables.settings.getBankAccNo());

        view.findViewById(R.id.btnSubmitBankAcc).setOnClickListener(view1 -> {
            if(BankName.getText().toString().equals("")){
                BankName.setError("Field Is Required");
            }if(etAccountNo.getText().toString().equals("")){
                etAccountNo.setError("Field Is Required");
            }if(etIfse.getText().toString().equals("")){
                etIfse.setError("Field Is Required");
            }if(etAccHolderName.getText().toString().equals("")){
                etAccHolderName.setError("Field Is Required");
            }else {

                params.put("kycType","bank");
                params.put("bankName",BankName.getText().toString());
                params.put("bankAccountNo",etAccountNo.getText().toString());
                params.put("bankIfse",etIfse.getText().toString());
                params.put("bankAccountNo",etAccountNo.getText().toString());
                params.put("bankeAccHolderName",etAccHolderName.getText().toString());
              //  Toast.makeText(WithdrawDetailsActivity.this, "Added Bank Account", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
                udateKyc();
            }
        });

        view.findViewById(R.id.btnDismissAddBank).setOnClickListener(view1 -> {
            alertDialog.dismiss();

        });

        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

    void setupAddPaytmWalletDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(WithdrawDetailsActivity.this).inflate(R.layout.dialog_add_paytm_wallet, findViewById(R.id.addPaytmView));
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        EditText etNamePaytm = view.findViewById(R.id.namePaytmEt);
        EditText etPaytmNo = view.findViewById(R.id.paytmNoEt);
        etNamePaytm.setText(GlobalVariables.settings.getPaytmName());
        etPaytmNo.setText(GlobalVariables.settings.getPaytmNo());

        view.findViewById(R.id.btnSubmitPaytmInfo).setOnClickListener(view1 -> {

            if(etNamePaytm.getText().toString().equals("")){
                etNamePaytm.setError("Field Is Required");
            }if(etPaytmNo.getText().toString().equals("")){
                etPaytmNo.setError("Field Is Required");
            }else {
                params.put("kycType","payTm");
                params.put("payTmName",etNamePaytm.getText().toString());
                params.put("payTmNo",etPaytmNo.getText().toString());
             //   Toast.makeText(WithdrawDetailsActivity.this, "Paytm no submitted!", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
                udateKyc();
            }
        });

        view.findViewById(R.id.btnDismissPaytm).setOnClickListener(view1 -> {
            alertDialog.dismiss();

        });

        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

    void setupGooglePayDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(WithdrawDetailsActivity.this).inflate(R.layout.dialog_add_google_pay, findViewById(R.id.addGooglePayView));
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        EditText etNameGpay = view.findViewById(R.id.nameGooglePayEt);
        EditText etGpayNo = view.findViewById(R.id.googlePayNoEt);

        etNameGpay.setText(GlobalVariables.settings.getgPayName());
        etGpayNo.setText(GlobalVariables.settings.getgPayNo());

        view.findViewById(R.id.btnSubmitGpayInfo).setOnClickListener(view1 -> {

            if(etNameGpay.getText().toString().equals("")){
                etNameGpay.setError("Field Is Required");
            }if(etGpayNo.getText().toString().equals("")){
                etGpayNo.setError("Field Is Required");
            }else {
                params.put("kycType","GPeUpi");
                params.put("GPeName",etNameGpay.getText().toString());
                params.put("GPeNo",etGpayNo.getText().toString());
             //   Toast.makeText(WithdrawDetailsActivity.this, "Google Pay no submitted!", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
                udateKyc();
            }
        });

        view.findViewById(R.id.btnDismissGpay).setOnClickListener(view1 -> {
            alertDialog.dismiss();

        });

        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

    void setupPhonePeDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(WithdrawDetailsActivity.this).inflate(R.layout.dialog_add_phonepe, findViewById(R.id.addPhonePeView));
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        EditText etNamePhonePe = view.findViewById(R.id.namePhonePeEt);
        EditText etphonPeNo = view.findViewById(R.id.phonPeNoEt);

        etNamePhonePe.setText(GlobalVariables.settings.getPhonePayName());
        etphonPeNo.setText(GlobalVariables.settings.getPhonePayNo());

        view.findViewById(R.id.btnSubmitPhonepe).setOnClickListener(view1 -> {

            if(etNamePhonePe.getText().toString().equals("")){
                etNamePhonePe.setError("Field Is Required");
            }if(etphonPeNo.getText().toString().equals("")){
                etphonPeNo.setError("Field Is Required");
            }else {

                params.put("kycType","phonePeUpi");
                params.put("phonePeName",etNamePhonePe.getText().toString());
                params.put("phonePeNo",etphonPeNo.getText().toString());
            //    Toast.makeText(WithdrawDetailsActivity.this, "PhonePe no submitted!", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
                udateKyc();
            }
        });

        view.findViewById(R.id.btnDismissPhonePe).setOnClickListener(view1 -> {
            alertDialog.dismiss();

        });

        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

    void setupPaytmUpiDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view2 = LayoutInflater.from(WithdrawDetailsActivity.this).inflate(R.layout.dialog_add_paytm_upi, findViewById(R.id.addPaytmUpiView));
        builder.setView(view2);
        final AlertDialog alertDialog = builder.create();
        EditText etNamePaytmUpi = view2.findViewById(R.id.namePaytmUpiEt);
        EditText etPaytmUpiNo = view2.findViewById(R.id.paytmUpiNoEt);
        etNamePaytmUpi.setText(GlobalVariables.settings.getPatymUpiName());
        etPaytmUpiNo.setText(GlobalVariables.settings.getPatymUpiNo());


        view2.findViewById(R.id.btnSubmitPaytmUpi).setOnClickListener(view1 -> {

            if(etNamePaytmUpi.getText().toString().equals("")){
                etNamePaytmUpi.setError("Field Is Required");
            }if(etPaytmUpiNo.getText().toString().equals("")){
                etPaytmUpiNo.setError("Field Is Required");
            }else {
                params.put("kycType","paytmUpi");
                params.put("paytmUpiName",etNamePaytmUpi.getText().toString());
                params.put("paytmUpiNo",etPaytmUpiNo.getText().toString());

               // Toast.makeText(WithdrawDetailsActivity.this, "Paytm Upi submitted!", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
                udateKyc();
            }
        });

        view2.findViewById(R.id.btnDismissPaytmUpi).setOnClickListener(view1 -> {
            alertDialog.dismiss();

        });

        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

    void setupUPIDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(WithdrawDetailsActivity.this).inflate(R.layout.dialog_add_upi, findViewById(R.id.addUpiView));
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        EditText etNameUpi = view.findViewById(R.id.nameUpiEt);
        EditText etUpiId = view.findViewById(R.id.upiIdEt);

        etNameUpi.setText(GlobalVariables.settings.getUpiName());
        etUpiId.setText(GlobalVariables.settings.getUpiNo());

        view.findViewById(R.id.btnSubmitUpi).setOnClickListener(view1 -> {

            if(etNameUpi.getText().toString().equals("")){
                etNameUpi.setError("Field Is Required");
            }if(etUpiId.getText().toString().equals("")){
                etUpiId.setError("Field Is Required");
            }else {
                params.put("kycType","Upi");
                params.put("upiName",etNameUpi.getText().toString());
                params.put("upiNo",etUpiId.getText().toString());

                udateKyc();
             //   Toast.makeText(WithdrawDetailsActivity.this, "UPI submitted!", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();

            }
        });

        view.findViewById(R.id.btnDismissUpi).setOnClickListener(view1 -> {
            alertDialog.dismiss();

        });

        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }


    private void udateKyc() {

        method.loadingDialogg(WithdrawDetailsActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, RestAPI.KYC_UPDATE,
                new com.android.volley.Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            // Toast.makeText(LoginActivity.this, "RESPONSE: " + response, Toast.LENGTH_SHORT).show();
                            try {
                                System.out.println(response);
                                JSONObject jsonObject = new JSONObject(response);

                                JSONObject jsonObject1 = jsonObject.getJSONObject(GlobalVariables.AppSid);

                                    String success = jsonObject1.getString("success");
                                    String title = jsonObject1.getString("title");
                                    String message = jsonObject1.getString("msg");


                                    if (success.equals("1")) {
                                    //Toast.makeText(LoginActivity.this, Constant.userPhone, Toast.LENGTH_LONG).show();
                                    // LoadSettings();
                                    method.loadingDialog.dismiss();

                                    Method.alertBox("1",title,message,WithdrawDetailsActivity.this);

                                } else {
                                    // mobileNumber=binding.countryCodePicker.getSelectedCountryCode()+binding.phone.getText().toString();

                                    method.loadingDialog.dismiss();

                                    Method.alertBox("0",title,message,WithdrawDetailsActivity.this);
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
                Ex.AlertBox("Internet Connection Not Available",WithdrawDetailsActivity.this);

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                /* Map<String, String> params = new HashMap<>();
                params.put("users_login", "KINGSN");
                params.put("phone",binding.phone.getText().toString() );
                params.put("device_id",method.getDeviceId(getApplicationContext()));*/
                params.put("mobile",GlobalVariables.profileuser.getMobile());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(WithdrawDetailsActivity.this);
        requestQueue.add(stringRequest);
        Log.d(TAG, "login:stringrequest "+stringRequest);


    }
}