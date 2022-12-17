package ir.farhad.simpleserver;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(value = "/proxy")
@ApplicationScoped
public class SimpleRestEndPoint {

    @Inject
    @RestClient
    SimpleRestServer service;
    @GET
    @Path("/text")
    public String getHello() {
        return service.getHello();
    }
    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleProperty getPropertyJSON(){
        return service.getPropertyJSON();
    }
}
