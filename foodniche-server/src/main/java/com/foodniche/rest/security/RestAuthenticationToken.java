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

    private List<GrantedAuthority> roles;

    public RestAuthenticationToken(Users user, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);

        this.user = user;

        roles = new ArrayList<>();
        roles.add(new UserRole(user.getRole()));

        setAuthenticated(true);
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return roles;
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
