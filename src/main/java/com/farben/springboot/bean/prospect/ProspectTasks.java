package com.farben.springboot.bean.prospect;


public class ProspectTasks {

  private String assigneeId;
  private String referrerId;
  private String prospectId;
  private String statusId;
  private java.sql.Timestamp createdTime;
  private java.sql.Timestamp lastModifiedTime;
  private String hasViewed;
  private java.sql.Date dueDate;
  private String priority;
  private String createdById;
  private String lastedModifiedById;
  private String id;


  public String getAssigneeId() {
    return assigneeId;
  }

  public void setAssigneeId(String assigneeId) {
    this.assigneeId = assigneeId;
  }


  public String getReferrerId() {
    return referrerId;
  }

  public void setReferrerId(String referrerId) {
    this.referrerId = referrerId;
  }


  public String getProspectId() {
    return prospectId;
  }

  public void setProspectId(String prospectId) {
    this.prospectId = prospectId;
  }


  public String getStatusId() {
    return statusId;
  }

  public void setStatusId(String statusId) {
    this.statusId = statusId;
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


  public String getHasViewed() {
    return hasViewed;
  }

  public void setHasViewed(String hasViewed) {
    this.hasViewed = hasViewed;
  }


  public java.sql.Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(java.sql.Date dueDate) {
    this.dueDate = dueDate;
  }


  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
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
