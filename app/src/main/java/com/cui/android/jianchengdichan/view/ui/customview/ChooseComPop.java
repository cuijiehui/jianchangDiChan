package com.cui.android.jianchengdichan.view.ui.customview;

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
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ScreenUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.ui.customview.Adapter.ComChildAdapter;
import com.cui.android.jianchengdichan.view.ui.customview.Adapter.ComGroupAdapter;
import com.cui.android.jianchengdichan.view.ui.customview.interfaces.ComResultListener;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private List<ChildCommunityBean> childList;

    private ListView lv_group;
    private ListView lv_child;

    private TextView tv_submit;
    private TextView tv_cancel;

    private ComGroupAdapter groupAdapter;
    private ComChildAdapter childAdapter;

    private ComResultListener comResultListener;

    private CommunityBean groupBean = null;
    private ChildCommunityBean childBean = null;


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
        childList = new ArrayList<>();

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
                childList = communityList.get(position).getList();
                groupAdapter.setSelectItem(position);
                childAdapter.setSelectItem(-1);
                groupAdapter.notifyDataSetChanged();
                initAdapter(childList);
                if (childList.size() == 0){
                    //childBean = childList.get(0);
                    ToastUtil.makeToast("该社区下没有单元");
                }
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
//        GetDataBiz.getComListData(area, cUid, token, new HttpRequestListener() {
//            @Override
//            public void onHttpRequestFinish(String result) throws JSONException {
//                LogUtils.i("社区列表的返回值" + result);
//                if (result == null || result.equals("")){
//                    return;
//                }
//                Gson gson = new Gson();
//                JSONObject jsonObject = new JSONObject(result);
//                if (jsonObject.getString("code").equals("200")) {
//                    JSONArray jsonArray = jsonObject.getJSONArray("data");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//
//                        JSONObject dataJsonObject = jsonArray.getJSONObject(i);
//                        CommunityBean communityBean = gson.fromJson(dataJsonObject.toString(), CommunityBean.class);
//
//                        JSONArray childJsonArray = dataJsonObject.getJSONArray("child");
//                        List<ChildCommunityBean> childList = new ArrayList<ChildCommunityBean>();
//                        for (int j = 0; j < childJsonArray.length(); j++) {
//                            JSONObject childJsonObject = childJsonArray.getJSONObject(j);
//                            ChildCommunityBean bean = gson.fromJson(childJsonObject.toString(), ChildCommunityBean.class);
//                            childList.add(bean);
//                        }
//                        communityBean.setList(childList);
//                        communityList.add(communityBean);
//                    }
//                    handler.obtainMessage(200, communityList).sendToTarget();
//                }
//            }
//
//            @Override
//            public void onHttpRequestError(String error) {
//
//            }
//        });
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
        for (CommunityBean communityBean:data){
            
        }
    }
}
