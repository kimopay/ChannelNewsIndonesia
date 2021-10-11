package com.kimopay.channelnewsindonesia.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kimopay.channelnewsindonesia.R;
import com.kimopay.channelnewsindonesia.data.model.News;
import com.kimopay.channelnewsindonesia.data.response.ResponseCategory;
import com.kimopay.channelnewsindonesia.network.ApiClient;
import com.kimopay.channelnewsindonesia.network.ApiInterface;
import com.kimopay.channelnewsindonesia.ui.DetailNewsActivity;
import com.kimopay.channelnewsindonesia.utils.Constanta;
import com.kimopay.channelnewsindonesia.utils.DownloadImageTask;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreakingNewsAdapter extends RecyclerView.Adapter<BreakingNewsAdapter.MyHolderView> {

    private Context context;
    private ArrayList<News> newsArrayList;

    public BreakingNewsAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @NonNull
    @Override
    public BreakingNewsAdapter.MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        BreakingNewsAdapter.MyHolderView myHolderView = new BreakingNewsAdapter.MyHolderView(view);
        return myHolderView;
    }

    @Override
    public void onBindViewHolder(@NonNull BreakingNewsAdapter.MyHolderView holder, int position) {

        String id = newsArrayList.get(position).getId();
        String title = newsArrayList.get(position).getTitle();
        String image_news = newsArrayList.get(position).getImage();
        String category_id = newsArrayList.get(position).getCategory_id();
        String date = newsArrayList.get(position).getCreated_at();

        holder.title_news.setText(title);
        Glide.with(context)
                .load(Constanta.img_url + image_news) // image url
                .placeholder(R.drawable.loading_animation) // any placeholder to load at start
                .override(200, 200) // resizing
                .centerCrop()
                .into(holder.img_foto);  // imageview object
//        new DownloadImageTask(holder.img_foto).execute(Constanta.img_url + image_news);
        initSubTitleHeadline(holder, category_id, date);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailNewsActivity.class);
                intent.putExtra("id", id);
                context.startActivity(intent);
            }
        });

    }

    private void initSubTitleHeadline(MyHolderView holder, String category_id, String date) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseCategory> responseCategoryCall = apiInterface.getCategoryId(category_id);
        responseCategoryCall.enqueue(new Callback<ResponseCategory>() {
            @Override
            public void onResponse(Call<ResponseCategory> call, Response<ResponseCategory> response) {
                if (response.isSuccessful()){
                    String kode = response.body().getKode();
                    if (kode.equals("1")){
                        String category_name = response.body().getCategory_item().getCategory_name();
                        holder.tv_sub_title.setText(category_name+" | "+date);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseCategory> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class MyHolderView extends RecyclerView.ViewHolder {
        ImageView img_foto;
        ImageView img_bookmark;
        TextView title_news;
        TextView tv_sub_title;
        public MyHolderView(@NonNull View itemView) {
            super(itemView);

            img_foto = itemView.findViewById(R.id.img_foto);
            img_bookmark = itemView.findViewById(R.id.img_bookmark);
            title_news = itemView.findViewById(R.id.title_news);
            tv_sub_title = itemView.findViewById(R.id.tv_sub_title);

        }
    }
}
