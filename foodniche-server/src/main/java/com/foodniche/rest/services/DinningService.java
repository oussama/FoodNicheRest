package com.foodniche.rest.services;

import com.foodniche.db.dao.BusinessDao;
import com.foodniche.db.dao.BusinessTypesDao;
import com.foodniche.db.entities.Businesses;
import com.foodniche.db.entities.Businesstypes;
import com.foodniche.rest.model.NearRestaurants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * @author Alexey Dubrov
 */

@Component
@Path("/api/dinning")
@Api(value = "/api/dinning", description = "User service")
public class DinningService {

    @Autowired
    private BusinessTypesDao businessTypesDao;

    @Autowired
    private BusinessDao businessDao;

    @GET
    @Path("/types")
    @Produces("application/json")
    @ApiOperation(value = "Get dinning types")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in Server")})
    public List<Businesstypes> getTypesList() {
        return businessTypesDao.getAll();
    }

    @POST
    @Path("/restaurants")
    @Consumes("application/json")
    @Produces("application/json")
    @ApiOperation(value = "Get nearest restaurant by type and zip code")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in Server")})
    public List<Businesses> getRestaurants(NearRestaurants nearRestaurants) {
        return businessDao.getNearestRestaurants(nearRestaurants.getTypeId(), nearRestaurants.getZipCode());
    }

}
