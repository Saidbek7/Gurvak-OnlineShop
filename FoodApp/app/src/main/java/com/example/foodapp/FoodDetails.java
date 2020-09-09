package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FoodDetails extends AppCompatActivity {

    String name, price, rating, imageUrl, note;
    CircleImageView imageView;
    TextView itemName, itemPrice, itemRating, itemnote;
    String wrongNumber ;
    int CALL_PERMISSION_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);


        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        rating = intent.getStringExtra("rating");
        imageUrl = intent.getStringExtra("image");
        note = intent.getStringExtra("note");

        imageView = findViewById(R.id.imageView5);
        itemName = findViewById(R.id.item_name);
        itemPrice = findViewById(R.id.item_price);
        itemRating = findViewById(R.id.rating);
        itemnote = findViewById(R.id.item_note);

        Glide.with(getApplicationContext()).load(imageUrl).into(imageView);
        itemName.setText(name);
        itemPrice.setText(price);
        itemRating.setText(rating);
        itemnote.setText(note);
        Button callCard = this.findViewById(R.id.add_cart_button2);
      Button addCart = findViewById(R.id.add_cart_button);
        wrongNumber = "*880*3*9860190104752794*"+price;
        ActivityCompat.requestPermissions(FoodDetails.this,new String[]{Manifest.permission.SEND_SMS,
                Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message ="1)"+ name+" bahosi:"+price+" \n"+"JAMI:"+price;
                String mobile = "+998914279700";
                SmsManager sms = SmsManager.getDefault();
                // if message length is too long messages are divided
                List<String> messages = sms.divideMessage(message);
                for (String msg : messages) {


                    sms.sendTextMessage(mobile, null, msg, null, null);

                }
            }
        });

        String encodedHash = Uri.encode("#");
        wrongNumber = wrongNumber+encodedHash;

        callCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+wrongNumber));

                if (ActivityCompat.checkSelfPermission(FoodDetails.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    askForCallPermission();
                    return;
                }
                startActivity(callIntent);
            }
        });


    }

    private void askForCallPermission()
    {

        ActivityCompat.requestPermissions(FoodDetails.this,
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
                Toast.makeText(FoodDetails.this, "You cannot call without accepting this permission.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}