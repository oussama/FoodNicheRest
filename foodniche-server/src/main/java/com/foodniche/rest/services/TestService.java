package com.foodniche.rest.services;

import com.foodniche.db.dao.UserDao;
import com.foodniche.db.entities.Users;
import com.foodniche.rest.model.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by agdubrov on 11/24/15.
 */

@Component
@Path("/api")
public class TestService {

    @Autowired
    private UserDao userDao;

    @GET
    @Produces("application/json")
    public TestBean test() {
        return new TestBean("test");
    }

    @GET
    @Path("user")
    @Produces("application/json")
    public Users find(@QueryParam("username") String username) {
        return userDao.findUserByName(username);
    }

    @GET
    @Path("version")
    @Produces("application/json")
    public TestBean version() throws IOException {
        Properties pomProperties = new Properties();
        pomProperties.load(TestService.class.getResourceAsStream("/META-INF/maven/com.foodniche/foodniche-server/pom.properties"));
        return new TestBean(pomProperties.getProperty("version"));
    }
}
