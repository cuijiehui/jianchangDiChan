package com.cui.android.jianchengdichan.http.bean;

import java.util.List;

public class HomeDataBean {

    /**
     * ad : [{"id":2,"pic":"http://shidecommunity.oss-cn-shenzhen.aliyuncs.com/0/telecom/0/ad1@2x.png","url":""},{"id":3,"pic":"http://shidecommunity.oss-cn-shenzhen.aliyuncs.com/0/telecom/0/ad2@2x.png","url":""}]
     * notice : [{"id":1,"title":"测试公告"},{"id":2,"title":"施工公告"}]
     * rent : [{"id":1,"pic":"https://shideshop.oss-cn-shenzhen.aliyuncs.com/community/0/SCBJ_031012.jpg","title":"保利上城","area":"天河区"}]
     * limit_time : {"id":349,"shorttitle":"【迪士尼系列】快乐萃金月饼礼盒","thumb":"http://wx.szshide.shop/attachment/images/1/2017/09/y2354o3SMRsV2r4RMRtM42Y122613S.jpg","timeend":1506959940}
     * newgood : [{"id":225,"shorttitle":"卡农UK系列尤克里里24寸夏威夷小吉他","thumb":"images/1/2017/05/BZaPTOMvaollebJVvWz4f2PV4MPvm4.jpg","marketprice":"316.00"},{"id":226,"shorttitle":"卡农AKS系列复古民谣吉他","thumb":"images/1/2017/05/Aw6fFS7cztkzE1qtN1Sw6F9R571qar.jpg","marketprice":"593.00"},{"id":231,"shorttitle":"阿迪普 智能免插式金属数据线 磁力充电线","thumb":"images/1/2017/05/Fxw05k7z0k7D7k2i06x23w4pd0B63i.jpg","marketprice":"45.00"},{"id":232,"shorttitle":"阿迪普 超轻航空铝数据线 时尚数据线","thumb":"images/1/2017/05/WcS28E2cVW7DC2Gd1ezGDG3J2DE89c.jpg","marketprice":"15.00"},{"id":233,"shorttitle":"红色镭电移动背夹电源iPhone6 /6plus","thumb":"images/1/2017/05/TDVivzBXuEKFP0K5i0rpedvPp17CxV.jpg","marketprice":"158.00"},{"id":234,"shorttitle":"金蟾/貔貅两款 高端车标","thumb":"images/1/2017/05/ccK8p013A718OKio10a07k331a7kLk.jpg","marketprice":"40.00"},{"id":239,"shorttitle":"阿迪普 智能儿童定位手表","thumb":"images/1/2017/05/ezcyfe558fDZ5n2nFto2c33tfYey3T.jpg","marketprice":"128.00"},{"id":241,"shorttitle":"阿迪普 超强磁吸车载支架","thumb":"images/1/2017/05/w6UMjJJdsJB64DHuSG9w9Fg994sTzt.jpg","marketprice":"40.00"},{"id":247,"shorttitle":"卡农AKS系列复古民谣吉他","thumb":"images/1/2017/05/EFw793ft6BC8C0w7w93I3d00f3906l.jpg","marketprice":"554.00"},{"id":248,"shorttitle":"卡农AKS系列复古民谣吉他","thumb":"images/1/2017/05/K5BVKAgrEL1lyTedzTVR1f4Alfld4g.jpg","marketprice":"517.00"}]
     * favor : [{"id":225,"shorttitle":"卡农UK系列尤克里里24寸夏威夷小吉他","title":"【卡农】UK系列-尤克里里24寸夏威夷风情小吉他 多个款式选择 更容易带走的萌系乐器 随时随地与朋友分享音乐的快乐","thumb":"images/1/2017/05/BZaPTOMvaollebJVvWz4f2PV4MPvm4.jpg","marketprice":"316.00"},{"id":226,"shorttitle":"卡农AKS系列复古民谣吉他","title":"【卡农】KANON AKS系列-复古民谣吉他 41寸 致敬吉他理想时代 卡农让我们与众不同 圆缺角两款可选 赠送配件大礼包","thumb":"images/1/2017/05/Aw6fFS7cztkzE1qtN1Sw6F9R571qar.jpg","marketprice":"593.00"},{"id":231,"shorttitle":"阿迪普 智能免插式金属数据线 磁力充电线","title":"【adpo】阿迪普 磁力充电线 智能免插式金属数据线 磁铁吸附线 IPhone5/5s/6/6Plus/6s/iPad mini/Air专用 兼容苹果/安卓接口","thumb":"images/1/2017/05/Fxw05k7z0k7D7k2i06x23w4pd0B63i.jpg","marketprice":"45.00"},{"id":232,"shorttitle":"阿迪普 超轻航空铝数据线 时尚数据线","title":"【adpo】阿迪普 时尚数据线 超轻航空铝数据线 拉面专利设计不易折断 100cm超长实用 兼容苹果/安卓接口","thumb":"images/1/2017/05/WcS28E2cVW7DC2Gd1ezGDG3J2DE89c.jpg","marketprice":"15.00"},{"id":233,"shorttitle":"红色镭电移动背夹电源iPhone6 /6plus","title":"【adpo】阿迪普 红色镭电移动背夹电源iPhone6 /6plus 加倍续航 轻便出行 银色、土豪金、玫瑰金三色可选","thumb":"images/1/2017/05/TDVivzBXuEKFP0K5i0rpedvPp17CxV.jpg","marketprice":"158.00"},{"id":234,"shorttitle":"金蟾/貔貅两款 高端车标","title":"【adpo】阿迪普  多功能嗞吸车载手机支架 金蟾/貔貅两款 高端车标 金蟾招财致富  貔貅招财进宝","thumb":"images/1/2017/05/ccK8p013A718OKio10a07k331a7kLk.jpg","marketprice":"40.00"},{"id":239,"shorttitle":"阿迪普 智能儿童定位手表","title":"【adpo】 阿迪普 智能儿童手表定位手表 智能提醒 睡眠监测 监控孩子安全","thumb":"images/1/2017/05/ezcyfe558fDZ5n2nFto2c33tfYey3T.jpg","marketprice":"128.00"},{"id":241,"shorttitle":"阿迪普 超强磁吸车载支架","title":"【adpo】阿迪普 超强磁吸车载支架 强力多功能磁芯支架 居家工作生活更方便实用 导航通话更安全便捷","thumb":"images/1/2017/05/w6UMjJJdsJB64DHuSG9w9Fg994sTzt.jpg","marketprice":"40.00"},{"id":247,"shorttitle":"卡农AKS系列复古民谣吉他","title":"【卡农】KANON AKS系列-复古民谣吉他 40寸 致敬吉他理想时代 卡农让我们与众不同 圆缺角两款可选 赠送配件大礼包","thumb":"images/1/2017/05/EFw793ft6BC8C0w7w93I3d00f3906l.jpg","marketprice":"554.00"},{"id":248,"shorttitle":"卡农AKS系列复古民谣吉他","title":"【卡农】KANON AKS系列-复古民谣吉他 38寸 致敬吉他理想时代 卡农让我们与众不同 圆缺角两款可选 赠送配件大礼包","thumb":"images/1/2017/05/K5BVKAgrEL1lyTedzTVR1f4Alfld4g.jpg","marketprice":"517.00"}]
     */

