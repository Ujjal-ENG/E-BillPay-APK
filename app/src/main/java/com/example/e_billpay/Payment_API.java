package com.example.e_billpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_billpay.CustomerPart_UI.User.user;

public class Payment_API extends AppCompatActivity {

    WebView webView;
    Button payment_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_api);
        webView = findViewById(R.id.webview);


        webView.loadUrl("https://www.bkash.com/electricity-paybill");
        payment_done = findViewById(R.id.payment_done);

        payment_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Payment_API.this, "Here i will set also the 'Paid' text " +
                        "when it clicked.. but it will update in future ", Toast.LENGTH_SHORT).show();

            }
        });

    }
}