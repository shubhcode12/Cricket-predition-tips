package com.crickpred.app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daasuu.bl.ArrowDirection;
import com.daasuu.bl.BubbleLayout;
import com.daasuu.bl.BubblePopupHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.crickpred.app.CloseIdActivity;
import com.crickpred.app.DepositIDActivity;
import com.crickpred.app.Models.My_ID_Data;
import com.crickpred.app.MyIdDetailsActivity;
import com.crickpred.app.R;
import com.crickpred.app.Util.GlobalVariables;

import java.util.List;
import java.util.Random;


public class MyDataAdapter extends RecyclerView.Adapter<MyDataAdapter.MyViewHolder> {

    //private static final String TAG="RecyclerAdapter";
   public List<My_ID_Data> myId_data;
    public final Context context;
    MyDataAdapter binding;

    // int count=0;
    public MyDataAdapter(List<My_ID_Data> myId_data, Context context) {
        this.myId_data = myId_data;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Log.i(TAG, "onCreateViewHolder: " + count++);
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_myid_item, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final My_ID_Data myIdData = myId_data.get(position);


        if (myIdData.getIdStatus().equals("1")) {
            // holder.idName.setTextColor(ContextCompat.getColor(context, R.color.red));
            holder.idName.setText(myIdData.getIdUsername());
            holder.idWebsite.setText(myIdData.getIdWebsite());

            if (!myIdData.getImg().equals("")) {
                Log.d("KINGSN", "onBindViewHolder: " + myIdData.getImg());
                Glide.with(context)
                        .load(myIdData.getImg())
                        .placeholder(R.drawable.round_bg)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.idImage);

            }
        /*    holder.tvDemoID.setText(myIdData.getDemoId());
            holder.tvDemoPass.setText(myIdData.getDemoPass());
            String url=myIdData.getDemoLink();
            holder.ivLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    open(url );
                }
            });*/

        }

        holder.idtv.setOnClickListener(view -> {
            Intent intent = new Intent(context, DepositIDActivity.class);
            intent.putExtra("createId", myIdData.getId());
            intent.putExtra("screenType", GlobalVariables.Deposit);
            intent.putExtra("idimageurl", myIdData.getImg());
            intent.putExtra("idname", myIdData.getIdName());
            intent.putExtra("idwebsite", myIdData.getIdWebsite());
            intent.putExtra("idUserName", myIdData.getIdUsername());
            intent.putExtra("minRefill", myIdData.getMinRefill());
            intent.putExtra("minWithdrawal", myIdData.getMinWithdrawal());
            intent.putExtra("minMaintainBal", myIdData.getMinMaintainBal());
            intent.putExtra("maxWithdrawal", myIdData.getMaxWithdrawal());
            intent.putExtra("idUsername", myIdData.getIdUsername());
            intent.putExtra("idPassword", myIdData.getIdPassword());
            context.startActivity(intent);
        });


        holder.iwtv.setOnClickListener(view -> {
            Intent intent = new Intent(context, DepositIDActivity.class);
            intent.putExtra("createId", myIdData.getId());
            intent.putExtra("screenType", GlobalVariables.Withdraw);
            intent.putExtra("idimageurl", myIdData.getImg());
            intent.putExtra("idname", myIdData.getIdName());
            intent.putExtra("idwebsite", myIdData.getIdWebsite());
            intent.putExtra("idUserName", myIdData.getIdUsername());
            intent.putExtra("minRefill", myIdData.getMinRefill());
            intent.putExtra("minWithdrawal", myIdData.getMinWithdrawal());
            intent.putExtra("minMaintainBal", myIdData.getMinMaintainBal());
            intent.putExtra("maxWithdrawal", myIdData.getMaxWithdrawal());
            intent.putExtra("idUsername", myIdData.getIdUsername());
            intent.putExtra("idPassword", myIdData.getIdPassword());
            context.startActivity(intent);
        });


