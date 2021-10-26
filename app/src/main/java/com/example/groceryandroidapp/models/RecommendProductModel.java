package com.example.groceryandroidapp.models;

public class RecommendProductModel {
    String name;
    String description;
    String img_url;
    String price;
    String rating;

    public RecommendProductModel() {
    }

    public RecommendProductModel(String name, String description, String img_url, String price, String rating) {
        this.name = name;
        this.description = description;
        this.img_url = img_url;
        this.price = price;
        this.rating = rating;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
