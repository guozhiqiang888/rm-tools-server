package com.farben.readme.bean.config;

import java.io.Serializable;
import java.util.List;

public class EroadConfigReq implements Serializable {

    private String id;
    private Section1 section1;
    private Section2 Section2;
    private List<Button> buttons;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Section1 getSection1() {
        return section1;
    }

    public void setSection1(Section1 section1) {
        this.section1 = section1;
    }

    public Section2 getSection2() {
        return Section2;
    }

    public void setSection2(Section2 section2) {
        Section2 = section2;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    @Override
    public String toString() {
        return "EroadConfigReq{" +
                "id='" + id + '\'' +
                ", section1=" + section1 +
                ", Section2=" + Section2 +
                ", buttons=" + buttons +
                '}';
    }
}
