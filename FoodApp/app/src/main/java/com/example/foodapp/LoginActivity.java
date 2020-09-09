package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

   private EditText emailEt, passwordEt;
   private TextView forgotPas, noAccount;
   private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEt = findViewById(R.id.login_email);
        passwordEt = findViewById(R.id.login_Password);
        forgotPas = findViewById(R.id.forget_password_link);
        loginBtn = findViewById(R.id.login_button);
        noAccount = findViewById(R.id.need_new_account_link);

        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterUserActivity.class));
               finish();
            }
        });

    }


}