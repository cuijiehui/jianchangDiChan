package com.cui.android.jianchengdichan.utils;

import android.text.TextUtils;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.view.ui.beans.AreaJsonBean;
import com.cui.android.jianchengdichan.view.ui.beans.CityJsonBean;
import com.cui.android.jianchengdichan.view.ui.beans.ProvinceJsonBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class CityData {
    /**
     * 从指定的配置文件里面根据指定的key读取value
     *
     * @param propertiesPath 配置文件地址
     * @param key            key值
     * @param defaultValue   如果该key没有找到，则返回的默认值
     * @return value值
     */
    public static String readFromProperties(String propertiesPath, String key, String defaultValue) {
        LogUtils.i("properties=" + propertiesPath);
        LogUtils.i("properties=" + key);
        LogUtils.i("properties=" + defaultValue);

        try {
            Properties properties = new Properties();
            InputStream in = MyApplication.class.getResourceAsStream(propertiesPath);
            properties.load(in);
            in.close();
            if (properties.containsKey(key)) {
                return properties.getProperty(key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defaultValue;

    }

    public static String getCity(String id) {
        String json = readFromProperties("/assets/tp_city.properties", "RECORDS", "");
        if (!TextUtils.isEmpty(json)) {
            Gson gson = new Gson();
            CityJsonBean beans = gson.fromJson(json, CityJsonBean.class);
            for (CityJsonBean.DataBean bean : beans.getData()) {
                if (id.equals(bean.getCityID())) {
                    return bean.getCity();
                }
            }
        }
        return "";
    }

    public static String getProvince(String id) {
        LogUtils.i("getProvince=" + id);

        String json = readFromProperties("/assets/tp_province.properties", "RECORDS", "");
        if (!TextUtils.isEmpty(json)) {
            Gson gson = new Gson();
            ProvinceJsonBean beans = gson.fromJson(json, ProvinceJsonBean.class);
            for (ProvinceJsonBean.DataBean bean : beans.getData()) {
                if (id.equals(bean.getProvinceID())) {
                    return bean.getProvince();
                }
            }
        }
        return "";
    }
    public static String getArea(String id) {
        LogUtils.i("getProvince=" + id);
        String json = readFromProperties("/assets/tp_area.properties", "RECORDS", "");
        if (!TextUtils.isEmpty(json)) {
            Gson gson = new Gson();
            AreaJsonBean beans = gson.fromJson(json, AreaJsonBean.class);
            for (AreaJsonBean.DataBean bean : beans.getData()) {
                if (id.equals(bean.getAreaID())) {
                    return bean.getArea();
                }
            }
        }
        return "";
    }
}