package com.cui.android.jianchengdichan.http.bean;

/**
 * @author CUI
 * @data 2018/5/16.
 * @description 你只有非常努力，才能看起来毫不费力
 */
public class BaseBean<T> {
    private static int SUCCESS_CODE=200;//成功的code
    private int code;
    private String msg;
    private T data;


    public boolean isSuccess(){
        return getCode()==SUCCESS_CODE;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "code=" + code +
                ", msg='" + msg + '\'';
    }
}
