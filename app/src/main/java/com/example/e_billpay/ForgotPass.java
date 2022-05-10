package com.example.e_billpay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPass extends AppCompatActivity {

    private EditText emailsend;
    private Button sendbutton;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        emailsend = findViewById(R.id.email);
        sendbutton = findViewById(R.id.send_button);
        setTitle("Forget your Password Here!!");
        auth = FirebaseAuth.getInstance();

        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
                Intent intent = new Intent(ForgotPass.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void resetPassword() {
        String Email = emailsend.getText().toString().trim();

        if(Email.isEmpty()){
            emailsend.setError("Enter an email address");
            emailsend.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            emailsend.setError("Enter a valid email address");
            emailsend.requestFocus();
            return;
        }
        auth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPass.this, "Check your email to reset your password", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ForgotPass.this, "Try Again!! Something wrong happened", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}