package com.jiek.mediadecorderdemo.audio_video;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jiek.mediadecorderdemo.R;

public class AudioVideoRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_video_record);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new CameraFragment()).commit();
        }
    }
}
