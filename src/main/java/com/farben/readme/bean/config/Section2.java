package com.farben.readme.bean.config;

import java.io.Serializable;
import java.util.List;

public class Section2 implements Serializable {

    private String title;
    private String value;
    private List<Part> items;

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

    public List<Part> getItems() {
        return items;
    }

    public void setItems(List<Part> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Section2{" +
                "title='" + title + '\'' +
                ", value='" + value + '\'' +
                ", items=" + items +
                '}';
    }
}
