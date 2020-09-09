package com.example.foodapp.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.adapter.DrinkAdapter;
import com.example.foodapp.adapter.PopularAdapter;
import com.example.foodapp.adapter.ReccomendedAdapter;
import com.example.foodapp.model.Drink;
import com.example.foodapp.model.FoodData;
import com.example.foodapp.model.Popular;
import com.example.foodapp.model.Recommended;
import com.example.foodapp.retrofit.ApiInterface;
import com.example.foodapp.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrinksActivity extends AppCompatActivity {
    String name;
    String id;
    TextView itemName;
    RecyclerView popularRecyclerView,reccomendedRecyclerView,AllMenuRecyclerView,CategoryRecyclerView;
    SearchView search_edit;
    PopularAdapter popularAdapter;
    ReccomendedAdapter reccomendedAdapter;
    ProgressDialog progressDialog;
    ApiInterface apiInterface;
    RecyclerView DrinkRecyclerView;
    DrinkAdapter drinkAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);




        RecyclerView drinksRecycler = findViewById(R.id.drinks_recycle);
        RecyclerView RecomendRecycler = findViewById(R.id.drinks_reccomended_recycler);

        progressDialog = new ProgressDialog(this);
        apiInterface  = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

        progressDialog.setMessage("Iltimos kutib turing ma'lumotlar yuklanmoqda...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        Call<List<FoodData>> call = apiInterface.getAllData();
        call.enqueue(new Callback<List<FoodData>>() {
            @Override
            public void onResponse(Call<List<FoodData>> call, Response<List<FoodData>> response) {
                List<FoodData> foodDataList = response.body();
                getDrinkData(foodDataList.get(0).getDrinks());
                getReccomendedData(foodDataList.get(0).getRecommended());
                progressDialog.dismiss();


            }

            @Override
            public void onFailure(Call<List<FoodData>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DrinksActivity.this, "Server ishlamayapti", Toast.LENGTH_SHORT).show();

            }
        });

        Intent intent = getIntent();

        name = intent.getStringExtra("categfory_name");
        id = intent.getStringExtra("categfory_id");
        itemName = findViewById(R.id.category_name);
        itemName.setText(name);






    }

    private void getDrinkData(List<Drink> drinks)
    {

        DrinkRecyclerView = findViewById(R.id.drinks_recycle);
        drinkAdapter = new DrinkAdapter(this, drinks);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        DrinkRecyclerView.setLayoutManager(layoutManager);
        DrinkRecyclerView.setAdapter(drinkAdapter);



    }

    private void getReccomendedData(List<Recommended> recommendedList)
    {
        reccomendedRecyclerView = findViewById(R.id.drinks_reccomended_recycler);
        reccomendedAdapter = new ReccomendedAdapter(this, recommendedList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        layoutManager.setAutoMeasureEnabled(true);
        reccomendedRecyclerView.setNestedScrollingEnabled(false);
        reccomendedRecyclerView.setLayoutManager(layoutManager);
        reccomendedRecyclerView.setAdapter(reccomendedAdapter);



    }




}