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

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class MyHolderView extends RecyclerView.ViewHolder {
        public MyHolderView(@NonNull View itemView) {
            super(itemView);
        }
    }
}
