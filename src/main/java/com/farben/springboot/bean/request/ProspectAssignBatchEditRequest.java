package com.farben.springboot.bean.request;

import java.io.Serializable;
import java.util.List;

public class ProspectAssignBatchEditRequest implements Serializable {

    private String assignId;

    private List<String> prospectId;

    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId;
    }

    public List<String> getProspectId() {
        return prospectId;
    }

    public void setProspectId(List<String> prospectId) {
        this.prospectId = prospectId;
    }

    @Override
    public String toString() {
        return "ProspectAssignBatchEditRequest{" +
                "assignId='" + assignId + '\'' +
                ", prospectId=" + prospectId +
                '}';
    }
}
