package com.cui.android.jianchengdichan.view.ui.Fragment.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepairsTypeAdapter extends RecyclerView.Adapter<RepairsTypeAdapter.MyViewHolder> {

    List<String > dataList;
    // 创建集合对象
    Map<String, TextView> viewList = new HashMap<>();

   public String selePosition ="1";
    public RepairsTypeAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RepairsTypeAdapter.MyViewHolder holder = new RepairsTypeAdapter.MyViewHolder(
                LayoutInflater.from(MyApplication.getAppContext())
                        .inflate(R.layout.item_repairs_type_layout , parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        viewList.put(position+"",holder.tv_type_text);
        holder.tv_type_text.setText(dataList.get(position));
        if(position==0){
            holder.tv_type_text.setBackgroundResource(R.drawable.bt_pressed_shape);
        }
        holder.tv_type_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBack(position);
            }
        });
    }

    private void setBack(int position) {
        selePosition=position+"";
        for (String key : viewList.keySet()) {
            if(key.equals(position+"")){
                viewList.get(key).setBackgroundResource(R.drawable.bt_pressed_shape);
            }else{
                viewList.get(key).setBackgroundResource(R.drawable.bt_pressed_gray_shape);
            }
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_type_text;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_type_text = (TextView) itemView.findViewById(R.id.tv_type_text);
        }
    }
}
