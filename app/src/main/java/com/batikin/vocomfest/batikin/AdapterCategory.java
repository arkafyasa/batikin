package com.batikin.vocomfest.batikin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HP on 30/03/2018.
 */

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.categoryViewHolder> {

    LayoutInflater categoryInflater;
    Context currentContext;
    ArrayList<CategoryItem> categoryItem;


    public AdapterCategory(Context ctx, ArrayList<CategoryItem> categoryItem){
        this.categoryInflater = LayoutInflater.from(ctx);
        this.currentContext = ctx;
        this.categoryItem = categoryItem;
    }

    @Override
    public AdapterCategory.categoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = categoryInflater.inflate(R.layout.row_category,parent,false);
        return new categoryViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(AdapterCategory.categoryViewHolder holder, int position) {
        CategoryItem currentItem = categoryItem.get(position);
        holder.categoryText.setText(currentItem.categoryName);
        Picasso.with(currentContext).load(currentItem.categoryImage).into(holder.categoryImg);
    }

    @Override
    public int getItemCount() {
        return categoryItem.size();
    }

    class categoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public AdapterCategory adapterCategory;
        public TextView categoryText;
        public ImageView categoryImg;

        public categoryViewHolder(View itemView,AdapterCategory adapterCategory) {
            super(itemView);
            categoryText = itemView.findViewById(R.id.txtCategory);
            categoryImg = itemView.findViewById(R.id.imgCategory);
            this.adapterCategory = adapterCategory;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
                Intent intentModel = new Intent(view.getContext(),ModelPage.class);
                intentModel.putExtra("category",categoryText.getText());
                currentContext.startActivity(intentModel);
        }
    }
}
