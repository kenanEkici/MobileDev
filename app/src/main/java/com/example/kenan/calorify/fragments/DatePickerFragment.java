package com.example.kenan.calorify.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import com.example.kenan.calorify.dal.repos.DayRepository;
import com.example.kenan.calorify.dal.repos.ConsumedProductRepository;
import com.example.kenan.calorify.dl.models.ConsumedProduct;
import com.example.kenan.calorify.dl.models.Day;
import com.example.kenan.calorify.helpers.AuthenticationHelper;

import org.joda.time.LocalDate;

import java.util.Calendar;

/**
 * Created by Kenan on 23/10/2017.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private ConsumedProduct product;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        product = (ConsumedProduct) getArguments().getSerializable("consumedProduct");
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        ConsumedProductRepository repo = new ConsumedProductRepository();
        Day day = new Day(new LocalDate(year, month, dayOfMonth).toString());
        DayRepository dayRepo = new DayRepository();
        ConsumedProduct updatedProduct = dayRepo.saveOrUpdateDayOfConsumedProduct(day,product);
        repo.addProduct(updatedProduct);

        getDialog().dismiss();
        AuthenticationHelper.continueToMenu(getContext());
    }
}
