package com.foodniche.api.service;

import com.foodniche.api.model.AuthData;
import com.foodniche.api.model.BaseResponse;
import com.foodniche.api.model.DataResponse;
import com.foodniche.api.model.LoginData;
import com.foodnicherest.Users;
import com.foodnicherest.service.UsersService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexey Dubrov
 *
 * Service for authentication.
 *
 */

@Stateless
@Path("/")
@Api(value = "/auth", description = "Authentication service")
public class AuthService extends UsersService {

    @POST
    @Path("register")
    @Consumes("application/json")
    @Produces("application/json")
    @ApiOperation(value = "Register user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in Server")})
    public BaseResponse register(Users user) {
        create(user);

        return new BaseResponse("Account registered success");
    }


    @POST
    @Path("login")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public BaseResponse test(LoginData login) {
        BaseResponse response;

        Map<String, Object> params = new HashMap<>();
        params.put("username", login.getUsername());
        Users user = execSingleResult("Users.findByUsername", params);

        if (user != null) {
            response = new DataResponse<AuthData>("Logged in successful", new AuthData(user.getUserid(), "test_token"));
        } else {
            response = new BaseResponse("Failed to find user");
        }

        return response;
    }
}
