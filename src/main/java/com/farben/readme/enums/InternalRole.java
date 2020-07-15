package com.farben.readme.enums;

public enum InternalRole {

        LM("LM"),
        RM("RM"),
        BA("BA");

    private String internalValue;

    InternalRole (String value) {
        this.internalValue = value;
    }

    public String getValue(){
       return this.internalValue;
    }
}
