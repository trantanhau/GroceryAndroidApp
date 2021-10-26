package com.example.groceryandroidapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.groceryandroidapp.R;
import com.example.groceryandroidapp.models.ProductModel;

public class DetailActivity extends AppCompatActivity {
    ImageView detailImage;
    TextView price, rating;
    Button addToCartButton;
    ImageView addItem, removeItem;

    ProductModel productModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof ProductModel){
            productModel = (ProductModel) object;
        }

        detailImage = findViewById(R.id.product_detail_img);
        price = findViewById(R.id.product_detail_price);
        rating = findViewById(R.id.product_detail_rating);
        addToCartButton = findViewById(R.id.add_to_cart_button);
        addItem = findViewById(R.id.add_more_item);
        removeItem = findViewById(R.id.remove_item);


        if (productModel !=  null){
            Glide.with(getApplicationContext()).load(productModel.getImg_url()).into(detailImage);
            rating.setText(productModel.getRating());
            price.setText("Price:"+productModel.getPrice()+ "/kg");
            if (productModel.getType().equals("milk")){
                price.setText("Price:"+productModel.getPrice()+ "/lit");
            }
            if (productModel.getType().equals("drinks")){
                price.setText("Price:"+productModel.getPrice()+ "/chai");
            }
        }
    }
}