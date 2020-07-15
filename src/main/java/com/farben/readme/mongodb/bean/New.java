package com.farben.readme.mongodb.bean;

import com.farben.readme.bean.BaseEntity;
import com.farben.readme.constant.Constant;
import com.farben.readme.util.TimeUtil;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

//new 是mongodb数据库中集合名
@Document(collection = "new")
public class New extends BaseEntity implements Serializable {
    private String id;
    private String title;
    private String imgUrl;
    private boolean isSaved;
    private String createdTimeStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public String getCreatedTimeStr() {
        if(this.createdTime != null){
            return TimeUtil.getTime(this.createdTime, Constant.PATTERN_YYYY_MM_DD_HH_MM_SS);
        }
        return createdTimeStr;
    }

    public void setCreatedTimeStr(String createdTimeStr) {
        this.createdTimeStr = createdTimeStr;
    }

    @Override
    public String toString() {
        return "New{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", isSaved=" + isSaved +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                ", updatedTime=" + updatedTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
