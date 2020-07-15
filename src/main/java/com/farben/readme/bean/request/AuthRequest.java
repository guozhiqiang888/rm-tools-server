package com.farben.readme.bean.request;

import java.io.Serializable;

public class AuthRequest implements Serializable {

    private String staffId;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Override
    public String toString() {
        return "AuthRequest{" +
                ", staffId='" + staffId + '\'' +
                '}';
    }
}
