package com.foxborn.enums;

public enum Gender {

//    MALE,FEMALE
    FEMALE("Female"),MALE("Male"),NA("WISH NOT TO DISCLOSE");

    private final String value;
    Gender(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
