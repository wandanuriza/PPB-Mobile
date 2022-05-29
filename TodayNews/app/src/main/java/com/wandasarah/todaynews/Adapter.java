package com.wandasarah.todaynews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<Model> modelArrayList;

    public Adapter(Context context, ArrayList<Model> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.headlines.setText(modelArrayList.get(position).getTitle());
        holder.mainnews.setText(modelArrayList.get(position).getDescription());
        holder.auther.setText(modelArrayList.get(position).getAuther());
        holder.publishedat.setText(modelArrayList.get(position).getPublishedAt());
        Glide.with(context).load(modelArrayList.get(position).getUrlToImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {

        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView headlines, mainnews, auther, publishedat;

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            headlines = itemView.findViewById(R.id.mainHeadlines_id);
            mainnews = itemView.findViewById(R.id.newsdescription_id);
            auther = itemView.findViewById(R.id.auther_id);
            publishedat = itemView.findViewById(R.id.published_id);
            imageView = itemView.findViewById(R.id.news_image);
        }
    }
}
