package com.cui.android.jianchengdichan.view.ui.fragment.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.RepairsBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepairsTypeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RepairsTypeAdapter(@Nullable List<String> data) {
        super(R.layout.item_repairs_type_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView tv_type_text = helper.getView(R.id.tv_type_text);
        final int position = helper.getPosition();
        viewList.put(position + "", tv_type_text);
        tv_type_text.setText(item);
        if (position == 0) {
            helper.setBackgroundRes(R.id.tv_type_text, R.drawable.bt_pressed_shape);
        }
        tv_type_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBack(position);

            }
        });
    }

    // 创建集合对象
    Map<String, TextView> viewList = new HashMap<>();

    public int selePosition = 0;


    private void setBack(int position) {
        selePosition = position;
        for (String key : viewList.keySet()) {
            if (key.equals(position + "")) {
                viewList.get(key).setBackgroundResource(R.drawable.bt_pressed_shape);
            } else {
                viewList.get(key).setBackgroundResource(R.drawable.bt_pressed_gray_shape);
            }
        }

    }

}
