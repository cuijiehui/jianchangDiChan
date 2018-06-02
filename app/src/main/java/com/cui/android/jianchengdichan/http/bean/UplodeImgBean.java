package com.cui.android.jianchengdichan.http.bean;

public class UplodeImgBean {


    /**
     * code : 200
     * message : 上传图片成功
     * data : {"pics":"/uploads/20180602/a509c6f1426402e1078221ca2c034ac5.JPEG,/uploads/20180602/bcf6f42f28d2a84b1d36a22ad7e54940.JPEG"}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pics : /uploads/20180602/a509c6f1426402e1078221ca2c034ac5.JPEG,/uploads/20180602/bcf6f42f28d2a84b1d36a22ad7e54940.JPEG
         */

        private String pics;

        public String getPics() {
            return pics;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }
    }
}
