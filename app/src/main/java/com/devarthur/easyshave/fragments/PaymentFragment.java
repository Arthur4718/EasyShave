package com.devarthur.easyshave.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devarthur.easyshave.R;

public class PaymentFragment extends Fragment {

    private static final String SELLER_EMAIL = "devarthur4718@gmail.com";
    private static final String SELLER_TOKEN = "31C3F3E9281D4781A468722B0B9EFD19";
    private final String NOTIFICATION_URL_PAYMENT = "https://pagseguro.uol.com.br/lojamodelo-qa/RetornoAutomatico-OK.jsp";


    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false);
    }

}
