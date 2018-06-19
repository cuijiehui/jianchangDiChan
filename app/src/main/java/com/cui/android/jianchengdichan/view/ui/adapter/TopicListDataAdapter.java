package com.cui.android.jianchengdichan.view.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.TopicListBean;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.utils.ScreenUtils;
import com.cui.android.jianchengdichan.view.ui.customview.CircleImageView;
import com.cui.android.jianchengdichan.view.ui.customview.SquareImageView;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/19.
 * @details
 */
public class TopicListDataAdapter extends BaseQuickAdapter<TopicListBean,BaseViewHolder> {
    public TopicListDataAdapter(int layoutResId, @Nullable List<TopicListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TopicListBean item) {
        CircleImageView headimg = helper.getView(R.id.iv_topic_headimg);
        GridLayout rl_topic_pic = helper.getView(R.id.rl_topic_pic);
//        ImageView iv_topic_one_pic = helper.getView(R.id.iv_topic_one_pic);
//        RecyclerView rv_topic_pic = helper.getView(R.id.rv_topic_pic);
        Okhttp3Utils.getInstance().glide(mContext,item.getHeadimg(),headimg);
        helper.setText(R.id.tv_topic_name,item.getNickname());
        helper.setText(R.id.tv_topic_content,item.getContent());
        helper.setText(R.id.tv_topic_title,item.getTitle());
        if(item.getPic()!=null&&item.getPics().size()>0){
            rl_topic_pic.setVisibility(View.VISIBLE);
            updateViewGroup(item.getPics(),rl_topic_pic);
//            if(item.getPic().size()==1){
////                iv_topic_one_pic.setVisibility(View.VISIBLE);
////                rv_topic_pic.setVisibility(View.GONE);
//                String piUrl = item.getPic().get(0);
////                Okhttp3Utils.getInstance().glide(mContext,piUrl,iv_topic_one_pic);
//            }else{
////                iv_topic_one_pic.setVisibility(View.GONE);
////                rv_topic_pic.setVisibility(View.VISIBLE);
//            }
        }else{
            rl_topic_pic.setVisibility(View.GONE);
        }
    }
    /**
     * 动态添加控件
     *
     * @param imageModels 图片集合
     */
    public void updateViewGroup(List<String> imageModels, GridLayout gridLayout) {
        gridLayout.removeAllViews();//清空子视图 防止原有的子视图影响
        int columnCount=gridLayout.getColumnCount();//得到列数
        int marginSize = ScreenUtils.dip2px(mContext, 4);//得到经过dp转化的margin值
        //遍历集合 动态添加
        for (int i = 0, size = imageModels.size(); i < size; i++) {
            GridLayout.Spec rowSpec = GridLayout.spec(i / columnCount);//行数
            GridLayout.Spec columnSpec = null;//列数 列宽的比例 weight=1
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                columnSpec = GridLayout.spec(i % columnCount, 1.0f);
            }
            ImageView imageView = new SquareImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //由于宽（即列）已经定义权重比例 宽设置为0 保证均分
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(new ViewGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.rowSpec=rowSpec;
            layoutParams.columnSpec=columnSpec;
            if(imageModels.size()==1){
                ViewGroup.LayoutParams layoutParams1 = gridLayout.getLayoutParams();
                layoutParams1.width= ScreenUtils.dip2px(mContext, 300);
                layoutParams1.height=ScreenUtils.dip2px(mContext, 300);
                gridLayout.setLayoutParams(layoutParams1);
            }
            layoutParams.setMargins(marginSize, marginSize, marginSize, marginSize);
            Okhttp3Utils.getInstance().glide(mContext,imageModels.get(i),imageView);
            gridLayout.addView(imageView, layoutParams);
        }
    }
}
