package com.example.foodapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.FoodDetails;
import com.example.foodapp.R;
import com.example.foodapp.model.Recommended;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReccomendedAdapter extends RecyclerView.Adapter<ReccomendedAdapter.ReccomendedViewHolder>
{
    private Context context;
    private List<Recommended> recommendedList;

    public ReccomendedAdapter(Context context, List<Recommended> recommendedList) {
        this.context = context;
        this.recommendedList = recommendedList;
    }

    @NonNull
    @Override
    public ReccomendedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reccomended_recycler_items,parent,false);


        return new ReccomendedAdapter.ReccomendedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReccomendedViewHolder holder, final int position)
    {

        holder.reccomendedName.setText(recommendedList.get(position).getName());
        holder.reccomendedRating.setText(recommendedList.get(position).getRating());
        holder.reccomendedPrice.setText(recommendedList.get(position).getPrice());
        Picasso.get().load(recommendedList.get(position).getImageUrl()).into(holder.reccomendedImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, FoodDetails.class);
                i.putExtra("name",recommendedList.get(position).getName());
                i.putExtra("price",recommendedList.get(position).getPrice());
                i.putExtra("rating",recommendedList.get(position).getRating());
                i.putExtra("image",recommendedList.get(position).getImageUrl());
                i.putExtra("note",recommendedList.get(position).getNote());
                context.startActivity(i);

                holder.addtocart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showQuantityDialog();
                    }
                });

            }
        });

    }

    private void showQuantityDialog()
    {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_quantity,null);
    }

    @Override
    public int getItemCount() {
        return  recommendedList.size();
    }


    public static class ReccomendedViewHolder extends RecyclerView.ViewHolder
{
    ImageView reccomendedImage,addtocart;
    TextView reccomendedName,reccomendedRating,reccomendedDeliveryTime,reccomendedPrice,reccomendedDeliveryCharges;


    public ReccomendedViewHolder(@NonNull View itemView) {
        super(itemView);

        addtocart=itemView.findViewById(R.id.add_reccommended);
        reccomendedImage= itemView.findViewById(R.id.recommended_image);
        reccomendedName= itemView.findViewById(R.id.recommended_name);
        reccomendedRating= itemView.findViewById(R.id.recommended_rating);
        reccomendedPrice= itemView.findViewById(R.id.recommended_price);
    }
}


}
