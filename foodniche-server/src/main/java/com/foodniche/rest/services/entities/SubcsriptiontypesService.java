/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodniche.rest.services.entities;

import com.foodniche.db.entities.Subcsriptiontypes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import java.util.List;

/**
 *
 * @author User
 */

@Component
@Path("/subcsriptiontypes")
@Api(value = "/subcsriptiontypes", description = "This Rest Service will contain Subcsription Types Information")
public class SubcsriptiontypesService extends AbstractFacade<Subcsriptiontypes> {
    @PersistenceContext
    private EntityManager em;

    public SubcsriptiontypesService() {
        super(Subcsriptiontypes.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    @ApiOperation(value = "Create Subcsriptiontypes ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public void create(Subcsriptiontypes entity) {
        super.create(entity);
    }

    @PUT
    @Path("/{id}")
    @Consumes({"application/json"})
    @ApiOperation(value = "Edit Subcsriptiontypes ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public void edit(@PathParam("id") Integer id, Subcsriptiontypes entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Remove Subcsriptiontypes ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("/{id}")
    @Produces({"application/json"})
    @ApiOperation(value = "Find Subcsriptiontypes ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Subcsriptiontypes find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    @ApiOperation(value = "Find All Subcsriptiontypes ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public List<Subcsriptiontypes> findAll() {
        return super.findAll();
    }

    @GET
    @Path("/{from}/{to}")
    @Produces({"application/json"})
    @ApiOperation(value = "Find Range of Subcsriptiontypes ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public List<Subcsriptiontypes> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("/count")
    @Produces("text/plain")
    @ApiOperation(value = "Count Subcsriptiontypes ")
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
