package com.kimopay.channelnewsindonesia.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kimopay.channelnewsindonesia.R;
import com.kimopay.channelnewsindonesia.adapter.NewsDinamicAdapter;
import com.kimopay.channelnewsindonesia.adapter.VideoMoreAdapter;
import com.kimopay.channelnewsindonesia.model.Video;

import java.util.ArrayList;

public class DetailVideoActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ImageView img_back;
    private RecyclerView rv_video_more;
    private ArrayList<Video> videos;
    private VideoMoreAdapter videoMoreAdapter;
    private SwipeRefreshLayout swipe_continer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_video);

        img_back = findViewById(R.id.img_back);
        rv_video_more = findViewById(R.id.rv_video_more);

        swipe_continer = findViewById(R.id.swipe_continer);
        swipe_continer.setOnRefreshListener(this);
        swipe_continer.setColorSchemeResources(R.color.color_primary_cnn,
                android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_green_dark);
        swipe_continer.post(new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void loadData() {

        rv_video_more.setLayoutManager(new LinearLayoutManager(this));
        videoMoreAdapter = new VideoMoreAdapter(this, videos);
        rv_video_more.setAdapter(videoMoreAdapter);
        swipe_continer.setRefreshing(false);

    }

    @Override
    public void onRefresh() {
        loadData();
    }
}