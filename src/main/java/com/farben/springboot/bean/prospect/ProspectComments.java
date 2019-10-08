package com.farben.springboot.bean.prospect;


public class ProspectComments {

  private String prospectId;
  private String comment;
  private java.sql.Timestamp createdTime;
  private java.sql.Timestamp lastModifiedTime;
  private String createdById;
  private String lastedModifiedById;
  private String id;


  public String getProspectId() {
    return prospectId;
  }

  public void setProspectId(String prospectId) {
    this.prospectId = prospectId;
  }


  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
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


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

}
