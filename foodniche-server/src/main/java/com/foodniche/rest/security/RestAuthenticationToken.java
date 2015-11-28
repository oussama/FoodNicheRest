package com.foodniche.rest.security;

import com.foodniche.db.entities.Users;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Alexey Dubrov
 *
 * Authentication token.
 */

public class RestAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 1L;

    private Users user;

    public RestAuthenticationToken(Users user, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);

        this.user = user;
        setAuthenticated(true);
    }

    public Users getUser() {
        return user;
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getPrincipal() {
        return user;
    }
}
