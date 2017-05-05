package com.jiek.ffmpeg;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);
        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI()
                +"\n\nurlprotocolinfo:"+urlprotocolinfo()
                +"\n\navformatinfo:"+avformatinfo()
                +"\n\navcodecinfo:"+avcodecinfo()
                +"\n\navfilterinfo:"+avfilterinfo()
        );

        Toast.makeText(this, "CPU_ABI:"+Build.CPU_ABI, Toast.LENGTH_SHORT).show();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native String urlprotocolinfo();
    public native String avformatinfo();
    public native String avcodecinfo();
    public native String avfilterinfo();
}
