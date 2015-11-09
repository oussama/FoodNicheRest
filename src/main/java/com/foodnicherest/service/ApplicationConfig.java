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
        resources.add(com.foodnicherest.service.BusinessesService.class);
        resources.add(com.foodnicherest.service.BusinesstypesService.class);
        resources.add(com.foodnicherest.service.ConnectionsService.class);
        resources.add(com.foodnicherest.service.ContentService.class);
        resources.add(com.foodnicherest.service.ContenttypesService.class);
        resources.add(com.foodnicherest.service.CoupondownloadsService.class);
        resources.add(com.foodnicherest.service.CouponsService.class);
        resources.add(com.foodnicherest.service.CoupontypesService.class);
        resources.add(com.foodnicherest.service.FoodpreferencesService.class);
        resources.add(com.foodnicherest.service.GroupsService.class);
        resources.add(com.foodnicherest.service.IngredientsService.class);
        resources.add(com.foodnicherest.service.MessagesService.class);
        resources.add(com.foodnicherest.service.PaymentsService.class);
        resources.add(com.foodnicherest.service.RecipesService.class);
        resources.add(com.foodnicherest.service.SubcsriptiontypesService.class);
        resources.add(com.foodnicherest.service.SubscriptionsService.class);
        resources.add(com.foodnicherest.service.UserfoodpreferencesService.class);
        resources.add(com.foodnicherest.service.UsersService.class);
    }
    
}
