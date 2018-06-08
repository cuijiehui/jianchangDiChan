package com.cui.android.jianchengdichan.view.ui.beans;

import java.util.List;

public class ProvinceJsonBean {

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
         * provinceID : 110000
         * province : 北京市
         */

        private int id;
        private String provinceID;
        private String province;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProvinceID() {
            return provinceID;
        }

        public void setProvinceID(String provinceID) {
            this.provinceID = provinceID;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }
    }
}
