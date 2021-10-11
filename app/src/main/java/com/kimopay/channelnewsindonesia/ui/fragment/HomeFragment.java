package com.kimopay.channelnewsindonesia.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.kimopay.channelnewsindonesia.R;
import com.kimopay.channelnewsindonesia.adapter.ViewPagerAdapterHome;
import com.kimopay.channelnewsindonesia.data.model.Category;
import com.kimopay.channelnewsindonesia.data.response.ResponseCategory;
import com.kimopay.channelnewsindonesia.network.ApiClient;
import com.kimopay.channelnewsindonesia.network.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private String TAG = "HomeFragment";

    private View view;
    private TabLayout tab_layout_home;
    private ViewPager view_pager_home;
    int no_of_categories = -1;

    private ArrayList<Category> categories;

    private ShimmerFrameLayout mShimmerViewContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        view_pager_home = view.findViewById(R.id.view_pager_home);
        tab_layout_home = view.findViewById(R.id.tab_layout_home);
//        view_pager_home.setOffscreenPageLimit(5);

        view_pager_home.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout_home));
        tab_layout_home.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                view_pager_home.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        loadDataCategory();
        return view;

    }

    private void loadDataCategory() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseCategory> responseCategoryCall = apiInterface.getCategoryAll();
        responseCategoryCall.enqueue(new Callback<ResponseCategory>() {
            @Override
            public void onResponse(Call<ResponseCategory> call, Response<ResponseCategory> response) {
                if (response.isSuccessful()) {
                    String kode = response.body().getKode();
                    if (kode.equals("1")) {
                        categories = (ArrayList<Category>) response.body().getCategory_result();
                        setDynamicFragmentToTabLayout(categories);
                    } else {
                        Log.e(TAG, "Response : " + response.message());
                        setDynamicFragmentToTabLayoutSingle();
                    }

                } else {
                    Log.e(TAG, "Response : " + response.message());
                    setDynamicFragmentToTabLayoutSingle();
                }

            }

            @Override
            public void onFailure(Call<ResponseCategory> call, Throwable t) {
                Log.e(TAG, "Response : " + t.getMessage());
                setDynamicFragmentToTabLayoutSingle();
            }
        });

    }

    private void showSnackBar(String message) {
        Snackbar.make(view.findViewById(R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }

    private void setDynamicFragmentToTabLayoutSingle() {
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
        tab_layout_home.addTab(tab_layout_home.newTab().setText("Semua"));
    }

    private void setDynamicFragmentToTabLayout(ArrayList<Category> categories) {
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
        tab_layout_home.addTab(tab_layout_home.newTab().setText("Semua"));
        for (int i = 0; i < categories.size(); i++) {
            tab_layout_home.addTab(tab_layout_home.newTab().setText(categories.get(i).getCategory_name()));
        }
        ViewPagerAdapterHome mDynamicFragmentAdapter = new ViewPagerAdapterHome(getActivity().getSupportFragmentManager(), tab_layout_home.getTabCount());
        view_pager_home.setAdapter(mDynamicFragmentAdapter);
        view_pager_home.setCurrentItem(0);
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        mShimmerViewContainer.startShimmerAnimation();
//        loadDataCategory();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mShimmerViewContainer.stopShimmerAnimation();
//    }
}
