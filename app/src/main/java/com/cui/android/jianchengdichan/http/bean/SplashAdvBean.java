package com.cui.android.jianchengdichan.http.bean;

/**
 * @author CUI
 * @data 2018/5/17.
 * @description 你只有非常努力，才能看起来毫不费力
 */
public class SplashAdvBean  {
    private String address;//图片地址
    private String id;
    private String adlink;//广告地址
    private String is_order;//是否排序

    public SplashAdvBean() {
    }

    public SplashAdvBean(String address, String id, String adlink, String is_order) {
        this.address = address;
        this.id = id;
        this.adlink = adlink;
        this.is_order = is_order;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdlink() {
        return adlink;
    }

    public void setAdlink(String adlink) {
        this.adlink = adlink;
    }

    public String getIs_order() {
        return is_order;
    }

    public void setIs_order(String is_order) {
        this.is_order = is_order;
    }

    @Override
    public String toString() {
        return "FullPageAdsEntity{" +
                "address='" + address + '\'' +
                ", id='" + id + '\'' +
                ", adlink='" + adlink + '\'' +
                ", is_order='" + is_order + '\'' +
                '}';
    }
}
