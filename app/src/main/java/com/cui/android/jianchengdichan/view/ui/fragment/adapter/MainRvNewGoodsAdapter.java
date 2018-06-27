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
import com.cui.android.jianchengdichan.view.ui.fragment.adapter.adapterBean.CommunityBean;

import java.util.ArrayList;
import java.util.List;

public class MainRvNewGoodsAdapter extends BaseQuickAdapter<HomeDataBean.NewgoodBean,BaseViewHolder> {
    public MainRvNewGoodsAdapter( @Nullable List<HomeDataBean.NewgoodBean> data) {
        super(R.layout.item_new_goods_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeDataBean.NewgoodBean item) {
        helper.setText(R.id.tv_new_goods_name,item.getShorttitle());
        helper.setText(R.id.tv_new_goods_price,item.getMarketprice());
        ImageView iv_new_goods_pic = helper.getView(R.id.iv_new_goods_pic);
        Okhttp3Utils.getInstance().glide(MyApplication.getAppContext(),item.getThumb(),iv_new_goods_pic);

    }

}