    private LimitTimeBean limit_time;
    private List<AdBean> ad;
    private List<NoticeBean> notice;
    private List<RentBean> rent;
    private List<NewgoodBean> newgood;
    private List<FavorBean> favor;

    public LimitTimeBean getLimit_time() {
        return limit_time;
    }

    public void setLimit_time(LimitTimeBean limit_time) {
        this.limit_time = limit_time;
    }

    public List<AdBean> getAd() {
        return ad;
    }

    public void setAd(List<AdBean> ad) {
        this.ad = ad;
    }

    public List<NoticeBean> getNotice() {
        return notice;
    }

    public void setNotice(List<NoticeBean> notice) {
        this.notice = notice;
    }

    public List<RentBean> getRent() {
        return rent;
    }

    public void setRent(List<RentBean> rent) {
        this.rent = rent;
    }

    public List<NewgoodBean> getNewgood() {
        return newgood;
    }

    public void setNewgood(List<NewgoodBean> newgood) {
        this.newgood = newgood;
    }

    public List<FavorBean> getFavor() {
        return favor;
    }

    public void setFavor(List<FavorBean> favor) {
        this.favor = favor;
    }

    public static class LimitTimeBean {
        /**
         * id : 349
         * shorttitle : 【迪士尼系列】快乐萃金月饼礼盒
         * thumb : http://wx.szshide.shop/attachment/images/1/2017/09/y2354o3SMRsV2r4RMRtM42Y122613S.jpg
         * timeend : 1506959940
         */

        private int id;
        private String shorttitle;
        private String thumb;
        private long timeend;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getShorttitle() {
            return shorttitle;
        }

        public void setShorttitle(String shorttitle) {
            this.shorttitle = shorttitle;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public long getTimeend() {
            return timeend;
        }

        public void setTimeend(long timeend) {
            this.timeend = timeend;
        }
    }

    public static class AdBean {
        /**
         * id : 2
         * pic : http://shidecommunity.oss-cn-shenzhen.aliyuncs.com/0/telecom/0/ad1@2x.png
         * url :
         */

        private int id;
        private String pic;
        private String url;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }

    public static class NoticeBean {
        /**
         * id : 1
         * title : 测试公告
         */

        private int id;
        private String title;

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
    }

    public static class RentBean {
        /**
         * id : 1
         * pic : https://shideshop.oss-cn-shenzhen.aliyuncs.com/community/0/SCBJ_031012.jpg
         * title : 保利上城
         * area : 天河区
         */

        private int id;
        private String pic;
        private String title;
        private String area;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }
    }

    public static class NewgoodBean {
        /**
         * id : 225
         * shorttitle : 卡农UK系列尤克里里24寸夏威夷小吉他
         * thumb : images/1/2017/05/BZaPTOMvaollebJVvWz4f2PV4MPvm4.jpg
         * marketprice : 316.00
         */

        private int id;
        private String shorttitle;
        private String thumb;
        private String marketprice;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getShorttitle() {
            return shorttitle;
        }

        public void setShorttitle(String shorttitle) {
            this.shorttitle = shorttitle;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getMarketprice() {
            return marketprice;
        }

        public void setMarketprice(String marketprice) {
            this.marketprice = marketprice;
        }
    }

    public static class FavorBean {
        /**
         * id : 225
         * shorttitle : 卡农UK系列尤克里里24寸夏威夷小吉他
         * title : 【卡农】UK系列-尤克里里24寸夏威夷风情小吉他 多个款式选择 更容易带走的萌系乐器 随时随地与朋友分享音乐的快乐
         * thumb : images/1/2017/05/BZaPTOMvaollebJVvWz4f2PV4MPvm4.jpg
         * marketprice : 316.00
         */

        private int id;
        private String shorttitle;
        private String title;
        private String thumb;
        private String marketprice;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getShorttitle() {
            return shorttitle;
        }

        public void setShorttitle(String shorttitle) {
            this.shorttitle = shorttitle;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getMarketprice() {
            return marketprice;
        }

        public void setMarketprice(String marketprice) {
            this.marketprice = marketprice;
        }
    }
}
