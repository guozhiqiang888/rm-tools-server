package com.farben.springboot.bean.prospect;


public class Prospect {

  private String id;
  private String localName;
  private String englishName;
  private String industry;
  private String description;
  private String registeredCapital;
  private java.sql.Date dateFounded;
  private String address;
  private String province;
  private String city;
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


  public String getLocalName() {
    return localName;
  }

  public void setLocalName(String localName) {
    this.localName = localName;
  }


  public String getEnglishName() {
    return englishName;
  }

  public void setEnglishName(String englishName) {
    this.englishName = englishName;
  }


  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String getRegisteredCapital() {
    return registeredCapital;
  }

  public void setRegisteredCapital(String registeredCapital) {
    this.registeredCapital = registeredCapital;
  }


  public java.sql.Date getDateFounded() {
    return dateFounded;
  }

  public void setDateFounded(java.sql.Date dateFounded) {
    this.dateFounded = dateFounded;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
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
