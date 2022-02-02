package com.san.daydream;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Handler;
import android.service.dreams.DreamService;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;


@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public class DayDream extends DreamService {

    SharedPreference sharedPreference;

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        sharedPreference = new SharedPreference(getApplicationContext());
        // Hide system UI
        setFullscreen(true);
        // Set the dream layout
        int id = sharedPreference.get_flag();
        if(id == 0){
            setContentView(R.layout.matrix_view);
        }
        else if(id == 1){
            setContentView(R.layout.coffee_view);
            setWaveLoading();
        }


    }

    @Override
    public void onDreamingStarted() {
        super.onDreamingStarted();

    }

    private void setWaveLoading(){

        WaveLoadingView mWaveLoadingView = findViewById(R.id.coffeeview);
        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.RECTANGLE);
        mWaveLoadingView.setAmplitudeRatio(90);
        mWaveLoadingView.setWaveColor(getResources().getColor(R.color.colorBrownLight));
        //  mWaveLoadingView.setWaveBgColor(Color.MAGENTA);
        mWaveLoadingView.setAnimDuration(3000);
        mWaveLoadingView.pauseAnimation();
        mWaveLoadingView.resumeAnimation();
        mWaveLoadingView.cancelAnimation();
        mWaveLoadingView.startAnimation();

        System.out.println("getBatteryPercentage(this) = " + getBatteryPercentage(this));
        mWaveLoadingView.setProgressValue(getBatteryPercentage(this));
    }

    public int getBatteryPercentage(Context context) {

        if (Build.VERSION.SDK_INT >= 21) {

            BatteryManager bm = (BatteryManager) context.getSystemService(BATTERY_SERVICE);
            return bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        } else {

            IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = context.registerReceiver(null, iFilter);

            int level = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
            int scale = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1) : -1;

            double batteryPct = level / (double) scale;

            return (int) (batteryPct * 100);
        }
    }




}
