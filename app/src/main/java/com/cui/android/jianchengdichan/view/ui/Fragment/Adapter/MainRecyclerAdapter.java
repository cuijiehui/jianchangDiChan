package com.cui.android.jianchengdichan.view.ui.Fragment.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.interfaces.OnRecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    private List<HomeDataBean.RentBean> list = new ArrayList<>();
    OnRecyclerViewItemClickListener mOnItemClickListener;
    public MainRecyclerAdapter(List<HomeDataBean.RentBean> list,OnRecyclerViewItemClickListener mOnItemClickListener) {
        this.list = list;
        this.mOnItemClickListener=mOnItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_recycler_item_layout, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        HomeDataBean.RentBean rentBean = list.get(position);
        holder.textView.setText(rentBean.getTitle());
        holder.areaTextView.setText(rentBean.getArea());
        holder.ll_recycler_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(v,position);
                }
            }
        });
        Glide.with(MyApplication.getAppContext()).load(rentBean.getPic()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ;
        TextView textView,areaTextView;
        LinearLayout ll_recycler_item;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=  itemView.findViewById(R.id.iv_recycler_img);
            textView = itemView.findViewById(R.id.tv_recycler_mane);
            areaTextView = itemView.findViewById(R.id.tv_recycler_area_mane);
            ll_recycler_item = itemView.findViewById(R.id.ll_recycler_item);
        }
    }
}
