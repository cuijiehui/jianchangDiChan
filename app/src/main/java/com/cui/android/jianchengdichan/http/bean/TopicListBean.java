package com.cui.android.jianchengdichan.http.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author CUI
 * @data 2018/6/19.
 * @details 话题数据
 */
public class TopicListBean implements Serializable {


    /**
     * id : 4
     * title : 【新活动】换购
     * pic : /uploads/20180612/5ec9fa049f57d37bd963d772c0492171.jpg
     * nickname : 柠檬
     * headimg : http://jc.szshide.cn:8888/public/uploads/20180525/d5ca72808ddb5480964953fdaed1c1be.jpg
     * content : 电路出问题了。
     * create_time : 2018-06-13 18:17:21
     * praise_num : 0
     * comment_num : 7
     * uid : 1
     * pics : ["http://jc.szshide.cn:8888/public/uploads/20180612/5ec9fa049f57d37bd963d772c0492171.jpg"]
     * is_praise : 0
     */

    private String id;
    private String title;
    private String pic;
    private String nickname;
    private String headimg;
    private String content;
    private String create_time;
    private String praise_num;
    private String comment_num;
    private String uid;
    private int is_praise;
    private List<String> pics;

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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public String getPraise_num() {
        return praise_num;
    }

    public void setPraise_num(String praise_num) {
        this.praise_num = praise_num;
    }

    public String getComment_num() {
        return comment_num;
    }

    public void setComment_num(String comment_num) {
        this.comment_num = comment_num;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getIs_praise() {
        return is_praise;
    }

    public void setIs_praise(int is_praise) {
        this.is_praise = is_praise;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }
}
