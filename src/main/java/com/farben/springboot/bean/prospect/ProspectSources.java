package com.farben.springboot.bean.prospect;


public class ProspectSources {

  private String id;
  private String prospectId;
  private String name;
  private String externalId;
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


  public String getProspectId() {
    return prospectId;
  }

  public void setProspectId(String prospectId) {
    this.prospectId = prospectId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getExternalId() {
    return externalId;
  }

  public void setExternalId(String externalId) {
    this.externalId = externalId;
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
