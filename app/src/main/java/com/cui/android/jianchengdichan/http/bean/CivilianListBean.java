package com.cui.android.jianchengdichan.http.bean;

/**
 * @author CUI
 * @data 2018/6/14.
 * @details
 */
public class CivilianListBean {

    /**
     * id : 3
     * title : 广州和谐医院
     * pic : http://localhost/telecom/public/uploads/20180612/9600a53e478811dcdc6ec5a5d5af757b.jpg
     * address : 广州市天河区珠江新城
     * phone : 15071349024
     * flow : 0
     */

    private int id;
    private String title;
    private String pic;
    private String address;
    private String phone;
    private int flow;

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

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }
}
