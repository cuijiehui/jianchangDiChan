package com.cui.android.jianchengdichan.http.bean;

/**
 * @author CUI
 * @data 2018/6/21.
 * @details
 */
public class CommentListBean {

    /**
     * id : 13
     * content : 发送
     * uid : 8
     * topic_id : 23
     * create_time : 2018-06-21 15:12:26
     * nickname : 来了童年
     * headimg : http://jc.szshide.cn:8888/public/uploads/20180604/5bea535304d13196da40900cbebf565f.JPEG
     * is_delete : 1
     */

    private String id;
    private String content;
    private String uid;
    private String topic_id;
    private String create_time;
    private String nickname;
    private String headimg;
    private int is_delete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }
}
