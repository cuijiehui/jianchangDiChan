package com.cui.android.jianchengdichan.utils;

import com.cui.android.jianchengdichan.MyApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CityData {
    /**
     * 从指定的配置文件里面根据指定的key读取value
     * @param propertiesPath 配置文件地址
     * @param key            key值
     * @param defaultValue   如果该key没有找到，则返回的默认值
     * @return value值
     */
    public static String readFromProperties(String propertiesPath, String key, String defaultValue) {
        LogUtils.i("properties="+propertiesPath);
        LogUtils.i("properties="+key);
        LogUtils.i("properties="+defaultValue);

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
}