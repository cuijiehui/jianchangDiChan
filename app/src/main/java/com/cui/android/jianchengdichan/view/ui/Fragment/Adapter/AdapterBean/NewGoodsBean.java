package com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.AdapterBean;

public class NewGoodsBean {
    private String name;
    private String path;
    private String price;

    public NewGoodsBean(String name, String path, String price) {
        this.name = name;
        this.path = path;
        this.price = price;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
