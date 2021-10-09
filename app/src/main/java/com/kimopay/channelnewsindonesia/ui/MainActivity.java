package com.kimopay.channelnewsindonesia.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.kimopay.channelnewsindonesia.R;
import com.kimopay.channelnewsindonesia.ui.fragment.HomeFragment;
import com.kimopay.channelnewsindonesia.ui.fragment.UserFragment;
import com.kimopay.channelnewsindonesia.ui.fragment.VideoFragment;

public class MainActivity extends AppCompatActivity {

    BubbleNavigationLinearView bubbleNavigation;
    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFragment(new HomeFragment());

        container = findViewById(R.id.container);
        bubbleNavigation = findViewById(R.id.bubbleNavigation);
        bubbleNavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                if (position==0){
                    openFragment(new HomeFragment());
                } else if (position==1){
                    openFragment(new VideoFragment());
                } else {
                    openFragment(new UserFragment());
                }
            }
        });

    }
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}