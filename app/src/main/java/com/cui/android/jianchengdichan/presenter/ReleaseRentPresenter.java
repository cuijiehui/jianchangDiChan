package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.bean.BaseBean;
import com.cui.android.jianchengdichan.http.bean.UplodeImgBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.ReleaseRentActivity;

import java.util.List;

public class ReleaseRentPresenter extends BasePresenter<ReleaseRentActivity> {
    public void uplodeImg(String uid, String token, String num, List<String> imgPath){
        LogUtils.i("uplodeImg"+"uid="+uid);
        LogUtils.i("uplodeImg"+"token="+token);
        LogUtils.i("uplodeImg"+"num="+num);
        int nums=new Integer(num);
        String[] data =new String[3+nums];
        data[0]=uid;
        data[1]=token;
        data[2]=num;
        for(int i=0;i<imgPath.size();i++){
            data[3+i]=imgPath.get(i);
        }
        DataModel.request(Token.API_UPLODE_IMG)
                .params(data)
                .execute(new CallBack<BaseBean<List<UplodeImgBean>>>() {
            @Override
            public void onSuccess(BaseBean<List<UplodeImgBean>> data) {
                getView().uplodaImg(data.getData());
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
