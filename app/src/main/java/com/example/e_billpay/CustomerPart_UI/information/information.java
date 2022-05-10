package com.example.e_billpay.CustomerPart_UI.information;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.e_billpay.R;
import com.example.e_billpay.RegistrationData;
import com.example.e_billpay.databinding.FragmentInformationBinding;
import com.example.e_billpay.databinding.FragmentUserBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class information extends Fragment {
    private ListView listView;
    DatabaseReference databaseReference;
    private List<RegistrationData> registrationDataList;
    private InformationAdapter informationAdapter;
    private RegistrationData registrationData;
    private Button update_information;
    BottomSheetDialog bottomSheetDialog;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_information,container,false);
        databaseReference = FirebaseDatabase.getInstance().getReference("Registration").child("Customer");

        listView = view.findViewById(R.id.listView);

        update_information = view.findViewById(R.id.btn_update_info);

        registrationDataList = new ArrayList<>();
        informationAdapter = new InformationAdapter(getActivity(),registrationDataList);


        update_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialogue();
            }

        });
        return view;
    }

    private void showBottomDialogue() {

        bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(R.layout.updated_popup);

        EditText editname = bottomSheetDialog.findViewById(R.id.txtname1);
        EditText editmobile = bottomSheetDialog.findViewById(R.id.txtmobileno);
        EditText editEmail = bottomSheetDialog.findViewById(R.id.txtemail);
        EditText edituserName = bottomSheetDialog.findViewById(R.id.txtusername);
        Button upbtn = bottomSheetDialog.findViewById(R.id.update_btn);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                registrationDataList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                  final RegistrationData registrationData = dataSnapshot.getValue(RegistrationData.class);
                }
                assert registrationData != null;
                editname.setText(registrationData.getName());
                editmobile.setText(registrationData.getMo_no());
                editEmail.setText(registrationData.getEm_id());
                edituserName.setText(registrationData.getUs_na());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        assert upbtn != null;

        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                HashMap user = new HashMap<>();
                user.put("Name: ",editname.getText().toString());
                user.put("Mobile No: ",editmobile.getText().toString());
                user.put("Email ID: ",editEmail.getText().toString());
                user.put("UserName: ",edituserName.getText().toString());

            }
        });
        bottomSheetDialog.show();
    }

    @Override
    public void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                registrationDataList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    registrationData = dataSnapshot.getValue(RegistrationData.class);
                    registrationDataList.add(registrationData);
                }
                listView.setAdapter(informationAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        super.onStart();
    }
}