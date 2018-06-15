package com.cui.android.jianchengdichan.http.bean;

/**
 * @author CUI
 * @data 2018/6/11.
 * @details
 */
public class LeaveMsgListBean {

    /**
     * id : 2
     * content : cxzcvzxvxzzvxzvxzvxz
     * create_time : 2018-05-22 14:38:35
     * uid : 1
     * reply : null
     * reply_time : null
     * rent_id : 1
     * username : null
     */

    private int id;
    private String content;
    private String create_time;
    private int uid;
    private Object reply;
    private Object reply_time;
    private int rent_id;
    private Object username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Object getReply() {
        return reply;
    }

    public void setReply(Object reply) {
        this.reply = reply;
    }

    public Object getReply_time() {
        return reply_time;
    }

    public void setReply_time(Object reply_time) {
        this.reply_time = reply_time;
    }

    public int getRent_id() {
        return rent_id;
    }

    public void setRent_id(int rent_id) {
        this.rent_id = rent_id;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }
}
