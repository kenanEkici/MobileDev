package com.example.kenan.calorify.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.kenan.calorify.R;
import com.example.kenan.calorify.dal.services.FoodService;
import com.example.kenan.calorify.dl.models.Product;
import com.example.kenan.calorify.dl.models.ProductDTO;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by Kenan on 10/10/2017.
 */

public class ScanFragment extends Fragment {

    public TextView result;
    private FoodService foodService;

    public static ScanFragment newInstance() {
        return new ScanFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.scan_page_fragment, container, false);
        Button scanButton = (Button) view.findViewById(R.id.scan_button);
        result = (TextView) view.findViewById(R.id.result);

        foodService = new FoodService();

        if (savedInstanceState != null){
            String scanResult = savedInstanceState.getString("scan_result");
            if (scanResult != null) {
                result.setText(scanResult);
            }
        }

        scanButton.setOnClickListener(v ->{
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        });
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

                FoodService.FoodInfoFromCodeTask foodInfoFromCodeTask = foodService.new FoodInfoFromCodeTask();
                try {
                    Gson gson = new Gson();
                    ProductDTO response = gson.fromJson(foodInfoFromCodeTask.execute(contents).get(), ProductDTO.class);
                    if (response.getProducts() != null) {
                        result.setText(response.getProducts()[0].getBrandName());
                    } else {
                        result.setText("Not found");
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Make sure to call the super method so that the states of our views are saved
        super.onSaveInstanceState(outState);
        // Save our own state now
        outState.putString("scan_result", result.getText().toString());
    }
}
