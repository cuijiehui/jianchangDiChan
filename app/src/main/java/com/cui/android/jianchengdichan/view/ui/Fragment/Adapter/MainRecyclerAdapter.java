package com.cui.android.jianchengdichan.view.ui.Fragment.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    private List<String> list = new ArrayList<>();

    public MainRecyclerAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_item_layout, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
        holder.areaTextView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ;
        TextView textView,areaTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView=  itemView.findViewById(R.id.iv_recycler_img);
            textView = itemView.findViewById(R.id.tv_recycler_mane);
            areaTextView = itemView.findViewById(R.id.tv_recycler_area_mane);
        }
    }
}
