package com.cui.android.jianchengdichan.view.ui.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CommunityBean;
import com.cui.android.jianchengdichan.presenter.InCommunityPresenter;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ScreenUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.ui.customview.adapter.ComChildAdapter;
import com.cui.android.jianchengdichan.view.ui.customview.adapter.ComGroupAdapter;
import com.cui.android.jianchengdichan.view.ui.customview.interfaces.ComResultListener;

import java.util.ArrayList;
import java.util.List;


/**
 * 选择社区的弹窗
 * <p>
 * Created by Android on 2017/6/2.
 */

public class ChooseComPop extends PopupWindow {

    private View view;
    private Context context;
    private String cUid;
    private String token;
    private List<CommunityBean> communityList;
    private List<ChildCommunityBean> childList = new ArrayList<>();

    private ListView lv_group;
    private ListView lv_child;

    private TextView tv_submit;
    private TextView tv_cancel;

    private ComGroupAdapter groupAdapter;
    private ComChildAdapter childAdapter;

    private ComResultListener comResultListener;

    private CommunityBean groupBean = null;
    private ChildCommunityBean childBean = null;


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    if (msg.obj != null) {
                        groupAdapter.notifyDataSetChanged();

//                        initAdapter(communityList.get(0).getList());
//                        groupBean = communityList.get(0);
//                        childBean = communityList.get(0).getList().get(0);

                    }
                    break;
            }
        }
    };
    private String area;
    private InCommunityPresenter inCommunityPresenter;

    public ChooseComPop(Context context, ComResultListener comResultListener, InCommunityPresenter inCommunityPresenter) {
        super(context);
        this.context = context;
        this.comResultListener = comResultListener;
        view = LayoutInflater.from(context).inflate(R.layout.pop_choose_com, null);
        this.setContentView(view);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(ScreenUtils.getScreenHeight(context) - ScreenUtils.getStatusBarHeight(context) - ScreenUtils.dpToPx(context, Float.valueOf(45)));
        this.inCommunityPresenter=inCommunityPresenter;
        setBackgroundDrawable(new BitmapDrawable());
        setTouchable(true);
        initView();
        initData();
    }

    private void initView() {
        int uid =(int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY,SPUtils.DATA_INT);
         token = (String)SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY,SPUtils.DATA_STRING);
        cUid=uid+"";
        area ="gz";
        communityList = new ArrayList<>();

        lv_group = (ListView) view.findViewById(R.id.lv_group);
        groupAdapter = new ComGroupAdapter(context, communityList);
        lv_group.setAdapter(groupAdapter);

        lv_child = (ListView) view.findViewById(R.id.lv_child);
        childAdapter = new ComChildAdapter(context);
        childAdapter.setList(childList);
        lv_child.setAdapter(childAdapter);

        tv_submit = (TextView) view.findViewById(R.id.tv_submit);
        tv_submit.setOnClickListener(clickListener);

        tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(clickListener);

        lv_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                groupBean = communityList.get(position);
                if (communityList.get(position).getList()==null||communityList.get(position).getList().size()==0){
                    //childBean = childList.get(0);
                    ToastUtil.makeToast("该社区下没有单元");
                    return;
                }
                childList.clear();
                childList.addAll( communityList.get(position).getList());
                groupAdapter.setSelectItem(position);
                childAdapter.setSelectItem(-1);
                groupAdapter.notifyDataSetChanged();
                initAdapter(childList);

            }
        });
        lv_group.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lv_child.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                childAdapter.setSelectItem(position);
                childBean = childList.get(position);
                childAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initAdapter(List<ChildCommunityBean> childList) {

        childAdapter.setList(childList);
        childAdapter.notifyDataSetChanged();
    }

    private void initData() {
        int uid= (int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY,SPUtils.DATA_INT);
        String token =(String)  SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY,SPUtils.DATA_STRING);
        inCommunityPresenter.getCommunityList(uid,token);
    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_submit:

                    if (groupBean != null && childBean != null) {
                        comResultListener.resultBean(groupBean, childBean);
                        ChooseComPop.this.dismiss();
                    } else {
                        ToastUtil.makeToast("您未选择社区");
                    }

                    break;
                case R.id.tv_cancel:

                    ChooseComPop.this.dismiss();

                    break;
            }
        }
    };

    public void getCommunityList(List<CommunityBean> data) {
        communityList.addAll(data);
         handler.obtainMessage(200, communityList).sendToTarget();

    }
}
