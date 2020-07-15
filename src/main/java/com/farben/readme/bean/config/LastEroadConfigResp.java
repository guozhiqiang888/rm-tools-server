package com.farben.readme.bean.config;

import java.io.Serializable;
import java.util.List;

public class LastEroadConfigResp implements Serializable {

    private Section1 section1;
    private Section2 Section2;
    private List<Button> buttons;
    private String savedby;
    private String savedTime;
    private String publishedBy;
    private String publishedTime;
    private String id;
    private String isPublished;
    private String publishedId;

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

    public String getSavedby() {
        return savedby;
    }

    public void setSavedby(String savedby) {
        this.savedby = savedby;
    }

    public String getSavedTime() {
        return savedTime;
    }

    public void setSavedTime(String savedTime) {
        this.savedTime = savedTime;
    }

    public String getPublishedBy() {
        return publishedBy;
    }

    public void setPublishedBy(String publishedBy) {
        this.publishedBy = publishedBy;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(String publishedTime) {
        this.publishedTime = publishedTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(String isPublished) {
        this.isPublished = isPublished;
    }

    public String getPublishedId() {
        return publishedId;
    }

    public void setPublishedId(String publishedId) {
        this.publishedId = publishedId;
    }

    @Override
    public String toString() {
        return "LastEroadConfigResp{" +
                "section1=" + section1 +
                ", Section2=" + Section2 +
                ", buttons=" + buttons +
                ", savedby='" + savedby + '\'' +
                ", savedTime='" + savedTime + '\'' +
                ", publishedBy='" + publishedBy + '\'' +
                ", publishedTime='" + publishedTime + '\'' +
                ", id='" + id + '\'' +
                ", isPublished='" + isPublished + '\'' +
                ", publishedId='" + publishedId + '\'' +
                '}';
    }
}
