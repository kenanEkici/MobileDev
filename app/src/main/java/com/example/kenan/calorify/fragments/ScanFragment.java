package com.example.kenan.calorify.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
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
import com.example.kenan.calorify.dal.repos.ScannedProductRepository;
import com.example.kenan.calorify.dal.services.FoodService;
import com.example.kenan.calorify.dl.models.ProductDTO;
import com.example.kenan.calorify.dl.models.ScannedProduct;
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

        ScannedProductRepository productRepo = new ScannedProductRepository();
        foodService = new FoodService();

        productHistory.setAdapter(new ArrayAdapter<>(getContext(),R.layout.list_products_item, productRepo.getAllProducts()));

        //set listener
        scanButton.setOnClickListener(v ->{
            //open a barcode scanner if available
            try {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");
                startActivityForResult(intent, 0);
            } catch (Exception ex) {
                Toast.makeText(getContext(), getString(R.string.scanner),Toast.LENGTH_LONG).show();
            }
        });

        productHistory.setOnItemClickListener((parent, view1, position, id) -> {
            Bundle args = new Bundle();
            ProductDialogFragment dialog = new ProductDialogFragment();
            args.putString("openedBy", "scan");
            args.putLong("productId", productRepo.getAllProducts().get(position).getId());
            dialog.setArguments(args);
            dialog.show(getFragmentManager(), "ProductDialogFragment");
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
                //check for internet access
                if (!isNetworkConnected()) {
                    Toast.makeText(getContext(), getString(R.string.internet), Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        //parse response
                        Gson gson = new Gson();
                        ProductDTO response = gson.fromJson(foodInfoFromCodeTask.execute(contents).get(), ProductDTO.class);
                        ScannedProductRepository repo = new ScannedProductRepository();

                        if (response.getProducts() != null) {
                            //retrieve results
                            ScannedProduct scannedProduct = response.getProducts()[0];
                            scannedProduct.setScannedAt(LocalDate.now().toString());
                            repo.addProduct(scannedProduct);

                            Bundle args = new Bundle();
                            ScannedProductDialogFragment dialog = new ScannedProductDialogFragment();
                            args.putSerializable("scannedProduct", scannedProduct);
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
                }
            } else {
                // Handle cancel
            }

        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}



