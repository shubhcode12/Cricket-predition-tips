package com.crickpred.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.crickpred.app.Models.Profileuser;
import com.crickpred.app.Util.GlobalVariables;
import com.crickpred.app.Util.Method;
import com.crickpred.app.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    private Method method;
    private Profileuser profileuser;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        method=new Method(this);
        preferences = getSharedPreferences(GlobalVariables.ADMIN_PREF, MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = preferences.edit();
        //editor = preferences.edit();
        SetDAta();
        binding.btnReferalProgram.setOnClickListener(view -> {
            startActivity(new Intent(ProfileActivity.this, ReferAndEarnActivity.class));
        });
    }
    @SuppressLint("SetTextI18n")
    private void SetDAta(){
        binding.profileName.setText(GlobalVariables.profileuser.getName());
        binding.profileNumberTv.setText(GlobalVariables.profileuser.getMobile());
        binding.memberTv.setText("Member Since "+GlobalVariables.profileuser.getJoiningTime());
        binding.rewardsearned.setText(GlobalVariables.profileuser.getTotalPaid());
        binding.rewardsearned.setText(GlobalVariables.profileuser.getTotalReferals());
        // binding.btnReferalProgram.setImageDrawable(GlobalVariables.);

      /*  Glide.with(ProfileActivity.this)
                .load("http://via.placeholder.com/300.png")
                .into(binding.prifileImageView);*/

        Glide.with(ProfileActivity.this)
                .load(GlobalVariables.profileuser.getProfilePic())
                .placeholder(R.drawable.dummyuser_image)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.prifileImageView);

        Glide.with(ProfileActivity.this)
                .load(GlobalVariables.settings.getAppPromo1())
                .placeholder(R.drawable.referal)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.btnReferalProgram);
//        binding.profileName.setText(preferences.getString(GlobalVariables.profileuser.getName(),""));
//       binding.profileNumberTv.setText(preferences.getString(GlobalVariables.profileuser.getName(),""));
    }
}