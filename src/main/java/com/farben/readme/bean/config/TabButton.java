package com.farben.readme.bean.config;

import java.io.Serializable;

public class TabButton implements Serializable {

    private String name;
    private String dec;
    private String picurl;
    private String picurl2;
    private String picurl3;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getPicurl2() {
        return picurl2;
    }

    public void setPicurl2(String picurl2) {
        this.picurl2 = picurl2;
    }

    public String getPicurl3() {
        return picurl3;
    }

    public void setPicurl3(String picurl3) {
        this.picurl3 = picurl3;
    }

    @Override
    public String toString() {
        return "TabButton{" +
                "name='" + name + '\'' +
                ", dec='" + dec + '\'' +
                ", picurl='" + picurl + '\'' +
                ", picurl2='" + picurl2 + '\'' +
                ", picurl3='" + picurl3 + '\'' +
                '}';
    }
}
