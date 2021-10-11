package com.kimopay.channelnewsindonesia.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.kimopay.channelnewsindonesia.R;
import com.kimopay.channelnewsindonesia.adapter.CategoryAdapter;
import com.kimopay.channelnewsindonesia.adapter.NewsMoreAdapter;
import com.kimopay.channelnewsindonesia.data.model.Category;
import com.kimopay.channelnewsindonesia.data.model.News;
import com.kimopay.channelnewsindonesia.data.model.Reporter;
import com.kimopay.channelnewsindonesia.data.response.ResponseNews;
import com.kimopay.channelnewsindonesia.data.response.ResponseReporter;
import com.kimopay.channelnewsindonesia.network.ApiClient;
import com.kimopay.channelnewsindonesia.network.ApiInterface;
import com.kimopay.channelnewsindonesia.utils.Constanta;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailNewsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ImageView img_news;
    private TextView title_news;
    private TextView tv_reporter;
    private TextView tv_date_news;
    private TextView tv_view_news;
    private TextView tv_description;
    private String news_id;
    String news_desc = "s";

    private RecyclerView rv_kategori;
    private RecyclerView rv_news_more;
    private SwipeRefreshLayout swipe_continer;

    private CategoryAdapter categoryAdapter;
    private ArrayList<Category> categories;

    private NewsMoreAdapter newsMoreAdapter;
    private ArrayList<News> newsArrayList;
    private News news;
    private Reporter reporter;

    private CollapsingToolbarLayout collaps_toolbar;
    private AppBarLayout appbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("Detail Berita");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_chevron_left_24);

        Bundle extras = getIntent().getExtras();
        news_id = extras.getString("id");
        img_news = findViewById(R.id.img_news);
        title_news = findViewById(R.id.title_news);
        tv_reporter = findViewById(R.id.tv_reporter);
        tv_date_news = findViewById(R.id.tv_date_news);
        tv_view_news = findViewById(R.id.tv_view_news);

        tv_description = findViewById(R.id.tv_description);
        rv_kategori = findViewById(R.id.rv_kategori);
        rv_news_more = findViewById(R.id.rv_news_more);

        collaps_toolbar = findViewById(R.id.collaps_toolbar);

        appbar = findViewById(R.id.appbar);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collaps_toolbar.setTitle("Detail Berita");
                    isShow = true;
                } else if (isShow) {
                    collaps_toolbar.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }

            }
        });

        swipe_continer = findViewById(R.id.swipe_continer);
        swipe_continer.setOnRefreshListener(this);
        swipe_continer.setColorSchemeResources(R.color.color_primary_cnn,
                android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_green_dark);
        swipe_continer.post(new Runnable() {
            @Override
            public void run() {
                loadData(news_id);
            }
        });

    }

    private void loadData(String news_id) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseNews> responseNewsCall = apiInterface.getNewsId(news_id);
        responseNewsCall.enqueue(new Callback<ResponseNews>() {
            @Override
            public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                if (response.isSuccessful()) {
                    String kode = response.body().getKode();
                    if (kode.equals("1")) {
                        news = response.body().getNews_item();
                        initNewsData(news);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseNews> call, Throwable t) {

            }
        });


        // category
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_kategori.setLayoutManager(horizontalLayoutManagaer);
        categoryAdapter = new CategoryAdapter(this, categories);
        rv_kategori.setAdapter(categoryAdapter);

        // news more
        LinearLayoutManager horizontalLayoutManagaer2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_news_more.setLayoutManager(horizontalLayoutManagaer2);
        newsMoreAdapter = new NewsMoreAdapter(this, newsArrayList);
        rv_news_more.setAdapter(newsMoreAdapter);

        swipe_continer.setRefreshing(false);

    }

    private void initNewsData(News news) {
        String description = news.getDescription();
        String title = news.getTitle();
        String repoter_id = news.getReporter_id();
        String category_id = news.getCategory_id();
        String image_news = news.getImage();
        String date_news = news.getCreated_at();
        String views = news.getView();

        title_news.setText(title);
        tv_date_news.setText(date_news);
        tv_view_news.setText(views);
        Glide.with(this)
                .load(Constanta.img_url + image_news) // image url
                .placeholder(R.drawable.loading_animation) // any placeholder to load at start
                .override(200, 200) // resizing
                .centerCrop()
                .into(img_news);  // imageview object

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv_description.setText(Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT));
        } else {
            tv_description.setText(Html.fromHtml(description));
        }

        loadReporter(repoter_id);
        loadCategory(category_id);

    }

    private void loadCategory(String category_id) {

    }

    private void loadReporter(String repoter_id) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseReporter> responseReporterCall = apiInterface.getReporterId(repoter_id);
        responseReporterCall.enqueue(new Callback<ResponseReporter>() {
            @Override
            public void onResponse(Call<ResponseReporter> call, Response<ResponseReporter> response) {
                if (response.isSuccessful()) {
                    String kode = response.body().getKode();
                    if (kode.equals("1")) {
                        reporter = response.body().getReporters_item();
                        initReporter(reporter);
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseReporter> call, Throwable t) {

            }
        });

    }

    private void initReporter(Reporter reporter) {

        String nama = reporter.getName();
        tv_reporter.setText("Reporter : " + nama);

    }

    @Override
    public void onRefresh() {
        loadData(news_id);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}