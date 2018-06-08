package com.cui.android.jianchengdichan.view.ui.Fragment.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.Transition;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.utils.ScreenUtils;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.interfaces.OnRecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    private List<HomeDataBean.RentBean> list = new ArrayList<>();
    OnRecyclerViewItemClickListener mOnItemClickListener;
    public MainRecyclerAdapter(List<HomeDataBean.RentBean> list,OnRecyclerViewItemClickListener mOnItemClickListener) {
        this.list = list;
        this.mOnItemClickListener=mOnItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_recycler_item_layout, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        HomeDataBean.RentBean rentBean = list.get(position);
        holder.textView.setText(rentBean.getTitle());
        holder.areaTextView.setText(rentBean.getArea());
        holder.ll_recycler_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(v,position);
                }
            }
        });


        Glide.with(MyApplication.getAppContext()).asBitmap().load(rentBean.getPic()).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable com.bumptech.glide.request.transition.Transition<? super Bitmap> transition) {
                holder.imageView.setImageBitmap(roundBitmapByShader(resource
                        , ScreenUtils.px2dip(MyApplication.getAppContext(), 900),
                        ScreenUtils.px2dip(MyApplication.getAppContext(),900),20));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ;
        TextView textView,areaTextView;
        LinearLayout ll_recycler_item;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=  itemView.findViewById(R.id.iv_recycler_img);
            textView = itemView.findViewById(R.id.tv_recycler_mane);
            areaTextView = itemView.findViewById(R.id.tv_recycler_area_mane);
            ll_recycler_item = itemView.findViewById(R.id.ll_recycler_item);
        }
    }
    /**
     * 利用BitmapShader绘制圆角图片
     *
     * @param bitmap
     *              待处理图片
     * @param outWidth
     *              结果图片宽度，一般为控件的宽度
     * @param outHeight
     *              结果图片高度，一般为控件的高度
     * @param radius
     *              圆角半径大小
     * @return
     *              结果图片
     */
    private Bitmap roundBitmapByShader(Bitmap bitmap, int outWidth, int outHeight, int radius) {
        if(bitmap == null) {
            throw new NullPointerException("Bitmap can't be null");
        }
        // 初始化缩放比
        float widthScale = outWidth * 1.0f / bitmap.getWidth();
        float heightScale = outHeight * 1.0f / bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale(widthScale, heightScale);

        // 初始化绘制纹理图
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        // 根据控件大小对纹理图进行拉伸缩放处理
        bitmapShader.setLocalMatrix(matrix);

        // 初始化目标bitmap
        Bitmap targetBitmap = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);

        // 初始化目标画布
        Canvas targetCanvas = new Canvas(targetBitmap);

        // 初始化画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);

        // 利用画笔将纹理图绘制到画布上面
        targetCanvas.drawRoundRect(new RectF(0, 0, outWidth, outWidth), radius, radius, paint);

        return targetBitmap;
    }
}
