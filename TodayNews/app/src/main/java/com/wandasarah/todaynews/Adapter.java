package com.wandasarah.todaynews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<Model> modelArrayList;
    private int lastPosition = -1;

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

        // Here you apply the animation when the view is bound
        // animasi ketika scroll view
        setAnimation(holder.itemView, position);

        Glide.with(context).load(modelArrayList.get(position).getUrlToImage()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(context,ReadNewsActivity.class);
                in.putExtra("URL",modelArrayList.get(position).getUrl());

                context.startActivity(in);
            }
        });
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
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
