package com.cui.android.jianchengdichan.http.bean;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/19.
 * @details
 */
public class TopicListBean  {


    /**
     * id : 15
     * title : 【促销】沃尔玛
     * pic : /uploads/20180612/5ec9fa049f57d37bd963d772c0492171.jpg,/uploads/20180612/d1cd90d3ebdaf6d83d2d77783ceaf61f.jpg
     * content : 5月2日早上一睁眼，微信里躺着老板转发的几条信息：“看看别人家的五一活动，咱们什么都没做啊！”老板说的对，可是，哥还没睡醒啊……想了想房贷车贷消费贷，想了想即将汹涌上市的95后应届生，我战战兢兢地回了一句话：“端午见。”。
     * praise_num : 0
     * comment_num : 0
     * create_time : 2018-06-19 09:31:52
     * nickname : 柠檬
     * headimg : http://jc.szshide.cn:8888/public/uploads/20180525/d5ca72808ddb5480964953fdaed1c1be.jpg
     * pics : ["http://jc.szshide.cn:8888/public/uploads/20180612/5ec9fa049f57d37bd963d772c0492171.jpg","http://jc.szshide.cn:8888/public/uploads/20180612/d1cd90d3ebdaf6d83d2d77783ceaf61f.jpg"]
     * is_praise : 0
     */

    private String id;
    private String title;
    private String pic;
    private String content;
    private String praise_num;
    private String comment_num;
    private String create_time;
    private String nickname;
    private String headimg;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
