//package com.cui.android.jianchengdichan.view.ui.avtivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.PersistableBundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.KeyEvent;
//import android.widget.ImageView;
//
//import com.cui.android.jianchengdichan.R;
//import com.journeyapps.barcodescanner.CaptureActivity;
//import com.journeyapps.barcodescanner.CaptureManager;
//import com.journeyapps.barcodescanner.DecoratedBarcodeView;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
//public class ScanActivity extends AppCompatActivity {
//
//    @BindView(R.id.dbv)
//    DecoratedBarcodeView mDBV;
//    @BindView(R.id.iv_back)
//    ImageView ivBack;
//    private CaptureManager captureManager;     //捕获管理器
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        captureManager.onPause();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        captureManager.onResume();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        captureManager.onDestroy();
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
//        captureManager.onSaveInstanceState(outState);
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        return mDBV.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scan);
//        ButterKnife.bind(this);
//        captureManager = new CaptureManager(this,mDBV);
//        captureManager.initializeFromIntent(getIntent(),savedInstanceState);
//        captureManager.decode();
//    }
//    @OnClick(R.id.iv_back)
//    public void onBack(){
//        finish();
//    }
//
//}
