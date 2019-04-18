package com.sinothk.manager.video.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sinothk.manager.video.DataUtils;
import com.sinothk.manager.video.bean.VideoSourceEntity;

public class AppVideoManagerDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_video_manager_demo_layout);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoManagerPlayActivity.start(AppVideoManagerDemoActivity.this, new VideoSourceEntity("音乐和艺术如何改变世界", DataUtils.VIDEO_URL_08));
            }
        });
    }
}
