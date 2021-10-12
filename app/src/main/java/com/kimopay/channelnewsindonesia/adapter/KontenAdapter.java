package com.kimopay.channelnewsindonesia.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kimopay.channelnewsindonesia.R;
import com.kimopay.channelnewsindonesia.data.model.Ads;
import com.kimopay.channelnewsindonesia.data.model.Konten;
import com.kimopay.channelnewsindonesia.ui.DetailNewsActivity;
import com.kimopay.channelnewsindonesia.ui.ViewImageActivity;
import com.kimopay.channelnewsindonesia.utils.Constanta;

import java.util.ArrayList;

public class KontenAdapter extends RecyclerView.Adapter<KontenAdapter.MyHolderView> {

    private Context context;
    private ArrayList<Ads> adsArrayList;

    public KontenAdapter(Context context, ArrayList<Ads> adsArrayList) {
        this.context = context;
        this.adsArrayList = adsArrayList;
    }

    @NonNull
    @Override
    public KontenAdapter.MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_konten, parent, false);
        KontenAdapter.MyHolderView myHolderView = new KontenAdapter.MyHolderView(view);
        return myHolderView;
    }

    @Override
    public void onBindViewHolder(@NonNull KontenAdapter.MyHolderView holder, int position) {
        String image_ads = adsArrayList.get(position).getImage();

        Glide.with(context)
                .load(Constanta.img_url + image_ads) // image url
                .placeholder(R.drawable.loading_animation) // any placeholder to load at start
                .override(200, 200) // resizing
                .centerCrop()
                .into(holder.img_konten);  // imageview object

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewImageActivity.class);
                intent.putExtra("image_ads", image_ads);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return adsArrayList.size();
    }

    public class MyHolderView extends RecyclerView.ViewHolder {
        ImageView img_konten;
        public MyHolderView(@NonNull View itemView) {
            super(itemView);
            img_konten = itemView.findViewById(R.id.img_konten);
        }
    }
}
