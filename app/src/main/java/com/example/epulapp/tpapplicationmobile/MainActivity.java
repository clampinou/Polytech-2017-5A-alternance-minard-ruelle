package com.example.epulapp.tpapplicationmobile;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.epulapp.tpapplicationmobile.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements BeerFragment.OnListFragmentInteractionListener {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

//        MenuFragment menuFragment = new MenuFragment();
        BeerFragment beerFragment = new BeerFragment();
        fragmentTransaction.add(R.id.game_container, beerFragment);
        fragmentTransaction.commit();
        
    }

    // onFragmentInteraction de TP2
//    @Override
//    public void onFragmentInteraction(int id) {
//        switch(id) {
//            case R.id.one_vs_ia:
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                JeuFragment jeuFragment = new JeuFragment();
//                fragmentTransaction.replace(R.id.game_container, jeuFragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//                break;
//        }
//    }

    @Override
    public void onListFragmentInteraction(BeerSerializable beer) {
        
    }


//    @Override
//    public void onClick(View view) {
//        int id = view.getId();
//        switch(id) {
//            case R.id.one_vs_ia:
//
//                break;
//
//        }
//    }

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
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifeCycle", "onRestart");
    }



}
