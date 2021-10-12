package com.kimopay.channelnewsindonesia.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.kimopay.channelnewsindonesia.R;
import com.kimopay.channelnewsindonesia.adapter.BreakingNewsAdapter;
import com.kimopay.channelnewsindonesia.adapter.KontenAdapter;
import com.kimopay.channelnewsindonesia.data.model.Ads;
import com.kimopay.channelnewsindonesia.data.model.Konten;
import com.kimopay.channelnewsindonesia.data.model.News;
import com.kimopay.channelnewsindonesia.data.response.ResponseAds;
import com.kimopay.channelnewsindonesia.data.response.ResponseCategory;
import com.kimopay.channelnewsindonesia.data.response.ResponseNews;
import com.kimopay.channelnewsindonesia.network.ApiClient;
import com.kimopay.channelnewsindonesia.network.ApiInterface;
import com.kimopay.channelnewsindonesia.utils.Constanta;
import com.kimopay.channelnewsindonesia.utils.DownloadImageTask;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllHomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipe_continer;

    //    private TextView tv1;
    private RecyclerView rv_konten;
    private BreakingNewsAdapter breakingNewsAdapter;
    private KontenAdapter kontenAdapter;
    private ArrayList<News> newsArrayList;
    private ArrayList<Konten> kontens;
    private ArrayList<Ads> adsArrayList;
    private RecyclerView rv_headline;
    private RecyclerView rv_headline2;

    private RelativeLayout continer_all_home;
    private ShimmerFrameLayout mShimmerViewContainer;

    //headline
    private ImageView img_headline;
    private TextView tv_title_headline;
    private TextView tv_sub_title;
    private TextView tv_view_news;
    private ImageView img_bookmark;
    private News news_headline;
    private String news_id_headline;

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

        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        continer_all_home = view.findViewById(R.id.continer_all_home);
        startShimmer();

        rv_headline = view.findViewById(R.id.rv_headline);
        rv_headline2 = view.findViewById(R.id.rv_headline2);
        img_headline = view.findViewById(R.id.img_headline);
        tv_title_headline = view.findViewById(R.id.tv_title_headline);
        tv_sub_title = view.findViewById(R.id.tv_sub_title);
        tv_view_news = view.findViewById(R.id.tv_view_news);
        img_bookmark = view.findViewById(R.id.img_bookmark);
        rv_konten = view.findViewById(R.id.rv_konten);

        swipe_continer = view.findViewById(R.id.swipe_continer);
        swipe_continer.setOnRefreshListener(this);
        swipe_continer.setColorSchemeResources(R.color.color_primary_cnn,
                android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_green_dark);
        swipe_continer.post(new Runnable() {
            @Override
            public void run() {
                loadDataHeadline();
                loadDataBreakingNews();
                loadDataBreakingNews2();
                loadDataAds("konten");

            }
        });

        return view;
    }

    private void loadDataAds(String category_ads) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseAds> responseAdsCall = apiInterface.getAdsCategory(category_ads);
        responseAdsCall.enqueue(new Callback<ResponseAds>() {
            @Override
            public void onResponse(Call<ResponseAds> call, Response<ResponseAds> response) {
                if (response.isSuccessful()){
                    String kode = response.body().getKode();
                    if (kode.equals("1")){
                        adsArrayList = (ArrayList<Ads>) response.body().getAds_category();
                        initAds(adsArrayList);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseAds> call, Throwable t) {

            }
        });


    }

    private void initAds(ArrayList<Ads> adsArrayList) {

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rv_konten.setLayoutManager(horizontalLayoutManagaer);
        kontenAdapter = new KontenAdapter(getActivity(), adsArrayList);
        rv_konten.setAdapter(kontenAdapter);
    }

    private void loadDataBreakingNews2() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseNews> responseNewsCall = apiInterface.getNewsAll("5", "10");
        responseNewsCall.enqueue(new Callback<ResponseNews>() {
            @Override
            public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                swipe_continer.setRefreshing(false);
                if (response.isSuccessful()) {
                    String kode = response.body().getKode();
                    if (kode.equals("1")) {
                        newsArrayList = response.body().getNews_result();
                        rv_headline2.setLayoutManager(new LinearLayoutManager(getActivity()));
                        breakingNewsAdapter = new BreakingNewsAdapter(getActivity(), newsArrayList);
                        rv_headline2.setAdapter(breakingNewsAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseNews> call, Throwable t) {
                swipe_continer.setRefreshing(false);

            }
        });

    }

    private void loadDataBreakingNews() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseNews> responseNewsCall = apiInterface.getNewsAll("0", "5");
        responseNewsCall.enqueue(new Callback<ResponseNews>() {
            @Override
            public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                swipe_continer.setRefreshing(false);
                if (response.isSuccessful()) {
                    String kode = response.body().getKode();
                    if (kode.equals("1")) {
                        newsArrayList = response.body().getNews_result();
                        rv_headline.setLayoutManager(new LinearLayoutManager(getActivity()));
                        breakingNewsAdapter = new BreakingNewsAdapter(getActivity(), newsArrayList);
                        rv_headline.setAdapter(breakingNewsAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseNews> call, Throwable t) {
                swipe_continer.setRefreshing(false);

            }
        });
    }

    private void startShimmer() {
        continer_all_home.setVisibility(View.GONE);
        mShimmerViewContainer.startShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.VISIBLE);
    }

    private void stopShimmer() {
        continer_all_home.setVisibility(View.VISIBLE);
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
    }

    private void loadDataHeadline() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseNews> responseNewsCall = apiInterface.getNewsRandom();
        responseNewsCall.enqueue(new Callback<ResponseNews>() {
            @Override
            public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                swipe_continer.setRefreshing(false);
                if (response.isSuccessful()) {
                    String kode = response.body().getKode();
                    if (kode.equals("1")) {
                        news_headline = response.body().getNews_item_random();
                        initHeadline(news_headline);
                    } else {
                        stopShimmer();
                    }
                } else {
                    stopShimmer();
                }


            }

            @Override
            public void onFailure(Call<ResponseNews> call, Throwable t) {
                swipe_continer.setRefreshing(false);
                stopShimmer();
            }
        });

    }

    private void initHeadline(News news_headline) {

        news_id_headline = news_headline.getId();
        String image_headline = news_headline.getImage();
        String title = news_headline.getTitle();
        String date = news_headline.getCreated_at();
        String description = news_headline.getDescription();
        String category_id = news_headline.getCategory_id();
        String viewer = news_headline.getView();
        String reporter_id = news_headline.getReporter_id();

        Log.e("URL_IMAGE", "respon : " + Constanta.img_url + image_headline);

        tv_title_headline.setText(title);
        tv_view_news.setText(viewer);

        Glide.with(getActivity())
                .load(Constanta.img_url + image_headline) // image url
                .placeholder(R.drawable.loading_animation) // any placeholder to load at start
                .override(200, 200) // resizing
                .centerCrop()
                .into(img_headline);  // imageview object
//        new DownloadImageTask(img_headline).execute(Constanta.img_url + image_headline);
//        Glide.with(getActivity())
//                .load(Constanta.img_url + image_headline)
//                .into(img_headline);

        initSubTitleHeadline(category_id, date);
        stopShimmer();

    }

    private void initSubTitleHeadline(String category_id, String date) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseCategory> responseCategoryCall = apiInterface.getCategoryId(category_id);
        responseCategoryCall.enqueue(new Callback<ResponseCategory>() {
            @Override
            public void onResponse(Call<ResponseCategory> call, Response<ResponseCategory> response) {
                if (response.isSuccessful()) {
                    String kode = response.body().getKode();
                    if (kode.equals("1")) {
                        String category_name = response.body().getCategory_item().getCategory_name();
                        tv_sub_title.setText(category_name + " | " + date);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseCategory> call, Throwable t) {

            }
        });

    }

    @Override
    public void onRefresh() {
        loadDataHeadline();
        loadDataBreakingNews();
        loadDataBreakingNews2();
        loadDataAds("konten");


    }
}
