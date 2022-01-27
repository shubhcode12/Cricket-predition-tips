package com.crickpred.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.crickpred.app.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    private String amount,str,mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        Bundle extras=getIntent().getExtras();
        assert extras != null;
        mobileNumber=extras.getString("mobile"); //if data you are sending is String.

        //binding.fone.setText(mobileNumber);
        binding.fone.setText(mobileNumber);

    }
}