package com.kimopay.channelnewsindonesia.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kimopay.channelnewsindonesia.R;
import com.kimopay.channelnewsindonesia.adapter.BreakingNewsAdapter;
import com.kimopay.channelnewsindonesia.adapter.KontenAdapter;
import com.kimopay.channelnewsindonesia.model.Konten;
import com.kimopay.channelnewsindonesia.model.News;

import java.util.ArrayList;

public class AllHomeFragment extends Fragment {

    //
//    private TextView tv1;
    private RecyclerView rv_headline;
    private RecyclerView rv_konten;
    private BreakingNewsAdapter breakingNewsAdapter;
    private KontenAdapter kontenAdapter;
    private ArrayList<News> newsArrayList;
    private ArrayList<Konten> kontens;

    public static AllHomeFragment newInstance() {
        return new AllHomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_home, container, false);
        initViews(view);

        rv_headline.setLayoutManager(new LinearLayoutManager(getActivity()));
        breakingNewsAdapter = new BreakingNewsAdapter(getActivity(), newsArrayList);
        rv_headline.setAdapter(breakingNewsAdapter);

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rv_konten.setLayoutManager(horizontalLayoutManagaer);
        kontenAdapter = new KontenAdapter(getActivity(), kontens);
        rv_konten.setAdapter(kontenAdapter);


        return view;
    }

    private void initViews(View view) {
        rv_headline = view.findViewById(R.id.rv_headline);
        rv_konten = view.findViewById(R.id.rv_konten);
//        tv1=view.findViewById(R.id.tv1);
//        tv1.setText(String.valueOf("Semua :  "+getArguments().getInt("position")));
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
