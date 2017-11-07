package com.example.kenan.calorify.fragments;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.kenan.calorify.R;
import com.example.kenan.calorify.dal.repos.ConsumedProductRepository;
import com.example.kenan.calorify.dl.models.ConsumedProduct;
import com.example.kenan.calorify.dl.models.ScannedProduct;
import com.example.kenan.calorify.helpers.ProductHelper;

/**
 * Created by Kenan on 17/10/2017.
 */

public class ScannedProductDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_product_scan, null);

        //get scanned product
        ScannedProduct scannedProduct = (ScannedProduct) getArguments().getSerializable("scannedProduct");

        //get dialog elements
        TextView productName = (TextView) view.findViewById(R.id.product_name);
        TextView calories = (TextView) view.findViewById(R.id.product_calories);
        TextView qty = (TextView) view.findViewById(R.id.product_serving_qty);
        EditText amount = (EditText) view.findViewById(R.id.consumed_amount);
        Button addToSchemeButton = (Button) view.findViewById(R.id.button_add_scheme);

        //fill in dialog
        productName.setText(scannedProduct.getBrandName());
        calories.setText(String.valueOf(scannedProduct.getCalories()));
        qty.setText(String.valueOf(scannedProduct.getServingQuantity()));

        //set listener
        addToSchemeButton.setOnClickListener(v -> {
            //add a consumed product
            ConsumedProductRepository consumerRepo = new ConsumedProductRepository();
            ConsumedProduct consumedProduct = ProductHelper.transformScannedToConsumed(scannedProduct);
            consumedProduct.setConsumedQuantity(Double.parseDouble(amount.getText().toString()));
            consumerRepo.addProduct(consumedProduct);

            Bundle args = new Bundle();
            args.putSerializable("consumedProduct", consumedProduct);

            //summon datepicker
            DatePickerFragment newFragment = new DatePickerFragment();
            newFragment.setArguments(args);
            newFragment.show(getFragmentManager(), "datePicker");
            this.dismiss();
        });

        builder.setView(view);
        return builder.create();
    }
}
