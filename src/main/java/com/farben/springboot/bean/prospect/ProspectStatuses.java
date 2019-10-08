package com.farben.springboot.bean.prospect;


public class ProspectStatuses {

  private String id;
  private String name;
  private String action;
  private java.sql.Timestamp createdTime;
  private java.sql.Timestamp lastModifiedTime;
  private String createdById;
  private String lastedModifiedById;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }


  public java.sql.Timestamp getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(java.sql.Timestamp createdTime) {
    this.createdTime = createdTime;
  }


  public java.sql.Timestamp getLastModifiedTime() {
    return lastModifiedTime;
  }

  public void setLastModifiedTime(java.sql.Timestamp lastModifiedTime) {
    this.lastModifiedTime = lastModifiedTime;
  }


  public String getCreatedById() {
    return createdById;
  }

  public void setCreatedById(String createdById) {
    this.createdById = createdById;
  }


  public String getLastedModifiedById() {
    return lastedModifiedById;
  }

  public void setLastedModifiedById(String lastedModifiedById) {
    this.lastedModifiedById = lastedModifiedById;
  }

}
