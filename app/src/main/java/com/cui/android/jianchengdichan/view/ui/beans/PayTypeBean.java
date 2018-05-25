package com.cui.android.jianchengdichan.view.ui.beans;

import java.io.Serializable;

public class PayTypeBean implements Serializable {
    public PayTypeBean() {
    }

    public PayTypeBean(String id, String num, String price, String sum, String instruct) {
        this.id = id;
        this.num = num;
        this.price = price;
        this.sum = sum;
        this.instruct = instruct;
    }

    /**
     * id : 10
     * num : 2
     * price : 3.00元/吨
     * sum : 9.00
     * instruct : 2017年四月份水费
     */

    private String id;
    private String num;
    private String price;
    private String sum;
    private String instruct;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getInstruct() {
        return instruct;
    }

    public void setInstruct(String instruct) {
        this.instruct = instruct;
    }
}
