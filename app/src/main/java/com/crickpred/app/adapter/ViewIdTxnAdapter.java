package com.crickpred.app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.crickpred.app.Models.View_IDTXN_Data;
import com.crickpred.app.R;

import java.util.List;


public class ViewIdTxnAdapter extends RecyclerView.Adapter<ViewIdTxnAdapter.MyViewHolder> {

    //private static final String TAG="RecyclerAdapter";
    public List<View_IDTXN_Data> view_idtxn_data;
    public final Context context;
    ViewIdTxnAdapter binding;

    // int count=0;
    public ViewIdTxnAdapter(List<View_IDTXN_Data> view_idtxn_data, Context context) {
        this.view_idtxn_data = view_idtxn_data;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Log.i(TAG, "onCreateViewHolder: " + count++);
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_myiddetails_item, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        View_IDTXN_Data viewIdtxnData = view_idtxn_data.get(position);

        if (viewIdtxnData.getTxnType().equals("0")) {
            holder.idName1.setText("Create ID");
            holder.amount.setText("+ "+viewIdtxnData.getAmount());
        } else if (viewIdtxnData.getTxnType().equals("1")) {
            holder.idName1.setText("Deposit to Id");
            holder.amount.setText("+ "+viewIdtxnData.getAmount());
        }else{
            holder.amount.setText("+ "+viewIdtxnData.getAmount());
        }
        if (viewIdtxnData.getTxnStatus().equals("0")) {
            holder.tvStatus.setText("Pending");
            holder.tvStatus.setTextColor(context.getResources().getColor(R.color.warningColor));
            holder.txnDate.setText(viewIdtxnData.getTxnDate());
        } else if (viewIdtxnData.getTxnStatus().equals("1")) {
            holder.tvStatus.setText("Verified");
            holder.tvStatus.setTextColor(context.getResources().getColor(R.color.successColor));
            holder.txnDate.setText(viewIdtxnData.getVerifiedDate());
        }

        holder.idName.setText(viewIdtxnData.getIdname());


        if(!viewIdtxnData.getIdimageurl().equals("")) {
            Log.d("KINGSN", "onBindViewHolder: "+viewIdtxnData.getIdimageurl());
            Glide.with(context)
                    .load(viewIdtxnData.getIdimageurl())
                    .placeholder(R.drawable.round_bg)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.txnimage);

        }







    }
  /*  public interface PassbookDataAdapterListner {
        void passbookDataAdapterListner(Passbook_Data dataPosition, int position);
    }*/
    @Override
    public int getItemCount() {
        return view_idtxn_data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout demoSection;
        Button arrowDownBtn;
        CardView passbookCard;
        de.hdodenhof.circleimageview.CircleImageView txnimage;
        TextView idName,idWebsite,tvStatus,idName1,idWebsite2,txnDate1,txnDate,tvStatus1,amount1,amount,date, name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            idName=itemView.findViewById(R.id.idName);
            idName1=itemView.findViewById(R.id.idName1);
            tvStatus=itemView.findViewById(R.id.tvStatus);
            txnDate=itemView.findViewById(R.id.txnDate);
            txnimage=itemView.findViewById(R.id.txnimage);
            amount=itemView.findViewById(R.id.amount);
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
