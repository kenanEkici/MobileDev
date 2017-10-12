package com.example.kenan.calorify.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.kenan.calorify.R;
import com.example.kenan.calorify.dal.repos.UserRepository;
import com.example.kenan.calorify.dl.enums.Gender;
import com.example.kenan.calorify.dl.models.User;

/**
 * Created by Kenan on 10/10/2017.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener{

    private OnFragmentInteractionListener mListener;
    private View view;
    private Button registerButton;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);

        view = inflater.inflate(R.layout.register_page_fragment, container, false);
        registerButton = (Button) view.findViewById(R.id.create_profile);
        Spinner gender = (Spinner) view.findViewById(R.id.gender);
        Gender[] genders = { Gender.Male, Gender.Female, Gender.Other };

        ArrayAdapter<Gender> adapter = new ArrayAdapter<Gender>(getActivity(),
                android.R.layout.simple_spinner_item, genders);
        gender.setAdapter(adapter);

        registerButton.setOnClickListener(this);

        return view;
    }

    private boolean tryCreateUser(){

        try{
            EditText name = (EditText) view.findViewById(R.id.full_name);
            EditText age = (EditText) view.findViewById(R.id.age);
            EditText weight = (EditText) view.findViewById(R.id.weight);
            EditText height = (EditText) view.findViewById(R.id.height);
            Spinner spinner = (Spinner) view.findViewById(R.id.gender);

            User user = new User(name.getText().toString(),(Gender.valueOf(spinner.getSelectedItem().toString()))
                    ,Double.parseDouble(weight.getText().toString()),Double.parseDouble(height.getText().toString()),
                    Integer.parseInt(age.getText().toString()),true);

            UserRepository userRepository = new UserRepository();
            userRepository.addUser(user);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if(tryCreateUser()) {
            registerButton.setEnabled(false);
            mListener.onRegisterComplete();
        }
    }

    public interface OnFragmentInteractionListener {
        void onRegisterComplete();
    }
}
