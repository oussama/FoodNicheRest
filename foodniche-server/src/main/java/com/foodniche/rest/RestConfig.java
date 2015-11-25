package com.foodniche.rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ejb.Stateless;

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

        // resources
        packages("com.foodniche.rest.services", "com.foodniche.rest.services.entities");

        // swagger
        packages("io.swagger.jaxrs.listing");
    }
}
