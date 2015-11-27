package com.foodniche.rest.model;

/**
 * @author Alexey Dubrov
 *
 * Authenticated data
 */

public class AuthData {
    private String token;

    public AuthData(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
