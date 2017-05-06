package com.jiek.mediadecorderdemo.capture_photo;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jiek.mediadecorderdemo.R;

import java.io.FileOutputStream;
import java.io.IOException;

public class CapturePhotoActivity extends AppCompatActivity {
    CameraView mCameraView;
    private static final String TAG = "CapturePhotoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_photo);
        mCameraView = (CameraView) findViewById(R.id.cameraView);

        /*mCameraView.mCamera.setPreviewCallbackWithBuffer(new Camera.PreviewCallback() {
            @Override
            public void onPreviewFrame(byte[] data, Camera camera) {
                Log.e(TAG, "onPreviewFrame: "+data.length);//此处可获预览NV21原始数据
            }
        });*/
    }

    public void click_capture(View view) {
        mCameraView.capture(new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                try {
                    FileOutputStream fos;
                    fos = openFileOutput("capture.jpg", MODE_WORLD_WRITEABLE);

                    fos = new FileOutputStream("/mnt/sdcard/capture.jpg", false);//此时存下的图片是原始方向，使用时自行转向。

                    fos.write(data);
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
