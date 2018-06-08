package com.cui.android.jianchengdichan.view.ui.adapter;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.utils.Bimp;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.MainRecyclerAdapter;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.interfaces.OnRecyclerViewItemClickListener;
import com.cui.android.jianchengdichan.view.ui.beans.ReleaseImgBean;

import java.util.LinkedList;
import java.util.List;

public class DatailedDrawingAdapter extends RecyclerView.Adapter<DatailedDrawingAdapter.ViewHolder>{

    LinkedList<ReleaseImgBean> dataList ;
    View.OnClickListener addListener;
    OnRecyclerViewItemClickListener compileListener;

    public DatailedDrawingAdapter(LinkedList<ReleaseImgBean> dataList, View.OnClickListener addListener, OnRecyclerViewItemClickListener compileListener) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ReleaseImgBean bean=dataList.get(position);
        LogUtils.i("bean.getType()="+bean.getType());
        switch (bean.getType()){
            case -1:
                holder.iv_add_drawing.setOnClickListener(addListener);
                holder.iv_del_drawing.setVisibility(View.GONE);
                holder.iv_add_drawing.setImageResource(R.drawable.add_pic_icon);
                break;
            case 1:
                holder.iv_del_drawing.setVisibility(View.VISIBLE);
                holder.iv_add_drawing.setOnClickListener(null);

                holder.iv_del_drawing.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        compileListener.onItemClick(v,position);
                    }
                });
                Bitmap bitmap = Bimp.getLoacalBitmap(bean.getPath());
                holder.iv_add_drawing.setImageBitmap(bitmap);
                bitmap=null;
                break;
            case 2:
                holder.iv_del_drawing.setVisibility(View.VISIBLE);
                holder.iv_add_drawing.setOnClickListener(null);
                holder.iv_del_drawing.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        compileListener.onItemClick(v,position);
                    }
                });
                Okhttp3Utils.getInstance().glide(MyApplication.getAppContext(),bean.getUrl(), holder.iv_add_drawing);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_add_drawing ,iv_del_drawing;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_add_drawing=  itemView.findViewById(R.id.iv_add_drawing);
            iv_del_drawing=  itemView.findViewById(R.id.iv_del_drawing);
        }
    }
}
