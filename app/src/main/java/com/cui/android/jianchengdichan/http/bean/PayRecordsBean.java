package com.cui.android.jianchengdichan.http.bean;

import java.io.Serializable;

public class PayRecordsBean implements Serializable{

    /**
     * id : 3
     * instruct : 2018年4月份水费。
     * sum : 6.00
     * pay_status : 1 1没有缴费 2已经缴费
     * cate : 水费
     * price : 1.00元每吨
     * num : 6
     * unit : 吨
     * create_time : 2018-05-25
     */

    private String id;
    private String instruct;
    private String sum;
    private String pay_status;
    private String cate;
    private String price;
    private String num;
    private String unit;
    private String create_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstruct() {
        return instruct;
    }

    public void setInstruct(String instruct) {
        this.instruct = instruct;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
