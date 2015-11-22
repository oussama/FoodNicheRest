package com.com.foodnicherest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;

/**
 * Created by ujuezeoke on 22/11/2015.
 */
@ApplicationPath("home")
public class ThisIsATest {

    @GET
    public String hello(){
        return "hello";
    }
}
