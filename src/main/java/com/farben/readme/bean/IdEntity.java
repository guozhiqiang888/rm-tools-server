package com.farben.readme.bean;

import java.io.Serializable;

public class IdEntity implements Serializable {

    protected String id;

    protected String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }
}
