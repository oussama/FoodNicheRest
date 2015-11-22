/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodnicherest.service;

import com.foodnicherest.Recipes;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author User
 */
@Stateless
@Path("/recipes")
@Api(value = "/recipes", description = "This Rest Service will contain Recipes Information")
public class RecipesService extends AbstractFacade<Recipes> {
    @PersistenceContext(unitName = "FoodNicheRest")
    private EntityManager em;

    public RecipesService() {
        super(Recipes.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    @ApiOperation(value = "Create Recipes ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public void create(Recipes entity) {
        super.create(entity);
    }

    @PUT
    @Path("/{id}")
    @Consumes({"application/json"})
    @ApiOperation(value = "Edit Recipes ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public void edit(@PathParam("id") Integer id, Recipes entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Remove Recipes ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("/{id}")
    @Produces({"application/json"})
    @ApiOperation(value = "Find Recipes ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Recipes find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    @ApiOperation(value = "Find All Recipes ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public List<Recipes> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    @ApiOperation(value = "Find Range of Recipes ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public List<Recipes> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    @ApiOperation(value = "Count Recipes ")
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
