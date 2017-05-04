package com.jiek.mediadecorderdemo;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.TextureView;
import android.view.View;
import android.widget.Toast;

import com.jiek.mediadecorderdemo.common.media.CameraHelper;

import java.io.IOException;

import static android.hardware.Camera.Parameters.FLASH_MODE_OFF;
import static android.hardware.Camera.Parameters.FLASH_MODE_TORCH;

/**
 * 使用TextureView展示摄像头;取自TextureView docs的示例
 * <p>
 * 增加切换摄像头功能，和DisplayOrientation旋转，闪光灯开关
 */
public class LiveCameraActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener {
    private static final String TAG = "LiveCameraActivity";
    TextureView mTextureView;
    private Camera mCamera;

    private int cameraId = 0;//0 后  1 前
    private boolean flashFlag = false;
    int degree = 90;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_camera);
        mTextureView = (TextureView) findViewById(R.id.textureView2);
        mTextureView.setSurfaceTextureListener(this);
    }

    /**
     * 显示方向选择
     *
     * @param view
     */
    public void click_switOrientation(View view) {
        try {
            degree += 90;
            degree %= 360;
            mCamera.setDisplayOrientation(degree);
            Toast.makeText(this, "degree: " + degree, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 闪光灯开关
     *
     * @param view
     */
    public void click_switchFlash(View view) {
        if (cameraId == 0) {
            flashFlag = !flashFlag;
            Toast.makeText(this, flashFlag + "", Toast.LENGTH_SHORT).show();
            Camera.Parameters params = mCamera.getParameters();
            params.setFlashMode(flashFlag ? FLASH_MODE_TORCH : FLASH_MODE_OFF);
            mCamera.setParameters(params);
        }
    }

    /**
     * 切换摄像头
     *
     * @param view
     */
    public void click_switchcamera(View view) {
        cameraId ^= 1;
        previewCamera(mTextureView.getSurfaceTexture());
    }

    private void previewCamera(SurfaceTexture surface) {
        cameraRelease();
        try {
            mCamera = cameraId == 0 ? CameraHelper.getDefaultBackFacingCameraInstance() : CameraHelper.getDefaultFrontFacingCameraInstance();// 或使用 Camera.open();
            mCamera.setPreviewTexture(surface);
            mCamera.setDisplayOrientation(degree);
            mCamera.startPreview();
        } catch (IOException ioe) {
            // Something bad happened
        }
    }

    private void cameraRelease() {
        try {
            mCamera.stopPreview();
            mCamera.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        previewCamera(surface);
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        // Ignored, Camera does all the work for us
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        cameraRelease();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }
}
