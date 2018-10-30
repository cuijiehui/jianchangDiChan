package com.cui.android.jianchengdichan.view.ui.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.TopicListBean;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.utils.ScreenUtils;
import com.cui.android.jianchengdichan.utils.TimeUtil;
import com.cui.android.jianchengdichan.view.ui.avtivity.PhotoBrowseActivity;
import com.cui.android.jianchengdichan.view.ui.customview.CircleImageView;
import com.cui.android.jianchengdichan.view.ui.customview.SquareImageView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author CUI
 * @data 2018/6/19.
 * @details
 */
public class TopicListDataAdapter extends BaseQuickAdapter<TopicListBean, BaseViewHolder> {
    Activity mActivity;
    public TopicListDataAdapter(int layoutResId, @Nullable List<TopicListBean> data, Activity activity) {
        super(layoutResId, data);
        mActivity=activity;

    }
    ImageClickListener imageClickListener;
    List<Object> payloads = new LinkedList<>();

    @Override
    protected void convert(BaseViewHolder helper, TopicListBean item) {

        if (payloads.isEmpty()) {
            CircleImageView headimg = helper.getView(R.id.iv_topic_headimg);
            GridLayout rl_topic_pic = helper.getView(R.id.rl_topic_pic);
            Okhttp3Utils.getInstance().glide(mContext, item.getHeadimg(), headimg);
            helper.setText(R.id.tv_topic_name, item.getNickname());
            helper.setText(R.id.tv_topic_content, item.getContent());
            helper.setText(R.id.tv_topic_title, item.getTitle());
            if (item.getPic() != null && item.getPics().size() > 0) {
                rl_topic_pic.setVisibility(View.VISIBLE);
                updateViewGroup(item.getPics(), rl_topic_pic);
            } else {
                rl_topic_pic.setVisibility(View.GONE);
            }
            TextView tv_topic_like = helper.getView(R.id.tv_topic_like);
            tv_topic_like.setText("+" + item.getPraise_num());
            TextView tv_comm_act_topic = helper.getView(R.id.tv_comm_act_topic);
            tv_comm_act_topic.setText("+" + item.getComment_num());
            if (item.getIs_praise() == 0) {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.to_like_icon);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tv_topic_like.setCompoundDrawables(drawable, null, null, null);
            } else {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.to_like_icon_select);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tv_topic_like.setCompoundDrawables(drawable, null, null, null);
            }
            helper.addOnClickListener(R.id.tv_topic_like);
            helper.addOnClickListener(R.id.tv_comm_act_topic);
           String time = TimeUtil.getTimeDetail(item.getCreate_time(),"yyyy-MM-dd HH:mm:ss");//2018-06-13 18:17:21
            if(!TextUtils.isEmpty(time)){
                helper.setText(R.id.tv_topic_time,time);
            }
        } else {
            TextView tv_topic_like = helper.getView(R.id.tv_topic_like);
            tv_topic_like.setText("+" + item.getPraise_num());
            TextView tv_comm_act_topic = helper.getView(R.id.tv_comm_act_topic);
            tv_comm_act_topic.setText("+" + item.getComment_num());
            LogUtils.i(item.getIs_praise() + "==item.getIs_praise");
            if (item.getIs_praise() == 0) {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.to_like_icon);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tv_topic_like.setCompoundDrawables(drawable, null, null, null);
            } else {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.to_like_icon_select);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tv_topic_like.setCompoundDrawables(drawable, null, null, null);
            }
        }

    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull List<Object> payloads) {
        this.payloads = payloads;
        super.onBindViewHolder(holder, position, payloads);
    }
    public void setImageClickListener(ImageClickListener listener){
        imageClickListener=listener;
    }
    public interface ImageClickListener{
        void onClick(List<String> urls,
                     int firstIndex, View view);
    }
    /**
     * 动态添加控件
     *
     * @param imageModels 图片集合
     */
    public void updateViewGroup(final List<String> imageModels, GridLayout gridLayout) {
        if (gridLayout==null) return;
        gridLayout.removeAllViews();//清空子视图 防止原有的子视图影响
        int columnCount = gridLayout.getColumnCount();//得到列数
        int marginSize = ScreenUtils.dip2px(mContext, 4);//得到经过dp转化的margin值
        LogUtils.i("imageModels.size()="+imageModels.size());
        //遍历集合 动态添加
        for (int i = 0, size = imageModels.size(); i < size; i++) {
            GridLayout.Spec rowSpec = GridLayout.spec(i / columnCount);//行数
            GridLayout.Spec columnSpec = null;//列数 列宽的比例 weight=1
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                columnSpec = GridLayout.spec(i % columnCount, 1.0f);
            }
            final ImageView imageView = new SquareImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            //由于宽（即列）已经定义权重比例 宽设置为0 保证均分
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(new ViewGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.rowSpec = rowSpec;
            layoutParams.columnSpec = columnSpec;
            LogUtils.i("size="+size);

            if (size == 1) {
                ViewGroup.LayoutParams layoutParams1 = gridLayout.getLayoutParams();
                layoutParams1.width = ScreenUtils.dip2px(mContext, 200);
                layoutParams1.height = ScreenUtils.dip2px(mContext, 200);
                gridLayout.setLayoutParams(layoutParams1);
                Okhttp3Utils.getInstance().glide(mContext, imageModels.get(i), imageView, ScreenUtils.dip2px(mContext, 200), ScreenUtils.dip2px(mContext, 200));
            } else if (size == 2){
                ViewGroup.LayoutParams layoutParams1 = gridLayout.getLayoutParams();
                layoutParams1.width = ViewGroup.LayoutParams.MATCH_PARENT;
                layoutParams1.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                gridLayout.setLayoutParams(layoutParams1);
                Okhttp3Utils.getInstance().glide(mContext, imageModels.get(i), imageView,(gridLayout.getMeasuredWidth()/2-marginSize), (gridLayout.getMeasuredWidth()/2-marginSize));
            }else{
                ViewGroup.LayoutParams layoutParams1 = gridLayout.getLayoutParams();
                layoutParams1.width = ViewGroup.LayoutParams.MATCH_PARENT;
                layoutParams1.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                gridLayout.setLayoutParams(layoutParams1);
                LogUtils.i("gridLayout.W="+(gridLayout.getMeasuredWidth()/3-marginSize*2));
                Okhttp3Utils.getInstance().glide(mContext, imageModels.get(i), imageView, (gridLayout.getMeasuredWidth()/3-marginSize*2), (gridLayout.getMeasuredWidth()/3-marginSize*2));
            }


            layoutParams.setMargins(marginSize, marginSize, marginSize, marginSize);
            final  int position =i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(imageClickListener!=null){
                        imageClickListener.onClick(imageModels,position,imageView);
                    }
                }
            });
            gridLayout.addView(imageView, layoutParams);
        }
    }
}
