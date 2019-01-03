package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.MyApplyBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.MyApplyPresenter;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.view.base.BaseActivity;
import com.cui.android.jianchengdichan.view.ui.adapter.ApplyCententAdapter;
import com.cui.android.jianchengdichan.view.ui.beans.PayingBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyApplyActivity extends BaseActivity {

    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.rv_apply_centent)
    RecyclerView rvApplyCentent;
    ApplyCententAdapter applyCententAdapter;
    List<MyApplyBean> myApplyBeans = new ArrayList<>();
    MyApplyPresenter myApplyPresenter = new MyApplyPresenter();

    @Override
    public BasePresenter initPresenter() {
        return myApplyPresenter;
    }


    @Override
    public void initParam(Bundle param) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_my_apply;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("发布记录");
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        rvApplyCentent.setLayoutManager(layoutManager1);
        applyCententAdapter = new ApplyCententAdapter(myApplyBeans);
        rvApplyCentent.setAdapter(applyCententAdapter);
        rvApplyCentent.addItemDecoration(new DividerItemDecoration(this,RecyclerView.VERTICAL));
        applyCententAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyApplyBean myApplyBean = myApplyBeans.get(position);

                switch (view.getId()) {
                    case R.id.iv_apply_del:
                        showDialog(myApplyBean);
                        break;
                    case R.id.iv_apply_com:
                        Intent intent = new Intent(mContext, ReleaseRentActivity.class);
                        Bundle bundle = new Bundle();
                        Gson gson = new Gson();
                        String json = gson.toJson(myApplyBean);
                        bundle.putString("json", json);
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
                        break;
                    case R.id.bt_paying:
                        String  id=myApplyBean.getId();
                        PayingBean payingBean = new PayingBean(
                                id
                                ,myApplyBean.getPic()
                                ,myApplyBean.getTitle()
                                ,myApplyBean.getCreate_time()
                                ,""
                                ,myApplyBean.getPay_money()+""
                                ,"2");
                        Intent payIntent = new Intent(mContext, PayingActivity.class);
                        Bundle payBundle = new Bundle();
                        payBundle.putSerializable("bean", payingBean);
                        payBundle.putString("typeName", "typaName");
                        payIntent.putExtras(payBundle);
                        mContext.startActivity(payIntent);
                        break;
                }
            }
        });
    }

    private void showDialog(final MyApplyBean myApplyBean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("")
                .setMessage("是否删除发布的租赁")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
                        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
                        myApplyPresenter.delRentInfo(uid, token, myApplyBean.getId());
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.show();
    }

    @Override
    public void doBusiness(Context mContext) {
        int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
        myApplyPresenter.getMyApplyModel(uid, token, 1);
    }

    @Override
    public View initBack() {
        return topBack;
    }

    public void getMyApplyModel(List<MyApplyBean> data) {
        if (data != null) {
            myApplyBeans.clear();
            myApplyBeans.addAll(data);
            applyCententAdapter.notifyDataSetChanged();
        }
    }

    public void delRentInfo() {
        int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
        myApplyPresenter.getMyApplyModel(uid, token, 1);
    }
}
