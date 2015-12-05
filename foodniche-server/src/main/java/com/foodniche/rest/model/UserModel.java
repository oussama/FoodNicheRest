package com.foodniche.rest.model;

/**
 * @author Alexey Dubrov
 *
 * Model for user registration
 */

public class UserModel {
    private String username;
    private String password;
    private boolean isBusiness;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBusiness() {
        return isBusiness;
    }

    public void setBusiness(boolean business) {
        isBusiness = business;
    }
}
