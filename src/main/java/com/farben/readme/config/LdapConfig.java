package com.farben.readme.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "ldap")
public class LdapConfig {

    @Value("${ldap.staffSuffix}")
    private String staffSuffix;

    @Value("${ldap.ldapAuthFilter}")
    private String ldapAuthFilter;

    @Value("${ldap.ldapSearchDC}")
    private String ldapSearchDC;

    private ConcurrentHashMap<String, String> ldapAttr;

    private Hashtable<String, String> ldapCtxHashTable;

    private List<String> ldapReturnedAtts;

    private List<String> ldapGroups;

    public String getStaffSuffix() {
        return staffSuffix;
    }

    public void setStaffSuffix(String staffSuffix) {
        this.staffSuffix = staffSuffix;
    }

    public ConcurrentHashMap<String, String> getLdapAttr() {
        return ldapAttr;
    }

    public void setLdapAttr(ConcurrentHashMap<String, String> ldapAttr) {
        this.ldapAttr = ldapAttr;
    }

    public Hashtable<String, String> getLdapCtxHashTable() {
        return ldapCtxHashTable;
    }

    public void setLdapCtxHashTable(Hashtable<String, String> ldapCtxHashTable) {
        this.ldapCtxHashTable = ldapCtxHashTable;
    }

    public List<String> getLdapReturnedAtts() {
        return ldapReturnedAtts;
    }

    public void setLdapReturnedAtts(List<String> ldapReturnedAtts) {
        this.ldapReturnedAtts = ldapReturnedAtts;
    }

    public String getLdapAuthFilter() {
        return ldapAuthFilter;
    }

    public void setLdapAuthFilter(String ldapAuthFilter) {
        this.ldapAuthFilter = ldapAuthFilter;
    }

    public String getLdapSearchDC() {
        return ldapSearchDC;
    }

    public void setLdapSearchDC(String ldapSearchDC) {
        this.ldapSearchDC = ldapSearchDC;
    }

    public List<String> getLdapGroups() {
        return ldapGroups;
    }

    public void setLdapGroups(List<String> ldapGroups) {
        this.ldapGroups = ldapGroups;
    }
}
