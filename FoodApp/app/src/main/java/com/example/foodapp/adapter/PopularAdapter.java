package com.example.foodapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.FoodDetails;
import com.example.foodapp.R;
import com.example.foodapp.model.Popular;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder>
    {
        private Context context;
        private List<Popular> popularList;

        public PopularAdapter(Context context, List<Popular> popularList) {
            this.context = context;
            this.popularList = popularList;
        }

        @NonNull
        @Override
        public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.popular_recycler_items,parent,false);


            return new PopularViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PopularViewHolder holder, final int position) {

            holder.popularName.setText(popularList.get(position).getName());
          Picasso.get().load(popularList.get(position).getImageUrl()).into(holder.popularImage);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, FoodDetails.class);
                    i.putExtra("name",popularList.get(position).getName());
                    i.putExtra("price",popularList.get(position).getPrice());
                    i.putExtra("rating",popularList.get(position).getRating());
                    i.putExtra("image",popularList.get(position).getImageUrl());
                    i.putExtra("note",popularList.get(position).getNote());
                    context.startActivity(i);

                }
            });
        }

        @Override
        public int getItemCount() {
            return popularList.size();
        }

        public static  class PopularViewHolder extends RecyclerView.ViewHolder{
            CircleImageView popularImage;
            TextView popularName;
            public PopularViewHolder(@NonNull View itemView) {
                super(itemView);

                popularName = itemView.findViewById(R.id.all_menu_name);
                popularImage = itemView.findViewById(R.id.all_menu_image);
            }
        }
    }

