package com.foodniche.rest;

import com.foodniche.rest.config.CorsFilter;
import io.swagger.jaxrs.config.BeanConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;


/**
 * @author Alexey Dubrov
 *
 * Main config or rest.
 *
 */

public class RestConfig extends ResourceConfig {

    public RestConfig() {
        // features
        register(MultiPartFeature.class);
        register(JacksonFeature.class);

        register(CorsFilter.class);

        // resources
        packages("com.foodniche.rest.services", "com.foodniche.rest.services.entities", "io.swagger.jaxrs.listing");
    }
}
