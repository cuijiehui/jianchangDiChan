package com.cui.android.jianchengdichan.presenter;

import android.text.TextUtils;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.CityListBean;
import com.cui.android.jianchengdichan.http.bean.LeaseRoomBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.LeaseCentreActivity;
import com.google.gson.JsonObject;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LeaseCentrePresenter extends BasePresenter<LeaseCentreActivity> {
    public void getCityList(String key) {
        LogUtils.i("getCityList="+key);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("key", key);
        String json = jsonObject.toString();
        LogUtils.i("json=()" + json);
        DataModel.request(Token.API_CITY_LIST)
                .params(json)
                .execute(new CallBack<BaseBean<List<CityListBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<CityListBean>> data) {
                        getView().getCityList(data.getData());
                    }

                    @Override
                    public void onFailure(String msg) {
                        getView().onFailure(msg);
                    }

                    @Override
                    public void onError() {
                        getView().onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 参数名	必选	类型	说明
     * uid	是	int	用户id
     token	是	int	token
     city	是	string	当前定位城市，如：广州
     page	是	int	页码
     key	否	string	搜索关键字
     area	否	int	区域
     house_type	否	int	0:不限 1：一室 2、二室 3、三室 4、四室 5、四室以上
     orientations	否	string	朝向 如：南北
     minprice	否	string	房租下边界
     maxprice	否	string	房租上边界
     rent_type	否	string	出租方式
     charge_pay	否	string	押付方式
     ,String key
     ,int area
     ,int house_type
     ,String orientations
     ,String minprice
     ,String maxprice
     ,String rent_type
     ,String charge_pay
     */
    public void getRentList(int uid
            , String token
            , String city
            , int page
            , Map<String,String> data){

        LogUtils.i("getRentList="+uid);
        JsonObject jsonObject = new JsonObject();
        if (uid>0){
            jsonObject.addProperty("uid", uid);
        }
        if (!TextUtils.isEmpty(token)) {
            jsonObject.addProperty("token", token);
        }
        jsonObject.addProperty("city", city);
        jsonObject.addProperty("page", page);

        if(data!=null){
            Set entrys = data.entrySet(); // 1.获得所有的键值对Entry对象
            Iterator iter = entrys.iterator(); // 2.迭代出所有的entry
            while(iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey(); // 分别获得key和value
                String value = (String) entry.getValue();
                if(!TextUtils.isEmpty(value)){
                    jsonObject.addProperty(key, value);
                }
                LogUtils.i("key="+key+"--value="+value);
            }
        }
        String json = jsonObject.toString();
        LogUtils.i("json=()" + json);
        DataModel.request(Token.API_RENT_LIST)
                .params(json)
                .execute(new CallBack<BaseBean<List<LeaseRoomBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<LeaseRoomBean>> data) {
                        getView().getRentList(data.getData());
                    }

                    @Override
                    public void onFailure(String msg) {
                        getView().onFailure(msg);
                    }

                    @Override
                    public void onError() {
                        getView().onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
