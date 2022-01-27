package com.crickpred.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.crickpred.app.databinding.ActivityHelpBinding;

public class HelpActivity extends AppCompatActivity {
    ActivityHelpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_help);
        setSupportActionBar(binding.toolbarHelp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.toolbarHelp.setNavigationOnClickListener(view -> {
            super.onBackPressed();
        });

        binding.wasapSection.setOnClickListener(view -> {
            setupAddWhatsappDialog();
        });
    }

    public void setupAddWhatsappDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(HelpActivity.this).inflate(R.layout.dialog_add_whatsapp, findViewById(R.id.logoutView));
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.addWhatsappEt);
        view.findViewById(R.id.btnSubmitWp).setOnClickListener(view1 -> {
            Toast.makeText(HelpActivity.this, "WhatsApp number submitted!", Toast.LENGTH_SHORT).show();
        });
        view.findViewById(R.id.btnCancelDialogWp).setOnClickListener(view1 -> {
            alertDialog.dismiss();
        });
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

}