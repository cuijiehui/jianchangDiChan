package com.cui.android.jianchengdichan.utils;

import android.util.Log;

/**
 * 日志工具类
 */
public class LogUtils {

    private static String className;//类名字
    private static String methodName;//方法名字
    private static int lineNumber;//代码行数
    public static boolean weatherPrint = true;

    /**
     * 获取方法名行数等参数
     *
     * @param sElements
     */
    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }

    /**
     * 组成log
     *
     * @param log
     * @return
     */
    private static String createLog(String log) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(methodName);
        buffer.append("(").append(className).append(":").append(lineNumber).append(")");
        buffer.append(log);
        return buffer.toString();
    }

    public static void i(String msg) {
        if (!weatherPrint)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.i(className, createLog(msg));
    }

    public static void i(String tag, String msg) {
        if (!weatherPrint)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.i(tag, createLog(msg));
    }

    public static void d(String msg) {
        if (!weatherPrint)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.d(className, createLog(msg));
    }

    public static void d(String tag, String msg) {
        if (!weatherPrint)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.d(tag, createLog(msg));
    }

    public static void e(String msg) {
        if (!weatherPrint)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.e(className, createLog(msg));
    }

    public static void e(String tag, String msg) {
        if (!weatherPrint)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.e(tag, createLog(msg));
    }
    public static void w(String msg) {
        if (!weatherPrint)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.w(className, createLog(msg));
    }
    public static void w(String tag,String msg) {
        if (!weatherPrint)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.w(tag, createLog(msg));
    }
    public static void w(String tag,String msg,Throwable t) {
        if (!weatherPrint)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.w(tag, createLog(msg),t);
    }
}
