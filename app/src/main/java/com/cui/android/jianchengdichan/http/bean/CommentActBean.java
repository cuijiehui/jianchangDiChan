package com.cui.android.jianchengdichan.http.bean;

/**
 * @author CUI
 * @data 2018/6/15.
 * @details
 */
public class CommentActBean {

    /**
     * id : 1
     * title : 促销活动
     * pic : http://localhost/telecom/public/uploads/20180612/5ec9fa049f57d37bd963d772c0492171.jpg
     * create_time : 2018-06-13 11:40:26
     */

    private int id;
    private String title;
    private String pic;
    private String create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
