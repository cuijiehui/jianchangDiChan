package com.cui.android.jianchengdichan.http.bean;

import java.util.List;

public class RentDetailBean  {

    /**
     * pics : ["http://shop.coresun.net/attachment/images/1/2017/07/GNeUt23BuTZp02ocB2p912b3uTJ91u.jpg"]
     * title : 宏正地产推荐上城国际精装中高层楼盘
     * create_time : 2017-07-13
     * rental : 1200
     * house_type_info : 1室1厅1卫
     * acreage : 50
     * rent_type : 整租
     * orientations : 南北向
     * local_floor : 12
     * total_floor : 30
     * decoration : 精装
     * ban_type : 公寓
     * charge_pay : 三
     * address : 天河北路寰城海航广场27楼
     * detail : 应节活动
     */

    private String title;
    private String create_time;
    private String rental;
    private String house_type_info;
    private String acreage;
    private String rent_type;
    private String orientations;
    private int local_floor;
    private int total_floor;
    private String decoration;
    private String ban_type;
    private String charge_pay;
    private String address;
    private String detail;
    private List<String> pics;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getRental() {
        return rental;
    }

    public void setRental(String rental) {
        this.rental = rental;
    }

    public String getHouse_type_info() {
        return house_type_info;
    }

    public void setHouse_type_info(String house_type_info) {
        this.house_type_info = house_type_info;
    }

    public String getAcreage() {
        return acreage;
    }

    public void setAcreage(String acreage) {
        this.acreage = acreage;
    }

    public String getRent_type() {
        return rent_type;
    }

    public void setRent_type(String rent_type) {
        this.rent_type = rent_type;
    }

    public String getOrientations() {
        return orientations;
    }

    public void setOrientations(String orientations) {
        this.orientations = orientations;
    }

    public int getLocal_floor() {
        return local_floor;
    }

    public void setLocal_floor(int local_floor) {
        this.local_floor = local_floor;
    }

    public int getTotal_floor() {
        return total_floor;
    }

    public void setTotal_floor(int total_floor) {
        this.total_floor = total_floor;
    }

    public String getDecoration() {
        return decoration;
    }

    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }

    public String getBan_type() {
        return ban_type;
    }

    public void setBan_type(String ban_type) {
        this.ban_type = ban_type;
    }

    public String getCharge_pay() {
        return charge_pay;
    }

    public void setCharge_pay(String charge_pay) {
        this.charge_pay = charge_pay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    @Override
    public String toString() {
        return "RentDetailBean{" +
                "title='" + title + '\'' +
                ", create_time='" + create_time + '\'' +
                ", rental='" + rental + '\'' +
                ", house_type_info='" + house_type_info + '\'' +
                ", acreage='" + acreage + '\'' +
                ", rent_type='" + rent_type + '\'' +
                ", orientations='" + orientations + '\'' +
                ", local_floor=" + local_floor +
                ", total_floor=" + total_floor +
                ", decoration='" + decoration + '\'' +
                ", ban_type='" + ban_type + '\'' +
                ", charge_pay='" + charge_pay + '\'' +
                ", address='" + address + '\'' +
                ", detail='" + detail + '\'' +
                ", pics=" + pics +
                '}';
    }
}
