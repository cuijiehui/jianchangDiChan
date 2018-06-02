package com.cui.android.jianchengdichan.http.bean;

import com.cui.android.jianchengdichan.view.ui.customview.ChildCommunityBean;

import java.io.Serializable;
import java.util.List;

public class CommunityBean implements Serializable {

    private String id;
    private String name;
    private List<ChildCommunityBean> child;

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

    public List<ChildCommunityBean> getList() {
        return child;
    }

    public void setList(List<ChildCommunityBean> list) {
        this.child = list;
    }

}
