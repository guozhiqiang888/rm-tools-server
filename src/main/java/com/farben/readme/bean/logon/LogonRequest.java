package com.farben.readme.bean.logon;

import java.io.Serializable;

public class LogonRequest implements Serializable {

    private String staffId;
    private String pwd;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Override
    public String toString() {
        return "LogonRequest{" +
                "staffId='" + staffId + '\'' +
                '}';
    }
}
