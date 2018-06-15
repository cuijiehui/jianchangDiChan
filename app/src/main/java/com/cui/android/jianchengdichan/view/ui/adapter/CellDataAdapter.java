package com.cui.android.jianchengdichan.view.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.TelephoneBean;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/14.
 * @details
 */
public class CellDataAdapter extends BaseQuickAdapter<TelephoneBean,BaseViewHolder> {

    public CellDataAdapter(int layoutResId, @Nullable List<TelephoneBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TelephoneBean item) {
        helper.setText(R.id.tv_cell_name,item.getName());
        helper.setText(R.id.tv_cell_phone,item.getPhone());
        helper.addOnClickListener(R.id.tv_cell_copy);
    }
}
