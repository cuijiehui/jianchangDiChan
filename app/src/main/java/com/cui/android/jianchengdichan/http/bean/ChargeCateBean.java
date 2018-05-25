package com.cui.android.jianchengdichan.http.bean;

import java.util.List;

public class ChargeCateBean {

    /**
     * id : 2
     * name : 水费
     * data : [{"id":10,"num":2,"price":"3.00元/吨","sum":"9.00","instruct":"2017年四月份水费"},{"id":6,"num":2,"price":"3.00元/吨","sum":"9.00","instruct":"2017年三月份水费"}]
     */

    private int id;
    private String name;
    private List<PayTypeBean> data;

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

    public List<PayTypeBean> getData() {
        return data;
    }

    public void setData(List<PayTypeBean> data) {
        this.data = data;
    }

    public static class PayTypeBean {


        /**
         * id : 3
         * num : 6吨
         * price : 1.00元每吨
         * sum : 6.00
         * instruct : 2018年4月份水费。
         * create_time : 2018-05-25
         */

        private String id;
        private String num;
        private String price;
        private String sum;
        private String instruct;
        private String create_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public String getInstruct() {
            return instruct;
        }

        public void setInstruct(String instruct) {
            this.instruct = instruct;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
