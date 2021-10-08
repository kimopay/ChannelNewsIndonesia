package com.kimopay.channelnewsindonesia.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.kimopay.channelnewsindonesia.R;
import com.kimopay.channelnewsindonesia.adapter.ViewPagerAdapterHome;

public class HomeFragment extends Fragment {

    private View view;
    private TabLayout tab_layout_home;
    private ViewPager view_pager_home;
    int no_of_categories=-1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container,false);


        view_pager_home = view.findViewById(R.id.view_pager_home);
        tab_layout_home =  view.findViewById(R.id.tab_layout_home);
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

        setDynamicFragmentToTabLayout();
        return view;

    }

    private void setDynamicFragmentToTabLayout() {
        tab_layout_home.addTab(tab_layout_home.newTab().setText("Semua"));
        for (int i = 0; i < 10; i++) {
            tab_layout_home.addTab(tab_layout_home.newTab().setText("Categorysasa: " + i));
        }
        ViewPagerAdapterHome mDynamicFragmentAdapter = new ViewPagerAdapterHome(getActivity().getSupportFragmentManager(), tab_layout_home.getTabCount());
        view_pager_home.setAdapter(mDynamicFragmentAdapter);
        view_pager_home.setCurrentItem(0);
    }

}
