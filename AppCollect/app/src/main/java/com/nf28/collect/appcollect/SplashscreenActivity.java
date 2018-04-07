package com.nf28.collect.appcollect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SplashscreenActivity extends AppCompatActivity {

    private static final int TIME_WAIT_SPLASH   = 2000; //1000 msec
    private boolean launchMain = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        getWindow().getDecorView().setSystemUiVisibility(//fullscreen
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

    }


    @Override
    protected void onDestroy() {
        Log.d("","onDestroy SplashScreen ");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d("","onPause SplashScreen ");
        launchMain = false;
        super.onPause();
        //finish();
    }

    @Override
    protected void onRestart() {
        Log.d("","onRestart SplashScreen ");
        Intent intent = new Intent(SplashscreenActivity.this, MainActivity.class);
        startActivity(intent);
        launchMain = true;
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d("","onResume SplashScreen ");
        if (!MainActivity.isShown) {
            Thread timerThread = new Thread() {
                public void run() {
                    try {
                        sleep(TIME_WAIT_SPLASH);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Intent intent = new Intent(SplashscreenActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            };
            timerThread.start();
        }else if (!launchMain) {
            Intent intent = new Intent(SplashscreenActivity.this, MainActivity.class);
            startActivity(intent);
        }
        super.onResume();
    }
}
