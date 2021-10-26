package com.example.groceryandroidapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryandroidapp.R;
import com.example.groceryandroidapp.adapters.ProductAdapter;
import com.example.groceryandroidapp.models.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    List<ProductModel> productModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        firestore = FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.view_product_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productModelList =  new ArrayList<>();
        productAdapter = new ProductAdapter(this, productModelList);
        recyclerView.setAdapter(productAdapter);

        if (type != null && type.equalsIgnoreCase("fruits")){
            firestore.collection("Products").whereEqualTo("type", "fruits").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ProductModel productModel = documentSnapshot.toObject(ProductModel.class);
                        productModelList.add(productModel);
                        productAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("vegetables")){
            firestore.collection("Products").whereEqualTo("type", "vegetables").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ProductModel productModel = documentSnapshot.toObject(ProductModel.class);
                        productModelList.add(productModel);
                        productAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("drinks")){
            firestore.collection("Products").whereEqualTo("type", "drinks").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ProductModel productModel = documentSnapshot.toObject(ProductModel.class);
                        productModelList.add(productModel);
                        productAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("milk")){
            firestore.collection("Products").whereEqualTo("type", "milk").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ProductModel productModel = documentSnapshot.toObject(ProductModel.class);
                        productModelList.add(productModel);
                        productAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("egg")){
            firestore.collection("Products").whereEqualTo("type", "egg").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ProductModel productModel = documentSnapshot.toObject(ProductModel.class);
                        productModelList.add(productModel);
                        productAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("fish")){
            firestore.collection("Products").whereEqualTo("type", "fish").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        ProductModel productModel = documentSnapshot.toObject(ProductModel.class);
                        productModelList.add(productModel);
                        productAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }
}