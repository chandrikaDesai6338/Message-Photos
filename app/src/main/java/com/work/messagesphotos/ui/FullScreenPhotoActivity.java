package com.work.messagesphotos.ui;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.work.messagesphotos.R;
import com.work.messagesphotos.adapter.FullImageAdapter;

import java.util.List;

public class FullScreenPhotoActivity extends AppCompatActivity {


    private FullImageAdapter adapter;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_photo);

        viewPager = findViewById(R.id.pager);


        Intent i = getIntent();
        List<String> photoUrl = i.getStringArrayListExtra("photoUrl");
        int position = i.getIntExtra("position", 0);

        adapter = new FullImageAdapter(FullScreenPhotoActivity.this, photoUrl);

        viewPager.setAdapter(adapter);

        // displaying selected image first
        viewPager.setCurrentItem(position);
    }
}

