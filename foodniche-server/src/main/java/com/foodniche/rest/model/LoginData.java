package com.foodniche.rest.model;

/**
 * @author Alexey Dubrov
 *
 * Authentication data json.
 */


public class LoginData {

    private String username;
    private String password;
    private Boolean remember;

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

    public Boolean getRemember() {
        return remember;
    }

    public void setRemember(Boolean remember) {
        this.remember = remember;
    }
}
