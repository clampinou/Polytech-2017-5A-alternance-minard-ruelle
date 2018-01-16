package com.example.epulapp.tpapplicationmobile;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.epulapp.tpapplicationmobile.dummy.DummyContent;

public class MainActivity extends FragmentActivity implements BeerFragment.OnListFragmentInteractionListener {

    private BeerFragment beerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (findViewById(R.id.game_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            if (beerFragment == null) {
                beerFragment = new BeerFragment();
            }
            beerFragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(R.id.game_container, beerFragment).commit();
        }

//        BeerFragment beerFragment = new BeerFragment();
//        fragmentTransaction.add(R.id.game_container, beerFragment);
//        fragmentTransaction.commit();
        
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
        BeerDetails beerDetails = BeerDetails.newInstance(beer);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.game_container, beerDetails).addToBackStack(null).commit();
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




}
