package com.appstone.maybatchtasksample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ItemsListHolder> {


    private Context context;
    private ArrayList<Items> items;


    public CustomAdapter (Context context , ArrayList<Items> items){
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public ItemsListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_cells,parent,false);
        ItemsListHolder holder = new ItemsListHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsListHolder holder, int position) {
        final Items items_lists = items.get(position);
        holder.info_id.setText(String.valueOf(items_lists.itemID));
        holder.info_title.setText(items_lists.title);
        holder.info_items.setText(items_lists.itemName);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemsListHolder extends RecyclerView.ViewHolder {
        private TextView info_id , info_title , info_items;
        public ItemsListHolder(@NonNull View itemView) {
            super(itemView);
            info_id = itemView.findViewById(R.id.tv_item_id);
            info_items =itemView.findViewById(R.id.tv_items_list);
            info_title =itemView.findViewById(R.id.tv_item_title_value);
        }
    }
}
