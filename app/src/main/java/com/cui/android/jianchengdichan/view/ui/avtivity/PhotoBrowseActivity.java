package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.GlideApp;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;

/**
 * @author CUI
 * @data 2018/6/27.
 * @details
 */
public class PhotoBrowseActivity extends Activity {

    @BindView(R.id.viewpager)
    ViewPager imageViewPager;
    @BindView(R.id.iv_animate)
    ImageView ivAnimate;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;

    private int firstDisplayImageIndex = 0;
    private boolean newPageSelected = false;
    private ImageView mCurImage;
    private List<String> pictureList;

    PagerAdapter adapter;

    boolean canDrag = false;
    private Bundle mEndValues;
    private Bundle mStartValues;

    public static Intent startWithElement(Activity context, List<String> urls,
                                          int firstIndex, View view) {
        Intent intent = new Intent(context, PhotoBrowseActivity.class);
        ArrayList<String> urlData = new ArrayList<>();
        urlData.addAll(urls);
        intent.putStringArrayListExtra("urls", urlData);
        intent.putExtra("index", firstIndex);
        intent.putExtra("view", captureValues(view));
        return intent;
    }

    private static Bundle captureValues(@NonNull View view) {
        Bundle b = new Bundle();
        int[] screenLocation = new int[2];
        view.getLocationOnScreen(screenLocation);
        LogUtils.i("坐标："+screenLocation[0]);
        LogUtils.i("坐标："+screenLocation[1]);
        b.putInt("PROPNAME_SCREENLOCATION_LEFT", screenLocation[0]);
        b.putInt("PROPNAME_SCREENLOCATION_TOP", screenLocation[1]);
        b.putInt("PROPNAME_WIDTH", view.getWidth());
        b.putInt("PROPNAME_HEIGHT", view.getHeight());
        return b;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_photo_browse);
        ButterKnife.bind(this);
        initView();
        imageViewPager.setVisibility(View.INVISIBLE);
        glide(pictureList.get(firstDisplayImageIndex));
    }
    public void glide(String url){
        GlideApp.with(this)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        onUiReady();
                        return false;
                    }
                })
                .placeholder(R.drawable.image_cache)
                .into(ivAnimate);
    }
    public void onUiReady(){
        ivAnimate.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                ivAnimate.getViewTreeObserver().removeOnPreDrawListener(this);
                mEndValues = captureValues(ivAnimate);
                ivAnimate.setTranslationX(1);
                ivAnimate.setTranslationY(1);
                ivAnimate.animate()
                        .setDuration(5*1000)
                        .translationX(0)
                        .translationY(0)
                        .start();
                return true;
            }
        });
    }

    public void prepareScene(){
        mEndValues = captureValues(ivAnimate);
        scaleDelta(mStartValues,mEndValues);
        ivAnimate.setScaleX(5);
        ivAnimate.setScaleY(5);
        ivAnimate.setTranslationX(5);
        ivAnimate.setTranslationY(5);

    }

    private int scaleDelta(Bundle mStartValues, Bundle mEndValues) {
        mStartValues.getInt("PROPNAME_SCREENLOCATION_LEFT");
        mStartValues.getInt("PROPNAME_SCREENLOCATION_TOP");
        mStartValues.getInt("PROPNAME_WIDTH");
        mStartValues.getInt("PROPNAME_HEIGHT");

        mEndValues.getInt("PROPNAME_SCREENLOCATION_LEFT");
        mEndValues.getInt("PROPNAME_SCREENLOCATION_TOP");
        mEndValues.getInt("PROPNAME_WIDTH");
        mEndValues.getInt("PROPNAME_HEIGHT");
        return 0;
    }

    public void initView() {
        pictureList = getIntent().getStringArrayListExtra("urls");
        firstDisplayImageIndex = Math.min(getIntent().getIntExtra("index", firstDisplayImageIndex), pictureList.size());
        mStartValues=getIntent().getBundleExtra("view");

        setViewPagerAdapter();

//        setEnterSharedElementCallback(new SharedElementCallback() {
//
//            @Override
//            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
//                ViewGroup layout = (ViewGroup) imageViewPager.findViewWithTag(imageViewPager.getCurrentItem());
//                if (layout == null) {
//                    return;
//                }
//                View sharedView = layout.findViewById(R.id.image_view);
//                sharedElements.clear();
//                sharedElements.put("tansition_view", sharedView);
//            }
//        });
    }

    private void setViewPagerAdapter() {
        adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return pictureList == null ? 0 : pictureList.size();
            }

            @Override
            public void notifyDataSetChanged() {
                super.notifyDataSetChanged();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                View layout = (View) object;
                container.removeView(layout);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return (view == object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View layout;
                layout = LayoutInflater.from(PhotoBrowseActivity.this).inflate(R.layout.layout_browse, null);
//                layout.setOnClickListener(onClickListener);
                container.addView(layout);
                layout.setTag(position);

                if (position == firstDisplayImageIndex) {
                    onViewPagerSelected(position);
                }

                return layout;

            }

            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }
        };

        imageViewPager.setAdapter(adapter);
        imageViewPager.setOffscreenPageLimit(9);
        imageViewPager.setCurrentItem(firstDisplayImageIndex);
        imageViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset == 0f && newPageSelected) {
                    newPageSelected = false;
                    onViewPagerSelected(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                newPageSelected = true;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        imageViewPager.setiAnimClose(new BaseAnimCloseViewPager.IAnimClose() {
//            @Override
//            public boolean canDrag() {
//                return canDrag;
//            }
//
//            @Override
//            public void onPictureClick() {
//                finishAfterTransition();
//            }
//
//            @Override
//            public void onPictureRelease(View view) {
//                finishAfterTransition();
//            }
//        });
    }


    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            finishAfterTransition();
        }
    };


    private void onViewPagerSelected(int position) {
        updateCurrentImageView(position);
        setImageView(pictureList.get(position));
    }


    /**
     * 设置图片
     *
     * @param path
     */
    private void setImageView(final String path) {
        if (mCurImage.getDrawable() != null)//判断是否已经加载了图片，避免闪动
            return;
        if (TextUtils.isEmpty(path)) {
            mCurImage.setBackgroundColor(Color.GRAY);
            return;
        }
        canDrag = false;
        Okhttp3Utils.getInstance().glide(this, path, mCurImage);
    }

    // 初始化每个view的image
    protected void updateCurrentImageView(final int position) {
        View currentLayout = imageViewPager.findViewWithTag(position);
        if (currentLayout == null) {
            ViewCompat.postOnAnimation(imageViewPager, new Runnable() {

                @Override
                public void run() {
                    updateCurrentImageView(position);
                }
            });
            return;
        }
        mCurImage = (ImageView) currentLayout.findViewById(R.id.image_view);
//        imageViewPager.setCurrentShowView(mCurImage);
    }


    @Override
    public void finishAfterTransition() {
        Intent intent = new Intent();
        intent.putExtra("index", imageViewPager.getCurrentItem());
        setResult(RESULT_OK, intent);
        super.finishAfterTransition();
    }
}
