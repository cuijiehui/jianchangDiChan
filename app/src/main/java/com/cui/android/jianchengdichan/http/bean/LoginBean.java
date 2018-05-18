package com.cui.android.jianchengdichan.http.bean;

/**
 * @author CUI
 * @data 2018/5/17.
 * @description 登录bean
 */
public class LoginBean {
    /**
     * {"code":"200","message":"\u767b\u5f55\u6210\u529f",
     * "data":{"id":"18646","openid":"wap_user_1_13229971658","mobile":"13229971658","salt":"Aa3M68G3cT8TZdCZ",
     * "nickname":"132xxxx1658","avatar_wechat":"","community":{"token":"17u9ThNbdI4OSqF64mvRFBHa7JnI2d1u",
     * "uid":"649","com_id":"6","community_id":"","unit_id":"","property_id":""}}}
     */
    /**
     * id : 18646
     * openid : wap_user_1_13229971658
     * mobile : 13229971658
     * salt : Aa3M68G3cT8TZdCZ
     * nickname : 132xxxx1658
     * avatar_wechat :
     * community : {"token":"17u9ThNbdI4OSqF64mvRFBHa7JnI2d1u","uid":"649","com_id":"6","community_id":"","unit_id":"","property_id":""}
     */

    private String id;
    private String openid;
    private String mobile;
    private String salt;
    private String nickname;
    private String avatar_wechat;
    private CommunityBean community;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar_wechat() {
        return avatar_wechat;
    }

    public void setAvatar_wechat(String avatar_wechat) {
        this.avatar_wechat = avatar_wechat;
    }

    public CommunityBean getCommunity() {
        return community;
    }

    public void setCommunity(CommunityBean community) {
        this.community = community;
    }

    public static class CommunityBean {
        /**
         * token : 17u9ThNbdI4OSqF64mvRFBHa7JnI2d1u
         * uid : 649
         * com_id : 6
         * community_id :
         * unit_id :
         * property_id :
         */

        private String token;
        private String uid;
        private String com_id;
        private String community_id;
        private String unit_id;
        private String property_id;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getCom_id() {
            return com_id;
        }

        public void setCom_id(String com_id) {
            this.com_id = com_id;
        }

        public String getCommunity_id() {
            return community_id;
        }

        public void setCommunity_id(String community_id) {
            this.community_id = community_id;
        }

        public String getUnit_id() {
            return unit_id;
        }

        public void setUnit_id(String unit_id) {
            this.unit_id = unit_id;
        }

        public String getProperty_id() {
            return property_id;
        }

        public void setProperty_id(String property_id) {
            this.property_id = property_id;
        }

        @Override
        public String toString() {
            return "CommunityBean{" +
                    "token='" + token + '\'' +
                    ", uid='" + uid + '\'' +
                    ", com_id='" + com_id + '\'' +
                    ", community_id='" + community_id + '\'' +
                    ", unit_id='" + unit_id + '\'' +
                    ", property_id='" + property_id + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "id='" + id + '\'' +
                ", openid='" + openid + '\'' +
                ", mobile='" + mobile + '\'' +
                ", salt='" + salt + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar_wechat='" + avatar_wechat + '\'' +
                ", community=" + community.toString() +
                '}';
    }
}
