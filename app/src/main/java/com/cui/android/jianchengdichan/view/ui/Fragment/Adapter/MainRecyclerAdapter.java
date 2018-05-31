package com.cui.android.jianchengdichan.view.ui.Fragment.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    private List<HomeDataBean.RentBean> list = new ArrayList<>();

    public MainRecyclerAdapter(List<HomeDataBean.RentBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_recycler_item_layout, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeDataBean.RentBean rentBean = list.get(position);
        holder.textView.setText(rentBean.getTitle());
        holder.areaTextView.setText(rentBean.getArea());
        Glide.with(MyApplication.getAppContext()).load(rentBean.getPic()).into(holder.imageView);

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
