package com.crickpred.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.crickpred.app.Util.GlobalVariables;
import com.crickpred.app.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;
    public static int SPLASH_DISPLAY_LENGTH = 1000;
    private SharedPreferences sharedPreferences;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        setTheme(R.style.Theme_Gpuntd_Fullscreen);
        preferences = getSharedPreferences(GlobalVariables.ADMIN_PREF, MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = preferences.edit();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(!preferences.getBoolean(String.valueOf(GlobalVariables.USER_IS_LOGIN),false)){
                    Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(mainIntent);
                    SplashActivity.this.finish();
                }else{
                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    SplashActivity.this.finish();
                }


            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}