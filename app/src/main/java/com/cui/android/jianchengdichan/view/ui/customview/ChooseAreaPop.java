package com.cui.android.jianchengdichan.view.ui.customview;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.utils.ScreenUtils;
import com.cui.android.jianchengdichan.view.ui.customview.Adapter.ComChildAdapter;
import com.cui.android.jianchengdichan.view.ui.customview.interfaces.IdentityResultListener;

import java.util.ArrayList;
import java.util.List;

public class ChooseAreaPop extends PopupWindow {

    private Context context;
    private View view;
    private String cityName;
    private ListView lv_choose_area;
    private ComChildAdapter adapter;
    private List<ChildCommunityBean> allList = new ArrayList<>();
    private ChildCommunityBean childBean;
    private IdentityResultListener listener;
    public ChooseAreaPop(Context context , String cityS,List<ChildCommunityBean> allList  , IdentityResultListener listener) {
        this.context = context;
        this.cityName = cityS;
        this.listener = listener;
        this.allList = allList;
        view = LayoutInflater.from(context).inflate(R.layout.pop_choose_area, null);
        this.setContentView(view);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(ScreenUtils.getScreenHeight(context) - ScreenUtils.getStatusBarHeight(context) - ScreenUtils.dpToPx(context , 146));
        setBackgroundDrawable(new BitmapDrawable());
        setTouchable(true);


        initView();

    }
    private void initView() {

        lv_choose_area = (ListView) view.findViewById(R.id.lv_choose_area);
        adapter = new ComChildAdapter(context);
        adapter.setList(allList);
        lv_choose_area.setAdapter(adapter);

        lv_choose_area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                adapter.setSelectItem(position);
                childBean = allList.get(position);
                adapter.notifyDataSetChanged();
                listener.identityResult(allList.get(position).getId());
                ChooseAreaPop.this.dismiss();

            }
        });
    }

}
