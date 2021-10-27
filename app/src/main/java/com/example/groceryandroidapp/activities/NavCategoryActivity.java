package com.example.groceryandroidapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryandroidapp.R;
import com.example.groceryandroidapp.adapters.NavCategoryDetailAdapter;
import com.example.groceryandroidapp.models.HomeCategoryModel;
import com.example.groceryandroidapp.models.NavCategoryDetailModel;
import com.example.groceryandroidapp.models.NavCategoryModel;
import com.example.groceryandroidapp.models.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NavCategoryActivity extends AppCompatActivity {

    FirebaseFirestore db;

    RecyclerView recyclerView;
    List<NavCategoryDetailModel> navCategoryDetailModelList;
    NavCategoryDetailAdapter navCategoryDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_category);

        db =  FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.category_detail_list);
        navCategoryDetailModelList = new ArrayList<>();
        navCategoryDetailAdapter = new NavCategoryDetailAdapter(this, navCategoryDetailModelList);
        recyclerView.setAdapter(navCategoryDetailAdapter);
        if (type != null && type.equalsIgnoreCase("fruits")){
            db.collection("NavCategoryDetail").whereEqualTo("type", "fruits").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        NavCategoryDetailModel navCategoryDetailModel = documentSnapshot.toObject(NavCategoryDetailModel.class);
                        navCategoryDetailModelList.add(navCategoryDetailModel);
                        navCategoryDetailAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        if (type != null && type.equalsIgnoreCase("drinks")){
            db.collection("NavCategoryDetail").whereEqualTo("type", "drinks").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        NavCategoryDetailModel navCategoryDetailModel = documentSnapshot.toObject(NavCategoryDetailModel.class);
                        navCategoryDetailModelList.add(navCategoryDetailModel);
                        navCategoryDetailAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        if (type != null && type.equalsIgnoreCase("breakfast")){
            db.collection("NavCategoryDetail").whereEqualTo("type", "breakfast").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                        NavCategoryDetailModel navCategoryDetailModel = documentSnapshot.toObject(NavCategoryDetailModel.class);
                        navCategoryDetailModelList.add(navCategoryDetailModel);
                        navCategoryDetailAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

    }
}