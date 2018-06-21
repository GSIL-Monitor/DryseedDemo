package com.dryseed.dryseedapp.architecture.aac.demo.entity;

public class UserEntity {
    private String name;

    public UserEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
