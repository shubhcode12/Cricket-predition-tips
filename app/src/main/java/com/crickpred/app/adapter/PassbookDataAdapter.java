package com.crickpred.app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.crickpred.app.Models.Passbook_Data;
import com.crickpred.app.R;
import com.crickpred.app.TransactionDetailsActivity;

import java.util.ArrayList;
import java.util.List;


public class PassbookDataAdapter extends RecyclerView.Adapter<PassbookDataAdapter.MyViewHolder> {

    //private static final String TAG="RecyclerAdapter";
    List<Passbook_Data> passbook_data;
    private final Context context;
    PassbookDataAdapter passbookDataAdapter;
    private PassbookDataAdapterListner passbookDataAdapterListner;
    // int count=0;
    public PassbookDataAdapter(List<Passbook_Data> passbook_data, Context context) {
        this.passbook_data = passbook_data;
        this.context = context;
    }

    public PassbookDataAdapter(ArrayList<Passbook_Data> passbook_data, Context context, PassbookDataAdapterListner passbookDataAdapterListner) {
        this.passbook_data = passbook_data;
        this.context = context;
        this.passbookDataAdapterListner = passbookDataAdapterListner;
        //sharedPrefrence = SharedPrefrence.getInstance(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Log.i(TAG, "onCreateViewHolder: " + count++);
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_passbook_item, parent, false);
        return new MyViewHolder(view);
    }


    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Passbook_Data passbookData = passbook_data.get(position);

        //Picasso.with(context)
        //      .load(listData
        //            .getImage_url())
        //  .into(holder.imageView);
        if (passbookData.getTxnType().equals("0"))  {

            holder.idName.setText("Create ID");
            holder.idName1.setText("Deposit to Wallet");

            if(passbookData.getStatus().equals("0")){
                holder.tvStatus.setText("Pending");
                holder.tvStatus1.setText("Pending");
                holder.amount.setText("-"+passbookData.getAmount());
                holder.amount1.setText("+"+passbookData.getAmount());
                holder.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.warningColor));
                holder.tvStatus1.setTextColor(ContextCompat.getColor(context, R.color.warningColor));
                holder.txnDate.setText(passbookData.getCreatedDate());
                holder.txnDate1.setText(passbookData.getApprovalDate());
            }else if (passbookData.getStatus().equals("1")){
                holder.tvStatus.setText("Success");
                holder.tvStatus1.setText("Success");
                holder.amount.setText("-"+passbookData.getAmount());
                holder.amount1.setText("+"+passbookData.getAmount());
                holder.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.successColor));
                holder.tvStatus1.setTextColor(ContextCompat.getColor(context, R.color.successColor));
                holder.txnDate.setText(passbookData.getCreatedDate());
                holder.txnDate1.setText(passbookData.getApprovalDate());
            }else if (passbookData.getStatus().equals("2")) {
                holder.tvStatus.setText("Rejected");
                holder.tvStatus1.setText("Rejected");
                holder.amount.setText("-" + passbookData.getAmount());
                holder.amount1.setText("+" + passbookData.getAmount());
                holder.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.errorColor));
                holder.tvStatus1.setTextColor(ContextCompat.getColor(context, R.color.errorColor));
                holder.txnDate.setText(passbookData.getCreatedDate());
                holder.txnDate1.setText(passbookData.getApprovalDate());

            }
           // holder.idName.setTextColor(ContextCompat.getColor(context, R.color.red));


            if(!passbookData.getImage().equals("")) {
                Log.d("KINGSN", "onBindViewHolder: "+passbookData.getImage());
                Glide.with(context)
                        .load(passbookData.getImage())
                        .placeholder(R.drawable.round_bg)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.txnimage);

            }

        }


        holder.passbookCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, TransactionDetailsActivity.class);
                intent.putExtra("userName", passbookData.getUsername());
                intent.putExtra("mobile", passbookData.getMobile());
                intent.putExtra("idimageurl", passbookData.getImage());
                intent.putExtra("createdId", passbookData.getCreatedId());
                intent.putExtra("idAmount", passbookData.getAmount());
                intent.putExtra("idTxnId", passbookData.getTxnId());
                intent.putExtra("txnType", passbookData.getTxnType());
                intent.putExtra("idStatus", passbookData.getStatus());
                intent.putExtra("idCreatedAt", passbookData.getCreatedDate());
                intent.putExtra("idApprovalDate", passbookData.getApprovalDate());
                intent.putExtra("idAmount", passbookData.getAmount());
                intent.putExtra("idWebsite", passbookData.getIdWebsite());
                intent.putExtra("idName", passbookData.getIdName());
                context.startActivity(intent);
               // passbookDataAdapterListner.passbookDataAdapterListner(passbookData.get(getAdapterPosition()), getAdapterPosition());
            }
        });

    }
    public interface PassbookDataAdapterListner {
        void passbookDataAdapterListner(Passbook_Data dataPosition, int position);
    }
    @Override
    public int getItemCount() {
        return passbook_data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout demoSection;
        Button arrowDownBtn;
        CardView passbookCard;
        de.hdodenhof.circleimageview.CircleImageView txnimage;
        TextView idName,idWebsite,tvStatus,idName1,idWebsite2,txnDate1,txnDate,tvStatus1,amount1,amount,date, name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            passbookCard=itemView.findViewById(R.id.passbookCard);
            idName=itemView.findViewById(R.id.idName);
            idName1=itemView.findViewById(R.id.idName1);
            amount=itemView.findViewById(R.id.amount);
            amount1=itemView.findViewById(R.id.amount1);
            tvStatus=itemView.findViewById(R.id.tvStatus);
            tvStatus1=itemView.findViewById(R.id.tvStatus1);
            txnDate1=itemView.findViewById(R.id.txnDate1);
            txnDate=itemView.findViewById(R.id.txnDate);
            txnimage=itemView.findViewById(R.id.txnimage);
            arrowDownBtn = itemView.findViewById(R.id.arrowDown);
            demoSection = itemView.findViewById(R.id.demoSection);
          /*  arrowDownBtn.setOnClickListener(view -> {
                demoSection.animate().
                        translationY(view.getHeight())
                        .setDuration(300);
                demoSection.setVisibility((demoSection.getVisibility() == View.VISIBLE)
                        ? View.GONE
                        : View.VISIBLE);

            });*/
        }
    }
}
