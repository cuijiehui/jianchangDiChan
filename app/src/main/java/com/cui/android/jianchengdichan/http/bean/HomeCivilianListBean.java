package com.cui.android.jianchengdichan.http.bean;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/12.
 * @details 便民服务首页
 */
public class HomeCivilianListBean {


    private List<AdBean> ad;
    private List<InfoBean> info;

    public List<AdBean> getAd() {
        return ad;
    }

    public void setAd(List<AdBean> ad) {
        this.ad = ad;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class AdBean {
        /**
         * id : 3
         * pic : http://localhost/telecom/public/uploads/20180612/3eca75ae85b4a9345f70b0404487ce40.jpg
         * info_id : 4
         */

        private int id;
        private String pic;
        private int info_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getInfo_id() {
            return info_id;
        }

        public void setInfo_id(int info_id) {
            this.info_id = info_id;
        }
    }

    public static class InfoBean {
        /**
         * id : 3
         * title : 广州和谐医院
         * pic : http://localhost/telecom/public/uploads/20180612/9600a53e478811dcdc6ec5a5d5af757b.jpg
         * address : 广州市天河区珠江新城
         * phone : 15071349024
         */

        private int id;
        private String title;
        private String pic;
        private String address;
        private String phone;

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
    }
}
