package com.example.groceryandroidapp.ui.category;

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

import com.example.groceryandroidapp.adapters.NavCategoryAdapter;
import com.example.groceryandroidapp.adapters.PopularProductAdapters;
import com.example.groceryandroidapp.databinding.FragmentCategoryBinding;
import com.example.groceryandroidapp.models.NavCategoryModel;
import com.example.groceryandroidapp.models.PopularProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    ScrollView scrollView;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    List<NavCategoryModel> navCategoryModelList;
    NavCategoryAdapter navCategoryAdapter;
    FirebaseFirestore db;
    private FragmentCategoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        db = FirebaseFirestore.getInstance();
        scrollView = binding.scrollView;
        progressBar = binding.progressBar;

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
        recyclerView = binding.categoryRecycle;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        navCategoryModelList = new ArrayList<>();
        navCategoryAdapter = new NavCategoryAdapter(getActivity(), navCategoryModelList);
        recyclerView.setAdapter(navCategoryAdapter);
        db.collection("NavCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NavCategoryModel navCategoryModel = document.toObject(NavCategoryModel.class);
                                navCategoryModelList.add(navCategoryModel);
                                navCategoryAdapter.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
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