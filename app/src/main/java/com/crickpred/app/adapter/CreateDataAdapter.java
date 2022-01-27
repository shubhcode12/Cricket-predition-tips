package com.crickpred.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.textfield.TextInputEditText;
import com.crickpred.app.CreateIdActivity;
import com.crickpred.app.Models.Create_ID_Data;
import com.crickpred.app.R;
import com.crickpred.app.databinding.FragmentIdsBinding;


import java.util.List;




public class CreateDataAdapter extends RecyclerView.Adapter<CreateDataAdapter.MyViewHolder> {

    //private static final String TAG="RecyclerAdapter";
    List<Create_ID_Data> create_data;
    private final Context context;
    CreateDataAdapter binding;
    // int count=0;
    public CreateDataAdapter(List<Create_ID_Data> create_data, Context context) {
        this.create_data = create_data;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Log.i(TAG, "onCreateViewHolder: " + count++);
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_create_id_item, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Create_ID_Data createIdData = create_data.get(position);

        //Picasso.with(context)
        //      .load(listData
        //            .getImage_url())
        //  .into(holder.imageView);
        if (createIdData.getIdStatus().equals("1"))  {
           // holder.idName.setTextColor(ContextCompat.getColor(context, R.color.red));
            holder.idName.setText(createIdData.getIdName());
            holder.idWebsite.setText(createIdData.getIdWebsite());

            if(!createIdData.getIdImage().equals("")) {
                Log.d("KINGSN", "onBindViewHolder: "+createIdData.getIdImage());
                Glide.with(context)
                        .load(createIdData.getIdImage())
                        .placeholder(R.drawable.round_bg)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.idImage);

            }
            holder.tvDemoID.setText(createIdData.getDemoId());
            holder.tvDemoPass.setText(createIdData.getDemoPass());
            String url=createIdData.getDemoLink();
            holder.ivLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    open(url );
                }
            });

        }

        holder.btnCreateId.setOnClickListener(view -> {
            Intent intent = new Intent(context, CreateIdActivity.class);
            intent.putExtra("createId", createIdData.getId());
            intent.putExtra("idimageurl", createIdData.getIdImage());
            intent.putExtra("idname", createIdData.getIdName());
            intent.putExtra("idwebsite", createIdData.getIdWebsite());
            intent.putExtra("minRefill", createIdData.getMinRefill());
            intent.putExtra("minWithdrawal", createIdData.getMinWithdrawal());
            intent.putExtra("minMaintainBal", createIdData.getMinMaintainBal());
            intent.putExtra("maxWithdrawal", createIdData.getMaxWithdrawal());
            context.startActivity(intent);
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

    @Override
    public int getItemCount() {
        return create_data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout demoSection;
        Button arrowDownBtn, btnCreateId;
        de.hdodenhof.circleimageview.CircleImageView idImage;
        TextView idName,idWebsite, date, name,tvDemoID,tvDemoPass;
        ImageView ivLink;
        TextInputEditText depositCoinsEt;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            idName=itemView.findViewById(R.id.idName);
           idWebsite=itemView.findViewById(R.id.idWebsite);
            idImage=itemView.findViewById(R.id.idImage);
            arrowDownBtn = itemView.findViewById(R.id.arrowDown);
            demoSection = itemView.findViewById(R.id.demoSection);
            btnCreateId = itemView.findViewById(R.id.btnCreateId);
            tvDemoID=itemView.findViewById(R.id.tvDemoID);
            tvDemoPass=itemView.findViewById(R.id.tvDemoPass);
            ivLink=itemView.findViewById(R.id.ivLink);

            arrowDownBtn.setOnClickListener(view -> {
                demoSection.animate().
                        translationY(view.getHeight())
                        .setDuration(300);
                demoSection.setVisibility((demoSection.getVisibility() == View.VISIBLE)
                        ? View.GONE
                        : View.VISIBLE);

            });
        }
    }
}
