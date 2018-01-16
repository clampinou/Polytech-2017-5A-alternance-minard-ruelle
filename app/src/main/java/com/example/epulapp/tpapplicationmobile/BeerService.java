package com.example.epulapp.tpapplicationmobile;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;



/**
 * Created by Epulapp on 29/11/2017.
 */

public class BeerService {
    IBeerService service;
//    List<Beer> listBeers = new ArrayList<>();

    public interface IBeerService {
        @GET("beers")
        Call<List<BeerSerializable>> getBeers();

        @GET("beers")
        Call<List<BeerSerializable>> getBeers(@Query("page") int page);
    }

    public BeerService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.punkapi.com/v2/")
                .build();
        service = retrofit.create(IBeerService.class);
    }

    public static IBeerService getRSClient() {
        return RetrofitService.getClient("https://api.punkapi.com/v2/").create(IBeerService.class);
    }
}
