package com.example.e_billpay.CustomerPart_UI.information;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.e_billpay.R;
import com.example.e_billpay.RegistrationData;

import java.util.List;

public class InformationAdapter extends ArrayAdapter<RegistrationData> {
    private Activity context;
    private List<RegistrationData> registrationDataList;

    public InformationAdapter(Activity context, List<RegistrationData> registrationDataList) {
        super(context, R.layout.sample_layout,registrationDataList);
        this.context = context;
        this.registrationDataList = registrationDataList;
    }

    @NonNull
    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout,null,true);

        RegistrationData registrationData = registrationDataList.get(position);

        TextView t1 = view.findViewById(R.id.show_name);
        TextView t2 = view.findViewById(R.id.show_c_no);
        TextView t3 = view.findViewById(R.id.show_meter_no);
        TextView t4 = view.findViewById(R.id.show_mobile_no);
        TextView t5 = view.findViewById(R.id.show_email_id);
        TextView t6 = view.findViewById(R.id.show_username);

        t1.setText("Name: " + registrationData.getName());
        t2.setText("Customer Number: " + registrationData.getC_no());
        t3.setText("Meter Number: " + registrationData.getMe_no());
        t4.setText("Mobile Number: " + registrationData.getMo_no());
        t5.setText("Email: " + registrationData.getEm_id());
        t6.setText("User Name: " + registrationData.getUs_na());

        return view;
    }
}
