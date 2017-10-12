package com.example.kenan.calorify.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.kenan.calorify.R;

/**
 * Created by Kenan on 10/10/2017.
 */

public class WelcomeFragment extends Fragment implements View.OnClickListener{

    private OnFragmentInteractionListener mListener;

    private Button registerButton;

    public static WelcomeFragment newInstance() {
        return new WelcomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.welcome_page_fragment, container, false);
        registerButton = (Button) view.findViewById(R.id.button_register);
        registerButton.setOnClickListener(this);

        return view;
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
        mListener.onFragmentInteraction();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }
}
