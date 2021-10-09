package com.kimopay.channelnewsindonesia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kimopay.channelnewsindonesia.R;
import com.kimopay.channelnewsindonesia.model.Video;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyHolderView> {

    private Context context;
    private ArrayList<Video> videos;

    public VideoAdapter(Context context, ArrayList<Video> videos) {
        this.context = context;
        this.videos = videos;
    }

    @NonNull
    @Override
    public VideoAdapter.MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        VideoAdapter.MyHolderView myHolderView = new VideoAdapter.MyHolderView(view);
        return myHolderView;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.MyHolderView holder, int position) {

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
