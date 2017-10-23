package com.example.kenan.calorify.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.example.kenan.calorify.R;
import com.example.kenan.calorify.dal.repos.DayRepository;
import com.example.kenan.calorify.dal.repos.ProductRepository;
import com.example.kenan.calorify.dal.services.FoodService;
import com.example.kenan.calorify.dl.models.Product;
import com.example.kenan.calorify.dl.models.ProductDTO;
import com.google.gson.Gson;
import org.joda.time.LocalDate;
import java.util.concurrent.ExecutionException;
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

        View view = inflater.inflate(R.layout.menu_scan_frag, container, false);
        Button scanButton = (Button) view.findViewById(R.id.scan_button);
        ListView productHistory = (ListView) view.findViewById(R.id.list_scanned_products);

        ProductRepository productRepo = new ProductRepository();
        foodService = new FoodService();

        productHistory.setAdapter(new ArrayAdapter<>(getContext(),R.layout.list_products_item, productRepo.getAllProducts()));

        //set listener
        scanButton.setOnClickListener(v ->{
            //open a barcode scanner if available
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");

                //start task to get product from api
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
                        ScannedProductDialogFragment dialog = new ScannedProductDialogFragment();
                        args.putLong("productId", scannedProduct.getId());
                        dialog.setArguments(args);
                        dialog.show(getFragmentManager(), "ScannedProductDialogFragment");
                    } else {
                        Toast.makeText(getContext(), "Product not found!", Toast.LENGTH_LONG).show();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                // Handle cancel
            }
        }
    }

}



