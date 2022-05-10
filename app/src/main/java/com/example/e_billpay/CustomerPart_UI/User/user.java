package com.example.e_billpay.CustomerPart_UI.User;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.airbnb.lottie.LottieAnimationView;
import com.example.e_billpay.Payment_API;
import com.example.e_billpay.R;



public class user extends Fragment {

    private Button payBill, getdetails,Btn_bill_details;
    TextView textView,Payment;
    LinearLayout get_Details,details_Layout;
    LottieAnimationView lottieAnimationView;
    ImageView imageView;
    Spinner spinner;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user,container,false);

        payBill = view.findViewById(R.id.btn_pay_bill);
        textView = view.findViewById(R.id.user_name);
        lottieAnimationView = view.findViewById(R.id.animationView);
        getdetails = view.findViewById(R.id.get_details_button);
        get_Details = view.findViewById(R.id.get_details);
        details_Layout = view.findViewById(R.id.details_layout);
        Payment = view.findViewById(R.id.staus_payment);
        Btn_bill_details = view.findViewById(R.id.btn_bill_details);
        imageView = view.findViewById(R.id.ujjalpic);
        spinner = view.findViewById(R.id.user_spinne3);

        String[] months = new String[] {
                "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November" , "December"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, months);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        textView.setText("Ujjal");

        payBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Payment_API.class);
                startActivity(intent);
                Payment.setText("PAID");
                Payment.setTextColor(Color.GREEN);
                getdetails.setBackgroundResource(android.R.drawable.btn_default);

            }
        });
        Btn_bill_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lottieAnimationView.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
                get_Details.setVisibility(View.VISIBLE);
            }
        });
        getdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                details_Layout.setVisibility(View.VISIBLE);
                getdetails.setBackgroundColor(Color.GREEN);
            }
        });


        return view;
    }
}