package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.CommunityBean;
import com.cui.android.jianchengdichan.http.bean.UserCommunityBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.InCommunityActivity;
import com.cui.android.jianchengdichan.view.ui.customview.ChildCommunityBean;
import com.google.gson.JsonObject;

import java.util.List;

public class InCommunityPresenter extends BasePresenter<InCommunityActivity> {
    /**
     * uid	是	int	用户名
     token	是	string	token
     name	是	string	名称
     type	是	int	用户身份 1=>”业主”,2=>’家人’, 3=>”物业员工”,4=>’物业高管’,5=>’其他’,
     community_id	是	int	社区id
     unit_id	否	int	单元楼栋id
     property_id	否	int	房间Id
     * @param uid
     * @param token
     */
    public void setUserCommunity(int uid
            ,String token
            ,String name
            ,String type
            ,String community_id
            ,String unit_id
            ,String property_id){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("name",name);
        jsonObject.addProperty("type",type);
        jsonObject.addProperty("community_id",community_id);
        jsonObject.addProperty("unit_id",unit_id);
        jsonObject.addProperty("property_id",property_id);
        String json =jsonObject.toString();
        LogUtils.i("json=()"+json);
        DataModel.request(Token.API_USER_COMMUNITY_IMG)
                .params(json)
                .execute(new CallBack<BaseBean<UserCommunityBean>>() {
                    @Override
                    public void onSuccess(BaseBean<UserCommunityBean> data) {
                        getView().getUserEnterance(data.getData());
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

    public void getCommunityList(int uid,String token) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        String json =jsonObject.toString();
        LogUtils.i("json=()"+json);
        DataModel.request(Token.API_COMMUNITY_LIST)
                .params(json)
                .execute(new CallBack<BaseBean<List<CommunityBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<CommunityBean>> data) {
                        getView().getCommunityList(data.getData());
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
    public void getUnitList(int uid,String token,String community_id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("community_id",community_id);
        String json =jsonObject.toString();
        LogUtils.i("json=()"+json);
        DataModel.request(Token.API_UNIT_LIST)
                .params(json)
                .execute(new CallBack<BaseBean<List<ChildCommunityBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<ChildCommunityBean>> data) {
                        getView().getUnitList(data.getData());
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
    public void getPropertyList(int uid,String token,String unit_id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("unit_id",unit_id);
        String json =jsonObject.toString();
        LogUtils.i("json=()"+json);
        DataModel.request(Token.API_PROPERTY_LIST)
                .params(json)
                .execute(new CallBack<BaseBean<List<ChildCommunityBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<ChildCommunityBean>> data) {
                        getView().getUnitList(data.getData());
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
