package com.kimopay.channelnewsindonesia.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.kimopay.channelnewsindonesia.R;
import com.kimopay.channelnewsindonesia.adapter.VideoAdapter;
import com.kimopay.channelnewsindonesia.data.model.Video;

import java.util.ArrayList;

public class VideoFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private View view;

    private RecyclerView rv_video;
    private ArrayList<Video> videoArrayList;
    private VideoAdapter videoAdapter;

    private SwipeRefreshLayout swipe_continer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video, container,false);

        rv_video = view.findViewById(R.id.rv_video);
        swipe_continer = view.findViewById(R.id.swipe_continer);

        swipe_continer = view.findViewById(R.id.swipe_continer);
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

        return view;

    }

    private void loadData() {
        rv_video.setLayoutManager(new LinearLayoutManager(getActivity()));
        videoAdapter = new VideoAdapter(getActivity(), videoArrayList);
        rv_video.setAdapter(videoAdapter);
        swipe_continer.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        loadData();
    }
}
