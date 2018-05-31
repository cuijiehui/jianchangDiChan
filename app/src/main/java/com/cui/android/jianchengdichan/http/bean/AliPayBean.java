package com.cui.android.jianchengdichan.http.bean;

public class AliPayBean {

    /**
     * orderinfo : app_id=2016090601854604&biz_content=%7B%22out_trade_no%22%3A%22201805301758209914139080%22%2C%22total_amount%22%3A0.01%2C%22subject%22%3A%222018%5Cu5e744%5Cu6708%5Cu4efd%5Cu6c34%5Cu8d39%5Cu3002%22%2C%22body%22%3A%22APP%3A0%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=utf-8&format=JSON&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fjc.szshide.cn%2Fapi%2FAlipay%2Fnotify&sign=Bo874GzAccn65WYQDgA8JAIDOEI8B3rR21qWvBUxF9vyokdNThu1tfayqaUEdI3J%2B92tMNw9ZridyiRRJ8iArjFkyBp4V0%2BwUG6fVkEjtH1rFRh%2BHYjxDFhaza0%2BHsRrwxwdO2kACpxI9aMLD6I9QSMlIWBOuVBgzQctgpnXhe15PRQH7GEGWIUw0qw9Y4DkDRj2wasQGxUd%2FC1y%2Btf7%2BY%2Ftb2PAFPqufq%2FA1Kw%2FbDeX1kcLSczyokxjjoTfJXTpT59nO%2B00xXzkcotE845vUkI6qDecvTUthi1iI6b8ewRHH%2FDpmRBqGeycNUwlk1uas3V7jtr79jmQmvvo7PhxrQ%3D%3D&sign_type=RSA2&timestamp=2018-05-30+17%3A58%3A20&version=1.0
     */

    private String orderinfo;

    public String getOrderinfo() {
        return orderinfo;
    }

    public void setOrderinfo(String orderinfo) {
        this.orderinfo = orderinfo;
    }
}
