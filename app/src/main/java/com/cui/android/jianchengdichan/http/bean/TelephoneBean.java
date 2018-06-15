package com.cui.android.jianchengdichan.http.bean;

/**
 * @author CUI
 * @data 2018/6/14.
 * @details
 */
public class TelephoneBean  {

    /**
     * id : 4
     * name : 5栋1单元
     * phone : 020-23659874
     * describe : 24小时值班
     */

    private int id;
    private String name;
    private String phone;
    private String describe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
