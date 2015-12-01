package com.foodniche.rest.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * @author Alexey Dubrov
 *
 * Token authentication filter.
 *
 */

public class TokenAuthenticationFilter extends GenericFilterBean {
    private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    private AuthenticationManager authenticationManager;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    public final String HEADER_SECURITY_TOKEN = "X-Auth-Token";

    @Autowired
    private TokenAuthService authService;

    /**
     * Attempt to authenticate request - basically just pass over to another method to authenticate request headers
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String token = httpRequest.getHeader(HEADER_SECURITY_TOKEN);

        if (token != null && token.length() > 0) {
            logger.info("token found:" + token);

            AbstractAuthenticationToken userAuthenticationToken = authUserByToken(token);
            if (userAuthenticationToken == null) {
                throw new AuthenticationServiceException(MessageFormat.format("Error | {0}", "Bad Token"));
            }

            SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(userAuthenticationToken));
        }

        chain.doFilter(request, response);
    }


    /**
     * authenticate the user based on token
     * @return
     */
    private AbstractAuthenticationToken authUserByToken(String token) {
        if(token==null) {
            return null;
        }

        AbstractAuthenticationToken authToken = authService.authenticate(token);
        try {
            return authToken;
        } catch (Exception e) {
            logger.error("Authenticate user by token error: ", e);
        }
        return authToken;
    }

}
