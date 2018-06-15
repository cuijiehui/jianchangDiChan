package com.cui.android.jianchengdichan.http.bean;

/**
 * @author CUI
 * @data 2018/6/12.
 * @details
 */
public class CivilianDetailBean {

    /**
     * id : 3
     * title : 广州和谐医院
     * bigimg : http://localhost/telecom/public/uploads/20180612/00b79d6d8eca1c83a5f9c35a9b631cee.jpg
     * address : 广州市天河区珠江新城
     * phone : 15071349024
     * contact : 客服刘
     * flow : 3
     * describe :        以前看妇科，很多人都想去公立三甲医院，因为觉得他们技术力量雄厚，专家资源丰富，确实公立医院整体资源是要比专科资源丰富。
     但公立医院科室众多，他们只关注妇科肿瘤、心脏类疾病。妇科只是她们的附属科室，只是当做小科室来做，选择公立医院看妇科，并不能享受到公立三甲医院所谓的"技术优势"，而专科医院凭借专科专病专治的优势，成为新时代女性看妇科最佳选择！
     */

    private int id;
    private String title;
    private String bigimg;
    private String address;
    private String phone;
    private String contact;
    private int flow;
    private String describe;

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

    public String getBigimg() {
        return bigimg;
    }

    public void setBigimg(String bigimg) {
        this.bigimg = bigimg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
