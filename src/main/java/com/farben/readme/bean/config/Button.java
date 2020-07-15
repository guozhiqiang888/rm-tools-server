package com.farben.readme.bean.config;

import java.io.Serializable;

public class Button implements Serializable {

    private String title;
    private String url;
    private String eventContent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    @Override
    public String toString() {
        return "Button{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", eventContent='" + eventContent + '\'' +
                '}';
    }
}
