package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.LeaseRoomBean;
import com.cui.android.jianchengdichan.http.bean.LeaveMsgListBean;
import com.cui.android.jianchengdichan.http.bean.RentDetailBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.RentDatailPresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.view.base.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.adapter.LeaseAdapter;
import com.cui.android.jianchengdichan.view.ui.adapter.RentLeaveMsgAdapter;
import com.cui.android.jianchengdichan.view.ui.customview.EditTextView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RentDatailActivity extends BaseActivtity implements View.OnLayoutChangeListener {

    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.bn_rent_datail)
    Banner bnRentDatail;
    @BindView(R.id.tv_rent_title)
    TextView tvRentTitle;
    @BindView(R.id.iv_rent_time_limit)
    TextView ivRentTimeLimit;
    @BindView(R.id.ll_rent_rent_msg)
    LinearLayout llRentRentMsg;
    @BindView(R.id.tv_rent_content)
    TextView tvRentContent;
    @BindView(R.id.tv_rent_data_area)
    TextView tvRentDataArea;
    @BindView(R.id.tv_rent_data_room)
    TextView tvRentDataRoom;
    @BindView(R.id.tv_rent_data_floor)
    TextView tvRentDataFloor;
    @BindView(R.id.tv_rent_data_address)
    TextView tvRentDataAddress;
    @BindView(R.id.rv_rent_leave_msg)
    RecyclerView rvRentLeaveMsg;
    @BindView(R.id.rent_leave_liuyan)
    TextView rentLeaveLiuyan;
    @BindView(R.id.iv_rent_to_msg)
    ImageView ivRentToMsg;
    @BindView(R.id.rl_rent_no_msg)
    RelativeLayout rlRentNoMsg;
    @BindView(R.id.rv_rent_recommend)
    RecyclerView rvRentRecommend;
    @BindView(R.id.rl_rent_call)
    RelativeLayout rlRentCall;
    @BindView(R.id.rl_rent_subscribe)
    RelativeLayout rlRentSubscribe;
    @BindView(R.id.rl_comment_com)
    RelativeLayout rlCommentCom;
    @BindView(R.id.tv_comment_sent)
    TextView tvCommentSent;
    @BindView(R.id.et_comment_content)
    EditTextView et_comment_content;
    LeaseAdapter leaseAdapter;

    RentDatailPresenter rentDatailPresenter ;
    private List<LeaseRoomBean> leaseRoomBeans = new ArrayList<LeaseRoomBean>();
    List<LeaveMsgListBean> leaveData = new ArrayList<>();
    private String mId;
    private List<String > picData= new ArrayList<>();
    RentLeaveMsgAdapter rentLeaveMsgAdapter;
    int page=1;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;
    @Override
    public BasePresenter initPresenter() {
        rentDatailPresenter=new RentDatailPresenter();
        return rentDatailPresenter;
    }

    @Override
    public void initParms(Bundle parms) {
        if(parms!=null){
            mId =   parms.getString("id");
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_rent_datail;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("详情");
        ivTopRight.setVisibility(View.VISIBLE);
        ivTopRight.setBackgroundResource(R.drawable.share_icon);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rvRentRecommend.setLayoutManager(linearLayoutManager);
        leaseAdapter = new LeaseAdapter(leaseRoomBeans);

        rvRentRecommend.setAdapter(leaseAdapter);
        leaseAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id",leaseRoomBeans.get(position).getId());
                startActivity(RentDatailActivity.class,bundle);
            }
        });
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mContext);
        rvRentLeaveMsg.setLayoutManager(linearLayoutManager1);
        rentLeaveMsgAdapter=new RentLeaveMsgAdapter(R.layout.rent_leave_msg_layout,leaveData);
        rvRentLeaveMsg.setAdapter(rentLeaveMsgAdapter);
        rvRentLeaveMsg.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        if(leaveData.size()>0){
            rvRentLeaveMsg.setVisibility(View.VISIBLE);
            rlRentNoMsg.setVisibility(View.GONE);
        }else{
            rvRentLeaveMsg.setVisibility(View.GONE);
            rlRentNoMsg.setVisibility(View.VISIBLE);

        }
        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight/3;
    }

    @Override
    public View initBack() {
        return topBack;
    }

    @Override
    public void doBusiness(Context mContext) {
        int uid = (int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY,SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY,SPUtils.DATA_STRING);
        rentDatailPresenter.rentDetail(uid,token,mId);
        rentDatailPresenter.leaveMsgList(mId,page,uid,token);
    }


    @OnClick({ R.id.iv_top_right, R.id.iv_rent_to_msg,R.id.rl_rent_call, R.id.rl_rent_subscribe,R.id.tv_comment_sent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_top_right:
                break;
            case R.id.iv_rent_to_msg:

                // 弹出输入法
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                rlCommentCom.setVisibility(View.VISIBLE);

                break;
            case R.id.tv_comment_sent:
                rlCommentCom.setVisibility(View.GONE);
                // 隐藏输入法，然后暂存当前输入框的内容，方便下次使用
                InputMethodManager im = (InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(tvCommentSent.getWindowToken(), 0);
                sent();
                break;
            case R.id.rl_rent_call:
                break;
            case R.id.rl_rent_subscribe:
                break;
        }
    }

    private void sent() {
        String content =et_comment_content.getText().toString().trim();
        if(TextUtils.isEmpty(content)){
            return;
        }
        int uid = (int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY,SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY,SPUtils.DATA_STRING);
        rentDatailPresenter.leaveMsg(mId,uid,token,content);
    }


    public void getRentDetail(RentDetailBean data) {
        LogUtils.i(data.toString());
        showView(data);
    }

    private void showView(RentDetailBean data) {

        tvRentTitle.setText(data.getTitle());
        tvRentContent.setText(data.getDetail());
        tvRentDataArea.setText("面积："+data.getAcreage()+"立方米");
        tvRentDataRoom.setText("厅室："+data.getHouse_type_info()+" "+data.getOrientations());
        tvRentDataFloor.setText("楼层："+data.getLocal_floor()+"/"+data.getTotal_floor()+"层");
        List<String > payMsg = new ArrayList<>();
        if (!TextUtils.isEmpty(data.getBan_type())) {
            payMsg.add(data.getBan_type());
        }
        if (!TextUtils.isEmpty(data.getCharge_pay())) {
            payMsg.add(data.getCharge_pay());
        }
        if (!TextUtils.isEmpty(data.getDecoration())) {
            payMsg.add(data.getDecoration());
        }
        ivRentTimeLimit.setText(data.getRental());
        initRentPayMsg(payMsg);
        initPicData(data.getPics());
        List<LeaseRoomBean> recommend = data.getRecommend();
        if(recommend!=null&&recommend.size()>0){
            leaseRoomBeans.clear();
            leaseRoomBeans.addAll(recommend);
            leaseAdapter.notifyDataSetChanged();
        }
    }

    private void initPicData(List<String> pics) {
        picData.clear();
        picData.addAll(pics);
        initAdvViewPage();

    }
    /**
     * 初始化图片轮播图
     */
    private void initAdvViewPage() {
        bnRentDatail.setImageLoader(new GlideLoader()); //设置图片加载器
        bnRentDatail.setImages(picData);//设置图片集合
        //设置自动轮播，默认为true
        bnRentDatail.isAutoPlay(true);
        //设置轮播时间
        bnRentDatail.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        bnRentDatail.setIndicatorGravity(BannerConfig.CENTER);
        bnRentDatail.start();
    }

    public void leaveMsgList(List<LeaveMsgListBean> data) {
        leaveData.clear();
        if(leaveData!=null){
            leaveData.addAll(data);
        }
        rentLeaveMsgAdapter.notifyDataSetChanged();
        if(leaveData.size()>0){
            rvRentLeaveMsg.setVisibility(View.VISIBLE);
            rlRentNoMsg.setVisibility(View.GONE);
        }else{
            rvRentLeaveMsg.setVisibility(View.GONE);
            rlRentNoMsg.setVisibility(View.VISIBLE);

        }
    }

    public void leaveMsg() {
        int uid = (int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY,SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY,SPUtils.DATA_STRING);
        rentDatailPresenter.leaveMsgList(mId,page,uid,token);
    }

    class GlideLoader extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            String picPath = (String) path;
            Okhttp3Utils.getInstance().glide(context,picPath,imageView);

        }
    }
    private void initRentPayMsg( List<String > payMsg) {
        if(payMsg.size()>0){
//            llRentRentMsg
            for (String msg:payMsg){
                TextView textView = new TextView(mContext);
                textView.setText(msg+"  ");
                llRentRentMsg.addView(textView);
            }
        }
    }
    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if(oldBottom != 0 && bottom != 0 &&(oldBottom - bottom > keyHeight)){
            LogUtils.i("监听到软键盘弹起");
        }else if(oldBottom != 0 && bottom != 0 &&(bottom - oldBottom > keyHeight)){
            LogUtils.i("监听到软件盘关闭");
            rlCommentCom.setVisibility(View.GONE);
        }
    }
}
