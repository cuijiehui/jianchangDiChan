package com.cui.android.jianchengdichan.http.bean;

public class NoticeBean {

    /**
     * title : 尚御大厦6月1日停水通知
     * content : 接广州市自来水公司通知，尚御大厦6月1日09点至18点停水，请住户做好储水准备
     * create_time : 2018-05-30
     * id : 6
     * pic : http://jc.szshide.cn:8888/public
     * signature : 建城物业
     */

    private String title;
    private String content;
    private String create_time;
    private String id;
    private String pic;
    private String signature;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
