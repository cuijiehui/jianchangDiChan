package com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.AdapterBean;

public class CommunityBean {
    private int imgResource;
    private String name;

    public CommunityBean(int imgResource, String name) {
        this.imgResource = imgResource;
        this.name = name;
    }

    @Override
    public String toString() {
        return "CommunityBean{" +
                "imgResource=" + imgResource +
                ", name='" + name + '\'' +
                '}';
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
