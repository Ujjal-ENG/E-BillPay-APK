package com.example.e_billpay.CustomerPart_UI.generate_bill;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.e_billpay.databinding.FragmentGenerateBillBinding;

public class generate_bill extends Fragment {

    private FragmentGenerateBillBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGenerateBillBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }
}