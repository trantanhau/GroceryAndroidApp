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
import com.example.groceryandroidapp.activities.NavCategoryActivity;
import com.example.groceryandroidapp.models.NavCategoryDetailModel;

import java.util.List;

public class NavCategoryDetailAdapter extends RecyclerView.Adapter<NavCategoryDetailAdapter.ViewHolder> {


    public NavCategoryDetailAdapter(Context context, List<NavCategoryDetailModel> navCategoryDetailModelList) {
        this.context = context;
        this.navCategoryDetailModelList = navCategoryDetailModelList;
    }

    Context  context;
    List<NavCategoryDetailModel> navCategoryDetailModelList;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_nav_detail_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(navCategoryDetailModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(navCategoryDetailModelList.get(position).getName());
        holder.price.setText(navCategoryDetailModelList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return navCategoryDetailModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.category_detail_img);
            name = itemView.findViewById(R.id.category_detail_name);
            price = itemView.findViewById(R.id.category_detail_price);
        }
    }
}
