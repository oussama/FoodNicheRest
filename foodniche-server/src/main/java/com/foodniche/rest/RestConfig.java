package com.foodniche.rest;

import com.foodniche.rest.config.CorsFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Alexey Dubrov
 *
 * Main config or rest.
 *
 */

public class RestConfig extends ResourceConfig {

    static Logger LOGGER = LoggerFactory.getLogger(RestConfig.class);

    public RestConfig() {
        LOGGER.debug("Creating RestConfig");

        // features
        register(MultiPartFeature.class);
        register(JacksonFeature.class);

        register(CorsFilter.class);

        // resources
        packages("com.foodniche.rest.services", "com.foodniche.rest.services.entities", "io.swagger.jaxrs.listing");
    }
}
