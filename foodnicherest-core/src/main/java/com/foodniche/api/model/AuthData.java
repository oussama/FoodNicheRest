package com.foodniche.api.model;

/**
 * @author Alexey Dubrov
 *
 * Authenticated data
 */

public class AuthData {
    private Integer userId;
    private String token;

    public AuthData(Integer userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
