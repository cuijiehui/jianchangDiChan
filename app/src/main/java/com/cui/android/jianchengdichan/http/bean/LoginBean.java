package com.cui.android.jianchengdichan.http.bean;

/**
 * @author CUI
 * @data 2018/5/17.
 * @description 登录bean
 */
public class LoginBean {
    /**
     * {
     *"code": 200,
     * "message": "登录成功",
     *"data": {
     *"token": "goTXe8TD4SmKomGLiMFFlTGUESzrOtEM",
     *"effect_time": 1519352023,
     * "uid": 77,
     * "com_id": 6,
     * "community_id": "",
     *"unit_id": "",
     * "property_id": "",
     *"sip_number": "15071349024",
     * "sip_pwd": "123456"
     *}
     * }
     */

    /**
     * token : goTXe8TD4SmKomGLiMFFlTGUESzrOtEM
     * effect_time : 1519352023
     * uid : 77
     * com_id : 6
     * community_id :
     * unit_id :
     * property_id :
     * sip_number : 15071349024
     * sip_pwd : 123456
     */

    private String token;
    private long effect_time;
    private int uid;
    private int com_id;
    private String community_id;
    private String unit_id;
    private String property_id;
    private String sip_number;
    private String sip_pwd;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getEffect_time() {
        return effect_time;
    }

    public void setEffect_time(long effect_time) {
        this.effect_time = effect_time;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getCom_id() {
        return com_id;
    }

    public void setCom_id(int com_id) {
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

    public String getSip_number() {
        return sip_number;
    }

    public void setSip_number(String sip_number) {
        this.sip_number = sip_number;
    }

    public String getSip_pwd() {
        return sip_pwd;
    }

    public void setSip_pwd(String sip_pwd) {
        this.sip_pwd = sip_pwd;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "token='" + token + '\'' +
                ", effect_time=" + effect_time +
                ", uid=" + uid +
                ", com_id=" + com_id +
                ", community_id='" + community_id + '\'' +
                ", unit_id='" + unit_id + '\'' +
                ", property_id='" + property_id + '\'' +
                ", sip_number='" + sip_number + '\'' +
                ", sip_pwd='" + sip_pwd + '\'' +
                '}';
    }
}
