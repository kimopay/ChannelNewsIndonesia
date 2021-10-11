package com.kimopay.channelnewsindonesia.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kimopay.channelnewsindonesia.R;
import com.kimopay.channelnewsindonesia.adapter.NewsDinamicAdapter;
import com.kimopay.channelnewsindonesia.data.model.News;

import java.util.ArrayList;

public class DinamicHomeFragment extends Fragment {

    private TextView tv1;
    private RecyclerView rv_news_dinamic;
    private NewsDinamicAdapter newsDinamicAdapter;
    private ArrayList<News> newsArrayList;


    public static DinamicHomeFragment newInstance() {
        return new DinamicHomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dinamic, container, false);
        initViews(view);

        rv_news_dinamic.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsDinamicAdapter = new NewsDinamicAdapter(getActivity(), newsArrayList);
        rv_news_dinamic.setAdapter(newsDinamicAdapter);

//        Toast.makeText(getActivity(), String.valueOf(getArguments().getInt("position")), Toast.LENGTH_SHORT).show();

        return view;
    }

    private void initViews(View view) {

        tv1=view.findViewById(R.id.tv1);
        rv_news_dinamic=view.findViewById(R.id.rv_news_dinamic);
        tv1.setText(String.valueOf("Category :  "+getArguments().getInt("position")));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
