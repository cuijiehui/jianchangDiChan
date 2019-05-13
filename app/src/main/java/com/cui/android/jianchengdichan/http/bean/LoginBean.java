package com.cui.android.jianchengdichan.http.bean;

/**
 * @author CUI
 * @data 2018/5/17.
 * @description 登录bean
 */
public class LoginBean {

    /**
     * token : BspobaP3o8856UHmpC9yfjshyqoPu1I1
     * uid : 8
     * com_id : 10
     * community_id :
     * unit_id :
     * property_id :
     * name : 丽丽
     * nickname : LOL
     * pic : http://jc.szshide.cn:8888/public/uploads/20180604/5bea535304d13196da40900cbebf565f.JPEG
     */

    private String token;
    private int uid;
    private String com_id;
    private String community_id;
    private String unit_id;
    private String property_id;
    private String name;
    private String nickname;
    private String pic;
    private String carNo;

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCom_id() {
        return com_id;
    }

    public void setCom_id(String com_id) {
        this.com_id = com_id;
    }

    public String getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(String community_id) {
        this.community_id = community_id;
    }

    public String getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
    }

    public String getProperty_id() {
        return property_id;
    }

    public void setProperty_id(String property_id) {
        this.property_id = property_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
