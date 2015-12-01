package com.foodniche.rest.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alexey Dubrov
 *
 * Entry point for rest.
 */

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    static Logger LOGGER = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException ) throws IOException, ServletException {
        LOGGER.debug("Hit com.foodniche.rest.security.RestAuthenticationEntryPoint.commence");
        String contentType = request.getContentType();
        logger.info(contentType);
        response.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized" );
    }

}