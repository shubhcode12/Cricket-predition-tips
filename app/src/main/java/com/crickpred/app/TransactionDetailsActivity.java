package com.crickpred.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.crickpred.app.databinding.ActivityTransactionDetailsBinding;

import java.util.Objects;

public class TransactionDetailsActivity extends AppCompatActivity {

    private static final String TAG ="KINGSN" ;
    ActivityTransactionDetailsBinding binding;
    private String idImage,createdId,idName,idWebsite,amount,txnId,reqDate,approvalDate,status,payMethod,payImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_details);

        if (getIntent().hasExtra("createdId")) {
            Log.d(TAG, "onCreate: "+getIntent().getExtras());
            createdId=getIntent().getStringExtra("createdId");
            idImage=getIntent().getStringExtra("idimageurl");
            idName=getIntent().getStringExtra("idName");
            idWebsite=getIntent().getStringExtra("idWebsite");
            amount=getIntent().getStringExtra("idAmount");
            txnId=getIntent().getStringExtra("idTxnId");
            reqDate=getIntent().getStringExtra("idCreatedAt");
            idWebsite=getIntent().getStringExtra("idWebsite");
            approvalDate=getIntent().getStringExtra("idApprovalDate");
            status=getIntent().getStringExtra("idStatus");
            payMethod=getIntent().getStringExtra("payMethod");
            payImg=getIntent().getStringExtra("payImg");

            if(!Objects.equals(getIntent().getStringExtra("idimageurl"), "")) {
                Log.d("KINGSN", "onBindViewHolder: "+getIntent().getStringExtra("idimageurl"));
                Glide.with(this)
                        .load(idImage)
                        .placeholder(R.drawable.round_icon_bg)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.ivIdProfile);

                binding.tvIDTitle.setText(idName);
                binding.tvIDWebsite.setText(idWebsite);
                binding.tvDemoID.setText(amount);
                binding.tvRefNumber.setText(txnId);
                binding.tvRequestDate.setText(reqDate);
                binding.tvDemoIDDepo.setText(amount);
                binding.tvRefNumberDepo.setText(txnId);
                binding.tvRequestDateDepo.setText(approvalDate);

                if(status.equals("0")){
                    binding.progess.setImageDrawable(getResources().getDrawable(R.drawable.process));
                    binding.rejectv.setVisibility(View.GONE);
                    binding.tvStatus.setText("Pending");
                    binding.tvStatus1.setText("Pending");
                    binding.tvStatus.setTextColor(ContextCompat.getColor(this, R.color.warningColor));
                    binding.tvStatus1.setTextColor(ContextCompat.getColor(this, R.color.warningColor));
                }else if(status.equals("1")){
                    binding.progess.setImageDrawable(getResources().getDrawable(R.drawable.process_success));
                    binding.rejectv.setVisibility(View.GONE);
                    binding.tvStatus.setBackgroundResource(R.color.successColor);
                    binding.tvStatus.setTextColor(getResources().getColor(R.color.black));
                    binding.tvStatus.setText("Success");
                    binding.tvStatus1.setText("Success");
                    binding.tvStatus1.setBackgroundResource(R.color.successColor);
                    binding.tvStatus1.setTextColor(getResources().getColor(R.color.black));
                }else if(status.equals("2")){
                    binding.progess.setImageDrawable(getResources().getDrawable(R.drawable.process_error));
                    binding.rejectv.setVisibility(View.VISIBLE);
                    binding.tvStatus.setBackgroundResource(R.color.errorColor);
                    binding.tvStatus.setTextColor(getResources().getColor(R.color.white));
                    binding.tvStatus.setText("Rejected");
                    binding.tvStatus1.setText("Rejected");
                    binding.tvStatus1.setBackgroundResource(R.color.errorColor);
                    binding.tvStatus1.setTextColor(getResources().getColor(R.color.white));
                    binding.tvRejectedDate.setText(approvalDate);
                }

            }

        }

    }
}