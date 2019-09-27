package com.farben.springboot.bean;

public class Corporation {
    private Integer id;
    private String corpNameCh;
    private String corpNameEn;
    private String corpImgUrl;
    private String legalRep;
    private Double regCapital;

    public Integer getId() {
        return id;
    }

    public String getCorpNameCh() {
        return corpNameCh;
    }

    public String getCorpNameEn() {
        return corpNameEn;
    }

    public String getCorpImgUrl() {
        return corpImgUrl;
    }

    public String getLegalRep() {
        return legalRep;
    }

    public Double getRegCapital() {
        return regCapital;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCorpNameCh(String corpNameCh) {
        this.corpNameCh = corpNameCh;
    }

    public void setCorpNameEn(String corpNameEn) {
        this.corpNameEn = corpNameEn;
    }

    public void setCorpImgUrl(String corpImgUrl) {
        this.corpImgUrl = corpImgUrl;
    }

    public void setLegalRep(String legalRep) {
        this.legalRep = legalRep;
    }

    public void setRegCapital(Double regCapital) {
        this.regCapital = regCapital;
    }
}
