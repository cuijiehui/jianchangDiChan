package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CommentListBean;
import com.cui.android.jianchengdichan.http.bean.TopicListBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.CommentPresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ScreenUtils;
import com.cui.android.jianchengdichan.utils.TimeUtil;
import com.cui.android.jianchengdichan.view.base.BaseActivity;
import com.cui.android.jianchengdichan.view.ui.adapter.CommentDataAdapter;
import com.cui.android.jianchengdichan.view.ui.customview.CircleImageView;
import com.cui.android.jianchengdichan.view.ui.customview.EditTextView;
import com.cui.android.jianchengdichan.view.ui.customview.FullyLinearLayoutManager;
import com.cui.android.jianchengdichan.view.ui.customview.SquareImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CommentActivity extends BaseActivity implements View.OnLayoutChangeListener {
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.rv_comment_data)
    RecyclerView rvCommentData;
    TopicListBean topicListBean;

    CommentPresenter commentPresenter = new CommentPresenter();
    List<CommentListBean> dataList = new ArrayList<>();
    CommentDataAdapter commentDataAdapter;
    @BindView(R.id.iv_topic_headimg)
    CircleImageView ivTopicHeadimg;

    @BindView(R.id.pic_ll)
    RelativeLayout picLl;
    @BindView(R.id.tv_topic_name)
    TextView tvTopicName;
    @BindView(R.id.tv_topic_time)
    TextView tvTopicTime;
    @BindView(R.id.iv_topic_share)
    ImageView ivTopicShare;
    @BindView(R.id.tv_topic_title)
    TextView tvTopicTitle;
    @BindView(R.id.tv_topic_content)
    TextView tvTopicContent;
    @BindView(R.id.rl_topic_pic)
    GridLayout rlTopicPic;
    @BindView(R.id.tv_topic_like)
    TextView tvTopicLike;
    @BindView(R.id.tv_comm_act_topic)
    TextView tvCommActTopic;
    @BindView(R.id.tv_comment_button)
    TextView tv_comment_button;
    @BindView(R.id.tv_comment_add)
    TextView tvCommentAdd;
    @BindView(R.id.et_comment_content)
    EditTextView et_comment_content;
    @BindView(R.id.tv_comment_sent)
    TextView tvCommentSent;
    @BindView(R.id.rl_comment_com)
    RelativeLayout rlCommentCom;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;
    int page =1;
    @Override
    public BasePresenter initPresenter() {
        return commentPresenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //添加layout大小发生改变监听器
        getRootView().addOnLayoutChangeListener(this);
        int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        commentPresenter.getCommList(uid,topicListBean.getId(),page);
    }

    @Override
    public void initParms(Bundle parms) {
        if (parms != null) {
            topicListBean = (TopicListBean) parms.getSerializable("data");
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_comment;
    }

    @Override
    public void initView(View view) {
        setView();
        initRecyclerView();
        et_comment_content.setOnKeyBoardHideListener(new EditTextView.OnKeyBoardHideListener() {
            @Override
            public void onKeyHide(int keyCode, KeyEvent event) {
                //隐藏
                et_comment_content.setVisibility(View.GONE);
                tv_comment_button.setVisibility(View.VISIBLE);

            }
        });
        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight/3;
    }

    private void setView() {
        if (topicListBean != null) {
            tvContentName.setText(topicListBean.getTitle());
            Okhttp3Utils.getInstance().glide(mContext, topicListBean.getHeadimg(), ivTopicHeadimg);
            tvTopicName.setText(topicListBean.getNickname());
            ivTopicShare.setVisibility(View.GONE);
            tvTopicTitle.setText(topicListBean.getTitle());
            tvTopicContent.setText(topicListBean.getContent());
            String time = TimeUtil.getTimeDetail(topicListBean.getCreate_time(),"yyyy-MM-dd HH:mm:ss");//2018-06-13 18:17:21
            if(!TextUtils.isEmpty(time)){
                tvTopicTime.setText(time);
            }
            if (topicListBean.getPic() != null && topicListBean.getPics().size() > 0) {
                rlTopicPic.setVisibility(View.VISIBLE);
                updateViewGroup(topicListBean.getPics(), rlTopicPic);
            } else {
                rlTopicPic.setVisibility(View.GONE);
            }
            tvCommActTopic.setText("+"+topicListBean.getComment_num());
            tvTopicLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
                    String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
                    if (topicListBean.getIs_praise() == 0) {
                        topicListBean.setIs_praise(1);
                        int praiseNum = new Integer(topicListBean.getPraise_num());
                        praiseNum += 1;
                        topicListBean.setPraise_num(praiseNum + "");
                        commentPresenter.doPraise(topicListBean.getId(), uid, token);
                    } else {
                        topicListBean.setIs_praise(0);
                        int praiseNum = new Integer(topicListBean.getPraise_num());
                        praiseNum -= 1;
                        topicListBean.setPraise_num(praiseNum + "");
                        commentPresenter.cancelPraise(topicListBean.getId(), uid, token);
                    }
                    changeLike();
                }
            });
            changeLike();
        }

    }

    public void changeLike() {
        if (topicListBean.getIs_praise() == 0) {
            Drawable drawable = mContext.getResources().getDrawable(R.drawable.to_like_icon);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvTopicLike.setCompoundDrawables(drawable, null, null, null);
        } else {
            Drawable drawable = mContext.getResources().getDrawable(R.drawable.to_like_icon_select);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvTopicLike.setCompoundDrawables(drawable, null, null, null);
        }
        tvTopicLike.setText("+"+topicListBean.getPraise_num());
    }

    private void initRecyclerView() {
        FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(mContext);
        rvCommentData.setLayoutManager(linearLayoutManager);
        commentDataAdapter = new CommentDataAdapter(R.layout.item_comm_list_layout, dataList);
        rvCommentData.setAdapter(commentDataAdapter);
        rvCommentData.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public View initBack() {
        return topBack;
    }


    public void getCommList(List<CommentListBean> data) {
        dataList.clear();
        if (data != null) {
            dataList.addAll(data);
        }
        commentDataAdapter.notifyDataSetChanged();
    }


    /**
     * 动态添加控件
     *
     * @param imageModels 图片集合
     */
    public void updateViewGroup(List<String> imageModels, GridLayout gridLayout) {
        gridLayout.removeAllViews();//清空子视图 防止原有的子视图影响
        int columnCount = gridLayout.getColumnCount();//得到列数
        int marginSize = ScreenUtils.dip2px(mContext, 4);//得到经过dp转化的margin值
        //遍历集合 动态添加
        for (int i = 0, size = imageModels.size(); i < size; i++) {
            GridLayout.Spec rowSpec = GridLayout.spec(i / columnCount);//行数
            GridLayout.Spec columnSpec = null;//列数 列宽的比例 weight=1
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                columnSpec = GridLayout.spec(i % columnCount, 1.0f);
            }
            ImageView imageView = new SquareImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            //由于宽（即列）已经定义权重比例 宽设置为0 保证均分
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(new ViewGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.rowSpec = rowSpec;
            layoutParams.columnSpec = columnSpec;
            if (imageModels.size() == 1) {
                ViewGroup.LayoutParams layoutParams1 = gridLayout.getLayoutParams();
                layoutParams1.width = ScreenUtils.dip2px(mContext, 200);
                layoutParams1.height = ScreenUtils.dip2px(mContext, 200);
                gridLayout.setLayoutParams(layoutParams1);
                Okhttp3Utils.getInstance().glide(mContext, imageModels.get(i), imageView, ScreenUtils.dip2px(mContext, 200), ScreenUtils.dip2px(mContext, 200));
            } else {
                ViewGroup.LayoutParams layoutParams1 = gridLayout.getLayoutParams();
                layoutParams1.width = ViewGroup.LayoutParams.MATCH_PARENT;
                layoutParams1.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                gridLayout.setLayoutParams(layoutParams1);
                Okhttp3Utils.getInstance().glide(mContext, imageModels.get(i), imageView);

            }
            layoutParams.setMargins(marginSize, marginSize, marginSize, marginSize);

            gridLayout.addView(imageView, layoutParams);
        }
    }

    public void cancelPraise() {

    }

    public void doPraise() {
    }



    @OnClick({R.id.tv_comment_button, R.id.tv_comment_add, R.id.tv_comment_sent, R.id.rl_comment_com})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_comment_button:
                // 弹出输入法
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                rlCommentCom.setVisibility(View.VISIBLE);
                tv_comment_button.setVisibility(View.GONE);
                break;
            case R.id.tv_comment_add:
                break;
            case R.id.tv_comment_sent:
                tv_comment_button.setVisibility(View.VISIBLE);
                rlCommentCom.setVisibility(View.GONE);
                // 隐藏输入法，然后暂存当前输入框的内容，方便下次使用
                InputMethodManager im = (InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(tvCommentSent.getWindowToken(), 0);
                sent();

                break;
            case R.id.rl_comment_com:
                break;
        }
    }

    private void sent() {
        String content =et_comment_content.getText().toString().trim();
        if(TextUtils.isEmpty(content)){
            return;
        }
        int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
        commentPresenter.senMsg(topicListBean.getId(),content,uid,token);
    }

    public void senMsg() {
        int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        commentPresenter.getCommList(uid,topicListBean.getId(),page);
    }


    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if(oldBottom != 0 && bottom != 0 &&(oldBottom - bottom > keyHeight)){
            LogUtils.i("监听到软键盘弹起");
        }else if(oldBottom != 0 && bottom != 0 &&(bottom - oldBottom > keyHeight)){
            LogUtils.i("监听到软件盘关闭");
            tv_comment_button.setVisibility(View.VISIBLE);
            rlCommentCom.setVisibility(View.GONE);

        }
    }
}
