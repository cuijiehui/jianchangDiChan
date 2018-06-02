package com.cui.android.jianchengdichan.http.bean;

public class RepairsBean {


    /**
     * id : 3
     * name : 测试
     * phone : 13229971658
     * describe : 测试一下
     * pics :
     * create_time : 2018-06-02 10:27:40
     * uid : 3
     * status : 1
     * type : 1
     * suggestion : null
     * date : null
     * reject : null
     * com_id : 10
     */

    private String id;
    private String name;
    private String phone;
    private String describe;
    private String pics;
    private String create_time;
    private String uid;
    private String status;
    private String type;
    private Object suggestion;
    private Object date;
    private Object reject;
    private String com_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(Object suggestion) {
        this.suggestion = suggestion;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public Object getReject() {
        return reject;
    }

    public void setReject(Object reject) {
        this.reject = reject;
    }

    public String getCom_id() {
        return com_id;
    }

    public void setCom_id(String com_id) {
        this.com_id = com_id;
    }
}
