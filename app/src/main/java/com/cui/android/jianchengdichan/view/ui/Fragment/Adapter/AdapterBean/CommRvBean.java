package com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.AdapterBean;

public class CommRvBean {
    int iconId;
    String title;
    String content;

    public CommRvBean(int iconId, String title, String content) {
        this.iconId = iconId;
        this.title = title;
        this.content = content;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

