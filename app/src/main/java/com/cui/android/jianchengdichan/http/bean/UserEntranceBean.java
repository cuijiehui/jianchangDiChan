package com.cui.android.jianchengdichan.http.bean;

public class UserEntranceBean {

    /**
     * id : 1
     * community_id : 17
     * status : 2
     * property : 崔的开发室
     * unit : 寰城海航广场开发部
     * community : 晟中社区体验区
     */

    private int id;
    private int community_id;
    private int status;
    private String property;
    private String unit;
    private String community;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(int community_id) {
        this.community_id = community_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }
}
