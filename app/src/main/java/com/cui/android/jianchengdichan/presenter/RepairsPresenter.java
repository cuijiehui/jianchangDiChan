package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.RepairCateBean;
import com.cui.android.jianchengdichan.http.bean.RepairsBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.RepairsActivity;
import com.google.gson.JsonObject;

import java.util.List;

public class RepairsPresenter extends BasePresenter<RepairsActivity> {
    /**
     * 参数名	必选	类型	说明
     * uid	是	int	用户id
     * token	是	string	token
     * phone	是	string	手机号
     * name	是	string	姓名
     * describe	是	string	描述
     * pics	是	string	上传图片 不上传图片时传空字符串
     * type	是	int	报修信息类别 1：公共场所 2：房间
     * cate	是	string	报修类别
     * room_number	是	string	房号
     * title	是	string	标题
     */
    public void submitRepairInfo(int uid
            , String token
            , String phone
            , String name
            , String describe
            , String pics
            , String type
            , String cate
            , String title
    ) {
        LogUtils.i("submitRepairInfo()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid", uid);
        jsonObject.addProperty("token", token);
        jsonObject.addProperty("phone", phone);
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("describe", describe);
        jsonObject.addProperty("pics", pics);
        jsonObject.addProperty("type", type);
        jsonObject.addProperty("cate", cate);
        jsonObject.addProperty("title", title);
        String json = jsonObject.toString();
        DataModel.request(Token.API_REPAIR_INFO)
                .params(json)
                .execute(new CallBack<BaseBean<Object>>() {
                    @Override
                    public void onSuccess(BaseBean<Object> data) {
                        getView().submitRepairInfo();
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
     * uid	是	int	用户id
     * token	是	string	token
     * page	是	int	页数
     */
    public void getRepairInfoList(int uid, String token, int page) {
        LogUtils.i("getRepairInfoList()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid", uid);
        jsonObject.addProperty("token", token);
        jsonObject.addProperty("page", page);
        String json = jsonObject.toString();
        DataModel.request(Token.API_REPAIR_INFO_LIST)
                .params(json)
                .execute(new CallBack<BaseBean<List<RepairsBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<RepairsBean>> data) {
                        getView().getRepairInfoList(data.getData());
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

    public void getRepairCate() {
        LogUtils.i("getRepairCate()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        DataModel.request(Token.API_GET_REPAIR_CATE_MODEL)
                .params()
                .execute(new CallBack<BaseBean<List<RepairCateBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<RepairCateBean>> data) {
                        getView().getRepairCate(data.getData());
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
