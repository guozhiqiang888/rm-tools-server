package com.farben.springboot.bean.request;

import java.io.Serializable;

public class ProspectRequest implements Serializable {
    private String PersonID;
    private String clientVisionName;
    private String contactPerson;
    private String confirmedOnBoardingName;
    private String status;

    public String getPersonID() {
        return PersonID;
    }

    public void setPersonID(String personID) {
        PersonID = personID;
    }

    public String getClientVisionName() {
        return clientVisionName;
    }

    public void setClientVisionName(String clientVisionName) {
        this.clientVisionName = clientVisionName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getConfirmedOnBoardingName() {
        return confirmedOnBoardingName;
    }

    public void setConfirmedOnBoardingName(String confirmedOnBoardingName) {
        this.confirmedOnBoardingName = confirmedOnBoardingName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProspectRequest{" +
                "PersonID='" + PersonID + '\'' +
                ", clientVisionName='" + clientVisionName + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", confirmedOnBoardingName='" + confirmedOnBoardingName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
