package ir.farhad.simpleserver;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient
@Path("/api")
public interface SimpleRestServer {
    @GET
    @Path("/text")
    String getHello();


    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    SimpleProperty getPropertyJSON();
}
