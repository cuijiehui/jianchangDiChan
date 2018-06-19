package com.cui.android.jianchengdichan.http.bean;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/15.
 * @details
 */
public class CommentTopicBean {


    /**
     * id : 4
     * title : 【新活动】换购
     * pic : /uploads/20180612/5ec9fa049f57d37bd963d772c0492171.jpg
     * nickname : 柠檬
     * headimg : http://jc.szshide.cn:8888/public/uploads/20180525/d5ca72808ddb5480964953fdaed1c1be.jpg
     * content : 电路出问题了。
     * create_time : 2018-06-13 18:17:21
     * pics : ["http://jc.szshide.cn:8888/public/uploads/20180612/5ec9fa049f57d37bd963d772c0492171.jpg"]
     */

    private String id;
    private String title;
    private String pic;
    private String nickname;
    private String headimg;
    private String content;
    private String create_time;
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

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }
}
