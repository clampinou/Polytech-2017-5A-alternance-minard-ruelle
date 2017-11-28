package com.example.epulapp.tpapplicationmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("lifeCycle", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifeCycle", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifeCycle", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifeCycle", "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifeCycle", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifeCycle", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifeCycle", "onRestart");
    }
}
