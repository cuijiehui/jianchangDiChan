package com.cui.android.jianchengdichan.http.bean;

import java.util.List;

public class RentDetailBean  {


    /**
     * id : 2
     * pics : ["http://shidecommunity.oss-cn-shenzhen.aliyuncs.com/0/rent/0/n_v21626713b6875452a948bd072897094ca_eb00230ae4ba371a.jpg","http://shidecommunity.oss-cn-shenzhen.aliyuncs.com/0/rent/0/n_v28f2349ef7a6348b4b849234b8a72e285_eed92328348639df.jpg","http://shidecommunity.oss-cn-shenzhen.aliyuncs.com/0/rent/0/n_v2d844d2ae6bb9486bb5db9541e3655fad_7e8bea8bf94895ac.jpg","http://shidecommunity.oss-cn-shenzhen.aliyuncs.com/0/rent/0/n_v2e3278feff5894895b235e8cd260b7ce0_596ce9d94131af98.jpg"]
     * title : 保利上城
     * create_time : 2018-06-01
     * rental : 2200
     * house_type_info : 三室两厅1卫
     * acreage : 96
     * rent_type : 合租
     * orientations : 南北
     * local_floor : 6
     * total_floor : 12
     * decoration : 新装
     * ban_type : 公寓
     * address : 天河北路
     * detail : 康富花园位于番禺市桥富园大街， 小区房型通透，采光好，楼层好，2. 小区物业管理严格，服务到家让你生活放心 3. 小区人文素质高，4. 小区居住安静舒适，让您居住并享受
     * charge_pay : 押一付三
     * recommend : [{"id":"1","title":"保利上城","pic":"http://shidecommunity.oss-cn-shenzhen.aliyuncs.com/0/rent/0/ddd9744cb47bcb9ecb9fb9fb455c344a.jpg","address":"越秀尚御大厦A栋2304","house_type":"3室","rental":"2200","acreage":"96","orientations":"南北","decoration":"新装","charge_pay":"押一付一","mobile":"15071349024","contact":"吴先生"},{"id":"3","title":"保利上城","pic":"http://shidecommunity.oss-cn-shenzhen.aliyuncs.com/0/rent/0/n_v1bkuyfvixjiovqkaiwnla_4efbf870af0a0e2d.jpg","address":"天河北路","house_type":"3室","rental":"2200","acreage":"96","orientations":"南北","decoration":"新装","charge_pay":"押一付三","mobile":"15071349024","contact":"吴先生"},{"id":"12","title":"都市华庭","pic":"/uploads/20180531/7c0f4f44aa1aa09a6a155e8ca3a98a15.jpg","address":"天河北路交叉口","house_type":"","rental":"10000","acreage":"100","orientations":"南北","decoration":null,"charge_pay":"押二付一","mobile":"13566668888","contact":"测试人员"}]
     */

    private String id;
    private String title;
    private String create_time;
    private String rental;
    private String house_type_info;
    private String acreage;
    private String rent_type;
    private String orientations;
    private String local_floor;
    private String total_floor;
    private String decoration;
    private String ban_type;
    private String address;
    private String detail;
    private String charge_pay;
    private List<String> pics;
    private List<LeaseRoomBean> recommend;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getLocal_floor() {
        return local_floor;
    }

    public void setLocal_floor(String local_floor) {
        this.local_floor = local_floor;
    }

    public String getTotal_floor() {
        return total_floor;
    }

    public void setTotal_floor(String total_floor) {
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

    public String getCharge_pay() {
        return charge_pay;
    }

    public void setCharge_pay(String charge_pay) {
        this.charge_pay = charge_pay;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public List<LeaseRoomBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<LeaseRoomBean> recommend) {
        this.recommend = recommend;
    }


}
