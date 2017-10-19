package com.example.kenan.calorify.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.kenan.calorify.R;
import com.example.kenan.calorify.dal.repos.ProductRepository;
import com.example.kenan.calorify.dal.services.FoodService;
import com.example.kenan.calorify.dl.models.Product;
import com.example.kenan.calorify.dl.models.ProductDTO;
import com.google.gson.Gson;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.concurrent.ExecutionException;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by Kenan on 10/10/2017.
 */

public class ScanFragment extends Fragment {


    private FoodService foodService;

    public static ScanFragment newInstance() {
        return new ScanFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.scan_page_fragment, container, false);
        Button scanButton = (Button) view.findViewById(R.id.scan_button);

        ProductRepository repo = new ProductRepository();
        ListView productHistory = (ListView) view.findViewById(R.id.list_scanned_products);
        productHistory.setAdapter(new ArrayAdapter<>(getContext(),R.layout.list_products_item, repo.getAllProducts()));

        foodService = new FoodService();

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
                    ProductRepository repo = new ProductRepository();

                    if (response.getProducts() != null) {
                        Product scannedProduct = response.getProducts()[0];
                        scannedProduct.setScannedAt(LocalDate.now().toString());
                        repo.addProduct(scannedProduct);
                        Bundle args = new Bundle();
                        ProductDialogFragment dialog = new ProductDialogFragment();
                        args.putLong("productId", scannedProduct.getId());
                        dialog.setArguments(args);
                        dialog.show(getFragmentManager(), "ProductDialogFragment");
                    } else {
                        Toast.makeText(getContext(), "Product not found!", Toast.LENGTH_LONG).show();
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

}



