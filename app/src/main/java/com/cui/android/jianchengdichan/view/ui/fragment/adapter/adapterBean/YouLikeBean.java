package com.cui.android.jianchengdichan.view.ui.fragment.adapter.adapterBean;

public class YouLikeBean {
    private String name;
    private String path;
    private String price;

    @Override
    public String toString() {
        return "YouLikeBean{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public YouLikeBean(String name, String path, String price) {
        this.name = name;
        this.path = path;
        this.price = price;
    }
}
