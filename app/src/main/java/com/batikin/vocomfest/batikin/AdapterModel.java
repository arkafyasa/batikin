package com.batikin.vocomfest.batikin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HP on 31/03/2018.
 */

public class AdapterModel extends RecyclerView.Adapter<AdapterModel.ModelViewHolder> {

    protected Context contextModel;
    protected List<ModelItem> modelItemList;

    public AdapterModel(Context contextModel, List<ModelItem> modelItemList) {
        this.contextModel = contextModel;
        this.modelItemList = modelItemList;
    }

    @Override
    public ModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_list, parent, false);

        return new ModelViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ModelViewHolder holder, int position) {
        ModelItem model = modelItemList.get(position);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contextModel, SelectSleeve.class);
                contextModel.startActivity(intent);
            }
        });
        holder.modelText.setText(model.modelName);
        Picasso.with(contextModel)
                .load(model.modelImage)
                .into(holder.modelImage);
    }

    @Override
    public int getItemCount() {
        return modelItemList.size();
    }


    public class ModelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView modelImage;
        TextView modelText;
        View mView;
        public ModelViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            modelImage = itemView.findViewById(R.id.imgModel);
            modelText = itemView.findViewById(R.id.txtModel);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent modelIntent = new Intent(view.getContext(),SelectSleeve.class);
            contextModel.startActivity(modelIntent);
        }
    }
}
