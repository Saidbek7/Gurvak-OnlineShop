package com.example.foodapp.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.FoodDetails;
import com.example.foodapp.R;
import com.example.foodapp.SMS;
import com.example.foodapp.model.Drink;
import com.example.foodapp.model.Popular;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>{

    private Context context;
    private List<Drink> drinkList;

    public DrinkAdapter(Context context, List<Drink> drinkList) {
        this.context = context;
        this.drinkList = drinkList;
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.products,parent,false);


        return new DrinkAdapter.DrinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, final int position)
    {

        holder.drinksName.setText(drinkList.get(position).getName());
        holder.drinksrating.setText(drinkList.get(position).getRating());
        holder.drinksPrice.setText(drinkList.get(position).getPrice());
       Picasso.get().load(drinkList.get(position).getImageUrl()).into(holder.drinksImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, FoodDetails.class);
                i.putExtra("name",drinkList.get(position).getName());
                i.putExtra("price",drinkList.get(position).getPrice());
                i.putExtra("rating",drinkList.get(position).getRating());
                i.putExtra("image",drinkList.get(position).getImageUrl());
                i.putExtra("note",drinkList.get(position).getNote());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return drinkList.size();
    }

    public static class DrinkViewHolder extends RecyclerView.ViewHolder
    {  ImageView drinksImage;
        TextView drinksName,drinksPrice,drinksrating;

        public DrinkViewHolder(@NonNull View itemView) {
            super(itemView);
            drinksName = itemView.findViewById(R.id.food_name);
            drinksImage = itemView.findViewById(R.id.food_image);
            drinksPrice = itemView.findViewById(R.id.food_price);
            drinksrating = itemView.findViewById(R.id.food_rating);
        }
    }
}

