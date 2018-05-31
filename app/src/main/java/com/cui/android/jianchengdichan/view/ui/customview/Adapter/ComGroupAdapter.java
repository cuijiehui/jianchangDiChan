package com.cui.android.jianchengdichan.view.ui.customview.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.http.bean.CommunityBean;

import java.util.List;

public class ComGroupAdapter extends BaseAdapter {

    private Context context;
    private List<CommunityBean> list;
    private LayoutInflater inflater;
    private int clickPosition = -1;

    public ComGroupAdapter(Context context, List<CommunityBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setSelectItem(int position) {
        this.clickPosition = position;
    }

    public int getSelectItem() {
        return clickPosition;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler = null;
        if (convertView == null){
            viewHodler = new ViewHodler();
            convertView = inflater.inflate(R.layout.item_community , null);
            viewHodler.tv_name = (TextView) convertView.findViewById(R.id.tv_community_name);
            viewHodler.lin_layout = (LinearLayout) convertView.findViewById(R.id.lin_layout);
            convertView.setTag(viewHodler);
        }else{
            viewHodler = (ViewHodler) convertView.getTag();
        }


        viewHodler.tv_name.setText(list.get(position).getName());
        viewHodler.tv_name.setBackgroundColor(Color.parseColor("#ffffff"));

        if (position == clickPosition) {

            LogUtils.i("position" + position + "clickPosition" + clickPosition);

            viewHodler.tv_name.setBackgroundColor(Color.parseColor("#eeeeee"));
        }

        return convertView;
    }


    static class ViewHodler{
        TextView tv_name;
        LinearLayout lin_layout;
    }

}
