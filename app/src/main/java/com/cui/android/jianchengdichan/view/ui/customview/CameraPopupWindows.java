package com.cui.android.jianchengdichan.view.ui.customview;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.view.base.BaseActivity;
import com.cui.android.jianchengdichan.view.base.PermissionActivity;

import java.io.File;

public class CameraPopupWindows extends PopupWindow {
    public static final int TAKE_PICTURE = 0;
    public static final int RESULT_LOAD_IMAGE = 1;
    public static final int CUT_PHOTO_REQUEST_CODE = 2;
    public static final int SELECTIMG_SEARCH = 3;
    private String path = "";
    private Uri photoUri;
    public Uri getPhotoUri(){
        return photoUri;
    }
    public CameraPopupWindows(final BaseActivity mContext, View parent) {

        View view = View
                .inflate(mContext, R.layout.add_picture_layout, null);

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
        RelativeLayout photograph = view
                .findViewById(R.id.rl_photograph);
        photograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.checkPermission(new PermissionActivity.CheckPermListener() {
                    @Override
                    public void superPermission() {
                        photo(mContext);
                        dismiss();
                    }
                },R.string.perm_tip, Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
        });
        RelativeLayout albums = view.findViewById(R.id.rl_albums);
        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        // 相册
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                mContext.startActivityForResult(i, RESULT_LOAD_IMAGE);
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
            String sdcardPathDir = android.os.Environment
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





