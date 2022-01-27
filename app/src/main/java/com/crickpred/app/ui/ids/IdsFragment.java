package com.crickpred.app.ui.ids;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.crickpred.app.MainActivity;
import com.crickpred.app.Models.Create_ID_Data;
import com.crickpred.app.Models.My_ID_Data;
import com.crickpred.app.R;
import com.crickpred.app.Util.GlobalVariables;
import com.crickpred.app.Util.Method;
import com.crickpred.app.Util.RestAPI;
import com.crickpred.app.adapter.CreateDataAdapter;
import com.crickpred.app.adapter.MyDataAdapter;
import com.crickpred.app.databinding.FragmentIdsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IdsFragment extends Fragment {

    private static final String TAG = "KINGSN";
    FragmentIdsBinding binding;
    private Method method;
    private List<Create_ID_Data> create_id_data;
    private List<My_ID_Data> my_id_data;
    RecyclerView CreaterecyclerView,recyclerView1,MyrecyclerView;
    CreateDataAdapter CreateRecyclerAdapter;
   // RedeemAdapter recyclerAdapter1;
    MyDataAdapter MyRecyclerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentIdsBinding.inflate(inflater, container, false);
        CreaterecyclerView = binding.rvCreateID;
        MyrecyclerView = binding.rvMyID;
        View root = binding.getRoot();
        method=new Method(requireContext());
        getCreateId();

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {

                    case 1:
                        binding.rvMyID.setVisibility(View.GONE);
                        binding.rvCreateID.setVisibility(View.VISIBLE);
                        break;

                    default:
                        getMyId();
                        binding.rvCreateID.setVisibility(View.GONE);
                        binding.rvMyID.setVisibility(View.VISIBLE);
                        break;

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.tabLayout.selectTab(binding.tabLayout.getTabAt(1));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void getCreateId(){
        create_id_data = new ArrayList<>();
        CreaterecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CreateRecyclerAdapter= new CreateDataAdapter(create_id_data,getContext());
        method.loadingDialogg(requireActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, RestAPI.API_GET_CREATEID,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText(LoginActivity.this, "RESPONSE: " + response, Toast.LENGTH_SHORT).show();
                        try {
                            //  System.out.println(response);
                            JSONObject jsonObject = new JSONObject(response);

                            JSONObject jsonObject1 = jsonObject.getJSONObject(GlobalVariables.AppSid);
                            JSONArray jsonArray2 = jsonObject1.getJSONArray("Results");
                            String success = jsonObject1.getString("success");

                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray2.length(); i++) {
                                    JSONObject object = jsonArray2.getJSONObject(i);
                                    Log.d(TAG, "onResponse: result"+jsonArray2);


                                    String id = object.getString("id");
                                    String idName = object.getString("id_name");
                                    String idImage = object.getString("id_image");
                                    String idWebsite = object.getString("id_website");
                                    String idStatus = object.getString("id_status");
                                    String demoId = object.getString("demoId");
                                    String demoPass = object.getString("demoPass");
                                    String demoLink = object.getString("demoLink");
                                    String minRefill = object.getString("minRefill");
                                    String minWithdrawal = object.getString("minWithdrawal");
                                    String minMaintainBal = object.getString("minMaintainBal");
                                    String maxWithdrawal = object.getString("maxWithdrawal");
                                    String idCreatedDate = object.getString("id_total_created");
                                    String idUpdatedDate = object.getString("id_created_date");
                                    String idTotalCreated = object.getString("id_total_created");

                                    Create_ID_Data ld1=new Create_ID_Data( id,  idName,  idImage,  idWebsite,  idStatus, demoId,demoPass,demoLink,
                                            minRefill,minWithdrawal,minMaintainBal,maxWithdrawal,idCreatedDate,
                                            idUpdatedDate,  idTotalCreated);
                                    create_id_data.add(ld1);
                                    //Toast.makeText(getActivity(), "hello"+ob.getString("amount"), Toast.LENGTH_LONG).show();


                                }
                                Log.d(TAG, "onResponse: "+jsonArray2);

                                CreateRecyclerAdapter= new CreateDataAdapter(create_id_data,getContext());
                                //recyclerView.setLayoutManager(new LinearLayoutManager(view));
                                //GridLayoutManager lm = new GridLayoutManager(view, 1);
                                // recyclerView.setLayoutManager();
                                CreaterecyclerView.setAdapter(CreateRecyclerAdapter);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        method.loadingDialog.dismiss();
                                    }
                                }, 1000);
                            }else{
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
                                                requireActivity().finish();
                                                startActivity(new Intent(requireActivity(), MainActivity.class));
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
                }
        );

        RequestQueue requestQueue= Volley.newRequestQueue(requireActivity());
        requestQueue.add(stringRequest);

    }

    public void getMyId(){
        my_id_data = new ArrayList<>();
        MyrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MyRecyclerAdapter= new MyDataAdapter(my_id_data,getContext());
        method.loadingDialogg(requireActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, RestAPI.API_GET_MYID,
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


                                    String id = object.getString ("id");
                                    String userMobile = object.getString ("userMobile");
                                    String corderId = object.getString ("CorderId");
                                    String createdId = object.getString ("createdId");
                                    String payMentMode = object.getString ("payMentMode");
                                    String idStatus = object.getString ("idStatus");
                                    String idUsername = object.getString ("idUsername");
                                    String idPassword = object.getString ("idPassword");
                                    String idBalance = object.getString ("idBalance");
                                    String idWithdrawal = object.getString ("idWithdrawal");
                                    String idTotalDeposited = object.getString ("idTotalDeposited");
                                    String idTotalWithdrawals = object.getString ("idTotalWithdrawals");
                                    String idApproval = object.getString ("idApproval");
                                    String adminComment = object.getString ("adminComment");
                                    String idCreatedDate = object.getString ("idCreatedDate");
                                    String idUpdatedDate = object.getString ("idUpdatedDate");
                                    String idCancelledDate = object.getString ("idCancelledDate");
                                    String idVerifiedDate = object.getString ("idVerifiedDate");
                                    String idName = object.getString ("id_name");
                                    String idWebsite = object.getString ("id_website");
                                    String demoLink = object.getString ("demoLink");
                                    String demoId = object.getString ("demoId");
                                    String demoPass = object.getString ("demoPass");
                                    String minRefill=object.getString("minRefill");
                                    String minWithdrawal=object.getString("minWithdrawal");
                                    String minMaintainBal=object.getString("minMaintainBal");
                                    String idCreatedDdate = object.getString ("id_created_ddate");
                                    String img = object.getString ("img");
                                    String maxWithdrawal = object.getString ("maxWithdrawal");


                                    My_ID_Data ld1=new My_ID_Data( id,  userMobile,  corderId,  createdId,  payMentMode,  idStatus,
                                            idUsername,  idPassword,  idBalance,  idWithdrawal,  idTotalDeposited,
                                            idTotalWithdrawals,  idApproval,  adminComment,  idCreatedDate,  idUpdatedDate,
                                            idCancelledDate,  idVerifiedDate,idName,idWebsite,demoLink,demoId,demoPass, minRefill,
                                            minWithdrawal,minMaintainBal,idCreatedDdate,img,maxWithdrawal);
                                    my_id_data.add(ld1);
                                    //Toast.makeText(getActivity(), "hello"+ob.getString("amount"), Toast.LENGTH_LONG).show();

                                    Log.d(TAG, "onResponse: myId"+idWebsite);

                                }
                                Log.d(TAG, "onResponse: "+jsonArray2);

                                MyRecyclerAdapter= new MyDataAdapter(my_id_data,getContext());
                                //recyclerView.setLayoutManager(new LinearLayoutManager(view));
                                //GridLayoutManager lm = new GridLayoutManager(view, 1);
                                // recyclerView.setLayoutManager();
                                MyrecyclerView.setAdapter(MyRecyclerAdapter);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        method.loadingDialog.dismiss();
                                    }
                                }, 1000);
                            }else{
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
                                                requireActivity().finish();
                                                startActivity(new Intent(requireActivity(), MainActivity.class));
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
              /*  params.put("device_id",method.getDeviceId(getApplicationContext()));*/

                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(requireActivity());
        requestQueue.add(stringRequest);

    }
}