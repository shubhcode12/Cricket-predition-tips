package com.crickpred.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.crickpred.app.Models.View_IDTXN_Data;
import com.crickpred.app.Util.GlobalVariables;
import com.crickpred.app.Util.Method;
import com.crickpred.app.Util.PrefManager;
import com.crickpred.app.Util.RestAPI;
import com.crickpred.app.adapter.ViewIdTxnAdapter;
import com.crickpred.app.databinding.ActivityDepositBinding;
import com.crickpred.app.databinding.ActivityMyIdDetailsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MyIdDetailsActivity extends AppCompatActivity {

    private static final String TAG = "KINGSN";
    ActivityMyIdDetailsBinding binding;
    private SharedPreferences preferences,sharedPreferences;
    private SharedPreferences.Editor editor;
    private PrefManager prefManager;
    private Method method;
    private List<View_IDTXN_Data> view_idtxn_data;
    String mobile,userName,wallet_bal,screenType,idname,idwebsite,minRefill,minWithdrawal,minMaintainBal,
            idPassword,idimageurl,idUsername,createdId,idBalance,Id;
    RecyclerView ViewIdTxnrecyclerView;

    ViewIdTxnAdapter viewIdTxnAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_my_id_details);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_id_details);
        setSupportActionBar(binding.toolbarIdDetails);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        prefManager = new PrefManager(this);
        Log.d(TAG, "onCreateView: "+ prefManager.getValue("OnesignalappKey"));
        preferences = MyIdDetailsActivity.this.getSharedPreferences(GlobalVariables.ADMIN_PREF, MODE_PRIVATE);
        editor = preferences.edit();
        method=new Method(this);


        setUI();

    }

    @SuppressLint("SetTextI18n")
    private void setUI() {
        ViewIdTxnrecyclerView = binding.rvMyIdDetails;

        if (getIntent().hasExtra("screenType")) {
            Log.d(TAG, "onCreate: " + getIntent().getExtras());

            Log.d(TAG, "onCreate: " + getIntent().getExtras());
            screenType = getIntent().getStringExtra("screenType");
          //  Id=getIntent().getStringExtra("Id");
            createdId=getIntent().getStringExtra("createId");
            idname=getIntent().getStringExtra("idname");
            idwebsite=getIntent().getStringExtra("idwebsite");
           idimageurl=getIntent().getStringExtra("idimageurl");
            idUsername = getIntent().getStringExtra("idUserName");
            idPassword=getIntent().getStringExtra("idPassword");
            idBalance=getIntent().getStringExtra("idBalance");
            //  mobile = getIntent().getStringExtra("mobile");
            GlobalVariables.idUsername= idUsername;
            GlobalVariables.idPassword= idPassword;
            userName = getIntent().getStringExtra("userName");
            binding.tvNameIdDetails.setText(idname);
            binding.tvWebsiteIdDetails.setText(idwebsite);
            binding.idBalance.setText(idBalance);
            binding.tvUsernameIdDetails.setText("Username : "+idUsername);
            binding.tvPassIdDetails.setText("Password : "+idPassword);
             binding.tvDateIdDetails.setText("Password : "+idPassword);
            if(!idimageurl.equals("")) {
                Log.d("KINGSN", "onBindViewHolder: "+idimageurl);

                Glide.with(MyIdDetailsActivity.this)
                        .load(idimageurl)
                        .placeholder(R.drawable.round_bg)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.ivIdDetails);
                getMyId();
            }
        }else{

        }


    }
    public void getMyId(){
        view_idtxn_data = new ArrayList<>();
        ViewIdTxnrecyclerView.setLayoutManager(new LinearLayoutManager(MyIdDetailsActivity.this));
        viewIdTxnAdapter= new ViewIdTxnAdapter(view_idtxn_data,MyIdDetailsActivity.this);
        method.loadingDialogg(MyIdDetailsActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, RestAPI.API_VIEW_IDTXN,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText(LoginActivity.this, "RESPONSE: " + response, Toast.LENGTH_SHORT).show();
                        try {
                            System.out.println(response);
                            JSONObject jsonObject = new JSONObject(response);

                            JSONObject jsonObject1 = jsonObject.getJSONObject(GlobalVariables.AppSid);
                            JSONArray jsonArray2 = jsonObject1.getJSONArray("Results");
                            String success = jsonObject1.getString("success");

                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray2.length(); i++) {
                                    JSONObject object = jsonArray2.getJSONObject(i);
                                    Log.d(TAG, "onResponse: result"+jsonArray2);


                                    String id  = object.getString ("id");
                                    String userMobile  = object.getString ("userMobile");
                                    String txnType  = object.getString ("txnType");
                                    String orderId  = object.getString ("orderId");
                                    String amount  = object.getString ("amount");
                                    String createdId  = object.getString ("createdId");
                                    String payMentMode  = object.getString ("payMentMode");
                                    String txnStatus  = object.getString ("txnStatus");
                                    String idUsername  = object.getString ("idUsername");
                                    String idPassword  = object.getString ("idPassword");
                                    String adminComment  = object.getString ("adminComment");
                                    String txnDate  = object.getString ("txnDate");
                                    String cancelledDate  = object.getString ("cancelledDate");
                                    String verifiedDate  = object.getString ("verifiedDate");
                                   // String idimageurl  = idimageurl;


                                    View_IDTXN_Data ld1=new View_IDTXN_Data( id, idimageurl,idname, userMobile,  txnType,  orderId,  amount,  createdId,  payMentMode,  txnStatus,  idUsername,  idPassword,  adminComment,  txnDate,  cancelledDate,  verifiedDate);
                                    view_idtxn_data.add(ld1);
                                    //Toast.makeText(getActivity(), "hello"+ob.getString("amount"), Toast.LENGTH_LONG).show();

                                    Log.d(TAG, "onResponse: myId"+createdId);

                                }
                                Log.d(TAG, "onResponse: "+jsonArray2);

                                viewIdTxnAdapter= new ViewIdTxnAdapter(view_idtxn_data,(MyIdDetailsActivity.this));
                                //recyclerView.setLayoutManager(new LinearLayoutManager(view));
                                //GridLayoutManager lm = new GridLayoutManager(view, 1);
                                // recyclerView.setLayoutManager();
                                ViewIdTxnrecyclerView.setAdapter(viewIdTxnAdapter);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        method.loadingDialog.dismiss();
                                    }
                                }, 1000);
                            }else{
                                method.loadingDialog.dismiss();
                                //sendVerificationCodeToUser(mobileNumber);
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MyIdDetailsActivity.this);
                                alertDialogBuilder.setTitle(jsonObject.getString("title"));
                                alertDialogBuilder.setMessage(jsonObject.getString("msg"));
                                alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
                                alertDialogBuilder.setPositiveButton(MyIdDetailsActivity.this.getResources().getString(R.string.ok_message),
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface arg0, int arg1) {
                                                method.loadingDialog.dismiss();
                                                finish();
                                                startActivity(new Intent(MyIdDetailsActivity.this, MainActivity.class));
                                                //Log.d("Response",msg);
                                                // finishAffinity();

                                            }
                                        });

                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();
                            }


                            //loadingDialog.dismiss();

                        }catch (JSONException e){
                            e.printStackTrace();
                            //  Toast.makeText(getActivity(), "hello"+e, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        //Toast.makeText(WalletActivity.this,"Error...!",Toast.LENGTH_SHORT).show();
                        method.loadingDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("users_login", "KINGSN");
                params.put("mobile",GlobalVariables.profileuser.getMobile());
                params.put("createdId",createdId);
                /*  params.put("device_id",method.getDeviceId(getApplicationContext()));*/

                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(MyIdDetailsActivity.this);
        requestQueue.add(stringRequest);

    }
}