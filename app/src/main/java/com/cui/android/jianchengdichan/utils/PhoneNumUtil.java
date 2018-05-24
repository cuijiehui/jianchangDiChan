package com.cui.android.jianchengdichan.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumUtil {
    /**
     * 验证手机座机等号码
     */
    public static boolean isPhoneNO(String mobiles){

        Pattern p = Pattern.compile("((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)");

        Matcher m = p.matcher(mobiles);

        return m.matches();

    }
    /**
     * 只验证手机号码
     */
    public static boolean isMobileNO(String mobiles){

        Pattern p = Pattern.compile("(\\d{11})$");

        Matcher m = p.matcher(mobiles);

        return m.matches();

    }
    /**
     * 验证手机号码格式是否正确 T为正常 F为异常
     */
    public static boolean verifyPhone(String phoneNum) {
        if (TextUtils.isEmpty(phoneNum) || (!PhoneNumUtil.isMobileNO(phoneNum))) {
            return false;
        }
        return true;
    }
}
