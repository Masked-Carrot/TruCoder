package com.carrot.trucoder.RecyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.carrot.trucoder.Collection.FriendList;

import com.carrot.trucoder.R;

import java.util.ArrayList;
import java.util.List;

public class FriendRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FriendList> list = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_layout , parent , false);
        return new RecyclerView.ViewHolder(v){

        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView handletv = holder.itemView.findViewById(R.id.friend_handle);
        TextView ratingtv = holder.itemView.findViewById(R.id.friend_rating);
        TextView ranktv = holder.itemView.findViewById(R.id.friend_rank);
        ImageView profilepic = holder.itemView.findViewById(R.id.friend_photo);
        handletv.setText(list.get(position).getHandle());
        int rating = Integer.parseInt(list.get(position).getRating());
        ratingtv.setText(list.get(position).getRating());
        ranktv.setText(list.get(position).getRank());
        Glide.with(context).load(list.get(position).getProfile()).into(profilepic);

        if(rating < 1200){
            ranktv.setTextColor(context.getResources().getColor(R.color.newbie));
            ratingtv.setTextColor(context.getResources().getColor(R.color.newbie));
        }
        else if(rating < 1400)
        {
            ranktv.setTextColor(context.getResources().getColor(R.color.pupil));
            ratingtv.setTextColor(context.getResources().getColor(R.color.pupil));
        }

        else if(rating < 1600)
        {
            ranktv.setTextColor(context.getResources().getColor(R.color.specilist));
            ratingtv.setTextColor(context.getResources().getColor(R.color.specilist));
        }

        else if(rating < 1900)
        {
            ranktv.setTextColor(context.getResources().getColor(R.color.expert));
            ratingtv.setTextColor(context.getResources().getColor(R.color.expert));
        }

        else if(rating < 2200)
        {
            ranktv.setTextColor(context.getResources().getColor(R.color.CandidateMaster));
            ratingtv.setTextColor(context.getResources().getColor(R.color.CandidateMaster));
        }

        else if(rating < 2400)
        {
            ranktv.setTextColor(context.getResources().getColor(R.color.InternationalMaster));
            ratingtv.setTextColor(context.getResources().getColor(R.color.InternationalMaster));
        }

        else if(rating < 2900)
        {
            ranktv.setTextColor(context.getResources().getColor(R.color.InternationalGrandmaster));
            ratingtv.setTextColor(context.getResources().getColor(R.color.InternationalGrandmaster));
        }

        else
        {
            ranktv.setTextColor(context.getResources().getColor(R.color.LegendaryGrandmaster));
            ratingtv.setTextColor(context.getResources().getColor(R.color.LegendaryGrandmaster));
        }


    }

    @Override
    public int getItemCount() {
        return list.size() ;
    }

    public void setList(List<FriendList> list , Context context){
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }
}
