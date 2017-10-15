package com.example.kenan.calorify.dal.services;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Kenan on 10/10/2017.
 */

public class FoodService {

    public class FoodInfoFromCodeTask extends AsyncTask<String, Void, String> {
        private OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {
            try {
                Request request = new Request.Builder()
                        .addHeader("x-app-id","4359abb2")
                        .addHeader("x-app-key","69316a39aa6242115b03d9aa15498b66")
                        .url("https://trackapi.nutritionix.com/v2/search/item?upc="+params)
                        .build();

                Response response = client.newCall(request).execute();
                return response.body().string();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}


