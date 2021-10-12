package com.kimopay.channelnewsindonesia.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.kimopay.channelnewsindonesia.R;
import com.kimopay.channelnewsindonesia.adapter.KontenAdapter;
import com.kimopay.channelnewsindonesia.adapter.NewsDinamicAdapter;
import com.kimopay.channelnewsindonesia.data.model.Category;
import com.kimopay.channelnewsindonesia.data.model.News;
import com.kimopay.channelnewsindonesia.data.response.ResponseCategory;
import com.kimopay.channelnewsindonesia.data.response.ResponseNews;
import com.kimopay.channelnewsindonesia.network.ApiClient;
import com.kimopay.channelnewsindonesia.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DinamicHomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private int fragment_numbar;

    //    private TextView tv1;
    private RecyclerView rv_news_dinamic;
    private NewsDinamicAdapter newsDinamicAdapter;
    private ArrayList<News> newsArrayList;
    private ArrayList<Category> categories;

    private RelativeLayout rl_refresh;
    private LinearLayout ll_empty;
    private SwipeRefreshLayout swipe_continer;

    private RelativeLayout continer_news_dinamic;
    private ShimmerFrameLayout shimmer_view_container;

    private List<String> list_id_category = new ArrayList<String>();

    public static DinamicHomeFragment newInstance() {
        return new DinamicHomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dinamic, container, false);

//        tv1=view.findViewById(R.id.tv1);
        rv_news_dinamic = view.findViewById(R.id.rv_news_dinamic);
        ll_empty = view.findViewById(R.id.ll_empty);
        rl_refresh = view.findViewById(R.id.rl_refresh);
        shimmer_view_container = view.findViewById(R.id.shimmer_view_container);
        continer_news_dinamic = view.findViewById(R.id.continer_news_dinamic);

        startShimmer();

        swipe_continer = view.findViewById(R.id.swipe_continer);
        swipe_continer.setOnRefreshListener(this);
        swipe_continer.setColorSchemeResources(R.color.color_primary_cnn,
                android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_green_dark);
        swipe_continer.post(new Runnable() {
            @Override
            public void run() {
                startShimmer();
                fragment_numbar = getArguments().getInt("position") - 1;
                loadCategory();
            }
        });

        rl_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startShimmer();
                fragment_numbar = getArguments().getInt("position") - 1;
                loadCategory();
            }
        });

        return view;
    }

    private void startShimmer() {
        continer_news_dinamic.setVisibility(View.GONE);
        shimmer_view_container.startShimmerAnimation();
        shimmer_view_container.setVisibility(View.VISIBLE);
    }

    private void stopShimmer() {
        continer_news_dinamic.setVisibility(View.VISIBLE);
        shimmer_view_container.stopShimmerAnimation();
        shimmer_view_container.setVisibility(View.GONE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragment_numbar = getArguments().getInt("position") - 1;
    }

    private void loadCategory() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseCategory> responseCategoryCall = apiInterface.getCategoryAll();
        responseCategoryCall.enqueue(new Callback<ResponseCategory>() {
            @Override
            public void onResponse(Call<ResponseCategory> call, Response<ResponseCategory> response) {
                if (response.isSuccessful()) {
                    String kode = response.body().getKode();
                    if (kode.equals("1")) {
                        categories = (ArrayList<Category>) response.body().getCategory_result();
                        initCategory(categories);
                    } else {
                        swipe_continer.setRefreshing(false);
                        Log.e("Category", "Response : " + response.message());
                        stopShimmer();
                        dataIsEmpty();
                    }

                } else {
                    swipe_continer.setRefreshing(false);
                    Log.e("Category", "Response : " + response.message());
                    stopShimmer();
                    dataIsEmpty();
                }

            }

            @Override
            public void onFailure(Call<ResponseCategory> call, Throwable t) {
                swipe_continer.setRefreshing(false);
                Log.e("Category", "Response : " + t.getMessage());
                stopShimmer();
                dataIsEmpty();
            }
        });

    }

    private void initCategory(ArrayList<Category> categories) {
        for (int a = 0; a < categories.size(); a++) {
            list_id_category.add(categories.get(a).getId());
        }
        loadCategoriId(list_id_category);
    }

    private void loadCategoriId(List<String> list_id_category) {
        String send_id = list_id_category.get(fragment_numbar);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseNews> responseCategoryCall = apiInterface.getNewsCategory(send_id);
        responseCategoryCall.enqueue(new Callback<ResponseNews>() {
            @Override
            public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                swipe_continer.setRefreshing(false);
                if (response.isSuccessful()) {
                    stopShimmer();
                    String kode = response.body().getKode();
                    if (kode.equals("1")) {
                        newsArrayList = response.body().getNews_result_category();
                        if (newsArrayList.size() < 1) {
                            dataIsEmpty();
                        } else {
                            dataIsReady();
                            initNews(newsArrayList);
                        }
                    } else {
                        stopShimmer();
                        dataIsEmpty();
                    }
                } else {
                    stopShimmer();
                    dataIsEmpty();
                }
            }

            @Override
            public void onFailure(Call<ResponseNews> call, Throwable t) {
                swipe_continer.setRefreshing(false);
                stopShimmer();
                dataIsEmpty();
            }
        });

    }

    private void initNews(ArrayList<News> newsArrayList) {
        rv_news_dinamic.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsDinamicAdapter = new NewsDinamicAdapter(getActivity(), newsArrayList);
        rv_news_dinamic.setAdapter(newsDinamicAdapter);
    }

    private void dataIsReady() {
        rv_news_dinamic.setVisibility(View.VISIBLE);
        ll_empty.setVisibility(View.GONE);
    }

    private void dataIsEmpty() {
        rv_news_dinamic.setVisibility(View.GONE);
        ll_empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        startShimmer();
        fragment_numbar = getArguments().getInt("position") - 1;
        loadCategory();
    }
}
