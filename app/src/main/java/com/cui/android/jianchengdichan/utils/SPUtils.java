package com.cui.android.jianchengdichan.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.cui.android.jianchengdichan.MyApplication;

public enum  SPUtils {
    INSTANCE;

    public static final int DATA_STRING = 1;                                                        // string
    public static final int DATA_INT = 2;                                                           // int
    public static final int DATA_LONG = 3;                                                          // long
    public static final int DATA_BOOLEAN = 4;                                                       // boolean
    public static final int DATA_FLOAT = 5;                                                         // float

    /**
     * 写配置文件
     *
     * @param configKey   键
     * @param configValue 值
     * @return
     */
    public boolean setSPValue(String configKey, Object configValue) {
        if (MyApplication.getAppContext() == null || configValue == null) {
            return false;
        }
        SharedPreferences preferences = MyApplication.getAppContext()
                .getSharedPreferences(SPKey.SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        if (configValue.getClass() == String.class) {                                               // String类型
            String str = (String) configValue;
            editor.putString(configKey, str);
        } else if (configValue.getClass() == Integer.class) {                                       // Integer类型
            Integer i = (Integer) configValue;
            editor.putInt(configKey, i);
        } else if (configValue.getClass() == Boolean.class) {                                       // Boolean类型
            Boolean b = (Boolean) configValue;
            editor.putBoolean(configKey, b);
        } else if (configValue.getClass() == Long.class) {                                          // Long类型
            Long l = (Long) configValue;
            editor.putLong(configKey, l);
        } else if (configValue.getClass() == Float.class) {                                         // Float类型
            Float f = (Float) configValue;
            editor.putFloat(configKey, f);
        } else {
            return false;
        }
        editor.commit();
        return true;
    }

    /**
     * 通过键删除相应的配置
     *
     * @param configKey 键
     * @return true : 成功 ; false : 失败
     */
    public boolean delSPValue(String configKey) {
        SharedPreferences preferences = MyApplication.getAppContext().getSharedPreferences(SPKey.SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.remove(configKey);
        return editor.commit();
    }

    /**
     * 删除配置文件
     *
     * @return true : 成功 ; false : 失败
     */
    public boolean delSPFile() {
        SharedPreferences preferences = MyApplication.getAppContext().getSharedPreferences(SPKey.SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.clear();
        return editor.commit();
    }

    /**
     * 获取配置文件中的配置
     *
     * @param configKey 键
     * @param dataType  值的类型
     * @return 值(返回null, 意味着配置表没有该key)
     */
    public Object getSPValue(String configKey, int dataType) {
        if (MyApplication.getAppContext() != null) {
            SharedPreferences sp = MyApplication.getAppContext().getSharedPreferences(SPKey.SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
            if (sp != null && sp.contains(configKey)) {
                switch (dataType) {
                    case DATA_STRING:
                        return sp.getString(configKey, "");
                    case DATA_INT:
                        return sp.getInt(configKey, -1);
                    case DATA_LONG:
                        return sp.getLong(configKey, 0L);
                    case DATA_BOOLEAN:
                        return sp.getBoolean(configKey, false);
                    case DATA_FLOAT:
                        return sp.getFloat(configKey, 0.0f);
                    default:
                        return null;
                }
            }else{
                switch (dataType) {
                    case DATA_STRING:
                        return  "";
                    case DATA_INT:
                        return -1;
                    case DATA_LONG:
                        return  0L;
                    case DATA_BOOLEAN:
                        return false;
                    case DATA_FLOAT:
                        return  0.0f;
                    default:
                        return null;
                }
            }
        }
        return null;
    }
    // 如果没有找到key对应的value，则设置默认值
    private void setDefaultValue(String configName, int dataType, Object defaultValue) {
        if (getSPValue(configName, dataType) == null) {
            setSPValue(configName, defaultValue);
        }
    }
}
