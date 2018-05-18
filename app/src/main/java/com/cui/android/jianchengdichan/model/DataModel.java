package com.cui.android.jianchengdichan.model;

import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.utils.LogUtils;

public class DataModel {
    public static BaseModel request(String token){
        LogUtils.d(token);
        BaseModel model = null;
        try{
            model = (BaseModel) Class.forName(token).newInstance();
        }catch (ClassNotFoundException e){
            LogUtils.e(e.getLocalizedMessage());
        }catch (InstantiationException e){
            LogUtils.e(e.getLocalizedMessage());

        }catch (IllegalAccessException e){
            LogUtils.e(e.getLocalizedMessage());

        }
        return model;
    }
}
