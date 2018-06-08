package com.cui.android.jianchengdichan.http.bean;

public class RenovationBean {

    /**
     * id : 1
     * company : 测试公司
     * startdate : 2018-06-06
     * enddate : 2018-07-06
     * status : 1
     * create_time : 2018-06-06
     */

    private int id;
    private String company;
    private String startdate;
    private String enddate;
    private int status;
    private String create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
