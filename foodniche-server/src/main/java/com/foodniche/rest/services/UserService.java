package com.foodniche.rest.services;

import com.foodniche.db.dao.*;
import com.foodniche.db.entities.*;
import com.foodniche.rest.model.BaseResponse;
import com.foodniche.rest.security.SecurityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
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

    @Autowired
    private GroupsDao groupsDao;

    @Autowired
    private UserGroupsDao userGroupsDao;

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

    @GET
    @Path("/profile/{id}")
    @ApiOperation(value = "Get user profile by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "User not found")})
    public Response getProfile(@PathParam("id") Integer id) {
        Users user = userDao.get(id);

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
    @Path("/connection/{id}")
    @Produces("application/json")
    @ApiOperation(value = "User's connections by userId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "User not found"),
            @ApiResponse(code = 500, message = "Server internal error")})
    public Response getConnection(@PathParam("id") Integer id) {
        Users userTo = userDao.get(id);

        Connections connections = connectionsDao.getConnection(securityService.getCurrentUser(), userTo);

        if (connections != null) {
            return Response.ok().entity(connections).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    @Path("/connections/{id}")
    @Produces("application/json")
    @ApiOperation(value = "User's connections by userId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "User not found"),
            @ApiResponse(code = 500, message = "Server internal error")})
    public List<Users> getConnections(@PathParam("id") Integer id) {
        List<Users> connectedUsers;
        Users fromUser = userDao.get(id);

        if (fromUser != null) {
            connectedUsers = connectionsDao.getConnections(fromUser);
        } else {
            connectedUsers = new ArrayList<>();
        }

        return connectedUsers;
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

    @GET
    @Path("/my-group")
    @ApiOperation(value = "My groups")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Server internal error")})
    public List<Groups> getMyGroup() {
        return userGroupsDao.getUserGroups(securityService.getCurrentUser());
    }

    @GET
    @Path("/group/{id}/members")
    @Produces("application/json")
    @ApiOperation(value = "Get all users in group")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Group not found"),
            @ApiResponse(code = 500, message = "Server internal error")})
    public Response getUserGroups(@PathParam("id") Integer id) {
        List<Users> users = userGroupsDao.getUsersInGroup(id);

        if (users != null) {
            return Response.ok().entity(users).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @POST
    @Path("/group/{id}")
    @Produces("application/json")
    @ApiOperation(value = "Add user to group")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Group not found"),
            @ApiResponse(code = 500, message = "Server internal error")})
    public Response addUserToGroup(@PathParam("id") Integer id) {
        Groups groups = groupsDao.get(id);

        if (groups != null) {
            userGroupsDao.addUserToGroup(groups, securityService.getCurrentUser());

            return Response.status(Response.Status.OK).entity(new BaseResponse("User was added to group")).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).entity(new BaseResponse("Failed to find group by id")).build();
        }
    }

    @PUT
    @Path("/group/{status}/{id}")
    @Produces("application/json")
    @ApiOperation(value = "approve/decline user group")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Group not found"),
            @ApiResponse(code = 500, message = "Server internal error")})
    public Response userGroupStatus(@PathParam("status")String status, @PathParam("id") Integer id) {
        Groups groups = groupsDao.get(id);

        if (groups != null) {
            if (status == null || !status.equals("approve") || status.equals("decline")) {
                return Response.status(Response.Status.NO_CONTENT).entity(new BaseResponse("Wrong status. Use 'approve' or 'decline'")).build();
            }
            UserGroupStatus ugStatus = status.equals("approve") ? UserGroupStatus.APPROVED : UserGroupStatus.DECLINED;

            if (!userGroupsDao.updateUserStatus(groups, securityService.getCurrentUser(), ugStatus)) {
                return Response.status(Response.Status.NO_CONTENT).entity(new BaseResponse("User was " + ugStatus.toString().toLowerCase())).build();
            } else {
                return Response.status(Response.Status.OK).entity(new BaseResponse("User was added to group")).build();
            }
        } else {
            return Response.status(Response.Status.NO_CONTENT).entity(new BaseResponse("Failed to find group by id")).build();
        }
    }

    @POST
    @Path("/invite-friend/{id}")
    @ApiOperation(value = "My groups")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Server internal error")})
    public Response sendInvitation(@PathParam("id") Integer id) {
        return Response.ok().build();
    }
}
