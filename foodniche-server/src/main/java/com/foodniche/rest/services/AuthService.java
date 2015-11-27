package com.foodniche.rest.services;


import com.foodniche.db.dao.UserDao;
import com.foodniche.db.entities.Users;
import com.foodniche.rest.model.AuthData;
import com.foodniche.rest.model.BaseResponse;
import com.foodniche.rest.model.DataResponse;
import com.foodniche.rest.model.LoginData;
import com.foodniche.rest.security.TokenAuthService;
import com.foodniche.rest.services.entities.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexey Dubrov
 *
 * Service for authentication.
 *
 */

@Component
@Path("/auth")
@Api(value = "/auth", description = "Authentication service")
public class AuthService {

    @Autowired
    private TokenAuthService tokenAuthService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @POST
    @Path("register")
    @Consumes("application/json")
    @Produces("application/json")
    @ApiOperation(value = "Register user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in Server")})
    public BaseResponse register(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);

        return new BaseResponse("Account registered success");
    }


    @POST
    @Path("login")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response login(LoginData login) {
        if (login != null && login.getUsername() != null) {
            try {
                String token = tokenAuthService.authenticateByCredentials(login.getUsername(), login.getPassword(), login.getRemember());

                return Response.ok().entity(new DataResponse<AuthData>("Logged in successfully", new AuthData(token))).build();
            } catch (Exception e) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Failed to authenticate").build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Failed to authenticate").build();
        }
    }
}