        holder.tdot.setOnClickListener(view -> {
            int[] location = new int[2];
            view.getLocationInWindow(location);
            if (holder.random.nextBoolean()) {
                holder.bubbleLayout.setArrowDirection(ArrowDirection.TOP_RIGHT);
            } else {
                // holder.bubbleLayout.setArrowDirection(ArrowDirection.BOTTOM);
            }
            holder.popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0], view.getHeight() + location[1]);
        });

        holder.btnDepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DepositIDActivity.class);
                intent.putExtra("createId", myIdData.getId());
                intent.putExtra("screenType", GlobalVariables.Deposit);
                intent.putExtra("idimageurl", myIdData.getImg());
                intent.putExtra("idname", myIdData.getIdName());
                intent.putExtra("idwebsite", myIdData.getIdWebsite());
                intent.putExtra("idUserName", myIdData.getIdUsername());
                intent.putExtra("minRefill", myIdData.getMinRefill());
                intent.putExtra("minWithdrawal", myIdData.getMinWithdrawal());
                intent.putExtra("minMaintainBal", myIdData.getMinMaintainBal());
                intent.putExtra("maxWithdrawal", myIdData.getMaxWithdrawal());
                intent.putExtra("idUsername", myIdData.getIdUsername());
                intent.putExtra("idPassword", myIdData.getIdPassword());
                context.startActivity(intent);
            }
        });
        holder.pwithdraw.setOnClickListener(view -> {
            Intent intent = new Intent(context, DepositIDActivity.class);
            intent.putExtra("createId", myIdData.getId());
            intent.putExtra("screenType", GlobalVariables.Withdraw);
            intent.putExtra("idimageurl", myIdData.getImg());
            intent.putExtra("idname", myIdData.getIdName());
            intent.putExtra("idwebsite", myIdData.getIdWebsite());
            intent.putExtra("idUserName", myIdData.getIdUsername());
            intent.putExtra("minRefill", myIdData.getMinRefill());
            intent.putExtra("minWithdrawal", myIdData.getMinWithdrawal());
            intent.putExtra("minMaintainBal", myIdData.getMinMaintainBal());
            intent.putExtra("maxWithdrawal", myIdData.getMaxWithdrawal());
            intent.putExtra("idUsername", myIdData.getIdUsername());
            intent.putExtra("idPassword", myIdData.getIdPassword());
            context.startActivity(intent);
        });
        holder.ptxnDetails.setOnClickListener(view -> {
            Intent intent = new Intent(context, MyIdDetailsActivity.class);
            intent.putExtra("screenType", GlobalVariables.Withdraw);
            intent.putExtra("createId", myIdData.getId());
            intent.putExtra("idimageurl", myIdData.getImg());
            intent.putExtra("idname", myIdData.getIdName());
            intent.putExtra("idwebsite", myIdData.getIdWebsite());
            intent.putExtra("idBalance", myIdData.getIdBalance());
            intent.putExtra("idUserName", myIdData.getIdUsername());
            intent.putExtra("minRefill", myIdData.getMinRefill());
            intent.putExtra("minWithdrawal", myIdData.getMinWithdrawal());
            intent.putExtra("minMaintainBal", myIdData.getMinMaintainBal());
            intent.putExtra("maxWithdrawal", myIdData.getMaxWithdrawal());
            intent.putExtra("idUsername", myIdData.getIdUsername());
            intent.putExtra("idPassword", myIdData.getIdPassword());
            context.startActivity(intent);
        });

        holder.pchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View root = LayoutInflater.from(context).inflate(R.layout.dialog_change_password, view.findViewById(R.id.requestIdPassView));
                builder.setView(root);
                final AlertDialog alertDialog = builder.create();
                Button btnRequest = root.findViewById(R.id.btnRequestConfirm);
                btnRequest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "Id Name : " + myIdData.getIdName(), Toast.LENGTH_SHORT).show();
                    }
                });

                if (alertDialog.getWindow() != null){
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                alertDialog.show();
            }
        });

        holder.pclose.setOnClickListener(view -> {
            context.startActivity(new Intent(context, CloseIdActivity.class));
        });

    }

   public void open( String url) {
        Uri uri = Uri.parse("googlechrome://navigate?url=" + url);
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        if (i.resolveActivity(context.getPackageManager()) == null) {
            i.setData(Uri.parse(url));
        }
        context.startActivity(i);
    }

    public void openDipositIdActivity(){

    }

    @Override
    public int getItemCount() {
        return myId_data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout demoSection;
        Button arrowDownBtn, btnCreateId;
        de.hdodenhof.circleimageview.CircleImageView idImage;
        TextView idName,idWebsite, date, name,tvDemoID,tvDemoPass,iwtv,idtv;
        ImageView ivLink,tdot;
        TextInputEditText depositCoinsEt;
        PopupWindow popupWindow;
        BubbleLayout bubbleLayout;
        final Random random;
        TextView btnDepo,pclose;
        TextView pwithdraw;
        TextView ptxnDetails;
        TextView pchange;


        @SuppressLint("InflateParams")
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            idName=itemView.findViewById(R.id.idUserName);
           idWebsite=itemView.findViewById(R.id.tvMyIdWebsite);
            idImage=itemView.findViewById(R.id.ivMyId);
            idtv = itemView.findViewById(R.id.idtv);
            iwtv = itemView.findViewById(R.id.iwtv);
            tdot = itemView.findViewById(R.id.tdot);
            random = new Random();


             bubbleLayout = (BubbleLayout) LayoutInflater.from(itemView.getContext()).inflate(R.layout.dialog_myid_options, null);
             popupWindow = BubblePopupHelper.create(itemView.getContext(), bubbleLayout);
               btnDepo=bubbleLayout.findViewById(R.id.pdeposit);
                pchange=bubbleLayout.findViewById(R.id.pchange);
                pwithdraw=bubbleLayout.findViewById(R.id.pwithdraw);
                ptxnDetails=bubbleLayout.findViewById(R.id.ptxnDetails);
                pclose=bubbleLayout.findViewById(R.id.pclose);





            /*tvDemoID=itemView.findViewById(R.id.tvDemoID);
            tvDemoPass=itemView.findViewById(R.id.tvDemoPass);
            ivLink=itemView.findViewById(R.id.ivLink);*/
/*
            arrowDownBtn.setOnClickListener(view -> {
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
