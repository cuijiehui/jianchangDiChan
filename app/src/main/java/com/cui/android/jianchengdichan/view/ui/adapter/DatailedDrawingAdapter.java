package com.cui.android.jianchengdichan.view.ui.adapter;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.utils.Bimp;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.MainRecyclerAdapter;

import java.util.LinkedList;
import java.util.List;

public class DatailedDrawingAdapter extends RecyclerView.Adapter<DatailedDrawingAdapter.ViewHolder>{

    LinkedList<String> dataList ;
    View.OnClickListener addListener;
    View.OnClickListener compileListener;

    public DatailedDrawingAdapter(LinkedList<String> dataList, View.OnClickListener addListener, View.OnClickListener compileListener) {
        this.dataList = dataList;
        this.addListener = addListener;
        this.compileListener =compileListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_datailed_drawing_layout, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(dataList.get(position).equals("-1")){
            holder.iv_add_drawing.setOnClickListener(addListener);
            return;
        }else{
            holder.iv_add_drawing.setOnClickListener(compileListener);
            Bitmap bitmap = Bimp.getLoacalBitmap(dataList.get(position));
            holder.iv_add_drawing.setImageBitmap(bitmap);
            bitmap=null;
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_add_drawing ;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_add_drawing=  itemView.findViewById(R.id.iv_add_drawing);
        }
    }
}
