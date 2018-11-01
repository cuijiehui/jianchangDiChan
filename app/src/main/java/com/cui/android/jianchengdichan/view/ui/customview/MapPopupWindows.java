package com.cui.android.jianchengdichan.view.ui.customview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.utils.MapUtils;

import java.io.File;

public class MapPopupWindows extends PopupWindow {
    public static final int TAKE_PICTURE = 0;
    public static final int RESULT_LOAD_IMAGE = 1;
    public static final int CUT_PHOTO_REQUEST_CODE = 2;
    public static final int SELECTIMG_SEARCH = 3;
    private String path = "";
    private Uri photoUri;
    private double mlatitude =0;
    private double mLongitude =0;
    public Uri getPhotoUri(){
        return photoUri;
    }
    public MapPopupWindows(final Activity mContext, View parent,double latitude,double longitude) {

        View view = View
                .inflate(mContext, R.layout.popup_map_layout, null);
        mlatitude=latitude;
        mLongitude=longitude;
        // ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
        // R.anim.push_bottom_in_2));

        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        update();
        RelativeLayout rl_baidu_map = view
                .findViewById(R.id.rl_baidu_map);
        rl_baidu_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MapUtils.checkMapAppsIsExist(mContext,MapUtils.BAIDU_PKG)) {
                    MapUtils.openBaidu(mContext,mlatitude,mLongitude);
                }else{
                    Toast.makeText(mContext,"没有安照百度地图",Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
        RelativeLayout rl_gaode_map = view.findViewById(R.id.rl_gaode_map);
        rl_gaode_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MapUtils.checkMapAppsIsExist(mContext,MapUtils.GAODE_PKG)) {
                    MapUtils.openGaoDe(mContext,mlatitude,mLongitude);
                }else{
                    Toast.makeText(mContext,"没有安照高德地图",Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
        RelativeLayout cancel = view.findViewById(R.id.rl_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });



    }
    public void photo(Activity context) {
        try {
            Intent openCameraIntent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);

            String sdcardState = Environment.getExternalStorageState();
            String sdcardPathDir = Environment
                    .getExternalStorageDirectory().getPath() + "/tempImage/";
            File file = null;
            if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
                // 有sd卡，是否有myImage文件夹
                File fileDir = new File(sdcardPathDir);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                // 是否有headImg文件
                file = new File(sdcardPathDir + System.currentTimeMillis()
                        + ".JPEG");
            }
            if (file != null) {
                path = file.getPath();
                photoUri = Uri.fromFile(file);
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                context.startActivityForResult(openCameraIntent, TAKE_PICTURE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





