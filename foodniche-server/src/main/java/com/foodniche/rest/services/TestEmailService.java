package com.foodniche.rest.services;

import com.foodniche.db.dao.UserDao;
import com.foodniche.db.entities.Users;
import com.foodniche.rest.model.TestBean;
import com.foodniche.rest.services.email.EmailAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 *
 */

@Component
@Path("/test/email")
public class TestEmailService {

    @Autowired
    private EmailAPI emailAPI;

    @GET
    @Path("/reg")
    @Produces("application/json")
    public TestBean emailReg() {
        Users userInfo = new Users();
        userInfo.setFirstname("John");
        userInfo.setLastname("Doe");
        userInfo.setEmail("aaborschenko@gmail.com");
        emailAPI.sendUserRegistrationMail(userInfo);
        return new TestBean("aaborschenko@gmail.com");
    }
}
