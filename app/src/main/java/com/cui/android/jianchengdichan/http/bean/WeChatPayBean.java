package com.cui.android.jianchengdichan.http.bean;

public class WeChatPayBean  {

    /**
     * appid : wx5f8889d080750afe
     * partnerid : 1293401801
     * prepayid : wx2017052217215502da3a7a460786980549
     * noncestr : 6glg707cj56596rb8htrqqxboxi9mrd3
     * sign : 84E2B316EE0655AF64BA3A393EFC10C5
     */

    private String appid;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
