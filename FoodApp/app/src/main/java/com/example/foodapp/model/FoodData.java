
package com.example.foodapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodData {

    @SerializedName("popular")
    @Expose
    private List<Popular> popular = null;

    @SerializedName("drinks")
    @Expose
    private List<Drink> drinks = null;

    @SerializedName("recommended")
    @Expose
    private List<Recommended> recommended = null;

    @SerializedName("allmenu")
    @Expose
    private List<Allmenu> allmenu = null;


    public List<Popular> getPopular() {
        return popular;
    }

    public void setPopular(List<Popular> popular) {
        this.popular = popular;
    }


    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }


    public List<Recommended> getRecommended() {
        return recommended;
    }

    public void setRecommended(List<Recommended> recommended) {
        this.recommended = recommended;
    }

    public List<Allmenu> getAllmenu() {
        return allmenu;
    }

    public void setAllmenu(List<Allmenu> allmenu) {
        this.allmenu = allmenu;
    }

}
