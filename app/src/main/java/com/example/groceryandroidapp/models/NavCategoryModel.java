package com.example.groceryandroidapp.models;

public class NavCategoryModel {
    String name;

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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    String description;
    String img_url;
    String  discount;

    public NavCategoryModel() {
    }

    public NavCategoryModel(String name, String description, String img_url, String discount) {
        this.name = name;
        this.description = description;
        this.img_url = img_url;
        this.discount = discount;
    }

}
