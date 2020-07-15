package com.farben.readme.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private String headerTag;
    private String secret;
    private int expiration;

    public String getHeaderTag() {
        return headerTag;
    }

    public void setHeaderTag(String headerTag) {
        this.headerTag = headerTag;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }
}
