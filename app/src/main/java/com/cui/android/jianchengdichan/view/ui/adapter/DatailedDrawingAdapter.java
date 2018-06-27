package com.cui.android.jianchengdichan.view.ui.adapter;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.utils.Bimp;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.view.ui.beans.ReleaseImgBean;

import java.util.List;

public class DatailedDrawingAdapter extends BaseQuickAdapter<ReleaseImgBean,BaseViewHolder> {
    public DatailedDrawingAdapter( @Nullable List<ReleaseImgBean> data) {
        super(R.layout.item_datailed_drawing_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReleaseImgBean item) {
        helper.addOnClickListener(R.id.iv_del_drawing);
        ImageView iv_add_drawing =helper.getView(R.id.iv_add_drawing);
        ImageView iv_del_drawing =helper.getView(R.id.iv_del_drawing);
        switch (item.getType()){
            case -1:
                iv_del_drawing.setVisibility(View.GONE);
                iv_add_drawing.setImageResource(R.drawable.add_pic_icon);
                break;
            case 1:
                iv_del_drawing.setVisibility(View.VISIBLE);
                Bitmap bitmap = Bimp.getLoacalBitmap(item.getPath());
                iv_add_drawing.setImageBitmap(bitmap);
                bitmap=null;
                break;
            case 2:
                iv_del_drawing.setVisibility(View.VISIBLE);
                Okhttp3Utils.getInstance().glide(MyApplication.getAppContext(),item.getUrl(),iv_add_drawing);
                break;
        }
    }


}
