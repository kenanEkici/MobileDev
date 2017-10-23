package com.example.kenan.calorify.fragments.adapters;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.example.kenan.calorify.MenuActivity;
import com.example.kenan.calorify.dal.repos.DayRepository;
import com.example.kenan.calorify.dal.repos.ProductRepository;
import com.example.kenan.calorify.dl.models.Day;
import com.example.kenan.calorify.dl.models.Product;

import org.joda.time.LocalDate;

import java.util.Calendar;

/**
 * Created by Kenan on 23/10/2017.
 */

public class DatePickerFragment extends DialogFragment
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

        Intent intent = new Intent(getContext(), MenuActivity.class);
        startActivity(intent);
    }
}
