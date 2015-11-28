package com.foodniche.rest.security;

import com.foodniche.db.entities.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Alexey Dubrov
 *
 * Spring security service.
 */

@Service
public class SecurityService {

    public Users getCurrentUser() {
        RestAuthenticationToken authToken = (RestAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return authToken.getUser();
    }
}
