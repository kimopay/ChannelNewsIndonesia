package com.kimopay.channelnewsindonesia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kimopay.channelnewsindonesia.R;
import com.kimopay.channelnewsindonesia.model.News;

import java.util.ArrayList;

public class NewsMoreAdapter extends RecyclerView.Adapter<NewsMoreAdapter.MyHolderView> {

    private Context context;
    private ArrayList<News> newsArrayList;

    public NewsMoreAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @NonNull
    @Override
    public NewsMoreAdapter.MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_news_more, parent, false);
        NewsMoreAdapter.MyHolderView myHolderView = new NewsMoreAdapter.MyHolderView(view);
        return myHolderView;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsMoreAdapter.MyHolderView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyHolderView extends RecyclerView.ViewHolder {
        public MyHolderView(@NonNull View itemView) {
            super(itemView);
        }
    }
}
