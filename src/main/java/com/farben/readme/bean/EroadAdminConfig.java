package com.farben.readme.bean;


import java.io.Serializable;
import java.util.Arrays;

public class EroadAdminConfig extends BaseEntity implements Serializable {

    private String id;
    private String key;
    private String type;
    private byte[] value;
    private long version;
    private String createdByName;
    private String updatedByName;
    private String isPublished;
    private String publishedId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }


    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
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
        return "EroadAdminConfig{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", type='" + type + '\'' +
                ", value=" + Arrays.toString(value) +
                ", version=" + version +
                ", createdByName='" + createdByName + '\'' +
                ", updatedByName='" + updatedByName + '\'' +
                ", isPublished='" + isPublished + '\'' +
                ", publishedId='" + publishedId + '\'' +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                ", updatedTime=" + updatedTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
