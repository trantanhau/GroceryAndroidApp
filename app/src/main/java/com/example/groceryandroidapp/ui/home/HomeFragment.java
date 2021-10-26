package com.example.groceryandroidapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryandroidapp.adapters.HomeAdapter;
import com.example.groceryandroidapp.adapters.PopularProductAdapters;
import com.example.groceryandroidapp.adapters.RecommendProductAdapter;
import com.example.groceryandroidapp.databinding.FragmentHomeBinding;
import com.example.groceryandroidapp.models.HomeCategoryModel;
import com.example.groceryandroidapp.models.PopularProductModel;
import com.example.groceryandroidapp.models.RecommendProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ScrollView scrollView;
    ProgressBar progressBar;

    RecyclerView popularProductRecycle, homeCategoryRecycle, recommendProductRecycle;

    // popular products
    List<PopularProductModel> popularProductModelList;
    PopularProductAdapters popularProductAdapters;

    // home  category
    List<HomeCategoryModel> homeCategoryModelList;
    HomeAdapter homeAdapter;

    // recommend product
    List<RecommendProductModel> recommendProductModelList;
    RecommendProductAdapter recommendProductAdapter;

    FirebaseFirestore db;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        scrollView = binding.scrollView;
        progressBar = binding.progressBar;

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);


        // Popular Product
        popularProductRecycle = binding.popularProduct;
        popularProductRecycle.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popularProductModelList = new ArrayList<>();
        popularProductAdapters = new PopularProductAdapters(getActivity(), popularProductModelList);
        popularProductRecycle.setAdapter(popularProductAdapters);
        db.collection("PopularProduct")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularProductModel popularProductModel = document.toObject(PopularProductModel.class);
                                popularProductModelList.add(popularProductModel);
                                popularProductAdapters.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        // Home Category
        homeCategoryRecycle = binding.exploreProduct;

        homeCategoryRecycle.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        homeCategoryModelList = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(), homeCategoryModelList);
        homeCategoryRecycle.setAdapter(homeAdapter);
        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HomeCategoryModel homeCategoryModel = document.toObject(HomeCategoryModel.class);
                                homeCategoryModelList.add(homeCategoryModel);
                                homeAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        // Recommend Product
        recommendProductRecycle = binding.recommendProduct;

        recommendProductRecycle.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recommendProductModelList = new ArrayList<>();
        recommendProductAdapter = new RecommendProductAdapter(getActivity(), recommendProductModelList);
        recommendProductRecycle.setAdapter(recommendProductAdapter);
        db.collection("RecommendProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                RecommendProductModel recommendProductModel = document.toObject(RecommendProductModel.class);
                                recommendProductModelList.add(recommendProductModel);
                                recommendProductAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}