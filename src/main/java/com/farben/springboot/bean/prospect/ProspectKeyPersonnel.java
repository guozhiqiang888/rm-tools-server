package com.farben.springboot.bean.prospect;


public class ProspectKeyPersonnel {

  private String id;
  private String personId;
  private String prospectId;
  private String contactDetails;
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


  public String getPersonId() {
    return personId;
  }

  public void setPersonId(String personId) {
    this.personId = personId;
  }


  public String getProspectId() {
    return prospectId;
  }

  public void setProspectId(String prospectId) {
    this.prospectId = prospectId;
  }


  public String getContactDetails() {
    return contactDetails;
  }

  public void setContactDetails(String contactDetails) {
    this.contactDetails = contactDetails;
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
