package com.farben.readme.mongodb.bean;

import org.springframework.data.mongodb.core.mapping.Document;

//user 是mongodb数据库中集合名
@Document(collection = "user")
public class User {
    private String id;
    private String account;
    private String password;
    private String right;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", right='" + right + '\'' +
                '}';
    }
}
