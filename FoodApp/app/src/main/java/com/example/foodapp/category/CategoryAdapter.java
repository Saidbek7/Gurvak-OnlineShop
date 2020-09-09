package com.example.foodapp.category;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter <CategoryAdapter.CategoryViewHolder>{

    Context context;

    List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_from_category,parent,false);


        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position)
    {
        holder.CategoryImagel.setImageResource(categoryList.get(position).getImageUrl());
        holder.CategoryName.setText(categoryList.get(position).getName());




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (position==0){

                Intent i = new Intent(context, DrinksActivity.class);
                i.putExtra("categfory_id",categoryList.get(position).getId());
                i.putExtra("categfory_name",categoryList.get(position).getName());
                context.startActivity(i);
            }
                if (position==1){

                    Toast.makeText(context, "Tuxum vas sut mahsulotlari", Toast.LENGTH_SHORT).show();
                }
                if (position==2){

                    Toast.makeText(context, "Baqqollik", Toast.LENGTH_SHORT).show();
                }
                if (position==3){

                    Toast.makeText(context, "Sabzavot va mevalar", Toast.LENGTH_SHORT).show();
                }


            }
        });



          }

    @Override
    public  int getItemCount() {
        return categoryList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder
{

    ImageView CategoryImagel;
    TextView CategoryName;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        CategoryImagel = itemView.findViewById(R.id.all_category_image);
        CategoryName = itemView.findViewById(R.id.all_category_name);
    }
}

}
