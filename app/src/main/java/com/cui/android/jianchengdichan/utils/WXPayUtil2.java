package com.cui.android.jianchengdichan.utils;

import android.content.Context;
import android.widget.Toast;

import com.cui.android.jianchengdichan.http.bean.WXPayEntity2;
import com.cui.android.jianchengdichan.http.bean.WeChatPayBean;
import com.google.gson.Gson;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WXPayUtil2 implements HttpRequestListener {
    private Context context;
    private IWXAPI api;
    private String timeStamp;

    public WXPayUtil2(Context context){
        this.context=context;
        regToWX();
    }

    /**
     * @param tradeNo 订单号
     */
    public void pay(String tradeNo){
//        try{
//            String url = AppConfig.WA_ORDER_NUM2;
//            Toast.makeText(CoresunApp.instance, "获取订单中...", Toast.LENGTH_SHORT).show();
//            HttpUtil http=new HttpUtil();
//            http.setOnHttpRequestFinishListener(this);
//
//            List<Params> list=new ArrayList<>();
//            list.add(new Params("out_trade_no",tradeNo));
//            http.sendPost(url,list);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

    public void pay(String tradeNo , String price){
//        try{
//            String url =AppConfig.WA_ORDER_NUM2;
//            Toast.makeText(CoresunApp.instance, "获取订单中...", Toast.LENGTH_SHORT).show();
//            HttpUtil http=new HttpUtil();
//            http.setOnHttpRequestFinishListener(this);
//            List<Params> list=new ArrayList<>();
//            list.add(new Params("ordersn",tradeNo));
//            list.add(new Params("price",price));
//            http.sendPost(url,list);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }


    public void pay(String uid , String token ,String id){
//        String url = AppConfig.COMMUNITY_BASE_URL + AppConfig.PAY_ORDER;
//        try {
//            HttpUtil http = new HttpUtil();
//            http.setOnHttpRequestFinishListener(this);
//            List<Params> list = new ArrayList<>();
//            list.add(new Params("uid", uid));
//            list.add(new Params("token", token));
//            list.add(new Params("id" , id));
//            http.sendPost(url , list);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void payExChange(WeChatPayBean weChat){
        String appId = weChat.getAppid();
        String partnerId = weChat.getPartnerid();    // TODO: 2017/9/27  商户ID暂时写死
        String prepayId = weChat.getPrepayid();
        String packageValue = "Sign=WXPay";
        String nonceStr = weChat.getNoncestr();
        String sign = weChat.getSign();
        String timeStamp=String.valueOf(System.currentTimeMillis() / 1000);
        LogUtils.e("e appid="+appId);
        LogUtils.e("e partnereId="+partnerId);
        LogUtils.e("e prepayId="+prepayId);
        LogUtils.e("e packageValue="+packageValue);
        LogUtils.e("e nonceStr="+nonceStr);
        LogUtils.e("e sign="+sign);
        LogUtils.e("e timeStamp="+timeStamp);
        //根据参数获取签名
        sign=getSign(weChat,timeStamp);

        PayReq request = new PayReq();
        request.appId = appId;
        request.partnerId = partnerId;
        request.prepayId = prepayId;
        request.packageValue = packageValue;
        request.nonceStr = nonceStr;
        request.timeStamp = timeStamp;
        request.sign = sign;

        api.sendReq(request);


    }


    //将app注册到微信
    private void regToWX() {
        //通过工厂获取IWXAPI实例
        api= WXAPIFactory.createWXAPI(context, Constants.APP_ID, true);
        //将应用的appid注册到微信
        api.registerApp(Constants.APP_ID);
    }

    @Override
    public void onHttpRequestFinish(String result) throws JSONException {
        LogUtils.i("微信支付返回:"+result);
        WXPayEntity2 enty = null;
        if (result == null && result.equals("")){
            return;
        }
        Gson gson =new Gson();
        if (result.contains("code")){
            JSONObject jsonObject = new JSONObject(result);
            if (jsonObject.getString("code").equals("200")){
                JSONObject dataJsonObject = jsonObject.getJSONObject("data");
                enty=  gson.fromJson(dataJsonObject.toString(),WXPayEntity2.class);
            }else{
                ToastUtil.makeToast(jsonObject.getString("message"));
                return;
            }
        }else{
            enty=  gson.fromJson(result,WXPayEntity2.class);
        }

        String appId=enty.getAppid();
        String partnerId=enty.getPartnerid();
        String prepayId=enty.getPrepayid();
        String packageValue="Sign=WXPay";
        String nonceStr=enty.getNoncestr();
        String sign=enty.getSign();
        String timeStamp=String.valueOf(System.currentTimeMillis() / 1000);
        LogUtils.i("appid="+appId);
        LogUtils.i("partnerId="+partnerId);
        LogUtils.i("prepayId="+prepayId);
        LogUtils.i("packageValue="+packageValue);
        LogUtils.i("nonceStr="+nonceStr);
        LogUtils.i("sign="+sign);
        LogUtils.i("timeStamp="+timeStamp);
        //根据参数获取签名
        sign=getSign(enty,timeStamp);

        PayReq request = new PayReq();
        request.appId = appId;
        request.partnerId = partnerId;
        request.prepayId = prepayId;
        request.packageValue = packageValue;
        request.nonceStr = nonceStr;
        request.timeStamp = timeStamp;
        request.sign = sign;

        api.sendReq(request);
    }

    @Override
    public void onHttpRequestError(String error) {

    }

    //获取签名
    private String getSign(WXPayEntity2 enty,String timeStamp) {
        String appId=enty.getAppid();
        String partnerId=enty.getPartnerid();
        String prepayId=enty.getPrepayid();
        String packageValue="Sign=WXPay";
        String nonceStr=enty.getNoncestr();

        String stringA="appid="+appId+"&noncestr="+nonceStr+"&package="+packageValue+"&partnerid="+partnerId+"&prepayid="+prepayId+"&timestamp="+timeStamp;
        String tempSign=stringA+"&key="+Constants.key;

        String sign= MD5.getMessageDigest(tempSign.getBytes()).toUpperCase();

        LogUtils.i("拼接StringA:"+stringA);
        LogUtils.i("拼接tempSign:"+tempSign);
        LogUtils.i("最后签名:"+sign);
        return sign;
    }

    //获取签名
    private String getSign(WeChatPayBean weChat,String timeStamp) {
        String appId = weChat.getAppid();
        String partnerId =weChat.getPartnerid();// TODO: 2017/9/27  商户ID暂时写死
        String prepayId = weChat.getPrepayid();
        String packageValue = "Sign=WXPay";
        String nonceStr = weChat.getNoncestr();

        String stringA = "appid="+appId+"&noncestr="+nonceStr+"&package="+packageValue+"&partnerid="+partnerId+"&prepayid="+prepayId+"&timestamp="+timeStamp;
        String tempSign = stringA+"&key="+Constants.key;

        String sign= MD5.getMessageDigest(tempSign.getBytes()).toUpperCase();

        LogUtils.e("拼接StringA:"+stringA);
        LogUtils.e("拼接tempSign:"+tempSign);
        LogUtils.e("最后签名:"+sign);
        return sign;
    }
}
