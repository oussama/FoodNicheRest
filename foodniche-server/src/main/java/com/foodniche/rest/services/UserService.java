package com.foodniche.rest.services;

import com.foodniche.db.dao.ConnectionsDao;
import com.foodniche.db.dao.UploadFileDao;
import com.foodniche.db.dao.UserDao;
import com.foodniche.db.entities.UploadedFiles;
import com.foodniche.db.entities.Users;
import com.foodniche.rest.security.SecurityService;
import com.foodniche.rest.services.entities.ConnectionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Alexey Dubrov
 */

@Component
@Path("/api/user")
@Api(value = "/api/user", description = "User service")
public class UserService {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ConnectionsDao connectionsDao;

    @Autowired
    private UploadFileDao uploadFileDao;

    @GET
    @Path("/profile")
    @ApiOperation(value = "Get user profile")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "User not found")})
    public Response getUserProfile() {
        Users user = userDao.get(securityService.getCurrentUser().getUserid());

        return user != null ? Response.ok().entity(user).build() :
                Response.status(Response.Status.NO_CONTENT).entity("User not found").build();
    }

    @PUT
    @Path("/profile")
    @Consumes({"application/json"})
    @ApiOperation(value = "Update user profile")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "User not found")})
    public Response updateProfile(Users user) {
        user.setUserid(securityService.getCurrentUser().getUserid());

        user = userDao.save(user);

        return user != null ? Response.ok().entity(user).build() :
                Response.status(Response.Status.NO_CONTENT).entity("User not found").build();
    }

    @GET
    @Path("/my-connection")
    @ApiOperation(value = "My connections")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Server internal error")})
    public List<Users> getMyConnections() {
        return connectionsDao.getConnections(securityService.getCurrentUser());
    }

    @GET
    @Path("/my-album")
    @ApiOperation(value = "My album")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Server internal error")})
    public List<UploadedFiles> getMyAlbum() {
        return uploadFileDao.getUserImages(securityService.getCurrentUser());
    }
}
