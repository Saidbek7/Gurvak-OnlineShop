package com.example.foodapp.category;

public class Category
{

    int id;
    int imageUrl;
    String name;

    public Category() {

    }

    public Category(int id, int imageUrl,String name) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }
}
