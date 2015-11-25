package com.foodniche.rest.services;


import com.foodniche.db.entities.Users;
import com.foodniche.rest.model.AuthData;
import com.foodniche.rest.model.BaseResponse;
import com.foodniche.rest.model.DataResponse;
import com.foodniche.rest.model.LoginData;
import com.foodniche.rest.services.entities.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public BaseResponse login(LoginData login) {
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
