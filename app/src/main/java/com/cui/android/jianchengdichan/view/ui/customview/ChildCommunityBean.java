package com.cui.android.jianchengdichan.view.ui.customview;

import java.io.Serializable;

public class ChildCommunityBean implements Serializable {

    public ChildCommunityBean() {
    }

    public ChildCommunityBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
