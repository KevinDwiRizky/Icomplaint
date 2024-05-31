package org.kevin.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.kevin.config.IssuerInfo;

@Path("/issuer")
public class IssuerResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IssuerInfo getIssuerInfo() {
        return new IssuerInfo("MyApplication", "admin@myapplication.com", "https://www.myapplication.com");
    }
}

