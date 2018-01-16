package com.example.epulapp.tpapplicationmobile;

import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity implements BeerFragment.OnListFragmentInteractionListener {

    private BeerFragment beerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        
    }

    @Override
    public void onListFragmentInteraction(BeerSerializable beer) {
        BeerDetails beerDetails = BeerDetails.newInstance(beer);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.game_container, beerDetails).addToBackStack(null).commit();
    }

}
