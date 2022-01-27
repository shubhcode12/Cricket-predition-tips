package com.crickpred.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.crickpred.app.Util.GlobalVariables;
import com.crickpred.app.Util.Method;
import com.crickpred.app.Util.PrefManager;
import com.crickpred.app.databinding.ActivityDepositBinding;

import java.util.Objects;

public class DepositActivity extends AppCompatActivity  {

    private static final String TAG = "KINGSN";
    ActivityDepositBinding binding;
    String mobile,userName,wallet_bal,screenType;
    private SharedPreferences preferences,sharedPreferences;
    private SharedPreferences.Editor editor;
    private PrefManager prefManager;
    private Method method;
    private CreateIdActivity method2;
    private String createdId="";

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_deposit);

        setSupportActionBar(binding.toolbarDeposit);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.toolbarDeposit.setNavigationOnClickListener(view -> {
            super.onBackPressed();
        });

        prefManager = new PrefManager(this);
        Log.d(TAG, "onCreateView: "+ prefManager.getValue("OnesignalappKey"));
        preferences = DepositActivity.this.getSharedPreferences(GlobalVariables.ADMIN_PREF, MODE_PRIVATE);
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
            wallet_bal = getIntent().getStringExtra("wallet_bal");
            mobile = getIntent().getStringExtra("mobile");
            userName = getIntent().getStringExtra("userName");
           binding.walletBalTv.setText(GlobalVariables.profileuser.getWallet());
            if(screenType.equals(GlobalVariables.Deposit)){
                binding.toolbarDeposit.setTitle(screenType);
                binding.txihint.setHint("Deposit Coins");
                binding.idbelow.setText("*Minimum Deposit amount is "+GlobalVariables.settings.getMinDepositcoin() +" coins");
                binding.withdrawTXT.setVisibility(View.GONE);
                binding.btnDepositCoin.setText("Deposit Coins");
                binding.depositCoinEt.setText(GlobalVariables.settings.getMinDepositcoin());
            }else if (screenType.equals(GlobalVariables.Withdraw)){
                binding.toolbarDeposit.setTitle(screenType);
                binding.withdrawTXT.setVisibility(View.VISIBLE);
                binding.txihint.setHint("Withdraw Coins");
                binding.idbelow.setText("*Minimum Withdraw amount is "+GlobalVariables.settings.getMinimumWidthrawal() +" coins");
                binding.withdrawTXT.setVisibility(View.VISIBLE);
                binding.withdrawTXT.setText("Withdraw Balance "+GlobalVariables.profileuser.getWallet());
                binding.btnDepositCoin.setText("Withdraw Coins");

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
    }

    public void checkRefilAmount(){
        if(Integer.parseInt(String.valueOf(binding.depositCoinEt.getText())) <
                Integer.parseInt(GlobalVariables.settings.getMinDepositcoin())){

         binding.depositCoinEt.setError("Minimum Deposit Coin Is "+GlobalVariables.settings.getMinDepositcoin());
        }else{
            final String TxnType="2";
            method.setupPaymentDialog(DepositActivity.this, Objects.requireNonNull(binding.depositCoinEt.getText()).toString(),createdId,TxnType);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DepositActivity.this, MainActivity.class));
    }
}