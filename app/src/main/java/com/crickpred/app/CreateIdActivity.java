package com.crickpred.app;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.crickpred.app.Util.GlobalVariables;
import com.crickpred.app.Util.Method;
import com.crickpred.app.Util.PrefManager;
import com.crickpred.app.databinding.ActivityCreateIdBinding;

import java.util.Objects;

public class CreateIdActivity extends AppCompatActivity {
    private static final String TAG = "KINGSN";
    ActivityCreateIdBinding binding;
    String checksum;
    String orderId;
    String mobile,minRefill,createId,idUsername,idPassword;
    private Method method;

    public static final String WEBSITE = "DEFAULT";
    public static final String CHANNEL_ID = "WAP";
    public static final String INDUSTRY_TYPE_ID = "Retail";
    public static final String CALLBACK_URL = "https://pguat.paytm.com/paytmchecksum/paytmCallback.jsp";
    private SharedPreferences.Editor editor;
    private PrefManager prefManager;
    private SharedPreferences preferences,sharedPreferences;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_id);
        method= new Method(this);
        Log.d(TAG, "onCreate: "+getIntent().getExtras());
        createId = getIntent().getStringExtra("createId");
        String idImageUrl = getIntent().getStringExtra("idimageurl");
        String idName = getIntent().getStringExtra("idname");
        String idWebsite = getIntent().getStringExtra("idwebsite");
         minRefill = getIntent().getStringExtra("minRefill");
        String minWithdrawal = getIntent().getStringExtra("minWithdrawal");
        String minMaintainBal = getIntent().getStringExtra("minMaintainBal");
        String maxWithdrawal = getIntent().getStringExtra("maxWithdrawal");
        Log.d(TAG, "onCreate: "+createId);

        prefManager = new PrefManager(this);
        Log.d(TAG, "onCreateView: "+ prefManager.getValue("OnesignalappKey"));
        preferences = CreateIdActivity.this.getSharedPreferences(GlobalVariables.ADMIN_PREF, MODE_PRIVATE);
        editor = preferences.edit();
        if (idImageUrl!=null){
            Glide.with(getApplicationContext())
                    .load(idImageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.ivIdProfile);
        }

        binding.tvIdName.setText(idName);
        binding.tvIdWebsite.setText(idWebsite);
        binding.depositCoinsEt.setText(minRefill);
        binding.tvMinRefillLink.setText(minRefill);
        binding.tvMinWithdrawal.setText(minWithdrawal);
        binding.tvMinMaintainingBal.setText(minMaintainBal);
        binding.tvMaxWithdrawal.setText(maxWithdrawal);
        binding.btnCreateIDActivity.setText("Continue To Pay"+" ₹ "+binding.depositCoinsEt.getText().toString());

        binding.depositCoinsEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                binding.btnCreateIDActivity.setText("Continue To Pay"+" ₹ "+binding.depositCoinsEt.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.btnCreateIDActivity.setText("Continue To Pay"+" ₹ "+binding.depositCoinsEt.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.btnCreateIDActivity.setText("Continue To Pay"+" ₹ "+binding.depositCoinsEt.getText().toString());
            }
        });

        binding.btnCreateIDActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Objects.requireNonNull(binding.usernameEt.getText()).toString().equals("")){
                    binding.usernameEt.setError("Username is Mandatory");
                }else{
                   // setupPaymentDialog();
                    //GenerateChecksum();
                    idUsername = binding.usernameEt.getText().toString();
                    idPassword="";
//                    editor.putString(GlobalVariables.idUsername,idUsername);
//                    editor.putString(GlobalVariables.idPassword,idPassword);
                    GlobalVariables.idUsername=binding.usernameEt.getText().toString();
                    GlobalVariables.idPassword=binding.usernameEt.getText().toString();
                    editor.apply();
                    final String TxnType="0";
                    Log.d(TAG, "onClick: "+GlobalVariables.idUsername);
                   method.setupPaymentDialog(CreateIdActivity.this, minRefill,createId,TxnType);

                }
            }
        });



    }

  /* public void setupPaymentDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view2 = LayoutInflater.from(CreateIdActivity.this).inflate(R.layout.dialog_select_payment_system, findViewById(R.id.selectPayment));
        builder.setView(view2);
        final AlertDialog alertDialog = builder.create();
//        EditText etNamePaytmUpi = view2.findViewById(R.id.namePaytmUpiEt);
//        EditText etPaytmUpiNo = view2.findViewById(R.id.paytmUpiNoEt);
        RadioButton checkboxPaymentGateway=view2.findViewById(R.id.checkboxPaymentGateway);
        RadioButton checkboxPaymentScreenshot=view2.findViewById(R.id.checkboxPaymentScreenshot);



        checkboxPaymentGateway.setOnClickListener(view -> {

            checkboxPaymentGateway.setChecked(true);
            checkboxPaymentScreenshot.setChecked(false);
            Toast.makeText(CreateIdActivity.this, "Paytm Upi submitted!", Toast.LENGTH_SHORT).show();


        });

        checkboxPaymentScreenshot.setOnClickListener(v -> {
            checkboxPaymentScreenshot.setChecked(true);
            checkboxPaymentGateway.setChecked(false);
            Toast.makeText(CreateIdActivity.this, "Payment Screent submitted", Toast.LENGTH_SHORT).show();
        });


        view2.findViewById(R.id.btnProceedCancel).setOnClickListener(view1 -> {
            alertDialog.dismiss();


        });

        view2.findViewById(R.id.btnProceedPayment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  alertDialog.dismiss();
                GenerateChecksum();
            }
        });
        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

    public void GenerateChecksum() {
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
        params.put( "TXN_AMOUNT" , minRefill);
        params.put( "WEBSITE" , WEBSITE);
        params.put( "INDUSTRY_TYPE_ID" , INDUSTRY_TYPE_ID);
        params.put( "CALLBACK_URL", CALLBACK_URL);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
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
                                onStartTransaction();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                 Toast.makeText(CreateIdActivity.this,"Error...!",Toast.LENGTH_SHORT).show();
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
                params.put( "TXN_AMOUNT" , minRefill);
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

    public void onStartTransaction() {
        PaytmPGService Service = PaytmPGService.getProductionService();
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put( "MID" ,GlobalVariables.Paytm_mid);
        // Key in your staging and production MID available in your dashboard

        paramMap.put( "ORDER_ID" , orderId);
        paramMap.put( "CUST_ID" , GlobalVariables.profileuser.getMobile());
        paramMap.put( "MOBILE_NO" , GlobalVariables.profileuser.getMobile());
        paramMap.put( "EMAIL" , GlobalVariables.profileuser.getEmail());
        paramMap.put( "CHANNEL_ID" , "WAP");
        paramMap.put( "TXN_AMOUNT" , minRefill);
        paramMap.put( "WEBSITE" , WEBSITE);
        paramMap.put( "INDUSTRY_TYPE_ID" , INDUSTRY_TYPE_ID);
        paramMap.put( "CALLBACK_URL", CALLBACK_URL);
        paramMap.put( "CHECKSUMHASH" , checksum);



        PaytmOrder Order = new PaytmOrder(paramMap);

		*//*PaytmMerchant Merchant = new PaytmMerchant(
				"https://pguat.paytm.com/paytmchecksum/paytmCheckSumGenerator.jsp",
				"https://pguat.paytm.com/paytmchecksum/paytmCheckSumVerify.jsp");*//*

        Service.initialize(Order, null);

        Service.startPaymentTransaction(this, true, true,
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
                            Toasty.success(CreateIdActivity.this, " Transaction success", Toast.LENGTH_LONG).show();
                            //  method.loadingDialog.dismiss();
                            //uploadData();
                           *//* Method.BalanceUpdate(constant.sharedPreferences.getString(constant.profileId, "profileId"),
                                    "Credit", amount, ip, 3);*//*
                            //onSuccessPay(orderId);
                            // showPay_Status_AlertDialog(1);
                            // method.loadingDialogg(Payment.this);
                            onSuccessPay(orderId);
                        } else if (!inResponse.getBoolean("STATUS")) {
                            //    Payment Failed
                            //  Toast.makeText(Payment.this, " Transaction Failed", Toast.LENGTH_LONG).show();
                            //startActivity(new Intent(getContext(), HomeActivity.class));
                            // finish();
                            showPay_Status_AlertDialog(2);
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
                        showPay_Status_AlertDialog(2);
                    }

                    @Override
                    public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
                        com.paytm.pgsdk.Log.d("KINGSN", "Payment Transaction Failed " + inErrorMessage);
                       // Toast.makeText(getBaseContext(), "Payment Transaction Failed ", Toast.LENGTH_LONG).show();
                        showPay_Status_AlertDialog(2);
                    }

                });
    }


    private void onSuccessPay(String orderId) {

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
                            Log.d(TAG, "onResponse: "+response);
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray jsonArray = jsonObject.getJSONArray(GlobalVariables.AppSid);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject object = jsonArray.getJSONObject(i);
                                String success = object.getString("success");

                                if (success.equals("1")) {
                                    Toast.makeText(CreateIdActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                                    // finish();
                                    // startActivity(getIntent());
                                    // method.loadingDialog.dismiss();
                                    // loadingDialog.dismiss();
                                    showPay_Status_AlertDialog(1);
                                   *//* android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(Payment.this);
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
                                   // Toast.makeText(Payment.this, object.getString("msg"), Toast.LENGTH_LONG).show();*//*


                                } else {
                                    method.loadingDialog.dismiss();
                                    android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(CreateIdActivity.this);
                                    alertDialogBuilder.setTitle(object.getString("title"));
                                    alertDialogBuilder.setMessage(object.getString("msg"));
                                    alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
                                    alertDialogBuilder.setPositiveButton(CreateIdActivity.this.getResources().getString(R.string.ok_message),
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
                                    //  Toast.makeText(Payment.this, object.getString("msg"), Toast.LENGTH_LONG).show();
                                }

                            }

                            // progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CreateIdActivity.this, "Error: " + e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Toast.makeText(Payment.this, "RESPONSE: " + error, Toast.LENGTH_SHORT).show();
                Log.e("Error", "" + error.getMessage());
                ifvolleyfail();

            }
        }) { @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<>();
            params.put("payMentMode","0");
            params.put("mobile", GlobalVariables.profileuser.getMobile());
            params.put("txnType","0"); *//* For Create if*//*
            params.put("txnStatus","1");
            params.put("createdId",createId);
            params.put("idUsername",  binding.usernameEt.getText().toString());
            params.put("idPassword","");
            params.put("email",  GlobalVariables.profileuser.getEmail());
            params.put("paid",minRefill);
            params.put("order_id", CreateIdActivity.this.orderId);
            params.put("city",   GlobalVariables.profileuser.getCity());

            return params;
        }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(CreateIdActivity.this);
        requestQueue.add(stringRequest);
        Log.d(TAG, "volley: "+stringRequest);


    }

    @SuppressLint("SetTextI18n")
    public void showPay_Status_AlertDialog(int status) {
        // method.loadingDialog.dismiss();
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(CreateIdActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(CreateIdActivity.this).inflate(R.layout.paydilog_status, viewGroup, false);
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
            ctvbTitle.setTextColor(getResources().getColor(R.color.successColor));
            paymsg.setText("Deposite Amount Has Been Added !\n Check Your Wallet");
            animationView.setAnimationFromUrl("https://assets7.lottiefiles.com/datafiles/F8yLwPvno9fP0Ag/data.json");
            animationView.setColorFilter(getResources().getColor(R.color.successColor));
        }else{
            ctvbTitle.setText("Payment Failed ");
            ctvbTitle.setTextColor(getResources().getColor(R.color.errorColor));
            paymsg.setText("Oops! Something went wrong !\n Please Try Again !");
            animationView.setAnimationFromUrl("https://assets9.lottiefiles.com/packages/lf20_QwXBUo.json");
            animationView.setColorFilter(getResources().getColor(R.color.errorColor));

        }

        txnid.setText("Transaction Id : "+orderId);
        txnamnt.setText("Transaction Amount : "+minRefill);
        int addpoint=Integer.parseInt(minRefill);
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
                finish();
                startActivity(getIntent());
            }
        });


    }
*/
    public void ifvolleyfail(){
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(CreateIdActivity.this);
        alertDialogBuilder.setTitle("Something Went Wrong");
        alertDialogBuilder.setMessage("Please Try With Active Internet ");
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
        alertDialogBuilder.setPositiveButton(CreateIdActivity.this.getResources().getString(R.string.ok_message),
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
    }


}