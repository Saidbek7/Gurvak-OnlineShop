package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.adapter.AllMenuAdapter;
import com.example.foodapp.adapter.DrinkAdapter;
import com.example.foodapp.adapter.PopularAdapter;
import com.example.foodapp.adapter.ReccomendedAdapter;
import com.example.foodapp.category.Category;
import com.example.foodapp.category.CategoryAdapter;
import com.example.foodapp.model.Allmenu;
import com.example.foodapp.model.Drink;
import com.example.foodapp.model.FoodData;
import com.example.foodapp.model.Popular;
import com.example.foodapp.model.Recommended;
import com.example.foodapp.retrofit.ApiInterface;
import com.example.foodapp.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
       ProgressDialog progressDialog;
        ApiInterface apiInterface;
        RecyclerView popularRecyclerView,reccomendedRecyclerView,AllMenuRecyclerView,CategoryRecyclerView;
    SearchView search_edit;
        PopularAdapter popularAdapter;
        ReccomendedAdapter reccomendedAdapter;
        AllMenuAdapter allMenuAdapter;
        DrinkAdapter drinkAdapter;
        CategoryAdapter categoryAdapter;
        List<Category> categoryList;
        TextView natija;

    private List<Allmenu> mExampleList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

progressDialog = new ProgressDialog(this);
                apiInterface  = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        AllMenuRecyclerView = findViewById(R.id.all_recycler);


        progressDialog.setMessage("Iltimos kutib turing ma'lumotlar yuklanmoqda...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        Call<List<FoodData>> call = apiInterface.getAllData();


        CategoryRecyclerView = findViewById(R.id.category_recycle);

        //add data to Category;

        categoryList = new ArrayList<>();
        categoryList.add(new Category(0,R.drawable.drinksnoticon,"Suv va suv ichimliklar"));
        categoryList.add(new Category(1,R.drawable.milk,"Tuxum va sut mahsulotlari"));
        categoryList.add(new Category(2,R.drawable.salt,"Baqqollik mahsulotlari"));
        categoryList.add(new Category(3,R.drawable.carrot,"Sabzavot va Mevalar "));
        categoryList.add(new Category(4,R.drawable.meat,"Go'sht va go'sht mahsulotlari"));
        categoryList.add(new Category(5,R.drawable.baby,"Bolalar uchun mahsulotlar "));
        setCategoryRecycler(categoryList);




        call.enqueue(new Callback<List<FoodData>>() {
            @Override
            public void onResponse(Call<List<FoodData>> call, Response<List<FoodData>> response) {


                List<FoodData> foodDataList = response.body();
                getPopularData(foodDataList.get(0).getPopular());
                getReccomendedData(foodDataList.get(0).getRecommended());
                getAllmenuData(foodDataList.get(0).getAllmenu());
              //  getDrinkData(foodDataList.get(0).getDrinks());
                progressDialog.dismiss();



            }

            @Override
            public void onFailure(Call<List<FoodData>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Server ishlamayapti", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setCategoryRecycler(List<Category> CategoryDatalist)
    {
        categoryAdapter = new CategoryAdapter(this, CategoryDatalist);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        CategoryRecyclerView.setNestedScrollingEnabled(false);
        CategoryRecyclerView.setLayoutManager(layoutManager);
        CategoryRecyclerView.setAdapter(categoryAdapter);

    }


    private void getPopularData(List<Popular> popularList)
    {
        popularRecyclerView = findViewById(R.id.popular_recycler);
        popularAdapter = new PopularAdapter(this, popularList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        layoutManager.setAutoMeasureEnabled(true);
        popularRecyclerView.setNestedScrollingEnabled(false);
        popularRecyclerView.setLayoutManager(layoutManager);
        popularRecyclerView.setAdapter(popularAdapter);

    }
    private void getAllmenuData(List<Allmenu> allmenuList)
    {
        AllMenuRecyclerView = findViewById(R.id.all_recycler);
        allMenuAdapter = new AllMenuAdapter(this, allmenuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        layoutManager.setAutoMeasureEnabled(true);
        AllMenuRecyclerView.setNestedScrollingEnabled(false);
        AllMenuRecyclerView.setLayoutManager(layoutManager);
        AllMenuRecyclerView.setAdapter(allMenuAdapter);

    }


    private void getReccomendedData(List<Recommended> recommendedList)
    {
        reccomendedRecyclerView = findViewById(R.id.reccomended_recycler);
        reccomendedAdapter = new ReccomendedAdapter(this, recommendedList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        layoutManager.setAutoMeasureEnabled(true);
        reccomendedRecyclerView.setNestedScrollingEnabled(false);
        reccomendedRecyclerView.setLayoutManager(layoutManager);
        reccomendedRecyclerView.setAdapter(reccomendedAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        AllMenuRecyclerView.setVisibility(View.INVISIBLE);
        final MenuItem searchMenuitem = menu.findItem(R.id.action_search);
       SearchView searchView = (SearchView) searchMenuitem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                AllMenuRecyclerView.setVisibility(View.VISIBLE);
                return false;

            }

            @Override
            public boolean onQueryTextChange(String s) {

                allMenuAdapter.getFilter().filter(s);
                AllMenuRecyclerView.setVisibility(View.VISIBLE);
                return false;
            }

        });


        return super.onCreateOptionsMenu(menu);

    }
}