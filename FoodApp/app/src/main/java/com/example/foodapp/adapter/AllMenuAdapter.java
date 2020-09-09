package com.example.foodapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.FoodDetails;
import com.example.foodapp.R;
import com.example.foodapp.model.Allmenu;
import com.example.foodapp.model.Recommended;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllMenuAdapter extends RecyclerView.Adapter<AllMenuAdapter.AllMenuViewHolder> implements  Filterable
{

    private Context context;
    private List<Allmenu> allMenuList;
    List<Allmenu> examplelistFull;

    public AllMenuAdapter(Context context, List<Allmenu> allmenuList) {
        this.context = context;
        this.allMenuList = allmenuList;
        examplelistFull=new ArrayList<>(allmenuList);


    }



    @NonNull
    @Override
    public AllMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.allmenu_recycler_items,parent,false);


        return new AllMenuAdapter.AllMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllMenuViewHolder holder, final int position)
    {

        holder.all_menu_Name.setText(allMenuList.get(position).getName());
        holder.all_menu_Rating.setText(allMenuList.get(position).getRating());
        holder.all_menu_Price.setText(allMenuList.get(position).getPrice());
        Picasso.get().load(allMenuList.get(position).getImageUrl()).into(holder.all_menu_Image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, FoodDetails.class);
                i.putExtra("name",allMenuList.get(position).getName());
                i.putExtra("price",allMenuList.get(position).getPrice());
                i.putExtra("rating",allMenuList.get(position).getRating());
                i.putExtra("image",allMenuList.get(position).getImageUrl());
                i.putExtra("note",allMenuList.get(position).getNote());
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return allMenuList.size();
    }




    public static class AllMenuViewHolder extends RecyclerView.ViewHolder
    {
      CircleImageView all_menu_Image;
        TextView all_menu_Name,all_menu_Rating,all_menu_DeliveryTime,all_menu_note,all_menu_Price,all_menu_DeliveryCharges;
        public AllMenuViewHolder(@NonNull View itemView) {
            super(itemView);

            all_menu_Image= itemView.findViewById(R.id.all_menu_image);
            all_menu_Name= itemView.findViewById(R.id.all_menu_name);
            all_menu_Rating= itemView.findViewById(R.id.all_menu_rating);
            all_menu_Price= itemView.findViewById(R.id.all_menu_price);

        }
    }
    @Override
    public Filter getFilter() {
        return examplefilter;
    }

    private Filter examplefilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
           List<Allmenu> filtredList = new ArrayList<>();
           if (charSequence == null || charSequence.length()==0)
           {
               //filtredList.addAll(examplelistFull);


           }
           else
               {
                   String filterPattern  = charSequence.toString().toLowerCase().trim();

                   for (Allmenu allmenu:examplelistFull){
                       if (allmenu.getName().toLowerCase().contains(filterPattern)){
                           filtredList.add(allmenu);
                       }
                   }
               }
           FilterResults results = new FilterResults();
           results.values = filtredList;
           return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            allMenuList.clear();
            allMenuList.addAll((List)filterResults.values);
            notifyDataSetChanged();


        }
    };

}
