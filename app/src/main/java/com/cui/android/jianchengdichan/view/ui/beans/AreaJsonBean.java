package com.cui.android.jianchengdichan.view.ui.beans;

import java.util.List;

public class AreaJsonBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * areaID : 110101
         * area : 东城区
         * father : 110100
         */

        private int id;
        private String areaID;
        private String area;
        private String father;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAreaID() {
            return areaID;
        }

        public void setAreaID(String areaID) {
            this.areaID = areaID;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getFather() {
            return father;
        }

        public void setFather(String father) {
            this.father = father;
        }
    }
}
