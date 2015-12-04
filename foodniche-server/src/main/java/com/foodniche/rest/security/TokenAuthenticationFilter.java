package com.foodniche.rest.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
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

    private AuthenticationFailureHandler failureHandler;
    private TokenSimpleUrlAuthenticationSuccessHandler successHandler;
    private RequestMatcher requestMatcher;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, String authPath) {
        this.authenticationManager = authenticationManager;
        this.successHandler = new TokenSimpleUrlAuthenticationSuccessHandler();
        this.failureHandler = new SimpleUrlAuthenticationFailureHandler();
        this.requestMatcher = new AntPathRequestMatcher(authPath);
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
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = httpRequest.getHeader(HEADER_SECURITY_TOKEN);

        if (token != null && token.length() > 0 && !requestMatcher.matches(httpRequest)) {
            logger.info("token found:" + token);

            AbstractAuthenticationToken userAuthenticationToken = authUserByToken(token);
            if (userAuthenticationToken == null) {
                unsuccessfulAuthentication(httpRequest, httpResponse, new AuthenticationServiceException(MessageFormat.format("Error | {0}", "Bad Token")));
            } else {
                successfulAuthentication(httpRequest, httpResponse, chain, userAuthenticationToken);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();

        failureHandler.onAuthenticationFailure(request, response, failed);
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);

        this.successHandler.onAuthenticationSuccess(request, response, authResult);
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
