package com.cui.android.jianchengdichan.view.ui.customview;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.presenter.InCommunityPresenter;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ScreenUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.ui.customview.adapter.ComChildAdapter;
import com.cui.android.jianchengdichan.view.ui.customview.interfaces.CodeResultListener;

import java.util.ArrayList;
import java.util.List;

public class ChooseCodePop extends PopupWindow {

    private View view;
    private Context context;
    private int cUid;
    private String token;

    private TextView tv_submit;
    private TextView tv_cancel;
    private String id;
    private List<ChildCommunityBean> allList = new ArrayList<>();

    private CodeResultListener codeResultListener;
    private int current = -1;
    private ListView lv_code;
    private ComChildAdapter childAdapter;


    private String area;
    InCommunityPresenter inCommunityPresenter;


    public ChooseCodePop(Context context , String id , CodeResultListener codeResultListener,InCommunityPresenter inCommunityPresenter) {
        super(context);
        this.context = context;
        this.codeResultListener = codeResultListener;
        this.id = id;
        view = LayoutInflater.from(context).inflate(R.layout.pop_choose_code, null);
        this.setContentView(view);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.inCommunityPresenter= inCommunityPresenter;
        this.setHeight(ScreenUtils.getScreenHeight(context) - ScreenUtils.getStatusBarHeight(context) - ScreenUtils.dpToPx(context , Float.valueOf(45)));
        setBackgroundDrawable(new BitmapDrawable());
        setTouchable(true);
        initView();
        initData();
    }

    private void initView() {
         cUid =(int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY,SPUtils.DATA_INT);
        token = (String)SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY,SPUtils.DATA_STRING);
        area ="gz";
        tv_submit = (TextView) view.findViewById(R.id.tv_submit);
        tv_submit.setOnClickListener(clickListener);

        tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(clickListener);

        lv_code = (ListView) view.findViewById(R.id.lv_code);
        childAdapter = new ComChildAdapter(context);
        childAdapter.setList(allList);
        lv_code.setAdapter(childAdapter);

        lv_code.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                childAdapter.setSelectItem(position);
                current = position;
                childAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initData() {
        inCommunityPresenter.getPropertyList(cUid,token,id);
//        GetDataBiz.getCodeListData(area, cUid, token, id ,new HttpRequestListener() {
//            @Override
//            public void onHttpRequestFinish(String result) throws JSONException {
//                LogUtil.i("楼栋下级房间列表" + result);
//                if (result == null || result.equals("")){
//                    return;
//                }
//                Gson gson = new Gson();
//                JSONObject jsonObject = new JSONObject(result);
//                List<ChildCommunityBean> list = new ArrayList<ChildCommunityBean>();
//
//                if (jsonObject.getString("code").equals("200")){
//                    JSONArray jsonArray = jsonObject.getJSONArray("data");
//                    for (int i = 0 ; i < jsonArray.length() ; i++){
//                        JSONObject dataJsonObject = jsonArray.getJSONObject(i);
//                        ChildCommunityBean communityBean = gson.fromJson(dataJsonObject.toString() , ChildCommunityBean.class);
//                        list.add(communityBean);
//                    }
//                    handler.obtainMessage(200 , list).sendToTarget();
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
            switch (v.getId()){
                case R.id.tv_submit:

                    if (current != -1){
                        codeResultListener.codeBean(allList.get(current));
                        ChooseCodePop.this.dismiss();
                    }else{
                        ToastUtil.makeToast("未选择房间号");
                    }

                    break;
                case R.id.tv_cancel:
                    ChooseCodePop.this.dismiss();
                    break;
            }
        }
    };

    public void getUnitList(List<ChildCommunityBean> data) {
        allList.clear();
        allList.addAll(data);
        childAdapter.setList(allList);
        childAdapter.notifyDataSetChanged();
    }
}
