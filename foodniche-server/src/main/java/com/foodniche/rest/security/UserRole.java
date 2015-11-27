package com.foodniche.rest.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Alexey Dubrov
 *
 * User granted authority.
 *
 */

public class UserRole implements GrantedAuthority {

    private String roleName;

    public UserRole(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
