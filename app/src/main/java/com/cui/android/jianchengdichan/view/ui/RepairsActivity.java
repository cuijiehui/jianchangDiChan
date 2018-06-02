package com.cui.android.jianchengdichan.view.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.RepairsBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.RepairsPresenter;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.Fragment.RepairsFragment;
import com.cui.android.jianchengdichan.view.ui.Fragment.RepairsRecordFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepairsActivity extends BaseActivtity {

    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.tab_repairs)
    TabLayout tabRepairs;
    @BindView(R.id.lin_repairs_content)
    LinearLayout linRepairsContent;
    RepairsPresenter repairsPresenter =new RepairsPresenter();
    private Fragment[] fragmentArray = new Fragment[2];
    private int currentIndex = 0;//当前的fragment下标
    private int selectedIndex = currentIndex;
    @Override
    public BasePresenter initPresenter() {
        return repairsPresenter;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_repairs;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("报修");
        tabRepairs.addTab(tabRepairs.newTab().setText("我要报修"));
        tabRepairs.addTab(tabRepairs.newTab().setText("报修记录"));
        fragmentArray[0]=RepairsFragment.getInstance(repairsPresenter);
        fragmentArray[1]= RepairsRecordFragment.getInstance(repairsPresenter);
        //添加
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.lin_repairs_content,fragmentArray[0]);
        //提交
        transaction.commit();
        // 设置tab文本的没有选中（第一个参数）和选中（第二个参数）的颜色
        tabRepairs.setTabTextColors(Color.parseColor("#666666"), Color.parseColor("#0095D9"));
        TabLayout.TabLayoutOnPageChangeListener listener = new TabLayout.TabLayoutOnPageChangeListener(tabRepairs);
        tabRepairs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectedIndex = tab.getPosition();
                changePage();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    //改变页面
    private void changePage(){
        //选中的不是当前的按钮
        if(selectedIndex != currentIndex){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Fragment showFragment = fragmentArray[selectedIndex];
            if(!showFragment.isAdded()){
                //将选中的响应页面添加到页面
                transaction.add(R.id.lin_repairs_content,showFragment);
            }
            //隐藏 和显示
            transaction.remove(fragmentArray[currentIndex]);
            transaction.show(showFragment);
            transaction.commit();
            currentIndex=selectedIndex;
        }
    }
    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public View initBack() {
        return topBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void submitRepairInfo() {
        ToastUtil.makeToast("提交成功");
        tabRepairs.getTabAt(1).select();
    }

    public void getRepairInfoList(List<RepairsBean> data) {
        if(data!=null&&data.size()>0){
            RepairsRecordFragment repairsRecordFragment =   (RepairsRecordFragment)fragmentArray[1];
            repairsRecordFragment.setData(data);
        }
    }
}
