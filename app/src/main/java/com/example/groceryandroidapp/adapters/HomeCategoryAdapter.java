package com.example.groceryandroidapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.groceryandroidapp.R;
import com.example.groceryandroidapp.activities.ProductsActivity;
import com.example.groceryandroidapp.models.HomeCategoryModel;

import java.util.List;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {

    public HomeCategoryAdapter(Context context, List<HomeCategoryModel> categoryList) {
        this.context = context;
        this.categoryModelList = categoryList;
    }

    Context context;
    List<HomeCategoryModel> categoryModelList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(categoryModelList.get(position).getImg_url()).into(holder.categoryImg);
        holder.name.setText(categoryModelList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("type", categoryModelList.get(holder.getAdapterPosition()).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImg;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImg = itemView.findViewById(R.id.category_img);
            name = itemView.findViewById(R.id.category_name);
        }
    }
}
