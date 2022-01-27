package com.crickpred.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.crickpred.app.databinding.ActivityReferAndEarnBinding;

public class ReferAndEarnActivity extends AppCompatActivity {
    ActivityReferAndEarnBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_refer_and_earn);
        setSupportActionBar(binding.toolbarRef);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.toolbarRef.setNavigationOnClickListener(view -> {
            super.onBackPressed();
        });

    }
}