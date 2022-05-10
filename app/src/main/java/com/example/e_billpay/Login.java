package com.example.e_billpay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    private Button signin;
    private TextView signup ,forgotpassword;
    Spinner spinner;
    private EditText enter_mail,password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signup = findViewById(R.id.signup);
        forgotpassword = findViewById(R.id.forget_pass);
        spinner = findViewById(R.id.user_spinne2);
        signin = findViewById(R.id.sign_in);
        enter_mail = findViewById(R.id.enter_mail);
        password = findViewById(R.id.password);
        setTitle("Login Page");
        mAuth = FirebaseAuth.getInstance();

        List<String> arraySpinner = new ArrayList<>();
        boolean customer =   arraySpinner.add("Customer");
        boolean manager =  arraySpinner.add("Manager");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinner.getSelectedItem().toString().equals("Customer")) {
                    setTitle("Login Page for Customer");
                } else {
                    setTitle("Login Page for Manager");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //do nothing
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,ForgotPass.class);
                startActivity(intent);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (spinner.getSelectedItem().toString().equals("Customer")) {

                    String email = enter_mail.getText().toString().trim();
                    String pass = password.getText().toString().trim();

                    if(email.isEmpty()){
                        enter_mail.setError("Enter an email address");
                        enter_mail.requestFocus();
                        return;
                    }
                    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        enter_mail.setError("Enter a valid email address");
                        enter_mail.requestFocus();
                        return;
                    }
                    if(pass.isEmpty()){
                        password.setError("Enter a passwords");
                        password.requestFocus();
                        return;
                    }
                    if(pass.length()<6){
                        password.setError("Minimum length of a passwords should be 6");
                        password.requestFocus();
                        return;
                    }
                    SignIn(email,pass);
                    return;
                } else {

                    Toast.makeText(Login.this, "This part mainly working in manager. But its not implement yet", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void SignIn(String email, String pass) {
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(Login.this,Customer.class);
                    startActivity(intent);
//                    finish();
                }else{
                    Toast.makeText(Login.this, "Registration is not Successfully"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.menu,menu);
      return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.exit){
            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            builder.setMessage("Do you Want to Exit?");
            builder.setCancelable(true);

            builder.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
//                    finish();
                }
            });
            builder.setPositiveButton("No",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        return true;
    }
}