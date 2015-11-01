/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodnicherest.service;

import com.foodnicherest.Connections;
import com.foodnicherest.ConnectionsPK;
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
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author User
 */
@Stateless
@Path("com.foodnicherest.connections")
public class ConnectionsFacadeREST extends AbstractFacade<Connections> {
    @PersistenceContext(unitName = "com_FoodNicheRest_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    private ConnectionsPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;fromuserid=fromuseridValue;touserid=touseridValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.foodnicherest.ConnectionsPK key = new com.foodnicherest.ConnectionsPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> fromuserid = map.get("fromuserid");
        if (fromuserid != null && !fromuserid.isEmpty()) {
            key.setFromuserid(new java.lang.Integer(fromuserid.get(0)));
        }
        java.util.List<String> touserid = map.get("touserid");
        if (touserid != null && !touserid.isEmpty()) {
            key.setTouserid(new java.lang.Integer(touserid.get(0)));
        }
        return key;
    }

    public ConnectionsFacadeREST() {
        super(Connections.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Connections entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") PathSegment id, Connections entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        com.foodnicherest.ConnectionsPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Connections find(@PathParam("id") PathSegment id) {
        com.foodnicherest.ConnectionsPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Connections> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Connections> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
