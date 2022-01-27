package com.crickpred.app.ui.passbook;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.crickpred.app.MainActivity;
import com.crickpred.app.Models.Passbook_Data;
import com.crickpred.app.R;
import com.crickpred.app.Util.GlobalVariables;
import com.crickpred.app.Util.Method;
import com.crickpred.app.Util.RestAPI;
import com.crickpred.app.adapter.PassbookDataAdapter;
import com.crickpred.app.databinding.FragmentPassbookBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class PassbookFragment extends Fragment {

    private static final String TAG = "KINGSN";
    private FragmentPassbookBinding binding;
    private Method method;
    private List<Passbook_Data> passbook_data;
    RecyclerView PassbookrecyclerView,recyclerView1;
    PassbookDataAdapter PassbookRecyclerAdapter;
    private SharedPreferences preferences,sharedPreferences;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPassbookBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        PassbookrecyclerView = binding.passbookRv;
        method=new Method(requireActivity());
        preferences = this.requireActivity().getSharedPreferences(GlobalVariables.ADMIN_PREF, MODE_PRIVATE);
        editor = preferences.edit();
        getPassbook();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void getPassbook(){
        passbook_data = new ArrayList<>();
        PassbookrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PassbookRecyclerAdapter= new PassbookDataAdapter(passbook_data,getContext());
        method.loadingDialogg(requireActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, RestAPI.API_GET_PASSBOOK,
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
                                    String username = object.getString("username");
                                    String mobile = object.getString("mobile");
                                    String createdId = object.getString("createdId");
                                    String amount = object.getString("amount");
                                    String txnType = object.getString("txnType");
                                    String status = object.getString("status");
                                    String txnId = object.getString("txnId");
                                    String createdDate = object.getString("createdDate");
                                    String approvalDate = object.getString("approvalDate");
                                    String image = object.getString("img");
                                    String idWebsite = object.getString("idWebsite");
                                    String idName = object.getString("idName");



                                    Passbook_Data ld1=new Passbook_Data( id,  username,  mobile, createdId, amount, txnType,
                                            status, txnId,createdDate,approvalDate,image,idWebsite,idName);
                                    passbook_data.add(ld1);
                                    //Toast.makeText(getActivity(), "hello"+ob.getString("amount"), Toast.LENGTH_LONG).show();


                                }
                                Log.d(TAG, "onResponse: "+jsonArray2);

                                PassbookRecyclerAdapter= new PassbookDataAdapter(passbook_data,getContext());
                                //recyclerView.setLayoutManager(new LinearLayoutManager(view));
                                //GridLayoutManager lm = new GridLayoutManager(view, 1);
                                // recyclerView.setLayoutManager();
                                PassbookrecyclerView.setAdapter(PassbookRecyclerAdapter);
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
                })
        {
            @Override
            protected Map<String, String> getParams() {

            Map<String, String> params = new HashMap<>();
            params.put("users_login", "KINGSN");
            params.put("mobile",preferences.getString(GlobalVariables.USER_MOBILE,"") );
            params.put("device_id",method.getDeviceId(requireActivity()));

            return params;
           }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(requireActivity());
        requestQueue.add(stringRequest);

    }
}