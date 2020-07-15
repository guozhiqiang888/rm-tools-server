package com.farben.readme.bean.config;

import java.io.Serializable;
import java.util.List;

public class Part implements Serializable {

    private String childName;
    private String childValue;
    private String dec;
    private List<Product> items;

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildValue() {
        return childValue;
    }

    public void setChildValue(String childValue) {
        this.childValue = childValue;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Part{" +
                "childName='" + childName + '\'' +
                ", childValue='" + childValue + '\'' +
                ", dec='" + dec + '\'' +
                ", items=" + items +
                '}';
    }
}
