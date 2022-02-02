package com.san.daydream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;

public class PreviewActivity extends AppCompatActivity {
    SharedPreference sharedPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_preview);


        int id = getIntent().getIntExtra("ID", -1);
        if(id != -1){
            if(id == 0){
                setContentView(R.layout.matrix_view);
            }
            else if(id == 1){
                setContentView(R.layout.coffee_view);
                setWaveLoading();
            }

        }


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

        BatteryManager bm = (BatteryManager) context.getSystemService(BATTERY_SERVICE);
        return bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

    }
}