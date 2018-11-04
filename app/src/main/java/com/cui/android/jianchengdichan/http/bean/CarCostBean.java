package com.cui.android.jianchengdichan.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class CarCostBean implements Parcelable {

    /**
     * code : 0501
     * startTime : 2016-04-08 19:14:13
     * endTime : 2016-11-17 14:57:49
     * parkTime : 5347 时 43 分
     * chargeTime : 5347 时 43 分
     * chargingCar : 标准计费
     * totalCharge : 10694.0
     * hasCharge : 0.0
     * favMoney : 0.0
     * favTime : 0
     * currFavMoney : 0.0
     * currFavTime : 0
     * partyMoney : 0
     * payCharge : 10694.0
     * lastPayTime :
     * nextChargeTime : 2016-11-17 15:01:00
     * nextChargeMoney : 10695.0
     */

    private String code;
    private String startTime;
    private String endTime;
    private String parkTime;
    private String chargeTime;
    private String chargingCar;
    private String totalCharge;
    private String hasCharge;
    private String favMoney;
    private String favTime;
    private String currFavMoney;
    private String currFavTime;
    private String partyMoney;
    private String payCharge;
    private String lastPayTime;
    private String nextChargeTime;
    private String nextChargeMoney;
    private String carNo;
    private String is_free;

    public String getIs_free() {
        return is_free;
    }

    public void setIs_free(String is_free) {
        this.is_free = is_free;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String mCarNo) {
        this.carNo = mCarNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getParkTime() {
        return parkTime;
    }

    public void setParkTime(String parkTime) {
        this.parkTime = parkTime;
    }

    public String getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(String chargeTime) {
        this.chargeTime = chargeTime;
    }

    public String getChargingCar() {
        return chargingCar;
    }

    public void setChargingCar(String chargingCar) {
        this.chargingCar = chargingCar;
    }

    public String getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(String totalCharge) {
        this.totalCharge = totalCharge;
    }

    public String getHasCharge() {
        return hasCharge;
    }

    public void setHasCharge(String hasCharge) {
        this.hasCharge = hasCharge;
    }

    public String getFavMoney() {
        return favMoney;
    }

    public void setFavMoney(String favMoney) {
        this.favMoney = favMoney;
    }

    public String getFavTime() {
        return favTime;
    }

    public void setFavTime(String favTime) {
        this.favTime = favTime;
    }

    public String getCurrFavMoney() {
        return currFavMoney;
    }

    public void setCurrFavMoney(String currFavMoney) {
        this.currFavMoney = currFavMoney;
    }

    public String getCurrFavTime() {
        return currFavTime;
    }

    public void setCurrFavTime(String currFavTime) {
        this.currFavTime = currFavTime;
    }

    public String getPartyMoney() {
        return partyMoney;
    }

    public void setPartyMoney(String partyMoney) {
        this.partyMoney = partyMoney;
    }

    public String getPayCharge() {
        return payCharge;
    }

    public void setPayCharge(String payCharge) {
        this.payCharge = payCharge;
    }

    public String getLastPayTime() {
        return lastPayTime;
    }

    public void setLastPayTime(String lastPayTime) {
        this.lastPayTime = lastPayTime;
    }

    public String getNextChargeTime() {
        return nextChargeTime;
    }

    public void setNextChargeTime(String nextChargeTime) {
        this.nextChargeTime = nextChargeTime;
    }

    public String getNextChargeMoney() {
        return nextChargeMoney;
    }

    public void setNextChargeMoney(String nextChargeMoney) {
        this.nextChargeMoney = nextChargeMoney;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.startTime);
        dest.writeString(this.endTime);
        dest.writeString(this.parkTime);
        dest.writeString(this.chargeTime);
        dest.writeString(this.chargingCar);
        dest.writeString(this.totalCharge);
        dest.writeString(this.hasCharge);
        dest.writeString(this.favMoney);
        dest.writeString(this.favTime);
        dest.writeString(this.currFavMoney);
        dest.writeString(this.currFavTime);
        dest.writeString(this.partyMoney);
        dest.writeString(this.payCharge);
        dest.writeString(this.lastPayTime);
        dest.writeString(this.nextChargeTime);
        dest.writeString(this.nextChargeMoney);
        dest.writeString(this.carNo);
    }

    public CarCostBean() {
    }

    protected CarCostBean(Parcel in) {
        this.code = in.readString();
        this.startTime = in.readString();
        this.endTime = in.readString();
        this.parkTime = in.readString();
        this.chargeTime = in.readString();
        this.chargingCar = in.readString();
        this.totalCharge = in.readString();
        this.hasCharge = in.readString();
        this.favMoney = in.readString();
        this.favTime = in.readString();
        this.currFavMoney = in.readString();
        this.currFavTime = in.readString();
        this.partyMoney = in.readString();
        this.payCharge = in.readString();
        this.lastPayTime = in.readString();
        this.nextChargeTime = in.readString();
        this.nextChargeMoney = in.readString();
        this.carNo = in.readString();
    }

    public static final Parcelable.Creator<CarCostBean> CREATOR = new Parcelable.Creator<CarCostBean>() {
        @Override
        public CarCostBean createFromParcel(Parcel source) {
            return new CarCostBean(source);
        }

        @Override
        public CarCostBean[] newArray(int size) {
            return new CarCostBean[size];
        }
    };
}
