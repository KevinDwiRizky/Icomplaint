package org.kevin.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.kevin.entities.enumPack.StatusEnum;

import java.util.Arrays;
import java.util.List;

@Path("/status-enum")
public class StatusEnumResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<StatusEnum> getStatusEnum() {
        return Arrays.asList(StatusEnum.values());
    }
}