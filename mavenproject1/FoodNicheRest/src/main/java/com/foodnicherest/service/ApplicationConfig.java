/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodnicherest.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author User
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.foodnicherest.service.BusinessesFacadeREST.class);
        resources.add(com.foodnicherest.service.BusinesstypesFacadeREST.class);
        resources.add(com.foodnicherest.service.ConnectionsFacadeREST.class);
        resources.add(com.foodnicherest.service.ContentFacadeREST.class);
        resources.add(com.foodnicherest.service.ContenttypesFacadeREST.class);
        resources.add(com.foodnicherest.service.CoupondownloadsFacadeREST.class);
        resources.add(com.foodnicherest.service.CouponsFacadeREST.class);
        resources.add(com.foodnicherest.service.CoupontypesFacadeREST.class);
        resources.add(com.foodnicherest.service.FoodpreferencesFacadeREST.class);
        resources.add(com.foodnicherest.service.GeographyColumnsFacadeREST.class);
        resources.add(com.foodnicherest.service.GeometryColumnsFacadeREST.class);
        resources.add(com.foodnicherest.service.GroupsFacadeREST.class);
        resources.add(com.foodnicherest.service.IngredientsFacadeREST.class);
        resources.add(com.foodnicherest.service.MessagesFacadeREST.class);
        resources.add(com.foodnicherest.service.PaymentsFacadeREST.class);
        resources.add(com.foodnicherest.service.RasterColumnsFacadeREST.class);
        resources.add(com.foodnicherest.service.RasterOverviewsFacadeREST.class);
        resources.add(com.foodnicherest.service.RecipesFacadeREST.class);
        resources.add(com.foodnicherest.service.SpatialRefSysFacadeREST.class);
        resources.add(com.foodnicherest.service.SubcsriptiontypesFacadeREST.class);
        resources.add(com.foodnicherest.service.SubscriptionsFacadeREST.class);
        resources.add(com.foodnicherest.service.UserfoodpreferencesFacadeREST.class);
        resources.add(com.foodnicherest.service.UsersFacadeREST.class);
    }
    
}
