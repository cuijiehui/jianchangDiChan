package com.cui.android.jianchengdichan.http.base;

public class BasesBean {
    private static int SUCCESS_CODE=200;//成功的code
    private int code;
    private String msg;

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
}
