package com.foodniche.api;

import com.foodniche.api.service.AuthService;
import com.foodnicherest.service.GroupsService;
import com.foodnicherest.service.UsersService;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

/**
 * @author Alexey Dubrov
 *
 * Api application config.
 */

@ApplicationPath("api")
public class ApiApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        /**
         * Removing it removes the warning and Swagger still works as expected.
         *
         * resources.add(com.wordnik.swagger.jaxrs.JaxrsApiReader.class);
         *
         * WARNING: A provider com.wordnik.swagger.jaxrs.JaxrsApiReader
         * registered in SERVER runtime does not implement any provider
         * interfaces applicable in the SERVER runtime. Due to constraint
         * configuration problems the provider
         * com.wordnik.swagger.jaxrs.JaxrsApiReader will be ignored.
         */
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);

        resources.add(MultiPartFeature.class);
        resources.add(JacksonFeature.class);

        addRestResourceClasses(resources);

        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(AuthService.class);
        resources.add(GroupsService.class);
        resources.add(UsersService.class);
    }
}
