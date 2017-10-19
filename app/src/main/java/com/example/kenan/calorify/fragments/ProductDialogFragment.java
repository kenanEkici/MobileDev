package com.example.kenan.calorify.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.kenan.calorify.R;
import com.example.kenan.calorify.dal.repos.DayRepository;
import com.example.kenan.calorify.dal.repos.ProductRepository;
import com.example.kenan.calorify.dl.models.Day;
import com.example.kenan.calorify.dl.models.Product;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.Calendar;

/**
 * Created by Kenan on 17/10/2017.
 */

public class ProductDialogFragment extends DialogFragment {

    private Product product;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_product, null);

        ProductRepository repo = new ProductRepository();
        product = repo.getProductByid(getArguments().getLong("productId"));

        Bundle args = new Bundle();
        args.putLong("productId", product.getId());

        Button addToSchemeButton = (Button) view.findViewById(R.id.button_add_scheme);
        EditText productName = (EditText) view.findViewById(R.id.product_name);
        EditText calories = (EditText) view.findViewById(R.id.product_calories);
        EditText qty = (EditText) view.findViewById(R.id.product_serving_qty);
        productName.setText(product.getBrandName());
        calories.setText(String.valueOf(product.getCalories()));
        qty.setText(String.valueOf(product.getServingQuantity()));

        addToSchemeButton.setOnClickListener(v -> {
            DatePickerFragment newFragment = new DatePickerFragment();
            newFragment.setArguments(args);
            newFragment.show(getFragmentManager(), "datePicker");
            this.dismiss();
        });

        builder.setView(view);
        return builder.create();
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private Product product;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            ProductRepository repo = new ProductRepository();
            product = repo.getProductByid(getArguments().getLong("productId"));
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            ProductRepository repo = new ProductRepository();
            Day day = new Day(new LocalDate(year, month, dayOfMonth).toString());
            DayRepository dayRepo = new DayRepository();
            Product updatedProduct = dayRepo.saveOrUpdateDayOfConsumedProduct(day,product);
            repo.addProduct(updatedProduct);
            getDialog().dismiss();

            //update the scheme
        }
    }
}
