package com.example.kenan.calorify.fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kenan.calorify.R;
import com.example.kenan.calorify.dal.repos.ProductRepository;
import com.example.kenan.calorify.dl.models.Product;

public class SchemeDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_product_scheme, null);

        ProductRepository repo = new ProductRepository();
        Product p = repo.getProductByid(getArguments().getLong("productId"));

        Bundle args = new Bundle();
        args.putLong("productId", p.getId());


        //Get the textViews from the view with the ID
        TextView brandNameText = (TextView) view.findViewById(R.id.fsd_Brand_Name);
        TextView foodNameText = (TextView) view.findViewById(R.id.fsd_Food_Name);
        TextView caloriesText = (TextView) view.findViewById(R.id.fsd_Calories);
        TextView cholesterolText = (TextView) view.findViewById(R.id.fsd_Cholesterol);
        TextView proteinText = (TextView) view.findViewById(R.id.fsd_Proteins);
        TextView saturatedFatText = (TextView) view.findViewById(R.id.fsd_Saturated_Fat);
        TextView sodiumText = (TextView) view.findViewById(R.id.fsd_Sodium);
        TextView totalCarbohydratesText = (TextView) view.findViewById(R.id.fsd_Total_Carbohydates);
        TextView sugarsText = (TextView) view.findViewById(R.id.fsd_Sugars);
        TextView totalFatText = (TextView) view.findViewById(R.id.fsd_Total_Fat);
        Button deleteProduct = (Button) view.findViewById(R.id.button_delete_prod);
        Button addToScheme = (Button) view.findViewById(R.id.button_add_prod_to_scheme);


        //Put the info in the textviews
        brandNameText.setText(p.getBrandName());
        foodNameText.setText(p.getFoodName());
        caloriesText.setText(Double.toString(p.getCalories()));
        cholesterolText.setText(Double.toString(p.getCholesterol()));
        proteinText.setText(Double.toString(p.getProtein()));
        saturatedFatText.setText(Double.toString(p.getSaturatedFatOfTotal()));
        sodiumText.setText(Double.toString(p.getSodium()));
        totalCarbohydratesText.setText(Double.toString(p.getCarbsTotal()));
        sugarsText.setText(Double.toString(p.getSugars()));
        totalFatText.setText(Double.toString(p.getTotalFat()));

        if (getArguments().getString("openedBy").equals("scan")) {
            deleteProduct.setVisibility(View.GONE);
        } else {
            addToScheme.setVisibility(View.GONE);
        }

        deleteProduct.setOnClickListener(v -> {
            //delete a product from a specific day
        });

        addToScheme.setOnClickListener(v -> {
            ScannedProductDialogFragment dialog = new ScannedProductDialogFragment();
            args.putLong("productId", p.getId());
            dialog.setArguments(args);
            dialog.show(getFragmentManager(), "ScannedProductDialogFragment");
        });

        builder.setView(view);
        return builder.create();
    }
}