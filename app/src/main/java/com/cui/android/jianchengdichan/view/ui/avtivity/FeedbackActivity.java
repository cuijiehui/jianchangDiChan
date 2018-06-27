package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.FeedbackPresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.base.BaseActivtity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends BaseActivtity {
    FeedbackPresenter mFeedbackPresenter;
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.bt_feedback_opinion)
    Button btFeedbackOpinion;
    @BindView(R.id.et_feedback_content)
    EditText etFeedbackContent;
    @BindView(R.id.et_feedback_phone)
    EditText etFeedbackPhone;

    @Override
    public BasePresenter initPresenter() {
        mFeedbackPresenter = new FeedbackPresenter();
        return mFeedbackPresenter;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View initBack() {
        return topBack;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_feed_back_layout;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("意见反馈");
        tvTopRight.setVisibility(View.VISIBLE);
        tvTopRight.setText("反馈历史");
    }

    @Override
    public void doBusiness(Context mContext) {

    }



    @OnClick({R.id.top_back, R.id.tv_top_right, R.id.bt_feedback_opinion})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                LogUtils.i("top_back");
                break;
            case R.id.tv_top_right:
                LogUtils.i("tv_top_right");
                startActivity(new Intent(mContext, FeedbackHistoryActivity.class));
                break;
            case R.id.bt_feedback_opinion:
                LogUtils.i("bt_feedback_opinion");
                toOpinion();
                break;
        }
    }

    private void toOpinion() {
        String content = etFeedbackContent.getText().toString();
        if(TextUtils.isEmpty(content)){
            ToastUtil.makeToast("内容不能为空");
            return;
        }
        String phone = etFeedbackPhone.getText().toString();
        if(TextUtils.isEmpty(phone)){
            ToastUtil.makeToast("联系人不能为空");
            return;
        }
        int uid = (int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
        mFeedbackPresenter.getOpinion(uid,token,phone,content);
    }

    public void getOpinion() {
        showPopwindow();

    }

    /**
     * 显示popupWindow
     */
    private void showPopwindow() {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.feedback_popupwindow_layout, null);

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

        final PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);

        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);
//                 window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 设置popWindow的显示和消失动画
//                 window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(getRootView(), Gravity.CENTER, 0, 0);

        // 这里检验popWindow里的button是否可以点击
        ImageView iv_feeback_popup_del = (ImageView) view.findViewById(R.id.iv_feeback_popup_del);
        iv_feeback_popup_del.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                System.out.println("第一个按钮被点击了");
                window.dismiss();
            }
        });

        // popWindow消失监听方法
//                 window.setOnDismissListener(new PopupWindow.OnDismissListener() {
//
//                     @Override
//             public void onDismiss() {
//                                 System.out.println("popWindow消失");
//                             }
//         });

    }

    public void onFailure() {
    }

    public void onError() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
