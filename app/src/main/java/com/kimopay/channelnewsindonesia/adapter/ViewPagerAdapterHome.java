package com.kimopay.channelnewsindonesia.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.kimopay.channelnewsindonesia.ui.fragment.AllHomeFragment;
import com.kimopay.channelnewsindonesia.ui.fragment.DinamicHomeFragment;

public class ViewPagerAdapterHome extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    public ViewPagerAdapterHome(FragmentManager fm, int NumOfTabs) {
        super(fm, NumOfTabs);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag;
        if (position < 1) {
            Bundle b = new Bundle();
            b.putInt("position", position);
            frag = AllHomeFragment.newInstance();
            frag.setArguments(b);
        } else {
            Bundle b = new Bundle();
            b.putInt("position", position);
            frag = DinamicHomeFragment.newInstance();
            frag.setArguments(b);
        }
        return frag;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}
