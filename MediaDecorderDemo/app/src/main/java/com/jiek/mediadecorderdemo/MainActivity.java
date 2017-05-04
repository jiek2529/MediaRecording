package com.jiek.mediadecorderdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jiek.mediadecorderdemo.MediaRecorder.MediaRecorderActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click_mediaRecorder(View view) {
        jump(MediaRecorderActivity.class);
    }

    private void jump(Class<? extends Activity> mActivityClass) {
        startActivity(new Intent(this, mActivityClass));
    }
}
