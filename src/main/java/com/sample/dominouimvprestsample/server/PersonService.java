package com.sample.dominouimvprestsample.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/person")
public class PersonService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPerson() {
        return "{\"firstName\" : \"Rafat\", \"lastName\" : \"Barouki\", \"phoneNumber\" : \"7894563210\", \"gender\" : \"male\"}";
    }
}
