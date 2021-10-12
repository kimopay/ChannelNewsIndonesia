package com.kimopay.channelnewsindonesia.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.kimopay.channelnewsindonesia.R;
import com.kimopay.channelnewsindonesia.utils.Constanta;

public class ViewImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        PhotoView photoView = (PhotoView) findViewById(R.id.img_zoom);
        ImageView img_close = findViewById(R.id.img_close);

        Bundle extras = getIntent().getExtras();
        String image_ads = extras.getString("image_ads");

        Glide.with(this)
                .load(Constanta.img_url + image_ads) // image url
                .placeholder(R.drawable.loading_animation) // any placeholder to load at start
                .override(200, 200) // resizing
                .centerCrop()
                .into(photoView);  // imageview object

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}