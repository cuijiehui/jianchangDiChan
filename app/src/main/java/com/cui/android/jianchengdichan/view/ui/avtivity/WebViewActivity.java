package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.wv_all)
    WebView wvAll;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    private String link;
    private String title="管家商城";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        getData();
        setSettings();
    }

    //获取上个页面传递过来的数据
    private void getData() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null){
            link = extras.getString("data");
            title= extras.getString("title","管家商城");
        }
        tvContentName.setText(title);

    }

    private void setSettings() {
        pbLoading.setVisibility(View.GONE);
        WebSettings settings = wvAll.getSettings();
        //设置可以使用javascript
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("gb2312");
        settings.setLoadsImagesAutomatically(true);
        settings.setBlockNetworkImage(false);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        // 这两行代码一定加上否则效果不会出现 加入SSH
        wvAll.setWebViewClient(new WebViewClient() {

            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                ///handler.cancel(); 默认的处理方式，WebView变成空白页
                handler.proceed(); // 接受证书
                //handleMessage(Message msg); 其他处理
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pbLoading.setVisibility(View.GONE);
            }
        });
        wvAll.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                tvContentName.setText(title);
            }
        });


        //加载页面
        wvAll.loadUrl(link);
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });

        wvAll.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Uri url = request.getUrl();
                    LogUtils.i("url="+url.getAuthority());
                    if ( url.getScheme().equals("js")) {

                        // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                        // 所以拦截url,下面JS开始调用Android需要的方法
                        if (url.getAuthority().equals("webview")) {

                            //  步骤3：
                            // 执行JS所需要调用的逻辑
//                            System.out.println("js调用了Android的方法");
//                            // 可以在协议上带有参数并传递到Android上
//                            HashMap<String, String> params = new HashMap<>();
//                            Set<String> collection = url.getQueryParameterNames();

                        }

//                        return true;
                    }

                }
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
    }

    @OnClick(R.id.top_back)
    public void onViewClicked() {
        finish();
    }
}
