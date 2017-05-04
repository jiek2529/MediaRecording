package com.jiek.mediaprojectiondemo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button record_btn;

    private static final int PERMISSION_CODE = 101;
    private static final int STORAGE_REQUEST_CODE = 102;
    private static final int AUDIO_REQUEST_CODE = 103;
    private int mScreenDensity;
    private MediaProjectionManager mProjectionManager;
    //    private static final int DISPLAY_WIDTH = 960;
//    private static final int DISPLAY_HEIGHT = 1280;
    Point point = new Point();
    private MediaProjection mMediaProjection;
    private VirtualDisplay mVirtualDisplay;
    private MediaProjectionCallback mMediaProjectionCallback;
    private MediaRecorder mMediaRecorder;

    @SuppressLint("SdCardPath")
    private final static String captureFileName = "/sdcard/capture.mp4";
    private ImageReader mImageReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPoint();
        setContentView(R.layout.activity_main);
        record_btn = (Button) findViewById(R.id.record);

        //设置当前Activity不被录制
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);


        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_REQUEST_CODE);
        } else granted = 1;
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, AUDIO_REQUEST_CODE);
        } else {
            if (granted == 1) {
                granted = 3;
            } else {
                granted = 2;
            }
        }
        if (granted == 3) {
            initProjectionManager();
        }
    }

    int granted = 0;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_REQUEST_CODE) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                if (granted == 2) granted = 3;
                else granted = 1;
            }
        }
        if (requestCode == AUDIO_REQUEST_CODE) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                if (granted == 1) granted = 3;
                else granted = 2;
            }
        }
        Toast.makeText(this, "granted: " + granted, Toast.LENGTH_SHORT).show();
        if (granted == 3) initProjectionManager();
    }

    private void initProjectionManager() {
        mProjectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);

        mMediaProjectionCallback = new MediaProjectionCallback();

        //初始化视频编码器
        mMediaRecorder = new MediaRecorder();
        initRecorder(captureFileName);
        prepareRecorder();
    }

    boolean recording = false;

    /**
     * 最简易录制屏幕
     *
     * @param view
     */
    public void click_sampleCapture(View view) {
        if (granted != 3) return;
        if (!recording) {
            shareScreen();
        } else {
            stopRecorder();
        }
        recording = !recording;
        record_btn.setText(recording ? R.string.start : R.string.stop);
    }

    @Override
    protected void onResume() {
        super.onResume();
        record_btn.setText(recording ? R.string.start : R.string.stop);
    }


    private void initPoint() {
        getWindowManager().getDefaultDisplay().getSize(point);
        if (point.x < 320) {
        }
        point.x = 960;
        point.y = 1080;

        //获取屏幕的分辨率
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mScreenDensity = metrics.densityDpi;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMediaProjection != null) {
            mMediaProjection.stop();
            mMediaProjection = null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != PERMISSION_CODE) {
            Log.e(TAG, "Unknown request code: " + requestCode);
            return;
        }
        if (resultCode != RESULT_OK) {
            Toast.makeText(this, "Screen Cast Permission Denied",
                    Toast.LENGTH_SHORT).show();
            record_btn.setClickable(false);
            return;
        }

        //这里的图片格式是RGBA_8888,这里的图片大小和下面的createVirtualPlayer的尺寸要保持一致就可以了
        mImageReader = ImageReader.newInstance(
                point.x, point.y,
                PixelFormat.RGBA_8888, 20);

        mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {
                try {
                    Image.Plane[] planes = reader.acquireLatestImage().getPlanes();
                    ByteBuffer buffer = planes[0].getBuffer();
                    int pixelStride = planes[0].getPixelStride();
                    int rowStride = planes[0].getRowStride();
//                int rowPadding = rowStride - pixelStride * width;
//                int bitmapWidth = width + rowPadding / pixelStride;
                    Log.e(TAG, "onImageAvailable: " + planes.length + " >>> pixelStride=" + pixelStride + " :: pixelStride=" + rowStride);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Handler());

        mMediaProjection = mProjectionManager.getMediaProjection(resultCode, data);
        mMediaProjection.registerCallback(mMediaProjectionCallback, null);
        mVirtualDisplay = createVirtualDisplay();
        mMediaRecorder.start();
        t("onActivityResult 权限请求成功。");
    }

    private void stopRecorder() {
        mMediaRecorder.stop();
        mMediaRecorder.reset();
    }

    private void shareScreen() {
        if (mMediaProjection == null) {
            startActivityForResult(mProjectionManager.createScreenCaptureIntent(), PERMISSION_CODE);
            return;
        }
        mVirtualDisplay = createVirtualDisplay();
        mMediaRecorder.start();
    }

    private void stopScreenSharing() {
        if (mVirtualDisplay == null) {
            return;
        }
        mVirtualDisplay.release();
    }

    private VirtualDisplay createVirtualDisplay() {
        /**
         * 创建虚拟画面
         * 第一个参数：虚拟画面名称
         * 第二个参数：虚拟画面的宽度
         * 第三个参数：虚拟画面的高度
         * 第四个参数：虚拟画面的标志
         * 第五个参数：虚拟画面输出的Surface
         * 第六个参数：虚拟画面回调接口
         */
        return mMediaProjection
                .createVirtualDisplay("MainActivity", point.x,
                        point.y, mScreenDensity,
                        DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
//                        mMediaRecorder.getSurface(),
                        mImageReader.getSurface(),
                        new VirtualDisplay.Callback() {
                            @Override
                            public void onPaused() {
                                super.onPaused();
                                t("VirtualDisplay.Callback  >> onPaused");
                            }

                            @Override
                            public void onResumed() {
                                super.onResumed();
                                t("VirtualDisplay.Callback  >> onResumed");
                            }

                            @Override
                            public void onStopped() {
                                super.onStopped();
                                t("VirtualDisplay.Callback  >> onStopped");
                            }
                        } /* Callbacks */, null /* Handler */);
    }

    private class MediaProjectionCallback extends MediaProjection.Callback {
        @Override
        public void onStop() {
            if (record_btn.isClickable()) {
                record_btn.setClickable(false);
                stopRecorder();
                Log.v(TAG, "Recording Stopped");
                initRecorder(captureFileName);
                prepareRecorder();
            }
            mMediaProjection = null;
            stopScreenSharing();
            Log.i(TAG, "MediaProjection Stopped");
        }
    }

    private void prepareRecorder() {
        try {
            mMediaRecorder.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            finish();
        } catch (IOException e) {
            e.printStackTrace();
            finish();
        }
    }

    private void initRecorder(String path) {
        /**
         *  视频编码格式：default，H263，H264，MPEG_4_SP
         获得视频资源：default，CAMERA
         音频编码格式：default，AAC，AMR_NB，AMR_WB
         获得音频资源：defalut，camcorder，mic，voice_call，voice_communication,
         voice_downlink,voice_recognition, voice_uplink
         输出方式：amr_nb，amr_wb,default,mpeg_4,raw_amr,three_gpp
         */
        //设置音频源
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //设置视频源：Surface和Camera 两种
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
        //设置视频输出格式
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        //设置视频编码格式
        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        //设置音频编码格式
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        //设置视频编码的码率
        mMediaRecorder.setVideoEncodingBitRate(5 * 1024 * 1024);//(512 * 1000);
        //设置视频编码的帧率
        mMediaRecorder.setVideoFrameRate(30);
        //设置视频尺寸大小
        mMediaRecorder.setVideoSize(point.x, point.y);
        //设置视频输出路径
        mMediaRecorder.setOutputFile(path);

    }

    public void t(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
