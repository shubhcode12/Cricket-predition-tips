package com.crickpred.app;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.crickpred.app.Util.GlobalVariables;
import com.crickpred.app.Util.Method;
import com.crickpred.app.Util.PrefManager;
import com.crickpred.app.databinding.ActivityDepositidBinding;


import java.util.Objects;

public class DepositIDActivity extends AppCompatActivity  {

    private static final String TAG = "KINGSN";
   ActivityDepositidBinding binding;
    String mobile,userName,wallet_bal,screenType,idname,idwebsite,minRefill,minWithdrawal,minMaintainBal,
            idPassword,idimageurl,idUsername,createdId;
    private SharedPreferences preferences,sharedPreferences;
    private SharedPreferences.Editor editor;
    private PrefManager prefManager;
    private Method method;
    private CreateIdActivity method2;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_depositid);

        setSupportActionBar(binding.toolbarDeposit);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.toolbarDeposit.setNavigationOnClickListener(view -> {
            super.onBackPressed();
        });

        prefManager = new PrefManager(this);
        Log.d(TAG, "onCreateView: "+ prefManager.getValue("OnesignalappKey"));
        preferences = DepositIDActivity.this.getSharedPreferences(GlobalVariables.ADMIN_PREF, MODE_PRIVATE);
        editor = preferences.edit();
        method=new Method(this);
        method2=new CreateIdActivity();

        setUI();
    }

    @SuppressLint("SetTextI18n")
    private void setUI() {
        if (getIntent().hasExtra("screenType")) {
            Log.d(TAG, "onCreate: " + getIntent().getExtras());
            screenType = getIntent().getStringExtra("screenType");
            createdId=getIntent().getStringExtra("createId");
            idname=getIntent().getStringExtra("idname");
            idwebsite=getIntent().getStringExtra("idwebsite");
            idimageurl=getIntent().getStringExtra("idimageurl");
            idUsername = getIntent().getStringExtra("idUserName");
            idPassword=getIntent().getStringExtra("idPassword");
          //  mobile = getIntent().getStringExtra("mobile");
            GlobalVariables.idUsername= idUsername;
            GlobalVariables.idPassword= idPassword;

            editor.apply();
            userName = getIntent().getStringExtra("userName");
           binding.tvIdName.setText(idname);
            binding.tvIdWebsite.setText(idwebsite);
            binding.usernameTXT.setText(idUsername);

            if(!idimageurl.equals("")) {
                Log.d("KINGSN", "onBindViewHolder: "+idimageurl);

                Glide.with(DepositIDActivity.this)
                        .load(idimageurl)
                        .placeholder(R.drawable.round_bg)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.ivIdProfile);

            }
            if(screenType.equals(GlobalVariables.Deposit)){
                binding.toolbarDeposit.setTitle(screenType);
                binding.txihint.setHint("Deposit Coins");
                binding.idbelow.setText("*Minimum Deposit amount is "+GlobalVariables.settings.getMinDepositcoin() +" coins");
                binding.payfromw.setVisibility(View.VISIBLE);
                binding.btnDepositCoin.setText("Deposit Coins");
                binding.wdmthd.setVisibility(View.GONE);
                binding.wlimit.setVisibility(View.GONE);
                binding.depositCoinEt.setText(GlobalVariables.settings.getMinDepositcoin());
            }else if (screenType.equals(GlobalVariables.Withdraw)){
                binding.toolbarDeposit.setTitle(screenType);
                binding.payfromw.setVisibility(View.GONE);
                binding.wdmthd.setVisibility(View.VISIBLE);
                binding.txihint.setHint("Withdraw Coins");
                binding.idbelow.setText("*Minimum Withdraw amount is "+GlobalVariables.settings.getMinimumWidthrawal() +" coins");
              /*  binding.withdrawTXT.setVisibility(View.VISIBLE);
                binding.withdrawTXT.setText("Withdraw Balance "+GlobalVariables.profileuser.getWallet());*/
                binding.btnDepositCoin.setText("Withdraw Coins");
                binding.wlimit.setVisibility(View.VISIBLE);
                binding.wlimit.setText("Daily Withdrawal Limit : "+getIntent().getStringExtra("maxWithdrawal"));

            }
        }else{

        }

        binding.btnDepositCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(screenType.equals(GlobalVariables.Deposit)){
                   checkRefilAmount();
                }
            }
        });

        binding.wtb.setOnClickListener(view -> {

            binding.wtb.setChecked(true);
            binding.wtw.setChecked(false);
            Toast.makeText(DepositIDActivity.this, "Withdraw To bank !", Toast.LENGTH_SHORT).show();


        });

        binding.wtw.setOnClickListener(v -> {
            binding.wtw.setChecked(true);
            binding.wtb.setChecked(false);
            Toast.makeText(DepositIDActivity.this, "Withdraw To Wallet", Toast.LENGTH_SHORT).show();
        });

    }

    public void checkRefilAmount(){
        if(Integer.parseInt(String.valueOf(binding.depositCoinEt.getText())) <
                Integer.parseInt(GlobalVariables.settings.getMinDepositcoin())){

         binding.depositCoinEt.setError("Minimum Deposit Coin Is "+GlobalVariables.settings.getMinDepositcoin());
        }else{
           final String TxnType="1";
            Log.d(TAG, "onClick: "+GlobalVariables.idUsername);
            method.setupPaymentDialog(DepositIDActivity.this, Objects.requireNonNull(binding.depositCoinEt.getText()).toString(),createdId,TxnType);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();/* startActivity(new Intent(DepositIDActivity.this, MainActivity.class));*/

    }
}