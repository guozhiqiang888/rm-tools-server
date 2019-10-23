package com.farben.springboot.bean;

import java.io.Serializable;

public class SysUserPreferences extends BaseEntity implements Serializable {

  private String userId;
  private String perferences;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getPerferences() {
    return perferences;
  }

  public void setPerferences(String perferences) {
    this.perferences = perferences;
  }

}
