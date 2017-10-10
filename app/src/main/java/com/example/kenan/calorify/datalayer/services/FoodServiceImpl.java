package com.example.kenan.calorify.datalayer.services;

import android.os.AsyncTask;
import android.util.Log;

import com.example.kenan.calorify.datalayer.models.Product;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Kenan on 10/10/2017.
 */

public class FoodServiceImpl {

    OkHttpClient client = new OkHttpClient();

    public void doGetRequest() throws IOException {
        String req;
        Request request = new Request.Builder()
                    .addHeader("x-app-id","4359abb2")
                    .addHeader("x-app-key","69316a39aa6242115b03d9aa15498b66")
                    .url("https://trackapi.nutritionix.com/v2/search/item?upc=5000181028133")
                    .build();


        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(final Call call, IOException e) {
                        // Error
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        Log.v("",response.body().string());
                    }
                });

    }


}


