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
import com.example.groceryandroidapp.models.PopularProductModel;

import java.util.List;

public class PopularProductAdapters extends RecyclerView.Adapter<PopularProductAdapters.ViewHolder> {

    private Context context;

    public PopularProductAdapters(Context context, List<PopularProductModel> popularProductModels) {
        this.context = context;
        this.popularProductModelsList = popularProductModels;
    }

    private List<PopularProductModel> popularProductModelsList;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popurlar_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(popularProductModelsList.get(position).getImg_url()).into(holder.popular_product_image);
        holder.name.setText(popularProductModelsList.get(position).getName());
        holder.description.setText(popularProductModelsList.get(position).getDescription());
        holder.rating.setText(popularProductModelsList.get(position).getRating());
        holder.discount.setText(popularProductModelsList.get(position).getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("type", popularProductModelsList.get(position).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularProductModelsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popular_product_image;
        TextView name, description,  rating, discount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popular_product_image = itemView.findViewById(R.id.popular_image);
            name = itemView.findViewById(R.id.popular_product_name);
            description = itemView.findViewById(R.id.popular_product_description);
            rating = itemView.findViewById(R.id.popular_product_rating);
            discount = itemView.findViewById(R.id.popular_product_discount);
        }
    }
}
