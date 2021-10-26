package com.example.groceryandroidapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.groceryandroidapp.R;
import com.example.groceryandroidapp.models.RecommendProductModel;

import java.util.List;

public class RecommendProductAdapter extends RecyclerView.Adapter<RecommendProductAdapter.ViewHolder> {

    public RecommendProductAdapter(Context context, List<RecommendProductModel> recommendProductModelList) {
        this.context = context;
        this.recommendProductModelList = recommendProductModelList;
    }

    Context context;
    List<RecommendProductModel> recommendProductModelList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(recommendProductModelList.get(position).getImg_url()).into(holder.recommendProductImg);
        holder.name.setText(recommendProductModelList.get(position).getName());
        holder.description.setText(recommendProductModelList.get(position).getDescription());
        holder.price.setText(recommendProductModelList.get(position).getPrice());
        holder.rating.setText(recommendProductModelList.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return recommendProductModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recommendProductImg;
        TextView name, description, price, rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recommendProductImg = itemView.findViewById(R.id.recommend_product_image);
            name = itemView.findViewById(R.id.recommend_product_name);
            description = itemView.findViewById(R.id.recommend_product_des);
            price = itemView.findViewById(R.id.recommend_product_price);
            rating = itemView.findViewById(R.id.recommend_product_rating);
        }
    }
}

