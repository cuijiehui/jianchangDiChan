package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.ReleaseRentActivity;
import com.google.gson.JsonObject;

public class ReleaseRentPresenter extends BasePresenter<ReleaseRentActivity> {

    /**
     * 参数名	必选	类型	说明
     uid	是	int	用户名
     token	是	string	密码
     ban_type	是	string	房子类型 普通住宅 公寓 别墅 商铺
     title	是	string	小区
     house_type	是	string	房型 0:不限 1：一室 2、二室 3、三室 4、四室 5、四室以上
     house_type_info	是	string	房型 如：两室一厅一卫
     rental	是	string	租金 元/月
     orientations	是	string	朝向
     acreage	是	string	面积
     pic	是	string	封面图片
     pics	是	string	详情图片
     local_floor	是	int	所在楼层
     total_floor	是	int	总共楼层
     detail	是	string	描述
     mobile	是	string	手机号
     contact	是	string	联系人
     sex	是	int	性别 1：男 2：女
     fee	是	string	物业费
     */
    public void publishRentInfo(int uid
            ,String token
            ,String ban_type
            ,String title
            ,String house_type
            ,String house_type_info
            ,String rental
            ,String orientations
            ,String acreage
            ,String pic
            ,String pics
            ,String local_floor
            ,String total_floor
            ,String detail
            ,String mobile
            ,String contact
            ,String sex
            ,String fee
    ,String address
    ,String rent_type
    ,String charge_pay
    ) {
        LogUtils.i("publishRentInfo()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("ban_type",ban_type);
        jsonObject.addProperty("title",title);
        jsonObject.addProperty("house_type",house_type);
        jsonObject.addProperty("house_type_info",house_type_info);
        jsonObject.addProperty("rental",rental);
        jsonObject.addProperty("orientations",orientations);
        jsonObject.addProperty("acreage",acreage);
        jsonObject.addProperty("pic",pic);
        jsonObject.addProperty("pics",pics);
        jsonObject.addProperty("local_floor",local_floor);
        jsonObject.addProperty("total_floor",total_floor);
        jsonObject.addProperty("detail",detail);
        jsonObject.addProperty("mobile",mobile);
        jsonObject.addProperty("contact",contact);
        jsonObject.addProperty("sex",sex);
        jsonObject.addProperty("fee",fee);
        jsonObject.addProperty("address",address);
        jsonObject.addProperty("area","440106");
        jsonObject.addProperty("rent_type",rent_type);
        jsonObject.addProperty("charge_pay",charge_pay);
        String json =jsonObject.toString();
        DataModel.request(Token.API_REPAIR_RENT_INFO)
                .params(json)
                .execute(new CallBack<BaseBean<Object>>() {
                    @Override
                    public void onSuccess(BaseBean<Object> data) {
                        getView().publishRentInfo();
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
     uid	是	int	用户名
     token	是	string	密码
     ban_type	是	string	房子类型 普通住宅 公寓 别墅 商铺
     title	是	string	小区
     house_type	是	string	房型 0:不限 1：一室 2、二室 3、三室 4、四室 5、四室以上
     house_type_info	是	string	房型 如：两室一厅一卫
     rental	是	string	租金 元/月
     orientations	是	string	朝向
     acreage	是	string	面积
     pic	是	string	封面图片
     pics	是	string	详情图片
     local_floor	是	int	所在楼层
     total_floor	是	int	总共楼层
     detail	是	string	描述
     mobile	是	string	手机号
     contact	是	string	联系人
     sex	是	int	性别 1：男 2：女
     fee	是	string	物业费
     */
    public void updateRent(
            String id
            ,int uid
            ,String token
            ,String ban_type
            ,String title
            ,String house_type
            ,String house_type_info
            ,String rental
            ,String orientations
            ,String acreage
            ,String pic
            ,String pics
            ,String local_floor
            ,String total_floor
            ,String detail
            ,String mobile
            ,String contact
            ,String sex
            ,String fee
            ,String address
            ,String rent_type
            ,String charge_pay
    ) {
        LogUtils.i("publishRentInfo()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",id);
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("ban_type",ban_type);
        jsonObject.addProperty("title",title);
        jsonObject.addProperty("house_type",house_type);
        jsonObject.addProperty("house_type_info",house_type_info);
        jsonObject.addProperty("rental",rental);
        jsonObject.addProperty("orientations",orientations);
        jsonObject.addProperty("acreage",acreage);
        jsonObject.addProperty("pic",pic);
        jsonObject.addProperty("pics",pics);
        jsonObject.addProperty("local_floor",local_floor);
        jsonObject.addProperty("total_floor",total_floor);
        jsonObject.addProperty("detail",detail);
        jsonObject.addProperty("mobile",mobile);
        jsonObject.addProperty("contact",contact);
        jsonObject.addProperty("sex",sex);
        jsonObject.addProperty("fee",fee);
        jsonObject.addProperty("address",address);
        jsonObject.addProperty("area","440106");
        jsonObject.addProperty("rent_type",rent_type);
        jsonObject.addProperty("charge_pay",charge_pay);
        String json =jsonObject.toString();
        DataModel.request(Token.API_UPDATE_RENT_MODEL_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<Object>>() {
                    @Override
                    public void onSuccess(BaseBean<Object> data) {
                        getView().publishRentInfo();
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
