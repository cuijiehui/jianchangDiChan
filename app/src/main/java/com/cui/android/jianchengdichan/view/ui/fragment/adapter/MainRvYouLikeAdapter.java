package com.cui.android.jianchengdichan.view.ui.fragment.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;

import java.util.ArrayList;
import java.util.List;

public class MainRvYouLikeAdapter extends BaseQuickAdapter<HomeDataBean.FavorBean,BaseViewHolder> {

    public MainRvYouLikeAdapter( @Nullable List<HomeDataBean.FavorBean> data) {
        super(R.layout.item_you_like_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeDataBean.FavorBean item) {
        helper.setText(R.id.tv_you_like_name,item.getShorttitle());
        helper.setText(R.id.tv_you_like_price_end,item.getMarketprice()+"元");
        helper.setText(R.id.tv_you_like_content,item.getTitle());
        helper.setText(R.id.tv_sales_volume,item.getSales());
        ImageView iv_you_like_pic = helper.getView(R.id.iv_you_like_pic);
        Okhttp3Utils.getInstance().glide(MyApplication.getAppContext(),item.getThumb(),iv_you_like_pic);

    }
}
