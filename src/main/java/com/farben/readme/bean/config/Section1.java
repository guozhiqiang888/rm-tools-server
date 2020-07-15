package com.farben.readme.bean.config;

import java.io.Serializable;
import java.util.List;

public class Section1 implements Serializable {

    private String title;
    private String value;
    private List<TabButton> items;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<TabButton> getItems() {
        return items;
    }

    public void setItems(List<TabButton> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Section1{" +
                "title='" + title + '\'' +
                ", value='" + value + '\'' +
                ", items=" + items +
                '}';
    }
}
