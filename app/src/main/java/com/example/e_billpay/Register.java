package com.example.e_billpay;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {
    private Spinner spinner;
    private Button save_data;
    private EditText name, customer_no, meter_no, mobile_no, email_id, username, password, confirm_password;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    String users, Name, C_no, Me_no, Mo_no, Em_id, Us_na, Pass, C_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        spinner = findViewById(R.id.user_spinner);
        meter_no = findViewById(R.id.enter_meter_no);
        save_data = findViewById(R.id.save_data);
        name = findViewById(R.id.enter_cname);
        customer_no = findViewById(R.id.enter_cno);
        mobile_no = findViewById(R.id.enter_mo_no);
        email_id = findViewById(R.id.enter_mail);
        username = findViewById(R.id.enter_username);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.enter_cpass);

        databaseReference = FirebaseDatabase.getInstance().getReference("Registration");
        mAuth = FirebaseAuth.getInstance();


        List<String> arraySpinner = new ArrayList<>();
        boolean customer = arraySpinner.add("Customer");
        boolean manager = arraySpinner.add("Manager");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinner.getSelectedItem().toString().equals("Customer")) {
                    meter_no.setVisibility(View.VISIBLE);
                    customer_no.setVisibility(View.VISIBLE);
                    setTitle("Registration for Customer");
                } else {
                    meter_no.setVisibility(View.INVISIBLE);
                    customer_no.setVisibility(View.INVISIBLE);
                    setTitle("Registration for Manager");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //do nothing
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("Registration").child(spinner.getSelectedItem().toString());

        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email_id.getText().toString() != null && password.getText().toString() != null) {
                    users = spinner.getSelectedItem().toString();
                    Name = name.getText().toString().trim();
                    C_no = customer_no.getText().toString().trim();
                    Me_no = meter_no.getText().toString().trim();
                    Mo_no = mobile_no.getText().toString().trim();
                    Em_id = email_id.getText().toString().trim();
                    Us_na = username.getText().toString().trim();
                    Pass = password.getText().toString().trim();
                    C_pass = confirm_password.getText().toString().trim();

                    String key = databaseReference.push().getKey();

                    RegistrationData registrationData = new RegistrationData(users, Name, C_no, Me_no, Mo_no, Em_id, Us_na, Pass, C_pass);
                    databaseReference.child(key).setValue(registrationData);
                    Toast.makeText(Register.this, "Information is Added", Toast.LENGTH_SHORT).show();
                    registerUser();
                    Intent intent = new Intent(Register.this,Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private void registerUser() {
        mAuth.createUserWithEmailAndPassword(Em_id, Pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}