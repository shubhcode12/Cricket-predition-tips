package com.crickpred.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.crickpred.app.databinding.ActivityOtpLoginBinding;

public class OtpLoginActivity extends AppCompatActivity {

    ActivityOtpLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp_login);

    }


}