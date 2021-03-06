package com.cui.android.jianchengdichan.view.ui.beans;

import java.util.List;

public class CityJsonBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 5
         * cityID : 130100
         * city : 石家庄市
         * father : 130000
         */

        private int id;
        private String cityID;
        private String city;
        private String father;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCityID() {
            return cityID;
        }

        public void setCityID(String cityID) {
            this.cityID = cityID;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getFather() {
            return father;
        }

        public void setFather(String father) {
            this.father = father;
        }
    }
}
