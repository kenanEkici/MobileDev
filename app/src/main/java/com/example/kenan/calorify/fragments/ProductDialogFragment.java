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
import com.example.kenan.calorify.dal.repos.ConsumedProductRepository;
import com.example.kenan.calorify.dal.repos.ScannedProductRepository;
import com.example.kenan.calorify.dl.models.ConsumedProduct;
import com.example.kenan.calorify.dl.models.ScannedProduct;
import com.example.kenan.calorify.helpers.AuthenticationHelper;

public class ProductDialogFragment extends DialogFragment {

    private ConsumedProduct openedConsumedProduct;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_product, null);


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

        Bundle args = new Bundle();


        if (getArguments().getString("openedBy").equals("scan")) {
            ScannedProductRepository repo = new ScannedProductRepository();
            ScannedProduct p = repo.getProductByid(getArguments().getLong("productId"));
            deleteProduct.setVisibility(View.GONE);
            args.putSerializable("scannedProduct", p);

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
        } else {
            ConsumedProductRepository repo = new ConsumedProductRepository();
            ConsumedProduct p = repo.getProductByid(getArguments().getLong("productId"));
            addToScheme.setVisibility(View.GONE);
            args.putSerializable("consumedProduct", p);
            openedConsumedProduct = p;

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
        }

        deleteProduct.setOnClickListener(v -> {
            if (openedConsumedProduct != null)
            new ConsumedProductRepository().deleteProductFromScheme(openedConsumedProduct);
            getDialog().dismiss();
            AuthenticationHelper.continueToMenu(getContext());
        });

        addToScheme.setOnClickListener(v -> {
            ScannedProductDialogFragment dialog = new ScannedProductDialogFragment();
            dialog.setArguments(args);
            dialog.show(getFragmentManager(), "ScannedProductDialogFragment");
        });

        builder.setView(view);
        return builder.create();
    }
}