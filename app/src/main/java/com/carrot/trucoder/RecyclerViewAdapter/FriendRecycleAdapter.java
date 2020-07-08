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
import com.carrot.trucoder.Collection.UserInfoList;
import com.carrot.trucoder.Collection.UserInfoResposne;
import com.carrot.trucoder.Collection.UserRatingResponse;
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
        ratingtv.setText(list.get(position).getRating());
        ranktv.setText(list.get(position).getRank());
        Glide.with(context).load(list.get(position).getProfile()).into(profilepic);

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
