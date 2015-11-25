/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodniche.rest.services.entities;

import com.foodniche.db.entities.Userfoodpreferences;
import com.foodniche.db.entities.UserfoodpreferencesPK;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.PathSegment;
import java.util.List;

/**
 *
 * @author User
 */

@Component
@Path("/userfoodpreferences")
@Api(value = "/userfoodpreferences", description = "This Rest Service will contain Userfoodpreferences Information")
public class UserfoodpreferencesService extends AbstractFacade<Userfoodpreferences> {
    @PersistenceContext
    private EntityManager em;

    private UserfoodpreferencesPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;userid=useridValue;foodpreferenceid=foodpreferenceidValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        UserfoodpreferencesPK key = new UserfoodpreferencesPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        List<String> userid = map.get("userid");
        if (userid != null && !userid.isEmpty()) {
            key.setUserid(new Integer(userid.get(0)));
        }
        List<String> foodpreferenceid = map.get("foodpreferenceid");
        if (foodpreferenceid != null && !foodpreferenceid.isEmpty()) {
            key.setFoodpreferenceid(new Integer(foodpreferenceid.get(0)));
        }
        return key;
    }

    public UserfoodpreferencesService() {
        super(Userfoodpreferences.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    @ApiOperation(value = "Create Userfoodpreferences ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public void create(Userfoodpreferences entity) {
        super.create(entity);
    }

    @PUT
    @Path("/{id}")
    @Consumes({"application/json"})
    @ApiOperation(value = "Edit Userfoodpreferences ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public void edit(@PathParam("id") PathSegment id, Userfoodpreferences entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Remove Userfoodpreferences ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public void remove(@PathParam("id") PathSegment id) {
        UserfoodpreferencesPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("/{id}")
    @Produces({"application/json"})
    @ApiOperation(value = "Find Userfoodpreferences ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Userfoodpreferences find(@PathParam("id") PathSegment id) {
        UserfoodpreferencesPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({"application/json"})
    @ApiOperation(value = "Find All Userfoodpreferences ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public List<Userfoodpreferences> findAll() {
        return super.findAll();
    }

    @GET
    @Path("/{from}/{to}")
    @Produces({"application/json"})
    @ApiOperation(value = "Find Range of Userfoodpreferences ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public List<Userfoodpreferences> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("/count")
    @Produces("text/plain")
    @ApiOperation(value = "Count Userfoodpreferences ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
