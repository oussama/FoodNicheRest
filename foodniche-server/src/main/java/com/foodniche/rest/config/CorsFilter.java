package com.foodniche.rest.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CorsFilter implements ContainerResponseFilter {

    static Logger LOGGER = LoggerFactory.getLogger(CorsFilter.class);

    private Set<String> allowedOrigins = new HashSet<>(Arrays.asList(
            "http://localhost:8082",
            "http://localhost:8081"
    ));

    public CorsFilter() {
        // empty
    }

    @Override
    public void filter(ContainerRequestContext req, ContainerResponseContext res) throws IOException {

        LOGGER.debug("Started com.foodniche.rest.config.CorsFilter.filter");

        String origin = req.getHeaderString("Origin");
        if (origin != null && allowedOrigins.contains(origin)) {

            MultivaluedMap<String, Object> headers = res.getHeaders();
            headers.add("Access-Control-Allow-Origin", origin);
            headers.add("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
            // Authorize CORS for 1 week
            headers.add("Access-Control-Max-Age", "604800");

            String requestHeaders = req.getHeaderString("Access-Control-Request-Headers");
            if (StringUtils.isNotBlank(requestHeaders)) {
                headers.add("Access-Control-Allow-Headers", requestHeaders);
            }
            if ("OPTIONS".equals(req.getMethod())) {
                res.setEntity(null);
                headers.add("Content-Type", "text/plain");
            }
        }

    }

}
