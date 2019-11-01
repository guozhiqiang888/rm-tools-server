package com.farben.springboot.bean.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class ProspectUpdateRequest implements Serializable {

    @JsonProperty("PersonID")
    private String PersonID;

    @JsonProperty("ClientVisionName")
    private String ClientVisionName;

    @JsonProperty("ContactPerson")
    private String ContactPerson;

    @JsonProperty("ConfirmedOnBoardingName")
    private String ConfirmedOnBoardingName;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("DueDate")
    private Date DueDate;

    @JsonProperty("Priority")
    private String Priority;

    @JsonProperty("AssigneeID")
    private String AssigneeID;

    @JsonProperty("HasViewed")
    private boolean HasViewed;

    @JsonProperty("HUBCustomerCode")
    private String HUBCustomerCode;

    @JsonProperty("ExpectedConversionDate")
    private Date ExpectedConversionDate;

    public String getPersonID() {
        return PersonID;
    }

    public void setPersonID(String personID) {
        PersonID = personID;
    }

    public String getClientVisionName() {
        return ClientVisionName;
    }

    public void setClientVisionName(String clientVisionName) {
        ClientVisionName = clientVisionName;
    }

    public String getContactPerson() {
        return ContactPerson;
    }

    public void setContactPerson(String contactPerson) {
        ContactPerson = contactPerson;
    }

    public String getConfirmedOnBoardingName() {
        return ConfirmedOnBoardingName;
    }

    public void setConfirmedOnBoardingName(String confirmedOnBoardingName) {
        ConfirmedOnBoardingName = confirmedOnBoardingName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Date getDueDate() {
        return DueDate;
    }

    public void setDueDate(Date dueDate) {
        DueDate = dueDate;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public String getAssigneeID() {
        return AssigneeID;
    }

    public void setAssigneeID(String assigneeID) {
        AssigneeID = assigneeID;
    }

    public boolean isHasViewed() {
        return HasViewed;
    }

    public void setHasViewed(boolean hasViewed) {
        HasViewed = hasViewed;
    }

    public String getHUBCustomerCode() {
        return HUBCustomerCode;
    }

    public void setHUBCustomerCode(String HUBCustomerCode) {
        this.HUBCustomerCode = HUBCustomerCode;
    }

    public Date getExpectedConversionDate() {
        return ExpectedConversionDate;
    }

    public void setExpectedConversionDate(Date expectedConversionDate) {
        ExpectedConversionDate = expectedConversionDate;
    }

    @Override
    public String toString() {
        return "ProspectUpdateRequest{" +
                "PersonID='" + PersonID + '\'' +
                ", ClientVisionName='" + ClientVisionName + '\'' +
                ", ContactPerson='" + ContactPerson + '\'' +
                ", ConfirmedOnBoardingName='" + ConfirmedOnBoardingName + '\'' +
                ", Status='" + Status + '\'' +
                ", DueDate=" + DueDate +
                ", Priority='" + Priority + '\'' +
                ", AssigneeID='" + AssigneeID + '\'' +
                ", HasViewed=" + HasViewed +
                ", HUBCustomerCode='" + HUBCustomerCode + '\'' +
                ", ExpectedConversionDate=" + ExpectedConversionDate +
                '}';
    }
}
