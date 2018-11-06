package com.cui.android.jianchengdichan.http.bean;

import java.io.Serializable;

/**
 * @author cui
 * @date 2018/11/6
 * @annotation
 */
public class CarInfoBean implements Serializable{
    private String carNo;
    private String parkCode;
    private String parkName;

    public CarInfoBean() {
    }

    public CarInfoBean(String carNo, String parkCode, String parkName) {
        this.carNo = carNo;
        this.parkCode = parkCode;
        this.parkName = parkName;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getParkCode() {
        return parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }
}
