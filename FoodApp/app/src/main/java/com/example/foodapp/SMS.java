package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.foodapp.model.Drink;

import java.util.List;

public class SMS extends AppCompatActivity {
    List<Drink> drinkList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_m_s);

        Button btnSend = findViewById(R.id.sendBtn);
        final EditText txtMessage = findViewById(R.id.txtmsg);
        ActivityCompat.requestPermissions(SMS.this,new String[]{Manifest.permission.SEND_SMS,
                Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = " txtMessage.getText().toString();";
                String mobile = "+998946340848";
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(mobile,null,msg,null,null);

            }
        });
    }
}