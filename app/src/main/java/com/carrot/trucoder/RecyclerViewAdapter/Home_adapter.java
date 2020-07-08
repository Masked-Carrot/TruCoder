package com.carrot.trucoder.RecyclerViewAdapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carrot.trucoder.Collection.UserRatingList;

import com.carrot.trucoder.R;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Home_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<UserRatingList> listList = new ArrayList<>();


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyclerview_layout , parent , false);
        return new RecyclerView.ViewHolder(v) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView id = holder.itemView.findViewById(R.id.contestid_recycle);
        TextView rating = holder.itemView.findViewById(R.id.rating_recycle);
        TextView rank = holder.itemView.findViewById(R.id.rank_recycle);
        UserRatingList userRatingList = listList.get(position);
        id.setText(userRatingList.getConstestName());
        rating.setText(String.valueOf(userRatingList.getNewRating() - userRatingList.getOldRating()));
        int  ratingchanges = userRatingList.getNewRating() - userRatingList.getOldRating();
        if(ratingchanges < 0)
            rating.setTextColor(Color.RED);
        else if (ratingchanges > 0)
            rating.setTextColor(Color.GREEN);
        rank.setText(userRatingList.getRank());
        setScaleAnimation(holder.itemView);

    }

    @Override
    public int getItemCount() {
        return listList.size();
    }

    public void setListList(List<UserRatingList> listList){
        Collections.reverse(listList);
        this.listList = listList;
        notifyDataSetChanged();
    }
    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(300);
        view.startAnimation(anim);
    }




}
