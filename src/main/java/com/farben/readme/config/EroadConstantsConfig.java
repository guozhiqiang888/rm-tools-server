package com.farben.readme.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "eroad")
public class EroadConstantsConfig {

    @Value("${eroad.hsbc.hsbcValidStaffUrl}")
    private String hsbcValidStaffUrl;

    @Value("${eroad.hsbc.tencentStaffId}")
    private String tencentStaffId;

    @Value("${eroad.hsbc.tencentStaffPwd}")
    private String tencentStaffPwd;

    @Value("${eroad.hsbc.tencentStaffMemberOf}")
    private String tencentStaffMemberOf;

    @Value("${eroad.hsbc.tencentStaffTabAuth}")
    private String tencentStaffTabAuth;

    public List<String> editAuthGroups;

    public String getHsbcValidStaffUrl() {
        return hsbcValidStaffUrl;
    }

    public void setHsbcValidStaffUrl(String hsbcValidStaffUrl) {
        this.hsbcValidStaffUrl = hsbcValidStaffUrl;
    }

    public String getTencentStaffId() {
        return tencentStaffId;
    }

    public void setTencentStaffId(String tencentStaffId) {
        this.tencentStaffId = tencentStaffId;
    }

    public String getTencentStaffPwd() {
        return tencentStaffPwd;
    }

    public void setTencentStaffPwd(String tencentStaffPwd) {
        this.tencentStaffPwd = tencentStaffPwd;
    }

    public String getTencentStaffMemberOf() {
        return tencentStaffMemberOf;
    }

    public void setTencentStaffMemberOf(String tencentStaffMemberOf) {
        this.tencentStaffMemberOf = tencentStaffMemberOf;
    }

    public String getTencentStaffTabAuth() {
        return tencentStaffTabAuth;
    }

    public void setTencentStaffTabAuth(String tencentStaffTabAuth) {
        this.tencentStaffTabAuth = tencentStaffTabAuth;
    }

    public List<String> getEditAuthGroups() {
        return editAuthGroups;
    }

    public void setEditAuthGroups(List<String> editAuthGroups) {
        this.editAuthGroups = editAuthGroups;
    }
}
