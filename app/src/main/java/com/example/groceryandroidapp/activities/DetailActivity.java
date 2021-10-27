package com.example.groceryandroidapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.groceryandroidapp.R;
import com.example.groceryandroidapp.models.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {
    TextView quantity;
    int totalQuantity = 1;
    int totalPrice = 0;
    ImageView detailImage;
    TextView price, rating;
    Button addToCartButton;
    ImageView addItem, removeItem;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    ProductModel productModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof ProductModel){
            productModel = (ProductModel) object;
        }

        quantity =  findViewById(R.id.quantity);
        detailImage = findViewById(R.id.product_detail_img);
        price = findViewById(R.id.product_detail_price);
        rating = findViewById(R.id.product_detail_rating);
        addToCartButton = findViewById(R.id.add_to_cart_button);
        addItem = findViewById(R.id.add_more_item);
        removeItem = findViewById(R.id.remove_item);


        if (productModel !=  null){
            Glide.with(getApplicationContext()).load(productModel.getImg_url()).into(detailImage);
            rating.setText(productModel.getRating());
            price.setText(productModel.getPrice()+ "/kg");

            totalPrice = productModel.getPrice() * totalQuantity;

            if (productModel.getType().equals("milk")){
                price.setText(productModel.getPrice()+ "/lit");
                totalPrice = productModel.getPrice() * totalQuantity;

            }
            if (productModel.getType().equals("drinks")){
                price.setText(productModel.getPrice()+ "/chai");
                totalPrice = productModel.getPrice() * totalQuantity;

            }
        }

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalQuantity < 10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = productModel.getPrice() * totalQuantity;

                }
            }
        });
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalQuantity > 0){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = productModel.getPrice() * totalQuantity;

                }
            }
        });
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });
    }
    private void addToCart() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate =  new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime =  new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();

        cartMap.put("productName", productModel.getName());
        cartMap.put("productPrice", price.getText().toString());
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("totalQuantity", quantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid()).collection("AddToCart").add(cartMap)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

    }
}