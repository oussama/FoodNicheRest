/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodnicherest.service;

import com.foodnicherest.Groups;
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
@Path("/groups")
@Api(value = "/groups", description = "This Rest Service will contain Groups Information")
public class GroupsService extends AbstractFacade<Groups> {
    @PersistenceContext(unitName = "com_FoodNicheRest_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public GroupsService() {
        super(Groups.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    @ApiOperation(value = "Create Groups ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public void create(Groups entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    @ApiOperation(value = "Edit Groups ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public void edit(@PathParam("id") Integer id, Groups entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    @ApiOperation(value = "Remove Groups ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    @ApiOperation(value = "Find Groups ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Groups find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    @ApiOperation(value = "Find All Groups ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public List<Groups> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    @ApiOperation(value = "Find Range of Groups ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public List<Groups> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    @ApiOperation(value = "Count Groups ")
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
