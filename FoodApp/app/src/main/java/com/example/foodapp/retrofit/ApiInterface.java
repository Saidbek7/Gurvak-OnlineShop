package com.example.foodapp.retrofit;

import com.example.foodapp.model.Drink;
import com.example.foodapp.model.FoodData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("drinks.json")
    Call<List<FoodData>> getAllData();


}
