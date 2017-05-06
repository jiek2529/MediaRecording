package com.jiek.mediadecorderdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jiek.mediadecorderdemo.MediaRecorder.MediaRecorderActivity;
import com.jiek.mediadecorderdemo.audio_video.AudioVideoRecordActivity;
import com.jiek.mediadecorderdemo.capture_photo.CapturePhotoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void jump(Class<? extends Activity> mActivityClass) {
        startActivity(new Intent(this, mActivityClass));
    }

    public void click_mediaRecorder(View view) {
        jump(MediaRecorderActivity.class);
    }

    public void click_livecamera(View view) {
        jump(LiveCameraActivity.class);
    }

    public void click_AV_to_file(View view) {
        jump(AudioVideoRecordActivity.class);
    }

    public void click_capturePhoto(View view) {
        jump(CapturePhotoActivity.class);
    }
}
