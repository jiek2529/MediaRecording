package com.jiek.mediadecorderdemo.capture_photo;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;

/**
 * Created by apple on 07/05/2017.
 */

public class CameraView extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceHolder mHolder;
    Camera mCamera;
    private static final String TAG = "CameraView";

    public CameraView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public CameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public boolean capture(Camera.PictureCallback callback) {
        if (mCamera != null) {
            mCamera.takePicture(null, null, callback);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        cameraOpen();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Camera.Parameters parameters = mCamera.getParameters();
        List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();
        Camera.Size cs = sizes.get(1);
        parameters.setPreviewSize(cs.width, cs.height);
        Log.e(TAG, "surfaceChanged: " + cs.width + "x" + cs.height);

        parameters.setJpegQuality(90);//设置jpg图片质量
//        Log.e(TAG, "surfaceChanged: "+parameters.getPictureSize().width+"x"+parameters.getPictureSize().height);
        parameters.setPictureSize(cs.width, cs.height);//设置jpg图片尺寸

        mCamera.setParameters(parameters);
        mCamera.setDisplayOrientation(90);//显示旋转90度
        mCamera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        cameraRelease();
    }

    public void cameraOpen() {
        mCamera = Camera.open();
        try {
            mCamera.setPreviewDisplay(mHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cameraRelease() {
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }
}
