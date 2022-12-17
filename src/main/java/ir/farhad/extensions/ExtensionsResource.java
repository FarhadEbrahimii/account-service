package ir.farhad.extensions;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Set;
import java.util.concurrent.CompletionStage;

@Path(value = "/extensions")
public class ExtensionsResource {

    @Inject
    @RestClient
    ExtensionsService extensionsService;

    @GET
    @Path("/id/{id}")
    public Set<Extension> id(@PathParam("id") String id) {
        return extensionsService.getById(id);
    }

    @GET
    @Path("/id-async/{id}")
    public CompletionStage<Set<Extension>> idAsync(@PathParam("id") String id) {
        return extensionsService.getByIdAsync(id);
    }

    @GET
    @Path("/id-uni/{id}")
    public Uni<Set<Extension>> idMutiny(@PathParam("id") String id) {
        return extensionsService.getByIdAsUni(id);
    }

}
