package com.cui.android.jianchengdichan.view.ui.beans;

import java.io.Serializable;

public class PayingBean implements Serializable {
    private String id ;
    private String path;//图片地址
    private String name;//缴费名
    private String time;//时间
    private String num;//数量
    private String price;//总价格

    public PayingBean(String id, String path, String name, String time, String num, String price) {
        this.id = id;
        this.path = path;
        this.name = name;
        this.time = time;
        this.num = num;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PayingBean{" +
                "id='" + id + '\'' +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", num='" + num + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
