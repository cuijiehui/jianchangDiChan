package com.cui.android.jianchengdichan.view.ui.beans;

public class ReleaseImgBean {
    String url;
    String path;
    int type;//1为本地图片 2为网络图片 -1为默认图

    public ReleaseImgBean(String url, String path, int type) {
        this.url = url;
        this.path = path;
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
