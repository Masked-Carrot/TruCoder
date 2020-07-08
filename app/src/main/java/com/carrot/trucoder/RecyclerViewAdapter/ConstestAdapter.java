package com.carrot.trucoder.RecyclerViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carrot.trucoder.Collection.ContestList;
import com.carrot.trucoder.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConstestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ContestList> contestLists = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.contest_recycle_layout , parent , false);
        return new RecyclerView.ViewHolder(v){
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView id = holder.itemView.findViewById(R.id.contest_recycle_text);
        id.setText(contestLists.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return contestLists.size();
    }

    public void setList(List<ContestList> list){
        Collections.reverse(list);
        this.contestLists = list;
        notifyDataSetChanged();
    }
}
